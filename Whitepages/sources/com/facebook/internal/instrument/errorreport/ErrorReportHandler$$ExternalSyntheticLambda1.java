package com.facebook.internal.instrument.errorreport;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import java.util.ArrayList;

public final /* synthetic */ class ErrorReportHandler$$ExternalSyntheticLambda1 implements GraphRequest.Callback {
    public final /* synthetic */ ArrayList f$0;

    public /* synthetic */ ErrorReportHandler$$ExternalSyntheticLambda1(ArrayList arrayList) {
        this.f$0 = arrayList;
    }

    public final void onCompleted(GraphResponse graphResponse) {
        ErrorReportHandler.sendErrorReports$lambda$2(this.f$0, graphResponse);
    }
}
