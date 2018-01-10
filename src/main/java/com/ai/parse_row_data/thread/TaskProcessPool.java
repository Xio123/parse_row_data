package com.ai.parse_row_data.thread;


import com.ai.parse_row_data.thread.process.TaskProcess;

/**
 * Created by rzhang on 2017/8/14.
 */
public interface TaskProcessPool {

    TaskProcess build();
}
