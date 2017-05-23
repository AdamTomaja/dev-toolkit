package com.cydercode.devtoolkit.configuration;

import com.cydercode.devtoolkit.Configuration;

import java.io.FileNotFoundException;

public interface ConfigurationLoader {

    Configuration load() throws FileNotFoundException, Exception;

}
