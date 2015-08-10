package dk.ksm.fstask.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;


public class JobServiceConfiguration extends Configuration {
    @NotEmpty
    private String queueType;

    @NotEmpty
    private String redisHost;

    @NotEmpty
    private String redisPort;

    @NotEmpty
    private String redisChannel;

    @JsonProperty
    public String getRedisChannel() {
        return redisChannel;
    }

    @JsonProperty
    public void setRedisChannel(String redisChannel) {
        this.redisChannel = redisChannel;
    }

    @JsonProperty
    public String getRedisHost() {
        return redisHost;
    }

    @JsonProperty
    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    @JsonProperty
    public int getRedisPort() {
        return Integer.parseInt(this.redisPort);
    }

    @JsonProperty
    public void setRedisPort(String redisPort) {
        this.redisPort = redisPort;
    }

    @JsonProperty
    public String getQueueType() {
        return queueType;
    }

    @JsonProperty
    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }


}
