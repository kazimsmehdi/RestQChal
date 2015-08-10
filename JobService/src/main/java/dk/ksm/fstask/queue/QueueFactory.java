package dk.ksm.fstask.queue;

import dk.ksm.fstask.service.JobServiceConfiguration;

public class QueueFactory {
    public static IQueue getQueue(JobServiceConfiguration conf) {
        String queueType = conf.getQueueType();
        switch (queueType.toLowerCase()) {
            case "local":
                return new LocalQueue();
            case "redis":
                return new RedisQueue(conf.getRedisHost(), conf.getRedisPort(), conf.getRedisChannel());
            default:
                return null;
        }
    }
}
