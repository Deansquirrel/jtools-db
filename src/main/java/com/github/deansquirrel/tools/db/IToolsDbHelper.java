package com.github.deansquirrel.tools.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;

public interface IToolsDbHelper {

    public static final String BEAN_TX_MANAGER = "txManagerDynamic";
    public static final String BEAN_JDBC_TEMPLATE = "jdbcTemplateDynamic";

    /**
     * 默认数据库查询超时时间（秒）
     */
    public static final int DEFAULT_QUERY_TIMEOUT = 3600;
    public static final int DEFAULT_MAX_ACTIVE = 30;

    /**
     * 获取数据源连接
     * @return 数据库连接
     */
    public JdbcTemplate getJdbcTemplate();

    /**
     * 添加数据源
     * @param key 表示
     * @param dataSource 连接配置
     */
    public void addDataSource(@NonNull String key, @NonNull DruidDataSource dataSource, Integer queryTimeout, Integer maxActive);

    default void addDataSource(@NonNull String key, @NonNull DruidDataSource dataSource) {
        this.addDataSource(key, dataSource, IToolsDbHelper.DEFAULT_QUERY_TIMEOUT, DEFAULT_MAX_ACTIVE);
    }

    /**
     * 移除数据源
     * @param key 标识
     */
    public void removeDataSource(@NonNull String key);

    /**
     * 清空所有数据源
     */
    public void clear();

    /**
     * 数据源数量
     * @return 数据源数量
     */
    public long size();

    /**
     * 设置当前数据源
     * @param key 标识
     */
    public void setDataSourceKey(@NonNull String key);

    /**
     * 重置缓存（连接使用完后需调用）
     */
    public void remove();

    default void setSourceAttributes(DruidDataSource dataSource, Integer queryTimeout, Integer maxActive) {
        dataSource.setMinIdle(0);
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(maxActive == null ? IToolsDbHelper.DEFAULT_MAX_ACTIVE : maxActive);
        dataSource.setMaxWait(10000);
        dataSource.setQueryTimeout(queryTimeout == null ? IToolsDbHelper.DEFAULT_QUERY_TIMEOUT : queryTimeout);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setTimeBetweenConnectErrorMillis(15 * 1000);
        dataSource.setLoginTimeout(10);
        dataSource.setTestWhileIdle(true);
    }

}
