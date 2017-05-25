package com.cydercode.devtoolkit.ui;


import com.cydercode.devtoolkit.configuration.model.Parameter;
import com.cydercode.devtoolkit.configuration.model.Values;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;

import java.util.Map;

import static com.cydercode.devtoolkit.Configuration.HIDDEN;
import static com.cydercode.devtoolkit.Configuration.TRUE;
import static javafx.collections.FXCollections.observableArrayList;

public class ParameterControlFactory {

    public Control produceControl(Parameter parameter) {
        Object defaultValue = parameter.getDefault();
        Values values = parameter.getValues();

        Control control;
        if (values != null) {
            control = new ComboBox(observableArrayList(values.getValue()));
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
