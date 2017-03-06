package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.Configuration;
import com.cydercode.devtoolkit.category.UiTest;
import com.google.common.collect.ImmutableMap;
import javafx.scene.control.Button;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static org.assertj.core.api.Assertions.assertThat;


@Category(UiTest.class)
public class PresetButtonFactoryTest extends JavaFXComponentsTest {

    @Test
    public void shouldProduceButtonWithTooltip() {
        // given
        String description = "This is preset button";
        String presetName = "PresetName";
        PresetButtonFactory factory = new PresetButtonFactory();
        Map<String, Object> presetConfiguration = of(Configuration.DESCRIPTION, description);

        // when
        Button button = factory.produce(presetName, presetConfiguration);

        // then
        assertThat(button.getText()).isEqualTo(presetName);
        assertThat(button.getTooltip().getText()).isEqualTo(description);
    }

    @Test
    public void shouldProduceButtonWithoutTooltip() {
        // given
        String presetName = "PresetName";
        PresetButtonFactory factory = new PresetButtonFactory();

        // when
        Button button = factory.produce(presetName, of());

        // then
        assertThat(button.getText()).isEqualTo(presetName);
        assertThat(button.getTooltip()).isEqualTo(null);
    }
}