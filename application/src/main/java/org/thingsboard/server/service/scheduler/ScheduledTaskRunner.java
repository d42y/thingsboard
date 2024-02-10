package org.thingsboard.server.service.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTaskRunner implements CommandLineRunner {

    @Autowired
    private SchedulerService schedulerService;

    @Override
    public void run(String... args) throws Exception {
        schedulerService.schedule(this::printCurrentDateTime, 0, TimeUnit.SECONDS);
    }

    private void printCurrentDateTime() {
        System.out.println("Current DateTime: " + new Date());
    }
}

