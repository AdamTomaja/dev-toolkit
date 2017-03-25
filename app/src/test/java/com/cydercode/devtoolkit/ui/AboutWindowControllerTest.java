package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.category.UiTest;
import com.cydercode.devtoolkit.plugin.PluginDescriptor;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@Category(UiTest.class)
public class AboutWindowControllerTest extends JavaFXComponentsTest {

    @Spy
    Pane pluginsBox = new VBox();

    @InjectMocks
    AboutWindowController aboutWindowController;

    @Test
    public void shouldShowPlugins() {
        // given
        List<PluginDescriptor> plugins = Arrays.asList(createPluginDescriptor("plug1"), createPluginDescriptor("2plug"));

        // when
        aboutWindowController.setLoadedPlugins(plugins);

        // then
        assertThat(((Label) pluginsBox.getChildren().get(0)).getText()).isEqualTo("plug1");
        assertThat(((Label) pluginsBox.getChildren().get(1)).getText()).isEqualTo("2plug");
    }

    private PluginDescriptor createPluginDescriptor(String name) {
        PluginDescriptor pluginDescriptor = new PluginDescriptor();
        pluginDescriptor.setName(name);
        return pluginDescriptor;
    }
}