package com.cydercode.devtoolkit.ui.component;

import com.cydercode.devtoolkit.category.UiTest;
import com.cydercode.devtoolkit.ui.JavaFXComponentsTest;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.cydercode.devtoolkit.ui.component.JobTab.LIMIT;
import static org.assertj.core.api.Assertions.assertThat;

@Category(UiTest.class)
public class JobTabTest extends JavaFXComponentsTest {

    @Test
    public void shouldContainLimitedTextAmount() {
        // given
        int lineLength = 100;
        String testValue = "this-is-test-value";
        JobTab jobTab = new JobTab();
        String line = RandomStringUtils.randomAlphabetic(lineLength);

        // when
        for (int i = 0; i < (LIMIT * 2) / lineLength; i++) {
            jobTab.appendText(line);
        }
        jobTab.appendText(testValue);


        // then
        assertThat(jobTab.getTextLength()).isLessThanOrEqualTo(LIMIT);
        assertThat(jobTab.getText()).contains(testValue);
    }
}