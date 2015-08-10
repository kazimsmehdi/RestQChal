package dk.ksm.fstask.service.broadcaster;

import com.fasterxml.jackson.core.JsonProcessingException;
import dk.ksm.fstask.common.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobBroadcaster implements IJobBroadcaster {
    private static final Logger log = LoggerFactory.getLogger(JobBroadcaster.class);

    @Override
    public void broadcast(Job job) {
        try {
            String valueAsString = job.toJsonString();
            BroadcastSocket.broadcast(valueAsString);
            log.info("job broadcasted");
        } catch (JsonProcessingException e) {
            log.error("Error in converting job to string job:" + job.toString(), e);
        }

    }
}
