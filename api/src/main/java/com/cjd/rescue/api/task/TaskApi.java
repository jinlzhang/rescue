package com.cjd.rescue.api.task;

import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.AddTaskParams;
import com.cjd.rescue.entity.product.Task;

public interface TaskApi {
    ReturnT add(AddTaskParams addTaskParams);

    ReturnT list(Task task);

    ReturnT get(Task task);

    ReturnT update(AddTaskParams addTaskParams);
}
