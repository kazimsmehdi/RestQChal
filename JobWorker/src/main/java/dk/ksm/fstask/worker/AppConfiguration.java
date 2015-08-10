package dk.ksm.fstask.worker;

import org.hibernate.validator.constraints.NotEmpty;

public class AppConfiguration {
    @NotEmpty
    private String redisHost;

    @NotEmpty
    private int redisPort;

    @NotEmpty
    private String redisChannel;

    @NotEmpty
    private String mongoHost;

    @NotEmpty
    private int mongoPort;

    @NotEmpty
    private String mongoDB;

    public String getMongoHost() {
        return mongoHost;
    }

    public void setMongoHost(String mongoHost) {
        this.mongoHost = mongoHost;
    }

    public int getMongoPort() {
        return mongoPort;
    }

    public void setMongoPort(int mongoPort) {
        this.mongoPort = mongoPort;
    }

    public String getMongoDB() {
        return mongoDB;
    }

    public void setMongoDB(String mongoDB) {
        this.mongoDB = mongoDB;
    }

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
