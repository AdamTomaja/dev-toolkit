package com.cydercode.devtoolkit;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

import static com.cydercode.devtoolkit.Configuration.DEFAULT_GROUP;
import static com.google.common.io.Resources.getResource;
import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationTraverserTest {

    @Test
    public void shouldCreateGroupsSet() throws IOException {
        // given
        Configuration configuration = new Gson().fromJson(new InputStreamReader(
                        getResource("com/cydercode/devtoolkit/ConfigurationTraverserTest/configuration.json")
                                .openStream()),
                Configuration.class);
        ConfigurationTraverser traverser = new ConfigurationTraverser();

        // when
        Set<String> groups = traverser.getGroups(configuration);

        // then
        assertThat(groups).containsOnly("group1", "group2");
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
}
