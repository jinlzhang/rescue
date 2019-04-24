package com.cjd.rescue.entity.product;

import com.cjd.rescue.entity.common.BaseObject;
import com.cjd.rescue.entity.common.SysKeyValue;

import java.util.List;

public class AddProjectParams  extends BaseObject {

    private Project project;

    private List<SysKeyValue> kvs;

    private List<Module> modules;


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<SysKeyValue> getKvs() {
        return kvs;
    }

    public void setKvs(List<SysKeyValue> kvs) {
        this.kvs = kvs;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
