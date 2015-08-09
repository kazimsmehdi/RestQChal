package dk.ksm.fstask.service.broadcaster;

import dk.ksm.fstask.common.model.Job;

/**
 * Created by ksm on 0009 09-08-15.
 */
public interface IJobBroadcaster {
    void broadcast(Job job);
}
