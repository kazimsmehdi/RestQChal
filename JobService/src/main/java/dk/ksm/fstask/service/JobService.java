package dk.ksm.fstask.service;

import dk.ksm.fstask.queue.QueueFactory;
import dk.ksm.fstask.service.resources.JobServiceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class JobService extends Application<JobServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new JobService().run(args);
    }

    @Override
    public String getName() {
        return "job-service";
    }

    @Override
    public void initialize(Bootstrap<JobServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(JobServiceConfiguration conf
            , Environment environment) throws ClassNotFoundException {

        String queueType = conf.getQueueType();
        QueueFactory.instantiateQueue(queueType);

        final JobServiceResource jobServiceResource = new JobServiceResource();

        environment.jersey().register(jobServiceResource);
    }
}

