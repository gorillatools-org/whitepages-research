package com.google.firebase.crashlytics.internal;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsState;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;
import java.util.ArrayList;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class CrashlyticsRemoteConfigListener implements RolloutsStateSubscriber {
    private final UserMetadata userMetadata;

    public CrashlyticsRemoteConfigListener(UserMetadata userMetadata2) {
        Intrinsics.checkNotNullParameter(userMetadata2, "userMetadata");
        this.userMetadata = userMetadata2;
    }

    public void onRolloutsStateChanged(RolloutsState rolloutsState) {
        Intrinsics.checkNotNullParameter(rolloutsState, "rolloutsState");
        UserMetadata userMetadata2 = this.userMetadata;
        Set<RolloutAssignment> rolloutAssignments = rolloutsState.getRolloutAssignments();
        Intrinsics.checkNotNullExpressionValue(rolloutAssignments, "rolloutsState.rolloutAssignments");
        Iterable<RolloutAssignment> iterable = rolloutAssignments;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (RolloutAssignment rolloutAssignment : iterable) {
            arrayList.add(com.google.firebase.crashlytics.internal.metadata.RolloutAssignment.create(rolloutAssignment.getRolloutId(), rolloutAssignment.getParameterKey(), rolloutAssignment.getParameterValue(), rolloutAssignment.getVariantId(), rolloutAssignment.getTemplateVersion()));
        }
        userMetadata2.updateRolloutsState(arrayList);
        Logger.getLogger().d("Updated Crashlytics Rollout State");
    }
}
