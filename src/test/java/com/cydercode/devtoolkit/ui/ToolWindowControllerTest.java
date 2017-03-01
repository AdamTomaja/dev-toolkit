package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.Base64Helper;
import javafx.scene.control.TextArea;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class ToolWindowControllerTest extends JavaFXComponentsTest {

    @Spy
    TextArea base64ContentTextArea = new TextArea();

    @InjectMocks
    ToolWindowController toolWindowController;

    @Test
    public void shouldDecode() {
        // given
        Base64Helper base64Helper = new Base64Helper();
        String plainText = "adam";
        base64ContentTextArea.setText(base64Helper.encode(plainText));

        // when
        toolWindowController.decodeBase64();

        // then
        assertThat(base64ContentTextArea.getText()).isEqualTo(plainText);
    }

    @Test
    public void shouldEncode() {
        // given
        Base64Helper base64Helper = new Base64Helper();
        String plainText = "adam";
        base64ContentTextArea.setText(plainText);

        // when
        toolWindowController.decodeBase64();

        // then
        assertThat(base64ContentTextArea.getText()).isEqualTo(base64Helper.decode(plainText));
    }
}