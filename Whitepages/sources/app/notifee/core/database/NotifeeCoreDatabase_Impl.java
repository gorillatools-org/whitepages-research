package app.notifee.core.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import n.o.t.i.f.e.e.o;
import n.o.t.i.f.e.e.p;

public final class NotifeeCoreDatabase_Impl extends NotifeeCoreDatabase {
    public volatile o d;

    public class a extends RoomOpenHelper.Delegate {
        public a(int i) {
            super(i);
        }

        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `work_data` (`id` TEXT NOT NULL, `notification` BLOB, `trigger` BLOB, `with_alarm_manager` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`id`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '24b2477514809255df232947ce7928c4')");
        }

        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `work_data`");
            if (NotifeeCoreDatabase_Impl.this.mCallbacks != null) {
                int size = NotifeeCoreDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) NotifeeCoreDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (NotifeeCoreDatabase_Impl.this.mCallbacks != null) {
                int size = NotifeeCoreDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) NotifeeCoreDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            SupportSQLiteDatabase unused = NotifeeCoreDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
            NotifeeCoreDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (NotifeeCoreDatabase_Impl.this.mCallbacks != null) {
                int size = NotifeeCoreDatabase_Impl.this.mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) NotifeeCoreDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                }
            }
        }

        public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
        }

        public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap hashMap = new HashMap(4);
            hashMap.put("id", new TableInfo.Column("id", "TEXT", true, 1, (String) null, 1));
            hashMap.put("notification", new TableInfo.Column("notification", "BLOB", false, 0, (String) null, 1));
            hashMap.put("trigger", new TableInfo.Column("trigger", "BLOB", false, 0, (String) null, 1));
            hashMap.put("with_alarm_manager", new TableInfo.Column("with_alarm_manager", "INTEGER", true, 0, "0", 1));
            TableInfo tableInfo = new TableInfo("work_data", hashMap, new HashSet(0), new HashSet(0));
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "work_data");
            if (tableInfo.equals(read)) {
                return new RoomOpenHelper.ValidationResult(true, (String) null);
            }
            return new RoomOpenHelper.ValidationResult(false, "work_data(app.notifee.core.database.WorkDataEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
        }
    }

    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "work_data");
    }

    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(2), "24b2477514809255df232947ce7928c4", "1ddaa4b892e61b0f7010597ddc582ed3")).build());
    }

    public List getAutoMigrations(Map map) {
        return Arrays.asList(new Migration[0]);
    }

    public Set getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    public Map getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(o.class, Collections.emptyList());
        return hashMap;
    }

    public o a() {
        o oVar;
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            try {
                if (this.d == null) {
                    this.d = new p(this);
                }
                oVar = this.d;
            } catch (Throwable th) {
                throw th;
            }
        }
        return oVar;
    }
}
