package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.ui.window.WindowTitleCreator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static com.google.common.io.Resources.getResource;

public class App extends Application {

    private MainWindowController mainWindowController;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main_window.fxml"));
        Parent root = loader.load();
        mainWindowController = loader.getController();

        Scene scene = new Scene(root, 600, 800);
        stage.setTitle(new WindowTitleCreator().create(getVersion()));
        stage.setScene(scene);
        stage.getIcons().add(new Image(getResource("com/cydercode/devtoolkit/ui/icon.png").openStream()));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        mainWindowController.stop();
    }

    private String getVersion() {
        String version = App.class.getPackage().getImplementationVersion();
        return version == null ? "V?" : version;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
