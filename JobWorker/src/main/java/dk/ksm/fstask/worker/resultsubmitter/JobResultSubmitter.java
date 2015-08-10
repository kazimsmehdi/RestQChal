package dk.ksm.fstask.worker.resultsubmitter;


import com.mongodb.MongoClient;
import dk.ksm.fstask.common.model.JobResult;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

public class JobResultSubmitter implements IResultSubmitter {

    private final MongoOperations mongoOperation;

    public JobResultSubmitter(String mongoHost, int mongoPort, String mongoDB) throws UnknownHostException {
        this.mongoOperation = new MongoTemplate(new MongoClient(mongoHost, mongoPort), mongoDB);
    }

    @Override
    public void submit(JobResult jobResult) {
        this.mongoOperation.save(jobResult);
    }
}
