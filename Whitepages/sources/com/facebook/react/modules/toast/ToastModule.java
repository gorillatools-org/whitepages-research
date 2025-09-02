package com.facebook.react.modules.toast;

import android.widget.Toast;
import com.facebook.fbreact.specs.NativeToastAndroidSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "ToastAndroid")
public final class ToastModule extends NativeToastAndroidSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String DURATION_LONG_KEY = "LONG";
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String GRAVITY_BOTTOM_KEY = "BOTTOM";
    private static final String GRAVITY_CENTER = "CENTER";
    private static final String GRAVITY_TOP_KEY = "TOP";
    public static final String NAME = "ToastAndroid";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ToastModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
    }

    public Map<String, Object> getTypedExportedConstants() {
        return MapsKt.mutableMapOf(TuplesKt.to(DURATION_SHORT_KEY, 0), TuplesKt.to(DURATION_LONG_KEY, 1), TuplesKt.to(GRAVITY_TOP_KEY, 49), TuplesKt.to(GRAVITY_BOTTOM_KEY, 81), TuplesKt.to(GRAVITY_CENTER, 17));
    }

    public void show(String str, double d) {
        UiThreadUtil.runOnUiThread(new ToastModule$$ExternalSyntheticLambda2(this, str, (int) d));
    }

    /* access modifiers changed from: private */
    public static final void show$lambda$0(ToastModule toastModule, String str, int i) {
        Toast.makeText(toastModule.getReactApplicationContext(), str, i).show();
    }

    public void showWithGravity(String str, double d, double d2) {
        UiThreadUtil.runOnUiThread(new ToastModule$$ExternalSyntheticLambda1(this, str, (int) d, (int) d2));
    }

    /* access modifiers changed from: private */
    public static final void showWithGravity$lambda$1(ToastModule toastModule, String str, int i, int i2) {
        Toast makeText = Toast.makeText(toastModule.getReactApplicationContext(), str, i);
        makeText.setGravity(i2, 0, 0);
        makeText.show();
    }

    public void showWithGravityAndOffset(String str, double d, double d2, double d3, double d4) {
        int i = (int) d2;
        UiThreadUtil.runOnUiThread(new ToastModule$$ExternalSyntheticLambda0(this, str, (int) d, i, (int) d3, (int) d4));
    }

    /* access modifiers changed from: private */
    public static final void showWithGravityAndOffset$lambda$2(ToastModule toastModule, String str, int i, int i2, int i3, int i4) {
        Toast makeText = Toast.makeText(toastModule.getReactApplicationContext(), str, i);
        makeText.setGravity(i2, i3, i4);
        makeText.show();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
