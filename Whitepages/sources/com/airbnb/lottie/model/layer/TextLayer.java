package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTextRangeSelector;
import com.airbnb.lottie.model.animatable.AnimatableTextStyle;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.model.content.TextRangeUnits;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextLayer extends BaseLayer {
    private final LongSparseArray codePointCache = new LongSparseArray();
    private BaseKeyframeAnimation colorAnimation;
    private BaseKeyframeAnimation colorCallbackAnimation;
    private final LottieComposition composition;
    private final Map contentsForCharacter = new HashMap();
    private final Paint fillPaint = new Paint(1) {
        {
            setStyle(Paint.Style.FILL);
        }
    };
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix = new Matrix();
    private BaseKeyframeAnimation opacityAnimation;
    private final RectF rectF = new RectF();
    private final StringBuilder stringBuilder = new StringBuilder(2);
    private BaseKeyframeAnimation strokeColorAnimation;
    private BaseKeyframeAnimation strokeColorCallbackAnimation;
    private final Paint strokePaint = new Paint(1) {
        {
            setStyle(Paint.Style.STROKE);
        }
    };
    private BaseKeyframeAnimation strokeWidthAnimation;
    private BaseKeyframeAnimation strokeWidthCallbackAnimation;
    private final TextKeyframeAnimation textAnimation;
    private BaseKeyframeAnimation textRangeEndAnimation;
    private BaseKeyframeAnimation textRangeOffsetAnimation;
    private BaseKeyframeAnimation textRangeStartAnimation;
    private TextRangeUnits textRangeUnits = TextRangeUnits.INDEX;
    private BaseKeyframeAnimation textSizeCallbackAnimation;
    private final List textSubLines = new ArrayList();
    private BaseKeyframeAnimation trackingAnimation;
    private BaseKeyframeAnimation trackingCallbackAnimation;
    private BaseKeyframeAnimation typefaceCallbackAnimation;

    TextLayer(LottieDrawable lottieDrawable2, Layer layer) {
        super(lottieDrawable2, layer);
        AnimatableTextRangeSelector animatableTextRangeSelector;
        AnimatableTextRangeSelector animatableTextRangeSelector2;
        AnimatableIntegerValue animatableIntegerValue;
        AnimatableTextRangeSelector animatableTextRangeSelector3;
        AnimatableIntegerValue animatableIntegerValue2;
        AnimatableTextRangeSelector animatableTextRangeSelector4;
        AnimatableIntegerValue animatableIntegerValue3;
        AnimatableTextStyle animatableTextStyle;
        AnimatableIntegerValue animatableIntegerValue4;
        AnimatableTextStyle animatableTextStyle2;
        AnimatableFloatValue animatableFloatValue;
        AnimatableTextStyle animatableTextStyle3;
        AnimatableFloatValue animatableFloatValue2;
        AnimatableTextStyle animatableTextStyle4;
        AnimatableColorValue animatableColorValue;
        AnimatableTextStyle animatableTextStyle5;
        AnimatableColorValue animatableColorValue2;
        this.lottieDrawable = lottieDrawable2;
        this.composition = layer.getComposition();
        TextKeyframeAnimation createAnimation = layer.getText().createAnimation();
        this.textAnimation = createAnimation;
        createAnimation.addUpdateListener(this);
        addAnimation(createAnimation);
        AnimatableTextProperties textProperties = layer.getTextProperties();
        if (!(textProperties == null || (animatableTextStyle5 = textProperties.textStyle) == null || (animatableColorValue2 = animatableTextStyle5.color) == null)) {
            BaseKeyframeAnimation createAnimation2 = animatableColorValue2.createAnimation();
            this.colorAnimation = createAnimation2;
            createAnimation2.addUpdateListener(this);
            addAnimation(this.colorAnimation);
        }
        if (!(textProperties == null || (animatableTextStyle4 = textProperties.textStyle) == null || (animatableColorValue = animatableTextStyle4.stroke) == null)) {
            BaseKeyframeAnimation createAnimation3 = animatableColorValue.createAnimation();
            this.strokeColorAnimation = createAnimation3;
            createAnimation3.addUpdateListener(this);
            addAnimation(this.strokeColorAnimation);
        }
        if (!(textProperties == null || (animatableTextStyle3 = textProperties.textStyle) == null || (animatableFloatValue2 = animatableTextStyle3.strokeWidth) == null)) {
            FloatKeyframeAnimation createAnimation4 = animatableFloatValue2.createAnimation();
            this.strokeWidthAnimation = createAnimation4;
            createAnimation4.addUpdateListener(this);
            addAnimation(this.strokeWidthAnimation);
        }
        if (!(textProperties == null || (animatableTextStyle2 = textProperties.textStyle) == null || (animatableFloatValue = animatableTextStyle2.tracking) == null)) {
            FloatKeyframeAnimation createAnimation5 = animatableFloatValue.createAnimation();
            this.trackingAnimation = createAnimation5;
            createAnimation5.addUpdateListener(this);
            addAnimation(this.trackingAnimation);
        }
        if (!(textProperties == null || (animatableTextStyle = textProperties.textStyle) == null || (animatableIntegerValue4 = animatableTextStyle.opacity) == null)) {
            BaseKeyframeAnimation createAnimation6 = animatableIntegerValue4.createAnimation();
            this.opacityAnimation = createAnimation6;
            createAnimation6.addUpdateListener(this);
            addAnimation(this.opacityAnimation);
        }
        if (!(textProperties == null || (animatableTextRangeSelector4 = textProperties.rangeSelector) == null || (animatableIntegerValue3 = animatableTextRangeSelector4.start) == null)) {
            BaseKeyframeAnimation createAnimation7 = animatableIntegerValue3.createAnimation();
            this.textRangeStartAnimation = createAnimation7;
            createAnimation7.addUpdateListener(this);
            addAnimation(this.textRangeStartAnimation);
        }
        if (!(textProperties == null || (animatableTextRangeSelector3 = textProperties.rangeSelector) == null || (animatableIntegerValue2 = animatableTextRangeSelector3.end) == null)) {
            BaseKeyframeAnimation createAnimation8 = animatableIntegerValue2.createAnimation();
            this.textRangeEndAnimation = createAnimation8;
            createAnimation8.addUpdateListener(this);
            addAnimation(this.textRangeEndAnimation);
        }
        if (!(textProperties == null || (animatableTextRangeSelector2 = textProperties.rangeSelector) == null || (animatableIntegerValue = animatableTextRangeSelector2.offset) == null)) {
            BaseKeyframeAnimation createAnimation9 = animatableIntegerValue.createAnimation();
            this.textRangeOffsetAnimation = createAnimation9;
            createAnimation9.addUpdateListener(this);
            addAnimation(this.textRangeOffsetAnimation);
        }
        if (textProperties != null && (animatableTextRangeSelector = textProperties.rangeSelector) != null) {
            this.textRangeUnits = animatableTextRangeSelector.units;
        }
    }

    public void getBounds(RectF rectF2, Matrix matrix2, boolean z) {
        super.getBounds(rectF2, matrix2, z);
        rectF2.set(0.0f, 0.0f, (float) this.composition.getBounds().width(), (float) this.composition.getBounds().height());
    }

    /* access modifiers changed from: package-private */
    public void drawLayer(Canvas canvas, Matrix matrix2, int i) {
        DocumentData documentData = (DocumentData) this.textAnimation.getValue();
        Font font = (Font) this.composition.getFonts().get(documentData.fontName);
        if (font != null) {
            canvas.save();
            canvas.concat(matrix2);
            configurePaint(documentData, i, 0);
            if (this.lottieDrawable.useTextGlyphs()) {
                drawTextWithGlyphs(documentData, matrix2, font, canvas, i);
            } else {
                drawTextWithFont(documentData, font, canvas, i);
            }
            canvas.restore();
        }
    }

    private void configurePaint(DocumentData documentData, int i, int i2) {
        BaseKeyframeAnimation baseKeyframeAnimation = this.colorCallbackAnimation;
        if (baseKeyframeAnimation != null) {
            this.fillPaint.setColor(((Integer) baseKeyframeAnimation.getValue()).intValue());
        } else if (this.colorAnimation == null || !isIndexInRangeSelection(i2)) {
            this.fillPaint.setColor(documentData.color);
        } else {
            this.fillPaint.setColor(((Integer) this.colorAnimation.getValue()).intValue());
        }
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.strokeColorCallbackAnimation;
        if (baseKeyframeAnimation2 != null) {
            this.strokePaint.setColor(((Integer) baseKeyframeAnimation2.getValue()).intValue());
        } else if (this.strokeColorAnimation == null || !isIndexInRangeSelection(i2)) {
            this.strokePaint.setColor(documentData.strokeColor);
        } else {
            this.strokePaint.setColor(((Integer) this.strokeColorAnimation.getValue()).intValue());
        }
        int i3 = 100;
        int intValue = this.transform.getOpacity() == null ? 100 : ((Integer) this.transform.getOpacity().getValue()).intValue();
        if (this.opacityAnimation != null && isIndexInRangeSelection(i2)) {
            i3 = ((Integer) this.opacityAnimation.getValue()).intValue();
        }
        int round = Math.round(((((((float) intValue) * 255.0f) / 100.0f) * (((float) i3) / 100.0f)) * ((float) i)) / 255.0f);
        this.fillPaint.setAlpha(round);
        this.strokePaint.setAlpha(round);
        BaseKeyframeAnimation baseKeyframeAnimation3 = this.strokeWidthCallbackAnimation;
        if (baseKeyframeAnimation3 != null) {
            this.strokePaint.setStrokeWidth(((Float) baseKeyframeAnimation3.getValue()).floatValue());
        } else if (this.strokeWidthAnimation == null || !isIndexInRangeSelection(i2)) {
            this.strokePaint.setStrokeWidth(documentData.strokeWidth * Utils.dpScale());
        } else {
            this.strokePaint.setStrokeWidth(((Float) this.strokeWidthAnimation.getValue()).floatValue());
        }
    }

    private boolean isIndexInRangeSelection(int i) {
        int length = ((DocumentData) this.textAnimation.getValue()).text.length();
        BaseKeyframeAnimation baseKeyframeAnimation = this.textRangeStartAnimation;
        if (baseKeyframeAnimation == null || this.textRangeEndAnimation == null) {
            return true;
        }
        int min = Math.min(((Integer) baseKeyframeAnimation.getValue()).intValue(), ((Integer) this.textRangeEndAnimation.getValue()).intValue());
        int max = Math.max(((Integer) this.textRangeStartAnimation.getValue()).intValue(), ((Integer) this.textRangeEndAnimation.getValue()).intValue());
        BaseKeyframeAnimation baseKeyframeAnimation2 = this.textRangeOffsetAnimation;
        if (baseKeyframeAnimation2 != null) {
            int intValue = ((Integer) baseKeyframeAnimation2.getValue()).intValue();
            min += intValue;
            max += intValue;
        }
        if (this.textRangeUnits != TextRangeUnits.INDEX) {
            float f = (((float) i) / ((float) length)) * 100.0f;
            if (f < ((float) min) || f >= ((float) max)) {
                return false;
            }
            return true;
        } else if (i < min || i >= max) {
            return false;
        } else {
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void drawTextWithGlyphs(com.airbnb.lottie.model.DocumentData r22, android.graphics.Matrix r23, com.airbnb.lottie.model.Font r24, android.graphics.Canvas r25, int r26) {
        /*
            r21 = this;
            r9 = r21
            r10 = r22
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r0 = r9.textSizeCallbackAnimation
            if (r0 == 0) goto L_0x0013
            java.lang.Object r0 = r0.getValue()
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            goto L_0x0015
        L_0x0013:
            float r0 = r10.size
        L_0x0015:
            r1 = 1120403456(0x42c80000, float:100.0)
            float r11 = r0 / r1
            float r12 = com.airbnb.lottie.utils.Utils.getScale(r23)
            java.lang.String r0 = r10.text
            java.util.List r13 = r9.getTextLines(r0)
            int r14 = r13.size()
            int r0 = r10.tracking
            float r0 = (float) r0
            r1 = 1092616192(0x41200000, float:10.0)
            float r0 = r0 / r1
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r1 = r9.trackingCallbackAnimation
            if (r1 == 0) goto L_0x003e
            java.lang.Object r1 = r1.getValue()
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
        L_0x003b:
            float r0 = r0 + r1
        L_0x003c:
            r15 = r0
            goto L_0x004d
        L_0x003e:
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r1 = r9.trackingAnimation
            if (r1 == 0) goto L_0x003c
            java.lang.Object r1 = r1.getValue()
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
            goto L_0x003b
        L_0x004d:
            r16 = 0
            r0 = -1
            r7 = r0
            r8 = r16
        L_0x0053:
            if (r8 >= r14) goto L_0x00c6
            java.lang.Object r0 = r13.get(r8)
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            android.graphics.PointF r0 = r10.boxSize
            if (r0 != 0) goto L_0x0063
            r0 = 0
        L_0x0061:
            r2 = r0
            goto L_0x0066
        L_0x0063:
            float r0 = r0.x
            goto L_0x0061
        L_0x0066:
            r6 = 1
            r0 = r21
            r3 = r24
            r4 = r11
            r5 = r15
            java.util.List r6 = r0.splitGlyphTextIntoLines(r1, r2, r3, r4, r5, r6)
            r5 = r16
        L_0x0073:
            int r0 = r6.size()
            if (r5 >= r0) goto L_0x00c1
            java.lang.Object r0 = r6.get(r5)
            com.airbnb.lottie.model.layer.TextLayer$TextSubLine r0 = (com.airbnb.lottie.model.layer.TextLayer.TextSubLine) r0
            int r7 = r7 + 1
            r25.save()
            float r1 = r0.width
            r4 = r25
            boolean r1 = r9.offsetCanvas(r4, r10, r7, r1)
            if (r1 == 0) goto L_0x00ad
            java.lang.String r1 = r0.text
            r0 = r21
            r2 = r22
            r3 = r24
            r4 = r25
            r17 = r5
            r5 = r12
            r18 = r6
            r6 = r11
            r19 = r7
            r7 = r15
            r20 = r8
            r8 = r26
            r0.drawGlyphTextLine(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x00b5
        L_0x00ad:
            r17 = r5
            r18 = r6
            r19 = r7
            r20 = r8
        L_0x00b5:
            r25.restore()
            int r5 = r17 + 1
            r6 = r18
            r7 = r19
            r8 = r20
            goto L_0x0073
        L_0x00c1:
            r20 = r8
            int r8 = r20 + 1
            goto L_0x0053
        L_0x00c6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.TextLayer.drawTextWithGlyphs(com.airbnb.lottie.model.DocumentData, android.graphics.Matrix, com.airbnb.lottie.model.Font, android.graphics.Canvas, int):void");
    }

    private void drawGlyphTextLine(String str, DocumentData documentData, Font font, Canvas canvas, float f, float f2, float f3, int i) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            String str2 = str;
            FontCharacter fontCharacter = (FontCharacter) this.composition.getCharacters().get(FontCharacter.hashFor(str.charAt(i2), font.getFamily(), font.getStyle()));
            if (fontCharacter == null) {
                Canvas canvas2 = canvas;
            } else {
                drawCharacterAsGlyph(fontCharacter, f2, documentData, canvas, i2, i);
                Canvas canvas3 = canvas;
                canvas.translate((((float) fontCharacter.getWidth()) * f2 * Utils.dpScale()) + f3, 0.0f);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0094  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void drawTextWithFont(com.airbnb.lottie.model.DocumentData r21, com.airbnb.lottie.model.Font r22, android.graphics.Canvas r23, int r24) {
        /*
            r20 = this;
            r7 = r20
            r8 = r21
            r9 = r22
            android.graphics.Typeface r0 = r7.getTypeface(r9)
            if (r0 != 0) goto L_0x000d
            return
        L_0x000d:
            java.lang.String r1 = r8.text
            com.airbnb.lottie.LottieDrawable r2 = r7.lottieDrawable
            com.airbnb.lottie.TextDelegate r2 = r2.getTextDelegate()
            if (r2 == 0) goto L_0x001f
            java.lang.String r3 = r20.getName()
            java.lang.String r1 = r2.getTextInternal(r3, r1)
        L_0x001f:
            android.graphics.Paint r2 = r7.fillPaint
            r2.setTypeface(r0)
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r0 = r7.textSizeCallbackAnimation
            if (r0 == 0) goto L_0x0033
            java.lang.Object r0 = r0.getValue()
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            goto L_0x0035
        L_0x0033:
            float r0 = r8.size
        L_0x0035:
            android.graphics.Paint r2 = r7.fillPaint
            float r3 = com.airbnb.lottie.utils.Utils.dpScale()
            float r3 = r3 * r0
            r2.setTextSize(r3)
            android.graphics.Paint r2 = r7.strokePaint
            android.graphics.Paint r3 = r7.fillPaint
            android.graphics.Typeface r3 = r3.getTypeface()
            r2.setTypeface(r3)
            android.graphics.Paint r2 = r7.strokePaint
            android.graphics.Paint r3 = r7.fillPaint
            float r3 = r3.getTextSize()
            r2.setTextSize(r3)
            int r2 = r8.tracking
            float r2 = (float) r2
            r3 = 1092616192(0x41200000, float:10.0)
            float r2 = r2 / r3
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r3 = r7.trackingCallbackAnimation
            if (r3 == 0) goto L_0x006b
            java.lang.Object r3 = r3.getValue()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
        L_0x0069:
            float r2 = r2 + r3
            goto L_0x007a
        L_0x006b:
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r3 = r7.trackingAnimation
            if (r3 == 0) goto L_0x007a
            java.lang.Object r3 = r3.getValue()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
            goto L_0x0069
        L_0x007a:
            float r3 = com.airbnb.lottie.utils.Utils.dpScale()
            float r2 = r2 * r3
            float r2 = r2 * r0
            r0 = 1120403456(0x42c80000, float:100.0)
            float r10 = r2 / r0
            java.util.List r11 = r7.getTextLines(r1)
            int r12 = r11.size()
            r13 = 0
            r0 = -1
            r14 = r0
            r15 = r13
            r16 = r15
        L_0x0092:
            if (r15 >= r12) goto L_0x0100
            java.lang.Object r0 = r11.get(r15)
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            android.graphics.PointF r0 = r8.boxSize
            if (r0 != 0) goto L_0x00a2
            r0 = 0
        L_0x00a0:
            r2 = r0
            goto L_0x00a5
        L_0x00a2:
            float r0 = r0.x
            goto L_0x00a0
        L_0x00a5:
            r4 = 0
            r6 = 0
            r0 = r20
            r3 = r22
            r5 = r10
            java.util.List r6 = r0.splitGlyphTextIntoLines(r1, r2, r3, r4, r5, r6)
            r5 = r13
        L_0x00b1:
            int r0 = r6.size()
            if (r5 >= r0) goto L_0x00fd
            java.lang.Object r0 = r6.get(r5)
            r17 = r0
            com.airbnb.lottie.model.layer.TextLayer$TextSubLine r17 = (com.airbnb.lottie.model.layer.TextLayer.TextSubLine) r17
            int r14 = r14 + 1
            r23.save()
            float r0 = r17.width
            r4 = r23
            boolean r0 = r7.offsetCanvas(r4, r8, r14, r0)
            if (r0 == 0) goto L_0x00e7
            java.lang.String r1 = r17.text
            r0 = r20
            r2 = r21
            r3 = r23
            r4 = r10
            r18 = r5
            r5 = r16
            r19 = r6
            r6 = r24
            r0.drawFontTextLine(r1, r2, r3, r4, r5, r6)
            goto L_0x00eb
        L_0x00e7:
            r18 = r5
            r19 = r6
        L_0x00eb:
            java.lang.String r0 = r17.text
            int r0 = r0.length()
            int r16 = r16 + r0
            r23.restore()
            int r5 = r18 + 1
            r6 = r19
            goto L_0x00b1
        L_0x00fd:
            int r15 = r15 + 1
            goto L_0x0092
        L_0x0100:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.TextLayer.drawTextWithFont(com.airbnb.lottie.model.DocumentData, com.airbnb.lottie.model.Font, android.graphics.Canvas, int):void");
    }

    private boolean offsetCanvas(Canvas canvas, DocumentData documentData, int i, float f) {
        float f2;
        float f3;
        PointF pointF = documentData.boxPosition;
        PointF pointF2 = documentData.boxSize;
        float dpScale = Utils.dpScale();
        float f4 = 0.0f;
        if (pointF == null) {
            f2 = 0.0f;
        } else {
            f2 = (documentData.lineHeight * dpScale) + pointF.y;
        }
        float f5 = (((float) i) * documentData.lineHeight * dpScale) + f2;
        if (this.lottieDrawable.getClipTextToBoundingBox() && pointF2 != null && pointF != null && f5 >= pointF.y + pointF2.y + documentData.size) {
            return false;
        }
        if (pointF == null) {
            f3 = 0.0f;
        } else {
            f3 = pointF.x;
        }
        if (pointF2 != null) {
            f4 = pointF2.x;
        }
        int i2 = AnonymousClass3.$SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[documentData.justification.ordinal()];
        if (i2 == 1) {
            canvas.translate(f3, f5);
        } else if (i2 == 2) {
            canvas.translate((f3 + f4) - f, f5);
        } else if (i2 == 3) {
            canvas.translate((f3 + (f4 / 2.0f)) - (f / 2.0f), f5);
        }
        return true;
    }

    /* renamed from: com.airbnb.lottie.model.layer.TextLayer$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.airbnb.lottie.model.DocumentData$Justification[] r0 = com.airbnb.lottie.model.DocumentData.Justification.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification = r0
                com.airbnb.lottie.model.DocumentData$Justification r1 = com.airbnb.lottie.model.DocumentData.Justification.LEFT_ALIGN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification     // Catch:{ NoSuchFieldError -> 0x001d }
                com.airbnb.lottie.model.DocumentData$Justification r1 = com.airbnb.lottie.model.DocumentData.Justification.RIGHT_ALIGN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.airbnb.lottie.model.DocumentData$Justification r1 = com.airbnb.lottie.model.DocumentData.Justification.CENTER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.TextLayer.AnonymousClass3.<clinit>():void");
        }
    }

    private Typeface getTypeface(Font font) {
        Typeface typeface;
        BaseKeyframeAnimation baseKeyframeAnimation = this.typefaceCallbackAnimation;
        if (baseKeyframeAnimation != null && (typeface = (Typeface) baseKeyframeAnimation.getValue()) != null) {
            return typeface;
        }
        Typeface typeface2 = this.lottieDrawable.getTypeface(font);
        if (typeface2 != null) {
            return typeface2;
        }
        return font.getTypeface();
    }

    private List getTextLines(String str) {
        return Arrays.asList(str.replaceAll("\r\n", "\r").replaceAll("\u0003", "\r").replaceAll(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "\r").split("\r"));
    }

    private void drawFontTextLine(String str, DocumentData documentData, Canvas canvas, float f, int i, int i2) {
        int i3 = 0;
        while (i3 < str.length()) {
            String codePointToString = codePointToString(str, i3);
            drawCharacterFromFont(codePointToString, documentData, canvas, i + i3, i2);
            canvas.translate(this.fillPaint.measureText(codePointToString) + f, 0.0f);
            i3 += codePointToString.length();
        }
    }

    private List splitGlyphTextIntoLines(String str, float f, Font font, float f2, float f3, boolean z) {
        float measureText;
        String str2 = str;
        int i = 0;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (int i4 = 0; i4 < str.length(); i4++) {
            char charAt = str2.charAt(i4);
            if (z) {
                FontCharacter fontCharacter = (FontCharacter) this.composition.getCharacters().get(FontCharacter.hashFor(charAt, font.getFamily(), font.getStyle()));
                if (fontCharacter == null) {
                } else {
                    measureText = ((float) fontCharacter.getWidth()) * f2 * Utils.dpScale();
                }
            } else {
                measureText = this.fillPaint.measureText(str2.substring(i4, i4 + 1));
            }
            float f7 = measureText + f3;
            if (charAt == ' ') {
                z2 = true;
                f6 = f7;
            } else if (z2) {
                z2 = false;
                i3 = i4;
                f5 = f7;
            } else {
                f5 += f7;
            }
            f4 += f7;
            if (f > 0.0f && f4 >= f && charAt != ' ') {
                i++;
                TextSubLine ensureEnoughSubLines = ensureEnoughSubLines(i);
                if (i3 == i2) {
                    String substring = str2.substring(i2, i4);
                    String trim = substring.trim();
                    ensureEnoughSubLines.set(trim, (f4 - f7) - (((float) (trim.length() - substring.length())) * f6));
                    i2 = i4;
                    i3 = i2;
                    f4 = f7;
                    f5 = f4;
                } else {
                    String substring2 = str2.substring(i2, i3 - 1);
                    String trim2 = substring2.trim();
                    ensureEnoughSubLines.set(trim2, ((f4 - f5) - (((float) (substring2.length() - trim2.length())) * f6)) - f6);
                    f4 = f5;
                    i2 = i3;
                }
            }
        }
        if (f4 > 0.0f) {
            i++;
            ensureEnoughSubLines(i).set(str2.substring(i2), f4);
        }
        return this.textSubLines.subList(0, i);
    }

    private TextSubLine ensureEnoughSubLines(int i) {
        for (int size = this.textSubLines.size(); size < i; size++) {
            this.textSubLines.add(new TextSubLine());
        }
        return (TextSubLine) this.textSubLines.get(i - 1);
    }

    private void drawCharacterAsGlyph(FontCharacter fontCharacter, float f, DocumentData documentData, Canvas canvas, int i, int i2) {
        configurePaint(documentData, i2, i);
        List contentsForCharacter2 = getContentsForCharacter(fontCharacter);
        for (int i3 = 0; i3 < contentsForCharacter2.size(); i3++) {
            Path path = ((ContentGroup) contentsForCharacter2.get(i3)).getPath();
            path.computeBounds(this.rectF, false);
            this.matrix.reset();
            this.matrix.preTranslate(0.0f, (-documentData.baselineShift) * Utils.dpScale());
            this.matrix.preScale(f, f);
            path.transform(this.matrix);
            if (documentData.strokeOverFill) {
                drawGlyph(path, this.fillPaint, canvas);
                drawGlyph(path, this.strokePaint, canvas);
            } else {
                drawGlyph(path, this.strokePaint, canvas);
                drawGlyph(path, this.fillPaint, canvas);
            }
        }
    }

    private void drawGlyph(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() != 0) {
            if (paint.getStyle() != Paint.Style.STROKE || paint.getStrokeWidth() != 0.0f) {
                canvas.drawPath(path, paint);
            }
        }
    }

    private void drawCharacterFromFont(String str, DocumentData documentData, Canvas canvas, int i, int i2) {
        configurePaint(documentData, i2, i);
        if (documentData.strokeOverFill) {
            drawCharacter(str, this.fillPaint, canvas);
            drawCharacter(str, this.strokePaint, canvas);
            return;
        }
        drawCharacter(str, this.strokePaint, canvas);
        drawCharacter(str, this.fillPaint, canvas);
    }

    private void drawCharacter(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() != 0) {
            if (paint.getStyle() != Paint.Style.STROKE || paint.getStrokeWidth() != 0.0f) {
                canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
            }
        }
    }

    private List getContentsForCharacter(FontCharacter fontCharacter) {
        if (this.contentsForCharacter.containsKey(fontCharacter)) {
            return (List) this.contentsForCharacter.get(fontCharacter);
        }
        List shapes = fontCharacter.getShapes();
        int size = shapes.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new ContentGroup(this.lottieDrawable, this, (ShapeGroup) shapes.get(i), this.composition));
        }
        this.contentsForCharacter.put(fontCharacter, arrayList);
        return arrayList;
    }

    private String codePointToString(String str, int i) {
        int codePointAt = str.codePointAt(i);
        int charCount = Character.charCount(codePointAt) + i;
        while (charCount < str.length()) {
            int codePointAt2 = str.codePointAt(charCount);
            if (!isModifier(codePointAt2)) {
                break;
            }
            charCount += Character.charCount(codePointAt2);
            codePointAt = (codePointAt * 31) + codePointAt2;
        }
        long j = (long) codePointAt;
        if (this.codePointCache.containsKey(j)) {
            return (String) this.codePointCache.get(j);
        }
        this.stringBuilder.setLength(0);
        while (i < charCount) {
            int codePointAt3 = str.codePointAt(i);
            this.stringBuilder.appendCodePoint(codePointAt3);
            i += Character.charCount(codePointAt3);
        }
        String sb = this.stringBuilder.toString();
        this.codePointCache.put(j, sb);
        return sb;
    }

    private boolean isModifier(int i) {
        return Character.getType(i) == 16 || Character.getType(i) == 27 || Character.getType(i) == 6 || Character.getType(i) == 28 || Character.getType(i) == 8 || Character.getType(i) == 19;
    }

    public void addValueCallback(Object obj, LottieValueCallback lottieValueCallback) {
        super.addValueCallback(obj, lottieValueCallback);
        if (obj == LottieProperty.COLOR) {
            BaseKeyframeAnimation baseKeyframeAnimation = this.colorCallbackAnimation;
            if (baseKeyframeAnimation != null) {
                removeAnimation(baseKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.colorCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.colorCallbackAnimation = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.addUpdateListener(this);
            addAnimation(this.colorCallbackAnimation);
        } else if (obj == LottieProperty.STROKE_COLOR) {
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.strokeColorCallbackAnimation;
            if (baseKeyframeAnimation2 != null) {
                removeAnimation(baseKeyframeAnimation2);
            }
            if (lottieValueCallback == null) {
                this.strokeColorCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.strokeColorCallbackAnimation = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            addAnimation(this.strokeColorCallbackAnimation);
        } else if (obj == LottieProperty.STROKE_WIDTH) {
            BaseKeyframeAnimation baseKeyframeAnimation3 = this.strokeWidthCallbackAnimation;
            if (baseKeyframeAnimation3 != null) {
                removeAnimation(baseKeyframeAnimation3);
            }
            if (lottieValueCallback == null) {
                this.strokeWidthCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation3 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.strokeWidthCallbackAnimation = valueCallbackKeyframeAnimation3;
            valueCallbackKeyframeAnimation3.addUpdateListener(this);
            addAnimation(this.strokeWidthCallbackAnimation);
        } else if (obj == LottieProperty.TEXT_TRACKING) {
            BaseKeyframeAnimation baseKeyframeAnimation4 = this.trackingCallbackAnimation;
            if (baseKeyframeAnimation4 != null) {
                removeAnimation(baseKeyframeAnimation4);
            }
            if (lottieValueCallback == null) {
                this.trackingCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation4 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.trackingCallbackAnimation = valueCallbackKeyframeAnimation4;
            valueCallbackKeyframeAnimation4.addUpdateListener(this);
            addAnimation(this.trackingCallbackAnimation);
        } else if (obj == LottieProperty.TEXT_SIZE) {
            BaseKeyframeAnimation baseKeyframeAnimation5 = this.textSizeCallbackAnimation;
            if (baseKeyframeAnimation5 != null) {
                removeAnimation(baseKeyframeAnimation5);
            }
            if (lottieValueCallback == null) {
                this.textSizeCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation5 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.textSizeCallbackAnimation = valueCallbackKeyframeAnimation5;
            valueCallbackKeyframeAnimation5.addUpdateListener(this);
            addAnimation(this.textSizeCallbackAnimation);
        } else if (obj == LottieProperty.TYPEFACE) {
            BaseKeyframeAnimation baseKeyframeAnimation6 = this.typefaceCallbackAnimation;
            if (baseKeyframeAnimation6 != null) {
                removeAnimation(baseKeyframeAnimation6);
            }
            if (lottieValueCallback == null) {
                this.typefaceCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation6 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.typefaceCallbackAnimation = valueCallbackKeyframeAnimation6;
            valueCallbackKeyframeAnimation6.addUpdateListener(this);
            addAnimation(this.typefaceCallbackAnimation);
        } else if (obj == LottieProperty.TEXT) {
            this.textAnimation.setStringValueCallback(lottieValueCallback);
        }
    }

    private static class TextSubLine {
        /* access modifiers changed from: private */
        public String text;
        /* access modifiers changed from: private */
        public float width;

        private TextSubLine() {
            this.text = "";
            this.width = 0.0f;
        }

        /* access modifiers changed from: package-private */
        public void set(String str, float f) {
            this.text = str;
            this.width = f;
        }
    }
}
