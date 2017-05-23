package com.cydercode.devtoolkit.configuration;

import com.cydercode.devtoolkit.Configuration;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.io.Resources.getResource;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


public class XmlConfigurationLoaderTest {

    @Test
    public void shouldLoadConfigurationFromXml() throws Exception {
        // given
        String configurationFile = "com/cydercode/devtoolkit/configuration/XmlConfigurationLoaderTest/configuration.xml";
        ConfigurationLoader loader = new XmlConfigurationLoader(new File(getResource(configurationFile).getFile()));

        // when
        Configuration configuration = loader.load();

        // then
        assertThat(configuration.getApplications().get("git")).isNotNull();
        assertThat(configuration.getApplications().get("git")).isEqualTo(of("path", "git"));

        assertThat(configuration.getParameters()).isNotNull();
        assertThat(configuration.getParameters().get("parameter-name")).isEqualTo(ImmutableMap.builder()
                .put(Configuration.TYPE, "string")
                .put(Configuration.VALUES, asList("value-a", "value-b"))
                .put(Configuration.HIDDEN, "true")
                .put(Configuration.GROUP, "utils")
                .build());

        assertThat(configuration.getPresets()).isNotNull();

        /**
         * preset name="preset-name">
         <application>git</application>
         <cmd>pull</cmd>
         <group>utils</group>
         <child-presets>
         <preset>preset-a</preset>
         <preset>preset-b</preset>
         </child-presets>
         <description>Description of a preset</description>
         <qtoolbox>true</qtoolbox>
         </preset>
         */
        assertThat(configuration.getPresets().get("preset-name")).isEqualTo(ImmutableMap.builder()
                .put(Configuration.APPLICATION, "git")
                .put(Configuration.CMD, "pull")
                .put(Configuration.GROUP, "utils")
                .put(Configuration.PRESETS, Arrays.asList(ImmutableMap.builder()
                        .put("preset", "preset-a")
                        .put("ignorable", false).build(), ImmutableMap.builder()
                        .put("preset", "preset-b")
                        .put("ignorable", true).build()))
                .build());
    }
}