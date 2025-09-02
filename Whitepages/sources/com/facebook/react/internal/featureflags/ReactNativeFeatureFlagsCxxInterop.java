package com.facebook.react.internal.featureflags;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@DoNotStrip
public final class ReactNativeFeatureFlagsCxxInterop {
    public static final ReactNativeFeatureFlagsCxxInterop INSTANCE = new ReactNativeFeatureFlagsCxxInterop();

    @DoNotStrip
    public static final native boolean commonTestFlag();

    @DoNotStrip
    public static final native boolean completeReactInstanceCreationOnBgThreadOnAndroid();

    @DoNotStrip
    public static final native String dangerouslyForceOverride(Object obj);

    @DoNotStrip
    public static final native void dangerouslyReset();

    @DoNotStrip
    public static final native boolean disableEventLoopOnBridgeless();

    @DoNotStrip
    public static final native boolean disableMountItemReorderingAndroid();

    @DoNotStrip
    public static final native boolean enableAccumulatedUpdatesInRawPropsAndroid();

    @DoNotStrip
    public static final native boolean enableBridgelessArchitecture();

    @DoNotStrip
    public static final native boolean enableCppPropsIteratorSetter();

    @DoNotStrip
    public static final native boolean enableDeletionOfUnmountedViews();

    @DoNotStrip
    public static final native boolean enableEagerRootViewAttachment();

    @DoNotStrip
    public static final native boolean enableEventEmitterRetentionDuringGesturesOnAndroid();

    @DoNotStrip
    public static final native boolean enableFabricLogs();

    @DoNotStrip
    public static final native boolean enableFabricRenderer();

    @DoNotStrip
    public static final native boolean enableFixForViewCommandRace();

    @DoNotStrip
    public static final native boolean enableGranularShadowTreeStateReconciliation();

    @DoNotStrip
    public static final native boolean enableIOSViewClipToPaddingBox();

    @DoNotStrip
    public static final native boolean enableImagePrefetchingAndroid();

    @DoNotStrip
    public static final native boolean enableLayoutAnimationsOnAndroid();

    @DoNotStrip
    public static final native boolean enableLayoutAnimationsOnIOS();

    @DoNotStrip
    public static final native boolean enableLongTaskAPI();

    @DoNotStrip
    public static final native boolean enableNewBackgroundAndBorderDrawables();

    @DoNotStrip
    public static final native boolean enablePreciseSchedulingForPremountItemsOnAndroid();

    @DoNotStrip
    public static final native boolean enablePropsUpdateReconciliationAndroid();

    @DoNotStrip
    public static final native boolean enableReportEventPaintTime();

    @DoNotStrip
    public static final native boolean enableSynchronousStateUpdates();

    @DoNotStrip
    public static final native boolean enableUIConsistency();

    @DoNotStrip
    public static final native boolean enableViewRecycling();

    @DoNotStrip
    public static final native boolean excludeYogaFromRawProps();

    @DoNotStrip
    public static final native boolean fixDifferentiatorEmittingUpdatesWithWrongParentTag();

    @DoNotStrip
    public static final native boolean fixMappingOfEventPrioritiesBetweenFabricAndReact();

    @DoNotStrip
    public static final native boolean fixMountingCoordinatorReportedPendingTransactionsOnAndroid();

    @DoNotStrip
    public static final native boolean fuseboxEnabledRelease();

    @DoNotStrip
    public static final native boolean initEagerTurboModulesOnNativeModulesQueueAndroid();

    @DoNotStrip
    public static final native boolean lazyAnimationCallbacks();

    @DoNotStrip
    public static final native boolean loadVectorDrawablesOnImages();

    @DoNotStrip
    public static final native void override(Object obj);

    @DoNotStrip
    public static final native boolean traceTurboModulePromiseRejectionsOnAndroid();

    @DoNotStrip
    public static final native boolean useAlwaysAvailableJSErrorHandling();

    @DoNotStrip
    public static final native boolean useFabricInterop();

    @DoNotStrip
    public static final native boolean useImmediateExecutorInAndroidBridgeless();

    @DoNotStrip
    public static final native boolean useNativeViewConfigsInBridgelessMode();

    @DoNotStrip
    public static final native boolean useOptimisedViewPreallocationOnAndroid();

    @DoNotStrip
    public static final native boolean useOptimizedEventBatchingOnAndroid();

    @DoNotStrip
    public static final native boolean useRawPropsJsiValue();

    @DoNotStrip
    public static final native boolean useRuntimeShadowNodeReferenceUpdate();

    @DoNotStrip
    public static final native boolean useTurboModuleInterop();

    @DoNotStrip
    public static final native boolean useTurboModules();

    private ReactNativeFeatureFlagsCxxInterop() {
    }

    static {
        SoLoader.loadLibrary("react_featureflagsjni");
    }
}
