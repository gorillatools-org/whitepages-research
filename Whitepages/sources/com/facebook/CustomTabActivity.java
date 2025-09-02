package com.facebook;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class CustomTabActivity extends Activity {
    public static final String CUSTOM_TAB_REDIRECT_ACTION;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DESTROY_ACTION;
    private BroadcastReceiver closeReceiver;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = new Intent(this, CustomTabMainActivity.class);
        intent.setAction(CUSTOM_TAB_REDIRECT_ACTION);
        intent.putExtra(CustomTabMainActivity.EXTRA_URL, getIntent().getDataString());
        intent.addFlags(603979776);
        startActivityForResult(intent, 2);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 0) {
            Intent intent2 = new Intent(CUSTOM_TAB_REDIRECT_ACTION);
            intent2.putExtra(CustomTabMainActivity.EXTRA_URL, getIntent().getDataString());
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);
            CustomTabActivity$onActivityResult$closeReceiver$1 customTabActivity$onActivityResult$closeReceiver$1 = new CustomTabActivity$onActivityResult$closeReceiver$1(this);
            LocalBroadcastManager.getInstance(this).registerReceiver(customTabActivity$onActivityResult$closeReceiver$1, new IntentFilter(DESTROY_ACTION));
            this.closeReceiver = customTabActivity$onActivityResult$closeReceiver$1;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        BroadcastReceiver broadcastReceiver = this.closeReceiver;
        if (broadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        }
        super.onDestroy();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        Class<CustomTabActivity> cls = CustomTabActivity.class;
        sb.append(cls.getSimpleName());
        sb.append(".action_customTabRedirect");
        CUSTOM_TAB_REDIRECT_ACTION = sb.toString();
        DESTROY_ACTION = cls.getSimpleName() + ".action_destroy";
    }
}
