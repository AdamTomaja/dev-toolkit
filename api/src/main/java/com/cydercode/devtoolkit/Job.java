package com.cydercode.devtoolkit;

import java.util.Map;

public interface Job {

    int getID();

    Map<String, Object> getParameters();

    String getPreset();
}
