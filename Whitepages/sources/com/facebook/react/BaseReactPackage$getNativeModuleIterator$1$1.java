package com.facebook.react;

import com.facebook.react.BaseReactPackage;
import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class BaseReactPackage$getNativeModuleIterator$1$1 implements Iterator<ModuleHolder>, KMappedMarker {
    final /* synthetic */ Iterator<Map.Entry<String, ReactModuleInfo>> $entrySetIterator;
    final /* synthetic */ ReactApplicationContext $reactContext;
    private Map.Entry<String, ReactModuleInfo> nextEntry;
    final /* synthetic */ BaseReactPackage this$0;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    BaseReactPackage$getNativeModuleIterator$1$1(Iterator<? extends Map.Entry<String, ReactModuleInfo>> it, BaseReactPackage baseReactPackage, ReactApplicationContext reactApplicationContext) {
        this.$entrySetIterator = it;
        this.this$0 = baseReactPackage;
        this.$reactContext = reactApplicationContext;
    }

    public final Map.Entry<String, ReactModuleInfo> getNextEntry() {
        return this.nextEntry;
    }

    public final void setNextEntry(Map.Entry<String, ReactModuleInfo> entry) {
        this.nextEntry = entry;
    }

    /* JADX WARNING: Removed duplicated region for block: B:2:0x0008  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void findNext() {
        /*
            r3 = this;
        L_0x0000:
            java.util.Iterator<java.util.Map$Entry<java.lang.String, com.facebook.react.module.model.ReactModuleInfo>> r0 = r3.$entrySetIterator
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L_0x0026
            java.util.Iterator<java.util.Map$Entry<java.lang.String, com.facebook.react.module.model.ReactModuleInfo>> r0 = r3.$entrySetIterator
            java.lang.Object r0 = r0.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getValue()
            com.facebook.react.module.model.ReactModuleInfo r1 = (com.facebook.react.module.model.ReactModuleInfo) r1
            boolean r2 = com.facebook.react.internal.featureflags.ReactNativeFeatureFlags.useTurboModules()
            if (r2 == 0) goto L_0x0023
            boolean r1 = r1.isTurboModule()
            if (r1 == 0) goto L_0x0023
            goto L_0x0000
        L_0x0023:
            r3.nextEntry = r0
            return
        L_0x0026:
            r0 = 0
            r3.nextEntry = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.BaseReactPackage$getNativeModuleIterator$1$1.findNext():void");
    }

    public boolean hasNext() {
        if (this.nextEntry == null) {
            findNext();
        }
        return this.nextEntry != null;
    }

    public ModuleHolder next() {
        if (this.nextEntry == null) {
            findNext();
        }
        Map.Entry<String, ReactModuleInfo> entry = this.nextEntry;
        if (entry != null) {
            findNext();
            return new ModuleHolder(entry.getValue(), new BaseReactPackage.ModuleHolderProvider(this.this$0, entry.getKey(), this.$reactContext));
        }
        throw new NoSuchElementException("ModuleHolder not found");
    }
}
