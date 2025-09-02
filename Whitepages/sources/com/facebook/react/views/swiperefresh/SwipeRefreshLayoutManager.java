package com.facebook.react.views.swiperefresh;

import android.view.View;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.viewmanagers.AndroidSwipeRefreshLayoutManagerDelegate;
import com.facebook.react.viewmanagers.AndroidSwipeRefreshLayoutManagerInterface;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "AndroidSwipeRefreshLayout")
public class SwipeRefreshLayoutManager extends ViewGroupManager<ReactSwipeRefreshLayout> implements AndroidSwipeRefreshLayoutManagerInterface<ReactSwipeRefreshLayout> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "AndroidSwipeRefreshLayout";
    private final ViewManagerDelegate<ReactSwipeRefreshLayout> delegate = new AndroidSwipeRefreshLayoutManagerDelegate(this);

    /* access modifiers changed from: protected */
    public ReactSwipeRefreshLayout createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return new ReactSwipeRefreshLayout(themedReactContext);
    }

    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactSwipeRefreshLayout reactSwipeRefreshLayout, boolean z) {
        Intrinsics.checkNotNullParameter(reactSwipeRefreshLayout, "view");
        reactSwipeRefreshLayout.setEnabled(z);
    }

    @ReactProp(customType = "ColorArray", name = "colors")
    public void setColors(ReactSwipeRefreshLayout reactSwipeRefreshLayout, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(reactSwipeRefreshLayout, "view");
        if (readableArray != null) {
            int size = readableArray.size();
            int[] iArr = new int[size];
            int size2 = readableArray.size();
            for (int i = 0; i < size2; i++) {
                if (readableArray.getType(i) == ReadableType.Map) {
                    Integer color = ColorPropConverter.getColor(readableArray.getMap(i), reactSwipeRefreshLayout.getContext());
                    Intrinsics.checkNotNullExpressionValue(color, "getColor(...)");
                    iArr[i] = color.intValue();
                } else {
                    iArr[i] = readableArray.getInt(i);
                }
            }
            reactSwipeRefreshLayout.setColorSchemeColors(Arrays.copyOf(iArr, size));
            return;
        }
        reactSwipeRefreshLayout.setColorSchemeColors(new int[0]);
    }

    @ReactProp(customType = "Color", name = "progressBackgroundColor")
    public void setProgressBackgroundColor(ReactSwipeRefreshLayout reactSwipeRefreshLayout, Integer num) {
        Intrinsics.checkNotNullParameter(reactSwipeRefreshLayout, "view");
        reactSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(num != null ? num.intValue() : 0);
    }

    public final void setSize(ReactSwipeRefreshLayout reactSwipeRefreshLayout, int i) {
        Intrinsics.checkNotNullParameter(reactSwipeRefreshLayout, "view");
        reactSwipeRefreshLayout.setSize(i);
    }

    public void setSize(ReactSwipeRefreshLayout reactSwipeRefreshLayout, String str) {
        Intrinsics.checkNotNullParameter(reactSwipeRefreshLayout, "view");
        if (str == null || str.equals(Constants.COLLATION_DEFAULT)) {
            reactSwipeRefreshLayout.setSize(1);
        } else if (str.equals("large")) {
            reactSwipeRefreshLayout.setSize(0);
        } else {
            throw new IllegalArgumentException("Size must be 'default' or 'large', received: " + str);
        }
    }

    @ReactProp(name = "size")
    public final void setSize(ReactSwipeRefreshLayout reactSwipeRefreshLayout, Dynamic dynamic) {
        Intrinsics.checkNotNullParameter(reactSwipeRefreshLayout, "view");
        Intrinsics.checkNotNullParameter(dynamic, "size");
        if (dynamic.isNull()) {
            reactSwipeRefreshLayout.setSize(1);
        } else if (dynamic.getType() == ReadableType.Number) {
            reactSwipeRefreshLayout.setSize(dynamic.asInt());
        } else if (dynamic.getType() == ReadableType.String) {
            setSize(reactSwipeRefreshLayout, dynamic.asString());
        } else {
            throw new IllegalArgumentException("Size must be 'default' or 'large'");
        }
    }

    @ReactProp(name = "refreshing")
    public void setRefreshing(ReactSwipeRefreshLayout reactSwipeRefreshLayout, boolean z) {
        Intrinsics.checkNotNullParameter(reactSwipeRefreshLayout, "view");
        reactSwipeRefreshLayout.setRefreshing(z);
    }

    @ReactProp(defaultFloat = 0.0f, name = "progressViewOffset")
    public void setProgressViewOffset(ReactSwipeRefreshLayout reactSwipeRefreshLayout, float f) {
        Intrinsics.checkNotNullParameter(reactSwipeRefreshLayout, "view");
        reactSwipeRefreshLayout.setProgressViewOffset(f);
    }

    public void setNativeRefreshing(ReactSwipeRefreshLayout reactSwipeRefreshLayout, boolean z) {
        Intrinsics.checkNotNullParameter(reactSwipeRefreshLayout, "view");
        setRefreshing(reactSwipeRefreshLayout, z);
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactSwipeRefreshLayout reactSwipeRefreshLayout) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        Intrinsics.checkNotNullParameter(reactSwipeRefreshLayout, "view");
        reactSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayoutManager$$ExternalSyntheticLambda0(themedReactContext, reactSwipeRefreshLayout));
    }

    /* access modifiers changed from: private */
    public static final void addEventEmitters$lambda$0(ThemedReactContext themedReactContext, ReactSwipeRefreshLayout reactSwipeRefreshLayout) {
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, reactSwipeRefreshLayout.getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new RefreshEvent(UIManagerHelper.getSurfaceId((View) reactSwipeRefreshLayout), reactSwipeRefreshLayout.getId()));
        }
    }

    public void receiveCommand(ReactSwipeRefreshLayout reactSwipeRefreshLayout, String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(reactSwipeRefreshLayout, "root");
        Intrinsics.checkNotNullParameter(str, "commandId");
        if (Intrinsics.areEqual((Object) str, (Object) "setNativeRefreshing") && readableArray != null) {
            setRefreshing(reactSwipeRefreshLayout, readableArray.getBoolean(0));
        }
    }

    public Map<String, Object> getExportedViewConstants() {
        return MapsKt.mutableMapOf(TuplesKt.to("SIZE", MapsKt.mutableMapOf(TuplesKt.to("DEFAULT", 1), TuplesKt.to("LARGE", 0))));
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap<>();
        }
        exportedCustomDirectEventTypeConstants.putAll(MapsKt.mutableMapOf(TuplesKt.to("topRefresh", MapsKt.mutableMapOf(TuplesKt.to("registrationName", "onRefresh")))));
        return exportedCustomDirectEventTypeConstants;
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<ReactSwipeRefreshLayout> getDelegate() {
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
