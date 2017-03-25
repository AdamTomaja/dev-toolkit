package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.plugin.PluginDescriptor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.List;

public class AboutWindowController {

    @FXML
    private Pane pluginsBox;

    public void setLoadedPlugins(List<PluginDescriptor> plugins) {
        for (PluginDescriptor pluginDescriptor : plugins) {
            Label label = new Label(pluginDescriptor.getName());
            pluginsBox.getChildren().add(label);
        }
    }
}
