package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.executor.CommandExecutor;
import com.cydercode.devtoolkit.executor.CommandExecutorListener;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandExecutorTest {

    @Test
    public void shouldExecuteCommand() throws IOException, InterruptedException {
        // given
        CommandExecutorListener executorListener = mock(CommandExecutorListener.class);
        CommandExecutor executor = new CommandExecutor();

        // when
        String text = "dev-toolkit-test";
        int result = executor.execute("echo " + text, executorListener);

        // then
        assertThat(result).isEqualTo(0);
        verify(executorListener).onCommand("echo " + text);
        verify(executorListener).onProcessOutput(text);
        verify(executorListener).onProcessOutput("Process completed with status code: 0");
        verify(executorListener).onProcessFinished(0);
        verify(executorListener).onProcessCreated(any(Process.class));
    }
}