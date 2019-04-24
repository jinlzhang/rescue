package com.cjd.rescue.entity.product;

import com.cjd.rescue.entity.shiro.User;

import java.util.List;

public class UserTeamParam {
    private String team_id;

    private List<User> user_ids;

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public List<User> getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(List<User> user_ids) {
        this.user_ids = user_ids;
    }
}
