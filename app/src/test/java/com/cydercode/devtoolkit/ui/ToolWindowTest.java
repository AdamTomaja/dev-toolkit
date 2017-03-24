package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.category.UiTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Base64;

import static org.apache.commons.lang.RandomStringUtils.random;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

@Category(UiTest.class)
public class ToolWindowTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tool_window.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void shouldEncodeBase64() throws InterruptedException {
        // given
        String text = random(10);
        clickOn("#base64ContentTextArea");
        write(text);

        // when
        clickOn("#encodeBase64Button");

        // then
        verifyThat("#base64ContentTextArea", hasText(Base64.getEncoder().encodeToString(text.getBytes())));
    }

    @Test
    public void shouldDecodeBase64() {
        // given
        String text = random(10);
        clickOn("#base64ContentTextArea");
        write(Base64.getEncoder().encodeToString(text.getBytes()));

        // when
        clickOn("#decodeBase64Button");

        // then
        verifyThat("#base64ContentTextArea", hasText(text));
    }
}
