package com.facebook.internal.instrument;

import com.facebook.AccessToken;
import com.facebook.BuildConfig;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.internal.Utility;
import com.facebook.react.uimanager.ViewProps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class InstrumentUtility {
    public static final InstrumentUtility INSTANCE = new InstrumentUtility();

    private InstrumentUtility() {
    }

    public static final String getCause(Throwable th) {
        if (th == null) {
            return null;
        }
        if (th.getCause() == null) {
            return th.toString();
        }
        return String.valueOf(th.getCause());
    }

    public static final String getStackTrace(Throwable th) {
        Throwable th2 = null;
        if (th == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        while (th != null && th != th2) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "t.stackTrace");
            for (StackTraceElement stackTraceElement : stackTrace) {
                jSONArray.put(stackTraceElement.toString());
            }
            th2 = th;
            th = th.getCause();
        }
        return jSONArray.toString();
    }

    public static final String getStackTrace(Thread thread) {
        Intrinsics.checkNotNullParameter(thread, "thread");
        StackTraceElement[] stackTrace = thread.getStackTrace();
        JSONArray jSONArray = new JSONArray();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "stackTrace");
        for (StackTraceElement stackTraceElement : stackTrace) {
            jSONArray.put(stackTraceElement.toString());
        }
        return jSONArray.toString();
    }

    public static final boolean isSDKRelatedException(Throwable th) {
        if (th == null) {
            return false;
        }
        Throwable th2 = null;
        while (th != null && th != th2) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "t.stackTrace");
            for (StackTraceElement stackTraceElement : stackTrace) {
                Intrinsics.checkNotNullExpressionValue(stackTraceElement, "element");
                if (isFromFbOrMeta(stackTraceElement)) {
                    return true;
                }
            }
            th2 = th;
            th = th.getCause();
        }
        return false;
    }

    public static final boolean isSDKRelatedThread(Thread thread) {
        StackTraceElement[] stackTrace;
        if (!(thread == null || (stackTrace = thread.getStackTrace()) == null)) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                Intrinsics.checkNotNullExpressionValue(stackTraceElement, "element");
                if (isFromFbOrMeta(stackTraceElement)) {
                    String className = stackTraceElement.getClassName();
                    Intrinsics.checkNotNullExpressionValue(className, "element.className");
                    if (!StringsKt.startsWith$default(className, "com.facebook.appevents.codeless", false, 2, (Object) null)) {
                        String className2 = stackTraceElement.getClassName();
                        Intrinsics.checkNotNullExpressionValue(className2, "element.className");
                        if (!StringsKt.startsWith$default(className2, "com.facebook.appevents.suggestedevents", false, 2, (Object) null)) {
                            return true;
                        }
                    }
                    String methodName = stackTraceElement.getMethodName();
                    Intrinsics.checkNotNullExpressionValue(methodName, "element.methodName");
                    if (!StringsKt.startsWith$default(methodName, ViewProps.ON_CLICK, false, 2, (Object) null)) {
                        String methodName2 = stackTraceElement.getMethodName();
                        Intrinsics.checkNotNullExpressionValue(methodName2, "element.methodName");
                        if (!StringsKt.startsWith$default(methodName2, "onItemClick", false, 2, (Object) null)) {
                            String methodName3 = stackTraceElement.getMethodName();
                            Intrinsics.checkNotNullExpressionValue(methodName3, "element.methodName");
                            if (!StringsKt.startsWith$default(methodName3, "onTouch", false, 2, (Object) null)) {
                                return true;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return false;
    }

    public static final File[] listAnrReportFiles() {
        File instrumentReportDir = getInstrumentReportDir();
        if (instrumentReportDir == null) {
            return new File[0];
        }
        File[] listFiles = instrumentReportDir.listFiles(new InstrumentUtility$$ExternalSyntheticLambda1());
        return listFiles == null ? new File[0] : listFiles;
    }

    /* access modifiers changed from: private */
    public static final boolean listAnrReportFiles$lambda$1(File file, String str) {
        Intrinsics.checkNotNullExpressionValue(str, "name");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("^%s[0-9]+.json$", Arrays.copyOf(new Object[]{"anr_log_"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return new Regex(format).matches(str);
    }

    public static final File[] listExceptionAnalysisReportFiles() {
        File instrumentReportDir = getInstrumentReportDir();
        if (instrumentReportDir == null) {
            return new File[0];
        }
        File[] listFiles = instrumentReportDir.listFiles(new InstrumentUtility$$ExternalSyntheticLambda2());
        return listFiles == null ? new File[0] : listFiles;
    }

    /* access modifiers changed from: private */
    public static final boolean listExceptionAnalysisReportFiles$lambda$2(File file, String str) {
        Intrinsics.checkNotNullExpressionValue(str, "name");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("^%s[0-9]+.json$", Arrays.copyOf(new Object[]{"analysis_log_"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return new Regex(format).matches(str);
    }

    public static final File[] listExceptionReportFiles() {
        File instrumentReportDir = getInstrumentReportDir();
        if (instrumentReportDir == null) {
            return new File[0];
        }
        File[] listFiles = instrumentReportDir.listFiles(new InstrumentUtility$$ExternalSyntheticLambda0());
        return listFiles == null ? new File[0] : listFiles;
    }

    /* access modifiers changed from: private */
    public static final boolean listExceptionReportFiles$lambda$3(File file, String str) {
        Intrinsics.checkNotNullExpressionValue(str, "name");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("^(%s|%s|%s)[0-9]+.json$", Arrays.copyOf(new Object[]{"crash_log_", "shield_log_", "thread_check_log_"}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return new Regex(format).matches(str);
    }

    public static final JSONObject readFile(String str, boolean z) {
        File instrumentReportDir = getInstrumentReportDir();
        if (!(instrumentReportDir == null || str == null)) {
            try {
                return new JSONObject(Utility.readStreamToString(new FileInputStream(new File(instrumentReportDir, str))));
            } catch (Exception unused) {
                if (z) {
                    deleteFile(str);
                }
            }
        }
        return null;
    }

    public static final void writeFile(String str, String str2) {
        File instrumentReportDir = getInstrumentReportDir();
        if (instrumentReportDir != null && str != null && str2 != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(instrumentReportDir, str));
                byte[] bytes = str2.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                fileOutputStream.write(bytes);
                fileOutputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static final boolean deleteFile(String str) {
        File instrumentReportDir = getInstrumentReportDir();
        if (instrumentReportDir == null || str == null) {
            return false;
        }
        return new File(instrumentReportDir, str).delete();
    }

    public static final void sendReports(String str, JSONArray jSONArray, GraphRequest.Callback callback) {
        Intrinsics.checkNotNullParameter(jSONArray, "reports");
        if (jSONArray.length() != 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(str, jSONArray.toString());
                JSONObject dataProcessingOptions = Utility.getDataProcessingOptions();
                if (dataProcessingOptions != null) {
                    Iterator<String> keys = dataProcessingOptions.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        jSONObject.put(next, dataProcessingOptions.get(next));
                    }
                }
                GraphRequest.Companion companion = GraphRequest.Companion;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%s/instruments", Arrays.copyOf(new Object[]{FacebookSdk.getApplicationId()}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                companion.newPostRequest((AccessToken) null, format, jSONObject, callback).executeAsync();
            } catch (JSONException unused) {
            }
        }
    }

    public static final File getInstrumentReportDir() {
        File file = new File(FacebookSdk.getApplicationContext().getCacheDir(), "instrument");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        return null;
    }

    public static final boolean isFromFbOrMeta(StackTraceElement stackTraceElement) {
        Intrinsics.checkNotNullParameter(stackTraceElement, "element");
        String className = stackTraceElement.getClassName();
        Intrinsics.checkNotNullExpressionValue(className, "element.className");
        if (!StringsKt.startsWith$default(className, BuildConfig.LIBRARY_PACKAGE_NAME, false, 2, (Object) null)) {
            String className2 = stackTraceElement.getClassName();
            Intrinsics.checkNotNullExpressionValue(className2, "element.className");
            return StringsKt.startsWith$default(className2, "com.meta", false, 2, (Object) null);
        }
    }
}
