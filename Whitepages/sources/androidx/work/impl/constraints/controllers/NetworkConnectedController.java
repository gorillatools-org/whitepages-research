package androidx.work.impl.constraints.controllers;

import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.jvm.internal.Intrinsics;

public final class NetworkConnectedController extends ConstraintController {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NetworkConnectedController(ConstraintTracker constraintTracker) {
        super(constraintTracker);
        Intrinsics.checkNotNullParameter(constraintTracker, "tracker");
    }

    public boolean hasConstraint(WorkSpec workSpec) {
        Intrinsics.checkNotNullParameter(workSpec, "workSpec");
        return workSpec.constraints.getRequiredNetworkType() == NetworkType.CONNECTED;
    }

    public boolean isConstrained(NetworkState networkState) {
        Intrinsics.checkNotNullParameter(networkState, "value");
        return !networkState.isConnected() || !networkState.isValidated();
    }
}
