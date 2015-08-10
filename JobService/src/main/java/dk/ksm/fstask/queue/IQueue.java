package dk.ksm.fstask.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import dk.ksm.fstask.common.model.Job;

import java.util.List;

public interface IQueue {
    void addJob(Job job) throws JsonProcessingException;

    List<Job> listJobs();
}
