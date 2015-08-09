package dk.ksm.fstask.service.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.ksm.fstask.common.model.Job;
import dk.ksm.fstask.service.broadcaster.IJobBroadcaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobBroadcasterMock implements IJobBroadcaster {
    private static final Logger log = LoggerFactory.getLogger(JobBroadcasterMock.class);

    public JobBroadcasterMock(ObjectMapper objectMapper) {
    }

    @Override
    public void broadcast(Job job) {
    }
}
