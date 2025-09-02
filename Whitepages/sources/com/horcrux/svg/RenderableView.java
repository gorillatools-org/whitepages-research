package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.ViewParent;
import androidx.customview.widget.ExploreByTouchHelper;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.touch.ReactHitSlopView;
import com.facebook.react.uimanager.PointerEvents;
import com.horcrux.svg.Brush;
import com.horcrux.svg.MaskView;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RenderableView extends VirtualView implements ReactHitSlopView {
    private static final int CAP_BUTT = 0;
    static final int CAP_ROUND = 1;
    private static final int CAP_SQUARE = 2;
    private static final int FILL_RULE_EVENODD = 0;
    static final int FILL_RULE_NONZERO = 1;
    private static final int JOIN_BEVEL = 2;
    private static final int JOIN_MITER = 0;
    static final int JOIN_ROUND = 1;
    private static final int VECTOR_EFFECT_DEFAULT = 0;
    private static final int VECTOR_EFFECT_NON_SCALING_STROKE = 1;
    static RenderableView contextElement;
    private static final Pattern regex = Pattern.compile("[0-9.-]+");
    public ReadableArray fill;
    public float fillOpacity = 1.0f;
    public Path.FillType fillRule = Path.FillType.WINDING;
    private ArrayList<String> mAttributeList;
    private RenderableView mCaller;
    private int mCurrentColor = 0;
    String mFilter;
    private ArrayList<String> mLastMergedList;
    private ArrayList<Object> mOriginProperties;
    private ArrayList<String> mPropList;
    public ReadableArray stroke;
    public SVGLength[] strokeDasharray;
    public float strokeDashoffset = 0.0f;
    public Paint.Cap strokeLinecap = Paint.Cap.BUTT;
    public Paint.Join strokeLinejoin = Paint.Join.MITER;
    public float strokeMiterlimit = 4.0f;
    public float strokeOpacity = 1.0f;
    public SVGLength strokeWidth = new SVGLength(1.0d);
    public int vectorEffect = 0;

    /* access modifiers changed from: package-private */
    public abstract Path getPath(Canvas canvas, Paint paint);

    RenderableView(ReactContext reactContext) {
        super(reactContext);
        setPivotX(0.0f);
        setPivotY(0.0f);
    }

    public Rect getHitSlopRect() {
        if (this.mPointerEvents == PointerEvents.BOX_NONE) {
            return new Rect(ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID, ExploreByTouchHelper.INVALID_ID);
        }
        return null;
    }

    public void setId(int i) {
        super.setId(i);
        VirtualViewManager.setRenderableView(i, this);
    }

    public void setVectorEffect(int i) {
        this.vectorEffect = i;
        invalidate();
    }

    public void setCurrentColor(Integer num) {
        this.mCurrentColor = num != null ? num.intValue() : 0;
        invalidate();
        clearChildCache();
    }

    /* access modifiers changed from: package-private */
    public int getCurrentColor() {
        int i = this.mCurrentColor;
        if (i != 0) {
            return i;
        }
        RenderableView renderableView = this.mCaller;
        if (renderableView != null) {
            return renderableView.getCurrentColor();
        }
        ViewParent parent = getParent();
        if (parent instanceof VirtualView) {
            return ((RenderableView) parent).getCurrentColor();
        }
        if (parent instanceof SvgView) {
            return ((SvgView) parent).mCurrentColor;
        }
        return 0;
    }

    public void setFill(Dynamic dynamic) {
        if (dynamic == null || dynamic.isNull()) {
            this.fill = null;
            invalidate();
            return;
        }
        ReadableType type = dynamic.getType();
        if (type.equals(ReadableType.Map)) {
            setFill(dynamic.asMap());
            return;
        }
        int i = 0;
        if (type.equals(ReadableType.Number)) {
            this.fill = JavaOnlyArray.of(0, Integer.valueOf(dynamic.asInt()));
        } else if (type.equals(ReadableType.Array)) {
            this.fill = dynamic.asArray();
        } else {
            JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
            javaOnlyArray.pushInt(0);
            Matcher matcher = regex.matcher(dynamic.asString());
            while (matcher.find()) {
                double parseDouble = Double.parseDouble(matcher.group());
                int i2 = i + 1;
                if (i < 3) {
                    parseDouble /= 255.0d;
                }
                javaOnlyArray.pushDouble(parseDouble);
                i = i2;
            }
            this.fill = javaOnlyArray;
        }
        invalidate();
    }

    public void setFill(ReadableMap readableMap) {
        if (readableMap == null) {
            this.fill = null;
            invalidate();
            return;
        }
        int i = readableMap.getInt("type");
        if (i == 0) {
            ReadableType type = readableMap.getType("payload");
            if (type.equals(ReadableType.Number)) {
                this.fill = JavaOnlyArray.of(0, Integer.valueOf(readableMap.getInt("payload")));
            } else if (type.equals(ReadableType.Map)) {
                this.fill = JavaOnlyArray.of(0, readableMap.getMap("payload"));
            }
        } else if (i == 1) {
            this.fill = JavaOnlyArray.of(1, readableMap.getString("brushRef"));
        } else {
            this.fill = JavaOnlyArray.of(Integer.valueOf(i));
        }
        invalidate();
    }

    public void setFillOpacity(float f) {
        this.fillOpacity = f;
        invalidate();
    }

    public void setFillRule(int i) {
        if (i == 0) {
            this.fillRule = Path.FillType.EVEN_ODD;
        } else if (i != 1) {
            throw new JSApplicationIllegalArgumentException("fillRule " + i + " unrecognized");
        }
        invalidate();
    }

    public void setStroke(Dynamic dynamic) {
        if (dynamic == null || dynamic.isNull()) {
            this.stroke = null;
            invalidate();
        } else if (dynamic.getType().equals(ReadableType.Map)) {
            setStroke(dynamic.asMap());
        } else {
            ReadableType type = dynamic.getType();
            int i = 0;
            if (type.equals(ReadableType.Number)) {
                this.stroke = JavaOnlyArray.of(0, Integer.valueOf(dynamic.asInt()));
            } else if (type.equals(ReadableType.Array)) {
                this.stroke = dynamic.asArray();
            } else {
                JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
                javaOnlyArray.pushInt(0);
                Matcher matcher = regex.matcher(dynamic.asString());
                while (matcher.find()) {
                    double parseDouble = Double.parseDouble(matcher.group());
                    int i2 = i + 1;
                    if (i < 3) {
                        parseDouble /= 255.0d;
                    }
                    javaOnlyArray.pushDouble(parseDouble);
                    i = i2;
                }
                this.stroke = javaOnlyArray;
            }
            invalidate();
        }
    }

    public void setStroke(ReadableMap readableMap) {
        if (readableMap == null) {
            this.stroke = null;
            invalidate();
            return;
        }
        int i = readableMap.getInt("type");
        if (i == 0) {
            ReadableType type = readableMap.getType("payload");
            if (type.equals(ReadableType.Number)) {
                this.stroke = JavaOnlyArray.of(0, Integer.valueOf(readableMap.getInt("payload")));
            } else if (type.equals(ReadableType.Map)) {
                this.stroke = JavaOnlyArray.of(0, readableMap.getMap("payload"));
            }
        } else if (i == 1) {
            this.stroke = JavaOnlyArray.of(1, readableMap.getString("brushRef"));
        } else {
            this.stroke = JavaOnlyArray.of(Integer.valueOf(i));
        }
        invalidate();
    }

    public void setStrokeOpacity(float f) {
        this.strokeOpacity = f;
        invalidate();
    }

    public void setStrokeDasharray(Dynamic dynamic) {
        ArrayList arrayFrom = SVGLength.arrayFrom(dynamic);
        if (arrayFrom != null) {
            this.strokeDasharray = (SVGLength[]) arrayFrom.toArray(new SVGLength[0]);
        } else {
            this.strokeDasharray = null;
        }
        invalidate();
    }

    public void setStrokeDashoffset(float f) {
        this.strokeDashoffset = f * this.mScale;
        invalidate();
    }

    public void setStrokeWidth(Dynamic dynamic) {
        this.strokeWidth = dynamic.isNull() ? new SVGLength(1.0d) : SVGLength.from(dynamic);
        invalidate();
    }

    public void setStrokeMiterlimit(float f) {
        this.strokeMiterlimit = f;
        invalidate();
    }

    public void setStrokeLinecap(int i) {
        if (i == 0) {
            this.strokeLinecap = Paint.Cap.BUTT;
        } else if (i == 1) {
            this.strokeLinecap = Paint.Cap.ROUND;
        } else if (i == 2) {
            this.strokeLinecap = Paint.Cap.SQUARE;
        } else {
            throw new JSApplicationIllegalArgumentException("strokeLinecap " + i + " unrecognized");
        }
        invalidate();
    }

    public void setStrokeLinejoin(int i) {
        if (i == 0) {
            this.strokeLinejoin = Paint.Join.MITER;
        } else if (i == 1) {
            this.strokeLinejoin = Paint.Join.ROUND;
        } else if (i == 2) {
            this.strokeLinejoin = Paint.Join.BEVEL;
        } else {
            throw new JSApplicationIllegalArgumentException("strokeLinejoin " + i + " unrecognized");
        }
        invalidate();
    }

    public void setPropList(ReadableArray readableArray) {
        if (readableArray != null) {
            ArrayList<String> arrayList = new ArrayList<>();
            this.mAttributeList = arrayList;
            this.mPropList = arrayList;
            for (int i = 0; i < readableArray.size(); i++) {
                this.mPropList.add(readableArray.getString(i));
            }
        }
        invalidate();
    }

    public void setFilter(String str) {
        this.mFilter = str;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void render(Canvas canvas, Paint paint, float f) {
        RectF rectF;
        MaskView maskView = this.mMask != null ? (MaskView) getSvgView().getDefinedMask(this.mMask) : null;
        FilterView filterView = this.mFilter != null ? (FilterView) getSvgView().getDefinedFilter(this.mFilter) : null;
        if (maskView == null && filterView == null) {
            draw(canvas, paint, f);
            return;
        }
        if (filterView != null) {
            Paint paint2 = new Paint(2);
            canvas.saveLayer((RectF) null, paint2);
            Bitmap currentBitmap = getSvgView().getCurrentBitmap();
            Bitmap createBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap);
            canvas2.setMatrix(canvas.getMatrix());
            draw(canvas2, paint, f);
            initBounds();
            RectF clientRect = getClientRect();
            if (!(this instanceof ImageView) || clientRect != null) {
                Bitmap applyFilter = filterView.applyFilter(createBitmap, currentBitmap, clientRect);
                int save = canvas.save();
                canvas.setMatrix((Matrix) null);
                canvas.drawBitmap(applyFilter, 0.0f, 0.0f, paint2);
                canvas.restoreToCount(save);
            } else {
                return;
            }
        } else {
            canvas.saveLayer((RectF) null, new Paint());
            draw(canvas, paint, f);
        }
        if (maskView != null) {
            Paint paint3 = new Paint();
            paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.saveLayer((RectF) null, paint3);
            if (maskView.getMaskType() == MaskView.MaskType.LUMINANCE) {
                Paint paint4 = new Paint();
                paint4.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.2125f, 0.7154f, 0.0721f, 0.0f, 0.0f})));
                canvas.saveLayer((RectF) null, paint4);
            } else {
                canvas.saveLayer((RectF) null, paint);
            }
            if (maskView.getMaskUnits() == Brush.BrushUnits.USER_SPACE_ON_USE) {
                float relativeOnWidth = (float) relativeOnWidth(maskView.mX);
                float relativeOnHeight = (float) relativeOnHeight(maskView.mY);
                rectF = new RectF(relativeOnWidth, relativeOnHeight, ((float) relativeOnWidth(maskView.mW)) + relativeOnWidth, ((float) relativeOnHeight(maskView.mH)) + relativeOnHeight);
            } else {
                RectF clientRect2 = getClientRect();
                if (!(this instanceof ImageView) || clientRect2 != null) {
                    this.mInvCTM.mapRect(clientRect2);
                    float relativeOnFraction = (float) relativeOnFraction(maskView.mX, clientRect2.width());
                    float relativeOnFraction2 = (float) relativeOnFraction(maskView.mY, clientRect2.height());
                    float f2 = clientRect2.left;
                    float f3 = clientRect2.top;
                    rectF = new RectF(f2 + relativeOnFraction, f3 + relativeOnFraction2, f2 + relativeOnFraction + ((float) relativeOnFraction(maskView.mW, clientRect2.width())), f3 + relativeOnFraction2 + ((float) relativeOnFraction(maskView.mH, clientRect2.height())));
                } else {
                    return;
                }
            }
            canvas.clipRect(rectF);
            maskView.draw(canvas, paint, 1.0f);
            canvas.restore();
            canvas.saveLayer((RectF) null, paint3);
            canvas.clipRect(rectF);
            maskView.draw(canvas, paint, 1.0f);
            canvas.restore();
            canvas.restore();
        }
        canvas.restore();
    }

    /* access modifiers changed from: package-private */
    public void draw(Canvas canvas, Paint paint, float f) {
        float f2 = f * this.mOpacity;
        boolean z = false;
        boolean z2 = this.mPath == null;
        if (z2) {
            Path path = getPath(canvas, paint);
            this.mPath = path;
            path.setFillType(this.fillRule);
        }
        if (this.vectorEffect == 1) {
            z = true;
        }
        Path path2 = this.mPath;
        if (z) {
            path2 = new Path();
            this.mPath.transform(this.mCTM, path2);
            canvas.setMatrix((Matrix) null);
        }
        if (z2 || path2 != this.mPath) {
            RectF rectF = new RectF();
            this.mBox = rectF;
            path2.computeBounds(rectF, true);
        }
        RectF rectF2 = new RectF(this.mBox);
        this.mCTM.mapRect(rectF2);
        setClientRect(rectF2);
        clip(canvas, paint);
        if (setupFillPaint(paint, this.fillOpacity * f2)) {
            if (z2) {
                Path path3 = new Path();
                this.mFillPath = path3;
                paint.getFillPath(path2, path3);
            }
            canvas.drawPath(path2, paint);
        }
        if (setupStrokePaint(paint, this.strokeOpacity * f2)) {
            if (z2) {
                Path path4 = new Path();
                this.mStrokePath = path4;
                paint.getFillPath(path2, path4);
            }
            canvas.drawPath(path2, paint);
        }
        renderMarkers(canvas, paint, f2);
    }

    /* access modifiers changed from: package-private */
    public void renderMarkers(Canvas canvas, Paint paint, float f) {
        MarkerView markerView = (MarkerView) getSvgView().getDefinedMarker(this.mMarkerStart);
        MarkerView markerView2 = (MarkerView) getSvgView().getDefinedMarker(this.mMarkerMid);
        MarkerView markerView3 = (MarkerView) getSvgView().getDefinedMarker(this.mMarkerEnd);
        ArrayList<PathElement> arrayList = this.elements;
        if (arrayList == null) {
            return;
        }
        if (markerView != null || markerView2 != null || markerView3 != null) {
            contextElement = this;
            ArrayList fromPath = RNSVGMarkerPosition.fromPath(arrayList);
            SVGLength sVGLength = this.strokeWidth;
            float relativeOnOther = (float) (sVGLength != null ? relativeOnOther(sVGLength) : 1.0d);
            this.mMarkerPath = new Path();
            Iterator it = fromPath.iterator();
            while (it.hasNext()) {
                RNSVGMarkerPosition rNSVGMarkerPosition = (RNSVGMarkerPosition) it.next();
                int i = AnonymousClass1.$SwitchMap$com$horcrux$svg$RNSVGMarkerType[rNSVGMarkerPosition.type.ordinal()];
                MarkerView markerView4 = i != 1 ? i != 2 ? i != 3 ? null : markerView3 : markerView2 : markerView;
                if (markerView4 != null) {
                    markerView4.renderMarker(canvas, paint, f, rNSVGMarkerPosition, relativeOnOther);
                    this.mMarkerPath.addPath(markerView4.getPath(canvas, paint), markerView4.markerTransform);
                }
            }
            contextElement = null;
        }
    }

    /* renamed from: com.horcrux.svg.RenderableView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$RNSVGMarkerType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.horcrux.svg.RNSVGMarkerType[] r0 = com.horcrux.svg.RNSVGMarkerType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$horcrux$svg$RNSVGMarkerType = r0
                com.horcrux.svg.RNSVGMarkerType r1 = com.horcrux.svg.RNSVGMarkerType.kStartMarker     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$horcrux$svg$RNSVGMarkerType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.horcrux.svg.RNSVGMarkerType r1 = com.horcrux.svg.RNSVGMarkerType.kMidMarker     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$horcrux$svg$RNSVGMarkerType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.horcrux.svg.RNSVGMarkerType r1 = com.horcrux.svg.RNSVGMarkerType.kEndMarker     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.RenderableView.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean setupFillPaint(Paint paint, float f) {
        ReadableArray readableArray = this.fill;
        if (readableArray == null || readableArray.size() <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(385);
        paint.setStyle(Paint.Style.FILL);
        setupPaint(paint, f, this.fill);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setupStrokePaint(Paint paint, float f) {
        ReadableArray readableArray;
        paint.reset();
        double relativeOnOther = relativeOnOther(this.strokeWidth);
        if (relativeOnOther == 0.0d || (readableArray = this.stroke) == null || readableArray.size() == 0) {
            return false;
        }
        paint.setFlags(385);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(this.strokeLinecap);
        paint.setStrokeJoin(this.strokeLinejoin);
        paint.setStrokeMiter(this.strokeMiterlimit * this.mScale);
        paint.setStrokeWidth((float) relativeOnOther);
        setupPaint(paint, f, this.stroke);
        SVGLength[] sVGLengthArr = this.strokeDasharray;
        if (sVGLengthArr == null) {
            return true;
        }
        int length = sVGLengthArr.length;
        float[] fArr = new float[length];
        for (int i = 0; i < length; i++) {
            fArr[i] = (float) relativeOnOther(this.strokeDasharray[i]);
        }
        paint.setPathEffect(new DashPathEffect(fArr, this.strokeDashoffset));
        return true;
    }

    private void setupPaint(Paint paint, float f, ReadableArray readableArray) {
        int i;
        ReadableArray readableArray2;
        RenderableView renderableView;
        ReadableArray readableArray3;
        int i2 = readableArray.getInt(0);
        if (i2 != 0) {
            if (i2 == 1) {
                Brush definedBrush = getSvgView().getDefinedBrush(readableArray.getString(1));
                if (definedBrush != null) {
                    definedBrush.setupPaint(paint, this.mBox, this.mScale, f);
                }
            } else if (i2 == 2) {
                int currentColor = getCurrentColor();
                paint.setColor((Math.round(((float) (currentColor >>> 24)) * f) << 24) | (currentColor & 16777215));
            } else if (i2 == 3) {
                RenderableView renderableView2 = contextElement;
                if (renderableView2 != null && (readableArray2 = renderableView2.fill) != null) {
                    setupPaint(paint, f, readableArray2);
                }
            } else if (i2 == 4 && (renderableView = contextElement) != null && (readableArray3 = renderableView.stroke) != null) {
                setupPaint(paint, f, readableArray3);
            }
        } else if (readableArray.size() == 2) {
            if (readableArray.getType(1) == ReadableType.Map) {
                i = ColorPropConverter.getColor(readableArray.getMap(1), getContext()).intValue();
            } else {
                i = readableArray.getInt(1);
            }
            paint.setColor((Math.round(((float) (i >>> 24)) * f) << 24) | (i & 16777215));
        } else {
            paint.setARGB((int) (readableArray.size() > 4 ? readableArray.getDouble(4) * ((double) f) * 255.0d : (double) (f * 255.0f)), (int) (readableArray.getDouble(1) * 255.0d), (int) (readableArray.getDouble(2) * 255.0d), (int) (readableArray.getDouble(3) * 255.0d));
        }
    }

    /* access modifiers changed from: package-private */
    public int hitTest(float[] fArr) {
        Region region;
        Region region2;
        if (this.mPath == null || !this.mInvertible || this.mPointerEvents == PointerEvents.NONE) {
            return -1;
        }
        float[] fArr2 = new float[2];
        this.mInvMatrix.mapPoints(fArr2, fArr);
        this.mInvTransform.mapPoints(fArr2);
        int round = Math.round(fArr2[0]);
        int round2 = Math.round(fArr2[1]);
        initBounds();
        Region region3 = this.mRegion;
        if ((region3 != null && region3.contains(round, round2)) || ((region = this.mStrokeRegion) != null && (region.contains(round, round2) || ((region2 = this.mMarkerRegion) != null && region2.contains(round, round2))))) {
            if (getClipPath() == null || this.mClipRegion.contains(round, round2)) {
                return getId();
            }
            return -1;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void initBounds() {
        if (this.mRegion == null && this.mFillPath != null) {
            RectF rectF = new RectF();
            this.mFillBounds = rectF;
            this.mFillPath.computeBounds(rectF, true);
            this.mRegion = getRegion(this.mFillPath, this.mFillBounds);
        }
        if (this.mRegion == null && this.mPath != null) {
            RectF rectF2 = new RectF();
            this.mFillBounds = rectF2;
            this.mPath.computeBounds(rectF2, true);
            this.mRegion = getRegion(this.mPath, this.mFillBounds);
        }
        if (this.mStrokeRegion == null && this.mStrokePath != null) {
            RectF rectF3 = new RectF();
            this.mStrokeBounds = rectF3;
            this.mStrokePath.computeBounds(rectF3, true);
            this.mStrokeRegion = getRegion(this.mStrokePath, this.mStrokeBounds);
        }
        if (this.mMarkerRegion == null && this.mMarkerPath != null) {
            RectF rectF4 = new RectF();
            this.mMarkerBounds = rectF4;
            this.mMarkerPath.computeBounds(rectF4, true);
            this.mMarkerRegion = getRegion(this.mMarkerPath, this.mMarkerBounds);
        }
        Path clipPath = getClipPath();
        if (clipPath != null && this.mClipRegionPath != clipPath) {
            this.mClipRegionPath = clipPath;
            RectF rectF5 = new RectF();
            this.mClipBounds = rectF5;
            clipPath.computeBounds(rectF5, true);
            this.mClipRegion = getRegion(clipPath, this.mClipBounds);
        }
    }

    /* access modifiers changed from: package-private */
    public Region getRegion(Path path, RectF rectF) {
        Region region = new Region();
        region.setPath(path, new Region((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom)));
        return region;
    }

    private ArrayList<String> getAttributeList() {
        return this.mAttributeList;
    }

    /* access modifiers changed from: package-private */
    public void mergeProperties(RenderableView renderableView) {
        this.mCaller = renderableView;
        ArrayList<String> attributeList = renderableView.getAttributeList();
        if (attributeList != null && attributeList.size() != 0) {
            this.mOriginProperties = new ArrayList<>();
            this.mAttributeList = this.mPropList == null ? new ArrayList<>() : new ArrayList<>(this.mPropList);
            int size = attributeList.size();
            int i = 0;
            while (i < size) {
                try {
                    String str = attributeList.get(i);
                    Field field = getClass().getField(str);
                    Object obj = field.get(renderableView);
                    this.mOriginProperties.add(field.get(this));
                    if (!hasOwnProperty(str)) {
                        this.mAttributeList.add(str);
                        field.set(this, obj);
                    }
                    i++;
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
            this.mLastMergedList = attributeList;
        }
    }

    /* access modifiers changed from: package-private */
    public void resetProperties() {
        ArrayList<String> arrayList = this.mLastMergedList;
        if (arrayList != null && this.mOriginProperties != null) {
            try {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    getClass().getField(this.mLastMergedList.get(size)).set(this, this.mOriginProperties.get(size));
                }
                this.mLastMergedList = null;
                this.mOriginProperties = null;
                this.mAttributeList = this.mPropList;
                this.mCaller = null;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private boolean hasOwnProperty(String str) {
        ArrayList<String> arrayList = this.mAttributeList;
        return arrayList != null && arrayList.contains(str);
    }
}
