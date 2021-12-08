package com.github.deansquirrel.tools.db;

import com.alibaba.druid.pool.DruidDataSource;

public interface IBaseConn {

    String getName();

    DruidDataSource getDataSource();

    /**
     * 获取数据源连接
     * @param queryTimeout 查询超时时间
     * @param maxActive 最大连接数
     * @return 数据源
     */
    default DruidDataSource getDataSource(Integer queryTimeout, Integer maxActive) {
        DruidDataSource ds = this.getDataSource();
        ds.setQueryTimeout(queryTimeout);
        ds.setMaxActive(maxActive);
        return ds;
    }

    /**
     * 设置数据源连接属性
     * @param dataSource 数据源
     * @param queryTimeout 查询超时
     * @param maxActive 最大连接数
     */
    default void setSourceAttributes(DruidDataSource dataSource, Integer queryTimeout, Integer maxActive) {
        dataSource.setMinIdle(0);
        dataSource.setInitialSize(1);
        dataSource.setMaxWait(10000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setTimeBetweenConnectErrorMillis(15 * 1000);
        dataSource.setLoginTimeout(10);
        dataSource.setTestWhileIdle(true);

        dataSource.setMaxActive(maxActive);
        dataSource.setQueryTimeout(queryTimeout);
    }

}
