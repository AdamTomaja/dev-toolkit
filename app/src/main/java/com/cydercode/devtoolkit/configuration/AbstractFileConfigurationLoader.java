package com.cydercode.devtoolkit.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public abstract class AbstractFileConfigurationLoader implements ConfigurationLoader {

    private final File file;

    protected AbstractFileConfigurationLoader(File file) {
        this.file = file;
    }

    protected Reader getReader() throws FileNotFoundException {
        return new FileReader(file);
    }
}
