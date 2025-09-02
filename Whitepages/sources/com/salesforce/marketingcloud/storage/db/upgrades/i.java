package com.salesforce.marketingcloud.storage.db.upgrades;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.salesforce.marketingcloud.g;

public class i {
    private static final String a = g.a("Version8ToVersion9");

    private i() {
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("ALTER TABLE analytic_item ADD COLUMN predictive_intelligence_identifier VARCHAR DEFAULT NULL");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            try {
                g.b(a, e, "Unable to update analytic_item table", new Object[0]);
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS analytic_item");
                sQLiteDatabase.execSQL("CREATE TABLE analytic_item (id INTEGER PRIMARY KEY AUTOINCREMENT, event_date VARCHAR, analytic_product_type INTEGER, analytic_type INTEGER, value INTEGER, ready_to_send SMALLINT, object_ids VARCHAR, json_payload VARCHAR, request_id VARCHAR, predictive_intelligence_identifier VARCHAR DEFAULT NULL);");
                sQLiteDatabase.setTransactionSuccessful();
            } catch (SQLException e2) {
                g.b(a, e2, "Unable to create analytic_item table", new Object[0]);
            } catch (Throwable th) {
                sQLiteDatabase.endTransaction();
                throw th;
            }
        }
        sQLiteDatabase.endTransaction();
    }

    public static void b(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
    }
}
