package com.cjd.rescue.shiro.controller;

import com.cjd.rescue.api.shiro.RoleApi;
import com.cjd.rescue.api.team.TeamApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.AddTeamParams;
import com.cjd.rescue.entity.product.Team;
import com.cjd.rescue.entity.shiro.AddRoleParams;
import com.cjd.rescue.entity.shiro.AssignRoleParams;
import com.cjd.rescue.entity.shiro.Role;
import com.cjd.rescue.entity.shiro.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleApi roleApi;
    @RequestMapping("/list")
    @SysLog
    public ReturnT list(Team team){
        return roleApi.list();
    }

    @RequestMapping("/add")
    @SysLog

    public ReturnT add(@RequestBody AddRoleParams addRoleParams){
        return roleApi.add(addRoleParams);
    }

    @RequestMapping("/delete")
    @SysLog

    public ReturnT delete(@RequestBody Role role){
        return roleApi.delete(role);
    }


    @RequestMapping("/get")
    @SysLog

    public ReturnT get(@RequestBody Role role){
        return roleApi.get(role);
    }

    @RequestMapping("/assignRole")
    @SysLog
    public ReturnT assignRole(@RequestBody UserRole userRole){
        return roleApi.assignRole(userRole);
    }



    @RequestMapping("/assign")
    @SysLog
    public ReturnT assign(@RequestBody AssignRoleParams assignRoleParams){
        return roleApi.assign(assignRoleParams);
    }
}
