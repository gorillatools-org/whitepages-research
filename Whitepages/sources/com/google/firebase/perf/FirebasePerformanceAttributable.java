package com.google.firebase.perf;

import java.util.Map;

public interface FirebasePerformanceAttributable {
    public static final int MAX_ATTRIBUTE_KEY_LENGTH = 40;
    public static final int MAX_ATTRIBUTE_VALUE_LENGTH = 100;
    public static final int MAX_TRACE_CUSTOM_ATTRIBUTES = 5;
    public static final int MAX_TRACE_NAME_LENGTH = 100;

    String getAttribute(String str);

    Map<String, String> getAttributes();

    void putAttribute(String str, String str2);

    void removeAttribute(String str);
}
