package com.cydercode.devtoolkit.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.String.format;

public class CommandExecutor {

    static final Logger LOGGER = LoggerFactory.getLogger(CommandExecutor.class);

    public int execute(String commandLine, CommandExecutorListener outputConsumer) throws IOException, InterruptedException {
        LOGGER.info(format("Running command: %s", commandLine));
        outputConsumer.onCommand(commandLine);

        Process process = Runtime.getRuntime().exec(commandLine);

        outputConsumer.onProcessCreated(process);

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        String stdLine = null;
        String errLine = null;

        while (((errLine = errReader.readLine()) != null) || ((stdLine = reader.readLine()) != null)) {
            if (stdLine != null) {
                LOGGER.info("P-OUT: {}", stdLine); // Standard process output
                outputConsumer.onProcessOutput(stdLine);
            }

            if (errLine != null) {
                LOGGER.warn("ERR-OUT: {}", errLine);
                outputConsumer.onProcessErrorOutput(errLine);
            }
        }

        process.waitFor();

        int exitValue = process.exitValue();
        LOGGER.info("Process exited with code: {}", exitValue);
        outputConsumer.onProcessOutput("Process completed with status code: " + exitValue);
        outputConsumer.onProcessFinished(exitValue);
        return exitValue;
    }
}
