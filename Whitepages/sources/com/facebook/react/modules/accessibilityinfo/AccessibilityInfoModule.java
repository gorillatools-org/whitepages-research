package com.facebook.react.modules.accessibilityinfo;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Build;
import android.provider.Settings;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.facebook.fbreact.specs.NativeAccessibilityInfoSpec;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Http2;

@ReactModule(name = "AccessibilityInfo")
public final class AccessibilityInfoModule extends NativeAccessibilityInfoSpec implements LifecycleEventListener {
    private static final String ACCESSIBILITY_HIGH_TEXT_CONTRAST_ENABLED_CONSTANT = "high_text_contrast_enabled";
    private static final String ACCESSIBILITY_SERVICE_EVENT_NAME = "accessibilityServiceDidChange";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String GRAYSCALE_MODE_EVENT_NAME = "grayscaleModeDidChange";
    private static final String HIGH_TEXT_CONTRAST_EVENT_NAME = "highTextContrastDidChange";
    private static final String INVERT_COLOR_EVENT_NAME = "invertColorDidChange";
    public static final String NAME = "AccessibilityInfo";
    private static final String REDUCE_MOTION_EVENT_NAME = "reduceMotionDidChange";
    private static final String TOUCH_EXPLORATION_EVENT_NAME = "touchExplorationDidChange";
    private final AccessibilityManager accessibilityManager;
    private final ReactAccessibilityServiceChangeListener accessibilityServiceChangeListener = new ReactAccessibilityServiceChangeListener();
    private boolean accessibilityServiceEnabled;
    private final ContentObserver animationScaleObserver = new AccessibilityInfoModule$animationScaleObserver$1(this, UiThreadUtil.getUiThreadHandler());
    private final ContentResolver contentResolver;
    private boolean grayscaleModeEnabled;
    private boolean highTextContrastEnabled;
    private final ContentObserver highTextContrastObserver = new AccessibilityInfoModule$highTextContrastObserver$1(this, UiThreadUtil.getUiThreadHandler());
    private boolean invertColorsEnabled;
    private int recommendedTimeout;
    private boolean reduceMotionEnabled;
    private boolean touchExplorationEnabled;
    private final ReactTouchExplorationStateChangeListener touchExplorationStateChangeListener = new ReactTouchExplorationStateChangeListener();

    public void onHostDestroy() {
    }

    public void setAccessibilityFocus(double d) {
    }

    @TargetApi(21)
    private final class ReactTouchExplorationStateChangeListener implements AccessibilityManager.TouchExplorationStateChangeListener {
        public ReactTouchExplorationStateChangeListener() {
        }

        public void onTouchExplorationStateChanged(boolean z) {
            AccessibilityInfoModule.this.updateAndSendTouchExplorationChangeEvent(z);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AccessibilityInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "context");
        Object systemService = reactApplicationContext.getApplicationContext().getSystemService("accessibility");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        AccessibilityManager accessibilityManager2 = (AccessibilityManager) systemService;
        this.accessibilityManager = accessibilityManager2;
        this.contentResolver = getReactApplicationContext().getContentResolver();
        this.touchExplorationEnabled = accessibilityManager2.isTouchExplorationEnabled();
        this.accessibilityServiceEnabled = accessibilityManager2.isEnabled();
        this.reduceMotionEnabled = isReduceMotionEnabledValue();
        this.highTextContrastEnabled = isHighTextContrastEnabledValue();
        this.grayscaleModeEnabled = isGrayscaleEnabledValue();
    }

    @TargetApi(21)
    private final class ReactAccessibilityServiceChangeListener implements AccessibilityManager.AccessibilityStateChangeListener {
        public ReactAccessibilityServiceChangeListener() {
        }

        public void onAccessibilityStateChanged(boolean z) {
            AccessibilityInfoModule.this.updateAndSendAccessibilityServiceChangeEvent(z);
        }
    }

    @TargetApi(21)
    private final boolean isReduceMotionEnabledValue() {
        String string = Settings.Global.getString(this.contentResolver, "transition_animation_scale");
        return (string != null ? Float.parseFloat(string) : 1.0f) == 0.0f;
    }

    @TargetApi(21)
    private final boolean isInvertColorsEnabledValue() {
        try {
            if (Settings.Secure.getInt(this.contentResolver, "accessibility_display_inversion_enabled") == 1) {
                return true;
            }
            return false;
        } catch (Settings.SettingNotFoundException unused) {
            return false;
        }
    }

