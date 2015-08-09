package dk.ksm.fstask.service.resource;

import dk.ksm.fstask.common.model.Job;
import dk.ksm.fstask.queue.LocalQueue;
import dk.ksm.fstask.service.broadcaster.IJobBroadcaster;
import dk.ksm.fstask.service.mock.JobBroadcasterMock;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.ws.rs.core.Response;
import java.util.List;

@Test(groups = "unit")
public class JobServiceResourceTest {

    @Test
    public void testListJobsWhenNoJobAdded() throws Exception {
        // arrange
        IJobBroadcaster jobBroadcaster = new JobBroadcasterMock(null);

        JobServiceResource jobServiceResource = new JobServiceResource(new LocalQueue(), jobBroadcaster);

        // act
        List<Job> jobs = jobServiceResource.listJobs();

        // assert
        Assert.assertNotNull(jobs);
        Assert.assertEquals(0, jobs.size());

    }


    public void testAddJob() throws Exception {
        // arrange
        IJobBroadcaster jobBroadcaster = new JobBroadcasterMock(null);

        JobServiceResource jobServiceResource = new JobServiceResource(new LocalQueue(), jobBroadcaster);

        Job job = new Job("crawling", "http://google.com", "zeta");

        // act
        Response response = jobServiceResource.addJob(job);

        // assert
        Assert.assertNotNull(response);
        Assert.assertEquals(201, response.getStatus());
    }

    @Test
    public void testListJobsWhenJobAdded() throws Exception {
        IJobBroadcaster jobBroadcaster = new JobBroadcasterMock(null);

        JobServiceResource jobServiceResource = new JobServiceResource(new LocalQueue(), jobBroadcaster);

        Job job = new Job("crawling", "http://google.com", "zeta");
        Response response = jobServiceResource.addJob(job);

        // act
        List<Job> jobs = jobServiceResource.listJobs();

        // assert
        Assert.assertNotNull(jobs);
        Assert.assertEquals(1, jobs.size());

    }
}