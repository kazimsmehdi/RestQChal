package dk.ksm.fstask.service.broadcaster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.ksm.fstask.common.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobBroadcaster implements IJobBroadcaster {
    private static final Logger log = LoggerFactory.getLogger(JobBroadcaster.class);

    private final ObjectMapper objectMapper;

    public JobBroadcaster(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void broadcast(Job job) {
        try {
            String valueAsString = this.objectMapper.writeValueAsString(job);
            BroadcastSocket.broadcast(valueAsString);
            log.info("job broadcasted");
        } catch (JsonProcessingException e) {
            log.error("Error in converting job to string job:" + job.toString(), e);
        }

    }
}
