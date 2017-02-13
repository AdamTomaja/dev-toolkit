package com.cydercode.devtoolkit;

import com.google.common.base.MoreObjects;

import java.util.Map;

public class Configuration {

    Map<String, Map<String, String>> applications;
    Map<String, Map<String, Object>> parameters;
    Map<String, Map<String, String>> presets;

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

    public Map<String, Map<String, String>> getPresets() {
        return presets;
    }

    public void setPresets(Map<String, Map<String, String>> presets) {
        this.presets = presets;
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