    @TargetApi(21)
    private final boolean isGrayscaleEnabledValue() {
        try {
            if (Settings.Secure.getInt(this.contentResolver, "accessibility_display_daltonizer_enabled") == 1 && Settings.Secure.getInt(this.contentResolver, "accessibility_display_daltonizer") == 0) {
                return true;
            }
            return false;
        } catch (Settings.SettingNotFoundException unused) {
            return false;
        }
    }

    @TargetApi(21)
    private final boolean isHighTextContrastEnabledValue() {
        return Settings.Secure.getInt(this.contentResolver, ACCESSIBILITY_HIGH_TEXT_CONTRAST_ENABLED_CONSTANT, 0) != 0;
    }

    public void isReduceMotionEnabled(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "successCallback");
        callback.invoke(Boolean.valueOf(this.reduceMotionEnabled));
    }

    public void isInvertColorsEnabled(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "successCallback");
        callback.invoke(Boolean.valueOf(this.invertColorsEnabled));
    }

    public void isGrayscaleEnabled(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "successCallback");
        callback.invoke(Boolean.valueOf(this.grayscaleModeEnabled));
    }

    public void isHighTextContrastEnabled(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "successCallback");
        callback.invoke(Boolean.valueOf(this.highTextContrastEnabled));
    }

    public void isTouchExplorationEnabled(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "successCallback");
        callback.invoke(Boolean.valueOf(this.touchExplorationEnabled));
    }

    public void isAccessibilityServiceEnabled(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "successCallback");
        callback.invoke(Boolean.valueOf(this.accessibilityServiceEnabled));
    }

    /* access modifiers changed from: private */
    public final void updateAndSendReduceMotionChangeEvent() {
        boolean isReduceMotionEnabledValue = isReduceMotionEnabledValue();
        if (this.reduceMotionEnabled != isReduceMotionEnabledValue) {
            this.reduceMotionEnabled = isReduceMotionEnabledValue;
            ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
            if (reactApplicationContextIfActiveOrWarn != null) {
                reactApplicationContextIfActiveOrWarn.emitDeviceEvent(REDUCE_MOTION_EVENT_NAME, Boolean.valueOf(this.reduceMotionEnabled));
            }
        }
    }

    private final void updateAndSendInvertColorsChangeEvent() {
        boolean isInvertColorsEnabledValue = isInvertColorsEnabledValue();
        if (this.invertColorsEnabled != isInvertColorsEnabledValue) {
            this.invertColorsEnabled = isInvertColorsEnabledValue;
            ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
            if (reactApplicationContextIfActiveOrWarn != null) {
                reactApplicationContextIfActiveOrWarn.emitDeviceEvent(INVERT_COLOR_EVENT_NAME, Boolean.valueOf(this.invertColorsEnabled));
            }
        }
    }

    /* access modifiers changed from: private */
    public final void updateAndSendHighTextContrastChangeEvent() {
        boolean isHighTextContrastEnabledValue = isHighTextContrastEnabledValue();
        if (this.highTextContrastEnabled != isHighTextContrastEnabledValue) {
            this.highTextContrastEnabled = isHighTextContrastEnabledValue;
            ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
            if (reactApplicationContextIfActiveOrWarn != null) {
                reactApplicationContextIfActiveOrWarn.emitDeviceEvent(HIGH_TEXT_CONTRAST_EVENT_NAME, Boolean.valueOf(this.highTextContrastEnabled));
            }
        }
    }

    /* access modifiers changed from: private */
    public final void updateAndSendTouchExplorationChangeEvent(boolean z) {
        if (this.touchExplorationEnabled != z) {
            this.touchExplorationEnabled = z;
            if (getReactApplicationContextIfActiveOrWarn() != null) {
                getReactApplicationContext().emitDeviceEvent(TOUCH_EXPLORATION_EVENT_NAME, Boolean.valueOf(this.touchExplorationEnabled));
            }
        }
    }

    /* access modifiers changed from: private */
    public final void updateAndSendAccessibilityServiceChangeEvent(boolean z) {
        if (this.accessibilityServiceEnabled != z) {
            this.accessibilityServiceEnabled = z;
            if (getReactApplicationContextIfActiveOrWarn() != null) {
                getReactApplicationContext().emitDeviceEvent(ACCESSIBILITY_SERVICE_EVENT_NAME, Boolean.valueOf(this.accessibilityServiceEnabled));
            }
        }
    }

    private final void updateAndSendGrayscaleModeChangeEvent() {
        boolean isGrayscaleEnabledValue = isGrayscaleEnabledValue();
        if (this.grayscaleModeEnabled != isGrayscaleEnabledValue) {
            this.grayscaleModeEnabled = isGrayscaleEnabledValue;
            ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
            if (reactApplicationContextIfActiveOrWarn != null) {
                reactApplicationContextIfActiveOrWarn.emitDeviceEvent(GRAYSCALE_MODE_EVENT_NAME, Boolean.valueOf(this.grayscaleModeEnabled));
            }
        }
    }

    @TargetApi(21)
    public void onHostResume() {
        AccessibilityManager accessibilityManager2 = this.accessibilityManager;
        if (accessibilityManager2 != null) {
            accessibilityManager2.addTouchExplorationStateChangeListener(this.touchExplorationStateChangeListener);
        }
        AccessibilityManager accessibilityManager3 = this.accessibilityManager;
        if (accessibilityManager3 != null) {
            accessibilityManager3.addAccessibilityStateChangeListener(this.accessibilityServiceChangeListener);
        }
        boolean z = false;
        this.contentResolver.registerContentObserver(Settings.Global.getUriFor("transition_animation_scale"), false, this.animationScaleObserver);
        this.contentResolver.registerContentObserver(Settings.Global.getUriFor(ACCESSIBILITY_HIGH_TEXT_CONTRAST_ENABLED_CONSTANT), false, this.highTextContrastObserver);
        AccessibilityManager accessibilityManager4 = this.accessibilityManager;
        updateAndSendTouchExplorationChangeEvent(accessibilityManager4 != null && accessibilityManager4.isTouchExplorationEnabled());
        AccessibilityManager accessibilityManager5 = this.accessibilityManager;
        if (accessibilityManager5 != null && accessibilityManager5.isEnabled()) {
            z = true;
        }
        updateAndSendAccessibilityServiceChangeEvent(z);
        updateAndSendReduceMotionChangeEvent();
        updateAndSendHighTextContrastChangeEvent();
        updateAndSendInvertColorsChangeEvent();
        updateAndSendGrayscaleModeChangeEvent();
    }

    @TargetApi(21)
    public void onHostPause() {
        AccessibilityManager accessibilityManager2 = this.accessibilityManager;
        if (accessibilityManager2 != null) {
            accessibilityManager2.removeTouchExplorationStateChangeListener(this.touchExplorationStateChangeListener);
        }
        AccessibilityManager accessibilityManager3 = this.accessibilityManager;
        if (accessibilityManager3 != null) {
            accessibilityManager3.removeAccessibilityStateChangeListener(this.accessibilityServiceChangeListener);
        }
        this.contentResolver.unregisterContentObserver(this.animationScaleObserver);
        this.contentResolver.unregisterContentObserver(this.highTextContrastObserver);
    }

    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
        AccessibilityManager accessibilityManager2 = this.accessibilityManager;
        boolean z = false;
        updateAndSendTouchExplorationChangeEvent(accessibilityManager2 != null && accessibilityManager2.isTouchExplorationEnabled());
        AccessibilityManager accessibilityManager3 = this.accessibilityManager;
        if (accessibilityManager3 != null && accessibilityManager3.isEnabled()) {
            z = true;
        }
        updateAndSendAccessibilityServiceChangeEvent(z);
        updateAndSendReduceMotionChangeEvent();
        updateAndSendHighTextContrastChangeEvent();
    }

    public void invalidate() {
        getReactApplicationContext().removeLifecycleEventListener(this);
        super.invalidate();
    }

    public void announceForAccessibility(String str) {
        AccessibilityManager accessibilityManager2 = this.accessibilityManager;
        if (accessibilityManager2 != null && accessibilityManager2.isEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(Http2.INITIAL_MAX_FRAME_SIZE);
            obtain.getText().add(str);
            obtain.setClassName(AccessibilityInfoModule.class.getName());
            obtain.setPackageName(getReactApplicationContext().getPackageName());
            this.accessibilityManager.sendAccessibilityEvent(obtain);
        }
    }

    public void getRecommendedTimeoutMillis(double d, Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "successCallback");
        if (Build.VERSION.SDK_INT < 29) {
            callback.invoke(Integer.valueOf((int) d));
            return;
        }
        AccessibilityManager accessibilityManager2 = this.accessibilityManager;
        int m = accessibilityManager2 != null ? accessibilityManager2.getRecommendedTimeoutMillis((int) d, 4) : 0;
        this.recommendedTimeout = m;
        callback.invoke(Integer.valueOf(m));
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
