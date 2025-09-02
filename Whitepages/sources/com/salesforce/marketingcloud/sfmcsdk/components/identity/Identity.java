package com.salesforce.marketingcloud.sfmcsdk.components.identity;

import com.google.android.gms.common.Scopes;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import com.salesforce.marketingcloud.storage.db.h;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.json.JSONObject;

public final class Identity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "~$Identity";
    /* access modifiers changed from: private */
    public static Identity _instance;
    /* access modifiers changed from: private */
    public final Map<ModuleIdentifier, ModuleIdentity> _moduleIdentities;
    private final String platform;
    private final String registrationId;
    private final List<String> reservedKeys;

    public /* synthetic */ Identity(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    public final void clearProfileAttribute(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        clearProfileAttribute$default(this, str, (ModuleIdentifier[]) null, 2, (Object) null);
    }

    public final void clearProfileAttributes(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "keys");
        clearProfileAttributes$default(this, list, (ModuleIdentifier[]) null, 2, (Object) null);
    }

    public final void setProfileAttribute(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        setProfileAttribute$default(this, str, str2, (ModuleIdentifier[]) null, 4, (Object) null);
    }

    public final void setProfileAttributes(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, k.a.h);
        setProfileAttributes$default(this, map, (ModuleIdentifier[]) null, 2, (Object) null);
    }

    public final void setProfileId(String str) {
        Intrinsics.checkNotNullParameter(str, "id");
        setProfileId$default(this, str, (ModuleIdentifier[]) null, 2, (Object) null);
    }

    private Identity(String str) {
        this.registrationId = str;
        this.platform = "Android";
        this._moduleIdentities = new LinkedHashMap();
        this.reservedKeys = CollectionsKt.listOf("deviceid", "userid", "eventid", "sessionid", "datetime", "eventtype", "category", h.a.b, h.a.c);
    }

    public final String getRegistrationId() {
        return this.registrationId;
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final Map<ModuleIdentifier, ModuleIdentity> getModuleIdentities() {
        return this._moduleIdentities;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void get_instance$annotations() {
        }

        private Companion() {
        }

        public final Identity getInstance() {
            Identity access$get_instance$cp = Identity._instance;
            if (access$get_instance$cp != null) {
                return access$get_instance$cp;
            }
            throw new IllegalStateException("You must initialize the SDK before attempting to use Identity.");
        }

        public final void setInstance(Identity identity) {
            Intrinsics.checkNotNullParameter(identity, "value");
            SFMCSdkLogger.INSTANCE.d(Identity.TAG, new Identity$Companion$instance$1(identity));
            Identity._instance = identity;
        }

        public final Identity create$sfmcsdk_release(String str) {
            Intrinsics.checkNotNullParameter(str, "registrationId");
            Identity access$get_instance$cp = Identity._instance;
            if (access$get_instance$cp != null) {
                return access$get_instance$cp;
            }
            Identity identity = new Identity(str, (DefaultConstructorMarker) null);
            Identity.Companion.setInstance(identity);
            return identity;
        }

        public final Map<String, Object> toEvent$sfmcsdk_release() {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Identity access$get_instance$cp = Identity._instance;
            if (access$get_instance$cp != null) {
                linkedHashMap.put(k.a.b, access$get_instance$cp.getPlatform());
                linkedHashMap.put("registrationId", access$get_instance$cp.getRegistrationId());
                JSONObject jSONObject = new JSONObject();
                for (Map.Entry entry : access$get_instance$cp._moduleIdentities.entrySet()) {
                    String lowerCase = ((ModuleIdentifier) entry.getKey()).name().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
                    jSONObject.put(lowerCase, ((ModuleIdentity) entry.getValue()).toJson());
                }
                Unit unit = Unit.INSTANCE;
                linkedHashMap.put("moduleIdentities", jSONObject);
            }
            return linkedHashMap;
        }
    }

    public static /* synthetic */ void setProfileId$default(Identity identity, String str, ModuleIdentifier[] moduleIdentifierArr, int i, Object obj) {
        if ((i & 2) != 0) {
            moduleIdentifierArr = ModuleIdentifier.values();
        }
        identity.setProfileId(str, moduleIdentifierArr);
    }

    public final void setProfileId(String str, ModuleIdentifier... moduleIdentifierArr) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(moduleIdentifierArr, "modules");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ModuleIdentifier put : moduleIdentifierArr) {
            linkedHashMap.put(put, str);
        }
        setProfileId((Map<ModuleIdentifier, String>) linkedHashMap);
    }

    public final void setProfileId(Map<ModuleIdentifier, String> map) {
        Intrinsics.checkNotNullParameter(map, "ids");
        synchronized (this._moduleIdentities) {
            try {
                for (Map.Entry entry : map.entrySet()) {
                    ModuleIdentity moduleIdentity = this._moduleIdentities.get(entry.getKey());
                    if (moduleIdentity != null) {
                        moduleIdentity.setProfileId((String) entry.getValue());
                    }
                }
                Event identityEvent$sfmcsdk_release = EventManager.Companion.identityEvent$sfmcsdk_release();
                if (identityEvent$sfmcsdk_release != null) {
                    identityEvent$sfmcsdk_release.track();
                    Unit unit = Unit.INSTANCE;
                }
            } finally {
            }
        }
    }

    public static /* synthetic */ void setProfileAttribute$default(Identity identity, String str, String str2, ModuleIdentifier[] moduleIdentifierArr, int i, Object obj) {
        if ((i & 4) != 0) {
            moduleIdentifierArr = ModuleIdentifier.values();
        }
        identity.setProfileAttribute(str, str2, moduleIdentifierArr);
    }

    public final void setProfileAttribute(String str, String str2, ModuleIdentifier... moduleIdentifierArr) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(moduleIdentifierArr, "modules");
        setProfileAttributes(MapsKt.mapOf(TuplesKt.to(str, str2)), (ModuleIdentifier[]) Arrays.copyOf(moduleIdentifierArr, moduleIdentifierArr.length));
    }

    public static /* synthetic */ void setProfileAttributes$default(Identity identity, Map map, ModuleIdentifier[] moduleIdentifierArr, int i, Object obj) {
        if ((i & 2) != 0) {
            moduleIdentifierArr = ModuleIdentifier.values();
        }
        identity.setProfileAttributes(map, moduleIdentifierArr);
    }

    public final void setProfileAttributes(Map<String, String> map, ModuleIdentifier... moduleIdentifierArr) {
        Intrinsics.checkNotNullParameter(map, k.a.h);
        Intrinsics.checkNotNullParameter(moduleIdentifierArr, "modules");
        synchronized (this._moduleIdentities) {
            try {
                for (ModuleIdentifier moduleIdentifier : moduleIdentifierArr) {
                    ModuleIdentity moduleIdentity = this._moduleIdentities.get(moduleIdentifier);
                    if (moduleIdentity != null) {
                        moduleIdentity.getCustomProperties().put(k.a.h, map);
                    }
                }
                Unit unit = Unit.INSTANCE;
            } finally {
            }
        }
        Event identityEvent$sfmcsdk_release = EventManager.Companion.identityEvent$sfmcsdk_release();
        if (identityEvent$sfmcsdk_release != null) {
            identityEvent$sfmcsdk_release.track();
        }
    }

    public static /* synthetic */ void clearProfileAttribute$default(Identity identity, String str, ModuleIdentifier[] moduleIdentifierArr, int i, Object obj) {
        if ((i & 2) != 0) {
            moduleIdentifierArr = ModuleIdentifier.values();
        }
        identity.clearProfileAttribute(str, moduleIdentifierArr);
    }

    public final void clearProfileAttribute(String str, ModuleIdentifier... moduleIdentifierArr) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(moduleIdentifierArr, "modules");
        setProfileAttributes(MapsKt.mapOf(TuplesKt.to(str, "")), (ModuleIdentifier[]) Arrays.copyOf(moduleIdentifierArr, moduleIdentifierArr.length));
    }

    public static /* synthetic */ void clearProfileAttributes$default(Identity identity, List list, ModuleIdentifier[] moduleIdentifierArr, int i, Object obj) {
        if ((i & 2) != 0) {
            moduleIdentifierArr = ModuleIdentifier.values();
        }
        identity.clearProfileAttributes(list, moduleIdentifierArr);
    }

    public final void clearProfileAttributes(List<String> list, ModuleIdentifier... moduleIdentifierArr) {
        Intrinsics.checkNotNullParameter(list, "keys");
        Intrinsics.checkNotNullParameter(moduleIdentifierArr, "modules");
        Iterable<String> iterable = list;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
        for (String put : iterable) {
            linkedHashMap.put(put, "");
        }
        setProfileAttributes(linkedHashMap, (ModuleIdentifier[]) Arrays.copyOf(moduleIdentifierArr, moduleIdentifierArr.length));
    }

    public final void setProfile(String str, Map<String, String> map, ModuleIdentifier moduleIdentifier, ModuleIdentifier... moduleIdentifierArr) {
        Intrinsics.checkNotNullParameter(str, "profileId");
        Intrinsics.checkNotNullParameter(map, k.a.h);
        Intrinsics.checkNotNullParameter(moduleIdentifier, "module");
        Intrinsics.checkNotNullParameter(moduleIdentifierArr, "modules");
        setProfile(new Profile(str, map), moduleIdentifier, (ModuleIdentifier[]) Arrays.copyOf(moduleIdentifierArr, moduleIdentifierArr.length));
    }

    public final void setProfile(Profile profile, ModuleIdentifier moduleIdentifier, ModuleIdentifier... moduleIdentifierArr) {
        Intrinsics.checkNotNullParameter(profile, Scopes.PROFILE);
        Intrinsics.checkNotNullParameter(moduleIdentifier, "module");
        Intrinsics.checkNotNullParameter(moduleIdentifierArr, "modules");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(moduleIdentifier, profile);
        for (ModuleIdentifier moduleIdentifier2 : moduleIdentifierArr) {
            if (!Intrinsics.areEqual((Object) moduleIdentifier2.name(), (Object) moduleIdentifier.name())) {
                linkedHashMap.put(moduleIdentifier2, profile);
            }
        }
        setProfile(linkedHashMap);
    }

    public final void setProfile(Map<ModuleIdentifier, Profile> map) {
        Intrinsics.checkNotNullParameter(map, "identities");
        synchronized (this._moduleIdentities) {
            try {
                for (Map.Entry next : map.entrySet()) {
                    ModuleIdentity moduleIdentity = this._moduleIdentities.get(next.getKey());
                    if (moduleIdentity != null) {
                        moduleIdentity.setProfileId(((Profile) next.getValue()).getProfileId());
                        moduleIdentity.getCustomProperties().put(k.a.h, MapsKt.toMutableMap(((Profile) next.getValue()).getAttributes()));
                    }
                }
                Unit unit = Unit.INSTANCE;
            } finally {
            }
        }
        Event identityEvent$sfmcsdk_release = EventManager.Companion.identityEvent$sfmcsdk_release();
        if (identityEvent$sfmcsdk_release != null) {
            identityEvent$sfmcsdk_release.track();
        }
    }

    public final void setModuleIdentity$sfmcsdk_release(ModuleIdentity moduleIdentity) {
        Intrinsics.checkNotNullParameter(moduleIdentity, "moduleIdentity");
        synchronized (this._moduleIdentities) {
            this._moduleIdentities.put(moduleIdentity.getModuleName(), moduleIdentity);
            Unit unit = Unit.INSTANCE;
        }
    }

    private final boolean isValidEventAttributeValue(Object obj) {
        return (obj instanceof Number ? true : obj instanceof Boolean ? true : obj instanceof String ? true : obj instanceof Character) || obj == null;
    }

    private final String validatedEventAttributeKey(String str) {
        String joinToString$default = CollectionsKt.joinToString$default(this.reservedKeys, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        String obj = StringsKt.trim(str).toString();
        if (StringsKt.isBlank(str)) {
            SFMCSdkLogger.INSTANCE.w(TAG, new Identity$validatedEventAttributeKey$1(str));
        } else {
            List<String> list = this.reservedKeys;
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "US");
            String lowerCase = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (list.contains(lowerCase)) {
                SFMCSdkLogger.INSTANCE.w(TAG, new Identity$validatedEventAttributeKey$2(str, joinToString$default));
            } else if (Intrinsics.areEqual((Object) str, (Object) obj)) {
                return obj;
            } else {
                SFMCSdkLogger.INSTANCE.w(TAG, new Identity$validatedEventAttributeKey$3(str, obj));
                return obj;
            }
        }
        return null;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(k.a.b, this.platform);
        jSONObject.put("registrationId", this.registrationId);
        JSONObject jSONObject2 = new JSONObject();
        for (Map.Entry next : this._moduleIdentities.entrySet()) {
            String lowerCase = ((ModuleIdentifier) next.getKey()).name().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            jSONObject2.put(lowerCase, ((ModuleIdentity) next.getValue()).toJson());
        }
        Unit unit = Unit.INSTANCE;
        jSONObject.put("moduleIdentities", jSONObject2);
        return jSONObject;
    }

    public String toString() {
        String jSONObject = toJson().toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "toJson().toString()");
        return jSONObject;
    }
}
