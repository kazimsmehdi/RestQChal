package dk.ksm.fstask.service.resource;

import com.codahale.metrics.annotation.Timed;
import dk.ksm.fstask.common.model.Job;
import dk.ksm.fstask.queue.IQueue;
import dk.ksm.fstask.service.broadcaster.IJobBroadcaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class JobServiceResource {

    private static final Logger log = LoggerFactory.getLogger(JobServiceResource.class);

    private IQueue queue;
    private IJobBroadcaster jobBroadcaster;

    public JobServiceResource(IQueue queueFactory, IJobBroadcaster jobBroadcaster) {
        this.queue = queueFactory;
        this.jobBroadcaster = jobBroadcaster;
    }

    @GET
    @Timed
    public List<Job> listJobs() {
        return this.queue.listJobs();
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addJob(Job job) {

        try {
            log.info("job received");

            this.queue.addJob(job);

            log.info("job added to queue");

            this.jobBroadcaster.broadcast(job);

            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            log.error("Error in converting job to string job:" + job.toString(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
