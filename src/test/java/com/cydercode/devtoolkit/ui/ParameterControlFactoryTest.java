package com.cydercode.devtoolkit.ui;

import com.google.common.collect.ImmutableMap;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ParameterControlFactoryTest extends JavaFXComponentsTest {

    @Test
    public void shouldProduceTextField() {
        // given
        ParameterControlFactory factory = new ParameterControlFactory();
        String defaultValue = "defaultValue";

        Map<String, Object> parameter = ImmutableMap.of(
                "type", "string",
                "default", defaultValue
        );

        // when
        Control control = factory.produceControl(parameter);

        // then
        assertThat(control).isInstanceOf(TextField.class);
        assertThat(((TextField) control).getText()).isEqualTo(defaultValue);
    }

    @Test
    public void shouldProduceComboBox() {
        // given
        ParameterControlFactory factory = new ParameterControlFactory();
        String defaultValue = "defaultValue";
        Map<String, Object> parameter = ImmutableMap.<String, Object>builder()
                .put("type", "string")
                .put("default", defaultValue)
                .put("values", Arrays.asList("a", "b", "c"))
                .build();

        // when
        Control control = factory.produceControl(parameter);

        // then
        assertThat(control).isInstanceOf(ComboBox.class);
        assertThat(((ComboBox) control).getValue()).isEqualTo(defaultValue);
    }
}