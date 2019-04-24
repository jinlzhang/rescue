package com.cjd.rescue.entity.product;

import com.cjd.rescue.entity.common.BaseObject;
import com.cjd.rescue.entity.common.SysKeyValue;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "module")
public class Module extends BaseObject{

    private String id;

    private String name;

    private String code;

    private String operate;

    private String operate_return;

    private Date create_time;

    private Date modify_time;

    private String project_id;

    @Transient
    private List<SysKeyValue> sysKeyValues;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getOperate_return() {
        return operate_return;
    }

    public void setOperate_return(String operate_return) {
        this.operate_return = operate_return;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public List<SysKeyValue> getSysKeyValues() {
        return sysKeyValues;
    }

    public void setSysKeyValues(List<SysKeyValue> sysKeyValues) {
        this.sysKeyValues = sysKeyValues;
    }
}
