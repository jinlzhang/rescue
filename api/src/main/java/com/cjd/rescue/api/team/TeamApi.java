package com.cjd.rescue.api.team;

import com.cjd.rescue.entity.common.ReturnT;
import com.cjd.rescue.entity.product.AddTeamParams;
import com.cjd.rescue.entity.product.Team;
import com.cjd.rescue.entity.product.UserTeamParam;
import com.cjd.rescue.entity.shiro.User;

public interface TeamApi {
    ReturnT list();
    ReturnT add(AddTeamParams addTeamParams);
    ReturnT delete(Team team);
    ReturnT get(Team team);

    ReturnT addUserTeam(UserTeamParam userTeamParam);

    ReturnT listUser(User user);

    ReturnT listTeamUser(User user);
}
