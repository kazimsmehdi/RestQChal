package dk.ksm.fstask.service;


import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class JobService extends Application<JobServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new JobService().run(new String[]{"server"});
    }

    @Override
    public String getName() {
        return "Job-Service";
    }

    @Override
    public void run(JobServiceConfiguration jobServiceConfiguration
            , Environment environment) throws Exception {

        final JobServiceResource jobServiceResource = new JobServiceResource();
        environment.jersey().register(jobServiceResource);
    }
}
