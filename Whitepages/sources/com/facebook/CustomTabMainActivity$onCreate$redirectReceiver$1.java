package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;

public final class CustomTabMainActivity$onCreate$redirectReceiver$1 extends BroadcastReceiver {
    final /* synthetic */ CustomTabMainActivity this$0;

    CustomTabMainActivity$onCreate$redirectReceiver$1(CustomTabMainActivity customTabMainActivity) {
        this.this$0 = customTabMainActivity;
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Intent intent2 = new Intent(this.this$0, CustomTabMainActivity.class);
        intent2.setAction(CustomTabMainActivity.REFRESH_ACTION);
        String str = CustomTabMainActivity.EXTRA_URL;
        intent2.putExtra(str, intent.getStringExtra(str));
        intent2.addFlags(603979776);
        this.this$0.startActivity(intent2);
    }
}
