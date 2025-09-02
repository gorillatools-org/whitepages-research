package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;

public interface ExecutionListener {
    void onExecuted(WorkGenerationalId workGenerationalId, boolean z);
}
