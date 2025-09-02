package com.facebook.react.defaults;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class DefaultReactActivityDelegate extends ReactActivityDelegate {
    private final boolean fabricEnabled;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DefaultReactActivityDelegate(ReactActivity reactActivity, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reactActivity, str, (i & 4) != 0 ? false : z);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultReactActivityDelegate(ReactActivity reactActivity, String str, boolean z) {
        super(reactActivity, str);
        Intrinsics.checkNotNullParameter(reactActivity, "activity");
        Intrinsics.checkNotNullParameter(str, "mainComponentName");
        this.fabricEnabled = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultReactActivityDelegate(ReactActivity reactActivity, String str, boolean z, boolean z2) {
        this(reactActivity, str, z);
        Intrinsics.checkNotNullParameter(reactActivity, "activity");
        Intrinsics.checkNotNullParameter(str, "mainComponentName");
    }

    /* access modifiers changed from: protected */
    public boolean isFabricEnabled() {
        return this.fabricEnabled;
    }
}
