package com.facebook.react.views.text;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.internal.ReactTextInlineImageShadowNode;
import com.facebook.react.views.text.internal.span.CustomLetterSpacingSpan;
import com.facebook.react.views.text.internal.span.CustomLineHeightSpan;
import com.facebook.react.views.text.internal.span.CustomStyleSpan;
import com.facebook.react.views.text.internal.span.ReactAbsoluteSizeSpan;
import com.facebook.react.views.text.internal.span.ReactBackgroundColorSpan;
import com.facebook.react.views.text.internal.span.ReactClickableSpan;
import com.facebook.react.views.text.internal.span.ReactForegroundColorSpan;
import com.facebook.react.views.text.internal.span.ReactSpan;
import com.facebook.react.views.text.internal.span.ReactStrikethroughSpan;
import com.facebook.react.views.text.internal.span.ReactTagSpan;
import com.facebook.react.views.text.internal.span.ReactUnderlineSpan;
import com.facebook.react.views.text.internal.span.SetSpanOperation;
import com.facebook.react.views.text.internal.span.ShadowStyleSpan;
import com.facebook.react.views.text.internal.span.TextInlineImageSpan;
import com.facebook.react.views.text.internal.span.TextInlineViewPlaceholderSpan;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ReactBaseTextShadowNode extends LayoutShadowNode {
    public static final int DEFAULT_TEXT_SHADOW_COLOR = 1426063360;
    private static final String INLINE_VIEW_PLACEHOLDER = "0";
    public static final String PROP_SHADOW_COLOR = "textShadowColor";
    public static final String PROP_SHADOW_OFFSET = "textShadowOffset";
    public static final String PROP_SHADOW_OFFSET_HEIGHT = "height";
    public static final String PROP_SHADOW_OFFSET_WIDTH = "width";
    public static final String PROP_SHADOW_RADIUS = "textShadowRadius";
    public static final String PROP_TEXT_TRANSFORM = "textTransform";
    protected ReactAccessibilityDelegate.AccessibilityRole mAccessibilityRole;
    protected boolean mAdjustsFontSizeToFit;
    protected int mBackgroundColor;
    protected int mColor;
    protected boolean mContainsImages;
    protected String mFontFamily;
    protected String mFontFeatureSettings;
    protected int mFontStyle;
    protected int mFontWeight;
    protected int mHyphenationFrequency;
    protected boolean mIncludeFontPadding;
    protected Map<Integer, ReactShadowNode> mInlineViews;
    protected boolean mIsBackgroundColorSet;
    protected boolean mIsColorSet;
    protected boolean mIsLineThroughTextDecorationSet;
    protected boolean mIsUnderlineTextDecorationSet;
    protected int mJustificationMode;
    protected float mMinimumFontScale;
    protected int mNumberOfLines;
    protected ReactTextViewManagerCallback mReactTextViewManagerCallback;
    protected ReactAccessibilityDelegate.Role mRole;
    protected int mTextAlign;
    protected TextAttributes mTextAttributes;
    protected int mTextBreakStrategy;
    protected int mTextShadowColor;
    protected float mTextShadowOffsetDx;
    protected float mTextShadowOffsetDy;
    protected float mTextShadowRadius;

    private static void buildSpannedFromShadowNode(ReactBaseTextShadowNode reactBaseTextShadowNode, SpannableStringBuilder spannableStringBuilder, List<SetSpanOperation> list, TextAttributes textAttributes, boolean z, Map<Integer, ReactShadowNode> map, int i) {
        TextAttributes textAttributes2;
        float f;
        float f2;
        ReactBaseTextShadowNode reactBaseTextShadowNode2 = reactBaseTextShadowNode;
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        List<SetSpanOperation> list2 = list;
        TextAttributes textAttributes3 = textAttributes;
        int i2 = i;
        if (textAttributes3 != null) {
            textAttributes2 = textAttributes3.applyChild(reactBaseTextShadowNode2.mTextAttributes);
        } else {
            textAttributes2 = reactBaseTextShadowNode2.mTextAttributes;
        }
        TextAttributes textAttributes4 = textAttributes2;
        int childCount = reactBaseTextShadowNode.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            ReactShadowNodeImpl childAt = reactBaseTextShadowNode2.getChildAt(i3);
            if (childAt instanceof ReactRawTextShadowNode) {
                spannableStringBuilder2.append(TextTransform.apply(((ReactRawTextShadowNode) childAt).getText(), textAttributes4.getTextTransform()));
            } else if (childAt instanceof ReactBaseTextShadowNode) {
                buildSpannedFromShadowNode((ReactBaseTextShadowNode) childAt, spannableStringBuilder, list, textAttributes4, z, map, spannableStringBuilder.length());
            } else if (childAt instanceof ReactTextInlineImageShadowNode) {
                spannableStringBuilder2.append(INLINE_VIEW_PLACEHOLDER);
                list2.add(new SetSpanOperation(spannableStringBuilder.length() - 1, spannableStringBuilder.length(), ((ReactTextInlineImageShadowNode) childAt).buildInlineImageSpan()));
            } else if (z) {
                int reactTag = childAt.getReactTag();
                YogaValue styleWidth = childAt.getStyleWidth();
                YogaValue styleHeight = childAt.getStyleHeight();
                YogaUnit yogaUnit = styleWidth.unit;
                YogaUnit yogaUnit2 = YogaUnit.POINT;
                if (yogaUnit == yogaUnit2 && styleHeight.unit == yogaUnit2) {
                    f2 = styleWidth.value;
                    f = styleHeight.value;
                } else {
                    childAt.calculateLayout();
                    f2 = childAt.getLayoutWidth();
                    f = childAt.getLayoutHeight();
                }
                spannableStringBuilder2.append(INLINE_VIEW_PLACEHOLDER);
                list2.add(new SetSpanOperation(spannableStringBuilder.length() - 1, spannableStringBuilder.length(), new TextInlineViewPlaceholderSpan(reactTag, (int) f2, (int) f)));
                map.put(Integer.valueOf(reactTag), childAt);
                childAt.markUpdateSeen();
            } else {
                throw new IllegalViewOperationException("Unexpected view type nested under a <Text> or <TextInput> node: " + childAt.getClass());
            }
            Map<Integer, ReactShadowNode> map2 = map;
            childAt.markUpdateSeen();
        }
        int length = spannableStringBuilder.length();
        if (length >= i2) {
            if (reactBaseTextShadowNode2.mIsColorSet) {
                list2.add(new SetSpanOperation(i2, length, new ReactForegroundColorSpan(reactBaseTextShadowNode2.mColor)));
            }
            if (reactBaseTextShadowNode2.mIsBackgroundColorSet) {
                list2.add(new SetSpanOperation(i2, length, new ReactBackgroundColorSpan(reactBaseTextShadowNode2.mBackgroundColor)));
            }
            ReactAccessibilityDelegate.Role role = reactBaseTextShadowNode2.mRole;
            if (role == null ? reactBaseTextShadowNode2.mAccessibilityRole == ReactAccessibilityDelegate.AccessibilityRole.LINK : role == ReactAccessibilityDelegate.Role.LINK) {
                list2.add(new SetSpanOperation(i2, length, new ReactClickableSpan(reactBaseTextShadowNode.getReactTag())));
            }
            float effectiveLetterSpacing = textAttributes4.getEffectiveLetterSpacing();
            if (!Float.isNaN(effectiveLetterSpacing) && (textAttributes3 == null || textAttributes.getEffectiveLetterSpacing() != effectiveLetterSpacing)) {
                list2.add(new SetSpanOperation(i2, length, new CustomLetterSpacingSpan(effectiveLetterSpacing)));
            }
            int effectiveFontSize = textAttributes4.getEffectiveFontSize();
            if (textAttributes3 == null || textAttributes.getEffectiveFontSize() != effectiveFontSize) {
                list2.add(new SetSpanOperation(i2, length, new ReactAbsoluteSizeSpan(effectiveFontSize)));
            }
            if (!(reactBaseTextShadowNode2.mFontStyle == -1 && reactBaseTextShadowNode2.mFontWeight == -1 && reactBaseTextShadowNode2.mFontFamily == null)) {
                list2.add(new SetSpanOperation(i2, length, new CustomStyleSpan(reactBaseTextShadowNode2.mFontStyle, reactBaseTextShadowNode2.mFontWeight, reactBaseTextShadowNode2.mFontFeatureSettings, reactBaseTextShadowNode2.mFontFamily, reactBaseTextShadowNode.getThemedContext().getAssets())));
            }
            if (reactBaseTextShadowNode2.mIsUnderlineTextDecorationSet) {
                list2.add(new SetSpanOperation(i2, length, new ReactUnderlineSpan()));
            }
            if (reactBaseTextShadowNode2.mIsLineThroughTextDecorationSet) {
                list2.add(new SetSpanOperation(i2, length, new ReactStrikethroughSpan()));
            }
            if (!((reactBaseTextShadowNode2.mTextShadowOffsetDx == 0.0f && reactBaseTextShadowNode2.mTextShadowOffsetDy == 0.0f && reactBaseTextShadowNode2.mTextShadowRadius == 0.0f) || Color.alpha(reactBaseTextShadowNode2.mTextShadowColor) == 0)) {
                list2.add(new SetSpanOperation(i2, length, new ShadowStyleSpan(reactBaseTextShadowNode2.mTextShadowOffsetDx, reactBaseTextShadowNode2.mTextShadowOffsetDy, reactBaseTextShadowNode2.mTextShadowRadius, reactBaseTextShadowNode2.mTextShadowColor)));
            }
            float effectiveLineHeight = textAttributes4.getEffectiveLineHeight();
            if (!Float.isNaN(effectiveLineHeight) && (textAttributes3 == null || textAttributes.getEffectiveLineHeight() != effectiveLineHeight)) {
                list2.add(new SetSpanOperation(i2, length, new CustomLineHeightSpan(effectiveLineHeight)));
            }
            list2.add(new SetSpanOperation(i2, length, new ReactTagSpan(reactBaseTextShadowNode.getReactTag())));
        }
    }

    /* access modifiers changed from: protected */
    public Spannable spannedFromShadowNode(ReactBaseTextShadowNode reactBaseTextShadowNode, String str, boolean z, NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        int i;
        ReactBaseTextShadowNode reactBaseTextShadowNode2 = reactBaseTextShadowNode;
        String str2 = str;
        NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer2 = nativeViewHierarchyOptimizer;
        Assertions.assertCondition(!z || nativeViewHierarchyOptimizer2 != null, "nativeViewHierarchyOptimizer is required when inline views are supported");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = z ? new HashMap() : null;
        if (str2 != null) {
            spannableStringBuilder.append(TextTransform.apply(str2, reactBaseTextShadowNode2.mTextAttributes.getTextTransform()));
        }
        buildSpannedFromShadowNode(reactBaseTextShadowNode, spannableStringBuilder, arrayList, (TextAttributes) null, z, hashMap, 0);
        reactBaseTextShadowNode2.mContainsImages = false;
        reactBaseTextShadowNode2.mInlineViews = hashMap;
        float f = Float.NaN;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            SetSpanOperation setSpanOperation = (SetSpanOperation) arrayList.get((arrayList.size() - i2) - 1);
            ReactSpan reactSpan = setSpanOperation.what;
            boolean z2 = reactSpan instanceof TextInlineImageSpan;
            if (z2 || (reactSpan instanceof TextInlineViewPlaceholderSpan)) {
                if (z2) {
                    i = ((TextInlineImageSpan) reactSpan).getHeight();
                    reactBaseTextShadowNode2.mContainsImages = true;
                } else {
                    TextInlineViewPlaceholderSpan textInlineViewPlaceholderSpan = (TextInlineViewPlaceholderSpan) reactSpan;
                    int height = textInlineViewPlaceholderSpan.getHeight();
                    ReactShadowNode reactShadowNode = (ReactShadowNode) hashMap.get(Integer.valueOf(textInlineViewPlaceholderSpan.getReactTag()));
                    nativeViewHierarchyOptimizer2.handleForceViewToBeNonLayoutOnly(reactShadowNode);
                    reactShadowNode.setLayoutParent(reactBaseTextShadowNode);
                    i = height;
                }
                if (Float.isNaN(f) || ((float) i) > f) {
                    f = (float) i;
                }
            }
            setSpanOperation.execute(spannableStringBuilder, i2);
        }
        reactBaseTextShadowNode2.mTextAttributes.setHeightOfTallestInlineViewOrImage(f);
        ReactTextViewManagerCallback reactTextViewManagerCallback = this.mReactTextViewManagerCallback;
        if (reactTextViewManagerCallback != null) {
            reactTextViewManagerCallback.onPostProcessSpannable(spannableStringBuilder);
        }
        return spannableStringBuilder;
    }

    public ReactBaseTextShadowNode() {
        this((ReactTextViewManagerCallback) null);
    }

    public ReactBaseTextShadowNode(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        this.mIsColorSet = false;
        this.mIsBackgroundColorSet = false;
        this.mAccessibilityRole = null;
        this.mRole = null;
        this.mNumberOfLines = -1;
        this.mTextAlign = 0;
        this.mTextBreakStrategy = 1;
        this.mHyphenationFrequency = 0;
        this.mJustificationMode = 0;
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        this.mTextShadowRadius = 0.0f;
        this.mTextShadowColor = DEFAULT_TEXT_SHADOW_COLOR;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        this.mIncludeFontPadding = true;
        this.mAdjustsFontSizeToFit = false;
        this.mMinimumFontScale = 0.0f;
        this.mFontStyle = -1;
        this.mFontWeight = -1;
        this.mFontFamily = null;
        this.mFontFeatureSettings = null;
        this.mContainsImages = false;
        this.mTextAttributes = new TextAttributes();
        this.mReactTextViewManagerCallback = reactTextViewManagerCallback;
    }

    private int getTextAlign() {
        int i = this.mTextAlign;
        if (getLayoutDirection() != YogaDirection.RTL) {
            return i;
        }
        if (i == 5) {
            return 3;
        }
        if (i == 3) {
            return 5;
        }
        return i;
    }

    @ReactProp(defaultInt = -1, name = "numberOfLines")
    public void setNumberOfLines(int i) {
        if (i == 0) {
            i = -1;
        }
        this.mNumberOfLines = i;
        markUpdated();
    }

    @ReactProp(defaultFloat = Float.NaN, name = "lineHeight")
    public void setLineHeight(float f) {
        this.mTextAttributes.setLineHeight(f);
        markUpdated();
    }

    @ReactProp(defaultFloat = 0.0f, name = "letterSpacing")
    public void setLetterSpacing(float f) {
        this.mTextAttributes.setLetterSpacing(f);
        markUpdated();
    }

    @ReactProp(defaultBoolean = true, name = "allowFontScaling")
    public void setAllowFontScaling(boolean z) {
        if (z != this.mTextAttributes.getAllowFontScaling()) {
            this.mTextAttributes.setAllowFontScaling(z);
            markUpdated();
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "maxFontSizeMultiplier")
    public void setMaxFontSizeMultiplier(float f) {
        if (f != this.mTextAttributes.getMaxFontSizeMultiplier()) {
            this.mTextAttributes.setMaxFontSizeMultiplier(f);
            markUpdated();
        }
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(String str) {
        if ("justify".equals(str)) {
            this.mJustificationMode = 1;
            this.mTextAlign = 3;
        } else {
            this.mJustificationMode = 0;
            if (str == null || "auto".equals(str)) {
                this.mTextAlign = 0;
            } else if (ViewProps.LEFT.equals(str)) {
                this.mTextAlign = 3;
            } else if (ViewProps.RIGHT.equals(str)) {
                this.mTextAlign = 5;
            } else if ("center".equals(str)) {
                this.mTextAlign = 1;
            } else {
                FLog.w(ReactConstants.TAG, "Invalid textAlign: " + str);
                this.mTextAlign = 0;
            }
        }
        markUpdated();
    }

    @ReactProp(defaultFloat = Float.NaN, name = "fontSize")
    public void setFontSize(float f) {
        this.mTextAttributes.setFontSize(f);
        markUpdated();
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(Integer num) {
        boolean z = num != null;
        this.mIsColorSet = z;
        if (z) {
            this.mColor = num.intValue();
        }
        markUpdated();
    }

    @ReactProp(customType = "Color", name = "backgroundColor")
    public void setBackgroundColor(Integer num) {
        if (isVirtual()) {
            boolean z = num != null;
            this.mIsBackgroundColorSet = z;
            if (z) {
                this.mBackgroundColor = num.intValue();
            }
            markUpdated();
        }
    }

    @ReactProp(name = "accessibilityRole")
    public void setAccessibilityRole(String str) {
        if (isVirtual()) {
            this.mAccessibilityRole = ReactAccessibilityDelegate.AccessibilityRole.fromValue(str);
            markUpdated();
        }
    }

    @ReactProp(name = "role")
    public void setRole(String str) {
        if (isVirtual()) {
            this.mRole = ReactAccessibilityDelegate.Role.fromValue(str);
            markUpdated();
        }
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(String str) {
        this.mFontFamily = str;
        markUpdated();
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(String str) {
        int parseFontWeight = ReactTypefaceUtils.parseFontWeight(str);
        if (parseFontWeight != this.mFontWeight) {
            this.mFontWeight = parseFontWeight;
            markUpdated();
        }
    }

    @ReactProp(name = "fontVariant")
    public void setFontVariant(ReadableArray readableArray) {
        String parseFontVariant = ReactTypefaceUtils.parseFontVariant(readableArray);
        if (!TextUtils.equals(parseFontVariant, this.mFontFeatureSettings)) {
            this.mFontFeatureSettings = parseFontVariant;
            markUpdated();
        }
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(String str) {
        int parseFontStyle = ReactTypefaceUtils.parseFontStyle(str);
        if (parseFontStyle != this.mFontStyle) {
            this.mFontStyle = parseFontStyle;
            markUpdated();
        }
    }

    @ReactProp(defaultBoolean = true, name = "includeFontPadding")
    public void setIncludeFontPadding(boolean z) {
        this.mIncludeFontPadding = z;
    }

    @ReactProp(name = "textDecorationLine")
    public void setTextDecorationLine(String str) {
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        if (str != null) {
            for (String str2 : str.split(" ")) {
                if ("underline".equals(str2)) {
                    this.mIsUnderlineTextDecorationSet = true;
                } else if ("line-through".equals(str2)) {
                    this.mIsLineThroughTextDecorationSet = true;
                }
            }
        }
        markUpdated();
    }

    @ReactProp(name = "textBreakStrategy")
    public void setTextBreakStrategy(String str) {
        if (str == null || "highQuality".equals(str)) {
            this.mTextBreakStrategy = 1;
        } else if ("simple".equals(str)) {
            this.mTextBreakStrategy = 0;
        } else if ("balanced".equals(str)) {
            this.mTextBreakStrategy = 2;
        } else {
            FLog.w(ReactConstants.TAG, "Invalid textBreakStrategy: " + str);
            this.mTextBreakStrategy = 1;
        }
        markUpdated();
    }

    @ReactProp(name = "textShadowOffset")
    public void setTextShadowOffset(ReadableMap readableMap) {
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        if (readableMap != null) {
            if (readableMap.hasKey("width") && !readableMap.isNull("width")) {
                this.mTextShadowOffsetDx = PixelUtil.toPixelFromDIP(readableMap.getDouble("width"));
            }
            if (readableMap.hasKey("height") && !readableMap.isNull("height")) {
                this.mTextShadowOffsetDy = PixelUtil.toPixelFromDIP(readableMap.getDouble("height"));
            }
        }
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "textShadowRadius")
    public void setTextShadowRadius(float f) {
        if (f != this.mTextShadowRadius) {
            this.mTextShadowRadius = f;
            markUpdated();
        }
    }

    @ReactProp(customType = "Color", defaultInt = 1426063360, name = "textShadowColor")
    public void setTextShadowColor(int i) {
        if (i != this.mTextShadowColor) {
            this.mTextShadowColor = i;
            markUpdated();
        }
    }

    @ReactProp(name = "textTransform")
    public void setTextTransform(String str) {
        if (str == null) {
            this.mTextAttributes.setTextTransform(TextTransform.UNSET);
        } else if ("none".equals(str)) {
            this.mTextAttributes.setTextTransform(TextTransform.NONE);
        } else if ("uppercase".equals(str)) {
            this.mTextAttributes.setTextTransform(TextTransform.UPPERCASE);
        } else if ("lowercase".equals(str)) {
            this.mTextAttributes.setTextTransform(TextTransform.LOWERCASE);
        } else if ("capitalize".equals(str)) {
            this.mTextAttributes.setTextTransform(TextTransform.CAPITALIZE);
        } else {
            FLog.w(ReactConstants.TAG, "Invalid textTransform: " + str);
            this.mTextAttributes.setTextTransform(TextTransform.UNSET);
        }
        markUpdated();
    }

    @ReactProp(name = "adjustsFontSizeToFit")
    public void setAdjustFontSizeToFit(boolean z) {
        if (z != this.mAdjustsFontSizeToFit) {
            this.mAdjustsFontSizeToFit = z;
            markUpdated();
        }
    }

    @ReactProp(name = "minimumFontScale")
    public void setMinimumFontScale(float f) {
        if (f != this.mMinimumFontScale) {
            this.mMinimumFontScale = f;
            markUpdated();
        }
    }
}
