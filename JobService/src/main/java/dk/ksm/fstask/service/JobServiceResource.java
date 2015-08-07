package dk.ksm.fstask.service;

import dk.ksm.fstask.common.model.Job;
import dk.ksm.fstask.queue.QueueFactory;
import org.atmosphere.annotation.Broadcast;
import org.atmosphere.annotation.Suspend;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JobServiceResource {

//    @GET
//    public List<Job> listJobs() {
//        return jobQueue.listJobs();
//    }

//    @GET
//    @Path("/latestjob")
//    public Job latestJob() {
//        return this.jobQueue.lastestJob();
//    }

    @POST
    @Path("/addjob")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addJob(Job job) {
        QueueFactory.getQueue().addJob(job);

//        Broadcaster broadcaster = lookupBroadcaster();
//        broadcaster.broadcast(job);

        //  return Response.status(Response.Status.CREATED).entity("job added").build();
    }

    @Suspend(contentType = MediaType.APPLICATION_JSON)
    @GET
    public String suspend() {
        return "";
    }

    @Broadcast(writeEntity = false)
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Job broadcast() {

        Job job = QueueFactory.getQueue().lastestJob();
        return job;
    }



}
