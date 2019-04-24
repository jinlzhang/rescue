package com.cjd.rescue.entity.shiro;

import com.cjd.rescue.entity.common.BaseObject;
import com.cjd.rescue.entity.common.SysKeyValue;
import com.cjd.rescue.entity.product.Module;
import com.cjd.rescue.entity.shiro.User;

import java.util.List;

public class AddUserParams extends BaseObject {

    private User user;

    private List<SysKeyValue> kvs;

    private List<Module> modules;




    public List<SysKeyValue> getKvs() {
        return kvs;
    }

    public void setKvs(List<SysKeyValue> kvs) {
        this.kvs = kvs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
