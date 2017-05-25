package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.configuration.model.ChildPreset;
import com.cydercode.devtoolkit.configuration.model.ChildPresets;
import com.cydercode.devtoolkit.configuration.model.Preset;
import com.cydercode.devtoolkit.executor.CommandExecutor;
import com.cydercode.devtoolkit.ui.JobListener;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PresetRunner {

    private final CommandExecutor executor;
    private final CommandBuilder commandBuilder;

    public PresetRunner(CommandExecutor executor, CommandBuilder commandBuilder) {
        this.executor = executor;
        this.commandBuilder = commandBuilder;
    }

    public int run(String presetName, Configuration configuration, Map<String, Object> parameters, JobListener jobListener) throws IOException, InterruptedException {
        Optional<Preset> preset = configuration.getPreset(presetName);
        if (!preset.isPresent()) {
            throw new IllegalArgumentException("Preset " + presetName + " not found!");
        }

        ChildPresets childPresetsWrapper = preset.get().getChildPresets();
        if (childPresetsWrapper != null) {
            // compound preset
            List<ChildPreset> children = childPresetsWrapper.getChildPreset();
            int exitValue = -1;
            for (ChildPreset childPreset : children) {
                exitValue = executor.execute(commandBuilder.buildCommand(configuration, childPreset.getPreset(), parameters), jobListener);
                if (exitValue != 0 && (!childPreset.isIgnorable())) {
                    return exitValue;
                }
            }

            return exitValue;
        } else {
            return executor.execute(commandBuilder.buildCommand(configuration, presetName, parameters), jobListener);
        }
    }
}
