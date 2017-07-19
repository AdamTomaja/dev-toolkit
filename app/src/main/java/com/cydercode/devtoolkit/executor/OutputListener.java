package com.cydercode.devtoolkit.executor;

@FunctionalInterface
public interface OutputListener {
    void onProcessOutput(String output);

    OutputListener NOOP = output -> {
        // do nothing;
    };
}
