package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;

public final class CustomTabActivity$onActivityResult$closeReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ CustomTabActivity this$0;

    CustomTabActivity$onActivityResult$closeReceiver$1(CustomTabActivity customTabActivity) {
        this.this$0 = customTabActivity;
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        this.this$0.finish();
    }
}
