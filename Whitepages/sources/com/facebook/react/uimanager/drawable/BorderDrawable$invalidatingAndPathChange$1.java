package com.facebook.react.uimanager.drawable;

import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

public final class BorderDrawable$invalidatingAndPathChange$1 extends ObservableProperty {
    final /* synthetic */ BorderDrawable this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BorderDrawable$invalidatingAndPathChange$1(T t, BorderDrawable borderDrawable) {
        super(t);
        this.this$0 = borderDrawable;
    }

    /* access modifiers changed from: protected */
    public void afterChange(KProperty kProperty, T t, T t2) {
        Intrinsics.checkNotNullParameter(kProperty, "property");
        if (!Intrinsics.areEqual((Object) t, (Object) t2)) {
            this.this$0.needUpdatePath = true;
            this.this$0.invalidateSelf();
        }
    }
}
