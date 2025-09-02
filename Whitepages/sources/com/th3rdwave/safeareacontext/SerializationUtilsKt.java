package com.th3rdwave.safeareacontext;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

public abstract class SerializationUtilsKt {
    public static final WritableMap edgeInsetsToJsMap(EdgeInsets edgeInsets) {
        Intrinsics.checkNotNullParameter(edgeInsets, "insets");
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(ViewProps.TOP, (double) PixelUtil.toDIPFromPixel(edgeInsets.getTop()));
        createMap.putDouble(ViewProps.RIGHT, (double) PixelUtil.toDIPFromPixel(edgeInsets.getRight()));
        createMap.putDouble(ViewProps.BOTTOM, (double) PixelUtil.toDIPFromPixel(edgeInsets.getBottom()));
        createMap.putDouble(ViewProps.LEFT, (double) PixelUtil.toDIPFromPixel(edgeInsets.getLeft()));
        Intrinsics.checkNotNull(createMap);
        return createMap;
    }

    public static final Map edgeInsetsToJavaMap(EdgeInsets edgeInsets) {
        Intrinsics.checkNotNullParameter(edgeInsets, "insets");
        return MapsKt.mapOf(TuplesKt.to(ViewProps.TOP, Float.valueOf(PixelUtil.toDIPFromPixel(edgeInsets.getTop()))), TuplesKt.to(ViewProps.RIGHT, Float.valueOf(PixelUtil.toDIPFromPixel(edgeInsets.getRight()))), TuplesKt.to(ViewProps.BOTTOM, Float.valueOf(PixelUtil.toDIPFromPixel(edgeInsets.getBottom()))), TuplesKt.to(ViewProps.LEFT, Float.valueOf(PixelUtil.toDIPFromPixel(edgeInsets.getLeft()))));
    }

    public static final WritableMap rectToJsMap(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("x", (double) PixelUtil.toDIPFromPixel(rect.getX()));
        createMap.putDouble("y", (double) PixelUtil.toDIPFromPixel(rect.getY()));
        createMap.putDouble("width", (double) PixelUtil.toDIPFromPixel(rect.getWidth()));
        createMap.putDouble("height", (double) PixelUtil.toDIPFromPixel(rect.getHeight()));
        Intrinsics.checkNotNull(createMap);
        return createMap;
    }

    public static final Map rectToJavaMap(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        return MapsKt.mapOf(TuplesKt.to("x", Float.valueOf(PixelUtil.toDIPFromPixel(rect.getX()))), TuplesKt.to("y", Float.valueOf(PixelUtil.toDIPFromPixel(rect.getY()))), TuplesKt.to("width", Float.valueOf(PixelUtil.toDIPFromPixel(rect.getWidth()))), TuplesKt.to("height", Float.valueOf(PixelUtil.toDIPFromPixel(rect.getHeight()))));
    }
}
