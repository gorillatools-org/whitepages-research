package com.salesforce.marketingcloud.registration;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationManagerCompat;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.internal.g;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.registration.RegistrationManager;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import com.salesforce.marketingcloud.storage.db.k;
import com.salesforce.marketingcloud.storage.j;
import com.salesforce.marketingcloud.util.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import org.json.JSONException;
import org.json.JSONObject;

class e implements RegistrationManager {
    public static final String w = "Android";
    static final String x = "previousRegistrationHash";
    static final String y = "lastRegistrationSendTimestamp";
    final Set<String> d;
    private final Context e;
    final MarketingCloudConfig f;
    final j g;
    final com.salesforce.marketingcloud.alarms.b h;
    final com.salesforce.marketingcloud.http.c i;
    final l j;
    final SFMCSdkComponents k;
    private final Set<RegistrationManager.RegistrationEventListener> l;
    private final f m;

    /* renamed from: n  reason: collision with root package name */
    private ConcurrentHashMap<String, String> f40n;
    private ConcurrentSkipListSet<String> o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private String t;
    private String u;
    private String v;

    class a implements MarketingCloudSdk.WhenReadyListener {
        final /* synthetic */ boolean a;

        /* renamed from: com.salesforce.marketingcloud.registration.e$a$a  reason: collision with other inner class name */
        class C0031a extends C0033e {

            /* renamed from: com.salesforce.marketingcloud.registration.e$a$a$a  reason: collision with other inner class name */
            class C0032a extends g {
                C0032a(String str, Object... objArr) {
                    super(str, objArr);
                }

                /* access modifiers changed from: protected */
                public void a() {
                    try {
                        SFMCSdkComponents sFMCSdkComponents = e.this.k;
                        String registrationId = sFMCSdkComponents != null ? sFMCSdkComponents.getRegistrationId() : null;
                        Registration l = e.this.g.u().l(e.this.g.b());
                        e eVar = e.this;
                        if (e.a(l, eVar.g, eVar.f.delayRegistrationUntilContactKeyIsSet())) {
                            e.this.h.d(a.C0001a.REGISTRATION);
                            e eVar2 = e.this;
                            eVar2.i.a(com.salesforce.marketingcloud.http.a.REGISTRATION.a(eVar2.f, eVar2.g.c(), d.a(l, registrationId)));
                        }
                    } catch (Exception e) {
                        com.salesforce.marketingcloud.g.b(RegistrationManager.a, e, "Failed to get our Registration from local storage.", new Object[0]);
                    }
                }
            }

            C0031a(int i) {
                super(i);
            }

            public void onFinish() {
                e.this.j.b().execute(new C0032a("registration_request", new Object[0]));
            }
        }

        a(boolean z) {
            this.a = z;
        }

        public void ready(MarketingCloudSdk marketingCloudSdk) {
            new C0031a(this.a ? 1000 : 0).start();
        }
    }

    class b extends g {
        b(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            e.this.g.u().c();
        }
    }

    class c extends g {
        c(String str, Object... objArr) {
            super(str, objArr);
        }

        /* access modifiers changed from: protected */
        public void a() {
            e eVar = e.this;
            if (e.a(eVar.g, eVar.f.delayRegistrationUntilContactKeyIsSet())) {
                e.this.h.b(a.C0001a.REGISTRATION);
            }
        }
    }

    static class d implements RegistrationManager.Editor, c {
        private static final List<String> j;
        private final Object a = new Object();
        private final Map<String, String> b;
        private final Set<String> c;
        private String d;
        private final f e;
        private String f;
        private final Map<String, String> g;
        private boolean h;
        private boolean i;

