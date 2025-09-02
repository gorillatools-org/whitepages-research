package com.facebook.react.views.textinput;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.ReactTextViewManagerCallback;
import com.facebook.react.views.view.MeasureUtil;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNode;

@VisibleForTesting
public class ReactTextInputShadowNode extends ReactBaseTextShadowNode implements YogaMeasureFunction {
    @VisibleForTesting
    public static final String PROP_PLACEHOLDER = "placeholder";
    @VisibleForTesting
    public static final String PROP_TEXT = "text";
    private EditText mInternalEditText;
    private ReactTextInputLocalData mLocalData;
    private int mMostRecentEventCount;
    private String mPlaceholder;
    private String mText;

    public boolean isVirtualAnchor() {
        return true;
    }

    public boolean isYogaLeafNode() {
        return true;
    }

    public ReactTextInputShadowNode(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        super(reactTextViewManagerCallback);
        this.mMostRecentEventCount = -1;
        this.mText = null;
        this.mPlaceholder = null;
        this.mTextBreakStrategy = 1;
        initMeasureFunction();
    }

    public ReactTextInputShadowNode() {
        this((ReactTextViewManagerCallback) null);
    }

    private void initMeasureFunction() {
        setMeasureFunction(this);
    }

    public void setThemedContext(ThemedReactContext themedReactContext) {
        super.setThemedContext(themedReactContext);
        EditText createInternalEditText = createInternalEditText();
        setDefaultPadding(4, (float) ViewCompat.getPaddingStart(createInternalEditText));
        setDefaultPadding(1, (float) createInternalEditText.getPaddingTop());
        setDefaultPadding(5, (float) ViewCompat.getPaddingEnd(createInternalEditText));
        setDefaultPadding(3, (float) createInternalEditText.getPaddingBottom());
        this.mInternalEditText = createInternalEditText;
        createInternalEditText.setPadding(0, 0, 0, 0);
        this.mInternalEditText.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    }

    public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        EditText editText = (EditText) Assertions.assertNotNull(this.mInternalEditText);
        ReactTextInputLocalData reactTextInputLocalData = this.mLocalData;
        if (reactTextInputLocalData != null) {
            reactTextInputLocalData.apply(editText);
        } else {
            editText.setTextSize(0, (float) this.mTextAttributes.getEffectiveFontSize());
            int i = this.mNumberOfLines;
            if (i != -1) {
                editText.setLines(i);
            }
            int breakStrategy = editText.getBreakStrategy();
            int i2 = this.mTextBreakStrategy;
            if (breakStrategy != i2) {
                editText.setBreakStrategy(i2);
            }
        }
        editText.setHint(getPlaceholder());
        editText.measure(MeasureUtil.getMeasureSpec(f, yogaMeasureMode), MeasureUtil.getMeasureSpec(f2, yogaMeasureMode2));
        return YogaMeasureOutput.make(editText.getMeasuredWidth(), editText.getMeasuredHeight());
    }

    public void setLocalData(Object obj) {
        Assertions.assertCondition(obj instanceof ReactTextInputLocalData);
        this.mLocalData = (ReactTextInputLocalData) obj;
        dirty();
    }

    @ReactProp(name = "mostRecentEventCount")
    public void setMostRecentEventCount(int i) {
        this.mMostRecentEventCount = i;
    }

    @ReactProp(name = "text")
    public void setText(String str) {
        this.mText = str;
        markUpdated();
    }

    public String getText() {
        return this.mText;
    }

    @ReactProp(name = "placeholder")
    public void setPlaceholder(String str) {
        this.mPlaceholder = str;
        markUpdated();
    }

    public String getPlaceholder() {
        return this.mPlaceholder;
    }

    public void setTextBreakStrategy(String str) {
        if (str == null || "simple".equals(str)) {
            this.mTextBreakStrategy = 0;
        } else if ("highQuality".equals(str)) {
            this.mTextBreakStrategy = 1;
        } else if ("balanced".equals(str)) {
            this.mTextBreakStrategy = 2;
        } else {
            FLog.w(ReactConstants.TAG, "Invalid textBreakStrategy: " + str);
            this.mTextBreakStrategy = 0;
        }
    }

    public void onCollectExtraUpdates(UIViewOperationQueue uIViewOperationQueue) {
        super.onCollectExtraUpdates(uIViewOperationQueue);
        if (this.mMostRecentEventCount != -1) {
            uIViewOperationQueue.enqueueUpdateExtraData(getReactTag(), new ReactTextUpdate(spannedFromShadowNode(this, getText(), false, (NativeViewHierarchyOptimizer) null), this.mMostRecentEventCount, this.mContainsImages, getPadding(0), getPadding(1), getPadding(2), getPadding(3), this.mTextAlign, this.mTextBreakStrategy, this.mJustificationMode));
        }
    }

    public void setPadding(int i, float f) {
        super.setPadding(i, f);
        markUpdated();
    }

    /* access modifiers changed from: protected */
    public EditText createInternalEditText() {
        return new EditText(new ContextThemeWrapper((Context) getThemedContext(), R.style.Theme_ReactNative_TextInput_DefaultBackground));
    }
}
