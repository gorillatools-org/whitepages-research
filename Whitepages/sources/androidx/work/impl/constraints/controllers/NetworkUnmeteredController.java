package androidx.work.impl.constraints.controllers;

import android.os.Build;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.jvm.internal.Intrinsics;

public final class NetworkUnmeteredController extends ConstraintController {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NetworkUnmeteredController(ConstraintTracker constraintTracker) {
        super(constraintTracker);
        Intrinsics.checkNotNullParameter(constraintTracker, "tracker");
    }

    public boolean hasConstraint(WorkSpec workSpec) {
        Intrinsics.checkNotNullParameter(workSpec, "workSpec");
        NetworkType requiredNetworkType = workSpec.constraints.getRequiredNetworkType();
        return requiredNetworkType == NetworkType.UNMETERED || (Build.VERSION.SDK_INT >= 30 && requiredNetworkType == NetworkType.TEMPORARILY_UNMETERED);
    }

    public boolean isConstrained(NetworkState networkState) {
        Intrinsics.checkNotNullParameter(networkState, "value");
        return !networkState.isConnected() || networkState.isMetered();
    }
}
