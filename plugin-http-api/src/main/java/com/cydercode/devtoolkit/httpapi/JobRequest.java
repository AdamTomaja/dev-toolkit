package com.cydercode.devtoolkit.httpapi;

import com.cydercode.devtoolkit.Job;

import java.util.Map;

public class JobRequest implements Job {

    private String preset;
    private Map<String,Object> parameters;

    public JobRequest(String preset, Map<String, Object> parameters) {
        this.preset = preset;
        this.parameters = parameters;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public String getPreset() {
        return preset;
    }
}
