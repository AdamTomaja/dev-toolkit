package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.category.UiTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;

@Category(UiTest.class)
public class WindowsTest extends JavaFXComponentsTest {

    @Test
    public void shouldLoadToolWindow() throws IOException {
        // given
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tool_window.fxml"));

        // when
        Parent root = (Parent) loader.load();
    }

    @Test
    public void shouldLoadQuickToolBoxWindow() throws IOException {
        // given
        FXMLLoader loader = new FXMLLoader(getClass().getResource("quicktoolbox/quick_tool_box.fxml"));

        // when
        Parent root = (Parent) loader.load();
    }
}
