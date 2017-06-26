package com.cydercode.devtoolkit.plugin;

import com.cydercode.devtoolkit.Job;
import com.cydercode.devtoolkit.configuration.ConfigurationHolder;

public interface DevToolkitContext {

    ConfigurationHolder getConfigurationHolder();

    Job enqueueJob(Job job);
}
