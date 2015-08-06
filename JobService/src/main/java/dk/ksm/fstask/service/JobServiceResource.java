package dk.ksm.fstask.service;

import dk.ksm.fstask.common.model.Job;
import dk.ksm.fstask.queue.IQueue;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JobServiceResource {

    private IQueue jobQueue;

    public JobServiceResource(IQueue jobQueue) {
        this.jobQueue = jobQueue;
    }


    @GET
    public List<Job> listJobs() {
        return jobQueue.listJobs();
    }

    @POST
    public Response addJob(Job job) {
        jobQueue.addJob(job);
        return Response.status(201).entity("job added").build();
    }
}
