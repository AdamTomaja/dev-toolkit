package com.cydercode.devtoolkit.ui.quicktoolbox;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static com.google.common.io.Resources.getResource;

public class QuickToolBoxFacade {

    private QuickToolBox quickToolBox;

    public QuickToolBox get() throws IOException {
        synchronized (this) {
            if (quickToolBox == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("quick_tool_box.fxml"));
                Parent root = (Parent) loader.load();
                QuickToolBoxController controller = loader.getController();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.getIcons().add(new Image(getResource("com/cydercode/devtoolkit/ui/icon.png").openStream()));
                stage.setTitle("QToolBox");
                stage.setAlwaysOnTop(true);
                quickToolBox = new QuickToolBox(stage, controller);
            }
        }

        return quickToolBox;
    }
}
