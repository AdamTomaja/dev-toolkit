package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.CommandBuilder;
import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.ConfigurationTraverser;
import com.cydercode.devtoolkit.PresetRunner;
import com.cydercode.devtoolkit.executor.CommandExecutor;
import com.cydercode.devtoolkit.ui.component.Group;
import com.cydercode.devtoolkit.ui.component.JobTab;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.cydercode.devtoolkit.Configuration.DEFAULT_GROUP;
import static com.google.common.io.Resources.getResource;

public class MainWindowController {

    static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

    ConfigurationHolder configurationHolder = new ConfigurationHolder(MainWindowController.class.getName());

    NotificationFacade notificationFacade = new NotificationFacade();
    ParameterControlFactory parameterControlFactory = new ParameterControlFactory();
    ParametersExtractor parametersExtractor = new ParametersExtractor();
    DialogHelper dialogHelper = new DialogHelper();
    ConfigurationTraverser configurationTraverser = new ConfigurationTraverser();
    PresetRunner presetRunner = new PresetRunner(new CommandExecutor(), new CommandBuilder());

    Map<String, Control> parametersControls = new HashMap<>();

    AtomicInteger jobsCounter = new AtomicInteger(0);

    @FXML
    TabPane runTabs;

    @FXML
    Pane groupsBox;

    @FXML
    protected void reloadConfiguration() throws FileNotFoundException {
        initialize();
    }

    @FXML
    public void openToolWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tool_window.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.getIcons().add(new Image(getResource("com/cydercode/devtoolkit/ui/icon.png").openStream()));
        stage.setTitle("Dev-toolkit tools");
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

    void loadConfiguration(Configuration configuration) {
        clearConfiguration();

        Set<String> groups = configurationTraverser.getGroups(configuration);
        groups.add(DEFAULT_GROUP); // default group

        for (String groupName : groups) {
            Group group = new Group();
            group.setText(Objects.equals(groupName, DEFAULT_GROUP) ? "Default group" : groupName);

            Set<String> presets = configurationTraverser.findObjectsWithGroup(groupName, configuration.getPresets()).keySet();

            for (String presetName : presets) {
                group.addPreset(createPresetButton(presetName));
            }

            Set<String> parameters = configurationTraverser.findObjectsWithGroup(groupName, configuration.getParameters()).keySet();
            for (String parameterName : parameters) {
                Map<String, Object> parameterConfiguration = configuration.getParameters().get(parameterName);
                Control control = parameterControlFactory.produceControl(parameterConfiguration);
                parametersControls.put(parameterName, control);
                if (!parameterControlFactory.isHidden(parameterConfiguration)) {
                    group.addParameter(buildParameter(parameterName, control));
                }
            }

            if (!group.isEmpty()) {
                groupsBox.getChildren().add(group);
            }
        }
    }

    private VBox buildParameter(String parameterName, Control control) {
        VBox vBox = new VBox();
        Label label = new Label();
        label.setText(parameterName);
        vBox.getChildren().add(label);
        vBox.getChildren().add(control);
        return vBox;
    }

    private Button createPresetButton(String presetName) {
        Button presetButton = new Button();
        presetButton.setText(presetName);
        presetButton.setOnAction(ev -> {
            runPreset(presetName);
        });
        return presetButton;
    }

    private void clearConfiguration() {
        groupsBox.getChildren().clear();
        parametersControls.clear();
    }

    private void runPreset(String presetName) {
        LOGGER.info("Executing preset: {}", presetName);
        String jobName = String.format("#%d - %s", jobsCounter.incrementAndGet(), presetName);


        JobTab jobTab = new JobTab();
        Tab tab = new Tab();
        tab.setText(jobName);
        tab.setContent(jobTab);

        JobListener jobListener = new JobListener(jobName, jobTab, notificationFacade);

        jobTab.setOnKillAction(ev -> {
            jobListener.kill();
        });

        runTabs.getTabs().add(tab);
        runTabs.getSelectionModel().select(tab);

        new Thread(() -> {
            try {
                presetRunner.run(presetName, configurationHolder.getCurrentConfiguration().get(),
                        parametersExtractor.extractParameters(parametersControls),
                        jobListener);
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
