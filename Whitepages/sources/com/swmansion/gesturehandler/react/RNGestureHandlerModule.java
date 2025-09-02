package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.util.Log;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.events.Event;
import com.facebook.soloader.SoLoader;
import com.swmansion.gesturehandler.NativeRNGestureHandlerModuleSpec;
import com.swmansion.gesturehandler.ReactContextExtensionsKt;
import com.swmansion.gesturehandler.ReanimatedEventDispatcher;
import com.swmansion.gesturehandler.core.FlingGestureHandler;
import com.swmansion.gesturehandler.core.GestureHandler;
import com.swmansion.gesturehandler.core.HoverGestureHandler;
import com.swmansion.gesturehandler.core.LongPressGestureHandler;
import com.swmansion.gesturehandler.core.ManualGestureHandler;
import com.swmansion.gesturehandler.core.NativeViewGestureHandler;
import com.swmansion.gesturehandler.core.PanGestureHandler;
import com.swmansion.gesturehandler.core.PinchGestureHandler;
import com.swmansion.gesturehandler.core.RotationGestureHandler;
import com.swmansion.gesturehandler.core.TapGestureHandler;
import com.swmansion.gesturehandler.react.RNGestureHandlerEvent;
import com.swmansion.gesturehandler.react.eventbuilders.FlingGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.GestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.HoverGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.LongPressGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.ManualGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.NativeGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.PanGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.PinchGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.RotationGestureHandlerEventDataBuilder;
import com.swmansion.gesturehandler.react.eventbuilders.TapGestureHandlerEventDataBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RNGestureHandlerModule")
public final class RNGestureHandlerModule extends NativeRNGestureHandlerModuleSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String KEY_DIRECTION = "direction";
    private static final String KEY_ENABLED = "enabled";
    private static final String KEY_HIT_SLOP = "hitSlop";
    private static final String KEY_HIT_SLOP_BOTTOM = "bottom";
    private static final String KEY_HIT_SLOP_HEIGHT = "height";
    private static final String KEY_HIT_SLOP_HORIZONTAL = "horizontal";
    private static final String KEY_HIT_SLOP_LEFT = "left";
    private static final String KEY_HIT_SLOP_RIGHT = "right";
    private static final String KEY_HIT_SLOP_TOP = "top";
    private static final String KEY_HIT_SLOP_VERTICAL = "vertical";
    private static final String KEY_HIT_SLOP_WIDTH = "width";
    private static final String KEY_LONG_PRESS_MAX_DIST = "maxDist";
    private static final String KEY_LONG_PRESS_MIN_DURATION_MS = "minDurationMs";
    private static final String KEY_MANUAL_ACTIVATION = "manualActivation";
    private static final String KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION = "disallowInterruption";
    private static final String KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START = "shouldActivateOnStart";
    private static final String KEY_NEEDS_POINTER_DATA = "needsPointerData";
    private static final String KEY_NUMBER_OF_POINTERS = "numberOfPointers";
    private static final String KEY_PAN_ACTIVATE_AFTER_LONG_PRESS = "activateAfterLongPress";
    private static final String KEY_PAN_ACTIVE_OFFSET_X_END = "activeOffsetXEnd";
    private static final String KEY_PAN_ACTIVE_OFFSET_X_START = "activeOffsetXStart";
    private static final String KEY_PAN_ACTIVE_OFFSET_Y_END = "activeOffsetYEnd";
    private static final String KEY_PAN_ACTIVE_OFFSET_Y_START = "activeOffsetYStart";
    private static final String KEY_PAN_AVG_TOUCHES = "avgTouches";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_X_END = "failOffsetXEnd";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_X_START = "failOffsetXStart";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_Y_END = "failOffsetYEnd";
    private static final String KEY_PAN_FAIL_OFFSET_RANGE_Y_START = "failOffsetYStart";
    private static final String KEY_PAN_MAX_POINTERS = "maxPointers";
    private static final String KEY_PAN_MIN_DIST = "minDist";
    private static final String KEY_PAN_MIN_POINTERS = "minPointers";
    private static final String KEY_PAN_MIN_VELOCITY = "minVelocity";
    private static final String KEY_PAN_MIN_VELOCITY_X = "minVelocityX";
    private static final String KEY_PAN_MIN_VELOCITY_Y = "minVelocityY";
    private static final String KEY_SHOULD_CANCEL_WHEN_OUTSIDE = "shouldCancelWhenOutside";
    private static final String KEY_TAP_MAX_DELAY_MS = "maxDelayMs";
    private static final String KEY_TAP_MAX_DELTA_X = "maxDeltaX";
    private static final String KEY_TAP_MAX_DELTA_Y = "maxDeltaY";
    private static final String KEY_TAP_MAX_DIST = "maxDist";
    private static final String KEY_TAP_MAX_DURATION_MS = "maxDurationMs";
    private static final String KEY_TAP_MIN_POINTERS = "minPointers";
    private static final String KEY_TAP_NUMBER_OF_TAPS = "numberOfTaps";
    public static final String NAME = "RNGestureHandlerModule";
    private final RNGestureHandlerModule$eventListener$1 eventListener = new RNGestureHandlerModule$eventListener$1(this);
    private final HandlerFactory[] handlerFactories = {new NativeViewGestureHandlerFactory(), new TapGestureHandlerFactory(), new LongPressGestureHandlerFactory(), new PanGestureHandlerFactory(), new PinchGestureHandlerFactory(), new RotationGestureHandlerFactory(), new FlingGestureHandlerFactory(), new ManualGestureHandlerFactory(), new HoverGestureHandlerFactory()};
    private final RNGestureHandlerInteractionManager interactionManager = new RNGestureHandlerInteractionManager();
    private final ReanimatedEventDispatcher reanimatedEventDispatcher = new ReanimatedEventDispatcher();
    private final RNGestureHandlerRegistry registry = new RNGestureHandlerRegistry();
    private final List<RNGestureHandlerRootHelper> roots = new ArrayList();

    private final native void decorateRuntime(long j);

    @ReactMethod
    public void flushOperations() {
    }

    @ReactMethod
    public void handleClearJSResponder() {
    }

    public RNGestureHandlerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private static abstract class HandlerFactory {
        public abstract GestureHandler create(Context context);

        public abstract GestureHandlerEventDataBuilder createEventBuilder(GestureHandler gestureHandler);

        public abstract String getName();

        public abstract Class getType();

        public void configure(GestureHandler gestureHandler, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(gestureHandler, "handler");
            Intrinsics.checkNotNullParameter(readableMap, "config");
            gestureHandler.resetConfig();
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_SHOULD_CANCEL_WHEN_OUTSIDE)) {
                gestureHandler.setShouldCancelWhenOutside(readableMap.getBoolean(RNGestureHandlerModule.KEY_SHOULD_CANCEL_WHEN_OUTSIDE));
            }
            if (readableMap.hasKey("enabled")) {
                gestureHandler.setEnabled(readableMap.getBoolean("enabled"));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP)) {
                RNGestureHandlerModule.Companion.handleHitSlopProperty(gestureHandler, readableMap);
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NEEDS_POINTER_DATA)) {
                gestureHandler.setNeedsPointerData(readableMap.getBoolean(RNGestureHandlerModule.KEY_NEEDS_POINTER_DATA));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_MANUAL_ACTIVATION)) {
                gestureHandler.setManualActivation(readableMap.getBoolean(RNGestureHandlerModule.KEY_MANUAL_ACTIVATION));
            }
            if (readableMap.hasKey("mouseButton")) {
                gestureHandler.setMouseButton(readableMap.getInt("mouseButton"));
            }
        }
    }

    private static final class NativeViewGestureHandlerFactory extends HandlerFactory {
        private final String name = "NativeViewGestureHandler";
        private final Class type = NativeViewGestureHandler.class;

        public Class getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public NativeViewGestureHandler create(Context context) {
            return new NativeViewGestureHandler();
        }

        public void configure(NativeViewGestureHandler nativeViewGestureHandler, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(nativeViewGestureHandler, "handler");
            Intrinsics.checkNotNullParameter(readableMap, "config");
            super.configure(nativeViewGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START)) {
                nativeViewGestureHandler.setShouldActivateOnStart(readableMap.getBoolean(RNGestureHandlerModule.KEY_NATIVE_VIEW_SHOULD_ACTIVATE_ON_START));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION)) {
                nativeViewGestureHandler.setDisallowInterruption(readableMap.getBoolean(RNGestureHandlerModule.KEY_NATIVE_VIEW_DISALLOW_INTERRUPTION));
            }
        }

        public NativeGestureHandlerEventDataBuilder createEventBuilder(NativeViewGestureHandler nativeViewGestureHandler) {
            Intrinsics.checkNotNullParameter(nativeViewGestureHandler, "handler");
            return new NativeGestureHandlerEventDataBuilder(nativeViewGestureHandler);
        }
    }

    private static final class TapGestureHandlerFactory extends HandlerFactory {
        private final String name = "TapGestureHandler";
        private final Class type = TapGestureHandler.class;

        public Class getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public TapGestureHandler create(Context context) {
            return new TapGestureHandler();
        }

        public void configure(TapGestureHandler tapGestureHandler, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(tapGestureHandler, "handler");
            Intrinsics.checkNotNullParameter(readableMap, "config");
            super.configure(tapGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_NUMBER_OF_TAPS)) {
                tapGestureHandler.setNumberOfTaps(readableMap.getInt(RNGestureHandlerModule.KEY_TAP_NUMBER_OF_TAPS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DURATION_MS)) {
                tapGestureHandler.setMaxDurationMs((long) readableMap.getInt(RNGestureHandlerModule.KEY_TAP_MAX_DURATION_MS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELAY_MS)) {
                tapGestureHandler.setMaxDelayMs((long) readableMap.getInt(RNGestureHandlerModule.KEY_TAP_MAX_DELAY_MS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_X)) {
                tapGestureHandler.setMaxDx(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_X)));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_Y)) {
                tapGestureHandler.setMaxDy(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_TAP_MAX_DELTA_Y)));
            }
            if (readableMap.hasKey("maxDist")) {
                tapGestureHandler.setMaxDist(PixelUtil.toPixelFromDIP(readableMap.getDouble("maxDist")));
            }
            if (readableMap.hasKey("minPointers")) {
                tapGestureHandler.setMinNumberOfPointers(readableMap.getInt("minPointers"));
            }
        }

        public TapGestureHandlerEventDataBuilder createEventBuilder(TapGestureHandler tapGestureHandler) {
            Intrinsics.checkNotNullParameter(tapGestureHandler, "handler");
            return new TapGestureHandlerEventDataBuilder(tapGestureHandler);
        }
    }

    private static final class LongPressGestureHandlerFactory extends HandlerFactory {
        private final String name = "LongPressGestureHandler";
        private final Class type = LongPressGestureHandler.class;

        public Class getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public LongPressGestureHandler create(Context context) {
            Intrinsics.checkNotNull(context);
            return new LongPressGestureHandler(context);
        }

        public void configure(LongPressGestureHandler longPressGestureHandler, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(longPressGestureHandler, "handler");
            Intrinsics.checkNotNullParameter(readableMap, "config");
            super.configure(longPressGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_LONG_PRESS_MIN_DURATION_MS)) {
                longPressGestureHandler.setMinDurationMs((long) readableMap.getInt(RNGestureHandlerModule.KEY_LONG_PRESS_MIN_DURATION_MS));
            }
            if (readableMap.hasKey("maxDist")) {
                longPressGestureHandler.setMaxDist(PixelUtil.toPixelFromDIP(readableMap.getDouble("maxDist")));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS)) {
                longPressGestureHandler.setNumberOfPointers(readableMap.getInt(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS));
            }
        }

        public LongPressGestureHandlerEventDataBuilder createEventBuilder(LongPressGestureHandler longPressGestureHandler) {
            Intrinsics.checkNotNullParameter(longPressGestureHandler, "handler");
            return new LongPressGestureHandlerEventDataBuilder(longPressGestureHandler);
        }
    }

    private static final class PanGestureHandlerFactory extends HandlerFactory {
        private final String name = "PanGestureHandler";
        private final Class type = PanGestureHandler.class;

        public Class getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public PanGestureHandler create(Context context) {
            return new PanGestureHandler(context);
        }

        public void configure(PanGestureHandler panGestureHandler, ReadableMap readableMap) {
            boolean z;
            Intrinsics.checkNotNullParameter(panGestureHandler, "handler");
            Intrinsics.checkNotNullParameter(readableMap, "config");
            super.configure(panGestureHandler, readableMap);
            boolean z2 = true;
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_START)) {
                panGestureHandler.setActiveOffsetXStart(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_START)));
                z = true;
            } else {
                z = false;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_END)) {
                panGestureHandler.setActiveOffsetXEnd(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_X_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_START)) {
                panGestureHandler.setFailOffsetXStart(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_START)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_END)) {
                panGestureHandler.setFailOffsetXEnd(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_X_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_START)) {
                panGestureHandler.setActiveOffsetYStart(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_START)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_END)) {
                panGestureHandler.setActiveOffsetYEnd(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_ACTIVE_OFFSET_Y_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_START)) {
                panGestureHandler.setFailOffsetYStart(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_START)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_END)) {
                panGestureHandler.setFailOffsetYEnd(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_FAIL_OFFSET_RANGE_Y_END)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY)) {
                panGestureHandler.setMinVelocity(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_X)) {
                panGestureHandler.setMinVelocityX(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_X)));
                z = true;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_Y)) {
                panGestureHandler.setMinVelocityY(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_VELOCITY_Y)));
            } else {
                z2 = z;
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MIN_DIST)) {
                panGestureHandler.setMinDist(PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_PAN_MIN_DIST)));
            } else if (z2) {
                panGestureHandler.setMinDist(Float.MAX_VALUE);
            }
            if (readableMap.hasKey("minPointers")) {
                panGestureHandler.setMinPointers(readableMap.getInt("minPointers"));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_MAX_POINTERS)) {
                panGestureHandler.setMaxPointers(readableMap.getInt(RNGestureHandlerModule.KEY_PAN_MAX_POINTERS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_AVG_TOUCHES)) {
                panGestureHandler.setAverageTouches(readableMap.getBoolean(RNGestureHandlerModule.KEY_PAN_AVG_TOUCHES));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_PAN_ACTIVATE_AFTER_LONG_PRESS)) {
                panGestureHandler.setActivateAfterLongPress((long) readableMap.getInt(RNGestureHandlerModule.KEY_PAN_ACTIVATE_AFTER_LONG_PRESS));
            }
        }

        public PanGestureHandlerEventDataBuilder createEventBuilder(PanGestureHandler panGestureHandler) {
            Intrinsics.checkNotNullParameter(panGestureHandler, "handler");
            return new PanGestureHandlerEventDataBuilder(panGestureHandler);
        }
    }

    private static final class PinchGestureHandlerFactory extends HandlerFactory {
        private final String name = "PinchGestureHandler";
        private final Class type = PinchGestureHandler.class;

        public Class getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public PinchGestureHandler create(Context context) {
            return new PinchGestureHandler();
        }

        public PinchGestureHandlerEventDataBuilder createEventBuilder(PinchGestureHandler pinchGestureHandler) {
            Intrinsics.checkNotNullParameter(pinchGestureHandler, "handler");
            return new PinchGestureHandlerEventDataBuilder(pinchGestureHandler);
        }
    }

    private static final class FlingGestureHandlerFactory extends HandlerFactory {
        private final String name = "FlingGestureHandler";
        private final Class type = FlingGestureHandler.class;

        public Class getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public FlingGestureHandler create(Context context) {
            return new FlingGestureHandler();
        }

        public void configure(FlingGestureHandler flingGestureHandler, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(flingGestureHandler, "handler");
            Intrinsics.checkNotNullParameter(readableMap, "config");
            super.configure(flingGestureHandler, readableMap);
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS)) {
                flingGestureHandler.setNumberOfPointersRequired(readableMap.getInt(RNGestureHandlerModule.KEY_NUMBER_OF_POINTERS));
            }
            if (readableMap.hasKey(RNGestureHandlerModule.KEY_DIRECTION)) {
                flingGestureHandler.setDirection(readableMap.getInt(RNGestureHandlerModule.KEY_DIRECTION));
            }
        }

        public FlingGestureHandlerEventDataBuilder createEventBuilder(FlingGestureHandler flingGestureHandler) {
            Intrinsics.checkNotNullParameter(flingGestureHandler, "handler");
            return new FlingGestureHandlerEventDataBuilder(flingGestureHandler);
        }
    }

    private static final class RotationGestureHandlerFactory extends HandlerFactory {
        private final String name = "RotationGestureHandler";
        private final Class type = RotationGestureHandler.class;

        public Class getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public RotationGestureHandler create(Context context) {
            return new RotationGestureHandler();
        }

        public RotationGestureHandlerEventDataBuilder createEventBuilder(RotationGestureHandler rotationGestureHandler) {
            Intrinsics.checkNotNullParameter(rotationGestureHandler, "handler");
            return new RotationGestureHandlerEventDataBuilder(rotationGestureHandler);
        }
    }

    private static final class ManualGestureHandlerFactory extends HandlerFactory {
        private final String name = "ManualGestureHandler";
        private final Class type = ManualGestureHandler.class;

        public Class getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public ManualGestureHandler create(Context context) {
            return new ManualGestureHandler();
        }

        public ManualGestureHandlerEventDataBuilder createEventBuilder(ManualGestureHandler manualGestureHandler) {
            Intrinsics.checkNotNullParameter(manualGestureHandler, "handler");
            return new ManualGestureHandlerEventDataBuilder(manualGestureHandler);
        }
    }

    private static final class HoverGestureHandlerFactory extends HandlerFactory {
        private final String name = "HoverGestureHandler";
        private final Class type = HoverGestureHandler.class;

        public Class getType() {
            return this.type;
        }

        public String getName() {
            return this.name;
        }

        public HoverGestureHandler create(Context context) {
            return new HoverGestureHandler();
        }

        public HoverGestureHandlerEventDataBuilder createEventBuilder(HoverGestureHandler hoverGestureHandler) {
            Intrinsics.checkNotNullParameter(hoverGestureHandler, "handler");
            return new HoverGestureHandlerEventDataBuilder(hoverGestureHandler);
        }
    }

    public final RNGestureHandlerRegistry getRegistry() {
        return this.registry;
    }

    public String getName() {
        return "RNGestureHandlerModule";
    }

    private final <T extends GestureHandler> void createGestureHandlerHelper(String str, int i, ReadableMap readableMap) {
        if (this.registry.getHandler(i) == null) {
            for (HandlerFactory handlerFactory : this.handlerFactories) {
                if (Intrinsics.areEqual((Object) handlerFactory.getName(), (Object) str)) {
                    GestureHandler create = handlerFactory.create(getReactApplicationContext());
                    create.setTag(i);
                    create.setOnTouchEventListener(this.eventListener);
                    this.registry.registerHandler(create);
                    this.interactionManager.configureInteractions(create, readableMap);
                    handlerFactory.configure(create, readableMap);
                    return;
                }
            }
            throw new JSApplicationIllegalArgumentException("Invalid handler name " + str);
        }
        throw new IllegalStateException("Handler with tag " + i + " already exists. Please ensure that no Gesture instance is used across multiple GestureDetectors.");
    }

    @ReactMethod
    public void createGestureHandler(String str, double d, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(str, "handlerName");
        Intrinsics.checkNotNullParameter(readableMap, "config");
        createGestureHandlerHelper(str, (int) d, readableMap);
    }

    @ReactMethod
    public void attachGestureHandler(double d, double d2, double d3) {
        int i = (int) d;
        if (!this.registry.attachHandlerToView(i, (int) d2, (int) d3)) {
            throw new JSApplicationIllegalArgumentException("Handler with tag " + i + " does not exists");
        }
    }

    private final <T extends GestureHandler> void updateGestureHandlerHelper(int i, ReadableMap readableMap) {
        HandlerFactory findFactoryForHandler;
        GestureHandler handler = this.registry.getHandler(i);
        if (handler != null && (findFactoryForHandler = findFactoryForHandler(handler)) != null) {
            this.interactionManager.dropRelationsForHandlerWithTag(i);
            this.interactionManager.configureInteractions(handler, readableMap);
            findFactoryForHandler.configure(handler, readableMap);
        }
    }

    @ReactMethod
    public void updateGestureHandler(double d, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        updateGestureHandlerHelper((int) d, readableMap);
    }

    @ReactMethod
    public void dropGestureHandler(double d) {
        int i = (int) d;
        this.interactionManager.dropRelationsForHandlerWithTag(i);
        this.registry.dropHandler(i);
    }

    @ReactMethod
    public void handleSetJSResponder(double d, boolean z) {
        int i = (int) d;
        RNGestureHandlerRootHelper findRootHelperForViewAncestor = findRootHelperForViewAncestor(i);
        if (findRootHelperForViewAncestor != null) {
            findRootHelperForViewAncestor.handleSetJSResponder(i, z);
        }
    }

    public void setGestureHandlerState(int i, int i2) {
        GestureHandler handler = this.registry.getHandler(i);
        if (handler == null) {
            return;
        }
        if (i2 == 1) {
            handler.fail();
        } else if (i2 == 2) {
            handler.begin();
        } else if (i2 == 3) {
            handler.cancel();
        } else if (i2 == 4) {
            handler.activate(true);
        } else if (i2 == 5) {
            handler.end();
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public boolean install() {
        getReactApplicationContext().runOnJSQueueThread(new RNGestureHandlerModule$$ExternalSyntheticLambda0(this));
        return true;
    }

    /* access modifiers changed from: private */
    public static final void install$lambda$2(RNGestureHandlerModule rNGestureHandlerModule) {
        try {
            SoLoader.loadLibrary("gesturehandler");
            JavaScriptContextHolder javaScriptContextHolder = rNGestureHandlerModule.getReactApplicationContext().getJavaScriptContextHolder();
            Intrinsics.checkNotNull(javaScriptContextHolder);
            rNGestureHandlerModule.decorateRuntime(javaScriptContextHolder.get());
        } catch (Exception unused) {
            Log.w("[RNGestureHandler]", "Could not install JSI bindings.");
        }
    }

    public Map<String, Object> getConstants() {
        return MapsKt.mapOf(TuplesKt.to("State", MapsKt.mapOf(TuplesKt.to("UNDETERMINED", 0), TuplesKt.to("BEGAN", 2), TuplesKt.to("ACTIVE", 4), TuplesKt.to("CANCELLED", 3), TuplesKt.to("FAILED", 1), TuplesKt.to("END", 5))), TuplesKt.to("Direction", MapsKt.mapOf(TuplesKt.to("RIGHT", 1), TuplesKt.to("LEFT", 2), TuplesKt.to("UP", 4), TuplesKt.to("DOWN", 8))));
    }

    public void invalidate() {
        this.registry.dropAllHandlers();
        this.interactionManager.reset();
        synchronized (this.roots) {
            while (!this.roots.isEmpty()) {
                try {
                    int size = this.roots.size();
                    this.roots.get(0).tearDown();
                    if (this.roots.size() >= size) {
                        throw new IllegalStateException("Expected root helper to get unregistered while tearing down");
                    }
                } finally {
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        super.invalidate();
    }

    public final void registerRootHelper(RNGestureHandlerRootHelper rNGestureHandlerRootHelper) {
        Intrinsics.checkNotNullParameter(rNGestureHandlerRootHelper, "root");
        synchronized (this.roots) {
            if (!this.roots.contains(rNGestureHandlerRootHelper)) {
                this.roots.add(rNGestureHandlerRootHelper);
            } else {
                throw new IllegalStateException("Root helper" + rNGestureHandlerRootHelper + " already registered");
            }
        }
    }

    public final void unregisterRootHelper(RNGestureHandlerRootHelper rNGestureHandlerRootHelper) {
        Intrinsics.checkNotNullParameter(rNGestureHandlerRootHelper, "root");
        synchronized (this.roots) {
            this.roots.remove(rNGestureHandlerRootHelper);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper findRootHelperForViewAncestor(int r7) {
        /*
            r6 = this;
            com.facebook.react.bridge.ReactApplicationContext r0 = r6.getReactApplicationContext()
            java.lang.String r1 = "getReactApplicationContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            com.facebook.react.uimanager.UIManagerModule r0 = com.swmansion.gesturehandler.react.ExtensionsKt.getUIManager(r0)
            int r7 = r0.resolveRootTagFromReactTag(r7)
            r0 = 1
            r1 = 0
            if (r7 >= r0) goto L_0x0016
            return r1
        L_0x0016:
            java.util.List<com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper> r0 = r6.roots
            monitor-enter(r0)
            java.util.List<com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper> r2 = r6.roots     // Catch:{ all -> 0x0044 }
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch:{ all -> 0x0044 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0044 }
        L_0x0021:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x0046
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0044 }
            r4 = r3
            com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper r4 = (com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper) r4     // Catch:{ all -> 0x0044 }
            android.view.ViewGroup r5 = r4.getRootView()     // Catch:{ all -> 0x0044 }
            boolean r5 = r5 instanceof com.facebook.react.ReactRootView     // Catch:{ all -> 0x0044 }
            if (r5 == 0) goto L_0x0021
            android.view.ViewGroup r4 = r4.getRootView()     // Catch:{ all -> 0x0044 }
            com.facebook.react.ReactRootView r4 = (com.facebook.react.ReactRootView) r4     // Catch:{ all -> 0x0044 }
            int r4 = r4.getRootViewTag()     // Catch:{ all -> 0x0044 }
            if (r4 != r7) goto L_0x0021
            r1 = r3
            goto L_0x0046
        L_0x0044:
            r7 = move-exception
            goto L_0x004a
        L_0x0046:
            com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper r1 = (com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper) r1     // Catch:{ all -> 0x0044 }
            monitor-exit(r0)
            return r1
        L_0x004a:
            monitor-exit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.react.RNGestureHandlerModule.findRootHelperForViewAncestor(int):com.swmansion.gesturehandler.react.RNGestureHandlerRootHelper");
    }

    private final <T extends GestureHandler> HandlerFactory findFactoryForHandler(GestureHandler gestureHandler) {
        for (HandlerFactory handlerFactory : this.handlerFactories) {
            if (Intrinsics.areEqual((Object) handlerFactory.getType(), (Object) gestureHandler.getClass())) {
                return handlerFactory;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final <T extends GestureHandler> void onHandlerUpdate(T t) {
        HandlerFactory findFactoryForHandler;
        if (t.getTag() < 0 || t.getState() != 4 || (findFactoryForHandler = findFactoryForHandler(t)) == null) {
            return;
        }
        if (t.getActionType() == 1) {
            sendEventForReanimated(RNGestureHandlerEvent.Companion.obtain$default(RNGestureHandlerEvent.Companion, t, findFactoryForHandler.createEventBuilder(t), false, 4, (Object) null));
        } else if (t.getActionType() == 2) {
            sendEventForNativeAnimatedEvent(RNGestureHandlerEvent.Companion.obtain(t, findFactoryForHandler.createEventBuilder(t), true));
        } else if (t.getActionType() == 3) {
            sendEventForDirectEvent(RNGestureHandlerEvent.Companion.obtain$default(RNGestureHandlerEvent.Companion, t, findFactoryForHandler.createEventBuilder(t), false, 4, (Object) null));
        } else if (t.getActionType() == 4) {
            sendEventForDeviceEvent("onGestureHandlerEvent", RNGestureHandlerEvent.Companion.createEventData(findFactoryForHandler.createEventBuilder(t)));
        }
    }

    /* access modifiers changed from: private */
    public final <T extends GestureHandler> void onStateChange(T t, int i, int i2) {
        HandlerFactory findFactoryForHandler;
        if (t.getTag() < 0 || (findFactoryForHandler = findFactoryForHandler(t)) == null) {
            return;
        }
        if (t.getActionType() == 1) {
            sendEventForReanimated(RNGestureHandlerStateChangeEvent.Companion.obtain(t, i, i2, findFactoryForHandler.createEventBuilder(t)));
        } else if (t.getActionType() == 2 || t.getActionType() == 3) {
            sendEventForDirectEvent(RNGestureHandlerStateChangeEvent.Companion.obtain(t, i, i2, findFactoryForHandler.createEventBuilder(t)));
        } else if (t.getActionType() == 4) {
            sendEventForDeviceEvent("onGestureHandlerStateChange", RNGestureHandlerStateChangeEvent.Companion.createEventData(findFactoryForHandler.createEventBuilder(t), i, i2));
        }
    }

    /* access modifiers changed from: private */
    public final <T extends GestureHandler> void onTouchEvent(T t) {
        if (t.getTag() >= 0) {
            if (t.getState() != 2 && t.getState() != 4 && t.getState() != 0 && t.getView() == null) {
                return;
            }
            if (t.getActionType() == 1) {
                sendEventForReanimated(RNGestureHandlerTouchEvent.Companion.obtain(t));
            } else if (t.getActionType() == 4) {
                sendEventForDeviceEvent("onGestureHandlerEvent", RNGestureHandlerTouchEvent.Companion.createEventData(t));
            }
        }
    }

    private final <T extends Event<T>> void sendEventForReanimated(T t) {
        sendEventForDirectEvent(t);
    }

    private final void sendEventForNativeAnimatedEvent(RNGestureHandlerEvent rNGestureHandlerEvent) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        ReactContextExtensionsKt.dispatchEvent(reactApplicationContext, rNGestureHandlerEvent);
    }

    private final <T extends Event<T>> void sendEventForDirectEvent(T t) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        ReactContextExtensionsKt.dispatchEvent(reactApplicationContext, t);
    }

    private final void sendEventForDeviceEvent(String str, WritableMap writableMap) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        ExtensionsKt.getDeviceEventEmitter(reactApplicationContext).emit(str, writableMap);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final void handleHitSlopProperty(GestureHandler gestureHandler, ReadableMap readableMap) {
            if (readableMap.getType(RNGestureHandlerModule.KEY_HIT_SLOP) == ReadableType.Number) {
                float pixelFromDIP = PixelUtil.toPixelFromDIP(readableMap.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP));
                gestureHandler.setHitSlop(pixelFromDIP, pixelFromDIP, pixelFromDIP, pixelFromDIP, Float.NaN, Float.NaN);
                return;
            }
            ReadableMap map = readableMap.getMap(RNGestureHandlerModule.KEY_HIT_SLOP);
            Intrinsics.checkNotNull(map);
            float f = Float.NaN;
            float pixelFromDIP2 = map.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP_HORIZONTAL) ? PixelUtil.toPixelFromDIP(map.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP_HORIZONTAL)) : Float.NaN;
            float f2 = pixelFromDIP2;
            float pixelFromDIP3 = map.hasKey(RNGestureHandlerModule.KEY_HIT_SLOP_VERTICAL) ? PixelUtil.toPixelFromDIP(map.getDouble(RNGestureHandlerModule.KEY_HIT_SLOP_VERTICAL)) : Float.NaN;
            float f3 = pixelFromDIP3;
            if (map.hasKey("left")) {
                pixelFromDIP2 = PixelUtil.toPixelFromDIP(map.getDouble("left"));
            }
            float f4 = pixelFromDIP2;
            if (map.hasKey("top")) {
                pixelFromDIP3 = PixelUtil.toPixelFromDIP(map.getDouble("top"));
            }
            float f5 = pixelFromDIP3;
            if (map.hasKey("right")) {
                f2 = PixelUtil.toPixelFromDIP(map.getDouble("right"));
            }
            float f6 = f2;
            if (map.hasKey("bottom")) {
                f3 = PixelUtil.toPixelFromDIP(map.getDouble("bottom"));
            }
            float f7 = f3;
            float pixelFromDIP4 = map.hasKey("width") ? PixelUtil.toPixelFromDIP(map.getDouble("width")) : Float.NaN;
            if (map.hasKey("height")) {
                f = PixelUtil.toPixelFromDIP(map.getDouble("height"));
            }
            gestureHandler.setHitSlop(f4, f5, f6, f7, pixelFromDIP4, f);
        }
    }
}
