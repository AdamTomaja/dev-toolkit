package com.cydercode.devtoolkit.ui;


import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Map;

import static com.cydercode.devtoolkit.Configuration.*;
import static javafx.collections.FXCollections.observableArrayList;

public class ParameterControlFactory {

    public Control produceControl(Map<String, Object> parameter) {
        Object defaultValue = parameter.get(DEFAULT);
        List<String> values = (List<String>) parameter.get(VALUES);

        Control control;
        if (values != null) {
            control = new ComboBox(observableArrayList(values));
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
        return TRUE.equals(parameter.get(HIDDEN));
    }
}
