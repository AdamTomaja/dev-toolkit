package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.CommandBuilder;
import com.cydercode.devtoolkit.CommandExecutor;
import com.cydercode.devtoolkit.Configuration;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.prefs.BackingStoreException;

public class MainWindowController {

    static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

    ConfigurationHolder configurationHolder = new ConfigurationHolder(MainWindowController.class.getName());

    CommandBuilder commandBuilder = new CommandBuilder();
    CommandExecutor executor = new CommandExecutor();
    ParameterControlFactory parameterControlFactory = new ParameterControlFactory();
    ParametersExtractor parametersExtractor = new ParametersExtractor();
    Map<String, Control> parametersControls = new HashMap<>();

    @FXML
    Pane parametersBox;

    @FXML
    Pane presetsBox;

    @FXML
    TabPane runTabs;

    @FXML
    protected void initialize() throws FileNotFoundException {
        Optional<Configuration> configuration = configurationHolder.loadLastConfiguration();
        if (configuration.isPresent()) {
            loadConfiguration(configuration.get());
        }
    }

    @FXML
    protected void openLoadConfigurationDialog() throws FileNotFoundException, BackingStoreException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select configuration json file");
        File file = fileChooser.showOpenDialog(null);
        LOGGER.info("Selected file: {}", file);

        if (file == null) {
            return;
        }

         loadConfiguration(configurationHolder.loadConfiguration(file).get());
    }

    private void loadConfiguration(Configuration configuration) {
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
            VBox vBox = new VBox();
            Label label = new Label();
            label.setText(parameterName);
            Map<String, Object> parameter = configuration.getParameters().get(parameterName);
            Control control = parameterControlFactory.produceControl(parameter);
            vBox.getChildren().add(label);
            vBox.getChildren().add(control);
            parametersBox.getChildren().add(vBox);
            parametersControls.put(parameterName, control);
        }
    }

    private void runPreset(String presetName) {
        LOGGER.info("Executing preset: {}", presetName);
        Map<String, Object> parameters = parametersExtractor.extractParameters(parametersControls);

        String command = commandBuilder.buildCommand(configurationHolder.getCurrentConfiguration().get(), presetName, parameters);

        TextArea logsArea = new TextArea();
        Tab tab = new Tab();
        tab.setText("Run: " + presetName);
        tab.setContent(logsArea);
        runTabs.getTabs().add(tab);
        runTabs.getSelectionModel().select(tab);

        new Thread(() -> {
            try {
                try {
                    executor.execute(command, output -> {
                        Platform.runLater(() -> {
                            logsArea.appendText(output);
                            logsArea.appendText(System.lineSeparator());
                        });
                    });
                } catch (InterruptedException e) {
                    LOGGER.error("Error when executing command", e);
                }
            } catch (IOException e) {
                LOGGER.error("Error during command execution", e);
            }
        }).start();
    }
}
