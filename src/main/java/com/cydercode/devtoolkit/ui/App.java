package com.cydercode.devtoolkit.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.google.common.io.Resources.getResource;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));

        Scene scene = new Scene(root, 600, 800);

        stage.setTitle("CyderCode dev-toolkit " + getVersion());
        stage.setScene(scene);
        stage.getIcons().add(new Image(getResource("com/cydercode/devtoolkit/ui/icon.png").openStream()));
        stage.show();
    }

    private String getVersion() {
        String version = App.class.getPackage().getImplementationVersion();
        return version == null ? "V?" : version;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
