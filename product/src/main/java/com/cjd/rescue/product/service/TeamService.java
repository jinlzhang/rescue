package com.cjd.rescue.product.service;

import com.cjd.rescue.api.team.TeamApi;
import com.cjd.rescue.common.anno.SysLog;
import com.cjd.rescue.common.resource.RES;
import com.cjd.rescue.common.util.IdUtil;
import com.cjd.rescue.dao.anno.JDBCException;
import com.cjd.rescue.dao.common.SysKeyValueMapper;
import com.cjd.rescue.dao.product.TeamMapper;
import com.cjd.rescue.dao.product.UserTeamMapper;
import com.cjd.rescue.dao.shiro.UserMapper;
import com.cjd.rescue.entity.common.Err;
import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.common.TypeOfKV;
import com.cjd.rescue.entity.product.AddTeamParams;
import com.cjd.rescue.entity.product.Team;
import com.cjd.rescue.entity.product.UserTeam;
import com.cjd.rescue.entity.product.UserTeamParam;
import com.cjd.rescue.entity.shiro.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamService implements TeamApi {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private SysKeyValueMapper sysKeyValueMapper;

    @Autowired
    private UserTeamMapper userTeamMapper;

    @Autowired
    private UserMapper userMapper;

    @SysLog
    @JDBCException
    @Override
    public ReturnT list() {

        Team params = new Team();
        params.setIs_delete("0");
        return ReturnT.result(Err.SUCCESS).dataMap("teamList",teamMapper.select(params));

    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT add(AddTeamParams addTeamParams) {
        Team team = addTeamParams.getTeam();
        List<SysKeyValue> list = addTeamParams.getKvs();
        team.setId(IdUtil.generateID());
        team.setIs_delete("0");
        teamMapper.insertSelective(team);
        if(null != list && list.size() > 0){
            for(SysKeyValue sysKeyValue:list){
                sysKeyValue.setAssociate(TypeOfKV.TEAM.getPrefix() + team.getId());
                sysKeyValue.setId(IdUtil.generateID());
                sysKeyValueMapper.insertSelective(sysKeyValue);
            }
        }

        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT delete(Team team) {
        team.setIs_delete("1");
        teamMapper.updateByPrimaryKeySelective(team);
        return ReturnT.result(Err.SUCCESS);
    }
    @SysLog
    @JDBCException
    @Override
    public ReturnT get(Team team) {


        Team temp =teamMapper.selectByPrimaryKey(team.getId());
        SysKeyValue sysKeyValue = new SysKeyValue();
        sysKeyValue.setAssociate(TypeOfKV.TEAM.getPrefix() + team.getId());
        List sysKeyValues = sysKeyValueMapper.select(sysKeyValue);

        return ReturnT.result(Err.SUCCESS).dataMap("sysKeyValues",sysKeyValues).dataMap("team",temp);
    }

    @Override
    public ReturnT addUserTeam(UserTeamParam userTeamParam) {
        String team_id = userTeamParam.getTeam_id();

        List<User> user_ids =  userTeamParam.getUser_ids();

        if(!CollectionUtils.isEmpty(user_ids) ){

            for(User user:user_ids){
                UserTeam userTeam = new UserTeam();
                userTeam.setId(IdUtil.generateID());
                userTeam.setUser_id(user.getId());
                userTeam.setTeam_id(team_id);
                Example example = new Example(UserTeam.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("user_id", user.getId());
                userTeamMapper.deleteByExample(example);
                userTeamMapper.insertSelective(userTeam);
            }
        }

        return ReturnT.result(Err.SUCCESS);
    }

    @Override
    public ReturnT listUser(User user) {
        Integer pageIndex = user.getPageIndex();

        Integer offset = (pageIndex - 1)* RES.limit;

        RowBounds rowBounds = new RowBounds(offset,RES.limit);


        List<User> userList = userMapper.selectByRowBounds(user,rowBounds);


        List<Map> userId_teamName = userTeamMapper.selectUserIdToTeam(userList);
        Map utMap = new HashMap();
        if(null != userId_teamName && userId_teamName.size() > 0 ){
            for (Map te:userId_teamName){
                utMap.put(te.get("user_id"),te.get("name"));
            }
        }

        if(null != userList && utMap.size() > 0 ){
            for(User tu:userList){
                Object uname = utMap.get(tu.getId());
                if(null != uname){
                    tu.setTeam_name(uname.toString());

                }
            }

        }

        Integer total = userMapper.selectCount(user);

        return ReturnT.result(Err.SUCCESS).dataMap("userList",userList).dataMap("total",total);
    }

    @Override
    public ReturnT listTeamUser(User user) {


        Integer pageIndex = user.getPageIndex();

        Integer offset = (pageIndex - 1)* RES.limit;

        List<User> userList = userTeamMapper.listTeamUser(user.getTeam_id());

        int total = userTeamMapper.listTeamUserCount(user.getTeam_id());

        return ReturnT.result(Err.SUCCESS).dataMap("userList",userList).dataMap("total",total);
    }
}
