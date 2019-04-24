package com.cjd.rescue.dao.product;

import com.cjd.rescue.entity.product.UserTeam;
import com.cjd.rescue.entity.shiro.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface UserTeamMapper extends Mapper<UserTeam>{
    List<Map> selectUserIdToTeam(@Param("userList") List<User> userList);

    List<User> listTeamUser(String team_id);

    int listTeamUserCount(String team_id);
}
