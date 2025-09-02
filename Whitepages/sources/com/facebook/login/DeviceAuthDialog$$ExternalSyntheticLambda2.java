package com.facebook.login;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

public final /* synthetic */ class DeviceAuthDialog$$ExternalSyntheticLambda2 implements GraphRequest.Callback {
    public final /* synthetic */ DeviceAuthDialog f$0;

    public /* synthetic */ DeviceAuthDialog$$ExternalSyntheticLambda2(DeviceAuthDialog deviceAuthDialog) {
        this.f$0 = deviceAuthDialog;
    }

    public final void onCompleted(GraphResponse graphResponse) {
        DeviceAuthDialog._get_pollRequest_$lambda$5(this.f$0, graphResponse);
    }
}
