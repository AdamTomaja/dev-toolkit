package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.configuration.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InMemoryConfiguration implements Configuration {


    private final DevToolkit devToolkitConfiguration;

    public InMemoryConfiguration(DevToolkit devToolkitConfiguration) {
        this.devToolkitConfiguration = devToolkitConfiguration;
    }

    @Override
    public List<Preset> getPresetsWithPredicate(Predicate<Preset> predicate) {
        return getWithPredicate(devToolkitConfiguration.getPresets().getPreset(), predicate);
    }

    @Override
    public Optional<Application> getApplication(String application) {
        return devToolkitConfiguration.getApplications().getApplication()
                .stream()
                .filter(app -> application.equals(app.getName()))
                .findFirst();
    }

    @Override
    public Optional<Parameter> getParameter(String parameterName) {
        return devToolkitConfiguration.getParameters().getParameter()
                .stream()
                .filter(parameter -> parameterName.equals(parameter.getName()))
                .findFirst();
    }

    @Override
    public Optional<Preset> getPreset(String name) {
        return devToolkitConfiguration.getPresets().getPreset().stream()
                .filter(p -> name.equals(p.getName()))
                .findFirst();
    }

    @Override
    public <T> List<T> getWithPredicate(List<T> objects, Predicate<T> predicate) {
        return objects.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Parameter> findParametersWithGroup(String group) {
        return findObjectsWithGroup(group, devToolkitConfiguration.getParameters().getParameter());

    }

    @Override
    public List<Preset> findPresetsWithGroup(String group) {
        return findObjectsWithGroup(group, devToolkitConfiguration.getPresets().getPreset());
    }

    private <T> List<T> findObjectsWithGroup(String group, List<T> objects) {
        List<T> result = new ArrayList<>();

        objects.forEach(object -> {
            if (Objects.equals(group, getGroup(object))) {
                result.add(object);
            }
        });

        return result;
    }

    @Override
    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<>();
        if (devToolkitConfiguration.getGroups() != null) {
            groups.addAll(devToolkitConfiguration.getGroups().getGroup());
        }
        searchForGroups(groups, devToolkitConfiguration.getParameters().getParameter());
        searchForGroups(groups, devToolkitConfiguration.getPresets().getPreset());
        return groups;
    }

    private void searchForGroups(List<Group> groups, List<? extends Object> objects) {
        objects.forEach(o -> {
            String groupName = getGroup(o);
            if (groupName != null && !groupExists(groups, groupName)) {
                Group group = new Group();
                group.setName(groupName);
                groups.add(group);
            }
        });
    }

    private String getGroup(Object object) {
        if (object instanceof Parameter) {
            return ((Parameter) object).getGroup();
        }

        if (object instanceof Preset) {
            return ((Preset) object).getGroup();
        }

        return null;
    }

    private boolean groupExists(List<Group> groups, String groupName) {
        return groups.stream()
                .filter(group -> groupName.equals(group.getName()))
                .findAny()
                .isPresent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryConfiguration that = (InMemoryConfiguration) o;
        return com.google.common.base.Objects.equal(devToolkitConfiguration, that.devToolkitConfiguration);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(devToolkitConfiguration);
    }
}
