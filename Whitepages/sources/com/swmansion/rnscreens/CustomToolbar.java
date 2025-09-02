package com.swmansion.rnscreens;

import android.content.Context;
import android.view.WindowInsets;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.swmansion.rnscreens.utils.InsetsKtKt;
import kotlin.jvm.internal.Intrinsics;

public class CustomToolbar extends Toolbar {
    private final ScreenStackHeaderConfig config;
    private boolean isForceShadowStateUpdateOnLayoutRequested;
    /* access modifiers changed from: private */
    public boolean isLayoutEnqueued;
    private Insets lastInsets;
    private final ChoreographerCompat.FrameCallback layoutCallback = new CustomToolbar$layoutCallback$1(this);

    public final ScreenStackHeaderConfig getConfig() {
        return this.config;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CustomToolbar(Context context, ScreenStackHeaderConfig screenStackHeaderConfig) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(screenStackHeaderConfig, "config");
        this.config = screenStackHeaderConfig;
        Insets insets = Insets.NONE;
        Intrinsics.checkNotNullExpressionValue(insets, "NONE");
        this.lastInsets = insets;
    }

    private final boolean getShouldAvoidDisplayCutout() {
        return this.config.isTopInsetEnabled();
    }

    private final boolean getShouldApplyTopInset() {
        return this.config.isTopInsetEnabled();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001a, code lost:
        r0 = (r0 = r0.getWindow()).getAttributes();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void requestLayout() {
        /*
            r3 = this;
            super.requestLayout()
            android.content.Context r0 = r3.getContext()
            java.lang.String r1 = "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            com.facebook.react.uimanager.ThemedReactContext r0 = (com.facebook.react.uimanager.ThemedReactContext) r0
            android.app.Activity r0 = r0.getCurrentActivity()
            if (r0 == 0) goto L_0x0027
            android.view.Window r0 = r0.getWindow()
            if (r0 == 0) goto L_0x0027
            android.view.WindowManager$LayoutParams r0 = r0.getAttributes()
            if (r0 == 0) goto L_0x0027
            int r0 = r0.softInputMode
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L_0x0028
        L_0x0027:
            r0 = 0
        L_0x0028:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 29
            if (r1 > r2) goto L_0x0051
            if (r0 != 0) goto L_0x0031
            goto L_0x0051
        L_0x0031:
            int r0 = r0.intValue()
            r1 = 32
            if (r0 != r1) goto L_0x0051
            boolean r0 = r3.isLayoutEnqueued
            if (r0 != 0) goto L_0x0051
            com.facebook.react.modules.core.ChoreographerCompat$FrameCallback r0 = r3.layoutCallback
            if (r0 == 0) goto L_0x0051
            r0 = 1
            r3.isLayoutEnqueued = r0
            com.facebook.react.modules.core.ReactChoreographer$Companion r0 = com.facebook.react.modules.core.ReactChoreographer.Companion
            com.facebook.react.modules.core.ReactChoreographer r0 = r0.getInstance()
            com.facebook.react.modules.core.ReactChoreographer$CallbackType r1 = com.facebook.react.modules.core.ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE
            com.facebook.react.modules.core.ChoreographerCompat$FrameCallback r2 = r3.layoutCallback
            r0.postFrameCallback(r1, r2)
        L_0x0051:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.CustomToolbar.requestLayout():void");
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        WindowInsets rootWindowInsets = getRootWindowInsets();
        WindowInsets windowInsets2 = rootWindowInsets;
        Insets resolveInsetsOrZero$default = InsetsKtKt.resolveInsetsOrZero$default(this, WindowInsetsCompat.Type.displayCutout(), windowInsets2, false, 4, (Object) null);
        Insets resolveInsetsOrZero$default2 = InsetsKtKt.resolveInsetsOrZero$default(this, WindowInsetsCompat.Type.systemBars(), windowInsets2, false, 4, (Object) null);
        Insets resolveInsetsOrZero = InsetsKtKt.resolveInsetsOrZero(this, WindowInsetsCompat.Type.systemBars(), rootWindowInsets, true);
        Insets of = Insets.of(resolveInsetsOrZero$default.left + resolveInsetsOrZero$default2.left, 0, resolveInsetsOrZero$default.right + resolveInsetsOrZero$default2.right, 0);
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        Insets of2 = Insets.of(0, Math.max(resolveInsetsOrZero$default.top, getShouldApplyTopInset() ? resolveInsetsOrZero.top : 0), 0, Math.max(resolveInsetsOrZero$default.bottom, 0));
        Intrinsics.checkNotNullExpressionValue(of2, "of(...)");
        Insets add = Insets.add(of, of2);
        Intrinsics.checkNotNullExpressionValue(add, "add(...)");
        if (!Intrinsics.areEqual((Object) this.lastInsets, (Object) add)) {
            this.lastInsets = add;
            applyExactPadding(add.left, add.top, add.right, add.bottom);
        }
        return onApplyWindowInsets;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.config.onNativeToolbarLayout(this, z || this.isForceShadowStateUpdateOnLayoutRequested);
        this.isForceShadowStateUpdateOnLayoutRequested = false;
    }

    public final void updateContentInsets() {
        setContentInsetStartWithNavigation(this.config.getPreferredContentInsetStartWithNavigation());
        setContentInsetsRelative(this.config.getPreferredContentInsetStart(), this.config.getPreferredContentInsetEnd());
    }

    private final void applyExactPadding(int i, int i2, int i3, int i4) {
        requestForceShadowStateUpdateOnLayout();
        setPadding(i, i2, i3, i4);
    }

    private final void requestForceShadowStateUpdateOnLayout() {
        this.isForceShadowStateUpdateOnLayoutRequested = getShouldAvoidDisplayCutout();
    }
}
