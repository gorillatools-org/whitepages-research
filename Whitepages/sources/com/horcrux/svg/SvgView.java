package com.horcrux.svg;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Base64;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.ReactCompoundView;
import com.facebook.react.uimanager.ReactCompoundViewGroup;
import com.facebook.react.views.view.ReactViewGroup;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"ViewConstructor"})
public class SvgView extends ReactViewGroup implements ReactCompoundView, ReactCompoundViewGroup {
    private String mAlign;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Bitmap mCurrentBitmap;
    int mCurrentColor;
    private final Map<String, Brush> mDefinedBrushes = new HashMap();
    private final Map<String, VirtualView> mDefinedClipPaths = new HashMap();
    private final Map<String, VirtualView> mDefinedFilters = new HashMap();
    private final Map<String, VirtualView> mDefinedMarkers = new HashMap();
    private final Map<String, VirtualView> mDefinedMasks = new HashMap();
    private final Map<String, VirtualView> mDefinedTemplates = new HashMap();
    final Matrix mInvViewBoxMatrix;
    private boolean mInvertible;
    private int mMeetOrSlice;
    private float mMinX;
    private float mMinY;
    private final Paint mPaint;
    private boolean mRemovalTransitionStarted;
    private boolean mRendered;
    private boolean mResponsible = false;
    private final float mScale;
    private float mVbHeight;
    private float mVbWidth;
    private SVGLength mbbHeight;
    private SVGLength mbbWidth;
    private Runnable toDataUrlTask = null;

    public boolean interceptsTouchEvent(float f, float f2) {
        return true;
    }

    public enum Events {
        EVENT_DATA_URL("onDataURL");
        
        private final String mName;

        private Events(String str) {
            this.mName = str;
        }

        public String toString() {
            return this.mName;
        }
    }

    public SvgView(ReactContext reactContext) {
        super(reactContext);
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mInvViewBoxMatrix = new Matrix();
        this.mInvertible = true;
        this.mRendered = false;
        this.mCurrentColor = -16777216;
        this.mScale = DisplayMetricsHolder.getScreenDisplayMetrics().density;
        paint.setFlags(385);
        paint.setTypeface(Typeface.DEFAULT);
        setWillNotDraw(false);
    }

    public void setId(int i) {
        super.setId(i);
        SvgViewManager.setSvgView(i, this);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setVisibleToUser(getGlobalVisibleRect(new Rect()));
        accessibilityNodeInfo.setClassName(getClass().getCanonicalName());
    }

    public void invalidate() {
        super.invalidate();
        ViewParent parent = getParent();
        if (parent instanceof VirtualView) {
            if (this.mRendered) {
                this.mRendered = false;
                ((VirtualView) parent).getSvgView().invalidate();
            }
        } else if (!this.mRemovalTransitionStarted) {
            Bitmap bitmap = this.mBitmap;
            if (bitmap != null) {
                bitmap.recycle();
            }
            this.mBitmap = null;
        }
    }

