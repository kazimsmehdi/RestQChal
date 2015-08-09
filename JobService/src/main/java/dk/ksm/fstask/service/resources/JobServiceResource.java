package dk.ksm.fstask.service.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.ksm.fstask.common.model.Job;
import dk.ksm.fstask.queue.QueueFactory;
import dk.ksm.fstask.service.ws.BroadcastSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class JobServiceResource {

    private static final Logger log = LoggerFactory.getLogger(BroadcastSocket.class);

    private final ObjectMapper objectMapper;

    public JobServiceResource(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GET
    @Timed
    public List<Job> listJobs() {
        return QueueFactory.getQueue().listJobs();
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public void addJob(Job job) {
        log.info("job received");
        QueueFactory.getQueue().addJob(job);
        log.info("job added to queue");

        broadcastJob(job);
    }

    private void broadcastJob(Job job) {
        try {
            String valueAsString = this.objectMapper.writeValueAsString(job);
            BroadcastSocket.broadcast(valueAsString);
            log.info("job broadcasted");
        } catch (JsonProcessingException e) {
            log.error("Error in converting job to string job:" + job.toString(), e);
        }


    }
}
