package com.facebook.react.views.textinput;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.QwertyKeyListener;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.util.Predicate;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.uimanager.style.Overflow;
import com.facebook.react.views.text.ReactTextUpdate;
import com.facebook.react.views.text.ReactTypefaceUtils;
import com.facebook.react.views.text.TextAttributes;
import com.facebook.react.views.text.TextLayoutManager;
import com.facebook.react.views.text.internal.span.CustomLetterSpacingSpan;
import com.facebook.react.views.text.internal.span.CustomLineHeightSpan;
import com.facebook.react.views.text.internal.span.CustomStyleSpan;
import com.facebook.react.views.text.internal.span.ReactAbsoluteSizeSpan;
import com.facebook.react.views.text.internal.span.ReactBackgroundColorSpan;
import com.facebook.react.views.text.internal.span.ReactForegroundColorSpan;
import com.facebook.react.views.text.internal.span.ReactSpan;
import com.facebook.react.views.text.internal.span.ReactStrikethroughSpan;
import com.facebook.react.views.text.internal.span.ReactTextPaintHolderSpan;
import com.facebook.react.views.text.internal.span.ReactUnderlineSpan;
import com.facebook.react.views.text.internal.span.TextInlineImageSpan;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class ReactEditText extends AppCompatEditText {
    public static final boolean DEBUG_MODE = false;
    /* access modifiers changed from: private */
    public static final KeyListener sKeyListener = QwertyKeyListener.getInstanceForFullKeyboard();
    /* access modifiers changed from: private */
    public final String TAG = ReactEditText.class.getSimpleName();
    private boolean mAutoFocus = false;
    protected boolean mContainsImages;
    private ContentSizeWatcher mContentSizeWatcher;
    /* access modifiers changed from: private */
    public boolean mContextMenuHidden = false;
    private final int mDefaultGravityHorizontal;
    private final int mDefaultGravityVertical;
    private boolean mDetectScrollMovement = false;
    private boolean mDidAttachToWindow = false;
    private boolean mDisableFullscreen;
    protected boolean mDisableTextDiffing = false;
    private EventDispatcher mEventDispatcher;
    private String mFontFamily = null;
    private int mFontStyle = -1;
    private int mFontWeight = -1;
    private final InputMethodManager mInputMethodManager;
    protected boolean mIsSettingTextFromJS;
    protected boolean mIsSettingTextFromState = false;
    private InternalKeyListener mKeyListener;
    /* access modifiers changed from: private */
    public ArrayList<TextWatcher> mListeners;
    protected int mNativeEventCount;
    private boolean mOnKeyPress = false;
    private Overflow mOverflow = Overflow.VISIBLE;
    private String mPlaceholder = null;
    private String mReturnKeyType;
    private ScrollWatcher mScrollWatcher;
    private boolean mSelectTextOnFocus = false;
    private SelectionWatcher mSelectionWatcher;
    private int mStagedInputType;
    private StateWrapper mStateWrapper = null;
    private String mSubmitBehavior = null;
    private TextAttributes mTextAttributes;
    private TextWatcherDelegator mTextWatcherDelegator;
    private boolean mTypefaceDirty = false;

    public boolean isLayoutRequested() {
        return false;
    }

    static {
        ReactBuildConfig reactBuildConfig = ReactBuildConfig.INSTANCE;
    }

    public ReactEditText(Context context) {
        super(context);
        setFocusableInTouchMode(false);
        this.mInputMethodManager = (InputMethodManager) Assertions.assertNotNull(context.getSystemService("input_method"));
        this.mDefaultGravityHorizontal = getGravity() & 8388615;
        this.mDefaultGravityVertical = getGravity() & 112;
        this.mNativeEventCount = 0;
        this.mIsSettingTextFromJS = false;
        this.mDisableFullscreen = false;
        this.mListeners = null;
        this.mTextWatcherDelegator = null;
        this.mStagedInputType = getInputType();
        if (this.mKeyListener == null) {
            this.mKeyListener = new InternalKeyListener();
        }
        this.mScrollWatcher = null;
        this.mTextAttributes = new TextAttributes();
        applyTextAttributes();
        if (Build.VERSION.SDK_INT <= 27) {
            setLayerType(1, (Paint) null);
        }
        ViewCompat.setAccessibilityDelegate(this, new ReactAccessibilityDelegate(this, isFocusable(), getImportantForAccessibility()) {
            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i != 16) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                int length = ReactEditText.this.getText().length();
                if (length > 0) {
                    ReactEditText.this.setSelection(length);
                }
                return ReactEditText.this.requestFocusInternal();
            }
        });
        AnonymousClass2 r4 = new ActionMode.Callback() {
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            public void onDestroyActionMode(ActionMode actionMode) {
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return true;
            }

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                if (ReactEditText.this.mContextMenuHidden) {
                    return false;
                }
                menu.removeItem(16908337);
                return true;
            }
        };
        setCustomSelectionActionModeCallback(r4);
        setCustomInsertionActionModeCallback(r4);
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        if (DEBUG_MODE) {
            String str = this.TAG;
            FLog.e(str, "finalize[" + getId() + "] delete cached spannable");
        }
        TextLayoutManager.deleteCachedSpannableForTag(getId());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        onContentSizeChange();
        if (this.mSelectTextOnFocus && isFocused()) {
            selectAll();
            this.mSelectTextOnFocus = false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mDetectScrollMovement = true;
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 2 && this.mDetectScrollMovement) {
            if (!canScrollVertically(-1) && !canScrollVertically(1) && !canScrollHorizontally(-1) && !canScrollHorizontally(1)) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            this.mDetectScrollMovement = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 66 || isMultiline()) {
            return super.onKeyUp(i, keyEvent);
        }
        hideSoftKeyboard();
        return true;
    }

    public void setLineHeight(int i) {
        this.mTextAttributes.setLineHeight((float) i);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        ScrollWatcher scrollWatcher = this.mScrollWatcher;
        if (scrollWatcher != null) {
            scrollWatcher.onScrollChanged(i, i2, i3, i4);
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        ReactContext reactContext = UIManagerHelper.getReactContext(this);
        InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (onCreateInputConnection != null && this.mOnKeyPress) {
            onCreateInputConnection = new ReactEditTextInputConnectionWrapper(onCreateInputConnection, reactContext, this, this.mEventDispatcher);
        }
        if (isMultiline() && (shouldBlurOnReturn() || shouldSubmitOnReturn())) {
            editorInfo.imeOptions &= -1073741825;
        }
        return onCreateInputConnection;
    }

    public boolean onTextContextMenuItem(int i) {
        if (i == 16908322) {
            i = 16908337;
        }
        return super.onTextContextMenuItem(i);
    }

    public void clearFocus() {
        setFocusableInTouchMode(false);
        super.clearFocus();
        hideSoftKeyboard();
    }

    public boolean requestFocus(int i, Rect rect) {
        return isFocused();
    }

    /* access modifiers changed from: private */
    public boolean requestFocusInternal() {
        setFocusableInTouchMode(true);
        boolean requestFocus = super.requestFocus(130, (Rect) null);
        if (getShowSoftInputOnFocus()) {
            showSoftKeyboard();
        }
        return requestFocus;
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
            super.addTextChangedListener(getTextWatcherDelegator());
        }
        this.mListeners.add(textWatcher);
    }

    public void removeTextChangedListener(TextWatcher textWatcher) {
        ArrayList<TextWatcher> arrayList = this.mListeners;
        if (arrayList != null) {
            arrayList.remove(textWatcher);
            if (this.mListeners.isEmpty()) {
                this.mListeners = null;
                super.removeTextChangedListener(getTextWatcherDelegator());
            }
        }
    }

    public void setContentSizeWatcher(ContentSizeWatcher contentSizeWatcher) {
        this.mContentSizeWatcher = contentSizeWatcher;
    }

    public void setScrollWatcher(ScrollWatcher scrollWatcher) {
        this.mScrollWatcher = scrollWatcher;
    }

    public void maybeSetSelection(int i, int i2, int i3) {
        if (canUpdateWithEventCount(i)) {
            maybeSetSelection(i2, i3);
        }
    }

    private void maybeSetSelection(int i, int i2) {
        if (i != -1 && i2 != -1) {
            setSelection(clampToTextLength(i), clampToTextLength(i2));
        }
    }

    private int clampToTextLength(int i) {
        return Math.max(0, Math.min(i, getText() == null ? 0 : getText().length()));
    }

    public void setSelection(int i, int i2) {
        if (DEBUG_MODE) {
            String str = this.TAG;
            FLog.e(str, "setSelection[" + getId() + "]: " + i + " " + i2);
        }
        super.setSelection(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onSelectionChanged(int i, int i2) {
        if (DEBUG_MODE) {
            String str = this.TAG;
            FLog.e(str, "onSelectionChanged[" + getId() + "]: " + i + " " + i2);
        }
        super.onSelectionChanged(i, i2);
        if (this.mSelectionWatcher != null && hasFocus()) {
            this.mSelectionWatcher.onSelectionChanged(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect) {
        SelectionWatcher selectionWatcher;
        super.onFocusChanged(z, i, rect);
        if (z && (selectionWatcher = this.mSelectionWatcher) != null) {
            selectionWatcher.onSelectionChanged(getSelectionStart(), getSelectionEnd());
        }
    }

    public void setSelectionWatcher(SelectionWatcher selectionWatcher) {
        this.mSelectionWatcher = selectionWatcher;
    }

    public void setOnKeyPress(boolean z) {
        this.mOnKeyPress = z;
    }

    public boolean shouldBlurOnReturn() {
        String submitBehavior = getSubmitBehavior();
        if (submitBehavior == null) {
            return !isMultiline();
        }
        return submitBehavior.equals("blurAndSubmit");
    }

    public boolean shouldSubmitOnReturn() {
        String submitBehavior = getSubmitBehavior();
        if (submitBehavior == null) {
            if (isMultiline()) {
                return false;
            }
        } else if (!submitBehavior.equals("submit") && !submitBehavior.equals("blurAndSubmit")) {
            return false;
        }
        return true;
    }

    public String getSubmitBehavior() {
        return this.mSubmitBehavior;
    }

    public void setSubmitBehavior(String str) {
        this.mSubmitBehavior = str;
    }

    public void setDisableFullscreenUI(boolean z) {
        this.mDisableFullscreen = z;
        updateImeOptions();
    }

    public boolean getDisableFullscreenUI() {
        return this.mDisableFullscreen;
    }

    public void setReturnKeyType(String str) {
        this.mReturnKeyType = str;
        updateImeOptions();
    }

    public String getReturnKeyType() {
        return this.mReturnKeyType;
    }

    /* access modifiers changed from: package-private */
    public int getStagedInputType() {
        return this.mStagedInputType;
    }

    /* access modifiers changed from: package-private */
    public void setStagedInputType(int i) {
        this.mStagedInputType = i;
    }

    /* access modifiers changed from: package-private */
    public void commitStagedInputType() {
        if (getInputType() != this.mStagedInputType) {
            int selectionStart = getSelectionStart();
            int selectionEnd = getSelectionEnd();
            setInputType(this.mStagedInputType);
            maybeSetSelection(selectionStart, selectionEnd);
        }
    }

    public void setInputType(int i) {
        Typeface typeface = super.getTypeface();
        super.setInputType(i);
        this.mStagedInputType = i;
        super.setTypeface(typeface);
        if (isMultiline()) {
            setSingleLine(false);
        }
        if (this.mKeyListener == null) {
            this.mKeyListener = new InternalKeyListener();
        }
        this.mKeyListener.setInputType(i);
        setKeyListener(this.mKeyListener);
    }

    public void setPlaceholder(String str) {
        if (!Objects.equals(str, this.mPlaceholder)) {
            this.mPlaceholder = str;
            setHint(str);
        }
    }

    public void setFontFamily(String str) {
        this.mFontFamily = str;
        this.mTypefaceDirty = true;
    }

    public void setFontWeight(String str) {
        int parseFontWeight = ReactTypefaceUtils.parseFontWeight(str);
        if (parseFontWeight != this.mFontWeight) {
            this.mFontWeight = parseFontWeight;
            this.mTypefaceDirty = true;
        }
    }

    public void setFontStyle(String str) {
        int parseFontStyle = ReactTypefaceUtils.parseFontStyle(str);
        if (parseFontStyle != this.mFontStyle) {
            this.mFontStyle = parseFontStyle;
            this.mTypefaceDirty = true;
        }
    }

    public void setFontFeatureSettings(String str) {
        if (!Objects.equals(str, getFontFeatureSettings())) {
            super.setFontFeatureSettings(str);
            this.mTypefaceDirty = true;
        }
    }

    public void maybeUpdateTypeface() {
        if (this.mTypefaceDirty) {
            this.mTypefaceDirty = false;
            setTypeface(ReactTypefaceUtils.applyStyles(getTypeface(), this.mFontStyle, this.mFontWeight, this.mFontFamily, getContext().getAssets()));
            if (this.mFontStyle == -1 && this.mFontWeight == -1 && this.mFontFamily == null && getFontFeatureSettings() == null) {
                setPaintFlags(getPaintFlags() & -129);
            } else {
                setPaintFlags(getPaintFlags() | 128);
            }
        }
    }

    public void requestFocusFromJS() {
        requestFocusInternal();
    }

    /* access modifiers changed from: package-private */
    public void clearFocusFromJS() {
        clearFocus();
    }

    public int incrementAndGetEventCounter() {
        int i = this.mNativeEventCount + 1;
        this.mNativeEventCount = i;
        return i;
    }

    public void maybeSetTextFromJS(ReactTextUpdate reactTextUpdate) {
        this.mIsSettingTextFromJS = true;
        maybeSetText(reactTextUpdate);
        this.mIsSettingTextFromJS = false;
    }

    public void maybeSetTextFromState(ReactTextUpdate reactTextUpdate) {
        this.mIsSettingTextFromState = true;
        maybeSetText(reactTextUpdate);
        this.mIsSettingTextFromState = false;
    }

    public boolean canUpdateWithEventCount(int i) {
        return i >= this.mNativeEventCount;
    }

    public void maybeSetText(ReactTextUpdate reactTextUpdate) {
        if ((!isSecureText() || !TextUtils.equals(getText(), reactTextUpdate.getText())) && canUpdateWithEventCount(reactTextUpdate.getJsEventCounter())) {
            if (DEBUG_MODE) {
                String str = this.TAG;
                FLog.e(str, "maybeSetText[" + getId() + "]: current text: " + getText() + " update: " + reactTextUpdate.getText());
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(reactTextUpdate.getText());
            manageSpans(spannableStringBuilder);
            stripStyleEquivalentSpans(spannableStringBuilder);
            this.mContainsImages = reactTextUpdate.containsImages();
            this.mDisableTextDiffing = true;
            if (reactTextUpdate.getText().length() == 0) {
                setText((CharSequence) null);
            } else {
                getText().replace(0, length(), spannableStringBuilder);
            }
            this.mDisableTextDiffing = false;
            if (getBreakStrategy() != reactTextUpdate.getTextBreakStrategy()) {
                setBreakStrategy(reactTextUpdate.getTextBreakStrategy());
            }
            updateCachedSpannable();
        }
    }

    private void manageSpans(SpannableStringBuilder spannableStringBuilder) {
        Object[] spans = getText().getSpans(0, length(), Object.class);
        for (Object obj : spans) {
            int spanFlags = getText().getSpanFlags(obj);
            boolean z = (spanFlags & 33) == 33;
            if (obj instanceof ReactSpan) {
                getText().removeSpan(obj);
            }
            if (z) {
                int spanStart = getText().getSpanStart(obj);
                int spanEnd = getText().getSpanEnd(obj);
                getText().removeSpan(obj);
                if (sameTextForSpan(getText(), spannableStringBuilder, spanStart, spanEnd)) {
                    spannableStringBuilder.setSpan(obj, spanStart, spanEnd, spanFlags);
                }
            }
        }
    }

    private void stripStyleEquivalentSpans(SpannableStringBuilder spannableStringBuilder) {
        stripSpansOfKind(spannableStringBuilder, ReactAbsoluteSizeSpan.class, new ReactEditText$$ExternalSyntheticLambda0(this));
        stripSpansOfKind(spannableStringBuilder, ReactBackgroundColorSpan.class, new ReactEditText$$ExternalSyntheticLambda1(this));
        stripSpansOfKind(spannableStringBuilder, ReactForegroundColorSpan.class, new ReactEditText$$ExternalSyntheticLambda2(this));
        stripSpansOfKind(spannableStringBuilder, ReactStrikethroughSpan.class, new ReactEditText$$ExternalSyntheticLambda3(this));
        stripSpansOfKind(spannableStringBuilder, ReactUnderlineSpan.class, new ReactEditText$$ExternalSyntheticLambda4(this));
        stripSpansOfKind(spannableStringBuilder, CustomLetterSpacingSpan.class, new ReactEditText$$ExternalSyntheticLambda5(this));
        stripSpansOfKind(spannableStringBuilder, CustomStyleSpan.class, new ReactEditText$$ExternalSyntheticLambda6(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$stripStyleEquivalentSpans$0(ReactAbsoluteSizeSpan reactAbsoluteSizeSpan) {
        return reactAbsoluteSizeSpan.getSize() == this.mTextAttributes.getEffectiveFontSize();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$stripStyleEquivalentSpans$1(ReactBackgroundColorSpan reactBackgroundColorSpan) {
        return Integer.valueOf(reactBackgroundColorSpan.getBackgroundColor()).equals(BackgroundStyleApplicator.getBackgroundColor(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$stripStyleEquivalentSpans$2(ReactForegroundColorSpan reactForegroundColorSpan) {
        return reactForegroundColorSpan.getForegroundColor() == getCurrentTextColor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$stripStyleEquivalentSpans$3(ReactStrikethroughSpan reactStrikethroughSpan) {
        return (getPaintFlags() & 16) != 0;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$stripStyleEquivalentSpans$4(ReactUnderlineSpan reactUnderlineSpan) {
        return (getPaintFlags() & 8) != 0;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$stripStyleEquivalentSpans$5(CustomLetterSpacingSpan customLetterSpacingSpan) {
        return customLetterSpacingSpan.getSpacing() == this.mTextAttributes.getEffectiveLetterSpacing();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$stripStyleEquivalentSpans$6(CustomStyleSpan customStyleSpan) {
        return customStyleSpan.getStyle() == this.mFontStyle && Objects.equals(customStyleSpan.getFontFamily(), this.mFontFamily) && customStyleSpan.getWeight() == this.mFontWeight && Objects.equals(customStyleSpan.getFontFeatureSettings(), getFontFeatureSettings());
    }

    private <T> void stripSpansOfKind(SpannableStringBuilder spannableStringBuilder, Class<T> cls, Predicate predicate) {
        for (Object obj : spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), cls)) {
            if (predicate.test(obj)) {
                spannableStringBuilder.removeSpan(obj);
            }
        }
    }

    private void addSpansFromStyleAttributes(SpannableStringBuilder spannableStringBuilder) {
        spannableStringBuilder.setSpan(new ReactAbsoluteSizeSpan(this.mTextAttributes.getEffectiveFontSize()), 0, spannableStringBuilder.length(), 16711698);
        spannableStringBuilder.setSpan(new ReactForegroundColorSpan(getCurrentTextColor()), 0, spannableStringBuilder.length(), 16711698);
        Integer backgroundColor = BackgroundStyleApplicator.getBackgroundColor(this);
        if (!(backgroundColor == null || backgroundColor.intValue() == 0)) {
            spannableStringBuilder.setSpan(new ReactBackgroundColorSpan(backgroundColor.intValue()), 0, spannableStringBuilder.length(), 16711698);
        }
        if ((getPaintFlags() & 16) != 0) {
            spannableStringBuilder.setSpan(new ReactStrikethroughSpan(), 0, spannableStringBuilder.length(), 16711698);
        }
        if ((getPaintFlags() & 8) != 0) {
            spannableStringBuilder.setSpan(new ReactUnderlineSpan(), 0, spannableStringBuilder.length(), 16711698);
        }
        float effectiveLetterSpacing = this.mTextAttributes.getEffectiveLetterSpacing();
        if (!Float.isNaN(effectiveLetterSpacing)) {
            spannableStringBuilder.setSpan(new CustomLetterSpacingSpan(effectiveLetterSpacing), 0, spannableStringBuilder.length(), 16711698);
        }
        if (!(this.mFontStyle == -1 && this.mFontWeight == -1 && this.mFontFamily == null && getFontFeatureSettings() == null)) {
            spannableStringBuilder.setSpan(new CustomStyleSpan(this.mFontStyle, this.mFontWeight, getFontFeatureSettings(), this.mFontFamily, getContext().getAssets()), 0, spannableStringBuilder.length(), 16711698);
        }
        float effectiveLineHeight = this.mTextAttributes.getEffectiveLineHeight();
        if (!Float.isNaN(effectiveLineHeight)) {
            spannableStringBuilder.setSpan(new CustomLineHeightSpan(effectiveLineHeight), 0, spannableStringBuilder.length(), 16711698);
        }
    }

    private static boolean sameTextForSpan(Editable editable, SpannableStringBuilder spannableStringBuilder, int i, int i2) {
        if (i > spannableStringBuilder.length() || i2 > spannableStringBuilder.length()) {
            return false;
        }
        while (i < i2) {
            if (editable.charAt(i) != spannableStringBuilder.charAt(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean showSoftKeyboard() {
        return this.mInputMethodManager.showSoftInput(this, 0);
    }

    /* access modifiers changed from: protected */
    public void hideSoftKeyboard() {
        this.mInputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    private TextWatcherDelegator getTextWatcherDelegator() {
        if (this.mTextWatcherDelegator == null) {
            this.mTextWatcherDelegator = new TextWatcherDelegator();
        }
        return this.mTextWatcherDelegator;
    }

    /* access modifiers changed from: package-private */
    public boolean isMultiline() {
        return (getInputType() & 131072) != 0;
    }

    private boolean isSecureText() {
        return (getInputType() & 144) != 0;
    }

    /* access modifiers changed from: private */
    public void onContentSizeChange() {
        ContentSizeWatcher contentSizeWatcher = this.mContentSizeWatcher;
        if (contentSizeWatcher != null) {
            contentSizeWatcher.onLayout();
        }
        setIntrinsicContentSize();
    }

    private void setIntrinsicContentSize() {
        ReactContext reactContext = UIManagerHelper.getReactContext(this);
        if (this.mStateWrapper == null && !reactContext.isBridgeless()) {
            ReactTextInputLocalData reactTextInputLocalData = new ReactTextInputLocalData(this);
            UIManagerModule uIManagerModule = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
            if (uIManagerModule != null) {
                uIManagerModule.setViewLocalData(getId(), reactTextInputLocalData);
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
            i = this.mDefaultGravityHorizontal;
        }
        setGravity(i | (getGravity() & -8388616));
    }

    /* access modifiers changed from: package-private */
    public void setGravityVertical(int i) {
        if (i == 0) {
            i = this.mDefaultGravityVertical;
        }
        setGravity(i | (getGravity() & -113));
    }

    private void updateImeOptions() {
        int i = 5;
        String str = this.mReturnKeyType;
        if (str != null) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1273775369:
                    if (str.equals("previous")) {
                        c = 0;
                        break;
                    }
                    break;
                case -906336856:
                    if (str.equals("search")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3304:
                    if (str.equals("go")) {
                        c = 2;
                        break;
                    }
                    break;
                case 3089282:
                    if (str.equals("done")) {
                        c = 3;
                        break;
                    }
                    break;
                case 3377907:
                    if (str.equals("next")) {
                        c = 4;
                        break;
                    }
                    break;
                case 3387192:
                    if (str.equals("none")) {
                        c = 5;
                        break;
                    }
                    break;
                case 3526536:
                    if (str.equals("send")) {
                        c = 6;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    i = 7;
                    break;
                case 1:
                    i = 3;
                    break;
                case 2:
                    i = 2;
                    break;
                case 4:
                    break;
                case 5:
                    i = 1;
                    break;
                case 6:
                    i = 4;
                    break;
            }
        }
        i = 6;
        if (this.mDisableFullscreen) {
            setImeOptions(i | 33554432);
        } else {
            setImeOptions(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan drawable2 : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                if (drawable2.getDrawable() == drawable) {
                    return true;
                }
            }
        }
        return super.verifyDrawable(drawable);
    }

    public void invalidateDrawable(Drawable drawable) {
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan drawable2 : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                if (drawable2.getDrawable() == drawable) {
                    invalidate();
                }
            }
        }
        super.invalidateDrawable(drawable);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan onDetachedFromWindow : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                onDetachedFromWindow.onDetachedFromWindow();
            }
        }
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan onStartTemporaryDetach : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                onStartTemporaryDetach.onStartTemporaryDetach();
            }
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        super.setTextIsSelectable(true);
        maybeSetSelection(selectionStart, selectionEnd);
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan onAttachedToWindow : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                onAttachedToWindow.onAttachedToWindow();
            }
        }
        if (this.mAutoFocus && !this.mDidAttachToWindow) {
            requestFocusInternal();
        }
        this.mDidAttachToWindow = true;
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        if (this.mContainsImages) {
            Editable text = getText();
            for (TextInlineImageSpan onFinishTemporaryDetach : (TextInlineImageSpan[]) text.getSpans(0, text.length(), TextInlineImageSpan.class)) {
                onFinishTemporaryDetach.onFinishTemporaryDetach();
            }
        }
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

    public int getBorderColor(int i) {
        Integer borderColor = BackgroundStyleApplicator.getBorderColor(this, LogicalEdge.values()[i]);
        if (borderColor == null) {
            return 0;
        }
        return borderColor.intValue();
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

    public void setLetterSpacingPt(float f) {
        this.mTextAttributes.setLetterSpacing(f);
        applyTextAttributes();
    }

    public void setAllowFontScaling(boolean z) {
        if (this.mTextAttributes.getAllowFontScaling() != z) {
            this.mTextAttributes.setAllowFontScaling(z);
            applyTextAttributes();
        }
    }

    public void setFontSize(float f) {
        this.mTextAttributes.setFontSize(f);
        applyTextAttributes();
    }

    public void setMaxFontSizeMultiplier(float f) {
        if (f != this.mTextAttributes.getMaxFontSizeMultiplier()) {
            this.mTextAttributes.setMaxFontSizeMultiplier(f);
            applyTextAttributes();
        }
    }

    public void setAutoFocus(boolean z) {
        this.mAutoFocus = z;
    }

    public void setSelectTextOnFocus(boolean z) {
        super.setSelectAllOnFocus(z);
        this.mSelectTextOnFocus = z;
    }

    public void setContextMenuHidden(boolean z) {
        this.mContextMenuHidden = z;
    }

    /* access modifiers changed from: protected */
    public void applyTextAttributes() {
        setTextSize(0, (float) this.mTextAttributes.getEffectiveFontSize());
        float effectiveLetterSpacing = this.mTextAttributes.getEffectiveLetterSpacing();
        if (!Float.isNaN(effectiveLetterSpacing)) {
            setLetterSpacing(effectiveLetterSpacing);
        }
    }

    public StateWrapper getStateWrapper() {
        return this.mStateWrapper;
    }

    public void setStateWrapper(StateWrapper stateWrapper) {
        this.mStateWrapper = stateWrapper;
    }

    /* access modifiers changed from: private */
    public void updateCachedSpannable() {
        if (this.mStateWrapper != null && getId() != -1) {
            Editable text = getText();
            boolean z = text != null && text.length() > 0;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (z) {
                try {
                    spannableStringBuilder.append(text.subSequence(0, text.length()));
                } catch (IndexOutOfBoundsException e) {
                    ReactSoftExceptionLogger.logSoftException(this.TAG, e);
                }
            }
            if (!z) {
                if (getHint() != null && getHint().length() > 0) {
                    spannableStringBuilder.append(getHint());
                } else if (ViewUtil.getUIManagerType((View) this) != 2) {
                    spannableStringBuilder.append("I");
                }
            }
            addSpansFromStyleAttributes(spannableStringBuilder);
            spannableStringBuilder.setSpan(new ReactTextPaintHolderSpan(new TextPaint(getPaint())), 0, spannableStringBuilder.length(), 18);
            TextLayoutManager.setCachedSpannableForTag(getId(), spannableStringBuilder);
        }
    }

    /* access modifiers changed from: package-private */
    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.mEventDispatcher = eventDispatcher;
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

    public void onDraw(Canvas canvas) {
        if (this.mOverflow != Overflow.VISIBLE) {
            BackgroundStyleApplicator.clipToPaddingBox(this, canvas);
        }
        super.onDraw(canvas);
    }

    private class TextWatcherDelegator implements TextWatcher {
        private TextWatcherDelegator() {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            ReactEditText reactEditText = ReactEditText.this;
            if (!reactEditText.mIsSettingTextFromJS && reactEditText.mListeners != null) {
                Iterator it = ReactEditText.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((TextWatcher) it.next()).beforeTextChanged(charSequence, i, i2, i3);
                }
            }
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (ReactEditText.DEBUG_MODE) {
                String r0 = ReactEditText.this.TAG;
                FLog.e(r0, "onTextChanged[" + ReactEditText.this.getId() + "]: " + charSequence + " " + i + " " + i2 + " " + i3);
            }
            ReactEditText reactEditText = ReactEditText.this;
            if (!reactEditText.mIsSettingTextFromJS && reactEditText.mListeners != null) {
                Iterator it = ReactEditText.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((TextWatcher) it.next()).onTextChanged(charSequence, i, i2, i3);
                }
            }
            ReactEditText.this.updateCachedSpannable();
            ReactEditText.this.onContentSizeChange();
        }

        public void afterTextChanged(Editable editable) {
            ReactEditText reactEditText = ReactEditText.this;
            if (!reactEditText.mIsSettingTextFromJS && reactEditText.mListeners != null) {
                Iterator it = ReactEditText.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((TextWatcher) it.next()).afterTextChanged(editable);
                }
            }
        }
    }

    private static class InternalKeyListener implements KeyListener {
        private int mInputType = 0;

        public void setInputType(int i) {
            this.mInputType = i;
        }

        public int getInputType() {
            return this.mInputType;
        }

        public boolean onKeyDown(View view, Editable editable, int i, KeyEvent keyEvent) {
            return ReactEditText.sKeyListener.onKeyDown(view, editable, i, keyEvent);
        }

        public boolean onKeyUp(View view, Editable editable, int i, KeyEvent keyEvent) {
            return ReactEditText.sKeyListener.onKeyUp(view, editable, i, keyEvent);
        }

        public boolean onKeyOther(View view, Editable editable, KeyEvent keyEvent) {
            return ReactEditText.sKeyListener.onKeyOther(view, editable, keyEvent);
        }

        public void clearMetaKeyState(View view, Editable editable, int i) {
            ReactEditText.sKeyListener.clearMetaKeyState(view, editable, i);
        }
    }
}
