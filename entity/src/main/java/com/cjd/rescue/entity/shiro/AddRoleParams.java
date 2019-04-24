package com.cjd.rescue.entity.shiro;

import com.cjd.rescue.entity.common.SysKeyValue;

import java.util.List;

public class AddRoleParams {

    private Role role;

    private List<SysKeyValue> kvs;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<SysKeyValue> getKvs() {
        return kvs;
    }

    public void setKvs(List<SysKeyValue> kvs) {
        this.kvs = kvs;
    }
}
