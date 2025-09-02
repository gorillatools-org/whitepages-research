package com.salesforce.marketingcloud.storage.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import com.salesforce.marketingcloud.storage.h;
import com.salesforce.marketingcloud.util.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SuppressLint({"UnknownNullness", "Range"})
public final class g extends b implements h {
    public static final String e = "inbox_messages";
    private static final String f = "(start_date IS NULL OR start_date<?) AND (end_date IS NULL OR end_date>?)";
    static final String g = com.salesforce.marketingcloud.g.a("InboxMessageDbStorage");
    private static final String[] h = {"id", "start_date", "is_deleted", "is_read", "message_hash", "is_dirty"};

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.salesforce.marketingcloud.storage.h$a[] r0 = com.salesforce.marketingcloud.storage.h.a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                com.salesforce.marketingcloud.storage.h$a r1 = com.salesforce.marketingcloud.storage.h.a.READ     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.salesforce.marketingcloud.storage.h$a r1 = com.salesforce.marketingcloud.storage.h.a.UNREAD     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.salesforce.marketingcloud.storage.h$a r1 = com.salesforce.marketingcloud.storage.h.a.DELETED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.salesforce.marketingcloud.storage.h$a r1 = com.salesforce.marketingcloud.storage.h.a.NOT_DELETED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.g.a.<clinit>():void");
        }
    }

    public g(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE inbox_messages(id TEXT PRIMARY KEY, start_date INTEGER DEFAULT NULL, end_date INTEGER DEFAULT NULL, is_deleted INTEGER DEFAULT 0, is_read INTEGER DEFAULT 0, is_dirty INTEGER DEFAULT 0, message_hash TEXT DEFAULT NULL, message_json TEXT);");
    }

    private static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement("SELECT id,start_date,end_date,is_deleted,is_read,is_dirty,message_hash,message_json FROM inbox_messages");
            return true;
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.e(g, e2, "%s is invalid", e);
            return false;
        }
    }

    public void a(InboxMessage inboxMessage, c cVar) {
        ContentValues a2 = d.a(inboxMessage, cVar);
        if (a(a2, "id = ?", new String[]{inboxMessage.id()}) == 0) {
            a(a2);
        }
    }

    public void d(String str) {
        String valueOf = String.valueOf(System.currentTimeMillis());
        this.c.execSQL("UPDATE inbox_messages SET   is_read = 1,  is_dirty = CASE WHEN is_dirty=1 OR is_deleted=0 THEN 1 ELSE 0 END WHERE   id=? AND (start_date IS NULL OR start_date<?) AND (end_date IS NULL OR end_date>?) AND is_read=0", new String[]{str, valueOf, valueOf});
    }

    public boolean e(String str) {
        return DatabaseUtils.queryNumEntries(this.c, e, "id=?", new String[]{str}) > 0;
    }

    public h.b f(String str) {
        Cursor a2 = a(h, "id=?", new String[]{str});
        h.b bVar = null;
        if (a2 != null) {
            if (a2.moveToFirst()) {
                bVar = a(a2);
            }
            a2.close();
        }
        return bVar;
    }

    public int h() {
        return i((String) null);
    }

    public List<h.b> i() {
        ArrayList arrayList = null;
        Cursor a2 = a(h, "is_dirty=1", (String[]) null);
        if (a2 != null) {
            if (a2.moveToFirst()) {
                ArrayList arrayList2 = new ArrayList(a2.getCount());
                do {
                    arrayList2.add(a(a2));
                } while (a2.moveToNext());
                arrayList = arrayList2;
            }
            a2.close();
        }
        return arrayList != null ? arrayList : Collections.emptyList();
    }

    public void j() {
        String valueOf = String.valueOf(System.currentTimeMillis());
        this.c.execSQL("UPDATE inbox_messages SET   is_read = 1,  is_dirty = CASE WHEN is_dirty=1 OR is_deleted=0 THEN 1 ELSE 0 END WHERE (start_date IS NULL OR start_date<?) AND (end_date IS NULL OR end_date>?) AND is_read=0", new String[]{valueOf, valueOf});
    }

    public List<InboxMessage> n(c cVar) {
        return a(a((String[]) null, (String) null, (String[]) null), cVar);
    }

    /* access modifiers changed from: package-private */
    public String o() {
        return e;
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS inbox_messages");
    }

    static boolean d(SQLiteDatabase sQLiteDatabase) {
        boolean c = c(sQLiteDatabase);
        if (c) {
            return c;
        }
        try {
            a(sQLiteDatabase);
            b(sQLiteDatabase);
            return c(sQLiteDatabase);
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(g, e2, "Unable to recover %s", e);
            return c;
        }
    }

    public void b() {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("is_dirty", 1);
        contentValues.put("is_deleted", 1);
        String valueOf = String.valueOf(System.currentTimeMillis());
        a(contentValues, "(start_date IS NULL OR start_date<?) AND (end_date IS NULL OR end_date>?) AND is_deleted=0", new String[]{valueOf, valueOf});
    }

    public void c(String str) {
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("is_dirty", 1);
        contentValues.put("is_deleted", 1);
        String valueOf = String.valueOf(System.currentTimeMillis());
        a(contentValues, "id=? AND (start_date IS NULL OR start_date<?) AND (end_date IS NULL OR end_date>?) AND is_deleted=0", new String[]{str, valueOf, valueOf});
    }

    private static String c(h.a aVar) {
        String str;
        StringBuilder sb = new StringBuilder(101);
        sb.append(f);
        int i = a.a[aVar.ordinal()];
        if (i == 1 || i == 2) {
            str = " AND is_read=? AND is_deleted=?";
        } else if (i == 3 || i == 4) {
            str = " AND is_deleted=?";
        } else {
            throw new IllegalArgumentException("Unknown MessageStatus while getting message counts.");
        }
        sb.append(str);
        return sb.toString();
    }

    public InboxMessage a(String str, c cVar) {
        Cursor a2 = a((String[]) null, "id=?", new String[]{str}, (String) null, (String) null, (String) null, "1");
        InboxMessage inboxMessage = null;
        if (a2 != null) {
            if (a2.moveToFirst()) {
                inboxMessage = d.a(a2, cVar);
            }
            a2.close();
        }
        return inboxMessage;
    }

    public void b(String[] strArr) {
        if (strArr.length > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_dirty", 0);
            try {
                a(contentValues, (Collection<String>) Arrays.asList(strArr));
            } catch (Exception unused) {
                com.salesforce.marketingcloud.g.e(g, "Unable to update %s table.", o());
            }
        }
    }

    private static String[] b(h.a aVar) {
        String valueOf = String.valueOf(System.currentTimeMillis());
        int i = a.a[aVar.ordinal()];
        String str = "1";
        if (i == 1 || i == 2) {
            if (aVar != h.a.READ) {
                str = "0";
            }
            return new String[]{valueOf, valueOf, str, "0"};
        } else if (i == 3 || i == 4) {
            if (aVar != h.a.DELETED) {
                str = "0";
            }
            return new String[]{valueOf, valueOf, str};
        } else {
            throw new IllegalArgumentException("Unknown MessageStatus while getting message counts.");
        }
    }

    public int a(h.a aVar) {
        return (int) DatabaseUtils.queryNumEntries(this.c, e, c(aVar), b(aVar));
    }

    public List<InboxMessage> a(c cVar, h.a aVar) {
        String c = c(aVar);
        String[] b = b(aVar);
        return a(a((String[]) null, c, b, (String) null, (String) null, "IFNULL(start_date, " + System.currentTimeMillis() + ") DESC"), cVar);
    }

    private static List<InboxMessage> a(Cursor cursor, c cVar) {
        List<InboxMessage> emptyList = Collections.emptyList();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                ArrayList arrayList = new ArrayList();
                do {
                    InboxMessage a2 = d.a(cursor, cVar);
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                } while (cursor.moveToNext());
                emptyList = arrayList;
            }
            cursor.close();
        }
        return emptyList;
    }

    private static h.b a(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("start_date");
        return new h.b(cursor.getString(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("message_hash")), cursor.isNull(columnIndex) ? null : new Date(cursor.getLong(columnIndex)), cursor.getInt(cursor.getColumnIndex("is_read")) == 1, cursor.getInt(cursor.getColumnIndex("is_deleted")) == 1, cursor.getInt(cursor.getColumnIndex("is_dirty")) == 1);
    }

    public int a(List<String> list) {
        if (list.size() == 0) {
            return a((String) null, (String[]) null);
        }
        try {
            return a(o(), (Collection<String>) list);
        } catch (Exception unused) {
            com.salesforce.marketingcloud.g.e(g, "Unable to clean up %s table.", o());
            return 0;
        }
    }
}
