package androidx.work.impl.constraints;

import java.util.List;

public interface WorkConstraintsCallback {
    void onAllConstraintsMet(List list);

    void onAllConstraintsNotMet(List list);
}
