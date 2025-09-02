package androidx.work.impl.constraints.controllers;

import androidx.work.impl.constraints.trackers.BatteryNotLowTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.jvm.internal.Intrinsics;

public final class BatteryNotLowController extends ConstraintController {
    public boolean isConstrained(boolean z) {
        return !z;
    }

    public /* bridge */ /* synthetic */ boolean isConstrained(Object obj) {
        return isConstrained(((Boolean) obj).booleanValue());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BatteryNotLowController(BatteryNotLowTracker batteryNotLowTracker) {
        super(batteryNotLowTracker);
        Intrinsics.checkNotNullParameter(batteryNotLowTracker, "tracker");
    }

    public boolean hasConstraint(WorkSpec workSpec) {
        Intrinsics.checkNotNullParameter(workSpec, "workSpec");
        return workSpec.constraints.requiresBatteryNotLow();
    }
}
