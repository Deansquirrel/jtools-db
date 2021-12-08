package com.github.deansquirrel.tools.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;

public interface IToolsDbHelper {

    /**
     * 获取数据源连接
     * @return 数据库连接
     */
    JdbcTemplate getJdbcTemplate();

    /**
     * 添加数据源
     * @param key 标识
     * @param dataSource 连接配置
     */
    default void addDataSource(@NonNull String key, @NonNull DruidDataSource dataSource) {
        this.addDataSource(key, dataSource, null,null);
    }

    default public void addDataSource(@NonNull String key, @NonNull DruidDataSource dataSource,
                                      Integer queryTimeout) {
        this.addDataSource(key, dataSource, queryTimeout, null);
    }

    public void addDataSource(@NonNull String key, @NonNull DruidDataSource dataSource,
                              Integer queryTimeout, Integer maxActive);

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

}
