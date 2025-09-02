package com.facebook.react.views.scroll;

import android.view.View;
import androidx.core.view.ViewCompat;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.RetryableMountingLayerException;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.views.scroll.MaintainVisibleScrollPositionHelper;
import com.facebook.react.views.scroll.ReactScrollViewCommandHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ReactModule(name = "RCTScrollView")
public class ReactScrollViewManager extends ViewGroupManager<ReactScrollView> implements ReactScrollViewCommandHelper.ScrollCommandHandler<ReactScrollView> {
    public static final String REACT_CLASS = "RCTScrollView";
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    private FpsListener mFpsListener;

    @ReactProp(name = "horizontal")
    public void setHorizontal(ReactScrollView reactScrollView, boolean z) {
    }

    public ReactScrollViewManager() {
        this((FpsListener) null);
    }

    public ReactScrollViewManager(FpsListener fpsListener) {
        this.mFpsListener = fpsListener;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactScrollView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactScrollView(themedReactContext, this.mFpsListener);
    }

    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setScrollEnabled(z);
        reactScrollView.setFocusable(z);
    }

    @ReactProp(defaultBoolean = true, name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setVerticalScrollBarEnabled(z);
    }

    @ReactProp(name = "decelerationRate")
    public void setDecelerationRate(ReactScrollView reactScrollView, float f) {
        reactScrollView.setDecelerationRate(f);
    }

    @ReactProp(name = "disableIntervalMomentum")
    public void setDisableIntervalMomentum(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setDisableIntervalMomentum(z);
    }

    @ReactProp(name = "snapToInterval")
    public void setSnapToInterval(ReactScrollView reactScrollView, float f) {
        reactScrollView.setSnapInterval((int) (f * PixelUtil.getDisplayMetricDensity()));
    }

    @ReactProp(name = "snapToOffsets")
    public void setSnapToOffsets(ReactScrollView reactScrollView, ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() == 0) {
            reactScrollView.setSnapOffsets((List<Integer>) null);
            return;
        }
        float displayMetricDensity = PixelUtil.getDisplayMetricDensity();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(Integer.valueOf((int) (readableArray.getDouble(i) * ((double) displayMetricDensity))));
        }
        reactScrollView.setSnapOffsets(arrayList);
    }

    @ReactProp(name = "snapToAlignment")
    public void setSnapToAlignment(ReactScrollView reactScrollView, String str) {
        reactScrollView.setSnapToAlignment(ReactScrollViewHelper.parseSnapToAlignment(str));
    }

    @ReactProp(name = "snapToStart")
    public void setSnapToStart(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setSnapToStart(z);
    }

    @ReactProp(name = "snapToEnd")
    public void setSnapToEnd(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setSnapToEnd(z);
    }

    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setRemoveClippedSubviews(z);
    }

    @ReactProp(name = "sendMomentumEvents")
    public void setSendMomentumEvents(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setSendMomentumEvents(z);
    }

    @ReactProp(name = "scrollPerfTag")
    public void setScrollPerfTag(ReactScrollView reactScrollView, String str) {
        reactScrollView.setScrollPerfTag(str);
    }

    @ReactProp(name = "pagingEnabled")
    public void setPagingEnabled(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setPagingEnabled(z);
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "endFillColor")
    public void setBottomFillColor(ReactScrollView reactScrollView, int i) {
        reactScrollView.setEndFillColor(i);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(ReactScrollView reactScrollView, String str) {
        reactScrollView.setOverScrollMode(ReactScrollViewHelper.parseOverScrollMode(str));
    }

    @ReactProp(name = "nestedScrollEnabled")
    public void setNestedScrollEnabled(ReactScrollView reactScrollView, boolean z) {
        ViewCompat.setNestedScrollingEnabled(reactScrollView, z);
    }

    public Map<String, Integer> getCommandsMap() {
        return ReactScrollViewCommandHelper.getCommandsMap();
    }

    public void receiveCommand(ReactScrollView reactScrollView, int i, ReadableArray readableArray) {
        ReactScrollViewCommandHelper.receiveCommand(this, reactScrollView, i, readableArray);
    }

    public void receiveCommand(ReactScrollView reactScrollView, String str, ReadableArray readableArray) {
        ReactScrollViewCommandHelper.receiveCommand(this, reactScrollView, str, readableArray);
    }

    public void flashScrollIndicators(ReactScrollView reactScrollView) {
        reactScrollView.flashScrollIndicators();
    }

    public void scrollTo(ReactScrollView reactScrollView, ReactScrollViewCommandHelper.ScrollToCommandData scrollToCommandData) {
        reactScrollView.abortAnimation();
        if (scrollToCommandData.mAnimated) {
            reactScrollView.reactSmoothScrollTo(scrollToCommandData.mDestX, scrollToCommandData.mDestY);
        } else {
            reactScrollView.scrollTo(scrollToCommandData.mDestX, scrollToCommandData.mDestY);
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactScrollView reactScrollView, int i, float f) {
        LengthPercentage lengthPercentage;
        if (Float.isNaN(f)) {
            lengthPercentage = null;
        } else {
            lengthPercentage = new LengthPercentage(f, LengthPercentageType.POINT);
        }
        BackgroundStyleApplicator.setBorderRadius(reactScrollView, BorderRadiusProp.values()[i], lengthPercentage);
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactScrollView reactScrollView, String str) {
        BackgroundStyleApplicator.setBorderStyle(reactScrollView, str == null ? null : BorderStyle.fromString(str));
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactScrollView reactScrollView, int i, float f) {
        BackgroundStyleApplicator.setBorderWidth(reactScrollView, LogicalEdge.values()[i], Float.valueOf(f));
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactScrollView reactScrollView, int i, Integer num) {
        BackgroundStyleApplicator.setBorderColor(reactScrollView, LogicalEdge.ALL, num);
    }

    @ReactProp(name = "overflow")
    public void setOverflow(ReactScrollView reactScrollView, String str) {
        reactScrollView.setOverflow(str);
    }

    public void scrollToEnd(ReactScrollView reactScrollView, ReactScrollViewCommandHelper.ScrollToEndCommandData scrollToEndCommandData) {
        View childAt = reactScrollView.getChildAt(0);
        if (childAt != null) {
            int height = childAt.getHeight() + reactScrollView.getPaddingBottom();
            reactScrollView.abortAnimation();
            if (scrollToEndCommandData.mAnimated) {
                reactScrollView.reactSmoothScrollTo(reactScrollView.getScrollX(), height);
            } else {
                reactScrollView.scrollTo(reactScrollView.getScrollX(), height);
            }
        } else {
            throw new RetryableMountingLayerException("scrollToEnd called on ScrollView without child");
        }
    }

    @ReactProp(name = "persistentScrollbar")
    public void setPersistentScrollbar(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setScrollbarFadingEnabled(!z);
    }

    @ReactProp(name = "fadingEdgeLength")
    public void setFadingEdgeLength(ReactScrollView reactScrollView, int i) {
        if (i > 0) {
            reactScrollView.setVerticalFadingEdgeEnabled(true);
            reactScrollView.setFadingEdgeLength(i);
            return;
        }
        reactScrollView.setVerticalFadingEdgeEnabled(false);
        reactScrollView.setFadingEdgeLength(0);
    }

    @ReactProp(customType = "Point", name = "contentOffset")
    public void setContentOffset(ReactScrollView reactScrollView, ReadableMap readableMap) {
        reactScrollView.setContentOffset(readableMap);
    }

    @ReactProp(name = "maintainVisibleContentPosition")
    public void setMaintainVisibleContentPosition(ReactScrollView reactScrollView, ReadableMap readableMap) {
        if (readableMap != null) {
            reactScrollView.setMaintainVisibleContentPosition(MaintainVisibleScrollPositionHelper.Config.fromReadableMap(readableMap));
        } else {
            reactScrollView.setMaintainVisibleContentPosition((MaintainVisibleScrollPositionHelper.Config) null);
        }
    }

    public Object updateState(ReactScrollView reactScrollView, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        reactScrollView.setStateWrapper(stateWrapper);
        return null;
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new HashMap<>();
        }
        exportedCustomDirectEventTypeConstants.putAll(createExportedCustomDirectEventTypeConstants());
        return exportedCustomDirectEventTypeConstants;
    }

    public static Map<String, Object> createExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), MapBuilder.of("registrationName", "onScroll")).put(ScrollEventType.getJSEventName(ScrollEventType.BEGIN_DRAG), MapBuilder.of("registrationName", "onScrollBeginDrag")).put(ScrollEventType.getJSEventName(ScrollEventType.END_DRAG), MapBuilder.of("registrationName", "onScrollEndDrag")).put(ScrollEventType.getJSEventName(ScrollEventType.MOMENTUM_BEGIN), MapBuilder.of("registrationName", "onMomentumScrollBegin")).put(ScrollEventType.getJSEventName(ScrollEventType.MOMENTUM_END), MapBuilder.of("registrationName", "onMomentumScrollEnd")).build();
    }

    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(ReactScrollView reactScrollView, String str) {
        reactScrollView.setPointerEvents(PointerEvents.parsePointerEvents(str));
    }

    @ReactProp(name = "scrollEventThrottle")
    public void setScrollEventThrottle(ReactScrollView reactScrollView, int i) {
        reactScrollView.setScrollEventThrottle(i);
    }

    @ReactProp(name = "isInvertedVirtualizedList")
    public void setIsInvertedVirtualizedList(ReactScrollView reactScrollView, boolean z) {
        if (z) {
            reactScrollView.setVerticalScrollbarPosition(1);
        } else {
            reactScrollView.setVerticalScrollbarPosition(0);
        }
    }
}
