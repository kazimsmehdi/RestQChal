package dk.ksm.fstask.service;


import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.config.FilterBuilder;
import com.yammer.dropwizard.config.HttpConfiguration;
import dk.ksm.fstask.queue.IQueue;
import dk.ksm.fstask.queue.QueueFactory;

import org.atmosphere.cpr.AtmosphereServlet;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.eclipse.jetty.servlets.CrossOriginFilter;


public class JobService extends Service<JobServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new JobService().run(args);
    }

    @Override
    public void initialize(Bootstrap<JobServiceConfiguration> bootstrap) {
        bootstrap.setName("job-service");
    }


    @Override
    public void run(JobServiceConfiguration conf
            , Environment environment) throws ClassNotFoundException {

        conf.getHttpConfiguration().setConnectorType(HttpConfiguration.ConnectorType.NONBLOCKING);

        String queueType = conf.getQueueType();
         QueueFactory.instantiateQueue(queueType);

        final JobServiceResource jobServiceResource = new JobServiceResource();
        environment.addResource(jobServiceResource);



        initializeAtmosphere(conf, environment);
    }

    private void initializeAtmosphere(JobServiceConfiguration conf, Environment environment) {
        FilterBuilder fconfig = environment.addFilter(CrossOriginFilter.class, "/lastjob");
        fconfig.setInitParam(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");

        AtmosphereServlet atmosphereServlet = new AtmosphereServlet();
        atmosphereServlet.framework().addInitParameter("com.sun.jersey.config.property.packages", "dk.ksm.fstask.service");
        atmosphereServlet.framework().addInitParameter("org.atmosphere.websocket.messageContentType", "application/json");
        environment.addServlet(atmosphereServlet, "/lastjob/*");

        Broadcaster broadcaster = lookupBroadcaster();
        broadcaster.add
    }

    Broadcaster lookupBroadcaster() {
        Broadcaster b = BroadcasterFactory.getDefault()
                .lookup("/lastjob", true);
        return b;
    }
}

