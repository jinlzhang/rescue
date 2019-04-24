package com.cjd.rescue.product.controller;

import com.cjd.rescue.api.plan.PlanApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.AddPlanParams;
import com.cjd.rescue.entity.product.AddProjectParams;
import com.cjd.rescue.entity.product.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanApi planApi;
    @RequestMapping("/list")
    @SysLog
    public ReturnT list(){
        return planApi.list();
    }

    @RequestMapping("/add")
    @SysLog

    public ReturnT add(@RequestBody AddPlanParams addProjectParams){
        return planApi.add(addProjectParams);
    }

    @RequestMapping("/delete")
    @SysLog

    public ReturnT delete(@RequestBody Plan plan){
        return planApi.delete(plan);
    }


    @RequestMapping("/get")
    @SysLog

    public ReturnT get(@RequestBody Plan plan){
        return planApi.get(plan);
    }
}
