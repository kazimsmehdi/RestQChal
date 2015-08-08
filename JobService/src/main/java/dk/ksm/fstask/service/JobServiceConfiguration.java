package dk.ksm.fstask.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;


public class JobServiceConfiguration extends Configuration {
    @NotEmpty
    private String queueType;

    @JsonProperty
    public String getQueueType() {
        return queueType;
    }

    @JsonProperty
    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }


}
