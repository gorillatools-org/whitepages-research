package com.facebook.common.executors;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class StatefulRunnable implements Runnable {
    protected final AtomicInteger mState = new AtomicInteger(0);

    /* access modifiers changed from: protected */
    public abstract void disposeResult(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object getResult();

    /* access modifiers changed from: protected */
    public abstract void onCancellation();

    /* access modifiers changed from: protected */
    public abstract void onFailure(Exception exc);

    /* access modifiers changed from: protected */
    public abstract void onSuccess(Object obj);

    public final void run() {
        if (this.mState.compareAndSet(0, 1)) {
            try {
                Object result = getResult();
                this.mState.set(3);
                try {
                    onSuccess(result);
                } finally {
                    disposeResult(result);
                }
            } catch (Exception e) {
                this.mState.set(4);
                onFailure(e);
            }
        }
    }

    public void cancel() {
        if (this.mState.compareAndSet(0, 2)) {
            onCancellation();
        }
    }
}
