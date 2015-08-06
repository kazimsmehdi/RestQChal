package dk.ksm.fstask.queue;

public class QueueFactory {

    public static IQueue getQueue(String queueType) {
        switch (queueType.toLowerCase()) {
            case "local":
                return new LocalQueue();
            default:
                return null;
        }
    }
}
