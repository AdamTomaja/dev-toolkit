package com.cydercode.devtoolkit.httpapi;

import com.cydercode.devtoolkit.plugin.DevToolkitContext;
import com.cydercode.devtoolkit.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpApiPlugin implements Plugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpApiPlugin.class);

    @Override
    public void onStart(DevToolkitContext context) {
        LOGGER.info("Starting HTTP Api Plugin");
    }

    @Override
    public void onAction() {

    }

    @Override
    public void onStop() {

    }
}
