package com.cjd.rescue.product.controller;

import com.cjd.rescue.api.product.ProductApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.AddProjectParams;
import com.cjd.rescue.entity.product.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProductApi productApi;
    @RequestMapping("/list")
    @SysLog
    public ReturnT list(Project resource){
        return productApi.list(resource);
    }

    @RequestMapping("/add")
    @SysLog

    public ReturnT add(@RequestBody AddProjectParams addProjectParams){
        return productApi.add(addProjectParams);
    }

    @RequestMapping("/delete")
    @SysLog

    public ReturnT delete(@RequestBody Project project){
        return productApi.delete(project);
    }


    @RequestMapping("/get")
    @SysLog

    public ReturnT get(@RequestBody Project project){
        return productApi.get(project);
    }
}
