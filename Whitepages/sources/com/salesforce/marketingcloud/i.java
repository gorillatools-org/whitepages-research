package com.salesforce.marketingcloud;

import com.salesforce.marketingcloud.registration.RegistrationManager;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.ModuleIdentity;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

public final class i extends ModuleIdentity {
    public static final a b = new a((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static ModuleIdentity c;
    private final RegistrationManager a;

    public static final class a {
        private a() {
        }

        public final ModuleIdentity a(String str, RegistrationManager registrationManager) {
            Intrinsics.checkNotNullParameter(str, "applicationId");
            Intrinsics.checkNotNullParameter(registrationManager, "registrationManager");
            if (i.c == null) {
                i.c = new i(str, registrationManager, (DefaultConstructorMarker) null);
            }
            return i.c;
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private i(String str, RegistrationManager registrationManager) {
        super(ModuleIdentifier.PUSH, str);
        this.a = registrationManager;
        if (registrationManager != null) {
            setProfileId(registrationManager.getContactKey());
            setInstallationId(registrationManager.getDeviceId());
            HashMap hashMap = new HashMap();
            hashMap.put("deviceId", registrationManager.getDeviceId());
            hashMap.put(k.a.h, registrationManager.getAttributes());
            hashMap.put(k.a.g, registrationManager.getTags());
            setCustomProperties(hashMap);
        }
    }

    public JSONObject customPropertiesToJson(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "customProperties");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("deviceId", map.get("deviceId"));
            Object obj = map.get(k.a.h);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.Any?, kotlin.Any?>");
            jSONObject.put(k.a.h, new JSONObject(TypeIntrinsics.asMutableMap(obj)));
            jSONObject.put(k.a.g, new JSONArray(this.a.getTags()));
            return jSONObject;
        } catch (Exception e) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("error", e.getMessage());
            return jSONObject2;
        }
    }

    public /* synthetic */ i(String str, RegistrationManager registrationManager, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, registrationManager);
    }

    public static final ModuleIdentity a(String str, RegistrationManager registrationManager) {
        return b.a(str, registrationManager);
    }
}
