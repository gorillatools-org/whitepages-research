package com.wpconnect;

import android.app.ActivityManager;
import android.app.Application;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.util.Log;
import android.view.WindowManager;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class CallerIdOverlayService extends Service {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "CallerIdOverlayService";
    /* access modifiers changed from: private */
    public static CallerIdOverlayService instance;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private CallStateMonitor monitor;
    private final OverlayManager overlayManager = new OverlayManager();
    private final Lazy windowManager$delegate = LazyKt.lazy(new CallerIdOverlayService$$ExternalSyntheticLambda0(this));

    public static final CallerIdOverlayService getInstance() {
        return Companion.getInstance();
    }

    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        return null;
    }

    public enum CallStyle {
        MODAL,
        FULLSCREEN;

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        static {
            CallStyle[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }

    /* access modifiers changed from: private */
    public final WindowManager getWindowManager() {
        return (WindowManager) this.windowManager$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final WindowManager windowManager_delegate$lambda$0(CallerIdOverlayService callerIdOverlayService) {
        Object systemService = callerIdOverlayService.getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        return (WindowManager) systemService;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CallerIdOverlayService getInstance() {
            CallerIdOverlayService access$getInstance$cp = CallerIdOverlayService.instance;
            if (access$getInstance$cp != null) {
                return access$getInstance$cp;
            }
            throw new IllegalStateException("CallerIdOverlayService not initialized");
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        instance = this;
        if (this.monitor == null) {
            CallStateMonitor callStateMonitor = new CallStateMonitor(this, new CallerIdOverlayService$$ExternalSyntheticLambda1(this));
            callStateMonitor.start();
            this.monitor = callStateMonitor;
        }
        String stringExtra = intent.getStringExtra("phoneNumber");
        if (stringExtra != null && !StringsKt.isBlank(stringExtra)) {
            this.overlayManager.showOverlay(stringExtra);
            getOrWaitForReactContext(new CallerIdOverlayService$$ExternalSyntheticLambda2(stringExtra));
        }
        return 2;
    }

    /* access modifiers changed from: private */
    public static final Unit onStartCommand$lambda$1(CallerIdOverlayService callerIdOverlayService) {
        callerIdOverlayService.overlayManager.hideOverlay();
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final Unit onStartCommand$lambda$4(String str, ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        WritableMap createMap = Arguments.createMap();
        createMap.putString("phoneNumber", str);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onIncomingCall", createMap);
        return Unit.INSTANCE;
    }

    private final class OverlayManager {
        private CallerIdOverlayView overlayView;

        public OverlayManager() {
        }

        private final CallStyle detectCallStyle() {
            Object systemService = CallerIdOverlayService.this.getSystemService("keyguard");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.KeyguardManager");
            Object systemService2 = CallerIdOverlayService.this.getSystemService("power");
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.os.PowerManager");
            Object systemService3 = CallerIdOverlayService.this.getSystemService("activity");
            Intrinsics.checkNotNull(systemService3, "null cannot be cast to non-null type android.app.ActivityManager");
            ActivityManager activityManager = (ActivityManager) systemService3;
            boolean isInteractive = ((PowerManager) systemService2).isInteractive();
            boolean isKeyguardLocked = ((KeyguardManager) systemService).isKeyguardLocked();
            if (!isInteractive || isKeyguardLocked) {
                return CallStyle.FULLSCREEN;
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses != null) {
                Iterable<ActivityManager.RunningAppProcessInfo> iterable = runningAppProcesses;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : iterable) {
                        if (runningAppProcessInfo.importance == 100) {
                            String str = runningAppProcessInfo.processName;
                            Intrinsics.checkNotNullExpressionValue(str, "processName");
                            if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "com.android.systemui", false, 2, (Object) null)) {
                                String str2 = runningAppProcessInfo.processName;
                                Intrinsics.checkNotNullExpressionValue(str2, "processName");
                                if (!StringsKt.contains$default((CharSequence) str2, (CharSequence) "com.android.phone", false, 2, (Object) null)) {
                                    String str3 = runningAppProcessInfo.processName;
                                    Intrinsics.checkNotNullExpressionValue(str3, "processName");
                                    if (!StringsKt.contains$default((CharSequence) str3, (CharSequence) "com.android.incallui", false, 2, (Object) null)) {
                                        return CallStyle.MODAL;
                                    }
                                } else {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
            }
            return CallStyle.FULLSCREEN;
        }

        public final void updateCallerIdDisplayedText(String str) {
            Intrinsics.checkNotNullParameter(str, "text");
            CallerIdOverlayView callerIdOverlayView = this.overlayView;
            if (callerIdOverlayView != null) {
                callerIdOverlayView.updateCallerIdDisplayedText(str);
            }
        }

        public final void displayCallerSpam() {
            CallerIdOverlayView callerIdOverlayView = this.overlayView;
            if (callerIdOverlayView != null) {
                callerIdOverlayView.displayCallerSpam();
            }
        }

        public final void showOverlay(String str) {
            Intrinsics.checkNotNullParameter(str, "phoneNumber");
            CallStyle detectCallStyle = detectCallStyle();
            Log.d(CallerIdOverlayService.TAG, "Detected call style: " + detectCallStyle);
            showOverlay(str, detectCallStyle);
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x008f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final void showOverlay(java.lang.String r11, com.wpconnect.CallerIdOverlayService.CallStyle r12) {
            /*
                r10 = this;
                boolean r11 = kotlin.text.StringsKt.isBlank(r11)
                if (r11 == 0) goto L_0x0007
                return
            L_0x0007:
                com.wpconnect.CallerIdOverlayService r11 = com.wpconnect.CallerIdOverlayService.this
                boolean r11 = com.wpconnect.CallerIdModule.Companion.PermissionHelper.hasOverlayPermission(r11)
                if (r11 != 0) goto L_0x0010
                return
            L_0x0010:
                com.wpconnect.CallerIdOverlayView r11 = r10.overlayView
                if (r11 == 0) goto L_0x0017
                r10.hideOverlay()
            L_0x0017:
                com.wpconnect.CallerIdOverlayView r11 = new com.wpconnect.CallerIdOverlayView
                com.wpconnect.CallerIdOverlayService r0 = com.wpconnect.CallerIdOverlayService.this
                com.wpconnect.CallerIdOverlayService$CallStyle r1 = com.wpconnect.CallerIdOverlayService.CallStyle.MODAL
                r2 = 0
                if (r12 != r1) goto L_0x0022
                r3 = 1
                goto L_0x0023
            L_0x0022:
                r3 = r2
            L_0x0023:
                r11.<init>(r0, r3)
                r11.setText()
                android.view.WindowManager$LayoutParams r0 = new android.view.WindowManager$LayoutParams
                int r8 = r10.getOverlayFlags()
                r9 = -3
                r5 = -2
                r6 = -2
                r7 = 2038(0x7f6, float:2.856E-42)
                r4 = r0
                r4.<init>(r5, r6, r7, r8, r9)
                com.wpconnect.CallerIdOverlayService r3 = com.wpconnect.CallerIdOverlayService.this
                android.content.res.Resources r4 = r3.getResources()
                android.util.DisplayMetrics r4 = r4.getDisplayMetrics()
                int r4 = r4.heightPixels
                int r5 = android.os.Build.VERSION.SDK_INT
                r6 = 28
                if (r5 < r6) goto L_0x0080
                java.lang.String r6 = "window"
                java.lang.Object r3 = r3.getSystemService(r6)
                java.lang.String r6 = "null cannot be cast to non-null type android.view.WindowManager"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r6)
                android.view.WindowManager r3 = (android.view.WindowManager) r3
                r6 = 30
                if (r5 < r6) goto L_0x0060
                android.view.WindowMetrics r3 = r3.getCurrentWindowMetrics()
                goto L_0x0061
            L_0x0060:
                r3 = 0
            L_0x0061:
                if (r3 == 0) goto L_0x0080
                if (r5 < r6) goto L_0x0080
                android.view.WindowInsets r3 = r3.getWindowInsets()
                int r5 = android.view.WindowInsets.Type.displayCutout()
                int r6 = android.view.WindowInsets.Type.statusBars()
                r5 = r5 | r6
                android.graphics.Insets r3 = r3.getInsetsIgnoringVisibility(r5)
                java.lang.String r5 = "getInsetsIgnoringVisibility(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
                int r3 = r3.top
                goto L_0x0081
            L_0x0080:
                r3 = r2
            L_0x0081:
                r5 = 49
                r0.gravity = r5
                double r4 = (double) r4
                r6 = 4598175219545276416(0x3fd0000000000000, double:0.25)
                double r6 = r6 * r4
                int r6 = (int) r6
                int r6 = r6 + r3
                r0.y = r6
                if (r12 != r1) goto L_0x0099
                r6 = 4599075939470750515(0x3fd3333333333333, double:0.3)
                double r4 = r4 * r6
                int r12 = (int) r4
                int r3 = r3 + r12
                r0.y = r3
            L_0x0099:
                com.wpconnect.CallerIdOverlayService r12 = com.wpconnect.CallerIdOverlayService.this
                android.view.WindowManager r12 = r12.getWindowManager()
                r12.addView(r11, r0)
                r11.setVisibility(r2)
                r10.overlayView = r11
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wpconnect.CallerIdOverlayService.OverlayManager.showOverlay(java.lang.String, com.wpconnect.CallerIdOverlayService$CallStyle):void");
        }

        private final int getOverlayFlags() {
            if (Build.VERSION.SDK_INT >= 33) {
                return 19661736;
            }
            return StringsKt.equals("samsung", Build.MANUFACTURER, true) ? 19661608 : 17040168;
        }

        public final void hideOverlay() {
            CallerIdOverlayView callerIdOverlayView = this.overlayView;
            if (callerIdOverlayView != null) {
                CallerIdOverlayService.this.getWindowManager().removeView(callerIdOverlayView);
                this.overlayView = null;
            }
        }
    }

    public final void updateCallerIdDisplayedText(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        this.mainHandler.post(new CallerIdOverlayService$$ExternalSyntheticLambda4(this, str));
    }

    /* access modifiers changed from: private */
    public static final void updateCallerIdDisplayedText$lambda$5(CallerIdOverlayService callerIdOverlayService, String str) {
        callerIdOverlayService.overlayManager.updateCallerIdDisplayedText(str);
    }

    public final void displayCallerSpam() {
        this.mainHandler.post(new CallerIdOverlayService$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: private */
    public static final void displayCallerSpam$lambda$6(CallerIdOverlayService callerIdOverlayService) {
        callerIdOverlayService.overlayManager.displayCallerSpam();
    }

    public final void hideOverlay() {
        this.overlayManager.hideOverlay();
    }

    private final void getOrWaitForReactContext(Function1 function1) {
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.facebook.react.ReactApplication");
        ReactInstanceManager reactInstanceManager = ((ReactApplication) application).getReactNativeHost().getReactInstanceManager();
        ReactContext currentReactContext = reactInstanceManager.getCurrentReactContext();
        if (currentReactContext == null || !currentReactContext.hasActiveReactInstance()) {
            if (currentReactContext != null && !currentReactContext.hasActiveReactInstance()) {
                reactInstanceManager.recreateReactContextInBackground();
            }
            reactInstanceManager.addReactInstanceEventListener(new CallerIdOverlayService$getOrWaitForReactContext$listener$1(new AtomicBoolean(false), reactInstanceManager, function1));
            if (!reactInstanceManager.hasStartedCreatingInitialContext()) {
                reactInstanceManager.createReactContextInBackground();
                return;
            }
            return;
        }
        function1.invoke(currentReactContext);
    }

    public void onDestroy() {
        CallStateMonitor callStateMonitor = this.monitor;
        if (callStateMonitor != null) {
            callStateMonitor.stop();
        }
        this.overlayManager.hideOverlay();
    }
}
