package com.google.firebase.crashlytics.internal.send;

import com.google.android.datatransport.Transformer;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.nio.charset.Charset;

public final /* synthetic */ class DataTransportCrashlyticsReportSender$$ExternalSyntheticLambda0 implements Transformer {
    public final Object apply(Object obj) {
        return DataTransportCrashlyticsReportSender.TRANSFORM.reportToJson((CrashlyticsReport) obj).getBytes(Charset.forName("UTF-8"));
    }
}
