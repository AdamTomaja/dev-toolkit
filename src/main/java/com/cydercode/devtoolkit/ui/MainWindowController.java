package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.CommandBuilder;
import com.cydercode.devtoolkit.CommandExecutor;
import com.cydercode.devtoolkit.Configuration;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MainWindowController {

    static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

    CommandBuilder commandBuilder = new CommandBuilder();
    CommandExecutor executor = new CommandExecutor();

    @FXML
    VBox presetsBox;

    Configuration configuration;

    @FXML
    protected void initialize() {
        Button button = new Button();
        button.setText("Lol");
        presetsBox.getChildren().add(button);

        System.out.println("lol");
    }

    @FXML
    protected void openLoadConfigurationDialog() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select configuration json file");
        File file = fileChooser.showOpenDialog(null);
        LOGGER.info("Selected file: {}", file);

        if (file == null) {
            return;
        }

        configuration = new Gson().fromJson(new FileReader(file), Configuration.class);
        LOGGER.info("Loaded configuration: {}", configuration);
        presetsBox.getChildren().clear();
        for (String presetName : configuration.getPresets().keySet()) {
            Button presetButton = new Button();
            presetButton.setText(presetName);
            presetsBox.getChildren().add(presetButton);
        }
    }
}
