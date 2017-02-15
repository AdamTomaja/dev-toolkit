package com.cydercode.devtoolkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class CommandExecutor {

    static final Logger LOGGER = LoggerFactory.getLogger(CommandExecutor.class);

    public void execute(String commandLine, Consumer<String> outputConsumer) throws IOException, InterruptedException {
        LOGGER.info("Executing command: {}", commandLine);

        Process process = Runtime.getRuntime().exec(commandLine);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;

        while ((line = reader.readLine()) != null) {
            LOGGER.info("P-OUT: {}", line); // Process out
            outputConsumer.accept(line);
        }

        process.waitFor();

        int exitValue = process.exitValue();
        LOGGER.info("Process exited with code: {}", exitValue);
        outputConsumer.accept("Process completed with status code: " + exitValue);
    }
}
