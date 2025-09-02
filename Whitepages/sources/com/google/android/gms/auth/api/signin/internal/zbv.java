package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.google.android.gms.common.api.GoogleApiClient;

final class zbv implements LoaderManager.LoaderCallbacks {
    final /* synthetic */ SignInHubActivity zba;

    /* synthetic */ zbv(SignInHubActivity signInHubActivity, zbw zbw) {
        this.zba = signInHubActivity;
    }

    public final Loader onCreateLoader(int i, Bundle bundle) {
        return new zbc(this.zba, GoogleApiClient.getAllClients());
    }

    public final /* bridge */ /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        Void voidR = (Void) obj;
        SignInHubActivity signInHubActivity = this.zba;
        signInHubActivity.setResult(signInHubActivity.zbe, signInHubActivity.zbf);
        this.zba.finish();
    }

    public final void onLoaderReset(Loader loader) {
    }
}
