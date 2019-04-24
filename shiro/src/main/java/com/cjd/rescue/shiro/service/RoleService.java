package com.cjd.rescue.shiro.service;

import com.cjd.rescue.api.shiro.RoleApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.anno.JDBCException;
import com.cjd.rescue.dao.common.SysKeyValueMapper;
import com.cjd.rescue.dao.shiro.RoleMapper;
import com.cjd.rescue.dao.shiro.UserRoleMapper;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.common.TypeOfKV;
import com.cjd.rescue.entity.shiro.AddRoleParams;
import com.cjd.rescue.entity.shiro.AssignRoleParams;
import com.cjd.rescue.entity.shiro.Role;
import com.cjd.rescue.entity.shiro.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements RoleApi {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private SysKeyValueMapper sysKeyValueMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;



    @SysLog
    @JDBCException
    @Override
    public ReturnT list() {

        Role params = new Role();
        params.setIs_delete("0");
        return ReturnT.result(Err.SUCCESS).dataMap("roleList",roleMapper.select(params));

    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT add(AddRoleParams addRoleParams) {
        Role role = addRoleParams.getRole();
        List<SysKeyValue> list = addRoleParams.getKvs();
        role.setId(IdUtil.generateID());
        role.setIs_delete("0");
        roleMapper.insertSelective(role);
        if(null != list && list.size() > 0){
            for(SysKeyValue sysKeyValue:list){
                sysKeyValue.setAssociate(TypeOfKV.ROLE.getPrefix() + role.getId());
                sysKeyValue.setId(IdUtil.generateID());
                sysKeyValueMapper.insertSelective(sysKeyValue);
            }
        }

        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT delete(Role role) {
        role.setIs_delete("1");
        roleMapper.updateByPrimaryKeySelective(role);
        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT get(Role role) {


        Role role1 =roleMapper.selectByPrimaryKey(role.getId());
        SysKeyValue sysKeyValue = new SysKeyValue();
        sysKeyValue.setAssociate(TypeOfKV.ROLE.getPrefix() + role.getId());
        List sysKeyValues = sysKeyValueMapper.select(sysKeyValue);

        return ReturnT.result(Err.SUCCESS).dataMap("sysKeyValues",sysKeyValues).dataMap("role",role1);
    }

    @SysLog
    @JDBCException
    @Override
    public ReturnT assignRole(UserRole userRole) {

        List<UserRole> userRoles = userRoleMapper.select(userRole);
        Role roleTemp = new Role();
        roleTemp.setIs_delete("0");
        List<Role> allRoles = roleMapper.select(roleTemp);

        List<String> hasRoleIds = new ArrayList<>();
        if(null != allRoles && allRoles.size() > 0){
            for (Role t:allRoles){
                if(null != userRoles && userRoles.size() > 0 ){
                    for (UserRole ut:userRoles){
                        if(t.getId().equals(ut.getRole_id())){

                            hasRoleIds.add(t.getId());
                        }

                    }
                }
            }
        }

        return ReturnT.result(Err.SUCCESS).dataMap("hasRoleIds",hasRoleIds).dataMap("allRoles",allRoles);
    }

    @Override
    public ReturnT assign(AssignRoleParams assignRoleParams) {
        String user_id = assignRoleParams.getUser_id();
        UserRole userRole = new UserRole();
        userRole.setUser_id(user_id);
        userRoleMapper.delete(userRole);
        List<String> role_ids = assignRoleParams.getHasRoleIds();
        if(null !=role_ids && role_ids.size() > 0){
            for (String id:role_ids){
                UserRole temp = new UserRole();
                temp.setUser_id(user_id);
                temp.setRole_id(id);
                temp.setId(IdUtil.generateID());
                userRoleMapper.insertSelective(temp);
            }
        }

        return ReturnT.result(Err.SUCCESS);
    }

}
