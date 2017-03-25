package com.cydercode.devtoolkit.ui.window;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class WindowTitleCreatorTest {

    @DataProvider
    public static Object[][] titles() {
        return new Object[][]{{"subtitle", "CyderCode Dev-Toolkit :: subtitle"},
                {null, "CyderCode Dev-Toolkit"},
                {"", "CyderCode Dev-Toolkit"}};
    }

    @Test
    @UseDataProvider("titles")
    public void shouldCreateTitle(String subtitle, String expectedTitle) {
        // given
        WindowTitleCreator windowTitleCreator = new WindowTitleCreator();

        // when
        String title = windowTitleCreator.create(subtitle);

        // then
        assertThat(title).isEqualTo(expectedTitle);
    }
}