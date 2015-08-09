package dk.ksm.fstask.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;


/**
 * Created by ksm on 0006 06-08-15.
 */
public class Job {

    private String jobType;
    private String url;
    private String source;
    private DateTime jobCreated;


    public Job() {
        this.jobCreated = DateTime.now();
    }

    public Job(String jobType, String url, String source) {
        this();

        this.jobType = jobType;
        this.url = url;
        this.source = source;
    }

    @JsonProperty
    public String getJobType() {
        return jobType;
    }

    @JsonProperty
    public String getUrl() {
        return url;
    }

    @JsonProperty
    public String getSource() {
        return source;
    }

    @JsonProperty
    public long getJobCreated() {
        return this.jobCreated.getMillis();
    }
}
