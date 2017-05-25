package com.cydercode.devtoolkit.ui.quicktoolbox;

import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.configuration.model.Preset;
import com.cydercode.devtoolkit.ui.PresetButtonFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.function.Consumer;

public class QuickToolBoxController {

    PresetButtonFactory presetButtonFactory = new PresetButtonFactory();

    @FXML
    Pane presetsBox;

    private Consumer<String> onAction;

    public void loadConfiguration(Configuration configuration) {
        presetsBox.getChildren().clear();

        List<Preset> presets = configuration.getPresetsWithPredicate(p -> p.isQtoolbox() != null && p.isQtoolbox());

        for (Preset preset : presets) {
            Button btn = presetButtonFactory.produce(preset);
            btn.setOnAction(ev -> {
                if (onAction != null) {
                    onAction.accept(preset.getName());
                }
            });

            presetsBox.getChildren().add(btn);
        }
    }

    public void setOnAction(Consumer<String> onAction) {
        this.onAction = onAction;
    }
}
