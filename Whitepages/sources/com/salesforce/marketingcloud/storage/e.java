package com.salesforce.marketingcloud.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.provider.Settings;
import androidx.collection.ArrayMap;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.util.c;
import com.salesforce.marketingcloud.util.i;
import com.salesforce.marketingcloud.util.j;
import com.salesforce.marketingcloud.util.l;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

abstract class e {
    protected static final String c = g.a("Storage");
    protected static final int d = -1;
    private static final String e = "DEFAULT_SHARED_PREFERENCES";
    private static final String f = "et_207_preference_migration_complete";
    private static AtomicBoolean g = new AtomicBoolean(false);
    private final String a;
    private final String b;

    e(String str, String str2) {
        this.a = (String) j.a((String) j.a(str, "Application ID is null."), "Application ID is empty.");
        this.b = (String) j.a((String) j.a(str2, "Access Token is null."), "Access Token is empty.");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:9|10) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        com.salesforce.marketingcloud.g.c(c, "Found unencrypted value for %s", r6);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object a(com.salesforce.marketingcloud.util.c r5, java.lang.String r6, java.lang.reflect.Type r7) {
        /*
            r4 = this;
            android.content.Context r0 = r4.a()
            java.lang.String r1 = "ETPush"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
            r1 = 0
            java.lang.String r2 = "%s_enc"
            java.lang.Object[] r3 = new java.lang.Object[]{r6}     // Catch:{ Exception -> 0x003f }
            java.lang.String r2 = java.lang.String.format(r2, r3)     // Catch:{ Exception -> 0x003f }
            java.lang.String r2 = r0.getString(r2, r1)     // Catch:{ Exception -> 0x003f }
            if (r2 != 0) goto L_0x0020
            java.lang.String r2 = r0.getString(r6, r1)     // Catch:{ Exception -> 0x003f }
        L_0x0020:
            if (r2 == 0) goto L_0x0031
            java.lang.String r2 = r5.b(r2)     // Catch:{ UnsupportedEncodingException | GeneralSecurityException -> 0x0033 }
            java.lang.String r5 = c     // Catch:{ UnsupportedEncodingException | GeneralSecurityException -> 0x0033 }
            java.lang.String r0 = "Found encrypted value for %s"
            java.lang.Object[] r3 = new java.lang.Object[]{r6}     // Catch:{ UnsupportedEncodingException | GeneralSecurityException -> 0x0033 }
            com.salesforce.marketingcloud.g.c((java.lang.String) r5, (java.lang.String) r0, (java.lang.Object[]) r3)     // Catch:{ UnsupportedEncodingException | GeneralSecurityException -> 0x0033 }
        L_0x0031:
            r1 = r2
            goto L_0x003f
        L_0x0033:
            java.lang.String r5 = c     // Catch:{ Exception -> 0x003f }
            java.lang.String r0 = "Found unencrypted value for %s"
            java.lang.Object[] r3 = new java.lang.Object[]{r6}     // Catch:{ Exception -> 0x003f }
            com.salesforce.marketingcloud.g.c((java.lang.String) r5, (java.lang.String) r0, (java.lang.Object[]) r3)     // Catch:{ Exception -> 0x003f }
            goto L_0x0031
        L_0x003f:
            if (r1 == 0) goto L_0x0042
            goto L_0x0046
        L_0x0042:
            java.lang.Object r1 = r4.a(r6, r7)
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.e.a(com.salesforce.marketingcloud.util.c, java.lang.String, java.lang.reflect.Type):java.lang.Object");
    }

    private void b(c cVar) {
        boolean z;
        SharedPreferences.Editor edit = f().edit();
        ArrayMap arrayMap = new ArrayMap();
        Class<Boolean> cls = Boolean.class;
        arrayMap.put("et_geo_enabled_key", cls);
        arrayMap.put("et_proximity_enabled_key", cls);
        arrayMap.put("et_push_enabled", cls);
        for (Map.Entry entry : arrayMap.entrySet()) {
            String str = (String) entry.getKey();
            Type type = (Type) entry.getValue();
            String str2 = c;
            g.c(str2, "Migrating %s ...", str);
            try {
                Object a2 = a(cVar, str, type);
                if (a2 != null) {
                    if (type == cls) {
                        if (a2 instanceof Boolean) {
                            z = ((Boolean) a2).booleanValue();
                        } else if (a2 instanceof String) {
                            z = Boolean.valueOf((String) a2).booleanValue();
                        } else {
                            g.c(str2, "Unknown Type for %s. Preference will not be migrated.", str);
                        }
                        edit.putBoolean(str, z);
                    } else {
                        g.c(str2, "Key '%s' with value of '%s' was not written to preferences.", str, a2);
                    }
                }
                g.c(str2, "Done with %s.", str);
            } catch (Exception e2) {
                g.b(c, e2, "Unable to migrate %s", str);
            }
        }
        edit.apply();
    }

    private void c(c cVar) {
        Map<String, String> c2;
        String[] strArr = {c.c, c.b, c.d};
        for (int i = 0; i < 3; i++) {
            String str = strArr[i];
            try {
                String str2 = c;
                g.c(str2, "Migrating %s ...", str);
                Object a2 = a(cVar, str, (Type) String.class);
                if (c.b.equals(str) && (c2 = l.c((String) a2)) != null) {
                    c2.remove("_ETSDKVersion");
                    a2 = l.a(c2);
                }
                if (a2 == null || "null".equals(a2)) {
                    g.c(str2, "Value was \"null\" and will not be migrated.", new Object[0]);
                } else {
                    g.c(str2, "Writing %s to encrypted preferences ...", str);
                    c().a(str, String.valueOf(a2));
                }
                g.c(str2, "Done with %s.", str);
            } catch (Exception e2) {
                g.b(c, e2, "Unable to migrate %s", str);
            }
        }
    }

    private String d() {
        return l.e(Settings.Secure.getString(a().getContentResolver(), "android_id") + "-" + a().getPackageName());
    }

    /* access modifiers changed from: protected */
    public abstract Context a();

    /* access modifiers changed from: protected */
    public abstract void a(Context context, int i, int i2);

    public abstract c b();

    public abstract c c();

    /* access modifiers changed from: protected */
    public abstract SQLiteOpenHelper e();

    public abstract SharedPreferences f();

    private Object a(String str, Type type) {
        String[] strArr = {"ETPush", "et_registration_cache", "~!Registration", "~!ETPush", "~!ETLocationManager", "etpushSDK@ETPush", "etpushsdk@ETLocationManager", e};
        for (int i = 0; i < 8; i++) {
            String str2 = strArr[i];
            SharedPreferences defaultSharedPreferences = e.equals(str2) ? PreferenceManager.getDefaultSharedPreferences(a()) : a().getSharedPreferences(str2, 0);
            if (defaultSharedPreferences.contains(str)) {
                if (type == Boolean.class) {
                    try {
                        return Boolean.valueOf(defaultSharedPreferences.getBoolean(str, false));
                    } catch (ClassCastException unused) {
                    }
                } else if (type == String.class) {
                    return defaultSharedPreferences.getString(str, (String) null);
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final void b(Context context, int i, int i2) {
        String[] databaseList;
        if (i == -1 && (databaseList = context.databaseList()) != null) {
            for (String equals : databaseList) {
                if ("etdb.db".equals(equals)) {
                    a(context, this.a, this.b);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(c cVar) throws GeneralSecurityException, UnsupportedEncodingException {
        String str = c;
        g.c(str, "Migrating SharedPreferences ...", new Object[0]);
        c(cVar);
        b(cVar);
        g.c(str, "Finished migrating SharedPreferences.", new Object[0]);
    }

    private void a(Context context, String str, String str2) {
        try {
            boolean andSet = g.getAndSet(true);
            boolean z = f().getBoolean(f, false);
            if (!andSet) {
                if (!z) {
                    i iVar = new i(context, str, str2, d());
                    try {
                        iVar.b(context.getSharedPreferences("ETPush", 0).getString("et_device_id_cache_enc", (String) null));
                        a(iVar);
                        g.c(c, "Old data migrations completed without exception.", new Object[0]);
                    } catch (Exception unused) {
                        g.a(c, "Unable to verify old encryption.  Moving on without migrating data.", new Object[0]);
                    }
                    f().edit().putBoolean(f, true).apply();
                    g.set(false);
                    return;
                }
            }
            f().edit().putBoolean(f, true).apply();
            g.set(false);
        } catch (Exception e2) {
            g.b(c, e2, "Data migration failed", new Object[0]);
        } catch (Throwable th) {
            f().edit().putBoolean(f, true).apply();
            g.set(false);
            throw th;
        }
    }
}
