package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.category.UiTest;
import com.cydercode.devtoolkit.plugin.Plugin;
import com.cydercode.devtoolkit.plugin.PluginDescriptor;
import com.cydercode.devtoolkit.pluginloader.PluginLoader;
import com.cydercode.devtoolkit.ui.JavaFXComponentsTest;
import com.cydercode.devtoolkit.ui.MainWindowController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@Category(UiTest.class)
public class PluginsControllerTest extends JavaFXComponentsTest {

    @Mock
    PluginLoader pluginLoader;

    @Mock
    MainWindowController mainWindowController;

    @InjectMocks
    PluginsController pluginsController;

    @Test
    public void shouldShowPlugins() {
        // given
        Menu menu = new Menu();
        when(mainWindowController.getPluginsMenu()).thenReturn(menu);
        PluginDescriptor pluginDescriptor = new PluginDescriptor();
        String pluginName = "My Plugin";
        Plugin plugin = mock(Plugin.class);
        pluginDescriptor.setName(pluginName);
        pluginDescriptor.setPlugin(plugin);
        when(pluginLoader.loadPlugins()).thenReturn(asList(pluginDescriptor));

        // when
        pluginsController.initialize();

        // then
        MenuItem menuItem = menu.getItems().get(0);
        assertThat(menuItem.getText()).isEqualTo(pluginName);
        Mockito.verify(plugin).onStart();
        verify(plugin, times(0)).onAction();

        // and
        // when
        menuItem.getOnAction().handle(null);

        // then
        verify(plugin, times(1)).onAction();
    }

    @Test
    public void shouldNotFailWhenPluginThrowsExceptionOnStart() {
        when(mainWindowController.getPluginsMenu()).thenReturn(new Menu());
        PluginDescriptor pluginDescriptor = new PluginDescriptor();
        String pluginName = "My Plugin";
        Plugin plugin = mock(Plugin.class);
        doThrow(new RuntimeException("Mocked exception")).when(plugin).onStart();
        pluginDescriptor.setName(pluginName);
        pluginDescriptor.setPlugin(plugin);
        when(pluginLoader.loadPlugins()).thenReturn(asList(pluginDescriptor));

        // when
        pluginsController.initialize();
    }

    @Test
    public void shouldStopPlugins() {
        // given
        when(mainWindowController.getPluginsMenu()).thenReturn(new Menu());
        PluginDescriptor pluginDescriptor = new PluginDescriptor();
        String pluginName = "My Plugin";
        Plugin plugin = mock(Plugin.class);
        pluginDescriptor.setName(pluginName);
        pluginDescriptor.setPlugin(plugin);
        when(pluginLoader.loadPlugins()).thenReturn(asList(pluginDescriptor));
        pluginsController.initialize();

        // when
        pluginsController.stop();

        // then
        verify(plugin).onStop();
    }

    @Test
    public void shouldNonFailOnExceptionWhenStoppingPlugin() {
        // given
        when(mainWindowController.getPluginsMenu()).thenReturn(new Menu());
        PluginDescriptor pluginDescriptor = new PluginDescriptor();
        String pluginName = "My Plugin";
        Plugin plugin = mock(Plugin.class);
        doThrow(new RuntimeException("Mocked exception")).when(plugin).onStop();
        pluginDescriptor.setName(pluginName);
        pluginDescriptor.setPlugin(plugin);
        when(pluginLoader.loadPlugins()).thenReturn(asList(pluginDescriptor));
        pluginsController.initialize();

        // when
        pluginsController.stop();
    }

    @Test
    public void shouldReturnLoadedPlugins() {
        // given
        when(mainWindowController.getPluginsMenu()).thenReturn(new Menu());
        PluginDescriptor pluginDescriptor = new PluginDescriptor();
        String pluginName = "My Plugin";
        Plugin plugin = mock(Plugin.class);
        pluginDescriptor.setName(pluginName);
        pluginDescriptor.setPlugin(plugin);
        when(pluginLoader.loadPlugins()).thenReturn(asList(pluginDescriptor));
        pluginsController.initialize();

        // when
        List<PluginDescriptor> loadedPlugins = pluginsController.getLoadedPlugins();

        // then
        assertThat(loadedPlugins).containsOnly(pluginDescriptor);
    }
}