package com.google.firebase.crashlytics.ktx;

import androidx.annotation.Keep;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Keep
public final class FirebaseCrashlyticsKtxRegistrar implements ComponentRegistrar {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public List<Component<?>> getComponents() {
        return CollectionsKt.emptyList();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
