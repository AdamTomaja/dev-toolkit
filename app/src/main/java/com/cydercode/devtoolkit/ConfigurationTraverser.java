package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.configuration.model.Group;

import java.util.*;

import static com.cydercode.devtoolkit.Configuration.GROUP;

public class ConfigurationTraverser {

    public Map<String, Map<String, Object>> findObjectsWithGroup(String group, Map<String, Map<String, Object>> objects) {
        Map<String, Map<String, Object>> result = new HashMap<>();
        objects.forEach((name, object) -> {
            if (Objects.equals(group, object.get(GROUP))) {
                result.put(name, object);
            }
        });
        return result;
    }

    public List<Group> getGroups(Configuration configuration) {
        List<Group> groups = new ArrayList<>();
        if (configuration.getGroups() != null) {
            groups.addAll(configuration.getGroups());
        }
        searchForGroups(groups, configuration.getParameters());
        searchForGroups(groups, configuration.getPresets());
        return groups;
    }

    public Set<String> getWithAttribute(Map<String, Map<String, Object>> objects, String key, Object value) {
        Set<String> result = new HashSet<>();
        objects.forEach((k, v) -> {
            if (Objects.equals(v.get(key), value)) {
                result.add(k);
            }
        });
        return result;
    }

    private void searchForGroups(List<Group> groups, Map<String, Map<String, Object>> objects) {
        objects.forEach((n, p) -> {
            String groupName = (String) p.get(GROUP);
            if (groupName != null && !groupExists(groups, groupName)) {
                Group group = new Group();
                group.setName(groupName);
                groups.add(group);
            }
        });
    }

    private boolean groupExists(List<Group> groups, String groupName) {
        return groups.stream()
                .filter(group -> groupName.equals(group.getName()))
                .findAny()
                .isPresent();
    }
}
