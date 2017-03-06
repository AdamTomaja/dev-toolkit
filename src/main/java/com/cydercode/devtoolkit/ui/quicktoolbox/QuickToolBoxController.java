package com.cydercode.devtoolkit.ui.quicktoolbox;

import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.ConfigurationTraverser;
import com.cydercode.devtoolkit.ui.PresetButtonFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.Set;
import java.util.function.Consumer;

import static com.cydercode.devtoolkit.Configuration.Q_TOOLBOX;

public class QuickToolBoxController {

    ConfigurationTraverser configurationTraverser = new ConfigurationTraverser();
    PresetButtonFactory presetButtonFactory = new PresetButtonFactory();

    @FXML
    Pane presetsBox;

    private Consumer<String> onAction;

    public void loadConfiguration(Configuration configuration) {
        presetsBox.getChildren().clear();

        Set<String> presets = configurationTraverser.getWithAttribute(configuration.getPresets(), Q_TOOLBOX, true);

        for (String preset : presets) {
            Button btn = presetButtonFactory.produce(preset, configuration.getPresets().get(preset));
            btn.setOnAction(ev -> {
                if (onAction != null) {
                    onAction.accept(preset);
                }
            });

            presetsBox.getChildren().add(btn);
        }
    }

    public void setOnAction(Consumer<String> onAction) {
        this.onAction = onAction;
    }
}
