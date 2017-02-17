package com.cydercode.devtoolkit;

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

    public Set<String> getGroups(Configuration configuration) {
        Set<String> groups = new HashSet<>();
        searchForGroups(groups, configuration.getParameters());
        searchForGroups(groups, configuration.getPresets());
        return groups;
    }

    private void searchForGroups(Set<String> groups, Map<String, Map<String, Object>> objects) {
        objects.forEach((n, p) -> {
            if (p.containsKey(GROUP)) {
                groups.add((String) p.get(GROUP));
            }
        });

    }
}
