package com.cjd.rescue.entity.shiro;

import java.util.List;

public class AssignRoleParams {

    private List<String> hasRoleIds;

    private String user_id;


    public List<String> getHasRoleIds() {
        return hasRoleIds;
    }

    public void setHasRoleIds(List<String> hasRoleIds) {
        this.hasRoleIds = hasRoleIds;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
