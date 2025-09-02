package com.swmansion.rnscreens;

import com.facebook.react.module.annotations.ReactModule;
import kotlin.jvm.internal.DefaultConstructorMarker;

@ReactModule(name = "RNSModalScreen")
public final class ModalScreenViewManager extends ScreenViewManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RNSModalScreen";

    public String getName() {
        return REACT_CLASS;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
