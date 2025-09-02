package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import androidx.core.util.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.text.internal.span.CustomLetterSpacingSpan;
import com.facebook.react.views.text.internal.span.CustomLineHeightSpan;
import com.facebook.react.views.text.internal.span.CustomStyleSpan;
import com.facebook.react.views.text.internal.span.ReactAbsoluteSizeSpan;
import com.facebook.react.views.text.internal.span.ReactBackgroundColorSpan;
import com.facebook.react.views.text.internal.span.ReactClickableSpan;
import com.facebook.react.views.text.internal.span.ReactForegroundColorSpan;
import com.facebook.react.views.text.internal.span.ReactOpacitySpan;
import com.facebook.react.views.text.internal.span.ReactStrikethroughSpan;
import com.facebook.react.views.text.internal.span.ReactTagSpan;
import com.facebook.react.views.text.internal.span.ReactTextPaintHolderSpan;
import com.facebook.react.views.text.internal.span.ReactUnderlineSpan;
import com.facebook.react.views.text.internal.span.SetSpanOperation;
import com.facebook.react.views.text.internal.span.ShadowStyleSpan;
import com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaMeasureMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TextLayoutManager {
    public static final short AS_KEY_BASE_ATTRIBUTES = 4;
    public static final short AS_KEY_CACHE_ID = 3;
    public static final short AS_KEY_FRAGMENTS = 2;
    public static final short AS_KEY_HASH = 0;
    public static final short AS_KEY_STRING = 1;
    private static final boolean DEFAULT_ADJUST_FONT_SIZE_TO_FIT = false;
    private static final boolean DEFAULT_INCLUDE_FONT_PADDING = true;
    private static final boolean ENABLE_MEASURE_LOGGING = false;
    public static final short FR_KEY_HEIGHT = 4;
    public static final short FR_KEY_IS_ATTACHMENT = 2;
    public static final short FR_KEY_REACT_TAG = 1;
    public static final short FR_KEY_STRING = 0;
    public static final short FR_KEY_TEXT_ATTRIBUTES = 5;
    public static final short FR_KEY_WIDTH = 3;
    private static final String INLINE_VIEW_PLACEHOLDER = "0";
    public static final short PA_KEY_ADJUST_FONT_SIZE_TO_FIT = 3;
    public static final short PA_KEY_ELLIPSIZE_MODE = 1;
    public static final short PA_KEY_HYPHENATION_FREQUENCY = 5;
    public static final short PA_KEY_INCLUDE_FONT_PADDING = 4;
    public static final short PA_KEY_MAXIMUM_FONT_SIZE = 7;
    public static final short PA_KEY_MAX_NUMBER_OF_LINES = 0;
    public static final short PA_KEY_MINIMUM_FONT_SIZE = 6;
    public static final short PA_KEY_TEXT_BREAK_STRATEGY = 2;
    private static final String TAG = TextLayoutManager.class.getSimpleName();
    private static final ConcurrentHashMap<Integer, Spannable> sTagToSpannableCache = new ConcurrentHashMap<>();
    private static final ThreadLocal<TextPaint> sTextPaintInstance = new ThreadLocal<TextPaint>() {
        /* access modifiers changed from: protected */
        public TextPaint initialValue() {
            return new TextPaint(1);
        }
    };

    static {
        ReactBuildConfig reactBuildConfig = ReactBuildConfig.INSTANCE;
    }

    public static void setCachedSpannableForTag(int i, Spannable spannable) {
        if (ENABLE_MEASURE_LOGGING) {
            String str = TAG;
            FLog.e(str, "Set cached spannable for tag[" + i + "]: " + spannable.toString());
        }
        sTagToSpannableCache.put(Integer.valueOf(i), spannable);
    }

    public static void deleteCachedSpannableForTag(int i) {
        if (ENABLE_MEASURE_LOGGING) {
            String str = TAG;
            FLog.e(str, "Delete cached spannable for tag[" + i + "]");
        }
        sTagToSpannableCache.remove(Integer.valueOf(i));
    }

    public static boolean isRTL(MapBuffer mapBuffer) {
        if (!mapBuffer.contains(2)) {
            return false;
        }
        MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(2);
        if (mapBuffer2.getCount() == 0) {
            return false;
        }
        MapBuffer mapBuffer3 = mapBuffer2.getMapBuffer(0).getMapBuffer(5);
        if (mapBuffer3.contains(23) && TextAttributeProps.getLayoutDirection(mapBuffer3.getString(23)) == 1) {
            return true;
        }
        return false;
    }

    private static String getTextAlignmentAttr(MapBuffer mapBuffer) {
        if (!mapBuffer.contains(2)) {
            return null;
        }
        MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(2);
        if (mapBuffer2.getCount() != 0) {
            MapBuffer mapBuffer3 = mapBuffer2.getMapBuffer(0).getMapBuffer(5);
            if (mapBuffer3.contains(12)) {
                return mapBuffer3.getString(12);
            }
        }
        return null;
    }

    private static int getTextJustificationMode(String str) {
        return (str == null || !str.equals("justified")) ? 0 : 1;
    }

    private static Layout.Alignment getTextAlignment(MapBuffer mapBuffer, Spannable spannable, String str) {
        boolean z = false;
        if (isRTL(mapBuffer) != TextDirectionHeuristics.FIRSTSTRONG_LTR.isRtl(spannable, 0, spannable.length())) {
            z = true;
        }
        Layout.Alignment alignment = z ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL;
        if (str == null) {
            return alignment;
        }
        if (str.equals("center")) {
            return Layout.Alignment.ALIGN_CENTER;
        }
        if (str.equals(ViewProps.RIGHT)) {
            return z ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_OPPOSITE;
        }
        return alignment;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        return 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0019, code lost:
        if (r4 != false) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0023, code lost:
        if (r4 != false) goto L_0x001d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getTextGravity(com.facebook.react.common.mapbuffer.MapBuffer r3, android.text.Spannable r4, int r5) {
        /*
            java.lang.String r0 = getTextAlignmentAttr(r3)
            android.text.Layout$Alignment r3 = getTextAlignment(r3, r4, r0)
            android.text.TextDirectionHeuristic r0 = android.text.TextDirectionHeuristics.FIRSTSTRONG_LTR
            int r1 = r4.length()
            r2 = 0
            boolean r4 = r0.isRtl(r4, r2, r1)
            android.text.Layout$Alignment r0 = android.text.Layout.Alignment.ALIGN_NORMAL
            r1 = 3
            r2 = 5
            if (r3 != r0) goto L_0x001f
            if (r4 == 0) goto L_0x001d
        L_0x001b:
            r5 = r2
            goto L_0x002b
        L_0x001d:
            r5 = r1
            goto L_0x002b
        L_0x001f:
            android.text.Layout$Alignment r0 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            if (r3 != r0) goto L_0x0026
            if (r4 == 0) goto L_0x001b
            goto L_0x001d
        L_0x0026:
            android.text.Layout$Alignment r4 = android.text.Layout.Alignment.ALIGN_CENTER
            if (r3 != r4) goto L_0x002b
            r5 = 1
        L_0x002b:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextLayoutManager.getTextGravity(com.facebook.react.common.mapbuffer.MapBuffer, android.text.Spannable, int):int");
    }

    private static void buildSpannableFromFragments(Context context, MapBuffer mapBuffer, SpannableStringBuilder spannableStringBuilder, List<SetSpanOperation> list) {
        ReactAccessibilityDelegate.Role role;
        List<SetSpanOperation> list2 = list;
        int count = mapBuffer.getCount();
        for (int i = 0; i < count; i++) {
            MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(i);
            int length = spannableStringBuilder.length();
            TextAttributeProps fromMapBuffer = TextAttributeProps.fromMapBuffer(mapBuffer2.getMapBuffer(5));
            spannableStringBuilder.append(TextTransform.apply(mapBuffer2.getString(0), fromMapBuffer.mTextTransform));
            int length2 = spannableStringBuilder.length();
            int i2 = mapBuffer2.contains(1) ? mapBuffer2.getInt(1) : -1;
            if (mapBuffer2.contains(2) && mapBuffer2.getBoolean(2)) {
                list2.add(new SetSpanOperation(spannableStringBuilder.length() - 1, spannableStringBuilder.length(), new TextInlineViewPlaceholderSpan(i2, (int) PixelUtil.toPixelFromSP(mapBuffer2.getDouble(3)), (int) PixelUtil.toPixelFromSP(mapBuffer2.getDouble(4)))));
            } else if (length2 >= length) {
                if ((role = fromMapBuffer.mRole) == null ? fromMapBuffer.mAccessibilityRole == ReactAccessibilityDelegate.AccessibilityRole.LINK : role == ReactAccessibilityDelegate.Role.LINK) {
                    list2.add(new SetSpanOperation(length, length2, new ReactClickableSpan(i2)));
                }
                if (fromMapBuffer.mIsColorSet) {
                    list2.add(new SetSpanOperation(length, length2, new ReactForegroundColorSpan(fromMapBuffer.mColor)));
                }
                if (fromMapBuffer.mIsBackgroundColorSet) {
                    list2.add(new SetSpanOperation(length, length2, new ReactBackgroundColorSpan(fromMapBuffer.mBackgroundColor)));
                }
                if (!Float.isNaN(fromMapBuffer.getOpacity())) {
                    list2.add(new SetSpanOperation(length, length2, new ReactOpacitySpan(fromMapBuffer.getOpacity())));
                }
                if (!Float.isNaN(fromMapBuffer.getLetterSpacing())) {
                    list2.add(new SetSpanOperation(length, length2, new CustomLetterSpacingSpan(fromMapBuffer.getLetterSpacing())));
                }
                list2.add(new SetSpanOperation(length, length2, new ReactAbsoluteSizeSpan(fromMapBuffer.mFontSize)));
                if (!(fromMapBuffer.mFontStyle == -1 && fromMapBuffer.mFontWeight == -1 && fromMapBuffer.mFontFamily == null)) {
                    list2.add(new SetSpanOperation(length, length2, new CustomStyleSpan(fromMapBuffer.mFontStyle, fromMapBuffer.mFontWeight, fromMapBuffer.mFontFeatureSettings, fromMapBuffer.mFontFamily, context.getAssets())));
                }
                if (fromMapBuffer.mIsUnderlineTextDecorationSet) {
                    list2.add(new SetSpanOperation(length, length2, new ReactUnderlineSpan()));
                }
                if (fromMapBuffer.mIsLineThroughTextDecorationSet) {
                    list2.add(new SetSpanOperation(length, length2, new ReactStrikethroughSpan()));
                }
                if (!((fromMapBuffer.mTextShadowOffsetDx == 0.0f && fromMapBuffer.mTextShadowOffsetDy == 0.0f && fromMapBuffer.mTextShadowRadius == 0.0f) || Color.alpha(fromMapBuffer.mTextShadowColor) == 0)) {
                    list2.add(new SetSpanOperation(length, length2, new ShadowStyleSpan(fromMapBuffer.mTextShadowOffsetDx, fromMapBuffer.mTextShadowOffsetDy, fromMapBuffer.mTextShadowRadius, fromMapBuffer.mTextShadowColor)));
                }
                if (!Float.isNaN(fromMapBuffer.getEffectiveLineHeight())) {
                    list2.add(new SetSpanOperation(length, length2, new CustomLineHeightSpan(fromMapBuffer.getEffectiveLineHeight())));
                }
                list2.add(new SetSpanOperation(length, length2, new ReactTagSpan(i2)));
            }
        }
    }

    public static Spannable getOrCreateSpannableForText(Context context, MapBuffer mapBuffer, ReactTextViewManagerCallback reactTextViewManagerCallback) {
        if (!mapBuffer.contains(3)) {
            return createSpannableFromAttributedString(context, mapBuffer, reactTextViewManagerCallback);
        }
        return sTagToSpannableCache.get(Integer.valueOf(mapBuffer.getInt(3)));
    }

    private static Spannable createSpannableFromAttributedString(Context context, MapBuffer mapBuffer, ReactTextViewManagerCallback reactTextViewManagerCallback) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList arrayList = new ArrayList();
        buildSpannableFromFragments(context, mapBuffer.getMapBuffer(2), spannableStringBuilder, arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            ((SetSpanOperation) arrayList.get((arrayList.size() - i) - 1)).execute(spannableStringBuilder, i);
        }
        if (reactTextViewManagerCallback != null) {
            reactTextViewManagerCallback.onPostProcessSpannable(spannableStringBuilder);
        }
        return spannableStringBuilder;
    }

    private static Layout createLayout(Spannable spannable, BoringLayout.Metrics metrics, float f, YogaMeasureMode yogaMeasureMode, boolean z, int i, int i2, Layout.Alignment alignment, int i3, TextPaint textPaint) {
        int i4;
        Spannable spannable2 = spannable;
        BoringLayout.Metrics metrics2 = metrics;
        float f2 = f;
        YogaMeasureMode yogaMeasureMode2 = yogaMeasureMode;
        boolean z2 = z;
        int i5 = i;
        int i6 = i2;
        Layout.Alignment alignment2 = alignment;
        TextPaint textPaint2 = textPaint;
        int length = spannable.length();
        boolean z3 = yogaMeasureMode2 == YogaMeasureMode.UNDEFINED || f2 < 0.0f;
        float desiredWidth = metrics2 == null ? Layout.getDesiredWidth(spannable2, textPaint2) : Float.NaN;
        boolean isRtl = TextDirectionHeuristics.FIRSTSTRONG_LTR.isRtl(spannable2, 0, length);
        if (metrics2 == null && (z3 || (!YogaConstants.isUndefined(desiredWidth) && desiredWidth <= f2))) {
            if (yogaMeasureMode2 == YogaMeasureMode.EXACTLY) {
                desiredWidth = f2;
            }
            StaticLayout.Builder textDirection = StaticLayout.Builder.obtain(spannable2, 0, length, textPaint2, (int) Math.ceil((double) desiredWidth)).setAlignment(alignment2).setLineSpacing(0.0f, 1.0f).setIncludePad(z2).setBreakStrategy(i5).setHyphenationFrequency(i6).setTextDirection(isRtl ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
            if (Build.VERSION.SDK_INT >= 28) {
                StaticLayout.Builder unused = textDirection.setUseLineSpacingFromFallbacks(true);
            }
            return textDirection.build();
        } else if (metrics2 == null || (!z3 && ((float) metrics2.width) > f2)) {
            StaticLayout.Builder textDirection2 = StaticLayout.Builder.obtain(spannable2, 0, length, textPaint2, (int) Math.ceil((double) f2)).setAlignment(alignment2).setLineSpacing(0.0f, 1.0f).setIncludePad(z2).setBreakStrategy(i5).setHyphenationFrequency(i6).setTextDirection(isRtl ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
            int i7 = Build.VERSION.SDK_INT;
            textDirection2.setJustificationMode(i3);
            if (i7 >= 28) {
                StaticLayout.Builder unused2 = textDirection2.setUseLineSpacingFromFallbacks(true);
            }
            return textDirection2.build();
        } else {
            int i8 = metrics2.width;
            if (yogaMeasureMode2 == YogaMeasureMode.EXACTLY) {
                i8 = (int) Math.ceil((double) f2);
            }
            if (metrics2.width < 0) {
                String str = TAG;
                ReactSoftExceptionLogger.logSoftException(str, new ReactNoCrashSoftException("Text width is invalid: " + metrics2.width));
                i4 = 0;
            } else {
                i4 = i8;
            }
            return BoringLayout.make(spannable, textPaint, i4, alignment, 1.0f, 0.0f, metrics, z);
        }
    }

    private static void updateTextPaint(TextPaint textPaint, TextAttributeProps textAttributeProps, Context context) {
        textPaint.reset();
        boolean z = true;
        textPaint.setAntiAlias(true);
        if (textAttributeProps.getEffectiveFontSize() != -1) {
            textPaint.setTextSize((float) textAttributeProps.getEffectiveFontSize());
        }
        if (textAttributeProps.getFontStyle() == -1 && textAttributeProps.getFontWeight() == -1 && textAttributeProps.getFontFamily() == null) {
            textPaint.setTypeface((Typeface) null);
            return;
        }
        Typeface applyStyles = ReactTypefaceUtils.applyStyles((Typeface) null, textAttributeProps.getFontStyle(), textAttributeProps.getFontWeight(), textAttributeProps.getFontFamily(), context.getAssets());
        textPaint.setTypeface(applyStyles);
        if (textAttributeProps.getFontStyle() != -1 && textAttributeProps.getFontStyle() != applyStyles.getStyle()) {
            int fontStyle = textAttributeProps.getFontStyle() & (~applyStyles.getStyle());
            if ((fontStyle & 1) == 0) {
                z = false;
            }
            textPaint.setFakeBoldText(z);
            textPaint.setTextSkewX((fontStyle & 2) != 0 ? -0.25f : 0.0f);
        }
    }

    private static Layout createLayout(Context context, MapBuffer mapBuffer, MapBuffer mapBuffer2, float f, float f2, ReactTextViewManagerCallback reactTextViewManagerCallback) {
        TextPaint textPaint;
        Context context2 = context;
        MapBuffer mapBuffer3 = mapBuffer;
        MapBuffer mapBuffer4 = mapBuffer2;
        Spannable orCreateSpannableForText = getOrCreateSpannableForText(context2, mapBuffer3, reactTextViewManagerCallback);
        if (mapBuffer3.contains(3)) {
            textPaint = ((ReactTextPaintHolderSpan[]) orCreateSpannableForText.getSpans(0, 0, ReactTextPaintHolderSpan.class))[0].getTextPaint();
        } else {
            TextAttributeProps fromMapBuffer = TextAttributeProps.fromMapBuffer(mapBuffer3.getMapBuffer(4));
            TextPaint textPaint2 = (TextPaint) Preconditions.checkNotNull(sTextPaintInstance.get());
            updateTextPaint(textPaint2, fromMapBuffer, context2);
            textPaint = textPaint2;
        }
        BoringLayout.Metrics isBoring = BoringLayout.isBoring(orCreateSpannableForText, textPaint);
        int textBreakStrategy = TextAttributeProps.getTextBreakStrategy(mapBuffer4.getString(2));
        boolean z = mapBuffer4.contains(4) ? mapBuffer4.getBoolean(4) : true;
        int hyphenationFrequency = TextAttributeProps.getHyphenationFrequency(mapBuffer4.getString(5));
        boolean z2 = mapBuffer4.contains(3) ? mapBuffer4.getBoolean(3) : false;
        int i = mapBuffer4.contains(0) ? mapBuffer4.getInt(0) : -1;
        String textAlignmentAttr = getTextAlignmentAttr(mapBuffer);
        Layout.Alignment textAlignment = getTextAlignment(mapBuffer3, orCreateSpannableForText, textAlignmentAttr);
        int textJustificationMode = getTextJustificationMode(textAlignmentAttr);
        if (z2) {
            adjustSpannableFontToFit(orCreateSpannableForText, f, YogaMeasureMode.EXACTLY, f2, YogaMeasureMode.UNDEFINED, mapBuffer4.contains(6) ? mapBuffer4.getDouble(6) : Double.NaN, i, z, textBreakStrategy, hyphenationFrequency, textAlignment, textJustificationMode, textPaint);
        }
        return createLayout(orCreateSpannableForText, isBoring, f, YogaMeasureMode.EXACTLY, z, textBreakStrategy, hyphenationFrequency, textAlignment, textJustificationMode, textPaint);
    }

    static void adjustSpannableFontToFit(Spannable spannable, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, double d, int i, boolean z, int i2, int i3, Layout.Alignment alignment, int i4, TextPaint textPaint) {
        Spannable spannable2 = spannable;
        int i5 = i;
        TextPaint textPaint2 = textPaint;
        BoringLayout.Metrics isBoring = BoringLayout.isBoring(spannable2, textPaint2);
        Layout createLayout = createLayout(spannable, isBoring, f, yogaMeasureMode, z, i2, i3, alignment, i4, textPaint);
        int pixelFromDIP = (int) (Double.isNaN(d) ? (double) PixelUtil.toPixelFromDIP(4.0f) : d);
        int i6 = 0;
        Class<ReactAbsoluteSizeSpan> cls = ReactAbsoluteSizeSpan.class;
        int i7 = pixelFromDIP;
        for (ReactAbsoluteSizeSpan size : (ReactAbsoluteSizeSpan[]) spannable2.getSpans(0, spannable.length(), cls)) {
            i7 = Math.max(i7, size.getSize());
        }
        int i8 = i7;
        while (i8 > pixelFromDIP) {
            if (i5 != -1 && i5 != 0 && createLayout.getLineCount() > i5) {
                YogaMeasureMode yogaMeasureMode3 = yogaMeasureMode2;
            } else if ((yogaMeasureMode2 == YogaMeasureMode.UNDEFINED || ((float) createLayout.getHeight()) <= f2) && (spannable.length() != 1 || createLayout.getLineWidth(i6) <= f)) {
                return;
            }
            int max = i8 - Math.max(1, (int) PixelUtil.toPixelFromDIP(1.0f));
            float f3 = ((float) max) / ((float) i7);
            float f4 = (float) pixelFromDIP;
            textPaint2.setTextSize(Math.max(textPaint.getTextSize() * f3, f4));
            ReactAbsoluteSizeSpan[] reactAbsoluteSizeSpanArr = (ReactAbsoluteSizeSpan[]) spannable2.getSpans(i6, spannable.length(), cls);
            int length = reactAbsoluteSizeSpanArr.length;
            int i9 = i6;
            while (i9 < length) {
                ReactAbsoluteSizeSpan reactAbsoluteSizeSpan = reactAbsoluteSizeSpanArr[i9];
                spannable2.setSpan(new ReactAbsoluteSizeSpan((int) Math.max(((float) reactAbsoluteSizeSpan.getSize()) * f3, f4)), spannable2.getSpanStart(reactAbsoluteSizeSpan), spannable2.getSpanEnd(reactAbsoluteSizeSpan), spannable2.getSpanFlags(reactAbsoluteSizeSpan));
                spannable2.removeSpan(reactAbsoluteSizeSpan);
                i9++;
                reactAbsoluteSizeSpanArr = reactAbsoluteSizeSpanArr;
                f3 = f3;
                f4 = f4;
            }
            if (isBoring != null) {
                isBoring = BoringLayout.isBoring(spannable2, textPaint2);
            }
            createLayout = createLayout(spannable, isBoring, f, yogaMeasureMode, z, i2, i3, alignment, i4, textPaint);
            i8 = max;
            i7 = i7;
            cls = cls;
            i6 = 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0087, code lost:
        if (r5 > r21) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a7, code lost:
        if (r3 > r23) goto L_0x00a9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x018a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long measureText(android.content.Context r18, com.facebook.react.common.mapbuffer.MapBuffer r19, com.facebook.react.common.mapbuffer.MapBuffer r20, float r21, com.facebook.yoga.YogaMeasureMode r22, float r23, com.facebook.yoga.YogaMeasureMode r24, com.facebook.react.views.text.ReactTextViewManagerCallback r25, float[] r26) {
        /*
            r6 = r20
            r7 = r22
            r8 = r24
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r23
            r5 = r25
            android.text.Layout r0 = createLayout(r0, r1, r2, r3, r4, r5)
            java.lang.CharSequence r1 = r0.getText()
            android.text.Spannable r1 = (android.text.Spannable) r1
            if (r1 != 0) goto L_0x0021
            r0 = 0
            return r0
        L_0x0021:
            r2 = 0
            boolean r3 = r6.contains(r2)
            r4 = -1
            if (r3 == 0) goto L_0x002e
            int r3 = r6.getInt(r2)
            goto L_0x002f
        L_0x002e:
            r3 = r4
        L_0x002f:
            if (r3 == r4) goto L_0x003d
            if (r3 != 0) goto L_0x0034
            goto L_0x003d
        L_0x0034:
            int r5 = r0.getLineCount()
            int r3 = java.lang.Math.min(r3, r5)
            goto L_0x0041
        L_0x003d:
            int r3 = r0.getLineCount()
        L_0x0041:
            com.facebook.yoga.YogaMeasureMode r5 = com.facebook.yoga.YogaMeasureMode.EXACTLY
            r6 = 10
            r9 = 1
            if (r7 != r5) goto L_0x0049
            goto L_0x0089
        L_0x0049:
            r5 = 0
            r10 = r2
        L_0x004b:
            if (r10 >= r3) goto L_0x0081
            int r11 = r1.length()
            if (r11 <= 0) goto L_0x0060
            int r11 = r0.getLineEnd(r10)
            int r11 = r11 - r9
            char r11 = r1.charAt(r11)
            if (r11 != r6) goto L_0x0060
            r11 = r9
            goto L_0x0061
        L_0x0060:
            r11 = r2
        L_0x0061:
            if (r11 != 0) goto L_0x006e
            int r12 = r10 + 1
            int r13 = r0.getLineCount()
            if (r12 >= r13) goto L_0x006e
            r5 = r21
            goto L_0x0081
        L_0x006e:
            if (r11 == 0) goto L_0x0075
            float r11 = r0.getLineMax(r10)
            goto L_0x0079
        L_0x0075:
            float r11 = r0.getLineWidth(r10)
        L_0x0079:
            int r12 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r12 <= 0) goto L_0x007e
            r5 = r11
        L_0x007e:
            int r10 = r10 + 1
            goto L_0x004b
        L_0x0081:
            com.facebook.yoga.YogaMeasureMode r10 = com.facebook.yoga.YogaMeasureMode.AT_MOST
            if (r7 != r10) goto L_0x008b
            int r7 = (r5 > r21 ? 1 : (r5 == r21 ? 0 : -1))
            if (r7 <= 0) goto L_0x008b
        L_0x0089:
            r5 = r21
        L_0x008b:
            int r7 = android.os.Build.VERSION.SDK_INT
            r10 = 29
            if (r7 <= r10) goto L_0x0097
            double r10 = (double) r5
            double r10 = java.lang.Math.ceil(r10)
            float r5 = (float) r10
        L_0x0097:
            com.facebook.yoga.YogaMeasureMode r7 = com.facebook.yoga.YogaMeasureMode.EXACTLY
            if (r8 == r7) goto L_0x00a9
            int r3 = r3 - r9
            int r3 = r0.getLineBottom(r3)
            float r3 = (float) r3
            com.facebook.yoga.YogaMeasureMode r7 = com.facebook.yoga.YogaMeasureMode.AT_MOST
            if (r8 != r7) goto L_0x00ab
            int r7 = (r3 > r23 ? 1 : (r3 == r23 ? 0 : -1))
            if (r7 <= 0) goto L_0x00ab
        L_0x00a9:
            r3 = r23
        L_0x00ab:
            r7 = r2
            r8 = r7
        L_0x00ad:
            int r10 = r1.length()
            if (r7 >= r10) goto L_0x017e
            int r10 = r1.length()
            java.lang.Class<com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan> r11 = com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan.class
            int r10 = r1.nextSpanTransition(r7, r10, r11)
            java.lang.Object[] r7 = r1.getSpans(r7, r10, r11)
            com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan[] r7 = (com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan[]) r7
            int r11 = r7.length
            r12 = r2
        L_0x00c5:
            if (r12 >= r11) goto L_0x017b
            r13 = r7[r12]
            int r14 = r1.getSpanStart(r13)
            int r15 = r0.getLineForOffset(r14)
            int r16 = r0.getEllipsisCount(r15)
            if (r16 <= 0) goto L_0x00ee
            int r16 = r0.getLineStart(r15)
            int r17 = r0.getEllipsisStart(r15)
            int r2 = r16 + r17
            if (r14 < r2) goto L_0x00ee
            int r2 = r0.getLineEnd(r15)
            if (r14 < r2) goto L_0x00ea
            goto L_0x00ee
        L_0x00ea:
            r4 = r6
            r6 = r9
            goto L_0x0173
        L_0x00ee:
            int r2 = r13.getWidth()
            float r2 = (float) r2
            int r13 = r13.getHeight()
            float r13 = (float) r13
            boolean r6 = r0.isRtlCharAt(r14)
            int r9 = r0.getParagraphDirection(r15)
            if (r9 != r4) goto L_0x0104
            r9 = 1
            goto L_0x0105
        L_0x0104:
            r9 = 0
        L_0x0105:
            int r16 = r1.length()
            r17 = 1
            int r4 = r16 + -1
            if (r14 != r4) goto L_0x013b
            int r4 = r1.length()
            if (r4 <= 0) goto L_0x0128
            int r4 = r0.getLineEnd(r15)
            int r4 = r4 + -1
            char r4 = r1.charAt(r4)
            r6 = 10
            if (r4 != r6) goto L_0x012a
            float r4 = r0.getLineMax(r15)
            goto L_0x012e
        L_0x0128:
            r6 = 10
        L_0x012a:
            float r4 = r0.getLineWidth(r15)
        L_0x012e:
            if (r9 == 0) goto L_0x0133
            float r2 = r5 - r4
            goto L_0x0139
        L_0x0133:
            float r4 = r0.getLineRight(r15)
            float r2 = r4 - r2
        L_0x0139:
            r4 = r6
            goto L_0x015b
        L_0x013b:
            r4 = 10
            if (r9 != r6) goto L_0x0144
            float r14 = r0.getPrimaryHorizontal(r14)
            goto L_0x0148
        L_0x0144:
            float r14 = r0.getSecondaryHorizontal(r14)
        L_0x0148:
            if (r9 == 0) goto L_0x0154
            if (r6 != 0) goto L_0x0154
            float r9 = r0.getLineRight(r15)
            float r9 = r9 - r14
            float r9 = r5 - r9
            goto L_0x0155
        L_0x0154:
            r9 = r14
        L_0x0155:
            if (r6 == 0) goto L_0x015a
            float r2 = r9 - r2
            goto L_0x015b
        L_0x015a:
            r2 = r9
        L_0x015b:
            int r6 = r0.getLineBaseline(r15)
            float r6 = (float) r6
            float r6 = r6 - r13
            int r9 = r8 * 2
            float r6 = com.facebook.react.uimanager.PixelUtil.toDIPFromPixel(r6)
            r26[r9] = r6
            r6 = 1
            int r9 = r9 + r6
            float r2 = com.facebook.react.uimanager.PixelUtil.toDIPFromPixel(r2)
            r26[r9] = r2
            int r8 = r8 + 1
        L_0x0173:
            int r12 = r12 + 1
            r9 = r6
            r2 = 0
            r6 = r4
            r4 = -1
            goto L_0x00c5
        L_0x017b:
            r7 = r10
            goto L_0x00ad
        L_0x017e:
            float r0 = com.facebook.react.uimanager.PixelUtil.toDIPFromPixel(r5)
            float r2 = com.facebook.react.uimanager.PixelUtil.toDIPFromPixel(r3)
            boolean r4 = ENABLE_MEASURE_LOGGING
            if (r4 == 0) goto L_0x01c5
            java.lang.String r4 = TAG
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "TextMeasure call ('"
            r6.append(r7)
            r6.append(r1)
            java.lang.String r1 = "'): w: "
            r6.append(r1)
            r6.append(r5)
            java.lang.String r1 = " px - h: "
            r6.append(r1)
            r6.append(r3)
            java.lang.String r1 = " px - w : "
            r6.append(r1)
            r6.append(r0)
            java.lang.String r1 = " sp - h: "
            r6.append(r1)
            r6.append(r2)
            java.lang.String r1 = " sp"
            r6.append(r1)
            java.lang.String r1 = r6.toString()
            com.facebook.common.logging.FLog.e((java.lang.String) r4, (java.lang.String) r1)
        L_0x01c5:
            long r0 = com.facebook.yoga.YogaMeasureOutput.make((float) r0, (float) r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextLayoutManager.measureText(android.content.Context, com.facebook.react.common.mapbuffer.MapBuffer, com.facebook.react.common.mapbuffer.MapBuffer, float, com.facebook.yoga.YogaMeasureMode, float, com.facebook.yoga.YogaMeasureMode, com.facebook.react.views.text.ReactTextViewManagerCallback, float[]):long");
    }

    public static WritableArray measureLines(Context context, MapBuffer mapBuffer, MapBuffer mapBuffer2, float f, float f2) {
        Layout createLayout = createLayout(context, mapBuffer, mapBuffer2, f, f2, (ReactTextViewManagerCallback) null);
        return FontMetricsUtil.getFontMetrics(createLayout.getText(), createLayout, (TextPaint) Preconditions.checkNotNull(sTextPaintInstance.get()), context);
    }
}
