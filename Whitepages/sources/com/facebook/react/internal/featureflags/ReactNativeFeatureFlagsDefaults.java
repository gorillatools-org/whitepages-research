package com.facebook.react.internal.featureflags;

public class ReactNativeFeatureFlagsDefaults implements ReactNativeFeatureFlagsProvider {
    public boolean commonTestFlag() {
        return false;
    }

    public boolean completeReactInstanceCreationOnBgThreadOnAndroid() {
        return true;
    }

    public boolean disableEventLoopOnBridgeless() {
        return false;
    }

    public boolean disableMountItemReorderingAndroid() {
        return false;
    }

    public boolean enableAccumulatedUpdatesInRawPropsAndroid() {
        return false;
    }

    public boolean enableBridgelessArchitecture() {
        return false;
    }

    public boolean enableCppPropsIteratorSetter() {
        return false;
    }

    public boolean enableDeletionOfUnmountedViews() {
        return false;
    }

    public boolean enableEagerRootViewAttachment() {
        return false;
    }

    public boolean enableEventEmitterRetentionDuringGesturesOnAndroid() {
        return false;
    }

    public boolean enableFabricLogs() {
        return false;
    }

    public boolean enableFabricRenderer() {
        return false;
    }

    public boolean enableFixForViewCommandRace() {
        return false;
    }

    public boolean enableGranularShadowTreeStateReconciliation() {
        return false;
    }

    public boolean enableIOSViewClipToPaddingBox() {
        return false;
    }

    public boolean enableImagePrefetchingAndroid() {
        return false;
    }

    public boolean enableLayoutAnimationsOnAndroid() {
        return false;
    }

    public boolean enableLayoutAnimationsOnIOS() {
        return true;
    }

    public boolean enableLongTaskAPI() {
        return false;
    }

    public boolean enableNewBackgroundAndBorderDrawables() {
        return false;
    }

    public boolean enablePreciseSchedulingForPremountItemsOnAndroid() {
        return false;
    }

    public boolean enablePropsUpdateReconciliationAndroid() {
        return false;
    }

    public boolean enableReportEventPaintTime() {
        return false;
    }

    public boolean enableSynchronousStateUpdates() {
        return false;
    }

    public boolean enableUIConsistency() {
        return false;
    }

    public boolean enableViewRecycling() {
        return false;
    }

    public boolean excludeYogaFromRawProps() {
        return false;
    }

    public boolean fixDifferentiatorEmittingUpdatesWithWrongParentTag() {
        return true;
    }

    public boolean fixMappingOfEventPrioritiesBetweenFabricAndReact() {
        return false;
    }

    public boolean fixMountingCoordinatorReportedPendingTransactionsOnAndroid() {
        return false;
    }

    public boolean fuseboxEnabledRelease() {
        return false;
    }

    public boolean initEagerTurboModulesOnNativeModulesQueueAndroid() {
        return true;
    }

    public boolean lazyAnimationCallbacks() {
        return false;
    }

    public boolean loadVectorDrawablesOnImages() {
        return true;
    }

    public boolean traceTurboModulePromiseRejectionsOnAndroid() {
        return false;
    }

    public boolean useAlwaysAvailableJSErrorHandling() {
        return false;
    }

    public boolean useFabricInterop() {
        return false;
    }

    public boolean useImmediateExecutorInAndroidBridgeless() {
        return true;
    }

    public boolean useNativeViewConfigsInBridgelessMode() {
        return false;
    }

    public boolean useOptimisedViewPreallocationOnAndroid() {
        return false;
    }

    public boolean useOptimizedEventBatchingOnAndroid() {
        return false;
    }

    public boolean useRawPropsJsiValue() {
        return false;
    }

    public boolean useRuntimeShadowNodeReferenceUpdate() {
        return true;
    }

    public boolean useTurboModuleInterop() {
        return false;
    }

    public boolean useTurboModules() {
        return false;
    }
}
