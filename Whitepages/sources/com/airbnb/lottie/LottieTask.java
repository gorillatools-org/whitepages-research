package com.airbnb.lottie;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieThreadFactory;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class LottieTask {
    public static Executor EXECUTOR = Executors.newCachedThreadPool(new LottieThreadFactory());
    private final Set failureListeners;
    private final Handler handler;
    private volatile LottieResult result;
    private final Set successListeners;

    public LottieTask(Callable callable) {
        this(callable, false);
    }

    public LottieTask(Object obj) {
        this.successListeners = new LinkedHashSet(1);
        this.failureListeners = new LinkedHashSet(1);
        this.handler = new Handler(Looper.getMainLooper());
        this.result = null;
        setResult(new LottieResult(obj));
    }

    LottieTask(Callable callable, boolean z) {
        this.successListeners = new LinkedHashSet(1);
        this.failureListeners = new LinkedHashSet(1);
        this.handler = new Handler(Looper.getMainLooper());
        this.result = null;
        if (z) {
            try {
                setResult((LottieResult) callable.call());
            } catch (Throwable th) {
                setResult(new LottieResult(th));
            }
        } else {
            EXECUTOR.execute(new LottieFutureTask(this, callable));
        }
    }

    /* access modifiers changed from: private */
    public void setResult(LottieResult lottieResult) {
        if (this.result == null) {
            this.result = lottieResult;
            notifyListeners();
            return;
        }
        throw new IllegalStateException("A task may only be set once.");
    }

    public synchronized LottieTask addListener(LottieListener lottieListener) {
        try {
            LottieResult lottieResult = this.result;
            if (!(lottieResult == null || lottieResult.getValue() == null)) {
                lottieListener.onResult(lottieResult.getValue());
            }
            this.successListeners.add(lottieListener);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this;
    }

    public synchronized LottieTask removeListener(LottieListener lottieListener) {
        this.successListeners.remove(lottieListener);
        return this;
    }

    public synchronized LottieTask addFailureListener(LottieListener lottieListener) {
        try {
            LottieResult lottieResult = this.result;
            if (!(lottieResult == null || lottieResult.getException() == null)) {
                lottieListener.onResult(lottieResult.getException());
            }
            this.failureListeners.add(lottieListener);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this;
    }

    public synchronized LottieTask removeFailureListener(LottieListener lottieListener) {
        this.failureListeners.remove(lottieListener);
        return this;
    }

    public LottieResult getResult() {
        return this.result;
    }

    private void notifyListeners() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            notifyListenersInternal();
        } else {
            this.handler.post(new LottieTask$$ExternalSyntheticLambda0(this));
        }
    }

    /* access modifiers changed from: private */
    public void notifyListenersInternal() {
        LottieResult lottieResult = this.result;
        if (lottieResult != null) {
            if (lottieResult.getValue() != null) {
                notifySuccessListeners(lottieResult.getValue());
            } else {
                notifyFailureListeners(lottieResult.getException());
            }
        }
    }

    private synchronized void notifySuccessListeners(Object obj) {
        for (LottieListener onResult : new ArrayList(this.successListeners)) {
            onResult.onResult(obj);
        }
    }

    private synchronized void notifyFailureListeners(Throwable th) {
        ArrayList<LottieListener> arrayList = new ArrayList<>(this.failureListeners);
        if (arrayList.isEmpty()) {
            Logger.warning("Lottie encountered an error but no failure listener was added:", th);
            return;
        }
        for (LottieListener onResult : arrayList) {
            onResult.onResult(th);
        }
    }

    private static class LottieFutureTask extends FutureTask {
        private LottieTask lottieTask;

        LottieFutureTask(LottieTask lottieTask2, Callable callable) {
            super(callable);
            this.lottieTask = lottieTask2;
        }

        /* access modifiers changed from: protected */
        public void done() {
            try {
                if (isCancelled()) {
                    this.lottieTask = null;
                    return;
                }
                this.lottieTask.setResult((LottieResult) get());
                this.lottieTask = null;
            } catch (InterruptedException | ExecutionException e) {
                this.lottieTask.setResult(new LottieResult(e));
            } catch (Throwable th) {
                this.lottieTask = null;
                throw th;
            }
        }
    }
}
