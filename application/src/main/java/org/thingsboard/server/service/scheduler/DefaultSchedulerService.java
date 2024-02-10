package org.thingsboard.server.service.scheduler;

import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class DefaultSchedulerService implements SchedulerService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    @Override
    public void schedule(Runnable task, long delay, TimeUnit timeUnit) {
        scheduler.scheduleAtFixedRate(task, delay, delay, timeUnit);
    }
}
