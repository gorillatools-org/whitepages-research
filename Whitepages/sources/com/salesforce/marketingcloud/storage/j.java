package com.salesforce.marketingcloud.storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import com.salesforce.marketingcloud.storage.c;
import com.salesforce.marketingcloud.storage.db.a;
import com.salesforce.marketingcloud.storage.db.e;
import com.salesforce.marketingcloud.storage.db.f;
import com.salesforce.marketingcloud.storage.db.g;
import com.salesforce.marketingcloud.storage.db.h;
import com.salesforce.marketingcloud.storage.db.i;
import com.salesforce.marketingcloud.storage.db.k;
import com.salesforce.marketingcloud.storage.db.l;
import com.salesforce.marketingcloud.storage.db.m;
import com.salesforce.marketingcloud.util.c;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Locale;

@SuppressLint({"UnknownNullness"})
public class j extends b {
    private static final String B = "mcsdk_cache_%s";
    private static final String C = "ETStorage.version";
    private static final int D = 1;
    private d A;
    private final c o;
    private final SharedPreferences p;
    private final l q;
    private final f r;
    private a s;
    private i t;
    private com.salesforce.marketingcloud.storage.db.j u;
    private k v;
    private h w;
    private g x;
    private f y;
    private m z;

    public j(Context context, c cVar, String str, String str2, com.salesforce.marketingcloud.internal.l lVar) {
        super(context, cVar, str, str2);
        l lVar2 = new l(context, cVar, this.h);
        this.q = lVar2;
        lVar2.getWritableDatabase();
        c.a aVar = new c.a(context, cVar, this.h);
        this.o = aVar;
        SharedPreferences sharedPreferences = context.getSharedPreferences(b.a(this.h), 0);
        this.p = sharedPreferences;
        this.r = new f(context, sharedPreferences, str, lVar);
        if (lVar2.a()) {
            aVar.a();
            sharedPreferences.edit().clear().apply();
        }
    }

    private void d(com.salesforce.marketingcloud.util.c cVar) throws Exception {
        this.p.edit().putString("create_date", cVar.a(String.valueOf(System.currentTimeMillis()))).apply();
    }

    private void l() throws Exception {
        File k = k();
        if (k.exists() && k.isDirectory()) {
            com.salesforce.marketingcloud.util.g.a(k);
        }
        w();
        this.q.b();
    }

    private void w() throws Exception {
        c().a();
        f().edit().clear().apply();
        d(this.j);
    }

    /* access modifiers changed from: protected */
    public final Context a() {
        return this.i;
    }

    /* access modifiers changed from: protected */
    public void a(Context context, int i, int i2) {
    }

    public com.salesforce.marketingcloud.util.c b() {
        return this.j;
    }

    public c c() {
        return this.o;
    }

    /* access modifiers changed from: protected */
    public SQLiteOpenHelper e() {
        return this.q;
    }

    public SharedPreferences f() {
        return this.p;
    }

    public File k() {
        return new File(this.i.getCacheDir(), String.format(Locale.ENGLISH, B, new Object[]{this.h}));
    }

    public a m() {
        if (this.s == null) {
            this.s = new a(this.q.getWritableDatabase());
        }
        return this.s;
    }

    public d n() {
        if (this.A == null) {
            this.A = new e(this.q.getWritableDatabase());
        }
        return this.A;
    }

    public f o() {
        return this.r;
    }

    public g p() {
        if (this.y == null) {
            this.y = new f(this.q.getWritableDatabase());
        }
        return this.y;
    }

    public h q() {
        if (this.x == null) {
            this.x = new g(this.q.getWritableDatabase());
        }
        return this.x;
    }

    public i r() {
        if (this.w == null) {
            this.w = new h(this.q.getWritableDatabase());
        }
        return this.w;
    }

    public k s() {
        if (this.t == null) {
            this.t = new i(this.q.getWritableDatabase());
        }
        return this.t;
    }

    public l t() {
        if (this.u == null) {
            this.u = new com.salesforce.marketingcloud.storage.db.j(this.q.getWritableDatabase());
        }
        return this.u;
    }

    public m u() {
        if (this.v == null) {
            this.v = new k(this.q.getWritableDatabase());
        }
        return this.v;
    }

    public n v() {
        if (this.z == null) {
            this.z = new m(this.q.getWritableDatabase());
        }
        return this.z;
    }

    public final void x() {
        this.q.close();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0043 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.salesforce.marketingcloud.InitializationStatus.a r5) {
        /*
            r4 = this;
            android.content.SharedPreferences r0 = r4.p
            boolean r0 = r4.a((android.content.SharedPreferences) r0)
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x002c
            android.content.SharedPreferences r0 = r4.p
            java.lang.String r3 = "create_date"
            boolean r0 = r0.contains(r3)
            r5.a((boolean) r0)
            if (r0 == 0) goto L_0x002c
            r4.l()     // Catch:{ Exception -> 0x001b }
            goto L_0x002c
        L_0x001b:
            r0 = move-exception
            r5.a((java.lang.Throwable) r0)
            r5.f(r2)
            java.lang.String r5 = com.salesforce.marketingcloud.storage.e.c
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "Failed to recover from encryption change."
            com.salesforce.marketingcloud.g.b(r5, r0, r2, r1)
            return
        L_0x002c:
            com.salesforce.marketingcloud.storage.db.l r0 = r4.q     // Catch:{ a -> 0x0043, IllegalStateException -> 0x0032 }
            r0.c()     // Catch:{ a -> 0x0043, IllegalStateException -> 0x0032 }
            goto L_0x0046
        L_0x0032:
            r0 = move-exception
            r5.a((java.lang.Throwable) r0)
            r5.f(r2)
            java.lang.String r5 = com.salesforce.marketingcloud.storage.e.c
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "Could not create the necessary database table(s)."
            com.salesforce.marketingcloud.g.b(r5, r0, r2, r1)
            return
        L_0x0043:
            r4.w()     // Catch:{ Exception -> 0x006c }
        L_0x0046:
            android.content.SharedPreferences r5 = r4.p
            r0 = -1
            java.lang.String r1 = "ETStorage.version"
            int r5 = r5.getInt(r1, r0)
            if (r5 == 0) goto L_0x006b
            if (r5 == r2) goto L_0x006b
            android.content.Context r0 = r4.i
            if (r2 >= r5) goto L_0x005b
            r4.a(r0, r5, r2)
            goto L_0x005e
        L_0x005b:
            r4.b(r0, r5, r2)
        L_0x005e:
            android.content.SharedPreferences r5 = r4.p
            android.content.SharedPreferences$Editor r5 = r5.edit()
            android.content.SharedPreferences$Editor r5 = r5.putInt(r1, r2)
            r5.apply()
        L_0x006b:
            return
        L_0x006c:
            r0 = move-exception
            r5.a((java.lang.Throwable) r0)
            r5.f(r2)
            java.lang.String r5 = com.salesforce.marketingcloud.storage.e.c
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r2 = "Failed to recover from data reset."
            com.salesforce.marketingcloud.g.b(r5, r0, r2, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.j.a(com.salesforce.marketingcloud.InitializationStatus$a):void");
    }

    /* access modifiers changed from: protected */
    public boolean a(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString("create_date", (String) null);
        if (string == null) {
            return false;
        }
        try {
            this.j.b(string);
            return true;
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            com.salesforce.marketingcloud.g.b(e.c, e, "Failed to verify existing encryption key", new Object[0]);
            return false;
        }
    }
}
