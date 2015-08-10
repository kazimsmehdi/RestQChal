package dk.ksm.fstask.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;

import java.io.IOException;

public class Job {

    private static ObjectMapper mapper = new ObjectMapper();

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

    public static Job fromJsonString(String jsonString) throws IOException {
        Job job = mapper.readValue(jsonString, Job.class);
        return job;
    }

    @JsonProperty
    public String getJobType() {
        return jobType;
    }

    @JsonProperty
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @JsonProperty
    public String getUrl() {
        return url;
    }

    @JsonProperty
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty
    public String getSource() {
        return source;
    }

    @JsonProperty
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty
    public long getJobCreated() {
        return this.jobCreated.getMillis();
    }

    @JsonProperty
    public void setJobCreated(long jobCreated) {
        this.jobCreated = new DateTime(jobCreated);
    }

    public String toJsonString() throws JsonProcessingException {
        String valueAsString = mapper.writeValueAsString(this);
        return valueAsString;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobType='" + jobType + '\'' +
                ", url='" + url + '\'' +
                ", source='" + source + '\'' +
                ", jobCreated=" + jobCreated +
                '}';
    }
}
