package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.category.UiTest;
import com.cydercode.devtoolkit.ui.component.Group;
import com.google.gson.Gson;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.io.Resources.getResource;

@RunWith(MockitoJUnitRunner.class)
@Category(UiTest.class)
public class MainWindowControllerTest extends JavaFXComponentsTest {

    @Spy
    Pane groupsBox = new VBox();

    @InjectMocks
    MainWindowController mainWindowController;


    @Test
    public void shouldNotAddEmptyGroup() throws IOException {
        // given
        Configuration configuration = new Gson().fromJson(new InputStreamReader(
                getResource("com/cydercode/devtoolkit/ui/MainWindowControllerTest/configuration.json")
                        .openStream()), Configuration.class);

        // when
        mainWindowController.loadConfiguration(configuration);

        // then
        Assertions.assertThat(groupsBox.getChildren().size()).isEqualTo(2);
        List<String> groups = new ArrayList<>();
        for (Node node : groupsBox.getChildren()) {
            groups.add(((Group) node).getText());
        }

        Assertions.assertThat(groups).containsOnly("Maven", "Git");
    }

}