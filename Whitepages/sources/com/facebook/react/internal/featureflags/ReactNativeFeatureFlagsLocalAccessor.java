package com.facebook.react.internal.featureflags;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class ReactNativeFeatureFlagsLocalAccessor implements ReactNativeFeatureFlagsAccessor {
    private final Set<String> accessedFeatureFlags = new LinkedHashSet();
    private Boolean commonTestFlagCache;
    private Boolean completeReactInstanceCreationOnBgThreadOnAndroidCache;
    private ReactNativeFeatureFlagsProvider currentProvider = new ReactNativeFeatureFlagsDefaults();
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

    /* access modifiers changed from: private */
    public static final CharSequence getAccessedFeatureFlags$lambda$1(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        return str;
    }

    /* access modifiers changed from: private */
    public static final CharSequence override$lambda$0(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        return str;
    }

    public void dangerouslyReset() {
    }

    public boolean commonTestFlag() {
        Boolean bool = this.commonTestFlagCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.commonTestFlag());
            this.accessedFeatureFlags.add("commonTestFlag");
            this.commonTestFlagCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean completeReactInstanceCreationOnBgThreadOnAndroid() {
        Boolean bool = this.completeReactInstanceCreationOnBgThreadOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.completeReactInstanceCreationOnBgThreadOnAndroid());
            this.accessedFeatureFlags.add("completeReactInstanceCreationOnBgThreadOnAndroid");
            this.completeReactInstanceCreationOnBgThreadOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean disableEventLoopOnBridgeless() {
        Boolean bool = this.disableEventLoopOnBridgelessCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.disableEventLoopOnBridgeless());
            this.accessedFeatureFlags.add("disableEventLoopOnBridgeless");
            this.disableEventLoopOnBridgelessCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean disableMountItemReorderingAndroid() {
        Boolean bool = this.disableMountItemReorderingAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.disableMountItemReorderingAndroid());
            this.accessedFeatureFlags.add("disableMountItemReorderingAndroid");
            this.disableMountItemReorderingAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableAccumulatedUpdatesInRawPropsAndroid() {
        Boolean bool = this.enableAccumulatedUpdatesInRawPropsAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableAccumulatedUpdatesInRawPropsAndroid());
            this.accessedFeatureFlags.add("enableAccumulatedUpdatesInRawPropsAndroid");
            this.enableAccumulatedUpdatesInRawPropsAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableBridgelessArchitecture() {
        Boolean bool = this.enableBridgelessArchitectureCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableBridgelessArchitecture());
            this.accessedFeatureFlags.add("enableBridgelessArchitecture");
            this.enableBridgelessArchitectureCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableCppPropsIteratorSetter() {
        Boolean bool = this.enableCppPropsIteratorSetterCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableCppPropsIteratorSetter());
            this.accessedFeatureFlags.add("enableCppPropsIteratorSetter");
            this.enableCppPropsIteratorSetterCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableDeletionOfUnmountedViews() {
        Boolean bool = this.enableDeletionOfUnmountedViewsCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableDeletionOfUnmountedViews());
            this.accessedFeatureFlags.add("enableDeletionOfUnmountedViews");
            this.enableDeletionOfUnmountedViewsCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableEagerRootViewAttachment() {
        Boolean bool = this.enableEagerRootViewAttachmentCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableEagerRootViewAttachment());
            this.accessedFeatureFlags.add("enableEagerRootViewAttachment");
            this.enableEagerRootViewAttachmentCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableEventEmitterRetentionDuringGesturesOnAndroid() {
        Boolean bool = this.enableEventEmitterRetentionDuringGesturesOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableEventEmitterRetentionDuringGesturesOnAndroid());
            this.accessedFeatureFlags.add("enableEventEmitterRetentionDuringGesturesOnAndroid");
            this.enableEventEmitterRetentionDuringGesturesOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableFabricLogs() {
        Boolean bool = this.enableFabricLogsCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableFabricLogs());
            this.accessedFeatureFlags.add("enableFabricLogs");
            this.enableFabricLogsCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableFabricRenderer() {
        Boolean bool = this.enableFabricRendererCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableFabricRenderer());
            this.accessedFeatureFlags.add("enableFabricRenderer");
            this.enableFabricRendererCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableFixForViewCommandRace() {
        Boolean bool = this.enableFixForViewCommandRaceCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableFixForViewCommandRace());
            this.accessedFeatureFlags.add("enableFixForViewCommandRace");
            this.enableFixForViewCommandRaceCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableGranularShadowTreeStateReconciliation() {
        Boolean bool = this.enableGranularShadowTreeStateReconciliationCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableGranularShadowTreeStateReconciliation());
            this.accessedFeatureFlags.add("enableGranularShadowTreeStateReconciliation");
            this.enableGranularShadowTreeStateReconciliationCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableIOSViewClipToPaddingBox() {
        Boolean bool = this.enableIOSViewClipToPaddingBoxCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableIOSViewClipToPaddingBox());
            this.accessedFeatureFlags.add("enableIOSViewClipToPaddingBox");
            this.enableIOSViewClipToPaddingBoxCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableImagePrefetchingAndroid() {
        Boolean bool = this.enableImagePrefetchingAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableImagePrefetchingAndroid());
            this.accessedFeatureFlags.add("enableImagePrefetchingAndroid");
            this.enableImagePrefetchingAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableLayoutAnimationsOnAndroid() {
        Boolean bool = this.enableLayoutAnimationsOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableLayoutAnimationsOnAndroid());
            this.accessedFeatureFlags.add("enableLayoutAnimationsOnAndroid");
            this.enableLayoutAnimationsOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableLayoutAnimationsOnIOS() {
        Boolean bool = this.enableLayoutAnimationsOnIOSCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableLayoutAnimationsOnIOS());
            this.accessedFeatureFlags.add("enableLayoutAnimationsOnIOS");
            this.enableLayoutAnimationsOnIOSCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableLongTaskAPI() {
        Boolean bool = this.enableLongTaskAPICache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableLongTaskAPI());
            this.accessedFeatureFlags.add("enableLongTaskAPI");
            this.enableLongTaskAPICache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableNewBackgroundAndBorderDrawables() {
        Boolean bool = this.enableNewBackgroundAndBorderDrawablesCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableNewBackgroundAndBorderDrawables());
            this.accessedFeatureFlags.add("enableNewBackgroundAndBorderDrawables");
            this.enableNewBackgroundAndBorderDrawablesCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enablePreciseSchedulingForPremountItemsOnAndroid() {
        Boolean bool = this.enablePreciseSchedulingForPremountItemsOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enablePreciseSchedulingForPremountItemsOnAndroid());
            this.accessedFeatureFlags.add("enablePreciseSchedulingForPremountItemsOnAndroid");
            this.enablePreciseSchedulingForPremountItemsOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enablePropsUpdateReconciliationAndroid() {
        Boolean bool = this.enablePropsUpdateReconciliationAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enablePropsUpdateReconciliationAndroid());
            this.accessedFeatureFlags.add("enablePropsUpdateReconciliationAndroid");
            this.enablePropsUpdateReconciliationAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableReportEventPaintTime() {
        Boolean bool = this.enableReportEventPaintTimeCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableReportEventPaintTime());
            this.accessedFeatureFlags.add("enableReportEventPaintTime");
            this.enableReportEventPaintTimeCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableSynchronousStateUpdates() {
        Boolean bool = this.enableSynchronousStateUpdatesCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableSynchronousStateUpdates());
            this.accessedFeatureFlags.add("enableSynchronousStateUpdates");
            this.enableSynchronousStateUpdatesCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableUIConsistency() {
        Boolean bool = this.enableUIConsistencyCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableUIConsistency());
            this.accessedFeatureFlags.add("enableUIConsistency");
            this.enableUIConsistencyCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean enableViewRecycling() {
        Boolean bool = this.enableViewRecyclingCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.enableViewRecycling());
            this.accessedFeatureFlags.add("enableViewRecycling");
            this.enableViewRecyclingCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean excludeYogaFromRawProps() {
        Boolean bool = this.excludeYogaFromRawPropsCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.excludeYogaFromRawProps());
            this.accessedFeatureFlags.add("excludeYogaFromRawProps");
            this.excludeYogaFromRawPropsCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean fixDifferentiatorEmittingUpdatesWithWrongParentTag() {
        Boolean bool = this.fixDifferentiatorEmittingUpdatesWithWrongParentTagCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.fixDifferentiatorEmittingUpdatesWithWrongParentTag());
            this.accessedFeatureFlags.add("fixDifferentiatorEmittingUpdatesWithWrongParentTag");
            this.fixDifferentiatorEmittingUpdatesWithWrongParentTagCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean fixMappingOfEventPrioritiesBetweenFabricAndReact() {
        Boolean bool = this.fixMappingOfEventPrioritiesBetweenFabricAndReactCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.fixMappingOfEventPrioritiesBetweenFabricAndReact());
            this.accessedFeatureFlags.add("fixMappingOfEventPrioritiesBetweenFabricAndReact");
            this.fixMappingOfEventPrioritiesBetweenFabricAndReactCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean fixMountingCoordinatorReportedPendingTransactionsOnAndroid() {
        Boolean bool = this.fixMountingCoordinatorReportedPendingTransactionsOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.fixMountingCoordinatorReportedPendingTransactionsOnAndroid());
            this.accessedFeatureFlags.add("fixMountingCoordinatorReportedPendingTransactionsOnAndroid");
            this.fixMountingCoordinatorReportedPendingTransactionsOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean fuseboxEnabledRelease() {
        Boolean bool = this.fuseboxEnabledReleaseCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.fuseboxEnabledRelease());
            this.accessedFeatureFlags.add("fuseboxEnabledRelease");
            this.fuseboxEnabledReleaseCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean initEagerTurboModulesOnNativeModulesQueueAndroid() {
        Boolean bool = this.initEagerTurboModulesOnNativeModulesQueueAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.initEagerTurboModulesOnNativeModulesQueueAndroid());
            this.accessedFeatureFlags.add("initEagerTurboModulesOnNativeModulesQueueAndroid");
            this.initEagerTurboModulesOnNativeModulesQueueAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean lazyAnimationCallbacks() {
        Boolean bool = this.lazyAnimationCallbacksCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.lazyAnimationCallbacks());
            this.accessedFeatureFlags.add("lazyAnimationCallbacks");
            this.lazyAnimationCallbacksCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean loadVectorDrawablesOnImages() {
        Boolean bool = this.loadVectorDrawablesOnImagesCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.loadVectorDrawablesOnImages());
            this.accessedFeatureFlags.add("loadVectorDrawablesOnImages");
            this.loadVectorDrawablesOnImagesCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean traceTurboModulePromiseRejectionsOnAndroid() {
        Boolean bool = this.traceTurboModulePromiseRejectionsOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.traceTurboModulePromiseRejectionsOnAndroid());
            this.accessedFeatureFlags.add("traceTurboModulePromiseRejectionsOnAndroid");
            this.traceTurboModulePromiseRejectionsOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useAlwaysAvailableJSErrorHandling() {
        Boolean bool = this.useAlwaysAvailableJSErrorHandlingCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useAlwaysAvailableJSErrorHandling());
            this.accessedFeatureFlags.add("useAlwaysAvailableJSErrorHandling");
            this.useAlwaysAvailableJSErrorHandlingCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useFabricInterop() {
        Boolean bool = this.useFabricInteropCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useFabricInterop());
            this.accessedFeatureFlags.add("useFabricInterop");
            this.useFabricInteropCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useImmediateExecutorInAndroidBridgeless() {
        Boolean bool = this.useImmediateExecutorInAndroidBridgelessCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useImmediateExecutorInAndroidBridgeless());
            this.accessedFeatureFlags.add("useImmediateExecutorInAndroidBridgeless");
            this.useImmediateExecutorInAndroidBridgelessCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useNativeViewConfigsInBridgelessMode() {
        Boolean bool = this.useNativeViewConfigsInBridgelessModeCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useNativeViewConfigsInBridgelessMode());
            this.accessedFeatureFlags.add("useNativeViewConfigsInBridgelessMode");
            this.useNativeViewConfigsInBridgelessModeCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useOptimisedViewPreallocationOnAndroid() {
        Boolean bool = this.useOptimisedViewPreallocationOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useOptimisedViewPreallocationOnAndroid());
            this.accessedFeatureFlags.add("useOptimisedViewPreallocationOnAndroid");
            this.useOptimisedViewPreallocationOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useOptimizedEventBatchingOnAndroid() {
        Boolean bool = this.useOptimizedEventBatchingOnAndroidCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useOptimizedEventBatchingOnAndroid());
            this.accessedFeatureFlags.add("useOptimizedEventBatchingOnAndroid");
            this.useOptimizedEventBatchingOnAndroidCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useRawPropsJsiValue() {
        Boolean bool = this.useRawPropsJsiValueCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useRawPropsJsiValue());
            this.accessedFeatureFlags.add("useRawPropsJsiValue");
            this.useRawPropsJsiValueCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useRuntimeShadowNodeReferenceUpdate() {
        Boolean bool = this.useRuntimeShadowNodeReferenceUpdateCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useRuntimeShadowNodeReferenceUpdate());
            this.accessedFeatureFlags.add("useRuntimeShadowNodeReferenceUpdate");
            this.useRuntimeShadowNodeReferenceUpdateCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useTurboModuleInterop() {
        Boolean bool = this.useTurboModuleInteropCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useTurboModuleInterop());
            this.accessedFeatureFlags.add("useTurboModuleInterop");
            this.useTurboModuleInteropCache = bool;
        }
        return bool.booleanValue();
    }

    public boolean useTurboModules() {
        Boolean bool = this.useTurboModulesCache;
        if (bool == null) {
            bool = Boolean.valueOf(this.currentProvider.useTurboModules());
            this.accessedFeatureFlags.add("useTurboModules");
            this.useTurboModulesCache = bool;
        }
        return bool.booleanValue();
    }

    public void override(ReactNativeFeatureFlagsProvider reactNativeFeatureFlagsProvider) {
        Intrinsics.checkNotNullParameter(reactNativeFeatureFlagsProvider, "provider");
        if (this.accessedFeatureFlags.isEmpty()) {
            this.currentProvider = reactNativeFeatureFlagsProvider;
            return;
        }
        String joinToString$default = CollectionsKt.joinToString$default(this.accessedFeatureFlags, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new ReactNativeFeatureFlagsLocalAccessor$$ExternalSyntheticLambda0(), 30, (Object) null);
        throw new IllegalStateException("Feature flags were accessed before being overridden: " + joinToString$default);
    }

    public String dangerouslyForceOverride(ReactNativeFeatureFlagsProvider reactNativeFeatureFlagsProvider) {
        Intrinsics.checkNotNullParameter(reactNativeFeatureFlagsProvider, "provider");
        String accessedFeatureFlags$ReactAndroid_release = getAccessedFeatureFlags$ReactAndroid_release();
        this.currentProvider = reactNativeFeatureFlagsProvider;
        return accessedFeatureFlags$ReactAndroid_release;
    }

    public final String getAccessedFeatureFlags$ReactAndroid_release() {
        if (this.accessedFeatureFlags.isEmpty()) {
            return null;
        }
        return CollectionsKt.joinToString$default(this.accessedFeatureFlags, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new ReactNativeFeatureFlagsLocalAccessor$$ExternalSyntheticLambda1(), 30, (Object) null);
    }
}
