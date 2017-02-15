package com.cydercode.devtoolkit.ui;


import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Map;

public class ParameterControlFactory {

    public Control produceControl(Map<String, Object> parameter) {
        Object defaultValue = parameter.get("default");
        List<String> values = (List<String>) parameter.get("values");

        Control control;
        if (values != null) {
            control = new ComboBox(FXCollections.observableArrayList(values));
            ((ComboBox) control).setValue(defaultValue);
        } else {
            control = new TextField();
            if (defaultValue != null) {
                ((TextField) control).setText(defaultValue.toString());
            }
        }

        return control;
    }

    public boolean isHidden(Map<String, Object> parameter) {
        return "true".equals(parameter.get("hidden"));
    }
}
