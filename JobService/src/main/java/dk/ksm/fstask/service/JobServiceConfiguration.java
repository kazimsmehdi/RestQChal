package dk.ksm.fstask.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class JobServiceConfiguration extends Configuration {

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
