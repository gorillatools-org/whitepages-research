package com.facebook.react.devsupport;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public final class DefaultDevLoadingViewImplementation implements DevLoadingViewManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static boolean isEnabled = true;
    private PopupWindow devLoadingPopup;
    private TextView devLoadingView;
    private final ReactInstanceDevHelper reactInstanceDevHelper;

    public DefaultDevLoadingViewImplementation(ReactInstanceDevHelper reactInstanceDevHelper2) {
        Intrinsics.checkNotNullParameter(reactInstanceDevHelper2, "reactInstanceDevHelper");
        this.reactInstanceDevHelper = reactInstanceDevHelper2;
    }

    public void showMessage(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        if (isEnabled) {
            UiThreadUtil.runOnUiThread(new DefaultDevLoadingViewImplementation$$ExternalSyntheticLambda2(this, str));
        }
    }

    /* access modifiers changed from: private */
    public static final void showMessage$lambda$0(DefaultDevLoadingViewImplementation defaultDevLoadingViewImplementation, String str) {
        defaultDevLoadingViewImplementation.showInternal(str);
    }

    public void updateProgress(String str, Integer num, Integer num2) {
        if (isEnabled) {
            UiThreadUtil.runOnUiThread(new DefaultDevLoadingViewImplementation$$ExternalSyntheticLambda1(num, num2, this, str));
        }
    }

    /* access modifiers changed from: private */
    public static final void updateProgress$lambda$1(Integer num, Integer num2, DefaultDevLoadingViewImplementation defaultDevLoadingViewImplementation, String str) {
        String str2;
        if (num == null || num2 == null || num2.intValue() <= 0) {
            str2 = "";
        } else {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            str2 = String.format(Locale.getDefault(), " %.1f%%", Arrays.copyOf(new Object[]{Float.valueOf((((float) num.intValue()) / ((float) num2.intValue())) * ((float) 100))}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        }
        TextView textView = defaultDevLoadingViewImplementation.devLoadingView;
        if (textView != null) {
            if (str == null) {
                str = "Loading";
            }
            textView.setText(str + str2 + "…");
        }
    }

    public void hide() {
        if (isEnabled) {
            UiThreadUtil.runOnUiThread(new DefaultDevLoadingViewImplementation$$ExternalSyntheticLambda0(this));
        }
    }

    /* access modifiers changed from: private */
    public static final void hide$lambda$2(DefaultDevLoadingViewImplementation defaultDevLoadingViewImplementation) {
        defaultDevLoadingViewImplementation.hideInternal();
    }

    private final void showInternal(String str) {
        PopupWindow popupWindow = this.devLoadingPopup;
        if (popupWindow == null || !popupWindow.isShowing()) {
            Activity currentActivity = this.reactInstanceDevHelper.getCurrentActivity();
            if (currentActivity == null) {
                FLog.e(ReactConstants.TAG, "Unable to display loading message because react activity isn't available");
                return;
            }
            try {
                Rect rect = new Rect();
                currentActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int i = rect.top;
                Object systemService = currentActivity.getSystemService("layout_inflater");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
                View inflate = ((LayoutInflater) systemService).inflate(R.layout.dev_loading_view, (ViewGroup) null);
                Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.TextView");
                TextView textView = (TextView) inflate;
                textView.setText(str);
                PopupWindow popupWindow2 = new PopupWindow(textView, -1, -2);
                popupWindow2.setTouchable(false);
                popupWindow2.showAtLocation(currentActivity.getWindow().getDecorView(), 0, 0, i);
                this.devLoadingView = textView;
                this.devLoadingPopup = popupWindow2;
            } catch (WindowManager.BadTokenException unused) {
                FLog.e(ReactConstants.TAG, "Unable to display loading message because react activity isn't active, message: " + str);
            }
        }
    }

    private final void hideInternal() {
        PopupWindow popupWindow = this.devLoadingPopup;
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            this.devLoadingPopup = null;
            this.devLoadingView = null;
        }
    }

    private final Context getContext() {
        return this.reactInstanceDevHelper.getCurrentActivity();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void setDevLoadingEnabled(boolean z) {
            DefaultDevLoadingViewImplementation.isEnabled = z;
        }
    }
}
