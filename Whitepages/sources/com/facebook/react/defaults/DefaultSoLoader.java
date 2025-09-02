package com.facebook.react.defaults;

import com.facebook.soloader.SoLoader;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class DefaultSoLoader {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized void maybeLoadSoLibrary() {
            SoLoader.loadLibrary("react_newarchdefaults");
            try {
                SoLoader.loadLibrary("appmodules");
            } catch (UnsatisfiedLinkError unused) {
            }
        }
    }

    public static final synchronized void maybeLoadSoLibrary() {
        synchronized (DefaultSoLoader.class) {
            Companion.maybeLoadSoLibrary();
        }
    }
}
