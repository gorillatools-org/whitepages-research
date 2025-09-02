package com.facebook.react.internal.featureflags;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public final class ReactNativeFeatureFlags {
    public static final ReactNativeFeatureFlags INSTANCE = new ReactNativeFeatureFlags();
    private static ReactNativeFeatureFlagsAccessor accessor;
    private static Function0 accessorProvider;

    private ReactNativeFeatureFlags() {
    }

    static {
        ReactNativeFeatureFlags$$ExternalSyntheticLambda0 reactNativeFeatureFlags$$ExternalSyntheticLambda0 = new ReactNativeFeatureFlags$$ExternalSyntheticLambda0();
        accessorProvider = reactNativeFeatureFlags$$ExternalSyntheticLambda0;
        accessor = (ReactNativeFeatureFlagsAccessor) reactNativeFeatureFlags$$ExternalSyntheticLambda0.invoke();
    }

    /* access modifiers changed from: private */
    public static final ReactNativeFeatureFlagsCxxAccessor accessorProvider$lambda$0() {
        return new ReactNativeFeatureFlagsCxxAccessor();
    }

    public static final boolean commonTestFlag() {
        return accessor.commonTestFlag();
    }

    public static final boolean completeReactInstanceCreationOnBgThreadOnAndroid() {
        return accessor.completeReactInstanceCreationOnBgThreadOnAndroid();
    }

    public static final boolean disableEventLoopOnBridgeless() {
        return accessor.disableEventLoopOnBridgeless();
    }

    public static final boolean disableMountItemReorderingAndroid() {
        return accessor.disableMountItemReorderingAndroid();
    }

    public static final boolean enableAccumulatedUpdatesInRawPropsAndroid() {
        return accessor.enableAccumulatedUpdatesInRawPropsAndroid();
    }

    public static final boolean enableBridgelessArchitecture() {
        return accessor.enableBridgelessArchitecture();
    }

    public static final boolean enableCppPropsIteratorSetter() {
        return accessor.enableCppPropsIteratorSetter();
    }

    public static final boolean enableDeletionOfUnmountedViews() {
        return accessor.enableDeletionOfUnmountedViews();
    }

    public static final boolean enableEagerRootViewAttachment() {
        return accessor.enableEagerRootViewAttachment();
    }

    public static final boolean enableEventEmitterRetentionDuringGesturesOnAndroid() {
        return accessor.enableEventEmitterRetentionDuringGesturesOnAndroid();
    }

    public static final boolean enableFabricLogs() {
        return accessor.enableFabricLogs();
    }

    public static final boolean enableFabricRenderer() {
        return accessor.enableFabricRenderer();
    }

    public static final boolean enableFixForViewCommandRace() {
        return accessor.enableFixForViewCommandRace();
    }

    public static final boolean enableGranularShadowTreeStateReconciliation() {
        return accessor.enableGranularShadowTreeStateReconciliation();
    }

    public static final boolean enableIOSViewClipToPaddingBox() {
        return accessor.enableIOSViewClipToPaddingBox();
    }

    public static final boolean enableImagePrefetchingAndroid() {
        return accessor.enableImagePrefetchingAndroid();
    }

    public static final boolean enableLayoutAnimationsOnAndroid() {
        return accessor.enableLayoutAnimationsOnAndroid();
    }

    public static final boolean enableLayoutAnimationsOnIOS() {
        return accessor.enableLayoutAnimationsOnIOS();
    }

    public static final boolean enableLongTaskAPI() {
        return accessor.enableLongTaskAPI();
    }

    public static final boolean enableNewBackgroundAndBorderDrawables() {
        return accessor.enableNewBackgroundAndBorderDrawables();
    }

    public static final boolean enablePreciseSchedulingForPremountItemsOnAndroid() {
        return accessor.enablePreciseSchedulingForPremountItemsOnAndroid();
    }

    public static final boolean enablePropsUpdateReconciliationAndroid() {
        return accessor.enablePropsUpdateReconciliationAndroid();
    }

    public static final boolean enableReportEventPaintTime() {
        return accessor.enableReportEventPaintTime();
    }

    public static final boolean enableSynchronousStateUpdates() {
        return accessor.enableSynchronousStateUpdates();
    }

    public static final boolean enableUIConsistency() {
        return accessor.enableUIConsistency();
    }

    public static final boolean enableViewRecycling() {
        return accessor.enableViewRecycling();
    }

    public static final boolean excludeYogaFromRawProps() {
        return accessor.excludeYogaFromRawProps();
    }

    public static final boolean fixDifferentiatorEmittingUpdatesWithWrongParentTag() {
        return accessor.fixDifferentiatorEmittingUpdatesWithWrongParentTag();
    }

    public static final boolean fixMappingOfEventPrioritiesBetweenFabricAndReact() {
        return accessor.fixMappingOfEventPrioritiesBetweenFabricAndReact();
    }

    public static final boolean fixMountingCoordinatorReportedPendingTransactionsOnAndroid() {
        return accessor.fixMountingCoordinatorReportedPendingTransactionsOnAndroid();
    }

    public static final boolean fuseboxEnabledRelease() {
        return accessor.fuseboxEnabledRelease();
    }

    public static final boolean initEagerTurboModulesOnNativeModulesQueueAndroid() {
        return accessor.initEagerTurboModulesOnNativeModulesQueueAndroid();
    }

    public static final boolean lazyAnimationCallbacks() {
        return accessor.lazyAnimationCallbacks();
    }

    public static final boolean loadVectorDrawablesOnImages() {
        return accessor.loadVectorDrawablesOnImages();
    }

    public static final boolean traceTurboModulePromiseRejectionsOnAndroid() {
        return accessor.traceTurboModulePromiseRejectionsOnAndroid();
    }

    public static final boolean useAlwaysAvailableJSErrorHandling() {
        return accessor.useAlwaysAvailableJSErrorHandling();
    }

    public static final boolean useFabricInterop() {
        return accessor.useFabricInterop();
    }

    public static final boolean useImmediateExecutorInAndroidBridgeless() {
        return accessor.useImmediateExecutorInAndroidBridgeless();
    }

    public static final boolean useNativeViewConfigsInBridgelessMode() {
        return accessor.useNativeViewConfigsInBridgelessMode();
    }

    public static final boolean useOptimisedViewPreallocationOnAndroid() {
        return accessor.useOptimisedViewPreallocationOnAndroid();
    }

    public static final boolean useOptimizedEventBatchingOnAndroid() {
        return accessor.useOptimizedEventBatchingOnAndroid();
    }

    public static final boolean useRawPropsJsiValue() {
        return accessor.useRawPropsJsiValue();
    }

    public static final boolean useRuntimeShadowNodeReferenceUpdate() {
        return accessor.useRuntimeShadowNodeReferenceUpdate();
    }

    public static final boolean useTurboModuleInterop() {
        return accessor.useTurboModuleInterop();
    }

    public static final boolean useTurboModules() {
        return accessor.useTurboModules();
    }

    public static final void override(ReactNativeFeatureFlagsProvider reactNativeFeatureFlagsProvider) {
        Intrinsics.checkNotNullParameter(reactNativeFeatureFlagsProvider, "provider");
        accessor.override(reactNativeFeatureFlagsProvider);
    }

    public static final void dangerouslyReset() {
        accessor.dangerouslyReset();
        accessor = (ReactNativeFeatureFlagsAccessor) accessorProvider.invoke();
    }

    public static final String dangerouslyForceOverride(ReactNativeFeatureFlagsProvider reactNativeFeatureFlagsProvider) {
        Intrinsics.checkNotNullParameter(reactNativeFeatureFlagsProvider, "provider");
        ReactNativeFeatureFlagsAccessor reactNativeFeatureFlagsAccessor = (ReactNativeFeatureFlagsAccessor) accessorProvider.invoke();
        String dangerouslyForceOverride = reactNativeFeatureFlagsAccessor.dangerouslyForceOverride(reactNativeFeatureFlagsProvider);
        accessor = reactNativeFeatureFlagsAccessor;
        return dangerouslyForceOverride;
    }

    public final void setAccessorProvider$ReactAndroid_release(Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "newAccessorProvider");
        accessorProvider = function0;
        accessor = (ReactNativeFeatureFlagsAccessor) function0.invoke();
    }
}
