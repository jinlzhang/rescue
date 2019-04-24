package com.cjd.rescue.shiro.controller;

import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.shiro.AddUserParams;
import com.cjd.rescue.entity.shiro.User;
import com.cjd.rescue.shiro.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @RequestMapping("/add")
    public ReturnT add(@RequestBody AddUserParams addUserParams){
        return userService.add(addUserParams);
    }

    @RequestMapping("/get")
    public ReturnT get(@RequestBody User user){
        return userService.get(user);
    }

    @RequestMapping("/list")
    public ReturnT list(@RequestBody User user){
        return userService.list(user);
    }
    @RequestMapping("/delete")
    public ReturnT delete(@RequestBody User user){
        return userService.delete(user);
    }

}
