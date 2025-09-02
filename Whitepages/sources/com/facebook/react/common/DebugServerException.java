package com.facebook.react.common;

import android.net.Uri;
import com.facebook.common.logging.FLog;
import com.facebook.react.devsupport.StackTraceHelper;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

public final class DebugServerException extends RuntimeException {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String GENERIC_ERROR_MESSAGE = "\n\nTry the following to fix the issue:\n\\u2022 Ensure that Metro is running\n\\u2022 Ensure that your device/emulator is connected to your machine and has USB debugging enabled - run 'adb devices' to see a list of connected devices\n\\u2022 Ensure Airplane Mode is disabled\n\\u2022 If you're on a physical device connected to the same machine, run 'adb reverse tcp:<PORT> tcp:<PORT> to forward requests from your device\n\\u2022 If your device is on the same Wi-Fi network, set 'Debug server host & port for device' in 'Dev settings' to your machine's IP address and the port of the local dev server - e.g. 10.0.1.1:<PORT>\n\n";
    private final String originalMessage;

    public /* synthetic */ DebugServerException(String str, String str2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, i2);
    }

    public static final DebugServerException makeGeneric(String str, String str2, String str3, Throwable th) {
        return Companion.makeGeneric(str, str2, str3, th);
    }

    public static final DebugServerException makeGeneric(String str, String str2, Throwable th) {
        return Companion.makeGeneric(str, str2, th);
    }

    public static final DebugServerException parse(String str, String str2) {
        return Companion.parse(str, str2);
    }

    public final String getOriginalMessage() {
        return this.originalMessage;
    }

    private DebugServerException(String str, String str2, int i, int i2) {
        super(str + "\n  at " + str2 + ":" + i + ":" + i2);
        this.originalMessage = str;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DebugServerException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "description");
        this.originalMessage = str;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DebugServerException(String str, Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(str, "detailMessage");
        this.originalMessage = str;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DebugServerException makeGeneric(String str, String str2, Throwable th) {
            Intrinsics.checkNotNullParameter(str, "url");
            Intrinsics.checkNotNullParameter(str2, "reason");
            return makeGeneric(str, str2, "", th);
        }

        public final DebugServerException makeGeneric(String str, String str2, String str3, Throwable th) {
            Intrinsics.checkNotNullParameter(str, "url");
            Intrinsics.checkNotNullParameter(str2, "reason");
            Intrinsics.checkNotNullParameter(str3, "extra");
            String replace$default = StringsKt.replace$default(DebugServerException.GENERIC_ERROR_MESSAGE, "<PORT>", String.valueOf(Uri.parse(str).getPort()), false, 4, (Object) null);
            return new DebugServerException(str2 + replace$default + str3, th);
        }

        public final DebugServerException parse(String str, String str2) {
            if (!(str2 == null || str2.length() == 0)) {
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    String string = jSONObject.getString("filename");
                    String string2 = jSONObject.getString(StackTraceHelper.MESSAGE_KEY);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    Intrinsics.checkNotNull(string);
                    return new DebugServerException(string2, shortenFileName(string), jSONObject.getInt("lineNumber"), jSONObject.getInt("column"), (DefaultConstructorMarker) null);
                } catch (JSONException e) {
                    FLog.w(ReactConstants.TAG, "Could not parse DebugServerException from: " + str2, (Throwable) e);
                }
            }
            return null;
        }

        private final String shortenFileName(String str) {
            List list;
            List split = new Regex(RemoteSettings.FORWARD_SLASH_STRING).split(str, 0);
            if (!split.isEmpty()) {
                ListIterator listIterator = split.listIterator(split.size());
                while (true) {
                    if (listIterator.hasPrevious()) {
                        if (((String) listIterator.previous()).length() != 0) {
                            list = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            list = CollectionsKt.emptyList();
            return (String) ArraysKt.last((String[]) list.toArray(new String[0]));
        }
    }
}
