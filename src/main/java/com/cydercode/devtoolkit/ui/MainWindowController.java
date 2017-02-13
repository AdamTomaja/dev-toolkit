package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.CommandBuilder;
import com.cydercode.devtoolkit.CommandExecutor;
import com.cydercode.devtoolkit.Configuration;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainWindowController {

    static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

    CommandBuilder commandBuilder = new CommandBuilder();
    CommandExecutor executor = new CommandExecutor();
    ParameterControlFactory parameterControlFactory = new ParameterControlFactory();
    ParametersExtractor parametersExtractor = new ParametersExtractor();
    Map<String, Control> parametersControls = new HashMap<>();

    @FXML
    VBox parametersBox;

    @FXML
    VBox presetsBox;

    @FXML
    TabPane runTabs;

    Configuration configuration;

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
            presetButton.setOnAction(ev -> {
                runPreset(presetName);
            });
            presetsBox.getChildren().add(presetButton);
        }

        parametersBox.getChildren().clear();
        parametersControls.clear();
        for (String parameterName : configuration.getParameters().keySet()) {
            Label label = new Label();
            label.setText(parameterName);
            Map<String, Object> parameter = configuration.getParameters().get(parameterName);
            Control control = parameterControlFactory.produceControl(parameter);
            parametersBox.getChildren().add(label);
            parametersBox.getChildren().add(control);
            parametersControls.put(parameterName, control);
        }
    }

    private void runPreset(String presetName) {
        LOGGER.info("Executing preset: {}", presetName);
        Map<String, Object> parameters = parametersExtractor.extractParameters(parametersControls);

        String command = commandBuilder.buildCommand(configuration, presetName, parameters);

        TextArea logsArea = new TextArea();
        Tab tab = new Tab();
        tab.setText("Run: " + presetName);
        tab.setContent(logsArea);
        runTabs.getTabs().add(tab);
        runTabs.getSelectionModel().select(tab);

        new Thread(() -> {
            try {
                executor.execute(command, output -> {
                    Platform.runLater(() -> {
                        logsArea.appendText(output);
                        logsArea.appendText(System.lineSeparator());
                    });
                });
            } catch (IOException e) {
                LOGGER.error("Error during command execution", e);
            }
        }).start();
    }
}
