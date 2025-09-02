package com.facebook.react.fabric;

import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.soloader.SoLoader;
import com.facebook.systrace.Systrace;

public final class FabricSoLoader {
    public static final FabricSoLoader INSTANCE = new FabricSoLoader();
    private static volatile boolean didInit;

    private FabricSoLoader() {
    }

    public static final void staticInit() {
        if (!didInit) {
            Systrace.beginSection(0, "FabricSoLoader.staticInit::load:fabricjni");
            ReactMarker.logMarker(ReactMarkerConstants.LOAD_REACT_NATIVE_SO_FILE_START);
            SoLoader.loadLibrary("fabricjni");
            ReactMarker.logMarker(ReactMarkerConstants.LOAD_REACT_NATIVE_SO_FILE_END);
            Systrace.endSection(0);
            didInit = true;
        }
    }
}
