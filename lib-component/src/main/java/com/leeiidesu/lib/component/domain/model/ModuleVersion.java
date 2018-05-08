package com.leeiidesu.lib.component.domain.model;

/**
 * Created by iilee on 2018/5/7.
 */

public class ModuleVersion {
    private String name;
    private String path;

    public ModuleVersion() {
    }

    public ModuleVersion(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
