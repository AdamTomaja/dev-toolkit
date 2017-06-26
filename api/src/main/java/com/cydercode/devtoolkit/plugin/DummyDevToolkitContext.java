package com.cydercode.devtoolkit.plugin;

import com.cydercode.devtoolkit.configuration.ConfigurationHolder;

import java.util.Map;

public class DummyDevToolkitContext implements DevToolkitContext {


    @Override
    public ConfigurationHolder getConfigurationHolder() {
        return null;
    }

    @Override
    public void executePreset(String preset, Map<String, Object> parameters) {

    }
}
