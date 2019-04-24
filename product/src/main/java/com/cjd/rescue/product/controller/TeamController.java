package com.cjd.rescue.product.controller;

import com.cjd.rescue.api.shiro.UserApi;
import com.cjd.rescue.api.team.TeamApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.AddTeamParams;
import com.cjd.rescue.entity.product.Team;
import com.cjd.rescue.entity.product.UserTeamParam;
import com.cjd.rescue.entity.shiro.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamApi teamApi;



    @RequestMapping("/list")
    @SysLog
    public ReturnT list(Team team){
        return teamApi.list();
    }

    @RequestMapping("/add")
    @SysLog

    public ReturnT add(@RequestBody AddTeamParams addTeamParams){
        return teamApi.add(addTeamParams);
    }

    @RequestMapping("/delete")
    @SysLog

    public ReturnT delete(@RequestBody Team team){
        return teamApi.delete(team);
    }


    @RequestMapping("/get")
    @SysLog

    public ReturnT get(@RequestBody Team team){
        return teamApi.get(team);
    }


    @RequestMapping("/addUserTeam")
    @SysLog

    public ReturnT addUserTeam(@RequestBody UserTeamParam userTeamParam){
        return teamApi.addUserTeam(userTeamParam);
    }



    @RequestMapping("/listUser")
    public ReturnT listUser(@RequestBody User user){
        return teamApi.listUser(user);
    }

    @RequestMapping("/listTeamUser")
    public ReturnT listTeamUser(@RequestBody User user){
        return teamApi.listTeamUser(user);
    }


}
