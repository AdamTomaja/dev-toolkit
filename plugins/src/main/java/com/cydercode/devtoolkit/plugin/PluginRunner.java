package com.cydercode.devtoolkit.plugin;

import com.google.common.io.Resources;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PluginRunner extends Application {

    public void start(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Resources.getResource("plugin_runner.fxml"));

        Scene scene = new Scene(root, 300, 300);

        primaryStage.setTitle("Dev-Toolkit plugin runner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
