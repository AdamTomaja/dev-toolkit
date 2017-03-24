package com.cydercode.devtoolkit.pluginexample;

import com.cydercode.devtoolkit.plugin.Plugin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PluginExample implements Plugin {

    static final Logger LOGGER = LoggerFactory.getLogger(PluginExample.class);

    @Override
    public void onStart() {
        LOGGER.info("Starting plugin...");
    }

    @Override
    public void onAction() {
        LOGGER.info("Plugin action clicked!");

        FXMLLoader loader = new FXMLLoader(this.getClass().getClassLoader().getResource("plugin_window.fxml"));
        loader.setController(new MainWindowController());
        Parent root = null;
        try {
            root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setTitle("Dev-toolkit Example Plugin");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        LOGGER.info("Stopping plugin...");
    }
}
