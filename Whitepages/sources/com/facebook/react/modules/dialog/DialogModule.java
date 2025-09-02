package com.facebook.react.modules.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeDialogManagerAndroidSpec;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Map;

@ReactModule(name = "DialogManagerAndroid")
public class DialogModule extends NativeDialogManagerAndroidSpec implements LifecycleEventListener {
    static final String ACTION_BUTTON_CLICKED = "buttonClicked";
    static final String ACTION_DISMISSED = "dismissed";
    static final Map<String, Object> CONSTANTS = MapBuilder.of("buttonClicked", "buttonClicked", "dismissed", "dismissed", KEY_BUTTON_POSITIVE, -1, KEY_BUTTON_NEGATIVE, -2, KEY_BUTTON_NEUTRAL, -3);
    static final String FRAGMENT_TAG = "com.facebook.catalyst.react.dialog.DialogModule";
    static final String KEY_BUTTON_NEGATIVE = "buttonNegative";
    static final String KEY_BUTTON_NEUTRAL = "buttonNeutral";
    static final String KEY_BUTTON_POSITIVE = "buttonPositive";
    static final String KEY_CANCELABLE = "cancelable";
    static final String KEY_ITEMS = "items";
    static final String KEY_MESSAGE = "message";
    static final String KEY_TITLE = "title";
    /* access modifiers changed from: private */
    public boolean mIsInForeground;

    public void onHostDestroy() {
    }

    public DialogModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private class FragmentManagerHelper {
        private final FragmentManager mFragmentManager;
        private Object mFragmentToShow;

        public FragmentManagerHelper(FragmentManager fragmentManager) {
            this.mFragmentManager = fragmentManager;
        }

        public void showPendingAlert() {
            UiThreadUtil.assertOnUiThread();
            SoftAssertions.assertCondition(DialogModule.this.mIsInForeground, "showPendingAlert() called in background");
            if (this.mFragmentToShow != null) {
                dismissExisting();
                ((AlertFragment) this.mFragmentToShow).show(this.mFragmentManager, DialogModule.FRAGMENT_TAG);
                this.mFragmentToShow = null;
            }
        }

        private void dismissExisting() {
            AlertFragment alertFragment;
            if (DialogModule.this.mIsInForeground && (alertFragment = (AlertFragment) this.mFragmentManager.findFragmentByTag(DialogModule.FRAGMENT_TAG)) != null && alertFragment.isResumed()) {
                alertFragment.dismiss();
            }
        }

        public void showNewAlert(Bundle bundle, Callback callback) {
            UiThreadUtil.assertOnUiThread();
            dismissExisting();
            AlertFragment alertFragment = new AlertFragment(callback != null ? new AlertFragmentListener(callback) : null, bundle);
            if (!DialogModule.this.mIsInForeground || this.mFragmentManager.isStateSaved()) {
                this.mFragmentToShow = alertFragment;
                return;
            }
            if (bundle.containsKey(DialogModule.KEY_CANCELABLE)) {
                alertFragment.setCancelable(bundle.getBoolean(DialogModule.KEY_CANCELABLE));
            }
            alertFragment.show(this.mFragmentManager, DialogModule.FRAGMENT_TAG);
        }
    }

    class AlertFragmentListener implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
        private final Callback mCallback;
        private boolean mCallbackConsumed = false;

        public AlertFragmentListener(Callback callback) {
            this.mCallback = callback;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            if (!this.mCallbackConsumed && DialogModule.this.getReactApplicationContext().hasActiveReactInstance()) {
                this.mCallback.invoke("buttonClicked", Integer.valueOf(i));
                this.mCallbackConsumed = true;
            }
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (!this.mCallbackConsumed && DialogModule.this.getReactApplicationContext().hasActiveReactInstance()) {
                this.mCallback.invoke("dismissed");
                this.mCallbackConsumed = true;
            }
        }
    }

    public Map<String, Object> getTypedExportedConstants() {
        return CONSTANTS;
    }

    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
    }

    public void onHostPause() {
        this.mIsInForeground = false;
    }

    public void onHostResume() {
        this.mIsInForeground = true;
        FragmentManagerHelper fragmentManagerHelper = getFragmentManagerHelper();
        if (fragmentManagerHelper != null) {
            fragmentManagerHelper.showPendingAlert();
        } else {
            FLog.w(DialogModule.class, "onHostResume called but no FragmentManager found");
        }
    }

    public void showAlert(ReadableMap readableMap, Callback callback, final Callback callback2) {
        final FragmentManagerHelper fragmentManagerHelper = getFragmentManagerHelper();
        if (fragmentManagerHelper == null) {
            callback.invoke("Tried to show an alert while not attached to an Activity");
            return;
        }
        final Bundle bundle = new Bundle();
        if (readableMap.hasKey("title")) {
            bundle.putString("title", readableMap.getString("title"));
        }
        if (readableMap.hasKey("message")) {
            bundle.putString("message", readableMap.getString("message"));
        }
        if (readableMap.hasKey(KEY_BUTTON_POSITIVE)) {
            bundle.putString("button_positive", readableMap.getString(KEY_BUTTON_POSITIVE));
        }
        if (readableMap.hasKey(KEY_BUTTON_NEGATIVE)) {
            bundle.putString("button_negative", readableMap.getString(KEY_BUTTON_NEGATIVE));
        }
        if (readableMap.hasKey(KEY_BUTTON_NEUTRAL)) {
            bundle.putString("button_neutral", readableMap.getString(KEY_BUTTON_NEUTRAL));
        }
        if (readableMap.hasKey("items")) {
            ReadableArray array = readableMap.getArray("items");
            CharSequence[] charSequenceArr = new CharSequence[array.size()];
            for (int i = 0; i < array.size(); i++) {
                charSequenceArr[i] = array.getString(i);
            }
            bundle.putCharSequenceArray("items", charSequenceArr);
        }
        if (readableMap.hasKey(KEY_CANCELABLE)) {
            bundle.putBoolean(KEY_CANCELABLE, readableMap.getBoolean(KEY_CANCELABLE));
        }
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                fragmentManagerHelper.showNewAlert(bundle, callback2);
            }
        });
    }

    private FragmentManagerHelper getFragmentManagerHelper() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null || !(currentActivity instanceof FragmentActivity)) {
            return null;
        }
        return new FragmentManagerHelper(((FragmentActivity) currentActivity).getSupportFragmentManager());
    }

    public void invalidate() {
        getReactApplicationContext().removeLifecycleEventListener(this);
        super.invalidate();
    }
}
