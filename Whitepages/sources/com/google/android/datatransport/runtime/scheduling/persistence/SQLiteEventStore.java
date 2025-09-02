package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.SystemClock;
import android.util.Base64;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.GlobalMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.firebase.transport.LogSourceMetrics;
import com.google.android.datatransport.runtime.firebase.transport.StorageMetrics;
import com.google.android.datatransport.runtime.firebase.transport.TimeWindow;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.inject.Provider;

public class SQLiteEventStore implements EventStore, SynchronizationGuard, ClientHealthMetricsStore {
    private static final Encoding PROTOBUF_ENCODING = Encoding.of("proto");
    private final EventStoreConfig config;
    private final Clock monotonicClock;
    private final Provider packageName;
    private final SchemaManager schemaManager;
    private final Clock wallClock;

    interface Function {
        Object apply(Object obj);
    }

    interface Producer {
        Object produce();
    }

    SQLiteEventStore(Clock clock, Clock clock2, EventStoreConfig eventStoreConfig, SchemaManager schemaManager2, Provider provider) {
        this.schemaManager = schemaManager2;
        this.wallClock = clock;
        this.monotonicClock = clock2;
        this.config = eventStoreConfig;
        this.packageName = provider;
    }

    /* access modifiers changed from: package-private */
    public SQLiteDatabase getDb() {
        SchemaManager schemaManager2 = this.schemaManager;
        Objects.requireNonNull(schemaManager2);
        return (SQLiteDatabase) retryIfDbLocked(new SQLiteEventStore$$ExternalSyntheticLambda4(schemaManager2), new SQLiteEventStore$$ExternalSyntheticLambda5());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ SQLiteDatabase lambda$getDb$0(Throwable th) {
        throw new SynchronizationException("Timed out while trying to open db.", th);
    }

    public PersistedEvent persist(TransportContext transportContext, EventInternal eventInternal) {
        Logging.d("SQLiteEventStore", "Storing event with priority=%s, name=%s for destination %s", transportContext.getPriority(), eventInternal.getTransportName(), transportContext.getBackendName());
        long longValue = ((Long) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda19(this, eventInternal, transportContext))).longValue();
        if (longValue < 1) {
            return null;
        }
        return PersistedEvent.create(longValue, transportContext, eventInternal);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Long lambda$persist$1(EventInternal eventInternal, TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        byte[] bArr;
        if (isStorageAtLimit()) {
            recordLogEventDropped(1, LogEventDropped.Reason.CACHE_FULL, eventInternal.getTransportName());
            return -1L;
        }
        long ensureTransportContext = ensureTransportContext(sQLiteDatabase, transportContext);
        int maxBlobByteSizePerRow = this.config.getMaxBlobByteSizePerRow();
        byte[] bytes = eventInternal.getEncodedPayload().getBytes();
        boolean z = bytes.length <= maxBlobByteSizePerRow;
        ContentValues contentValues = new ContentValues();
        contentValues.put("context_id", Long.valueOf(ensureTransportContext));
        contentValues.put("transport_name", eventInternal.getTransportName());
        contentValues.put("timestamp_ms", Long.valueOf(eventInternal.getEventMillis()));
        contentValues.put("uptime_ms", Long.valueOf(eventInternal.getUptimeMillis()));
        contentValues.put("payload_encoding", eventInternal.getEncodedPayload().getEncoding().getName());
        contentValues.put("code", eventInternal.getCode());
        contentValues.put("num_attempts", 0);
        contentValues.put("inline", Boolean.valueOf(z));
        if (z) {
            bArr = bytes;
        } else {
            bArr = new byte[0];
        }
        contentValues.put("payload", bArr);
        contentValues.put("product_id", eventInternal.getProductId());
        contentValues.put("pseudonymous_id", eventInternal.getPseudonymousId());
        contentValues.put("experiment_ids_clear_blob", eventInternal.getExperimentIdsClear());
        contentValues.put("experiment_ids_encrypted_blob", eventInternal.getExperimentIdsEncrypted());
        long insert = sQLiteDatabase.insert("events", (String) null, contentValues);
        if (!z) {
            int ceil = (int) Math.ceil(((double) bytes.length) / ((double) maxBlobByteSizePerRow));
            for (int i = 1; i <= ceil; i++) {
                byte[] copyOfRange = Arrays.copyOfRange(bytes, (i - 1) * maxBlobByteSizePerRow, Math.min(i * maxBlobByteSizePerRow, bytes.length));
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("event_id", Long.valueOf(insert));
                contentValues2.put("sequence_num", Integer.valueOf(i));
                contentValues2.put("bytes", copyOfRange);
                sQLiteDatabase.insert("event_payloads", (String) null, contentValues2);
            }
        }
        for (Map.Entry entry : eventInternal.getMetadata().entrySet()) {
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("event_id", Long.valueOf(insert));
            contentValues3.put("name", (String) entry.getKey());
            contentValues3.put("value", (String) entry.getValue());
            sQLiteDatabase.insert("event_metadata", (String) null, contentValues3);
        }
        return Long.valueOf(insert);
    }

    private long ensureTransportContext(SQLiteDatabase sQLiteDatabase, TransportContext transportContext) {
        Long transportContextId = getTransportContextId(sQLiteDatabase, transportContext);
        if (transportContextId != null) {
            return transportContextId.longValue();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("backend_name", transportContext.getBackendName());
        contentValues.put("priority", Integer.valueOf(PriorityMapping.toInt(transportContext.getPriority())));
        contentValues.put("next_request_ms", 0);
        if (transportContext.getExtras() != null) {
            contentValues.put("extras", Base64.encodeToString(transportContext.getExtras(), 0));
        }
        return sQLiteDatabase.insert("transport_contexts", (String) null, contentValues);
    }

    private Long getTransportContextId(SQLiteDatabase sQLiteDatabase, TransportContext transportContext) {
        StringBuilder sb = new StringBuilder("backend_name = ? and priority = ?");
        ArrayList arrayList = new ArrayList(Arrays.asList(new String[]{transportContext.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext.getPriority()))}));
        if (transportContext.getExtras() != null) {
            sb.append(" and extras = ?");
            arrayList.add(Base64.encodeToString(transportContext.getExtras(), 0));
        } else {
            sb.append(" and extras is null");
        }
        return (Long) tryWithCursor(sQLiteDatabase.query("transport_contexts", new String[]{"_id"}, sb.toString(), (String[]) arrayList.toArray(new String[0]), (String) null, (String) null, (String) null), new SQLiteEventStore$$ExternalSyntheticLambda18());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Long lambda$getTransportContextId$2(Cursor cursor) {
        if (!cursor.moveToNext()) {
            return null;
        }
        return Long.valueOf(cursor.getLong(0));
    }

    public void recordFailure(Iterable iterable) {
        if (iterable.iterator().hasNext()) {
            inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda0(this, "UPDATE events SET num_attempts = num_attempts + 1 WHERE _id in " + toIdList(iterable), "SELECT COUNT(*), transport_name FROM events WHERE num_attempts >= 16 GROUP BY transport_name"));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$recordFailure$4(String str, String str2, SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.compileStatement(str).execute();
        tryWithCursor(sQLiteDatabase.rawQuery(str2, (String[]) null), new SQLiteEventStore$$ExternalSyntheticLambda13(this));
        sQLiteDatabase.compileStatement("DELETE FROM events WHERE num_attempts >= 16").execute();
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$recordFailure$3(Cursor cursor) {
        while (cursor.moveToNext()) {
            int i = cursor.getInt(0);
            recordLogEventDropped((long) i, LogEventDropped.Reason.MAX_RETRIES_REACHED, cursor.getString(1));
        }
        return null;
    }

    public void recordSuccess(Iterable iterable) {
        if (iterable.iterator().hasNext()) {
            getDb().compileStatement("DELETE FROM events WHERE _id in " + toIdList(iterable)).execute();
        }
    }

    private static String toIdList(Iterable iterable) {
        StringBuilder sb = new StringBuilder("(");
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            sb.append(((PersistedEvent) it.next()).getId());
            if (it.hasNext()) {
                sb.append(',');
            }
        }
        sb.append(')');
        return sb.toString();
    }

    public long getNextCallTime(TransportContext transportContext) {
        return ((Long) tryWithCursor(getDb().rawQuery("SELECT next_request_ms FROM transport_contexts WHERE backend_name = ? and priority = ?", new String[]{transportContext.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext.getPriority()))}), new SQLiteEventStore$$ExternalSyntheticLambda10())).longValue();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Long lambda$getNextCallTime$5(Cursor cursor) {
        if (cursor.moveToNext()) {
            return Long.valueOf(cursor.getLong(0));
        }
        return 0L;
    }

    public boolean hasPendingEventsFor(TransportContext transportContext) {
        return ((Boolean) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda6(this, transportContext))).booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$hasPendingEventsFor$6(TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        Long transportContextId = getTransportContextId(sQLiteDatabase, transportContext);
        if (transportContextId == null) {
            return Boolean.FALSE;
        }
        return (Boolean) tryWithCursor(getDb().rawQuery("SELECT 1 FROM events WHERE context_id = ? LIMIT 1", new String[]{transportContextId.toString()}), new SQLiteEventStore$$ExternalSyntheticLambda12());
    }

    public void recordNextCallTime(TransportContext transportContext, long j) {
        inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda7(j, transportContext));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$recordNextCallTime$7(long j, TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("next_request_ms", Long.valueOf(j));
        if (sQLiteDatabase.update("transport_contexts", contentValues, "backend_name = ? and priority = ?", new String[]{transportContext.getBackendName(), String.valueOf(PriorityMapping.toInt(transportContext.getPriority()))}) < 1) {
            contentValues.put("backend_name", transportContext.getBackendName());
            contentValues.put("priority", Integer.valueOf(PriorityMapping.toInt(transportContext.getPriority())));
            sQLiteDatabase.insert("transport_contexts", (String) null, contentValues);
        }
        return null;
    }

    public Iterable loadBatch(TransportContext transportContext) {
        return (Iterable) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda3(this, transportContext));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ List lambda$loadBatch$8(TransportContext transportContext, SQLiteDatabase sQLiteDatabase) {
        List loadEvents = loadEvents(sQLiteDatabase, transportContext, this.config.getLoadBatchSize());
        for (Priority priority : Priority.values()) {
            if (priority != transportContext.getPriority()) {
                int loadBatchSize = this.config.getLoadBatchSize() - loadEvents.size();
                if (loadBatchSize <= 0) {
                    break;
                }
                loadEvents.addAll(loadEvents(sQLiteDatabase, transportContext.withPriority(priority), loadBatchSize));
            }
        }
        return join(loadEvents, loadMetadata(sQLiteDatabase, loadEvents));
    }

    public Iterable loadActiveContexts() {
        return (Iterable) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda17());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$loadActiveContexts$10(SQLiteDatabase sQLiteDatabase) {
        return (List) tryWithCursor(sQLiteDatabase.rawQuery("SELECT distinct t._id, t.backend_name, t.priority, t.extras FROM transport_contexts AS t, events AS e WHERE e.context_id = t._id", new String[0]), new SQLiteEventStore$$ExternalSyntheticLambda22());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ List lambda$loadActiveContexts$9(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            arrayList.add(TransportContext.builder().setBackendName(cursor.getString(1)).setPriority(PriorityMapping.valueOf(cursor.getInt(2))).setExtras(maybeBase64Decode(cursor.getString(3))).build());
        }
        return arrayList;
    }

    public int cleanUp() {
        return ((Integer) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda21(this, this.wallClock.getTime() - this.config.getEventCleanUpAge()))).intValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Integer lambda$cleanUp$12(long j, SQLiteDatabase sQLiteDatabase) {
        String[] strArr = {String.valueOf(j)};
        tryWithCursor(sQLiteDatabase.rawQuery("SELECT COUNT(*), transport_name FROM events WHERE timestamp_ms < ? GROUP BY transport_name", strArr), new SQLiteEventStore$$ExternalSyntheticLambda25(this));
        return Integer.valueOf(sQLiteDatabase.delete("events", "timestamp_ms < ?", strArr));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$cleanUp$11(Cursor cursor) {
        while (cursor.moveToNext()) {
            int i = cursor.getInt(0);
            recordLogEventDropped((long) i, LogEventDropped.Reason.MESSAGE_TOO_OLD, cursor.getString(1));
        }
        return null;
    }

    public void close() {
        this.schemaManager.close();
    }

    private static byte[] maybeBase64Decode(String str) {
        if (str == null) {
            return null;
        }
        return Base64.decode(str, 0);
    }

    private List loadEvents(SQLiteDatabase sQLiteDatabase, TransportContext transportContext, int i) {
        ArrayList arrayList = new ArrayList();
        Long transportContextId = getTransportContextId(sQLiteDatabase, transportContext);
        if (transportContextId == null) {
            return arrayList;
        }
        tryWithCursor(sQLiteDatabase.query("events", new String[]{"_id", "transport_name", "timestamp_ms", "uptime_ms", "payload_encoding", "payload", "code", "inline", "product_id", "pseudonymous_id", "experiment_ids_clear_blob", "experiment_ids_encrypted_blob"}, "context_id = ?", new String[]{transportContextId.toString()}, (String) null, (String) null, (String) null, String.valueOf(i)), new SQLiteEventStore$$ExternalSyntheticLambda14(this, arrayList, transportContext));
        return arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$loadEvents$14(List list, TransportContext transportContext, Cursor cursor) {
        while (cursor.moveToNext()) {
            boolean z = false;
            long j = cursor.getLong(0);
            if (cursor.getInt(7) != 0) {
                z = true;
            }
            EventInternal.Builder uptimeMillis = EventInternal.builder().setTransportName(cursor.getString(1)).setEventMillis(cursor.getLong(2)).setUptimeMillis(cursor.getLong(3));
            if (z) {
                uptimeMillis.setEncodedPayload(new EncodedPayload(toEncoding(cursor.getString(4)), cursor.getBlob(5)));
            } else {
                uptimeMillis.setEncodedPayload(new EncodedPayload(toEncoding(cursor.getString(4)), readPayload(j)));
            }
            if (!cursor.isNull(6)) {
                uptimeMillis.setCode(Integer.valueOf(cursor.getInt(6)));
            }
            if (!cursor.isNull(8)) {
                uptimeMillis.setProductId(Integer.valueOf(cursor.getInt(8)));
            }
            if (!cursor.isNull(9)) {
                uptimeMillis.setPseudonymousId(cursor.getString(9));
            }
            if (!cursor.isNull(10)) {
                uptimeMillis.setExperimentIdsClear(cursor.getBlob(10));
            }
            if (!cursor.isNull(11)) {
                uptimeMillis.setExperimentIdsEncrypted(cursor.getBlob(11));
            }
            list.add(PersistedEvent.create(j, transportContext, uptimeMillis.build()));
        }
        return null;
    }

    private byte[] readPayload(long j) {
        return (byte[]) tryWithCursor(getDb().query("event_payloads", new String[]{"bytes"}, "event_id = ?", new String[]{String.valueOf(j)}, (String) null, (String) null, "sequence_num"), new SQLiteEventStore$$ExternalSyntheticLambda23());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ byte[] lambda$readPayload$15(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (cursor.moveToNext()) {
            byte[] blob = cursor.getBlob(0);
            arrayList.add(blob);
            i += blob.length;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            byte[] bArr2 = (byte[]) arrayList.get(i3);
            System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
            i2 += bArr2.length;
        }
        return bArr;
    }

    private static Encoding toEncoding(String str) {
        if (str == null) {
            return PROTOBUF_ENCODING;
        }
        return Encoding.of(str);
    }

    private Map loadMetadata(SQLiteDatabase sQLiteDatabase, List list) {
        HashMap hashMap = new HashMap();
        StringBuilder sb = new StringBuilder("event_id IN (");
        for (int i = 0; i < list.size(); i++) {
            sb.append(((PersistedEvent) list.get(i)).getId());
            if (i < list.size() - 1) {
                sb.append(',');
            }
        }
        sb.append(')');
        tryWithCursor(sQLiteDatabase.query("event_metadata", new String[]{"event_id", "name", "value"}, sb.toString(), (String[]) null, (String) null, (String) null, (String) null), new SQLiteEventStore$$ExternalSyntheticLambda16(hashMap));
        return hashMap;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$loadMetadata$16(Map map, Cursor cursor) {
        while (cursor.moveToNext()) {
            long j = cursor.getLong(0);
            Set set = (Set) map.get(Long.valueOf(j));
            if (set == null) {
                set = new HashSet();
                map.put(Long.valueOf(j), set);
            }
            set.add(new Metadata(cursor.getString(1), cursor.getString(2)));
        }
        return null;
    }

    private List join(List list, Map map) {
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            PersistedEvent persistedEvent = (PersistedEvent) listIterator.next();
            if (map.containsKey(Long.valueOf(persistedEvent.getId()))) {
                EventInternal.Builder builder = persistedEvent.getEvent().toBuilder();
                for (Metadata metadata : (Set) map.get(Long.valueOf(persistedEvent.getId()))) {
                    builder.addMetadata(metadata.key, metadata.value);
                }
                listIterator.set(PersistedEvent.create(persistedEvent.getId(), persistedEvent.getTransportContext(), builder.build()));
            }
        }
        return list;
    }

    private Object retryIfDbLocked(Producer producer, Function function) {
        long time = this.monotonicClock.getTime();
        while (true) {
            try {
                return producer.produce();
            } catch (SQLiteDatabaseLockedException e) {
                if (this.monotonicClock.getTime() >= ((long) this.config.getCriticalSectionEnterTimeoutMs()) + time) {
                    return function.apply(e);
                }
                SystemClock.sleep(50);
            }
        }
    }

    public void recordLogEventDropped(long j, LogEventDropped.Reason reason, String str) {
        inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda9(str, reason, j));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$recordLogEventDropped$18(String str, LogEventDropped.Reason reason, long j, SQLiteDatabase sQLiteDatabase) {
        if (!((Boolean) tryWithCursor(sQLiteDatabase.rawQuery("SELECT 1 FROM log_event_dropped WHERE log_source = ? AND reason = ?", new String[]{str, Integer.toString(reason.getNumber())}), new SQLiteEventStore$$ExternalSyntheticLambda15())).booleanValue()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("log_source", str);
            contentValues.put("reason", Integer.valueOf(reason.getNumber()));
            contentValues.put("events_dropped_count", Long.valueOf(j));
            sQLiteDatabase.insert("log_event_dropped", (String) null, contentValues);
        } else {
            sQLiteDatabase.execSQL("UPDATE log_event_dropped SET events_dropped_count = events_dropped_count + " + j + " WHERE log_source = ? AND reason = ?", new String[]{str, Integer.toString(reason.getNumber())});
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$recordLogEventDropped$17(Cursor cursor) {
        return Boolean.valueOf(cursor.getCount() > 0);
    }

    private LogEventDropped.Reason convertToReason(int i) {
        LogEventDropped.Reason reason = LogEventDropped.Reason.REASON_UNKNOWN;
        if (i == reason.getNumber()) {
            return reason;
        }
        LogEventDropped.Reason reason2 = LogEventDropped.Reason.MESSAGE_TOO_OLD;
        if (i == reason2.getNumber()) {
            return reason2;
        }
        LogEventDropped.Reason reason3 = LogEventDropped.Reason.CACHE_FULL;
        if (i == reason3.getNumber()) {
            return reason3;
        }
        LogEventDropped.Reason reason4 = LogEventDropped.Reason.PAYLOAD_TOO_BIG;
        if (i == reason4.getNumber()) {
            return reason4;
        }
        LogEventDropped.Reason reason5 = LogEventDropped.Reason.MAX_RETRIES_REACHED;
        if (i == reason5.getNumber()) {
            return reason5;
        }
        LogEventDropped.Reason reason6 = LogEventDropped.Reason.INVALID_PAYLOD;
        if (i == reason6.getNumber()) {
            return reason6;
        }
        LogEventDropped.Reason reason7 = LogEventDropped.Reason.SERVER_ERROR;
        if (i == reason7.getNumber()) {
            return reason7;
        }
        Logging.d("SQLiteEventStore", "%n is not valid. No matched LogEventDropped-Reason found. Treated it as REASON_UNKNOWN", (Object) Integer.valueOf(i));
        return reason;
    }

    public ClientMetrics loadClientMetrics() {
        return (ClientMetrics) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda11(this, "SELECT log_source, reason, events_dropped_count FROM log_event_dropped", new HashMap(), ClientMetrics.newBuilder()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ClientMetrics lambda$loadClientMetrics$20(String str, Map map, ClientMetrics.Builder builder, SQLiteDatabase sQLiteDatabase) {
        return (ClientMetrics) tryWithCursor(sQLiteDatabase.rawQuery(str, new String[0]), new SQLiteEventStore$$ExternalSyntheticLambda20(this, map, builder));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ClientMetrics lambda$loadClientMetrics$19(Map map, ClientMetrics.Builder builder, Cursor cursor) {
        while (cursor.moveToNext()) {
            String string = cursor.getString(0);
            LogEventDropped.Reason convertToReason = convertToReason(cursor.getInt(1));
            long j = cursor.getLong(2);
            if (!map.containsKey(string)) {
                map.put(string, new ArrayList());
            }
            ((List) map.get(string)).add(LogEventDropped.newBuilder().setReason(convertToReason).setEventsDroppedCount(j).build());
        }
        populateLogSourcesMetrics(builder, map);
        return builder.setWindow(getTimeWindow()).setGlobalMetrics(getGlobalMetrics()).setAppNamespace((String) this.packageName.get()).build();
    }

    private void populateLogSourcesMetrics(ClientMetrics.Builder builder, Map map) {
        for (Map.Entry entry : map.entrySet()) {
            builder.addLogSourceMetrics(LogSourceMetrics.newBuilder().setLogSource((String) entry.getKey()).setLogEventDroppedList((List) entry.getValue()).build());
        }
    }

    private TimeWindow getTimeWindow() {
        return (TimeWindow) inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda24(this.wallClock.getTime()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ TimeWindow lambda$getTimeWindow$22(long j, SQLiteDatabase sQLiteDatabase) {
        return (TimeWindow) tryWithCursor(sQLiteDatabase.rawQuery("SELECT last_metrics_upload_ms FROM global_log_event_state LIMIT 1", new String[0]), new SQLiteEventStore$$ExternalSyntheticLambda26(j));
    }

    private GlobalMetrics getGlobalMetrics() {
        return GlobalMetrics.newBuilder().setStorageMetrics(StorageMetrics.newBuilder().setCurrentCacheSizeBytes(getByteSize()).setMaxCacheSizeBytes(EventStoreConfig.DEFAULT.getMaxStorageSizeInBytes()).build()).build();
    }

    public void resetClientMetrics() {
        inTransaction(new SQLiteEventStore$$ExternalSyntheticLambda8(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$resetClientMetrics$23(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.compileStatement("DELETE FROM log_event_dropped").execute();
        sQLiteDatabase.compileStatement("UPDATE global_log_event_state SET last_metrics_upload_ms=" + this.wallClock.getTime()).execute();
        return null;
    }

    private void ensureBeginTransaction(SQLiteDatabase sQLiteDatabase) {
        retryIfDbLocked(new SQLiteEventStore$$ExternalSyntheticLambda1(sQLiteDatabase), new SQLiteEventStore$$ExternalSyntheticLambda2());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$ensureBeginTransaction$25(Throwable th) {
        throw new SynchronizationException("Timed out while trying to acquire the lock.", th);
    }

    public Object runCriticalSection(SynchronizationGuard.CriticalSection criticalSection) {
        SQLiteDatabase db = getDb();
        ensureBeginTransaction(db);
        try {
            Object execute = criticalSection.execute();
            db.setTransactionSuccessful();
            return execute;
        } finally {
            db.endTransaction();
        }
    }

    /* access modifiers changed from: package-private */
    public Object inTransaction(Function function) {
        SQLiteDatabase db = getDb();
        db.beginTransaction();
        try {
            Object apply = function.apply(db);
            db.setTransactionSuccessful();
            return apply;
        } finally {
            db.endTransaction();
        }
    }

    private static class Metadata {
        final String key;
        final String value;

        private Metadata(String str, String str2) {
            this.key = str;
            this.value = str2;
        }
    }

    private boolean isStorageAtLimit() {
        return getPageCount() * getPageSize() >= this.config.getMaxStorageSizeInBytes();
    }

    /* access modifiers changed from: package-private */
    public long getByteSize() {
        return getPageCount() * getPageSize();
    }

    private long getPageSize() {
        return getDb().compileStatement("PRAGMA page_size").simpleQueryForLong();
    }

    private long getPageCount() {
        return getDb().compileStatement("PRAGMA page_count").simpleQueryForLong();
    }

    static Object tryWithCursor(Cursor cursor, Function function) {
        try {
            return function.apply(cursor);
        } finally {
            cursor.close();
        }
    }
}
