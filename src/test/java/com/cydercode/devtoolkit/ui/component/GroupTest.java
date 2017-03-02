package com.cydercode.devtoolkit.ui.component;

import com.cydercode.devtoolkit.category.UiTest;
import com.cydercode.devtoolkit.ui.JavaFXComponentsTest;
import javafx.scene.control.Label;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.assertj.core.api.Assertions.assertThat;

@Category(UiTest.class)
public class GroupTest extends JavaFXComponentsTest {

    @Test
    public void shouldBeEmpty() {
        // given
        Group group = new Group();

        // when
        boolean isEmpty = group.isEmpty();

        // then
        assertThat(isEmpty).isTrue();
    }

    @Test
    public void shouldBeNotEmptyAfterParameterAdded() {
        // given
        Group group = new Group();
        group.addParameter(new Label());

        // when
        boolean isEmpty = group.isEmpty();

        // then
        assertThat(isEmpty).isFalse();
    }

    @Test
    public void shouldBeNotEmptyAfterPresetAdded() {
        // given
        Group group = new Group();
        group.addPreset(new Label());

        // when
        boolean isEmpty = group.isEmpty();

        // then
        assertThat(isEmpty).isFalse();
    }
}