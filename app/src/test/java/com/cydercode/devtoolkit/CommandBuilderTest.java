package com.cydercode.devtoolkit;

import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.io.Resources.getResource;

public class CommandBuilderTest {

    @Test
    public void shouldBuildCommandLine() throws Exception {
        // given
        Configuration configuration = loadConfiguration();

        // when
        String command = new CommandBuilder()
                .buildCommand(configuration, "build", of("distribution", "A",
                        "maven-path", "/bin/mvn",
                        "profile", "dev"));


        // then
        Assertions.assertThat(command).isEqualTo("/bin/mvn/mvn clean install -d A -p dev");
    }

    @Test
    public void shouldResolveEnvironmentVariable() throws Exception {
        // given
        Configuration configuration = loadConfiguration();

        // when
        String command = new CommandBuilder()
                .buildCommand(configuration, "env-variable", of());

        // then
        Assertions.assertThat(command).isEqualTo("${maven-path}/mvn clean install -X " + System.getenv("SHELL"));
    }

    @Test
    public void environmentVariableShouldBeReplacedWithParameter() throws Exception {
        // given
        Configuration configuration = loadConfiguration();

        // when
        String command = new CommandBuilder()
                .buildCommand(configuration, "env-variable", of("SHELL", "custom-value-of-shell-var"));

        // then
        Assertions.assertThat(command).isEqualTo("${maven-path}/mvn clean install -X custom-value-of-shell-var");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentWhenNoApplication() throws Exception {
        // given
        CommandBuilder commandBuilder = new CommandBuilder();
        Configuration configuration = loadConfiguration();

        // when
        commandBuilder
                .buildCommand(configuration, "application-error", of());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNoPreset() throws Exception {
        // given
        CommandBuilder commandBuilder = new CommandBuilder();
        Configuration configuration = loadConfiguration();

        // when
        commandBuilder.buildCommand(configuration, "not-existing-preset", of());
    }

    private Configuration loadConfiguration() throws IOException {
        return new Gson()
                .fromJson(new InputStreamReader(
                        getResource("com/cydercode/devtoolkit/CommandBuilderTest/configuration.json")
                                .openStream()), Configuration.class);
    }
}