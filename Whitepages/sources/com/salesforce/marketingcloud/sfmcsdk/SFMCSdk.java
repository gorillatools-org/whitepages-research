package com.salesforce.marketingcloud.sfmcsdk;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.Identity;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.LogLevel;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.LogListener;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutors;
import com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutorsKt;
import com.salesforce.marketingcloud.sfmcsdk.modules.Config;
import com.salesforce.marketingcloud.sfmcsdk.modules.Module;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import com.salesforce.marketingcloud.sfmcsdk.modules.cdp.CdpModule;
import com.salesforce.marketingcloud.sfmcsdk.modules.cdp.CdpModuleConfig;
import com.salesforce.marketingcloud.sfmcsdk.modules.cdp.CdpModuleReadyListener;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModule;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleConfig;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class SFMCSdk {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Object SDK_LOCK = new Object();
    public static final String SDK_VERSION_NAME = "1.0.3";
    private static final String TAG = "~$SFMCSdk";
    /* access modifiers changed from: private */
    public static final List<WhenReadyHandler> UNIFIED_SDK_INSTANCE_REQUESTS = new ArrayList();
    /* access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    public static final BehaviorManagerImpl behaviorManager;
    /* access modifiers changed from: private */
    public static CdpModule cdpModule = new CdpModule();
    /* access modifiers changed from: private */
    public static volatile InitializationState initializationState = InitializationState.NONE;
    /* access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    public static SFMCSdk instance;
    /* access modifiers changed from: private */
    public static PushModule pushModule = new PushModule();
    private final SFMCSdkModuleConfig config;
    private final SdkExecutors executors;
    public Identity identity;
    /* access modifiers changed from: private */
    public final List<Module> modules;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ModuleIdentifier.values().length];
            iArr[ModuleIdentifier.PUSH.ordinal()] = 1;
            iArr[ModuleIdentifier.CDP.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ SFMCSdk(SFMCSdkModuleConfig sFMCSdkModuleConfig, DefaultConstructorMarker defaultConstructorMarker) {
        this(sFMCSdkModuleConfig);
    }

    public static final void configure(Context context, SFMCSdkModuleConfig sFMCSdkModuleConfig) {
        Companion.configure(context, sFMCSdkModuleConfig);
    }

    public static final void configure(Context context, SFMCSdkModuleConfig sFMCSdkModuleConfig, Function1 function1) {
        Companion.configure(context, sFMCSdkModuleConfig, function1);
    }

    public static final void requestSdk(SFMCSdkReadyListener sFMCSdkReadyListener) {
        Companion.requestSdk(sFMCSdkReadyListener);
    }

    public static final void setLogging(LogLevel logLevel, LogListener logListener) {
        Companion.setLogging(logLevel, logListener);
    }

    public static final void track(Event... eventArr) {
        Companion.track(eventArr);
    }

    private SFMCSdk(SFMCSdkModuleConfig sFMCSdkModuleConfig) {
        this.config = sFMCSdkModuleConfig;
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        Intrinsics.checkNotNullExpressionValue(newCachedThreadPool, "newCachedThreadPool()");
        this.executors = new SdkExecutors(newCachedThreadPool, (ExecutorService) null, 2, (DefaultConstructorMarker) null);
        this.modules = new ArrayList();
        for (Config moduleIdentifier : sFMCSdkModuleConfig.getConfigs$sfmcsdk_release()) {
            int i = WhenMappings.$EnumSwitchMapping$0[moduleIdentifier.getModuleIdentifier().ordinal()];
            if (i != 1) {
                if (i == 2 && this.config.getCdpModuleConfig() != null) {
                    this.modules.add(cdpModule);
                }
            } else if (this.config.getPushModuleConfig() != null) {
                this.modules.add(pushModule);
            }
        }
    }

    public final SFMCSdkModuleConfig getConfig() {
        return this.config;
    }

    public static final class Companion {

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[InitializationState.values().length];
                iArr[InitializationState.READY.ordinal()] = 1;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void configure(Context context, SFMCSdkModuleConfig sFMCSdkModuleConfig) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(sFMCSdkModuleConfig, "config");
            configure$default(this, context, sFMCSdkModuleConfig, (Function1) null, 4, (Object) null);
        }

        private Companion() {
        }

        public final PushModule getPushModule$sfmcsdk_release() {
            return SFMCSdk.pushModule;
        }

        public final void setPushModule$sfmcsdk_release(PushModule pushModule) {
            Intrinsics.checkNotNullParameter(pushModule, "<set-?>");
            SFMCSdk.pushModule = pushModule;
        }

        public final CdpModule getCdpModule$sfmcsdk_release() {
            return SFMCSdk.cdpModule;
        }

        public final void setCdpModule$sfmcsdk_release(CdpModule cdpModule) {
            Intrinsics.checkNotNullParameter(cdpModule, "<set-?>");
            SFMCSdk.cdpModule = cdpModule;
        }

        public final BehaviorManagerImpl getBehaviorManager$sfmcsdk_release() {
            return SFMCSdk.behaviorManager;
        }

        public static /* synthetic */ void configure$default(Companion companion, Context context, SFMCSdkModuleConfig sFMCSdkModuleConfig, Function1 function1, int i, Object obj) {
            if ((i & 4) != 0) {
                function1 = null;
            }
            companion.configure(context, sFMCSdkModuleConfig, function1);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void configure(android.content.Context r6, com.salesforce.marketingcloud.sfmcsdk.SFMCSdkModuleConfig r7, kotlin.jvm.functions.Function1 r8) {
            /*
                r5 = this;
                java.lang.String r0 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r0 = "config"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                java.lang.Object r0 = com.salesforce.marketingcloud.sfmcsdk.SFMCSdk.SDK_LOCK
                monitor-enter(r0)
                com.salesforce.marketingcloud.sfmcsdk.SFMCSdk r1 = com.salesforce.marketingcloud.sfmcsdk.SFMCSdk.instance     // Catch:{ all -> 0x0026 }
                if (r1 == 0) goto L_0x0051
                com.salesforce.marketingcloud.sfmcsdk.InitializationState r2 = com.salesforce.marketingcloud.sfmcsdk.SFMCSdk.initializationState     // Catch:{ all -> 0x0026 }
                com.salesforce.marketingcloud.sfmcsdk.InitializationState r3 = com.salesforce.marketingcloud.sfmcsdk.InitializationState.READY     // Catch:{ all -> 0x0026 }
                if (r2 == r3) goto L_0x0028
                com.salesforce.marketingcloud.sfmcsdk.InitializationState r2 = com.salesforce.marketingcloud.sfmcsdk.SFMCSdk.initializationState     // Catch:{ all -> 0x0026 }
                com.salesforce.marketingcloud.sfmcsdk.InitializationState r4 = com.salesforce.marketingcloud.sfmcsdk.InitializationState.INITIALIZING     // Catch:{ all -> 0x0026 }
                if (r2 != r4) goto L_0x0051
                goto L_0x0028
            L_0x0026:
                r6 = move-exception
                goto L_0x0077
            L_0x0028:
                com.salesforce.marketingcloud.sfmcsdk.SFMCSdkModuleConfig r1 = r1.getConfig()     // Catch:{ all -> 0x0026 }
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r1)     // Catch:{ all -> 0x0026 }
                if (r1 == 0) goto L_0x0051
                com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger r6 = com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger.INSTANCE     // Catch:{ all -> 0x0026 }
                java.lang.String r1 = "~$SFMCSdk"
                com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$1$1 r2 = new com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$1$1     // Catch:{ all -> 0x0026 }
                r2.<init>(r7)     // Catch:{ all -> 0x0026 }
                r6.d(r1, r2)     // Catch:{ all -> 0x0026 }
                com.salesforce.marketingcloud.sfmcsdk.InitializationState r6 = com.salesforce.marketingcloud.sfmcsdk.SFMCSdk.initializationState     // Catch:{ all -> 0x0026 }
                if (r6 != r3) goto L_0x004f
                if (r8 == 0) goto L_0x004f
                com.salesforce.marketingcloud.sfmcsdk.SFMCSdkInitializationStatus r6 = new com.salesforce.marketingcloud.sfmcsdk.SFMCSdkInitializationStatus     // Catch:{ all -> 0x0026 }
                r7 = 1
                r6.<init>(r7)     // Catch:{ all -> 0x0026 }
                r8.invoke(r6)     // Catch:{ all -> 0x0026 }
            L_0x004f:
                monitor-exit(r0)
                return
            L_0x0051:
                com.salesforce.marketingcloud.sfmcsdk.InitializationState r1 = com.salesforce.marketingcloud.sfmcsdk.InitializationState.INITIALIZING     // Catch:{ all -> 0x0026 }
                com.salesforce.marketingcloud.sfmcsdk.SFMCSdk.initializationState = r1     // Catch:{ all -> 0x0026 }
                com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger r1 = com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger.INSTANCE     // Catch:{ all -> 0x0026 }
                java.lang.String r2 = "~$SFMCSdk"
                com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$2 r3 = com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$2.INSTANCE     // Catch:{ all -> 0x0026 }
                r1.d(r2, r3)     // Catch:{ all -> 0x0026 }
                java.lang.Thread r1 = new java.lang.Thread     // Catch:{ all -> 0x0026 }
                com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$$ExternalSyntheticLambda1 r2 = new com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$$ExternalSyntheticLambda1     // Catch:{ all -> 0x0026 }
                r2.<init>(r7, r8, r6)     // Catch:{ all -> 0x0026 }
                r1.<init>(r2)     // Catch:{ all -> 0x0026 }
                r1.start()     // Catch:{ all -> 0x0026 }
                java.lang.Object r6 = com.salesforce.marketingcloud.sfmcsdk.SFMCSdk.SDK_LOCK     // Catch:{ all -> 0x0026 }
                r6.notifyAll()     // Catch:{ all -> 0x0026 }
                kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0026 }
                monitor-exit(r0)
                return
            L_0x0077:
                monitor-exit(r0)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk.Companion.configure(android.content.Context, com.salesforce.marketingcloud.sfmcsdk.SFMCSdkModuleConfig, kotlin.jvm.functions.Function1):void");
        }

        /* access modifiers changed from: private */
        /* renamed from: configure$lambda-10$lambda-9  reason: not valid java name */
        public static final void m806configure$lambda10$lambda9(SFMCSdkModuleConfig sFMCSdkModuleConfig, Function1 function1, Context context) {
            SFMCSdkModuleConfig sFMCSdkModuleConfig2 = sFMCSdkModuleConfig;
            Function1 function12 = function1;
            Context context2 = context;
            Intrinsics.checkNotNullParameter(sFMCSdkModuleConfig2, "$config");
            Intrinsics.checkNotNullParameter(context2, "$context");
            String name = Thread.currentThread().getName();
            Thread.currentThread().setName("SFMCSdk_Init");
            try {
                if (SFMCSdk.instance != null) {
                    SFMCSdk.Companion.staticTearDown();
                }
                SFMCSdk.instance = new SFMCSdk(sFMCSdkModuleConfig2, (DefaultConstructorMarker) null);
                long currentTimeMillis = System.currentTimeMillis();
                CountDownLatch countDownLatch = new CountDownLatch(sFMCSdkModuleConfig.getConfigs$sfmcsdk_release().size());
                SFMCSdkLogger.INSTANCE.d(SFMCSdk.TAG, new SFMCSdk$Companion$configure$1$3$moduleInitLatch$1$1(sFMCSdkModuleConfig2));
                for (Config config : sFMCSdkModuleConfig.getConfigs$sfmcsdk_release()) {
                    SFMCSdkLogger.INSTANCE.d(SFMCSdk.TAG, new SFMCSdk$Companion$configure$1$3$2$1(config));
                    SFMCSdkComponents sFMCSdkComponents = new SFMCSdkComponents(context, config.getModuleIdentifier().name(), config.getModuleApplicationId(), SFMCSdk.Companion.getBehaviorManager$sfmcsdk_release().initIfNecessary$sfmcsdk_release(context2), new EventManager(config.getModuleIdentifier().name()));
                    SFMCSdk access$getInstance$cp = SFMCSdk.instance;
                    if (access$getInstance$cp != null && access$getInstance$cp.identity == null) {
                        access$getInstance$cp.setIdentity(Identity.Companion.getInstance());
                    }
                    if (config instanceof PushModuleConfig) {
                        SdkExecutorsKt.namedRunnable(new SdkExecutors((ExecutorService) null, (ExecutorService) null, 3, (DefaultConstructorMarker) null).getDiskIO(), config.getModuleIdentifier().name(), new SFMCSdk$Companion$configure$1$3$2$3(context2, config, sFMCSdkComponents, countDownLatch));
                    } else if (config instanceof CdpModuleConfig) {
                        SdkExecutorsKt.namedRunnable(new SdkExecutors((ExecutorService) null, (ExecutorService) null, 3, (DefaultConstructorMarker) null).getDiskIO(), config.getModuleIdentifier().name(), new SFMCSdk$Companion$configure$1$3$2$4(context2, config, sFMCSdkComponents, countDownLatch));
                    }
                }
                boolean await = countDownLatch.await(5, TimeUnit.SECONDS);
                SFMCSdkLogger sFMCSdkLogger = SFMCSdkLogger.INSTANCE;
                sFMCSdkLogger.d(SFMCSdk.TAG, new SFMCSdk$Companion$configure$1$3$3$1(await));
                Companion companion = SFMCSdk.Companion;
                SFMCSdk.initializationState = InitializationState.READY;
                sFMCSdkLogger.d(SFMCSdk.TAG, new SFMCSdk$Companion$configure$1$3$4(currentTimeMillis));
                synchronized (SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS) {
                    for (WhenReadyHandler whenReadyHandler : SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS) {
                        try {
                            SFMCSdk access$getInstance$cp2 = SFMCSdk.instance;
                            if (access$getInstance$cp2 != null) {
                                whenReadyHandler.deliverSdk(access$getInstance$cp2);
                            }
                        } catch (Exception e) {
                            SFMCSdkLogger.INSTANCE.e(SFMCSdk.TAG, e, new SFMCSdk$Companion$configure$1$3$5$1$2(whenReadyHandler));
                        }
                    }
                    SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS.clear();
                    SFMCSdk.Companion.notifyInitializationStatusListener(function12, true);
                    Unit unit = Unit.INSTANCE;
                }
                Thread.currentThread().setName(name);
                SFMCSdkLogger.INSTANCE.d(SFMCSdk.TAG, SFMCSdk$Companion$configure$1$3$7.INSTANCE);
            } catch (Exception e2) {
                Companion companion2 = SFMCSdk.Companion;
                companion2.staticTearDown();
                SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS.clear();
                companion2.notifyInitializationStatusListener(function12, false);
                SFMCSdkLogger sFMCSdkLogger2 = SFMCSdkLogger.INSTANCE;
                sFMCSdkLogger2.e(SFMCSdk.TAG, e2, SFMCSdk$Companion$configure$1$3$6.INSTANCE);
                Thread.currentThread().setName(name);
                sFMCSdkLogger2.d(SFMCSdk.TAG, SFMCSdk$Companion$configure$1$3$7.INSTANCE);
            } catch (Throwable th) {
                Thread.currentThread().setName(name);
                SFMCSdkLogger.INSTANCE.d(SFMCSdk.TAG, SFMCSdk$Companion$configure$1$3$7.INSTANCE);
                throw th;
            }
        }

        private final void notifyInitializationStatusListener(Function1 function1, boolean z) {
            if (function1 != null) {
                try {
                    function1.invoke(new SFMCSdkInitializationStatus(z));
                } catch (Exception e) {
                    SFMCSdkLogger.INSTANCE.e(SFMCSdk.TAG, e, new SFMCSdk$Companion$notifyInitializationStatusListener$1(function1));
                }
            }
        }

        private final void staticTearDown() {
            SFMCSdk access$getInstance$cp = SFMCSdk.instance;
            if (access$getInstance$cp != null) {
                for (Module tearDown : access$getInstance$cp.modules) {
                    tearDown.tearDown();
                }
            }
            EventManager.Companion.staticTearDown$sfmcsdk_release();
            SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS.clear();
            SFMCSdk.instance = null;
            SFMCSdk.initializationState = InitializationState.NONE;
        }

        public final void requestSdk(SFMCSdkReadyListener sFMCSdkReadyListener) {
            Intrinsics.checkNotNullParameter(sFMCSdkReadyListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            WhenReadyHandler whenReadyHandler = new WhenReadyHandler(sFMCSdkReadyListener);
            synchronized (SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS) {
                if (WhenMappings.$EnumSwitchMapping$0[SFMCSdk.initializationState.ordinal()] == 1) {
                    try {
                        SFMCSdk access$getInstance$cp = SFMCSdk.instance;
                        if (access$getInstance$cp != null) {
                            whenReadyHandler.deliverSdk(access$getInstance$cp);
                            Unit unit = Unit.INSTANCE;
                        }
                    } catch (Exception e) {
                        SFMCSdkLogger.INSTANCE.e(SFMCSdk.TAG, e, new SFMCSdk$Companion$requestSdk$1$2(whenReadyHandler));
                        Unit unit2 = Unit.INSTANCE;
                    }
                } else {
                    SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS.add(whenReadyHandler);
                }
            }
        }

        public final void track(Event... eventArr) {
            Intrinsics.checkNotNullParameter(eventArr, "events");
            requestSdk(new SFMCSdk$Companion$$ExternalSyntheticLambda0(eventArr));
        }

        /* access modifiers changed from: private */
        /* renamed from: track$lambda-16  reason: not valid java name */
        public static final void m807track$lambda16(Event[] eventArr, SFMCSdk sFMCSdk) {
            Intrinsics.checkNotNullParameter(eventArr, "$events");
            Intrinsics.checkNotNullParameter(sFMCSdk, "sdk");
            sFMCSdk.internalTrack((Event[]) Arrays.copyOf(eventArr, eventArr.length));
        }

        public final void setLogging(LogLevel logLevel, LogListener logListener) {
            Intrinsics.checkNotNullParameter(logLevel, FirebaseAnalytics.Param.LEVEL);
            SFMCSdkLogger sFMCSdkLogger = SFMCSdkLogger.INSTANCE;
            sFMCSdkLogger.setLogLevel(logLevel);
            sFMCSdkLogger.setListener(logListener);
        }
    }

    static {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(newSingleThreadExecutor, "newSingleThreadExecutor()");
        behaviorManager = new BehaviorManagerImpl(newSingleThreadExecutor);
    }

    public final JSONObject getSdkState() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sfmcSDKVersion", "1.0.3");
        for (Module module : this.modules) {
            jSONObject.put(module.getName(), module.getState());
        }
        return jSONObject;
    }

    /* access modifiers changed from: private */
    public final void internalTrack(Event... eventArr) {
        try {
            SFMCSdkLogger.INSTANCE.d(TAG, new SFMCSdk$internalTrack$1$1(eventArr));
        } catch (Exception unused) {
        }
        EventManager.Companion.publish$sfmcsdk_release(this.executors, (Event[]) Arrays.copyOf(eventArr, eventArr.length));
    }

    public final void mp(PushModuleReadyListener pushModuleReadyListener) {
        Intrinsics.checkNotNullParameter(pushModuleReadyListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        pushModule.requestModule(pushModuleReadyListener);
    }

    public final void cdp(CdpModuleReadyListener cdpModuleReadyListener) {
        Intrinsics.checkNotNullParameter(cdpModuleReadyListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        cdpModule.requestModule(cdpModuleReadyListener);
    }

    public final Identity getIdentity() {
        Identity identity2 = this.identity;
        if (identity2 != null) {
            return identity2;
        }
        Intrinsics.throwUninitializedPropertyAccessException(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
        return null;
    }

    public final void setIdentity(Identity identity2) {
        Intrinsics.checkNotNullParameter(identity2, "<set-?>");
        this.identity = identity2;
    }
}
