package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.executor.OutputListener;
import com.cydercode.devtoolkit.script.ScriptApi;
import com.cydercode.devtoolkit.script.ScriptsExecutor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ParametersResolver {

    public static final String CONFIGURATION_SOURCE = "devtoolkit.conf.src";
    public static final String CONFIGURATION_DIRECTORY = "devtoolkit.conf.dir";

    public Map<String, Object> resolve(Map<String, Object> initialParameters,
                                       Configuration configuration,
                                       OutputListener outputListener) {
        Map<String, Object> effectiveParameters = new HashMap<>();
        effectiveParameters.putAll(System.getenv());
        effectiveParameters.put(CONFIGURATION_SOURCE, configuration.getSource());
        tryResolveConfigurationDirectory(effectiveParameters, configuration.getSource());
        effectiveParameters.putAll(initialParameters);

        ScriptApi scriptApi = new ScriptApi();
        scriptApi.setOutputListener(outputListener);
        scriptApi.setParameters(effectiveParameters);

        new ScriptsExecutor(scriptApi).executeScripts(configuration.getScripts());
        effectiveParameters.putAll(scriptApi.getParameters());

        return effectiveParameters;
    }

    private void tryResolveConfigurationDirectory(Map<String, Object> effectiveParameters, String source) {
        if (source == null) {
            return;
        }

        String parent = new File(source).getParent();
        if (parent != null) {
            effectiveParameters.put(CONFIGURATION_DIRECTORY, parent);
        }
    }
}
