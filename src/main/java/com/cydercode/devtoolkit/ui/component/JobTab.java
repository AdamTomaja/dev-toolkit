package com.cydercode.devtoolkit.ui.component;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import java.io.IOException;

import static com.google.common.io.Resources.getResource;

public class JobTab extends HBox {

    @FXML
    TextArea logsArea;

    @FXML
    Button killButton;

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
}
