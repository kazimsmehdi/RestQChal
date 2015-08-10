package dk.ksm.fstask.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import dk.ksm.fstask.common.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

public class RedisQueue implements IQueue {
    private static final Logger log = LoggerFactory.getLogger(RedisQueue.class);

    private final Jedis publisher;
    private final String channel;

    public RedisQueue(String redisHost, int redisPort, String channel) {
        this.channel = channel;

        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        final JedisPool jedisPool = new JedisPool(poolConfig, redisHost, redisPort, 0);
        this.publisher = jedisPool.getResource();
        log.info("redis connection acquired!:" + this.publisher.toString());
    }

    @Override
    public void addJob(Job job) throws JsonProcessingException {
        String valueAsString = job.toJsonString();
        //this.publisher.publish(this.channel, valueAsString);
        this.publisher.rpush(this.channel, valueAsString);
        log.info("Job published to queue");
    }

    @Override
    public List<Job> listJobs() {
        return null;
    }
}
