package com.cydercode.devtoolkit.script;

import com.cydercode.devtoolkit.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

public class ScriptsExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptsExecutor.class);

    public Map<String, Object> executeScripts(Configuration configuration, Map<String, Object> parameters) {
        Map<String, String> scripts = configuration.getScripts();
        ScriptApi api = new ScriptApi(new HashMap<>(parameters));
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByExtension("js");
        scriptEngine.put("api", api);
        scripts.forEach((name, source) -> {
            LOGGER.info("Executing script: {}", name);
            try {
                scriptEngine.eval(source);
            } catch (ScriptException e) {
                LOGGER.error("Error during script eval", e);
                throw new RuntimeException(e);
            }
        });

        return api.getParameters();
    }
}
