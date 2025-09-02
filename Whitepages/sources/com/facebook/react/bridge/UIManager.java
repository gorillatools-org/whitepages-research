package com.facebook.react.bridge;

import android.view.View;
import com.facebook.react.uimanager.events.EventDispatcher;

public interface UIManager extends PerformanceCounter {
    <T extends View> int addRootView(T t, WritableMap writableMap);

    void addUIManagerEventListener(UIManagerListener uIManagerListener);

    void dispatchCommand(int i, int i2, ReadableArray readableArray);

    void dispatchCommand(int i, String str, ReadableArray readableArray);

    EventDispatcher getEventDispatcher();

    void initialize();

    void invalidate();

    void markActiveTouchForTag(int i, int i2);

    void receiveEvent(int i, int i2, String str, WritableMap writableMap);

    void receiveEvent(int i, String str, WritableMap writableMap);

    void removeUIManagerEventListener(UIManagerListener uIManagerListener);

    String resolveCustomDirectEventName(String str);

    View resolveView(int i);

    void sendAccessibilityEvent(int i, int i2);

    <T extends View> int startSurface(T t, String str, WritableMap writableMap, int i, int i2);

    void stopSurface(int i);

    void sweepActiveTouchForTag(int i, int i2);

    void synchronouslyUpdateViewOnUIThread(int i, ReadableMap readableMap);

    void updateRootLayoutSpecs(int i, int i2, int i3, int i4, int i5);
}
