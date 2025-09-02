package com.facebook.react.common.mapbuffer;

import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.soloader.SoLoader;
import com.facebook.systrace.Systrace;

public final class MapBufferSoLoader {
    public static final MapBufferSoLoader INSTANCE = new MapBufferSoLoader();
    private static volatile boolean didInit;

    private MapBufferSoLoader() {
    }

    public static final void staticInit() {
        if (!didInit) {
            didInit = true;
            Systrace.beginSection(0, "ReadableMapBufferSoLoader.staticInit::load:mapbufferjni");
            ReactMarker.logMarker(ReactMarkerConstants.LOAD_REACT_NATIVE_MAPBUFFER_SO_FILE_START);
            SoLoader.loadLibrary("mapbufferjni");
            ReactMarker.logMarker(ReactMarkerConstants.LOAD_REACT_NATIVE_MAPBUFFER_SO_FILE_END);
            Systrace.endSection(0);
        }
    }
}
