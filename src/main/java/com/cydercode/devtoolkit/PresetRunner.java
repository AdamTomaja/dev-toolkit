package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.executor.CommandExecutor;
import com.cydercode.devtoolkit.ui.JobListener;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.cydercode.devtoolkit.Configuration.PRESETS;

public class PresetRunner {

    private final CommandExecutor executor;
    private final CommandBuilder commandBuilder;

    public PresetRunner(CommandExecutor executor, CommandBuilder commandBuilder) {
        this.executor = executor;
        this.commandBuilder = commandBuilder;
    }


    public int run(String presetName, Configuration configuration, Map<String, Object> parameters, JobListener jobListener) throws IOException, InterruptedException {
        Map<String, Object> preset = configuration.getPresets().get(presetName);
        if (preset.containsKey(PRESETS)) {
            // compound preset
            List<String> children = (List<String>) preset.get(PRESETS);
            int exitValue = -1;
            for (String childPreset : children) {
                exitValue = executor.execute(commandBuilder.buildCommand(configuration, childPreset, parameters), jobListener);
                if (exitValue != 0) {
                    return exitValue;
                }
            }

            return exitValue;
        } else {
            return executor.execute(commandBuilder.buildCommand(configuration, presetName, parameters), jobListener);
        }
    }
}
