package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.configuration.model.Group;
import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.cydercode.devtoolkit.Configuration.DEFAULT_GROUP;
import static com.google.common.io.Resources.getResource;
import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTraverserTest {

    @Test
    public void shouldObtainGroupListWithDescriptions() throws IOException {
        // given
        Configuration configuration = new Gson().fromJson(new InputStreamReader(
                        getResource("com/cydercode/devtoolkit/ConfigurationTraverserTest/configuration.json")
                                .openStream()),
                Configuration.class);
        ConfigurationTraverser traverser = new ConfigurationTraverser();

        // when
        List<Group> groups = traverser.getGroups(configuration);

        // then
        assertThat(groups.get(0).getName()).isEqualTo("group1");
        assertThat(groups.get(1).getName()).isEqualTo("group2");
    }

    @Test
    public void shouldFindObjectsFromGroup() throws IOException {
        // given
        Configuration configuration = new Gson().fromJson(new InputStreamReader(
                        getResource("com/cydercode/devtoolkit/ConfigurationTraverserTest/configuration.json")
                                .openStream()),
                Configuration.class);
        ConfigurationTraverser traverser = new ConfigurationTraverser();

        // when
        Map<String, Map<String, Object>> objects = traverser.findObjectsWithGroup("group2", configuration.getPresets());

        // then
        assertThat(objects).hasSize(1);
        assertThat(objects).containsKey("application-error");
    }

    @Test
    public void shouldFindObjectsWithoutGroup() throws IOException {
        // given
        Configuration configuration = new Gson().fromJson(new InputStreamReader(
                        getResource("com/cydercode/devtoolkit/ConfigurationTraverserTest/configuration.json")
                                .openStream()),
                Configuration.class);
        ConfigurationTraverser traverser = new ConfigurationTraverser();

        // when
        Map<String, Map<String, Object>> objects = traverser.findObjectsWithGroup(DEFAULT_GROUP, configuration.getPresets());

        // then
        assertThat(objects).containsOnlyKeys("clean", "build");
    }

    @Test
    public void shouldFindObjectWithAttribute() throws IOException {
        // given
        Configuration configuration = new Gson().fromJson(new InputStreamReader(
                        getResource("com/cydercode/devtoolkit/ConfigurationTraverserTest/shouldFindWithAttribute.json")
                                .openStream()),
                Configuration.class);

        ConfigurationTraverser traverser = new ConfigurationTraverser();

        // when
        Set<String> presets = traverser.getWithAttribute(configuration.getPresets(), "booleanAttribute", true);

        // then
        assertThat(presets).containsOnly("application-error");
    }

    @Test
    public void shouldFindObjectsWithAttribute() throws IOException {
        // given
        Configuration configuration = new Gson().fromJson(new InputStreamReader(
                        getResource("com/cydercode/devtoolkit/ConfigurationTraverserTest/shouldFindWithAttribute.json")
                                .openStream()),
                Configuration.class);

        ConfigurationTraverser traverser = new ConfigurationTraverser();

        // when
        Set<String> presets = traverser.getWithAttribute(configuration.getPresets(), "attr", "attrValue");

        // then
        assertThat(presets).containsOnly("clean-2", "clean-3");
    }
}
