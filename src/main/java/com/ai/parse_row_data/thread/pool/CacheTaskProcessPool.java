package com.ai.parse_row_data.thread.pool;

import com.ai.parse_row_data.thread.TaskProcessPool;
import com.ai.parse_row_data.thread.process.TaskProcess;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 构造一个缓冲功能的线程池，配置corePoolSize=0，maximumPoolSize=Integer.MAX_VALUE，
 * keepAliveTime=60s,以及一个无容量的阻塞队列 SynchronousQueue，
 * 因此任务提交之后，将会创建新的线程执行；线程空闲超过60s将会销毁
 */
public class CacheTaskProcessPool implements TaskProcessPool {

    private int coreSize;

    private int poolSize;

    public CacheTaskProcessPool(int coreSize, int poolSize) {
        this.coreSize = coreSize;
        this.poolSize = poolSize;
    }

    public CacheTaskProcessPool() {
        this.coreSize = 0;
        this.poolSize = Integer.MAX_VALUE;
    }

    /**
     * 实际上就是构造一个允许线程池为线程最大数量的线程池执行器
     * @return
     */
    @Override
    public TaskProcess build() {
        return new TaskProcess(coreSize, poolSize, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}