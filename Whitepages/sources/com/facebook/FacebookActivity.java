package com.facebook;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.common.R$layout;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.internal.logging.dumpsys.EndToEndDumper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class FacebookActivity extends FragmentActivity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = FacebookActivity.class.getName();
    private Fragment currentFragment;

    public final Fragment getCurrentFragment() {
        return this.currentFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (!FacebookSdk.isInitialized()) {
            Utility.logd(TAG, "Facebook SDK not initialized. Make sure you call sdkInitialize inside your Application's onCreate method.");
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            FacebookSdk.sdkInitialize(applicationContext);
        }
        setContentView(R$layout.com_facebook_activity_layout);
        if (Intrinsics.areEqual((Object) "PassThrough", (Object) intent.getAction())) {
            handlePassThroughError();
        } else {
            this.currentFragment = getFragment();
        }
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [com.facebook.internal.FacebookDialogFragment, androidx.fragment.app.Fragment, androidx.fragment.app.DialogFragment] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.fragment.app.Fragment getFragment() {
        /*
            r4 = this;
            android.content.Intent r0 = r4.getIntent()
            androidx.fragment.app.FragmentManager r1 = r4.getSupportFragmentManager()
            java.lang.String r2 = "supportFragmentManager"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r2 = "SingleFragment"
            androidx.fragment.app.Fragment r3 = r1.findFragmentByTag(r2)
            if (r3 != 0) goto L_0x0045
            java.lang.String r3 = "FacebookDialogFragment"
            java.lang.String r0 = r0.getAction()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r0)
            r3 = 1
            if (r0 == 0) goto L_0x002f
            com.facebook.internal.FacebookDialogFragment r0 = new com.facebook.internal.FacebookDialogFragment
            r0.<init>()
            r0.setRetainInstance(r3)
            r0.show((androidx.fragment.app.FragmentManager) r1, (java.lang.String) r2)
        L_0x002d:
            r3 = r0
            goto L_0x0045
        L_0x002f:
            com.facebook.login.LoginFragment r0 = new com.facebook.login.LoginFragment
            r0.<init>()
            r0.setRetainInstance(r3)
            androidx.fragment.app.FragmentTransaction r1 = r1.beginTransaction()
            int r3 = com.facebook.common.R$id.com_facebook_fragment_container
            androidx.fragment.app.FragmentTransaction r1 = r1.add((int) r3, (androidx.fragment.app.Fragment) r0, (java.lang.String) r2)
            r1.commit()
            goto L_0x002d
        L_0x0045:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.FacebookActivity.getFragment():androidx.fragment.app.Fragment");
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        Fragment fragment = this.currentFragment;
        if (fragment != null) {
            fragment.onConfigurationChanged(configuration);
        }
    }

    private final void handlePassThroughError() {
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "requestIntent");
        FacebookException exceptionFromErrorData = NativeProtocol.getExceptionFromErrorData(NativeProtocol.getMethodArgumentsFromIntent(intent));
        Intent intent2 = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent2, "intent");
        setResult(0, NativeProtocol.createProtocolResultIntent(intent2, (Bundle) null, exceptionFromErrorData));
        finish();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(str, "prefix");
                Intrinsics.checkNotNullParameter(printWriter, "writer");
                EndToEndDumper.Companion.getInstance();
                super.dump(str, fileDescriptor, printWriter, strArr);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
