package com.cydercode.devtoolkit.configuration;

import com.cydercode.devtoolkit.Configuration;

public interface ConfigurationLoader {

    Configuration load() throws Exception;

}
