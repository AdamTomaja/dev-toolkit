package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.configuration.model.Application;
import com.cydercode.devtoolkit.configuration.model.Preset;
import com.cydercode.devtoolkit.script.ScriptsExecutor;
import org.apache.commons.lang.text.StrBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandBuilder {

    private final ScriptsExecutor scriptsExecutor = new ScriptsExecutor();

    public String buildCommand(Configuration configuration, String presetName, Map<String, Object> parameters) {
        Optional<Preset> preset = configuration.getPreset(presetName);
        if (!preset.isPresent()) {
            throw new IllegalArgumentException("Cannot find preset with name: " + presetName);
        }

        Preset presetObject = preset.get();

        Optional<Application> application = configuration.getApplication(presetObject.getApplication());
        if (!application.isPresent()) {
            throw new IllegalArgumentException("Cannot find application with name: " + presetObject.getApplication());
        }

        StrBuilder strBuilder = new StrBuilder(String.format("%s %s", application.get().getPath(), presetObject.getCmd()));
        Map<String, Object> effectiveParameters = new HashMap<>();
        effectiveParameters.putAll(System.getenv());
        effectiveParameters.putAll(parameters);

        effectiveParameters = scriptsExecutor.executeScripts(configuration, effectiveParameters);

        effectiveParameters.forEach((k, v) -> {
            strBuilder.replaceAll(String.format("${%s}", k), v.toString());
        });

        return strBuilder.toString();
    }
}
