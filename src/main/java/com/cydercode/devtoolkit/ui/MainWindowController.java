package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.CommandBuilder;
import com.cydercode.devtoolkit.CommandExecutor;
import com.cydercode.devtoolkit.Configuration;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
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
import java.util.List;
import java.util.Map;

public class MainWindowController {

    static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

    CommandBuilder commandBuilder = new CommandBuilder();
    CommandExecutor executor = new CommandExecutor();

    Map<String, Control> parameterTextFields = new HashMap<>();

    @FXML
    VBox parametersBox;

    @FXML
    VBox presetsBox;

    Configuration configuration;

    @FXML
    protected void initialize() {

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
            presetButton.setOnAction(ev -> {
                LOGGER.info("Executing preset: {}", presetName);
                Map<String, Object> parameters = new HashMap<>();
                parameterTextFields.forEach((p, tf) -> {
                    String value;

                    if (tf instanceof TextField) {
                        value = ((TextField) tf).getText();
                    } else if (tf instanceof ComboBox) {
                        value = ((ComboBox) tf).getValue().toString();
                    } else {
                        throw new RuntimeException("Unknown control type: " + tf.getClass());
                    }
                    parameters.put(p, value);
                });

                String command = commandBuilder.buildCommand(configuration, presetName, parameters);

                new Thread(() -> {
                    try {
                        executor.execute(command, output -> {
                            // ignore output
                        });
                    } catch (IOException e) {
                        LOGGER.error("Error during command execution", e);
                    }
                }).start();
            });
            presetsBox.getChildren().add(presetButton);
        }

        parametersBox.getChildren().clear();
        parameterTextFields.clear();
        for (String parameterName : configuration.getParameters().keySet()) {
            Label label = new Label();
            label.setText(parameterName);


            Map<String, Object> parameter = configuration.getParameters().get(parameterName);

            Object defaultValue = parameter.get("default");

            Control control;
            List<String> values = (List<String>) parameter.get("values");
            if (values != null) {
                control = new ComboBox(FXCollections.observableArrayList(values));
                ((ComboBox) control).setValue(defaultValue);
            } else {
                control = new TextField();
                if (defaultValue != null) {
                    ((TextField) control).setText(defaultValue.toString());
                }
            }


            parametersBox.getChildren().add(label);
            parametersBox.getChildren().add(control);

            parameterTextFields.put(parameterName, control);
        }
    }
}
