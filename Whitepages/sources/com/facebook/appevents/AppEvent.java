package com.facebook.appevents;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.eventdeactivation.EventDeactivationManager;
import com.facebook.appevents.integrity.IntegrityManager;
import com.facebook.appevents.integrity.RedactedEventsManager;
import com.facebook.appevents.restrictivedatafilter.RestrictiveDataManager;
import com.facebook.internal.Logger;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.Regex;
import org.json.JSONException;
import org.json.JSONObject;

public final class AppEvent implements Serializable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long serialVersionUID = 1;
    /* access modifiers changed from: private */
    public static final HashSet validatedIdentifiers = new HashSet();
    private final boolean inBackground;
    private final boolean isImplicit;
    private final JSONObject jsonObject;
    private final String name;
    private final JSONObject operationalJsonObject;

    public /* synthetic */ AppEvent(String str, String str2, boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, z, z2);
    }

    public final JSONObject getJsonObject() {
        return this.jsonObject;
    }

    public final JSONObject getOperationalJsonObject() {
        return this.operationalJsonObject;
    }

    public final boolean isImplicit() {
        return this.isImplicit;
    }

    public final String getName() {
        return this.name;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0015, code lost:
        r11 = r14.toJSON();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppEvent(java.lang.String r7, java.lang.String r8, java.lang.Double r9, android.os.Bundle r10, boolean r11, boolean r12, java.util.UUID r13, com.facebook.appevents.OperationalData r14) {
        /*
            r6 = this;
            java.lang.String r0 = "contextName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "eventName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r6.<init>()
            r6.isImplicit = r11
            r6.inBackground = r12
            r6.name = r8
            if (r14 == 0) goto L_0x001b
            org.json.JSONObject r11 = r14.toJSON()
            if (r11 != 0) goto L_0x0020
        L_0x001b:
            org.json.JSONObject r11 = new org.json.JSONObject
            r11.<init>()
        L_0x0020:
            r6.operationalJsonObject = r11
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r13
            org.json.JSONObject r7 = r0.getJSONObjectForAppEvent(r1, r2, r3, r4, r5)
            r6.jsonObject = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.AppEvent.<init>(java.lang.String, java.lang.String, java.lang.Double, android.os.Bundle, boolean, boolean, java.util.UUID, com.facebook.appevents.OperationalData):void");
    }

    private AppEvent(String str, String str2, boolean z, boolean z2) {
        JSONObject jSONObject = new JSONObject(str);
        this.jsonObject = jSONObject;
        this.operationalJsonObject = new JSONObject(str2);
        this.isImplicit = z;
        String optString = jSONObject.optString("_eventName");
        Intrinsics.checkNotNullExpressionValue(optString, "jsonObject.optString(Con…nts.EVENT_NAME_EVENT_KEY)");
        this.name = optString;
        this.inBackground = z2;
    }

    public final boolean getIsImplicit() {
        return this.isImplicit;
    }

    public final JSONObject getJSONObject() {
        return this.jsonObject;
    }

    private final JSONObject getJSONObjectForAppEvent(String str, String str2, Double d, Bundle bundle, UUID uuid) {
        Companion.validateIdentifier(str2);
        JSONObject jSONObject = new JSONObject();
        String processEvent = RestrictiveDataManager.processEvent(str2);
        if (Intrinsics.areEqual((Object) processEvent, (Object) str2)) {
            processEvent = RedactedEventsManager.processEventsRedaction(str2);
        }
        jSONObject.put("_eventName", processEvent);
        jSONObject.put("_logTime", System.currentTimeMillis() / ((long) 1000));
        jSONObject.put("_ui", str);
        if (uuid != null) {
            jSONObject.put("_session_id", uuid);
        }
        if (bundle != null) {
            Map validateParameters$default = validateParameters$default(this, bundle, false, 2, (Object) null);
            for (String str3 : validateParameters$default.keySet()) {
                jSONObject.put(str3, validateParameters$default.get(str3));
            }
        }
        if (d != null) {
            jSONObject.put("_valueToSum", d.doubleValue());
        }
        if (this.inBackground) {
            jSONObject.put("_inBackground", "1");
        }
        if (this.isImplicit) {
            jSONObject.put("_implicitlyLogged", "1");
        } else {
            Logger.Companion companion = Logger.Companion;
            LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "eventObject.toString()");
            companion.log(loggingBehavior, "AppEvents", "Created app event '%s'", jSONObject2);
        }
        return jSONObject;
    }

    static /* synthetic */ Map validateParameters$default(AppEvent appEvent, Bundle bundle, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return appEvent.validateParameters(bundle, z);
    }

    private final Map validateParameters(Bundle bundle, boolean z) {
        HashMap hashMap = new HashMap();
        for (String next : bundle.keySet()) {
            Companion companion = Companion;
            Intrinsics.checkNotNullExpressionValue(next, "key");
            companion.validateIdentifier(next);
            Object obj = bundle.get(next);
            if ((obj instanceof String) || (obj instanceof Number)) {
                hashMap.put(next, obj.toString());
            } else {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("Parameter value '%s' for key '%s' should be a string or a numeric type.", Arrays.copyOf(new Object[]{obj, next}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                throw new FacebookException(format);
            }
        }
        if (!z) {
            IntegrityManager.processParameters(hashMap);
            RestrictiveDataManager.processParameters(TypeIntrinsics.asMutableMap(hashMap), this.name);
            EventDeactivationManager.processDeprecatedParameters(TypeIntrinsics.asMutableMap(hashMap), this.name);
        }
        return hashMap;
    }

    public static final class SerializationProxyV2 implements Serializable {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private static final long serialVersionUID = 20160803001L;
        private final boolean inBackground;
        private final boolean isImplicit;
        private final String jsonString;
        private final String operationalJsonString;

        public SerializationProxyV2(String str, String str2, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(str, "jsonString");
            Intrinsics.checkNotNullParameter(str2, "operationalJsonString");
            this.jsonString = str;
            this.operationalJsonString = str2;
            this.isImplicit = z;
            this.inBackground = z2;
        }

        private final Object readResolve() throws JSONException, ObjectStreamException {
            return new AppEvent(this.jsonString, this.operationalJsonString, this.isImplicit, this.inBackground, (DefaultConstructorMarker) null);
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    private final Object writeReplace() throws ObjectStreamException {
        String jSONObject = this.jsonObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "jsonObject.toString()");
        String jSONObject2 = this.operationalJsonObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "operationalJsonObject.toString()");
        return new SerializationProxyV2(jSONObject, jSONObject2, this.isImplicit, this.inBackground);
    }

    public String toString() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("\"%s\", implicit: %b, json: %s", Arrays.copyOf(new Object[]{this.jsonObject.optString("_eventName"), Boolean.valueOf(this.isImplicit), this.jsonObject.toString()}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void validateIdentifier(String str) {
            boolean contains;
            Intrinsics.checkNotNullParameter(str, "identifier");
            if (str.length() == 0 || str.length() > 40) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.ROOT, "Identifier '%s' must be less than %d characters", Arrays.copyOf(new Object[]{str, 40}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                throw new FacebookException(format);
            }
            synchronized (AppEvent.validatedIdentifiers) {
                contains = AppEvent.validatedIdentifiers.contains(str);
                Unit unit = Unit.INSTANCE;
            }
            if (contains) {
                return;
            }
            if (new Regex("^[0-9a-zA-Z_]+[0-9a-zA-Z _-]*$").matches(str)) {
                synchronized (AppEvent.validatedIdentifiers) {
                    AppEvent.validatedIdentifiers.add(str);
                }
                return;
            }
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format2 = String.format("Skipping event named '%s' due to illegal name - must be under 40 chars and alphanumeric, _, - or space, and not start with a space or hyphen.", Arrays.copyOf(new Object[]{str}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            throw new FacebookException(format2);
        }
    }
}
