package com.cydercode.devtoolkit.configuration;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigurationLoaderFactory {

    public ConfigurationLoader produceForFile(File file) throws FileNotFoundException {
        String extension = FilenameUtils.getExtension(file.getName()).toLowerCase();

        if (extension.equals("xml")) {
            return new XmlConfigurationLoader(file);
        }

        if (extension.equals("json")) {
            throw new IllegalArgumentException("JSON format is no longer supported!");
        }

        throw new IllegalArgumentException("Unknown file type: " + extension);
    }
}
