package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.configuration.model.Application;
import com.cydercode.devtoolkit.configuration.model.Group;
import com.cydercode.devtoolkit.configuration.model.Parameter;
import com.cydercode.devtoolkit.configuration.model.Preset;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Configuration {
    String HIDDEN = "hidden";
    String TRUE = "true";
    String DEFAULT_GROUP = null;

    List<Preset> getPresetsWithPredicate(Predicate<Preset> predicate);

    Optional<Application> getApplication(String application);

    Optional<Parameter> getParameter(String parameterName);

    Optional<Preset> getPreset(String name);

    <T> List<T> getWithPredicate(List<T> objects, Predicate<T> predicate);

    List<Parameter> findParametersWithGroup(String group);

    List<Preset> findPresetsWithGroup(String group);

    List<Group> getGroups();
}
