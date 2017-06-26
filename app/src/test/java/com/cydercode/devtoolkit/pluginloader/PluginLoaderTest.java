package com.cydercode.devtoolkit.pluginloader;

import com.cydercode.devtoolkit.plugin.DevToolkitContext;
import com.cydercode.devtoolkit.plugin.Plugin;
import com.cydercode.devtoolkit.plugin.PluginDescriptor;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URLClassLoader;
import java.util.List;
import java.util.function.Function;

import static com.cydercode.devtoolkit.plugin.PluginConstants.DESCRIPTOR_FILENAME;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.jglue.fluentjson.JsonBuilderFactory.buildObject;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PluginLoaderTest {

    @Test
    public void shouldLoadPlugin() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // given
        String mainClass = "com.cydercode.plugin.Plugin";
        String name = "testPlugin";
        File file1 = new File("plugin1.jar");

        JarsFinder jarsFinder = mock(JarsFinder.class);
        when(jarsFinder.searchForJars(any(File.class))).thenReturn(asList(file1));
        ClassLoader classLoader = mock(ClassLoader.class);
        String descriptor = buildObject()
                .add("mainClass", mainClass)
                .add("name", name).toString();

        when(classLoader.getResourceAsStream(DESCRIPTOR_FILENAME))
                .thenReturn(new ByteArrayInputStream(descriptor.getBytes(UTF_8)));

        Plugin plugin = mock(Plugin.class);

        when(classLoader.loadClass(mainClass)).thenReturn((Class) TestPlugin.class);

        PluginLoader loader = new PluginLoader();

        Function<File, ClassLoader> classLoaderFunction = mock(Function.class);
        when(classLoaderFunction.apply(file1)).thenReturn(classLoader);

        loader.setClassLoaderFactory(classLoaderFunction);
        loader.setJarsFinder(jarsFinder);

        // when
        List<PluginDescriptor> pluginDescriptors = loader.loadPlugins();

        //then
        PluginDescriptor pluginDescriptor = pluginDescriptors.get(0);
        assertThat(pluginDescriptor.getName()).isEqualTo(name);
        assertThat(pluginDescriptor.getMainClass()).isEqualTo(mainClass);
        assertThat(pluginDescriptor.getPlugin()).isInstanceOf(TestPlugin.class);
    }

    @Test
    public void shouldHaveDefaultClassLoaderFactoryAndJarsFinder() {
        // given
        PluginLoader loader = new PluginLoader();

        // then
        assertThat(loader.getClassLoaderFactory().apply(new File(""))).
                isExactlyInstanceOf(URLClassLoader.class);

        assertThat(loader.getJarsFinder())
                .isExactlyInstanceOf(JarsFinder.class);
    }

    @Test
    public void shouldNotFailWhenPluginConstructorThrowsException() throws ClassNotFoundException {
        // given
        String mainClass = "com.cydercode.plugin.Plugin";
        String name = "testPlugin";
        File file1 = new File("plugin1.jar");

        JarsFinder jarsFinder = mock(JarsFinder.class);
        when(jarsFinder.searchForJars(any(File.class))).thenReturn(asList(file1));
        ClassLoader classLoader = mock(ClassLoader.class);
        String descriptor = buildObject()
                .add("mainClass", mainClass)
                .add("name", name).toString();

        when(classLoader.getResourceAsStream(DESCRIPTOR_FILENAME))
                .thenReturn(new ByteArrayInputStream(descriptor.getBytes(UTF_8)));

        Plugin plugin = mock(Plugin.class);

        when(classLoader.loadClass(mainClass)).thenReturn((Class) TestPluginException.class);

        PluginLoader loader = new PluginLoader();

        Function<File, ClassLoader> classLoaderFunction = mock(Function.class);
        when(classLoaderFunction.apply(file1)).thenReturn(classLoader);

        loader.setClassLoaderFactory(classLoaderFunction);
        loader.setJarsFinder(jarsFinder);

        // when
        List<PluginDescriptor> pluginDescriptors = loader.loadPlugins();
    }

    @Test
    public void shouldNotFailWhenLinkageError() throws ClassNotFoundException {
        // given
        String mainClass = "com.cydercode.plugin.Plugin";
        String name = "testPlugin";
        File file1 = new File("plugin1.jar");

        JarsFinder jarsFinder = mock(JarsFinder.class);
        when(jarsFinder.searchForJars(any(File.class))).thenReturn(asList(file1));
        ClassLoader classLoader = mock(ClassLoader.class);
        String descriptor = buildObject()
                .add("mainClass", mainClass)
                .add("name", name).toString();

        when(classLoader.getResourceAsStream(DESCRIPTOR_FILENAME))
                .thenReturn(new ByteArrayInputStream(descriptor.getBytes(UTF_8)));

        Plugin plugin = mock(Plugin.class);

        when(classLoader.loadClass(mainClass)).thenThrow(NoClassDefFoundError.class);

        PluginLoader loader = new PluginLoader();

        Function<File, ClassLoader> classLoaderFunction = mock(Function.class);
        when(classLoaderFunction.apply(file1)).thenReturn(classLoader);

        loader.setClassLoaderFactory(classLoaderFunction);
        loader.setJarsFinder(jarsFinder);

        // when
        List<PluginDescriptor> pluginDescriptors = loader.loadPlugins();
    }

    public static class TestPlugin implements Plugin {

        @Override
        public void onStart(DevToolkitContext context) {

        }

        @Override
        public void onAction() {

        }

        @Override
        public void onStop() {

        }
    }

    public static class TestPluginException implements Plugin {

        public TestPluginException() {
            throw new RuntimeException("Exception from Plugin constructor");
        }

        @Override
        public void onStart(DevToolkitContext context) {

        }

        @Override
        public void onAction() {

        }

        @Override
        public void onStop() {

        }
    }
}