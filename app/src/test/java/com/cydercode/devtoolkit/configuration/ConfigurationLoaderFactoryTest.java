package com.cydercode.devtoolkit.configuration;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationLoaderFactoryTest {

    private final ConfigurationLoaderFactory factory = new ConfigurationLoaderFactory();

    @Test
    public void shouldProduceJsonLoader() throws Exception {
        // given
        File file = new File("path/configuration.json");

        // when
        ConfigurationLoader loader = factory.produceForFile(file);

        // then
        assertThat(loader).isInstanceOf(JsonConfigurationLoader.class);
    }

    @Test
    public void shouldProduceXmlLoader() throws Exception {
        // given
        File file = new File("path/configuration.xml");

        // when
        ConfigurationLoader loader = factory.produceForFile(file);

        // then
        assertThat(loader).isInstanceOf(XmlConfigurationLoader.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenUnknownType() throws Exception {
        // when
        factory.produceForFile(new File("path/asdasd.unknownextension"));
    }
}