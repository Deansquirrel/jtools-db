package com.github.deansquirrel.tools.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.lang.NonNull;

public abstract class ABaseConn{

    private String name;

    private ABaseConn(){};

    protected ABaseConn(@NonNull String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * 获取数据源连接
     * @return 数据源连接
     */
    public abstract DruidDataSource getDataSource();

    /**
     * 根据指定配置获取数据源连接
     * @param queryTimeout 查询超时时间
     * @param maxActive 最大连接数
     * @return 数据源
     */
    protected DruidDataSource getDataSource(Integer queryTimeout, Integer maxActive) {
        DruidDataSource ds = this.getDataSource();
        if(queryTimeout != null && queryTimeout >= 1) {
            ds.setQueryTimeout(queryTimeout);
        }
        if(maxActive != null && maxActive >= 1) {
            ds.setMaxActive(maxActive);
        }
        return ds;
    }

    /**
     * 设置数据源连接属性
     * @param dataSource 数据源
     */
    protected void setSourceAttributes(DruidDataSource dataSource) {
        dataSource.setName(this.name);
        dataSource.setMinIdle(0);
        dataSource.setInitialSize(1);
        dataSource.setMaxWait(10000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setTimeBetweenConnectErrorMillis(15 * 1000);
        dataSource.setLoginTimeout(10);
        dataSource.setTestWhileIdle(true);

        dataSource.setMaxActive(Constant.DEFAULT_MAX_ACTIVE);
        dataSource.setQueryTimeout(Constant.DEFAULT_QUERY_TIMEOUT);
    }

}
