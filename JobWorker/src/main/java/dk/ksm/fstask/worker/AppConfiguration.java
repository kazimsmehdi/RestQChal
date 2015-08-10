package dk.ksm.fstask.worker;

import org.hibernate.validator.constraints.NotEmpty;

public class AppConfiguration {
    @NotEmpty
    private String redisHost;

    @NotEmpty
    private int redisPort;
    private String redisChannel;

    public String getRedisChannel() {
        return redisChannel;
    }

    public void setRedisChannel(String redisChannel) {
        this.redisChannel = redisChannel;
    }

    public String getRedisHost() {
        return redisHost;
    }


    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }


    public int getRedisPort() {
        return this.redisPort;
    }


    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

}
