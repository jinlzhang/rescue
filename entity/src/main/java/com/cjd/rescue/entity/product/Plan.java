package com.cjd.rescue.entity.product;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "plan")
public class Plan {

    @Id
    private String id;

    private String name;

    private Date expect_time;

    private String plan_status = "1";

    private String is_delete;

    private Date create_time;

    private Date modify_time;


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

    public Date getExpect_time() {
        return expect_time;
    }

    public void setExpect_time(Date expect_time) {
        this.expect_time = expect_time;
    }

    public String getPlan_status() {
        return plan_status;
    }

    public void setPlan_status(String plan_status) {
        this.plan_status = plan_status;
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

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
}
