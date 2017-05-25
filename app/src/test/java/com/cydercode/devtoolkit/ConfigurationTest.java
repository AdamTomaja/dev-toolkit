package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.configuration.XmlConfigurationLoader;
import com.cydercode.devtoolkit.configuration.model.Group;
import com.cydercode.devtoolkit.configuration.model.Preset;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static com.cydercode.devtoolkit.Configuration.DEFAULT_GROUP;
import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTest {

    @Test
    public void shouldObtainGroupListWithDescriptions() throws Exception {
        // given
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/ConfigurationTraverserTest/configuration.xml");

        // when
        List<Group> groups = configuration.getGroups();

        // then
        assertThat(groups.get(0).getName()).isEqualTo("group1");
        assertThat(groups.get(1).getName()).isEqualTo("group2");
    }

    @Test
    public void shouldFindObjectsFromGroup() throws Exception {
        // given
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/ConfigurationTraverserTest/configuration.xml");

        // when
        List<Preset> presets = configuration.findPresetsWithGroup("group2");

        // then
        assertThat(presets).hasSize(1);
        assertThat(presets.get(0).getName()).isEqualTo("application-error");
    }

    @Test
    public void shouldFindObjectsWithoutGroup() throws Exception {
        // given
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/ConfigurationTraverserTest/configuration.xml");

        // when
        List<Preset> objects = configuration.findPresetsWithGroup(DEFAULT_GROUP);

        // then
        assertThat(objects.stream().map(p -> p.getName()).collect(Collectors.toList())).containsExactly("build", "clean");
    }

    @Test
    public void shouldFindObjectWithAttribute() throws Exception {
        // given
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/ConfigurationTraverserTest/shouldFindWithAttribute.xml");

        // when
        List<Preset> presets = configuration.getPresetsWithPredicate(p -> p.isQtoolbox() != null && p.isQtoolbox());

        // then
        assertThat(presets.stream().map(p -> p.getName()).collect(Collectors.toList())).containsOnly("application-error");
    }

    @Test
    public void shouldFindObjectsWithAttribute() throws Exception {
        // given
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/ConfigurationTraverserTest/shouldFindWithAttribute.xml");

        // when
        List<Preset> presets = configuration.getPresetsWithPredicate(p -> p.getApplication().equals("Maven"));

        // then
        assertThat(presets.stream().map(p -> p.getName()).collect(Collectors.toList())).containsExactly("build", "clean");
    }

    private Configuration loadConfiguration(String file) throws Exception {
        return new XmlConfigurationLoader(new File(Resources.getResource(file).getFile())).load();
    }
}
