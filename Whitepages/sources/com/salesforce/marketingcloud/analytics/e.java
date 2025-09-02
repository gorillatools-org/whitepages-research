package com.salesforce.marketingcloud.analytics;

import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.registration.f;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.Identity;
import com.salesforce.marketingcloud.storage.db.k;
import com.salesforce.marketingcloud.util.l;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public final class e {
    private final f a;
    private final PushMessageManager b;
    private final boolean c;
    private final Identity d;

    public e(f fVar, PushMessageManager pushMessageManager, boolean z, Identity identity) {
        Intrinsics.checkNotNullParameter(fVar, "registrationMeta");
        this.a = fVar;
        this.b = pushMessageManager;
        this.c = z;
        this.d = identity;
    }

    private final f a() {
        return this.a;
    }

    private final PushMessageManager b() {
        return this.b;
    }

    private final boolean c() {
        return this.c;
    }

    private final Identity d() {
        return this.d;
    }

    public final JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("deviceID", this.a.f());
        jSONObject.put("etAppId", this.a.d());
        jSONObject.put(k.a.m, this.a.g());
        jSONObject.put(k.a.b, this.a.h());
        jSONObject.put("platform_Version", this.a.i());
        jSONObject.put("sdk_Version", this.a.j());
        jSONObject.put("app_Version", this.a.e());
        jSONObject.put("locale", Locale.getDefault().toString());
        jSONObject.put(RemoteConfigConstants.RequestFieldKey.TIME_ZONE, l.b());
        jSONObject.put("location_Enabled", this.c);
        PushMessageManager pushMessageManager = this.b;
        if (pushMessageManager != null) {
            jSONObject.put("backgroundRefreshEnabled", pushMessageManager.isPushEnabled());
            jSONObject.put("push_Enabled", pushMessageManager.isPushEnabled());
        }
        Identity identity = this.d;
        if (identity != null) {
            jSONObject.put(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY, identity.toJson());
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return Intrinsics.areEqual((Object) this.a, (Object) eVar.a) && Intrinsics.areEqual((Object) this.b, (Object) eVar.b) && this.c == eVar.c && Intrinsics.areEqual((Object) this.d, (Object) eVar.d);
    }

    public int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        PushMessageManager pushMessageManager = this.b;
        int i = 0;
        int hashCode2 = (hashCode + (pushMessageManager == null ? 0 : pushMessageManager.hashCode())) * 31;
        boolean z = this.c;
        if (z) {
            z = true;
        }
        int i2 = (hashCode2 + (z ? 1 : 0)) * 31;
        Identity identity = this.d;
        if (identity != null) {
            i = identity.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        f fVar = this.a;
        PushMessageManager pushMessageManager = this.b;
        boolean z = this.c;
        Identity identity = this.d;
        return "EventMetaData(registrationMeta=" + fVar + ", pushMessageManager=" + pushMessageManager + ", locationEnabled=" + z + ", identity=" + identity + ")";
    }

    public final e a(f fVar, PushMessageManager pushMessageManager, boolean z, Identity identity) {
        Intrinsics.checkNotNullParameter(fVar, "registrationMeta");
        return new e(fVar, pushMessageManager, z, identity);
    }

    public static /* synthetic */ e a(e eVar, f fVar, PushMessageManager pushMessageManager, boolean z, Identity identity, int i, Object obj) {
        if ((i & 1) != 0) {
            fVar = eVar.a;
        }
        if ((i & 2) != 0) {
            pushMessageManager = eVar.b;
        }
        if ((i & 4) != 0) {
            z = eVar.c;
        }
        if ((i & 8) != 0) {
            identity = eVar.d;
        }
        return eVar.a(fVar, pushMessageManager, z, identity);
    }
}
