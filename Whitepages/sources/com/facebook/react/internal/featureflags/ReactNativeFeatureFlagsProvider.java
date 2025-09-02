package com.facebook.react.internal.featureflags;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface ReactNativeFeatureFlagsProvider {
    @DoNotStrip
    boolean commonTestFlag();

    @DoNotStrip
    boolean completeReactInstanceCreationOnBgThreadOnAndroid();

    @DoNotStrip
    boolean disableEventLoopOnBridgeless();

    @DoNotStrip
    boolean disableMountItemReorderingAndroid();

    @DoNotStrip
    boolean enableAccumulatedUpdatesInRawPropsAndroid();

    @DoNotStrip
    boolean enableBridgelessArchitecture();

    @DoNotStrip
    boolean enableCppPropsIteratorSetter();

    @DoNotStrip
    boolean enableDeletionOfUnmountedViews();

    @DoNotStrip
    boolean enableEagerRootViewAttachment();

    @DoNotStrip
    boolean enableEventEmitterRetentionDuringGesturesOnAndroid();

    @DoNotStrip
    boolean enableFabricLogs();

    @DoNotStrip
    boolean enableFabricRenderer();

    @DoNotStrip
    boolean enableFixForViewCommandRace();

    @DoNotStrip
    boolean enableGranularShadowTreeStateReconciliation();

    @DoNotStrip
    boolean enableIOSViewClipToPaddingBox();

    @DoNotStrip
    boolean enableImagePrefetchingAndroid();

    @DoNotStrip
    boolean enableLayoutAnimationsOnAndroid();

    @DoNotStrip
    boolean enableLayoutAnimationsOnIOS();

    @DoNotStrip
    boolean enableLongTaskAPI();

    @DoNotStrip
    boolean enableNewBackgroundAndBorderDrawables();

    @DoNotStrip
    boolean enablePreciseSchedulingForPremountItemsOnAndroid();

    @DoNotStrip
    boolean enablePropsUpdateReconciliationAndroid();

    @DoNotStrip
    boolean enableReportEventPaintTime();

    @DoNotStrip
    boolean enableSynchronousStateUpdates();

    @DoNotStrip
    boolean enableUIConsistency();

    @DoNotStrip
    boolean enableViewRecycling();

    @DoNotStrip
    boolean excludeYogaFromRawProps();

    @DoNotStrip
    boolean fixDifferentiatorEmittingUpdatesWithWrongParentTag();

    @DoNotStrip
    boolean fixMappingOfEventPrioritiesBetweenFabricAndReact();

    @DoNotStrip
    boolean fixMountingCoordinatorReportedPendingTransactionsOnAndroid();

    @DoNotStrip
    boolean fuseboxEnabledRelease();

    @DoNotStrip
    boolean initEagerTurboModulesOnNativeModulesQueueAndroid();

    @DoNotStrip
    boolean lazyAnimationCallbacks();

    @DoNotStrip
    boolean loadVectorDrawablesOnImages();

    @DoNotStrip
    boolean traceTurboModulePromiseRejectionsOnAndroid();

    @DoNotStrip
    boolean useAlwaysAvailableJSErrorHandling();

    @DoNotStrip
    boolean useFabricInterop();

    @DoNotStrip
    boolean useImmediateExecutorInAndroidBridgeless();

    @DoNotStrip
    boolean useNativeViewConfigsInBridgelessMode();

    @DoNotStrip
    boolean useOptimisedViewPreallocationOnAndroid();

    @DoNotStrip
    boolean useOptimizedEventBatchingOnAndroid();

    @DoNotStrip
    boolean useRawPropsJsiValue();

    @DoNotStrip
    boolean useRuntimeShadowNodeReferenceUpdate();

    @DoNotStrip
    boolean useTurboModuleInterop();

    @DoNotStrip
    boolean useTurboModules();
}
