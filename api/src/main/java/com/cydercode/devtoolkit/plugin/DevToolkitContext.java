package com.cydercode.devtoolkit.plugin;

import com.cydercode.devtoolkit.configuration.ConfigurationHolder;

import java.util.Map;

public interface DevToolkitContext {

    ConfigurationHolder getConfigurationHolder();

    void executePreset(String preset, Map<String, Object> parameters);
}
