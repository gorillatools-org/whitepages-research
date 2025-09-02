package com.google.firebase.messaging;

public final /* synthetic */ class SharedPreferencesQueue$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SharedPreferencesQueue f$0;

    public /* synthetic */ SharedPreferencesQueue$$ExternalSyntheticLambda0(SharedPreferencesQueue sharedPreferencesQueue) {
        this.f$0 = sharedPreferencesQueue;
    }

    public final void run() {
        this.f$0.syncState();
    }
}
