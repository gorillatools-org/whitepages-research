package com.facebook;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.internal.CustomTab;
import com.facebook.internal.InstagramCustomTab;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.login.LoginTargetApp;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomTabMainActivity extends Activity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EXTRA_ACTION;
    public static final String EXTRA_CHROME_PACKAGE;
    public static final String EXTRA_PARAMS;
    public static final String EXTRA_TARGET_APP;
    public static final String EXTRA_URL;
    public static final String NO_ACTIVITY_EXCEPTION;
    public static final String REFRESH_ACTION;
    private BroadcastReceiver redirectReceiver;
    private boolean shouldCloseCustomTab = true;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoginTargetApp.values().length];
            try {
                iArr[LoginTargetApp.INSTAGRAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String stringExtra;
        CustomTab customTab;
        super.onCreate(bundle);
        String str = CustomTabActivity.CUSTOM_TAB_REDIRECT_ACTION;
        if (Intrinsics.areEqual((Object) str, (Object) getIntent().getAction())) {
            setResult(0);
            finish();
        } else if (bundle == null && (stringExtra = getIntent().getStringExtra(EXTRA_ACTION)) != null) {
            Bundle bundleExtra = getIntent().getBundleExtra(EXTRA_PARAMS);
            String stringExtra2 = getIntent().getStringExtra(EXTRA_CHROME_PACKAGE);
            if (WhenMappings.$EnumSwitchMapping$0[LoginTargetApp.Companion.fromString(getIntent().getStringExtra(EXTRA_TARGET_APP)).ordinal()] == 1) {
                customTab = new InstagramCustomTab(stringExtra, bundleExtra);
            } else {
                customTab = new CustomTab(stringExtra, bundleExtra);
            }
            boolean openCustomTab = customTab.openCustomTab(this, stringExtra2);
            this.shouldCloseCustomTab = false;
            if (!openCustomTab) {
                setResult(0, getIntent().putExtra(NO_ACTIVITY_EXCEPTION, true));
                finish();
                return;
            }
            CustomTabMainActivity$onCreate$redirectReceiver$1 customTabMainActivity$onCreate$redirectReceiver$1 = new CustomTabMainActivity$onCreate$redirectReceiver$1(this);
            this.redirectReceiver = customTabMainActivity$onCreate$redirectReceiver$1;
            LocalBroadcastManager.getInstance(this).registerReceiver(customTabMainActivity$onCreate$redirectReceiver$1, new IntentFilter(str));
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        if (Intrinsics.areEqual((Object) REFRESH_ACTION, (Object) intent.getAction())) {
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(CustomTabActivity.DESTROY_ACTION));
            sendResult(-1, intent);
        } else if (Intrinsics.areEqual((Object) CustomTabActivity.CUSTOM_TAB_REDIRECT_ACTION, (Object) intent.getAction())) {
            sendResult(-1, intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.shouldCloseCustomTab) {
            sendResult(0, (Intent) null);
        }
        this.shouldCloseCustomTab = true;
    }

    private final void sendResult(int i, Intent intent) {
        BroadcastReceiver broadcastReceiver = this.redirectReceiver;
        if (broadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        }
        if (intent != null) {
            String stringExtra = intent.getStringExtra(EXTRA_URL);
            Bundle access$parseResponseUri = stringExtra != null ? Companion.parseResponseUri(stringExtra) : new Bundle();
            Intent intent2 = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent2, "intent");
            Intent createProtocolResultIntent = NativeProtocol.createProtocolResultIntent(intent2, access$parseResponseUri, (FacebookException) null);
            if (createProtocolResultIntent != null) {
                intent = createProtocolResultIntent;
            }
            setResult(i, intent);
        } else {
            Intent intent3 = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent3, "intent");
            setResult(i, NativeProtocol.createProtocolResultIntent(intent3, (Bundle) null, (FacebookException) null));
        }
        finish();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final Bundle parseResponseUri(String str) {
            Uri parse = Uri.parse(str);
            Bundle parseUrlQueryString = Utility.parseUrlQueryString(parse.getQuery());
            parseUrlQueryString.putAll(Utility.parseUrlQueryString(parse.getFragment()));
            return parseUrlQueryString;
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        Class<CustomTabMainActivity> cls = CustomTabMainActivity.class;
        sb.append(cls.getSimpleName());
        sb.append(".extra_action");
        EXTRA_ACTION = sb.toString();
        EXTRA_PARAMS = cls.getSimpleName() + ".extra_params";
        EXTRA_CHROME_PACKAGE = cls.getSimpleName() + ".extra_chromePackage";
        EXTRA_URL = cls.getSimpleName() + ".extra_url";
        EXTRA_TARGET_APP = cls.getSimpleName() + ".extra_targetApp";
        REFRESH_ACTION = cls.getSimpleName() + ".action_refresh";
        NO_ACTIVITY_EXCEPTION = cls.getSimpleName() + ".no_activity_exception";
    }
}
