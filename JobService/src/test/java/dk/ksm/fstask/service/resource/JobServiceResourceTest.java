package dk.ksm.fstask.service.resource;

import dk.ksm.fstask.common.model.Job;
import dk.ksm.fstask.queue.LocalQueue;
import dk.ksm.fstask.service.broadcaster.IJobBroadcaster;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@Test(groups = "unit")
public class JobServiceResourceTest {
    private IJobBroadcaster mockJobBroadcaster;

    @BeforeClass
    public void init() {
        this.mockJobBroadcaster = Mockito.mock(IJobBroadcaster.class);
        doNothing().when(mockJobBroadcaster).broadcast(any(Job.class));
    }

    @Test
    public void testListJobsWhenNoJobAdded() throws Exception {
        // arrange
        JobServiceResource jobServiceResource = new JobServiceResource(new LocalQueue(), this.mockJobBroadcaster);

        // act
        List<Job> jobs = jobServiceResource.listJobs();

        // assert
        Assert.assertNotNull(jobs);
        Assert.assertEquals(0, jobs.size());
    }

    public void testAddJob() throws Exception {
        // arrange
        JobServiceResource jobServiceResource = new JobServiceResource(new LocalQueue(), this.mockJobBroadcaster);

        Job job = new Job("crawling", "http://google.com", "zeta");

        // act
        Response response = jobServiceResource.addJob(job);

        // assert
        Assert.assertNotNull(response);
        Assert.assertEquals(201, response.getStatus());
    }

    @Test
    public void testListJobsWhenJobAdded() throws Exception {
        // arrange
        JobServiceResource jobServiceResource = new JobServiceResource(new LocalQueue(), this.mockJobBroadcaster);

        Job job = new Job("crawling", "http://google.com", "zeta");
        Response response = jobServiceResource.addJob(job);

        // act
        List<Job> jobs = jobServiceResource.listJobs();

        // assert
        Assert.assertNotNull(jobs);
        Assert.assertEquals(1, jobs.size());
    }
}