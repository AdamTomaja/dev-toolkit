package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.executor.CommandExecutor;
import com.cydercode.devtoolkit.executor.CommandExecutorListener;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

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
        executor.execute("echo " + text, executorListener);

        // then
        verify(executorListener).onProcessOutput("Running command: echo " + text);
        verify(executorListener).onProcessOutput(text);
        verify(executorListener).onProcessOutput("Process completed with status code: 0");
        verify(executorListener).onProcessFinished(0);
        verify(executorListener).onProcessCreated(any(Process.class));
    }
}