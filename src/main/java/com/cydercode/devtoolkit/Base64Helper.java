package com.cydercode.devtoolkit;

import java.util.Base64;

public class Base64Helper {

    public String encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public String decode(String text) {
        return new String(Base64.getDecoder().decode(text.getBytes()));
    }
}
