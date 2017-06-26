package com.cydercode.devtoolkit.executor;

import com.cydercode.devtoolkit.Job;

import java.util.Map;

public class StandardJob implements Job {

    private int id;
    private String preset;
    private Map<String, Object> parameters;

    public StandardJob() {
        //
    }

    public StandardJob(Job job) {
        id = job.getID();
        preset = job.getPreset();
        parameters = job.getParameters();
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public int getID() {
        return id;
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
