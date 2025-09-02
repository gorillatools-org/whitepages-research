package com.facebook.internal;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.internal.WebDialog;
import java.util.concurrent.CountDownLatch;

public final /* synthetic */ class WebDialog$UploadStagingResourcesTask$$ExternalSyntheticLambda0 implements GraphRequest.Callback {
    public final /* synthetic */ String[] f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ WebDialog.UploadStagingResourcesTask f$2;
    public final /* synthetic */ CountDownLatch f$3;

    public /* synthetic */ WebDialog$UploadStagingResourcesTask$$ExternalSyntheticLambda0(String[] strArr, int i, WebDialog.UploadStagingResourcesTask uploadStagingResourcesTask, CountDownLatch countDownLatch) {
        this.f$0 = strArr;
        this.f$1 = i;
        this.f$2 = uploadStagingResourcesTask;
        this.f$3 = countDownLatch;
    }

    public final void onCompleted(GraphResponse graphResponse) {
        WebDialog.UploadStagingResourcesTask.doInBackground$lambda$0(this.f$0, this.f$1, this.f$2, this.f$3, graphResponse);
    }
}
