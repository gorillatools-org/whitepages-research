package com.facebook.appevents;

public final /* synthetic */ class UserDataStore$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ UserDataStore$$ExternalSyntheticLambda0(String str, String str2) {
        this.f$0 = str;
        this.f$1 = str2;
    }

    public final void run() {
        UserDataStore.writeDataIntoCache$lambda$0(this.f$0, this.f$1);
    }
}
