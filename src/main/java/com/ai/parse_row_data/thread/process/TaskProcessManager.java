package com.ai.parse_row_data.thread.process;

import com.ai.parse_row_data.thread.TaskProcessPool;
import com.ai.parse_row_data.thread.pool.FixedTaskProcessPool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 异步任务执行管理器
 * 作用：根据业务域创建或者获取对应的执行器
 */

public class TaskProcessManager {

    private TaskProcessManager() {
    }

    /**
     * 执行器的容器，根据业务域来区分
     * 每个业务域只有一个执行器
     *
     * 每一种业务都会对应一种执行容器
     */
    private static Map<String, TaskProcess> taskProcessContainer = new ConcurrentHashMap<>();

    /**
     * 默认异步任务执行器工厂
     *
     * 默认异步任务执行器工厂
     */
    private static TaskProcessPool defaultTaskProcessPool = new FixedTaskProcessPool();

    /**
     * 获取执行器
     * 通过业务域获取执行器
     * @param businessDomain 业务域
     * @return 执行器
     */
    public static TaskProcess getTaskProcess(String businessDomain) {
        return getTaskProcess(businessDomain, defaultTaskProcessPool);
    }

    /**
     * 注册执行器
     *
     * @param businessDomain 业务域
     */
    public static TaskProcess getTaskProcess(String businessDomain, TaskProcessPool factory) {
        if (factory == null) {
            factory = defaultTaskProcessPool;
        }
        TaskProcess tmpProcess = taskProcessContainer.get(businessDomain);
        if (tmpProcess == null) {
            synchronized (TaskProcessManager.class) {
                tmpProcess = taskProcessContainer.get(businessDomain);
                if (tmpProcess == null) {
                    tmpProcess = factory.build();
                    taskProcessContainer.put(businessDomain, tmpProcess);
                }
            }
        }
        return tmpProcess;
    }
}
