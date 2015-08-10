package dk.ksm.fstask.common.model;


import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Document(collection = "JobResults")
public class JobResult {

    @Id
    private String id;

    private Job job;

    private DateTime jobFinishedAt;

    private String processedBy;

    private String status;

    public JobResult(Job job, String status) throws UnknownHostException {
        this.job = job;
        this.jobFinishedAt = DateTime.now();
        this.processedBy = InetAddress.getLocalHost().getHostName();
        this.status = status;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public DateTime getJobFinishedAt() {
        return jobFinishedAt;
    }

    public void setJobFinishedAt(DateTime jobFinishedAt) {
        this.jobFinishedAt = jobFinishedAt;
    }

    public String getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(String processedBy) {
        this.processedBy = processedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
