package org.thingsboard.server.service.scheduler;

import java.util.concurrent.TimeUnit;

public interface SchedulerService {
    void schedule(Runnable task, long delay, TimeUnit timeUnit);
}

