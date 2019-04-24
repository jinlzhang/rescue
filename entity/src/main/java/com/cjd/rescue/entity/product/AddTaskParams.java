package com.cjd.rescue.entity.product;

import com.cjd.rescue.entity.common.BaseObject;
import com.cjd.rescue.entity.common.SysKeyValue;

import java.util.List;

public class AddTaskParams extends BaseObject {

    private Task task;

    private List<SysKeyValue> kvs;


    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<SysKeyValue> getKvs() {
        return kvs;
    }

    public void setKvs(List<SysKeyValue> kvs) {
        this.kvs = kvs;
    }
}
