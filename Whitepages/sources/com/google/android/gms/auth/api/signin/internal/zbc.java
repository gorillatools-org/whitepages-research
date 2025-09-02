package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.util.Log;
import androidx.loader.content.AsyncTaskLoader;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public final class zbc extends AsyncTaskLoader implements SignInConnectionListener {
    private final Semaphore zba = new Semaphore(0);
    private final Set zbb;

    public zbc(Context context, Set set) {
        super(context);
        this.zbb = set;
    }

    public final /* bridge */ /* synthetic */ Object loadInBackground() {
        int i = 0;
        for (GoogleApiClient maybeSignIn : this.zbb) {
            if (maybeSignIn.maybeSignIn(this)) {
                i++;
            }
        }
        try {
            this.zba.tryAcquire(i, 5, TimeUnit.SECONDS);
            return null;
        } catch (InterruptedException e) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", e);
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public final void onComplete() {
        this.zba.release();
    }

    /* access modifiers changed from: protected */
    public final void onStartLoading() {
        this.zba.drainPermits();
        forceLoad();
    }
}
