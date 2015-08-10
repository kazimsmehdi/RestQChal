package dk.ksm.fstask.service;

import dk.ksm.fstask.queue.IQueue;
import dk.ksm.fstask.queue.QueueFactory;
import dk.ksm.fstask.service.broadcaster.BroadcastServlet;
import dk.ksm.fstask.service.broadcaster.IJobBroadcaster;
import dk.ksm.fstask.service.broadcaster.JobBroadcaster;
import dk.ksm.fstask.service.resource.JobServiceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;
import java.util.EnumSet;


public class JobService extends Application<JobServiceConfiguration> {
    private static final Logger log = LoggerFactory.getLogger(JobService.class);

    public static void main(String[] args) {
        try {
            new JobService().run(args);
        } catch (Exception e) {

            log.error(e.toString());
        }
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

        log.info("Service started");
    }


    private void registerServices(JobServiceConfiguration conf, Environment env) {

        try {
            IQueue queue = QueueFactory.getQueue(conf);

            IJobBroadcaster jobBroadcaster = new JobBroadcaster();

            final JobServiceResource jobServiceResource = new JobServiceResource(queue, jobBroadcaster);

            env.jersey().register(jobServiceResource);

            env.getApplicationContext().getServletHandler().addServletWithMapping(
                    BroadcastServlet.class, "/broadcaster/*"
            );
        } catch (Exception e) {
            log.error("In registering service", e);
        }
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

