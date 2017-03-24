package com.cydercode.devtoolkit.pluginloader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JarsFinder {

    public List<File> searchForJars(File startDirectory) {
        List<File> files = new ArrayList<>();
        searchForJars(startDirectory, files);
        return files;
    }

    public void searchForJars(File currentDirectory, List<File> pluginJars) {
        String[] fileNames = currentDirectory.list();
        for (String fileName : fileNames) {
            File file = new File(currentDirectory, fileName);
            if (file.isDirectory()) {
                searchForJars(file, pluginJars);
            } else if (fileName.startsWith("plugin") && fileName.endsWith(".jar")) {
                pluginJars.add(file);
            }
        }
    }
}