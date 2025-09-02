package com.facebook.react.reactperflogger;

import com.facebook.jni.HybridData;

public abstract class NativeModulePerfLogger {
    private final HybridData mHybridData = initHybrid();

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    /* access modifiers changed from: protected */
    public abstract HybridData initHybrid();

    public abstract void moduleCreateCacheHit(String str, int i);

    public abstract void moduleCreateConstructEnd(String str, int i);

    public abstract void moduleCreateConstructStart(String str, int i);

    public abstract void moduleCreateEnd(String str, int i);

    public abstract void moduleCreateFail(String str, int i);

    public abstract void moduleCreateSetUpEnd(String str, int i);

    public abstract void moduleCreateSetUpStart(String str, int i);

    public abstract void moduleCreateStart(String str, int i);

    public abstract void moduleDataCreateEnd(String str, int i);

    public abstract void moduleDataCreateStart(String str, int i);

    protected NativeModulePerfLogger() {
        maybeLoadOtherSoLibraries();
    }

    /* access modifiers changed from: protected */
    public final synchronized void maybeLoadOtherSoLibraries() {
    }
}
