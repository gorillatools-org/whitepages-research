package com.facebook.hermes.instrumentation;

import com.facebook.soloader.SoLoader;

public final class HermesSamplingProfiler {
    public static final HermesSamplingProfiler INSTANCE = new HermesSamplingProfiler();

    public static final native void disable();

    public static final native void dumpSampledTraceToFile(String str);

    public static final native void enable();

    private HermesSamplingProfiler() {
    }

    static {
        SoLoader.loadLibrary("jsijniprofiler");
    }
}
