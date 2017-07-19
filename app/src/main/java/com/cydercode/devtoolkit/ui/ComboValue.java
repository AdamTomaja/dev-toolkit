package com.cydercode.devtoolkit.ui;

import com.cydercode.devtoolkit.configuration.model.Values;
import com.google.common.base.Objects;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ComboValue {

    private final Values.Value value;

    public ComboValue(Values.Value value) {
        this.value = value;
    }

    public Values.Value getValue() {
        return value;
    }

    public static List<ComboValue> wrapValues(Values values) {
        return values.getValue()
                .stream()
                .map(val -> new ComboValue(val))
                .collect(toList());
    }

    @Override
    public String toString() {
        if (value.getDescription() != null) {
            return value.getDescription();
        } else {
            return value.getValue();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComboValue that = (ComboValue) o;
        return Objects.equal(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
