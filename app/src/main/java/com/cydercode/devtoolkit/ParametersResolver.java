package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.executor.OutputListener;
import com.cydercode.devtoolkit.script.ScriptApi;
import com.cydercode.devtoolkit.script.ScriptsExecutor;

import java.util.HashMap;
import java.util.Map;

public class ParametersResolver {

    public Map<String, Object> resolve(Map<String, Object> initialParameters, Map<String, String> scripts, OutputListener outputListener) {
        Map<String, Object> effectiveParameters = new HashMap<>();
        effectiveParameters.putAll(System.getenv());
        effectiveParameters.putAll(initialParameters);

        ScriptApi scriptApi = new ScriptApi();
        scriptApi.setOutputListener(outputListener);
        scriptApi.setParameters(effectiveParameters);

        new ScriptsExecutor(scriptApi).executeScripts(scripts);
        effectiveParameters.putAll(scriptApi.getParameters());

        return effectiveParameters;
    }
}
