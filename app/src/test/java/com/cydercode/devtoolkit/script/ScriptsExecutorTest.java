package com.cydercode.devtoolkit.script;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ScriptsExecutorTest {

    @Test
    public void shouldExecuteScripts() throws Exception {
        // given
        ScriptApi scriptApi = mock(ScriptApi.class);
        ScriptsExecutor executor = new ScriptsExecutor(scriptApi);
        Map<String, String> scripts = ImmutableMap.<String, String>builder()
                .put("script-1", "api.println('script-1 output');")
                .put("script-2", "api.println('script-2 output');")
                .build();

        // when
        executor.executeScripts(scripts);

        // then
        verify(scriptApi).println("script-1 output");
        verify(scriptApi).println("script-2 output");
    }

    @Test
    public void shouldLogScriptException() {
        ScriptApi scriptApi = mock(ScriptApi.class);
        ScriptsExecutor executor = new ScriptsExecutor(scriptApi);
        Map<String, String> scripts = ImmutableMap.<String, String>builder()
                .put("invalid-script", "api.println_qwerty('script-1 output');")
                .build();

        // when
        try {
            executor.executeScripts(scripts);
            fail("Should not pass");
        } catch (RuntimeException e) {
            // then
            assertThat(e.getMessage()).isEqualTo("invalid-script script error");
            verify(scriptApi).println("Exception in invalid-script script: " +
                    "class javax.script.ScriptException - TypeError: " +
                    "api.println_qwerty is not a function in <eval> " +
                    "at line number 1");
        }
    }
}