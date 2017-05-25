package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.configuration.XmlConfigurationLoader;
import com.cydercode.devtoolkit.executor.CommandExecutor;
import com.cydercode.devtoolkit.ui.JobListener;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class PresetRunnerTest {

    @Test
    public void shouldExecuteCompound() throws Exception {
        // given
        PresetRunner runner = new PresetRunner(new CommandExecutor(), new CommandBuilder());

        Map<String, Object> parameters = ImmutableMap.of();
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/PresetRunnerTest/configuration.xml");
        JobListener jobListener = mock(JobListener.class);

        // when
        int exitValue = runner.run("compound-preset", configuration, parameters, jobListener);

        // then
        Assertions.assertThat(exitValue).isEqualTo(0);
        verify(jobListener).onCommand("echo 1test1");
        verify(jobListener).onProcessOutput("1test1");
        verify(jobListener).onCommand("echo 2test2");
        verify(jobListener).onProcessOutput("2test2");
        verify(jobListener, times(2)).onProcessFinished(0);
        verify(jobListener, times(2)).onProcessCreated(any(Process.class));
    }

    @Test
    public void shouldExecuteSingle() throws Exception {
        // given
        PresetRunner runner = new PresetRunner(new CommandExecutor(), new CommandBuilder());

        Map<String, Object> parameters = ImmutableMap.of();
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/PresetRunnerTest/configuration.xml");
        JobListener jobListener = mock(JobListener.class);

        // when
        int exitValue = runner.run("first-preset", configuration, parameters, jobListener);

        // then
        Assertions.assertThat(exitValue).isEqualTo(0);
        verify(jobListener).onCommand("echo 1test1");
        verify(jobListener).onProcessOutput("1test1");
        verify(jobListener).onProcessFinished(0);
        verify(jobListener).onProcessCreated(any(Process.class));
    }

    @Test
    public void shouldStopAfterError() throws Exception {
        // given
        PresetRunner runner = new PresetRunner(new CommandExecutor(), new CommandBuilder());

        Map<String, Object> parameters = ImmutableMap.of();
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/PresetRunnerTest/configuration.xml");
        JobListener jobListener = mock(JobListener.class);

        // when
        int exitValue = runner.run("error-compound-preset", configuration, parameters, jobListener);

        // then
        Assertions.assertThat(exitValue).isEqualTo(1);
        verify(jobListener).onCommand("mvn error-command");
        verify(jobListener).onProcessCreated(any(Process.class));
        verify(jobListener).onProcessFinished(1);
        verify(jobListener, times(1)).onProcessCreated(any(Process.class));
        verify(jobListener, times(1)).onCommand(any());
    }

    @Test
    public void shouldIgnoreErrorPronePreset() throws Exception {
        // given
        // given
        PresetRunner runner = new PresetRunner(new CommandExecutor(), new CommandBuilder());

        Map<String, Object> parameters = ImmutableMap.of();
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/PresetRunnerTest/configuration.xml");
        JobListener jobListener = mock(JobListener.class);

        // when
        int exitValue = runner.run("ignorable-preset", configuration, parameters, jobListener);

        // then
        Assertions.assertThat(exitValue).isEqualTo(0);
        verify(jobListener).onCommand("echo 1test1");
        verify(jobListener).onCommand("echo 2test2");
        verify(jobListener).onCommand("mvn error-command");
        verify(jobListener, times(1)).onProcessFinished(1);
        verify(jobListener, times(2)).onProcessFinished(0);
        verify(jobListener).onProcessOutput("2test2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenInvalidPreset() throws Exception {
        PresetRunner runner = new PresetRunner(new CommandExecutor(), new CommandBuilder());

        Map<String, Object> parameters = ImmutableMap.of();
        Configuration configuration = loadConfiguration("com/cydercode/devtoolkit/PresetRunnerTest/configuration.xml");
        JobListener jobListener = mock(JobListener.class);

        // when
        int exitValue = runner.run("invalid-preset", configuration, parameters, jobListener);
    }

    private Configuration loadConfiguration(String file) throws Exception {
        return new XmlConfigurationLoader(new File(Resources.getResource(file).getFile())).load();
    }
}
