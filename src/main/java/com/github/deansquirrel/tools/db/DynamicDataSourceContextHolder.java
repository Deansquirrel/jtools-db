package com.github.deansquirrel.tools.db;

class DynamicDataSourceContextHolder {

    private final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {

        @Override
        protected String initialValue() {
            return "dynamic_dbo";
        }

    };

    public void setDataSourceKey(String key) {
        this.contextHolder.set(key);
    }

    public String getDataSourceKey() {
        return this.contextHolder.get();
    }

    public void remove() {
        this.contextHolder.remove();
    }

}
