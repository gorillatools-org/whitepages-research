package com.horcrux.svg;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewParent;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.views.text.ReactFontManager;
import java.text.Bidi;
import java.util.ArrayList;

class TSpanView extends TextView {
    private final AssetManager assets = this.mContext.getResources().getAssets();
    private final ArrayList emoji = new ArrayList();
    private final ArrayList emojiTransforms = new ArrayList();
    private Path mCachedPath;
    String mContent;
    private TextPathView textPath;

    public TSpanView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setContent(String str) {
        this.mContent = str;
        invalidate();
    }

    public void invalidate() {
        this.mCachedPath = null;
        super.invalidate();
    }

    /* access modifiers changed from: package-private */
    public void clearCache() {
        this.mCachedPath = null;
        super.clearCache();
    }

    /* access modifiers changed from: package-private */
    public void draw(Canvas canvas, Paint paint, float f) {
        if (this.mContent != null) {
            SVGLength sVGLength = this.mInlineSize;
            if (sVGLength == null || sVGLength.value == 0.0d) {
                int size = this.emoji.size();
                if (size > 0) {
                    applyTextPropertiesToPaint(paint, getTextRootGlyphContext().getFont());
                    for (int i = 0; i < size; i++) {
                        canvas.save();
                        canvas.concat((Matrix) this.emojiTransforms.get(i));
                        canvas.drawText((String) this.emoji.get(i), 0.0f, 0.0f, paint);
                        canvas.restore();
                    }
                }
                drawPath(canvas, paint, f);
                return;
            }
            if (setupFillPaint(paint, this.fillOpacity * f)) {
                drawWrappedText(canvas, paint);
            }
            if (setupStrokePaint(paint, f * this.strokeOpacity)) {
                drawWrappedText(canvas, paint);
                return;
            }
            return;
        }
        clip(canvas, paint);
        drawGroup(canvas, paint, f);
    }

    private void drawWrappedText(Canvas canvas, Paint paint) {
        Layout.Alignment alignment;
        GlyphContext textRootGlyphContext = getTextRootGlyphContext();
        pushGlyphContext();
        FontData font = textRootGlyphContext.getFont();
        TextPaint textPaint = new TextPaint(paint);
        applyTextPropertiesToPaint(textPaint, font);
        applySpacingAndFeatures(textPaint, font);
        double fontSize = textRootGlyphContext.getFontSize();
        int i = AnonymousClass1.$SwitchMap$com$horcrux$svg$TextProperties$TextAnchor[font.textAnchor.ordinal()];
        if (i == 2) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        } else if (i != 3) {
            alignment = Layout.Alignment.ALIGN_NORMAL;
        } else {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout staticLayout = getStaticLayout(textPaint, alignment, true, new SpannableString(this.mContent), (int) PropHelper.fromRelative(this.mInlineSize, (double) canvas.getWidth(), 0.0d, (double) this.mScale, fontSize));
        int lineAscent = staticLayout.getLineAscent(0);
        popGlyphContext();
        canvas.save();
        canvas.translate((float) textRootGlyphContext.nextX(0.0d), (float) (textRootGlyphContext.nextY() + ((double) lineAscent)));
        staticLayout.draw(canvas);
        canvas.restore();
    }

    private StaticLayout getStaticLayout(TextPaint textPaint, Layout.Alignment alignment, boolean z, SpannableString spannableString, int i) {
        return StaticLayout.Builder.obtain(spannableString, 0, spannableString.length(), textPaint, i).setAlignment(alignment).setLineSpacing(0.0f, 1.0f).setIncludePad(z).setBreakStrategy(1).setHyphenationFrequency(1).build();
    }

