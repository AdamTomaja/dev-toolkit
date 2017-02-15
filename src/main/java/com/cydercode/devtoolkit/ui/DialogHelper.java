package com.cydercode.devtoolkit.ui;

import javafx.scene.control.Alert;
import org.apache.commons.lang.exception.ExceptionUtils;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class DialogHelper {

    public Alert createExceptionAlert(String header, Exception e) {
        Alert alert = new Alert(ERROR);
        alert.setTitle(header);
        alert.setHeaderText(e.getMessage());
        alert.setContentText(ExceptionUtils.getStackTrace(e).substring(0, 1500) + "...");
        return alert;
    }
}
