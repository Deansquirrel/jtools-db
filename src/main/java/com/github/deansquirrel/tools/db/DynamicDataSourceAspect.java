package com.github.deansquirrel.tools.db;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
class DynamicDataSourceAspect {

    private final IToolsDbHelper iToolsDbHelper;

    public DynamicDataSourceAspect(IToolsDbHelper iToolsDbHelper) {
        this.iToolsDbHelper = iToolsDbHelper;
    }

    @Pointcut(value = "@annotation(com.github.deansquirrel.tools.db.TargetSource)")
    public void pointCut() {}

    @Before(value = "pointCut() && @annotation(targetSource)")
    public void doBefore(TargetSource targetSource) {
        this.iToolsDbHelper.setDataSourceKey(targetSource.value());
    }

    @After(value = "pointCut() && @annotation(targetSource)")
    public void doAfter(TargetSource targetSource) {
        this.iToolsDbHelper.remove();
    }
}
