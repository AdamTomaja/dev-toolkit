package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.configuration.XmlConfigurationLoader;
import com.cydercode.devtoolkit.configuration.model.Group;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.cydercode.devtoolkit.Configuration.DEFAULT_GROUP;
import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTraverserTest {

    @Test
    public void shouldObtainGroupListWithDescriptions() throws Exception {
        // given
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/ConfigurationTraverserTest/configuration.xml");
        ConfigurationTraverser traverser = new ConfigurationTraverser();

        // when
        List<Group> groups = traverser.getGroups(configuration);

        // then
        assertThat(groups.get(0).getName()).isEqualTo("group1");
        assertThat(groups.get(1).getName()).isEqualTo("group2");
    }

    @Test
    public void shouldFindObjectsFromGroup() throws Exception {
        // given
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/ConfigurationTraverserTest/configuration.xml");
        ConfigurationTraverser traverser = new ConfigurationTraverser();

        // when
        Map<String, Map<String, Object>> objects = traverser.findObjectsWithGroup("group2", configuration.getPresets());

        // then
        assertThat(objects).hasSize(1);
        assertThat(objects).containsKey("application-error");
    }

    @Test
    public void shouldFindObjectsWithoutGroup() throws Exception {
        // given
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/ConfigurationTraverserTest/configuration.xml");
        ConfigurationTraverser traverser = new ConfigurationTraverser();

        // when
        Map<String, Map<String, Object>> objects = traverser.findObjectsWithGroup(DEFAULT_GROUP, configuration.getPresets());

        // then
        assertThat(objects).containsOnlyKeys("clean", "build");
    }

    @Test
    public void shouldFindObjectWithAttribute() throws Exception {
        // given
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/ConfigurationTraverserTest/shouldFindWithAttribute.xml");

        ConfigurationTraverser traverser = new ConfigurationTraverser();

        // when
        Set<String> presets = traverser.getWithAttribute(configuration.getPresets(), Configuration.Q_TOOLBOX, true);

        // then
        assertThat(presets).containsOnly("application-error");
    }

    @Test
    public void shouldFindObjectsWithAttribute() throws Exception {
        // given
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/ConfigurationTraverserTest/shouldFindWithAttribute.xml");

        ConfigurationTraverser traverser = new ConfigurationTraverser();

        // when
        Set<String> presets = traverser.getWithAttribute(configuration.getPresets(), Configuration.APPLICATION, "Maven");

        // then
        assertThat(presets).containsOnly("build", "clean");
    }

    private Configuration loadConfiguration(String file) throws Exception {
        return new XmlConfigurationLoader(new File(Resources.getResource(file).getFile())).load();
    }
}
