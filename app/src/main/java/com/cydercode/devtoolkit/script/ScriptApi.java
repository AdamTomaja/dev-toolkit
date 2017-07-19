package com.cydercode.devtoolkit.script;

import com.cydercode.devtoolkit.executor.OutputListener;

import java.util.HashMap;
import java.util.Map;

public class ScriptApi {

    private final Map<String, Object> parameters = new HashMap<>();

    private OutputListener outputListener = OutputListener.NOOP;

    public Object getParameter(String name) {
        return parameters.get(name);
    }

    public void setParameter(String name, Object object) {
        parameters.put(name, object);
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters.putAll(parameters);
    }

    public OutputListener getOutputListener() {
        return outputListener;
    }

    public void setOutputListener(OutputListener outputListener) {
        this.outputListener = outputListener;
    }

    public void println(String text) {
        outputListener.onProcessOutput("Script: " + text);
    }
}
