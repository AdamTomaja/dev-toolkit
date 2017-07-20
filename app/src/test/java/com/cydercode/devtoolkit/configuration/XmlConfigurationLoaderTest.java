package com.cydercode.devtoolkit.configuration;

import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.configuration.model.*;
import org.junit.Test;

import java.io.File;
import java.util.Optional;

import static com.google.common.io.Resources.getResource;
import static org.assertj.core.api.Assertions.assertThat;


public class XmlConfigurationLoaderTest {

    @Test
    public void shouldLoadConfigurationFromXml() throws Exception {
        // given
        String configurationSource = "com/cydercode/devtoolkit/configuration/XmlConfigurationLoaderTest/configuration.xml";
        String configurationFile = configurationSource;
        ConfigurationLoader loader = new XmlConfigurationLoader(new File(getResource(configurationFile).getFile()));

        // when
        Configuration configuration = loader.load();

        // then
        Optional<Application> application = configuration.getApplication("git");
        assertThat(application).isPresent();
        assertThat(application.get().getPath()).isEqualTo("git");

        Optional<Parameter> parameter = configuration.getParameter("parameter-name");
        assertThat(parameter).isPresent();
        Parameter parameterObj = parameter.get();

        assertThat(parameterObj.getType()).isEqualTo(ParameterType.STRING);
        assertThat(parameterObj.getValues().getValue()).containsExactly(
                new Values.Value().withDescription("Value A").withValue("value-a"),
                new Values.Value().withValue("value-b"));

        assertThat(parameterObj.isHidden()).isTrue();
        assertThat(parameterObj.getGroup()).isEqualTo("utils");
        assertThat(parameterObj.getDefault()).isEqualTo("default-value");

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
        Optional<Preset> preset = configuration.getPreset("preset-name");
        assertThat(preset).isPresent();
        Preset presetObj = preset.get();
        assertThat(presetObj.getApplication()).isEqualTo("git");
        assertThat(presetObj.getCmd()).isEqualTo("pull");

        assertThat(configuration.getGroups().get(0).getName()).isEqualTo("group-a");
        assertThat(configuration.getGroups().get(0).getDescription()).isEqualTo("Description A");

        assertThat(configuration.getGroups().get(1).getName()).isEqualTo("group-b");
        assertThat(configuration.getGroups().get(1).getDescription()).isEqualTo("Description B");
        assertThat(configuration.getSource()).endsWith(configurationSource);
    }
}