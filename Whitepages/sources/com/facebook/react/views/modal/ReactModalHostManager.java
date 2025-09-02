package com.facebook.react.views.modal;

import android.content.Context;
import android.content.DialogInterface;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.viewmanagers.ModalHostViewManagerDelegate;
import com.facebook.react.viewmanagers.ModalHostViewManagerInterface;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RCTModalHostView")
public final class ReactModalHostManager extends ViewGroupManager<ReactModalHostView> implements ModalHostViewManagerInterface<ReactModalHostView> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RCTModalHostView";
    private final ViewManagerDelegate<ReactModalHostView> delegate = new ModalHostViewManagerDelegate(this);

    @ReactProp(name = "animated")
    public void setAnimated(ReactModalHostView reactModalHostView, boolean z) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
    }

    @ReactProp(name = "identifier")
    public void setIdentifier(ReactModalHostView reactModalHostView, int i) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
    }

    @ReactProp(name = "presentationStyle")
    public void setPresentationStyle(ReactModalHostView reactModalHostView, String str) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
    }

    @ReactProp(name = "supportedOrientations")
    public void setSupportedOrientations(ReactModalHostView reactModalHostView, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
    }

    @ReactProp(name = "visible")
    public void setVisible(ReactModalHostView reactModalHostView, boolean z) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ReactModalHostView createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return new ReactModalHostView(themedReactContext);
    }

    public void onDropViewInstance(ReactModalHostView reactModalHostView) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
        super.onDropViewInstance(reactModalHostView);
        reactModalHostView.onDropInstance();
    }

    @ReactProp(name = "animationType")
    public void setAnimationType(ReactModalHostView reactModalHostView, String str) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
        if (str != null) {
            reactModalHostView.setAnimationType(str);
        }
    }

    @ReactProp(name = "transparent")
    public void setTransparent(ReactModalHostView reactModalHostView, boolean z) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
        reactModalHostView.setTransparent(z);
    }

    @ReactProp(name = "statusBarTranslucent")
    public void setStatusBarTranslucent(ReactModalHostView reactModalHostView, boolean z) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
        reactModalHostView.setStatusBarTranslucent(z);
    }

    @ReactProp(name = "navigationBarTranslucent")
    public void setNavigationBarTranslucent(ReactModalHostView reactModalHostView, boolean z) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
        reactModalHostView.setNavigationBarTranslucent(z);
    }

    @ReactProp(name = "hardwareAccelerated")
    public void setHardwareAccelerated(ReactModalHostView reactModalHostView, boolean z) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
        reactModalHostView.setHardwareAccelerated(z);
    }

    public void setTestId(ReactModalHostView reactModalHostView, String str) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
        super.setTestId(reactModalHostView, str);
        reactModalHostView.setDialogRootViewGroupTestId(str);
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactModalHostView reactModalHostView) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, reactModalHostView.getId());
        if (eventDispatcherForReactTag != null) {
            reactModalHostView.setOnRequestCloseListener(new ReactModalHostManager$$ExternalSyntheticLambda0(eventDispatcherForReactTag, themedReactContext, reactModalHostView));
            reactModalHostView.setOnShowListener(new ReactModalHostManager$$ExternalSyntheticLambda1(eventDispatcherForReactTag, themedReactContext, reactModalHostView));
            reactModalHostView.setEventDispatcher(eventDispatcherForReactTag);
        }
    }

    /* access modifiers changed from: private */
    public static final void addEventEmitters$lambda$0(EventDispatcher eventDispatcher, ThemedReactContext themedReactContext, ReactModalHostView reactModalHostView, DialogInterface dialogInterface) {
        eventDispatcher.dispatchEvent(new RequestCloseEvent(UIManagerHelper.getSurfaceId((Context) themedReactContext), reactModalHostView.getId()));
    }

    /* access modifiers changed from: private */
    public static final void addEventEmitters$lambda$1(EventDispatcher eventDispatcher, ThemedReactContext themedReactContext, ReactModalHostView reactModalHostView, DialogInterface dialogInterface) {
        eventDispatcher.dispatchEvent(new ShowEvent(UIManagerHelper.getSurfaceId((Context) themedReactContext), reactModalHostView.getId()));
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = new LinkedHashMap<>();
        }
        exportedCustomDirectEventTypeConstants.put(RequestCloseEvent.EVENT_NAME, MapsKt.mapOf(TuplesKt.to("registrationName", "onRequestClose")));
        exportedCustomDirectEventTypeConstants.put(ShowEvent.EVENT_NAME, MapsKt.mapOf(TuplesKt.to("registrationName", "onShow")));
        exportedCustomDirectEventTypeConstants.put("topDismiss", MapsKt.mapOf(TuplesKt.to("registrationName", "onDismiss")));
        exportedCustomDirectEventTypeConstants.put("topOrientationChange", MapsKt.mapOf(TuplesKt.to("registrationName", "onOrientationChange")));
        return exportedCustomDirectEventTypeConstants;
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ReactModalHostView reactModalHostView) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
        super.onAfterUpdateTransaction(reactModalHostView);
        reactModalHostView.showOrUpdate();
    }

    public Object updateState(ReactModalHostView reactModalHostView, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        Intrinsics.checkNotNullParameter(reactModalHostView, "view");
        Intrinsics.checkNotNullParameter(reactStylesDiffMap, "props");
        Intrinsics.checkNotNullParameter(stateWrapper, "stateWrapper");
        reactModalHostView.setStateWrapper(stateWrapper);
        return null;
    }

    public ViewManagerDelegate<ReactModalHostView> getDelegate() {
        return this.delegate;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
