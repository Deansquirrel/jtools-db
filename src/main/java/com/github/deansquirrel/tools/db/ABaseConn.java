package com.github.deansquirrel.tools.db;

import org.springframework.lang.NonNull;

public abstract class ABaseConn implements IBaseConn {

    private String name;

    private ABaseConn(){};

    public ABaseConn(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
