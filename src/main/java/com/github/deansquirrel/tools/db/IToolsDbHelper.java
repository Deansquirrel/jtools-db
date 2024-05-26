package com.github.deansquirrel.tools.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;

import java.util.Set;

public interface IToolsDbHelper {

    /**
     * 获取数据源连接
     * @return 数据库连接
     */
    JdbcTemplate getJdbcTemplate();

    /**
     * 查询是否包含指定的数据源
     * @param key 关键字
     * @return 查询结果 true / false
     */
    boolean isExistDataSource(String key);

    /**
     * 数据源key列表
     * @return 列表
     */
    Set<String> keySet();

    /**
     * 添加数据源
     * @param key 标识
     * @param dataSource 连接配置
     */
    void addDataSource(@NonNull String key, @NonNull DruidDataSource dataSource);

    /**
     * 移除数据源
     * @param key 标识
     */
    void removeDataSource(@NonNull String key);

    /**
     * 清空所有数据源
     */
    void clear();

    /**
     * 数据源数量
     * @return 数据源数量
     */
    long size();

    /**
     * 设置当前数据源
     * @param key 标识
     */
    void setDataSourceKey(@NonNull String key);

    /**
     * 重置缓存（连接使用完后需调用）
     */
    void remove();

}
