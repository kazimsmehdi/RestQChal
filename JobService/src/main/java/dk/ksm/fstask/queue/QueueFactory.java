package dk.ksm.fstask.queue;

public class QueueFactory {

    private static volatile IQueue queue;

    public static IQueue getQueue() {
        return queue;
    }

    public static void instantiateQueue(String queueType) {
        if (null == queue) {
            synchronized (QueueFactory.class) {
                if (null == queue) {
                    switch (queueType.toLowerCase()) {
                        case "local":
                            queue = new LocalQueue();
                            break;
                        default:
                            queue = null;
                            break;
                    }
                }
            }
        }
    }
}
