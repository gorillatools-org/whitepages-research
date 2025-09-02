package com.facebook.react.views.text;

import android.content.Context;
import android.text.Spannable;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.internal.SystraceSection;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.IViewManagerWithChildren;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.internal.span.ReactClickableSpan;
import com.facebook.react.views.text.internal.span.TextInlineImageSpan;
import com.facebook.yoga.YogaMeasureMode;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name = "RCTText")
public class ReactTextViewManager extends ReactTextAnchorViewManager<ReactTextView, ReactTextShadowNode> implements IViewManagerWithChildren {
    @VisibleForTesting
    public static final String REACT_CLASS = "RCTText";
    private static final String TAG = "ReactTextViewManager";
    private static final short TX_STATE_KEY_ATTRIBUTED_STRING = 0;
    private static final short TX_STATE_KEY_HASH = 2;
    private static final short TX_STATE_KEY_MOST_RECENT_EVENT_COUNT = 3;
    private static final short TX_STATE_KEY_PARAGRAPH_ATTRIBUTES = 1;
    protected ReactTextViewManagerCallback mReactTextViewManagerCallback;

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    public ReactTextViewManager() {
        this((ReactTextViewManagerCallback) null);
    }

    public ReactTextViewManager(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        this.mReactTextViewManagerCallback = reactTextViewManagerCallback;
        setupViewRecycling();
    }

    /* access modifiers changed from: protected */
    public ReactTextView prepareToRecycleView(ThemedReactContext themedReactContext, ReactTextView reactTextView) {
        ReactTextView reactTextView2 = (ReactTextView) super.prepareToRecycleView(themedReactContext, reactTextView);
        if (reactTextView2 != null) {
            reactTextView2.recycleView();
            setSelectionColor(reactTextView2, (Integer) null);
        }
        return reactTextView;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactTextView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactTextView(themedReactContext);
    }

    public void updateExtraData(ReactTextView reactTextView, Object obj) {
        SystraceSection systraceSection = new SystraceSection("ReactTextViewManager.updateExtraData");
        try {
            ReactTextUpdate reactTextUpdate = (ReactTextUpdate) obj;
            Spannable text = reactTextUpdate.getText();
            if (reactTextUpdate.containsImages()) {
                TextInlineImageSpan.possiblyUpdateInlineImageSpans(text, reactTextView);
            }
            reactTextView.setText(reactTextUpdate);
            ReactClickableSpan[] reactClickableSpanArr = (ReactClickableSpan[]) text.getSpans(0, reactTextUpdate.getText().length(), ReactClickableSpan.class);
            if (reactClickableSpanArr.length > 0) {
                reactTextView.setTag(R.id.accessibility_links, new ReactAccessibilityDelegate.AccessibilityLinks(reactClickableSpanArr, text));
                ReactAccessibilityDelegate.resetDelegate(reactTextView, reactTextView.isFocusable(), reactTextView.getImportantForAccessibility());
            }
            systraceSection.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public ReactTextShadowNode createShadowNodeInstance() {
        return new ReactTextShadowNode(this.mReactTextViewManagerCallback);
    }

    public ReactTextShadowNode createShadowNodeInstance(ReactTextViewManagerCallback reactTextViewManagerCallback) {
        return new ReactTextShadowNode(reactTextViewManagerCallback);
    }

    public Class<ReactTextShadowNode> getShadowNodeClass() {
        return ReactTextShadowNode.class;
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ReactTextView reactTextView) {
        super.onAfterUpdateTransaction(reactTextView);
        reactTextView.updateView();
    }

    public Object updateState(ReactTextView reactTextView, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        SystraceSection systraceSection = new SystraceSection("ReactTextViewManager.updateState");
        try {
            ReadableMapBuffer stateDataMapBuffer = stateWrapper.getStateDataMapBuffer();
            if (stateDataMapBuffer != null) {
                Object reactTextUpdate = getReactTextUpdate(reactTextView, reactStylesDiffMap, stateDataMapBuffer);
                systraceSection.close();
                return reactTextUpdate;
            }
            systraceSection.close();
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private Object getReactTextUpdate(ReactTextView reactTextView, ReactStylesDiffMap reactStylesDiffMap, MapBuffer mapBuffer) {
        MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(0);
        MapBuffer mapBuffer3 = mapBuffer.getMapBuffer(1);
        Spannable orCreateSpannableForText = TextLayoutManager.getOrCreateSpannableForText(reactTextView.getContext(), mapBuffer2, this.mReactTextViewManagerCallback);
        reactTextView.setSpanned(orCreateSpannableForText);
        try {
            reactTextView.setMinimumFontSize((float) mapBuffer3.getDouble(6));
            return new ReactTextUpdate(orCreateSpannableForText, -1, false, TextLayoutManager.getTextGravity(mapBuffer2, orCreateSpannableForText, reactTextView.getGravityHorizontal()), TextAttributeProps.getTextBreakStrategy(mapBuffer3.getString(2)), TextAttributeProps.getJustificationMode(reactStylesDiffMap, reactTextView.getJustificationMode()));
        } catch (IllegalArgumentException e) {
            FLog.e(TAG, "Paragraph Attributes: %s", mapBuffer3 != null ? mapBuffer3.toString() : "<empty>");
            throw e;
        }
    }

    public Map getExportedCustomDirectEventTypeConstants() {
        Map exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap();
        }
        exportedCustomDirectEventTypeConstants.putAll(MapBuilder.of("topTextLayout", MapBuilder.of("registrationName", "onTextLayout"), "topInlineViewLayout", MapBuilder.of("registrationName", "onInlineViewLayout")));
        return exportedCustomDirectEventTypeConstants;
    }

    public long measure(Context context, MapBuffer mapBuffer, MapBuffer mapBuffer2, MapBuffer mapBuffer3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, float[] fArr) {
        return TextLayoutManager.measureText(context, mapBuffer, mapBuffer2, f, yogaMeasureMode, f2, yogaMeasureMode2, this.mReactTextViewManagerCallback, fArr);
    }

    public void setPadding(ReactTextView reactTextView, int i, int i2, int i3, int i4) {
        reactTextView.setPadding(i, i2, i3, i4);
    }

    @ReactProp(name = "overflow")
    public void setOverflow(ReactTextView reactTextView, String str) {
        reactTextView.setOverflow(str);
    }
}
