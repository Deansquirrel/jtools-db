package com.github.deansquirrel.tools.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.lang.NonNull;

abstract class BaseConnInfo {

    public BaseConnInfo() {}

    public BaseConnInfo(@NonNull String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DruidDataSource getDataSource(){
        return this.getDataSource(IToolsDbHelper.DEFAULT_QUERY_TIMEOUT, IToolsDbHelper.DEFAULT_MAX_ACTIVE);
    };

    public abstract DruidDataSource getDataSource(Integer queryTimeout, Integer maxActive);
}
