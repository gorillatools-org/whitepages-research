package com.google.android.gms.fido.u2f;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.fido.u2f.api.common.RegisterRequestParams;
import com.google.android.gms.fido.u2f.api.common.SignRequestParams;
import com.google.android.gms.internal.fido.zzx;
import com.google.android.gms.tasks.Task;

@Deprecated
public class U2fApiClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api.ClientKey zza;
    private static final Api zzb;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        zza = clientKey;
        zzb = new Api("Fido.U2F_API", new zzx(), clientKey);
    }

    public U2fApiClient(Activity activity) {
        super(activity, zzb, Api.ApiOptions.NO_OPTIONS, (StatusExceptionMapper) new ApiExceptionMapper());
    }

    public Task<U2fPendingIntent> getRegisterIntent(RegisterRequestParams registerRequestParams) {
        return doRead(TaskApiCall.builder().setMethodKey(5424).run(new zzb(this, registerRequestParams)).build());
    }

    public Task<U2fPendingIntent> getSignIntent(SignRequestParams signRequestParams) {
        return doRead(TaskApiCall.builder().setMethodKey(5425).run(new zza(this, signRequestParams)).build());
    }

    public U2fApiClient(Context context) {
        super(context, zzb, Api.ApiOptions.NO_OPTIONS, (StatusExceptionMapper) new ApiExceptionMapper());
    }
}
