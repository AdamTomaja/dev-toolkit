package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.Configuration;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.awt.X11.XAtom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class ConfigurationHolder {

    static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationHolder.class);
    static final String FILE_PREF_KEY = "file";

    final Preferences preferences;

    Configuration configuration;

    public ConfigurationHolder(String holderId) {
        preferences = Preferences.userNodeForPackage(ConfigurationHolder.class).node(holderId);
    }

    public Optional<Configuration> loadLastConfiguration() throws FileNotFoundException {
        String file = preferences.get(FILE_PREF_KEY, null);
        if (file != null) {
            load(new File(file));
        }

        return Optional.ofNullable(configuration);
    }

    public Optional<Configuration> loadConfiguration(File file) throws BackingStoreException, FileNotFoundException {
        load(file);
        preferences.put(FILE_PREF_KEY, file.getAbsolutePath());
        preferences.sync();
        return Optional.of(configuration);
    }

    private void load(File file) throws FileNotFoundException {
        configuration = new Gson().fromJson(new FileReader(file), Configuration.class);
        LOGGER.info("Loaded configuration: {}", configuration);
    }

    public Optional<Configuration> getCurrentConfiguration() {
        return Optional.ofNullable(configuration);
    }
}
