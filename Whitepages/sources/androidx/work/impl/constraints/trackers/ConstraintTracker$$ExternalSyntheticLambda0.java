package androidx.work.impl.constraints.trackers;

import java.util.List;

public final /* synthetic */ class ConstraintTracker$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ ConstraintTracker f$1;

    public /* synthetic */ ConstraintTracker$$ExternalSyntheticLambda0(List list, ConstraintTracker constraintTracker) {
        this.f$0 = list;
        this.f$1 = constraintTracker;
    }

    public final void run() {
        ConstraintTracker._set_state_$lambda$4$lambda$3(this.f$0, this.f$1);
    }
}
