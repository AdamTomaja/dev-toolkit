package com.cydercode.devtoolkit.executor;

public interface CommandExecutorListener extends OutputListener, ErrorOutputListener {
    void onProcessFinished(int exitValue);
    void onProcessCreated(Process process);
    void onCommand(String commandLine);
}
