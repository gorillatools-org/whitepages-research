package com.facebook.react.internal.featureflags;

import kotlin.jvm.internal.Intrinsics;

public final class ReactNativeFeatureFlagsCxxAccessor implements ReactNativeFeatureFlagsAccessor {
    private Boolean commonTestFlagCache;
    private Boolean completeReactInstanceCreationOnBgThreadOnAndroidCache;
    private Boolean disableEventLoopOnBridgelessCache;
    private Boolean disableMountItemReorderingAndroidCache;
    private Boolean enableAccumulatedUpdatesInRawPropsAndroidCache;
    private Boolean enableBridgelessArchitectureCache;
    private Boolean enableCppPropsIteratorSetterCache;
    private Boolean enableDeletionOfUnmountedViewsCache;
    private Boolean enableEagerRootViewAttachmentCache;
    private Boolean enableEventEmitterRetentionDuringGesturesOnAndroidCache;
    private Boolean enableFabricLogsCache;
    private Boolean enableFabricRendererCache;
    private Boolean enableFixForViewCommandRaceCache;
    private Boolean enableGranularShadowTreeStateReconciliationCache;
    private Boolean enableIOSViewClipToPaddingBoxCache;
    private Boolean enableImagePrefetchingAndroidCache;
    private Boolean enableLayoutAnimationsOnAndroidCache;
    private Boolean enableLayoutAnimationsOnIOSCache;
    private Boolean enableLongTaskAPICache;
    private Boolean enableNewBackgroundAndBorderDrawablesCache;
    private Boolean enablePreciseSchedulingForPremountItemsOnAndroidCache;
    private Boolean enablePropsUpdateReconciliationAndroidCache;
    private Boolean enableReportEventPaintTimeCache;
    private Boolean enableSynchronousStateUpdatesCache;
    private Boolean enableUIConsistencyCache;
    private Boolean enableViewRecyclingCache;
    private Boolean excludeYogaFromRawPropsCache;
    private Boolean fixDifferentiatorEmittingUpdatesWithWrongParentTagCache;
    private Boolean fixMappingOfEventPrioritiesBetweenFabricAndReactCache;
    private Boolean fixMountingCoordinatorReportedPendingTransactionsOnAndroidCache;
    private Boolean fuseboxEnabledReleaseCache;
    private Boolean initEagerTurboModulesOnNativeModulesQueueAndroidCache;
    private Boolean lazyAnimationCallbacksCache;
    private Boolean loadVectorDrawablesOnImagesCache;
    private Boolean traceTurboModulePromiseRejectionsOnAndroidCache;
    private Boolean useAlwaysAvailableJSErrorHandlingCache;
    private Boolean useFabricInteropCache;
    private Boolean useImmediateExecutorInAndroidBridgelessCache;
    private Boolean useNativeViewConfigsInBridgelessModeCache;
    private Boolean useOptimisedViewPreallocationOnAndroidCache;
    private Boolean useOptimizedEventBatchingOnAndroidCache;
    private Boolean useRawPropsJsiValueCache;
    private Boolean useRuntimeShadowNodeReferenceUpdateCache;
    private Boolean useTurboModuleInteropCache;
    private Boolean useTurboModulesCache;

