package com.salesforce.marketingcloud.storage.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.f;
import com.salesforce.marketingcloud.messages.Message;
import com.salesforce.marketingcloud.storage.k;
import com.salesforce.marketingcloud.util.c;
import com.salesforce.marketingcloud.util.l;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@SuppressLint({"UnknownNullness"})
public final class i extends b implements k {
    public static final String e = "messages";
    private static final String[] f = {"id", "title", "alert", "sound", a.e, a.f, a.g, "start_date", "end_date", "message_type", "content_type", "url", a.m, "keys", a.o, a.q, a.r, a.p, a.s, a.t, a.u, a.v, a.w, a.x, a.y};
    private static final String g = "CREATE TABLE messages (id VARCHAR PRIMARY KEY, title VARCHAR, alert VARCHAR, sound VARCHAR, mediaUrl VARCHAR, mediaAlt VARCHAR, open_direct VARCHAR, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, custom VARCHAR, keys VARCHAR, period_show_count INTEGER, last_shown_date VARCHAR, next_allowed_show VARCHAR, show_count INTEGER, message_limit INTEGER, rolling_period SMALLINT, period_type INTEGER, number_of_periods INTEGER, messages_per_period INTEGER, proximity INTEGER, notify_id INTEGER );";
    private static final String h = g.a("MessageDbStorage");

    public static class a {
        public static final String a = "id";
        public static final String b = "title";
        public static final String c = "alert";
        public static final String d = "sound";
        public static final String e = "mediaUrl";
        public static final String f = "mediaAlt";
        public static final String g = "open_direct";
        public static final String h = "start_date";
        public static final String i = "end_date";
        public static final String j = "message_type";
        public static final String k = "content_type";
        public static final String l = "url";
        public static final String m = "custom";

        /* renamed from: n  reason: collision with root package name */
        public static final String f42n = "keys";
        public static final String o = "period_show_count";
        public static final String p = "show_count";
        public static final String q = "last_shown_date";
        public static final String r = "next_allowed_show";
        public static final String s = "message_limit";
        public static final String t = "rolling_period";
        public static final String u = "period_type";
        public static final String v = "number_of_periods";
        public static final String w = "messages_per_period";
        public static final String x = "proximity";
        public static final String y = "notify_id";
    }

    public i(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }

    private static boolean c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.compileStatement(c.a("SELECT %s FROM %s", TextUtils.join(",", f), e));
            return true;
        } catch (Exception e2) {
            g.e(h, e2, "%s is invalid", e);
            return false;
        }
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
            g.b(h, e2, "Unable to recover %s", e);
            return c;
        }
    }

    public void a(Message message, c cVar) throws Exception {
        ContentValues b = b(message, cVar);
        if (a(b, a("%s = ?", "id"), new String[]{message.id()}) == 0) {
            a(b);
        }
    }

    public List<Message> b(c cVar) {
        List<Message> emptyList = Collections.emptyList();
        Cursor a2 = a(f, a(5));
        if (a2 != null) {
            if (a2.moveToFirst()) {
                ArrayList arrayList = new ArrayList(a2.getCount());
                do {
                    Message b = d.b(a2, cVar);
                    if (b != null) {
                        arrayList.add(b);
                    }
                } while (a2.moveToNext());
                emptyList = arrayList;
            }
            a2.close();
        }
        return emptyList;
    }

    public int e(int i) {
        return a(a("%s = ?", "message_type"), new String[]{String.valueOf(i)});
    }

    /* access modifiers changed from: package-private */
    public String o() {
        return e;
    }

    private String a(int... iArr) {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (int i : iArr) {
            if (z) {
                sb.append("message_type");
                sb.append(" IN(");
                z = false;
            } else {
                sb.append(',');
            }
            sb.append(i);
        }
        sb.append(");");
        return sb.toString();
    }

    static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(g);
    }

    private static ContentValues b(Message message, c cVar) throws Exception {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", message.id());
        contentValues.put("title", cVar.a(message.title()));
        contentValues.put("alert", cVar.a(message.alert()));
        contentValues.put("sound", message.sound());
        if (message.media() != null) {
            contentValues.put(a.e, cVar.a(message.media().url()));
            contentValues.put(a.f, cVar.a(message.media().altText()));
        }
        contentValues.put("start_date", l.a(message.startDateUtc()));
        contentValues.put("end_date", l.a(message.endDateUtc()));
        contentValues.put("message_type", Integer.valueOf(message.messageType()));
        contentValues.put("content_type", Integer.valueOf(message.contentType()));
        contentValues.put("url", cVar.a(message.url()));
        contentValues.put(a.m, cVar.a(message.custom()));
        contentValues.put(a.w, Integer.valueOf(message.messagesPerPeriod()));
        contentValues.put(a.v, Integer.valueOf(message.numberOfPeriods()));
        contentValues.put(a.u, Integer.valueOf(message.periodType()));
        contentValues.put(a.t, Integer.valueOf(message.isRollingPeriod() ? 1 : 0));
        contentValues.put(a.s, Integer.valueOf(message.messageLimit()));
        contentValues.put(a.x, Integer.valueOf(message.proximity()));
        contentValues.put(a.g, cVar.a(message.openDirect()));
        contentValues.put("keys", cVar.a(l.a(message.customKeys())));
        contentValues.put(a.r, l.a(f.b(message)));
        contentValues.put(a.o, Integer.valueOf(f.d(message)));
        contentValues.put(a.y, Integer.valueOf(f.c(message)));
        contentValues.put(a.p, Integer.valueOf(f.e(message)));
        contentValues.put(a.q, l.a(f.a(message)));
        return contentValues;
    }

    public int a(String str) {
        return a(a("%s = ?", "id"), new String[]{str});
    }

    static void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS messages");
    }

    private static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    public List<Message> a(c cVar) {
        List<Message> emptyList = Collections.emptyList();
        Cursor a2 = a(f, a(3, 4));
        if (a2 != null) {
            if (a2.moveToFirst()) {
                ArrayList arrayList = new ArrayList(a2.getCount());
                do {
                    Message b = d.b(a2, cVar);
                    if (b != null) {
                        arrayList.add(b);
                    }
                } while (a2.moveToNext());
                emptyList = arrayList;
            }
            a2.close();
        }
        return emptyList;
    }

    public Message a(String str, c cVar) {
        Cursor a2 = a(f, a("%s = ?", "id"), new String[]{str}, (String) null, (String) null, (String) null, "1");
        Message message = null;
        if (a2 != null) {
            if (a2.moveToFirst()) {
                message = d.b(a2, cVar);
            }
            a2.close();
        }
        return message;
    }

    public int a(String str, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(a.y, Integer.valueOf(i));
        return a(contentValues, a("%s = ?", "id"), new String[]{str});
    }
}
