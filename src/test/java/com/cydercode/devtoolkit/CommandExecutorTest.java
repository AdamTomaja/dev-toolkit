package com.cydercode.devtoolkit;

import org.junit.Test;

import java.io.IOException;
import java.util.function.Consumer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandExecutorTest {

    @Test
    public void shouldExecuteCommand() throws IOException, InterruptedException {
        // given
        Consumer outputConsumer = mock(Consumer.class);
        CommandExecutor executor = new CommandExecutor();

        // when
        String text = "dev-toolkit-test";
        executor.execute("echo " + text, outputConsumer);

        // then
        verify(outputConsumer).accept(text);
        verify(outputConsumer).accept("Process completed with status code: 0");
    }
}