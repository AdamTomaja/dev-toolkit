package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.executor.CommandExecutorListener;
import javafx.scene.control.TextArea;

import static java.lang.String.format;
import static javafx.application.Platform.runLater;

public class UiCommandExecutorListener implements CommandExecutorListener {

    private final TextArea logsArea;
    private final String jobName;
    private final NotificationFacade notificationFacade;

    public UiCommandExecutorListener(String jobName, TextArea logsArea, NotificationFacade notificationFacade) {
        this.logsArea = logsArea;
        this.jobName = jobName;
        this.notificationFacade = notificationFacade;
    }

    @Override
    public void onProcessOutput(String output) {
        runLater(() -> {
            logsArea.appendText(output);
            logsArea.appendText(System.lineSeparator());
        });
    }

    @Override
    public void onProcessFinished(int exitValue) {
        runLater(() -> notificationFacade.showInformation("Dev-toolkit",
                format("Job %s finished with status: %d",
                        jobName,
                        exitValue)));
    }
}
