package dk.ksm.fstask.worker;

import dk.ksm.fstask.common.model.Job;
import dk.ksm.fstask.common.model.JobResult;
import dk.ksm.fstask.worker.resultsubmitter.IResultSubmitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobWorker {
    private static final Logger log = LoggerFactory.getLogger(JobWorker.class);
    private Job job;
    private IResultSubmitter submitter;

    public JobWorker(Job job, IResultSubmitter submitter) {

        this.job = job;
        this.submitter = submitter;
    }

    public void process() {

        try {
            JobResult jobResult = new JobResult(this.job, "Success");
            this.submitter.submit(jobResult);
        } catch (Exception e) {
            log.error("Error in saving result", e);
        }
    }
}
