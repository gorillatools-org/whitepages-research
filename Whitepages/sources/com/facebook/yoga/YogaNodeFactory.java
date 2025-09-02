package com.facebook.yoga;

public abstract class YogaNodeFactory {
    public static YogaNode create(YogaConfig yogaConfig) {
        return new YogaNodeJNIFinalizer(yogaConfig);
    }
}
