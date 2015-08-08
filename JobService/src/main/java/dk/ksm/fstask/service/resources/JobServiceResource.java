package dk.ksm.fstask.service.resources;

import com.codahale.metrics.annotation.Timed;
import dk.ksm.fstask.common.model.Job;
import dk.ksm.fstask.queue.QueueFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class JobServiceResource {

    @GET
    @Timed
    public List<Job> listJobs() {
        return QueueFactory.getQueue().listJobs();
    }

    @GET
    @Path("/latestjob")
    public Job latestJob() {
        return QueueFactory.getQueue().lastestJob();
    }

    @POST
    @Timed
    @Path("/addjob")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addJob(Job job) {
        QueueFactory.getQueue().addJob(job);
    }
}
