package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.Configuration;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

import java.util.Map;

public class PresetButtonFactory {

    public Button produce(String presetName, Map<String, Object> presetConfiguration) {
        Button presetButton = new Button();

        String description = (String) presetConfiguration.get(Configuration.DESCRIPTION);
        if (description != null && (!description.isEmpty())) {
            presetButton.setTooltip(new Tooltip(description));
        }

        presetButton.setText(presetName);

        return presetButton;
    }
}
