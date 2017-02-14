package com.cydercode.devtoolkit;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Map;


public class ConfigurationTest {

    @Test
    public void shouldSetApplications() {
        // Given
        Map<String, Map<String, String>> map = ImmutableMap.of();
        Configuration configuration = new Configuration();

        // when
        configuration.setApplications(map);

        // then
        Assertions.assertThat(configuration.getApplications()).isSameAs(map);
    }

    @Test
    public void shouldSetParameters() {
        // Given
        Map<String, Map<String, Object>> map = ImmutableMap.of();
        Configuration configuration = new Configuration();

        // when
        configuration.setParameters(map);

        // then
        Assertions.assertThat(configuration.getParameters()).isSameAs(map);
    }

    @Test
    public void shouldSetPresets() {
        // Given
        Map<String, Map<String, String>> map = ImmutableMap.of();
        Configuration configuration = new Configuration();

        // when
        configuration.setPresets(map);

        // then
        Assertions.assertThat(configuration.getPresets()).isSameAs(map);
    }

    @Test
    public void toStringShouldReturnValues() {
        // given
        Configuration configuration = new Configuration();

        // when
        String toString = configuration.toString();

        // then
        Assertions.assertThat(toString).contains("null");
    }
}