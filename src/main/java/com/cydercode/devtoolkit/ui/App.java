package com.cydercode.devtoolkit.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));

        Scene scene = new Scene(root, 600, 800);

        stage.setTitle("CyderCode dev-toolkit");
        stage.setScene(scene);
        stage.show();
    }
}
