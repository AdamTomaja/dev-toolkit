package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.PluginsController;
import com.cydercode.devtoolkit.category.UiTest;
import com.cydercode.devtoolkit.configuration.XmlConfigurationLoader;
import com.cydercode.devtoolkit.ui.component.Group;
import com.cydercode.devtoolkit.ui.quicktoolbox.QuickToolBox;
import com.cydercode.devtoolkit.ui.quicktoolbox.QuickToolBoxController;
import com.cydercode.devtoolkit.ui.quicktoolbox.QuickToolBoxFacade;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.io.Resources.getResource;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@Category(UiTest.class)
public class MainWindowControllerTest extends JavaFXComponentsTest {

    @Spy
    Pane groupsBox = new VBox();

    @Mock
    PluginsController pluginsController;

    @Mock
    QuickToolBoxFacade quickToolBoxFacade;

    @InjectMocks
    MainWindowController mainWindowController;

    @Before
    public void before() throws IOException {
        QuickToolBox quickToolBox = mock(QuickToolBox.class);
        when(quickToolBoxFacade.get()).thenReturn(quickToolBox);
    }

    @Test
    public void shouldNotAddEmptyGroup() throws Exception {
        // given
        Configuration configuration = new XmlConfigurationLoader(new File(getResource("com/cydercode/devtoolkit/ui/MainWindowControllerTest/configuration.xml").getFile())).load();

        QuickToolBox quickToolBox = spy(new QuickToolBox(mock(Stage.class), mock(QuickToolBoxController.class)));

        when(quickToolBoxFacade.get()).thenReturn(quickToolBox);

        // when
        mainWindowController.loadConfiguration(configuration);

        // then
        Assertions.assertThat(groupsBox.getChildren().size()).isEqualTo(2);
        List<String> groups = new ArrayList<>();
        for (Node node : groupsBox.getChildren()) {
            groups.add(((Group) node).getText());
        }

        verify(quickToolBox).loadConfiguration(configuration);

        Assertions.assertThat(groups).containsOnly("Maven", "Git");
    }

    @Test
    public void shouldInitializePluginsOnInitialization() throws IOException {
        // when
        mainWindowController.initialize();

        // then
        verify(pluginsController).initialize();
    }

    @Test
    public void shouldStopPlugins() {
        // when
        mainWindowController.stop();

        // then
        verify(pluginsController).stop();
    }

}