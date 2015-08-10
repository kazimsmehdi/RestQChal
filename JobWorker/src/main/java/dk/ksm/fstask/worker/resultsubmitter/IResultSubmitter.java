package dk.ksm.fstask.worker.resultsubmitter;

import dk.ksm.fstask.common.model.JobResult;

public interface IResultSubmitter {

    void submit(JobResult jobResult);
}
