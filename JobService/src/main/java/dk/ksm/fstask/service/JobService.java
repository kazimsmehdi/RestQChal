package dk.ksm.fstask.service;

import dk.ksm.fstask.queue.QueueFactory;
import dk.ksm.fstask.service.resources.JobServiceResource;
import dk.ksm.fstask.service.ws.BroadcastServlet;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import java.util.EnumSet;


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
    }

    @Override
    public void run(JobServiceConfiguration conf
            , Environment env) throws Exception {

        registerServices(conf, env);

        configureCors(env);
    }


    private void registerServices(JobServiceConfiguration conf, Environment env) {
        String queueType = conf.getQueueType();
        QueueFactory.instantiateQueue(queueType);

        final JobServiceResource jobServiceResource = new JobServiceResource(env.getObjectMapper());

        env.jersey().register(jobServiceResource);

        env.getApplicationContext().getServletHandler().addServletWithMapping(
                BroadcastServlet.class, "/ws/*"
        );
    }

    private void configureCors(Environment environment) {
        Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
    }
}

