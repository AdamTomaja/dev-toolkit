package com.cydercode.devtoolkit.ui.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static com.google.common.io.Resources.getResource;

public class WindowLoader {

    private WindowTitleCreator windowTitleCreator = new WindowTitleCreator();

    private final FXMLLoader loader;

    public WindowLoader(String path) {
        loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
    }

    public Stage load(String subtitle) throws IOException {
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(getResource("com/cydercode/devtoolkit/ui/icon.png").openStream()));
        stage.setTitle(windowTitleCreator.create(subtitle));
        return stage;
    }

    public <T> T getController() {
        return loader.getController();
    }
}
