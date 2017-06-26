package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.plugin.DevToolkitContext;
import com.cydercode.devtoolkit.plugin.Plugin;
import com.cydercode.devtoolkit.plugin.PluginDescriptor;
import com.cydercode.devtoolkit.pluginloader.PluginLoader;
import com.cydercode.devtoolkit.ui.MainWindowController;
import javafx.scene.control.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PluginsController {

    static final Logger LOGGER = LoggerFactory.getLogger(PluginsController.class);

    private PluginLoader pluginLoader = new PluginLoader();
    private List<PluginDescriptor> plugins = new ArrayList<>();

    private MainWindowController mainWindowController;

    public PluginsController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public void initialize(DevToolkitContext context) {
        plugins = pluginLoader.loadPlugins();
        LOGGER.info("Loaded plugins: {}", plugins);
        for (PluginDescriptor pluginDescriptor : plugins) {
            MenuItem menuItem = new MenuItem(pluginDescriptor.getName());
            Plugin plugin = pluginDescriptor.getPlugin();

            menuItem.setOnAction(ev -> {
                plugin.onAction();
            });

            mainWindowController.getPluginsMenu().getItems().add(menuItem);

            try {
                plugin.onStart(context);
            } catch (Throwable e) {
                LOGGER.error("Cannot start plugin: {}", pluginDescriptor.getName(), e);
            }
        }
    }

    public List<PluginDescriptor> getLoadedPlugins() {
        return plugins;
    }

    public void stop() {
        for (PluginDescriptor pluginDescriptor : plugins) {
            try {
                pluginDescriptor.getPlugin().onStop();
            } catch (Exception e) {
                LOGGER.error("Unable to stop plugin: {}", pluginDescriptor.getName(), e);
            }
        }
    }
}
