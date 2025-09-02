package com.google.firebase.crashlytics;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.crashlytics.internal.Logger;

public final /* synthetic */ class FirebaseCrashlytics$$ExternalSyntheticLambda0 implements OnFailureListener {
    public final void onFailure(Exception exc) {
        Logger.getLogger().e("Error fetching settings.", exc);
    }
}
