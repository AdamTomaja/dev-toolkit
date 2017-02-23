package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.executor.CommandExecutor;
import com.cydercode.devtoolkit.ui.JobListener;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static com.cydercode.devtoolkit.Configuration.IGNORABLE;
import static com.cydercode.devtoolkit.Configuration.PRESET;
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
            List<Object> children = (List<Object>) preset.get(PRESETS);
            int exitValue = -1;
            for (Object childPreset : children) {
                boolean ignorable = false;
                String childPresetName;
                if(childPreset instanceof String) {
                    childPresetName = (String) childPreset;
                } else if(childPreset instanceof Map) {
                    Map<String, Object> childPresetParameters = (Map) childPreset;
                    childPresetName = (String) childPresetParameters.get(PRESET);
                    if(childPresetParameters.containsKey(IGNORABLE)) {
                        ignorable = (boolean) childPresetParameters.get(IGNORABLE);
                    }
                } else {
                    throw new IllegalArgumentException("Child preset can be string or map");
                }

                exitValue = executor.execute(commandBuilder.buildCommand(configuration, childPresetName, parameters), jobListener);
                if (exitValue != 0 && (!ignorable)) {
                    return exitValue;
                }
            }

            return exitValue;
        } else {
            return executor.execute(commandBuilder.buildCommand(configuration, presetName, parameters), jobListener);
        }
    }
}
