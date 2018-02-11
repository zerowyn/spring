package com.example.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTest {
    private final static Logger log = LoggerFactory.getLogger(ScheduledTest.class);

    @Scheduled(cron = "0/5 * * * * ?")
    @Async
    public void executeTask3(){
        Thread thread = Thread.currentThread();
        log.info("task 3：{}-->{}",thread.getName(),thread.getId());
    }

}