    public static String visualToLogical(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        Bidi bidi = new Bidi(str, -2);
        if (bidi.isLeftToRight()) {
            return str;
        }
        int runCount = bidi.getRunCount();
        byte[] bArr = new byte[runCount];
        Integer[] numArr = new Integer[runCount];
        for (int i = 0; i < runCount; i++) {
            bArr[i] = (byte) bidi.getRunLevel(i);
            numArr[i] = Integer.valueOf(i);
        }
        Bidi.reorderVisually(bArr, 0, numArr, 0, runCount);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < runCount; i2++) {
            int intValue = numArr[i2].intValue();
            int runStart = bidi.getRunStart(intValue);
            int runLimit = bidi.getRunLimit(intValue);
            if ((bArr[intValue] & 1) != 0) {
                while (true) {
                    runLimit--;
                    if (runLimit < runStart) {
                        break;
                    }
                    sb.append(str.charAt(runLimit));
                }
            } else {
                sb.append(str, runStart, runLimit);
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = this.mCachedPath;
        if (path != null) {
            return path;
        }
        if (this.mContent == null) {
            Path groupPath = getGroupPath(canvas, paint);
            this.mCachedPath = groupPath;
            return groupPath;
        }
        setupTextPath();
        pushGlyphContext();
        this.mCachedPath = getLinePath(visualToLogical(this.mContent), paint, canvas);
        popGlyphContext();
        return this.mCachedPath;
    }

    /* access modifiers changed from: package-private */
    public double getSubtreeTextChunksTotalAdvance(Paint paint) {
        if (!Double.isNaN(this.cachedAdvance)) {
            return this.cachedAdvance;
        }
        String str = this.mContent;
        double d = 0.0d;
        if (str == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof TextView) {
                    d += ((TextView) childAt).getSubtreeTextChunksTotalAdvance(paint);
                }
            }
            this.cachedAdvance = d;
            return d;
        } else if (str.length() == 0) {
            this.cachedAdvance = 0.0d;
            return 0.0d;
        } else {
            FontData font = getTextRootGlyphContext().getFont();
            applyTextPropertiesToPaint(paint, font);
            applySpacingAndFeatures(paint, font);
            double measureText = (double) paint.measureText(str);
            this.cachedAdvance = measureText;
            return measureText;
        }
    }

    private void applySpacingAndFeatures(Paint paint, FontData fontData) {
        double d = fontData.letterSpacing;
        paint.setLetterSpacing((float) (d / (fontData.fontSize * ((double) this.mScale))));
        if (d == 0.0d && fontData.fontVariantLigatures == TextProperties$FontVariantLigatures.normal) {
            paint.setFontFeatureSettings("'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk','kern', 'hlig', 'cala', " + fontData.fontFeatureSettings);
        } else {
            paint.setFontFeatureSettings("'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk','kern', 'liga' 0, 'clig' 0, 'dlig' 0, 'hlig' 0, 'cala' 0, " + fontData.fontFeatureSettings);
        }
        paint.setFontVariationSettings("'wght' " + fontData.absoluteFontWeight + fontData.fontVariationSettings);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01e2, code lost:
        r13 = r53;
        r12 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01ec, code lost:
        r3 = r11;
        r13 = r53;
        r12 = false;
        r11 = r72;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01fb, code lost:
        r11 = r3 * r7;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0317  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01d3  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0250 A[FALL_THROUGH] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0253  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x026b  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0279  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x028e A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Path getLinePath(java.lang.String r71, android.graphics.Paint r72, android.graphics.Canvas r73) {
        /*
            r70 = this;
            r6 = r70
            r14 = r72
            r15 = r73
            r12 = 0
            int r10 = r71.length()
            android.graphics.Path r9 = new android.graphics.Path
            r9.<init>()
            java.util.ArrayList r0 = r6.emoji
            r0.clear()
            java.util.ArrayList r0 = r6.emojiTransforms
            r0.clear()
            if (r10 != 0) goto L_0x001d
            return r9
        L_0x001d:
            com.horcrux.svg.TextPathView r0 = r6.textPath
            if (r0 == 0) goto L_0x0024
            r16 = 1
            goto L_0x0026
        L_0x0024:
            r16 = r12
        L_0x0026:
            r17 = 0
            if (r16 == 0) goto L_0x0048
            android.graphics.PathMeasure r0 = new android.graphics.PathMeasure
            com.horcrux.svg.TextPathView r1 = r6.textPath
            android.graphics.Path r1 = r1.getTextPath(r15, r14)
            r0.<init>(r1, r12)
            float r1 = r0.getLength()
            double r1 = (double) r1
            boolean r3 = r0.isClosed()
            int r4 = (r1 > r17 ? 1 : (r1 == r17 ? 0 : -1))
            if (r4 != 0) goto L_0x0043
            return r9
        L_0x0043:
            r8 = r0
            r4 = r1
            r19 = r3
            goto L_0x004e
        L_0x0048:
            r0 = 0
            r8 = r0
            r19 = r12
            r4 = r17
        L_0x004e:
            com.horcrux.svg.GlyphContext r2 = r70.getTextRootGlyphContext()
            com.horcrux.svg.FontData r0 = r2.getFont()
            r6.applyTextPropertiesToPaint(r14, r0)
            com.horcrux.svg.GlyphPathBag r3 = new com.horcrux.svg.GlyphPathBag
            r3.<init>(r14)
            boolean[] r1 = new boolean[r10]
            char[] r20 = r71.toCharArray()
            r22 = r8
            double r7 = r0.kerning
            double r11 = r0.wordSpacing
            double r13 = r0.letterSpacing
            r26 = r11
            boolean r12 = r0.manualKerning
            int r11 = (r13 > r17 ? 1 : (r13 == r17 ? 0 : -1))
            if (r11 != 0) goto L_0x007e
            com.horcrux.svg.TextProperties$FontVariantLigatures r11 = r0.fontVariantLigatures
            r28 = r1
            com.horcrux.svg.TextProperties$FontVariantLigatures r1 = com.horcrux.svg.TextProperties$FontVariantLigatures.normal
            if (r11 != r1) goto L_0x0080
            r1 = 1
            goto L_0x0081
        L_0x007e:
            r28 = r1
        L_0x0080:
            r1 = 0
        L_0x0081:
            if (r1 == 0) goto L_0x009e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r11 = "'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk','kern', 'hlig', 'cala', "
            r1.append(r11)
            java.lang.String r11 = r0.fontFeatureSettings
            r1.append(r11)
            java.lang.String r1 = r1.toString()
            r11 = r72
            r11.setFontFeatureSettings(r1)
            r29 = r3
            goto L_0x00b8
        L_0x009e:
            r11 = r72
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r29 = r3
            java.lang.String r3 = "'rlig', 'liga', 'clig', 'calt', 'locl', 'ccmp', 'mark', 'mkmk','kern', 'liga' 0, 'clig' 0, 'dlig' 0, 'hlig' 0, 'cala' 0, "
            r1.append(r3)
            java.lang.String r3 = r0.fontFeatureSettings
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r11.setFontFeatureSettings(r1)
        L_0x00b8:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "'wght' "
            r1.append(r3)
            int r3 = r0.absoluteFontWeight
            r1.append(r3)
            java.lang.String r3 = r0.fontVariationSettings
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r11.setFontVariationSettings(r1)
            com.facebook.react.bridge.ReadableMap r3 = r0.fontData
            float[] r1 = new float[r10]
            r30 = r3
            r3 = r71
            r11.getTextWidths(r3, r1)
            com.horcrux.svg.TextProperties$TextAnchor r3 = r0.textAnchor
            com.horcrux.svg.TextView r0 = r70.getTextAnchorRoot()
            r31 = r7
            double r7 = r0.getSubtreeTextChunksTotalAdvance(r11)
            double r33 = r6.getTextAnchorOffset(r3, r7)
            double r44 = r2.getFontSize()
            r46 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r16 == 0) goto L_0x014b
            com.horcrux.svg.TextPathView r0 = r6.textPath
            com.horcrux.svg.TextProperties$TextPathMidLine r0 = r0.getMidLine()
            r35 = r1
            com.horcrux.svg.TextProperties$TextPathMidLine r1 = com.horcrux.svg.TextProperties$TextPathMidLine.sharp
            if (r0 != r1) goto L_0x0105
            r36 = 1
            goto L_0x0107
        L_0x0105:
            r36 = 0
        L_0x0107:
            com.horcrux.svg.TextPathView r0 = r6.textPath
            com.horcrux.svg.TextProperties$TextPathSide r0 = r0.getSide()
            com.horcrux.svg.TextProperties$TextPathSide r1 = com.horcrux.svg.TextProperties$TextPathSide.right
            if (r0 != r1) goto L_0x0114
            r37 = -1
            goto L_0x0116
        L_0x0114:
            r37 = 1
        L_0x0116:
            com.horcrux.svg.TextPathView r0 = r6.textPath
            com.horcrux.svg.SVGLength r1 = r0.getStartOffset()
            r0 = r70
            r48 = r35
            r49 = r9
            r15 = r30
            r30 = r2
            r9 = r3
            r2 = r4
            r50 = r12
            r11 = r4
            r4 = r44
            double r0 = r0.getAbsoluteStartOffset(r1, r2, r4)
            double r33 = r33 + r0
            if (r19 == 0) goto L_0x0147
            double r4 = r11 / r46
            com.horcrux.svg.TextProperties$TextAnchor r2 = com.horcrux.svg.TextProperties$TextAnchor.middle
            if (r9 != r2) goto L_0x013d
            double r2 = -r4
            goto L_0x013f
        L_0x013d:
            r2 = r17
        L_0x013f:
            double r0 = r0 + r2
            double r4 = r0 + r11
        L_0x0142:
            r2 = r36
            r3 = r37
            goto L_0x015a
        L_0x0147:
            r4 = r11
            r0 = r17
            goto L_0x0142
        L_0x014b:
            r48 = r1
            r49 = r9
            r50 = r12
            r15 = r30
            r30 = r2
            r11 = r4
            r0 = r17
            r2 = 0
            r3 = 1
        L_0x015a:
            com.horcrux.svg.SVGLength r9 = r6.mTextLength
            r51 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r9 == 0) goto L_0x01a1
            r53 = r11
            int r11 = r73.getWidth()
            double r11 = (double) r11
            r71 = r2
            float r2 = r6.mScale
            r55 = r0
            double r0 = (double) r2
            r38 = 0
            r35 = r9
            r36 = r11
            r40 = r0
            r42 = r44
            double r0 = com.horcrux.svg.PropHelper.fromRelative(r35, r36, r38, r40, r42)
            int r2 = (r0 > r17 ? 1 : (r0 == r17 ? 0 : -1))
            if (r2 < 0) goto L_0x0199
            int[] r2 = com.horcrux.svg.TSpanView.AnonymousClass1.$SwitchMap$com$horcrux$svg$TextProperties$TextLengthAdjust
            com.horcrux.svg.TextProperties$TextLengthAdjust r9 = r6.mLengthAdjust
            int r9 = r9.ordinal()
            r2 = r2[r9]
            r9 = 2
            if (r2 == r9) goto L_0x0196
            double r0 = r0 - r7
            r2 = 1
            int r7 = r10 + -1
            double r7 = (double) r7
            double r0 = r0 / r7
            double r13 = r13 + r0
        L_0x0194:
            r0 = r13
            goto L_0x01a8
        L_0x0196:
            double r51 = r0 / r7
            goto L_0x0194
        L_0x0199:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Negative textLength value"
            r0.<init>(r1)
            throw r0
        L_0x01a1:
            r55 = r0
            r71 = r2
            r53 = r11
            goto L_0x0194
        L_0x01a8:
            double r13 = (double) r3
            double r11 = r51 * r13
            android.graphics.Paint$FontMetrics r2 = r72.getFontMetrics()
            float r7 = r2.descent
            double r7 = (double) r7
            float r9 = r2.leading
            r42 = r11
            double r11 = (double) r9
            double r11 = r11 + r7
            r19 = r3
            float r3 = r2.ascent
            float r3 = -r3
            float r3 = r3 + r9
            r57 = r4
            double r3 = (double) r3
            float r2 = r2.top
            float r2 = -r2
            r59 = r13
            double r13 = (double) r2
            double r35 = r13 + r11
            java.lang.String r2 = r70.getBaselineShift()
            com.horcrux.svg.TextProperties$AlignmentBaseline r5 = r70.getAlignmentBaseline()
            if (r5 == 0) goto L_0x0224
            int[] r9 = com.horcrux.svg.TSpanView.AnonymousClass1.$SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline
            int r37 = r5.ordinal()
            r9 = r9[r37]
            switch(r9) {
                case 2: goto L_0x021d;
                case 3: goto L_0x021d;
                case 4: goto L_0x021d;
                case 5: goto L_0x0224;
                case 6: goto L_0x021d;
                case 7: goto L_0x0205;
                case 8: goto L_0x0201;
                case 9: goto L_0x01fe;
                case 10: goto L_0x01f6;
                case 11: goto L_0x01f3;
                case 12: goto L_0x01f3;
                case 13: goto L_0x01f3;
                case 14: goto L_0x01ec;
                case 15: goto L_0x01ea;
                case 16: goto L_0x01e6;
                default: goto L_0x01de;
            }
        L_0x01de:
            r11 = r72
            r3 = r17
        L_0x01e2:
            r13 = r53
            r12 = 0
            goto L_0x022b
        L_0x01e6:
            r11 = r72
            r3 = r13
            goto L_0x01e2
        L_0x01ea:
            double r11 = r35 / r46
        L_0x01ec:
            r3 = r11
            r13 = r53
            r12 = 0
            r11 = r72
            goto L_0x022b
        L_0x01f3:
            r11 = r72
            goto L_0x01e2
        L_0x01f6:
            r7 = 4605380978949069210(0x3fe999999999999a, double:0.8)
        L_0x01fb:
            double r11 = r3 * r7
            goto L_0x01ec
        L_0x01fe:
            r7 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            goto L_0x01fb
        L_0x0201:
            double r3 = r3 - r7
            double r11 = r3 / r46
            goto L_0x01ec
        L_0x0205:
            android.graphics.Rect r3 = new android.graphics.Rect
            r3.<init>()
            java.lang.String r4 = "x"
            r11 = r72
            r13 = r53
            r7 = 1
            r12 = 0
            r11.getTextBounds(r4, r12, r7, r3)
            int r3 = r3.height()
            double r3 = (double) r3
            double r3 = r3 / r46
            goto L_0x022b
        L_0x021d:
            r11 = r72
            r13 = r53
            r12 = 0
            double r3 = -r7
            goto L_0x022b
        L_0x0224:
            r11 = r72
            r13 = r53
            r12 = 0
            r3 = r17
        L_0x022b:
            if (r2 == 0) goto L_0x028a
            boolean r7 = r2.isEmpty()
            if (r7 != 0) goto L_0x028a
            int[] r7 = com.horcrux.svg.TSpanView.AnonymousClass1.$SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline
            int r5 = r5.ordinal()
            r5 = r7[r5]
            r7 = 14
            if (r5 == r7) goto L_0x028a
            r7 = 16
            if (r5 == r7) goto L_0x028a
            java.lang.String r5 = "os2"
            java.lang.String r7 = "unitsPerEm"
            java.lang.String r8 = "tables"
            int r9 = r2.hashCode()
            switch(r9) {
                case -1720785339: goto L_0x026b;
                case 114240: goto L_0x025f;
                case 109801339: goto L_0x0253;
                default: goto L_0x0250;
            }
        L_0x0250:
            r21 = -1
            goto L_0x0276
        L_0x0253:
            java.lang.String r9 = "super"
            boolean r9 = r2.equals(r9)
            if (r9 != 0) goto L_0x025c
            goto L_0x0250
        L_0x025c:
            r21 = 2
            goto L_0x0276
        L_0x025f:
            java.lang.String r9 = "sub"
            boolean r9 = r2.equals(r9)
            if (r9 != 0) goto L_0x0268
            goto L_0x0250
        L_0x0268:
            r21 = 1
            goto L_0x0276
        L_0x026b:
            java.lang.String r9 = "baseline"
            boolean r9 = r2.equals(r9)
            if (r9 != 0) goto L_0x0274
            goto L_0x0250
        L_0x0274:
            r21 = r12
        L_0x0276:
            switch(r21) {
                case 0: goto L_0x028a;
                case 1: goto L_0x02c6;
                case 2: goto L_0x028e;
                default: goto L_0x0279;
            }
        L_0x0279:
            float r5 = r6.mScale
            double r7 = (double) r5
            double r36 = r7 * r44
            double r7 = (double) r5
            r35 = r2
            r38 = r7
            r40 = r44
            double r7 = com.horcrux.svg.PropHelper.fromRelative(r35, r36, r38, r40)
            double r3 = r3 - r7
        L_0x028a:
            r53 = r13
            goto L_0x02fd
        L_0x028e:
            if (r15 == 0) goto L_0x028a
            boolean r2 = r15.hasKey(r8)
            if (r2 == 0) goto L_0x028a
            boolean r2 = r15.hasKey(r7)
            if (r2 == 0) goto L_0x028a
            int r2 = r15.getInt(r7)
            com.facebook.react.bridge.ReadableMap r7 = r15.getMap(r8)
            boolean r8 = r7.hasKey(r5)
            if (r8 == 0) goto L_0x028a
            com.facebook.react.bridge.ReadableMap r5 = r7.getMap(r5)
            java.lang.String r7 = "ySuperscriptYOffset"
            boolean r8 = r5.hasKey(r7)
            if (r8 == 0) goto L_0x028a
            double r7 = r5.getDouble(r7)
            float r5 = r6.mScale
            r53 = r13
            double r12 = (double) r5
            double r12 = r12 * r44
            double r12 = r12 * r7
            double r7 = (double) r2
            double r12 = r12 / r7
            double r3 = r3 - r12
            goto L_0x02fd
        L_0x02c6:
            r53 = r13
            if (r15 == 0) goto L_0x02fd
            boolean r2 = r15.hasKey(r8)
            if (r2 == 0) goto L_0x02fd
            boolean r2 = r15.hasKey(r7)
            if (r2 == 0) goto L_0x02fd
            int r2 = r15.getInt(r7)
            com.facebook.react.bridge.ReadableMap r7 = r15.getMap(r8)
            boolean r8 = r7.hasKey(r5)
            if (r8 == 0) goto L_0x02fd
            com.facebook.react.bridge.ReadableMap r5 = r7.getMap(r5)
            java.lang.String r7 = "ySubscriptYOffset"
            boolean r8 = r5.hasKey(r7)
            if (r8 == 0) goto L_0x02fd
            double r7 = r5.getDouble(r7)
            float r5 = r6.mScale
            double r12 = (double) r5
            double r12 = r12 * r44
            double r12 = r12 * r7
            double r7 = (double) r2
            double r12 = r12 / r7
            double r3 = r3 + r12
        L_0x02fd:
            android.graphics.Matrix r2 = new android.graphics.Matrix
            r2.<init>()
            android.graphics.Matrix r5 = new android.graphics.Matrix
            r5.<init>()
            android.graphics.Matrix r14 = new android.graphics.Matrix
            r14.<init>()
            r7 = 9
            float[] r15 = new float[r7]
            float[] r13 = new float[r7]
            r7 = r31
            r12 = 0
        L_0x0315:
            if (r12 >= r10) goto L_0x05a1
            char r9 = r20[r12]
            java.lang.String r21 = java.lang.String.valueOf(r9)
            boolean r31 = r28[r12]
            if (r31 == 0) goto L_0x032a
            java.lang.String r21 = ""
            r36 = r7
            r6 = r21
            r21 = 0
            goto L_0x0363
        L_0x032a:
            r24 = r12
            r61 = r21
            r21 = 0
        L_0x0330:
            r32 = 1
            int r6 = r24 + 1
            if (r6 >= r10) goto L_0x033e
            r32 = r48[r6]
            r35 = 0
            int r32 = (r32 > r35 ? 1 : (r32 == r35 ? 0 : -1))
            if (r32 <= 0) goto L_0x0343
        L_0x033e:
            r36 = r7
            r8 = r61
            goto L_0x0362
        L_0x0343:
            r36 = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r8 = r61
            r7.append(r8)
            char r8 = r20[r6]
            r7.append(r8)
            java.lang.String r61 = r7.toString()
            r7 = 1
            r28[r6] = r7
            r24 = r6
            r7 = r36
            r21 = 1
            goto L_0x0330
        L_0x0362:
            r6 = r8
        L_0x0363:
            float r7 = r11.measureText(r6)
            double r7 = (double) r7
            double r7 = r7 * r51
            r32 = r10
            if (r50 != 0) goto L_0x0376
            r10 = r48[r12]
            double r10 = (double) r10
            double r10 = r10 * r51
            double r10 = r10 - r7
            r36 = r10
        L_0x0376:
            r10 = 32
            if (r9 != r10) goto L_0x037c
            r10 = 1
            goto L_0x037d
        L_0x037c:
            r10 = 0
        L_0x037d:
            if (r10 == 0) goto L_0x0382
            r38 = r26
            goto L_0x0384
        L_0x0382:
            r38 = r17
        L_0x0384:
            double r38 = r38 + r0
            double r38 = r7 + r38
            if (r31 == 0) goto L_0x0391
            r44 = r0
            r0 = r17
            r11 = r30
            goto L_0x0399
        L_0x0391:
            double r40 = r36 + r38
            r44 = r0
            r11 = r30
            r0 = r40
        L_0x0399:
            double r0 = r11.nextX(r0)
            r40 = r3
            double r3 = r11.nextY()
            double r61 = r11.nextDeltaX()
            double r63 = r11.nextDeltaY()
            r65 = r3
            double r3 = r11.nextRotation()
            if (r31 != 0) goto L_0x03b5
            if (r10 == 0) goto L_0x03de
        L_0x03b5:
            r10 = r70
            r0 = r73
            r23 = r12
            r24 = r13
            r4 = r19
            r1 = r22
            r25 = r26
            r9 = r29
            r21 = r32
            r38 = r42
            r6 = r49
            r22 = r50
            r30 = r53
            r42 = r59
            r3 = 1
            r27 = 0
            r32 = 2
            r53 = r2
            r19 = r11
        L_0x03da:
            r2 = r72
            goto L_0x057b
        L_0x03de:
            double r38 = r38 * r59
            double r7 = r7 * r59
            double r0 = r0 + r61
            double r0 = r0 * r59
            double r0 = r33 + r0
            double r0 = r0 - r38
            if (r16 == 0) goto L_0x04bb
            r30 = r11
            double r10 = r0 + r7
            double r7 = r7 / r46
            r38 = r3
            double r3 = r0 + r7
            int r31 = (r3 > r57 ? 1 : (r3 == r57 ? 0 : -1))
            if (r31 <= 0) goto L_0x0420
        L_0x03fa:
            r10 = r70
            r0 = r73
            r23 = r12
            r24 = r13
            r4 = r19
            r1 = r22
            r25 = r26
            r9 = r29
            r19 = r30
            r21 = r32
            r38 = r42
            r6 = r49
            r22 = r50
            r30 = r53
            r42 = r59
            r3 = 1
            r27 = 0
            r32 = 2
            r53 = r2
            goto L_0x03da
        L_0x0420:
            int r31 = (r3 > r55 ? 1 : (r3 == r55 ? 0 : -1))
            if (r31 >= 0) goto L_0x0425
            goto L_0x03fa
        L_0x0425:
            r31 = r12
            r12 = 3
            if (r71 == 0) goto L_0x043c
            float r0 = (float) r3
            r1 = r22
            r1.getMatrix(r0, r5, r12)
            r61 = r6
            r22 = r9
            r67 = r53
            r24 = 2
            r6 = r1
            r53 = r2
            goto L_0x04a0
        L_0x043c:
            r69 = r22
            r22 = r9
            r9 = r69
            int r61 = (r0 > r17 ? 1 : (r0 == r17 ? 0 : -1))
            if (r61 >= 0) goto L_0x0452
            r61 = r6
            r6 = 0
            r9.getMatrix(r6, r2, r12)
            float r0 = (float) r0
            r2.preTranslate(r0, r6)
            r6 = 1
            goto L_0x0459
        L_0x0452:
            r61 = r6
            float r0 = (float) r0
            r6 = 1
            r9.getMatrix(r0, r2, r6)
        L_0x0459:
            float r0 = (float) r3
            r9.getMatrix(r0, r5, r6)
            int r0 = (r10 > r53 ? 1 : (r10 == r53 ? 0 : -1))
            if (r0 <= 0) goto L_0x046e
            r3 = r53
            float r0 = (float) r3
            r9.getMatrix(r0, r14, r12)
            double r10 = r10 - r3
            float r0 = (float) r10
            r1 = 0
            r14.preTranslate(r0, r1)
            goto L_0x0474
        L_0x046e:
            r3 = r53
            float r0 = (float) r10
            r9.getMatrix(r0, r14, r6)
        L_0x0474:
            r2.getValues(r15)
            r14.getValues(r13)
            r24 = 2
            r0 = r15[r24]
            double r0 = (double) r0
            r10 = 5
            r11 = r15[r10]
            double r11 = (double) r11
            r6 = r13[r24]
            r53 = r2
            r67 = r3
            double r2 = (double) r6
            r4 = r13[r10]
            r6 = r9
            double r9 = (double) r4
            double r2 = r2 - r0
            double r9 = r9 - r11
            double r0 = java.lang.Math.atan2(r9, r2)
            r2 = 4633260481411531256(0x404ca5dc1a63c1f8, double:57.29577951308232)
            double r0 = r0 * r2
            double r0 = r0 * r59
            float r0 = (float) r0
            r5.preRotate(r0)
        L_0x04a0:
            double r0 = -r7
            float r0 = (float) r0
            double r1 = r63 + r40
            float r1 = (float) r1
            r5.preTranslate(r0, r1)
            r2 = r42
            float r0 = (float) r2
            r4 = r19
            float r1 = (float) r4
            r5.preScale(r0, r1)
            r7 = r65
            float r0 = (float) r7
            r1 = 0
            r5.postTranslate(r1, r0)
        L_0x04b8:
            r0 = r38
            goto L_0x04dd
        L_0x04bb:
            r38 = r3
            r61 = r6
            r30 = r11
            r31 = r12
            r4 = r19
            r6 = r22
            r67 = r53
            r7 = r65
            r24 = 2
            r53 = r2
            r22 = r9
            r2 = r42
            float r0 = (float) r0
            double r7 = r7 + r63
            double r7 = r7 + r40
            float r1 = (float) r7
            r5.setTranslate(r0, r1)
            goto L_0x04b8
        L_0x04dd:
            float r0 = (float) r0
            r5.preRotate(r0)
            if (r21 == 0) goto L_0x0517
            android.graphics.Path r0 = new android.graphics.Path
            r0.<init>()
            int r10 = r61.length()
            r11 = 0
            r12 = 0
            r9 = 0
            r7 = r72
            r1 = r6
            r8 = r61
            r19 = r30
            r6 = r49
            r21 = r32
            r38 = r2
            r25 = r26
            r3 = 1
            r2 = r72
            r23 = r31
            r22 = r50
            r27 = 0
            r32 = r24
            r42 = r59
            r30 = r67
            r24 = r13
            r13 = r0
            r7.getTextPath(r8, r9, r10, r11, r12, r13)
            r7 = r0
            r0 = r29
            goto L_0x053d
        L_0x0517:
            r38 = r2
            r1 = r6
            r7 = r22
            r25 = r26
            r0 = r29
            r19 = r30
            r23 = r31
            r21 = r32
            r6 = r49
            r22 = r50
            r42 = r59
            r8 = r61
            r30 = r67
            r3 = 1
            r27 = 0
            r2 = r72
            r32 = r24
            r24 = r13
            android.graphics.Path r7 = r0.getOrCreateAndCache(r7, r8)
        L_0x053d:
            android.graphics.RectF r9 = new android.graphics.RectF
            r9.<init>()
            r7.computeBounds(r9, r3)
            float r9 = r9.width()
            r10 = 0
            int r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r9 != 0) goto L_0x0570
            r73.save()
            r9 = r0
            r0 = r73
            r0.concat(r5)
            r7 = r10
            r10 = r70
            java.util.ArrayList r11 = r10.emoji
            r11.add(r8)
            java.util.ArrayList r11 = r10.emojiTransforms
            android.graphics.Matrix r12 = new android.graphics.Matrix
            r12.<init>(r5)
            r11.add(r12)
            r0.drawText(r8, r7, r7, r2)
            r73.restore()
            goto L_0x057b
        L_0x0570:
            r10 = r70
            r9 = r0
            r0 = r73
            r7.transform(r5)
            r6.addPath(r7)
        L_0x057b:
            int r12 = r23 + 1
            r11 = r2
            r49 = r6
            r29 = r9
            r6 = r10
            r10 = r21
            r50 = r22
            r13 = r24
            r26 = r25
            r7 = r36
            r59 = r42
            r2 = r53
            r22 = r1
            r53 = r30
            r42 = r38
            r0 = r44
            r30 = r19
            r19 = r4
            r3 = r40
            goto L_0x0315
        L_0x05a1:
            r10 = r6
            r6 = r49
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TSpanView.getLinePath(java.lang.String, android.graphics.Paint, android.graphics.Canvas):android.graphics.Path");
    }

    /* renamed from: com.horcrux.svg.TSpanView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline;
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor;
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$TextProperties$TextLengthAdjust;

        /* JADX WARNING: Can't wrap try/catch for region: R(42:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|(2:35|36)|37|39|40|(2:41|42)|43|45|46|47|48|(3:49|50|52)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(45:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|39|40|(2:41|42)|43|45|46|47|48|49|50|52) */
        /* JADX WARNING: Can't wrap try/catch for region: R(46:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|39|40|(2:41|42)|43|45|46|47|48|49|50|52) */
        /* JADX WARNING: Can't wrap try/catch for region: R(47:0|(2:1|2)|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|39|40|41|42|43|45|46|47|48|49|50|52) */
        /* JADX WARNING: Can't wrap try/catch for region: R(48:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|39|40|41|42|43|45|46|47|48|49|50|52) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00d1 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00ec */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00f6 */
        static {
            /*
                com.horcrux.svg.TextProperties$AlignmentBaseline[] r0 = com.horcrux.svg.TextProperties$AlignmentBaseline.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline = r0
                r1 = 1
                com.horcrux.svg.TextProperties$AlignmentBaseline r2 = com.horcrux.svg.TextProperties$AlignmentBaseline.baseline     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x001d }
                com.horcrux.svg.TextProperties$AlignmentBaseline r3 = com.horcrux.svg.TextProperties$AlignmentBaseline.textBottom     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.afterEdge     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.textAfterEdge     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x003e }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.alphabetic     // Catch:{ NoSuchFieldError -> 0x003e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.ideographic     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.middle     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r5 = 7
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.central     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r5 = 8
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x006c }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.mathematical     // Catch:{ NoSuchFieldError -> 0x006c }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r5 = 9
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.hanging     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r5 = 10
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.textTop     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r5 = 11
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.beforeEdge     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r5 = 12
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x009c }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.textBeforeEdge     // Catch:{ NoSuchFieldError -> 0x009c }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r5 = 13
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.bottom     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r5 = 14
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.center     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r5 = 15
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$AlignmentBaseline     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.horcrux.svg.TextProperties$AlignmentBaseline r4 = com.horcrux.svg.TextProperties$AlignmentBaseline.top     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r5 = 16
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                com.horcrux.svg.TextProperties$TextLengthAdjust[] r3 = com.horcrux.svg.TextProperties$TextLengthAdjust.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$horcrux$svg$TextProperties$TextLengthAdjust = r3
                com.horcrux.svg.TextProperties$TextLengthAdjust r4 = com.horcrux.svg.TextProperties$TextLengthAdjust.spacing     // Catch:{ NoSuchFieldError -> 0x00d1 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d1 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x00d1 }
            L_0x00d1:
                int[] r3 = $SwitchMap$com$horcrux$svg$TextProperties$TextLengthAdjust     // Catch:{ NoSuchFieldError -> 0x00db }
                com.horcrux.svg.TextProperties$TextLengthAdjust r4 = com.horcrux.svg.TextProperties$TextLengthAdjust.spacingAndGlyphs     // Catch:{ NoSuchFieldError -> 0x00db }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00db }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x00db }
            L_0x00db:
                com.horcrux.svg.TextProperties$TextAnchor[] r3 = com.horcrux.svg.TextProperties$TextAnchor.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor = r3
                com.horcrux.svg.TextProperties$TextAnchor r4 = com.horcrux.svg.TextProperties$TextAnchor.start     // Catch:{ NoSuchFieldError -> 0x00ec }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ec }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x00ec }
            L_0x00ec:
                int[] r1 = $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor     // Catch:{ NoSuchFieldError -> 0x00f6 }
                com.horcrux.svg.TextProperties$TextAnchor r3 = com.horcrux.svg.TextProperties$TextAnchor.middle     // Catch:{ NoSuchFieldError -> 0x00f6 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x00f6 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x00f6 }
            L_0x00f6:
                int[] r0 = $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor     // Catch:{ NoSuchFieldError -> 0x0100 }
                com.horcrux.svg.TextProperties$TextAnchor r1 = com.horcrux.svg.TextProperties$TextAnchor.end     // Catch:{ NoSuchFieldError -> 0x0100 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0100 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0100 }
            L_0x0100:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TSpanView.AnonymousClass1.<clinit>():void");
        }
    }

    private double getAbsoluteStartOffset(SVGLength sVGLength, double d, double d2) {
        return PropHelper.fromRelative(sVGLength, d, 0.0d, (double) this.mScale, d2);
    }

    private double getTextAnchorOffset(TextProperties$TextAnchor textProperties$TextAnchor, double d) {
        int i = AnonymousClass1.$SwitchMap$com$horcrux$svg$TextProperties$TextAnchor[textProperties$TextAnchor.ordinal()];
        if (i == 2) {
            return (-d) / 2.0d;
        }
        if (i != 3) {
            return 0.0d;
        }
        return -d;
    }

    private void applyTextPropertiesToPaint(Paint paint, FontData fontData) {
        Typeface typeface;
        int i = 0;
        boolean z = fontData.fontWeight == TextProperties$FontWeight.Bold || fontData.absoluteFontWeight >= 550;
        boolean z2 = fontData.fontStyle == TextProperties$FontStyle.italic;
        if (z && z2) {
            i = 3;
        } else if (z) {
            i = 1;
        } else if (z2) {
            i = 2;
        }
        int i2 = fontData.absoluteFontWeight;
        String str = fontData.fontFamily;
        if (str == null || str.length() <= 0) {
            typeface = null;
        } else {
            String str2 = "fonts/" + str + ".ttf";
            Typeface.Builder builder = new Typeface.Builder(this.assets, "fonts/" + str + ".otf");
            builder.setFontVariationSettings("'wght' " + i2 + fontData.fontVariationSettings);
            builder.setWeight(i2);
            builder.setItalic(z2);
            typeface = builder.build();
            if (typeface == null) {
                Typeface.Builder builder2 = new Typeface.Builder(this.assets, str2);
                builder2.setFontVariationSettings("'wght' " + i2 + fontData.fontVariationSettings);
                builder2.setWeight(i2);
                builder2.setItalic(z2);
                typeface = builder2.build();
            }
        }
        if (typeface == null) {
            try {
                typeface = ReactFontManager.getInstance().getTypeface(str, i, this.assets);
            } catch (Exception unused) {
            }
        }
        if (Build.VERSION.SDK_INT >= 28) {
            typeface = Typeface.create(typeface, i2, z2);
        }
        paint.setLinearText(true);
        paint.setSubpixelText(true);
        paint.setTypeface(typeface);
        paint.setTextSize((float) (fontData.fontSize * ((double) this.mScale)));
        paint.setLetterSpacing(0.0f);
    }

    private void setupTextPath() {
        ViewParent parent = getParent();
        while (parent != null) {
            if (parent.getClass() == TextPathView.class) {
                this.textPath = (TextPathView) parent;
                return;
            } else if (parent instanceof TextView) {
                parent = parent.getParent();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int hitTest(float[] fArr) {
        Region region;
        if (this.mContent == null) {
            return super.hitTest(fArr);
        }
        if (this.mPath != null && this.mInvertible) {
            float[] fArr2 = new float[2];
            this.mInvMatrix.mapPoints(fArr2, fArr);
            this.mInvTransform.mapPoints(fArr2);
            int round = Math.round(fArr2[0]);
            int round2 = Math.round(fArr2[1]);
            initBounds();
            Region region2 = this.mRegion;
            if ((region2 != null && region2.contains(round, round2)) || ((region = this.mStrokeRegion) != null && region.contains(round, round2))) {
                if (getClipPath() == null || this.mClipRegion.contains(round, round2)) {
                    return getId();
                }
                return -1;
            }
        }
        return -1;
    }
}
