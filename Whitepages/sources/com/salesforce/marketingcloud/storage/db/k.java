package com.salesforce.marketingcloud.storage.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.salesforce.marketingcloud.registration.Registration;
import com.salesforce.marketingcloud.storage.m;
import com.salesforce.marketingcloud.util.c;
import com.salesforce.marketingcloud.util.l;
import java.util.Locale;

@SuppressLint({"UnknownNullness"})
public final class k extends b implements m {
    public static final String e = "registration";
    private static final String[] f = {"id", a.b, a.c, a.d, a.e, a.f, a.g, a.h, a.i, a.j, a.k, a.l, a.m, a.o, "device_id", a.q, a.r, a.s, "locale"};
    private static final String g = "CREATE TABLE registration (id INTEGER PRIMARY KEY AUTOINCREMENT, platform VARCHAR, subscriber_key VARCHAR, et_app_id VARCHAR, timezone INTEGER, dst SMALLINT, tags VARCHAR, attributes VARCHAR, platform_version VARCHAR, push_enabled SMALLINT, location_enabled SMALLINT, proximity_enabled SMALLINT, hwid VARCHAR, system_token VARCHAR, device_id VARCHAR, app_version VARCHAR, sdk_version VARCHAR, signed_string VARCHAR, locale VARCHAR );";

    public static class a {
        public static final String a = "id";
        public static final String b = "platform";
        public static final String c = "subscriber_key";
        public static final String d = "et_app_id";
        public static final String e = "timezone";
        public static final String f = "dst";
        public static final String g = "tags";
        public static final String h = "attributes";
        public static final String i = "platform_version";
        public static final String j = "push_enabled";
        public static final String k = "location_enabled";
        public static final String l = "proximity_enabled";
        public static final String m = "hwid";

        /* renamed from: n  reason: collision with root package name */
        public static final String f43n = "locale";
        public static final String o = "system_token";
        public static final String p = "device_id";
        public static final String q = "app_version";
        public static final String r = "sdk_version";
        public static final String s = "signed_string";
    }

    public k(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(g);
    }

    public void a(Registration registration, c cVar) throws Exception {
        com.salesforce.marketingcloud.internal.k.a(registration, (int) a(c(registration, cVar)));
    }

    public int c() {
        return i(a("%1$s NOT IN ( SELECT %1$s FROM ( SELECT %1$s FROM %2$s ORDER BY %1$s DESC LIMIT 1))", "id", o()));
    }

    public Registration l(c cVar) throws Exception {
        Cursor a2 = a(f, (String) null, (String[]) null, (String) null, (String) null, a("%s DESC", "id"), "1");
        Registration registration = null;
        if (a2 != null) {
            if (a2.moveToFirst()) {
                registration = d.d(a2, cVar);
            }
            a2.close();
        }
        return registration;
    }

    public int n() {
        return i((String) null);
    }

    /* access modifiers changed from: package-private */
    public String o() {
        return e;
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS registration");
    }

    static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement(a("SELECT %s FROM %s", TextUtils.join(",", f), e));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public int b(Registration registration, c cVar) throws Exception {
        return a(c(registration, cVar), a("%s = ?", "id"), new String[]{String.valueOf(com.salesforce.marketingcloud.internal.k.a(registration))});
    }

    private static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    private static ContentValues c(Registration registration, c cVar) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put(a.c, cVar.a(registration.contactKey()));
        contentValues.put(a.s, cVar.a(registration.signedString()));
        contentValues.put(a.d, cVar.a(registration.appId()));
        contentValues.put(a.o, cVar.a(registration.systemToken()));
        contentValues.put(a.g, cVar.a(l.a(registration.tags())));
        contentValues.put(a.h, cVar.a(l.a(registration.attributes())));
        contentValues.put("device_id", registration.deviceId());
        contentValues.put(a.b, registration.platform());
        contentValues.put(a.e, Integer.valueOf(registration.timeZone()));
        contentValues.put(a.f, Integer.valueOf(registration.dst() ? 1 : 0));
        contentValues.put(a.i, registration.platformVersion());
        contentValues.put(a.j, Integer.valueOf(registration.pushEnabled() ? 1 : 0));
        contentValues.put(a.k, Integer.valueOf(registration.locationEnabled() ? 1 : 0));
        contentValues.put(a.l, Integer.valueOf(registration.proximityEnabled() ? 1 : 0));
        contentValues.put(a.m, registration.hwid());
        contentValues.put("locale", registration.locale());
        contentValues.put(a.q, registration.appVersion());
        contentValues.put(a.r, registration.sdkVersion());
        return contentValues;
    }
}
