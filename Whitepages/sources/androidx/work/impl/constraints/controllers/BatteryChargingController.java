package androidx.work.impl.constraints.controllers;

import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.jvm.internal.Intrinsics;

public final class BatteryChargingController extends ConstraintController {
    public boolean isConstrained(boolean z) {
        return !z;
    }

    public /* bridge */ /* synthetic */ boolean isConstrained(Object obj) {
        return isConstrained(((Boolean) obj).booleanValue());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryChargingController(ConstraintTracker constraintTracker) {
        super(constraintTracker);
        Intrinsics.checkNotNullParameter(constraintTracker, "tracker");
    }

    public boolean hasConstraint(WorkSpec workSpec) {
        Intrinsics.checkNotNullParameter(workSpec, "workSpec");
        return workSpec.constraints.requiresCharging();
    }
}
