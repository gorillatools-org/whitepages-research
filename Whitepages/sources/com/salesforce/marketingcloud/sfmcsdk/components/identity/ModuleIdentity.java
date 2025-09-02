package com.salesforce.marketingcloud.sfmcsdk.components.identity;

import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public abstract class ModuleIdentity {
    private final String applicationId;
    private Map<String, Object> customProperties = new LinkedHashMap();
    private String installationId;
    private final ModuleIdentifier moduleName;
    private String profileId;

    public abstract JSONObject customPropertiesToJson(Map<String, ? extends Object> map);

    public ModuleIdentity(ModuleIdentifier moduleIdentifier, String str) {
        Intrinsics.checkNotNullParameter(moduleIdentifier, "moduleName");
        Intrinsics.checkNotNullParameter(str, "applicationId");
        this.moduleName = moduleIdentifier;
        this.applicationId = str;
    }

    public final ModuleIdentifier getModuleName() {
        return this.moduleName;
    }

    public final String getApplicationId() {
        return this.applicationId;
    }

    public final String getProfileId() {
        return this.profileId;
    }

    public final void setProfileId(String str) {
        if (!Intrinsics.areEqual((Object) str, (Object) this.profileId)) {
            this.profileId = str;
        }
    }

    public final Map<String, Object> getCustomProperties() {
        return this.customProperties;
    }

    public final void setCustomProperties(Map<String, Object> map) {
        Intrinsics.checkNotNullParameter(map, "value");
        if (!Intrinsics.areEqual((Object) map, (Object) this.customProperties)) {
            this.customProperties = map;
        }
    }

    public final String getInstallationId() {
        return this.installationId;
    }

    public final void setInstallationId(String str) {
        if (!Intrinsics.areEqual((Object) str, (Object) this.installationId)) {
            this.installationId = str;
        }
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("profileId", this.profileId);
        jSONObject.put("applicationId", this.applicationId);
        jSONObject.put("installationId", this.installationId);
        jSONObject.put("customProperties", customPropertiesToJson(this.customProperties));
        return jSONObject;
    }

    public String toString() {
        String jSONObject = toJson().toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "toJson().toString()");
        return jSONObject;
    }
}
