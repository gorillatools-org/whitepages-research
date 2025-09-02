package com.facebook.react.uimanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerListener;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.EventDispatcherImpl;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@ReactModule(name = "UIManager")
public class UIManagerModule extends ReactContextBaseJavaModule implements OnBatchCompleteListener, LifecycleEventListener, UIManager {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean DEBUG = PrinterHolder.getPrinter().shouldDisplayLogMessage(ReactDebugOverlayTags.UI_MANAGER);
    public static final String NAME = "UIManager";
    public static final String TAG = "UIManagerModule";
    private int mBatchId = 0;
    private final Map<String, Object> mCustomDirectEvents;
    private final EventDispatcher mEventDispatcher;
    private final List<UIManagerModuleListener> mListeners = new ArrayList();
    private final MemoryTrimCallback mMemoryTrimCallback = new MemoryTrimCallback();
    private final Map<String, Object> mModuleConstants;
    /* access modifiers changed from: private */
    public final UIImplementation mUIImplementation;
    private final CopyOnWriteArrayList<UIManagerListener> mUIManagerListeners = new CopyOnWriteArrayList<>();
    private final ViewManagerRegistry mViewManagerRegistry;

    public interface CustomEventNamesResolver {
        String resolveCustomEventName(String str);
    }

    public void markActiveTouchForTag(int i, int i2) {
    }

    public void sweepActiveTouchForTag(int i, int i2) {
    }

    public UIManagerModule(ReactApplicationContext reactApplicationContext, ViewManagerResolver viewManagerResolver, int i) {
        super(reactApplicationContext);
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(reactApplicationContext);
        EventDispatcherImpl eventDispatcherImpl = new EventDispatcherImpl(reactApplicationContext);
        this.mEventDispatcher = eventDispatcherImpl;
        this.mModuleConstants = createConstants(viewManagerResolver);
        this.mCustomDirectEvents = UIManagerModuleConstants.getDirectEventTypeConstants();
        ViewManagerRegistry viewManagerRegistry = new ViewManagerRegistry(viewManagerResolver);
        this.mViewManagerRegistry = viewManagerRegistry;
        this.mUIImplementation = new UIImplementation(reactApplicationContext, viewManagerRegistry, (EventDispatcher) eventDispatcherImpl, i);
        reactApplicationContext.addLifecycleEventListener(this);
    }

    public UIManagerModule(ReactApplicationContext reactApplicationContext, List<ViewManager> list, int i) {
        super(reactApplicationContext);
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(reactApplicationContext);
        EventDispatcherImpl eventDispatcherImpl = new EventDispatcherImpl(reactApplicationContext);
        this.mEventDispatcher = eventDispatcherImpl;
        HashMap newHashMap = MapBuilder.newHashMap();
        this.mCustomDirectEvents = newHashMap;
        this.mModuleConstants = createConstants(list, (Map<String, Object>) null, newHashMap);
        ViewManagerRegistry viewManagerRegistry = new ViewManagerRegistry(list);
        this.mViewManagerRegistry = viewManagerRegistry;
        this.mUIImplementation = new UIImplementation(reactApplicationContext, viewManagerRegistry, (EventDispatcher) eventDispatcherImpl, i);
        reactApplicationContext.addLifecycleEventListener(this);
    }

    @Deprecated
    public UIImplementation getUIImplementation() {
        return this.mUIImplementation;
    }

    public String getName() {
        return NAME;
    }

    public Map<String, Object> getConstants() {
        return this.mModuleConstants;
    }

    public void initialize() {
        getReactApplicationContext().registerComponentCallbacks(this.mMemoryTrimCallback);
        getReactApplicationContext().registerComponentCallbacks(this.mViewManagerRegistry);
        this.mEventDispatcher.registerEventEmitter(1, (RCTEventEmitter) getReactApplicationContext().getJSModule(RCTEventEmitter.class));
    }

    public void onHostResume() {
        this.mUIImplementation.onHostResume();
    }

    public void onHostPause() {
        this.mUIImplementation.onHostPause();
    }

    public void onHostDestroy() {
        this.mUIImplementation.onHostDestroy();
    }

    public void invalidate() {
        super.invalidate();
        this.mEventDispatcher.onCatalystInstanceDestroyed();
        this.mUIImplementation.onCatalystInstanceDestroyed();
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.unregisterComponentCallbacks(this.mMemoryTrimCallback);
        reactApplicationContext.unregisterComponentCallbacks(this.mViewManagerRegistry);
        YogaNodePool.get().clear();
        ViewManagerPropertyUpdater.clear();
    }

    @Deprecated
    public ViewManagerRegistry getViewManagerRegistry_DO_NOT_USE() {
        return this.mViewManagerRegistry;
    }

