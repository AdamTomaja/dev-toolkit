package com.cydercode.devtoolkit.plugin;

public interface Plugin {
    void onStart(DevToolkitContext context);
    void onAction();
    void onStop();
}
