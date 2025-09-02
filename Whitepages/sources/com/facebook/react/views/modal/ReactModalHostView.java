package com.facebook.react.views.modal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import app.notifee.core.event.LogEvent;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.uimanager.JSPointerDispatcher;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.WindowUtilKt;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@SuppressLint({"ViewConstructor"})
public final class ReactModalHostView extends ViewGroup implements LifecycleEventListener {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "ReactModalHost";
    private String animationType;
    private boolean createNewDialog;
    private Dialog dialog;
    private final DialogRootViewGroup dialogRootViewGroup;
    private boolean hardwareAccelerated;
    private boolean navigationBarTranslucent;
    private OnRequestCloseListener onRequestCloseListener;
    private DialogInterface.OnShowListener onShowListener;
    private boolean statusBarTranslucent;
    private boolean transparent;

    public interface OnRequestCloseListener {
        void onRequestClose(DialogInterface dialogInterface);
    }

    public void addChildrenForAccessibility(ArrayList<View> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "outChildren");
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Intrinsics.checkNotNullParameter(accessibilityEvent, "event");
        return false;
    }

    public void onHostPause() {
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactModalHostView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        this.dialogRootViewGroup = new DialogRootViewGroup(themedReactContext);
    }

    @VisibleForTesting
    public final Dialog getDialog() {
        return this.dialog;
    }

    public final boolean getTransparent() {
        return this.transparent;
    }

    public final void setTransparent(boolean z) {
        this.transparent = z;
    }

    public final DialogInterface.OnShowListener getOnShowListener() {
        return this.onShowListener;
    }

    public final void setOnShowListener(DialogInterface.OnShowListener onShowListener2) {
        this.onShowListener = onShowListener2;
    }

    public final OnRequestCloseListener getOnRequestCloseListener() {
        return this.onRequestCloseListener;
    }

    public final void setOnRequestCloseListener(OnRequestCloseListener onRequestCloseListener2) {
        this.onRequestCloseListener = onRequestCloseListener2;
    }

    public final boolean getStatusBarTranslucent() {
        return this.statusBarTranslucent;
    }

    public final void setStatusBarTranslucent(boolean z) {
        this.statusBarTranslucent = z;
        this.createNewDialog = true;
    }

    public final boolean getNavigationBarTranslucent() {
        return this.navigationBarTranslucent;
    }

    public final void setNavigationBarTranslucent(boolean z) {
        this.navigationBarTranslucent = z;
        this.createNewDialog = true;
    }

    public final String getAnimationType() {
        return this.animationType;
    }

    public final void setAnimationType(String str) {
        this.animationType = str;
        this.createNewDialog = true;
    }

    public final boolean getHardwareAccelerated() {
        return this.hardwareAccelerated;
    }

    public final void setHardwareAccelerated(boolean z) {
        this.hardwareAccelerated = z;
        this.createNewDialog = true;
    }

    public final StateWrapper getStateWrapper() {
        return this.dialogRootViewGroup.getStateWrapper$ReactAndroid_release();
    }

    public final void setStateWrapper(StateWrapper stateWrapper) {
        this.dialogRootViewGroup.setStateWrapper$ReactAndroid_release(stateWrapper);
    }

    public final EventDispatcher getEventDispatcher() {
        return this.dialogRootViewGroup.getEventDispatcher$ReactAndroid_release();
    }

    public final void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.dialogRootViewGroup.setEventDispatcher$ReactAndroid_release(eventDispatcher);
    }

    public void dispatchProvideStructure(ViewStructure viewStructure) {
        Intrinsics.checkNotNullParameter(viewStructure, "structure");
        this.dialogRootViewGroup.dispatchProvideStructure(viewStructure);
    }

    public void setId(int i) {
        super.setId(i);
        this.dialogRootViewGroup.setId(i);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ((ThemedReactContext) context).addLifecycleEventListener(this);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onDropInstance();
    }

    public void addView(View view, int i) {
        UiThreadUtil.assertOnUiThread();
        this.dialogRootViewGroup.addView(view, i);
    }

    public int getChildCount() {
        return this.dialogRootViewGroup.getChildCount();
    }

    public View getChildAt(int i) {
        return this.dialogRootViewGroup.getChildAt(i);
    }

    public void removeView(View view) {
        UiThreadUtil.assertOnUiThread();
        if (view != null) {
            this.dialogRootViewGroup.removeView(view);
        }
    }

    public void removeViewAt(int i) {
        UiThreadUtil.assertOnUiThread();
        this.dialogRootViewGroup.removeView(getChildAt(i));
    }

    public final void onDropInstance() {
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ((ThemedReactContext) context).removeLifecycleEventListener(this);
        dismiss();
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.view.ViewParent] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void dismiss() {
        /*
            r3 = this;
            com.facebook.react.bridge.UiThreadUtil.assertOnUiThread()
            android.app.Dialog r0 = r3.dialog
            if (r0 == 0) goto L_0x003d
            boolean r1 = r0.isShowing()
            if (r1 == 0) goto L_0x0024
            android.content.Context r1 = r0.getContext()
            java.lang.Class<android.app.Activity> r2 = android.app.Activity.class
            java.lang.Object r1 = com.facebook.react.views.common.ContextUtils.findContextOfType(r1, r2)
            android.app.Activity r1 = (android.app.Activity) r1
            if (r1 == 0) goto L_0x0021
            boolean r1 = r1.isFinishing()
            if (r1 != 0) goto L_0x0024
        L_0x0021:
            r0.dismiss()
        L_0x0024:
            r0 = 0
            r3.dialog = r0
            r1 = 1
            r3.createNewDialog = r1
            com.facebook.react.views.modal.ReactModalHostView$DialogRootViewGroup r1 = r3.dialogRootViewGroup
            android.view.ViewParent r1 = r1.getParent()
            boolean r2 = r1 instanceof android.view.ViewGroup
            if (r2 == 0) goto L_0x0037
            r0 = r1
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
        L_0x0037:
            if (r0 == 0) goto L_0x003d
            r1 = 0
            r0.removeViewAt(r1)
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.modal.ReactModalHostView.dismiss():void");
    }

    public void onHostResume() {
        showOrUpdate();
    }

    public void onHostDestroy() {
        onDropInstance();
    }

    private final Activity getCurrentActivity() {
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        return ((ThemedReactContext) context).getCurrentActivity();
    }

    private final boolean isFlagSecureSet(Activity activity) {
        return (activity == null || (activity.getWindow().getAttributes().flags & UserMetadata.MAX_INTERNAL_KEY_SIZE) == 0) ? false : true;
    }

    public final void showOrUpdate() {
        int i;
        Window window;
        Window window2;
        UiThreadUtil.assertOnUiThread();
        if (this.createNewDialog) {
            dismiss();
            this.createNewDialog = false;
            String str = this.animationType;
            if (Intrinsics.areEqual((Object) str, (Object) "fade")) {
                i = R.style.Theme_FullScreenDialogAnimatedFade;
            } else if (Intrinsics.areEqual((Object) str, (Object) "slide")) {
                i = R.style.Theme_FullScreenDialogAnimatedSlide;
            } else {
                i = R.style.Theme_FullScreenDialog;
            }
            Activity currentActivity = getCurrentActivity();
            Dialog dialog2 = new Dialog(currentActivity != null ? currentActivity : getContext(), i);
            this.dialog = dialog2;
            Window window3 = dialog2.getWindow();
            Objects.requireNonNull(window3);
            window3.setFlags(8, 8);
            dialog2.setContentView(getContentView());
            updateProperties();
            dialog2.setOnShowListener(this.onShowListener);
            dialog2.setOnKeyListener(new ReactModalHostView$showOrUpdate$1(this));
            Window window4 = dialog2.getWindow();
            if (window4 != null) {
                window4.setSoftInputMode(16);
            }
            if (this.hardwareAccelerated && (window2 = dialog2.getWindow()) != null) {
                window2.addFlags(16777216);
            }
            if (isFlagSecureSet(currentActivity) && (window = dialog2.getWindow()) != null) {
                window.setFlags(UserMetadata.MAX_INTERNAL_KEY_SIZE, UserMetadata.MAX_INTERNAL_KEY_SIZE);
            }
            if (currentActivity != null && !currentActivity.isFinishing()) {
                dialog2.show();
                updateSystemAppearance();
                Window window5 = dialog2.getWindow();
                if (window5 != null) {
                    window5.clearFlags(8);
                    return;
                }
                return;
            }
            return;
        }
        updateProperties();
    }

    private final View getContentView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(this.dialogRootViewGroup);
        if (!this.statusBarTranslucent) {
            frameLayout.setFitsSystemWindows(true);
        }
        return frameLayout;
    }

    private final void updateProperties() {
        Dialog dialog2 = this.dialog;
        if (dialog2 != null) {
            Window window = dialog2.getWindow();
            if (window != null) {
                Activity currentActivity = getCurrentActivity();
                if (currentActivity != null && !currentActivity.isFinishing() && !currentActivity.isDestroyed()) {
                    try {
                        Window window2 = currentActivity.getWindow();
                        if (window2 != null) {
                            if ((window2.getAttributes().flags & 1024) != 0) {
                                window.addFlags(1024);
                            } else {
                                window.clearFlags(1024);
                            }
                        }
                        WindowUtilKt.setSystemBarsTranslucency(window, this.navigationBarTranslucent);
                        if (!this.navigationBarTranslucent) {
                            WindowUtilKt.setStatusBarTranslucency(window, this.statusBarTranslucent);
                        }
                        if (this.transparent) {
                            window.clearFlags(2);
                            return;
                        }
                        window.setDimAmount(0.5f);
                        window.setFlags(2, 2);
                    } catch (IllegalArgumentException e) {
                        FLog.e(ReactConstants.TAG, "ReactModalHostView: error while setting window flags: ", e.getMessage());
                    }
                }
            } else {
                throw new IllegalStateException("dialog must have window when we call updateProperties");
            }
        } else {
            throw new IllegalStateException("dialog must exist when we call updateProperties");
        }
    }

    private final void updateSystemAppearance() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            Dialog dialog2 = this.dialog;
            if (dialog2 != null) {
                Window window = dialog2.getWindow();
                if (window != null) {
                    Window window2 = currentActivity.getWindow();
                    if (Build.VERSION.SDK_INT > 30) {
                        WindowInsetsController m = window2.getInsetsController();
                        if (m != null) {
                            int m2 = m.getSystemBarsAppearance() & 8;
                            WindowInsetsController m3 = window.getInsetsController();
                            if (m3 != null) {
                                m3.setSystemBarsAppearance(m2, 8);
                                return;
                            }
                            return;
                        }
                        throw new IllegalStateException("Required value was null.");
                    }
                    window.getDecorView().setSystemUiVisibility(window2.getDecorView().getSystemUiVisibility());
                    return;
                }
                throw new IllegalStateException("dialog must have window when we call updateProperties");
            }
            throw new IllegalStateException("dialog must exist when we call updateProperties");
        }
    }

    public final void setDialogRootViewGroupTestId(String str) {
        this.dialogRootViewGroup.setTag(R.id.react_test_id, str);
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class DialogRootViewGroup extends ReactViewGroup implements RootView {
        private EventDispatcher eventDispatcher;
        private JSPointerDispatcher jSPointerDispatcher;
        private final JSTouchDispatcher jSTouchDispatcher = new JSTouchDispatcher(this);
        private StateWrapper stateWrapper;
        /* access modifiers changed from: private */
        public int viewHeight;
        /* access modifiers changed from: private */
        public int viewWidth;

        public void requestDisallowInterceptTouchEvent(boolean z) {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DialogRootViewGroup(Context context) {
            super(context);
            Intrinsics.checkNotNullParameter(context, "context");
            if (ReactFeatureFlags.dispatchPointerEvents) {
                this.jSPointerDispatcher = new JSPointerDispatcher(this);
            }
        }

        public final StateWrapper getStateWrapper$ReactAndroid_release() {
            return this.stateWrapper;
        }

        public final void setStateWrapper$ReactAndroid_release(StateWrapper stateWrapper2) {
            this.stateWrapper = stateWrapper2;
        }

        public final EventDispatcher getEventDispatcher$ReactAndroid_release() {
            return this.eventDispatcher;
        }

        public final void setEventDispatcher$ReactAndroid_release(EventDispatcher eventDispatcher2) {
            this.eventDispatcher = eventDispatcher2;
        }

        /* access modifiers changed from: private */
        public final ThemedReactContext getReactContext() {
            Context context = getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
            return (ThemedReactContext) context;
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            Intrinsics.checkNotNullParameter(accessibilityNodeInfo, LogEvent.LEVEL_INFO);
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            String str = (String) getTag(R.id.react_test_id);
            if (str != null) {
                accessibilityNodeInfo.setViewIdResourceName(str);
            }
        }

        /* access modifiers changed from: protected */
        public void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            this.viewWidth = i;
            this.viewHeight = i2;
            updateState(i, i2);
        }

        public final void updateState(int i, int i2) {
            PixelUtil pixelUtil = PixelUtil.INSTANCE;
            float pxToDp = pixelUtil.pxToDp((float) i);
            float pxToDp2 = pixelUtil.pxToDp((float) i2);
            StateWrapper stateWrapper2 = this.stateWrapper;
            if (stateWrapper2 != null) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putDouble("screenWidth", (double) pxToDp);
                writableNativeMap.putDouble("screenHeight", (double) pxToDp2);
                stateWrapper2.updateState(writableNativeMap);
                return;
            }
            getReactContext().runOnNativeModulesQueueThread(new ReactModalHostView$DialogRootViewGroup$updateState$2$1(this, getReactContext()));
        }

        public void handleException(Throwable th) {
            Intrinsics.checkNotNullParameter(th, "t");
            getReactContext().getReactApplicationContext().handleException(new RuntimeException(th));
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            EventDispatcher eventDispatcher2 = this.eventDispatcher;
            if (eventDispatcher2 != null) {
                this.jSTouchDispatcher.handleTouchEvent(motionEvent, eventDispatcher2, getReactContext());
                JSPointerDispatcher jSPointerDispatcher2 = this.jSPointerDispatcher;
                if (jSPointerDispatcher2 != null) {
                    jSPointerDispatcher2.handleMotionEvent(motionEvent, eventDispatcher2, true);
                }
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            EventDispatcher eventDispatcher2 = this.eventDispatcher;
            if (eventDispatcher2 != null) {
                this.jSTouchDispatcher.handleTouchEvent(motionEvent, eventDispatcher2, getReactContext());
                JSPointerDispatcher jSPointerDispatcher2 = this.jSPointerDispatcher;
                if (jSPointerDispatcher2 != null) {
                    jSPointerDispatcher2.handleMotionEvent(motionEvent, eventDispatcher2, false);
                }
            }
            super.onTouchEvent(motionEvent);
            return true;
        }

        public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
            JSPointerDispatcher jSPointerDispatcher2;
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            EventDispatcher eventDispatcher2 = this.eventDispatcher;
            if (!(eventDispatcher2 == null || (jSPointerDispatcher2 = this.jSPointerDispatcher) == null)) {
                jSPointerDispatcher2.handleMotionEvent(motionEvent, eventDispatcher2, true);
            }
            return super.onHoverEvent(motionEvent);
        }

        public boolean onHoverEvent(MotionEvent motionEvent) {
            JSPointerDispatcher jSPointerDispatcher2;
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            EventDispatcher eventDispatcher2 = this.eventDispatcher;
            if (!(eventDispatcher2 == null || (jSPointerDispatcher2 = this.jSPointerDispatcher) == null)) {
                jSPointerDispatcher2.handleMotionEvent(motionEvent, eventDispatcher2, false);
            }
            return super.onHoverEvent(motionEvent);
        }

        public void onChildStartedNativeGesture(View view, MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "ev");
            EventDispatcher eventDispatcher2 = this.eventDispatcher;
            if (eventDispatcher2 != null) {
                this.jSTouchDispatcher.onChildStartedNativeGesture(motionEvent, eventDispatcher2);
                JSPointerDispatcher jSPointerDispatcher2 = this.jSPointerDispatcher;
                if (jSPointerDispatcher2 != null) {
                    jSPointerDispatcher2.onChildStartedNativeGesture(view, motionEvent, eventDispatcher2);
                }
            }
        }

        public void onChildEndedNativeGesture(View view, MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(view, "childView");
            Intrinsics.checkNotNullParameter(motionEvent, "ev");
            EventDispatcher eventDispatcher2 = this.eventDispatcher;
            if (eventDispatcher2 != null) {
                this.jSTouchDispatcher.onChildEndedNativeGesture(motionEvent, eventDispatcher2);
            }
            JSPointerDispatcher jSPointerDispatcher2 = this.jSPointerDispatcher;
            if (jSPointerDispatcher2 != null) {
                jSPointerDispatcher2.onChildEndedNativeGesture();
            }
        }
    }
}
