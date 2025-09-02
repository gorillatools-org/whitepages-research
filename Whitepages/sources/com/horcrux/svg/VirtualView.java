package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.OnLayoutEvent;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.view.ReactViewGroup;
import com.horcrux.svg.SVGLength;
import java.util.ArrayList;

@SuppressLint({"ViewConstructor"})
public abstract class VirtualView extends ReactViewGroup {
    private static final int CLIP_RULE_EVENODD = 0;
    static final int CLIP_RULE_NONZERO = 1;
    private static final double M_SQRT1_2l = 0.7071067811865476d;
    private static final float[] sRawMatrix = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private double canvasDiagonal = -1.0d;
    private float canvasHeight = -1.0f;
    private float canvasWidth = -1.0f;
    ArrayList<PathElement> elements;
    private double fontSize = -1.0d;
    private GlyphContext glyphContext;
    RectF mBox;
    Matrix mCTM = new Matrix();
    boolean mCTMInvertible = true;
    private Path mCachedClipPath;
    private RectF mClientRect;
    RectF mClipBounds;
    private String mClipPath;
    Region mClipRegion;
    Path mClipRegionPath;
    int mClipRule;
    final ReactContext mContext;
    String mDisplay;
    RectF mFillBounds;
    Path mFillPath;
    Matrix mInvCTM = new Matrix();
    Matrix mInvMatrix = new Matrix();
    final Matrix mInvTransform = new Matrix();
    boolean mInvertible = true;
    RectF mMarkerBounds;
    String mMarkerEnd;
    String mMarkerMid;
    Path mMarkerPath;
    Region mMarkerRegion;
    String mMarkerStart;
    String mMask;
    Matrix mMatrix = new Matrix();
    String mName;
    float mOpacity = 1.0f;
    Path mPath;
    PointerEvents mPointerEvents;
    Region mRegion;
    private boolean mResponsible;
    final float mScale;
    RectF mStrokeBounds;
    Path mStrokePath;
    Region mStrokeRegion;
    private GroupView mTextRoot;
    private SvgView svgView;

    /* access modifiers changed from: package-private */
    public abstract void draw(Canvas canvas, Paint paint, float f);

    /* access modifiers changed from: package-private */
    public abstract Path getPath(Canvas canvas, Paint paint);

    /* access modifiers changed from: package-private */
    public abstract int hitTest(float[] fArr);

