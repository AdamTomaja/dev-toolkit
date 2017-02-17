package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.CommandBuilder;
import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.executor.CommandExecutor;
import eu.hansolo.enzo.notification.Notification;
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
import java.util.concurrent.atomic.AtomicInteger;

public class MainWindowController {

    static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

    ConfigurationHolder configurationHolder = new ConfigurationHolder(MainWindowController.class.getName());

    NotificationFacade notificationFacade = new NotificationFacade();
    CommandBuilder commandBuilder = new CommandBuilder();
    CommandExecutor executor = new CommandExecutor();
    ParameterControlFactory parameterControlFactory = new ParameterControlFactory();
    ParametersExtractor parametersExtractor = new ParametersExtractor();
    DialogHelper dialogHelper = new DialogHelper();

    Map<String, Control> parametersControls = new HashMap<>();

    AtomicInteger jobsCounter = new AtomicInteger(0);

    @FXML
    Pane parametersBox;

    @FXML
    Pane presetsBox;

    @FXML
    TabPane runTabs;

    @FXML
    protected void reloadConfiguration() throws FileNotFoundException {
        initialize();
    }

    @FXML
    protected void initialize() throws FileNotFoundException {
        Optional<Configuration> configuration = configurationHolder.loadLastConfiguration();
        if (configuration.isPresent()) {
            loadConfiguration(configuration.get());
        }
    }

    @FXML
    protected void openLoadConfigurationDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select configuration json file");
        File file = fileChooser.showOpenDialog(null);
        LOGGER.info("Selected file: {}", file);

        if (file == null) {
            return;
        }

        try {
            loadConfiguration(configurationHolder.loadConfiguration(file).get());
        } catch (Exception e) {
            LOGGER.error("Unable to load configuration", e);
            dialogHelper.createExceptionAlert("Error when loading configuration", e)
                    .showAndWait();
        }
    }

    private void loadConfiguration(Configuration configuration) {
        clearConfiguration();

        for (String presetName : configuration.getPresets().keySet()) {
            Button presetButton = new Button();
            presetButton.setText(presetName);
            presetButton.setOnAction(ev -> {
                runPreset(presetName);
            });
            presetsBox.getChildren().add(presetButton);
        }

        for (String parameterName : configuration.getParameters().keySet()) {
            Map<String, Object> parameter = configuration.getParameters().get(parameterName);
            Control control = parameterControlFactory.produceControl(parameter);

            parametersControls.put(parameterName, control);

            if (!parameterControlFactory.isHidden(parameter)) {
                VBox vBox = new VBox();
                Label label = new Label();
                label.setText(parameterName);
                vBox.getChildren().add(label);
                vBox.getChildren().add(control);
                parametersBox.getChildren().add(vBox);
            }
        }
    }

    private void clearConfiguration() {
        presetsBox.getChildren().clear();
        parametersBox.getChildren().clear();
        parametersControls.clear();
    }

    private void runPreset(String presetName) {
        LOGGER.info("Executing preset: {}", presetName);
        String jobName = String.format("#%d - %s", jobsCounter.incrementAndGet(), presetName);

        Map<String, Object> parameters = parametersExtractor.extractParameters(parametersControls);

        String command = commandBuilder.buildCommand(configurationHolder.getCurrentConfiguration().get(),
                presetName,
                parameters);

        TextArea logsArea = new TextArea();
        Tab tab = new Tab();
        tab.setText(jobName);
        tab.setContent(logsArea);
        runTabs.getTabs().add(tab);
        runTabs.getSelectionModel().select(tab);

        new Thread(() -> {
            try {
                executor.execute(command, new UiCommandExecutorListener(jobName, logsArea, notificationFacade));
            } catch (InterruptedException | IOException e) {
                LOGGER.error("Error during command execution", e);
                Platform.runLater(() -> {
                    dialogHelper.createExceptionAlert("Error during command execution", e)
                            .showAndWait();
                });
            }
        }).start();
    }
}
