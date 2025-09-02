package com.salesforce.marketingcloud.storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.storage.c;
import com.salesforce.marketingcloud.storage.db.l;
import com.salesforce.marketingcloud.util.c;
import java.io.File;
import java.util.Locale;

@SuppressLint({"UnknownNullness"})
public abstract class b extends e {
    protected static final String k = "create_date";
    private static final String l = "storagedb.db";
    private static final String m = "ETSharedPrefs";

    /* renamed from: n  reason: collision with root package name */
    private static final String f41n = "mcsdk_%s";
    protected final String h;
    protected final Context i;
    protected final c j;

    b(Context context, c cVar, String str, String str2) {
        super(str, str2);
        this.i = context;
        this.j = cVar;
        this.h = str;
        if (!g() && i() && j()) {
            try {
                h();
            } catch (Exception e) {
                g.b(e.c, e, "Unable to migrate data to BU specific storage", new Object[0]);
            }
        }
    }

    static String a(String str) {
        return String.format(Locale.ENGLISH, f41n, new Object[]{str});
    }

    private boolean g() {
        File databasePath = this.i.getDatabasePath(l.a(this.h));
        return databasePath != null && databasePath.exists();
    }

    private void h() {
        File databasePath = this.i.getDatabasePath(l);
        if (databasePath != null && databasePath.exists()) {
            try {
                if (!databasePath.renameTo(new File(databasePath.getParent(), l.a(this.h)))) {
                    g.b(e.c, "Unable to rename storagedb.db to BU specific naming scheme", new Object[0]);
                }
            } catch (Exception e) {
                g.b(e.c, e, "Unable to rename storagedb.db to BU specific naming scheme", new Object[0]);
            }
        }
        File file = new File(this.i.getApplicationInfo().dataDir, "shared_prefs/");
        if (file.exists()) {
            File file2 = new File(file, "ETCustomerPrefs.xml");
            if (file2.exists()) {
                try {
                    if (!file2.renameTo(new File(file, c.a.b(this.h) + ".xml"))) {
                        g.b(e.c, "Unable to rename ETCustomerPrefs.xml to BU specific naming scheme", new Object[0]);
                    }
                } catch (Exception e2) {
                    g.b(e.c, e2, "Unable to rename ETCustomerPrefs.xml to BU specific naming scheme", new Object[0]);
                }
            }
            File file3 = new File(file, "ETSharedPrefs.xml");
            if (file3.exists()) {
                try {
                    if (!file3.renameTo(new File(file, a(this.h) + ".xml"))) {
                        g.b(e.c, "Unable to rename ETSharedPrefs.xml to BU specific naming scheme", new Object[0]);
                    }
                } catch (Exception e3) {
                    g.b(e.c, e3, "Unable to rename ETSharedPrefs.xml to BU specific naming scheme", new Object[0]);
                }
            }
        }
    }

    private boolean i() {
        File databasePath = this.i.getDatabasePath(l);
        return databasePath != null && databasePath.exists();
    }

    private boolean j() {
        return a(this.i.getSharedPreferences(m, 0));
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(SharedPreferences sharedPreferences);
}