    private static Map<String, Object> createConstants(ViewManagerResolver viewManagerResolver) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
        SystraceMessage.beginSection(0, "CreateUIManagerConstants").arg("Lazy", (Object) Boolean.TRUE).flush();
        try {
            return UIManagerModuleConstantsHelper.createConstants(viewManagerResolver);
        } finally {
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
        }
    }

    public static Map<String, Object> createConstants(List<ViewManager> list, Map<String, Object> map, Map<String, Object> map2) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START);
        SystraceMessage.beginSection(0, "CreateUIManagerConstants").arg("Lazy", (Object) Boolean.FALSE).flush();
        try {
            return UIManagerModuleConstantsHelper.createConstants(list, map, map2);
        } finally {
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END);
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getConstantsForViewManager(String str) {
        ViewManager resolveViewManager = this.mUIImplementation.resolveViewManager(str);
        if (resolveViewManager == null) {
            return null;
        }
        return getConstantsForViewManager(resolveViewManager, this.mCustomDirectEvents);
    }

    public static WritableMap getConstantsForViewManager(ViewManager viewManager, Map<String, Object> map) {
        SystraceMessage.beginSection(0, "UIManagerModule.getConstantsForViewManager").arg("ViewManager", (Object) viewManager.getName()).arg("Lazy", (Object) Boolean.TRUE).flush();
        try {
            Map<String, Object> createConstantsForViewManager = UIManagerModuleConstantsHelper.createConstantsForViewManager(viewManager, (Map) null, (Map) null, (Map) null, map);
            if (createConstantsForViewManager != null) {
                return Arguments.makeNativeMap(createConstantsForViewManager);
            }
            SystraceMessage.endSection(0).flush();
            return null;
        } finally {
            SystraceMessage.endSection(0).flush();
        }
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public WritableMap getDefaultEventTypes() {
        return Arguments.makeNativeMap(UIManagerModuleConstantsHelper.getDefaultExportableEventTypes());
    }

    @Deprecated
    public CustomEventNamesResolver getDirectEventNamesResolver() {
        return new CustomEventNamesResolver() {
            public String resolveCustomEventName(String str) {
                return UIManagerModule.this.resolveCustomDirectEventName(str);
            }
        };
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = (java.util.Map) r1.mCustomDirectEvents.get(r2);
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String resolveCustomDirectEventName(java.lang.String r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x0014
            java.util.Map<java.lang.String, java.lang.Object> r0 = r1.mCustomDirectEvents
            java.lang.Object r0 = r0.get(r2)
            java.util.Map r0 = (java.util.Map) r0
            if (r0 == 0) goto L_0x0014
            java.lang.String r2 = "registrationName"
            java.lang.Object r2 = r0.get(r2)
            java.lang.String r2 = (java.lang.String) r2
        L_0x0014:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.UIManagerModule.resolveCustomDirectEventName(java.lang.String):java.lang.String");
    }

    public void profileNextBatch() {
        this.mUIImplementation.profileNextBatch();
    }

    public Map<String, Long> getPerformanceCounters() {
        return this.mUIImplementation.getProfiledBatchPerfCounters();
    }

    public <T extends View> int addRootView(T t) {
        return addRootView(t, (WritableMap) null);
    }

    public void synchronouslyUpdateViewOnUIThread(int i, ReadableMap readableMap) {
        this.mUIImplementation.synchronouslyUpdateViewOnUIThread(i, new ReactStylesDiffMap(readableMap));
    }

    public <T extends View> int addRootView(T t, WritableMap writableMap) {
        Systrace.beginSection(0, "UIManagerModule.addRootView");
        int nextRootViewTag = ReactRootViewTagGenerator.getNextRootViewTag();
        this.mUIImplementation.registerRootView(t, nextRootViewTag, new ThemedReactContext(getReactApplicationContext(), t.getContext(), ((ReactRoot) t).getSurfaceID(), -1));
        Systrace.endSection(0);
        return nextRootViewTag;
    }

    public <T extends View> int startSurface(T t, String str, WritableMap writableMap, int i, int i2) {
        throw new UnsupportedOperationException();
    }

    public void stopSurface(int i) {
        throw new UnsupportedOperationException();
    }

    @ReactMethod
    public void removeRootView(int i) {
        this.mUIImplementation.removeRootView(i);
    }

    public void updateNodeSize(int i, int i2, int i3) {
        getReactApplicationContext().assertOnNativeModulesQueueThread();
        this.mUIImplementation.updateNodeSize(i, i2, i3);
    }

    public void updateInsetsPadding(int i, int i2, int i3, int i4, int i5) {
        getReactApplicationContext().assertOnNativeModulesQueueThread();
        this.mUIImplementation.updateInsetsPadding(i, i2, i3, i4, i5);
    }

    public void setViewLocalData(final int i, final Object obj) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.assertOnUiQueueThread();
        reactApplicationContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactApplicationContext) {
            public void runGuarded() {
                UIManagerModule.this.mUIImplementation.setViewLocalData(i, obj);
            }
        });
    }

    @ReactMethod
    public void createView(int i, String str, int i2, ReadableMap readableMap) {
        if (DEBUG) {
            String str2 = "(UIManager.createView) tag: " + i + ", class: " + str + ", props: " + readableMap;
            FLog.d(ReactConstants.TAG, str2);
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.UI_MANAGER, str2);
        }
        this.mUIImplementation.createView(i, str, i2, readableMap);
    }

    @ReactMethod
    public void updateView(int i, String str, ReadableMap readableMap) {
        if (DEBUG) {
            String str2 = "(UIManager.updateView) tag: " + i + ", class: " + str + ", props: " + readableMap;
            FLog.d(ReactConstants.TAG, str2);
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.UI_MANAGER, str2);
        }
        this.mUIImplementation.updateView(i, str, readableMap);
    }

    @ReactMethod
    public void manageChildren(int i, ReadableArray readableArray, ReadableArray readableArray2, ReadableArray readableArray3, ReadableArray readableArray4, ReadableArray readableArray5) {
        if (DEBUG) {
            String str = "(UIManager.manageChildren) tag: " + i + ", moveFrom: " + readableArray + ", moveTo: " + readableArray2 + ", addTags: " + readableArray3 + ", atIndices: " + readableArray4 + ", removeFrom: " + readableArray5;
            FLog.d(ReactConstants.TAG, str);
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.UI_MANAGER, str);
        }
        this.mUIImplementation.manageChildren(i, readableArray, readableArray2, readableArray3, readableArray4, readableArray5);
    }

    @ReactMethod
    public void setChildren(int i, ReadableArray readableArray) {
        if (DEBUG) {
            String str = "(UIManager.setChildren) tag: " + i + ", children: " + readableArray;
            FLog.d(ReactConstants.TAG, str);
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.UI_MANAGER, str);
        }
        this.mUIImplementation.setChildren(i, readableArray);
    }

    @ReactMethod
    public void measure(int i, Callback callback) {
        this.mUIImplementation.measure(i, callback);
    }

    @ReactMethod
    public void measureInWindow(int i, Callback callback) {
        this.mUIImplementation.measureInWindow(i, callback);
    }

    @ReactMethod
    public void measureLayout(int i, int i2, Callback callback, Callback callback2) {
        this.mUIImplementation.measureLayout(i, i2, callback, callback2);
    }

    @ReactMethod
    public void findSubviewIn(int i, ReadableArray readableArray, Callback callback) {
        this.mUIImplementation.findSubviewIn(i, (float) Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(0))), (float) Math.round(PixelUtil.toPixelFromDIP(readableArray.getDouble(1))), callback);
    }

    @Deprecated
    @ReactMethod
    public void viewIsDescendantOf(int i, int i2, Callback callback) {
        this.mUIImplementation.viewIsDescendantOf(i, i2, callback);
    }

    @ReactMethod
    public void setJSResponder(int i, boolean z) {
        this.mUIImplementation.setJSResponder(i, z);
    }

    @ReactMethod
    public void clearJSResponder() {
        this.mUIImplementation.clearJSResponder();
    }

    @ReactMethod
    public void dispatchViewManagerCommand(int i, Dynamic dynamic, ReadableArray readableArray) {
        UIManager uIManager = UIManagerHelper.getUIManager(getReactApplicationContext(), ViewUtil.getUIManagerType(i));
        if (uIManager != null) {
            if (dynamic.getType() == ReadableType.Number) {
                uIManager.dispatchCommand(i, dynamic.asInt(), readableArray);
            } else if (dynamic.getType() == ReadableType.String) {
                uIManager.dispatchCommand(i, dynamic.asString(), readableArray);
            }
        }
    }

    @Deprecated
    public void dispatchCommand(int i, int i2, ReadableArray readableArray) {
        this.mUIImplementation.dispatchViewManagerCommand(i, i2, readableArray);
    }

    public void dispatchCommand(int i, String str, ReadableArray readableArray) {
        this.mUIImplementation.dispatchViewManagerCommand(i, str, readableArray);
    }

    @ReactMethod
    public void setLayoutAnimationEnabledExperimental(boolean z) {
        this.mUIImplementation.setLayoutAnimationEnabledExperimental(z);
    }

    @ReactMethod
    public void configureNextLayoutAnimation(ReadableMap readableMap, Callback callback, Callback callback2) {
        this.mUIImplementation.configureNextLayoutAnimation(readableMap, callback);
    }

    public void onBatchComplete() {
        int i = this.mBatchId;
        this.mBatchId = i + 1;
        SystraceMessage.beginSection(0, "onBatchCompleteUI").arg("BatchId", i).flush();
        for (UIManagerModuleListener willDispatchViewUpdates : this.mListeners) {
            willDispatchViewUpdates.willDispatchViewUpdates(this);
        }
        Iterator<UIManagerListener> it = this.mUIManagerListeners.iterator();
        while (it.hasNext()) {
            it.next().willDispatchViewUpdates(this);
        }
        try {
            if (this.mUIImplementation.getRootViewNum() > 0) {
                this.mUIImplementation.dispatchViewUpdates(i);
            }
        } finally {
            Systrace.endSection(0);
        }
    }

    public void setViewHierarchyUpdateDebugListener(NotThreadSafeViewHierarchyUpdateDebugListener notThreadSafeViewHierarchyUpdateDebugListener) {
        this.mUIImplementation.setViewHierarchyUpdateDebugListener(notThreadSafeViewHierarchyUpdateDebugListener);
    }

    public EventDispatcher getEventDispatcher() {
        return this.mEventDispatcher;
    }

    @ReactMethod
    public void sendAccessibilityEvent(int i, int i2) {
        int uIManagerType = ViewUtil.getUIManagerType(i);
        if (uIManagerType == 2) {
            UIManager uIManager = UIManagerHelper.getUIManager(getReactApplicationContext(), uIManagerType);
            if (uIManager != null) {
                uIManager.sendAccessibilityEvent(i, i2);
                return;
            }
            return;
        }
        this.mUIImplementation.sendAccessibilityEvent(i, i2);
    }

    public void addUIBlock(UIBlock uIBlock) {
        this.mUIImplementation.addUIBlock(uIBlock);
    }

    public void prependUIBlock(UIBlock uIBlock) {
        this.mUIImplementation.prependUIBlock(uIBlock);
    }

    @Deprecated
    public void addUIManagerListener(UIManagerModuleListener uIManagerModuleListener) {
        this.mListeners.add(uIManagerModuleListener);
    }

    @Deprecated
    public void removeUIManagerListener(UIManagerModuleListener uIManagerModuleListener) {
        this.mListeners.remove(uIManagerModuleListener);
    }

    public void addUIManagerEventListener(UIManagerListener uIManagerListener) {
        this.mUIManagerListeners.add(uIManagerListener);
    }

    public void removeUIManagerEventListener(UIManagerListener uIManagerListener) {
        this.mUIManagerListeners.remove(uIManagerListener);
    }

    @Deprecated
    public int resolveRootTagFromReactTag(int i) {
        return ViewUtil.isRootTag(i) ? i : this.mUIImplementation.resolveRootTagFromReactTag(i);
    }

    public void invalidateNodeLayout(int i) {
        ReactShadowNode resolveShadowNode = this.mUIImplementation.resolveShadowNode(i);
        if (resolveShadowNode == null) {
            FLog.w(ReactConstants.TAG, "Warning : attempted to dirty a non-existent react shadow node. reactTag=" + i);
            return;
        }
        resolveShadowNode.dirty();
        this.mUIImplementation.dispatchViewUpdates(-1);
    }

    public void updateRootLayoutSpecs(int i, int i2, int i3, int i4, int i5) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        final int i6 = i;
        final int i7 = i2;
        final int i8 = i3;
        reactApplicationContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactApplicationContext) {
            public void runGuarded() {
                UIManagerModule.this.mUIImplementation.updateRootView(i6, i7, i8);
                UIManagerModule.this.mUIImplementation.dispatchViewUpdates(-1);
            }
        });
    }

    private static class MemoryTrimCallback implements ComponentCallbacks2 {
        public void onConfigurationChanged(Configuration configuration) {
        }

        public void onLowMemory() {
        }

        private MemoryTrimCallback() {
        }

        public void onTrimMemory(int i) {
            if (i >= 60) {
                YogaNodePool.get().clear();
            }
        }
    }

    public View resolveView(int i) {
        UiThreadUtil.assertOnUiThread();
        return this.mUIImplementation.getUIViewOperationQueue().getNativeViewHierarchyManager().resolveView(i);
    }

    public void receiveEvent(int i, String str, WritableMap writableMap) {
        receiveEvent(-1, i, str, writableMap);
    }

    public void receiveEvent(int i, int i2, String str, WritableMap writableMap) {
        ((RCTEventEmitter) getReactApplicationContext().getJSModule(RCTEventEmitter.class)).receiveEvent(i2, str, writableMap);
    }
}
