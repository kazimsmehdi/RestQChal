package dk.ksm.fstask.service;

import dk.ksm.fstask.common.model.Job;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class JobServiceResource {

    private List<Job> jobs;

    public JobServiceResource() {
        this.jobs = new ArrayList<Job>();
    }

    @GET
    public List<Job> listJobs() {
        //return Arrays.asList(new Job("crawling","http://google.com","alpha"),new Job("extraction","http://google.com","beta"));
        return this.jobs;
    }

    @POST
    public void addJob(Job job) {
        this.jobs.add(job);
    }
}
