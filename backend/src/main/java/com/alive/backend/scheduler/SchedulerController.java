package com.alive.backend.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class SchedulerController {
    SchedulerService schedulerService;
    public SchedulerController(SchedulerService schedulerService){
        this.schedulerService = schedulerService;
    }
    @Scheduled(cron = "* 0/1 * * * *")
    public void aliveCheck(){
        System.out.println("현재 시간은 과연 몇시 "+new Date());
    }
}
