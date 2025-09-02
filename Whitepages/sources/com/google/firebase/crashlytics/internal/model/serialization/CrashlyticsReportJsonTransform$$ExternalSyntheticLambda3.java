package com.google.firebase.crashlytics.internal.model.serialization;

import android.util.JsonReader;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;

public final /* synthetic */ class CrashlyticsReportJsonTransform$$ExternalSyntheticLambda3 implements CrashlyticsReportJsonTransform.ObjectParser {
    public final Object parse(JsonReader jsonReader) {
        return CrashlyticsReportJsonTransform.parseProcessDetails(jsonReader);
    }
}
