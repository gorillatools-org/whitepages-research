package com.horcrux.svg;

import android.graphics.Matrix;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.MatrixMathHelper;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.TransformHelper;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.drawable.OutsetBoxShadowDrawableKt;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.horcrux.svg.VirtualView;
import java.util.Locale;

class VirtualViewManager<V extends VirtualView> extends ViewGroupManager<VirtualView> {
    private static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = 5.0f;
    private static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    private static final SparseArray<RenderableView> mTagToRenderableView = new SparseArray<>();
    private static final SparseArray<Runnable> mTagToRunnable = new SparseArray<>();
    private static final MatrixMathHelper.MatrixDecompositionContext sMatrixDecompositionContext = new MatrixMathHelper.MatrixDecompositionContext();
    private static final double[] sTransformDecompositionArray = new double[16];
    protected final String mClassName;
    protected ViewManagerDelegate<V> mDelegate;
    protected final SVGClass svgClass;

    protected enum SVGClass {
        RNSVGGroup,
        RNSVGPath,
        RNSVGText,
        RNSVGTSpan,
        RNSVGTextPath,
        RNSVGImage,
        RNSVGCircle,
        RNSVGEllipse,
        RNSVGLine,
        RNSVGRect,
        RNSVGClipPath,
        RNSVGDefs,
        RNSVGUse,
        RNSVGSymbol,
        RNSVGLinearGradient,
        RNSVGRadialGradient,
        RNSVGPattern,
        RNSVGMask,
        RNSVGFilter,
        RNSVGFeBlend,
        RNSVGFeColorMatrix,
        RNSVGFeComposite,
        RNSVGFeFlood,
        RNSVGFeGaussianBlur,
        RNSVGFeMerge,
        RNSVGFeOffset,
        RNSVGMarker,
        RNSVGForeignObject
    }

