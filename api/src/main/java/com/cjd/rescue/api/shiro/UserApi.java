package com.cjd.rescue.api.shiro;

import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.shiro.AddUserParams;
import com.cjd.rescue.entity.shiro.User;


public interface UserApi {

    ReturnT add(AddUserParams addUserParams);


    ReturnT list(User user);

    ReturnT delete(User user);

    ReturnT get(User user);
}
