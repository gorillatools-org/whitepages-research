package com.facebook.react.views.switchview;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.viewmanagers.AndroidSwitchManagerDelegate;
import com.facebook.react.viewmanagers.AndroidSwitchManagerInterface;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactSwitchManager extends BaseViewManager<ReactSwitch, ReactSwitchShadowNode> implements AndroidSwitchManagerInterface<ReactSwitch> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final CompoundButton.OnCheckedChangeListener ON_CHECKED_CHANGE_LISTENER = new ReactSwitchManager$$ExternalSyntheticLambda0();
    public static final String REACT_CLASS = "AndroidSwitch";
    private final ViewManagerDelegate<ReactSwitch> delegate = new AndroidSwitchManagerDelegate(this);

    public void updateExtraData(ReactSwitch reactSwitch, Object obj) {
        Intrinsics.checkNotNullParameter(reactSwitch, "root");
        Intrinsics.checkNotNullParameter(obj, "extraData");
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactSwitchShadowNode createShadowNodeInstance() {
        return new ReactSwitchShadowNode();
    }

    public Class<ReactSwitchShadowNode> getShadowNodeClass() {
        return ReactSwitchShadowNode.class;
    }

    /* access modifiers changed from: protected */
    public ReactSwitch createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        ReactSwitch reactSwitch = new ReactSwitch(themedReactContext);
        reactSwitch.setShowText(false);
        return reactSwitch;
    }

    public void setBackgroundColor(ReactSwitch reactSwitch, int i) {
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        reactSwitch.setBackgroundColor(i);
    }

    @ReactProp(defaultBoolean = false, name = "disabled")
    public void setDisabled(ReactSwitch reactSwitch, boolean z) {
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        reactSwitch.setEnabled(!z);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSwitch reactSwitch, boolean z) {
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        reactSwitch.setEnabled(z);
    }

    @ReactProp(name = "on")
    public void setOn(ReactSwitch reactSwitch, boolean z) {
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        setValueInternal(reactSwitch, z);
    }

    @ReactProp(name = "value")
    public void setValue(ReactSwitch reactSwitch, boolean z) {
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        setValueInternal(reactSwitch, z);
    }

    @ReactProp(customType = "Color", name = "thumbTintColor")
    public void setThumbTintColor(ReactSwitch reactSwitch, Integer num) {
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        setThumbColor(reactSwitch, num);
    }

    @ReactProp(customType = "Color", name = "thumbColor")
    public void setThumbColor(ReactSwitch reactSwitch, Integer num) {
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        reactSwitch.setThumbColor(num);
    }

    @ReactProp(customType = "Color", name = "trackColorForFalse")
    public void setTrackColorForFalse(ReactSwitch reactSwitch, Integer num) {
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        reactSwitch.setTrackColorForFalse(num);
    }

    @ReactProp(customType = "Color", name = "trackColorForTrue")
    public void setTrackColorForTrue(ReactSwitch reactSwitch, Integer num) {
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        reactSwitch.setTrackColorForTrue(num);
    }

    @ReactProp(customType = "Color", name = "trackTintColor")
    public void setTrackTintColor(ReactSwitch reactSwitch, Integer num) {
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        reactSwitch.setTrackColor(num);
    }

    public void setNativeValue(ReactSwitch reactSwitch, boolean z) {
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        setValueInternal(reactSwitch, z);
    }

    public void receiveCommand(ReactSwitch reactSwitch, String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        Intrinsics.checkNotNullParameter(str, "commandId");
        if (Intrinsics.areEqual((Object) str, (Object) "setNativeValue")) {
            boolean z = false;
            if (readableArray != null) {
                z = readableArray.getBoolean(0);
            }
            setValueInternal(reactSwitch, z);
        }
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactSwitch reactSwitch) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        Intrinsics.checkNotNullParameter(reactSwitch, "view");
        reactSwitch.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<ReactSwitch> getDelegate() {
        return this.delegate;
    }

    public long measure(Context context, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, float[] fArr) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(yogaMeasureMode, "widthMode");
        Intrinsics.checkNotNullParameter(yogaMeasureMode2, "heightMode");
        ReactSwitch reactSwitch = new ReactSwitch(context);
        reactSwitch.setShowText(false);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        reactSwitch.measure(makeMeasureSpec, makeMeasureSpec);
        return YogaMeasureOutput.make(PixelUtil.toDIPFromPixel((float) reactSwitch.getMeasuredWidth()), PixelUtil.toDIPFromPixel((float) reactSwitch.getMeasuredHeight()));
    }

    private final void setValueInternal(ReactSwitch reactSwitch, boolean z) {
        reactSwitch.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        reactSwitch.setOn(z);
        reactSwitch.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* access modifiers changed from: private */
    public static final void ON_CHECKED_CHANGE_LISTENER$lambda$2(CompoundButton compoundButton, boolean z) {
        Context context = compoundButton.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        ReactContext reactContext = (ReactContext) context;
        int id = compoundButton.getId();
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, id);
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new ReactSwitchEvent(UIManagerHelper.getSurfaceId((Context) reactContext), id, z));
        }
    }
}
