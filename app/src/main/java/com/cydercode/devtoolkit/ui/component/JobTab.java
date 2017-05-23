package com.cydercode.devtoolkit.ui.component;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.IOException;

import static com.google.common.io.Resources.getResource;

public class JobTab extends HBox {

    public static final int LIMIT = 6 * 1000;

    @FXML
    TextArea logsArea;

    @FXML
    Button killButton;

    @FXML
    Label commandLineLabel;

    @FXML
    public void initialize() {
        logsArea.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                int length = logsArea.getText().length();
                if (length >= LIMIT) {
                    logsArea.setText(logsArea.getText().substring(length - LIMIT, length));
                }
            }
        });
    }

    public JobTab() {
        FXMLLoader fxmlLoader = new FXMLLoader(getResource("com/cydercode/devtoolkit/ui/component/job_tab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showError() {
        logsArea.getStyleClass().add("error");
    }

    public void showSuccess() {
        logsArea.getStyleClass().add("success");
    }

    public void setOnKillAction(EventHandler<ActionEvent> eventHandler) {
        killButton.setOnAction(eventHandler);
    }

    public void appendText(String text) {
        logsArea.appendText(text);
    }

    public void setKillDisabled(boolean killDisabled) {
        killButton.setDisable(killDisabled);
    }

    public int getTextLength() {
        return logsArea.getLength();
    }

    public String getText() {
        return logsArea.getText();
    }

    public void setCommandLineText(String commandLine) {
        commandLineLabel.setText(commandLine);
    }
}
