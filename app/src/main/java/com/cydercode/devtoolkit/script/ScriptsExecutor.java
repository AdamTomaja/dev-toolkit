package com.cydercode.devtoolkit.script;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

public class ScriptsExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptsExecutor.class);

    private final ScriptApi scriptApi;

    public ScriptsExecutor(ScriptApi scriptApi) {
        this.scriptApi = scriptApi;
    }

    public void executeScripts(Map<String, String> scripts) {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByExtension("js");
        scriptEngine.put("api", scriptApi);
        scripts.forEach((name, source) -> {
            LOGGER.info("Executing script: {}", name);
            try {
                scriptEngine.eval(source);
            } catch (ScriptException e) {
                LOGGER.error("Error during script eval", e);
                scriptApi.println(String.format("Exception in %s script: %s - %s", name, e.getClass(), e.getMessage()));
                throw new RuntimeException(name + " script error", e);
            }
        });
    }
}
