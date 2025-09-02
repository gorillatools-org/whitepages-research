package com.facebook.react.fabric;

import android.annotation.SuppressLint;
import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridClassBase;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.uimanager.StateWrapper;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
public class StateWrapperImpl extends HybridClassBase implements StateWrapper {
    private static final String TAG = "StateWrapperImpl";

    private native ReadableNativeMap getStateDataImpl();

    private native ReadableMapBuffer getStateMapBufferDataImpl();

    private native void initHybrid();

    public native void updateStateImpl(NativeMap nativeMap);

    static {
        FabricSoLoader.staticInit();
    }

    private StateWrapperImpl() {
        initHybrid();
    }

    public ReadableMapBuffer getStateDataMapBuffer() {
        if (isValid()) {
            return getStateMapBufferDataImpl();
        }
        FLog.e(TAG, "Race between StateWrapperImpl destruction and getState");
        return null;
    }

    public ReadableNativeMap getStateData() {
        if (isValid()) {
            return getStateDataImpl();
        }
        FLog.e(TAG, "Race between StateWrapperImpl destruction and getState");
        return null;
    }

    public void updateState(WritableMap writableMap) {
        if (!isValid()) {
            FLog.e(TAG, "Race between StateWrapperImpl destruction and updateState");
        } else {
            updateStateImpl((NativeMap) writableMap);
        }
    }

    public void destroyState() {
        if (isValid()) {
            resetNative();
        }
    }

    public String toString() {
        if (!isValid()) {
            return "<destroyed>";
        }
        ReadableMapBuffer stateMapBufferDataImpl = getStateMapBufferDataImpl();
        if (stateMapBufferDataImpl != null) {
            return stateMapBufferDataImpl.toString();
        }
        ReadableNativeMap stateDataImpl = getStateDataImpl();
        if (stateDataImpl == null) {
            return "<unexpected null>";
        }
        return stateDataImpl.toString();
    }
}
