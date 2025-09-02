package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.infer.annotation.Assertions;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.systrace.SystraceMessage;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Provider;

@DoNotStrip
public class ModuleHolder {
    private static final AtomicInteger sInstanceKeyCounter = new AtomicInteger(1);
    private boolean mInitializable;
    private final int mInstanceKey = sInstanceKeyCounter.getAndIncrement();
    private boolean mIsCreating;
    private boolean mIsInitializing;
    private NativeModule mModule;
    private final String mName;
    private Provider mProvider;
    private final ReactModuleInfo mReactModuleInfo;

    public ModuleHolder(ReactModuleInfo reactModuleInfo, Provider provider) {
        this.mName = reactModuleInfo.name();
        this.mProvider = provider;
        this.mReactModuleInfo = reactModuleInfo;
        if (reactModuleInfo.needsEagerInit()) {
            this.mModule = create();
        }
    }

    public ModuleHolder(NativeModule nativeModule) {
        String name = nativeModule.getName();
        this.mName = name;
        this.mReactModuleInfo = new ReactModuleInfo(nativeModule.getName(), nativeModule.getClass().getSimpleName(), nativeModule.canOverrideExistingModule(), true, CxxModuleWrapper.class.isAssignableFrom(nativeModule.getClass()), ReactModuleInfo.classIsTurboModule(nativeModule.getClass()));
        this.mModule = nativeModule;
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.NATIVE_MODULE, "NativeModule init: %s", name);
    }

    /* access modifiers changed from: package-private */
    public void markInitializable() {
        boolean z;
        NativeModule nativeModule;
        synchronized (this) {
            z = true;
            try {
                this.mInitializable = true;
                if (this.mModule != null) {
                    Assertions.assertCondition(!this.mIsInitializing);
                    nativeModule = this.mModule;
                } else {
                    nativeModule = null;
                    z = false;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (z) {
            doInitialize(nativeModule);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean hasInstance() {
        return this.mModule != null;
    }

    public synchronized void destroy() {
        NativeModule nativeModule = this.mModule;
        if (nativeModule != null) {
            nativeModule.invalidate();
        }
    }

    @DoNotStrip
    public String getName() {
        return this.mName;
    }

    public boolean getCanOverrideExistingModule() {
        return this.mReactModuleInfo.canOverrideExistingModule();
    }

    public boolean isTurboModule() {
        return this.mReactModuleInfo.isTurboModule();
    }

    public boolean isCxxModule() {
        return this.mReactModuleInfo.isCxxModule();
    }

    public String getClassName() {
        return this.mReactModuleInfo.className();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0014, code lost:
        if (r0 == false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0016, code lost:
        r0 = create();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001a, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r2.mIsCreating = false;
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0020, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0021, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0025, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r0 = r2.mModule;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0028, code lost:
        if (r0 != null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        wait();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r0 = (com.facebook.react.bridge.NativeModule) com.facebook.infer.annotation.Assertions.assertNotNull(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x003a, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x003b, code lost:
        return r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0026 */
    @com.facebook.proguard.annotations.DoNotStrip
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.bridge.NativeModule getModule() {
        /*
            r2 = this;
            monitor-enter(r2)
            com.facebook.react.bridge.NativeModule r0 = r2.mModule     // Catch:{ all -> 0x0007 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r2)     // Catch:{ all -> 0x0007 }
            return r0
        L_0x0007:
            r0 = move-exception
            goto L_0x003e
        L_0x0009:
            boolean r0 = r2.mIsCreating     // Catch:{ all -> 0x0007 }
            r1 = 0
            if (r0 != 0) goto L_0x0012
            r0 = 1
            r2.mIsCreating = r0     // Catch:{ all -> 0x0007 }
            goto L_0x0013
        L_0x0012:
            r0 = r1
        L_0x0013:
            monitor-exit(r2)     // Catch:{ all -> 0x0007 }
            if (r0 == 0) goto L_0x0025
            com.facebook.react.bridge.NativeModule r0 = r2.create()
            monitor-enter(r2)
            r2.mIsCreating = r1     // Catch:{ all -> 0x0022 }
            r2.notifyAll()     // Catch:{ all -> 0x0022 }
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            return r0
        L_0x0022:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            throw r0
        L_0x0025:
            monitor-enter(r2)
        L_0x0026:
            com.facebook.react.bridge.NativeModule r0 = r2.mModule     // Catch:{ all -> 0x0032 }
            if (r0 != 0) goto L_0x0034
            boolean r1 = r2.mIsCreating     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0034
            r2.wait()     // Catch:{ InterruptedException -> 0x0026 }
            goto L_0x0026
        L_0x0032:
            r0 = move-exception
            goto L_0x003c
        L_0x0034:
            java.lang.Object r0 = com.facebook.infer.annotation.Assertions.assertNotNull(r0)     // Catch:{ all -> 0x0032 }
            com.facebook.react.bridge.NativeModule r0 = (com.facebook.react.bridge.NativeModule) r0     // Catch:{ all -> 0x0032 }
            monitor-exit(r2)     // Catch:{ all -> 0x0032 }
            return r0
        L_0x003c:
            monitor-exit(r2)     // Catch:{ all -> 0x0032 }
            throw r0
        L_0x003e:
            monitor-exit(r2)     // Catch:{ all -> 0x0007 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.bridge.ModuleHolder.getModule():com.facebook.react.bridge.NativeModule");
    }

    private NativeModule create() {
        boolean z = false;
        SoftAssertions.assertCondition(this.mModule == null, "Creating an already created module.");
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, this.mName, this.mInstanceKey);
        SystraceMessage.beginSection(0, "ModuleHolder.createModule").arg("name", (Object) this.mName).flush();
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.NATIVE_MODULE, "NativeModule init: %s", this.mName);
        try {
            NativeModule nativeModule = (NativeModule) ((Provider) Assertions.assertNotNull(this.mProvider)).get();
            this.mProvider = null;
            synchronized (this) {
                this.mModule = nativeModule;
                if (this.mInitializable && !this.mIsInitializing) {
                    z = true;
                }
            }
            if (z) {
                doInitialize(nativeModule);
            }
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END, this.mName, this.mInstanceKey);
            SystraceMessage.endSection(0).flush();
            return nativeModule;
        } catch (Throwable th) {
            try {
                FLog.e(ReactConstants.TAG, th, "Failed to create NativeModule '%s'", this.mName);
                throw th;
            } catch (Throwable th2) {
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END, this.mName, this.mInstanceKey);
                SystraceMessage.endSection(0).flush();
                throw th2;
            }
        }
    }

    private void doInitialize(NativeModule nativeModule) {
        boolean z;
        SystraceMessage.beginSection(0, "ModuleHolder.initialize").arg("name", (Object) this.mName).flush();
        ReactMarker.logMarker(ReactMarkerConstants.INITIALIZE_MODULE_START, this.mName, this.mInstanceKey);
        try {
            synchronized (this) {
                if (!this.mInitializable || this.mIsInitializing) {
                    z = false;
                } else {
                    z = true;
                    this.mIsInitializing = true;
                }
            }
            if (z) {
                nativeModule.initialize();
                synchronized (this) {
                    this.mIsInitializing = false;
                }
            }
            ReactMarker.logMarker(ReactMarkerConstants.INITIALIZE_MODULE_END, this.mName, this.mInstanceKey);
            SystraceMessage.endSection(0).flush();
        } catch (Throwable th) {
            ReactMarker.logMarker(ReactMarkerConstants.INITIALIZE_MODULE_END, this.mName, this.mInstanceKey);
            SystraceMessage.endSection(0).flush();
            throw th;
        }
    }
}
