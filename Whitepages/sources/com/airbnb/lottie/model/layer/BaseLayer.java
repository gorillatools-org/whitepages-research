package com.airbnb.lottie.model.layer;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.content.DrawingContent;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseLayer implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElement {
    private final List animations;
    BlurMaskFilter blurMaskFilter;
    float blurMaskFilterRadius;
    final Matrix boundsMatrix;
    private final RectF canvasBounds;
    private final Matrix canvasMatrix = new Matrix();
    private final Paint clearPaint;
    private final Paint contentPaint = new LPaint(1);
    private final String drawTraceName;
    private final Paint dstInPaint;
    private final Paint dstOutPaint;
    private FloatKeyframeAnimation inOutAnimation;
    final Layer layerModel;
    final LottieDrawable lottieDrawable;
    private MaskKeyframeAnimation mask;
    private final RectF maskBoundsRect;
    private final Matrix matrix = new Matrix();
    private final RectF matteBoundsRect;
    private BaseLayer matteLayer;
    private final Paint mattePaint;
    private boolean outlineMasksAndMattes;
    private Paint outlineMasksAndMattesPaint;
    private BaseLayer parentLayer;
    private List parentLayers;
    private final Path path = new Path();
    private final RectF rect;
    LPaint solidWhitePaint;
    private final RectF tempMaskBoundsRect;
    public final TransformKeyframeAnimation transform;
    private boolean visible;

    /* access modifiers changed from: package-private */
    public abstract void drawLayer(Canvas canvas, Matrix matrix2, int i);

    /* access modifiers changed from: package-private */
    public void resolveChildKeyPath(KeyPath keyPath, int i, List list, KeyPath keyPath2) {
    }

    public void setContents(List list, List list2) {
    }

    static BaseLayer forModel(CompositionLayer compositionLayer, Layer layer, LottieDrawable lottieDrawable2, LottieComposition lottieComposition) {
        switch (AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[layer.getLayerType().ordinal()]) {
            case 1:
                return new ShapeLayer(lottieDrawable2, layer, compositionLayer, lottieComposition);
            case 2:
                return new CompositionLayer(lottieDrawable2, layer, lottieComposition.getPrecomps(layer.getRefId()), lottieComposition);
            case 3:
                return new SolidLayer(lottieDrawable2, layer);
            case 4:
                return new ImageLayer(lottieDrawable2, layer);
            case 5:
                return new NullLayer(lottieDrawable2, layer);
            case 6:
                return new TextLayer(lottieDrawable2, layer);
            default:
                Logger.warning("Unknown layer type " + layer.getLayerType());
                return null;
        }
    }

    BaseLayer(LottieDrawable lottieDrawable2, Layer layer) {
        PorterDuff.Mode mode = PorterDuff.Mode.DST_IN;
        this.dstInPaint = new LPaint(1, mode);
        PorterDuff.Mode mode2 = PorterDuff.Mode.DST_OUT;
        this.dstOutPaint = new LPaint(1, mode2);
        LPaint lPaint = new LPaint(1);
        this.mattePaint = lPaint;
        this.clearPaint = new LPaint(PorterDuff.Mode.CLEAR);
        this.rect = new RectF();
        this.canvasBounds = new RectF();
        this.maskBoundsRect = new RectF();
        this.matteBoundsRect = new RectF();
        this.tempMaskBoundsRect = new RectF();
        this.boundsMatrix = new Matrix();
        this.animations = new ArrayList();
        this.visible = true;
        this.blurMaskFilterRadius = 0.0f;
        this.lottieDrawable = lottieDrawable2;
        this.layerModel = layer;
        this.drawTraceName = layer.getName() + "#draw";
        if (layer.getMatteType() == Layer.MatteType.INVERT) {
            lPaint.setXfermode(new PorterDuffXfermode(mode2));
        } else {
            lPaint.setXfermode(new PorterDuffXfermode(mode));
        }
        TransformKeyframeAnimation createAnimation = layer.getTransform().createAnimation();
        this.transform = createAnimation;
        createAnimation.addListener(this);
        if (layer.getMasks() != null && !layer.getMasks().isEmpty()) {
            MaskKeyframeAnimation maskKeyframeAnimation = new MaskKeyframeAnimation(layer.getMasks());
            this.mask = maskKeyframeAnimation;
            for (BaseKeyframeAnimation addUpdateListener : maskKeyframeAnimation.getMaskAnimations()) {
                addUpdateListener.addUpdateListener(this);
            }
            for (BaseKeyframeAnimation baseKeyframeAnimation : this.mask.getOpacityAnimations()) {
                addAnimation(baseKeyframeAnimation);
                baseKeyframeAnimation.addUpdateListener(this);
            }
        }
        setupInOutAnimations();
    }

    /* access modifiers changed from: package-private */
    public void setOutlineMasksAndMattes(boolean z) {
        if (z && this.outlineMasksAndMattesPaint == null) {
            this.outlineMasksAndMattesPaint = new LPaint();
        }
        this.outlineMasksAndMattes = z;
    }

    public void onValueChanged() {
        invalidateSelf();
    }

    /* access modifiers changed from: package-private */
    public Layer getLayerModel() {
        return this.layerModel;
    }

    /* access modifiers changed from: package-private */
    public void setMatteLayer(BaseLayer baseLayer) {
        this.matteLayer = baseLayer;
    }

    /* access modifiers changed from: package-private */
    public boolean hasMatteOnThisLayer() {
        return this.matteLayer != null;
    }

    /* access modifiers changed from: package-private */
    public void setParentLayer(BaseLayer baseLayer) {
        this.parentLayer = baseLayer;
    }

    private void setupInOutAnimations() {
        boolean z = true;
        if (!this.layerModel.getInOutKeyframes().isEmpty()) {
            FloatKeyframeAnimation floatKeyframeAnimation = new FloatKeyframeAnimation(this.layerModel.getInOutKeyframes());
            this.inOutAnimation = floatKeyframeAnimation;
            floatKeyframeAnimation.setIsDiscrete();
            this.inOutAnimation.addUpdateListener(new BaseLayer$$ExternalSyntheticLambda0(this));
            if (((Float) this.inOutAnimation.getValue()).floatValue() != 1.0f) {
                z = false;
            }
            setVisible(z);
            addAnimation(this.inOutAnimation);
            return;
        }
        setVisible(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setupInOutAnimations$0() {
        setVisible(this.inOutAnimation.getFloatValue() == 1.0f);
    }

    private void invalidateSelf() {
        this.lottieDrawable.invalidateSelf();
    }

    public void addAnimation(BaseKeyframeAnimation baseKeyframeAnimation) {
        if (baseKeyframeAnimation != null) {
            this.animations.add(baseKeyframeAnimation);
        }
    }

    public void removeAnimation(BaseKeyframeAnimation baseKeyframeAnimation) {
        this.animations.remove(baseKeyframeAnimation);
    }

    public void getBounds(RectF rectF, Matrix matrix2, boolean z) {
        this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        buildParentLayerListIfNeeded();
        this.boundsMatrix.set(matrix2);
        if (z) {
            List list = this.parentLayers;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.boundsMatrix.preConcat(((BaseLayer) this.parentLayers.get(size)).transform.getMatrix());
                }
            } else {
                BaseLayer baseLayer = this.parentLayer;
                if (baseLayer != null) {
                    this.boundsMatrix.preConcat(baseLayer.transform.getMatrix());
                }
            }
        }
        this.boundsMatrix.preConcat(this.transform.getMatrix());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005c, code lost:
        r0 = (java.lang.Integer) r0.getValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void draw(android.graphics.Canvas r12, android.graphics.Matrix r13, int r14) {
        /*
            r11 = this;
            java.lang.String r0 = r11.drawTraceName
            com.airbnb.lottie.L.beginSection(r0)
            boolean r0 = r11.visible
            if (r0 == 0) goto L_0x025d
            com.airbnb.lottie.model.layer.Layer r0 = r11.layerModel
            boolean r0 = r0.isHidden()
            if (r0 == 0) goto L_0x0013
            goto L_0x025d
        L_0x0013:
            r11.buildParentLayerListIfNeeded()
            boolean r0 = com.airbnb.lottie.L.isTraceEnabled()
            java.lang.String r1 = "Layer#parentMatrix"
            if (r0 == 0) goto L_0x0021
            com.airbnb.lottie.L.beginSection(r1)
        L_0x0021:
            android.graphics.Matrix r0 = r11.matrix
            r0.reset()
            android.graphics.Matrix r0 = r11.matrix
            r0.set(r13)
            java.util.List r0 = r11.parentLayers
            int r0 = r0.size()
            int r0 = r0 + -1
        L_0x0033:
            if (r0 < 0) goto L_0x004b
            android.graphics.Matrix r2 = r11.matrix
            java.util.List r3 = r11.parentLayers
            java.lang.Object r3 = r3.get(r0)
            com.airbnb.lottie.model.layer.BaseLayer r3 = (com.airbnb.lottie.model.layer.BaseLayer) r3
            com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation r3 = r3.transform
            android.graphics.Matrix r3 = r3.getMatrix()
            r2.preConcat(r3)
            int r0 = r0 + -1
            goto L_0x0033
        L_0x004b:
            boolean r0 = com.airbnb.lottie.L.isTraceEnabled()
            if (r0 == 0) goto L_0x0054
            com.airbnb.lottie.L.endSection(r1)
        L_0x0054:
            com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation r0 = r11.transform
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r0 = r0.getOpacity()
            if (r0 == 0) goto L_0x0069
            java.lang.Object r0 = r0.getValue()
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L_0x0069
            int r0 = r0.intValue()
            goto L_0x006b
        L_0x0069:
            r0 = 100
        L_0x006b:
            float r14 = (float) r14
            r1 = 1132396544(0x437f0000, float:255.0)
            float r14 = r14 / r1
            float r0 = (float) r0
            float r14 = r14 * r0
            r0 = 1120403456(0x42c80000, float:100.0)
            float r14 = r14 / r0
            float r14 = r14 * r1
            int r14 = (int) r14
            boolean r0 = r11.hasMatteOnThisLayer()
            java.lang.String r1 = "Layer#drawLayer"
            if (r0 != 0) goto L_0x00b8
            boolean r0 = r11.hasMasksOnThisLayer()
            if (r0 != 0) goto L_0x00b8
            com.airbnb.lottie.model.content.LBlendMode r0 = r11.getBlendMode()
            com.airbnb.lottie.model.content.LBlendMode r2 = com.airbnb.lottie.model.content.LBlendMode.NORMAL
            if (r0 != r2) goto L_0x00b8
            android.graphics.Matrix r13 = r11.matrix
            com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation r0 = r11.transform
            android.graphics.Matrix r0 = r0.getMatrix()
            r13.preConcat(r0)
            boolean r13 = com.airbnb.lottie.L.isTraceEnabled()
            if (r13 == 0) goto L_0x00a0
            com.airbnb.lottie.L.beginSection(r1)
        L_0x00a0:
            android.graphics.Matrix r13 = r11.matrix
            r11.drawLayer(r12, r13, r14)
            boolean r12 = com.airbnb.lottie.L.isTraceEnabled()
            if (r12 == 0) goto L_0x00ae
            com.airbnb.lottie.L.endSection(r1)
        L_0x00ae:
            java.lang.String r12 = r11.drawTraceName
            float r12 = com.airbnb.lottie.L.endSection(r12)
            r11.recordRenderTime(r12)
            return
        L_0x00b8:
            boolean r0 = com.airbnb.lottie.L.isTraceEnabled()
            java.lang.String r2 = "Layer#computeBounds"
            if (r0 == 0) goto L_0x00c3
            com.airbnb.lottie.L.beginSection(r2)
        L_0x00c3:
            android.graphics.RectF r0 = r11.rect
            android.graphics.Matrix r3 = r11.matrix
            r4 = 0
            r11.getBounds(r0, r3, r4)
            android.graphics.RectF r0 = r11.rect
            r11.intersectBoundsWithMatte(r0, r13)
            android.graphics.Matrix r0 = r11.matrix
            com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation r3 = r11.transform
            android.graphics.Matrix r3 = r3.getMatrix()
            r0.preConcat(r3)
            android.graphics.RectF r0 = r11.rect
            android.graphics.Matrix r3 = r11.matrix
            r11.intersectBoundsWithMask(r0, r3)
            android.graphics.RectF r0 = r11.canvasBounds
            int r3 = r12.getWidth()
            float r3 = (float) r3
            int r4 = r12.getHeight()
            float r4 = (float) r4
            r5 = 0
            r0.set(r5, r5, r3, r4)
            android.graphics.Matrix r0 = r11.canvasMatrix
            r12.getMatrix(r0)
            android.graphics.Matrix r0 = r11.canvasMatrix
            boolean r0 = r0.isIdentity()
            if (r0 != 0) goto L_0x010b
            android.graphics.Matrix r0 = r11.canvasMatrix
            r0.invert(r0)
            android.graphics.Matrix r0 = r11.canvasMatrix
            android.graphics.RectF r3 = r11.canvasBounds
            r0.mapRect(r3)
        L_0x010b:
            android.graphics.RectF r0 = r11.rect
            android.graphics.RectF r3 = r11.canvasBounds
            boolean r0 = r0.intersect(r3)
            if (r0 != 0) goto L_0x011a
            android.graphics.RectF r0 = r11.rect
            r0.set(r5, r5, r5, r5)
        L_0x011a:
            boolean r0 = com.airbnb.lottie.L.isTraceEnabled()
            if (r0 == 0) goto L_0x0123
            com.airbnb.lottie.L.endSection(r2)
        L_0x0123:
            android.graphics.RectF r0 = r11.rect
            float r0 = r0.width()
            r2 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L_0x021a
            android.graphics.RectF r0 = r11.rect
            float r0 = r0.height()
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L_0x021a
            boolean r0 = com.airbnb.lottie.L.isTraceEnabled()
            java.lang.String r3 = "Layer#saveLayer"
            if (r0 == 0) goto L_0x0144
            com.airbnb.lottie.L.beginSection(r3)
        L_0x0144:
            android.graphics.Paint r0 = r11.contentPaint
            r4 = 255(0xff, float:3.57E-43)
            r0.setAlpha(r4)
            android.graphics.Paint r0 = r11.contentPaint
            com.airbnb.lottie.model.content.LBlendMode r4 = r11.getBlendMode()
            androidx.core.graphics.BlendModeCompat r4 = r4.toNativeBlendMode()
            androidx.core.graphics.PaintCompat.setBlendMode(r0, r4)
            android.graphics.RectF r0 = r11.rect
            android.graphics.Paint r4 = r11.contentPaint
            com.airbnb.lottie.utils.Utils.saveLayerCompat(r12, r0, r4)
            boolean r0 = com.airbnb.lottie.L.isTraceEnabled()
            if (r0 == 0) goto L_0x0168
            com.airbnb.lottie.L.endSection(r3)
        L_0x0168:
            com.airbnb.lottie.model.content.LBlendMode r0 = r11.getBlendMode()
            com.airbnb.lottie.model.content.LBlendMode r4 = com.airbnb.lottie.model.content.LBlendMode.MULTIPLY
            if (r0 == r4) goto L_0x0174
            r11.clearCanvas(r12)
            goto L_0x019b
        L_0x0174:
            com.airbnb.lottie.animation.LPaint r0 = r11.solidWhitePaint
            if (r0 != 0) goto L_0x0183
            com.airbnb.lottie.animation.LPaint r0 = new com.airbnb.lottie.animation.LPaint
            r0.<init>()
            r11.solidWhitePaint = r0
            r4 = -1
            r0.setColor(r4)
        L_0x0183:
            android.graphics.RectF r0 = r11.rect
            float r4 = r0.left
            float r6 = r4 - r2
            float r4 = r0.top
            float r7 = r4 - r2
            float r4 = r0.right
            float r8 = r4 + r2
            float r0 = r0.bottom
            float r9 = r0 + r2
            com.airbnb.lottie.animation.LPaint r10 = r11.solidWhitePaint
            r5 = r12
            r5.drawRect(r6, r7, r8, r9, r10)
        L_0x019b:
            boolean r0 = com.airbnb.lottie.L.isTraceEnabled()
            if (r0 == 0) goto L_0x01a4
            com.airbnb.lottie.L.beginSection(r1)
        L_0x01a4:
            android.graphics.Matrix r0 = r11.matrix
            r11.drawLayer(r12, r0, r14)
            boolean r0 = com.airbnb.lottie.L.isTraceEnabled()
            if (r0 == 0) goto L_0x01b2
            com.airbnb.lottie.L.endSection(r1)
        L_0x01b2:
            boolean r0 = r11.hasMasksOnThisLayer()
            if (r0 == 0) goto L_0x01bd
            android.graphics.Matrix r0 = r11.matrix
            r11.applyMasks(r12, r0)
        L_0x01bd:
            boolean r0 = r11.hasMatteOnThisLayer()
            java.lang.String r1 = "Layer#restoreLayer"
            if (r0 == 0) goto L_0x0205
            boolean r0 = com.airbnb.lottie.L.isTraceEnabled()
            java.lang.String r2 = "Layer#drawMatte"
            if (r0 == 0) goto L_0x01d3
            com.airbnb.lottie.L.beginSection(r2)
            com.airbnb.lottie.L.beginSection(r3)
        L_0x01d3:
            android.graphics.RectF r0 = r11.rect
            android.graphics.Paint r4 = r11.mattePaint
            r5 = 19
            com.airbnb.lottie.utils.Utils.saveLayerCompat(r12, r0, r4, r5)
            boolean r0 = com.airbnb.lottie.L.isTraceEnabled()
            if (r0 == 0) goto L_0x01e5
            com.airbnb.lottie.L.endSection(r3)
        L_0x01e5:
            r11.clearCanvas(r12)
            com.airbnb.lottie.model.layer.BaseLayer r0 = r11.matteLayer
            r0.draw(r12, r13, r14)
            boolean r13 = com.airbnb.lottie.L.isTraceEnabled()
            if (r13 == 0) goto L_0x01f6
            com.airbnb.lottie.L.beginSection(r1)
        L_0x01f6:
            r12.restore()
            boolean r13 = com.airbnb.lottie.L.isTraceEnabled()
            if (r13 == 0) goto L_0x0205
            com.airbnb.lottie.L.endSection(r1)
            com.airbnb.lottie.L.endSection(r2)
        L_0x0205:
            boolean r13 = com.airbnb.lottie.L.isTraceEnabled()
            if (r13 == 0) goto L_0x020e
            com.airbnb.lottie.L.beginSection(r1)
        L_0x020e:
            r12.restore()
            boolean r13 = com.airbnb.lottie.L.isTraceEnabled()
            if (r13 == 0) goto L_0x021a
            com.airbnb.lottie.L.endSection(r1)
        L_0x021a:
            boolean r13 = r11.outlineMasksAndMattes
            if (r13 == 0) goto L_0x0253
            android.graphics.Paint r13 = r11.outlineMasksAndMattesPaint
            if (r13 == 0) goto L_0x0253
            android.graphics.Paint$Style r14 = android.graphics.Paint.Style.STROKE
            r13.setStyle(r14)
            android.graphics.Paint r13 = r11.outlineMasksAndMattesPaint
            r14 = -251901(0xfffffffffffc2803, float:NaN)
            r13.setColor(r14)
            android.graphics.Paint r13 = r11.outlineMasksAndMattesPaint
            r14 = 1082130432(0x40800000, float:4.0)
            r13.setStrokeWidth(r14)
            android.graphics.RectF r13 = r11.rect
            android.graphics.Paint r14 = r11.outlineMasksAndMattesPaint
            r12.drawRect(r13, r14)
            android.graphics.Paint r13 = r11.outlineMasksAndMattesPaint
            android.graphics.Paint$Style r14 = android.graphics.Paint.Style.FILL
            r13.setStyle(r14)
            android.graphics.Paint r13 = r11.outlineMasksAndMattesPaint
            r14 = 1357638635(0x50ebebeb, float:3.1664855E10)
            r13.setColor(r14)
            android.graphics.RectF r13 = r11.rect
            android.graphics.Paint r14 = r11.outlineMasksAndMattesPaint
            r12.drawRect(r13, r14)
        L_0x0253:
            java.lang.String r12 = r11.drawTraceName
            float r12 = com.airbnb.lottie.L.endSection(r12)
            r11.recordRenderTime(r12)
            return
        L_0x025d:
            java.lang.String r12 = r11.drawTraceName
            com.airbnb.lottie.L.endSection(r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.BaseLayer.draw(android.graphics.Canvas, android.graphics.Matrix, int):void");
    }

    private void recordRenderTime(float f) {
        this.lottieDrawable.getComposition().getPerformanceTracker().recordRenderTime(this.layerModel.getName(), f);
    }

    private void clearCanvas(Canvas canvas) {
        if (L.isTraceEnabled()) {
            L.beginSection("Layer#clearLayer");
        }
        RectF rectF = this.rect;
        canvas.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.clearPaint);
        if (L.isTraceEnabled()) {
            L.endSection("Layer#clearLayer");
        }
    }

    private void intersectBoundsWithMask(RectF rectF, Matrix matrix2) {
        this.maskBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (hasMasksOnThisLayer()) {
            int size = this.mask.getMasks().size();
            for (int i = 0; i < size; i++) {
                Mask mask2 = (Mask) this.mask.getMasks().get(i);
                Path path2 = (Path) ((BaseKeyframeAnimation) this.mask.getMaskAnimations().get(i)).getValue();
                if (path2 != null) {
                    this.path.set(path2);
                    this.path.transform(matrix2);
                    int i2 = AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[mask2.getMaskMode().ordinal()];
                    if (i2 != 1 && i2 != 2) {
                        if ((i2 != 3 && i2 != 4) || !mask2.isInverted()) {
                            this.path.computeBounds(this.tempMaskBoundsRect, false);
                            if (i == 0) {
                                this.maskBoundsRect.set(this.tempMaskBoundsRect);
                            } else {
                                RectF rectF2 = this.maskBoundsRect;
                                rectF2.set(Math.min(rectF2.left, this.tempMaskBoundsRect.left), Math.min(this.maskBoundsRect.top, this.tempMaskBoundsRect.top), Math.max(this.maskBoundsRect.right, this.tempMaskBoundsRect.right), Math.max(this.maskBoundsRect.bottom, this.tempMaskBoundsRect.bottom));
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
            if (!rectF.intersect(this.maskBoundsRect)) {
                rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
    }

    /* renamed from: com.airbnb.lottie.model.layer.BaseLayer$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode;
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType;

        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|(3:29|30|32)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
        static {
            /*
                com.airbnb.lottie.model.content.Mask$MaskMode[] r0 = com.airbnb.lottie.model.content.Mask.MaskMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode = r0
                r1 = 1
                com.airbnb.lottie.model.content.Mask$MaskMode r2 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.airbnb.lottie.model.content.Mask$MaskMode r3 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_SUBTRACT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.airbnb.lottie.model.content.Mask$MaskMode r4 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_INTERSECT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.airbnb.lottie.model.content.Mask$MaskMode r5 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_ADD     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                com.airbnb.lottie.model.layer.Layer$LayerType[] r4 = com.airbnb.lottie.model.layer.Layer.LayerType.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType = r4
                com.airbnb.lottie.model.layer.Layer$LayerType r5 = com.airbnb.lottie.model.layer.Layer.LayerType.SHAPE     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r1 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType     // Catch:{ NoSuchFieldError -> 0x004e }
                com.airbnb.lottie.model.layer.Layer$LayerType r4 = com.airbnb.lottie.model.layer.Layer.LayerType.PRE_COMP     // Catch:{ NoSuchFieldError -> 0x004e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.airbnb.lottie.model.layer.Layer$LayerType r1 = com.airbnb.lottie.model.layer.Layer.LayerType.SOLID     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.airbnb.lottie.model.layer.Layer$LayerType r1 = com.airbnb.lottie.model.layer.Layer.LayerType.IMAGE     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType     // Catch:{ NoSuchFieldError -> 0x006d }
                com.airbnb.lottie.model.layer.Layer$LayerType r1 = com.airbnb.lottie.model.layer.Layer.LayerType.NULL     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.airbnb.lottie.model.layer.Layer$LayerType r1 = com.airbnb.lottie.model.layer.Layer.LayerType.TEXT     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType     // Catch:{ NoSuchFieldError -> 0x0083 }
                com.airbnb.lottie.model.layer.Layer$LayerType r1 = com.airbnb.lottie.model.layer.Layer.LayerType.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.BaseLayer.AnonymousClass1.<clinit>():void");
        }
    }

    private void intersectBoundsWithMatte(RectF rectF, Matrix matrix2) {
        if (hasMatteOnThisLayer() && this.layerModel.getMatteType() != Layer.MatteType.INVERT) {
            this.matteBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.matteLayer.getBounds(this.matteBoundsRect, matrix2, true);
            if (!rectF.intersect(this.matteBoundsRect)) {
                rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
    }

    private void applyMasks(Canvas canvas, Matrix matrix2) {
        if (L.isTraceEnabled()) {
            L.beginSection("Layer#saveLayer");
        }
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint, 19);
        if (Build.VERSION.SDK_INT < 28) {
            clearCanvas(canvas);
        }
        if (L.isTraceEnabled()) {
            L.endSection("Layer#saveLayer");
        }
        for (int i = 0; i < this.mask.getMasks().size(); i++) {
            Mask mask2 = (Mask) this.mask.getMasks().get(i);
            BaseKeyframeAnimation baseKeyframeAnimation = (BaseKeyframeAnimation) this.mask.getMaskAnimations().get(i);
            BaseKeyframeAnimation baseKeyframeAnimation2 = (BaseKeyframeAnimation) this.mask.getOpacityAnimations().get(i);
            int i2 = AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[mask2.getMaskMode().ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    if (i == 0) {
                        this.contentPaint.setColor(-16777216);
                        this.contentPaint.setAlpha(255);
                        canvas.drawRect(this.rect, this.contentPaint);
                    }
                    if (mask2.isInverted()) {
                        applyInvertedSubtractMask(canvas, matrix2, baseKeyframeAnimation, baseKeyframeAnimation2);
                    } else {
                        applySubtractMask(canvas, matrix2, baseKeyframeAnimation);
                    }
                } else if (i2 != 3) {
                    if (i2 == 4) {
                        if (mask2.isInverted()) {
                            applyInvertedAddMask(canvas, matrix2, baseKeyframeAnimation, baseKeyframeAnimation2);
                        } else {
                            applyAddMask(canvas, matrix2, baseKeyframeAnimation, baseKeyframeAnimation2);
                        }
                    }
                } else if (mask2.isInverted()) {
                    applyInvertedIntersectMask(canvas, matrix2, baseKeyframeAnimation, baseKeyframeAnimation2);
                } else {
                    applyIntersectMask(canvas, matrix2, baseKeyframeAnimation, baseKeyframeAnimation2);
                }
            } else if (areAllMasksNone()) {
                this.contentPaint.setAlpha(255);
                canvas.drawRect(this.rect, this.contentPaint);
            }
        }
        if (L.isTraceEnabled()) {
            L.beginSection("Layer#restoreLayer");
        }
        canvas.restore();
        if (L.isTraceEnabled()) {
            L.endSection("Layer#restoreLayer");
        }
    }

    private boolean areAllMasksNone() {
        if (this.mask.getMaskAnimations().isEmpty()) {
            return false;
        }
        for (int i = 0; i < this.mask.getMasks().size(); i++) {
            if (((Mask) this.mask.getMasks().get(i)).getMaskMode() != Mask.MaskMode.MASK_MODE_NONE) {
                return false;
            }
        }
        return true;
    }

    private void applyAddMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation baseKeyframeAnimation, BaseKeyframeAnimation baseKeyframeAnimation2) {
        this.path.set((Path) baseKeyframeAnimation.getValue());
        this.path.transform(matrix2);
        this.contentPaint.setAlpha((int) (((float) ((Integer) baseKeyframeAnimation2.getValue()).intValue()) * 2.55f));
        canvas.drawPath(this.path, this.contentPaint);
    }

    private void applyInvertedAddMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation baseKeyframeAnimation, BaseKeyframeAnimation baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.rect, this.contentPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.path.set((Path) baseKeyframeAnimation.getValue());
        this.path.transform(matrix2);
        this.contentPaint.setAlpha((int) (((float) ((Integer) baseKeyframeAnimation2.getValue()).intValue()) * 2.55f));
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applySubtractMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation baseKeyframeAnimation) {
        this.path.set((Path) baseKeyframeAnimation.getValue());
        this.path.transform(matrix2);
        canvas.drawPath(this.path, this.dstOutPaint);
    }

    private void applyInvertedSubtractMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation baseKeyframeAnimation, BaseKeyframeAnimation baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstOutPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha((int) (((float) ((Integer) baseKeyframeAnimation2.getValue()).intValue()) * 2.55f));
        this.path.set((Path) baseKeyframeAnimation.getValue());
        this.path.transform(matrix2);
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applyIntersectMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation baseKeyframeAnimation, BaseKeyframeAnimation baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint);
        this.path.set((Path) baseKeyframeAnimation.getValue());
        this.path.transform(matrix2);
        this.contentPaint.setAlpha((int) (((float) ((Integer) baseKeyframeAnimation2.getValue()).intValue()) * 2.55f));
        canvas.drawPath(this.path, this.contentPaint);
        canvas.restore();
    }

    private void applyInvertedIntersectMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation baseKeyframeAnimation, BaseKeyframeAnimation baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha((int) (((float) ((Integer) baseKeyframeAnimation2.getValue()).intValue()) * 2.55f));
        this.path.set((Path) baseKeyframeAnimation.getValue());
        this.path.transform(matrix2);
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    /* access modifiers changed from: package-private */
    public boolean hasMasksOnThisLayer() {
        MaskKeyframeAnimation maskKeyframeAnimation = this.mask;
        return maskKeyframeAnimation != null && !maskKeyframeAnimation.getMaskAnimations().isEmpty();
    }

    private void setVisible(boolean z) {
        if (z != this.visible) {
            this.visible = z;
            invalidateSelf();
        }
    }

    /* access modifiers changed from: package-private */
    public void setProgress(float f) {
        if (L.isTraceEnabled()) {
            L.beginSection("BaseLayer#setProgress");
            L.beginSection("BaseLayer#setProgress.transform");
        }
        this.transform.setProgress(f);
        if (L.isTraceEnabled()) {
            L.endSection("BaseLayer#setProgress.transform");
        }
        if (this.mask != null) {
            if (L.isTraceEnabled()) {
                L.beginSection("BaseLayer#setProgress.mask");
            }
            for (int i = 0; i < this.mask.getMaskAnimations().size(); i++) {
                ((BaseKeyframeAnimation) this.mask.getMaskAnimations().get(i)).setProgress(f);
            }
            if (L.isTraceEnabled()) {
                L.endSection("BaseLayer#setProgress.mask");
            }
        }
        if (this.inOutAnimation != null) {
            if (L.isTraceEnabled()) {
                L.beginSection("BaseLayer#setProgress.inout");
            }
            this.inOutAnimation.setProgress(f);
            if (L.isTraceEnabled()) {
                L.endSection("BaseLayer#setProgress.inout");
            }
        }
        if (this.matteLayer != null) {
            if (L.isTraceEnabled()) {
                L.beginSection("BaseLayer#setProgress.matte");
            }
            this.matteLayer.setProgress(f);
            if (L.isTraceEnabled()) {
                L.endSection("BaseLayer#setProgress.matte");
            }
        }
        if (L.isTraceEnabled()) {
            L.beginSection("BaseLayer#setProgress.animations." + this.animations.size());
        }
        for (int i2 = 0; i2 < this.animations.size(); i2++) {
            ((BaseKeyframeAnimation) this.animations.get(i2)).setProgress(f);
        }
        if (L.isTraceEnabled()) {
            L.endSection("BaseLayer#setProgress.animations." + this.animations.size());
            L.endSection("BaseLayer#setProgress");
        }
    }

    private void buildParentLayerListIfNeeded() {
        if (this.parentLayers == null) {
            if (this.parentLayer == null) {
                this.parentLayers = Collections.emptyList();
                return;
            }
            this.parentLayers = new ArrayList();
            for (BaseLayer baseLayer = this.parentLayer; baseLayer != null; baseLayer = baseLayer.parentLayer) {
                this.parentLayers.add(baseLayer);
            }
        }
    }

    public String getName() {
        return this.layerModel.getName();
    }

    public BlurEffect getBlurEffect() {
        return this.layerModel.getBlurEffect();
    }

    public LBlendMode getBlendMode() {
        return this.layerModel.getBlendMode();
    }

    public BlurMaskFilter getBlurMaskFilter(float f) {
        if (this.blurMaskFilterRadius == f) {
            return this.blurMaskFilter;
        }
        BlurMaskFilter blurMaskFilter2 = new BlurMaskFilter(f / 2.0f, BlurMaskFilter.Blur.NORMAL);
        this.blurMaskFilter = blurMaskFilter2;
        this.blurMaskFilterRadius = f;
        return blurMaskFilter2;
    }

    public DropShadowEffect getDropShadowEffect() {
        return this.layerModel.getDropShadowEffect();
    }

    public void resolveKeyPath(KeyPath keyPath, int i, List list, KeyPath keyPath2) {
        BaseLayer baseLayer = this.matteLayer;
        if (baseLayer != null) {
            KeyPath addKey = keyPath2.addKey(baseLayer.getName());
            if (keyPath.fullyResolvesTo(this.matteLayer.getName(), i)) {
                list.add(addKey.resolve(this.matteLayer));
            }
            if (keyPath.propagateToChildren(getName(), i)) {
                this.matteLayer.resolveChildKeyPath(keyPath, keyPath.incrementDepthBy(this.matteLayer.getName(), i) + i, list, addKey);
            }
        }
        if (keyPath.matches(getName(), i)) {
            if (!"__container".equals(getName())) {
                keyPath2 = keyPath2.addKey(getName());
                if (keyPath.fullyResolvesTo(getName(), i)) {
                    list.add(keyPath2.resolve(this));
                }
            }
            if (keyPath.propagateToChildren(getName(), i)) {
                resolveChildKeyPath(keyPath, i + keyPath.incrementDepthBy(getName(), i), list, keyPath2);
            }
        }
    }

    public void addValueCallback(Object obj, LottieValueCallback lottieValueCallback) {
        this.transform.applyValueCallback(obj, lottieValueCallback);
    }
}
