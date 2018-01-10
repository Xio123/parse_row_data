package com.ai.parse_row_data.thread.pool;


import com.ai.parse_row_data.thread.TaskProcessPool;
import com.ai.parse_row_data.thread.process.TaskProcess;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 构造一个只支持一个线程的线程池，配置corePoolSize=maximumPoolSize=1，无界阻塞队列LinkedBlockingQueue；保证任务由一个线程串行执行
 */
public class SingleTaskProcessPool implements TaskProcessPool {

    @Override
    public TaskProcess build() {
        return new TaskProcess(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