        static {
            String[] strArr = {"addressId", "alias", "apId", "backgroundRefreshEnabled", "badge", "channel", "contactId", "contactKey", "createdBy", "createdDate", "customObjectKey", "device", "deviceId", "deviceType", "gcmSenderId", "hardwareId", "isHonorDst", "lastAppOpen", "lastMessageOpen", "lastSend", "locationEnabled", "messageOpenCount", "modifiedBy", "modifiedDate", "optInDate", "optInMethodId", "optInStatusId", "optOutDate", "optOutMethodId", "optOutStatusId", k.a.b, RemoteConfigConstants.RequestFieldKey.PLATFORM_VERSION, "providerToken", "proximityEnabled", "pushAddressExtensionId", "pushApplicationId", RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "sendCount", "source", "sourceObjectId", "status", "systemToken", k.a.e, "utcOffset", "signedString", "quietPushEnabled"};
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < 46; i2++) {
                arrayList.add(strArr[i2].toLowerCase(Locale.ENGLISH));
            }
            j = Collections.unmodifiableList(arrayList);
        }

        d(f fVar, String str, String str2, ConcurrentHashMap<String, String> concurrentHashMap, ConcurrentSkipListSet<String> concurrentSkipListSet, Set<String> set) {
            Comparator comparator = String.CASE_INSENSITIVE_ORDER;
            this.b = new TreeMap(comparator);
            this.c = new TreeSet(comparator);
            this.e = fVar;
            this.d = str;
            this.f = str2;
            this.g = new b(concurrentHashMap);
            Iterator<String> it = concurrentSkipListSet.iterator();
            while (it.hasNext()) {
                String next = it.next();
                this.b.put(next, next);
            }
            this.c.addAll(set);
        }

        private boolean a(String str) {
            if (TextUtils.isEmpty(str)) {
                com.salesforce.marketingcloud.g.e(RegistrationManager.a, "The attribute you provided was null or empty.", new Object[0]);
                return false;
            }
            String trim = str.trim();
            if (TextUtils.isEmpty(trim)) {
                com.salesforce.marketingcloud.g.e(RegistrationManager.a, "The attribute you provided was blank.", new Object[0]);
                return false;
            } else if (j.contains(trim.toLowerCase(Locale.ENGLISH))) {
                com.salesforce.marketingcloud.g.e(RegistrationManager.a, "Attribute key '%s' is invalid and can not be added.  Please see documentation regarding Attributes and Reserved Words.", trim);
                return false;
            } else if (trim.length() <= 128) {
                return true;
            } else {
                com.salesforce.marketingcloud.g.e(RegistrationManager.a, "Your attribute key was %s characters long.  Attribute keys are restricted to %s characters.  Your attribute key will be truncated.", Integer.valueOf(trim.length()), 128);
                return false;
            }
        }

        private boolean b(String str) {
            if (str != null) {
                return true;
            }
            com.salesforce.marketingcloud.g.b(RegistrationManager.a, "Attribute value was null and will not be saved.", new Object[0]);
            return false;
        }

        private boolean c(String str) {
            return str == null || TextUtils.getTrimmedLength(str) > 0;
        }

        private String d(String str) {
            if (!TextUtils.isEmpty(str) && TextUtils.getTrimmedLength(str) != 0) {
                return str.trim();
            }
            com.salesforce.marketingcloud.g.e(RegistrationManager.a, "An empty or NULL ContactKey will not be transmitted to the Marketing Cloud and was NOT updated with the provided value.", new Object[0]);
            return null;
        }

        private String e(String str) {
            return str != null ? str.trim() : str;
        }

