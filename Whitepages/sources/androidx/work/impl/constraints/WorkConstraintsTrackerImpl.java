package androidx.work.impl.constraints;

import androidx.work.Logger;
import androidx.work.impl.constraints.controllers.BatteryChargingController;
import androidx.work.impl.constraints.controllers.BatteryNotLowController;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.constraints.controllers.NetworkConnectedController;
import androidx.work.impl.constraints.controllers.NetworkMeteredController;
import androidx.work.impl.constraints.controllers.NetworkNotRoamingController;
import androidx.work.impl.constraints.controllers.NetworkUnmeteredController;
import androidx.work.impl.constraints.controllers.StorageNotLowController;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public final class WorkConstraintsTrackerImpl implements WorkConstraintsTracker, ConstraintController.OnConstraintUpdatedCallback {
    private final WorkConstraintsCallback callback;
    private final ConstraintController[] constraintControllers;
    private final Object lock;

    public WorkConstraintsTrackerImpl(WorkConstraintsCallback workConstraintsCallback, ConstraintController[] constraintControllerArr) {
        Intrinsics.checkNotNullParameter(constraintControllerArr, "constraintControllers");
        this.callback = workConstraintsCallback;
        this.constraintControllers = constraintControllerArr;
        this.lock = new Object();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WorkConstraintsTrackerImpl(Trackers trackers, WorkConstraintsCallback workConstraintsCallback) {
        this(workConstraintsCallback, new ConstraintController[]{new BatteryChargingController(trackers.getBatteryChargingTracker()), new BatteryNotLowController(trackers.getBatteryNotLowTracker()), new StorageNotLowController(trackers.getStorageNotLowTracker()), new NetworkConnectedController(trackers.getNetworkStateTracker()), new NetworkUnmeteredController(trackers.getNetworkStateTracker()), new NetworkNotRoamingController(trackers.getNetworkStateTracker()), new NetworkMeteredController(trackers.getNetworkStateTracker())});
        Intrinsics.checkNotNullParameter(trackers, "trackers");
    }

    public void replace(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "workSpecs");
        synchronized (this.lock) {
            try {
                for (ConstraintController callback2 : this.constraintControllers) {
                    callback2.setCallback((ConstraintController.OnConstraintUpdatedCallback) null);
                }
                for (ConstraintController replace : this.constraintControllers) {
                    replace.replace(iterable);
                }
                for (ConstraintController callback3 : this.constraintControllers) {
                    callback3.setCallback(this);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void reset() {
        synchronized (this.lock) {
            try {
                for (ConstraintController reset : this.constraintControllers) {
                    reset.reset();
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean areAllConstraintsMet(String str) {
        boolean z;
        ConstraintController constraintController;
        Intrinsics.checkNotNullParameter(str, "workSpecId");
        synchronized (this.lock) {
            try {
                ConstraintController[] constraintControllerArr = this.constraintControllers;
                int length = constraintControllerArr.length;
                z = false;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        constraintController = null;
                        break;
                    }
                    constraintController = constraintControllerArr[i];
                    if (constraintController.isWorkSpecConstrained(str)) {
                        break;
                    }
                    i++;
                }
                if (constraintController != null) {
                    Logger.get().debug(WorkConstraintsTrackerKt.TAG, "Work " + str + " constrained by " + constraintController.getClass().getSimpleName());
                }
                if (constraintController == null) {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public void onConstraintMet(List list) {
        Intrinsics.checkNotNullParameter(list, "workSpecs");
        synchronized (this.lock) {
            try {
                ArrayList<WorkSpec> arrayList = new ArrayList<>();
                for (Object next : list) {
                    if (areAllConstraintsMet(((WorkSpec) next).id)) {
                        arrayList.add(next);
                    }
                }
                for (WorkSpec workSpec : arrayList) {
                    Logger logger = Logger.get();
                    String access$getTAG$p = WorkConstraintsTrackerKt.TAG;
                    logger.debug(access$getTAG$p, "Constraints met for " + workSpec);
                }
                WorkConstraintsCallback workConstraintsCallback = this.callback;
                if (workConstraintsCallback != null) {
                    workConstraintsCallback.onAllConstraintsMet(arrayList);
                    Unit unit = Unit.INSTANCE;
                }
            } finally {
            }
        }
    }

    public void onConstraintNotMet(List list) {
        Intrinsics.checkNotNullParameter(list, "workSpecs");
        synchronized (this.lock) {
            WorkConstraintsCallback workConstraintsCallback = this.callback;
            if (workConstraintsCallback != null) {
                workConstraintsCallback.onAllConstraintsNotMet(list);
                Unit unit = Unit.INSTANCE;
            }
        }
    }
}
