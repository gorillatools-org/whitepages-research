package com.facebook.react.views.debuggingoverlay;

import android.graphics.RectF;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UnexpectedNativeTypeException;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.DebuggingOverlayManagerDelegate;
import com.facebook.react.viewmanagers.DebuggingOverlayManagerInterface;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "DebuggingOverlay")
public final class DebuggingOverlayManager extends SimpleViewManager<DebuggingOverlay> implements DebuggingOverlayManagerInterface<DebuggingOverlay> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "DebuggingOverlay";
    private final ViewManagerDelegate<DebuggingOverlay> delegate = new DebuggingOverlayManagerDelegate(this);

    public ViewManagerDelegate<DebuggingOverlay> getDelegate() {
        return this.delegate;
    }

    public void receiveCommand(DebuggingOverlay debuggingOverlay, String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(debuggingOverlay, "view");
        Intrinsics.checkNotNullParameter(str, "commandId");
        int hashCode = str.hashCode();
        if (hashCode != -1942063165) {
            if (hashCode != 1326903961) {
                if (hashCode == 1385348555 && str.equals("highlightElements")) {
                    highlightElements(debuggingOverlay, readableArray);
                    return;
                }
            } else if (str.equals("highlightTraceUpdates")) {
                highlightTraceUpdates(debuggingOverlay, readableArray);
                return;
            }
        } else if (str.equals("clearElementsHighlights")) {
            clearElementsHighlights(debuggingOverlay);
            return;
        }
        ReactSoftExceptionLogger.logSoftException(REACT_CLASS, new ReactNoCrashSoftException("Received unexpected command in DebuggingOverlayManager"));
    }

    public void highlightTraceUpdates(DebuggingOverlay debuggingOverlay, ReadableArray readableArray) {
        DebuggingOverlay debuggingOverlay2 = debuggingOverlay;
        ReadableArray readableArray2 = readableArray;
        Intrinsics.checkNotNullParameter(debuggingOverlay2, "view");
        if (readableArray2 != null) {
            boolean z = false;
            ReadableArray array = readableArray2.getArray(0);
            if (array != null) {
                ArrayList arrayList = new ArrayList();
                int size = array.size();
                int i = 0;
                boolean z2 = true;
                while (true) {
                    if (i >= size) {
                        z = z2;
                        break;
                    }
                    ReadableMap map = array.getMap(i);
                    if (map != null) {
                        ReadableMap map2 = map.getMap("rectangle");
                        if (map2 == null) {
                            ReactSoftExceptionLogger.logSoftException(REACT_CLASS, new ReactNoCrashSoftException("Unexpected payload for highlighting trace updates: rectangle field is null"));
                            break;
                        }
                        int i2 = map.getInt("id");
                        int i3 = map.getInt(ViewProps.COLOR);
                        try {
                            float f = (float) map2.getDouble("x");
                            float f2 = (float) map2.getDouble("y");
                            float f3 = (float) (((double) f) + map2.getDouble("width"));
                            float f4 = (float) (((double) f2) + map2.getDouble("height"));
                            PixelUtil pixelUtil = PixelUtil.INSTANCE;
                            arrayList.add(new TraceUpdate(i2, new RectF(pixelUtil.dpToPx(f), pixelUtil.dpToPx(f2), pixelUtil.dpToPx(f3), pixelUtil.dpToPx(f4)), i3));
                        } catch (Exception e) {
                            if ((e instanceof NoSuchKeyException) || (e instanceof UnexpectedNativeTypeException)) {
                                ReactSoftExceptionLogger.logSoftException(REACT_CLASS, new ReactNoCrashSoftException("Unexpected payload for highlighting trace updates: rectangle field should have x, y, width, height fields"));
                                Unit unit = Unit.INSTANCE;
                                z2 = false;
                            } else {
                                throw e;
                            }
                        }
                    }
                    i++;
                    z = false;
                }
                if (z) {
                    debuggingOverlay2.setTraceUpdates(arrayList);
                }
            }
        }
    }

    public void highlightElements(DebuggingOverlay debuggingOverlay, ReadableArray readableArray) {
        ReadableArray array;
        DebuggingOverlay debuggingOverlay2 = debuggingOverlay;
        ReadableArray readableArray2 = readableArray;
        Intrinsics.checkNotNullParameter(debuggingOverlay2, "view");
        if (readableArray2 != null && (array = readableArray2.getArray(0)) != null) {
            ArrayList arrayList = new ArrayList();
            int size = array.size();
            boolean z = true;
            for (int i = 0; i < size; i++) {
                ReadableMap map = array.getMap(i);
                if (map != null) {
                    try {
                        float f = (float) map.getDouble("x");
                        float f2 = (float) map.getDouble("y");
                        PixelUtil pixelUtil = PixelUtil.INSTANCE;
                        arrayList.add(new RectF(pixelUtil.dpToPx(f), pixelUtil.dpToPx(f2), pixelUtil.dpToPx((float) (((double) f) + map.getDouble("width"))), pixelUtil.dpToPx((float) (((double) f2) + map.getDouble("height")))));
                    } catch (Exception e) {
                        if ((e instanceof NoSuchKeyException) || (e instanceof UnexpectedNativeTypeException)) {
                            ReactSoftExceptionLogger.logSoftException(REACT_CLASS, new ReactNoCrashSoftException("Unexpected payload for highlighting elements: every element should have x, y, width, height fields"));
                            Unit unit = Unit.INSTANCE;
                            z = false;
                        } else {
                            throw e;
                        }
                    }
                }
            }
            if (z) {
                debuggingOverlay2.setHighlightedElementsRectangles(arrayList);
            }
        }
    }

    public void clearElementsHighlights(DebuggingOverlay debuggingOverlay) {
        Intrinsics.checkNotNullParameter(debuggingOverlay, "view");
        debuggingOverlay.clearElementsHighlights();
    }

    public DebuggingOverlay createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        return new DebuggingOverlay(themedReactContext);
    }

    public String getName() {
        return REACT_CLASS;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
