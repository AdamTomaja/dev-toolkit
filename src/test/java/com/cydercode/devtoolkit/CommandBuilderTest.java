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
    public void shouldBuildCommandLine() throws IOException {
        // given
        Configuration configuration = new Gson()
                .fromJson(new InputStreamReader(
                        getResource("com/cydercode/devtoolkit/CommandBuilderTest/configuration.json")
                                .openStream()), Configuration.class);

        // when
        String command = new CommandBuilder()
                .buildCommand(configuration, "build", of("distribution", "A",
                        "maven-path", "/bin/mvn",
                        "profile", "dev"));


        // then
        Assertions.assertThat(command).isEqualTo("/bin/mvn/mvn clean install -d A -p dev");
    }
}