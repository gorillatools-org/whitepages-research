package com.swmansion.rnscreens;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class NativeProxy {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void addScreenToMap(int i, Screen screen) {
            Intrinsics.checkNotNullParameter(screen, "view");
        }

        public final void clearMapOnInvalidate() {
        }

        public final void removeScreenFromMap(int i) {
        }

        private Companion() {
        }
    }
}
