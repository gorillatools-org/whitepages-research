package com.salesforce.marketingcloud.registration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.e;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.http.c;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.registration.RegistrationManager;
import com.salesforce.marketingcloud.registration.e;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.sfmcsdk.components.events.EventSubscriber;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import com.salesforce.marketingcloud.storage.db.k;
import com.salesforce.marketingcloud.storage.j;
import com.salesforce.marketingcloud.util.h;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONObject;

@SuppressLint({"UnknownNullness"})
public class d implements e, RegistrationManager, com.salesforce.marketingcloud.behaviors.b, b.C0003b, c.C0017c, e.f, EventSubscriber {
    private static final EnumSet<com.salesforce.marketingcloud.behaviors.a> o = EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_PACKAGE_REPLACED, new com.salesforce.marketingcloud.behaviors.a[]{com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_TIME_ZONE_CHANGED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_FENCE_MESSAGING_TOGGLED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_PROXIMITY_MESSAGING_TOGGLED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_PUSH_MESSAGING_TOGGLED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_TOKEN_REFRESHED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_BACKGROUNDED});
    private final Context d;
    private final MarketingCloudConfig e;
    private final j f;
    private final com.salesforce.marketingcloud.behaviors.c g;
    private final com.salesforce.marketingcloud.alarms.b h;
    private final com.salesforce.marketingcloud.http.c i;
    private final PushMessageManager j;
    private final l k;
    private final SFMCSdkComponents l;
    private final f m;

    /* renamed from: n  reason: collision with root package name */
    private e f39n;

    class a implements com.salesforce.marketingcloud.storage.c {
        a() {
        }

        public void a() {
        }

        public void a(String str) {
        }

        public void a(String str, String str2) {
        }

        public String b(String str, String str2) {
            return str2;
        }
    }

    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|(2:1|2)|3|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(21:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x004f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0065 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0023 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002e */
        static {
            /*
                com.salesforce.marketingcloud.alarms.a$a[] r0 = com.salesforce.marketingcloud.alarms.a.C0001a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                b = r0
                r1 = 1
                com.salesforce.marketingcloud.alarms.a$a r2 = com.salesforce.marketingcloud.alarms.a.C0001a.REGISTRATION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.salesforce.marketingcloud.behaviors.a[] r0 = com.salesforce.marketingcloud.behaviors.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.behaviors.a r2 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_PACKAGE_REPLACED     // Catch:{ NoSuchFieldError -> 0x0023 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0023 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0023 }
            L_0x0023:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x002e }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_TIME_ZONE_CHANGED     // Catch:{ NoSuchFieldError -> 0x002e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0039 }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_PUSH_MESSAGING_TOGGLED     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0044 }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_FENCE_MESSAGING_TOGGLED     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x004f }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_PROXIMITY_MESSAGING_TOGGLED     // Catch:{ NoSuchFieldError -> 0x004f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004f }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004f }
            L_0x004f:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x005a }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0065 }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_BACKGROUNDED     // Catch:{ NoSuchFieldError -> 0x0065 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0065 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0065 }
            L_0x0065:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0071 }
                com.salesforce.marketingcloud.behaviors.a r1 = com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_TOKEN_REFRESHED     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.registration.d.b.<clinit>():void");
        }
    }

    static class c implements RegistrationManager.Editor {
        c() {
        }

        public RegistrationManager.Editor addTag(String str) {
            return this;
        }

        public RegistrationManager.Editor addTags(Iterable<String> iterable) {
            return this;
        }

        public RegistrationManager.Editor addTags(String... strArr) {
            return this;
        }

        public RegistrationManager.Editor clearAttribute(String str) {
            return this;
        }

        public RegistrationManager.Editor clearAttributes(Iterable<String> iterable) {
            return this;
        }

        public RegistrationManager.Editor clearAttributes(String... strArr) {
            return this;
        }

        public RegistrationManager.Editor clearTags() {
            return this;
        }

        public boolean commit() {
            return false;
        }

        public RegistrationManager.Editor removeTag(String str) {
            return this;
        }

        public RegistrationManager.Editor removeTags(Iterable<String> iterable) {
            return this;
        }

        public RegistrationManager.Editor removeTags(String... strArr) {
            return this;
        }

        public RegistrationManager.Editor setAttribute(String str, String str2) {
            return this;
        }

        public RegistrationManager.Editor setContactKey(String str) {
            return this;
        }

        public RegistrationManager.Editor setSignedString(String str) {
            return this;
        }
    }

    public d(Context context, MarketingCloudConfig marketingCloudConfig, j jVar, f fVar, com.salesforce.marketingcloud.behaviors.c cVar, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.c cVar2, PushMessageManager pushMessageManager, l lVar) {
        this(context, marketingCloudConfig, jVar, fVar, cVar, bVar, cVar2, pushMessageManager, lVar, (SFMCSdkComponents) null);
    }

    public static String a(j jVar) {
        return jVar.c().b(com.salesforce.marketingcloud.storage.c.d, (String) null);
    }

    public final String componentName() {
        return "RegistrationManager";
    }

    public final JSONObject componentState() {
        e eVar = this.f39n;
        return eVar != null ? eVar.d() : new JSONObject();
    }

    public void controlChannelInit(int i2) {
        if (com.salesforce.marketingcloud.b.a(i2, 2)) {
            this.f39n = null;
            e.a(this.f, this.h, com.salesforce.marketingcloud.b.c(i2, 2));
            this.g.a((com.salesforce.marketingcloud.behaviors.b) this);
            a();
            this.h.e(a.C0001a.REGISTRATION);
            this.i.a(com.salesforce.marketingcloud.http.a.REGISTRATION);
        } else if (this.f39n == null) {
            a((InitializationStatus.a) null);
            this.f39n.g();
        }
    }

    public RegistrationManager.Editor edit() {
        e eVar = this.f39n;
        return eVar != null ? eVar.a((e.f) this) : new c();
    }

    public Map<String, String> getAttributes() {
        e eVar = this.f39n;
        return eVar != null ? eVar.getAttributes() : Collections.emptyMap();
    }

    public String getContactKey() {
        e eVar = this.f39n;
        if (eVar != null) {
            return eVar.getContactKey();
        }
        return null;
    }

    public String getDeviceId() {
        e eVar = this.f39n;
        return eVar != null ? eVar.getDeviceId() : "";
    }

    public String getSignedString() {
        e eVar = this.f39n;
        if (eVar != null) {
            return eVar.getSignedString();
        }
        return null;
    }

    public String getSystemToken() {
        e eVar = this.f39n;
        if (eVar != null) {
            return eVar.getSystemToken();
        }
        return null;
    }

    public Set<String> getTags() {
        e eVar = this.f39n;
        return eVar != null ? eVar.getTags() : Collections.emptySet();
    }

    public void init(InitializationStatus.a aVar, int i2) {
        if (com.salesforce.marketingcloud.b.b(i2, 2)) {
            a(aVar);
        }
    }

    public final void onBehavior(com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        if (this.f39n != null) {
            switch (b.a[aVar.ordinal()]) {
                case 1:
                    this.f39n.c();
                    return;
                case 2:
                    this.f39n.h();
                    return;
                case 3:
                    this.f39n.b(bundle.getBoolean(PushMessageManager.h));
                    return;
                case 4:
                case 5:
                case 6:
                    this.f39n.b();
                    return;
                case 7:
                    this.f39n.a();
                    return;
                case 8:
                    this.f39n.a(bundle.getString(PushMessageManager.i, ""));
                    return;
                default:
                    g.a(RegistrationManager.a, "Unhandled behavior: %s", aVar);
                    return;
            }
        }
    }

    public void onEventPublished(Event... eventArr) {
        for (com.salesforce.marketingcloud.events.Event attributes : com.salesforce.marketingcloud.events.d.a((Object[]) eventArr, (EnumSet<Event.Producer>) EnumSet.of(Event.Producer.SFMC_SDK), (EnumSet<Event.Category>) EnumSet.of(Event.Category.IDENTITY))) {
            try {
                Object obj = attributes.attributes().get("moduleIdentities");
                Objects.requireNonNull(obj);
                JSONObject jSONObject = (JSONObject) ((JSONObject) obj).get(ModuleIdentifier.PUSH.name().toLowerCase());
                JSONObject jSONObject2 = jSONObject.getJSONObject("customProperties").getJSONObject(k.a.h);
                Iterator<String> keys = jSONObject2.keys();
                HashMap hashMap = new HashMap();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, (String) jSONObject2.get(next));
                }
                String optString = jSONObject.optString("profileId", (String) null);
                (optString != null ? this.f39n.b((e.f) this).a(optString, (Map<String, String>) hashMap, false) : this.f39n.b((e.f) this).a((Map<String, String>) hashMap, false)).commit();
            } catch (Exception e2) {
                g.e(RegistrationManager.a, e2, "Failed to parse event for identity update.", new Object[0]);
            }
        }
    }

    public void registerForRegistrationEvents(RegistrationManager.RegistrationEventListener registrationEventListener) {
        e eVar = this.f39n;
        if (eVar != null) {
            eVar.registerForRegistrationEvents(registrationEventListener);
        }
    }

    public void tearDown(boolean z) {
        com.salesforce.marketingcloud.alarms.b bVar = this.h;
        a.C0001a aVar = a.C0001a.REGISTRATION;
        bVar.d(aVar);
        this.h.e(aVar);
        this.g.a((com.salesforce.marketingcloud.behaviors.b) this);
        a();
    }

    public void unregisterForRegistrationEvents(RegistrationManager.RegistrationEventListener registrationEventListener) {
        e eVar = this.f39n;
        if (eVar != null) {
            eVar.unregisterForRegistrationEvents(registrationEventListener);
        }
    }

    public d(Context context, MarketingCloudConfig marketingCloudConfig, j jVar, f fVar, com.salesforce.marketingcloud.behaviors.c cVar, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.c cVar2, PushMessageManager pushMessageManager, l lVar, SFMCSdkComponents sFMCSdkComponents) {
        this.d = context;
        this.e = marketingCloudConfig;
        this.f = jVar;
        this.m = fVar;
        this.g = cVar;
        this.h = bVar;
        this.i = cVar2;
        this.j = pushMessageManager;
        this.k = lVar;
        this.l = sFMCSdkComponents;
    }

    private void a(InitializationStatus.a aVar) {
        this.g.a(this, o);
        this.h.a((b.C0003b) this, a.C0001a.REGISTRATION);
        this.i.a(com.salesforce.marketingcloud.http.a.REGISTRATION, (c.C0017c) this);
        SFMCSdkComponents sFMCSdkComponents = this.l;
        if (sFMCSdkComponents != null) {
            sFMCSdkComponents.getEventManager().subscribe(this);
        }
        try {
            this.f39n = new e(this.d, this.e, this.f, this.m, this.h, this.i, this.j, this.k, this.l);
        } catch (Exception e2) {
            if (aVar != null) {
                aVar.a((Throwable) e2);
            }
        }
    }

    d(e eVar, Context context, MarketingCloudConfig marketingCloudConfig, j jVar, f fVar, com.salesforce.marketingcloud.behaviors.c cVar, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.c cVar2, PushMessageManager pushMessageManager, l lVar) {
        this.f39n = eVar;
        this.d = context;
        this.e = marketingCloudConfig;
        this.f = jVar;
        this.m = fVar;
        this.g = cVar;
        this.h = bVar;
        this.i = cVar2;
        this.j = pushMessageManager;
        this.k = lVar;
        this.l = null;
    }

    public final void a(a.C0001a aVar) {
        e eVar;
        if (b.b[aVar.ordinal()] == 1 && (eVar = this.f39n) != null) {
            eVar.e();
        }
    }

    public void a(String str, String str2, Map<String, String> map, Collection<String> collection, boolean z) {
        e eVar = this.f39n;
        if (eVar != null) {
            try {
                eVar.a(str, str2, map, collection, z);
            } catch (Exception e2) {
                g.b(RegistrationManager.a, e2, "Error encountered while saving registration", new Object[0]);
            }
        }
    }

    public void a(com.salesforce.marketingcloud.http.b bVar, com.salesforce.marketingcloud.http.d dVar) {
        if (this.f39n == null) {
            return;
        }
        if (dVar.g()) {
            try {
                this.f39n.a(com.salesforce.marketingcloud.internal.k.a(new JSONObject(bVar.o())), dVar.d());
            } catch (Exception unused) {
                this.f39n.a(-1, "Failed to convert our Response Body into a Registration.");
            }
        } else {
            this.f39n.a(dVar.b(), dVar.e());
        }
    }

    public static com.salesforce.marketingcloud.http.d a(MarketingCloudConfig marketingCloudConfig, Context context, String str, String str2) {
        Registration registration = r0;
        Registration registration2 = new Registration(0, (String) null, str, (String) null, MarketingCloudSdk.getSdkVersionName(), h.a(context), TimeZone.getDefault().inDaylightTime(new Date()), false, false, Build.VERSION.RELEASE, false, com.salesforce.marketingcloud.util.l.b(), (String) null, "Android", String.format(Locale.ENGLISH, "%s %s", new Object[]{Build.MANUFACTURER, Build.MODEL}), marketingCloudConfig.applicationId(), Locale.getDefault().toString(), Collections.emptySet(), Collections.emptyMap());
        return com.salesforce.marketingcloud.http.a.REGISTRATION.a(marketingCloudConfig, (com.salesforce.marketingcloud.storage.c) new a(), a(registration, str2)).j();
    }

    static String a(Registration registration, String str) {
        try {
            return com.salesforce.marketingcloud.internal.k.b(registration).put("registrationDateUtc", com.salesforce.marketingcloud.util.l.a(new Date())).put("quietPushEnabled", false).putOpt("registrationId", str).toString();
        } catch (Exception e2) {
            g.b(RegistrationManager.a, e2, "Unable to create registration request payload", new Object[0]);
            return null;
        }
    }

    private void a() {
        SFMCSdkComponents sFMCSdkComponents = this.l;
        if (sFMCSdkComponents != null) {
            sFMCSdkComponents.getEventManager().unsubscribe(this);
        }
    }
}
