package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.category.UiTest;
import com.cydercode.devtoolkit.configuration.model.Parameter;
import com.cydercode.devtoolkit.configuration.model.ParameterType;
import com.cydercode.devtoolkit.configuration.model.Values;
import com.google.common.collect.ImmutableMap;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Category(UiTest.class)
public class ParameterControlFactoryTest extends JavaFXComponentsTest {

    @Test
    public void shouldProduceTextField() {
        // given
        ParameterControlFactory factory = new ParameterControlFactory();
        String defaultValue = "defaultValue";

        Parameter parameter = new Parameter();
        parameter.setDefault(defaultValue);
        parameter.setType(ParameterType.STRING);


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
//        Map<String, Object> parameter = ImmutableMap.<String, Object>builder()
//                .put("type", "string")
//                .put("default", defaultValue)
//                .put("values", Arrays.asList("a", "b", "c"))
//                .build();

        Parameter parameter = new Parameter();
        parameter.setType(ParameterType.STRING);
        parameter.setDefault(defaultValue);
        Values values = Mockito.mock(Values.class);
        when(values.getValue()).thenReturn(Arrays.asList("a", "b"));
        parameter.setValues(values);

        // when
        Control control = factory.produceControl(parameter);

        // then
        assertThat(control).isInstanceOf(ComboBox.class);
        assertThat(((ComboBox) control).getValue()).isEqualTo(defaultValue);
    }

    @Test
    public void shouldBeHidden() {
        // given
        ParameterControlFactory factory = new ParameterControlFactory();
        Map<String, Object> parameter = ImmutableMap.<String, Object>builder()
                .put("hidden", "true")
                .build();

        // when
        boolean isHidden = factory.isHidden(parameter);

        // then
        assertThat(isHidden).isTrue();
    }

    @Test
    public void shouldBeVisible() {
        // given
        ParameterControlFactory factory = new ParameterControlFactory();
        Map<String, Object> parameter = ImmutableMap.<String, Object>builder()
                .build();

        // when
        boolean isHidden = factory.isHidden(parameter);

        // then
        assertThat(isHidden).isFalse();
    }
}