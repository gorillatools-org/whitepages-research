package com.facebook.react.fabric;

import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.RuntimeScheduler;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerProvider;
import com.facebook.react.fabric.events.EventBeatManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.systrace.Systrace;

public class FabricUIManagerProviderImpl implements UIManagerProvider {
    private final ComponentFactory mComponentFactory;
    private final ViewManagerRegistry mViewManagerRegistry;

    public FabricUIManagerProviderImpl(ComponentFactory componentFactory, ViewManagerRegistry viewManagerRegistry) {
        this.mComponentFactory = componentFactory;
        this.mViewManagerRegistry = viewManagerRegistry;
    }

    public UIManager createUIManager(ReactApplicationContext reactApplicationContext) {
        Systrace.beginSection(0, "FabricUIManagerProviderImpl.create");
        EventBeatManager eventBeatManager = new EventBeatManager();
        Systrace.beginSection(0, "FabricUIManagerProviderImpl.createUIManager");
        FabricUIManager fabricUIManager = new FabricUIManager(reactApplicationContext, this.mViewManagerRegistry, eventBeatManager);
        Systrace.endSection(0);
        Systrace.beginSection(0, "FabricUIManagerProviderImpl.registerBinding");
        FabricUIManagerBinding fabricUIManagerBinding = new FabricUIManagerBinding();
        CatalystInstance catalystInstance = reactApplicationContext.getCatalystInstance();
        RuntimeExecutor runtimeExecutor = catalystInstance.getRuntimeExecutor();
        RuntimeScheduler runtimeScheduler = catalystInstance.getRuntimeScheduler();
        if (runtimeExecutor == null || runtimeScheduler == null) {
            throw new IllegalStateException("Unable to register FabricUIManager with CatalystInstance, runtimeExecutor and runtimeScheduler must not be null");
        }
        fabricUIManagerBinding.register(runtimeExecutor, runtimeScheduler, fabricUIManager, eventBeatManager, this.mComponentFactory);
        Systrace.endSection(0);
        Systrace.endSection(0);
        return fabricUIManager;
    }
}
