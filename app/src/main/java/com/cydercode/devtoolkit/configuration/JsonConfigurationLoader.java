package com.cydercode.devtoolkit.configuration;

import com.cydercode.devtoolkit.Configuration;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;

public class JsonConfigurationLoader extends AbstractFileConfigurationLoader {

    protected JsonConfigurationLoader(File file) throws FileNotFoundException {
        super(file);
    }

    @Override
    public Configuration load() throws Exception {
        return new Gson().fromJson(getReader(), Configuration.class);
    }
}
