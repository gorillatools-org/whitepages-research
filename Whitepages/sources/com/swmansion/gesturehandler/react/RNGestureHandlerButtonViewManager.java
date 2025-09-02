package com.swmansion.gesturehandler.react;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.ViewGroupKt;
import app.notifee.core.event.LogEvent;
import com.facebook.react.R;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerDelegate;
import com.facebook.react.viewmanagers.RNGestureHandlerButtonManagerInterface;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.core.NativeViewGestureHandler;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@ReactModule(name = "RNGestureHandlerButton")
public final class RNGestureHandlerButtonViewManager extends ViewGroupManager<ButtonViewGroup> implements RNGestureHandlerButtonManagerInterface<ButtonViewGroup> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RNGestureHandlerButton";
    private final ViewManagerDelegate<ButtonViewGroup> mDelegate = new RNGestureHandlerButtonManagerDelegate(this);

    public String getName() {
        return REACT_CLASS;
    }

    public ButtonViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        return new ButtonViewGroup(themedReactContext);
    }

    @ReactProp(name = "foreground")
    @TargetApi(23)
    public void setForeground(ButtonViewGroup buttonViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setUseDrawableOnForeground(z);
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(ButtonViewGroup buttonViewGroup, int i) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setBackgroundColor(i);
    }

    @ReactProp(name = "borderless")
    public void setBorderless(ButtonViewGroup buttonViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setUseBorderlessDrawable(z);
    }

    @ReactProp(name = "enabled")
    public void setEnabled(ButtonViewGroup buttonViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setEnabled(z);
    }

    @ReactProp(name = "borderRadius")
    public void setBorderRadius(ButtonViewGroup buttonViewGroup, float f) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setBorderRadius(f);
    }

    @ReactProp(name = "borderTopLeftRadius")
    public void setBorderTopLeftRadius(ButtonViewGroup buttonViewGroup, float f) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setBorderTopLeftRadius(f);
    }

    @ReactProp(name = "borderTopRightRadius")
    public void setBorderTopRightRadius(ButtonViewGroup buttonViewGroup, float f) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setBorderTopRightRadius(f);
    }

    @ReactProp(name = "borderBottomLeftRadius")
    public void setBorderBottomLeftRadius(ButtonViewGroup buttonViewGroup, float f) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setBorderBottomLeftRadius(f);
    }

    @ReactProp(name = "borderBottomRightRadius")
    public void setBorderBottomRightRadius(ButtonViewGroup buttonViewGroup, float f) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setBorderBottomRightRadius(f);
    }

    @ReactProp(name = "borderWidth")
    public void setBorderWidth(ButtonViewGroup buttonViewGroup, float f) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setBorderWidth(f);
    }

    @ReactProp(name = "borderColor")
    public void setBorderColor(ButtonViewGroup buttonViewGroup, Integer num) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setBorderColor(num);
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ButtonViewGroup buttonViewGroup, String str) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setBorderStyle(str);
    }

    @ReactProp(name = "rippleColor")
    public void setRippleColor(ButtonViewGroup buttonViewGroup, Integer num) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setRippleColor(num);
    }

    @ReactProp(name = "rippleRadius")
    public void setRippleRadius(ButtonViewGroup buttonViewGroup, int i) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setRippleRadius(Integer.valueOf(i));
    }

    @ReactProp(name = "exclusive")
    public void setExclusive(ButtonViewGroup buttonViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setExclusive(z);
    }

    @ReactProp(name = "touchSoundDisabled")
    public void setTouchSoundDisabled(ButtonViewGroup buttonViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        buttonViewGroup.setSoundEffectsEnabled(!z);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ButtonViewGroup buttonViewGroup) {
        Intrinsics.checkNotNullParameter(buttonViewGroup, "view");
        super.onAfterUpdateTransaction(buttonViewGroup);
        buttonViewGroup.updateBackground();
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<ButtonViewGroup> getDelegate() {
        return this.mDelegate;
    }

    public static final class ButtonViewGroup extends ViewGroup implements NativeViewGestureHandler.NativeViewGestureHandlerHook {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private static View.OnClickListener dummyClickListener = new RNGestureHandlerButtonViewManager$ButtonViewGroup$$ExternalSyntheticLambda0();
        private static TypedValue resolveOutValue = new TypedValue();
        private static ButtonViewGroup soundResponder;
        private static ButtonViewGroup touchResponder;
        private int _backgroundColor;
        private float borderBottomLeftRadius;
        private float borderBottomRightRadius;
        private Integer borderColor;
        private float borderRadius;
        private String borderStyle = "solid";
        private float borderTopLeftRadius;
        private float borderTopRightRadius;
        private float borderWidth;
        private boolean exclusive = true;
        private boolean isTouched;
        private int lastAction = -1;
        private long lastEventTime = -1;
        private boolean needBackgroundUpdate;
        private boolean receivedKeyEvent;
        private Integer rippleColor;
        private Integer rippleRadius;
        private boolean useBorderlessDrawable;
        private boolean useDrawableOnForeground;

        /* access modifiers changed from: private */
        public static final void dummyClickListener$lambda$14(View view) {
        }

        public void dispatchDrawableHotspotChanged(float f, float f2) {
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        }

        public boolean canActivate(View view) {
            return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.canActivate(this, view);
        }

        public void handleEventBeforeActivation(MotionEvent motionEvent) {
            NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.handleEventBeforeActivation(this, motionEvent);
        }

        public Boolean sendTouchEvent(View view, MotionEvent motionEvent) {
            return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.sendTouchEvent(this, view, motionEvent);
        }

        public boolean shouldCancelRootViewGestureHandlerIfNecessary() {
            return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.shouldCancelRootViewGestureHandlerIfNecessary(this);
        }

        public Boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
            return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.shouldRecognizeSimultaneously(this, gestureHandler);
        }

        public boolean wantsToHandleEventBeforeActivation() {
            return NativeViewGestureHandler.NativeViewGestureHandlerHook.DefaultImpls.wantsToHandleEventBeforeActivation(this);
        }

        public ButtonViewGroup(Context context) {
            super(context);
            setOnClickListener(dummyClickListener);
            setClickable(true);
            setFocusable(true);
            this.needBackgroundUpdate = true;
            setClipChildren(false);
        }

        public final Integer getRippleColor() {
            return this.rippleColor;
        }

        public final void setRippleColor(Integer num) {
            this.rippleColor = num;
            this.needBackgroundUpdate = true;
        }

        public final Integer getRippleRadius() {
            return this.rippleRadius;
        }

        public final void setRippleRadius(Integer num) {
            this.rippleRadius = num;
            this.needBackgroundUpdate = true;
        }

        public final boolean getUseDrawableOnForeground() {
            return this.useDrawableOnForeground;
        }

        public final void setUseDrawableOnForeground(boolean z) {
            this.useDrawableOnForeground = z;
            this.needBackgroundUpdate = true;
        }

        public final boolean getUseBorderlessDrawable() {
            return this.useBorderlessDrawable;
        }

        public final void setUseBorderlessDrawable(boolean z) {
            this.useBorderlessDrawable = z;
        }

        public final float getBorderRadius() {
            return this.borderRadius;
        }

        public final void setBorderRadius(float f) {
            this.borderRadius = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        public final float getBorderTopLeftRadius() {
            return this.borderTopLeftRadius;
        }

        public final void setBorderTopLeftRadius(float f) {
            this.borderTopLeftRadius = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        public final float getBorderTopRightRadius() {
            return this.borderTopRightRadius;
        }

        public final void setBorderTopRightRadius(float f) {
            this.borderTopRightRadius = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        public final float getBorderBottomLeftRadius() {
            return this.borderBottomLeftRadius;
        }

        public final void setBorderBottomLeftRadius(float f) {
            this.borderBottomLeftRadius = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        public final float getBorderBottomRightRadius() {
            return this.borderBottomRightRadius;
        }

        public final void setBorderBottomRightRadius(float f) {
            this.borderBottomRightRadius = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        public final float getBorderWidth() {
            return this.borderWidth;
        }

        public final void setBorderWidth(float f) {
            this.borderWidth = f * getResources().getDisplayMetrics().density;
            this.needBackgroundUpdate = true;
        }

        public final Integer getBorderColor() {
            return this.borderColor;
        }

        public final void setBorderColor(Integer num) {
            this.borderColor = num;
            this.needBackgroundUpdate = true;
        }

        public final String getBorderStyle() {
            return this.borderStyle;
        }

        public final void setBorderStyle(String str) {
            this.borderStyle = str;
            this.needBackgroundUpdate = true;
        }

        private final boolean getHasBorderRadii() {
            return (this.borderRadius == 0.0f && this.borderTopLeftRadius == 0.0f && this.borderTopRightRadius == 0.0f && this.borderBottomLeftRadius == 0.0f && this.borderBottomRightRadius == 0.0f) ? false : true;
        }

        public final boolean getExclusive() {
            return this.exclusive;
        }

        public final void setExclusive(boolean z) {
            this.exclusive = z;
        }

        public final void setTouched(boolean z) {
            this.isTouched = z;
        }

        private final float[] buildBorderRadii() {
            float f = this.borderTopLeftRadius;
            float f2 = this.borderTopRightRadius;
            float f3 = this.borderBottomRightRadius;
            float f4 = this.borderBottomLeftRadius;
            float[] fArr = {f, f, f2, f2, f3, f3, f4, f4};
            ArrayList arrayList = new ArrayList(8);
            for (int i = 0; i < 8; i++) {
                float f5 = fArr[i];
                if (f5 == 0.0f) {
                    f5 = this.borderRadius;
                }
                arrayList.add(Float.valueOf(f5));
            }
            return CollectionsKt.toFloatArray(arrayList);
        }

        private final PathEffect buildBorderStyle() {
            String str = this.borderStyle;
            if (Intrinsics.areEqual((Object) str, (Object) "dotted")) {
                float f = this.borderWidth;
                return new DashPathEffect(new float[]{f, f, f, f}, 0.0f);
            } else if (!Intrinsics.areEqual((Object) str, (Object) "dashed")) {
                return null;
            } else {
                float f2 = this.borderWidth;
                float f3 = (float) 3;
                return new DashPathEffect(new float[]{f2 * f3, f2 * f3, f2 * f3, f2 * f3}, 0.0f);
            }
        }

        public void setBackgroundColor(int i) {
            this._backgroundColor = i;
            this.needBackgroundUpdate = true;
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            Intrinsics.checkNotNullParameter(accessibilityNodeInfo, LogEvent.LEVEL_INFO);
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            Object tag = super.getTag(R.id.react_test_id);
            if (tag instanceof String) {
                accessibilityNodeInfo.setViewIdResourceName((String) tag);
            }
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "ev");
            if (super.onInterceptTouchEvent(motionEvent)) {
                return true;
            }
            onTouchEvent(motionEvent);
            return isPressed();
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            long eventTime = motionEvent.getEventTime();
            int action = motionEvent.getAction();
            ButtonViewGroup buttonViewGroup = touchResponder;
            if (!(buttonViewGroup == null || buttonViewGroup == this)) {
                Intrinsics.checkNotNull(buttonViewGroup);
                if (buttonViewGroup.exclusive) {
                    if (isPressed()) {
                        setPressed(false);
                    }
                    this.lastEventTime = eventTime;
                    this.lastAction = action;
                    return false;
                }
            }
            if (motionEvent.getAction() == 3) {
                tryFreeingResponder();
            }
            if (this.lastEventTime == eventTime && this.lastAction == action && action != 3) {
                return false;
            }
            this.lastEventTime = eventTime;
            this.lastAction = action;
            return super.onTouchEvent(motionEvent);
        }

        private final void updateBackgroundColor(int i, Drawable drawable, Drawable drawable2) {
            PaintDrawable paintDrawable = new PaintDrawable(i);
            if (getHasBorderRadii()) {
                paintDrawable.setCornerRadii(buildBorderRadii());
            }
            setBackground(new LayerDrawable(drawable2 != null ? new Drawable[]{paintDrawable, drawable2, drawable} : new Drawable[]{paintDrawable, drawable}));
        }

        public final void updateBackground() {
            if (this.needBackgroundUpdate) {
                this.needBackgroundUpdate = false;
                if (this._backgroundColor == 0) {
                    setBackground((Drawable) null);
                }
                setForeground((Drawable) null);
                Drawable createSelectableDrawable = createSelectableDrawable();
                Drawable createBorderDrawable = createBorderDrawable();
                if (getHasBorderRadii() && (createSelectableDrawable instanceof RippleDrawable)) {
                    PaintDrawable paintDrawable = new PaintDrawable(-1);
                    paintDrawable.setCornerRadii(buildBorderRadii());
                    ((RippleDrawable) createSelectableDrawable).setDrawableByLayerId(16908334, paintDrawable);
                }
                if (this.useDrawableOnForeground) {
                    setForeground(createSelectableDrawable);
                    int i = this._backgroundColor;
                    if (i != 0) {
                        updateBackgroundColor(i, createBorderDrawable, (Drawable) null);
                        return;
                    }
                    return;
                }
                int i2 = this._backgroundColor;
                if (i2 == 0 && this.rippleColor == null) {
                    setBackground(new LayerDrawable(new Drawable[]{createSelectableDrawable, createBorderDrawable}));
                } else {
                    updateBackgroundColor(i2, createBorderDrawable, createSelectableDrawable);
                }
            }
        }

        private final Drawable createBorderDrawable() {
            PaintDrawable paintDrawable = new PaintDrawable(0);
            if (getHasBorderRadii()) {
                paintDrawable.setCornerRadii(buildBorderRadii());
            }
            if (this.borderWidth > 0.0f) {
                Paint paint = paintDrawable.getPaint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(this.borderWidth);
                Integer num = this.borderColor;
                paint.setColor(num != null ? num.intValue() : -16777216);
                paint.setPathEffect(buildBorderStyle());
            }
            return paintDrawable;
        }

        private final Drawable createSelectableDrawable() {
            ColorStateList colorStateList;
            Integer num = this.rippleColor;
            if (num != null && num.intValue() == 0) {
                return null;
            }
            int[][] iArr = {new int[]{16842910}};
            Integer num2 = this.rippleRadius;
            Integer num3 = this.rippleColor;
            if (num3 != null) {
                Intrinsics.checkNotNull(num3);
                colorStateList = new ColorStateList(iArr, new int[]{num3.intValue()});
            } else {
                getContext().getTheme().resolveAttribute(16843820, resolveOutValue, true);
                colorStateList = new ColorStateList(iArr, new int[]{resolveOutValue.data});
            }
            RippleDrawable rippleDrawable = new RippleDrawable(colorStateList, (Drawable) null, this.useBorderlessDrawable ? null : new ShapeDrawable(new RectShape()));
            if (num2 != null) {
                rippleDrawable.setRadius((int) PixelUtil.toPixelFromDIP((float) num2.intValue()));
            }
            return rippleDrawable;
        }

        public void drawableHotspotChanged(float f, float f2) {
            ButtonViewGroup buttonViewGroup = touchResponder;
            if (buttonViewGroup == null || buttonViewGroup == this) {
                super.drawableHotspotChanged(f, f2);
            }
        }

        public boolean canBegin(MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            if (motionEvent.getAction() == 3 || motionEvent.getAction() == 1 || motionEvent.getActionMasked() == 6) {
                return false;
            }
            boolean tryGrabbingResponder = tryGrabbingResponder();
            if (tryGrabbingResponder) {
                this.isTouched = true;
            }
            return tryGrabbingResponder;
        }

        public void afterGestureEnd(MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            tryFreeingResponder();
            this.isTouched = false;
        }

        private final void tryFreeingResponder() {
            if (touchResponder == this) {
                touchResponder = null;
                soundResponder = this;
            }
        }

        private final boolean tryGrabbingResponder() {
            if (isChildTouched$default(this, (Sequence) null, 1, (Object) null)) {
                return false;
            }
            ButtonViewGroup buttonViewGroup = touchResponder;
            if (buttonViewGroup == null) {
                touchResponder = this;
                return true;
            }
            if (!this.exclusive) {
                if (!(buttonViewGroup != null ? buttonViewGroup.exclusive : false)) {
                    return true;
                }
            } else if (buttonViewGroup == this) {
                return true;
            }
            return false;
        }

        static /* synthetic */ boolean isChildTouched$default(ButtonViewGroup buttonViewGroup, Sequence sequence, int i, Object obj) {
            if ((i & 1) != 0) {
                sequence = ViewGroupKt.getChildren(buttonViewGroup);
            }
            return buttonViewGroup.isChildTouched(sequence);
        }

        private final boolean isChildTouched(Sequence sequence) {
            Iterator it = sequence.iterator();
            while (it.hasNext()) {
                View view = (View) it.next();
                if (view instanceof ButtonViewGroup) {
                    ButtonViewGroup buttonViewGroup = (ButtonViewGroup) view;
                    if (buttonViewGroup.isTouched || buttonViewGroup.isPressed()) {
                        return true;
                    }
                }
                if ((view instanceof ViewGroup) && isChildTouched(ViewGroupKt.getChildren((ViewGroup) view))) {
                    return true;
                }
            }
            return false;
        }

        public boolean onKeyUp(int i, KeyEvent keyEvent) {
            this.receivedKeyEvent = true;
            return super.onKeyUp(i, keyEvent);
        }

        public boolean performClick() {
            if (isChildTouched$default(this, (Sequence) null, 1, (Object) null)) {
                return false;
            }
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            if (ExtensionsKt.isScreenReaderOn(context)) {
                RNGestureHandlerRootView findGestureHandlerRootView = findGestureHandlerRootView();
                if (findGestureHandlerRootView != null) {
                    findGestureHandlerRootView.activateNativeHandlers(this);
                }
            } else if (this.receivedKeyEvent) {
                RNGestureHandlerRootView findGestureHandlerRootView2 = findGestureHandlerRootView();
                if (findGestureHandlerRootView2 != null) {
                    findGestureHandlerRootView2.activateNativeHandlers(this);
                }
                this.receivedKeyEvent = false;
            }
            if (soundResponder != this) {
                return false;
            }
            tryFreeingResponder();
            soundResponder = null;
            return super.performClick();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
            if (isChildTouched$default(r3, (kotlin.sequences.Sequence) null, 1, (java.lang.Object) null) != false) goto L_0x0021;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
            if (r0.exclusive == true) goto L_0x0021;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setPressed(boolean r4) {
            /*
                r3 = this;
                if (r4 == 0) goto L_0x000a
                boolean r0 = r3.tryGrabbingResponder()
                if (r0 == 0) goto L_0x000a
                soundResponder = r3
            L_0x000a:
                boolean r0 = r3.exclusive
                r1 = 0
                if (r0 != 0) goto L_0x0021
                com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager$ButtonViewGroup r0 = touchResponder
                r2 = 1
                if (r0 == 0) goto L_0x0019
                boolean r0 = r0.exclusive
                if (r0 != r2) goto L_0x0019
                goto L_0x0021
            L_0x0019:
                r0 = 0
                boolean r0 = isChildTouched$default(r3, r0, r2, r0)
                if (r0 != 0) goto L_0x0021
                goto L_0x0022
            L_0x0021:
                r2 = r1
            L_0x0022:
                if (r4 == 0) goto L_0x002a
                com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager$ButtonViewGroup r0 = touchResponder
                if (r0 == r3) goto L_0x002a
                if (r2 == 0) goto L_0x002f
            L_0x002a:
                r3.isTouched = r4
                super.setPressed(r4)
            L_0x002f:
                if (r4 != 0) goto L_0x0037
                com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager$ButtonViewGroup r4 = touchResponder
                if (r4 != r3) goto L_0x0037
                r3.isTouched = r1
            L_0x0037:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager.ButtonViewGroup.setPressed(boolean):void");
        }

        private final RNGestureHandlerRootView findGestureHandlerRootView() {
            RNGestureHandlerRootView rNGestureHandlerRootView = null;
            for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
                if (parent instanceof RNGestureHandlerRootView) {
                    rNGestureHandlerRootView = (RNGestureHandlerRootView) parent;
                }
            }
            return rNGestureHandlerRootView;
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
