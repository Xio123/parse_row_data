package com.ai.parse_row_data.thread.pool;


import com.ai.parse_row_data.thread.TaskProcessPool;
import com.ai.parse_row_data.thread.process.TaskProcess;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 构造一个固定线程数目的线程池，
 * 配置的corePoolSize与maximumPoolSize，
 * 同时使用了一个LinkedBlockingQueue存放阻塞任务
 */
public class FixedTaskProcessPool implements TaskProcessPool {

    /**
     * 核心线程数
     */
    private int coreSize;

    /**
     * 线程池大小
     */
    private int poolSize;

    /**
     * 自定义coreSize 与 poolSize 构造固定线程数的执行器
     * @param coreSize
     * @param poolSize
     */
    public FixedTaskProcessPool(int coreSize, int poolSize) {
        this.coreSize = coreSize;
        this.poolSize = poolSize;
    }

    /**
     * 通过环境的值来创建线程池数量
     */
    public FixedTaskProcessPool() {
        this.coreSize = Runtime.getRuntime().availableProcessors();
        this.poolSize = coreSize * 2;
    }

    /**
     * 构建一个拥有固定数量的线程池执行器
     * @return
     */
    @Override
    public TaskProcess build() {
        return new TaskProcess(coreSize, poolSize, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(coreSize), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
