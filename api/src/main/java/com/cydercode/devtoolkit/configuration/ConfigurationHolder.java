package com.cydercode.devtoolkit.configuration;

import com.cydercode.devtoolkit.Configuration;

import java.util.Optional;

public interface ConfigurationHolder {
    Optional<Configuration> getCurrentConfiguration();
}
