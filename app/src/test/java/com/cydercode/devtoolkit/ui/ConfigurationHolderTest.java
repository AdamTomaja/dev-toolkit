package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.configuration.ConfigurationHolder;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Optional;

import static com.google.common.io.Resources.getResource;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.apache.commons.lang.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationHolderTest {

    @Test
    public void shouldBeEmptyAfterStart() throws Exception {
        // given
        ConfigurationHolder holder = new ConfigurationHolder(randomAlphabetic(10));

        // when
        Optional<Configuration> configuration = holder.loadLastConfiguration();

        // then
        assertThat(configuration).isEmpty();
    }

    @Test
    public void shouldBePresentIfLoadedPreviosly() throws Exception {
        // given
        String holderId = randomAlphabetic(10);

        ConfigurationHolder holder = new ConfigurationHolder(holderId);
        ConfigurationHolder newHolder = new ConfigurationHolder(holderId);

        // when
        Optional<Configuration> oldConfiguration = holder.loadConfiguration(new File(
                getResource("com/cydercode/devtoolkit/ui/ConfigurationHolderTest/configuration.xml")
                        .getFile()));

        Optional<Configuration> configuration = newHolder.loadLastConfiguration();

        // then
        assertThat(configuration).isNotEmpty();
        assertThat(oldConfiguration.get()).isEqualTo(configuration.get());
    }

    @Test
    public void shouldNotLoadAfterPreviousError() throws Exception {
        // given
        String holderId = randomAlphabetic(10);
        ConfigurationHolder holder = new ConfigurationHolder(holderId);
        ConfigurationHolder newHolder = new ConfigurationHolder(holderId);

        // when
        try {
            holder.loadConfiguration(new File("/not/existing/path/to/file.xml"));
        } catch (FileNotFoundException e) {
            LoggerFactory.getLogger(ConfigurationHolderTest.class).error("Unable to load config", e);
        }

        Optional<Configuration> configuration = newHolder.loadLastConfiguration();

        // then
        Assertions.assertThat(configuration).isEmpty();
    }

    @Test
    public void shouldNotFileWhenLastConfigurationFileNotFound() throws Exception {
        // given
        String holderId = randomAlphabetic(10);
        ConfigurationHolder oldHolder = new ConfigurationHolder(holderId);
        ConfigurationHolder newHolder = new ConfigurationHolder(holderId);
        File file = File.createTempFile("ConfigurationHolderTest", holderId+".xml");
        Files.copy(getResource("com/cydercode/devtoolkit/ui/ConfigurationHolderTest/configuration.xml")
                .openStream(), file.toPath(), REPLACE_EXISTING);

        // when
        oldHolder.loadConfiguration(file);
        FileUtils.deleteQuietly(file);
        newHolder.loadLastConfiguration();
    }
}