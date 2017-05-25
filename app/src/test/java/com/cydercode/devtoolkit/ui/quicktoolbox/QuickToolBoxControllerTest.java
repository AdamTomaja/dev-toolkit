package com.cydercode.devtoolkit.ui.quicktoolbox;

import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.category.UiTest;
import com.cydercode.devtoolkit.configuration.XmlConfigurationLoader;
import com.cydercode.devtoolkit.ui.JavaFXComponentsTest;
import com.google.common.io.Resources;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
@Category(UiTest.class)
public class QuickToolBoxControllerTest extends JavaFXComponentsTest {

    @Mock
    Pane presetsBox;

    @InjectMocks
    QuickToolBoxController controller;

    @Test
    public void shouldAddOnlyWithQToolBoxAttribute() throws Exception {
        // given
        ObservableList<Node> nodeList = FXCollections.observableArrayList();

        Configuration configuration = new XmlConfigurationLoader(new File(Resources.getResource("com/cydercode/devtoolkit/ui/quicktoolbox/QuickToolBoxControllerTest/configuration.xml").getFile())).load();


        doReturn(nodeList).when(presetsBox).getChildren();

        // when
        controller.loadConfiguration(configuration);

        // then
        assertThat(nodeList.stream().map(b -> ((Button) b).getText()).collect(Collectors.toSet()))
                .containsOnly("build", "clean");
    }

    @Test
    public void shouldAddTooltip() throws Exception {
        // given
        ObservableList<Node> nodeList = FXCollections.observableArrayList();

        Configuration configuration = new XmlConfigurationLoader(new File(Resources.getResource("com/cydercode/devtoolkit/ui/quicktoolbox/QuickToolBoxControllerTest/configuration.xml").getFile())).load();

        doReturn(nodeList).when(presetsBox).getChildren();

        // when
        controller.loadConfiguration(configuration);

        // then
        Button cleanButton = (Button) nodeList.stream().filter(b -> ((Button) b).getText().equals("clean"))
                .findFirst().get();

        assertThat(cleanButton.getTooltip().getText()).isEqualTo("Clean working directory");
    }
}