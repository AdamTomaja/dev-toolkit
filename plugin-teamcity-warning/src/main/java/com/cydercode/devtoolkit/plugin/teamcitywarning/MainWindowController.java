package com.cydercode.devtoolkit.plugin.teamcitywarning;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.util.Map;

public class MainWindowController {

    @FXML
    Pane mainBox;

    @FXML
    TextArea statusArea;

    public void setError() {
        mainBox.setStyle("-fx-background-color: orange;");
    }

    public void setStatus(Map status) {
        if(status.get("status").equals("SUCCESS")) {
            mainBox.setStyle("-fx-background-color: green;");
        } else {
            mainBox.setStyle("-fx-background-color: red;");
        }
        statusArea.setText(status.toString());
    }
}
