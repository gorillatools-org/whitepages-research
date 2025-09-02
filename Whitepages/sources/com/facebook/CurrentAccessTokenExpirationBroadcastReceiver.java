package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;

public final class CurrentAccessTokenExpirationBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (Intrinsics.areEqual((Object) "com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED", (Object) intent.getAction()) && FacebookSdk.isInitialized()) {
            AccessTokenManager.Companion.getInstance().currentAccessTokenChanged();
        }
    }
}
