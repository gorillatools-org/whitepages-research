package com.facebook.react.internal.turbomodule.core;

import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.CxxModuleWrapper;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.internal.turbomodule.core.TurboModuleInteropUtils;
import com.facebook.react.internal.turbomodule.core.interfaces.TurboModuleRegistry;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.react.turbomodule.core.NativeMethodCallInvokerHolderImpl;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.facebook.react.turbomodule.core.interfaces.NativeMethodCallInvokerHolder;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurboModuleManager implements TurboModuleRegistry {
    private static final String TAG = "TurboModuleManager";
    private final TurboModuleManagerDelegate mDelegate;
    private final List<String> mEagerInitModuleNames;
    @DoNotStrip
    private final HybridData mHybridData;
    private final ModuleProvider mLegacyModuleProvider;
    private final Object mModuleCleanupLock = new Object();
    private boolean mModuleCleanupStarted = false;
    private final Map<String, ModuleHolder> mModuleHolders = new HashMap();
    private final ModuleProvider mTurboModuleProvider;

    private interface ModuleProvider {
        NativeModule getModule(String str);
    }

    private native HybridData initHybrid(RuntimeExecutor runtimeExecutor, CallInvokerHolderImpl callInvokerHolderImpl, NativeMethodCallInvokerHolderImpl nativeMethodCallInvokerHolderImpl, TurboModuleManagerDelegate turboModuleManagerDelegate);

    private native void installJSIBindings(boolean z);

    /* access modifiers changed from: private */
    public static /* synthetic */ NativeModule lambda$new$0(String str) {
        return null;
    }

    static {
        NativeModuleSoLoader.maybeLoadSoLibrary();
    }

    public TurboModuleManager(RuntimeExecutor runtimeExecutor, TurboModuleManagerDelegate turboModuleManagerDelegate, CallInvokerHolder callInvokerHolder, NativeMethodCallInvokerHolder nativeMethodCallInvokerHolder) {
        ModuleProvider moduleProvider;
        this.mDelegate = turboModuleManagerDelegate;
        this.mHybridData = initHybrid(runtimeExecutor, (CallInvokerHolderImpl) callInvokerHolder, (NativeMethodCallInvokerHolderImpl) nativeMethodCallInvokerHolder, turboModuleManagerDelegate);
        installJSIBindings(shouldEnableLegacyModuleInterop());
        this.mEagerInitModuleNames = turboModuleManagerDelegate == null ? Collections.emptyList() : turboModuleManagerDelegate.getEagerInitModuleNames();
        ModuleProvider turboModuleManager$$ExternalSyntheticLambda0 = new TurboModuleManager$$ExternalSyntheticLambda0();
        if (turboModuleManagerDelegate == null) {
            moduleProvider = turboModuleManager$$ExternalSyntheticLambda0;
        } else {
            moduleProvider = new TurboModuleManager$$ExternalSyntheticLambda1(turboModuleManagerDelegate);
        }
        this.mTurboModuleProvider = moduleProvider;
        if (turboModuleManagerDelegate != null && shouldEnableLegacyModuleInterop()) {
            turboModuleManager$$ExternalSyntheticLambda0 = new TurboModuleManager$$ExternalSyntheticLambda2(turboModuleManagerDelegate);
        }
        this.mLegacyModuleProvider = turboModuleManager$$ExternalSyntheticLambda0;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ NativeModule lambda$new$1(TurboModuleManagerDelegate turboModuleManagerDelegate, String str) {
        return (NativeModule) turboModuleManagerDelegate.getModule(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ NativeModule lambda$new$2(TurboModuleManagerDelegate turboModuleManagerDelegate, String str) {
        NativeModule legacyModule = turboModuleManagerDelegate.getLegacyModule(str);
        if (legacyModule == null) {
            return null;
        }
        Assertions.assertCondition(!(legacyModule instanceof TurboModule), "NativeModule \"" + str + "\" is a TurboModule");
        return legacyModule;
    }

    private boolean isTurboModule(String str) {
        TurboModuleManagerDelegate turboModuleManagerDelegate = this.mDelegate;
        return turboModuleManagerDelegate != null && turboModuleManagerDelegate.unstable_isModuleRegistered(str);
    }

    private boolean isLegacyModule(String str) {
        TurboModuleManagerDelegate turboModuleManagerDelegate = this.mDelegate;
        return turboModuleManagerDelegate != null && turboModuleManagerDelegate.unstable_isLegacyModuleRegistered(str);
    }

    private boolean shouldEnableLegacyModuleInterop() {
        TurboModuleManagerDelegate turboModuleManagerDelegate = this.mDelegate;
        return turboModuleManagerDelegate != null && turboModuleManagerDelegate.unstable_shouldEnableLegacyModuleInterop();
    }

    public List<String> getEagerInitModuleNames() {
        return this.mEagerInitModuleNames;
    }

    @DoNotStrip
    private static List<TurboModuleInteropUtils.MethodDescriptor> getMethodDescriptorsFromModule(NativeModule nativeModule) {
        return TurboModuleInteropUtils.getMethodDescriptorsFromModule(nativeModule);
    }

    @DoNotStrip
    private NativeModule getLegacyJavaModule(String str) {
        if (!isLegacyModule(str)) {
            return null;
        }
        NativeModule module = getModule(str);
        if ((module instanceof CxxModuleWrapper) || (module instanceof TurboModule)) {
            return null;
        }
        return module;
    }

    @DoNotStrip
    private CxxModuleWrapper getLegacyCxxModule(String str) {
        if (!isLegacyModule(str)) {
            return null;
        }
        NativeModule module = getModule(str);
        if (!(module instanceof CxxModuleWrapper) || (module instanceof TurboModule)) {
            return null;
        }
        return (CxxModuleWrapper) module;
    }

    @DoNotStrip
    private CxxModuleWrapper getTurboLegacyCxxModule(String str) {
        if (!isTurboModule(str)) {
            return null;
        }
        NativeModule module = getModule(str);
        if (!(module instanceof CxxModuleWrapper) || !(module instanceof TurboModule)) {
            return null;
        }
        return (CxxModuleWrapper) module;
    }

    @DoNotStrip
    private TurboModule getTurboJavaModule(String str) {
        if (!isTurboModule(str)) {
            return null;
        }
        NativeModule module = getModule(str);
        if ((module instanceof CxxModuleWrapper) || !(module instanceof TurboModule)) {
            return null;
        }
        return (TurboModule) module;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateStart(r6, r1.getModuleId());
        r0 = getOrCreateModule(r6, r1, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004e, code lost:
        if (r0 == null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0050, code lost:
        com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateEnd(r6, r1.getModuleId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0058, code lost:
        com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateFail(r6, r1.getModuleId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005f, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.bridge.NativeModule getModule(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mModuleCleanupLock
            monitor-enter(r0)
            boolean r1 = r5.mModuleCleanupStarted     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x0027
            java.lang.String r1 = "TurboModuleManager"
            java.lang.String r2 = "getModule(): Tried to get module \"%s\", but TurboModuleManager was tearing down (legacy: %b, turbo: %b)"
            boolean r3 = r5.isLegacyModule(r6)     // Catch:{ all -> 0x0025 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x0025 }
            boolean r4 = r5.isTurboModule(r6)     // Catch:{ all -> 0x0025 }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x0025 }
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r3, r4}     // Catch:{ all -> 0x0025 }
            com.facebook.common.logging.FLog.e((java.lang.String) r1, (java.lang.String) r2, (java.lang.Object[]) r6)     // Catch:{ all -> 0x0025 }
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            r6 = 0
            return r6
        L_0x0025:
            r6 = move-exception
            goto L_0x0060
        L_0x0027:
            java.util.Map<java.lang.String, com.facebook.react.internal.turbomodule.core.TurboModuleManager$ModuleHolder> r1 = r5.mModuleHolders     // Catch:{ all -> 0x0025 }
            boolean r1 = r1.containsKey(r6)     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0039
            java.util.Map<java.lang.String, com.facebook.react.internal.turbomodule.core.TurboModuleManager$ModuleHolder> r1 = r5.mModuleHolders     // Catch:{ all -> 0x0025 }
            com.facebook.react.internal.turbomodule.core.TurboModuleManager$ModuleHolder r2 = new com.facebook.react.internal.turbomodule.core.TurboModuleManager$ModuleHolder     // Catch:{ all -> 0x0025 }
            r2.<init>()     // Catch:{ all -> 0x0025 }
            r1.put(r6, r2)     // Catch:{ all -> 0x0025 }
        L_0x0039:
            java.util.Map<java.lang.String, com.facebook.react.internal.turbomodule.core.TurboModuleManager$ModuleHolder> r1 = r5.mModuleHolders     // Catch:{ all -> 0x0025 }
            java.lang.Object r1 = r1.get(r6)     // Catch:{ all -> 0x0025 }
            com.facebook.react.internal.turbomodule.core.TurboModuleManager$ModuleHolder r1 = (com.facebook.react.internal.turbomodule.core.TurboModuleManager.ModuleHolder) r1     // Catch:{ all -> 0x0025 }
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            int r0 = r1.getModuleId()
            com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateStart(r6, r0)
            r0 = 1
            com.facebook.react.bridge.NativeModule r0 = r5.getOrCreateModule(r6, r1, r0)
            if (r0 == 0) goto L_0x0058
            int r1 = r1.getModuleId()
            com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateEnd(r6, r1)
            goto L_0x005f
        L_0x0058:
            int r1 = r1.getModuleId()
            com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateFail(r6, r1)
        L_0x005f:
            return r0
        L_0x0060:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.internal.turbomodule.core.TurboModuleManager.getModule(java.lang.String):com.facebook.react.bridge.NativeModule");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        if (r7 == false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002b, code lost:
        com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateConstructStart(r5, r6.getModuleId());
        r7 = r4.mTurboModuleProvider.getModule(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0038, code lost:
        if (r7 != null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        r7 = r4.mLegacyModuleProvider.getModule(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateConstructEnd(r5, r6.getModuleId());
        com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateSetUpStart(r5, r6.getModuleId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004e, code lost:
        if (r7 == null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0050, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r6.setModule(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0054, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0055, code lost:
        r7.initialize();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005c, code lost:
        com.facebook.common.logging.FLog.e(TAG, "getOrCreateModule(): Unable to create module \"%s\" (legacy: %b, turbo: %b)", r5, java.lang.Boolean.valueOf(isLegacyModule(r5)), java.lang.Boolean.valueOf(isTurboModule(r5)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0077, code lost:
        com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateSetUpEnd(r5, r6.getModuleId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007e, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r6.endCreatingModule();
        r6.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0085, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0086, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x008a, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008f, code lost:
        if (r6.isCreatingModule() == false) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r6.wait();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0097, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0099, code lost:
        if (r1 == false) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a2, code lost:
        r5 = r6.getModule();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00a6, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a7, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.react.bridge.NativeModule getOrCreateModule(java.lang.String r5, com.facebook.react.internal.turbomodule.core.TurboModuleManager.ModuleHolder r6, boolean r7) {
        /*
            r4 = this;
            monitor-enter(r6)
            boolean r0 = r6.isDoneCreatingModule()     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x001a
            if (r7 == 0) goto L_0x0014
            int r7 = r6.getModuleId()     // Catch:{ all -> 0x0011 }
            com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateCacheHit(r5, r7)     // Catch:{ all -> 0x0011 }
            goto L_0x0014
        L_0x0011:
            r5 = move-exception
            goto L_0x00aa
        L_0x0014:
            com.facebook.react.bridge.NativeModule r5 = r6.getModule()     // Catch:{ all -> 0x0011 }
            monitor-exit(r6)     // Catch:{ all -> 0x0011 }
            return r5
        L_0x001a:
            boolean r7 = r6.isCreatingModule()     // Catch:{ all -> 0x0011 }
            r0 = 1
            r1 = 0
            if (r7 != 0) goto L_0x0027
            r6.startCreatingModule()     // Catch:{ all -> 0x0011 }
            r7 = r0
            goto L_0x0028
        L_0x0027:
            r7 = r1
        L_0x0028:
            monitor-exit(r6)     // Catch:{ all -> 0x0011 }
            if (r7 == 0) goto L_0x008a
            int r7 = r6.getModuleId()
            com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateConstructStart(r5, r7)
            com.facebook.react.internal.turbomodule.core.TurboModuleManager$ModuleProvider r7 = r4.mTurboModuleProvider
            com.facebook.react.bridge.NativeModule r7 = r7.getModule(r5)
            if (r7 != 0) goto L_0x0040
            com.facebook.react.internal.turbomodule.core.TurboModuleManager$ModuleProvider r7 = r4.mLegacyModuleProvider
            com.facebook.react.bridge.NativeModule r7 = r7.getModule(r5)
        L_0x0040:
            int r0 = r6.getModuleId()
            com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateConstructEnd(r5, r0)
            int r0 = r6.getModuleId()
            com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateSetUpStart(r5, r0)
            if (r7 == 0) goto L_0x005c
            monitor-enter(r6)
            r6.setModule(r7)     // Catch:{ all -> 0x0059 }
            monitor-exit(r6)     // Catch:{ all -> 0x0059 }
            r7.initialize()
            goto L_0x0077
        L_0x0059:
            r5 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0059 }
            throw r5
        L_0x005c:
            java.lang.String r0 = "TurboModuleManager"
            java.lang.String r1 = "getOrCreateModule(): Unable to create module \"%s\" (legacy: %b, turbo: %b)"
            boolean r2 = r4.isLegacyModule(r5)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            boolean r3 = r4.isTurboModule(r5)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            java.lang.Object[] r2 = new java.lang.Object[]{r5, r2, r3}
            com.facebook.common.logging.FLog.e((java.lang.String) r0, (java.lang.String) r1, (java.lang.Object[]) r2)
        L_0x0077:
            int r0 = r6.getModuleId()
            com.facebook.react.internal.turbomodule.core.TurboModulePerfLogger.moduleCreateSetUpEnd(r5, r0)
            monitor-enter(r6)
            r6.endCreatingModule()     // Catch:{ all -> 0x0087 }
            r6.notifyAll()     // Catch:{ all -> 0x0087 }
            monitor-exit(r6)     // Catch:{ all -> 0x0087 }
            return r7
        L_0x0087:
            r5 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0087 }
            throw r5
        L_0x008a:
            monitor-enter(r6)
        L_0x008b:
            boolean r5 = r6.isCreatingModule()     // Catch:{ all -> 0x0095 }
            if (r5 == 0) goto L_0x0099
            r6.wait()     // Catch:{ InterruptedException -> 0x0097 }
            goto L_0x008b
        L_0x0095:
            r5 = move-exception
            goto L_0x00a8
        L_0x0097:
            r1 = r0
            goto L_0x008b
        L_0x0099:
            if (r1 == 0) goto L_0x00a2
            java.lang.Thread r5 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0095 }
            r5.interrupt()     // Catch:{ all -> 0x0095 }
        L_0x00a2:
            com.facebook.react.bridge.NativeModule r5 = r6.getModule()     // Catch:{ all -> 0x0095 }
            monitor-exit(r6)     // Catch:{ all -> 0x0095 }
            return r5
        L_0x00a8:
            monitor-exit(r6)     // Catch:{ all -> 0x0095 }
            throw r5
        L_0x00aa:
            monitor-exit(r6)     // Catch:{ all -> 0x0011 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.internal.turbomodule.core.TurboModuleManager.getOrCreateModule(java.lang.String, com.facebook.react.internal.turbomodule.core.TurboModuleManager$ModuleHolder, boolean):com.facebook.react.bridge.NativeModule");
    }

    public Collection<NativeModule> getModules() {
        ArrayList<ModuleHolder> arrayList = new ArrayList<>();
        synchronized (this.mModuleCleanupLock) {
            arrayList.addAll(this.mModuleHolders.values());
        }
        ArrayList arrayList2 = new ArrayList();
        for (ModuleHolder moduleHolder : arrayList) {
            synchronized (moduleHolder) {
                try {
                    if (moduleHolder.getModule() != null) {
                        arrayList2.add(moduleHolder.getModule());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return arrayList2;
    }

    public boolean hasModule(String str) {
        ModuleHolder moduleHolder;
        synchronized (this.mModuleCleanupLock) {
            moduleHolder = this.mModuleHolders.get(str);
        }
        if (moduleHolder == null) {
            return false;
        }
        synchronized (moduleHolder) {
            try {
                if (moduleHolder.getModule() != null) {
                    return true;
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void invalidate() {
        synchronized (this.mModuleCleanupLock) {
            this.mModuleCleanupStarted = true;
        }
        for (Map.Entry next : this.mModuleHolders.entrySet()) {
            NativeModule orCreateModule = getOrCreateModule((String) next.getKey(), (ModuleHolder) next.getValue(), false);
            if (orCreateModule != null) {
                orCreateModule.invalidate();
            }
        }
        this.mModuleHolders.clear();
        this.mHybridData.resetNative();
    }

    private static class ModuleHolder {
        private static volatile int sHolderCount;
        private volatile boolean mIsDoneCreatingModule = false;
        private volatile boolean mIsTryingToCreate = false;
        private volatile NativeModule mModule = null;
        private volatile int mModuleId = sHolderCount;

        public ModuleHolder() {
            sHolderCount++;
        }

        /* access modifiers changed from: package-private */
        public int getModuleId() {
            return this.mModuleId;
        }

        /* access modifiers changed from: package-private */
        public void setModule(NativeModule nativeModule) {
            this.mModule = nativeModule;
        }

        /* access modifiers changed from: package-private */
        public NativeModule getModule() {
            return this.mModule;
        }

        /* access modifiers changed from: package-private */
        public void startCreatingModule() {
            this.mIsTryingToCreate = true;
        }

        /* access modifiers changed from: package-private */
        public void endCreatingModule() {
            this.mIsTryingToCreate = false;
            this.mIsDoneCreatingModule = true;
        }

        /* access modifiers changed from: package-private */
        public boolean isDoneCreatingModule() {
            return this.mIsDoneCreatingModule;
        }

        /* access modifiers changed from: package-private */
        public boolean isCreatingModule() {
            return this.mIsTryingToCreate;
        }
    }
}
