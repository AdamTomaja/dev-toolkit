package com.cydercode.devtoolkit.ui.window;

public class WindowTitleCreator {

    public String create(String subtitle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CyderCode Dev-Toolkit");
        if (subtitle != null && !subtitle.isEmpty()) {
            stringBuilder.append(" :: ");
            stringBuilder.append(subtitle);
        }
        return stringBuilder.toString();
    }
}
