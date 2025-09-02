package com.facebook.react.views.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.DynamicFromObject;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.style.BackgroundImageLayer;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.LogicalEdge;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RCTView")
public class ReactViewManager extends ReactClippingViewManager<ReactViewGroup> {
    private static final int CMD_HOTSPOT_UPDATE = 1;
    private static final int CMD_SET_PRESSED = 2;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String HOTSPOT_UPDATE_KEY = "hotspotUpdate";
    public static final String REACT_CLASS = "RCTView";
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3, 4, 5, 9, 10, 11};

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Null     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.view.ReactViewManager.WhenMappings.<clinit>():void");
        }
    }

    @ReactProp(name = "collapsable")
    public void setCollapsable(ReactViewGroup reactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
    }

    @ReactProp(name = "collapsableChildren")
    public void setCollapsableChildren(ReactViewGroup reactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
    }

    public ReactViewManager() {
        setupViewRecycling();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* access modifiers changed from: protected */
    public ReactViewGroup prepareToRecycleView(ThemedReactContext themedReactContext, ReactViewGroup reactViewGroup) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        ReactViewGroup reactViewGroup2 = (ReactViewGroup) super.prepareToRecycleView(themedReactContext, reactViewGroup);
        if (reactViewGroup2 != null) {
            reactViewGroup2.recycleView();
        }
        return reactViewGroup;
    }

    @ReactProp(name = "accessible")
    public void setAccessible(ReactViewGroup reactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        reactViewGroup.setFocusable(z);
    }

    @ReactProp(name = "hasTVPreferredFocus")
    public void setTVPreferredFocus(ReactViewGroup reactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        if (z) {
            reactViewGroup.setFocusable(true);
            reactViewGroup.setFocusableInTouchMode(true);
            reactViewGroup.requestFocus();
        }
    }

    @ReactProp(customType = "BackgroundImage", name = "experimental_backgroundImage")
    public void setBackgroundImage(ReactViewGroup reactViewGroup, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        if (ViewUtil.getUIManagerType((View) reactViewGroup) != 2) {
            return;
        }
        if (readableArray == null || readableArray.size() <= 0) {
            BackgroundStyleApplicator.setBackgroundImage(reactViewGroup, (List<BackgroundImageLayer>) null);
            return;
        }
        ArrayList arrayList = new ArrayList(readableArray.size());
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            ReadableMap map = readableArray.getMap(i);
            Context context = reactViewGroup.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            arrayList.add(new BackgroundImageLayer(map, context));
        }
        BackgroundStyleApplicator.setBackgroundImage(reactViewGroup, arrayList);
    }

    @ReactProp(defaultInt = -1, name = "nextFocusDown")
    public void nextFocusDown(ReactViewGroup reactViewGroup, int i) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        reactViewGroup.setNextFocusDownId(i);
    }

    @ReactProp(defaultInt = -1, name = "nextFocusForward")
    public void nextFocusForward(ReactViewGroup reactViewGroup, int i) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        reactViewGroup.setNextFocusForwardId(i);
    }

    @ReactProp(defaultInt = -1, name = "nextFocusLeft")
    public void nextFocusLeft(ReactViewGroup reactViewGroup, int i) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        reactViewGroup.setNextFocusLeftId(i);
    }

    @ReactProp(defaultInt = -1, name = "nextFocusRight")
    public void nextFocusRight(ReactViewGroup reactViewGroup, int i) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        reactViewGroup.setNextFocusRightId(i);
    }

    @ReactProp(defaultInt = -1, name = "nextFocusUp")
    public void nextFocusUp(ReactViewGroup reactViewGroup, int i) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        reactViewGroup.setNextFocusUpId(i);
    }

    @ReactPropGroup(names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius", "borderTopStartRadius", "borderTopEndRadius", "borderBottomStartRadius", "borderBottomEndRadius", "borderEndEndRadius", "borderEndStartRadius", "borderStartEndRadius", "borderStartStartRadius"})
    public void setBorderRadius(ReactViewGroup reactViewGroup, int i, Dynamic dynamic) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        Intrinsics.checkNotNullParameter(dynamic, "rawBorderRadius");
        LengthPercentage fromDynamic = LengthPercentage.Companion.setFromDynamic(dynamic);
        if (!(ViewUtil.getUIManagerType((View) reactViewGroup) == 2 || fromDynamic == null || fromDynamic.getType() != LengthPercentageType.PERCENT)) {
            fromDynamic = null;
        }
        BackgroundStyleApplicator.setBorderRadius(reactViewGroup, BorderRadiusProp.values()[i], fromDynamic);
    }

    public void setBorderRadius(ReactViewGroup reactViewGroup, int i, float f) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        setBorderRadius(reactViewGroup, i, (Dynamic) new DynamicFromObject(Float.valueOf(f)));
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactViewGroup reactViewGroup, String str) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        BackgroundStyleApplicator.setBorderStyle(reactViewGroup, str == null ? null : BorderStyle.Companion.fromString(str));
    }

    @ReactProp(name = "hitSlop")
    public void setHitSlop(ReactViewGroup reactViewGroup, Dynamic dynamic) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        Intrinsics.checkNotNullParameter(dynamic, "hitSlop");
        int i = WhenMappings.$EnumSwitchMapping$0[dynamic.getType().ordinal()];
        if (i == 1) {
            ReadableMap asMap = dynamic.asMap();
            reactViewGroup.setHitSlopRect(new Rect(px(asMap, ViewProps.LEFT), px(asMap, ViewProps.TOP), px(asMap, ViewProps.RIGHT), px(asMap, ViewProps.BOTTOM)));
        } else if (i == 2) {
            int dpToPx = (int) PixelUtil.INSTANCE.dpToPx(dynamic.asDouble());
            reactViewGroup.setHitSlopRect(new Rect(dpToPx, dpToPx, dpToPx, dpToPx));
        } else if (i != 3) {
            ReadableType type = dynamic.getType();
            FLog.w(ReactConstants.TAG, "Invalid type for 'hitSlop' value " + type);
            reactViewGroup.setHitSlopRect((Rect) null);
        } else {
            reactViewGroup.setHitSlopRect((Rect) null);
        }
    }

    private final int px(ReadableMap readableMap, String str) {
        if (readableMap.hasKey(str)) {
            return (int) PixelUtil.INSTANCE.dpToPx(readableMap.getDouble(str));
        }
        return 0;
    }

    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(ReactViewGroup reactViewGroup, String str) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        reactViewGroup.setPointerEvents(PointerEvents.Companion.parsePointerEvents(str));
    }

    @ReactProp(name = "nativeBackgroundAndroid")
    public void setNativeBackground(ReactViewGroup reactViewGroup, ReadableMap readableMap) {
        Drawable drawable;
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        if (readableMap != null) {
            Context context = reactViewGroup.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            drawable = ReactDrawableHelper.createDrawableFromJSDescription(context, readableMap);
        } else {
            drawable = null;
        }
        BackgroundStyleApplicator.setFeedbackUnderlay(reactViewGroup, drawable);
    }

    @ReactProp(name = "nativeForegroundAndroid")
    public void setNativeForeground(ReactViewGroup reactViewGroup, ReadableMap readableMap) {
        Drawable drawable;
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        if (readableMap != null) {
            Context context = reactViewGroup.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            drawable = ReactDrawableHelper.createDrawableFromJSDescription(context, readableMap);
        } else {
            drawable = null;
        }
        reactViewGroup.setForeground(drawable);
    }

    @ReactProp(name = "needsOffscreenAlphaCompositing")
    public void setNeedsOffscreenAlphaCompositing(ReactViewGroup reactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        reactViewGroup.setNeedsOffscreenAlphaCompositing(z);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth", "borderStartWidth", "borderEndWidth"})
    public void setBorderWidth(ReactViewGroup reactViewGroup, int i, float f) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        BackgroundStyleApplicator.setBorderWidth(reactViewGroup, LogicalEdge.values()[i], Float.valueOf(f));
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor", "borderStartColor", "borderEndColor", "borderBlockColor", "borderBlockEndColor", "borderBlockStartColor"})
    public void setBorderColor(ReactViewGroup reactViewGroup, int i, Integer num) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        BackgroundStyleApplicator.setBorderColor(reactViewGroup, LogicalEdge.Companion.fromSpacingType(SPACING_TYPES[i]), num);
    }

    @ReactProp(name = "focusable")
    public void setFocusable(ReactViewGroup reactViewGroup, boolean z) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        if (z) {
            reactViewGroup.setOnClickListener(new ReactViewManager$$ExternalSyntheticLambda0(reactViewGroup));
            reactViewGroup.setFocusable(true);
            return;
        }
        reactViewGroup.setOnClickListener((View.OnClickListener) null);
        reactViewGroup.setClickable(false);
    }

    /* access modifiers changed from: private */
    public static final void setFocusable$lambda$2(ReactViewGroup reactViewGroup, View view) {
        Context context = reactViewGroup.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, reactViewGroup.getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new ViewGroupClickEvent(UIManagerHelper.getSurfaceId(reactViewGroup.getContext()), reactViewGroup.getId()));
        }
    }

    @ReactProp(name = "overflow")
    public void setOverflow(ReactViewGroup reactViewGroup, String str) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        reactViewGroup.setOverflow(str);
    }

    @ReactProp(name = "backfaceVisibility")
    public void setBackfaceVisibility(ReactViewGroup reactViewGroup, String str) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        Intrinsics.checkNotNullParameter(str, "backfaceVisibility");
        reactViewGroup.setBackfaceVisibility(str);
    }

    public void setOpacity(ReactViewGroup reactViewGroup, float f) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        reactViewGroup.setOpacityIfPossible(f);
    }

    /* access modifiers changed from: protected */
    public void setTransformProperty(ReactViewGroup reactViewGroup, ReadableArray readableArray, ReadableArray readableArray2) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "view");
        super.setTransformProperty(reactViewGroup, readableArray, readableArray2);
        reactViewGroup.setBackfaceVisibilityDependantOpacity();
    }

    public String getName() {
        return "RCTView";
    }

    public ReactViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        return new ReactViewGroup(themedReactContext);
    }

    public Map<String, Integer> getCommandsMap() {
        return MapsKt.mutableMapOf(TuplesKt.to(HOTSPOT_UPDATE_KEY, 1), TuplesKt.to("setPressed", 2));
    }

    public void receiveCommand(ReactViewGroup reactViewGroup, int i, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "root");
        if (i == 1) {
            handleHotspotUpdate(reactViewGroup, readableArray);
        } else if (i == 2) {
            handleSetPressed(reactViewGroup, readableArray);
        }
    }

    public void receiveCommand(ReactViewGroup reactViewGroup, String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(reactViewGroup, "root");
        Intrinsics.checkNotNullParameter(str, "commandId");
        if (Intrinsics.areEqual((Object) str, (Object) HOTSPOT_UPDATE_KEY)) {
            handleHotspotUpdate(reactViewGroup, readableArray);
        } else if (Intrinsics.areEqual((Object) str, (Object) "setPressed")) {
            handleSetPressed(reactViewGroup, readableArray);
        }
    }

    private final void handleSetPressed(ReactViewGroup reactViewGroup, ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() != 1) {
            throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'setPressed' command");
        }
        reactViewGroup.setPressed(readableArray.getBoolean(0));
    }

    private final void handleHotspotUpdate(ReactViewGroup reactViewGroup, ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() != 2) {
            throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'updateHotspot' command");
        }
        PixelUtil pixelUtil = PixelUtil.INSTANCE;
        reactViewGroup.drawableHotspotChanged(pixelUtil.dpToPx(readableArray.getDouble(0)), pixelUtil.dpToPx(readableArray.getDouble(1)));
    }
}
