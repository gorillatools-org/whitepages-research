package com.google.firebase.iid.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.Task;
import java.io.IOException;

@KeepForSdk
public interface FirebaseInstanceIdInternal {

    @KeepForSdk
    public interface NewTokenListener {
        @KeepForSdk
        void onNewToken(String str);
    }

    @KeepForSdk
    void addNewTokenListener(NewTokenListener newTokenListener);

    @KeepForSdk
    void deleteToken(String str, String str2) throws IOException;

    @KeepForSdk
    String getId();

    @KeepForSdk
    String getToken();

    @KeepForSdk
    Task<String> getTokenTask();
}
