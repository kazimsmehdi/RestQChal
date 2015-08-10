package dk.ksm.fstask.worker.queue;

import dk.ksm.fstask.common.model.Job;
import dk.ksm.fstask.worker.AppConfiguration;
import dk.ksm.fstask.worker.JobWorker;
import dk.ksm.fstask.worker.resultsubmitter.IResultSubmitter;
import dk.ksm.fstask.worker.resultsubmitter.JobResultSubmitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueueMonitor {

    private static final Logger log = LoggerFactory.getLogger(QueueMonitor.class);
    private final String channel;
    private final Jedis jedisSubscriber;
    private final IResultSubmitter submitter;

    private final int processors = Runtime.getRuntime().availableProcessors();


    public QueueMonitor(AppConfiguration conf) throws UnknownHostException {
        // initialize jedis
        this.channel = conf.getRedisChannel();
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        final JedisPool jedisPool = new JedisPool(poolConfig, conf.getRedisHost(), conf.getRedisPort(), 0);

        this.jedisSubscriber = jedisPool.getResource();
        log.info("redis connection acquired!:" + this.jedisSubscriber.toString());

        // initialize Mongo
        this.submitter = new JobResultSubmitter(conf.getMongoHost(), conf.getMongoPort(), conf.getMongoDB());
    }

    public void start() {
        log.info("worker started");
        log.info("Total no of cpus: " + processors);
        Runnable queueMonitoring = () -> {
            try {
                log.info(String.format("Subscribing to \"%s\". This thread will be blocked.", this.channel));

                while (true) {
                    List<String> poppedString = this.jedisSubscriber.blpop(0, this.channel);
                    createWorker(poppedString.get(1));

                }
            } catch (Exception e) {
                log.error("Subscribing failed.", e);
            } finally {
                log.info("Subscription ended.");
            }
        };

        // start the thread
        new Thread(queueMonitoring).start();
    }


    private void createWorker(String message) {
        log.info("Message received. Channel: {}, Msg: {}", channel, message);
        try {
            Job job = Job.fromJsonString(message);
            log.info("Job Received!" + job.toString());
            ExecutorService executor = Executors.newFixedThreadPool(processors);
            executor.submit(() -> {
                JobWorker jobWorker = new JobWorker(job, this.submitter);
                jobWorker.process();
            });
        } catch (Exception e) {
            log.error("Error in receiving job", e);
        }
    }
}
