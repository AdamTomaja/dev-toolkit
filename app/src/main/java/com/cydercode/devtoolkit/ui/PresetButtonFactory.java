package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.configuration.model.Preset;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class PresetButtonFactory {

    public Button produce(Preset preset) {
        Button presetButton = new Button();

        String description = preset.getDescription();

        if (description != null && (!description.isEmpty())) {
            presetButton.setTooltip(new Tooltip(description));
        }

        presetButton.setText(preset.getName());

        return presetButton;
    }
}
