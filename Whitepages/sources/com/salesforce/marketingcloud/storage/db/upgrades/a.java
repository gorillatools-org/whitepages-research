package com.salesforce.marketingcloud.storage.db.upgrades;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.util.c;

@SuppressLint({"Range"})
public class a {
    private static final String a = g.a("Version10ToVersion11");

    private a() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f6 A[Catch:{ SQLException -> 0x00f9, all -> 0x00d6 }, LOOP:0: B:6:0x0037->B:27:0x00f6, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00f5 A[EDGE_INSN: B:43:0x00f5->B:26:0x00f5 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(android.database.sqlite.SQLiteDatabase r16, com.salesforce.marketingcloud.util.c r17) {
        /*
            r1 = r16
            java.lang.String r2 = "object_ids"
            java.lang.String r3 = "ready_to_send"
            java.lang.String r4 = "value"
            java.lang.String r5 = "analytic_type"
            java.lang.String r6 = "analytic_product_type"
            java.lang.String r7 = "event_date"
            java.lang.String r8 = "id"
            java.lang.String r9 = "CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_type INTEGER, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, enc_json_pi_payload VARCHAR, enc_json_et_payload VARCHAR, predictive_intelligence_identifier VARCHAR DEFAULT NULL)"
            r10 = 0
            r16.beginTransaction()     // Catch:{ SQLException -> 0x00f9 }
            java.lang.String r0 = "CREATE TEMPORARY TABLE analytic_item_temp (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_type INTEGER, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, json_payload VARCHAR, request_id VARCHAR, predictive_intelligence_identifier VARCHAR DEFAULT NULL)"
            r1.execSQL(r0)     // Catch:{ SQLException -> 0x00f9 }
            java.lang.String r0 = "INSERT INTO analytic_item_temp SELECT id,event_date,analytic_product_type,analytic_type,value,ready_to_send,object_ids,json_payload,request_id,predictive_intelligence_identifier FROM analytic_item"
            r1.execSQL(r0)     // Catch:{ SQLException -> 0x00f9 }
            java.lang.String r0 = "DROP TABLE analytic_item"
            r1.execSQL(r0)     // Catch:{ SQLException -> 0x00f9 }
            r1.execSQL(r9)     // Catch:{ SQLException -> 0x00f9 }
            java.lang.String r0 = "SELECT * FROM analytic_item_temp"
            r11 = 0
            android.database.Cursor r12 = r1.rawQuery(r0, r11)     // Catch:{ SQLException -> 0x00f9 }
            if (r12 == 0) goto L_0x00fe
            boolean r0 = r12.moveToFirst()     // Catch:{ SQLException -> 0x00f9 }
            if (r0 == 0) goto L_0x00fb
        L_0x0037:
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch:{ SQLException -> 0x00f9 }
            r0.<init>()     // Catch:{ SQLException -> 0x00f9 }
            int r13 = r12.getColumnIndex(r8)     // Catch:{ Exception -> 0x00da }
            int r13 = r12.getInt(r13)     // Catch:{ Exception -> 0x00da }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Exception -> 0x00da }
            r0.put(r8, r13)     // Catch:{ Exception -> 0x00da }
            int r13 = r12.getColumnIndex(r7)     // Catch:{ Exception -> 0x00da }
            java.lang.String r13 = r12.getString(r13)     // Catch:{ Exception -> 0x00da }
            r0.put(r7, r13)     // Catch:{ Exception -> 0x00da }
            int r13 = r12.getColumnIndex(r6)     // Catch:{ Exception -> 0x00da }
            int r13 = r12.getInt(r13)     // Catch:{ Exception -> 0x00da }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Exception -> 0x00da }
            r0.put(r6, r13)     // Catch:{ Exception -> 0x00da }
            int r13 = r12.getColumnIndex(r5)     // Catch:{ Exception -> 0x00da }
            int r13 = r12.getInt(r13)     // Catch:{ Exception -> 0x00da }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Exception -> 0x00da }
            r0.put(r5, r13)     // Catch:{ Exception -> 0x00da }
            int r13 = r12.getColumnIndex(r4)     // Catch:{ Exception -> 0x00da }
            int r13 = r12.getInt(r13)     // Catch:{ Exception -> 0x00da }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Exception -> 0x00da }
            r0.put(r4, r13)     // Catch:{ Exception -> 0x00da }
            int r13 = r12.getColumnIndex(r3)     // Catch:{ Exception -> 0x00da }
            int r13 = r12.getInt(r13)     // Catch:{ Exception -> 0x00da }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ Exception -> 0x00da }
            r0.put(r3, r13)     // Catch:{ Exception -> 0x00da }
            int r13 = r12.getColumnIndex(r2)     // Catch:{ Exception -> 0x00da }
            java.lang.String r13 = r12.getString(r13)     // Catch:{ Exception -> 0x00da }
            r0.put(r2, r13)     // Catch:{ Exception -> 0x00da }
            java.lang.String r13 = "enc_json_pi_payload"
            java.lang.String r14 = "json_payload"
            int r14 = r12.getColumnIndex(r14)     // Catch:{ Exception -> 0x00da }
            java.lang.String r14 = r12.getString(r14)     // Catch:{ Exception -> 0x00da }
            r0.put(r13, r14)     // Catch:{ Exception -> 0x00da }
            java.lang.String r13 = "request_id"
            int r13 = r12.getColumnIndex(r13)     // Catch:{ Exception -> 0x00da }
            java.lang.String r13 = r12.getString(r13)     // Catch:{ Exception -> 0x00da }
            boolean r14 = android.text.TextUtils.isEmpty(r13)     // Catch:{ Exception -> 0x00da }
            if (r14 != 0) goto L_0x00de
            org.json.JSONObject r14 = new org.json.JSONObject     // Catch:{ Exception -> 0x00da }
            r14.<init>()     // Catch:{ Exception -> 0x00da }
            java.lang.String r15 = "requestId"
            r14.put(r15, r13)     // Catch:{ Exception -> 0x00da }
            java.lang.String r13 = "enc_json_et_payload"
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x00da }
            r15 = r17
            java.lang.String r14 = r15.a(r14)     // Catch:{ Exception -> 0x00d8 }
            r0.put(r13, r14)     // Catch:{ Exception -> 0x00d8 }
            goto L_0x00e0
        L_0x00d6:
            r0 = move-exception
            goto L_0x0126
        L_0x00d8:
            r0 = move-exception
            goto L_0x00e6
        L_0x00da:
            r0 = move-exception
            r15 = r17
            goto L_0x00e6
        L_0x00de:
            r15 = r17
        L_0x00e0:
            java.lang.String r13 = "analytic_item"
            r1.insert(r13, r11, r0)     // Catch:{ Exception -> 0x00d8 }
            goto L_0x00ef
        L_0x00e6:
            java.lang.String r13 = a     // Catch:{ SQLException -> 0x00f9 }
            java.lang.String r14 = "Failed to update item in Analytics local storage during upgrade."
            java.lang.Object[] r11 = new java.lang.Object[r10]     // Catch:{ SQLException -> 0x00f9 }
            com.salesforce.marketingcloud.g.b(r13, r0, r14, r11)     // Catch:{ SQLException -> 0x00f9 }
        L_0x00ef:
            boolean r0 = r12.moveToNext()     // Catch:{ SQLException -> 0x00f9 }
            if (r0 != 0) goto L_0x00f6
            goto L_0x00fb
        L_0x00f6:
            r11 = 0
            goto L_0x0037
        L_0x00f9:
            r0 = move-exception
            goto L_0x010a
        L_0x00fb:
            r12.close()     // Catch:{ SQLException -> 0x00f9 }
        L_0x00fe:
            java.lang.String r0 = "DROP TABLE analytic_item_temp"
            r1.execSQL(r0)     // Catch:{ SQLException -> 0x00f9 }
            r16.setTransactionSuccessful()     // Catch:{ SQLException -> 0x00f9 }
        L_0x0106:
            r16.endTransaction()
            goto L_0x0125
        L_0x010a:
            java.lang.String r2 = a     // Catch:{ all -> 0x00d6 }
            java.lang.String r3 = "Failed to upgrade Analytics local storage.  Starting fresh.  Some analytics items may have been lost."
            java.lang.Object[] r4 = new java.lang.Object[r10]     // Catch:{ all -> 0x00d6 }
            com.salesforce.marketingcloud.g.b(r2, r0, r3, r4)     // Catch:{ all -> 0x00d6 }
            r1.execSQL(r9)     // Catch:{ SQLException -> 0x011a }
            r16.setTransactionSuccessful()     // Catch:{ SQLException -> 0x011a }
            goto L_0x0106
        L_0x011a:
            r0 = move-exception
            java.lang.String r2 = a     // Catch:{ all -> 0x00d6 }
            java.lang.String r3 = "Failed to create local storage for Analytics."
            java.lang.Object[] r4 = new java.lang.Object[r10]     // Catch:{ all -> 0x00d6 }
            com.salesforce.marketingcloud.g.b(r2, r0, r3, r4)     // Catch:{ all -> 0x00d6 }
            goto L_0x0106
        L_0x0125:
            return
        L_0x0126:
            r16.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.db.upgrades.a.a(android.database.sqlite.SQLiteDatabase, com.salesforce.marketingcloud.util.c):void");
    }

    public static void b(SQLiteDatabase sQLiteDatabase, c cVar) {
        a(sQLiteDatabase, cVar);
    }
}
