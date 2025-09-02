package com.facebook.react.views.drawer;

import android.view.View;
import androidx.drawerlayout.widget.DrawerLayout;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerDelegate;
import com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface;
import com.facebook.react.views.drawer.events.DrawerClosedEvent;
import com.facebook.react.views.drawer.events.DrawerOpenedEvent;
import com.facebook.react.views.drawer.events.DrawerSlideEvent;
import com.facebook.react.views.drawer.events.DrawerStateChangedEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "AndroidDrawerLayout")
public final class ReactDrawerLayoutManager extends ViewGroupManager<ReactDrawerLayout> implements AndroidDrawerLayoutManagerInterface<ReactDrawerLayout> {
    public static final int CLOSE_DRAWER = 2;
    public static final String COMMAND_CLOSE_DRAWER = "closeDrawer";
    public static final String COMMAND_OPEN_DRAWER = "openDrawer";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String DRAWER_POSITION = "DrawerPosition";
    private static final String DRAWER_POSITION_LEFT = "Left";
    private static final String DRAWER_POSITION_RIGHT = "Right";
    public static final int OPEN_DRAWER = 1;
    public static final String REACT_CLASS = "AndroidDrawerLayout";
    private final ViewManagerDelegate<ReactDrawerLayout> delegate = new AndroidDrawerLayoutManagerDelegate(this);

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @ReactProp(customType = "Color", name = "drawerBackgroundColor")
    public void setDrawerBackgroundColor(ReactDrawerLayout reactDrawerLayout, Integer num) {
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "view");
    }

    @ReactProp(name = "keyboardDismissMode")
    public void setKeyboardDismissMode(ReactDrawerLayout reactDrawerLayout, String str) {
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "view");
    }

    @ReactProp(customType = "Color", name = "statusBarBackgroundColor")
    public void setStatusBarBackgroundColor(ReactDrawerLayout reactDrawerLayout, Integer num) {
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "view");
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactDrawerLayout reactDrawerLayout) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "view");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, reactDrawerLayout.getId());
        if (eventDispatcherForReactTag != null) {
            reactDrawerLayout.addDrawerListener(new DrawerEventEmitter(reactDrawerLayout, eventDispatcherForReactTag));
        }
    }

    /* access modifiers changed from: protected */
    public ReactDrawerLayout createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        return new ReactDrawerLayout(themedReactContext);
    }

    public void setDrawerPosition(ReactDrawerLayout reactDrawerLayout, String str) {
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "view");
        if (str == null) {
            reactDrawerLayout.setDrawerPosition$ReactAndroid_release(8388611);
        } else {
            setDrawerPositionInternal(reactDrawerLayout, str);
        }
    }

    @ReactProp(name = "drawerPosition")
    public final void setDrawerPosition(ReactDrawerLayout reactDrawerLayout, Dynamic dynamic) {
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "view");
        Intrinsics.checkNotNullParameter(dynamic, "drawerPosition");
        if (dynamic.isNull()) {
            reactDrawerLayout.setDrawerPosition$ReactAndroid_release(8388611);
        } else if (dynamic.getType() == ReadableType.Number) {
            int asInt = dynamic.asInt();
            if (8388611 == asInt || 8388613 == asInt) {
                reactDrawerLayout.setDrawerPosition$ReactAndroid_release(asInt);
                return;
            }
            FLog.w(ReactConstants.TAG, "Unknown drawerPosition " + asInt);
            reactDrawerLayout.setDrawerPosition$ReactAndroid_release(8388611);
        } else if (dynamic.getType() == ReadableType.String) {
            setDrawerPositionInternal(reactDrawerLayout, dynamic.asString());
        } else {
            FLog.w(ReactConstants.TAG, "drawerPosition must be a string or int");
            reactDrawerLayout.setDrawerPosition$ReactAndroid_release(8388611);
        }
    }

    private final void setDrawerPositionInternal(ReactDrawerLayout reactDrawerLayout, String str) {
        if (Intrinsics.areEqual((Object) str, (Object) ViewProps.LEFT)) {
            reactDrawerLayout.setDrawerPosition$ReactAndroid_release(8388611);
        } else if (Intrinsics.areEqual((Object) str, (Object) ViewProps.RIGHT)) {
            reactDrawerLayout.setDrawerPosition$ReactAndroid_release(8388613);
        } else {
            FLog.w(ReactConstants.TAG, "drawerPosition must be 'left' or 'right', received" + str);
            reactDrawerLayout.setDrawerPosition$ReactAndroid_release(8388611);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "drawerWidth")
    public final void setDrawerWidth(ReactDrawerLayout reactDrawerLayout, float f) {
        int i;
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "view");
        if (Float.isNaN(f)) {
            i = -1;
        } else {
            i = Math.round(PixelUtil.INSTANCE.dpToPx(f));
        }
        reactDrawerLayout.setDrawerWidth$ReactAndroid_release(i);
    }

    public void setDrawerWidth(ReactDrawerLayout reactDrawerLayout, Float f) {
        int i;
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "view");
        if (f != null) {
            i = Math.round(PixelUtil.INSTANCE.dpToPx(f.floatValue()));
        } else {
            i = -1;
        }
        reactDrawerLayout.setDrawerWidth$ReactAndroid_release(i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        if (r5.equals("unlocked") != false) goto L_0x005a;
     */
    @com.facebook.react.uimanager.annotations.ReactProp(name = "drawerLockMode")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDrawerLockMode(com.facebook.react.views.drawer.ReactDrawerLayout r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 0
            if (r5 == 0) goto L_0x005a
            int r1 = r5.hashCode()
            r2 = -1292600945(0xffffffffb2f4798f, float:-2.8460617E-8)
            if (r1 == r2) goto L_0x0033
            r2 = -210949405(0xfffffffff36d2ae3, float:-1.8790347E31)
            if (r1 == r2) goto L_0x002a
            r2 = 168848173(0xa106b2d, float:6.953505E-33)
            if (r1 == r2) goto L_0x001c
            goto L_0x003b
        L_0x001c:
            java.lang.String r1 = "locked-open"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0025
            goto L_0x003b
        L_0x0025:
            r5 = 2
            r4.setDrawerLockMode(r5)
            goto L_0x005d
        L_0x002a:
            java.lang.String r1 = "unlocked"
            boolean r1 = r5.equals(r1)
            if (r1 == 0) goto L_0x003b
            goto L_0x005a
        L_0x0033:
            java.lang.String r1 = "locked-closed"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0055
        L_0x003b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown drawerLockMode "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            java.lang.String r1 = "ReactNative"
            com.facebook.common.logging.FLog.w((java.lang.String) r1, (java.lang.String) r5)
            r4.setDrawerLockMode(r0)
            goto L_0x005d
        L_0x0055:
            r5 = 1
            r4.setDrawerLockMode(r5)
            goto L_0x005d
        L_0x005a:
            r4.setDrawerLockMode(r0)
        L_0x005d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.drawer.ReactDrawerLayoutManager.setDrawerLockMode(com.facebook.react.views.drawer.ReactDrawerLayout, java.lang.String):void");
    }

    public void openDrawer(ReactDrawerLayout reactDrawerLayout) {
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "view");
        reactDrawerLayout.openDrawer$ReactAndroid_release();
    }

    public void closeDrawer(ReactDrawerLayout reactDrawerLayout) {
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "view");
        reactDrawerLayout.closeDrawer$ReactAndroid_release();
    }

    public void setElevation(ReactDrawerLayout reactDrawerLayout, float f) {
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "view");
        reactDrawerLayout.setDrawerElevation(PixelUtil.INSTANCE.dpToPx(f));
    }

    public Map<String, Integer> getCommandsMap() {
        return MapsKt.mapOf(TuplesKt.to(COMMAND_OPEN_DRAWER, 1), TuplesKt.to(COMMAND_CLOSE_DRAWER, 2));
    }

    public void receiveCommand(ReactDrawerLayout reactDrawerLayout, int i, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "root");
        if (i == 1) {
            reactDrawerLayout.openDrawer$ReactAndroid_release();
        } else if (i == 2) {
            reactDrawerLayout.closeDrawer$ReactAndroid_release();
        }
    }

    public void receiveCommand(ReactDrawerLayout reactDrawerLayout, String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "root");
        Intrinsics.checkNotNullParameter(str, "commandId");
        if (Intrinsics.areEqual((Object) str, (Object) COMMAND_OPEN_DRAWER)) {
            reactDrawerLayout.openDrawer$ReactAndroid_release();
        } else if (Intrinsics.areEqual((Object) str, (Object) COMMAND_CLOSE_DRAWER)) {
            reactDrawerLayout.closeDrawer$ReactAndroid_release();
        }
    }

    public Map<String, Object> getExportedViewConstants() {
        return MapsKt.mapOf(TuplesKt.to(DRAWER_POSITION, MapsKt.mapOf(TuplesKt.to(DRAWER_POSITION_LEFT, 8388611), TuplesKt.to(DRAWER_POSITION_RIGHT, 8388613))));
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new LinkedHashMap<>();
        }
        exportedCustomDirectEventTypeConstants.put(DrawerSlideEvent.EVENT_NAME, MapsKt.mapOf(TuplesKt.to("registrationName", "onDrawerSlide")));
        exportedCustomDirectEventTypeConstants.put(DrawerOpenedEvent.EVENT_NAME, MapsKt.mapOf(TuplesKt.to("registrationName", "onDrawerOpen")));
        exportedCustomDirectEventTypeConstants.put(DrawerClosedEvent.EVENT_NAME, MapsKt.mapOf(TuplesKt.to("registrationName", "onDrawerClose")));
        exportedCustomDirectEventTypeConstants.put(DrawerStateChangedEvent.EVENT_NAME, MapsKt.mapOf(TuplesKt.to("registrationName", "onDrawerStateChanged")));
        return exportedCustomDirectEventTypeConstants;
    }

    public void addView(ReactDrawerLayout reactDrawerLayout, View view, int i) {
        Intrinsics.checkNotNullParameter(reactDrawerLayout, "parent");
        Intrinsics.checkNotNullParameter(view, "child");
        if (getChildCount(reactDrawerLayout) >= 2) {
            throw new JSApplicationIllegalArgumentException("The Drawer cannot have more than two children");
        } else if (i == 0 || i == 1) {
            reactDrawerLayout.addView(view, i);
            reactDrawerLayout.setDrawerProperties$ReactAndroid_release();
        } else {
            throw new JSApplicationIllegalArgumentException("The only valid indices for drawer's child are 0 or 1. Got " + i + " instead.");
        }
    }

    public ViewManagerDelegate<ReactDrawerLayout> getDelegate() {
        return this.delegate;
    }

    public static final class DrawerEventEmitter implements DrawerLayout.DrawerListener {
        private final DrawerLayout drawerLayout;
        private final EventDispatcher eventDispatcher;

        public DrawerEventEmitter(DrawerLayout drawerLayout2, EventDispatcher eventDispatcher2) {
            Intrinsics.checkNotNullParameter(drawerLayout2, "drawerLayout");
            Intrinsics.checkNotNullParameter(eventDispatcher2, "eventDispatcher");
            this.drawerLayout = drawerLayout2;
            this.eventDispatcher = eventDispatcher2;
        }

        public void onDrawerSlide(View view, float f) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.eventDispatcher.dispatchEvent(new DrawerSlideEvent(UIManagerHelper.getSurfaceId((View) this.drawerLayout), this.drawerLayout.getId(), f));
        }

        public void onDrawerOpened(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.eventDispatcher.dispatchEvent(new DrawerOpenedEvent(UIManagerHelper.getSurfaceId((View) this.drawerLayout), this.drawerLayout.getId()));
        }

        public void onDrawerClosed(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.eventDispatcher.dispatchEvent(new DrawerClosedEvent(UIManagerHelper.getSurfaceId((View) this.drawerLayout), this.drawerLayout.getId()));
        }

        public void onDrawerStateChanged(int i) {
            this.eventDispatcher.dispatchEvent(new DrawerStateChangedEvent(UIManagerHelper.getSurfaceId((View) this.drawerLayout), this.drawerLayout.getId(), i));
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
