package com.ai.parse_row_data.thread.process;


import com.ai.parse_row_data.thread.TaskAction;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * 抽象类：提供了执行任务两种方法
 * 1.异步执行无返回结果的任务
 * 2.同步执行有返回结果的任务
 */
public abstract class AbstractTaskProcess {

    /**
     * concurrent执行器   包含一个执行框架
     */
    protected ExecutorService executor;

    /**
     * 使用信号量等待多个线程任务执行完成之后一次返回结果（阻塞方法）
     * 执行TaskAction并等待执行结果
     */
    public <T> List<T> executeTask(List<TaskAction<T>> tasks) throws InterruptedException {
        TaskAction<T>[] actions = new TaskAction[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            actions[i] = tasks.get(i);
        }
        return doExecuteTask(actions);
    }

    /**
     * 异步执行TaskAction，无须等待执行结果
     */
    public void asyncExecuteTask(List<TaskAction<?>> tasks) {
        TaskAction<?>[] actions = new TaskAction[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            actions[i] = tasks.get(i);
        }
        doAsyncExecuteTask(actions);
    }


    protected <T> List<T> doExecuteTask(TaskAction<T>[] tasks) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(tasks.length);
        List<T> resultList = new CopyOnWriteArrayList<>();
        for (final TaskAction<T> runnable : tasks) {
            executor.execute(() -> {
                try {
                    T result = runnable.doInAction();
                    if (Objects.nonNull(result)) {
                        resultList.add(result);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        return resultList;
    }

    protected void doAsyncExecuteTask(TaskAction<?>[] tasks) {
        for (final TaskAction<?> runnable : tasks) {
            executor.execute(runnable::doInAction);
        }
    }


}
