package com.cydercode.devtoolkit;

import org.apache.commons.lang.text.StrBuilder;

import java.util.Map;

public class CommandBuilder {

    public String buildCommand(Configuration configuration, String preset, Map<String, Object> parameters) {
        Map<String, String> presetConfiguration = configuration.getPresets().get(preset);
        if (presetConfiguration == null) {
            throw new IllegalArgumentException("Cannot find preset with name: " + preset);
        }

        String applicationName = presetConfiguration.get("application");
        Map<String, String> applicationConfiguration = configuration.getApplications()
                .get(applicationName);
        if (applicationConfiguration == null) {
            throw new IllegalArgumentException("Cannot find application with name: " + applicationName);
        }

        StrBuilder strBuilder = new StrBuilder(String.format("%s %s", applicationConfiguration.get("path"), presetConfiguration.get("cmd")));
        parameters.forEach((k, v) -> {
            strBuilder.replaceAll(String.format("${%s}", k), v.toString());
        });

        return strBuilder.toString();
    }
}
