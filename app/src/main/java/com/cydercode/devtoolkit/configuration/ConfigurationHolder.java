package com.cydercode.devtoolkit.configuration;

import com.cydercode.devtoolkit.Configuration;

import java.util.Optional;

/**
 * Created by mint on 26.06.17.
 */
public interface ConfigurationHolder {
    Optional<Configuration> getCurrentConfiguration();
}
