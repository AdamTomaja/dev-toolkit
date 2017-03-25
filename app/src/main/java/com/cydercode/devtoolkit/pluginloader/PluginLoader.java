package com.cydercode.devtoolkit.pluginloader;

import com.cydercode.devtoolkit.plugin.Plugin;
import com.cydercode.devtoolkit.plugin.PluginDescriptor;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.cydercode.devtoolkit.plugin.PluginConstants.DESCRIPTOR_FILENAME;

public class PluginLoader {

    static final Logger LOGGER = LoggerFactory.getLogger(PluginLoader.class);

    private JarsFinder jarsFinder = new JarsFinder();

    private Function<File, ClassLoader> classLoaderFactory = (f) -> {
        try {
            return new URLClassLoader(new URL[]{f.toURL()});
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    };

    public List<PluginDescriptor> loadPlugins() {
        List<PluginDescriptor> plugins = new ArrayList<>();
        List<File> pluginJars = jarsFinder.searchForJars(new File("."));
        ;
        for (File jarFile : pluginJars) {
            try {
                ClassLoader cl = classLoaderFactory.apply(jarFile);
                InputStream descriptorStream = cl.getResourceAsStream(DESCRIPTOR_FILENAME);
                if (descriptorStream != null) {
                    LOGGER.info("Descriptor found in {}", jarFile);
                    PluginDescriptor pluginDescriptor = new Gson().fromJson(new InputStreamReader(descriptorStream), PluginDescriptor.class);
                    LOGGER.info("Plugin descriptor loaded: {}", pluginDescriptor);
                    if (pluginDescriptor.getMainClass() == null) {
                        throw new IllegalArgumentException("mainClass cannot be null!");
                    }
                    Class pluginMainClass = cl.loadClass(pluginDescriptor.getMainClass());
                    pluginDescriptor.setPlugin((Plugin) pluginMainClass.newInstance());
                    plugins.add(pluginDescriptor);
                } else {
                    LOGGER.info("No descriptor in jar file: {}", jarFile);
                }
            } catch (Exception | LinkageError e) {
                LOGGER.error("Cannot load plugin: {}", jarFile, e);
            }
        }
        return plugins;
    }


    public JarsFinder getJarsFinder() {
        return jarsFinder;
    }

    public Function<File, ClassLoader> getClassLoaderFactory() {
        return classLoaderFactory;
    }

    public void setJarsFinder(JarsFinder jarsFinder) {
        this.jarsFinder = jarsFinder;
    }

    public void setClassLoaderFactory(Function<File, ClassLoader> classLoaderFactory) {
        this.classLoaderFactory = classLoaderFactory;
    }
}
