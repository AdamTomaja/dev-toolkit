package com.cydercode.devtoolkit.pluginexample;

import com.cydercode.devtoolkit.plugin.PluginRunner;

/**
 * This class shows how to run dev-toolkit plugin without the dev-toolkit application
 */
public class PluginDemonstration {

    public static void main(String[] args) {
        new PluginRunner().start(args);
    }
}
