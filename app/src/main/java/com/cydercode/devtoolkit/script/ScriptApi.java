package com.cydercode.devtoolkit.script;

import java.util.Map;

public class ScriptApi {

    private final Map<String, Object> parameters;

    public ScriptApi(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Object getParameter(String name) {
        return parameters.get(name);
    }

    public void setParameter(String name, Object object) {
        parameters.put(name, object);
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
