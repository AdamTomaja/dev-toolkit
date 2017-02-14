package com.cydercode.devtoolkit.ui;


import javafx.stage.Stage;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    public void shouldConfigureStage() throws Exception {
        // given
        App app = new App();

        // when
        new Thread(() -> {
            app.main(new String[0]);
        }).start();

        // then
        app.stop();
    }
}
