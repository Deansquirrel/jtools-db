package com.github.deansquirrel.tools.db;

abstract class BaseConnInfo {

    public BaseConnInfo() {}

    public BaseConnInfo(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
