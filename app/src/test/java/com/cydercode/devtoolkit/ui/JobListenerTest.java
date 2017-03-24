package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.category.UiTest;
import com.cydercode.devtoolkit.ui.component.JobTab;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static java.lang.String.format;
import static java.lang.Thread.sleep;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Category(UiTest.class)
public class JobListenerTest extends JavaFXComponentsTest {

    @Test
    public void shouldAppendTextToLogsAreaAndCreateNotification() throws InterruptedException {
        // given
        String jobName = "JobName";
        String output = "TestOutput";

        JobTab jobTab = mock(JobTab.class);
        NotificationFacade notificationFacade = mock(NotificationFacade.class);
        JobListener listener = new JobListener(jobName, jobTab, notificationFacade);

        // when
        listener.onProcessFinished(0);
        listener.onProcessOutput(output);


        // then
        sleep(1000); // wait for Platform.runLater execution
        verify(jobTab).appendText(output);
        verify(notificationFacade).showInformation("Dev-toolkit", format("Job %s finished with status: %d", jobName, 0));
    }

    @Test
    public void shouldDestroyForcibly() {
        // given
        JobTab jobTab = mock(JobTab.class);
        NotificationFacade notificationFacade = mock(NotificationFacade.class);
        JobListener listener = new JobListener(RandomStringUtils.randomAlphabetic(10), jobTab, notificationFacade);
        Process process = mock(Process.class);

        // when
        listener.onProcessCreated(process);
        listener.kill();

        // then
        verify(process).destroyForcibly();
    }

    @Test
    public void shouldEnableKillButton() {
        // given
        JobTab jobTab = mock(JobTab.class);
        NotificationFacade notificationFacade = mock(NotificationFacade.class);
        JobListener listener = new JobListener(RandomStringUtils.randomAlphabetic(10), jobTab, notificationFacade);
        Process process = mock(Process.class);

        // when
        listener.onProcessCreated(process);

        // then
        verify(jobTab).setKillDisabled(false);
    }

    @Test
    public void shouldDisableKillButton() {
        // given
        JobTab jobTab = mock(JobTab.class);
        NotificationFacade notificationFacade = mock(NotificationFacade.class);
        JobListener listener = new JobListener(RandomStringUtils.randomAlphabetic(10), jobTab, notificationFacade);
        Process process = mock(Process.class);

        // when
        listener.onProcessCreated(process);
        listener.onProcessFinished(0);

        // then
        verify(jobTab).setKillDisabled(true);
    }

    @Test
    public void shouldShowSuccess() {
        // given
        JobTab jobTab = mock(JobTab.class);
        NotificationFacade notificationFacade = mock(NotificationFacade.class);
        JobListener listener = new JobListener(RandomStringUtils.randomAlphabetic(10), jobTab, notificationFacade);

        // when
        listener.onProcessFinished(0);

        // then
        verify(jobTab).showSuccess();
    }

    @Test
    public void shouldShowErrorWhenExitValueDifferentThanZero() {
        // given
        JobTab jobTab = mock(JobTab.class);
        NotificationFacade notificationFacade = mock(NotificationFacade.class);
        JobListener listener = new JobListener(RandomStringUtils.randomAlphabetic(10), jobTab, notificationFacade);

        // when
        listener.onProcessFinished(123);

        // then
        verify(jobTab).showError();
    }

    @Test
    public void shouldShowCommand() throws InterruptedException {
        // given
        String command = "some-command";
        JobTab jobTab = mock(JobTab.class);
        NotificationFacade notificationFacade = mock(NotificationFacade.class);
        JobListener jobListener = new JobListener(RandomStringUtils.randomAlphabetic(10), jobTab, notificationFacade);

        // when
        jobListener.onCommand(command);

        // then
        sleep(1000); // wait for Platform.runLater execution
        verify(jobTab).appendText("Running command: " + command);
    }

    @Test
    public void shouldBeepWhenProcessFinished() {
        // given
        NotificationFacade notificationFacade = mock(NotificationFacade.class);
        JobListener jobListener = new JobListener(RandomStringUtils.randomAlphabetic(10), mock(JobTab.class), notificationFacade);

        // when
        jobListener.onProcessFinished(0);

        // then
        verify(notificationFacade).beep();
    }

    @Test
    public void shouldSetCommandLine() throws InterruptedException {
        // given
        JobTab jobTab = mock(JobTab.class);
        JobListener jobListener = new JobListener(RandomStringUtils.randomAlphabetic(10), jobTab, mock(NotificationFacade.class));
        String commandLine = "command-line";

        // when
        jobListener.onCommand(commandLine);

        // then
        sleep(1000); // wait for Platform.runLater execution
        verify(jobTab).setCommandLineText(commandLine);
    }
}