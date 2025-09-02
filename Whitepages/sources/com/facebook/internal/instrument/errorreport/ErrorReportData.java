package com.facebook.internal.instrument.errorreport;

import com.facebook.internal.instrument.InstrumentUtility;
import com.google.firebase.messaging.Constants;
import java.io.File;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public final class ErrorReportData {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private String errorMessage;
    private String filename;
    private Long timestamp;

    public ErrorReportData(String str) {
        this.timestamp = Long.valueOf(System.currentTimeMillis() / ((long) 1000));
        this.errorMessage = str;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("error_log_");
        Long l = this.timestamp;
        Intrinsics.checkNotNull(l, "null cannot be cast to non-null type kotlin.Long");
        stringBuffer.append(l.longValue());
        stringBuffer.append(".json");
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(stringBuffer2, "StringBuffer()\n         …)\n            .toString()");
        this.filename = stringBuffer2;
    }

    public ErrorReportData(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "file.name");
        this.filename = name;
        JSONObject readFile = InstrumentUtility.readFile(name, true);
        if (readFile != null) {
            this.timestamp = Long.valueOf(readFile.optLong("timestamp", 0));
            this.errorMessage = readFile.optString("error_message", (String) null);
        }
    }

    public final int compareTo(ErrorReportData errorReportData) {
        Intrinsics.checkNotNullParameter(errorReportData, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        Long l = this.timestamp;
        if (l == null) {
            return -1;
        }
        long longValue = l.longValue();
        Long l2 = errorReportData.timestamp;
        if (l2 != null) {
            return Intrinsics.compare(l2.longValue(), longValue);
        }
        return 1;
    }

    public final boolean isValid() {
        return (this.errorMessage == null || this.timestamp == null) ? false : true;
    }

    public final void save() {
        if (isValid()) {
            InstrumentUtility.writeFile(this.filename, toString());
        }
    }

    public final void clear() {
        InstrumentUtility.deleteFile(this.filename);
    }

    public String toString() {
        JSONObject parameters = getParameters();
        if (parameters == null) {
            return super.toString();
        }
        String jSONObject = parameters.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "params.toString()");
        return jSONObject;
    }

    public final JSONObject getParameters() {
        JSONObject jSONObject = new JSONObject();
        try {
            Long l = this.timestamp;
            if (l != null) {
                jSONObject.put("timestamp", l);
            }
            jSONObject.put("error_message", this.errorMessage);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
