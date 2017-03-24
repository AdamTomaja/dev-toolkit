package com.cydercode.devtoolkit.pluginloader;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class JarsFinderTest {

    File myTempDir;

    @Before
    public void before() throws IOException {
        myTempDir = Files.createTempDir();
        Files.touch(new File(myTempDir, "plugin-test1.jar"));
        File secondFileDir = new File(myTempDir, "subdir");
        File secondFile = new File(secondFileDir, "asd.jar");
        Files.createParentDirs(secondFile);
        Files.touch(secondFile);
        Files.touch(new File(secondFileDir, "plugin-test2.jar"));
    }

    @Test
    public void shouldFindOnlyPluginJars() throws Exception {
        // given
        JarsFinder jarsFinder = new JarsFinder();

        // when
        List<File> jars = jarsFinder.searchForJars(myTempDir);

        // then
        assertThat(jars).containsOnly(new File(myTempDir, "plugin-test1.jar"),
                new File(myTempDir, "subdir/plugin-test2.jar"));
    }

    @After
    public void after() {
        FileUtils.deleteQuietly(myTempDir);
    }
}