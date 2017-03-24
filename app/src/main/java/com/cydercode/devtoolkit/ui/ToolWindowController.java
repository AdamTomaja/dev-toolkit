package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.Base64Helper;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToolWindowController {

    static final Logger LOGGER = LoggerFactory.getLogger(ToolWindowController.class);

    final DialogHelper dialogHelper = new DialogHelper();
    final Base64Helper base64 = new Base64Helper();

    @FXML
    TextArea base64ContentTextArea;

    @FXML
    public void initialize() {
        LOGGER.info("asdasd");
    }

    @FXML
    public void encodeBase64() {
        try {
            base64ContentTextArea.setText(base64.encode(base64ContentTextArea.getText()));
        } catch (Exception e) {
            LOGGER.error("Error when encoding base64", e);
            dialogHelper.createExceptionAlert("Unable to encode base64", e).show();
        }
    }

    @FXML
    public void decodeBase64() {
        try {
            base64ContentTextArea.setText(base64.decode(base64ContentTextArea.getText()));
        } catch (Exception e) {
            LOGGER.error("Error when decoding base64", e);
            dialogHelper.createExceptionAlert("Unable to decode base64", e).show();
        }
    }
}
