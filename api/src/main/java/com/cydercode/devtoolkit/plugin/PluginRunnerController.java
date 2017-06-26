package com.cydercode.devtoolkit.plugin;

import com.cydercode.devtoolkit.DummyDevToolkitContext;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.InputStreamReader;

import static com.cydercode.devtoolkit.plugin.PluginConstants.DESCRIPTOR_FILENAME;

public class PluginRunnerController {

    @FXML
    private Button actionButton;

    private PluginDescriptor pluginDescriptor;

    @FXML
    public void initialize() {
        DevToolkitContext context = new DummyDevToolkitContext();
        pluginDescriptor = new Gson().fromJson(new InputStreamReader(this.getClass()
                .getClassLoader().getResourceAsStream(DESCRIPTOR_FILENAME)), PluginDescriptor.class);
        if (pluginDescriptor.getMainClass() == null) {
            throw new IllegalArgumentException("mainClass cannot be null!");
        }

        try {
            Class pluginClass = Class.forName(pluginDescriptor.getMainClass());
            Plugin plugin = (Plugin) pluginClass.newInstance();
            plugin.onStart(context);
            pluginDescriptor.setPlugin(plugin);
            actionButton.setText(pluginDescriptor.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void executeAction() {
        pluginDescriptor.getPlugin().onAction();
    }
}