    protected VirtualViewManager(SVGClass sVGClass) {
        this.svgClass = sVGClass;
        this.mClassName = sVGClass.toString();
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate getDelegate() {
        return this.mDelegate;
    }

    static class RenderableShadowNode extends LayoutShadowNode {
        @ReactPropGroup(names = {"alignSelf", "alignItems", "collapsable", "flex", "flexBasis", "flexDirection", "flexGrow", "flexShrink", "flexWrap", "justifyContent", "overflow", "alignContent", "display", "position", "right", "top", "bottom", "left", "start", "end", "width", "height", "minWidth", "maxWidth", "minHeight", "maxHeight", "margin", "marginVertical", "marginHorizontal", "marginLeft", "marginRight", "marginTop", "marginBottom", "marginStart", "marginEnd", "padding", "paddingVertical", "paddingHorizontal", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom", "paddingStart", "paddingEnd", "borderWidth", "borderStartWidth", "borderEndWidth", "borderTopWidth", "borderBottomWidth", "borderLeftWidth", "borderRightWidth"})
        public void ignoreLayoutProps(int i, Dynamic dynamic) {
        }

        RenderableShadowNode() {
        }
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new RenderableShadowNode();
    }

    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return RenderableShadowNode.class;
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
            return 0.0f;
        }
        throw new IllegalStateException("Invalid float property value: " + f);
    }

    /* access modifiers changed from: protected */
    public void setTransformProperty(VirtualView virtualView, ReadableArray readableArray) {
        if (readableArray == null) {
            virtualView.setTranslationX(PixelUtil.toPixelFromDIP(0.0f));
            virtualView.setTranslationY(PixelUtil.toPixelFromDIP(0.0f));
            virtualView.setRotation(0.0f);
            virtualView.setRotationX(0.0f);
            virtualView.setRotationY(0.0f);
            virtualView.setScaleX(1.0f);
            virtualView.setScaleY(1.0f);
            virtualView.setCameraDistance(0.0f);
            return;
        }
        MatrixMathHelper.MatrixDecompositionContext matrixDecompositionContext = sMatrixDecompositionContext;
        matrixDecompositionContext.reset();
        double[] dArr = sTransformDecompositionArray;
        TransformHelper.processTransform(readableArray, dArr, (float) virtualView.getWidth(), (float) virtualView.getHeight(), (ReadableArray) null, false);
        MatrixMathHelper.decomposeMatrix(dArr, matrixDecompositionContext);
        virtualView.setTranslationX(PixelUtil.toPixelFromDIP(sanitizeFloatPropertyValue((float) matrixDecompositionContext.translation[0])));
        virtualView.setTranslationY(PixelUtil.toPixelFromDIP(sanitizeFloatPropertyValue((float) matrixDecompositionContext.translation[1])));
        virtualView.setRotation(sanitizeFloatPropertyValue((float) matrixDecompositionContext.rotationDegrees[2]));
        virtualView.setRotationX(sanitizeFloatPropertyValue((float) matrixDecompositionContext.rotationDegrees[0]));
        virtualView.setRotationY(sanitizeFloatPropertyValue((float) matrixDecompositionContext.rotationDegrees[1]));
        virtualView.setScaleX(sanitizeFloatPropertyValue((float) matrixDecompositionContext.scale[0]));
        virtualView.setScaleY(sanitizeFloatPropertyValue((float) matrixDecompositionContext.scale[1]));
        double[] dArr2 = matrixDecompositionContext.perspective;
        if (dArr2.length > 2) {
            float f = (float) dArr2[2];
            if (f == 0.0f) {
                f = 7.8125E-4f;
            }
            float f2 = -1.0f / f;
            float f3 = DisplayMetricsHolder.getScreenDisplayMetrics().density;
            virtualView.setCameraDistance(f3 * f3 * f2 * CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER);
        }
    }

    public String getName() {
        return this.mClassName;
    }

    @ReactProp(name = "mask")
    public void setMask(V v, String str) {
        v.setMask(str);
    }

    @ReactProp(name = "markerStart")
    public void setMarkerStart(V v, String str) {
        v.setMarkerStart(str);
    }

    @ReactProp(name = "markerMid")
    public void setMarkerMid(V v, String str) {
        v.setMarkerMid(str);
    }

    @ReactProp(name = "markerEnd")
    public void setMarkerEnd(V v, String str) {
        v.setMarkerEnd(str);
    }

    @ReactProp(name = "clipPath")
    public void setClipPath(V v, String str) {
        v.setClipPath(str);
    }

    @ReactProp(name = "clipRule")
    public void setClipRule(V v, int i) {
        v.setClipRule(i);
    }

    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(V v, float f) {
        v.setOpacity(f);
    }

    @ReactProp(name = "responsible")
    public void setResponsible(V v, boolean z) {
        v.setResponsible(z);
    }

    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(V v, String str) {
        if (str == null) {
            v.setPointerEvents(PointerEvents.AUTO);
        } else {
            v.setPointerEvents(PointerEvents.valueOf(str.toUpperCase(Locale.US).replace("-", "_")));
        }
    }

    @ReactProp(name = "name")
    public void setName(V v, String str) {
        v.setName(str);
    }

    @ReactProp(name = "display")
    public void setDisplay(V v, String str) {
        v.setDisplay(str);
    }

    @ReactProp(name = "matrix")
    public void setMatrix(V v, Dynamic dynamic) {
        v.setMatrix(dynamic);
    }

    public void setMatrix(V v, ReadableArray readableArray) {
        v.setMatrix(readableArray);
    }

    public void setTransform(VirtualView virtualView, ReadableArray readableArray) {
        setTransformProperty(virtualView, readableArray);
        Matrix matrix = virtualView.getMatrix();
        virtualView.mMatrix = matrix;
        virtualView.mInvertible = matrix.invert(virtualView.mInvMatrix);
    }

    @ReactProp(name = "transform")
    public void setTransform(V v, Dynamic dynamic) {
        if (dynamic.getType() == ReadableType.Array) {
            setTransform((VirtualView) v, dynamic.asArray());
        }
    }

    /* access modifiers changed from: private */
    public void invalidateSvgView(V v) {
        SvgView svgView = v.getSvgView();
        if (svgView != null) {
            svgView.invalidate();
        }
        if (v instanceof TextView) {
            ((TextView) v).getTextContainer().clearChildCache();
        }
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, VirtualView virtualView) {
        super.addEventEmitters(themedReactContext, virtualView);
        virtualView.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            public void onChildViewAdded(View view, View view2) {
                if (view instanceof VirtualView) {
                    VirtualViewManager.this.invalidateSvgView((VirtualView) view);
                }
            }

            public void onChildViewRemoved(View view, View view2) {
                if (view instanceof VirtualView) {
                    VirtualViewManager.this.invalidateSvgView((VirtualView) view);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(VirtualView virtualView) {
        super.onAfterUpdateTransaction(virtualView);
        invalidateSvgView(virtualView);
    }

    /* renamed from: com.horcrux.svg.VirtualViewManager$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass;

        /* JADX WARNING: Can't wrap try/catch for region: R(56:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|(3:55|56|58)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(58:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|58) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00cc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00d8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00e4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00f0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00fc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x0108 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x0114 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x0120 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x012c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x0138 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x0144 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.horcrux.svg.VirtualViewManager$SVGClass[] r0 = com.horcrux.svg.VirtualViewManager.SVGClass.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass = r0
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGGroup     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x001d }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGPath     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGCircle     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGEllipse     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x003e }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGLine     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGRect     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGText     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGTSpan     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x006c }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGTextPath     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGImage     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGClipPath     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGDefs     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x009c }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGUse     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGSymbol     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGLinearGradient     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGRadialGradient     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGPattern     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x00d8 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGMask     // Catch:{ NoSuchFieldError -> 0x00d8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d8 }
            L_0x00d8:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x00e4 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGFilter     // Catch:{ NoSuchFieldError -> 0x00e4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e4 }
                r2 = 19
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e4 }
            L_0x00e4:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x00f0 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGFeBlend     // Catch:{ NoSuchFieldError -> 0x00f0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f0 }
                r2 = 20
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00f0 }
            L_0x00f0:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x00fc }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGFeColorMatrix     // Catch:{ NoSuchFieldError -> 0x00fc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fc }
                r2 = 21
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00fc }
            L_0x00fc:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0108 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGFeComposite     // Catch:{ NoSuchFieldError -> 0x0108 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0108 }
                r2 = 22
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0108 }
            L_0x0108:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0114 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGFeFlood     // Catch:{ NoSuchFieldError -> 0x0114 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0114 }
                r2 = 23
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0114 }
            L_0x0114:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0120 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGFeGaussianBlur     // Catch:{ NoSuchFieldError -> 0x0120 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0120 }
                r2 = 24
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0120 }
            L_0x0120:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x012c }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGFeMerge     // Catch:{ NoSuchFieldError -> 0x012c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x012c }
                r2 = 25
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x012c }
            L_0x012c:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0138 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGFeOffset     // Catch:{ NoSuchFieldError -> 0x0138 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0138 }
                r2 = 26
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0138 }
            L_0x0138:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0144 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGMarker     // Catch:{ NoSuchFieldError -> 0x0144 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0144 }
                r2 = 27
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0144 }
            L_0x0144:
                int[] r0 = $SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass     // Catch:{ NoSuchFieldError -> 0x0150 }
                com.horcrux.svg.VirtualViewManager$SVGClass r1 = com.horcrux.svg.VirtualViewManager.SVGClass.RNSVGForeignObject     // Catch:{ NoSuchFieldError -> 0x0150 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0150 }
                r2 = 28
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0150 }
            L_0x0150:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.VirtualViewManager.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public VirtualView createViewInstance(ThemedReactContext themedReactContext) {
        switch (AnonymousClass2.$SwitchMap$com$horcrux$svg$VirtualViewManager$SVGClass[this.svgClass.ordinal()]) {
            case 1:
                return new GroupView(themedReactContext);
            case 2:
                return new PathView(themedReactContext);
            case 3:
                return new CircleView(themedReactContext);
            case 4:
                return new EllipseView(themedReactContext);
            case 5:
                return new LineView(themedReactContext);
            case 6:
                return new RectView(themedReactContext);
            case 7:
                return new TextView(themedReactContext);
            case 8:
                return new TSpanView(themedReactContext);
            case 9:
                return new TextPathView(themedReactContext);
            case 10:
                return new ImageView(themedReactContext);
            case 11:
                return new ClipPathView(themedReactContext);
            case 12:
                return new DefsView(themedReactContext);
            case 13:
                return new UseView(themedReactContext);
            case 14:
                return new SymbolView(themedReactContext);
            case 15:
                return new LinearGradientView(themedReactContext);
            case 16:
                return new RadialGradientView(themedReactContext);
            case 17:
                return new PatternView(themedReactContext);
            case 18:
                return new MaskView(themedReactContext);
            case 19:
                return new FilterView(themedReactContext);
            case 20:
                return new FeBlendView(themedReactContext);
            case CommonStatusCodes.RECONNECTION_TIMED_OUT_DURING_UPDATE:
                return new FeColorMatrixView(themedReactContext);
            case 22:
                return new FeCompositeView(themedReactContext);
            case ConnectionResult.API_DISABLED:
                return new FeFloodView(themedReactContext);
            case ConnectionResult.API_DISABLED_FOR_CONNECTION:
                return new FeGaussianBlurView(themedReactContext);
            case 25:
                return new FeMergeView(themedReactContext);
            case 26:
                return new FeOffsetView(themedReactContext);
            case 27:
                return new MarkerView(themedReactContext);
            case OutsetBoxShadowDrawableKt.MIN_OUTSET_BOX_SHADOW_SDK_VERSION:
                return new ForeignObjectView(themedReactContext);
            default:
                throw new IllegalStateException("Unexpected type " + this.svgClass.toString());
        }
    }

    static void setRenderableView(int i, RenderableView renderableView) {
        mTagToRenderableView.put(i, renderableView);
        SparseArray<Runnable> sparseArray = mTagToRunnable;
        Runnable runnable = sparseArray.get(i);
        if (runnable != null) {
            runnable.run();
            sparseArray.delete(i);
        }
    }

    static void runWhenViewIsAvailable(int i, Runnable runnable) {
        mTagToRunnable.put(i, runnable);
    }

    static RenderableView getRenderableViewByTag(int i) {
        return mTagToRenderableView.get(i);
    }

    public void onDropViewInstance(VirtualView virtualView) {
        super.onDropViewInstance(virtualView);
        mTagToRenderableView.remove(virtualView.getId());
    }
}
