package com.cjd.rescue.entity.product;

import com.cjd.rescue.entity.common.BaseObject;
import com.cjd.rescue.entity.common.SysKeyValue;

import java.util.List;

public class AddTeamParams extends BaseObject {

    private Team team;

    private List<SysKeyValue> kvs;


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<SysKeyValue> getKvs() {
        return kvs;
    }

    public void setKvs(List<SysKeyValue> kvs) {
        this.kvs = kvs;
    }
}
