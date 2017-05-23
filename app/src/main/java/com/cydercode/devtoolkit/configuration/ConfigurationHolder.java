package com.cydercode.devtoolkit.configuration;

import com.cydercode.devtoolkit.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.prefs.Preferences;

public class ConfigurationHolder {

    static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationHolder.class);
    static final String FILE_PREF_KEY = "file";

    private ConfigurationLoaderFactory configurationLoaderFactory = new ConfigurationLoaderFactory();

    final Preferences preferences;

    Configuration configuration;

    public ConfigurationHolder(String holderId) {
        preferences = Preferences.userNodeForPackage(ConfigurationHolder.class).node(holderId);
    }

    public Optional<Configuration> loadLastConfiguration() throws FileNotFoundException {
        String file = preferences.get(FILE_PREF_KEY, null);

        try {
            if (file != null) {
                load(new File(file));
            }
        } catch (Exception e) {
            LOGGER.warn("It was impossible to load last configuration", e);
        }

        return Optional.ofNullable(configuration);
    }

    public Optional<Configuration> loadConfiguration(File file) throws Exception {
        load(file);
        preferences.put(FILE_PREF_KEY, file.getAbsolutePath());
        preferences.sync();
        return Optional.of(configuration);
    }

    private void load(File file) throws Exception {
        configuration = configurationLoaderFactory.produceForFile(file).load();
        LOGGER.info("Loaded configuration: {}", configuration);
    }

    public Optional<Configuration> getCurrentConfiguration() {
        return Optional.ofNullable(configuration);
    }
}
