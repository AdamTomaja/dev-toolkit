package com.cydercode.devtoolkit.ui;

import javafx.scene.control.Alert;
import org.apache.commons.lang.exception.ExceptionUtils;

import static javafx.scene.control.Alert.AlertType.ERROR;
import static org.apache.commons.lang.exception.ExceptionUtils.getStackTrace;

public class DialogHelper {

    public Alert createExceptionAlert(String header, Exception e) {
        Alert alert = new Alert(ERROR);
        alert.setTitle(header);
        alert.setHeaderText(e.getMessage());
        String stackTrace = getStackTrace(e);
        alert.setContentText(stackTrace.substring(0, Math.min(stackTrace.length(), 1500)) + "...");
        return alert;
    }
}
