package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.MotionEvent;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintContextWrapper;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.internal.SystraceSection;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactCompoundView;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.uimanager.style.Overflow;
import com.facebook.react.views.text.internal.span.ReactTagSpan;
import com.facebook.react.views.text.internal.span.TextInlineImageSpan;
import com.facebook.yoga.YogaMeasureMode;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.messages.iam.j;

public class ReactTextView extends AppCompatTextView implements ReactCompoundView {
    private static final int DEFAULT_GRAVITY = 8388659;
    private static final ViewGroup.LayoutParams EMPTY_LAYOUT_PARAMS = new ViewGroup.LayoutParams(0, 0);
    private boolean mAdjustsFontSizeToFit;
    private boolean mContainsImages;
    private TextUtils.TruncateAt mEllipsizeLocation;
    private float mFontSize;
    private float mLetterSpacing;
    private int mLinkifyMaskType;
    private float mMinimumFontSize;
    private boolean mNotifyOnInlineViewLayout;
    private int mNumberOfLines;
    private Overflow mOverflow = Overflow.VISIBLE;
    private boolean mShouldAdjustSpannableFontSize;
    private Spannable mSpanned;
    private boolean mTextIsSelectable;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public ReactTextView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        this.mNumberOfLines = Integer.MAX_VALUE;
        this.mAdjustsFontSizeToFit = false;
        this.mLinkifyMaskType = 0;
        this.mNotifyOnInlineViewLayout = false;
        this.mTextIsSelectable = false;
        this.mShouldAdjustSpannableFontSize = false;
        this.mEllipsizeLocation = TextUtils.TruncateAt.END;
        this.mFontSize = Float.NaN;
        this.mMinimumFontSize = Float.NaN;
        this.mLetterSpacing = 0.0f;
        this.mOverflow = Overflow.VISIBLE;
        this.mSpanned = null;
    }

    /* access modifiers changed from: package-private */
    public void recycleView() {
        initView();
        BackgroundStyleApplicator.reset(this);
        setBreakStrategy(0);
        setMovementMethod(getDefaultMovementMethod());
        setJustificationMode(0);
        setLayoutParams(EMPTY_LAYOUT_PARAMS);
        super.setText((CharSequence) null);
        applyTextAttributes();
        setGravity(DEFAULT_GRAVITY);
        setNumberOfLines(this.mNumberOfLines);
        setAdjustFontSizeToFit(this.mAdjustsFontSizeToFit);
        setLinkifyMask(this.mLinkifyMaskType);
        setTextIsSelectable(this.mTextIsSelectable);
        setIncludeFontPadding(true);
        setEnabled(true);
        setLinkifyMask(0);
        setEllipsizeLocation(this.mEllipsizeLocation);
        setEnabled(true);
        setFocusable(16);
        setHyphenationFrequency(0);
        updateView();
    }

    private static WritableMap inlineViewJson(int i, int i2, int i3, int i4, int i5, int i6) {
        WritableMap createMap = Arguments.createMap();
        if (i == 8) {
            createMap.putString("visibility", "gone");
            createMap.putInt(FirebaseAnalytics.Param.INDEX, i2);
        } else if (i == 0) {
            createMap.putString("visibility", ViewProps.VISIBLE);
            createMap.putInt(FirebaseAnalytics.Param.INDEX, i2);
            createMap.putDouble(ViewProps.LEFT, (double) PixelUtil.toDIPFromPixel((float) i3));
            createMap.putDouble(ViewProps.TOP, (double) PixelUtil.toDIPFromPixel((float) i4));
            createMap.putDouble(ViewProps.RIGHT, (double) PixelUtil.toDIPFromPixel((float) i5));
            createMap.putDouble(ViewProps.BOTTOM, (double) PixelUtil.toDIPFromPixel((float) i6));
        } else {
            createMap.putString("visibility", j.h);
            createMap.putInt(FirebaseAnalytics.Param.INDEX, i2);
        }
        return createMap;
    }

    private ReactContext getReactContext() {
        Context context = getContext();
        if (context instanceof TintContextWrapper) {
            return (ReactContext) ((TintContextWrapper) context).getBaseContext();
        }
        return (ReactContext) context;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0102, code lost:
        if (r5 != false) goto L_0x0104;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x015f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r24, int r25, int r26, int r27, int r28) {
        /*
            r23 = this;
            r0 = r23
            int r1 = r23.getId()
            java.lang.CharSequence r2 = r23.getText()
            boolean r2 = r2 instanceof android.text.Spanned
            if (r2 == 0) goto L_0x01a4
            int r2 = com.facebook.react.uimanager.common.ViewUtil.getUIManagerType((int) r1)
            r3 = 2
            if (r2 != r3) goto L_0x0017
            goto L_0x01a4
        L_0x0017:
            com.facebook.react.bridge.ReactContext r2 = r23.getReactContext()
            java.lang.Class<com.facebook.react.uimanager.UIManagerModule> r3 = com.facebook.react.uimanager.UIManagerModule.class
            com.facebook.react.bridge.NativeModule r2 = r2.getNativeModule(r3)
            com.facebook.react.uimanager.UIManagerModule r2 = (com.facebook.react.uimanager.UIManagerModule) r2
            java.lang.Object r2 = com.facebook.infer.annotation.Assertions.assertNotNull(r2)
            com.facebook.react.uimanager.UIManagerModule r2 = (com.facebook.react.uimanager.UIManagerModule) r2
            java.lang.CharSequence r3 = r23.getText()
            android.text.Spanned r3 = (android.text.Spanned) r3
            android.text.Layout r4 = r23.getLayout()
            if (r4 != 0) goto L_0x0036
            return
        L_0x0036:
            int r5 = r3.length()
            java.lang.Class<com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan> r6 = com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan.class
            r7 = 0
            java.lang.Object[] r5 = r3.getSpans(r7, r5, r6)
            com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan[] r5 = (com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan[]) r5
            boolean r6 = r0.mNotifyOnInlineViewLayout
            if (r6 == 0) goto L_0x004e
            java.util.ArrayList r6 = new java.util.ArrayList
            int r8 = r5.length
            r6.<init>(r8)
            goto L_0x004f
        L_0x004e:
            r6 = 0
        L_0x004f:
            int r8 = r27 - r25
            int r9 = r28 - r26
            int r10 = r5.length
            r11 = r7
        L_0x0055:
            if (r11 >= r10) goto L_0x016c
            r12 = r5[r11]
            int r13 = r12.getReactTag()
            android.view.View r13 = r2.resolveView(r13)
            int r15 = r3.getSpanStart(r12)
            int r14 = r4.getLineForOffset(r15)
            int r16 = r4.getEllipsisCount(r14)
            if (r16 <= 0) goto L_0x0088
            int r16 = r4.getLineStart(r14)
            int r17 = r4.getEllipsisStart(r14)
            int r7 = r16 + r17
            if (r15 >= r7) goto L_0x007c
            goto L_0x0088
        L_0x007c:
            r21 = r1
            r22 = r3
            r20 = r5
            r28 = r10
            r1 = 8
            goto L_0x0147
        L_0x0088:
            int r7 = r0.mNumberOfLines
            if (r14 >= r7) goto L_0x007c
            int r7 = r4.getLineEnd(r14)
            if (r15 < r7) goto L_0x0093
            goto L_0x007c
        L_0x0093:
            int r7 = r12.getWidth()
            int r12 = r12.getHeight()
            r20 = r5
            boolean r5 = r4.isRtlCharAt(r15)
            r28 = r10
            int r10 = r4.getParagraphDirection(r14)
            r21 = r1
            r1 = -1
            r16 = 1
            if (r10 != r1) goto L_0x00b1
            r1 = r16
            goto L_0x00b2
        L_0x00b1:
            r1 = 0
        L_0x00b2:
            int r10 = r3.length()
            int r10 = r10 + -1
            if (r15 != r10) goto L_0x00e7
            int r10 = r3.length()
            if (r10 <= 0) goto L_0x00d5
            int r10 = r4.getLineEnd(r14)
            int r10 = r10 + -1
            char r10 = r3.charAt(r10)
            r22 = r3
            r3 = 10
            if (r10 != r3) goto L_0x00d7
            float r3 = r4.getLineMax(r14)
            goto L_0x00db
        L_0x00d5:
            r22 = r3
        L_0x00d7:
            float r3 = r4.getLineWidth(r14)
        L_0x00db:
            if (r1 == 0) goto L_0x00e1
            int r1 = (int) r3
            int r1 = r8 - r1
            goto L_0x0105
        L_0x00e1:
            float r1 = r4.getLineRight(r14)
            int r1 = (int) r1
            goto L_0x0104
        L_0x00e7:
            r22 = r3
            if (r1 != r5) goto L_0x00f1
            float r3 = r4.getPrimaryHorizontal(r15)
        L_0x00ef:
            int r3 = (int) r3
            goto L_0x00f6
        L_0x00f1:
            float r3 = r4.getSecondaryHorizontal(r15)
            goto L_0x00ef
        L_0x00f6:
            if (r1 == 0) goto L_0x0101
            float r1 = r4.getLineRight(r14)
            int r1 = (int) r1
            int r1 = r1 - r3
            int r1 = r8 - r1
            goto L_0x0102
        L_0x0101:
            r1 = r3
        L_0x0102:
            if (r5 == 0) goto L_0x0105
        L_0x0104:
            int r1 = r1 - r7
        L_0x0105:
            if (r5 == 0) goto L_0x010d
            int r3 = r23.getTotalPaddingRight()
        L_0x010b:
            int r1 = r1 + r3
            goto L_0x0112
        L_0x010d:
            int r3 = r23.getTotalPaddingLeft()
            goto L_0x010b
        L_0x0112:
            int r3 = r25 + r1
            int r5 = r23.getTotalPaddingTop()
            int r10 = r4.getLineBaseline(r14)
            int r5 = r5 + r10
            int r5 = r5 - r12
            int r10 = r26 + r5
            if (r8 <= r1) goto L_0x0127
            if (r9 > r5) goto L_0x0125
            goto L_0x0127
        L_0x0125:
            r14 = 0
            goto L_0x0129
        L_0x0127:
            r14 = 8
        L_0x0129:
            int r1 = r3 + r7
            int r5 = r10 + r12
            r13.setVisibility(r14)
            r13.layout(r3, r10, r1, r5)
            boolean r7 = r0.mNotifyOnInlineViewLayout
            if (r7 == 0) goto L_0x015f
            r16 = r3
            r17 = r10
            r18 = r1
            r19 = r5
            com.facebook.react.bridge.WritableMap r1 = inlineViewJson(r14, r15, r16, r17, r18, r19)
            r6.add(r1)
            goto L_0x015f
        L_0x0147:
            r13.setVisibility(r1)
            boolean r1 = r0.mNotifyOnInlineViewLayout
            if (r1 == 0) goto L_0x015f
            r18 = -1
            r19 = -1
            r14 = 8
            r16 = -1
            r17 = -1
            com.facebook.react.bridge.WritableMap r1 = inlineViewJson(r14, r15, r16, r17, r18, r19)
            r6.add(r1)
        L_0x015f:
            int r11 = r11 + 1
            r10 = r28
            r5 = r20
            r1 = r21
            r3 = r22
            r7 = 0
            goto L_0x0055
        L_0x016c:
            r21 = r1
            boolean r1 = r0.mNotifyOnInlineViewLayout
            if (r1 == 0) goto L_0x01a4
            com.facebook.react.views.text.ReactTextView$1 r1 = new com.facebook.react.views.text.ReactTextView$1
            r1.<init>()
            java.util.Collections.sort(r6, r1)
            com.facebook.react.bridge.WritableArray r1 = com.facebook.react.bridge.Arguments.createArray()
            java.util.Iterator r3 = r6.iterator()
        L_0x0182:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0192
            java.lang.Object r4 = r3.next()
            com.facebook.react.bridge.WritableMap r4 = (com.facebook.react.bridge.WritableMap) r4
            r1.pushMap(r4)
            goto L_0x0182
        L_0x0192:
            com.facebook.react.bridge.WritableMap r3 = com.facebook.react.bridge.Arguments.createMap()
            java.lang.String r4 = "inlineViews"
            r3.putArray(r4, r1)
            if (r2 == 0) goto L_0x01a4
            java.lang.String r1 = "topInlineViewLayout"
            r4 = r21
            r2.receiveEvent(r4, r1, r3)
        L_0x01a4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTextView.onLayout(boolean, int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Throwable th;
        SystraceSection systraceSection = new SystraceSection("ReactTextView.onDraw");
        try {
            if (this.mAdjustsFontSizeToFit && getSpanned() != null && this.mShouldAdjustSpannableFontSize) {
                this.mShouldAdjustSpannableFontSize = false;
                YogaMeasureMode yogaMeasureMode = YogaMeasureMode.EXACTLY;
                TextLayoutManager.adjustSpannableFontToFit(getSpanned(), (float) getWidth(), yogaMeasureMode, (float) getHeight(), yogaMeasureMode, (double) this.mMinimumFontSize, this.mNumberOfLines, getIncludeFontPadding(), getBreakStrategy(), getHyphenationFrequency(), Layout.Alignment.ALIGN_NORMAL, getJustificationMode(), getPaint());
                setText(getSpanned());
            }
            if (this.mOverflow != Overflow.VISIBLE) {
                BackgroundStyleApplicator.clipToPaddingBox(this, canvas);
            }
            super.onDraw(canvas);
            systraceSection.close();
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        SystraceSection systraceSection = new SystraceSection("ReactTextView.onMeasure");
        try {
            super.onMeasure(i, i2);
            systraceSection.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void setText(ReactTextUpdate reactTextUpdate) {
        SystraceSection systraceSection = new SystraceSection("ReactTextView.setText(ReactTextUpdate)");
        try {
            this.mContainsImages = reactTextUpdate.containsImages();
            if (getLayoutParams() == null) {
                setLayoutParams(EMPTY_LAYOUT_PARAMS);
            }
            Spannable text = reactTextUpdate.getText();
            int i = this.mLinkifyMaskType;
            if (i > 0) {
                Linkify.addLinks(text, i);
                setMovementMethod(LinkMovementMethod.getInstance());
            }
            setText(text);
            float paddingLeft = reactTextUpdate.getPaddingLeft();
            float paddingTop = reactTextUpdate.getPaddingTop();
            float paddingRight = reactTextUpdate.getPaddingRight();
            float paddingBottom = reactTextUpdate.getPaddingBottom();
            if (!(paddingLeft == -1.0f || paddingTop == -1.0f || paddingRight == -1.0f || paddingBottom == -1.0f)) {
                setPadding((int) Math.floor((double) paddingLeft), (int) Math.floor((double) paddingTop), (int) Math.floor((double) paddingRight), (int) Math.floor((double) paddingBottom));
            }
            int textAlign = reactTextUpdate.getTextAlign();
            if (textAlign != getGravityHorizontal()) {
                setGravityHorizontal(textAlign);
            }
            if (getBreakStrategy() != reactTextUpdate.getTextBreakStrategy()) {
                setBreakStrategy(reactTextUpdate.getTextBreakStrategy());
            }
            if (getJustificationMode() != reactTextUpdate.getJustificationMode()) {
                setJustificationMode(reactTextUpdate.getJustificationMode());
            }
            requestLayout();
            systraceSection.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public int reactTagForTouch(float f, float f2) {
        int i;
        CharSequence text = getText();
        int id = getId();
        int i2 = (int) f;
        int i3 = (int) f2;
        Layout layout = getLayout();
        if (layout == null) {
            return id;
        }
        int lineForVertical = layout.getLineForVertical(i3);
        int lineLeft = (int) layout.getLineLeft(lineForVertical);
        int lineRight = (int) layout.getLineRight(lineForVertical);
        if ((text instanceof Spanned) && i2 >= lineLeft && i2 <= lineRight) {
            Spanned spanned = (Spanned) text;
            try {
                int offsetForHorizontal = layout.getOffsetForHorizontal(lineForVertical, (float) i2);
                ReactTagSpan[] reactTagSpanArr = (ReactTagSpan[]) spanned.getSpans(offsetForHorizontal, offsetForHorizontal, ReactTagSpan.class);
                if (reactTagSpanArr != null) {
                    int length = text.length();
                    for (int i4 = 0; i4 < reactTagSpanArr.length; i4++) {
                        int spanStart = spanned.getSpanStart(reactTagSpanArr[i4]);
                        int spanEnd = spanned.getSpanEnd(reactTagSpanArr[i4]);
                        if (spanEnd >= offsetForHorizontal && (i = spanEnd - spanStart) <= length) {
                            id = reactTagSpanArr[i4].getReactTag();
                            length = i;
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                FLog.e(ReactConstants.TAG, "Crash in HorizontalMeasurementProvider: " + e.getMessage());
            }
        }
        return id;
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan drawable2 : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                if (drawable2.getDrawable() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan drawable2 : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                if (drawable2.getDrawable() == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan onDetachedFromWindow : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                onDetachedFromWindow.onDetachedFromWindow();
            }
        }
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan onStartTemporaryDetach : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                onStartTemporaryDetach.onStartTemporaryDetach();
            }
        }
    }

    public void setTextIsSelectable(boolean z) {
        this.mTextIsSelectable = z;
        super.setTextIsSelectable(z);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setTextIsSelectable(this.mTextIsSelectable);
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan onAttachedToWindow : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                onAttachedToWindow.onAttachedToWindow();
            }
        }
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.mContainsImages && (getText() instanceof Spanned)) {
            Spanned spanned = (Spanned) getText();
            for (TextInlineImageSpan onFinishTemporaryDetach : (TextInlineImageSpan[]) spanned.getSpans(0, spanned.length(), TextInlineImageSpan.class)) {
                onFinishTemporaryDetach.onFinishTemporaryDetach();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getGravityHorizontal() {
        return getGravity() & 8388615;
    }

    /* access modifiers changed from: package-private */
    public void setGravityHorizontal(int i) {
        if (i == 0) {
            i = 8388611;
        }
        setGravity(i | (getGravity() & -8388616));
    }

    /* access modifiers changed from: package-private */
    public void setGravityVertical(int i) {
        if (i == 0) {
            i = 48;
        }
        setGravity(i | (getGravity() & -113));
    }

    public void setNumberOfLines(int i) {
        if (i == 0) {
            i = Integer.MAX_VALUE;
        }
        this.mNumberOfLines = i;
        setMaxLines(i);
        this.mShouldAdjustSpannableFontSize = true;
    }

    public void setAdjustFontSizeToFit(boolean z) {
        this.mAdjustsFontSizeToFit = z;
    }

    public void setFontSize(float f) {
        double ceil;
        if (this.mAdjustsFontSizeToFit) {
            ceil = Math.ceil((double) PixelUtil.toPixelFromSP(f));
        } else {
            ceil = Math.ceil((double) PixelUtil.toPixelFromDIP(f));
        }
        this.mFontSize = (float) ceil;
        applyTextAttributes();
    }

    public void setMinimumFontSize(float f) {
        this.mMinimumFontSize = f;
        this.mShouldAdjustSpannableFontSize = true;
    }

    public void setIncludeFontPadding(boolean z) {
        super.setIncludeFontPadding(z);
        this.mShouldAdjustSpannableFontSize = true;
    }

    public void setBreakStrategy(int i) {
        super.setBreakStrategy(i);
        this.mShouldAdjustSpannableFontSize = true;
    }

    public void setHyphenationFrequency(int i) {
        super.setHyphenationFrequency(i);
        this.mShouldAdjustSpannableFontSize = true;
    }

    public void setLetterSpacing(float f) {
        if (!Float.isNaN(f)) {
            this.mLetterSpacing = PixelUtil.toPixelFromDIP(f) / this.mFontSize;
            applyTextAttributes();
        }
    }

    public void setEllipsizeLocation(TextUtils.TruncateAt truncateAt) {
        this.mEllipsizeLocation = truncateAt;
    }

    public void setNotifyOnInlineViewLayout(boolean z) {
        this.mNotifyOnInlineViewLayout = z;
    }

    public void updateView() {
        setEllipsize((this.mNumberOfLines == Integer.MAX_VALUE || this.mAdjustsFontSizeToFit) ? null : this.mEllipsizeLocation);
    }

    public void setBackgroundColor(int i) {
        BackgroundStyleApplicator.setBackgroundColor(this, Integer.valueOf(i));
    }

    public void setBorderWidth(int i, float f) {
        BackgroundStyleApplicator.setBorderWidth(this, LogicalEdge.values()[i], Float.valueOf(PixelUtil.toDIPFromPixel(f)));
    }

    public void setBorderColor(int i, Integer num) {
        BackgroundStyleApplicator.setBorderColor(this, LogicalEdge.values()[i], num);
    }

    public void setBorderRadius(float f) {
        setBorderRadius(f, BorderRadiusProp.BORDER_RADIUS.ordinal());
    }

    public void setBorderRadius(float f, int i) {
        LengthPercentage lengthPercentage;
        if (Float.isNaN(f)) {
            lengthPercentage = null;
        } else {
            lengthPercentage = new LengthPercentage(PixelUtil.toDIPFromPixel(f), LengthPercentageType.POINT);
        }
        BackgroundStyleApplicator.setBorderRadius(this, BorderRadiusProp.values()[i], lengthPercentage);
    }

    public void setBorderStyle(String str) {
        BackgroundStyleApplicator.setBorderStyle(this, str == null ? null : BorderStyle.fromString(str));
    }

    public void setSpanned(Spannable spannable) {
        this.mSpanned = spannable;
        this.mShouldAdjustSpannableFontSize = true;
    }

    public Spannable getSpanned() {
        return this.mSpanned;
    }

    public void setLinkifyMask(int i) {
        this.mLinkifyMaskType = i;
    }

    /* access modifiers changed from: protected */
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (ViewCompat.hasAccessibilityDelegate(this)) {
            AccessibilityDelegateCompat accessibilityDelegate = ViewCompat.getAccessibilityDelegate(this);
            if (accessibilityDelegate instanceof ExploreByTouchHelper) {
                return ((ExploreByTouchHelper) accessibilityDelegate).dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
            }
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    private void applyTextAttributes() {
        if (!Float.isNaN(this.mFontSize)) {
            setTextSize(0, this.mFontSize);
        }
        if (!Float.isNaN(this.mLetterSpacing)) {
            super.setLetterSpacing(this.mLetterSpacing);
        }
    }

    public void setOverflow(String str) {
        if (str == null) {
            this.mOverflow = Overflow.VISIBLE;
        } else {
            Overflow fromString = Overflow.fromString(str);
            if (fromString == null) {
                fromString = Overflow.VISIBLE;
            }
            this.mOverflow = fromString;
        }
        invalidate();
    }
}
