package com.cydercode.devtoolkit;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Map;

public class Configuration {

    public static final String GROUP = "group",
            DEFAULT = "default",
            VALUES = "values",
            APPLICATION = "application",
            HIDDEN = "hidden",
            TRUE = "true",
            DEFAULT_GROUP = null;


    Map<String, Map<String, String>> applications;
    Map<String, Map<String, Object>> parameters;
    Map<String, Map<String, Object>> presets;

    public Map<String, Map<String, String>> getApplications() {
        return applications;
    }

    public void setApplications(Map<String, Map<String, String>> applications) {
        this.applications = applications;
    }

    public Map<String, Map<String, Object>> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Map<String, Object>> parameters) {
        this.parameters = parameters;
    }

    public Map<String, Map<String, Object>> getPresets() {
        return presets;
    }

    public void setPresets(Map<String, Map<String, Object>> presets) {
        this.presets = presets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return Objects.equal(applications, that.applications) &&
                Objects.equal(parameters, that.parameters) &&
                Objects.equal(presets, that.presets);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(applications, parameters, presets);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("applications", applications)
                .add("parameters", parameters)
                .add("presets", presets)
                .toString();
    }
}
