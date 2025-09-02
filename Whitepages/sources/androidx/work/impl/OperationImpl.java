package androidx.work.impl;

import androidx.lifecycle.MutableLiveData;
import androidx.work.Operation;
import androidx.work.impl.utils.futures.SettableFuture;

public class OperationImpl implements Operation {
    private final SettableFuture mOperationFuture = SettableFuture.create();
    private final MutableLiveData mOperationState = new MutableLiveData();

    public OperationImpl() {
        markState(Operation.IN_PROGRESS);
    }

    public void markState(Operation.State state) {
        this.mOperationState.postValue(state);
        if (state instanceof Operation.State.SUCCESS) {
            this.mOperationFuture.set((Operation.State.SUCCESS) state);
        } else if (state instanceof Operation.State.FAILURE) {
            this.mOperationFuture.setException(((Operation.State.FAILURE) state).getThrowable());
        }
    }
}
