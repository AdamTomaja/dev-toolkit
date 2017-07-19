package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.executor.OutputListener;
import org.junit.Test;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;


public class ParametersResolverTest {

    @Test
    public void shouldResolveEnvironmentVariable() throws Exception {
        // given
        ParametersResolver parametersResolver = new ParametersResolver();

        // when
        Map<String, Object> resolvedParameters = parametersResolver.resolve(emptyMap(),
                emptyMap(),
                OutputListener.NOOP);

        // then
        assertThat(resolvedParameters.get("SHELL")).isEqualTo(System.getenv("SHELL"));
    }

    @Test
    public void environmentVariableShouldBeReplacedWithParameter() throws Exception {
        // given
        ParametersResolver parametersResolver = new ParametersResolver();

        // when
        Map<String, Object> resolvedParameters = parametersResolver.resolve(of("SHELL", "custom-value"),
                emptyMap(),
                OutputListener.NOOP);

        // then
        assertThat(resolvedParameters.get("SHELL")).isEqualTo("custom-value");
    }

    @Test
    public void parametersShouldBeReplacedWithScriptGeneratedVariable() {
        // given
        ParametersResolver parametersResolver = new ParametersResolver();

        // when
        Map<String, Object> resolvedParameters = parametersResolver.resolve(of("SHELL", "custom-value"),
                of("script", "api.setParameter('SHELL', 'script value');"),
                OutputListener.NOOP);

        // then
        assertThat(resolvedParameters.get("SHELL")).isEqualTo("script value");
    }
}