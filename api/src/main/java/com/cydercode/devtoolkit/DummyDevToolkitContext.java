package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.configuration.ConfigurationHolder;
import com.cydercode.devtoolkit.plugin.DevToolkitContext;

public class DummyDevToolkitContext implements DevToolkitContext {


    @Override
    public ConfigurationHolder getConfigurationHolder() {
        return null;
    }

    @Override
    public Job enqueueJob(Job job) {
        return job;
    }

}
