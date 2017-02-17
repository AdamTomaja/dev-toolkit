package com.cydercode.devtoolkit.ui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.google.common.io.Resources.getResource;

public class Group extends VBox {

    @FXML
    private Pane parametersBox;

    @FXML
    private Pane presetsBox;

    @FXML
    private Label groupNameLabel;

    public Group() {
        FXMLLoader fxmlLoader = new FXMLLoader(getResource("com/cydercode/devtoolkit/ui/group.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setText(String name) {
        groupNameLabel.setText(name);
    }

    public void addParameter(Node parameter) {
        parametersBox.getChildren().add(parameter);
    }

    public void addPreset(Node preset) {
        presetsBox.getChildren().add(preset);
    }
}
