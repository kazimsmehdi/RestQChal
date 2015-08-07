package dk.ksm.fstask.service;


import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.config.FilterBuilder;
import dk.ksm.fstask.queue.IQueue;
import dk.ksm.fstask.queue.QueueFactory;
import org.atmosphere.cpr.AtmosphereServlet;
import org.eclipse.jetty.servlets.CrossOriginFilter;


public class JobService extends Service<JobServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new JobService().run(args);
    }

    @Override
    public void initialize(Bootstrap<JobServiceConfiguration> bootstrap) {
        bootstrap.setName("job-service");
    }

    void initializeAtmosphere(JobServiceConfiguration configuration, Environment environment) {
        FilterBuilder fconfig = environment.addFilter(CrossOriginFilter.class, "/");
        fconfig.setInitParam(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");

        AtmosphereServlet atmosphereServlet = new AtmosphereServlet();
        atmosphereServlet.framework().addInitParameter("com.sun.jersey.config.property.packages", "dk.ksm.fstask.service");
        atmosphereServlet.framework().addInitParameter("org.atmosphere.websocket.messageContentType", "application/json");
        environment.addServlet(atmosphereServlet, "/*");
    }

    @Override
    public void run(JobServiceConfiguration conf
            , Environment environment) throws Exception {

        String queueType = conf.getQueueType();
        IQueue queue = QueueFactory.getQueue(queueType);
        final JobServiceResource jobServiceResource = new JobServiceResource(queue);
        environment.addResource(jobServiceResource);
    }
}

