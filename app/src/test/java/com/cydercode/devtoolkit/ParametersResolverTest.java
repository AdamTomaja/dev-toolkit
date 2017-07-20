package com.cydercode.devtoolkit;

import com.cydercode.devtoolkit.executor.OutputListener;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static java.io.File.separator;
import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ParametersResolverTest {

    @Test
    public void shouldResolveEnvironmentVariable() throws Exception {
        // given
        ParametersResolver parametersResolver = new ParametersResolver();

        // when
        Map<String, Object> resolvedParameters = parametersResolver.resolve(emptyMap(),
                createConfigurationMock(),
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
                createConfigurationMock(),
                OutputListener.NOOP);

        // then
        assertThat(resolvedParameters.get("SHELL")).isEqualTo("custom-value");
    }

    @Test
    public void parametersShouldBeReplacedWithScriptGeneratedVariable() {
        // given
        ParametersResolver parametersResolver = new ParametersResolver();
        Configuration configuration = createConfigurationMock();
        when(configuration.getScripts()).thenReturn(of("script", "api.setParameter('SHELL', 'script value');"));

        // when
        Map<String, Object> resolvedParameters = parametersResolver.resolve(of("SHELL", "custom-value"),
                configuration,
                OutputListener.NOOP);

        // then
        assertThat(resolvedParameters.get("SHELL")).isEqualTo("script value");
    }

    @Test
    public void shouldAddConfigurationSourceParameter() {
        // given
        ParametersResolver resolver = new ParametersResolver();
        Configuration configuration = createConfigurationMock();
        String source = "mocked configuration source";
        when(configuration.getSource()).thenReturn(source);

        // when
        Map<String, Object> resolvedParameters = resolver.resolve(of(), configuration, OutputListener.NOOP);

        // then
        assertThat(resolvedParameters.get(ParametersResolver.CONFIGURATION_SOURCE))
                .isEqualTo(source);
        assertThat(resolvedParameters.get(ParametersResolver.CONFIGURATION_DIRECTORY)).isNull();
    }

    @Test
    public void shouldExtractConfigurationDirectoryIfPossible() {
        // given
        ParametersResolver resolver = new ParametersResolver();
        Configuration configuration = createConfigurationMock();
        String source = "mocked configuration directory" + separator + "a" + separator + "b";
        when(configuration.getSource()).thenReturn(source);

        // when
        Map<String, Object> resolvedParameters = resolver.resolve(of(), configuration, OutputListener.NOOP);

        // then
        assertThat(resolvedParameters.get(ParametersResolver.CONFIGURATION_SOURCE))
                .isEqualTo(source);
        assertThat(resolvedParameters.get(ParametersResolver.CONFIGURATION_DIRECTORY))
                .isEqualTo("mocked configuration directory" + separator + "a");
    }

    private Configuration createConfigurationMock() {
        Configuration mock = mock(Configuration.class);
        when(mock.getScripts()).thenReturn(Collections.emptyMap());
        return mock;
    }
}