    public boolean commonTestFlag() {
        Boolean bool = this.commonTestFlagCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.commonTestFlag());
            this.commonTestFlagCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean completeReactInstanceCreationOnBgThreadOnAndroid() {
        Boolean bool = this.completeReactInstanceCreationOnBgThreadOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.completeReactInstanceCreationOnBgThreadOnAndroid());
            this.completeReactInstanceCreationOnBgThreadOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean disableEventLoopOnBridgeless() {
        Boolean bool = this.disableEventLoopOnBridgelessCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.disableEventLoopOnBridgeless());
            this.disableEventLoopOnBridgelessCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean disableMountItemReorderingAndroid() {
        Boolean bool = this.disableMountItemReorderingAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.disableMountItemReorderingAndroid());
            this.disableMountItemReorderingAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableAccumulatedUpdatesInRawPropsAndroid() {
        Boolean bool = this.enableAccumulatedUpdatesInRawPropsAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableAccumulatedUpdatesInRawPropsAndroid());
            this.enableAccumulatedUpdatesInRawPropsAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableBridgelessArchitecture() {
        Boolean bool = this.enableBridgelessArchitectureCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableBridgelessArchitecture());
            this.enableBridgelessArchitectureCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableCppPropsIteratorSetter() {
        Boolean bool = this.enableCppPropsIteratorSetterCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableCppPropsIteratorSetter());
            this.enableCppPropsIteratorSetterCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableDeletionOfUnmountedViews() {
        Boolean bool = this.enableDeletionOfUnmountedViewsCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableDeletionOfUnmountedViews());
            this.enableDeletionOfUnmountedViewsCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableEagerRootViewAttachment() {
        Boolean bool = this.enableEagerRootViewAttachmentCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableEagerRootViewAttachment());
            this.enableEagerRootViewAttachmentCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableEventEmitterRetentionDuringGesturesOnAndroid() {
        Boolean bool = this.enableEventEmitterRetentionDuringGesturesOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableEventEmitterRetentionDuringGesturesOnAndroid());
            this.enableEventEmitterRetentionDuringGesturesOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableFabricLogs() {
        Boolean bool = this.enableFabricLogsCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableFabricLogs());
            this.enableFabricLogsCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableFabricRenderer() {
        Boolean bool = this.enableFabricRendererCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableFabricRenderer());
            this.enableFabricRendererCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableFixForViewCommandRace() {
        Boolean bool = this.enableFixForViewCommandRaceCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableFixForViewCommandRace());
            this.enableFixForViewCommandRaceCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableGranularShadowTreeStateReconciliation() {
        Boolean bool = this.enableGranularShadowTreeStateReconciliationCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableGranularShadowTreeStateReconciliation());
            this.enableGranularShadowTreeStateReconciliationCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableIOSViewClipToPaddingBox() {
        Boolean bool = this.enableIOSViewClipToPaddingBoxCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableIOSViewClipToPaddingBox());
            this.enableIOSViewClipToPaddingBoxCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableImagePrefetchingAndroid() {
        Boolean bool = this.enableImagePrefetchingAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableImagePrefetchingAndroid());
            this.enableImagePrefetchingAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableLayoutAnimationsOnAndroid() {
        Boolean bool = this.enableLayoutAnimationsOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableLayoutAnimationsOnAndroid());
            this.enableLayoutAnimationsOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableLayoutAnimationsOnIOS() {
        Boolean bool = this.enableLayoutAnimationsOnIOSCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableLayoutAnimationsOnIOS());
            this.enableLayoutAnimationsOnIOSCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableLongTaskAPI() {
        Boolean bool = this.enableLongTaskAPICache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableLongTaskAPI());
            this.enableLongTaskAPICache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableNewBackgroundAndBorderDrawables() {
        Boolean bool = this.enableNewBackgroundAndBorderDrawablesCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableNewBackgroundAndBorderDrawables());
            this.enableNewBackgroundAndBorderDrawablesCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enablePreciseSchedulingForPremountItemsOnAndroid() {
        Boolean bool = this.enablePreciseSchedulingForPremountItemsOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enablePreciseSchedulingForPremountItemsOnAndroid());
            this.enablePreciseSchedulingForPremountItemsOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enablePropsUpdateReconciliationAndroid() {
        Boolean bool = this.enablePropsUpdateReconciliationAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enablePropsUpdateReconciliationAndroid());
            this.enablePropsUpdateReconciliationAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableReportEventPaintTime() {
        Boolean bool = this.enableReportEventPaintTimeCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableReportEventPaintTime());
            this.enableReportEventPaintTimeCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableSynchronousStateUpdates() {
        Boolean bool = this.enableSynchronousStateUpdatesCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableSynchronousStateUpdates());
            this.enableSynchronousStateUpdatesCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableUIConsistency() {
        Boolean bool = this.enableUIConsistencyCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableUIConsistency());
            this.enableUIConsistencyCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableViewRecycling() {
        Boolean bool = this.enableViewRecyclingCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.enableViewRecycling());
            this.enableViewRecyclingCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean excludeYogaFromRawProps() {
        Boolean bool = this.excludeYogaFromRawPropsCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.excludeYogaFromRawProps());
            this.excludeYogaFromRawPropsCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean fixDifferentiatorEmittingUpdatesWithWrongParentTag() {
        Boolean bool = this.fixDifferentiatorEmittingUpdatesWithWrongParentTagCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.fixDifferentiatorEmittingUpdatesWithWrongParentTag());
            this.fixDifferentiatorEmittingUpdatesWithWrongParentTagCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean fixMappingOfEventPrioritiesBetweenFabricAndReact() {
        Boolean bool = this.fixMappingOfEventPrioritiesBetweenFabricAndReactCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.fixMappingOfEventPrioritiesBetweenFabricAndReact());
            this.fixMappingOfEventPrioritiesBetweenFabricAndReactCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean fixMountingCoordinatorReportedPendingTransactionsOnAndroid() {
        Boolean bool = this.fixMountingCoordinatorReportedPendingTransactionsOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.fixMountingCoordinatorReportedPendingTransactionsOnAndroid());
            this.fixMountingCoordinatorReportedPendingTransactionsOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean fuseboxEnabledRelease() {
        Boolean bool = this.fuseboxEnabledReleaseCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.fuseboxEnabledRelease());
            this.fuseboxEnabledReleaseCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean initEagerTurboModulesOnNativeModulesQueueAndroid() {
        Boolean bool = this.initEagerTurboModulesOnNativeModulesQueueAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.initEagerTurboModulesOnNativeModulesQueueAndroid());
            this.initEagerTurboModulesOnNativeModulesQueueAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean lazyAnimationCallbacks() {
        Boolean bool = this.lazyAnimationCallbacksCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.lazyAnimationCallbacks());
            this.lazyAnimationCallbacksCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean loadVectorDrawablesOnImages() {
        Boolean bool = this.loadVectorDrawablesOnImagesCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.loadVectorDrawablesOnImages());
            this.loadVectorDrawablesOnImagesCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean traceTurboModulePromiseRejectionsOnAndroid() {
        Boolean bool = this.traceTurboModulePromiseRejectionsOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.traceTurboModulePromiseRejectionsOnAndroid());
            this.traceTurboModulePromiseRejectionsOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useAlwaysAvailableJSErrorHandling() {
        Boolean bool = this.useAlwaysAvailableJSErrorHandlingCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.useAlwaysAvailableJSErrorHandling());
            this.useAlwaysAvailableJSErrorHandlingCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useFabricInterop() {
        Boolean bool = this.useFabricInteropCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.useFabricInterop());
            this.useFabricInteropCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useImmediateExecutorInAndroidBridgeless() {
        Boolean bool = this.useImmediateExecutorInAndroidBridgelessCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.useImmediateExecutorInAndroidBridgeless());
            this.useImmediateExecutorInAndroidBridgelessCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useNativeViewConfigsInBridgelessMode() {
        Boolean bool = this.useNativeViewConfigsInBridgelessModeCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.useNativeViewConfigsInBridgelessMode());
            this.useNativeViewConfigsInBridgelessModeCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useOptimisedViewPreallocationOnAndroid() {
        Boolean bool = this.useOptimisedViewPreallocationOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.useOptimisedViewPreallocationOnAndroid());
            this.useOptimisedViewPreallocationOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useOptimizedEventBatchingOnAndroid() {
        Boolean bool = this.useOptimizedEventBatchingOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.useOptimizedEventBatchingOnAndroid());
            this.useOptimizedEventBatchingOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useRawPropsJsiValue() {
        Boolean bool = this.useRawPropsJsiValueCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.useRawPropsJsiValue());
            this.useRawPropsJsiValueCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useRuntimeShadowNodeReferenceUpdate() {
        Boolean bool = this.useRuntimeShadowNodeReferenceUpdateCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.useRuntimeShadowNodeReferenceUpdate());
            this.useRuntimeShadowNodeReferenceUpdateCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useTurboModuleInterop() {
        Boolean bool = this.useTurboModuleInteropCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.useTurboModuleInterop());
            this.useTurboModuleInteropCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useTurboModules() {
        Boolean bool = this.useTurboModulesCache;
        if (bool == null) {
            bool = Boolean.valueOf(ReactNativeFeatureFlagsCxxInterop.useTurboModules());
            this.useTurboModulesCache = bool;
        }
        return bool.booleanValue();
    }

    public void override(ReactNativeFeatureFlagsProvider reactNativeFeatureFlagsProvider) {
        Intrinsics.checkNotNullParameter(reactNativeFeatureFlagsProvider, "provider");
        ReactNativeFeatureFlagsCxxInterop.override(reactNativeFeatureFlagsProvider);
    }

    public void dangerouslyReset() {
        ReactNativeFeatureFlagsCxxInterop.dangerouslyReset();
    }

    public String dangerouslyForceOverride(ReactNativeFeatureFlagsProvider reactNativeFeatureFlagsProvider) {
        Intrinsics.checkNotNullParameter(reactNativeFeatureFlagsProvider, "provider");
        return ReactNativeFeatureFlagsCxxInterop.dangerouslyForceOverride(reactNativeFeatureFlagsProvider);
    }
}