    VirtualView(ReactContext reactContext) {
        super(reactContext);
        this.mContext = reactContext;
        this.mScale = DisplayMetricsHolder.getScreenDisplayMetrics().density;
    }

    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (this.mClientRect != null) {
            SvgView svgView2 = getSvgView();
            int[] iArr = new int[2];
            getSvgView().getLocationOnScreen(iArr);
            Rect rect = new Rect();
            boolean z = false;
            rect.left = iArr[0] + ((int) Math.floor((double) this.mClientRect.left));
            rect.top = iArr[1] + ((int) Math.floor((double) this.mClientRect.top));
            rect.right = rect.left + ((int) Math.ceil((double) this.mClientRect.width()));
            rect.bottom = rect.top + ((int) Math.ceil((double) this.mClientRect.height()));
            Rect rect2 = new Rect();
            if (svgView2.getGlobalVisibleRect(rect2) && rect.intersect(rect2)) {
                z = true;
            }
            String canonicalName = getClass().getCanonicalName();
            accessibilityNodeInfo.setBoundsInScreen(rect);
            accessibilityNodeInfo.setClassName(canonicalName);
            accessibilityNodeInfo.setVisibleToUser(z);
        }
    }

    public void invalidate() {
        if (!(this instanceof RenderableView) || this.mPath != null) {
            clearCache();
            clearParentCache();
            super.invalidate();
        }
    }

    /* access modifiers changed from: package-private */
    public void clearCache() {
        this.canvasDiagonal = -1.0d;
        this.canvasHeight = -1.0f;
        this.canvasWidth = -1.0f;
        this.fontSize = -1.0d;
        this.mStrokeRegion = null;
        this.mMarkerRegion = null;
        this.mRegion = null;
        this.mPath = null;
    }

    /* access modifiers changed from: package-private */
    public void clearChildCache() {
        clearCache();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof VirtualView) {
                ((VirtualView) childAt).clearChildCache();
            }
        }
    }

    private void clearParentCache() {
        VirtualView virtualView = this;
        while (true) {
            ViewParent parent = virtualView.getParent();
            if (parent instanceof VirtualView) {
                virtualView = (VirtualView) parent;
                if (virtualView.mPath != null) {
                    virtualView.clearCache();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public GroupView getTextRoot() {
        if (this.mTextRoot == null) {
            VirtualView virtualView = this;
            while (true) {
                if (virtualView == null) {
                    break;
                }
                if (virtualView instanceof GroupView) {
                    GroupView groupView = (GroupView) virtualView;
                    if (groupView.getGlyphContext() != null) {
                        this.mTextRoot = groupView;
                        break;
                    }
                }
                ViewParent parent = virtualView.getParent();
                if (!(parent instanceof VirtualView)) {
                    virtualView = null;
                } else {
                    virtualView = (VirtualView) parent;
                }
            }
        }
        return this.mTextRoot;
    }

    /* access modifiers changed from: package-private */
    public GroupView getParentTextRoot() {
        ViewParent parent = getParent();
        if (!(parent instanceof VirtualView)) {
            return null;
        }
        return ((VirtualView) parent).getTextRoot();
    }

    private double getFontSizeFromContext() {
        double d = this.fontSize;
        if (d != -1.0d) {
            return d;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            return 12.0d;
        }
        if (this.glyphContext == null) {
            this.glyphContext = textRoot.getGlyphContext();
        }
        double fontSize2 = this.glyphContext.getFontSize();
        this.fontSize = fontSize2;
        return fontSize2;
    }

    /* access modifiers changed from: package-private */
    public void render(Canvas canvas, Paint paint, float f) {
        draw(canvas, paint, f);
    }

    /* access modifiers changed from: package-private */
    public int saveAndSetupCanvas(Canvas canvas, Matrix matrix) {
        int save = canvas.save();
        this.mCTM.set(this.mMatrix);
        canvas.concat(this.mCTM);
        this.mCTM.preConcat(matrix);
        this.mCTMInvertible = this.mCTM.invert(this.mInvCTM);
        return save;
    }

    /* access modifiers changed from: package-private */
    public void restoreCanvas(Canvas canvas, int i) {
        canvas.restoreToCount(i);
    }

    public void setName(String str) {
        this.mName = str;
        invalidate();
    }

    public void setDisplay(String str) {
        this.mDisplay = str;
        invalidate();
    }

    public void setMask(String str) {
        this.mMask = str;
        invalidate();
    }

    public void setMarkerStart(String str) {
        this.mMarkerStart = str;
        invalidate();
    }

    public void setMarkerMid(String str) {
        this.mMarkerMid = str;
        invalidate();
    }

    public void setMarkerEnd(String str) {
        this.mMarkerEnd = str;
        invalidate();
    }

    public void setClipPath(String str) {
        this.mCachedClipPath = null;
        this.mClipPath = str;
        invalidate();
    }

    public void setClipRule(int i) {
        this.mClipRule = i;
        invalidate();
    }

    public void setOpacity(float f) {
        this.mOpacity = f;
        invalidate();
    }

    public void setMatrix(Dynamic dynamic) {
        setMatrix((dynamic.isNull() || !dynamic.getType().equals(ReadableType.Array)) ? null : dynamic.asArray());
    }

    public void setMatrix(ReadableArray readableArray) {
        if (readableArray != null) {
            float[] fArr = sRawMatrix;
            int matrixData = PropHelper.toMatrixData(readableArray, fArr, this.mScale);
            if (matrixData == 6) {
                if (this.mMatrix == null) {
                    this.mMatrix = new Matrix();
                    this.mInvMatrix = new Matrix();
                }
                this.mMatrix.setValues(fArr);
                this.mInvertible = this.mMatrix.invert(this.mInvMatrix);
            } else if (matrixData != -1) {
                FLog.w(ReactConstants.TAG, "RNSVG: Transform matrices must be of size 6");
            }
        } else {
            this.mMatrix.reset();
            this.mInvMatrix.reset();
            this.mInvertible = true;
        }
        super.invalidate();
        clearParentCache();
    }

    public void setResponsible(boolean z) {
        this.mResponsible = z;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public Path getClipPath() {
        return this.mCachedClipPath;
    }

    /* access modifiers changed from: package-private */
    public Path getClipPath(Canvas canvas, Paint paint) {
        Path path;
        if (this.mClipPath != null) {
            ClipPathView clipPathView = (ClipPathView) getSvgView().getDefinedClipPath(this.mClipPath);
            if (clipPathView != null) {
                if (this.mClipRule == 0) {
                    path = clipPathView.getPath(canvas, paint);
                } else {
                    path = clipPathView.getPath(canvas, paint, Region.Op.UNION);
                }
                path.transform(clipPathView.mMatrix);
                int i = this.mClipRule;
                if (i == 0) {
                    path.setFillType(Path.FillType.EVEN_ODD);
                } else if (i != 1) {
                    FLog.w(ReactConstants.TAG, "RNSVG: clipRule: " + this.mClipRule + " unrecognized");
                }
                this.mCachedClipPath = path;
            } else {
                FLog.w(ReactConstants.TAG, "RNSVG: Undefined clipPath: " + this.mClipPath);
            }
        }
        return getClipPath();
    }

    /* access modifiers changed from: package-private */
    public void clip(Canvas canvas, Paint paint) {
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isResponsible() {
        return this.mResponsible;
    }

    public SvgView getSvgView() {
        SvgView svgView2 = this.svgView;
        if (svgView2 != null) {
            return svgView2;
        }
        ViewParent parent = getParent();
        if (parent == null) {
            return null;
        }
        if (parent instanceof SvgView) {
            this.svgView = (SvgView) parent;
        } else if (parent instanceof VirtualView) {
            this.svgView = ((VirtualView) parent).getSvgView();
        } else {
            FLog.e(ReactConstants.TAG, "RNSVG: " + getClass().getName() + " should be descendant of a SvgView.");
        }
        return this.svgView;
    }

    /* access modifiers changed from: package-private */
    public double relativeOnFraction(SVGLength sVGLength, float f) {
        double d;
        SVGLength.UnitType unitType = sVGLength.unit;
        if (unitType == SVGLength.UnitType.NUMBER) {
            d = sVGLength.value;
        } else if (unitType != SVGLength.UnitType.PERCENTAGE) {
            return fromRelativeFast(sVGLength);
        } else {
            d = sVGLength.value / 100.0d;
        }
        return d * ((double) f);
    }

    /* access modifiers changed from: package-private */
    public double relativeOn(SVGLength sVGLength, float f) {
        SVGLength.UnitType unitType = sVGLength.unit;
        if (unitType == SVGLength.UnitType.NUMBER) {
            return sVGLength.value * ((double) this.mScale);
        }
        if (unitType == SVGLength.UnitType.PERCENTAGE) {
            return (sVGLength.value / 100.0d) * ((double) f);
        }
        return fromRelativeFast(sVGLength);
    }

    /* access modifiers changed from: package-private */
    public double relativeOnWidth(SVGLength sVGLength) {
        return relativeOn(sVGLength, getCanvasWidth());
    }

    /* access modifiers changed from: package-private */
    public double relativeOnHeight(SVGLength sVGLength) {
        return relativeOn(sVGLength, getCanvasHeight());
    }

    /* access modifiers changed from: package-private */
    public double relativeOnOther(SVGLength sVGLength) {
        return relativeOn(sVGLength, (float) getCanvasDiagonal());
    }

    /* renamed from: com.horcrux.svg.VirtualView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$SVGLength$UnitType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.horcrux.svg.SVGLength$UnitType[] r0 = com.horcrux.svg.SVGLength.UnitType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$horcrux$svg$SVGLength$UnitType = r0
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.EMS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.EXS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.CM     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.MM     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.IN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.PT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$horcrux$svg$SVGLength$UnitType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.horcrux.svg.SVGLength$UnitType r1 = com.horcrux.svg.SVGLength.UnitType.PC     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.VirtualView.AnonymousClass1.<clinit>():void");
        }
    }

    private double fromRelativeFast(SVGLength sVGLength) {
        double d;
        switch (AnonymousClass1.$SwitchMap$com$horcrux$svg$SVGLength$UnitType[sVGLength.unit.ordinal()]) {
            case 1:
                d = getFontSizeFromContext();
                break;
            case 2:
                d = getFontSizeFromContext() / 2.0d;
                break;
            case 3:
                d = 35.43307d;
                break;
            case 4:
                d = 3.543307d;
                break;
            case 5:
                d = 90.0d;
                break;
            case 6:
                d = 1.25d;
                break;
            case 7:
                d = 15.0d;
                break;
            default:
                d = 1.0d;
                break;
        }
        return sVGLength.value * d * ((double) this.mScale);
    }

    private float getCanvasWidth() {
        float f = this.canvasWidth;
        if (f != -1.0f) {
            return f;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            this.canvasWidth = (float) getSvgView().getCanvasBounds().width();
        } else {
            this.canvasWidth = textRoot.getGlyphContext().getWidth();
        }
        return this.canvasWidth;
    }

    private float getCanvasHeight() {
        float f = this.canvasHeight;
        if (f != -1.0f) {
            return f;
        }
        GroupView textRoot = getTextRoot();
        if (textRoot == null) {
            this.canvasHeight = (float) getSvgView().getCanvasBounds().height();
        } else {
            this.canvasHeight = textRoot.getGlyphContext().getHeight();
        }
        return this.canvasHeight;
    }

    private double getCanvasDiagonal() {
        double d = this.canvasDiagonal;
        if (d != -1.0d) {
            return d;
        }
        double sqrt = Math.sqrt(Math.pow((double) getCanvasWidth(), 2.0d) + Math.pow((double) getCanvasHeight(), 2.0d)) * M_SQRT1_2l;
        this.canvasDiagonal = sqrt;
        return sqrt;
    }

    /* access modifiers changed from: package-private */
    public void saveDefinition() {
        if (this.mName != null) {
            getSvgView().defineTemplate(this, this.mName);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        RectF rectF = this.mClientRect;
        if (rectF != null) {
            i3 = (int) Math.ceil((double) rectF.width());
        } else {
            i3 = View.getDefaultSize(getSuggestedMinimumWidth(), i);
        }
        RectF rectF2 = this.mClientRect;
        if (rectF2 != null) {
            i4 = (int) Math.ceil((double) rectF2.height());
        } else {
            i4 = View.getDefaultSize(getSuggestedMinimumHeight(), i2);
        }
        setMeasuredDimension(i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        RectF rectF = this.mClientRect;
        if (rectF != null) {
            if (!(this instanceof GroupView)) {
                setLeft((int) Math.floor((double) rectF.left));
                setTop((int) Math.floor((double) this.mClientRect.top));
                setRight((int) Math.ceil((double) this.mClientRect.right));
                setBottom((int) Math.ceil((double) this.mClientRect.bottom));
            }
            setMeasuredDimension((int) Math.ceil((double) this.mClientRect.width()), (int) Math.ceil((double) this.mClientRect.height()));
        }
    }

    /* access modifiers changed from: package-private */
    public void setClientRect(RectF rectF) {
        SvgView svgView2;
        RectF rectF2 = this.mClientRect;
        if (rectF2 == null || !rectF2.equals(rectF)) {
            this.mClientRect = rectF;
            if (rectF != null) {
                int ceil = (int) Math.ceil((double) rectF.width());
                int ceil2 = (int) Math.ceil((double) this.mClientRect.height());
                int floor = (int) Math.floor((double) this.mClientRect.left);
                int floor2 = (int) Math.floor((double) this.mClientRect.top);
                int ceil3 = (int) Math.ceil((double) this.mClientRect.right);
                int ceil4 = (int) Math.ceil((double) this.mClientRect.bottom);
                setMeasuredDimension(ceil, ceil2);
                if (!(this instanceof GroupView) && (svgView2 = getSvgView()) != null) {
                    setLeft(Math.max(floor, 0));
                    setTop(Math.max(floor2, 0));
                    setRight(Math.min(ceil3, svgView2.getWidth()));
                    setBottom(Math.min(ceil4, svgView2.getHeight()));
                }
                EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(this.mContext, getId());
                if (eventDispatcherForReactTag != null) {
                    eventDispatcherForReactTag.dispatchEvent(OnLayoutEvent.obtain(getId(), floor, floor2, ceil, ceil2));
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public RectF getClientRect() {
        return this.mClientRect;
    }
}
