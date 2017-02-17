package com.cydercode.devtoolkit.ui;

import javafx.scene.control.TextArea;
import org.junit.Test;
import org.mockito.Mockito;

import static java.lang.String.format;

public class UiCommandExecutorListenerTest extends JavaFXComponentsTest {

    @Test
    public void shouldAppendTextToLogsAreaAndCreateNotification() throws InterruptedException {
        // given
        TextArea logsArea = Mockito.mock(TextArea.class);
        NotificationFacade notificationFacade = Mockito.mock(NotificationFacade.class);
        String jobName = "JobName";

        UiCommandExecutorListener listener = new UiCommandExecutorListener(jobName, logsArea, notificationFacade);
        String output = "TestOutput";

        // when
        listener.onProcessFinished(0);
        listener.onProcessOutput(output);


        // then
        Thread.sleep(200); // wait for Platform.runLater execution
        Mockito.verify(logsArea).appendText(output);
        Mockito.verify(notificationFacade).showInformation("Dev-toolkit", format("Job %s finished with status: %d", jobName, 0));
    }
}