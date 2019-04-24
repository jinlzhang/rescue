package com.cjd.rescue.api.shiro;

import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.shiro.AddRoleParams;
import com.cjd.rescue.entity.shiro.AssignRoleParams;
import com.cjd.rescue.entity.shiro.Role;
import com.cjd.rescue.entity.shiro.UserRole;

public interface RoleApi {

    ReturnT list();
    ReturnT add(AddRoleParams addRoleParams);
    ReturnT delete(Role role);
    ReturnT get(Role role);
    ReturnT assignRole(UserRole userRole);

    ReturnT assign(AssignRoleParams assignRoleParams);
}
