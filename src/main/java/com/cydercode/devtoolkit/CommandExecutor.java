package com.cydercode.devtoolkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class CommandExecutor {

    static final Logger LOGGER = LoggerFactory.getLogger(CommandExecutor.class);

    public void execute(String commandLine, Consumer<String> outputConsumer) throws IOException {
        Process process = Runtime.getRuntime().exec(commandLine);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;

        while ((line = reader.readLine()) != null) {
            LOGGER.info(line);
            outputConsumer.accept(line);
        }
    }
}
