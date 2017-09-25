package com.ms.springbootdemo_version009.schedule;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * 定时任务配置
 * 
 * @author wuketao
 *
 */
@Component
public class MyScheduleConfig {

    /**
     * 配置一个线程池任务调度器
     * 
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);// 配置线程池尺寸
        return taskScheduler;
    }
}
