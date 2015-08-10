package dk.ksm.fstask.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.ksm.fstask.service.JobServiceConfiguration;

public class QueueFactory {
    public static IQueue getQueue(JobServiceConfiguration conf, ObjectMapper objectMapper) {
        String queueType = conf.getQueueType();
        switch (queueType.toLowerCase()) {
            case "local":
                return new LocalQueue();
            case "redis":
                return new RedisQueue(conf.getRedisHost(), conf.getRedisPort(), "JobQueue", objectMapper);
            default:
                return null;
        }
    }
}
