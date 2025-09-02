package com.salesforce.marketingcloud.storage.db.upgrades;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.salesforce.marketingcloud.g;

public class d {
    private static final String a = g.a("Version3ToVersion4");

    private d() {
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("ALTER TABLE regions ADD COLUMN is_inside SMALLINT");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            try {
                sQLiteDatabase.execSQL("CREATE TABLE regions (id VARCHAR PRIMARY KEY, latitude VARCHAR, longitude VARCHAR, radius INTEGER, beacon_guid VARCHAR, beacon_major INTEGER, beacon_minor INTEGER, description VARCHAR, name VARCHAR, location_type INTEGER, is_inside SMALLINT );");
                sQLiteDatabase.setTransactionSuccessful();
                g.b(a, e, "Unable to update regions table", new Object[0]);
            } catch (SQLException e2) {
                g.b(a, e2, "Unable to create regions table", new Object[0]);
            } catch (Throwable th) {
                sQLiteDatabase.endTransaction();
                throw th;
            }
        }
        sQLiteDatabase.endTransaction();
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("CREATE TABLE messages_temp (id VARCHAR PRIMARY KEY, alert VARCHAR, sound VARCHAR, open_direct VARCHAR, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, custom VARCHAR, keys VARCHAR, period_show_count INTEGER, last_shown_date VARCHAR, next_allowed_show VARCHAR, show_count INTEGER, message_limit INTEGER, rolling_period SMALLINT, period_type INTEGER, number_of_periods INTEGER, messages_per_period INTEGER, proximity INTEGER, notify_id INTEGER);");
            sQLiteDatabase.execSQL("INSERT INTO messages_temp SELECT id, alert, sound, open_direct, start_date, end_date, message_type, content_type, url, custom, keys, period_show_count, last_shown_date, next_allowed_show, show_count, message_limit, rolling_period, period_type, number_of_periods, messages_per_period, proximity, notify_id FROM messages");
            sQLiteDatabase.execSQL("DROP TABLE messages");
            sQLiteDatabase.execSQL("CREATE TABLE messages (id VARCHAR PRIMARY KEY, alert VARCHAR, sound VARCHAR, open_direct VARCHAR, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, custom VARCHAR, keys VARCHAR, period_show_count INTEGER, last_shown_date VARCHAR, next_allowed_show VARCHAR, show_count INTEGER, message_limit INTEGER, rolling_period SMALLINT, period_type INTEGER, number_of_periods INTEGER, messages_per_period INTEGER, proximity INTEGER, notify_id INTEGER);");
            sQLiteDatabase.execSQL("INSERT INTO messages SELECT id, alert, sound, open_direct, start_date, end_date, message_type, content_type, url, custom, keys, period_show_count, last_shown_date, next_allowed_show, show_count, message_limit, rolling_period, period_type, number_of_periods, messages_per_period, proximity, notify_id FROM messages_temp");
            sQLiteDatabase.execSQL("DROP TABLE messages_temp");
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN title VARCHAR;");
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN mediaUrl VARCHAR;");
            sQLiteDatabase.execSQL("ALTER TABLE messages ADD COLUMN mediaAlt VARCHAR;");
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS messages");
                sQLiteDatabase.execSQL("CREATE TABLE messages (id VARCHAR PRIMARY KEY, title VARCHAR, alert VARCHAR, sound VARCHAR, mediaUrl VARCHAR, mediaAlt VARCHAR, open_direct VARCHAR, start_date VARCHAR, end_date VARCHAR, message_type INTEGER, content_type INTEGER, url VARCHAR, custom VARCHAR, keys VARCHAR, period_show_count INTEGER, last_shown_date VARCHAR, next_allowed_show VARCHAR, show_count INTEGER, message_limit INTEGER, rolling_period SMALLINT, period_type INTEGER, number_of_periods INTEGER, messages_per_period INTEGER, proximity INTEGER, notify_id INTEGER);");
                sQLiteDatabase.setTransactionSuccessful();
                g.b(a, e, "Unable to update messages table", new Object[0]);
            } catch (SQLException e2) {
                g.b(a, e2, "Unable to create messages table", new Object[0]);
            } catch (Throwable th) {
                sQLiteDatabase.endTransaction();
                throw th;
            }
        }
        sQLiteDatabase.endTransaction();
    }

    public static void c(SQLiteDatabase sQLiteDatabase) {
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }
}
