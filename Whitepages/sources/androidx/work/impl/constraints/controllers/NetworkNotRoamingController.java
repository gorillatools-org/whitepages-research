package androidx.work.impl.constraints.controllers;

import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class NetworkNotRoamingController extends ConstraintController {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NetworkNotRoamingController(ConstraintTracker constraintTracker) {
        super(constraintTracker);
        Intrinsics.checkNotNullParameter(constraintTracker, "tracker");
    }

    public boolean hasConstraint(WorkSpec workSpec) {
        Intrinsics.checkNotNullParameter(workSpec, "workSpec");
        return workSpec.constraints.getRequiredNetworkType() == NetworkType.NOT_ROAMING;
    }

    public boolean isConstrained(NetworkState networkState) {
        Intrinsics.checkNotNullParameter(networkState, "value");
        return !networkState.isConnected() || !networkState.isNotRoaming();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        String tagWithPrefix = Logger.tagWithPrefix("NetworkNotRoamingCtrlr");
        Intrinsics.checkNotNullExpressionValue(tagWithPrefix, "tagWithPrefix(\"NetworkNotRoamingCtrlr\")");
        TAG = tagWithPrefix;
    }
}
