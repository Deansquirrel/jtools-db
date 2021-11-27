package com.github.deansquirrel.tools.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ToolsHelperImpl implements IToolsDbHelper {

    private final JdbcTemplate jdbcTemplate;
    private final DynamicRoutingDataSource dynamicRoutingDataSource;

    public ToolsHelperImpl(@Qualifier(value = IToolsDbHelper.BEAN_JDBC_TEMPLATE) JdbcTemplate jdbcTemplate,
                           @Qualifier(value = IToolsDbHelper.DYNAMIC_ROUTEING_DATASOURCE) DynamicRoutingDataSource dynamicRoutingDataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dynamicRoutingDataSource = dynamicRoutingDataSource;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    @Override
    public void addDataSource(String key, DruidDataSource dataSource, Integer queryTimeout, Integer maxActive) {
        if(queryTimeout != null) { dataSource.setQueryTimeout(queryTimeout); }
        if(maxActive != null) { dataSource.setMaxActive(maxActive); }
        this.dynamicRoutingDataSource.addDataSource(key, dataSource);
    }

    @Override
    public void removeDataSource(String key) {
        this.dynamicRoutingDataSource.removeDataSource(key);
    }

    @Override
    public void clear() {
        this.dynamicRoutingDataSource.clear();
    }

    @Override
    public long size() {
        return this.dynamicRoutingDataSource.size();
    }

    @Override
    public void setDataSourceKey(String key) {
        this.dynamicRoutingDataSource.setDataSourceKey(key);
    }

    @Override
    public void remove() {
        this.dynamicRoutingDataSource.remove();
    }

}
