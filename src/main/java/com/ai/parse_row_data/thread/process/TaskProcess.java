package com.ai.parse_row_data.thread.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 该类继承自抽象的任务内
 * 主要提供了如下方法
 * 初始化一个exector 并保证exector是关闭状态
 */
public class TaskProcess extends AbstractTaskProcess {

    private static Logger LOGGER = LoggerFactory.getLogger(TaskProcess.class);

    /**
     * 核心线程池大小
     */
    private int coreSize;

    /**
     * 最大线程池大小
     */
    private int poolSize;

    /**
     * 空闲线程最大存活时间
     */
    private long keepAliveTime;

    /**
     * keepAliveTime时间单位
     */
    private TimeUnit timeUnit;

    /**
     * 阻塞任务队列
     * 申明了一个阻塞的任务队列
     */
    private BlockingQueue<Runnable> workQueue;

    /**
     * 拒绝任务的处理策略
     * 申明了一个拒绝任务的处理策略
     * 当Executor已经关闭（即执行了executorService.shutdown()方法后），
     * 并且Executor将有限边界用于最大线程数量和工作队列容量，且已经饱和时，在方法execute()中提交的新任务将被拒绝。
     */
    private RejectedExecutionHandler handler;

    private void buildExecutor() {
        /**
         * ThreadFactory
         * 在创建线程的时候，我们当然也能使用工厂模式来生产线程，ThreadFactory是用来实现创建线程的工厂接口，
         * 其实它只有一个方法Thread newThread(Runnable r)，所以这个接口没多大用，可以自己编写新接口。
         * 使用ThreadFactory工厂能替代默认的new Thread，而且在自定义工厂里面，我们能创建自定义化的Thread，并且计数，
         * 或则限制创建Thread的数量，给每个Thread设置对应的好听的名字，或则其他的很多很多事情。
         * t.setDaemon(true); 表示将该行程设置为守护线程 ，默认为fasle即用户线程
         *
         */
        ThreadFactory factory = r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        };


        executor = new ThreadPoolExecutor(coreSize, poolSize, keepAliveTime, timeUnit, workQueue,
                factory, handler);

        /**
         * Runtime类封装了运行时的环境。每个 Java 应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。
         *
         * Runtime.getRuntime()
         * 返回与当前 Java 应用程序相关的运行时对象。
         *
         * addShutdownHook() 注册新的虚拟机来关闭挂钩。  似乎时新创建一个线程来保证创建的执行框架是关闭的
         */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (executor == null) {
                return;
            }
            try {
                executor.shutdown();
                /**
                 * 接收timeout和TimeUnit两个参数，用于设定超时时间及单位。
                 * 当等待超过设定时间时，会监测ExecutorService是否已经关闭，若关闭则返回true，
                 * 否则返回false。一般情况下会和shutdown方法组合使用。
                 */
                executor.awaitTermination(5, TimeUnit.MINUTES);
            } catch (Exception e) {
                LOGGER.warn("interrupted when shut down the process executor:\n{}", e);
            }
        }));
    }

    public TaskProcess(int coreSize, int poolSize, long keepAliveTime, TimeUnit timeUnit,
                       BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        this.coreSize = coreSize;
        this.poolSize = poolSize;
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.workQueue = workQueue;
        this.handler = handler;
        buildExecutor();
    }
}
