package androidx.work.impl.constraints.controllers;

import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public abstract class ConstraintController implements ConstraintListener {
    private OnConstraintUpdatedCallback callback;
    private Object currentValue;
    private final List matchingWorkSpecIds = new ArrayList();
    private final List matchingWorkSpecs = new ArrayList();
    private final ConstraintTracker tracker;

    public interface OnConstraintUpdatedCallback {
        void onConstraintMet(List list);

        void onConstraintNotMet(List list);
    }

    public abstract boolean hasConstraint(WorkSpec workSpec);

    public abstract boolean isConstrained(Object obj);

    public ConstraintController(ConstraintTracker constraintTracker) {
        Intrinsics.checkNotNullParameter(constraintTracker, "tracker");
        this.tracker = constraintTracker;
    }

    public final void setCallback(OnConstraintUpdatedCallback onConstraintUpdatedCallback) {
        if (this.callback != onConstraintUpdatedCallback) {
            this.callback = onConstraintUpdatedCallback;
            updateCallback(onConstraintUpdatedCallback, this.currentValue);
        }
    }

    public final void replace(Iterable iterable) {
        Intrinsics.checkNotNullParameter(iterable, "workSpecs");
        this.matchingWorkSpecs.clear();
        this.matchingWorkSpecIds.clear();
        Collection collection = this.matchingWorkSpecs;
        for (Object next : iterable) {
            if (hasConstraint((WorkSpec) next)) {
                collection.add(next);
            }
        }
        Collection collection2 = this.matchingWorkSpecIds;
        for (WorkSpec workSpec : this.matchingWorkSpecs) {
            collection2.add(workSpec.id);
        }
        if (this.matchingWorkSpecs.isEmpty()) {
            this.tracker.removeListener(this);
        } else {
            this.tracker.addListener(this);
        }
        updateCallback(this.callback, this.currentValue);
    }

    public final void reset() {
        if (!this.matchingWorkSpecs.isEmpty()) {
            this.matchingWorkSpecs.clear();
            this.tracker.removeListener(this);
        }
    }

    public final boolean isWorkSpecConstrained(String str) {
        Intrinsics.checkNotNullParameter(str, "workSpecId");
        Object obj = this.currentValue;
        return obj != null && isConstrained(obj) && this.matchingWorkSpecIds.contains(str);
    }

    private final void updateCallback(OnConstraintUpdatedCallback onConstraintUpdatedCallback, Object obj) {
        if (!this.matchingWorkSpecs.isEmpty() && onConstraintUpdatedCallback != null) {
            if (obj == null || isConstrained(obj)) {
                onConstraintUpdatedCallback.onConstraintNotMet(this.matchingWorkSpecs);
            } else {
                onConstraintUpdatedCallback.onConstraintMet(this.matchingWorkSpecs);
            }
        }
    }

    public void onConstraintChanged(Object obj) {
        this.currentValue = obj;
        updateCallback(this.callback, obj);
    }
}
