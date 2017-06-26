package com.cydercode.devtoolkit.plugin;

import com.google.common.base.MoreObjects;

public class PluginDescriptor {

    private String name;
    private String mainClass;
    private Plugin plugin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("mainClass", mainClass)
                .add("plugin", plugin)
                .toString();
    }
}
