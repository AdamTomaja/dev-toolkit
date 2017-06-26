package com.cydercode.devtoolkit.httpapi;

import com.cydercode.devtoolkit.plugin.DevToolkitContext;
import com.cydercode.devtoolkit.plugin.Plugin;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.util.Map;

public class HttpApiPlugin implements Plugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpApiPlugin.class);
    private DevToolkitContext context;

    @Override
    public void onStart(DevToolkitContext context) {
        LOGGER.info("Starting HTTP Api Plugin");
        this.context = context;

        Gson gson = new Gson();

        Spark.get("/groups", (req, res) -> context.getConfigurationHolder()
                .getCurrentConfiguration()
                .get()
                .getGroups(), gson::toJson);

        Spark.get("/presets/:group", (req, res) -> context.getConfigurationHolder()
                        .getCurrentConfiguration()
                        .get()
                        .findPresetsWithGroup(req.params(":group")),
                gson::toJson);

        Spark.get("/parameters/:group", (req, res) -> context.getConfigurationHolder()
                        .getCurrentConfiguration()
                        .get()
                        .findParametersWithGroup(req.params(":group")),
                gson::toJson);

        Spark.post("/execute/:preset", (req, res) -> {
                    Map parameters = gson.fromJson(req.body(), Map.class);
                    return context.enqueueJob(new JobRequest(req.params(":preset"), parameters));
                }
                , gson::toJson);
    }

    @Override
    public void onAction() {
        LOGGER.info(String.valueOf(context.getConfigurationHolder().getCurrentConfiguration()));
    }

    @Override
    public void onStop() {

    }
}
