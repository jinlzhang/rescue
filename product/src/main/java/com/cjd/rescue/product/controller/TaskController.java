package com.cjd.rescue.product.controller;

import com.cjd.rescue.api.task.TaskApi;
import com.cjd.rescue.api.team.TeamApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.*;
import com.cjd.rescue.entity.shiro.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskApi taskApi;


    @RequestMapping("/add")
    @SysLog

    public ReturnT add(@RequestBody AddTaskParams addTaskParams){
        return taskApi.add(addTaskParams);
    }

    @RequestMapping("/list")
    public ReturnT list(@RequestBody Task task){
        return taskApi.list(task);
    }


    @RequestMapping("/get")
    public ReturnT get(@RequestBody Task task){
        return taskApi.get(task);
    }



    @RequestMapping("/update")
    @SysLog

    public ReturnT update(@RequestBody AddTaskParams addTaskParams){
        return taskApi.update(addTaskParams);
    }
}
