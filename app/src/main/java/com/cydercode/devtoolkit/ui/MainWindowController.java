package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.CommandBuilder;
import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.PluginsController;
import com.cydercode.devtoolkit.PresetRunner;
import com.cydercode.devtoolkit.configuration.PreferencesConfigurationHolder;
import com.cydercode.devtoolkit.configuration.model.Parameter;
import com.cydercode.devtoolkit.configuration.model.Preset;
import com.cydercode.devtoolkit.executor.CommandExecutor;
import com.cydercode.devtoolkit.plugin.DevToolkitContext;
import com.cydercode.devtoolkit.ui.component.Group;
import com.cydercode.devtoolkit.ui.component.JobTab;
import com.cydercode.devtoolkit.ui.quicktoolbox.QuickToolBox;
import com.cydercode.devtoolkit.ui.quicktoolbox.QuickToolBoxFacade;
import com.cydercode.devtoolkit.ui.window.WindowLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.cydercode.devtoolkit.Configuration.DEFAULT_GROUP;

public class MainWindowController {

    static final Logger LOGGER = LoggerFactory.getLogger(MainWindowController.class);

    PreferencesConfigurationHolder configurationHolder = new PreferencesConfigurationHolder(MainWindowController.class.getName());

    NotificationFacade notificationFacade = new NotificationFacade();
    ParameterControlFactory parameterControlFactory = new ParameterControlFactory();
    ParametersExtractor parametersExtractor = new ParametersExtractor();
    DialogHelper dialogHelper = new DialogHelper();
    PresetRunner presetRunner = new PresetRunner(new CommandExecutor(), new CommandBuilder());
    QuickToolBoxFacade quickToolBoxFacade = new QuickToolBoxFacade();
    PresetButtonFactory presetButtonFactory = new PresetButtonFactory();
    Map<String, Control> parametersControls = new HashMap<>();

    AtomicInteger jobsCounter = new AtomicInteger(0);
    PluginsController pluginsController = new PluginsController(this);

    @FXML
    TabPane runTabs;

    @FXML
    Pane groupsBox;

    @FXML
    Menu pluginsMenu;

    @FXML
    public void openToolWindow(ActionEvent actionEvent) throws IOException {
        new WindowLoader("com/cydercode/devtoolkit/ui/tool_window.fxml")
                .load("Tools")
                .show();
    }

    @FXML
    public void openQuickToolBox() throws IOException {
        QuickToolBox quickToolBox = quickToolBoxFacade.get();
        if (quickToolBox.isShowing()) {
            quickToolBox.hide();
        } else {
            quickToolBox.show();
        }
    }

    @FXML
    protected void initialize() throws IOException {
        DevToolkitContext context = new DevToolkitContext();
        pluginsController.initialize(context);
        doConfigurationReload();
    }


    public void stop() {
        pluginsController.stop();
    }

    @FXML
    protected void reloadConfiguration() throws IOException {
        doConfigurationReload();
        notificationFacade.showInformation("Configuration reloaded", "Configuration reloaded sucessfully");
    }

    protected void doConfigurationReload() throws IOException {
        Optional<Configuration> configuration = configurationHolder.loadLastConfiguration();
        if (configuration.isPresent()) {
            loadConfiguration(configuration.get());
        }

        quickToolBoxFacade.get().setOnAction((presetName) -> {
            runPreset(presetName);
        });

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
            notificationFacade.showInformation("Configuration loaded", "Configuration loaded sucessfully");
        } catch (Exception e) {
            LOGGER.error("Unable to load configuration", e);
            dialogHelper.createExceptionAlert("Error when loading configuration", e)
                    .showAndWait();
        }
    }

    @FXML
    protected void openHelpWindow() throws IOException {
        WindowLoader windowLoader = new WindowLoader("com/cydercode/devtoolkit/ui/about_window.fxml");
        Stage stage = windowLoader.load("About");
        windowLoader.<AboutWindowController>getController().setLoadedPlugins(pluginsController.getLoadedPlugins());
        stage.show();
    }

    void loadConfiguration(Configuration configuration) throws IOException {
        clearConfiguration();
        List<com.cydercode.devtoolkit.configuration.model.Group> groups = configuration.getGroups();
        groups.add(0, new com.cydercode.devtoolkit.configuration.model.Group() {{
            setName(DEFAULT_GROUP);  // default group
            setDescription("Default group");
        }});

        for (com.cydercode.devtoolkit.configuration.model.Group groupItem : groups) {
            Group group = new Group();
            group.setText(createGroupText(groupItem));

            List<Preset> presets = configuration.findPresetsWithGroup(groupItem.getName());

            presets.forEach(preset -> group.addPreset(createPresetButton(preset)));

            List<Parameter> parameters = configuration.findParametersWithGroup(groupItem.getName());

            for (Parameter parameter : parameters) {
                Control control = parameterControlFactory.produceControl(parameter);
                parametersControls.put(parameter.getName(), control);
                if (parameter.isHidden() != null && !parameter.isHidden()) {
                    group.addParameter(buildParameter(parameter, control));
                }
            }

            if (!group.isEmpty()) {
                groupsBox.getChildren().add(group);
            }
        }

        quickToolBoxFacade.get().loadConfiguration(configuration);
    }

    private String createGroupText(com.cydercode.devtoolkit.configuration.model.Group group) {
        return (Objects.equals(DEFAULT_GROUP, group.getName()) ? "Default group" : group.getName()) +
                (group.getDescription() != null ? " - " + group.getDescription() : "");
    }

    private VBox buildParameter(Parameter parameter, Control control) {
        VBox vBox = new VBox();
        Label label = new Label();
        label.setText(parameter.getName());
        vBox.getChildren().add(label);
        vBox.getChildren().add(control);
        return vBox;
    }

    private Button createPresetButton(Preset preset) {
        Button presetButton = presetButtonFactory.produce(preset);
        presetButton.setOnAction(ev -> {
            runPreset(preset.getName());
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
        tab.setContextMenu(createTabContextMenu(tab));

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

    private ContextMenu createTabContextMenu(Tab tab) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem closeAllItem = new MenuItem("Close all tabs");
        MenuItem closeAllButThisItem = new MenuItem("Close all tabs but this");

        closeAllItem.setOnAction(ev -> {
            runTabs.getTabs().clear();
        });


        closeAllButThisItem.setOnAction(ev -> {
            runTabs.getTabs().clear();
            runTabs.getTabs().add(tab);
            runTabs.getSelectionModel().select(tab);
        });

        contextMenu.getItems().add(closeAllItem);
        contextMenu.getItems().add(closeAllButThisItem);
        return contextMenu;
    }

    public Menu getPluginsMenu() {
        return pluginsMenu;
    }
}
