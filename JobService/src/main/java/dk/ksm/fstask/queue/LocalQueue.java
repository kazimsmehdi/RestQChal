package dk.ksm.fstask.queue;

import dk.ksm.fstask.common.model.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksm on 0006 06-08-15.
 */
public class LocalQueue implements IQueue {
    private List<Job> jobs;

    public LocalQueue() {
        this.jobs = new ArrayList<Job>();
    }

    @Override
    public void addJob(Job job) {
        this.jobs.add(job);
    }

    @Override
    public List<Job> listJobs() {
        return this.jobs;
    }

    @Override
    public Job lastestJob() {
        if(this.jobs.size()>0) {
            return this.jobs.get(this.jobs.size() - 1);
        }

        return  null;
    }
}
