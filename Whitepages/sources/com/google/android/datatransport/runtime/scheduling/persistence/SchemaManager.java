package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Arrays;
import java.util.List;

final class SchemaManager extends SQLiteOpenHelper {
    private static final String CREATE_INITIAL_GLOBAL_LOG_EVENT_STATE_VALUE_SQL = ("INSERT INTO global_log_event_state VALUES (" + System.currentTimeMillis() + ")");
    private static final List INCREMENTAL_MIGRATIONS;
    private static final Migration MIGRATE_TO_V1;
    private static final Migration MIGRATE_TO_V2;
    private static final Migration MIGRATE_TO_V3;
    private static final Migration MIGRATE_TO_V4;
    private static final Migration MIGRATE_TO_V6;
    private static final Migration MIGRATE_TO_V7;
    private static final Migration MIGRATION_TO_V5;
    static int SCHEMA_VERSION = 7;
    private boolean configured = false;
    private final int schemaVersion;

    public interface Migration {
        void upgrade(SQLiteDatabase sQLiteDatabase);
    }

    static {
        SchemaManager$$ExternalSyntheticLambda0 schemaManager$$ExternalSyntheticLambda0 = new SchemaManager$$ExternalSyntheticLambda0();
        MIGRATE_TO_V1 = schemaManager$$ExternalSyntheticLambda0;
        SchemaManager$$ExternalSyntheticLambda1 schemaManager$$ExternalSyntheticLambda1 = new SchemaManager$$ExternalSyntheticLambda1();
        MIGRATE_TO_V2 = schemaManager$$ExternalSyntheticLambda1;
        SchemaManager$$ExternalSyntheticLambda2 schemaManager$$ExternalSyntheticLambda2 = new SchemaManager$$ExternalSyntheticLambda2();
        MIGRATE_TO_V3 = schemaManager$$ExternalSyntheticLambda2;
        SchemaManager$$ExternalSyntheticLambda3 schemaManager$$ExternalSyntheticLambda3 = new SchemaManager$$ExternalSyntheticLambda3();
        MIGRATE_TO_V4 = schemaManager$$ExternalSyntheticLambda3;
        SchemaManager$$ExternalSyntheticLambda4 schemaManager$$ExternalSyntheticLambda4 = new SchemaManager$$ExternalSyntheticLambda4();
        MIGRATION_TO_V5 = schemaManager$$ExternalSyntheticLambda4;
        SchemaManager$$ExternalSyntheticLambda5 schemaManager$$ExternalSyntheticLambda5 = new SchemaManager$$ExternalSyntheticLambda5();
        MIGRATE_TO_V6 = schemaManager$$ExternalSyntheticLambda5;
        SchemaManager$$ExternalSyntheticLambda6 schemaManager$$ExternalSyntheticLambda6 = new SchemaManager$$ExternalSyntheticLambda6();
        MIGRATE_TO_V7 = schemaManager$$ExternalSyntheticLambda6;
        INCREMENTAL_MIGRATIONS = Arrays.asList(new Migration[]{schemaManager$$ExternalSyntheticLambda0, schemaManager$$ExternalSyntheticLambda1, schemaManager$$ExternalSyntheticLambda2, schemaManager$$ExternalSyntheticLambda3, schemaManager$$ExternalSyntheticLambda4, schemaManager$$ExternalSyntheticLambda5, schemaManager$$ExternalSyntheticLambda6});
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE events (_id INTEGER PRIMARY KEY, context_id INTEGER NOT NULL, transport_name TEXT NOT NULL, timestamp_ms INTEGER NOT NULL, uptime_ms INTEGER NOT NULL, payload BLOB NOT NULL, code INTEGER, num_attempts INTEGER NOT NULL,FOREIGN KEY (context_id) REFERENCES transport_contexts(_id) ON DELETE CASCADE)");
        sQLiteDatabase.execSQL("CREATE TABLE event_metadata (_id INTEGER PRIMARY KEY, event_id INTEGER NOT NULL, name TEXT NOT NULL, value TEXT NOT NULL,FOREIGN KEY (event_id) REFERENCES events(_id) ON DELETE CASCADE)");
        sQLiteDatabase.execSQL("CREATE TABLE transport_contexts (_id INTEGER PRIMARY KEY, backend_name TEXT NOT NULL, priority INTEGER NOT NULL, next_request_ms INTEGER NOT NULL)");
        sQLiteDatabase.execSQL("CREATE INDEX events_backend_id on events(context_id)");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX contexts_backend_priority on transport_contexts(backend_name, priority)");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$1(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE transport_contexts ADD COLUMN extras BLOB");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX contexts_backend_priority_extras on transport_contexts(backend_name, priority, extras)");
        sQLiteDatabase.execSQL("DROP INDEX contexts_backend_priority");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$3(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN inline BOOLEAN NOT NULL DEFAULT 1");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS event_payloads");
        sQLiteDatabase.execSQL("CREATE TABLE event_payloads (sequence_num INTEGER NOT NULL, event_id INTEGER NOT NULL, bytes BLOB NOT NULL,FOREIGN KEY (event_id) REFERENCES events(_id) ON DELETE CASCADE,PRIMARY KEY (sequence_num, event_id))");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$4(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS log_event_dropped");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS global_log_event_state");
        sQLiteDatabase.execSQL("CREATE TABLE log_event_dropped (log_source VARCHAR(45) NOT NULL,reason INTEGER NOT NULL,events_dropped_count BIGINT NOT NULL,PRIMARY KEY(log_source, reason))");
        sQLiteDatabase.execSQL("CREATE TABLE global_log_event_state (last_metrics_upload_ms BIGINT PRIMARY KEY)");
        sQLiteDatabase.execSQL(CREATE_INITIAL_GLOBAL_LOG_EVENT_STATE_VALUE_SQL);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$6(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN pseudonymous_id TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN experiment_ids_clear_blob BLOB");
        sQLiteDatabase.execSQL("ALTER TABLE events ADD COLUMN experiment_ids_encrypted_blob BLOB");
    }

    SchemaManager(Context context, String str, int i) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, i);
        this.schemaVersion = i;
    }

    public void onConfigure(SQLiteDatabase sQLiteDatabase) {
        this.configured = true;
        sQLiteDatabase.rawQuery("PRAGMA busy_timeout=0;", new String[0]).close();
        sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
    }

    private void ensureConfigured(SQLiteDatabase sQLiteDatabase) {
        if (!this.configured) {
            onConfigure(sQLiteDatabase);
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        onCreate(sQLiteDatabase, this.schemaVersion);
    }

    private void onCreate(SQLiteDatabase sQLiteDatabase, int i) {
        ensureConfigured(sQLiteDatabase);
        upgrade(sQLiteDatabase, 0, i);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        ensureConfigured(sQLiteDatabase);
        upgrade(sQLiteDatabase, i, i2);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE events");
        sQLiteDatabase.execSQL("DROP TABLE event_metadata");
        sQLiteDatabase.execSQL("DROP TABLE transport_contexts");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS event_payloads");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS log_event_dropped");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS global_log_event_state");
        onCreate(sQLiteDatabase, i2);
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        ensureConfigured(sQLiteDatabase);
    }

    private void upgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        List list = INCREMENTAL_MIGRATIONS;
        if (i2 <= list.size()) {
            while (i < i2) {
                ((Migration) INCREMENTAL_MIGRATIONS.get(i)).upgrade(sQLiteDatabase);
                i++;
            }
            return;
        }
        throw new IllegalArgumentException("Migration from " + i + " to " + i2 + " was requested, but cannot be performed. Only " + list.size() + " migrations are provided");
    }
}
