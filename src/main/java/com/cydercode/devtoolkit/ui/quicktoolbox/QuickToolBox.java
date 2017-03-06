package com.cydercode.devtoolkit.ui.quicktoolbox;

import com.cydercode.devtoolkit.Configuration;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class QuickToolBox {

    private final Stage stage;
    private final QuickToolBoxController controller;

    public QuickToolBox(Stage stage, QuickToolBoxController controller) {
        this.stage = stage;
        this.controller = controller;
    }

    public void show() {
        stage.show();
    }

    public void hide() {
        stage.hide();
    }

    public boolean isShowing() {
        return stage.isShowing();
    }

    public void setOnAction(Consumer<String> presetConsumer) {
        controller.setOnAction(presetConsumer);
    }

    public void loadConfiguration(Configuration configuration) {
        controller.loadConfiguration(configuration);
    }
}
