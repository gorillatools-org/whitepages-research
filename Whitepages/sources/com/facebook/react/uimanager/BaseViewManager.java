package com.facebook.react.uimanager;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.MatrixMathHelper;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.events.PointerEventHelper;
import com.facebook.react.uimanager.style.OutlineStyle;
import com.facebook.react.uimanager.util.ReactFindViewUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class BaseViewManager<T extends View, C extends LayoutShadowNode> extends ViewManager<T, C> implements View.OnLayoutChangeListener {
    private static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = ((float) Math.sqrt(5.0d));
    private static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    private static final String STATE_BUSY = "busy";
    private static final String STATE_CHECKED = "checked";
    private static final String STATE_EXPANDED = "expanded";
    private static final String STATE_MIXED = "mixed";
    private static final MatrixMathHelper.MatrixDecompositionContext sMatrixDecompositionContext = new MatrixMathHelper.MatrixDecompositionContext();
    private static final double[] sTransformDecompositionArray = new double[16];

    @ReactProp(name = "onMoveShouldSetResponder")
    public void setMoveShouldSetResponder(T t, boolean z) {
    }

    @ReactProp(name = "onMoveShouldSetResponderCapture")
    public void setMoveShouldSetResponderCapture(T t, boolean z) {
    }

    @ReactProp(name = "onResponderEnd")
    public void setResponderEnd(T t, boolean z) {
    }

    @ReactProp(name = "onResponderGrant")
    public void setResponderGrant(T t, boolean z) {
    }

    @ReactProp(name = "onResponderMove")
    public void setResponderMove(T t, boolean z) {
    }

    @ReactProp(name = "onResponderReject")
    public void setResponderReject(T t, boolean z) {
    }

    @ReactProp(name = "onResponderRelease")
    public void setResponderRelease(T t, boolean z) {
    }

    @ReactProp(name = "onResponderStart")
    public void setResponderStart(T t, boolean z) {
    }

    @ReactProp(name = "onResponderTerminate")
    public void setResponderTerminate(T t, boolean z) {
    }

    @ReactProp(name = "onResponderTerminationRequest")
    public void setResponderTerminationRequest(T t, boolean z) {
    }

    @ReactProp(name = "onShouldBlockNativeResponder")
    public void setShouldBlockNativeResponder(T t, boolean z) {
    }

    @ReactProp(name = "onStartShouldSetResponder")
    public void setStartShouldSetResponder(T t, boolean z) {
    }

    @ReactProp(name = "onStartShouldSetResponderCapture")
    public void setStartShouldSetResponderCapture(T t, boolean z) {
    }

    @ReactProp(name = "onTouchCancel")
    public void setTouchCancel(T t, boolean z) {
    }

    @ReactProp(name = "onTouchEnd")
    public void setTouchEnd(T t, boolean z) {
    }

    @ReactProp(name = "onTouchMove")
    public void setTouchMove(T t, boolean z) {
    }

    @ReactProp(name = "onTouchStart")
    public void setTouchStart(T t, boolean z) {
    }

    public BaseViewManager() {
        super((ReactApplicationContext) null);
    }

    public BaseViewManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* access modifiers changed from: protected */
    public T prepareToRecycleView(ThemedReactContext themedReactContext, T t) {
        t.setTag((Object) null);
        t.setTag(R.id.pointer_events, (Object) null);
        t.setTag(R.id.react_test_id, (Object) null);
        t.setTag(R.id.view_tag_native_id, (Object) null);
        t.setTag(R.id.labelled_by, (Object) null);
        t.setTag(R.id.accessibility_label, (Object) null);
        t.setTag(R.id.accessibility_hint, (Object) null);
        t.setTag(R.id.accessibility_role, (Object) null);
        t.setTag(R.id.accessibility_state, (Object) null);
        t.setTag(R.id.accessibility_actions, (Object) null);
        t.setTag(R.id.accessibility_value, (Object) null);
        t.setTag(R.id.accessibility_state_expanded, (Object) null);
        t.setTag(R.id.view_clipped, (Object) null);
        setTransformProperty(t, (ReadableArray) null, (ReadableArray) null);
        int i = Build.VERSION.SDK_INT;
        if (i < 28) {
            return null;
        }
        t.resetPivot();
        t.setTop(0);
        t.setBottom(0);
        t.setLeft(0);
        t.setRight(0);
        t.setElevation(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        if (i >= 29) {
            t.setAnimationMatrix((Matrix) null);
        }
        t.setTag(R.id.transform, (Object) null);
        t.setTag(R.id.transform_origin, (Object) null);
        t.setTag(R.id.invalidate_transform, (Object) null);
        t.removeOnLayoutChangeListener(this);
        t.setTag(R.id.use_hardware_layer, (Object) null);
        t.setTag(R.id.filter, (Object) null);
        t.setTag(R.id.mix_blend_mode, (Object) null);
        LayerEffectsHelper.apply(t, (ReadableArray) null, (Boolean) null);
        if (i >= 28) {
            t.setOutlineAmbientShadowColor(-16777216);
            t.setOutlineSpotShadowColor(-16777216);
        }
        t.setNextFocusDownId(-1);
        t.setNextFocusForwardId(-1);
        t.setNextFocusRightId(-1);
        t.setNextFocusUpId(-1);
        t.setFocusable(false);
        t.setFocusableInTouchMode(false);
        t.setElevation(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        t.setAlpha(1.0f);
        setPadding(t, 0, 0, 0, 0);
        t.setForeground((Drawable) null);
        return t;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i7 - i5;
        int i10 = i3 - i;
        if (i4 - i2 != i8 - i6 || i10 != i9) {
            ReadableArray readableArray = (ReadableArray) view.getTag(R.id.transform_origin);
            ReadableArray readableArray2 = (ReadableArray) view.getTag(R.id.transform);
            if (readableArray2 != null || readableArray != null) {
                setTransformProperty(view, readableArray2, readableArray);
            }
        }
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "backgroundColor")
    public void setBackgroundColor(T t, int i) {
        BackgroundStyleApplicator.setBackgroundColor(t, Integer.valueOf(i));
    }

    @ReactProp(customType = "Filter", name = "filter")
    public void setFilter(T t, ReadableArray readableArray) {
        if (ViewUtil.getUIManagerType((View) t) == 2) {
            t.setTag(R.id.filter, readableArray);
        }
    }

    @ReactProp(name = "mixBlendMode")
    public void setMixBlendMode(T t, String str) {
        if (ViewUtil.getUIManagerType((View) t) == 2) {
            t.setTag(R.id.mix_blend_mode, BlendModeHelper.parseMixBlendMode(str));
            if (t.getParent() instanceof View) {
                ((View) t.getParent()).invalidate();
            }
        }
    }

    @ReactProp(name = "transform")
    public void setTransform(T t, ReadableArray readableArray) {
        if (!Objects.equals((ReadableArray) t.getTag(R.id.transform), readableArray)) {
            t.setTag(R.id.transform, readableArray);
            t.setTag(R.id.invalidate_transform, Boolean.TRUE);
        }
    }

    @ReactProp(name = "transformOrigin")
    public void setTransformOrigin(T t, ReadableArray readableArray) {
        if (!Objects.equals((ReadableArray) t.getTag(R.id.transform_origin), readableArray)) {
            t.setTag(R.id.transform_origin, readableArray);
            t.setTag(R.id.invalidate_transform, Boolean.TRUE);
        }
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(T t, float f) {
        t.setAlpha(f);
    }

    @ReactProp(name = "elevation")
    public void setElevation(T t, float f) {
        ViewCompat.setElevation(t, PixelUtil.toPixelFromDIP(f));
    }

    @ReactProp(customType = "Color", defaultInt = -16777216, name = "shadowColor")
    public void setShadowColor(T t, int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            t.setOutlineAmbientShadowColor(i);
            t.setOutlineSpotShadowColor(i);
        }
    }

    @ReactProp(name = "zIndex")
    public void setZIndex(T t, float f) {
        ViewGroupManager.setViewZIndex(t, Math.round(f));
        ViewParent parent = t.getParent();
        if (parent instanceof ReactZIndexedViewGroup) {
            ((ReactZIndexedViewGroup) parent).updateDrawingOrder();
        }
    }

    @ReactProp(name = "renderToHardwareTextureAndroid")
    public void setRenderToHardwareTexture(T t, boolean z) {
        t.setTag(R.id.use_hardware_layer, Boolean.valueOf(z));
    }

    @ReactProp(name = "testID")
    public void setTestId(T t, String str) {
        t.setTag(R.id.react_test_id, str);
        t.setTag(str);
    }

    @ReactProp(name = "nativeID")
    public void setNativeId(T t, String str) {
        t.setTag(R.id.view_tag_native_id, str);
        ReactFindViewUtil.notifyViewRendered(t);
    }

    @ReactProp(name = "accessibilityLabelledBy")
    public void setAccessibilityLabelledBy(T t, Dynamic dynamic) {
        if (!dynamic.isNull()) {
            if (dynamic.getType() == ReadableType.String) {
                t.setTag(R.id.labelled_by, dynamic.asString());
            } else if (dynamic.getType() == ReadableType.Array) {
                t.setTag(R.id.labelled_by, dynamic.asArray().getString(0));
            }
        }
    }

    @ReactProp(name = "accessibilityLabel")
    public void setAccessibilityLabel(T t, String str) {
        t.setTag(R.id.accessibility_label, str);
        updateViewContentDescription(t);
    }

    @ReactProp(name = "accessibilityHint")
    public void setAccessibilityHint(T t, String str) {
        t.setTag(R.id.accessibility_hint, str);
        updateViewContentDescription(t);
    }

    @ReactProp(name = "accessibilityRole")
    public void setAccessibilityRole(T t, String str) {
        if (str == null) {
            t.setTag(R.id.accessibility_role, (Object) null);
        } else {
            t.setTag(R.id.accessibility_role, ReactAccessibilityDelegate.AccessibilityRole.fromValue(str));
        }
    }

    @ReactProp(name = "accessibilityCollection")
    public void setAccessibilityCollection(T t, ReadableMap readableMap) {
        t.setTag(R.id.accessibility_collection, readableMap);
    }

    @ReactProp(name = "accessibilityCollectionItem")
    public void setAccessibilityCollectionItem(T t, ReadableMap readableMap) {
        t.setTag(R.id.accessibility_collection_item, readableMap);
    }

    @ReactProp(name = "accessibilityState")
    public void setViewState(T t, ReadableMap readableMap) {
        if (readableMap != null) {
            if (readableMap.hasKey(STATE_EXPANDED)) {
                t.setTag(R.id.accessibility_state_expanded, Boolean.valueOf(readableMap.getBoolean(STATE_EXPANDED)));
            }
            if (readableMap.hasKey("selected")) {
                boolean isSelected = t.isSelected();
                boolean z = readableMap.getBoolean("selected");
                t.setSelected(z);
                if (t.isAccessibilityFocused() && isSelected && !z) {
                    t.announceForAccessibility(t.getContext().getString(R.string.state_unselected_description));
                }
            } else {
                t.setSelected(false);
            }
            t.setTag(R.id.accessibility_state, readableMap);
            if (readableMap.hasKey("disabled") && !readableMap.getBoolean("disabled")) {
                t.setEnabled(true);
            }
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                if (nextKey.equals(STATE_BUSY) || nextKey.equals(STATE_EXPANDED) || (nextKey.equals(STATE_CHECKED) && readableMap.getType(STATE_CHECKED) == ReadableType.String)) {
                    updateViewContentDescription(t);
                    return;
                } else if (t.isAccessibilityFocused()) {
                    t.sendAccessibilityEvent(1);
                }
            }
        }
    }

    private void updateViewContentDescription(T t) {
        Dynamic dynamic;
        String str = (String) t.getTag(R.id.accessibility_label);
        ReadableMap readableMap = (ReadableMap) t.getTag(R.id.accessibility_state);
        ArrayList arrayList = new ArrayList();
        ReadableMap readableMap2 = (ReadableMap) t.getTag(R.id.accessibility_value);
        if (str != null) {
            arrayList.add(str);
        }
        if (readableMap != null) {
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                Dynamic dynamic2 = readableMap.getDynamic(nextKey);
                if (nextKey.equals(STATE_CHECKED) && dynamic2.getType() == ReadableType.String && dynamic2.asString().equals(STATE_MIXED)) {
                    arrayList.add(t.getContext().getString(R.string.state_mixed_description));
                } else if (nextKey.equals(STATE_BUSY) && dynamic2.getType() == ReadableType.Boolean && dynamic2.asBoolean()) {
                    arrayList.add(t.getContext().getString(R.string.state_busy_description));
                }
            }
        }
        if (readableMap2 != null && readableMap2.hasKey("text") && (dynamic = readableMap2.getDynamic("text")) != null && dynamic.getType() == ReadableType.String) {
            arrayList.add(dynamic.asString());
        }
        if (arrayList.size() > 0) {
            t.setContentDescription(TextUtils.join(", ", arrayList));
        }
    }

    @ReactProp(name = "accessibilityActions")
    public void setAccessibilityActions(T t, ReadableArray readableArray) {
        if (readableArray != null) {
            t.setTag(R.id.accessibility_actions, readableArray);
        }
    }

    @ReactProp(name = "accessibilityValue")
    public void setAccessibilityValue(T t, ReadableMap readableMap) {
        if (readableMap == null) {
            t.setTag(R.id.accessibility_value, (Object) null);
            t.setContentDescription((CharSequence) null);
            return;
        }
        t.setTag(R.id.accessibility_value, readableMap);
        if (readableMap.hasKey("text")) {
            updateViewContentDescription(t);
        }
    }

    @ReactProp(name = "importantForAccessibility")
    public void setImportantForAccessibility(T t, String str) {
        if (str == null || str.equals("auto")) {
            ViewCompat.setImportantForAccessibility(t, 0);
        } else if (str.equals("yes")) {
            ViewCompat.setImportantForAccessibility(t, 1);
        } else if (str.equals("no")) {
            ViewCompat.setImportantForAccessibility(t, 2);
        } else if (str.equals("no-hide-descendants")) {
            ViewCompat.setImportantForAccessibility(t, 4);
        }
    }

    @ReactProp(name = "role")
    public void setRole(T t, String str) {
        if (str == null) {
            t.setTag(R.id.role, (Object) null);
        } else {
            t.setTag(R.id.role, ReactAccessibilityDelegate.Role.fromValue(str));
        }
    }

    @ReactProp(name = "rotation")
    @Deprecated
    public void setRotation(T t, float f) {
        t.setRotation(f);
    }

    @ReactProp(defaultFloat = 1.0f, name = "scaleX")
    @Deprecated
    public void setScaleX(T t, float f) {
        t.setScaleX(f);
    }

    @ReactProp(defaultFloat = 1.0f, name = "scaleY")
    @Deprecated
    public void setScaleY(T t, float f) {
        t.setScaleY(f);
    }

    @ReactProp(defaultFloat = 0.0f, name = "translateX")
    @Deprecated
    public void setTranslateX(T t, float f) {
        t.setTranslationX(PixelUtil.toPixelFromDIP(f));
    }

    @ReactProp(defaultFloat = 0.0f, name = "translateY")
    @Deprecated
    public void setTranslateY(T t, float f) {
        t.setTranslationY(PixelUtil.toPixelFromDIP(f));
    }

    @ReactProp(name = "accessibilityLiveRegion")
    public void setAccessibilityLiveRegion(T t, String str) {
        if (str == null || str.equals("none")) {
            ViewCompat.setAccessibilityLiveRegion(t, 0);
        } else if (str.equals("polite")) {
            ViewCompat.setAccessibilityLiveRegion(t, 1);
        } else if (str.equals("assertive")) {
            ViewCompat.setAccessibilityLiveRegion(t, 2);
        }
    }

    private static class LayerEffectsHelper {
        private LayerEffectsHelper() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x002c A[ADDED_TO_REGION] */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x003a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static void apply(android.view.View r4, com.facebook.react.bridge.ReadableArray r5, java.lang.Boolean r6) {
            /*
                int r0 = android.os.Build.VERSION.SDK_INT
                r1 = 0
                r2 = 31
                if (r0 < r2) goto L_0x000a
                r4.setRenderEffect(r1)
            L_0x000a:
                if (r5 == 0) goto L_0x0028
                boolean r3 = com.facebook.react.uimanager.FilterHelper.isOnlyColorMatrixFilters(r5)
                if (r3 == 0) goto L_0x001f
                android.graphics.Paint r0 = new android.graphics.Paint
                r0.<init>()
                android.graphics.ColorMatrixColorFilter r5 = com.facebook.react.uimanager.FilterHelper.parseColorMatrixFilters(r5)
                r0.setColorFilter(r5)
                goto L_0x0029
            L_0x001f:
                if (r0 < r2) goto L_0x0028
                android.graphics.RenderEffect r5 = com.facebook.react.uimanager.FilterHelper.parseFilters(r5)
                r4.setRenderEffect(r5)
            L_0x0028:
                r0 = r1
            L_0x0029:
                r5 = 2
                if (r0 != 0) goto L_0x003a
                if (r6 == 0) goto L_0x0035
                boolean r6 = r6.booleanValue()
                if (r6 == 0) goto L_0x0035
                goto L_0x0036
            L_0x0035:
                r5 = 0
            L_0x0036:
                r4.setLayerType(r5, r1)
                goto L_0x003d
            L_0x003a:
                r4.setLayerType(r5, r0)
            L_0x003d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.BaseViewManager.LayerEffectsHelper.apply(android.view.View, com.facebook.react.bridge.ReadableArray, java.lang.Boolean):void");
        }
    }

    /* access modifiers changed from: protected */
    public void setTransformProperty(T t, ReadableArray readableArray, ReadableArray readableArray2) {
        if (readableArray == null) {
            t.setTranslationX(PixelUtil.toPixelFromDIP((float) CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER));
            t.setTranslationY(PixelUtil.toPixelFromDIP((float) CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER));
            t.setRotation(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            t.setRotationX(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            t.setRotationY(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            t.setScaleX(1.0f);
            t.setScaleY(1.0f);
            t.setCameraDistance(CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
            return;
        }
        boolean z = ViewUtil.getUIManagerType((View) t) == 2;
        MatrixMathHelper.MatrixDecompositionContext matrixDecompositionContext = sMatrixDecompositionContext;
        matrixDecompositionContext.reset();
        double[] dArr = sTransformDecompositionArray;
        TransformHelper.processTransform(readableArray, dArr, PixelUtil.toDIPFromPixel((float) t.getWidth()), PixelUtil.toDIPFromPixel((float) t.getHeight()), readableArray2, z);
        MatrixMathHelper.decomposeMatrix(dArr, matrixDecompositionContext);
        t.setTranslationX(PixelUtil.toPixelFromDIP(sanitizeFloatPropertyValue((float) matrixDecompositionContext.translation[0])));
        t.setTranslationY(PixelUtil.toPixelFromDIP(sanitizeFloatPropertyValue((float) matrixDecompositionContext.translation[1])));
        t.setRotation(sanitizeFloatPropertyValue((float) matrixDecompositionContext.rotationDegrees[2]));
        t.setRotationX(sanitizeFloatPropertyValue((float) matrixDecompositionContext.rotationDegrees[0]));
        t.setRotationY(sanitizeFloatPropertyValue((float) matrixDecompositionContext.rotationDegrees[1]));
        t.setScaleX(sanitizeFloatPropertyValue((float) matrixDecompositionContext.scale[0]));
        t.setScaleY(sanitizeFloatPropertyValue((float) matrixDecompositionContext.scale[1]));
        double[] dArr2 = matrixDecompositionContext.perspective;
        if (dArr2.length > 2) {
            float f = (float) dArr2[2];
            if (f == CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER) {
                f = 7.8125E-4f;
            }
            float f2 = -1.0f / f;
            float f3 = DisplayMetricsHolder.getScreenDisplayMetrics().density;
            t.setCameraDistance(sanitizeFloatPropertyValue(f3 * f3 * f2 * CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER));
        }
    }

    private static float sanitizeFloatPropertyValue(float f) {
        if (f >= -3.4028235E38f && f <= Float.MAX_VALUE) {
            return f;
        }
        if (f < -3.4028235E38f || f == Float.NEGATIVE_INFINITY) {
            return -3.4028235E38f;
        }
        if (f > Float.MAX_VALUE || f == Float.POSITIVE_INFINITY) {
            return Float.MAX_VALUE;
        }
        if (Float.isNaN(f)) {
            return CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER;
        }
        throw new IllegalStateException("Invalid float property value: " + f);
    }

    private void updateViewAccessibility(T t) {
        ReactAccessibilityDelegate.setDelegate(t, t.isFocusable(), t.getImportantForAccessibility());
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(T t) {
        super.onAfterUpdateTransaction(t);
        updateViewAccessibility(t);
        Boolean bool = (Boolean) t.getTag(R.id.invalidate_transform);
        if (bool != null && bool.booleanValue()) {
            t.addOnLayoutChangeListener(this);
            setTransformProperty(t, (ReadableArray) t.getTag(R.id.transform), (ReadableArray) t.getTag(R.id.transform_origin));
            t.setTag(R.id.invalidate_transform, Boolean.FALSE);
        }
        LayerEffectsHelper.apply(t, (ReadableArray) t.getTag(R.id.filter), (Boolean) t.getTag(R.id.use_hardware_layer));
    }

    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap<>();
        }
        MapBuilder.Builder put = MapBuilder.builder().put(PointerEventHelper.POINTER_CANCEL, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPointerCancel", "captured", "onPointerCancelCapture"))).put(PointerEventHelper.POINTER_DOWN, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPointerDown", "captured", "onPointerDownCapture")));
        Boolean bool = Boolean.TRUE;
        exportedCustomDirectEventTypeConstants.putAll(put.put(PointerEventHelper.POINTER_ENTER, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", ViewProps.ON_POINTER_ENTER, "captured", ViewProps.ON_POINTER_ENTER_CAPTURE, "skipBubbling", bool))).put(PointerEventHelper.POINTER_LEAVE, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", ViewProps.ON_POINTER_LEAVE, "captured", ViewProps.ON_POINTER_LEAVE_CAPTURE, "skipBubbling", bool))).put(PointerEventHelper.POINTER_MOVE, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", ViewProps.ON_POINTER_MOVE, "captured", ViewProps.ON_POINTER_MOVE_CAPTURE))).put(PointerEventHelper.POINTER_UP, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onPointerUp", "captured", "onPointerUpCapture"))).put(PointerEventHelper.POINTER_OUT, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", ViewProps.ON_POINTER_OUT, "captured", ViewProps.ON_POINTER_OUT_CAPTURE))).put(PointerEventHelper.POINTER_OVER, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", ViewProps.ON_POINTER_OVER, "captured", ViewProps.ON_POINTER_OVER_CAPTURE))).put(PointerEventHelper.CLICK, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", ViewProps.ON_CLICK, "captured", ViewProps.ON_CLICK_CAPTURE))).build());
        return exportedCustomDirectEventTypeConstants;
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap<>();
        }
        exportedCustomDirectEventTypeConstants.putAll(MapBuilder.builder().put(ReactAccessibilityDelegate.TOP_ACCESSIBILITY_ACTION_EVENT, MapBuilder.of("registrationName", "onAccessibilityAction")).build());
        return exportedCustomDirectEventTypeConstants;
    }

    public void setBorderRadius(T t, float f) {
        logUnsupportedPropertyWarning(ViewProps.BORDER_RADIUS);
    }

    public void setBorderBottomLeftRadius(T t, float f) {
        logUnsupportedPropertyWarning(ViewProps.BORDER_BOTTOM_LEFT_RADIUS);
    }

    public void setBorderBottomRightRadius(T t, float f) {
        logUnsupportedPropertyWarning(ViewProps.BORDER_BOTTOM_RIGHT_RADIUS);
    }

    public void setBorderTopLeftRadius(T t, float f) {
        logUnsupportedPropertyWarning(ViewProps.BORDER_TOP_LEFT_RADIUS);
    }

    public void setBorderTopRightRadius(T t, float f) {
        logUnsupportedPropertyWarning(ViewProps.BORDER_TOP_RIGHT_RADIUS);
    }

    @ReactProp(customType = "Color", name = "outlineColor")
    public void setOutlineColor(T t, Integer num) {
        BackgroundStyleApplicator.setOutlineColor(t, num);
    }

    @ReactProp(name = "outlineOffset")
    public void setOutlineOffset(T t, float f) {
        BackgroundStyleApplicator.setOutlineOffset(t, f);
    }

    @ReactProp(name = "outlineStyle")
    public void setOutlineStyle(T t, String str) {
        BackgroundStyleApplicator.setOutlineStyle(t, str == null ? null : OutlineStyle.fromString(str));
    }

    @ReactProp(name = "outlineWidth")
    public void setOutlineWidth(T t, float f) {
        BackgroundStyleApplicator.setOutlineWidth(t, f);
    }

    @ReactProp(customType = "BoxShadow", name = "boxShadow")
    public void setBoxShadow(T t, ReadableArray readableArray) {
        BackgroundStyleApplicator.setBoxShadow((View) t, readableArray);
    }

    private void logUnsupportedPropertyWarning(String str) {
        FLog.w(ReactConstants.TAG, "%s doesn't support property '%s'", getName(), str);
    }

    private static void setPointerEventsFlag(View view, PointerEventHelper.EVENT event, boolean z) {
        Integer num = (Integer) view.getTag(R.id.pointer_events);
        int intValue = num != null ? num.intValue() : 0;
        int ordinal = 1 << event.ordinal();
        view.setTag(R.id.pointer_events, Integer.valueOf(z ? ordinal | intValue : (~ordinal) & intValue));
    }

    @ReactProp(name = "onPointerEnter")
    public void setPointerEnter(T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.ENTER, z);
    }

    @ReactProp(name = "onPointerEnterCapture")
    public void setPointerEnterCapture(T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.ENTER_CAPTURE, z);
    }

    @ReactProp(name = "onPointerOver")
    public void setPointerOver(T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.OVER, z);
    }

    @ReactProp(name = "onPointerOverCapture")
    public void setPointerOverCapture(T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.OVER_CAPTURE, z);
    }

    @ReactProp(name = "onPointerOut")
    public void setPointerOut(T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.OUT, z);
    }

    @ReactProp(name = "onPointerOutCapture")
    public void setPointerOutCapture(T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.OUT_CAPTURE, z);
    }

    @ReactProp(name = "onPointerLeave")
    public void setPointerLeave(T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.LEAVE, z);
    }

    @ReactProp(name = "onPointerLeaveCapture")
    public void setPointerLeaveCapture(T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.LEAVE_CAPTURE, z);
    }

    @ReactProp(name = "onPointerMove")
    public void setPointerMove(T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.MOVE, z);
    }

    @ReactProp(name = "onPointerMoveCapture")
    public void setPointerMoveCapture(T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.MOVE_CAPTURE, z);
    }

    @ReactProp(name = "onClick")
    public void setClick(T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.CLICK, z);
    }

    @ReactProp(name = "onClickCapture")
    public void setClickCapture(T t, boolean z) {
        setPointerEventsFlag(t, PointerEventHelper.EVENT.CLICK_CAPTURE, z);
    }
}
