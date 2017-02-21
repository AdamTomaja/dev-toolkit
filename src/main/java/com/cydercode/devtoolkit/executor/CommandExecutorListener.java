package com.cydercode.devtoolkit.executor;

public interface CommandExecutorListener {
    void onProcessOutput(String output);
    void onProcessFinished(int exitValue);
    void onProcessCreated(Process process);
    void onCommand(String commandLine);
}
