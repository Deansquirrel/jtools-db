package com.github.deansquirrel.tools.db;

public class Constant {

    private Constant(){};

    public static final String BEAN_TX_MANAGER = "txManagerDynamic";
    public static final String BEAN_JDBC_TEMPLATE = "jdbcTemplateDynamic";
    public static final String DYNAMIC_ROUTEING_DATASOURCE = "dynamicRoutingDataSourceDynamic";

    /**
     * 默认数据库查询超时时间（秒）
     */
    public static final int DEFAULT_QUERY_TIMEOUT = 300;
    public static final int DEFAULT_MAX_ACTIVE = 30;

}