        public RegistrationManager.Editor addTag(String str) {
            String e2 = e(str);
            synchronized (this.a) {
                try {
                    if (!TextUtils.isEmpty(e2) && !e2.equals(this.b.put(e2, e2))) {
                        this.h = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return this;
        }

        public RegistrationManager.Editor addTags(Iterable<String> iterable) {
            if (iterable == null) {
                return this;
            }
            for (String addTag : iterable) {
                addTag(addTag);
            }
            return this;
        }

        public RegistrationManager.Editor clearAttribute(String str) {
            return !a(str) ? this : setAttribute(str, "");
        }

        public RegistrationManager.Editor clearAttributes(Iterable<String> iterable) {
            for (String clearAttribute : iterable) {
                clearAttribute(clearAttribute);
            }
            return this;
        }

        public RegistrationManager.Editor clearTags() {
            synchronized (this.a) {
                try {
                    if (this.b.keySet().retainAll(this.c)) {
                        this.h = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return this;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
            return false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean commit() {
            /*
                r8 = this;
                java.lang.Object r0 = r8.a
                monitor-enter(r0)
                boolean r1 = r8.h     // Catch:{ all -> 0x001f }
                if (r1 == 0) goto L_0x0021
                com.salesforce.marketingcloud.registration.e$f r2 = r8.e     // Catch:{ all -> 0x001f }
                if (r2 == 0) goto L_0x0021
                java.lang.String r3 = r8.d     // Catch:{ all -> 0x001f }
                java.lang.String r4 = r8.f     // Catch:{ all -> 0x001f }
                java.util.Map<java.lang.String, java.lang.String> r5 = r8.g     // Catch:{ all -> 0x001f }
                java.util.Map<java.lang.String, java.lang.String> r1 = r8.b     // Catch:{ all -> 0x001f }
                java.util.Collection r6 = r1.values()     // Catch:{ all -> 0x001f }
                boolean r7 = r8.i     // Catch:{ all -> 0x001f }
                r2.a(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x001f }
                monitor-exit(r0)     // Catch:{ all -> 0x001f }
                r0 = 1
                return r0
            L_0x001f:
                r1 = move-exception
                goto L_0x0024
            L_0x0021:
                monitor-exit(r0)     // Catch:{ all -> 0x001f }
                r0 = 0
                return r0
            L_0x0024:
                monitor-exit(r0)     // Catch:{ all -> 0x001f }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.registration.e.d.commit():boolean");
        }

        public RegistrationManager.Editor removeTag(String str) {
            if (str == null) {
                return this;
            }
            synchronized (this.a) {
                try {
                    if (!this.c.contains(str) && this.b.remove(str) != null) {
                        this.h = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return this;
        }

        public RegistrationManager.Editor removeTags(Iterable<String> iterable) {
            if (iterable == null) {
                return this;
            }
            for (String removeTag : iterable) {
                removeTag(removeTag);
            }
            return this;
        }

        @Deprecated
        public RegistrationManager.Editor setAttribute(String str, String str2) {
            return a(str, str2, true);
        }

        @Deprecated
        public RegistrationManager.Editor setContactKey(String str) {
            return a(str, true);
        }

        public RegistrationManager.Editor setSignedString(String str) {
            synchronized (this.a) {
                try {
                    if (c(str)) {
                        this.d = str;
                        this.h = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return this;
        }

        public RegistrationManager.Editor a(String str, String str2, boolean z) {
            synchronized (this.a) {
                try {
                    if (a(str) && b(str2)) {
                        this.g.put(str, str2);
                        this.h = true;
                        this.i = z;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return this;
        }

        public RegistrationManager.Editor addTags(String... strArr) {
            if (!(strArr == null || strArr.length == 0)) {
                for (String addTag : strArr) {
                    addTag(addTag);
                }
            }
            return this;
        }

        public RegistrationManager.Editor clearAttributes(String... strArr) {
            if (!(strArr == null || strArr.length == 0)) {
                for (String clearAttribute : strArr) {
                    clearAttribute(clearAttribute);
                }
            }
            return this;
        }

        public RegistrationManager.Editor removeTags(String... strArr) {
            if (!(strArr == null || strArr.length == 0)) {
                for (String removeTag : strArr) {
                    removeTag(removeTag);
                }
            }
            return this;
        }

        public RegistrationManager.Editor a(String str, boolean z) {
            String d2 = d(str);
            if (d2 != null) {
                synchronized (this.a) {
                    this.h = true;
                    this.i = z;
                    this.f = d2;
                }
            }
            return this;
        }

        public RegistrationManager.Editor a(String str, Map<String, String> map, boolean z) {
            a(str, z);
            for (Map.Entry next : map.entrySet()) {
                a((String) next.getKey(), (String) next.getValue(), z);
            }
            return this;
        }

        public RegistrationManager.Editor a(Map<String, String> map, boolean z) {
            for (Map.Entry next : map.entrySet()) {
                a((String) next.getKey(), (String) next.getValue(), z);
            }
            return this;
        }
    }

    /* renamed from: com.salesforce.marketingcloud.registration.e$e  reason: collision with other inner class name */
    static abstract class C0033e extends CountDownTimer {
        public C0033e(int i) {
            this((long) i, 1000);
        }

        public void onTick(long j) {
        }

        private C0033e(long j, long j2) {
            super(j, j2);
        }
    }

    interface f {
        void a(String str, String str2, Map<String, String> map, Collection<String> collection) {
            a(str, str2, map, collection, false);
        }

        void a(String str, String str2, Map<String, String> map, Collection<String> collection, boolean z);
    }

    e(Context context, MarketingCloudConfig marketingCloudConfig, j jVar, f fVar, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.c cVar, PushMessageManager pushMessageManager, l lVar) {
        this(context, marketingCloudConfig, jVar, fVar, bVar, cVar, pushMessageManager, lVar, (SFMCSdkComponents) null);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        SharedPreferences.Editor edit = this.g.f().edit();
        edit.remove(com.salesforce.marketingcloud.http.a.REGISTRATION.c + "_device").apply();
        a(false);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        boolean b2 = h.b(this.e);
        boolean z = b2 && h.c(this.e);
        boolean areNotificationsEnabled = NotificationManagerCompat.from(this.e).areNotificationsEnabled();
        if (b2 != this.p || z != this.q || areNotificationsEnabled != this.r) {
            this.p = b2;
            this.q = z;
            this.r = areNotificationsEnabled;
            g();
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.h.d(a.C0001a.REGISTRATION);
        g();
    }

    /* access modifiers changed from: package-private */
    public JSONObject d() {
        String b2;
        Registration a2 = a(0);
        if (a2 == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("current_registration", com.salesforce.marketingcloud.internal.k.b(a2));
            if (a(a2, this.g, this.f.delayRegistrationUntilContactKeyIsSet()) && (b2 = this.g.c().b(com.salesforce.marketingcloud.storage.c.h, (String) null)) != null) {
                jSONObject.put("last_registration_sent", new JSONObject(b2));
            }
            long j2 = this.g.f().getLong(y, 0);
            if (j2 > 0) {
                jSONObject.put("last_sent_timestamp", com.salesforce.marketingcloud.util.l.a(new Date(j2)));
            }
        } catch (JSONException e2) {
            com.salesforce.marketingcloud.g.b(RegistrationManager.a, e2, "Failed to build our component state JSONObject.", new Object[0]);
        }
        return jSONObject;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        a(true);
    }

    public RegistrationManager.Editor edit() {
        com.salesforce.marketingcloud.g.a(RegistrationManager.a, "Changes with this editor will not be saved.", new Object[0]);
        return new d((f) null, this.v, this.t, this.f40n, this.o, this.d);
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        return this.s && NotificationManagerCompat.from(this.e).areNotificationsEnabled();
    }

    /* access modifiers changed from: package-private */
    public void g() {
        c(false);
    }

    public Map<String, String> getAttributes() {
        return new HashMap(this.f40n);
    }

    public String getContactKey() {
        return this.t;
    }

    public String getDeviceId() {
        return this.m.f();
    }

    public String getSignedString() {
        return this.v;
    }

    public String getSystemToken() {
        return this.u;
    }

    public Set<String> getTags() {
        return new TreeSet(this.o);
    }

    /* access modifiers changed from: package-private */
    public void h() {
        g();
    }

    public void registerForRegistrationEvents(RegistrationManager.RegistrationEventListener registrationEventListener) {
        if (registrationEventListener != null) {
            synchronized (this.l) {
                this.l.add(registrationEventListener);
            }
        }
    }

    public void unregisterForRegistrationEvents(RegistrationManager.RegistrationEventListener registrationEventListener) {
        synchronized (this.l) {
            this.l.remove(registrationEventListener);
        }
    }

    e(Context context, MarketingCloudConfig marketingCloudConfig, j jVar, f fVar, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.c cVar, PushMessageManager pushMessageManager, l lVar, SFMCSdkComponents sFMCSdkComponents) {
        Registration registration;
        this.l = new ArraySet();
        this.e = context;
        this.f = marketingCloudConfig;
        this.g = jVar;
        this.m = fVar;
        this.h = bVar;
        this.i = cVar;
        this.j = lVar;
        this.k = sFMCSdkComponents;
        TreeSet treeSet = new TreeSet();
        treeSet.add("ALL");
        treeSet.add("Android");
        if (com.salesforce.marketingcloud.util.l.a(context)) {
            treeSet.add("DEBUG");
        }
        Set<String> unmodifiableSet = Collections.unmodifiableSet(treeSet);
        this.d = unmodifiableSet;
        this.s = pushMessageManager.isPushEnabled();
        boolean b2 = h.b(context);
        this.p = b2;
        boolean z = true;
        boolean z2 = false;
        this.q = b2 && h.c(context);
        this.r = NotificationManagerCompat.from(context).areNotificationsEnabled();
        this.u = pushMessageManager.getPushToken();
        com.salesforce.marketingcloud.storage.c c2 = jVar.c();
        try {
            Registration l2 = jVar.u().l(jVar.b());
            if (l2 == null) {
                this.v = null;
                this.t = c2.b(com.salesforce.marketingcloud.storage.c.d, (String) null);
                this.f40n = new ConcurrentHashMap<>(com.salesforce.marketingcloud.util.l.c(c2.b(com.salesforce.marketingcloud.storage.c.b, "")));
                ConcurrentSkipListSet concurrentSkipListSet = new ConcurrentSkipListSet(com.salesforce.marketingcloud.util.l.d(c2.b(com.salesforce.marketingcloud.storage.c.c, "")));
                this.o = concurrentSkipListSet.isEmpty() ? new ConcurrentSkipListSet<>(unmodifiableSet) : a((ConcurrentSkipListSet<String>) concurrentSkipListSet, unmodifiableSet);
                registration = a(0);
                z = false;
            } else {
                this.v = l2.signedString();
                this.t = l2.contactKey();
                this.f40n = new ConcurrentHashMap<>(l2.attributes());
                this.o = a((ConcurrentSkipListSet<String>) new ConcurrentSkipListSet(l2.tags()), unmodifiableSet);
                registration = a(com.salesforce.marketingcloud.internal.k.a(l2));
            }
            a(jVar, this.t);
            z2 = z;
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(RegistrationManager.a, e2, "Error trying to get, update or add a registration to local storage.", new Object[0]);
            this.o = new ConcurrentSkipListSet<>(this.d);
            this.f40n = new ConcurrentHashMap<>();
            this.t = null;
            this.v = null;
            registration = a(0);
        }
        lVar.b().execute(new a(jVar.u(), jVar.b(), registration, z2));
        if (a(registration, jVar, marketingCloudConfig.delayRegistrationUntilContactKeyIsSet())) {
            e();
        }
    }

    private static ConcurrentSkipListSet<String> a(ConcurrentSkipListSet<String> concurrentSkipListSet, Set<String> set) {
        if (!concurrentSkipListSet.containsAll(set)) {
            concurrentSkipListSet.addAll(set);
        }
        return concurrentSkipListSet;
    }

    /* access modifiers changed from: package-private */
    public c b(f fVar) {
        return new d(fVar, this.v, this.t, this.f40n, this.o, this.d);
    }

    /* access modifiers changed from: package-private */
    public void c(boolean z) {
        try {
            Registration a2 = a(0);
            this.j.b().execute(new a(this.g.u(), this.g.b(), a2, false));
            a(this.g, a2.contactKey());
            if (a(a2, this.g, this.f.delayRegistrationUntilContactKeyIsSet())) {
                SFMCSdkComponents sFMCSdkComponents = this.k;
                if (sFMCSdkComponents != null && z) {
                    if (this.t != null) {
                        sFMCSdkComponents.getIdentity().setProfile(this.t, this.f40n, ModuleIdentifier.PUSH, new ModuleIdentifier[0]);
                    } else {
                        sFMCSdkComponents.getIdentity().setProfileAttributes(this.f40n, ModuleIdentifier.PUSH);
                    }
                }
                e();
            }
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(RegistrationManager.a, e2, "An error occurred trying to save our Registration.", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public RegistrationManager.Editor a(f fVar) {
        return new d(fVar, this.v, this.t, this.f40n, this.o, this.d);
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z) {
        this.s = z;
        g();
    }

    private Registration a(int i2) {
        return new Registration(i2, this.v, this.m.f(), this.u, this.m.j(), this.m.e(), TimeZone.getDefault().inDaylightTime(new Date()), this.p, this.q, this.m.i(), f(), com.salesforce.marketingcloud.util.l.b(), this.t, this.m.h(), this.m.g(), this.f.applicationId(), Locale.getDefault().toString(), this.o, this.f40n);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        MarketingCloudSdk.requestSdk(new a(z));
    }

    /* access modifiers changed from: package-private */
    public void a(Registration registration, Map<String, List<String>> map) {
        com.salesforce.marketingcloud.http.a.a(map, this.g.c());
        this.h.c(a.C0001a.REGISTRATION);
        synchronized (this.l) {
            for (RegistrationManager.RegistrationEventListener next : this.l) {
                if (next != null) {
                    try {
                        next.onRegistrationReceived(registration);
                    } catch (Exception e2) {
                        com.salesforce.marketingcloud.g.b(RegistrationManager.a, e2, "%s threw an exception while processing the registration response", next.getClass().getName());
                    }
                }
            }
        }
        String jSONObject = com.salesforce.marketingcloud.internal.k.b(registration).toString();
        this.g.c().a(com.salesforce.marketingcloud.storage.c.h, jSONObject);
        this.g.f().edit().putLong(y, System.currentTimeMillis()).putString(x, com.salesforce.marketingcloud.util.l.b(jSONObject)).apply();
        this.j.b().execute(new b("delete_old_registrations", new Object[0]));
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, Map<String, String> map, Collection<String> collection) throws Exception {
        a(str, str2, map, collection, false);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, Map<String, String> map, Collection<String> collection, boolean z) throws Exception {
        this.v = str;
        this.t = str2;
        this.f40n.clear();
        this.f40n.putAll(map);
        this.o.clear();
        this.o.addAll(collection);
        this.h.c(a.C0001a.REGISTRATION);
        c(z);
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, String str) {
        com.salesforce.marketingcloud.g.a(RegistrationManager.a, "%s: %s", Integer.valueOf(i2), str);
        this.j.b().execute(new c("schedule_registration_retry", new Object[0]));
    }

    private void a(j jVar, String str) {
        jVar.c().a(com.salesforce.marketingcloud.storage.c.d, str);
    }

    static boolean a(Registration registration, j jVar, boolean z) {
        if (registration == null) {
            return false;
        }
        if (registration.contactKey() != null || !z) {
            String string = jVar.f().getString(x, (String) null);
            return string == null || !com.salesforce.marketingcloud.util.l.b(com.salesforce.marketingcloud.internal.k.b(registration).toString()).equals(string);
        }
        com.salesforce.marketingcloud.g.e(RegistrationManager.a, "You have delayRegistrationUntilContactKeyIsSet set to `true.`  The SDK will not send a registration to the Marketing Cloud until a contact key has been set.", new Object[0]);
        return false;
    }

    static boolean a(j jVar, boolean z) {
        try {
            return a(jVar.u().l(jVar.b()), jVar, z);
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(RegistrationManager.a, e2, "Failed to get Registration from local storage or we can not determine if this Registration contains any changes.", new Object[0]);
            return false;
        }
    }

    static void a(j jVar, com.salesforce.marketingcloud.alarms.b bVar, boolean z) {
        if (z) {
            jVar.u().n();
            jVar.c().a(com.salesforce.marketingcloud.storage.c.d);
        }
        bVar.d(a.C0001a.REGISTRATION);
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.u)) {
            this.u = str;
            g();
        }
    }
}
