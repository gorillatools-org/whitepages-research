package com.google.firebase.perf.util;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.concurrent.atomic.AtomicReference;

public class PreDrawListener implements ViewTreeObserver.OnPreDrawListener {
    private final Runnable callbackBoQ;
    private final Runnable callbackFoQ;
    @SuppressLint({"ThreadPoolCreation"})
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private final AtomicReference<View> viewReference;

    public static void registerForNextDraw(View view, Runnable runnable, Runnable runnable2) {
        view.getViewTreeObserver().addOnPreDrawListener(new PreDrawListener(view, runnable, runnable2));
    }

    private PreDrawListener(View view, Runnable runnable, Runnable runnable2) {
        this.viewReference = new AtomicReference<>(view);
        this.callbackBoQ = runnable;
        this.callbackFoQ = runnable2;
    }

    public boolean onPreDraw() {
        View andSet = this.viewReference.getAndSet((Object) null);
        if (andSet == null) {
            return true;
        }
        andSet.getViewTreeObserver().removeOnPreDrawListener(this);
        this.mainThreadHandler.post(this.callbackBoQ);
        this.mainThreadHandler.postAtFrontOfQueue(this.callbackFoQ);
        return true;
    }
}
