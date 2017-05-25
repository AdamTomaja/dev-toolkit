package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.category.UiTest;
import com.cydercode.devtoolkit.configuration.model.Preset;
import javafx.scene.control.Button;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.assertj.core.api.Assertions.assertThat;


@Category(UiTest.class)
public class PresetButtonFactoryTest extends JavaFXComponentsTest {

    @Test
    public void shouldProduceButtonWithTooltip() {
        // given
        String description = "This is preset button";
        String presetName = "PresetName";
        PresetButtonFactory factory = new PresetButtonFactory();
        Preset preset = new Preset();
        preset.setName(presetName);
        preset.setDescription(description);
        // when
        Button button = factory.produce(preset);

        // then
        assertThat(button.getText()).isEqualTo(presetName);
        assertThat(button.getTooltip().getText()).isEqualTo(description);
    }

    @Test
    public void shouldProduceButtonWithoutTooltip() {
        // given
        String presetName = "PresetName";
        Preset preset = new Preset();
        preset.setName(presetName);
        PresetButtonFactory factory = new PresetButtonFactory();

        // when
        Button button = factory.produce(preset);

        // then
        assertThat(button.getText()).isEqualTo(presetName);
        assertThat(button.getTooltip()).isEqualTo(null);
    }
}