package org.thingsboard.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thingsboard.server.service.scheduler.DefaultSchedulerService;
import org.thingsboard.server.service.scheduler.SchedulerService;

@Configuration
public class SchedulerServiceConfig {

    @Bean
    public SchedulerService schedulerService() {
        return new DefaultSchedulerService();
    }
}
