package com.cydercode.devtoolkit.ui;


import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class ParametersExtractor {

    public Map<String, Object> extractParameters(Map<String, Control> controls) {
        Map<String, Object> parameters = new HashMap<>();
        controls.forEach((p, tf) -> {
            String value;

            if (tf instanceof TextField) {
                value = ((TextField) tf).getText();
            } else if (tf instanceof ComboBox) {
                ComboBox comboBox = (ComboBox) tf;
                ComboValue comboValue = (ComboValue) comboBox.getValue();
                value = comboValue.getValue().getValue();
            } else {
                throw new RuntimeException("Unknown control type: " + tf.getClass());
            }
            parameters.put(p, value);
        });
        return parameters;
    }
}
