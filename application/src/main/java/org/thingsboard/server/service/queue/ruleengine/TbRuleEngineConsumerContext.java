package org.thingsboard.server.service.queue.ruleengine;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thingsboard.common.util.ThingsBoardThreadFactory;
import org.thingsboard.server.actors.ActorSystemContext;
import org.thingsboard.server.common.stats.StatsFactory;
import org.thingsboard.server.queue.TbQueueAdmin;
import org.thingsboard.server.queue.discovery.PartitionService;
import org.thingsboard.server.queue.discovery.TbServiceInfoProvider;
import org.thingsboard.server.queue.provider.TbQueueProducerProvider;
import org.thingsboard.server.queue.provider.TbRuleEngineQueueFactory;
import org.thingsboard.server.queue.util.TbRuleEngineComponent;
import org.thingsboard.server.service.queue.processing.TbRuleEngineProcessingStrategyFactory;
import org.thingsboard.server.service.queue.processing.TbRuleEngineSubmitStrategyFactory;
import org.thingsboard.server.service.stats.RuleEngineStatisticsService;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@TbRuleEngineComponent
@Slf4j
@Data
public class TbRuleEngineConsumerContext {

    @Value("${queue.rule-engine.poll-interval}")
    private long pollDuration;
    @Value("${queue.rule-engine.pack-processing-timeout}")
    private long packProcessingTimeout;
    @Value("${queue.rule-engine.stats.enabled:true}")
    private boolean statsEnabled;
    @Value("${queue.rule-engine.prometheus-stats.enabled:false}")
    boolean prometheusStatsEnabled;
    @Value("${queue.rule-engine.topic-deletion-delay:30}")
    private int topicDeletionDelayInSec;
    @Value("${queue.rule-engine.management-thread-pool-size:12}")
    private int mgmtThreadPoolSize;

    private final ActorSystemContext actorContext;
    private final StatsFactory statsFactory;
    private final TbRuleEngineSubmitStrategyFactory submitStrategyFactory;
    private final TbRuleEngineProcessingStrategyFactory processingStrategyFactory;
    private final TbRuleEngineQueueFactory queueFactory;
    private final RuleEngineStatisticsService statisticsService;
    private final TbServiceInfoProvider serviceInfoProvider;
    private final PartitionService partitionService;
    private final TbQueueProducerProvider producerProvider;
    private final TbQueueAdmin queueAdmin;

    private ExecutorService consumersExecutor;
    private ExecutorService mgmtExecutor;
    private ScheduledExecutorService scheduler;

    private volatile boolean isReady = false;

    @PostConstruct
    public void init() {
        this.consumersExecutor = Executors.newCachedThreadPool(ThingsBoardThreadFactory.forName("tb-rule-engine-consumer"));
        this.mgmtExecutor = Executors.newFixedThreadPool(mgmtThreadPoolSize, ThingsBoardThreadFactory.forName("tb-rule-engine-mgmt"));
        this.scheduler = Executors.newSingleThreadScheduledExecutor(ThingsBoardThreadFactory.forName("tb-rule-engine-consumer-scheduler"));
    }

    public void stop() {
        scheduler.shutdownNow();
        consumersExecutor.shutdown();
        mgmtExecutor.shutdown();
        try {
            mgmtExecutor.awaitTermination(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.warn("Failed to await mgmtExecutor termination");
        }
    }
}
