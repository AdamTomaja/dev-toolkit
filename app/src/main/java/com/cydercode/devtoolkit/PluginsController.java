package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.plugin.Plugin;
import com.cydercode.devtoolkit.plugin.PluginDescriptor;
import com.cydercode.devtoolkit.pluginloader.PluginLoader;
import com.cydercode.devtoolkit.ui.MainWindowController;
import javafx.scene.control.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PluginsController {

    static final Logger LOGGER = LoggerFactory.getLogger(PluginsController.class);

    private PluginLoader pluginLoader = new PluginLoader();

    private MainWindowController mainWindowController;

    public PluginsController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public void initialize() {
        List<PluginDescriptor> plugins = pluginLoader.loadPlugins();
        LOGGER.info("Loaded plugins: {}", plugins);
        for (PluginDescriptor pluginDescriptor : plugins) {
            MenuItem menuItem = new MenuItem(pluginDescriptor.getName());
            Plugin plugin = pluginDescriptor.getPlugin();

            menuItem.setOnAction(ev -> {
                plugin.onAction();
            });

            mainWindowController.getPluginsMenu().getItems().add(menuItem);

            plugin.onStart();
        }
    }
}
