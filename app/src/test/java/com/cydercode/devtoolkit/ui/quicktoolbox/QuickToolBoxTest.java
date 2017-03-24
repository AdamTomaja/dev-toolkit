package com.cydercode.devtoolkit.ui.quicktoolbox;

import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.category.UiTest;
import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.io.InputStreamReader;

import static com.google.common.io.Resources.getResource;
import static javafx.application.Platform.runLater;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.ParentMatchers.hasChildren;

@Category(UiTest.class)
public class QuickToolBoxTest extends ApplicationTest {

    QuickToolBoxController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("quick_tool_box.fxml"));
        Parent root = (Parent) loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void shouldLoadConfiguration() throws IOException, InterruptedException {
        // given
        Configuration configuration = new Gson().fromJson(new InputStreamReader(
                        getResource("com/cydercode/devtoolkit/ui/quicktoolbox/QuickToolBoxTest/configuration.json")
                                .openStream()),
                Configuration.class);
        Object lock = new Object();

        // when
        runLater(() -> {
            controller.loadConfiguration(configuration);
            synchronized (lock) {
                lock.notify();
            }
        });

        synchronized (lock) {
            lock.wait();
        }

        // then
        verifyThat("#presetsBox", hasChildren(2));
    }
}