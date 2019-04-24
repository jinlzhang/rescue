package com.cjd.rescue.product.service;

import com.cjd.rescue.api.task.TaskApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.common.resource.RES;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.anno.JDBCException;
import com.cjd.rescue.dao.common.SysKeyValueMapper;
import com.cjd.rescue.dao.product.TaskMapper;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.common.TypeOfKV;
import com.cjd.rescue.entity.product.AddTaskParams;
import com.cjd.rescue.entity.product.Task;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements TaskApi {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private SysKeyValueMapper sysKeyValueMapper;



    @SysLog
    @JDBCException
    @Override
    public ReturnT add(AddTaskParams addTaskParams) {
        Task task = addTaskParams.getTask();
        List<SysKeyValue> list = addTaskParams.getKvs();
        task.setId(IdUtil.generateID());
        taskMapper.insertSelective(task);
        inserSysKeyValues(list,task.getId());

        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT list(Task task) {


        Integer pageIndex = task.getPageIndex();

        Integer offset = (pageIndex - 1)* RES.limit;

        RowBounds rowBounds = new RowBounds(offset,RES.limit);


        List<Task> userList = taskMapper.selectByRowBounds(task,rowBounds);

        Integer total = taskMapper.selectCount(task);

        return ReturnT.result(Err.SUCCESS).dataMap("taskList",userList).dataMap("total",total);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT get(Task task) {


        Task temp =taskMapper.selectByPrimaryKey(task.getId());
        SysKeyValue sysKeyValue = new SysKeyValue();
        sysKeyValue.setAssociate(TypeOfKV.TASK.getPrefix() + temp.getId());
        List sysKeyValues = sysKeyValueMapper.select(sysKeyValue);

        return ReturnT.result(Err.SUCCESS).dataMap("sysKeyValues",sysKeyValues).dataMap("task",temp);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT update(AddTaskParams addTaskParams) {
        Task task = addTaskParams.getTask();

        SysKeyValue sysKeyValueParam = new SysKeyValue();
        sysKeyValueParam.setAssociate(TypeOfKV.TASK.getPrefix() + task.getId());
        sysKeyValueMapper.delete(sysKeyValueParam);

        List<SysKeyValue> list = addTaskParams.getKvs();
        taskMapper.updateByPrimaryKeySelective(task);
        inserSysKeyValues(list,task.getId());

        return ReturnT.result(Err.SUCCESS);
    }


    public void inserSysKeyValues(List<SysKeyValue> list,String associate_id){
        if(null != list && list.size() > 0){
            for(SysKeyValue sysKeyValue:list){
                sysKeyValue.setAssociate(TypeOfKV.TASK.getPrefix() + associate_id);
                sysKeyValue.setId(IdUtil.generateID());
                sysKeyValueMapper.insertSelective(sysKeyValue);
            }
        }
    }

}
