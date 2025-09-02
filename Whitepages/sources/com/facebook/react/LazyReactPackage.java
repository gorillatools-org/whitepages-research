package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.systrace.SystraceMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Deprecated(since = "This class is deprecated, please use BaseReactPackage instead.")
public abstract class LazyReactPackage implements ReactPackage {
    /* access modifiers changed from: protected */
    public abstract List<ModuleSpec> getNativeModules(ReactApplicationContext reactApplicationContext);

    public abstract ReactModuleInfoProvider getReactModuleInfoProvider();

    /* access modifiers changed from: package-private */
    public Iterable<ModuleHolder> getNativeModuleIterator(ReactApplicationContext reactApplicationContext) {
        return new LazyReactPackage$$ExternalSyntheticLambda0(this, getNativeModules(reactApplicationContext), getReactModuleInfoProvider().getReactModuleInfos());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Iterator lambda$getNativeModuleIterator$0(final List list, final Map map) {
        return new Iterator<ModuleHolder>() {
            int position = 0;

            /* JADX INFO: finally extract failed */
            public ModuleHolder next() {
                List list = list;
                int i = this.position;
                this.position = i + 1;
                ModuleSpec moduleSpec = (ModuleSpec) list.get(i);
                String name = moduleSpec.getName();
                ReactModuleInfo reactModuleInfo = (ReactModuleInfo) map.get(name);
                if (reactModuleInfo != null) {
                    return new ModuleHolder(reactModuleInfo, moduleSpec.getProvider());
                }
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, name);
                try {
                    NativeModule nativeModule = (NativeModule) moduleSpec.getProvider().get();
                    ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                    return new ModuleHolder(nativeModule);
                } catch (Throwable th) {
                    ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                    throw th;
                }
            }

            public boolean hasNext() {
                return this.position < list.size();
            }

            public void remove() {
                throw new UnsupportedOperationException("Cannot remove native modules from the list");
            }
        };
    }

    /* JADX INFO: finally extract failed */
    public final List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        for (ModuleSpec next : getNativeModules(reactApplicationContext)) {
            SystraceMessage.beginSection(0, "createNativeModule").flush();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, next.getName());
            try {
                NativeModule nativeModule = (NativeModule) next.getProvider().get();
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                SystraceMessage.endSection(0).flush();
                arrayList.add(nativeModule);
            } catch (Throwable th) {
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                SystraceMessage.endSection(0).flush();
                throw th;
            }
        }
        return arrayList;
    }

    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ModuleSpec> viewManagers = getViewManagers(reactApplicationContext);
        if (viewManagers == null || viewManagers.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (ModuleSpec provider : viewManagers) {
            arrayList.add((ViewManager) provider.getProvider().get());
        }
        return arrayList;
    }
}
