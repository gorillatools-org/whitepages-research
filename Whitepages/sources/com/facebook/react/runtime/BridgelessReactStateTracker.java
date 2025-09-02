package com.facebook.react.runtime;

import com.facebook.common.logging.FLog;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class BridgelessReactStateTracker {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "BridgelessReact";
    private final boolean shouldTrackStates;
    private final List<String> states = Collections.synchronizedList(new ArrayList());

    public BridgelessReactStateTracker(boolean z) {
        this.shouldTrackStates = z;
    }

    /* access modifiers changed from: protected */
    public final void enterState(String str) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.ResponseFieldKey.STATE);
        FLog.w(TAG, str);
        if (this.shouldTrackStates) {
            this.states.add(str);
        }
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
