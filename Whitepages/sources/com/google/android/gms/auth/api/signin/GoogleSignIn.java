package com.google.android.gms.auth.api.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zbm;
import com.google.android.gms.auth.api.signin.internal.zbn;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Deprecated
public final class GoogleSignIn {
    private GoogleSignIn() {
    }

    public static GoogleSignInAccount getAccountForExtension(Context context, GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        Preconditions.checkNotNull(context, "please provide a valid Context object");
        Preconditions.checkNotNull(googleSignInOptionsExtension, "please provide valid GoogleSignInOptionsExtension");
        GoogleSignInAccount lastSignedInAccount = getLastSignedInAccount(context);
        if (lastSignedInAccount == null) {
            lastSignedInAccount = GoogleSignInAccount.createDefault();
        }
        return lastSignedInAccount.requestExtraScopes(zbb(googleSignInOptionsExtension.getImpliedScopes()));
    }

    public static GoogleSignInAccount getAccountForScopes(Context context, Scope scope, Scope... scopeArr) {
        Preconditions.checkNotNull(context, "please provide a valid Context object");
        Preconditions.checkNotNull(scope, "please provide at least one valid scope");
        GoogleSignInAccount lastSignedInAccount = getLastSignedInAccount(context);
        if (lastSignedInAccount == null) {
            lastSignedInAccount = GoogleSignInAccount.createDefault();
        }
        lastSignedInAccount.requestExtraScopes(scope);
        lastSignedInAccount.requestExtraScopes(scopeArr);
        return lastSignedInAccount;
    }

    public static GoogleSignInClient getClient(Activity activity, GoogleSignInOptions googleSignInOptions) {
        return new GoogleSignInClient(activity, (GoogleSignInOptions) Preconditions.checkNotNull(googleSignInOptions));
    }

    public static GoogleSignInAccount getLastSignedInAccount(Context context) {
        return zbn.zbc(context).zba();
    }

    public static Task<GoogleSignInAccount> getSignedInAccountFromIntent(Intent intent) {
        GoogleSignInResult zbd = zbm.zbd(intent);
        GoogleSignInAccount signInAccount = zbd.getSignInAccount();
        if (!zbd.getStatus().isSuccess() || signInAccount == null) {
            return Tasks.forException(ApiExceptionUtil.fromStatus(zbd.getStatus()));
        }
        return Tasks.forResult(signInAccount);
    }

    public static boolean hasPermissions(GoogleSignInAccount googleSignInAccount, GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        Preconditions.checkNotNull(googleSignInOptionsExtension, "Please provide a non-null GoogleSignInOptionsExtension");
        return hasPermissions(googleSignInAccount, zbb(googleSignInOptionsExtension.getImpliedScopes()));
    }

    public static void requestPermissions(Activity activity, int i, GoogleSignInAccount googleSignInAccount, GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        Preconditions.checkNotNull(activity, "Please provide a non-null Activity");
        Preconditions.checkNotNull(googleSignInOptionsExtension, "Please provide a non-null GoogleSignInOptionsExtension");
        requestPermissions(activity, i, googleSignInAccount, zbb(googleSignInOptionsExtension.getImpliedScopes()));
    }

    private static Intent zba(Activity activity, GoogleSignInAccount googleSignInAccount, Scope... scopeArr) {
        GoogleSignInOptions.Builder builder = new GoogleSignInOptions.Builder();
        if (scopeArr.length > 0) {
            builder.requestScopes(scopeArr[0], scopeArr);
        }
        if (googleSignInAccount != null && !TextUtils.isEmpty(googleSignInAccount.getEmail())) {
            builder.setAccountName((String) Preconditions.checkNotNull(googleSignInAccount.getEmail()));
        }
        return new GoogleSignInClient(activity, builder.build()).getSignInIntent();
    }

    private static Scope[] zbb(List list) {
        return list == null ? new Scope[0] : (Scope[]) list.toArray(new Scope[list.size()]);
    }

    public static GoogleSignInClient getClient(Context context, GoogleSignInOptions googleSignInOptions) {
        return new GoogleSignInClient(context, (GoogleSignInOptions) Preconditions.checkNotNull(googleSignInOptions));
    }

    public static boolean hasPermissions(GoogleSignInAccount googleSignInAccount, Scope... scopeArr) {
        if (googleSignInAccount == null) {
            return false;
        }
        HashSet hashSet = new HashSet();
        Collections.addAll(hashSet, scopeArr);
        return googleSignInAccount.getGrantedScopes().containsAll(hashSet);
    }

    public static void requestPermissions(Activity activity, int i, GoogleSignInAccount googleSignInAccount, Scope... scopeArr) {
        Preconditions.checkNotNull(activity, "Please provide a non-null Activity");
        Preconditions.checkNotNull(scopeArr, "Please provide at least one scope");
        activity.startActivityForResult(zba(activity, googleSignInAccount, scopeArr), i);
    }

    public static void requestPermissions(Fragment fragment, int i, GoogleSignInAccount googleSignInAccount, GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        Preconditions.checkNotNull(fragment, "Please provide a non-null Fragment");
        Preconditions.checkNotNull(googleSignInOptionsExtension, "Please provide a non-null GoogleSignInOptionsExtension");
        requestPermissions(fragment, i, googleSignInAccount, zbb(googleSignInOptionsExtension.getImpliedScopes()));
    }

    public static void requestPermissions(Fragment fragment, int i, GoogleSignInAccount googleSignInAccount, Scope... scopeArr) {
        Preconditions.checkNotNull(fragment, "Please provide a non-null Fragment");
        Preconditions.checkNotNull(scopeArr, "Please provide at least one scope");
        fragment.startActivityForResult(zba(fragment.getActivity(), googleSignInAccount, scopeArr), i);
    }
}
