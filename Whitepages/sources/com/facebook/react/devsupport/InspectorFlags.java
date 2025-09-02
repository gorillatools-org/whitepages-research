package com.facebook.react.devsupport;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public final class InspectorFlags {
    public static final InspectorFlags INSTANCE = new InspectorFlags();

    @DoNotStrip
    public static final native boolean getFuseboxEnabled();

    @DoNotStrip
    public static final native boolean getIsProfilingBuild();

    private InspectorFlags() {
    }

    static {
        DevSupportSoLoader.staticInit();
    }
}
