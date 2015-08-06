package dk.ksm.fstask.service;

import dk.ksm.fstask.common.model.Job;
import dk.ksm.fstask.queue.IQueue;
import dk.ksm.fstask.queue.LocalQueue;
import junit.framework.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.core.Response;
import java.util.List;

@Test(groups = "unit")
public class JobServiceResourceTest {

    @Test
    public void testListJobsWhenNoJobAdded() throws Exception {
        // arrange
        IQueue localQueue = new LocalQueue();
        JobServiceResource jobServiceResource = new JobServiceResource(localQueue);

        // act
        List<Job> jobs = jobServiceResource.listJobs();

        // assert
        Assert.assertNotNull(jobs);
        Assert.assertEquals(0, jobs.size());

    }

    @Test
    public void testAddJob() throws Exception {
        // arrange
        IQueue localQueue = new LocalQueue();
        JobServiceResource jobServiceResource = new JobServiceResource(localQueue);
        Job job = new Job("crawling", "http://google.com", "zeta");

        // act
        Response response = jobServiceResource.addJob(job);

        // assert
        Assert.assertNotNull(response);
        Assert.assertEquals(Response.Status.CREATED, response.getStatus());
    }

    @Test
    public void testListJobsWhenJobAdded() throws Exception {
        // arrange
        IQueue localQueue = new LocalQueue();
        JobServiceResource jobServiceResource = new JobServiceResource(localQueue);
        Job job = new Job("crawling", "http://google.com", "zeta");
        Response response = jobServiceResource.addJob(job);

        // act
        List<Job> jobs = jobServiceResource.listJobs();

        // assert
        Assert.assertNotNull(jobs);
        Assert.assertEquals(1, jobs.size());

    }
}