package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.ArrayList;

class GroupView extends RenderableView {
    ReadableMap mFont;
    private GlyphContext mGlyphContext;
    private Bitmap mLayerBitmap;
    private Canvas mLayerCanvas;
    private final Paint mLayerPaint = new Paint(1);

    public GroupView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setFont(Dynamic dynamic) {
        if (dynamic.getType() == ReadableType.Map) {
            this.mFont = dynamic.asMap();
        } else {
            this.mFont = null;
        }
        invalidate();
    }

    public void setFont(ReadableMap readableMap) {
        this.mFont = readableMap;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void setupGlyphContext(Canvas canvas) {
        RectF rectF = new RectF(canvas.getClipBounds());
        Matrix matrix = this.mMatrix;
        if (matrix != null) {
            matrix.mapRect(rectF);
        }
        this.mGlyphContext = new GlyphContext(this.mScale, rectF.width(), rectF.height());
    }

    /* access modifiers changed from: package-private */
    public GlyphContext getGlyphContext() {
        return this.mGlyphContext;
    }

    private static Object requireNonNull(Object obj) {
        obj.getClass();
        return obj;
    }

    /* access modifiers changed from: package-private */
    public GlyphContext getTextRootGlyphContext() {
        return ((GroupView) requireNonNull(getTextRoot())).getGlyphContext();
    }

    /* access modifiers changed from: package-private */
    public void pushGlyphContext() {
        getTextRootGlyphContext().pushContext(this, this.mFont);
    }

    /* access modifiers changed from: package-private */
    public void popGlyphContext() {
        getTextRootGlyphContext().popContext();
    }

    /* access modifiers changed from: package-private */
    public void draw(Canvas canvas, Paint paint, float f) {
        setupGlyphContext(canvas);
        clip(canvas, paint);
        drawGroup(canvas, paint, f);
        renderMarkers(canvas, paint, f);
    }

    /* access modifiers changed from: package-private */
    public void drawGroup(Canvas canvas, Paint paint, float f) {
        pushGlyphContext();
        SvgView svgView = getSvgView();
        RectF rectF = new RectF();
        if (this.mOpacity != 1.0f) {
            Bitmap bitmap = this.mLayerBitmap;
            if (bitmap == null) {
                this.mLayerBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
                this.mLayerCanvas = new Canvas(this.mLayerBitmap);
            } else {
                bitmap.recycle();
                Bitmap createBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
                this.mLayerBitmap = createBitmap;
                this.mLayerCanvas.setBitmap(createBitmap);
            }
            this.mLayerCanvas.save();
            this.mLayerCanvas.setMatrix(canvas.getMatrix());
        } else {
            this.mLayerCanvas = canvas;
        }
        this.elements = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (!(childAt instanceof MaskView)) {
                if (childAt instanceof VirtualView) {
                    VirtualView virtualView = (VirtualView) childAt;
                    if (!"none".equals(virtualView.mDisplay)) {
                        boolean z = virtualView instanceof RenderableView;
                        if (z) {
                            ((RenderableView) virtualView).mergeProperties(this);
                        }
                        int saveAndSetupCanvas = virtualView.saveAndSetupCanvas(this.mLayerCanvas, this.mCTM);
                        virtualView.render(this.mLayerCanvas, paint, f);
                        RectF clientRect = virtualView.getClientRect();
                        if (clientRect != null) {
                            rectF.union(clientRect);
                        }
                        virtualView.restoreCanvas(this.mLayerCanvas, saveAndSetupCanvas);
                        if (z) {
                            ((RenderableView) virtualView).resetProperties();
                        }
                        if (virtualView.isResponsible()) {
                            svgView.enableTouchEvents();
                        }
                        ArrayList<PathElement> arrayList = virtualView.elements;
                        if (arrayList != null) {
                            this.elements.addAll(arrayList);
                        }
                    }
                } else if (childAt instanceof SvgView) {
                    SvgView svgView2 = (SvgView) childAt;
                    svgView2.drawChildren(canvas);
                    if (svgView2.isResponsible()) {
                        svgView.enableTouchEvents();
                    }
                }
            }
        }
        if (this.mOpacity != 1.0f) {
            this.mLayerCanvas.restore();
            int save = canvas.save();
            canvas.setMatrix((Matrix) null);
            this.mLayerPaint.setAlpha((int) (this.mOpacity * 255.0f));
            canvas.drawBitmap(this.mLayerBitmap, 0.0f, 0.0f, this.mLayerPaint);
            canvas.restoreToCount(save);
        }
        setClientRect(rectF);
        popGlyphContext();
    }

    /* access modifiers changed from: package-private */
    public void drawPath(Canvas canvas, Paint paint, float f) {
        super.draw(canvas, paint, f);
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = this.mPath;
        if (path != null) {
            return path;
        }
        this.mPath = new Path();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (!(childAt instanceof MaskView) && (childAt instanceof VirtualView)) {
                VirtualView virtualView = (VirtualView) childAt;
                this.mPath.addPath(virtualView.getPath(canvas, paint), virtualView.mMatrix);
            }
        }
        return this.mPath;
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint, Region.Op op) {
        Path path;
        Path path2 = new Path();
        Path.Op valueOf = Path.Op.valueOf(op.name());
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (!(childAt instanceof MaskView) && (childAt instanceof VirtualView)) {
                VirtualView virtualView = (VirtualView) childAt;
                Matrix matrix = virtualView.mMatrix;
                if (virtualView instanceof GroupView) {
                    path = ((GroupView) virtualView).getPath(canvas, paint, op);
                } else {
                    path = virtualView.getPath(canvas, paint);
                }
                path.transform(matrix);
                path2.op(path, valueOf);
            }
        }
        return path2;
    }

    /* access modifiers changed from: package-private */
    public int hitTest(float[] fArr) {
        int reactTagForTouch;
        VirtualView virtualView;
        int hitTest;
        if (!this.mInvertible) {
            return -1;
        }
        float[] fArr2 = new float[2];
        this.mInvMatrix.mapPoints(fArr2, fArr);
        this.mInvTransform.mapPoints(fArr2);
        int round = Math.round(fArr2[0]);
        int round2 = Math.round(fArr2[1]);
        Path clipPath = getClipPath();
        if (clipPath != null) {
            if (this.mClipRegionPath != clipPath) {
                this.mClipRegionPath = clipPath;
                RectF rectF = new RectF();
                this.mClipBounds = rectF;
                clipPath.computeBounds(rectF, true);
                this.mClipRegion = getRegion(clipPath, this.mClipBounds);
            }
            if (!this.mClipRegion.contains(round, round2)) {
                return -1;
            }
        }
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt instanceof VirtualView) {
                if (!(childAt instanceof MaskView) && (hitTest = virtualView.hitTest(fArr2)) != -1) {
                    return ((virtualView = (VirtualView) childAt).isResponsible() || hitTest != childAt.getId()) ? hitTest : getId();
                }
            } else if ((childAt instanceof SvgView) && (reactTagForTouch = ((SvgView) childAt).reactTagForTouch(fArr2[0], fArr2[1])) != childAt.getId()) {
                return reactTagForTouch;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public void saveDefinition() {
        if (this.mName != null) {
            getSvgView().defineTemplate(this, this.mName);
        }
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof VirtualView) {
                ((VirtualView) childAt).saveDefinition();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void resetProperties() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof RenderableView) {
                ((RenderableView) childAt).resetProperties();
            }
        }
    }
}
