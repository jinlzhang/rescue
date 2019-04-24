package com.cjd.rescue.entity.product;

import com.cjd.rescue.entity.common.BaseObject;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "pro_project")
public class Project  extends BaseObject {
    @Id
    private String id;


    private Date modify_time;

    private Date create_time;

    private String name;

    private String code;

    private String is_delete;


    private String current_version;
    private String current_version_prefix;


    private String team_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
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

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getCurrent_version() {
        return current_version;
    }

    public void setCurrent_version(String current_version) {
        this.current_version = current_version;
    }

    public String getCurrent_version_prefix() {
        return current_version_prefix;
    }

    public void setCurrent_version_prefix(String current_version_prefix) {
        this.current_version_prefix = current_version_prefix;
    }
}
