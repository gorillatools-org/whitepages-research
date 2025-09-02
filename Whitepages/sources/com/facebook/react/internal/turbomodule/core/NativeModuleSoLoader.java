package com.facebook.react.internal.turbomodule.core;

import com.facebook.soloader.SoLoader;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class NativeModuleSoLoader {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static boolean isSoLibraryLoaded;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized void maybeLoadSoLibrary() {
            if (!NativeModuleSoLoader.isSoLibraryLoaded) {
                SoLoader.loadLibrary("turbomodulejsijni");
                NativeModuleSoLoader.isSoLibraryLoaded = true;
            }
        }
    }

    public static final synchronized void maybeLoadSoLibrary() {
        synchronized (NativeModuleSoLoader.class) {
            Companion.maybeLoadSoLibrary();
        }
    }
}
