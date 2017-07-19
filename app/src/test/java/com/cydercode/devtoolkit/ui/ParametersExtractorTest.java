package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.category.UiTest;
import com.cydercode.devtoolkit.configuration.model.Values;
import com.google.common.collect.ImmutableMap;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Map;

import static org.assertj.core.api.Assertions.entry;

@Category(UiTest.class)
public class ParametersExtractorTest extends JavaFXComponentsTest {

    @Test
    public void shouldExtractParameters() {
        // given
        ParametersExtractor extractor = new ParametersExtractor();

        TextField textField = new TextField();
        textField.setText("textFieldValue");

        ComboBox comboBox = new ComboBox();
        comboBox.setValue(new ComboValue(new Values.Value().withValue("comboBoxValue")));

        Map<String, Control> controls = ImmutableMap.of("text-field", textField,
                "combo-box", comboBox);

        // when
        Map<String, Object> parameters = extractor.extractParameters(controls);

        // then
        Assertions.assertThat(parameters).containsOnly(entry("text-field", "textFieldValue"),
                entry("combo-box", "comboBoxValue"));

    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenUnknownControlType() {
        // given
        Map<String, Control> controls = ImmutableMap.of("c", new Label());
        ParametersExtractor extractor = new ParametersExtractor();

        // when
        extractor.extractParameters(controls);
    }
}