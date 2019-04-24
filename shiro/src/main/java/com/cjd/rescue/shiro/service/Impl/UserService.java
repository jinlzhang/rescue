package com.cjd.rescue.shiro.service.Impl;

import com.cjd.rescue.api.shiro.UserApi;
import com.cjd.rescue.common.resource.RES;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.common.SysKeyValueMapper;
import com.cjd.rescue.dao.product.TeamMapper;
import com.cjd.rescue.dao.shiro.UserMapper;
import com.cjd.rescue.dao.product.UserTeamMapper;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.common.TypeOfKV;
import com.cjd.rescue.entity.product.Team;
import com.cjd.rescue.entity.shiro.AddUserParams;
import com.cjd.rescue.entity.shiro.User;
import com.cjd.rescue.entity.product.UserTeam;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService implements UserApi {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SysKeyValueMapper sysKeyValueMapper;

    @Autowired
    private UserTeamMapper userTeamMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public ReturnT add(AddUserParams addUserParams) {
        User user = addUserParams.getUser();
        String userId = IdUtil.generateID();
        user.setId(userId);
        userMapper.insertSelective(user);

        List<SysKeyValue> sysKeyValues = addUserParams.getKvs();


        if(null != sysKeyValues && sysKeyValues.size() > 0){
            for (SysKeyValue temp:sysKeyValues){
                temp.setId(IdUtil.generateID());
                temp.setAssociate(TypeOfKV.USER + userId);
                sysKeyValueMapper.insertSelective(temp);
            }
        }


        return ReturnT.result(Err.SUCCESS);
    }

    @Override
    public ReturnT list(User user) {
        Integer pageIndex = user.getPageIndex();

        Integer offset = (pageIndex - 1)* RES.limit;

        RowBounds rowBounds = new RowBounds(offset,RES.limit);


        List<User> userList = userMapper.selectByRowBounds(user,rowBounds);

        Integer total = userMapper.selectCount(user);

        return ReturnT.result(Err.SUCCESS).dataMap("userList",userList).dataMap("total",total);
    }

    @Override
    public ReturnT delete(User user) {
        user.setDelete_status("1");
        userMapper.updateByPrimaryKeySelective(user);
        return ReturnT.result(Err.SUCCESS);
    }

    @Override
    public ReturnT get(User user) {

        User userResult = userMapper.selectByPrimaryKey(user);
        SysKeyValue sysKeyValue = new SysKeyValue();
        sysKeyValue.setAssociate(TypeOfKV.USER.getPrefix() + userResult.getId());
        List sysKeyValues = sysKeyValueMapper.select(sysKeyValue);
        UserTeam userTeam = new UserTeam();
        userTeam.setUser_id(userResult.getId());
        UserTeam userTeamResult = userTeamMapper.selectOne(userTeam);
        Team resultTeam = null;
        if(null != userTeamResult){
            Team teamParam = new Team();
            teamParam.setId(userTeamResult.getTeam_id());
            resultTeam = teamMapper.selectByPrimaryKey(teamParam);
        }


        return ReturnT.result(Err.SUCCESS).dataMap("user",userResult).dataMap("sysKeyValues",sysKeyValues).dataMap("team",resultTeam);
    }
}
