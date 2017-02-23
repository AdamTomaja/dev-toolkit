package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.executor.CommandExecutorListener;
import com.cydercode.devtoolkit.ui.component.JobTab;

import static java.lang.String.format;
import static javafx.application.Platform.runLater;

public class JobListener implements CommandExecutorListener {

    private final JobTab jobTab;
    private final String jobName;
    private final NotificationFacade notificationFacade;
    private Process process;

    public JobListener(String jobName, JobTab jobTab, NotificationFacade notificationFacade) {
        this.jobTab = jobTab;
        this.jobName = jobName;
        this.notificationFacade = notificationFacade;
    }

    @Override
    public void onCommand(String commandLine) {
        onProcessOutput(String.format("Running command: %s", commandLine));
    }

    @Override
    public void onProcessOutput(String output) {
        runLater(() -> {
            jobTab.appendText(output);
            jobTab.appendText(System.lineSeparator());
        });
    }

    @Override
    public void onProcessFinished(int exitValue) {
        notificationFacade.beep();

        runLater(() -> notificationFacade.showInformation("Dev-toolkit",
                format("Job %s finished with status: %d",
                        jobName,
                        exitValue)));

        jobTab.setKillDisabled(true);

        if (exitValue != 0) {
            jobTab.showError();
        } else {
            jobTab.showSuccess();
        }
    }

    @Override
    public void onProcessCreated(Process process) {
        this.process = process;
        jobTab.setKillDisabled(false);
    }

    public void kill() {
        process.destroyForcibly();
    }
}
