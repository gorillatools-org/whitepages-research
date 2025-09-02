package com.facebook.internal.instrument;

import android.os.Build;
import com.facebook.internal.Utility;
import com.google.firebase.messaging.Constants;
import com.salesforce.marketingcloud.storage.db.k;
import java.io.File;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class InstrumentData {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private String appVersion;
    private String cause;
    private JSONArray featureNames;
    private String filename;
    private String stackTrace;
    private Long timestamp;
    private Type type;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.facebook.internal.instrument.InstrumentData$Type[] r0 = com.facebook.internal.instrument.InstrumentData.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.internal.instrument.InstrumentData$Type r1 = com.facebook.internal.instrument.InstrumentData.Type.Analysis     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.internal.instrument.InstrumentData$Type r1 = com.facebook.internal.instrument.InstrumentData.Type.AnrReport     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.internal.instrument.InstrumentData$Type r1 = com.facebook.internal.instrument.InstrumentData.Type.CrashReport     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.internal.instrument.InstrumentData$Type r1 = com.facebook.internal.instrument.InstrumentData.Type.CrashShield     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.facebook.internal.instrument.InstrumentData$Type r1 = com.facebook.internal.instrument.InstrumentData.Type.ThreadCheck     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.instrument.InstrumentData.WhenMappings.<clinit>():void");
        }
    }

    public /* synthetic */ InstrumentData(File file, DefaultConstructorMarker defaultConstructorMarker) {
        this(file);
    }

    public /* synthetic */ InstrumentData(String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2);
    }

    public /* synthetic */ InstrumentData(Throwable th, Type type2, DefaultConstructorMarker defaultConstructorMarker) {
        this(th, type2);
    }

    public /* synthetic */ InstrumentData(JSONArray jSONArray, DefaultConstructorMarker defaultConstructorMarker) {
        this(jSONArray);
    }

    public enum Type {
        Unknown,
        Analysis,
        AnrReport,
        CrashReport,
        CrashShield,
        ThreadCheck;

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
            static {
                /*
                    com.facebook.internal.instrument.InstrumentData$Type[] r0 = com.facebook.internal.instrument.InstrumentData.Type.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.facebook.internal.instrument.InstrumentData$Type r1 = com.facebook.internal.instrument.InstrumentData.Type.Analysis     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.facebook.internal.instrument.InstrumentData$Type r1 = com.facebook.internal.instrument.InstrumentData.Type.AnrReport     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    com.facebook.internal.instrument.InstrumentData$Type r1 = com.facebook.internal.instrument.InstrumentData.Type.CrashReport     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    com.facebook.internal.instrument.InstrumentData$Type r1 = com.facebook.internal.instrument.InstrumentData.Type.CrashShield     // Catch:{ NoSuchFieldError -> 0x002b }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                    r2 = 4
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                L_0x002b:
                    com.facebook.internal.instrument.InstrumentData$Type r1 = com.facebook.internal.instrument.InstrumentData.Type.ThreadCheck     // Catch:{ NoSuchFieldError -> 0x0034 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                    r2 = 5
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
                L_0x0034:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.instrument.InstrumentData.Type.WhenMappings.<clinit>():void");
            }
        }

        public String toString() {
            int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
            if (i == 1) {
                return "Analysis";
            }
            if (i == 2) {
                return "AnrReport";
            }
            if (i == 3) {
                return "CrashReport";
            }
            if (i == 4) {
                return "CrashShield";
            }
            if (i != 5) {
                return "Unknown";
            }
            return "ThreadCheck";
        }

        public final String getLogPrefix() {
            int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
            if (i == 1) {
                return "analysis_log_";
            }
            if (i == 2) {
                return "anr_log_";
            }
            if (i == 3) {
                return "crash_log_";
            }
            if (i == 4) {
                return "shield_log_";
            }
            if (i != 5) {
                return "Unknown";
            }
            return "thread_check_log_";
        }
    }

    private InstrumentData(JSONArray jSONArray) {
        this.type = Type.Analysis;
        this.timestamp = Long.valueOf(System.currentTimeMillis() / ((long) 1000));
        this.featureNames = jSONArray;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("analysis_log_");
        stringBuffer.append(String.valueOf(this.timestamp));
        stringBuffer.append(".json");
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(stringBuffer2, "StringBuffer()\n         …)\n            .toString()");
        this.filename = stringBuffer2;
    }

    private InstrumentData(Throwable th, Type type2) {
        this.type = type2;
        this.appVersion = Utility.getAppVersion();
        this.cause = InstrumentUtility.getCause(th);
        this.stackTrace = InstrumentUtility.getStackTrace(th);
        this.timestamp = Long.valueOf(System.currentTimeMillis() / ((long) 1000));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(type2.getLogPrefix());
        stringBuffer.append(String.valueOf(this.timestamp));
        stringBuffer.append(".json");
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(stringBuffer2, "StringBuffer().append(t.…ppend(\".json\").toString()");
        this.filename = stringBuffer2;
    }

    private InstrumentData(String str, String str2) {
        this.type = Type.AnrReport;
        this.appVersion = Utility.getAppVersion();
        this.cause = str;
        this.stackTrace = str2;
        this.timestamp = Long.valueOf(System.currentTimeMillis() / ((long) 1000));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("anr_log_");
        stringBuffer.append(String.valueOf(this.timestamp));
        stringBuffer.append(".json");
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(stringBuffer2, "StringBuffer()\n         …)\n            .toString()");
        this.filename = stringBuffer2;
    }

    private InstrumentData(File file) {
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "file.name");
        this.filename = name;
        this.type = Companion.getType(name);
        JSONObject readFile = InstrumentUtility.readFile(this.filename, true);
        if (readFile != null) {
            this.timestamp = Long.valueOf(readFile.optLong("timestamp", 0));
            this.appVersion = readFile.optString(k.a.q, (String) null);
            this.cause = readFile.optString("reason", (String) null);
            this.stackTrace = readFile.optString("callstack", (String) null);
            this.featureNames = readFile.optJSONArray("feature_names");
        }
    }

    public final int compareTo(InstrumentData instrumentData) {
        Intrinsics.checkNotNullParameter(instrumentData, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        Long l = this.timestamp;
        if (l == null) {
            return -1;
        }
        long longValue = l.longValue();
        Long l2 = instrumentData.timestamp;
        if (l2 != null) {
            return Intrinsics.compare(l2.longValue(), longValue);
        }
        return 1;
    }

    public final boolean isValid() {
        Type type2 = this.type;
        int i = type2 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type2.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (!(i == 3 || i == 4 || i == 5) || this.stackTrace == null || this.timestamp == null) {
                    return false;
                }
            } else if (this.stackTrace == null || this.cause == null || this.timestamp == null) {
                return false;
            }
        } else if (this.featureNames == null || this.timestamp == null) {
            return false;
        }
        return true;
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
            String jSONObject = new JSONObject().toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject, "JSONObject().toString()");
            return jSONObject;
        }
        String jSONObject2 = parameters.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "params.toString()");
        return jSONObject2;
    }

    private final JSONObject getParameters() {
        Type type2 = this.type;
        int i = type2 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type2.ordinal()];
        if (i == 1) {
            return getAnalysisReportParameters();
        }
        if (i == 2 || i == 3 || i == 4 || i == 5) {
            return getExceptionReportParameters();
        }
        return null;
    }

    private final JSONObject getAnalysisReportParameters() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = this.featureNames;
            if (jSONArray != null) {
                jSONObject.put("feature_names", jSONArray);
            }
            Long l = this.timestamp;
            if (l != null) {
                jSONObject.put("timestamp", l);
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    private final JSONObject getExceptionReportParameters() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device_os_version", Build.VERSION.RELEASE);
            jSONObject.put("device_model", Build.MODEL);
            String str = this.appVersion;
            if (str != null) {
                jSONObject.put(k.a.q, str);
            }
            Long l = this.timestamp;
            if (l != null) {
                jSONObject.put("timestamp", l);
            }
            String str2 = this.cause;
            if (str2 != null) {
                jSONObject.put("reason", str2);
            }
            String str3 = this.stackTrace;
            if (str3 != null) {
                jSONObject.put("callstack", str3);
            }
            Type type2 = this.type;
            if (type2 != null) {
                jSONObject.put("type", type2);
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static final class Builder {
        public static final Builder INSTANCE = new Builder();

        private Builder() {
        }

        public static final InstrumentData load(File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            return new InstrumentData(file, (DefaultConstructorMarker) null);
        }

        public static final InstrumentData build(Throwable th, Type type) {
            Intrinsics.checkNotNullParameter(type, "t");
            return new InstrumentData(th, type, (DefaultConstructorMarker) null);
        }

        public static final InstrumentData build(JSONArray jSONArray) {
            Intrinsics.checkNotNullParameter(jSONArray, "features");
            return new InstrumentData(jSONArray, (DefaultConstructorMarker) null);
        }

        public static final InstrumentData build(String str, String str2) {
            return new InstrumentData(str, str2, (DefaultConstructorMarker) null);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final Type getType(String str) {
            if (StringsKt.startsWith$default(str, "crash_log_", false, 2, (Object) null)) {
                return Type.CrashReport;
            }
            if (StringsKt.startsWith$default(str, "shield_log_", false, 2, (Object) null)) {
                return Type.CrashShield;
            }
            if (StringsKt.startsWith$default(str, "thread_check_log_", false, 2, (Object) null)) {
                return Type.ThreadCheck;
            }
            if (StringsKt.startsWith$default(str, "analysis_log_", false, 2, (Object) null)) {
                return Type.Analysis;
            }
            if (StringsKt.startsWith$default(str, "anr_log_", false, 2, (Object) null)) {
                return Type.AnrReport;
            }
            return Type.Unknown;
        }
    }
}
