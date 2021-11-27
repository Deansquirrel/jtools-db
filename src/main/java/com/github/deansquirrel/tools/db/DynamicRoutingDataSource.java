package com.github.deansquirrel.tools.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private final Logger logger = LoggerFactory.getLogger(DynamicRoutingDataSource.class);

    private DynamicDataSourceContextHolder contextHolder = null;
    private Map<Object, Object> targetDataSources = null;

    private DynamicRoutingDataSource() {};

    public static DynamicRoutingDataSource createDynamicRoutingDataSource(DynamicDataSourceContextHolder contextHolder) {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setTargetDataSources(new HashMap<>());
        dataSource.contextHolder = contextHolder;
        return dataSource;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return this.contextHolder.getDataSourceKey();
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        this.targetDataSources = targetDataSources;
    }

    /**
     * 查询是否包含指定的数据源
     * @param key 关键字
     * @return 查询结果 true / false
     */
    public boolean isExistDataSource(String key) {
        return this.targetDataSources.containsKey(key);
    }

    public int size() {
        return this.targetDataSources.size();
    }

    /**
     * 添加数据源
     * @param key 关键字
     * @param dataSource 数据源
     */
    public synchronized void addDataSource(String key, DataSource dataSource) {
        logger.info("adding datasource {}", key);
        if(this.targetDataSources.containsKey(key)) {
            logger.warn("datasource {} is exists", key);
            return;
        }
        this.targetDataSources.put(key, dataSource);
        this.afterPropertiesSet();
        logger.info("datasource {} has been added", key);
    }

    /**
     * 移除数据源
     * @param key 关键字
     */
    public synchronized void removeDataSource(String key) {
        logger.info("removing datasource {}", key);
        DruidDataSource dataSource = (DruidDataSource) this.targetDataSources.get(key);
        if(this.targetDataSources.containsKey(key)) {
            this.targetDataSources.remove(key);
            dataSource.close();
            this.afterPropertiesSet();
        }
        logger.info("datasource {} has been removed", key);
    }

    /**
     * 设置当前数据源
     * @param key 关键字
     */
    public synchronized void setDataSourceKey(String key) {
        logger.debug("set current datasource {}", key);
        this.contextHolder.setDataSourceKey(key);
    }

    public synchronized void remove() {
        logger.debug("set current datasource default");
        this.contextHolder.remove();
    }

    /**
     * 数据源key列表
     * @return 列表
     */
    public Set<String> keySet() {
        Set<String> result = new HashSet<String>();
        for(Object key : this.targetDataSources.keySet()) {
            result.add(String.valueOf(key));
        }
        return result;
    }

    /**
     * 清空数据源
     */
    public synchronized void clear() {
        for(String key : this.keySet()) {
            this.removeDataSource(key);
        }
    }
}
