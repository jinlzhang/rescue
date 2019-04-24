package com.cjd.rescue.entity.common;

public enum TypeOfKV {
    PROJECT("project"),
    TEAM("team"),
    MODULE("module"),
    PLAN("plan"),
    USER("user"),
    ROLE("role"),
    TASK("task"),
    ;

    private String prefix;

    TypeOfKV(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
