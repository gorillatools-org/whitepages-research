package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.Status;

@KeepName
public class SignInHubActivity extends FragmentActivity {
    private static boolean zba = false;
    private boolean zbb = false;
    private SignInConfiguration zbc;
    private boolean zbd;
    /* access modifiers changed from: private */
    public int zbe;
    /* access modifiers changed from: private */
    public Intent zbf;

    private final void zbc() {
        getSupportLoaderManager().initLoader(0, (Bundle) null, new zbv(this, (zbw) null));
        zba = false;
    }

    private final void zbd(int i) {
        Status status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
        zba = false;
    }

    private final void zbe(String str) {
        Intent intent = new Intent(str);
        if (str.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN")) {
            intent.setPackage("com.google.android.gms");
        } else {
            intent.setPackage(getPackageName());
        }
        intent.putExtra("config", this.zbc);
        try {
            startActivityForResult(intent, 40962);
        } catch (ActivityNotFoundException unused) {
            this.zbb = true;
            Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
            zbd(17);
        }
    }

    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        if (!this.zbb) {
            setResult(0);
            if (i == 40962) {
                if (intent != null) {
                    SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                    if (signInAccount != null && signInAccount.zba() != null) {
                        GoogleSignInAccount zba2 = signInAccount.zba();
                        if (zba2 == null) {
                            Log.e("AuthSignInClient", "Google account is null");
                            zbd(GoogleSignInStatusCodes.SIGN_IN_FAILED);
                            return;
                        }
                        zbn.zbc(this).zbe(this.zbc.zba(), zba2);
                        intent.removeExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                        intent.putExtra("googleSignInAccount", zba2);
                        this.zbd = true;
                        this.zbe = i2;
                        this.zbf = intent;
                        zbc();
                        return;
                    } else if (intent.hasExtra("errorCode")) {
                        int intExtra = intent.getIntExtra("errorCode", 8);
                        if (intExtra == 13) {
                            intExtra = GoogleSignInStatusCodes.SIGN_IN_CANCELLED;
                        }
                        zbd(intExtra);
                        return;
                    }
                }
                zbd(8);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (action == null) {
            Log.e("AuthSignInClient", "Null action");
            zbd(GoogleSignInStatusCodes.SIGN_IN_FAILED);
        } else if (action.equals("com.google.android.gms.auth.NO_IMPL")) {
            Log.e("AuthSignInClient", "Action not implemented");
            zbd(GoogleSignInStatusCodes.SIGN_IN_FAILED);
        } else if (action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") || action.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
            Bundle bundleExtra = intent.getBundleExtra("config");
            if (bundleExtra == null) {
                Log.e("AuthSignInClient", "Activity started with no configuration.");
                setResult(0);
                finish();
                return;
            }
            SignInConfiguration signInConfiguration = (SignInConfiguration) bundleExtra.getParcelable("config");
            if (signInConfiguration == null) {
                Log.e("AuthSignInClient", "Activity started with invalid configuration.");
                setResult(0);
                finish();
                return;
            }
            this.zbc = signInConfiguration;
            if (bundle != null) {
                boolean z = bundle.getBoolean("signingInGoogleApiClients");
                this.zbd = z;
                if (z) {
                    this.zbe = bundle.getInt("signInResultCode");
                    Intent intent2 = (Intent) bundle.getParcelable("signInResultData");
                    if (intent2 == null) {
                        Log.e("AuthSignInClient", "Sign in result data cannot be null");
                        setResult(0);
                        finish();
                        return;
                    }
                    this.zbf = intent2;
                    zbc();
                }
            } else if (zba) {
                setResult(0);
                zbd(GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS);
            } else {
                zba = true;
                zbe(action);
            }
        } else {
            Log.e("AuthSignInClient", "Unknown action: ".concat(String.valueOf(intent.getAction())));
            finish();
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        zba = false;
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zbd);
        if (this.zbd) {
            bundle.putInt("signInResultCode", this.zbe);
            bundle.putParcelable("signInResultData", this.zbf);
        }
    }
}
