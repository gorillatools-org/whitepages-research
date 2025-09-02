package com.swmansion.rnscreens;

import android.view.View;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.RNSScreenManagerDelegate;
import com.facebook.react.viewmanagers.RNSScreenManagerInterface;
import com.swmansion.rnscreens.Screen;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.sequences.SequencesKt;

@ReactModule(name = "RNSScreen")
public class ScreenViewManager extends ViewGroupManager<Screen> implements RNSScreenManagerInterface<Screen> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RNSScreen";
    private final ViewManagerDelegate<Screen> delegate = new RNSScreenManagerDelegate(this);

    public void setCustomAnimationOnSwipe(Screen screen, boolean z) {
    }

    public void setFullScreenSwipeEnabled(Screen screen, boolean z) {
    }

    public void setFullScreenSwipeShadowEnabled(Screen screen, boolean z) {
    }

    public void setGestureResponseDistance(Screen screen, ReadableMap readableMap) {
    }

    public void setHideKeyboardOnSwipe(Screen screen, boolean z) {
    }

    public void setHomeIndicatorHidden(Screen screen, boolean z) {
    }

    public void setPreventNativeDismiss(Screen screen, boolean z) {
    }

    public void setSwipeDirection(Screen screen, String str) {
    }

    public void setTransitionDuration(Screen screen, int i) {
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public Screen createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return new Screen(themedReactContext);
    }

    public void setActivityState(Screen screen, float f) {
        Intrinsics.checkNotNullParameter(screen, "view");
        setActivityState(screen, (int) f);
    }

    public void addView(Screen screen, View view, int i) {
        Intrinsics.checkNotNullParameter(screen, "parent");
        Intrinsics.checkNotNullParameter(view, "child");
        if (view instanceof ScreenContentWrapper) {
            screen.registerLayoutCallbackForWrapper((ScreenContentWrapper) view);
        } else if (view instanceof ScreenFooter) {
            screen.setFooter((ScreenFooter) view);
        }
        super.addView(screen, view, i);
    }

    public void removeViewAt(Screen screen, int i) {
        Intrinsics.checkNotNullParameter(screen, "parent");
        if (screen.getChildAt(i) instanceof ScreenFooter) {
            screen.setFooter((ScreenFooter) null);
        }
        super.removeViewAt(screen, i);
    }

    public void removeView(Screen screen, View view) {
        Intrinsics.checkNotNullParameter(screen, "parent");
        Intrinsics.checkNotNullParameter(view, "view");
        super.removeView(screen, view);
        if (view instanceof ScreenFooter) {
            screen.setFooter((ScreenFooter) null);
        }
    }

    public Object updateState(Screen screen, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        Intrinsics.checkNotNullParameter(screen, "view");
        return super.updateState(screen, reactStylesDiffMap, stateWrapper);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "view");
        super.onAfterUpdateTransaction(screen);
        screen.onFinalizePropsUpdate$react_native_screens_release();
    }

    @ReactProp(name = "activityState")
    public final void setActivityState(Screen screen, int i) {
        Intrinsics.checkNotNullParameter(screen, "view");
        if (i != -1) {
            if (i == 0) {
                screen.setActivityState(Screen.ActivityState.INACTIVE);
            } else if (i == 1) {
                screen.setActivityState(Screen.ActivityState.TRANSITIONING_OR_BELOW_TOP);
            } else if (i == 2) {
                screen.setActivityState(Screen.ActivityState.ON_TOP);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        if (r4.equals("containedTransparentModal") != false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
        if (r4.equals("containedModal") != false) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        if (r4.equals("modal") != false) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        r4 = com.swmansion.rnscreens.Screen.StackPresentation.MODAL;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0051, code lost:
        if (r4.equals("transparentModal") != false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0053, code lost:
        r4 = com.swmansion.rnscreens.Screen.StackPresentation.TRANSPARENT_MODAL;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0055, code lost:
        r3.setStackPresentation(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r4.equals("fullScreenModal") != false) goto L_0x003d;
     */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "stackPresentation")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setStackPresentation(com.swmansion.rnscreens.Screen r3, java.lang.String r4) {
        /*
            r2 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            if (r4 == 0) goto L_0x0059
            int r0 = r4.hashCode()
            switch(r0) {
                case -76271493: goto L_0x004b;
                case 3452698: goto L_0x0040;
                case 104069805: goto L_0x0035;
                case 438078970: goto L_0x002c;
                case 955284238: goto L_0x0023;
                case 1171936146: goto L_0x001a;
                case 1798290171: goto L_0x000f;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0059
        L_0x000f:
            java.lang.String r0 = "formSheet"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0059
            com.swmansion.rnscreens.Screen$StackPresentation r4 = com.swmansion.rnscreens.Screen.StackPresentation.FORM_SHEET
            goto L_0x0055
        L_0x001a:
            java.lang.String r0 = "fullScreenModal"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0059
            goto L_0x003d
        L_0x0023:
            java.lang.String r0 = "containedTransparentModal"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0059
            goto L_0x0053
        L_0x002c:
            java.lang.String r0 = "containedModal"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0059
            goto L_0x003d
        L_0x0035:
            java.lang.String r0 = "modal"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0059
        L_0x003d:
            com.swmansion.rnscreens.Screen$StackPresentation r4 = com.swmansion.rnscreens.Screen.StackPresentation.MODAL
            goto L_0x0055
        L_0x0040:
            java.lang.String r0 = "push"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0059
            com.swmansion.rnscreens.Screen$StackPresentation r4 = com.swmansion.rnscreens.Screen.StackPresentation.PUSH
            goto L_0x0055
        L_0x004b:
            java.lang.String r0 = "transparentModal"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0059
        L_0x0053:
            com.swmansion.rnscreens.Screen$StackPresentation r4 = com.swmansion.rnscreens.Screen.StackPresentation.TRANSPARENT_MODAL
        L_0x0055:
            r3.setStackPresentation(r4)
            return
        L_0x0059:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r3 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unknown presentation type "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenViewManager.setStackPresentation(com.swmansion.rnscreens.Screen, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004e, code lost:
        if (r4.equals("flip") != false) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0083, code lost:
        if (r4.equals("simple_push") != false) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009c, code lost:
        throw new com.facebook.react.bridge.JSApplicationIllegalArgumentException("Unknown animation type " + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
        if (r4.equals(com.facebook.hermes.intl.Constants.COLLATION_DEFAULT) != false) goto L_0x009d;
     */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "stackAnimation")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setStackAnimation(com.swmansion.rnscreens.Screen r3, java.lang.String r4) {
        /*
            r2 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            if (r4 == 0) goto L_0x009d
            int r0 = r4.hashCode()
            switch(r0) {
                case -1418955385: goto L_0x007d;
                case -1198710326: goto L_0x0072;
                case -427095442: goto L_0x0067;
                case -349395819: goto L_0x005c;
                case 3135100: goto L_0x0051;
                case 3145837: goto L_0x0048;
                case 3387192: goto L_0x003d;
                case 182437661: goto L_0x0032;
                case 1500346553: goto L_0x0026;
                case 1544803905: goto L_0x001c;
                case 1601504978: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x0086
        L_0x0010:
            java.lang.String r0 = "slide_from_bottom"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0086
            com.swmansion.rnscreens.Screen$StackAnimation r4 = com.swmansion.rnscreens.Screen.StackAnimation.SLIDE_FROM_BOTTOM
            goto L_0x009f
        L_0x001c:
            java.lang.String r0 = "default"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0086
            goto L_0x009d
        L_0x0026:
            java.lang.String r0 = "ios_from_right"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0086
            com.swmansion.rnscreens.Screen$StackAnimation r4 = com.swmansion.rnscreens.Screen.StackAnimation.IOS_FROM_RIGHT
            goto L_0x009f
        L_0x0032:
            java.lang.String r0 = "fade_from_bottom"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0086
            com.swmansion.rnscreens.Screen$StackAnimation r4 = com.swmansion.rnscreens.Screen.StackAnimation.FADE_FROM_BOTTOM
            goto L_0x009f
        L_0x003d:
            java.lang.String r0 = "none"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0086
            com.swmansion.rnscreens.Screen$StackAnimation r4 = com.swmansion.rnscreens.Screen.StackAnimation.NONE
            goto L_0x009f
        L_0x0048:
            java.lang.String r0 = "flip"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0086
            goto L_0x009d
        L_0x0051:
            java.lang.String r0 = "fade"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0086
            com.swmansion.rnscreens.Screen$StackAnimation r4 = com.swmansion.rnscreens.Screen.StackAnimation.FADE
            goto L_0x009f
        L_0x005c:
            java.lang.String r0 = "slide_from_right"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0086
            com.swmansion.rnscreens.Screen$StackAnimation r4 = com.swmansion.rnscreens.Screen.StackAnimation.SLIDE_FROM_RIGHT
            goto L_0x009f
        L_0x0067:
            java.lang.String r0 = "slide_from_left"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0086
            com.swmansion.rnscreens.Screen$StackAnimation r4 = com.swmansion.rnscreens.Screen.StackAnimation.SLIDE_FROM_LEFT
            goto L_0x009f
        L_0x0072:
            java.lang.String r0 = "ios_from_left"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0086
            com.swmansion.rnscreens.Screen$StackAnimation r4 = com.swmansion.rnscreens.Screen.StackAnimation.IOS_FROM_LEFT
            goto L_0x009f
        L_0x007d:
            java.lang.String r0 = "simple_push"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0086
            goto L_0x009d
        L_0x0086:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r3 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unknown animation type "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r3.<init>(r4)
            throw r3
        L_0x009d:
            com.swmansion.rnscreens.Screen$StackAnimation r4 = com.swmansion.rnscreens.Screen.StackAnimation.DEFAULT
        L_0x009f:
            r3.setStackAnimation(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenViewManager.setStackAnimation(com.swmansion.rnscreens.Screen, java.lang.String):void");
    }

    @ReactProp(defaultBoolean = true, name = "gestureEnabled")
    public void setGestureEnabled(Screen screen, boolean z) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setGestureEnabled(z);
    }

    @ReactProp(name = "replaceAnimation")
    public void setReplaceAnimation(Screen screen, String str) {
        Screen.ReplaceAnimation replaceAnimation;
        Intrinsics.checkNotNullParameter(screen, "view");
        if (str == null || Intrinsics.areEqual((Object) str, (Object) "pop")) {
            replaceAnimation = Screen.ReplaceAnimation.POP;
        } else if (Intrinsics.areEqual((Object) str, (Object) "push")) {
            replaceAnimation = Screen.ReplaceAnimation.PUSH;
        } else {
            throw new JSApplicationIllegalArgumentException("Unknown replace animation type " + str);
        }
        screen.setReplaceAnimation(replaceAnimation);
    }

    @ReactProp(name = "screenOrientation")
    public void setScreenOrientation(Screen screen, String str) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setScreenOrientation(str);
    }

    @ReactProp(name = "statusBarAnimation")
    public void setStatusBarAnimation(Screen screen, String str) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setStatusBarAnimated(Boolean.valueOf(str != null && !Intrinsics.areEqual((Object) "none", (Object) str)));
    }

    @ReactProp(customType = "Color", name = "statusBarColor")
    public void setStatusBarColor(Screen screen, Integer num) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setStatusBarColor(num);
    }

    @ReactProp(name = "statusBarStyle")
    public void setStatusBarStyle(Screen screen, String str) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setStatusBarStyle(str);
    }

    @ReactProp(name = "statusBarTranslucent")
    public void setStatusBarTranslucent(Screen screen, boolean z) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setStatusBarTranslucent(Boolean.valueOf(z));
    }

    @ReactProp(name = "statusBarHidden")
    public void setStatusBarHidden(Screen screen, boolean z) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setStatusBarHidden(Boolean.valueOf(z));
    }

    @ReactProp(customType = "Color", name = "navigationBarColor")
    public void setNavigationBarColor(Screen screen, Integer num) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setNavigationBarColor(num);
    }

    @ReactProp(name = "navigationBarTranslucent")
    public void setNavigationBarTranslucent(Screen screen, boolean z) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setNavigationBarTranslucent(Boolean.valueOf(z));
    }

    @ReactProp(name = "navigationBarHidden")
    public void setNavigationBarHidden(Screen screen, boolean z) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setNavigationBarHidden(Boolean.valueOf(z));
    }

    @ReactProp(name = "nativeBackButtonDismissalEnabled")
    public void setNativeBackButtonDismissalEnabled(Screen screen, boolean z) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setNativeBackButtonDismissalEnabled(z);
    }

    @ReactProp(name = "sheetElevation")
    public void setSheetElevation(Screen screen, int i) {
        if (screen != null) {
            screen.setSheetElevation((float) i);
        }
    }

    @ReactProp(name = "sheetAllowedDetents")
    public void setSheetAllowedDetents(Screen screen, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.getSheetDetents().clear();
        if (readableArray == null || readableArray.size() == 0) {
            screen.getSheetDetents().add(Double.valueOf(1.0d));
        } else {
            SequencesKt.toCollection(SequencesKt.map(CollectionsKt.asSequence(IntProgression.Companion.fromClosedRange(0, readableArray.size() - 1, 1)), new ScreenViewManager$$ExternalSyntheticLambda0(readableArray)), screen.getSheetDetents());
        }
    }

    /* access modifiers changed from: private */
    public static final double setSheetAllowedDetents$lambda$0(ReadableArray readableArray, int i) {
        return readableArray.getDouble(i);
    }

    @ReactProp(name = "sheetLargestUndimmedDetent")
    public void setSheetLargestUndimmedDetent(Screen screen, int i) {
        Intrinsics.checkNotNullParameter(screen, "view");
        if (-1 > i || i >= 3) {
            throw new IllegalStateException("[RNScreens] sheetLargestUndimmedDetent on Android supports values between -1 and 2");
        }
        screen.setSheetLargestUndimmedDetentIndex(i);
    }

    @ReactProp(name = "sheetGrabberVisible")
    public void setSheetGrabberVisible(Screen screen, boolean z) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setSheetGrabberVisible(z);
    }

    @ReactProp(name = "sheetCornerRadius")
    public void setSheetCornerRadius(Screen screen, float f) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setSheetCornerRadius(f);
    }

    @ReactProp(name = "sheetExpandsWhenScrolledToEdge")
    public void setSheetExpandsWhenScrolledToEdge(Screen screen, boolean z) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setSheetExpandsWhenScrolledToEdge(z);
    }

    @ReactProp(name = "sheetInitialDetent")
    public void setSheetInitialDetent(Screen screen, int i) {
        Intrinsics.checkNotNullParameter(screen, "view");
        screen.setSheetInitialDetentIndex(i);
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapsKt.mutableMapOf(TuplesKt.to("topDismissed", MapBuilder.of("registrationName", "onDismissed")), TuplesKt.to("topWillAppear", MapBuilder.of("registrationName", "onWillAppear")), TuplesKt.to("topAppear", MapBuilder.of("registrationName", "onAppear")), TuplesKt.to("topWillDisappear", MapBuilder.of("registrationName", "onWillDisappear")), TuplesKt.to("topDisappear", MapBuilder.of("registrationName", "onDisappear")), TuplesKt.to("topHeaderHeightChange", MapBuilder.of("registrationName", "onHeaderHeightChange")), TuplesKt.to("topHeaderBackButtonClicked", MapBuilder.of("registrationName", "onHeaderBackButtonClicked")), TuplesKt.to("topTransitionProgress", MapBuilder.of("registrationName", "onTransitionProgress")), TuplesKt.to("topSheetDetentChanged", MapBuilder.of("registrationName", "onSheetDetentChanged")));
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<Screen> getDelegate() {
        return this.delegate;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