    public void startViewTransition(View view) {
        this.mRemovalTransitionStarted = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.mBitmap = null;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (!(getParent() instanceof VirtualView)) {
            super.onDraw(canvas);
            if (this.mBitmap == null) {
                this.mBitmap = drawOutput();
            }
            if (this.mBitmap != null) {
                this.mPaint.reset();
                this.mPaint.setFlags(387);
                this.mPaint.setTypeface(Typeface.DEFAULT);
                canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, this.mPaint);
                Runnable runnable = this.toDataUrlTask;
                if (runnable != null) {
                    runnable.run();
                    this.toDataUrlTask = null;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setToDataUrlTask(Runnable runnable) {
        this.toDataUrlTask = runnable;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        invalidate();
    }

    public int reactTagForTouch(float f, float f2) {
        return hitTest(f, f2);
    }

    /* access modifiers changed from: package-private */
    public boolean notRendered() {
        return !this.mRendered;
    }

    private void clearChildCache() {
        if (this.mRendered) {
            this.mRendered = false;
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof VirtualView) {
                    ((VirtualView) childAt).clearChildCache();
                }
            }
        }
    }

    public void setCurrentColor(Integer num) {
        this.mCurrentColor = num != null ? num.intValue() : 0;
        invalidate();
        clearChildCache();
    }

    public void setMinX(float f) {
        this.mMinX = f;
        invalidate();
        clearChildCache();
    }

    public void setMinY(float f) {
        this.mMinY = f;
        invalidate();
        clearChildCache();
    }

    public void setVbWidth(float f) {
        this.mVbWidth = f;
        invalidate();
        clearChildCache();
    }

    public void setVbHeight(float f) {
        this.mVbHeight = f;
        invalidate();
        clearChildCache();
    }

    public void setBbWidth(Dynamic dynamic) {
        this.mbbWidth = SVGLength.from(dynamic);
        invalidate();
        clearChildCache();
    }

    public void setBbHeight(Dynamic dynamic) {
        this.mbbHeight = SVGLength.from(dynamic);
        invalidate();
        clearChildCache();
    }

    public void setAlign(String str) {
        this.mAlign = str;
        invalidate();
        clearChildCache();
    }

    public void setMeetOrSlice(int i) {
        this.mMeetOrSlice = i;
        invalidate();
        clearChildCache();
    }

    private Bitmap drawOutput() {
        this.mRendered = true;
        float width = (float) getWidth();
        float height = (float) getHeight();
        if (Float.isNaN(width) || Float.isNaN(height) || width < 1.0f || height < 1.0f || Math.log10((double) width) + Math.log10((double) height) > 42.0d) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap((int) width, (int) height, Bitmap.Config.ARGB_8888);
        this.mCurrentBitmap = createBitmap;
        drawChildren(new Canvas(createBitmap));
        return createBitmap;
    }

    /* access modifiers changed from: package-private */
    public Rect getCanvasBounds() {
        return this.mCanvas.getClipBounds();
    }

    /* access modifiers changed from: package-private */
    public float getCanvasWidth() {
        return (float) this.mCanvas.getWidth();
    }

    /* access modifiers changed from: package-private */
    public float getCanvasHeight() {
        return (float) this.mCanvas.getHeight();
    }

    /* access modifiers changed from: package-private */
    public Matrix getCtm() {
        return this.mCanvas.getMatrix();
    }

    /* access modifiers changed from: package-private */
    public synchronized void drawChildren(Canvas canvas) {
        try {
            this.mRendered = true;
            this.mCanvas = canvas;
            Matrix matrix = new Matrix();
            if (this.mAlign != null) {
                RectF viewBox = getViewBox();
                float width = (float) canvas.getWidth();
                float height = (float) canvas.getHeight();
                boolean z = getParent() instanceof VirtualView;
                if (z) {
                    width = (float) PropHelper.fromRelative(this.mbbWidth, (double) width, 0.0d, (double) this.mScale, 12.0d);
                    height = (float) PropHelper.fromRelative(this.mbbHeight, (double) height, 0.0d, (double) this.mScale, 12.0d);
                }
                RectF rectF = new RectF(0.0f, 0.0f, width, height);
                if (z) {
                    canvas.clipRect(rectF);
                }
                matrix = ViewBox.getTransform(viewBox, rectF, this.mAlign, this.mMeetOrSlice);
                this.mInvertible = matrix.invert(this.mInvViewBoxMatrix);
                canvas.concat(matrix);
            }
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof VirtualView) {
                    ((VirtualView) childAt).saveDefinition();
                }
            }
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                View childAt2 = getChildAt(i2);
                if (childAt2 instanceof VirtualView) {
                    VirtualView virtualView = (VirtualView) childAt2;
                    int saveAndSetupCanvas = virtualView.saveAndSetupCanvas(canvas, matrix);
                    virtualView.render(canvas, this.mPaint, 1.0f);
                    virtualView.restoreCanvas(canvas, saveAndSetupCanvas);
                    if (virtualView.isResponsible() && !this.mResponsible) {
                        this.mResponsible = true;
                    }
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private RectF getViewBox() {
        float f = this.mMinX;
        float f2 = this.mScale;
        float f3 = this.mMinY;
        return new RectF(f * f2, f3 * f2, (f + this.mVbWidth) * f2, (f3 + this.mVbHeight) * f2);
    }

    /* access modifiers changed from: package-private */
    public String toDataURL() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        clearChildCache();
        drawChildren(new Canvas(createBitmap));
        clearChildCache();
        invalidate();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        createBitmap.recycle();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    /* access modifiers changed from: package-private */
    public String toDataURL(int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        clearChildCache();
        drawChildren(new Canvas(createBitmap));
        clearChildCache();
        invalidate();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        createBitmap.recycle();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    /* access modifiers changed from: package-private */
    public void enableTouchEvents() {
        if (!this.mResponsible) {
            this.mResponsible = true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isResponsible() {
        return this.mResponsible;
    }

    private int hitTest(float f, float f2) {
        if (!this.mResponsible || !this.mInvertible) {
            return getId();
        }
        float[] fArr = {f, f2};
        this.mInvViewBoxMatrix.mapPoints(fArr);
        int i = -1;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (childAt instanceof VirtualView) {
                i = ((VirtualView) childAt).hitTest(fArr);
            } else if (childAt instanceof SvgView) {
                i = ((SvgView) childAt).hitTest(f, f2);
            }
            if (i != -1) {
                break;
            }
        }
        if (i == -1) {
            return getId();
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public void defineClipPath(VirtualView virtualView, String str) {
        this.mDefinedClipPaths.put(str, virtualView);
    }

    /* access modifiers changed from: package-private */
    public VirtualView getDefinedClipPath(String str) {
        return this.mDefinedClipPaths.get(str);
    }

    /* access modifiers changed from: package-private */
    public void defineTemplate(VirtualView virtualView, String str) {
        this.mDefinedTemplates.put(str, virtualView);
    }

    /* access modifiers changed from: package-private */
    public VirtualView getDefinedTemplate(String str) {
        return this.mDefinedTemplates.get(str);
    }

    /* access modifiers changed from: package-private */
    public void defineBrush(Brush brush, String str) {
        this.mDefinedBrushes.put(str, brush);
    }

    /* access modifiers changed from: package-private */
    public Brush getDefinedBrush(String str) {
        return this.mDefinedBrushes.get(str);
    }

    /* access modifiers changed from: package-private */
    public void defineMask(VirtualView virtualView, String str) {
        this.mDefinedMasks.put(str, virtualView);
    }

    /* access modifiers changed from: package-private */
    public VirtualView getDefinedMask(String str) {
        return this.mDefinedMasks.get(str);
    }

    /* access modifiers changed from: package-private */
    public void defineFilter(VirtualView virtualView, String str) {
        this.mDefinedFilters.put(str, virtualView);
    }

    /* access modifiers changed from: package-private */
    public VirtualView getDefinedFilter(String str) {
        return this.mDefinedFilters.get(str);
    }

    /* access modifiers changed from: package-private */
    public void defineMarker(VirtualView virtualView, String str) {
        this.mDefinedMarkers.put(str, virtualView);
    }

    /* access modifiers changed from: package-private */
    public VirtualView getDefinedMarker(String str) {
        return this.mDefinedMarkers.get(str);
    }

    public Bitmap getCurrentBitmap() {
        return this.mCurrentBitmap;
    }
}
