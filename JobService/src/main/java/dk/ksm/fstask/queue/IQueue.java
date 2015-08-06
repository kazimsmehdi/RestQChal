package dk.ksm.fstask.queue;

import dk.ksm.fstask.common.model.Job;

import java.util.List;

public interface IQueue {
    void addJob(Job job);

    List<Job> listJobs();
}
