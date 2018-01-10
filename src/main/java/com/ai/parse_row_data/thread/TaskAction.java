package com.ai.parse_row_data.thread;


/**
 * 任务回调封装
 *
 * @param <T> 返回类型
 * @author wangchong
 */
public interface TaskAction<T> {

    T doInAction();
}