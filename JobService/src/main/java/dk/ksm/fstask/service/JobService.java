package dk.ksm.fstask.service;


import dk.ksm.fstask.queue.IQueue;
import dk.ksm.fstask.queue.QueueFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class JobService extends Application<JobServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new JobService().run(args);
    }

    @Override
    public String getName() {
        return "Job-Service";
    }

    @Override
    public void run(JobServiceConfiguration conf
            , Environment environment) throws Exception {

        String queueType = conf.getQueueType();
        IQueue queue = QueueFactory.getQueue(queueType);
        final JobServiceResource jobServiceResource = new JobServiceResource(queue);
        environment.jersey().register(jobServiceResource);
    }
}
