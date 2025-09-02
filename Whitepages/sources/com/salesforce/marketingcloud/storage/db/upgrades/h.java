package com.salesforce.marketingcloud.storage.db.upgrades;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.g;

@SuppressLint({"Range"})
public class h {
    private static final String a = g.a("Version7ToVersion8");

    private h() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006f A[SYNTHETIC, Splitter:B:28:0x006f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.database.sqlite.SQLiteDatabase r9) {
        /*
            java.lang.String r0 = "DELETE FROM cloud_page_messages WHERE message_type=1"
            java.lang.String r1 = "id"
            r2 = 0
            r3 = 0
            java.lang.String r4 = "SELECT id,read,message_deleted FROM cloud_page_messages WHERE message_type=1"
            android.database.Cursor r4 = r9.rawQuery(r4, r2)     // Catch:{ Exception -> 0x0062 }
            if (r4 == 0) goto L_0x009d
            boolean r5 = r4.moveToFirst()     // Catch:{ Exception -> 0x0062 }
            if (r5 == 0) goto L_0x005d
        L_0x0014:
            r5 = 1
            java.lang.String r6 = "message_deleted"
            int r6 = r4.getColumnIndex(r6)     // Catch:{ Exception -> 0x0054 }
            int r6 = r4.getInt(r6)     // Catch:{ Exception -> 0x0054 }
            r7 = -1
            if (r6 != r5) goto L_0x0024
            r6 = 2
            goto L_0x0033
        L_0x0024:
            java.lang.String r6 = "read"
            int r6 = r4.getColumnIndex(r6)     // Catch:{ Exception -> 0x0054 }
            int r6 = r4.getInt(r6)     // Catch:{ Exception -> 0x0054 }
            if (r6 != r5) goto L_0x0032
            r6 = r5
            goto L_0x0033
        L_0x0032:
            r6 = r7
        L_0x0033:
            if (r6 == r7) goto L_0x0056
            android.content.ContentValues r7 = new android.content.ContentValues     // Catch:{ Exception -> 0x0054 }
            r7.<init>()     // Catch:{ Exception -> 0x0054 }
            int r8 = r4.getColumnIndex(r1)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r8 = r4.getString(r8)     // Catch:{ Exception -> 0x0054 }
            r7.put(r1, r8)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r8 = "status"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0054 }
            r7.put(r8, r6)     // Catch:{ Exception -> 0x0054 }
            java.lang.String r6 = "inbox_message_status"
            r9.insert(r6, r2, r7)     // Catch:{ Exception -> 0x0054 }
            goto L_0x0056
        L_0x0054:
            r1 = move-exception
            goto L_0x0064
        L_0x0056:
            boolean r6 = r4.moveToNext()     // Catch:{ Exception -> 0x0054 }
            if (r6 != 0) goto L_0x0014
            goto L_0x005e
        L_0x005d:
            r5 = r3
        L_0x005e:
            r4.close()     // Catch:{ Exception -> 0x0054 }
            goto L_0x006d
        L_0x0062:
            r1 = move-exception
            r5 = r3
        L_0x0064:
            java.lang.String r4 = a
            java.lang.Object[] r6 = new java.lang.Object[r3]
            java.lang.String r7 = "Unable to set inbox message statuses for legacy messages"
            com.salesforce.marketingcloud.g.b(r4, r1, r7, r6)
        L_0x006d:
            if (r5 == 0) goto L_0x009d
            android.content.ContentValues r1 = new android.content.ContentValues     // Catch:{ Exception -> 0x0085 }
            r1.<init>()     // Catch:{ Exception -> 0x0085 }
            java.lang.String r4 = "message_type"
            r5 = 8
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x0085 }
            r1.put(r4, r5)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r4 = "cloud_page_messages"
            r9.update(r4, r1, r2, r2)     // Catch:{ Exception -> 0x0085 }
            goto L_0x009d
        L_0x0085:
            r1 = move-exception
            java.lang.String r4 = a
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.String r6 = "Unable to update message_type for legacy Inbox messages.  Attempting to delete them."
            com.salesforce.marketingcloud.g.b(r4, r1, r6, r5)
            r9.execSQL(r0, r2)     // Catch:{ Exception -> 0x0093 }
            goto L_0x009d
        L_0x0093:
            r1 = move-exception
            java.lang.String r4 = a
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.String r6 = "Unable to delete legacy Inbox messages."
            com.salesforce.marketingcloud.g.b(r4, r1, r6, r5)
        L_0x009d:
            r9.execSQL(r0, r2)     // Catch:{ Exception -> 0x00a1 }
            goto L_0x00ab
        L_0x00a1:
            r9 = move-exception
            java.lang.String r0 = a
            java.lang.Object[] r1 = new java.lang.Object[r3]
            java.lang.String r2 = "Final attempt to delete legacy Inbox messages failed."
            com.salesforce.marketingcloud.g.b(r0, r9, r2, r1)
        L_0x00ab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.upgrades.h.a(android.database.sqlite.SQLiteDatabase):void");
    }
}
