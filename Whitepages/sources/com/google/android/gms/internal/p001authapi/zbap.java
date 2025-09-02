package com.google.android.gms.internal.p001authapi;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.identity.zbs;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zbap  reason: invalid package */
public final class zbap extends GoogleApi implements SignInClient {
    private static final Api.ClientKey zba;
    private static final Api.AbstractClientBuilder zbb;
    private static final Api zbc;
    private final String zbd = zbas.zba();

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zba = clientKey;
        zbak zbak = new zbak();
        zbb = zbak;
        zbc = new Api("Auth.Api.Identity.SignIn.API", zbak, clientKey);
    }

    public zbap(Activity activity, zbs zbs) {
        super(activity, zbc, zbs, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<BeginSignInResult> beginSignIn(BeginSignInRequest beginSignInRequest) {
        Preconditions.checkNotNull(beginSignInRequest);
        BeginSignInRequest.Builder zba2 = BeginSignInRequest.zba(beginSignInRequest);
        zba2.zba(this.zbd);
        return doRead(TaskApiCall.builder().setFeatures(new Feature("auth_api_credentials_begin_sign_in", 8)).run(new zbai(this, zba2.build())).setAutoResolveMissingFeatures(false).setMethodKey(1553).build());
    }

    public final String getPhoneNumberFromIntent(Intent intent) throws ApiException {
        if (intent != null) {
            Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "status", Status.CREATOR);
            if (status == null) {
                throw new ApiException(Status.RESULT_CANCELED);
            } else if (status.isSuccess()) {
                String stringExtra = intent.getStringExtra("phone_number_hint_result");
                if (stringExtra != null) {
                    return stringExtra;
                }
                throw new ApiException(Status.RESULT_INTERNAL_ERROR);
            } else {
                throw new ApiException(status);
            }
        } else {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
    }

    public final Task<PendingIntent> getPhoneNumberHintIntent(GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest) {
        Preconditions.checkNotNull(getPhoneNumberHintIntentRequest);
        return doRead(TaskApiCall.builder().setFeatures(zbar.zbh).run(new zbag(this, getPhoneNumberHintIntentRequest)).setMethodKey(1653).build());
    }

    public final SignInCredential getSignInCredentialFromIntent(Intent intent) throws ApiException {
        if (intent != null) {
            Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "status", Status.CREATOR);
            if (status == null) {
                throw new ApiException(Status.RESULT_CANCELED);
            } else if (status.isSuccess()) {
                SignInCredential signInCredential = (SignInCredential) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "sign_in_credential", SignInCredential.CREATOR);
                if (signInCredential != null) {
                    return signInCredential;
                }
                throw new ApiException(Status.RESULT_INTERNAL_ERROR);
            } else {
                throw new ApiException(status);
            }
        } else {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
    }

    public final Task<PendingIntent> getSignInIntent(GetSignInIntentRequest getSignInIntentRequest) {
        Preconditions.checkNotNull(getSignInIntentRequest);
        GetSignInIntentRequest.Builder zba2 = GetSignInIntentRequest.zba(getSignInIntentRequest);
        zba2.zba(this.zbd);
        return doRead(TaskApiCall.builder().setFeatures(zbar.zbf).run(new zbaj(this, zba2.build())).setMethodKey(1555).build());
    }

    public final Task<Void> signOut() {
        getApplicationContext().getSharedPreferences("com.google.android.gms.signin", 0).edit().clear().apply();
        for (GoogleApiClient maybeSignOut : GoogleApiClient.getAllClients()) {
            maybeSignOut.maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
        return doWrite(TaskApiCall.builder().setFeatures(zbar.zbb).run(new zbah(this)).setAutoResolveMissingFeatures(false).setMethodKey(1554).build());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zba(GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest, zbaq zbaq, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zbv) zbaq.getService()).zbd(new zbao(this, taskCompletionSource), getPhoneNumberHintIntentRequest, this.zbd);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zbb(zbaq zbaq, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zbv) zbaq.getService()).zbf(new zbam(this, taskCompletionSource), this.zbd);
    }

    public zbap(Context context, zbs zbs) {
        super(context, zbc, zbs, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
