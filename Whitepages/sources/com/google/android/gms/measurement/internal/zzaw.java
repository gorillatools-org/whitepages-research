package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzhm;
import com.google.android.gms.internal.measurement.zzht;
import com.google.android.gms.internal.measurement.zzhv;
import com.google.android.gms.internal.measurement.zzhw;
import com.google.android.gms.internal.measurement.zzhx;
import com.google.android.gms.internal.measurement.zzpn;
import com.google.android.gms.internal.measurement.zzqr;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.salesforce.marketingcloud.storage.db.k;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

final class zzaw extends zzpg {
    static final String[] zza = {"associated_row_id", "ALTER TABLE upload_queue ADD COLUMN associated_row_id INTEGER;", "last_upload_timestamp", "ALTER TABLE upload_queue ADD COLUMN last_upload_timestamp INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzb = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzc = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzd = {k.a.q, "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;", "sgtm_upload_enabled", "ALTER TABLE apps ADD COLUMN sgtm_upload_enabled INTEGER;", "target_os_version", "ALTER TABLE apps ADD COLUMN target_os_version INTEGER;", "session_stitching_token_hash", "ALTER TABLE apps ADD COLUMN session_stitching_token_hash INTEGER;", "ad_services_version", "ALTER TABLE apps ADD COLUMN ad_services_version INTEGER;", "unmatched_first_open_without_ad_id", "ALTER TABLE apps ADD COLUMN unmatched_first_open_without_ad_id INTEGER;", "npa_metadata_value", "ALTER TABLE apps ADD COLUMN npa_metadata_value INTEGER;", "attribution_eligibility_status", "ALTER TABLE apps ADD COLUMN attribution_eligibility_status INTEGER;", "sgtm_preview_key", "ALTER TABLE apps ADD COLUMN sgtm_preview_key TEXT;", "dma_consent_state", "ALTER TABLE apps ADD COLUMN dma_consent_state INTEGER;", "daily_realtime_dcu_count", "ALTER TABLE apps ADD COLUMN daily_realtime_dcu_count INTEGER;", "bundle_delivery_index", "ALTER TABLE apps ADD COLUMN bundle_delivery_index INTEGER;", "serialized_npa_metadata", "ALTER TABLE apps ADD COLUMN serialized_npa_metadata TEXT;", "unmatched_pfo", "ALTER TABLE apps ADD COLUMN unmatched_pfo INTEGER;", "unmatched_uwa", "ALTER TABLE apps ADD COLUMN unmatched_uwa INTEGER;", "ad_campaign_info", "ALTER TABLE apps ADD COLUMN ad_campaign_info BLOB;", "daily_registered_triggers_count", "ALTER TABLE apps ADD COLUMN daily_registered_triggers_count INTEGER;", "client_upload_eligibility", "ALTER TABLE apps ADD COLUMN client_upload_eligibility INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zze = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzf = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzh = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzi = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    /* access modifiers changed from: private */
    public static final String[] zzj = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzk = {"consent_source", "ALTER TABLE consent_settings ADD COLUMN consent_source INTEGER;", "dma_consent_settings", "ALTER TABLE consent_settings ADD COLUMN dma_consent_settings TEXT;", "storage_consent_at_bundling", "ALTER TABLE consent_settings ADD COLUMN storage_consent_at_bundling TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzl = {"idempotent", "CREATE INDEX IF NOT EXISTS trigger_uris_index ON trigger_uris (app_id);"};
    private final zzav zzm;
    /* access modifiers changed from: private */
    public final zzou zzn = new zzou(this.zzu.zzaU());

    zzaw(zzpv zzpv) {
        super(zzpv);
        this.zzu.zzf();
        this.zzm = new zzav(this, this.zzu.zzaT(), "google_app_measurement.db");
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0131  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.measurement.internal.zzbd zzaA(java.lang.String r30, java.lang.String r31, java.lang.String r32) {
        /*
            r29 = this;
            r1 = r29
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r31)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r32)
            r29.zzg()
            r29.zzav()
            java.util.ArrayList r0 = new java.util.ArrayList
            java.lang.String r9 = "last_exempt_from_sampling"
            java.lang.String r10 = "current_session_count"
            java.lang.String r2 = "lifetime_count"
            java.lang.String r3 = "current_bundle_count"
            java.lang.String r4 = "last_fire_timestamp"
            java.lang.String r5 = "last_bundled_timestamp"
            java.lang.String r6 = "last_bundled_day"
            java.lang.String r7 = "last_sampled_complex_event_id"
            java.lang.String r8 = "last_sampling_rate"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3, r4, r5, r6, r7, r8, r9, r10}
            java.util.List r2 = java.util.Arrays.asList(r2)
            r0.<init>(r2)
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r29.zzj()     // Catch:{ SQLiteException -> 0x010a, all -> 0x0108 }
            r11 = 0
            java.lang.String[] r4 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x010a, all -> 0x0108 }
            java.lang.Object[] r0 = r0.toArray(r4)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0108 }
            r5 = r0
            java.lang.String[] r5 = (java.lang.String[]) r5     // Catch:{ SQLiteException -> 0x010a, all -> 0x0108 }
            java.lang.String r6 = "app_id=? and name=?"
            java.lang.String[] r7 = new java.lang.String[]{r31, r32}     // Catch:{ SQLiteException -> 0x010a, all -> 0x0108 }
            r9 = 0
            r10 = 0
            r8 = 0
            r4 = r30
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x010a, all -> 0x0108 }
            boolean r0 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x00ca }
            if (r0 != 0) goto L_0x0053
            goto L_0x0129
        L_0x0053:
            long r15 = r3.getLong(r11)     // Catch:{ SQLiteException -> 0x00ca }
            r0 = 1
            long r17 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x00ca }
            r4 = 2
            long r21 = r3.getLong(r4)     // Catch:{ SQLiteException -> 0x00ca }
            r4 = 3
            boolean r5 = r3.isNull(r4)     // Catch:{ SQLiteException -> 0x00ca }
            r6 = 0
            if (r5 == 0) goto L_0x006d
            r23 = r6
            goto L_0x0073
        L_0x006d:
            long r4 = r3.getLong(r4)     // Catch:{ SQLiteException -> 0x00ca }
            r23 = r4
        L_0x0073:
            r4 = 4
            boolean r5 = r3.isNull(r4)     // Catch:{ SQLiteException -> 0x00ca }
            if (r5 == 0) goto L_0x007d
            r25 = r2
            goto L_0x0087
        L_0x007d:
            long r4 = r3.getLong(r4)     // Catch:{ SQLiteException -> 0x00ca }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x00ca }
            r25 = r4
        L_0x0087:
            r4 = 5
            boolean r5 = r3.isNull(r4)     // Catch:{ SQLiteException -> 0x00ca }
            if (r5 == 0) goto L_0x0091
            r26 = r2
            goto L_0x009b
        L_0x0091:
            long r4 = r3.getLong(r4)     // Catch:{ SQLiteException -> 0x00ca }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x00ca }
            r26 = r4
        L_0x009b:
            r4 = 6
            boolean r5 = r3.isNull(r4)     // Catch:{ SQLiteException -> 0x00ca }
            if (r5 == 0) goto L_0x00a5
            r27 = r2
            goto L_0x00af
        L_0x00a5:
            long r4 = r3.getLong(r4)     // Catch:{ SQLiteException -> 0x00ca }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x00ca }
            r27 = r4
        L_0x00af:
            r4 = 7
            boolean r5 = r3.isNull(r4)     // Catch:{ SQLiteException -> 0x00ca }
            if (r5 != 0) goto L_0x00cc
            long r4 = r3.getLong(r4)     // Catch:{ SQLiteException -> 0x00ca }
            r8 = 1
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 != 0) goto L_0x00c1
            r11 = r0
        L_0x00c1:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r11)     // Catch:{ SQLiteException -> 0x00ca }
            r28 = r0
            goto L_0x00ce
        L_0x00c8:
            r0 = move-exception
            goto L_0x0106
        L_0x00ca:
            r0 = move-exception
            goto L_0x010c
        L_0x00cc:
            r28 = r2
        L_0x00ce:
            r0 = 8
            boolean r4 = r3.isNull(r0)     // Catch:{ SQLiteException -> 0x00ca }
            if (r4 == 0) goto L_0x00d9
            r19 = r6
            goto L_0x00df
        L_0x00d9:
            long r4 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x00ca }
            r19 = r4
        L_0x00df:
            com.google.android.gms.measurement.internal.zzbd r0 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ SQLiteException -> 0x00ca }
            r12 = r0
            r13 = r31
            r14 = r32
            r12.<init>(r13, r14, r15, r17, r19, r21, r23, r25, r26, r27, r28)     // Catch:{ SQLiteException -> 0x00ca }
            boolean r4 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x00ca }
            if (r4 == 0) goto L_0x0102
            com.google.android.gms.measurement.internal.zzio r4 = r1.zzu     // Catch:{ SQLiteException -> 0x00ca }
            com.google.android.gms.measurement.internal.zzhe r4 = r4.zzaW()     // Catch:{ SQLiteException -> 0x00ca }
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zze()     // Catch:{ SQLiteException -> 0x00ca }
            java.lang.String r5 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r31)     // Catch:{ SQLiteException -> 0x00ca }
            r4.zzb(r5, r6)     // Catch:{ SQLiteException -> 0x00ca }
        L_0x0102:
            r3.close()
            return r0
        L_0x0106:
            r2 = r3
            goto L_0x012f
        L_0x0108:
            r0 = move-exception
            goto L_0x012f
        L_0x010a:
            r0 = move-exception
            r3 = r2
        L_0x010c:
            com.google.android.gms.measurement.internal.zzio r4 = r1.zzu     // Catch:{ all -> 0x00c8 }
            com.google.android.gms.measurement.internal.zzhe r5 = r4.zzaW()     // Catch:{ all -> 0x00c8 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ all -> 0x00c8 }
            java.lang.String r6 = "Error querying events. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzhe.zzn(r31)     // Catch:{ all -> 0x00c8 }
            com.google.android.gms.measurement.internal.zzgx r4 = r4.zzj()     // Catch:{ all -> 0x00c8 }
            r8 = r32
            java.lang.String r4 = r4.zzd(r8)     // Catch:{ all -> 0x00c8 }
            r5.zzd(r6, r7, r4, r0)     // Catch:{ all -> 0x00c8 }
        L_0x0129:
            if (r3 == 0) goto L_0x012e
            r3.close()
        L_0x012e:
            return r2
        L_0x012f:
            if (r2 == 0) goto L_0x0134
            r2.close()
        L_0x0134:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzaA(java.lang.String, java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzbd");
    }

    private final zzpz zzaB(String str, long j, byte[] bArr, String str2, String str3, int i, int i2, long j2, long j3, long j4) {
        String str4 = str3;
        int i3 = i2;
        if (TextUtils.isEmpty(str2)) {
            this.zzu.zzaW().zzd().zza("Upload uri is null or empty. Destination is unknown. Dropping batch. ");
            return null;
        }
        try {
            zzht zzht = (zzht) zzqa.zzp(zzhv.zzb(), bArr);
            zzmf zzb2 = zzmf.zzb(i);
            if (!(zzb2 == zzmf.GOOGLE_SIGNAL || zzb2 == zzmf.GOOGLE_SIGNAL_PENDING || i3 <= 0)) {
                ArrayList arrayList = new ArrayList();
                for (zzhx zzch : zzht.zzj()) {
                    zzhw zzhw = (zzhw) zzch.zzch();
                    zzhw.zzat(i3);
                    arrayList.add((zzhx) zzhw.zzba());
                }
                zzht.zzd();
                zzht.zzb(arrayList);
            }
            HashMap hashMap = new HashMap();
            if (str4 != null) {
                String[] split = str4.split("\r\n");
                int length = split.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        break;
                    }
                    String str5 = split[i4];
                    if (str5.isEmpty()) {
                        break;
                    }
                    String[] split2 = str5.split("=", 2);
                    if (split2.length != 2) {
                        this.zzu.zzaW().zze().zzb("Invalid upload header: ", str5);
                        break;
                    }
                    hashMap.put(split2[0], split2[1]);
                    i4++;
                }
            }
            zzpx zzpx = new zzpx();
            zzpx.zzf(j);
            zzpx.zzd((zzhv) zzht.zzba());
            zzpx.zzi(str2);
            zzpx.zzg(hashMap);
            zzpx.zzh(zzb2);
            zzpx.zzb(j2);
            zzpx.zza(j3);
            zzpx.zzc(j4);
            zzpx.zze(i3);
            return zzpx.zzj();
        } catch (IOException e) {
            String str6 = str;
            this.zzu.zzaW().zze().zzc("Failed to queued MeasurementBatch from upload_queue. appId", str, e);
            return null;
        }
    }

    private final String zzaC() {
        zzio zzio = this.zzu;
        long currentTimeMillis = zzio.zzaU().currentTimeMillis();
        Locale locale = Locale.US;
        zzmf zzmf = zzmf.GOOGLE_SIGNAL;
        Integer valueOf = Integer.valueOf(zzmf.zza());
        Long valueOf2 = Long.valueOf(currentTimeMillis);
        zzio.zzf();
        Long l = (Long) zzgi.zzR.zza((Object) null);
        l.longValue();
        String format = String.format(locale, "(upload_type = %d AND ABS(creation_timestamp - %d) > %d)", new Object[]{valueOf, valueOf2, l});
        Integer valueOf3 = Integer.valueOf(zzmf.zza());
        zzio.zzf();
        String format2 = String.format(locale, "(upload_type != %d AND ABS(creation_timestamp - %d) > %d)", new Object[]{valueOf3, valueOf2, Long.valueOf(zzam.zzI())});
        return "(" + format + " OR " + format2 + ")";
    }

    private final String zzaD(String str, String[] strArr, String str2) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = zzj().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                String string = rawQuery.getString(0);
                rawQuery.close();
                return string;
            }
            rawQuery.close();
            return "";
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private final void zzaE(String str, String str2) {
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzav();
        try {
            zzj().delete(str, "app_id=?", new String[]{str2});
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Error deleting snapshot. appId", zzhe.zzn(str2), e);
        }
    }

    private final void zzaF(String str, zzbd zzbd) {
        Preconditions.checkNotNull(zzbd);
        zzg();
        zzav();
        ContentValues contentValues = new ContentValues();
        String str2 = zzbd.zza;
        contentValues.put("app_id", str2);
        contentValues.put("name", zzbd.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzbd.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzbd.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzbd.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzbd.zzg));
        contentValues.put("last_bundled_day", zzbd.zzh);
        contentValues.put("last_sampled_complex_event_id", zzbd.zzi);
        contentValues.put("last_sampling_rate", zzbd.zzj);
        contentValues.put("current_session_count", Long.valueOf(zzbd.zze));
        Boolean bool = zzbd.zzk;
        contentValues.put("last_exempt_from_sampling", (bool == null || !bool.booleanValue()) ? null : 1L);
        try {
            if (zzj().insertWithOnConflict(str, (String) null, contentValues, 5) == -1) {
                this.zzu.zzaW().zze().zzb("Failed to insert/update event aggregates (got -1). appId", zzhe.zzn(str2));
            }
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Error storing event aggregates. appId", zzhe.zzn(zzbd.zza), e);
        }
    }

    private final void zzaG(String str, String str2, ContentValues contentValues) {
        try {
            SQLiteDatabase zzj2 = zzj();
            String asString = contentValues.getAsString("app_id");
            if (asString == null) {
                this.zzu.zzaW().zzf().zzb("Value of the primary key is not set.", zzhe.zzn("app_id"));
                return;
            }
            if (((long) zzj2.update("consent_settings", contentValues, "app_id" + " = ?", new String[]{asString})) == 0 && zzj2.insertWithOnConflict("consent_settings", (String) null, contentValues, 5) == -1) {
                this.zzu.zzaW().zze().zzc("Failed to insert/update table (got -1). key", zzhe.zzn("consent_settings"), zzhe.zzn("app_id"));
            }
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzd("Error storing into table. key", zzhe.zzn("consent_settings"), zzhe.zzn("app_id"), e);
        }
    }

    private static final String zzaH(List list) {
        if (list.isEmpty()) {
            return "";
        }
        return String.format(" AND (upload_type IN (%s))", new Object[]{TextUtils.join(", ", list)});
    }

    static final void zzau(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty("value");
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put("value", (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put("value", (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put("value", (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    private final long zzay(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = zzj().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                cursor.close();
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public final long zzaz(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = zzj().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                j = rawQuery.getLong(0);
            }
            rawQuery.close();
            return j;
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzA() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.zzj()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x001c }
            if (r2 == 0) goto L_0x0036
            r2 = 0
            java.lang.String r1 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x001c }
            r0.close()
            return r1
        L_0x001a:
            r1 = move-exception
            goto L_0x001e
        L_0x001c:
            r2 = move-exception
            goto L_0x0027
        L_0x001e:
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x003c
        L_0x0022:
            r0 = move-exception
            goto L_0x003c
        L_0x0024:
            r0 = move-exception
            r2 = r0
            r0 = r1
        L_0x0027:
            com.google.android.gms.measurement.internal.zzio r3 = r6.zzu     // Catch:{ all -> 0x001a }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ all -> 0x001a }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ all -> 0x001a }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zzb(r4, r2)     // Catch:{ all -> 0x001a }
        L_0x0036:
            if (r0 == 0) goto L_0x003b
            r0.close()
        L_0x003b:
            return r1
        L_0x003c:
            if (r1 == 0) goto L_0x0041
            r1.close()
        L_0x0041:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzA():java.lang.String");
    }

    public final List zzB(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzav();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzC(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public final List zzC(String str, String[] strArr) {
        zzg();
        zzav();
        List arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            SQLiteDatabase zzj2 = zzj();
            String[] strArr2 = {"app_id", "origin", "name", "value", "active", AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, "timed_out_event", AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, "triggered_event", AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, "expired_event"};
            zzio zzio = this.zzu;
            zzio.zzf();
            cursor = zzj2.query("conditional_properties", strArr2, str, strArr, (String) null, (String) null, "rowid", "1001");
            if (cursor.moveToFirst()) {
                while (true) {
                    int size = arrayList.size();
                    zzio.zzf();
                    if (size < 1000) {
                        String string = cursor.getString(0);
                        String string2 = cursor.getString(1);
                        String string3 = cursor.getString(2);
                        Object zzz = zzz(cursor, 3);
                        boolean z = cursor.getInt(4) != 0;
                        String string4 = cursor.getString(5);
                        long j = cursor.getLong(6);
                        zzpv zzpv = this.zzg;
                        zzqa zzA = zzpv.zzA();
                        byte[] blob = cursor.getBlob(7);
                        Parcelable.Creator<zzbh> creator = zzbh.CREATOR;
                        arrayList.add(new zzai(string, string2, new zzqb(string3, cursor.getLong(10), zzz, string2), cursor.getLong(8), z, string4, (zzbh) zzA.zzi(blob, creator), j, (zzbh) zzpv.zzA().zzi(cursor.getBlob(9), creator), cursor.getLong(11), (zzbh) zzpv.zzA().zzi(cursor.getBlob(12), creator)));
                        if (!cursor.moveToNext()) {
                            break;
                        }
                    } else {
                        zzhc zze2 = zzio.zzaW().zze();
                        zzio.zzf();
                        zze2.zzb("Read more than the max allowed conditional properties, ignoring extra", 1000);
                        break;
                    }
                }
            }
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzb("Error querying conditional user property value", e);
            arrayList = Collections.emptyList();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x010c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List zzD(java.lang.String r26, com.google.android.gms.measurement.internal.zzpc r27, int r28) {
        /*
            r25 = this;
            r14 = r25
            com.google.android.gms.measurement.internal.zzio r0 = r14.zzu
            com.google.android.gms.measurement.internal.zzam r0 = r0.zzf()
            com.google.android.gms.measurement.internal.zzgg r1 = com.google.android.gms.measurement.internal.zzgi.zzaP
            r2 = 0
            boolean r0 = r0.zzx(r2, r1)
            if (r0 != 0) goto L_0x0016
            java.util.List r0 = java.util.Collections.emptyList()
            return r0
        L_0x0016:
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r26)
            r25.zzg()
            r25.zzav()
            android.database.sqlite.SQLiteDatabase r3 = r25.zzj()     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            java.lang.String r4 = "upload_queue"
            java.lang.String r15 = "rowId"
            java.lang.String r16 = "app_id"
            java.lang.String r17 = "measurement_batch"
            java.lang.String r18 = "upload_uri"
            java.lang.String r19 = "upload_headers"
            java.lang.String r20 = "upload_type"
            java.lang.String r21 = "retry_count"
            java.lang.String r22 = "creation_timestamp"
            java.lang.String r23 = "associated_row_id"
            java.lang.String r24 = "last_upload_timestamp"
            java.lang.String[] r5 = new java.lang.String[]{r15, r16, r17, r18, r19, r20, r21, r22, r23, r24}     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            r0 = r27
            java.util.List r0 = r0.zza     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            java.lang.String r0 = zzaH(r0)     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            java.lang.String r1 = r25.zzaC()     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            r6.<init>()     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            java.lang.String r7 = "app_id=?"
            r6.append(r7)     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            r6.append(r0)     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            java.lang.String r0 = " AND NOT "
            r6.append(r0)     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            r6.append(r1)     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            java.lang.String r6 = r6.toString()     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            java.lang.String[] r7 = new java.lang.String[]{r26}     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            java.lang.String r10 = "creation_timestamp ASC"
            if (r28 <= 0) goto L_0x0076
            java.lang.String r0 = java.lang.String.valueOf(r28)     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            r11 = r0
            goto L_0x0077
        L_0x0070:
            r0 = move-exception
            goto L_0x00e8
        L_0x0073:
            r0 = move-exception
            goto L_0x00eb
        L_0x0076:
            r11 = r2
        L_0x0077:
            r8 = 0
            r9 = 0
            android.database.Cursor r15 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x0073, all -> 0x0070 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00d5, all -> 0x00d1 }
            r0.<init>()     // Catch:{ SQLiteException -> 0x00d5, all -> 0x00d1 }
        L_0x0082:
            boolean r1 = r15.moveToNext()     // Catch:{ SQLiteException -> 0x00d5, all -> 0x00d1 }
            if (r1 == 0) goto L_0x00d9
            r1 = 0
            long r3 = r15.getLong(r1)     // Catch:{ SQLiteException -> 0x00d5, all -> 0x00d1 }
            r1 = 2
            byte[] r5 = r15.getBlob(r1)     // Catch:{ SQLiteException -> 0x00d5, all -> 0x00d1 }
            r1 = 3
            java.lang.String r6 = r15.getString(r1)     // Catch:{ SQLiteException -> 0x00d5, all -> 0x00d1 }
            r1 = 4
            java.lang.String r7 = r15.getString(r1)     // Catch:{ SQLiteException -> 0x00d5, all -> 0x00d1 }
            r1 = 5
            int r8 = r15.getInt(r1)     // Catch:{ SQLiteException -> 0x00d5, all -> 0x00d1 }
            r1 = 6
            int r9 = r15.getInt(r1)     // Catch:{ SQLiteException -> 0x00d5, all -> 0x00d1 }
            r1 = 7
            long r10 = r15.getLong(r1)     // Catch:{ SQLiteException -> 0x00d5, all -> 0x00d1 }
            r1 = 8
            long r12 = r15.getLong(r1)     // Catch:{ SQLiteException -> 0x00d5, all -> 0x00d1 }
            r1 = 9
            long r16 = r15.getLong(r1)     // Catch:{ SQLiteException -> 0x00d5, all -> 0x00d1 }
            r1 = r25
            r2 = r26
            r18 = r15
            r14 = r16
            com.google.android.gms.measurement.internal.zzpz r1 = r1.zzaB(r2, r3, r5, r6, r7, r8, r9, r10, r12, r14)     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cd }
            if (r1 == 0) goto L_0x00c8
            r0.add(r1)     // Catch:{ SQLiteException -> 0x00cf, all -> 0x00cd }
        L_0x00c8:
            r14 = r25
            r15 = r18
            goto L_0x0082
        L_0x00cd:
            r0 = move-exception
            goto L_0x00de
        L_0x00cf:
            r0 = move-exception
            goto L_0x00e3
        L_0x00d1:
            r0 = move-exception
            r18 = r15
            goto L_0x00de
        L_0x00d5:
            r0 = move-exception
            r18 = r15
            goto L_0x00e3
        L_0x00d9:
            r18 = r15
            r1 = r25
            goto L_0x0103
        L_0x00de:
            r1 = r25
            r2 = r18
            goto L_0x010a
        L_0x00e3:
            r1 = r25
            r2 = r18
            goto L_0x00ed
        L_0x00e8:
            r1 = r25
            goto L_0x010a
        L_0x00eb:
            r1 = r25
        L_0x00ed:
            com.google.android.gms.measurement.internal.zzio r3 = r1.zzu     // Catch:{ all -> 0x0109 }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ all -> 0x0109 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ all -> 0x0109 }
            java.lang.String r4 = "Error to querying MeasurementBatch from upload_queue. appId"
            r5 = r26
            r3.zzc(r4, r5, r0)     // Catch:{ all -> 0x0109 }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0109 }
            r15 = r2
        L_0x0103:
            if (r15 == 0) goto L_0x0108
            r15.close()
        L_0x0108:
            return r0
        L_0x0109:
            r0 = move-exception
        L_0x010a:
            if (r2 == 0) goto L_0x010f
            r2.close()
        L_0x010f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzD(java.lang.String, com.google.android.gms.measurement.internal.zzpc, int):java.util.List");
    }

    public final List zzE(String str) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzav();
        List arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            zzio zzio = this.zzu;
            zzio.zzf();
            cursor = zzj().query("user_attributes", new String[]{"name", "origin", "set_timestamp", "value"}, "app_id=?", new String[]{str}, (String) null, (String) null, "rowid", "1000");
            if (cursor.moveToFirst()) {
                do {
                    String string = cursor.getString(0);
                    String string2 = cursor.getString(1);
                    if (string2 == null) {
                        string2 = "";
                    }
                    String str2 = string2;
                    long j = cursor.getLong(2);
                    Object zzz = zzz(cursor, 3);
                    if (zzz == null) {
                        zzio.zzaW().zze().zzb("Read invalid user property value, ignoring it. appId", zzhe.zzn(str));
                    } else {
                        arrayList.add(new zzqd(str, str2, string, j, zzz));
                    }
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Error querying user properties. appId", zzhe.zzn(str), e);
            arrayList = Collections.emptyList();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (cursor != null) {
            cursor.close();
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x011b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List zzF(java.lang.String r19, java.lang.String r20, java.lang.String r21) {
        /*
            r18 = this;
            r1 = r18
            r0 = r21
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r19)
            r18.zzg()
            r18.zzav()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.String r11 = "1001"
            r12 = 0
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00fe }
            r13 = 3
            r3.<init>(r13)     // Catch:{ SQLiteException -> 0x00fe }
            r14 = r19
            r3.add(r14)     // Catch:{ SQLiteException -> 0x00fa }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00fa }
            java.lang.String r5 = "app_id=?"
            r4.<init>(r5)     // Catch:{ SQLiteException -> 0x00fa }
            boolean r5 = android.text.TextUtils.isEmpty(r20)     // Catch:{ SQLiteException -> 0x00fa }
            if (r5 != 0) goto L_0x0038
            r15 = r20
            r3.add(r15)     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.String r5 = " and origin=?"
            r4.append(r5)     // Catch:{ SQLiteException -> 0x00e5 }
            goto L_0x003a
        L_0x0038:
            r15 = r20
        L_0x003a:
            boolean r5 = android.text.TextUtils.isEmpty(r21)     // Catch:{ SQLiteException -> 0x00e5 }
            if (r5 != 0) goto L_0x0059
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00e5 }
            r5.<init>()     // Catch:{ SQLiteException -> 0x00e5 }
            r5.append(r0)     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.String r6 = "*"
            r5.append(r6)     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.String r5 = r5.toString()     // Catch:{ SQLiteException -> 0x00e5 }
            r3.add(r5)     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.String r5 = " and name glob ?"
            r4.append(r5)     // Catch:{ SQLiteException -> 0x00e5 }
        L_0x0059:
            int r5 = r3.size()     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.Object[] r3 = r3.toArray(r5)     // Catch:{ SQLiteException -> 0x00e5 }
            r7 = r3
            java.lang.String[] r7 = (java.lang.String[]) r7     // Catch:{ SQLiteException -> 0x00e5 }
            android.database.sqlite.SQLiteDatabase r3 = r18.zzj()     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.String r5 = "user_attributes"
            java.lang.String r6 = "name"
            java.lang.String r8 = "set_timestamp"
            java.lang.String r9 = "value"
            java.lang.String r10 = "origin"
            java.lang.String[] r6 = new java.lang.String[]{r6, r8, r9, r10}     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.String r8 = r4.toString()     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.String r10 = "rowid"
            com.google.android.gms.measurement.internal.zzio r9 = r1.zzu     // Catch:{ SQLiteException -> 0x00e5 }
            r9.zzf()     // Catch:{ SQLiteException -> 0x00e5 }
            r16 = 0
            r17 = 0
            r4 = r5
            r5 = r6
            r6 = r8
            r8 = r16
            r16 = r9
            r9 = r17
            android.database.Cursor r12 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x00e5 }
            boolean r3 = r12.moveToFirst()     // Catch:{ SQLiteException -> 0x00e5 }
            if (r3 != 0) goto L_0x009c
            goto L_0x0119
        L_0x009c:
            int r3 = r2.size()     // Catch:{ SQLiteException -> 0x00e5 }
            r16.zzf()     // Catch:{ SQLiteException -> 0x00e5 }
            r4 = 1000(0x3e8, float:1.401E-42)
            if (r3 < r4) goto L_0x00be
            com.google.android.gms.measurement.internal.zzhe r0 = r16.zzaW()     // Catch:{ SQLiteException -> 0x00e5 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.String r3 = "Read more than the max allowed user properties, ignoring excess"
            r16.zzf()     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ SQLiteException -> 0x00e5 }
            r0.zzb(r3, r4)     // Catch:{ SQLiteException -> 0x00e5 }
            goto L_0x0119
        L_0x00bc:
            r0 = move-exception
            goto L_0x011f
        L_0x00be:
            r3 = 0
            java.lang.String r7 = r12.getString(r3)     // Catch:{ SQLiteException -> 0x00e5 }
            r3 = 1
            long r8 = r12.getLong(r3)     // Catch:{ SQLiteException -> 0x00e5 }
            r3 = 2
            java.lang.Object r10 = r1.zzz(r12, r3)     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.String r15 = r12.getString(r13)     // Catch:{ SQLiteException -> 0x00e5 }
            if (r10 != 0) goto L_0x00e7
            com.google.android.gms.measurement.internal.zzhe r3 = r16.zzaW()     // Catch:{ SQLiteException -> 0x00e5 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.String r4 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzhe.zzn(r19)     // Catch:{ SQLiteException -> 0x00e5 }
            r3.zzd(r4, r5, r15, r0)     // Catch:{ SQLiteException -> 0x00e5 }
            goto L_0x00f3
        L_0x00e5:
            r0 = move-exception
            goto L_0x0102
        L_0x00e7:
            com.google.android.gms.measurement.internal.zzqd r3 = new com.google.android.gms.measurement.internal.zzqd     // Catch:{ SQLiteException -> 0x00e5 }
            r4 = r3
            r5 = r19
            r6 = r15
            r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ SQLiteException -> 0x00e5 }
            r2.add(r3)     // Catch:{ SQLiteException -> 0x00e5 }
        L_0x00f3:
            boolean r3 = r12.moveToNext()     // Catch:{ SQLiteException -> 0x00e5 }
            if (r3 != 0) goto L_0x009c
            goto L_0x0119
        L_0x00fa:
            r0 = move-exception
        L_0x00fb:
            r15 = r20
            goto L_0x0102
        L_0x00fe:
            r0 = move-exception
            r14 = r19
            goto L_0x00fb
        L_0x0102:
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu     // Catch:{ all -> 0x00bc }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x00bc }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x00bc }
            java.lang.String r3 = "(2)Error querying user properties"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r19)     // Catch:{ all -> 0x00bc }
            r2.zzd(r3, r4, r15, r0)     // Catch:{ all -> 0x00bc }
            java.util.List r2 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00bc }
        L_0x0119:
            if (r12 == 0) goto L_0x011e
            r12.close()
        L_0x011e:
            return r2
        L_0x011f:
            if (r12 == 0) goto L_0x0124
            r12.close()
        L_0x0124:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzF(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v6, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v10, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.google.android.gms.internal.measurement.zzhx} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v11, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: com.google.android.gms.internal.measurement.zzhx} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v17, resolved type: com.google.android.gms.internal.measurement.zzhx} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: com.google.android.gms.internal.measurement.zzhx} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v21, resolved type: java.lang.Object} */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ff, code lost:
        if (r2 != null) goto L_0x00ca;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x012b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzG(java.lang.String r27, java.lang.Long r28, java.lang.String r29, android.os.Bundle r30) {
        /*
            r26 = this;
            r1 = r26
            r12 = r27
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r30)
            r26.zzg()
            r26.zzav()
            com.google.android.gms.measurement.internal.zzio r13 = r1.zzu
            com.google.android.gms.measurement.internal.zzam r0 = r13.zzf()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzbe
            r14 = 0
            boolean r0 = r0.zzx(r14, r2)
            if (r0 == 0) goto L_0x0029
            if (r28 == 0) goto L_0x0029
            com.google.android.gms.measurement.internal.zzau r0 = new com.google.android.gms.measurement.internal.zzau
            long r2 = r28.longValue()
            r0.<init>(r1, r12, r2)
        L_0x0027:
            r15 = r0
            goto L_0x002f
        L_0x0029:
            com.google.android.gms.measurement.internal.zzau r0 = new com.google.android.gms.measurement.internal.zzau
            r0.<init>(r1, r12)
            goto L_0x0027
        L_0x002f:
            java.util.List r0 = r15.zza()
        L_0x0033:
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L_0x02c8
            java.util.Iterator r16 = r0.iterator()
        L_0x003d:
            boolean r0 = r16.hasNext()
            if (r0 == 0) goto L_0x02bd
            java.lang.Object r0 = r16.next()
            r11 = r0
            com.google.android.gms.measurement.internal.zzat r11 = (com.google.android.gms.measurement.internal.zzat) r11
            boolean r0 = android.text.TextUtils.isEmpty(r29)
            if (r0 != 0) goto L_0x0126
            long r2 = r11.zzb
            android.database.sqlite.SQLiteDatabase r17 = r26.zzj()     // Catch:{ SQLiteException -> 0x00e9, all -> 0x00e7 }
            java.lang.String r18 = "raw_events_metadata"
            java.lang.String r0 = "metadata"
            java.lang.String[] r19 = new java.lang.String[]{r0}     // Catch:{ SQLiteException -> 0x00e9, all -> 0x00e7 }
            java.lang.String r20 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String r0 = java.lang.Long.toString(r2)     // Catch:{ SQLiteException -> 0x00e9, all -> 0x00e7 }
            java.lang.String[] r21 = new java.lang.String[]{r12, r0}     // Catch:{ SQLiteException -> 0x00e9, all -> 0x00e7 }
            java.lang.String r24 = "rowid"
            java.lang.String r25 = "2"
            r22 = 0
            r23 = 0
            android.database.Cursor r2 = r17.query(r18, r19, r20, r21, r22, r23, r24, r25)     // Catch:{ SQLiteException -> 0x00e9, all -> 0x00e7 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0093 }
            if (r0 != 0) goto L_0x0095
            com.google.android.gms.measurement.internal.zzhe r0 = r13.zzaW()     // Catch:{ SQLiteException -> 0x0093 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ SQLiteException -> 0x0093 }
            java.lang.String r3 = "Raw event metadata record is missing. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r27)     // Catch:{ SQLiteException -> 0x0093 }
            r0.zzb(r3, r4)     // Catch:{ SQLiteException -> 0x0093 }
        L_0x008b:
            r2.close()
            r3 = r14
            goto L_0x0102
        L_0x0091:
            r0 = move-exception
            goto L_0x00e3
        L_0x0093:
            r0 = move-exception
            goto L_0x00e5
        L_0x0095:
            r0 = 0
            byte[] r0 = r2.getBlob(r0)     // Catch:{ SQLiteException -> 0x0093 }
            com.google.android.gms.internal.measurement.zzhw r3 = com.google.android.gms.internal.measurement.zzhx.zzz()     // Catch:{ IOException -> 0x00ce }
            com.google.android.gms.internal.measurement.zzng r0 = com.google.android.gms.measurement.internal.zzqa.zzp(r3, r0)     // Catch:{ IOException -> 0x00ce }
            com.google.android.gms.internal.measurement.zzhw r0 = (com.google.android.gms.internal.measurement.zzhw) r0     // Catch:{ IOException -> 0x00ce }
            com.google.android.gms.internal.measurement.zzmd r0 = r0.zzba()     // Catch:{ IOException -> 0x00ce }
            r3 = r0
            com.google.android.gms.internal.measurement.zzhx r3 = (com.google.android.gms.internal.measurement.zzhx) r3     // Catch:{ IOException -> 0x00ce }
            boolean r0 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x00c5 }
            if (r0 == 0) goto L_0x00c7
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ SQLiteException -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ SQLiteException -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()     // Catch:{ SQLiteException -> 0x00c5 }
            java.lang.String r4 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzhe.zzn(r27)     // Catch:{ SQLiteException -> 0x00c5 }
            r0.zzb(r4, r5)     // Catch:{ SQLiteException -> 0x00c5 }
            goto L_0x00c7
        L_0x00c5:
            r0 = move-exception
            goto L_0x00ec
        L_0x00c7:
            r2.close()     // Catch:{ SQLiteException -> 0x00c5 }
        L_0x00ca:
            r2.close()
            goto L_0x0102
        L_0x00ce:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r3 = r1.zzu     // Catch:{ SQLiteException -> 0x0093 }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ SQLiteException -> 0x0093 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ SQLiteException -> 0x0093 }
            java.lang.String r4 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzhe.zzn(r27)     // Catch:{ SQLiteException -> 0x0093 }
            r3.zzc(r4, r5, r0)     // Catch:{ SQLiteException -> 0x0093 }
            goto L_0x008b
        L_0x00e3:
            r14 = r2
            goto L_0x0129
        L_0x00e5:
            r3 = r14
            goto L_0x00ec
        L_0x00e7:
            r0 = move-exception
            goto L_0x0129
        L_0x00e9:
            r0 = move-exception
            r2 = r14
            r3 = r2
        L_0x00ec:
            com.google.android.gms.measurement.internal.zzio r4 = r1.zzu     // Catch:{ all -> 0x0091 }
            com.google.android.gms.measurement.internal.zzhe r4 = r4.zzaW()     // Catch:{ all -> 0x0091 }
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zze()     // Catch:{ all -> 0x0091 }
            java.lang.String r5 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r27)     // Catch:{ all -> 0x0091 }
            r4.zzc(r5, r6, r0)     // Catch:{ all -> 0x0091 }
            if (r2 == 0) goto L_0x0102
            goto L_0x00ca
        L_0x0102:
            if (r3 == 0) goto L_0x0126
            java.util.List r0 = r3.zzY()
            java.util.Iterator r0 = r0.iterator()
        L_0x010c:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0126
            java.lang.Object r2 = r0.next()
            com.google.android.gms.internal.measurement.zzio r2 = (com.google.android.gms.internal.measurement.zzio) r2
            java.lang.String r2 = r2.zzg()
            r9 = r29
            boolean r2 = r2.equals(r9)
            if (r2 == 0) goto L_0x010c
            goto L_0x003d
        L_0x0126:
            r9 = r29
            goto L_0x012f
        L_0x0129:
            if (r14 == 0) goto L_0x012e
            r14.close()
        L_0x012e:
            throw r0
        L_0x012f:
            com.google.android.gms.measurement.internal.zzpv r0 = r1.zzg
            com.google.android.gms.measurement.internal.zzqa r2 = r0.zzA()
            com.google.android.gms.internal.measurement.zzhm r3 = r11.zzd
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>()
            java.util.List r5 = r3.zzi()
            java.util.Iterator r5 = r5.iterator()
        L_0x0144:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x01c9
            java.lang.Object r6 = r5.next()
            com.google.android.gms.internal.measurement.zzhq r6 = (com.google.android.gms.internal.measurement.zzhq) r6
            boolean r7 = r6.zzu()
            if (r7 == 0) goto L_0x0167
            java.lang.String r7 = r6.zzg()
            r28 = r15
            double r14 = r6.zza()
            r4.putDouble(r7, r14)
        L_0x0163:
            r15 = r28
            r14 = 0
            goto L_0x0144
        L_0x0167:
            r28 = r15
            boolean r7 = r6.zzv()
            if (r7 == 0) goto L_0x017b
            java.lang.String r7 = r6.zzg()
            float r6 = r6.zzb()
            r4.putFloat(r7, r6)
            goto L_0x0163
        L_0x017b:
            boolean r7 = r6.zzw()
            if (r7 == 0) goto L_0x018d
            java.lang.String r7 = r6.zzg()
            long r14 = r6.zzd()
            r4.putLong(r7, r14)
            goto L_0x0163
        L_0x018d:
            boolean r7 = r6.zzy()
            if (r7 == 0) goto L_0x019f
            java.lang.String r7 = r6.zzg()
            java.lang.String r6 = r6.zzh()
            r4.putString(r7, r6)
            goto L_0x0163
        L_0x019f:
            java.util.List r7 = r6.zzi()
            boolean r7 = r7.isEmpty()
            if (r7 != 0) goto L_0x01b9
            java.lang.String r7 = r6.zzg()
            java.util.List r6 = r6.zzi()
            android.os.Bundle[] r6 = com.google.android.gms.measurement.internal.zzqa.zzC(r6)
            r4.putParcelableArray(r7, r6)
            goto L_0x0163
        L_0x01b9:
            com.google.android.gms.measurement.internal.zzio r7 = r2.zzu
            com.google.android.gms.measurement.internal.zzhe r7 = r7.zzaW()
            com.google.android.gms.measurement.internal.zzhc r7 = r7.zze()
            java.lang.String r8 = "Unexpected parameter type for parameter"
            r7.zzb(r8, r6)
            goto L_0x0163
        L_0x01c9:
            r28 = r15
            java.lang.String r2 = "_o"
            java.lang.String r5 = r4.getString(r2)
            r4.remove(r2)
            com.google.android.gms.measurement.internal.zzhf r2 = new com.google.android.gms.measurement.internal.zzhf
            java.lang.String r18 = r3.zzh()
            if (r5 != 0) goto L_0x01de
            java.lang.String r5 = ""
        L_0x01de:
            r19 = r5
            long r21 = r3.zzd()
            r17 = r2
            r20 = r4
            r17.<init>(r18, r19, r20, r21)
            com.google.android.gms.measurement.internal.zzio r14 = r1.zzu
            android.os.Bundle r15 = r2.zzd
            com.google.android.gms.measurement.internal.zzqf r4 = r14.zzw()
            r10 = r30
            r4.zzO(r15, r10)
            com.google.android.gms.measurement.internal.zzio r4 = r1.zzu
            java.lang.String r5 = r2.zzb
            com.google.android.gms.measurement.internal.zzbc r7 = new com.google.android.gms.measurement.internal.zzbc
            java.lang.String r6 = r3.zzh()
            long r17 = r3.zzd()
            long r19 = r3.zzc()
            r2 = r7
            r3 = r4
            r4 = r5
            r5 = r27
            r21 = r7
            r7 = r17
            r9 = r19
            r12 = r11
            r11 = r15
            r2.<init>((com.google.android.gms.measurement.internal.zzio) r3, (java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6, (long) r7, (long) r9, (android.os.Bundle) r11)
            long r2 = r12.zza
            long r4 = r12.zzb
            boolean r6 = r12.zzc
            r26.zzg()
            r26.zzav()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r21)
            r7 = r21
            java.lang.String r8 = r7.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)
            com.google.android.gms.measurement.internal.zzqa r0 = r0.zzA()
            com.google.android.gms.internal.measurement.zzhm r0 = r0.zzm(r7)
            byte[] r0 = r0.zzcd()
            android.content.ContentValues r9 = new android.content.ContentValues
            r9.<init>()
            java.lang.String r10 = "app_id"
            r9.put(r10, r8)
            java.lang.String r10 = r7.zzb
            java.lang.String r11 = "name"
            r9.put(r11, r10)
            long r10 = r7.zzd
            java.lang.Long r10 = java.lang.Long.valueOf(r10)
            java.lang.String r11 = "timestamp"
            r9.put(r11, r10)
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            java.lang.String r5 = "metadata_fingerprint"
            r9.put(r5, r4)
            java.lang.String r4 = "data"
            r9.put(r4, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)
            java.lang.String r4 = "realtime"
            r9.put(r4, r0)
            android.database.sqlite.SQLiteDatabase r0 = r26.zzj()     // Catch:{ SQLiteException -> 0x02a6 }
            java.lang.String r4 = "raw_events"
            java.lang.String r5 = "rowid = ?"
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            java.lang.String[] r2 = new java.lang.String[]{r2}     // Catch:{ SQLiteException -> 0x02a6 }
            int r0 = r0.update(r4, r9, r5, r2)     // Catch:{ SQLiteException -> 0x02a6 }
            long r2 = (long) r0     // Catch:{ SQLiteException -> 0x02a6 }
            r4 = 1
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x029f
            com.google.android.gms.measurement.internal.zzhe r0 = r14.zzaW()     // Catch:{ SQLiteException -> 0x02a6 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ SQLiteException -> 0x02a6 }
            java.lang.String r4 = "Failed to update raw event. appId, updatedRows"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzhe.zzn(r8)     // Catch:{ SQLiteException -> 0x02a6 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ SQLiteException -> 0x02a6 }
            r0.zzc(r4, r5, r2)     // Catch:{ SQLiteException -> 0x02a6 }
        L_0x029f:
            r12 = r27
            r15 = r28
            r14 = 0
            goto L_0x003d
        L_0x02a6:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()
            java.lang.String r3 = r7.zza
            java.lang.String r4 = "Error updating raw event. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)
            r2.zzc(r4, r3, r0)
            goto L_0x029f
        L_0x02bd:
            r28 = r15
            java.util.List r0 = r28.zza()
            r12 = r27
            r14 = 0
            goto L_0x0033
        L_0x02c8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzG(java.lang.String, java.lang.Long, java.lang.String, android.os.Bundle):void");
    }

    public final void zzH() {
        zzav();
        zzj().beginTransaction();
    }

    public final void zzI(String str) {
        zzbd zzaA;
        zzaE("events_snapshot", str);
        Cursor cursor = null;
        try {
            cursor = zzj().query("events", (String[]) Collections.singletonList("name").toArray(new String[0]), "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (cursor.moveToFirst()) {
                do {
                    String string = cursor.getString(0);
                    if (!(string == null || (zzaA = zzaA("events", str, string)) == null)) {
                        zzaF("events_snapshot", zzaA);
                    }
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Error creating snapshot. appId", zzhe.zzn(str), e);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    public final void zzJ(List list) {
        Preconditions.checkNotNull(list);
        zzg();
        zzav();
        StringBuilder sb = new StringBuilder("rowid in (");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(((Long) list.get(i)).longValue());
        }
        sb.append(")");
        int delete = zzj().delete("raw_events", sb.toString(), (String[]) null);
        if (delete != list.size()) {
            this.zzu.zzaW().zze().zzc("Deleted fewer rows from raw events table than expected", Integer.valueOf(delete), Integer.valueOf(list.size()));
        }
    }

    public final void zzK(Long l) {
        zzg();
        zzav();
        Preconditions.checkNotNull(l);
        zzio zzio = this.zzu;
        if (zzio.zzf().zzx((String) null, zzgi.zzaM)) {
            try {
                if (zzj().delete("upload_queue", "rowid=?", new String[]{l.toString()}) != 1) {
                    zzio.zzaW().zzk().zza("Deleted fewer rows from upload_queue than expected");
                }
            } catch (SQLiteException e) {
                this.zzu.zzaW().zze().zzb("Failed to delete a MeasurementBatch in a upload_queue table", e);
                throw e;
            }
        }
    }

    public final void zzL() {
        zzav();
        zzj().endTransaction();
    }

    /* access modifiers changed from: package-private */
    public final void zzM(List list) {
        zzg();
        zzav();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzae()) {
            String str = "(" + TextUtils.join(",", list) + ")";
            if (zzay("SELECT COUNT(1) FROM queue WHERE rowid IN " + str + " AND retry_count =  2147483647 LIMIT 1", (String[]) null) > 0) {
                this.zzu.zzaW().zzk().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                zzj().execSQL("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " + str + " AND (retry_count IS NULL OR retry_count < 2147483647)");
            } catch (SQLiteException e) {
                this.zzu.zzaW().zze().zzb("Error incrementing retry count. error", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzN(Long l) {
        String str;
        zzg();
        zzav();
        Preconditions.checkNotNull(l);
        zzio zzio = this.zzu;
        if (zzio.zzf().zzx((String) null, zzgi.zzaM) && zzae()) {
            if (zzay("SELECT COUNT(1) FROM upload_queue WHERE rowid = " + l + " AND retry_count =  2147483647 LIMIT 1", (String[]) null) > 0) {
                zzio.zzaW().zzk().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase zzj2 = zzj();
                if (zzio.zzf().zzx((String) null, zzgi.zzaP)) {
                    str = " SET retry_count = retry_count + 1, last_upload_timestamp = " + zzio.zzaU().currentTimeMillis();
                } else {
                    str = " SET retry_count = retry_count + 1 ";
                }
                zzj2.execSQL("UPDATE upload_queue" + str + " WHERE rowid = " + l + " AND retry_count < 2147483647");
            } catch (SQLiteException e) {
                this.zzu.zzaW().zze().zzb("Error incrementing retry count. error", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzO() {
        zzg();
        zzav();
        if (zzae()) {
            zzpv zzpv = this.zzg;
            long zza2 = zzpv.zzw().zza.zza();
            zzio zzio = this.zzu;
            long elapsedRealtime = zzio.zzaU().elapsedRealtime();
            long abs = Math.abs(elapsedRealtime - zza2);
            zzio.zzf();
            if (abs > zzam.zzJ()) {
                zzpv.zzw().zza.zzb(elapsedRealtime);
                zzg();
                zzav();
                if (zzae()) {
                    SQLiteDatabase zzj2 = zzj();
                    String valueOf = String.valueOf(zzio.zzaU().currentTimeMillis());
                    zzio.zzf();
                    int delete = zzj2.delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{valueOf, String.valueOf(zzam.zzI())});
                    if (delete > 0) {
                        zzio.zzaW().zzj().zzb("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                    }
                }
            }
        }
    }

    public final void zzP(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzav();
        try {
            zzj().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzio zzio = this.zzu;
            zzio.zzaW().zze().zzd("Error deleting user property. appId", zzhe.zzn(str), zzio.zzj().zzf(str2), e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c0, code lost:
        if (r8 != null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0059, code lost:
        if (r8 != null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x005b, code lost:
        zzaF("events", r8);
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00cf A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzQ(java.lang.String r20) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            java.lang.String r3 = "events_snapshot"
            java.util.ArrayList r0 = new java.util.ArrayList
            java.lang.String r4 = "lifetime_count"
            java.lang.String r5 = "name"
            java.lang.String[] r4 = new java.lang.String[]{r5, r4}
            java.util.List r4 = java.util.Arrays.asList(r4)
            r0.<init>(r4)
            java.lang.String r4 = "events"
            java.lang.String r5 = "_f"
            com.google.android.gms.measurement.internal.zzbd r6 = r1.zzaA(r4, r2, r5)
            java.lang.String r7 = "_v"
            com.google.android.gms.measurement.internal.zzbd r8 = r1.zzaA(r4, r2, r7)
            r1.zzaE(r4, r2)
            r9 = 0
            r10 = 0
            android.database.sqlite.SQLiteDatabase r11 = r19.zzj()     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            java.lang.String r12 = "events_snapshot"
            java.lang.String[] r13 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            java.lang.Object[] r0 = r0.toArray(r13)     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            r13 = r0
            java.lang.String[] r13 = (java.lang.String[]) r13     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            java.lang.String r14 = "app_id=?"
            java.lang.String[] r15 = new java.lang.String[]{r20}     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            r17 = 0
            r18 = 0
            r16 = 0
            android.database.Cursor r9 = r11.query(r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            boolean r0 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x009e, all -> 0x009b }
            if (r0 != 0) goto L_0x0060
            r9.close()
            if (r6 == 0) goto L_0x0059
        L_0x0054:
            r1.zzaF(r4, r6)
            goto L_0x00c3
        L_0x0059:
            if (r8 == 0) goto L_0x00c3
        L_0x005b:
            r1.zzaF(r4, r8)
            goto L_0x00c3
        L_0x0060:
            r11 = r10
            r12 = r11
        L_0x0062:
            java.lang.String r0 = r9.getString(r10)     // Catch:{ SQLiteException -> 0x008e, all -> 0x008c }
            r13 = 1
            long r14 = r9.getLong(r13)     // Catch:{ SQLiteException -> 0x008e, all -> 0x008c }
            r16 = 1
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 < 0) goto L_0x0080
            boolean r14 = r5.equals(r0)     // Catch:{ SQLiteException -> 0x008e, all -> 0x008c }
            if (r14 == 0) goto L_0x0079
            r11 = r13
            goto L_0x0080
        L_0x0079:
            boolean r14 = r7.equals(r0)     // Catch:{ SQLiteException -> 0x008e, all -> 0x008c }
            if (r14 == 0) goto L_0x0080
            r12 = r13
        L_0x0080:
            if (r0 == 0) goto L_0x0090
            com.google.android.gms.measurement.internal.zzbd r0 = r1.zzaA(r3, r2, r0)     // Catch:{ SQLiteException -> 0x008e, all -> 0x008c }
            if (r0 == 0) goto L_0x0090
            r1.zzaF(r4, r0)     // Catch:{ SQLiteException -> 0x008e, all -> 0x008c }
            goto L_0x0090
        L_0x008c:
            r0 = move-exception
            goto L_0x0097
        L_0x008e:
            r0 = move-exception
            goto L_0x0099
        L_0x0090:
            boolean r0 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x008e, all -> 0x008c }
            if (r0 != 0) goto L_0x0062
            goto L_0x00b4
        L_0x0097:
            r10 = r11
            goto L_0x00c8
        L_0x0099:
            r10 = r11
            goto L_0x00a0
        L_0x009b:
            r0 = move-exception
            r12 = r10
            goto L_0x00c8
        L_0x009e:
            r0 = move-exception
            r12 = r10
        L_0x00a0:
            com.google.android.gms.measurement.internal.zzio r5 = r1.zzu     // Catch:{ all -> 0x00c7 }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ all -> 0x00c7 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ all -> 0x00c7 }
            java.lang.String r7 = "Error querying snapshot. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzhe.zzn(r20)     // Catch:{ all -> 0x00c7 }
            r5.zzc(r7, r11, r0)     // Catch:{ all -> 0x00c7 }
            r11 = r10
        L_0x00b4:
            if (r9 == 0) goto L_0x00b9
            r9.close()
        L_0x00b9:
            if (r11 != 0) goto L_0x00be
            if (r6 == 0) goto L_0x00be
            goto L_0x0054
        L_0x00be:
            if (r12 != 0) goto L_0x00c3
            if (r8 == 0) goto L_0x00c3
            goto L_0x005b
        L_0x00c3:
            r1.zzaE(r3, r2)
            return
        L_0x00c7:
            r0 = move-exception
        L_0x00c8:
            if (r9 == 0) goto L_0x00cd
            r9.close()
        L_0x00cd:
            if (r10 != 0) goto L_0x00d6
            if (r6 != 0) goto L_0x00d2
            goto L_0x00d6
        L_0x00d2:
            r1.zzaF(r4, r6)
            goto L_0x00dd
        L_0x00d6:
            if (r12 != 0) goto L_0x00dd
            if (r8 == 0) goto L_0x00dd
            r1.zzaF(r4, r8)
        L_0x00dd:
            r1.zzaE(r3, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzQ(java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x032d, code lost:
        r0 = java.lang.Boolean.valueOf(r3.zzi());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0336, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0337, code lost:
        r10.put("session_scoped", r0);
        r10.put(com.google.firebase.messaging.Constants.ScionAnalytics.MessageType.DATA_MESSAGE, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x034b, code lost:
        if (zzj().insertWithOnConflict("property_filters", (java.lang.String) null, r10, 5) != -1) goto L_0x0363;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x034d, code lost:
        r1.zzu.zzaW().zze().zzb("Failed to insert property filter (got -1). appId", com.google.android.gms.measurement.internal.zzhe.zzn(r24));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0361, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0363, code lost:
        r0 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:?, code lost:
        r1.zzu.zzaW().zze().zzc("Error storing property filter. appId", com.google.android.gms.measurement.internal.zzhe.zzn(r24), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x03a7, code lost:
        r3 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x017b, code lost:
        r10 = r0.zzh().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0187, code lost:
        if (r10.hasNext() == false) goto L_0x01ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0193, code lost:
        if (((com.google.android.gms.internal.measurement.zzfr) r10.next()).zzj() != false) goto L_0x0183;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0195, code lost:
        r1.zzu.zzaW().zzk().zzc("Property filter with no ID. Audience definition ignored. appId, audienceId", com.google.android.gms.measurement.internal.zzhe.zzn(r24), java.lang.Integer.valueOf(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x01ae, code lost:
        r10 = r0.zzg().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01c4, code lost:
        if (r10.hasNext() == false) goto L_0x029a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r11 = (com.google.android.gms.internal.measurement.zzfj) r10.next();
        zzav();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01e0, code lost:
        if (r11.zzg().isEmpty() == false) goto L_0x0214;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01e2, code lost:
        r0 = r1.zzu.zzaW().zzk();
        r10 = com.google.android.gms.measurement.internal.zzhe.zzn(r24);
        r12 = java.lang.Integer.valueOf(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01fa, code lost:
        if (r11.zzp() == false) goto L_0x0207;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01fc, code lost:
        r16 = java.lang.Integer.valueOf(r11.zzb());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0207, code lost:
        r16 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0209, code lost:
        r0.zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", r10, r12, java.lang.String.valueOf(r16));
        r21 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0214, code lost:
        r3 = r11.zzcd();
        r21 = r7;
        r7 = new android.content.ContentValues();
        r7.put("app_id", r2);
        r7.put("audience_id", java.lang.Integer.valueOf(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x022d, code lost:
        if (r11.zzp() == false) goto L_0x0238;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x022f, code lost:
        r8 = java.lang.Integer.valueOf(r11.zzb());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0238, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0239, code lost:
        r7.put("filter_id", r8);
        r7.put("event_name", r11.zzg());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0249, code lost:
        if (r11.zzq() == false) goto L_0x0254;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x024b, code lost:
        r8 = java.lang.Boolean.valueOf(r11.zzn());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0254, code lost:
        r8 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0255, code lost:
        r7.put("session_scoped", r8);
        r7.put(com.google.firebase.messaging.Constants.ScionAnalytics.MessageType.DATA_MESSAGE, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0269, code lost:
        if (zzj().insertWithOnConflict("event_filters", (java.lang.String) null, r7, 5) != -1) goto L_0x027e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x026b, code lost:
        r1.zzu.zzaW().zze().zzb("Failed to insert event filter (got -1). appId", com.google.android.gms.measurement.internal.zzhe.zzn(r24));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x027e, code lost:
        r3 = r25;
        r7 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x029a, code lost:
        r21 = r7;
        r0 = r0.zzh().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x02a8, code lost:
        if (r0.hasNext() == false) goto L_0x03a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x02aa, code lost:
        r3 = (com.google.android.gms.internal.measurement.zzfr) r0.next();
        zzav();
        zzg();
        com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24);
        com.google.android.gms.common.internal.Preconditions.checkNotNull(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x02c4, code lost:
        if (r3.zze().isEmpty() == false) goto L_0x02f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x02c6, code lost:
        r0 = r1.zzu.zzaW().zzk();
        r8 = com.google.android.gms.measurement.internal.zzhe.zzn(r24);
        r10 = java.lang.Integer.valueOf(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x02de, code lost:
        if (r3.zzj() == false) goto L_0x02eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x02e0, code lost:
        r16 = java.lang.Integer.valueOf(r3.zza());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x02eb, code lost:
        r16 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x02ed, code lost:
        r0.zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", r8, r10, java.lang.String.valueOf(r16));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x02f6, code lost:
        r7 = r3.zzcd();
        r10 = new android.content.ContentValues();
        r10.put("app_id", r2);
        r10.put("audience_id", java.lang.Integer.valueOf(r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x030d, code lost:
        if (r3.zzj() == false) goto L_0x0318;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x030f, code lost:
        r11 = java.lang.Integer.valueOf(r3.zza());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0318, code lost:
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0319, code lost:
        r10.put("filter_id", r11);
        r22 = r0;
        r10.put("property_name", r3.zze());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x032b, code lost:
        if (r3.zzk() == false) goto L_0x0336;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzR(java.lang.String r24, java.util.List r25) {
        /*
            r23 = this;
            r1 = r23
            r2 = r24
            r3 = r25
            java.lang.String r4 = "app_id=? and audience_id=?"
            java.lang.String r0 = "app_id=?"
            java.lang.String r5 = "event_filters"
            java.lang.String r6 = "property_filters"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r25)
            r8 = 0
        L_0x0012:
            int r9 = r25.size()
            if (r8 >= r9) goto L_0x00dc
            java.lang.Object r9 = r3.get(r8)
            com.google.android.gms.internal.measurement.zzfh r9 = (com.google.android.gms.internal.measurement.zzfh) r9
            com.google.android.gms.internal.measurement.zzlz r9 = r9.zzch()
            com.google.android.gms.internal.measurement.zzfg r9 = (com.google.android.gms.internal.measurement.zzfg) r9
            int r10 = r9.zza()
            if (r10 == 0) goto L_0x009d
            r10 = 0
        L_0x002b:
            int r11 = r9.zza()
            if (r10 >= r11) goto L_0x009d
            com.google.android.gms.internal.measurement.zzfj r11 = r9.zze(r10)
            com.google.android.gms.internal.measurement.zzlz r11 = r11.zzch()
            com.google.android.gms.internal.measurement.zzfi r11 = (com.google.android.gms.internal.measurement.zzfi) r11
            com.google.android.gms.internal.measurement.zzlz r12 = r11.zzaR()
            com.google.android.gms.internal.measurement.zzfi r12 = (com.google.android.gms.internal.measurement.zzfi) r12
            java.lang.String r13 = r11.zze()
            java.lang.String r13 = com.google.android.gms.measurement.internal.zzjy.zzb(r13)
            if (r13 == 0) goto L_0x0050
            r12.zzb(r13)
            r13 = 1
            goto L_0x0051
        L_0x0050:
            r13 = 0
        L_0x0051:
            r15 = 0
        L_0x0052:
            int r14 = r11.zza()
            if (r15 >= r14) goto L_0x0088
            com.google.android.gms.internal.measurement.zzfl r14 = r11.zzd(r15)
            java.lang.String r7 = r14.zze()
            r17 = r11
            java.lang.String[] r11 = com.google.android.gms.measurement.internal.zzjz.zza
            r18 = r4
            java.lang.String[] r4 = com.google.android.gms.measurement.internal.zzjz.zzb
            java.lang.String r4 = com.google.android.gms.measurement.internal.zzmg.zzb(r7, r11, r4)
            if (r4 == 0) goto L_0x0081
            com.google.android.gms.internal.measurement.zzlz r7 = r14.zzch()
            com.google.android.gms.internal.measurement.zzfk r7 = (com.google.android.gms.internal.measurement.zzfk) r7
            r7.zza(r4)
            com.google.android.gms.internal.measurement.zzmd r4 = r7.zzba()
            com.google.android.gms.internal.measurement.zzfl r4 = (com.google.android.gms.internal.measurement.zzfl) r4
            r12.zzc(r15, r4)
            r13 = 1
        L_0x0081:
            int r15 = r15 + 1
            r11 = r17
            r4 = r18
            goto L_0x0052
        L_0x0088:
            r18 = r4
            if (r13 == 0) goto L_0x0098
            r9.zzc(r10, r12)
            com.google.android.gms.internal.measurement.zzmd r4 = r9.zzba()
            com.google.android.gms.internal.measurement.zzfh r4 = (com.google.android.gms.internal.measurement.zzfh) r4
            r3.set(r8, r4)
        L_0x0098:
            int r10 = r10 + 1
            r4 = r18
            goto L_0x002b
        L_0x009d:
            r18 = r4
            int r4 = r9.zzb()
            if (r4 == 0) goto L_0x00d6
            r4 = 0
        L_0x00a6:
            int r7 = r9.zzb()
            if (r4 >= r7) goto L_0x00d6
            com.google.android.gms.internal.measurement.zzfr r7 = r9.zzf(r4)
            java.lang.String r10 = r7.zze()
            java.lang.String[] r11 = com.google.android.gms.measurement.internal.zzka.zza
            java.lang.String[] r12 = com.google.android.gms.measurement.internal.zzka.zzb
            java.lang.String r10 = com.google.android.gms.measurement.internal.zzmg.zzb(r10, r11, r12)
            if (r10 == 0) goto L_0x00d3
            com.google.android.gms.internal.measurement.zzlz r7 = r7.zzch()
            com.google.android.gms.internal.measurement.zzfq r7 = (com.google.android.gms.internal.measurement.zzfq) r7
            r7.zza(r10)
            r9.zzd(r4, r7)
            com.google.android.gms.internal.measurement.zzmd r7 = r9.zzba()
            com.google.android.gms.internal.measurement.zzfh r7 = (com.google.android.gms.internal.measurement.zzfh) r7
            r3.set(r8, r7)
        L_0x00d3:
            int r4 = r4 + 1
            goto L_0x00a6
        L_0x00d6:
            int r8 = r8 + 1
            r4 = r18
            goto L_0x0012
        L_0x00dc:
            r18 = r4
            r23.zzav()
            r23.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r25)
            android.database.sqlite.SQLiteDatabase r4 = r23.zzj()
            r4.beginTransaction()
            r23.zzav()     // Catch:{ all -> 0x0142 }
            r23.zzg()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)     // Catch:{ all -> 0x0142 }
            android.database.sqlite.SQLiteDatabase r7 = r23.zzj()     // Catch:{ all -> 0x0142 }
            java.lang.String[] r8 = new java.lang.String[]{r24}     // Catch:{ all -> 0x0142 }
            r7.delete(r6, r0, r8)     // Catch:{ all -> 0x0142 }
            java.lang.String[] r8 = new java.lang.String[]{r24}     // Catch:{ all -> 0x0142 }
            r7.delete(r5, r0, r8)     // Catch:{ all -> 0x0142 }
            java.util.Iterator r7 = r25.iterator()     // Catch:{ all -> 0x0142 }
        L_0x0110:
            boolean r0 = r7.hasNext()     // Catch:{ all -> 0x0142 }
            if (r0 == 0) goto L_0x03aa
            java.lang.Object r0 = r7.next()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.internal.measurement.zzfh r0 = (com.google.android.gms.internal.measurement.zzfh) r0     // Catch:{ all -> 0x0142 }
            r23.zzav()     // Catch:{ all -> 0x0142 }
            r23.zzg()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)     // Catch:{ all -> 0x0142 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ all -> 0x0142 }
            boolean r9 = r0.zzk()     // Catch:{ all -> 0x0142 }
            if (r9 != 0) goto L_0x0145
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()     // Catch:{ all -> 0x0142 }
            java.lang.String r8 = "Audience with no ID. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzhe.zzn(r24)     // Catch:{ all -> 0x0142 }
            r0.zzb(r8, r9)     // Catch:{ all -> 0x0142 }
            goto L_0x0110
        L_0x0142:
            r0 = move-exception
            goto L_0x0484
        L_0x0145:
            int r9 = r0.zza()     // Catch:{ all -> 0x0142 }
            java.util.List r10 = r0.zzg()     // Catch:{ all -> 0x0142 }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ all -> 0x0142 }
        L_0x0151:
            boolean r11 = r10.hasNext()     // Catch:{ all -> 0x0142 }
            if (r11 == 0) goto L_0x017b
            java.lang.Object r11 = r10.next()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.internal.measurement.zzfj r11 = (com.google.android.gms.internal.measurement.zzfj) r11     // Catch:{ all -> 0x0142 }
            boolean r11 = r11.zzp()     // Catch:{ all -> 0x0142 }
            if (r11 != 0) goto L_0x0151
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()     // Catch:{ all -> 0x0142 }
            java.lang.String r8 = "Event filter with no ID. Audience definition ignored. appId, audienceId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzhe.zzn(r24)     // Catch:{ all -> 0x0142 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0142 }
            r0.zzc(r8, r10, r9)     // Catch:{ all -> 0x0142 }
            goto L_0x0110
        L_0x017b:
            java.util.List r10 = r0.zzh()     // Catch:{ all -> 0x0142 }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ all -> 0x0142 }
        L_0x0183:
            boolean r11 = r10.hasNext()     // Catch:{ all -> 0x0142 }
            if (r11 == 0) goto L_0x01ae
            java.lang.Object r11 = r10.next()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.internal.measurement.zzfr r11 = (com.google.android.gms.internal.measurement.zzfr) r11     // Catch:{ all -> 0x0142 }
            boolean r11 = r11.zzj()     // Catch:{ all -> 0x0142 }
            if (r11 != 0) goto L_0x0183
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()     // Catch:{ all -> 0x0142 }
            java.lang.String r8 = "Property filter with no ID. Audience definition ignored. appId, audienceId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzhe.zzn(r24)     // Catch:{ all -> 0x0142 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0142 }
            r0.zzc(r8, r10, r9)     // Catch:{ all -> 0x0142 }
            goto L_0x0110
        L_0x01ae:
            java.util.List r10 = r0.zzg()     // Catch:{ all -> 0x0142 }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ all -> 0x0142 }
        L_0x01b6:
            boolean r11 = r10.hasNext()     // Catch:{ all -> 0x0142 }
            java.lang.String r15 = "data"
            java.lang.String r12 = "session_scoped"
            java.lang.String r13 = "filter_id"
            java.lang.String r8 = "audience_id"
            java.lang.String r14 = "app_id"
            if (r11 == 0) goto L_0x029a
            java.lang.Object r11 = r10.next()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.internal.measurement.zzfj r11 = (com.google.android.gms.internal.measurement.zzfj) r11     // Catch:{ all -> 0x0142 }
            r23.zzav()     // Catch:{ all -> 0x0142 }
            r23.zzg()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)     // Catch:{ all -> 0x0142 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)     // Catch:{ all -> 0x0142 }
            java.lang.String r21 = r11.zzg()     // Catch:{ all -> 0x0142 }
            boolean r21 = r21.isEmpty()     // Catch:{ all -> 0x0142 }
            if (r21 == 0) goto L_0x0214
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()     // Catch:{ all -> 0x0142 }
            java.lang.String r8 = "Event filter had no event name. Audience definition ignored. appId, audienceId, filterId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzhe.zzn(r24)     // Catch:{ all -> 0x0142 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0142 }
            boolean r13 = r11.zzp()     // Catch:{ all -> 0x0142 }
            if (r13 == 0) goto L_0x0207
            int r11 = r11.zzb()     // Catch:{ all -> 0x0142 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x0142 }
            r16 = r11
            goto L_0x0209
        L_0x0207:
            r16 = 0
        L_0x0209:
            java.lang.String r11 = java.lang.String.valueOf(r16)     // Catch:{ all -> 0x0142 }
            r0.zzd(r8, r10, r12, r11)     // Catch:{ all -> 0x0142 }
            r21 = r7
            goto L_0x037a
        L_0x0214:
            byte[] r3 = r11.zzcd()     // Catch:{ all -> 0x0142 }
            r21 = r7
            android.content.ContentValues r7 = new android.content.ContentValues     // Catch:{ all -> 0x0142 }
            r7.<init>()     // Catch:{ all -> 0x0142 }
            r7.put(r14, r2)     // Catch:{ all -> 0x0142 }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0142 }
            r7.put(r8, r14)     // Catch:{ all -> 0x0142 }
            boolean r8 = r11.zzp()     // Catch:{ all -> 0x0142 }
            if (r8 == 0) goto L_0x0238
            int r8 = r11.zzb()     // Catch:{ all -> 0x0142 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0142 }
            goto L_0x0239
        L_0x0238:
            r8 = 0
        L_0x0239:
            r7.put(r13, r8)     // Catch:{ all -> 0x0142 }
            java.lang.String r8 = "event_name"
            java.lang.String r13 = r11.zzg()     // Catch:{ all -> 0x0142 }
            r7.put(r8, r13)     // Catch:{ all -> 0x0142 }
            boolean r8 = r11.zzq()     // Catch:{ all -> 0x0142 }
            if (r8 == 0) goto L_0x0254
            boolean r8 = r11.zzn()     // Catch:{ all -> 0x0142 }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch:{ all -> 0x0142 }
            goto L_0x0255
        L_0x0254:
            r8 = 0
        L_0x0255:
            r7.put(r12, r8)     // Catch:{ all -> 0x0142 }
            r7.put(r15, r3)     // Catch:{ all -> 0x0142 }
            android.database.sqlite.SQLiteDatabase r3 = r23.zzj()     // Catch:{ SQLiteException -> 0x0284 }
            r8 = 0
            r11 = 5
            long r11 = r3.insertWithOnConflict(r5, r8, r7, r11)     // Catch:{ SQLiteException -> 0x0284 }
            r7 = -1
            int r3 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r3 != 0) goto L_0x027e
            com.google.android.gms.measurement.internal.zzio r3 = r1.zzu     // Catch:{ SQLiteException -> 0x0284 }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ SQLiteException -> 0x0284 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ SQLiteException -> 0x0284 }
            java.lang.String r7 = "Failed to insert event filter (got -1). appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzhe.zzn(r24)     // Catch:{ SQLiteException -> 0x0284 }
            r3.zzb(r7, r8)     // Catch:{ SQLiteException -> 0x0284 }
        L_0x027e:
            r3 = r25
            r7 = r21
            goto L_0x01b6
        L_0x0284:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r3 = r1.zzu     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ all -> 0x0142 }
            java.lang.String r7 = "Error storing event filter. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzhe.zzn(r24)     // Catch:{ all -> 0x0142 }
            r3.zzc(r7, r8, r0)     // Catch:{ all -> 0x0142 }
            goto L_0x037a
        L_0x029a:
            r21 = r7
            java.util.List r0 = r0.zzh()     // Catch:{ all -> 0x0142 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0142 }
        L_0x02a4:
            boolean r3 = r0.hasNext()     // Catch:{ all -> 0x0142 }
            if (r3 == 0) goto L_0x03a7
            java.lang.Object r3 = r0.next()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.internal.measurement.zzfr r3 = (com.google.android.gms.internal.measurement.zzfr) r3     // Catch:{ all -> 0x0142 }
            r23.zzav()     // Catch:{ all -> 0x0142 }
            r23.zzg()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)     // Catch:{ all -> 0x0142 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x0142 }
            java.lang.String r7 = r3.zze()     // Catch:{ all -> 0x0142 }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x0142 }
            if (r7 == 0) goto L_0x02f6
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()     // Catch:{ all -> 0x0142 }
            java.lang.String r7 = "Property filter had no property name. Audience definition ignored. appId, audienceId, filterId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzhe.zzn(r24)     // Catch:{ all -> 0x0142 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0142 }
            boolean r11 = r3.zzj()     // Catch:{ all -> 0x0142 }
            if (r11 == 0) goto L_0x02eb
            int r3 = r3.zza()     // Catch:{ all -> 0x0142 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0142 }
            r16 = r3
            goto L_0x02ed
        L_0x02eb:
            r16 = 0
        L_0x02ed:
            java.lang.String r3 = java.lang.String.valueOf(r16)     // Catch:{ all -> 0x0142 }
            r0.zzd(r7, r8, r10, r3)     // Catch:{ all -> 0x0142 }
            goto L_0x037a
        L_0x02f6:
            byte[] r7 = r3.zzcd()     // Catch:{ all -> 0x0142 }
            android.content.ContentValues r10 = new android.content.ContentValues     // Catch:{ all -> 0x0142 }
            r10.<init>()     // Catch:{ all -> 0x0142 }
            r10.put(r14, r2)     // Catch:{ all -> 0x0142 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0142 }
            r10.put(r8, r11)     // Catch:{ all -> 0x0142 }
            boolean r11 = r3.zzj()     // Catch:{ all -> 0x0142 }
            if (r11 == 0) goto L_0x0318
            int r11 = r3.zza()     // Catch:{ all -> 0x0142 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x0142 }
            goto L_0x0319
        L_0x0318:
            r11 = 0
        L_0x0319:
            r10.put(r13, r11)     // Catch:{ all -> 0x0142 }
            java.lang.String r11 = "property_name"
            r22 = r0
            java.lang.String r0 = r3.zze()     // Catch:{ all -> 0x0142 }
            r10.put(r11, r0)     // Catch:{ all -> 0x0142 }
            boolean r0 = r3.zzk()     // Catch:{ all -> 0x0142 }
            if (r0 == 0) goto L_0x0336
            boolean r0 = r3.zzi()     // Catch:{ all -> 0x0142 }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0142 }
            goto L_0x0337
        L_0x0336:
            r0 = 0
        L_0x0337:
            r10.put(r12, r0)     // Catch:{ all -> 0x0142 }
            r10.put(r15, r7)     // Catch:{ all -> 0x0142 }
            android.database.sqlite.SQLiteDatabase r0 = r23.zzj()     // Catch:{ SQLiteException -> 0x0361 }
            r3 = 0
            r7 = 5
            long r10 = r0.insertWithOnConflict(r6, r3, r10, r7)     // Catch:{ SQLiteException -> 0x0361 }
            r19 = -1
            int r0 = (r10 > r19 ? 1 : (r10 == r19 ? 0 : -1))
            if (r0 != 0) goto L_0x0363
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ SQLiteException -> 0x0361 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ SQLiteException -> 0x0361 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ SQLiteException -> 0x0361 }
            java.lang.String r3 = "Failed to insert property filter (got -1). appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzhe.zzn(r24)     // Catch:{ SQLiteException -> 0x0361 }
            r0.zzb(r3, r7)     // Catch:{ SQLiteException -> 0x0361 }
            goto L_0x037a
        L_0x0361:
            r0 = move-exception
            goto L_0x0367
        L_0x0363:
            r0 = r22
            goto L_0x02a4
        L_0x0367:
            com.google.android.gms.measurement.internal.zzio r3 = r1.zzu     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ all -> 0x0142 }
            java.lang.String r7 = "Error storing property filter. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzhe.zzn(r24)     // Catch:{ all -> 0x0142 }
            r3.zzc(r7, r8, r0)     // Catch:{ all -> 0x0142 }
        L_0x037a:
            r23.zzav()     // Catch:{ all -> 0x0142 }
            r23.zzg()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)     // Catch:{ all -> 0x0142 }
            android.database.sqlite.SQLiteDatabase r0 = r23.zzj()     // Catch:{ all -> 0x0142 }
            java.lang.String r3 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x0142 }
            java.lang.String[] r3 = new java.lang.String[]{r2, r3}     // Catch:{ all -> 0x0142 }
            r7 = r18
            r0.delete(r6, r7, r3)     // Catch:{ all -> 0x0142 }
            java.lang.String r3 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x0142 }
            java.lang.String[] r3 = new java.lang.String[]{r2, r3}     // Catch:{ all -> 0x0142 }
            r0.delete(r5, r7, r3)     // Catch:{ all -> 0x0142 }
            r3 = r25
            r18 = r7
        L_0x03a3:
            r7 = r21
            goto L_0x0110
        L_0x03a7:
            r3 = r25
            goto L_0x03a3
        L_0x03aa:
            r3 = 0
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0142 }
            r0.<init>()     // Catch:{ all -> 0x0142 }
            java.util.Iterator r5 = r25.iterator()     // Catch:{ all -> 0x0142 }
        L_0x03b4:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x0142 }
            if (r6 == 0) goto L_0x03d4
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.internal.measurement.zzfh r6 = (com.google.android.gms.internal.measurement.zzfh) r6     // Catch:{ all -> 0x0142 }
            boolean r7 = r6.zzk()     // Catch:{ all -> 0x0142 }
            if (r7 == 0) goto L_0x03cf
            int r6 = r6.zza()     // Catch:{ all -> 0x0142 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0142 }
            goto L_0x03d0
        L_0x03cf:
            r8 = r3
        L_0x03d0:
            r0.add(r8)     // Catch:{ all -> 0x0142 }
            goto L_0x03b4
        L_0x03d4:
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)     // Catch:{ all -> 0x0142 }
            r23.zzav()     // Catch:{ all -> 0x0142 }
            r23.zzg()     // Catch:{ all -> 0x0142 }
            android.database.sqlite.SQLiteDatabase r3 = r23.zzj()     // Catch:{ all -> 0x0142 }
            java.lang.String r5 = "select count(1) from audience_filter_values where app_id=?"
            java.lang.String[] r6 = new java.lang.String[]{r24}     // Catch:{ SQLiteException -> 0x0469 }
            long r5 = r1.zzay(r5, r6)     // Catch:{ SQLiteException -> 0x0469 }
            com.google.android.gms.measurement.internal.zzio r7 = r1.zzu     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzam r7 = r7.zzf()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzgg r8 = com.google.android.gms.measurement.internal.zzgi.zzT     // Catch:{ all -> 0x0142 }
            int r7 = r7.zzh(r2, r8)     // Catch:{ all -> 0x0142 }
            r8 = 2000(0x7d0, float:2.803E-42)
            int r7 = java.lang.Math.min(r8, r7)     // Catch:{ all -> 0x0142 }
            r8 = 0
            int r7 = java.lang.Math.max(r8, r7)     // Catch:{ all -> 0x0142 }
            long r9 = (long) r7     // Catch:{ all -> 0x0142 }
            int r5 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r5 > 0) goto L_0x0408
            goto L_0x047d
        L_0x0408:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0142 }
            r5.<init>()     // Catch:{ all -> 0x0142 }
        L_0x040d:
            int r6 = r0.size()     // Catch:{ all -> 0x0142 }
            if (r8 >= r6) goto L_0x0429
            java.lang.Object r6 = r0.get(r8)     // Catch:{ all -> 0x0142 }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ all -> 0x0142 }
            if (r6 == 0) goto L_0x047d
            int r6 = r6.intValue()     // Catch:{ all -> 0x0142 }
            java.lang.String r6 = java.lang.Integer.toString(r6)     // Catch:{ all -> 0x0142 }
            r5.add(r6)     // Catch:{ all -> 0x0142 }
            int r8 = r8 + 1
            goto L_0x040d
        L_0x0429:
            java.lang.String r0 = ","
            java.lang.String r0 = android.text.TextUtils.join(r0, r5)     // Catch:{ all -> 0x0142 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            r5.<init>()     // Catch:{ all -> 0x0142 }
            java.lang.String r6 = "("
            r5.append(r6)     // Catch:{ all -> 0x0142 }
            r5.append(r0)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = ")"
            r5.append(r0)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0142 }
            java.lang.String r5 = "audience_filter_values"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            r6.<init>()     // Catch:{ all -> 0x0142 }
            java.lang.String r8 = "audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in "
            r6.append(r8)     // Catch:{ all -> 0x0142 }
            r6.append(r0)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = " order by rowid desc limit -1 offset ?)"
            r6.append(r0)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x0142 }
            java.lang.String r6 = java.lang.Integer.toString(r7)     // Catch:{ all -> 0x0142 }
            java.lang.String[] r2 = new java.lang.String[]{r2, r6}     // Catch:{ all -> 0x0142 }
            r3.delete(r5, r0, r2)     // Catch:{ all -> 0x0142 }
            goto L_0x047d
        L_0x0469:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r3 = r1.zzu     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ all -> 0x0142 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ all -> 0x0142 }
            java.lang.String r5 = "Database error querying filters. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzhe.zzn(r24)     // Catch:{ all -> 0x0142 }
            r3.zzc(r5, r2, r0)     // Catch:{ all -> 0x0142 }
        L_0x047d:
            r4.setTransactionSuccessful()     // Catch:{ all -> 0x0142 }
            r4.endTransaction()
            return
        L_0x0484:
            r4.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzR(java.lang.String, java.util.List):void");
    }

    public final void zzS() {
        zzav();
        zzj().setTransactionSuccessful();
    }

    public final void zzT(zzh zzh2, boolean z, boolean z2) {
        Preconditions.checkNotNull(zzh2);
        zzg();
        zzav();
        String zzC = zzh2.zzC();
        Preconditions.checkNotNull(zzC);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzC);
        if (z) {
            contentValues.put("app_instance_id", (String) null);
        } else if (this.zzg.zzu(zzC).zzr(zzjw.ANALYTICS_STORAGE)) {
            contentValues.put("app_instance_id", zzh2.zzD());
        }
        contentValues.put("gmp_app_id", zzh2.zzH());
        zzpv zzpv = this.zzg;
        if (zzpv.zzu(zzC).zzr(zzjw.AD_STORAGE)) {
            contentValues.put("resettable_device_id_hash", zzh2.zzJ());
        }
        contentValues.put("last_bundle_index", Long.valueOf(zzh2.zzt()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzh2.zzu()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzh2.zzs()));
        contentValues.put(k.a.q, zzh2.zzF());
        contentValues.put("app_store", zzh2.zzE());
        contentValues.put("gmp_version", Long.valueOf(zzh2.zzq()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzh2.zzn()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzh2.zzaJ()));
        contentValues.put("day", Long.valueOf(zzh2.zzm()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzh2.zzk()));
        contentValues.put("daily_events_count", Long.valueOf(zzh2.zzj()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzh2.zzh()));
        contentValues.put("config_fetched_time", Long.valueOf(zzh2.zzg()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzh2.zzp()));
        contentValues.put("app_version_int", Long.valueOf(zzh2.zze()));
        contentValues.put("firebase_instance_id", zzh2.zzG());
        contentValues.put("daily_error_events_count", Long.valueOf(zzh2.zzi()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzh2.zzl()));
        contentValues.put("health_monitor_sample", zzh2.zzI());
        contentValues.put("android_id", 0L);
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzh2.zzaI()));
        contentValues.put("admob_app_id", zzh2.zzA());
        contentValues.put("dynamite_version", Long.valueOf(zzh2.zzo()));
        if (zzpv.zzu(zzC).zzr(zzjw.ANALYTICS_STORAGE)) {
            contentValues.put("session_stitching_token", zzh2.zzL());
        }
        contentValues.put("sgtm_upload_enabled", Boolean.valueOf(zzh2.zzaL()));
        contentValues.put("target_os_version", Long.valueOf(zzh2.zzw()));
        contentValues.put("session_stitching_token_hash", Long.valueOf(zzh2.zzv()));
        zzqr.zzb();
        zzio zzio = this.zzu;
        if (zzio.zzf().zzx(zzC, zzgi.zzaV)) {
            contentValues.put("ad_services_version", Integer.valueOf(zzh2.zza()));
            contentValues.put("attribution_eligibility_status", Long.valueOf(zzh2.zzf()));
        }
        contentValues.put("unmatched_first_open_without_ad_id", Boolean.valueOf(zzh2.zzaM()));
        contentValues.put("npa_metadata_value", zzh2.zzx());
        contentValues.put("bundle_delivery_index", Long.valueOf(zzh2.zzr()));
        contentValues.put("sgtm_preview_key", zzh2.zzM());
        contentValues.put("dma_consent_state", Integer.valueOf(zzh2.zzd()));
        contentValues.put("daily_realtime_dcu_count", Integer.valueOf(zzh2.zzc()));
        contentValues.put("serialized_npa_metadata", zzh2.zzK());
        if (zzio.zzf().zzx(zzC, zzgi.zzaP)) {
            contentValues.put("client_upload_eligibility", Integer.valueOf(zzh2.zzb()));
        }
        List zzN = zzh2.zzN();
        if (zzN != null) {
            if (zzN.isEmpty()) {
                zzio.zzaW().zzk().zzb("Safelisted events should not be an empty list. appId", zzC);
            } else {
                contentValues.put("safelisted_events", TextUtils.join(",", zzN));
            }
        }
        zzpn.zzb();
        if (zzio.zzf().zzx((String) null, zzgi.zzaJ) && !contentValues.containsKey("safelisted_events")) {
            contentValues.put("safelisted_events", (String) null);
        }
        contentValues.put("unmatched_pfo", zzh2.zzy());
        contentValues.put("unmatched_uwa", zzh2.zzz());
        contentValues.put("ad_campaign_info", zzh2.zzaN());
        try {
            SQLiteDatabase zzj2 = zzj();
            if (((long) zzj2.update("apps", contentValues, "app_id = ?", new String[]{zzC})) == 0 && zzj2.insertWithOnConflict("apps", (String) null, contentValues, 5) == -1) {
                zzio.zzaW().zze().zzb("Failed to insert/update app (got -1). appId", zzhe.zzn(zzC));
            }
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Error storing app. appId", zzhe.zzn(zzC), e);
        }
    }

    public final void zzU(String str, zzba zzba) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzba);
        zzg();
        zzav();
        zzjx zzu = zzu(str);
        zzjx zzjx = zzjx.zza;
        if (zzu == zzjx) {
            zzX(str, zzjx);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("dma_consent_settings", zzba.zzj());
        zzaG("consent_settings", "app_id", contentValues);
    }

    public final void zzV(zzbd zzbd) {
        zzaF("events", zzbd);
    }

    public final void zzW(String str, zzjx zzjx) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzjx);
        zzg();
        zzav();
        zzX(str, zzu(str));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("storage_consent_at_bundling", zzjx.zzq());
        zzaG("consent_settings", "app_id", contentValues);
    }

    public final void zzX(String str, zzjx zzjx) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(zzjx);
        zzg();
        zzav();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("consent_state", zzjx.zzq());
        contentValues.put("consent_source", Integer.valueOf(zzjx.zzb()));
        zzaG("consent_settings", "app_id", contentValues);
    }

    public final boolean zzY(String str) {
        zzio zzio = this.zzu;
        if (zzio.zzf().zzx((String) null, zzgi.zzaM)) {
            if (zzio.zzf().zzx((String) null, zzgi.zzaP)) {
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(Integer.valueOf(new zzmf[]{zzmf.GOOGLE_SIGNAL}[0].zza()));
                String zzaH = zzaH(arrayList);
                String zzaC = zzaC();
                if (zzay("SELECT COUNT(1) > 0 FROM upload_queue WHERE app_id=?" + zzaH + " AND NOT " + zzaC, new String[]{str}) != 0) {
                    return true;
                }
                return false;
            }
            if (zzay("SELECT COUNT(1) > 0 FROM upload_queue WHERE app_id=? AND NOT ".concat(zzaC()), new String[]{str}) != 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean zzZ(String str, String str2) {
        return zzay("select count(1) from raw_events where app_id = ? and name = ?", new String[]{str, str2}) > 0;
    }

    public final int zza(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzg();
        zzav();
        try {
            return zzj().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzio zzio = this.zzu;
            zzio.zzaW().zze().zzd("Error deleting conditional property", zzhe.zzn(str), zzio.zzj().zzf(str2), e);
            return 0;
        }
    }

    public final boolean zzaa() {
        return zzay("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzab() {
        return zzay("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    public final boolean zzac() {
        return zzay("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final boolean zzad(String str, zzov zzov) {
        zzg();
        zzav();
        Preconditions.checkNotNull(zzov);
        Preconditions.checkNotEmpty(str);
        zzio zzio = this.zzu;
        long currentTimeMillis = zzio.zzaU().currentTimeMillis();
        zzgg zzgg = zzgi.zzau;
        long j = zzov.zzb;
        if (j < currentTimeMillis - ((Long) zzgg.zza((Object) null)).longValue() || j > ((Long) zzgg.zza((Object) null)).longValue() + currentTimeMillis) {
            zzio.zzaW().zzk().zzd("Storing trigger URI outside of the max retention time span. appId, now, timestamp", zzhe.zzn(str), Long.valueOf(currentTimeMillis), Long.valueOf(j));
        }
        zzio.zzaW().zzj().zza("Saving trigger URI");
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("trigger_uri", zzov.zza);
        contentValues.put("source", Integer.valueOf(zzov.zzc));
        contentValues.put("timestamp_millis", Long.valueOf(j));
        try {
            if (zzj().insert("trigger_uris", (String) null, contentValues) != -1) {
                return true;
            }
            zzio.zzaW().zze().zzb("Failed to insert trigger URI (got -1). appId", zzhe.zzn(str));
            return false;
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Error storing trigger URI. appId", zzhe.zzn(str), e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzae() {
        zzio zzio = this.zzu;
        Context zzaT = zzio.zzaT();
        zzio.zzf();
        return zzaT.getDatabasePath("google_app_measurement.db").exists();
    }

    public final boolean zzaf(String str, Long l, long j, zzhm zzhm) {
        zzg();
        zzav();
        Preconditions.checkNotNull(zzhm);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        zzio zzio = this.zzu;
        byte[] zzcd = zzhm.zzcd();
        zzio.zzaW().zzj().zzc("Saving complex main event, appId, data size", zzio.zzj().zzd(str), Integer.valueOf(zzcd.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", zzcd);
        try {
            if (zzj().insertWithOnConflict("main_event_params", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzio.zzaW().zze().zzb("Failed to insert complex main event (got -1). appId", zzhe.zzn(str));
            return false;
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Error storing complex main event. appId", zzhe.zzn(str), e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzag(String str, long j) {
        zzio zzio = this.zzu;
        if (!zzio.zzf().zzx((String) null, zzgi.zzbe) && zzio.zzaU().currentTimeMillis() > 15000 + j) {
            return false;
        }
        try {
            if (zzaz("select count(*) from raw_events where app_id=? and timestamp >= ? and name not like '!_%' escape '!' limit 1;", new String[]{str, String.valueOf(j)}, 0) <= 0 && zzaz("select count(*) from raw_events where app_id=? and timestamp >= ? and name like '!_%' escape '!' limit 1;", new String[]{str, String.valueOf(j)}, 0) > 0) {
                return true;
            }
            return false;
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzb("Error checking backfill conditions", e);
            return false;
        }
    }

    public final boolean zzah(zzai zzai) {
        Preconditions.checkNotNull(zzai);
        zzg();
        zzav();
        String str = zzai.zza;
        Preconditions.checkNotNull(str);
        if (zzy(str, zzai.zzc.zzb) == null) {
            long zzay = zzay("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{str});
            this.zzu.zzf();
            if (zzay >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzai.zzb);
        contentValues.put("name", zzai.zzc.zzb);
        zzau(contentValues, "value", Preconditions.checkNotNull(zzai.zzc.zza()));
        contentValues.put("active", Boolean.valueOf(zzai.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzai.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzai.zzh));
        zzio zzio = this.zzu;
        contentValues.put("timed_out_event", zzio.zzw().zzay(zzai.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzai.zzd));
        contentValues.put("triggered_event", zzio.zzw().zzay(zzai.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzai.zzc.zzc));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzai.zzj));
        contentValues.put("expired_event", zzio.zzw().zzay(zzai.zzk));
        try {
            if (zzj().insertWithOnConflict("conditional_properties", (String) null, contentValues, 5) != -1) {
                return true;
            }
            zzio.zzaW().zze().zzb("Failed to insert/update conditional user property (got -1)", zzhe.zzn(str));
            return true;
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Error storing conditional user property", zzhe.zzn(str), e);
            return true;
        }
    }

    public final boolean zzai(zzqd zzqd) {
        Preconditions.checkNotNull(zzqd);
        zzg();
        zzav();
        String str = zzqd.zza;
        String str2 = zzqd.zzc;
        if (zzy(str, str2) == null) {
            if (zzqf.zzaq(str2)) {
                if (zzay("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{str}) >= ((long) this.zzu.zzf().zzi(str, zzgi.zzU, 25, 100))) {
                    return false;
                }
            } else if (!"_npa".equals(str2)) {
                long zzay = zzay("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{str, zzqd.zzb});
                this.zzu.zzf();
                if (zzay >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("origin", zzqd.zzb);
        contentValues.put("name", str2);
        contentValues.put("set_timestamp", Long.valueOf(zzqd.zzd));
        zzau(contentValues, "value", zzqd.zze);
        try {
            if (zzj().insertWithOnConflict("user_attributes", (String) null, contentValues, 5) != -1) {
                return true;
            }
            this.zzu.zzaW().zze().zzb("Failed to insert/update user property (got -1). appId", zzhe.zzn(str));
            return true;
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Error storing user property. appId", zzhe.zzn(zzqd.zza), e);
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        r7 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0072, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0075, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0076, code lost:
        r15 = r3;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007b, code lost:
        r15 = r3;
        r3 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0217  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x021d  */
    /* JADX WARNING: Removed duplicated region for block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0072 A[ExcHandler: all (th java.lang.Throwable), PHI: r3 
      PHI: (r3v8 android.database.Cursor) = (r3v0 android.database.Cursor), (r3v0 android.database.Cursor), (r3v0 android.database.Cursor), (r3v15 android.database.Cursor), (r3v15 android.database.Cursor), (r3v15 android.database.Cursor), (r3v0 android.database.Cursor) binds: [B:1:0x000e, B:30:0x0084, B:33:0x008a, B:15:0x005a, B:20:0x0066, B:21:?, B:7:0x0022] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x000e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzat(java.lang.String r20, long r21, long r23, com.google.android.gms.measurement.internal.zzpr r25) {
        /*
            r19 = this;
            r1 = r19
            r2 = r25
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r25)
            r19.zzg()
            r19.zzav()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r0 = r19.zzj()     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            boolean r4 = android.text.TextUtils.isEmpty(r20)     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            r13 = 1
            java.lang.String r5 = ""
            r14 = -1
            r12 = 0
            if (r4 == 0) goto L_0x0080
            int r4 = (r23 > r14 ? 1 : (r23 == r14 ? 0 : -1))
            if (r4 == 0) goto L_0x0034
            java.lang.String r6 = java.lang.String.valueOf(r23)     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            java.lang.String r7 = java.lang.String.valueOf(r21)     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            java.lang.String[] r6 = new java.lang.String[]{r6, r7}     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            goto L_0x003c
        L_0x002f:
            r0 = move-exception
            r7 = r20
            goto L_0x01ff
        L_0x0034:
            java.lang.String r6 = java.lang.String.valueOf(r21)     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            java.lang.String[] r6 = new java.lang.String[]{r6}     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
        L_0x003c:
            if (r4 == 0) goto L_0x0040
            java.lang.String r5 = "rowid <= ? and "
        L_0x0040:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            java.lang.String r7 = "select app_id, metadata_fingerprint from raw_events where "
            r4.append(r7)     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            r4.append(r5)     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            java.lang.String r5 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r4.append(r5)     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            java.lang.String r4 = r4.toString()     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            android.database.Cursor r3 = r0.rawQuery(r4, r6)     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x007a, all -> 0x0072 }
            if (r4 != 0) goto L_0x0062
            goto L_0x0215
        L_0x0062:
            java.lang.String r4 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x007a, all -> 0x0072 }
            java.lang.String r5 = r3.getString(r13)     // Catch:{ SQLiteException -> 0x0075, all -> 0x0072 }
            r3.close()     // Catch:{ SQLiteException -> 0x0075, all -> 0x0072 }
            r16 = r3
            r3 = r4
            r11 = r5
            goto L_0x00c6
        L_0x0072:
            r0 = move-exception
            goto L_0x021b
        L_0x0075:
            r0 = move-exception
            r15 = r3
            r3 = r4
            goto L_0x0201
        L_0x007a:
            r0 = move-exception
            r15 = r3
            r3 = r20
            goto L_0x0201
        L_0x0080:
            int r4 = (r23 > r14 ? 1 : (r23 == r14 ? 0 : -1))
            if (r4 == 0) goto L_0x008f
            java.lang.String r6 = java.lang.String.valueOf(r23)     // Catch:{ SQLiteException -> 0x002f, all -> 0x0072 }
            r7 = r20
            java.lang.String[] r6 = new java.lang.String[]{r7, r6}     // Catch:{ SQLiteException -> 0x01fe, all -> 0x0072 }
            goto L_0x0095
        L_0x008f:
            r7 = r20
            java.lang.String[] r6 = new java.lang.String[]{r20}     // Catch:{ SQLiteException -> 0x01fe, all -> 0x0072 }
        L_0x0095:
            if (r4 == 0) goto L_0x0099
            java.lang.String r5 = " and rowid <= ?"
        L_0x0099:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x01fe, all -> 0x0072 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x01fe, all -> 0x0072 }
            java.lang.String r8 = "select metadata_fingerprint from raw_events where app_id = ?"
            r4.append(r8)     // Catch:{ SQLiteException -> 0x01fe, all -> 0x0072 }
            r4.append(r5)     // Catch:{ SQLiteException -> 0x01fe, all -> 0x0072 }
            java.lang.String r5 = " order by rowid limit 1;"
            r4.append(r5)     // Catch:{ SQLiteException -> 0x01fe, all -> 0x0072 }
            java.lang.String r4 = r4.toString()     // Catch:{ SQLiteException -> 0x01fe, all -> 0x0072 }
            android.database.Cursor r3 = r0.rawQuery(r4, r6)     // Catch:{ SQLiteException -> 0x01fe, all -> 0x0072 }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x01fe, all -> 0x0072 }
            if (r4 != 0) goto L_0x00bb
            goto L_0x0215
        L_0x00bb:
            java.lang.String r5 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x01fe, all -> 0x0072 }
            r3.close()     // Catch:{ SQLiteException -> 0x01fe, all -> 0x0072 }
            r16 = r3
            r11 = r5
            r3 = r7
        L_0x00c6:
            java.lang.String r5 = "raw_events_metadata"
            java.lang.String r4 = "metadata"
            java.lang.String[] r6 = new java.lang.String[]{r4}     // Catch:{ SQLiteException -> 0x01f6, all -> 0x01f4 }
            java.lang.String r7 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r8 = new java.lang.String[]{r3, r11}     // Catch:{ SQLiteException -> 0x01f6, all -> 0x01f4 }
            java.lang.String r17 = "rowid"
            java.lang.String r18 = "2"
            r9 = 0
            r10 = 0
            r4 = r0
            r13 = r11
            r11 = r17
            r14 = r12
            r12 = r18
            android.database.Cursor r15 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ SQLiteException -> 0x01f6, all -> 0x01f4 }
            boolean r4 = r15.moveToFirst()     // Catch:{ SQLiteException -> 0x0103 }
            if (r4 != 0) goto L_0x0106
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ SQLiteException -> 0x0103 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ SQLiteException -> 0x0103 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ SQLiteException -> 0x0103 }
            java.lang.String r2 = "Raw event metadata record is missing. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ SQLiteException -> 0x0103 }
            r0.zzb(r2, r4)     // Catch:{ SQLiteException -> 0x0103 }
            goto L_0x01f0
        L_0x0100:
            r0 = move-exception
            goto L_0x01f2
        L_0x0103:
            r0 = move-exception
            goto L_0x0201
        L_0x0106:
            byte[] r4 = r15.getBlob(r14)     // Catch:{ SQLiteException -> 0x0103 }
            com.google.android.gms.internal.measurement.zzhw r5 = com.google.android.gms.internal.measurement.zzhx.zzz()     // Catch:{ IOException -> 0x01dc }
            com.google.android.gms.internal.measurement.zzng r4 = com.google.android.gms.measurement.internal.zzqa.zzp(r5, r4)     // Catch:{ IOException -> 0x01dc }
            com.google.android.gms.internal.measurement.zzhw r4 = (com.google.android.gms.internal.measurement.zzhw) r4     // Catch:{ IOException -> 0x01dc }
            com.google.android.gms.internal.measurement.zzmd r4 = r4.zzba()     // Catch:{ IOException -> 0x01dc }
            com.google.android.gms.internal.measurement.zzhx r4 = (com.google.android.gms.internal.measurement.zzhx) r4     // Catch:{ IOException -> 0x01dc }
            boolean r5 = r15.moveToNext()     // Catch:{ SQLiteException -> 0x0103 }
            if (r5 == 0) goto L_0x0133
            com.google.android.gms.measurement.internal.zzio r5 = r1.zzu     // Catch:{ SQLiteException -> 0x0103 }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ SQLiteException -> 0x0103 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zzk()     // Catch:{ SQLiteException -> 0x0103 }
            java.lang.String r6 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ SQLiteException -> 0x0103 }
            r5.zzb(r6, r7)     // Catch:{ SQLiteException -> 0x0103 }
        L_0x0133:
            r15.close()     // Catch:{ SQLiteException -> 0x0103 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ SQLiteException -> 0x0103 }
            r2.zza = r4     // Catch:{ SQLiteException -> 0x0103 }
            r4 = -1
            int r4 = (r23 > r4 ? 1 : (r23 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x014e
            java.lang.String r4 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            java.lang.String r5 = java.lang.String.valueOf(r23)     // Catch:{ SQLiteException -> 0x0103 }
            java.lang.String[] r5 = new java.lang.String[]{r3, r13, r5}     // Catch:{ SQLiteException -> 0x0103 }
        L_0x014b:
            r7 = r4
            r8 = r5
            goto L_0x0155
        L_0x014e:
            java.lang.String r4 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r5 = new java.lang.String[]{r3, r13}     // Catch:{ SQLiteException -> 0x0103 }
            goto L_0x014b
        L_0x0155:
            java.lang.String r5 = "raw_events"
            java.lang.String r4 = "rowid"
            java.lang.String r6 = "name"
            java.lang.String r9 = "timestamp"
            java.lang.String r10 = "data"
            java.lang.String[] r6 = new java.lang.String[]{r4, r6, r9, r10}     // Catch:{ SQLiteException -> 0x0103 }
            java.lang.String r11 = "rowid"
            r12 = 0
            r9 = 0
            r10 = 0
            r4 = r0
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ SQLiteException -> 0x0103 }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            if (r0 == 0) goto L_0x01c3
        L_0x0173:
            long r5 = r4.getLong(r14)     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            r0 = 3
            byte[] r0 = r4.getBlob(r0)     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            com.google.android.gms.internal.measurement.zzhl r7 = com.google.android.gms.internal.measurement.zzhm.zze()     // Catch:{ IOException -> 0x01a7 }
            com.google.android.gms.internal.measurement.zzng r0 = com.google.android.gms.measurement.internal.zzqa.zzp(r7, r0)     // Catch:{ IOException -> 0x01a7 }
            com.google.android.gms.internal.measurement.zzhl r0 = (com.google.android.gms.internal.measurement.zzhl) r0     // Catch:{ IOException -> 0x01a7 }
            r7 = 1
            java.lang.String r8 = r4.getString(r7)     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            r0.zzi(r8)     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            r8 = 2
            long r8 = r4.getLong(r8)     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            r0.zzm(r8)     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            com.google.android.gms.internal.measurement.zzmd r0 = r0.zzba()     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            com.google.android.gms.internal.measurement.zzhm r0 = (com.google.android.gms.internal.measurement.zzhm) r0     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            boolean r0 = r2.zza(r5, r0)     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            if (r0 != 0) goto L_0x01bc
            goto L_0x01d6
        L_0x01a3:
            r0 = move-exception
            goto L_0x01d8
        L_0x01a5:
            r0 = move-exception
            goto L_0x01da
        L_0x01a7:
            r0 = move-exception
            r7 = 1
            com.google.android.gms.measurement.internal.zzio r5 = r1.zzu     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            java.lang.String r6 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            r5.zzc(r6, r8, r0)     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
        L_0x01bc:
            boolean r0 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            if (r0 != 0) goto L_0x0173
            goto L_0x01d6
        L_0x01c3:
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            java.lang.String r2 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
            r0.zzb(r2, r5)     // Catch:{ SQLiteException -> 0x01a5, all -> 0x01a3 }
        L_0x01d6:
            r3 = r4
            goto L_0x0215
        L_0x01d8:
            r3 = r4
            goto L_0x021b
        L_0x01da:
            r15 = r4
            goto L_0x0201
        L_0x01dc:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu     // Catch:{ SQLiteException -> 0x0103 }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ SQLiteException -> 0x0103 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ SQLiteException -> 0x0103 }
            java.lang.String r4 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ SQLiteException -> 0x0103 }
            r2.zzc(r4, r5, r0)     // Catch:{ SQLiteException -> 0x0103 }
        L_0x01f0:
            r3 = r15
            goto L_0x0215
        L_0x01f2:
            r3 = r15
            goto L_0x021b
        L_0x01f4:
            r0 = move-exception
            goto L_0x01f8
        L_0x01f6:
            r0 = move-exception
            goto L_0x01fb
        L_0x01f8:
            r3 = r16
            goto L_0x021b
        L_0x01fb:
            r15 = r16
            goto L_0x0201
        L_0x01fe:
            r0 = move-exception
        L_0x01ff:
            r15 = r3
            r3 = r7
        L_0x0201:
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu     // Catch:{ all -> 0x0100 }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x0100 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x0100 }
            java.lang.String r4 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ all -> 0x0100 }
            r2.zzc(r4, r3, r0)     // Catch:{ all -> 0x0100 }
            goto L_0x01f0
        L_0x0215:
            if (r3 == 0) goto L_0x021a
            r3.close()
        L_0x021a:
            return
        L_0x021b:
            if (r3 == 0) goto L_0x0220
            r3.close()
        L_0x0220:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzat(java.lang.String, long, long, com.google.android.gms.measurement.internal.zzpr):void");
    }

    /* access modifiers changed from: protected */
    public final boolean zzb() {
        return false;
    }

    public final long zzd(String str, zzhv zzhv, String str2, Map map, zzmf zzmf, Long l) {
        int delete;
        String str3 = str;
        Long l2 = l;
        zzg();
        zzav();
        Preconditions.checkNotNull(zzhv);
        Preconditions.checkNotEmpty(str);
        zzio zzio = this.zzu;
        if (zzio.zzf().zzx((String) null, zzgi.zzaM)) {
            zzg();
            zzav();
            if (zzae()) {
                zzpv zzpv = this.zzg;
                long zza2 = zzpv.zzw().zzb.zza();
                long elapsedRealtime = zzio.zzaU().elapsedRealtime();
                long abs = Math.abs(elapsedRealtime - zza2);
                zzio.zzf();
                if (abs > zzam.zzJ()) {
                    zzpv.zzw().zzb.zzb(elapsedRealtime);
                    zzg();
                    zzav();
                    if (zzae() && (delete = zzj().delete("upload_queue", zzaC(), new String[0])) > 0) {
                        zzio.zzaW().zzj().zzb("Deleted stale MeasurementBatch rows from upload_queue. rowsDeleted", Integer.valueOf(delete));
                    }
                    if (zzio.zzf().zzx((String) null, zzgi.zzaP)) {
                        Preconditions.checkNotEmpty(str);
                        zzg();
                        zzav();
                        try {
                            int zzh2 = zzio.zzf().zzh(str3, zzgi.zzz);
                            if (zzh2 > 0) {
                                zzj().delete("upload_queue", "rowid in (SELECT rowid FROM upload_queue WHERE app_id=? ORDER BY rowid DESC LIMIT -1 OFFSET ?)", new String[]{str3, String.valueOf(zzh2)});
                            }
                        } catch (SQLiteException e) {
                            this.zzu.zzaW().zze().zzc("Error deleting over the limit queued batches. appId", zzhe.zzn(str), e);
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : map.entrySet()) {
                arrayList.add(((String) entry.getKey()) + "=" + ((String) entry.getValue()));
            }
            byte[] zzcd = zzhv.zzcd();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str3);
            contentValues.put("measurement_batch", zzcd);
            contentValues.put("upload_uri", str2);
            StringBuilder sb = new StringBuilder();
            Iterator it = arrayList.iterator();
            if (it.hasNext()) {
                while (true) {
                    sb.append((CharSequence) it.next());
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append("\r\n");
                }
            }
            contentValues.put("upload_headers", sb.toString());
            contentValues.put("upload_type", Integer.valueOf(zzmf.zza()));
            zzio zzio2 = this.zzu;
            contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzio2.zzaU().currentTimeMillis()));
            contentValues.put("retry_count", 0);
            if (l2 != null) {
                contentValues.put("associated_row_id", l2);
            }
            try {
                long insert = zzj().insert("upload_queue", (String) null, contentValues);
                if (insert != -1) {
                    return insert;
                }
                zzio2.zzaW().zze().zzb("Failed to insert MeasurementBatch (got -1) to upload_queue. appId", str3);
                return -1;
            } catch (SQLiteException e2) {
                this.zzu.zzaW().zze().zzc("Error storing MeasurementBatch to upload_queue. appId", str3, e2);
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public final long zze(String str, String str2) {
        long j;
        long j2;
        SQLiteException e;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty("first_open_count");
        zzg();
        zzav();
        SQLiteDatabase zzj2 = zzj();
        zzj2.beginTransaction();
        try {
            j = -1;
            j2 = zzaz("select " + "first_open_count" + " from app2 where app_id=?", new String[]{str}, -1);
            if (j2 == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", 0);
                contentValues.put("previous_install_count", 0);
                if (zzj2.insertWithOnConflict("app2", (String) null, contentValues, 5) == -1) {
                    this.zzu.zzaW().zze().zzc("Failed to insert column (got -1). appId", zzhe.zzn(str), "first_open_count");
                    zzj2.endTransaction();
                    return j;
                }
                j2 = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put("first_open_count", Long.valueOf(1 + j2));
                if (((long) zzj2.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    this.zzu.zzaW().zze().zzc("Failed to update column (got 0). appId", zzhe.zzn(str), "first_open_count");
                    zzj2.endTransaction();
                    return j;
                }
                zzj2.setTransactionSuccessful();
                j = j2;
                zzj2.endTransaction();
                return j;
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    this.zzu.zzaW().zze().zzd("Error inserting column. appId", zzhe.zzn(str), "first_open_count", e);
                    j = j2;
                    zzj2.endTransaction();
                    return j;
                } catch (Throwable th) {
                    zzj2.endTransaction();
                    throw th;
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            j2 = 0;
            this.zzu.zzaW().zze().zzd("Error inserting column. appId", zzhe.zzn(str), "first_open_count", e);
            j = j2;
            zzj2.endTransaction();
            return j;
        }
    }

    public final long zzf() {
        return zzaz("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    public final long zzh() {
        return zzaz("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public final long zzi(String str) {
        Preconditions.checkNotEmpty(str);
        return zzaz("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    /* access modifiers changed from: package-private */
    public final SQLiteDatabase zzj() {
        zzg();
        try {
            return this.zzm.getWritableDatabase();
        } catch (SQLiteException e) {
            this.zzu.zzaW().zzk().zzb("Error opening database", e);
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0087  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Bundle zzk(java.lang.String r6) {
        /*
            r5 = this;
            r5.zzg()
            r5.zzav()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r5.zzj()     // Catch:{ SQLiteException -> 0x006e, all -> 0x006c }
            java.lang.String r2 = "select parameters from default_event_params where app_id=?"
            java.lang.String[] r3 = new java.lang.String[]{r6}     // Catch:{ SQLiteException -> 0x006e, all -> 0x006c }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x006e, all -> 0x006c }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x002d }
            if (r2 != 0) goto L_0x002f
            com.google.android.gms.measurement.internal.zzio r6 = r5.zzu     // Catch:{ SQLiteException -> 0x002d }
            com.google.android.gms.measurement.internal.zzhe r6 = r6.zzaW()     // Catch:{ SQLiteException -> 0x002d }
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zzj()     // Catch:{ SQLiteException -> 0x002d }
            java.lang.String r2 = "Default event parameters not found"
            r6.zza(r2)     // Catch:{ SQLiteException -> 0x002d }
            goto L_0x007f
        L_0x002b:
            r6 = move-exception
            goto L_0x006a
        L_0x002d:
            r6 = move-exception
            goto L_0x0070
        L_0x002f:
            r2 = 0
            byte[] r2 = r1.getBlob(r2)     // Catch:{ SQLiteException -> 0x002d }
            com.google.android.gms.internal.measurement.zzhl r3 = com.google.android.gms.internal.measurement.zzhm.zze()     // Catch:{ IOException -> 0x0055 }
            com.google.android.gms.internal.measurement.zzng r2 = com.google.android.gms.measurement.internal.zzqa.zzp(r3, r2)     // Catch:{ IOException -> 0x0055 }
            com.google.android.gms.internal.measurement.zzhl r2 = (com.google.android.gms.internal.measurement.zzhl) r2     // Catch:{ IOException -> 0x0055 }
            com.google.android.gms.internal.measurement.zzmd r2 = r2.zzba()     // Catch:{ IOException -> 0x0055 }
            com.google.android.gms.internal.measurement.zzhm r2 = (com.google.android.gms.internal.measurement.zzhm) r2     // Catch:{ IOException -> 0x0055 }
            com.google.android.gms.measurement.internal.zzpv r6 = r5.zzg     // Catch:{ SQLiteException -> 0x002d }
            r6.zzA()     // Catch:{ SQLiteException -> 0x002d }
            java.util.List r6 = r2.zzi()     // Catch:{ SQLiteException -> 0x002d }
            android.os.Bundle r6 = com.google.android.gms.measurement.internal.zzqa.zzF(r6)     // Catch:{ SQLiteException -> 0x002d }
            r1.close()
            return r6
        L_0x0055:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzio r3 = r5.zzu     // Catch:{ SQLiteException -> 0x002d }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ SQLiteException -> 0x002d }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ SQLiteException -> 0x002d }
            java.lang.String r4 = "Failed to retrieve default event parameters. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r6)     // Catch:{ SQLiteException -> 0x002d }
            r3.zzc(r4, r6, r2)     // Catch:{ SQLiteException -> 0x002d }
            goto L_0x007f
        L_0x006a:
            r0 = r1
            goto L_0x0085
        L_0x006c:
            r6 = move-exception
            goto L_0x0085
        L_0x006e:
            r6 = move-exception
            r1 = r0
        L_0x0070:
            com.google.android.gms.measurement.internal.zzio r2 = r5.zzu     // Catch:{ all -> 0x002b }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x002b }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x002b }
            java.lang.String r3 = "Error selecting default event parameters"
            r2.zzb(r3, r6)     // Catch:{ all -> 0x002b }
        L_0x007f:
            if (r1 == 0) goto L_0x0084
            r1.close()
        L_0x0084:
            return r0
        L_0x0085:
            if (r0 == 0) goto L_0x008a
            r0.close()
        L_0x008a:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzk(java.lang.String):android.os.Bundle");
    }

    /* JADX WARNING: Removed duplicated region for block: B:92:0x0315  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x031b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzh zzl(java.lang.String r52) {
        /*
            r51 = this;
            r1 = r51
            r2 = r52
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r52)
            r51.zzg()
            r51.zzav()
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = r51.zzj()     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fc }
            java.lang.String r5 = "apps"
            java.lang.String r6 = "app_instance_id"
            java.lang.String r7 = "gmp_app_id"
            java.lang.String r8 = "resettable_device_id_hash"
            java.lang.String r9 = "last_bundle_index"
            java.lang.String r10 = "last_bundle_start_timestamp"
            java.lang.String r11 = "last_bundle_end_timestamp"
            java.lang.String r12 = "app_version"
            java.lang.String r13 = "app_store"
            java.lang.String r14 = "gmp_version"
            java.lang.String r15 = "dev_cert_hash"
            java.lang.String r16 = "measurement_enabled"
            java.lang.String r17 = "day"
            java.lang.String r18 = "daily_public_events_count"
            java.lang.String r19 = "daily_events_count"
            java.lang.String r20 = "daily_conversions_count"
            java.lang.String r21 = "config_fetched_time"
            java.lang.String r22 = "failed_config_fetch_time"
            java.lang.String r23 = "app_version_int"
            java.lang.String r24 = "firebase_instance_id"
            java.lang.String r25 = "daily_error_events_count"
            java.lang.String r26 = "daily_realtime_events_count"
            java.lang.String r27 = "health_monitor_sample"
            java.lang.String r28 = "android_id"
            java.lang.String r29 = "adid_reporting_enabled"
            java.lang.String r30 = "admob_app_id"
            java.lang.String r31 = "dynamite_version"
            java.lang.String r32 = "safelisted_events"
            java.lang.String r33 = "ga_app_id"
            java.lang.String r34 = "session_stitching_token"
            java.lang.String r35 = "sgtm_upload_enabled"
            java.lang.String r36 = "target_os_version"
            java.lang.String r37 = "session_stitching_token_hash"
            java.lang.String r38 = "ad_services_version"
            java.lang.String r39 = "unmatched_first_open_without_ad_id"
            java.lang.String r40 = "npa_metadata_value"
            java.lang.String r41 = "attribution_eligibility_status"
            java.lang.String r42 = "sgtm_preview_key"
            java.lang.String r43 = "dma_consent_state"
            java.lang.String r44 = "daily_realtime_dcu_count"
            java.lang.String r45 = "bundle_delivery_index"
            java.lang.String r46 = "serialized_npa_metadata"
            java.lang.String r47 = "unmatched_pfo"
            java.lang.String r48 = "unmatched_uwa"
            java.lang.String r49 = "ad_campaign_info"
            java.lang.String r50 = "client_upload_eligibility"
            java.lang.String[] r6 = new java.lang.String[]{r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50}     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fc }
            java.lang.String r7 = "app_id=?"
            java.lang.String[] r8 = new java.lang.String[]{r52}     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fc }
            r10 = 0
            r11 = 0
            r9 = 0
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x02fe, all -> 0x02fc }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x00aa }
            if (r0 != 0) goto L_0x0087
            goto L_0x0313
        L_0x0087:
            com.google.android.gms.measurement.internal.zzh r0 = new com.google.android.gms.measurement.internal.zzh     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.measurement.internal.zzpv r5 = r1.zzg     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.measurement.internal.zzio r6 = r5.zzt()     // Catch:{ SQLiteException -> 0x00aa }
            r0.<init>(r6, r2)     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.measurement.internal.zzjx r6 = r5.zzu(r2)     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.measurement.internal.zzjw r7 = com.google.android.gms.measurement.internal.zzjw.ANALYTICS_STORAGE     // Catch:{ SQLiteException -> 0x00aa }
            boolean r6 = r6.zzr(r7)     // Catch:{ SQLiteException -> 0x00aa }
            r8 = 0
            if (r6 == 0) goto L_0x00ad
            java.lang.String r6 = r4.getString(r8)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzV(r6)     // Catch:{ SQLiteException -> 0x00aa }
            goto L_0x00ad
        L_0x00a7:
            r0 = move-exception
            goto L_0x02fa
        L_0x00aa:
            r0 = move-exception
            goto L_0x0300
        L_0x00ad:
            r6 = 1
            java.lang.String r9 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzao(r9)     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.measurement.internal.zzjx r9 = r5.zzu(r2)     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.measurement.internal.zzjw r10 = com.google.android.gms.measurement.internal.zzjw.AD_STORAGE     // Catch:{ SQLiteException -> 0x00aa }
            boolean r9 = r9.zzr(r10)     // Catch:{ SQLiteException -> 0x00aa }
            if (r9 == 0) goto L_0x00c9
            r9 = 2
            java.lang.String r9 = r4.getString(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzax(r9)     // Catch:{ SQLiteException -> 0x00aa }
        L_0x00c9:
            r9 = 3
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzat(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 4
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzau(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 5
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzas(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 6
            java.lang.String r9 = r4.getString(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzX(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 7
            java.lang.String r9 = r4.getString(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzW(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 8
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzap(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 9
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzaj(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 10
            boolean r10 = r4.isNull(r9)     // Catch:{ SQLiteException -> 0x00aa }
            if (r10 != 0) goto L_0x0111
            int r9 = r4.getInt(r9)     // Catch:{ SQLiteException -> 0x00aa }
            if (r9 == 0) goto L_0x0113
        L_0x0111:
            r9 = r6
            goto L_0x0114
        L_0x0113:
            r9 = r8
        L_0x0114:
            r0.zzav(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 11
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzai(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 12
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzaf(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 13
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzae(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 14
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzac(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 15
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzab(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 16
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzam(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 17
            boolean r10 = r4.isNull(r9)     // Catch:{ SQLiteException -> 0x00aa }
            if (r10 == 0) goto L_0x0159
            r9 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x015e
        L_0x0159:
            int r9 = r4.getInt(r9)     // Catch:{ SQLiteException -> 0x00aa }
            long r9 = (long) r9     // Catch:{ SQLiteException -> 0x00aa }
        L_0x015e:
            r0.zzY(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 18
            java.lang.String r9 = r4.getString(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzan(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 19
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzad(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 20
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzah(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 21
            java.lang.String r9 = r4.getString(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzaq(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 23
            boolean r10 = r4.isNull(r9)     // Catch:{ SQLiteException -> 0x00aa }
            if (r10 != 0) goto L_0x0193
            int r9 = r4.getInt(r9)     // Catch:{ SQLiteException -> 0x00aa }
            if (r9 == 0) goto L_0x0195
        L_0x0193:
            r9 = r6
            goto L_0x0196
        L_0x0195:
            r9 = r8
        L_0x0196:
            r0.zzU(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 24
            java.lang.String r9 = r4.getString(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzS(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 25
            boolean r10 = r4.isNull(r9)     // Catch:{ SQLiteException -> 0x00aa }
            if (r10 == 0) goto L_0x01ad
            r9 = 0
            goto L_0x01b1
        L_0x01ad:
            long r9 = r4.getLong(r9)     // Catch:{ SQLiteException -> 0x00aa }
        L_0x01b1:
            r0.zzal(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r9 = 26
            boolean r10 = r4.isNull(r9)     // Catch:{ SQLiteException -> 0x00aa }
            if (r10 != 0) goto L_0x01ce
            java.lang.String r9 = r4.getString(r9)     // Catch:{ SQLiteException -> 0x00aa }
            java.lang.String r10 = ","
            r11 = -1
            java.lang.String[] r9 = r9.split(r10, r11)     // Catch:{ SQLiteException -> 0x00aa }
            java.util.List r9 = java.util.Arrays.asList(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzay(r9)     // Catch:{ SQLiteException -> 0x00aa }
        L_0x01ce:
            com.google.android.gms.measurement.internal.zzjx r5 = r5.zzu(r2)     // Catch:{ SQLiteException -> 0x00aa }
            boolean r5 = r5.zzr(r7)     // Catch:{ SQLiteException -> 0x00aa }
            if (r5 == 0) goto L_0x01e1
            r5 = 28
            java.lang.String r5 = r4.getString(r5)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzaA(r5)     // Catch:{ SQLiteException -> 0x00aa }
        L_0x01e1:
            r5 = 29
            boolean r7 = r4.isNull(r5)     // Catch:{ SQLiteException -> 0x00aa }
            if (r7 != 0) goto L_0x01f1
            int r5 = r4.getInt(r5)     // Catch:{ SQLiteException -> 0x00aa }
            if (r5 == 0) goto L_0x01f1
            r5 = r6
            goto L_0x01f2
        L_0x01f1:
            r5 = r8
        L_0x01f2:
            r0.zzaD(r5)     // Catch:{ SQLiteException -> 0x00aa }
            r5 = 39
            long r9 = r4.getLong(r5)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzar(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r5 = 36
            java.lang.String r5 = r4.getString(r5)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzaC(r5)     // Catch:{ SQLiteException -> 0x00aa }
            r5 = 30
            long r9 = r4.getLong(r5)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzaE(r9)     // Catch:{ SQLiteException -> 0x00aa }
            r5 = 31
            long r9 = r4.getLong(r5)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzaB(r9)     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.internal.measurement.zzqr.zzb()     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.measurement.internal.zzio r5 = r1.zzu     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.measurement.internal.zzam r7 = r5.zzf()     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.measurement.internal.zzgg r9 = com.google.android.gms.measurement.internal.zzgi.zzaV     // Catch:{ SQLiteException -> 0x00aa }
            boolean r7 = r7.zzx(r2, r9)     // Catch:{ SQLiteException -> 0x00aa }
            if (r7 == 0) goto L_0x023c
            r7 = 32
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzT(r7)     // Catch:{ SQLiteException -> 0x00aa }
            r7 = 35
            long r9 = r4.getLong(r7)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzZ(r9)     // Catch:{ SQLiteException -> 0x00aa }
        L_0x023c:
            r7 = 33
            boolean r9 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x00aa }
            if (r9 != 0) goto L_0x024c
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x00aa }
            if (r7 == 0) goto L_0x024c
            r7 = r6
            goto L_0x024d
        L_0x024c:
            r7 = r8
        L_0x024d:
            r0.zzaF(r7)     // Catch:{ SQLiteException -> 0x00aa }
            r7 = 34
            boolean r9 = r4.isNull(r7)     // Catch:{ SQLiteException -> 0x00aa }
            if (r9 == 0) goto L_0x025a
            r6 = r3
            goto L_0x0265
        L_0x025a:
            int r7 = r4.getInt(r7)     // Catch:{ SQLiteException -> 0x00aa }
            if (r7 == 0) goto L_0x0261
            r8 = r6
        L_0x0261:
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r8)     // Catch:{ SQLiteException -> 0x00aa }
        L_0x0265:
            r0.zzaw(r6)     // Catch:{ SQLiteException -> 0x00aa }
            r6 = 37
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzak(r6)     // Catch:{ SQLiteException -> 0x00aa }
            r6 = 38
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzag(r6)     // Catch:{ SQLiteException -> 0x00aa }
            r6 = 40
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x00aa }
            if (r7 == 0) goto L_0x0285
            java.lang.String r6 = ""
            goto L_0x028f
        L_0x0285:
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteException -> 0x00aa }
            java.lang.Object r6 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)     // Catch:{ SQLiteException -> 0x00aa }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ SQLiteException -> 0x00aa }
        L_0x028f:
            r0.zzaz(r6)     // Catch:{ SQLiteException -> 0x00aa }
            r6 = 41
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x00aa }
            if (r7 != 0) goto L_0x02a5
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x00aa }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzaG(r6)     // Catch:{ SQLiteException -> 0x00aa }
        L_0x02a5:
            r6 = 42
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x00aa }
            if (r7 != 0) goto L_0x02b8
            long r6 = r4.getLong(r6)     // Catch:{ SQLiteException -> 0x00aa }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzaH(r6)     // Catch:{ SQLiteException -> 0x00aa }
        L_0x02b8:
            r6 = 43
            byte[] r6 = r4.getBlob(r6)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzR(r6)     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.measurement.internal.zzam r6 = r5.zzf()     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.measurement.internal.zzgg r7 = com.google.android.gms.measurement.internal.zzgi.zzaP     // Catch:{ SQLiteException -> 0x00aa }
            boolean r6 = r6.zzx(r2, r7)     // Catch:{ SQLiteException -> 0x00aa }
            if (r6 == 0) goto L_0x02dc
            r6 = 44
            boolean r7 = r4.isNull(r6)     // Catch:{ SQLiteException -> 0x00aa }
            if (r7 != 0) goto L_0x02dc
            int r6 = r4.getInt(r6)     // Catch:{ SQLiteException -> 0x00aa }
            r0.zzaa(r6)     // Catch:{ SQLiteException -> 0x00aa }
        L_0x02dc:
            r0.zzO()     // Catch:{ SQLiteException -> 0x00aa }
            boolean r6 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x00aa }
            if (r6 == 0) goto L_0x02f6
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ SQLiteException -> 0x00aa }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ SQLiteException -> 0x00aa }
            java.lang.String r6 = "Got multiple records for app, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzhe.zzn(r52)     // Catch:{ SQLiteException -> 0x00aa }
            r5.zzb(r6, r7)     // Catch:{ SQLiteException -> 0x00aa }
        L_0x02f6:
            r4.close()
            return r0
        L_0x02fa:
            r3 = r4
            goto L_0x0319
        L_0x02fc:
            r0 = move-exception
            goto L_0x0319
        L_0x02fe:
            r0 = move-exception
            r4 = r3
        L_0x0300:
            com.google.android.gms.measurement.internal.zzio r5 = r1.zzu     // Catch:{ all -> 0x00a7 }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ all -> 0x00a7 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ all -> 0x00a7 }
            java.lang.String r6 = "Error querying app. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzhe.zzn(r52)     // Catch:{ all -> 0x00a7 }
            r5.zzc(r6, r2, r0)     // Catch:{ all -> 0x00a7 }
        L_0x0313:
            if (r4 == 0) goto L_0x0318
            r4.close()
        L_0x0318:
            return r3
        L_0x0319:
            if (r3 == 0) goto L_0x031e
            r3.close()
        L_0x031e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzl(java.lang.String):com.google.android.gms.measurement.internal.zzh");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x011d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzai zzm(java.lang.String r27, java.lang.String r28) {
        /*
            r26 = this;
            r1 = r26
            r8 = r28
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r27)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r28)
            r26.zzg()
            r26.zzav()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r26.zzj()     // Catch:{ SQLiteException -> 0x00f8, all -> 0x00f6 }
            java.lang.String r11 = "conditional_properties"
            java.lang.String r12 = "origin"
            java.lang.String r13 = "value"
            java.lang.String r14 = "active"
            java.lang.String r15 = "trigger_event_name"
            java.lang.String r16 = "trigger_timeout"
            java.lang.String r17 = "timed_out_event"
            java.lang.String r18 = "creation_timestamp"
            java.lang.String r19 = "triggered_event"
            java.lang.String r20 = "triggered_timestamp"
            java.lang.String r21 = "time_to_live"
            java.lang.String r22 = "expired_event"
            java.lang.String[] r12 = new java.lang.String[]{r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22}     // Catch:{ SQLiteException -> 0x00f8, all -> 0x00f6 }
            java.lang.String r13 = "app_id=? and name=?"
            java.lang.String[] r14 = new java.lang.String[]{r27, r28}     // Catch:{ SQLiteException -> 0x00f8, all -> 0x00f6 }
            r16 = 0
            r17 = 0
            r15 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteException -> 0x00f8, all -> 0x00f6 }
            boolean r0 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x0056 }
            if (r0 != 0) goto L_0x0048
            goto L_0x0115
        L_0x0048:
            r0 = 0
            java.lang.String r2 = r10.getString(r0)     // Catch:{ SQLiteException -> 0x0056 }
            if (r2 != 0) goto L_0x0051
            java.lang.String r2 = ""
        L_0x0051:
            r13 = r2
            goto L_0x0059
        L_0x0053:
            r0 = move-exception
            goto L_0x00f4
        L_0x0056:
            r0 = move-exception
            goto L_0x00fa
        L_0x0059:
            r2 = 1
            java.lang.Object r6 = r1.zzz(r10, r2)     // Catch:{ SQLiteException -> 0x0056 }
            r3 = 2
            int r3 = r10.getInt(r3)     // Catch:{ SQLiteException -> 0x0056 }
            if (r3 == 0) goto L_0x0068
            r17 = r2
            goto L_0x006a
        L_0x0068:
            r17 = r0
        L_0x006a:
            r0 = 3
            java.lang.String r18 = r10.getString(r0)     // Catch:{ SQLiteException -> 0x0056 }
            r0 = 4
            long r20 = r10.getLong(r0)     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzpv r0 = r1.zzg     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzqa r2 = r0.zzA()     // Catch:{ SQLiteException -> 0x0056 }
            r3 = 5
            byte[] r3 = r10.getBlob(r3)     // Catch:{ SQLiteException -> 0x0056 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbh> r4 = com.google.android.gms.measurement.internal.zzbh.CREATOR     // Catch:{ SQLiteException -> 0x0056 }
            android.os.Parcelable r2 = r2.zzi(r3, r4)     // Catch:{ SQLiteException -> 0x0056 }
            r19 = r2
            com.google.android.gms.measurement.internal.zzbh r19 = (com.google.android.gms.measurement.internal.zzbh) r19     // Catch:{ SQLiteException -> 0x0056 }
            r2 = 6
            long r15 = r10.getLong(r2)     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzqa r2 = r0.zzA()     // Catch:{ SQLiteException -> 0x0056 }
            r3 = 7
            byte[] r3 = r10.getBlob(r3)     // Catch:{ SQLiteException -> 0x0056 }
            android.os.Parcelable r2 = r2.zzi(r3, r4)     // Catch:{ SQLiteException -> 0x0056 }
            r22 = r2
            com.google.android.gms.measurement.internal.zzbh r22 = (com.google.android.gms.measurement.internal.zzbh) r22     // Catch:{ SQLiteException -> 0x0056 }
            r2 = 8
            long r11 = r10.getLong(r2)     // Catch:{ SQLiteException -> 0x0056 }
            r2 = 9
            long r23 = r10.getLong(r2)     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzqa r0 = r0.zzA()     // Catch:{ SQLiteException -> 0x0056 }
            r2 = 10
            byte[] r2 = r10.getBlob(r2)     // Catch:{ SQLiteException -> 0x0056 }
            android.os.Parcelable r0 = r0.zzi(r2, r4)     // Catch:{ SQLiteException -> 0x0056 }
            r25 = r0
            com.google.android.gms.measurement.internal.zzbh r25 = (com.google.android.gms.measurement.internal.zzbh) r25     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzqb r14 = new com.google.android.gms.measurement.internal.zzqb     // Catch:{ SQLiteException -> 0x0056 }
            r2 = r14
            r3 = r28
            r4 = r11
            r7 = r13
            r2.<init>(r3, r4, r6, r7)     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzai r0 = new com.google.android.gms.measurement.internal.zzai     // Catch:{ SQLiteException -> 0x0056 }
            r11 = r0
            r12 = r27
            r11.<init>(r12, r13, r14, r15, r17, r18, r19, r20, r22, r23, r25)     // Catch:{ SQLiteException -> 0x0056 }
            boolean r2 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x0056 }
            if (r2 == 0) goto L_0x00f0
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzhe r3 = r2.zzaW()     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ SQLiteException -> 0x0056 }
            java.lang.String r4 = "Got multiple records for conditional property, expected one"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzhe.zzn(r27)     // Catch:{ SQLiteException -> 0x0056 }
            com.google.android.gms.measurement.internal.zzgx r2 = r2.zzj()     // Catch:{ SQLiteException -> 0x0056 }
            java.lang.String r2 = r2.zzf(r8)     // Catch:{ SQLiteException -> 0x0056 }
            r3.zzc(r4, r5, r2)     // Catch:{ SQLiteException -> 0x0056 }
        L_0x00f0:
            r10.close()
            return r0
        L_0x00f4:
            r9 = r10
            goto L_0x011b
        L_0x00f6:
            r0 = move-exception
            goto L_0x011b
        L_0x00f8:
            r0 = move-exception
            r10 = r9
        L_0x00fa:
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu     // Catch:{ all -> 0x0053 }
            com.google.android.gms.measurement.internal.zzhe r3 = r2.zzaW()     // Catch:{ all -> 0x0053 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ all -> 0x0053 }
            java.lang.String r4 = "Error querying conditional property"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzhe.zzn(r27)     // Catch:{ all -> 0x0053 }
            com.google.android.gms.measurement.internal.zzgx r2 = r2.zzj()     // Catch:{ all -> 0x0053 }
            java.lang.String r2 = r2.zzf(r8)     // Catch:{ all -> 0x0053 }
            r3.zzd(r4, r5, r2, r0)     // Catch:{ all -> 0x0053 }
        L_0x0115:
            if (r10 == 0) goto L_0x011a
            r10.close()
        L_0x011a:
            return r9
        L_0x011b:
            if (r9 == 0) goto L_0x0120
            r9.close()
        L_0x0120:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzm(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzai");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzar zzn(java.lang.String r10) {
        /*
            r9 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            r9.zzg()
            r9.zzav()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r9.zzj()     // Catch:{ SQLiteException -> 0x006b, all -> 0x0069 }
            java.lang.String r2 = "apps"
            java.lang.String r3 = "remote_config"
            java.lang.String r4 = "config_last_modified_time"
            java.lang.String r5 = "e_tag"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r5}     // Catch:{ SQLiteException -> 0x006b, all -> 0x0069 }
            java.lang.String r4 = "app_id=?"
            java.lang.String[] r5 = new java.lang.String[]{r10}     // Catch:{ SQLiteException -> 0x006b, all -> 0x0069 }
            r7 = 0
            r8 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x006b, all -> 0x0069 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0059 }
            if (r2 != 0) goto L_0x002e
            goto L_0x0081
        L_0x002e:
            r2 = 0
            byte[] r2 = r1.getBlob(r2)     // Catch:{ SQLiteException -> 0x0059 }
            r3 = 1
            java.lang.String r3 = r1.getString(r3)     // Catch:{ SQLiteException -> 0x0059 }
            r4 = 2
            java.lang.String r4 = r1.getString(r4)     // Catch:{ SQLiteException -> 0x0059 }
            boolean r5 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0059 }
            if (r5 == 0) goto L_0x005b
            com.google.android.gms.measurement.internal.zzio r5 = r9.zzu     // Catch:{ SQLiteException -> 0x0059 }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ SQLiteException -> 0x0059 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ SQLiteException -> 0x0059 }
            java.lang.String r6 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzhe.zzn(r10)     // Catch:{ SQLiteException -> 0x0059 }
            r5.zzb(r6, r7)     // Catch:{ SQLiteException -> 0x0059 }
            goto L_0x005b
        L_0x0057:
            r10 = move-exception
            goto L_0x0067
        L_0x0059:
            r2 = move-exception
            goto L_0x006e
        L_0x005b:
            if (r2 != 0) goto L_0x005e
            goto L_0x0081
        L_0x005e:
            com.google.android.gms.measurement.internal.zzar r5 = new com.google.android.gms.measurement.internal.zzar     // Catch:{ SQLiteException -> 0x0059 }
            r5.<init>(r2, r3, r4)     // Catch:{ SQLiteException -> 0x0059 }
            r1.close()
            return r5
        L_0x0067:
            r0 = r1
            goto L_0x0087
        L_0x0069:
            r10 = move-exception
            goto L_0x0087
        L_0x006b:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L_0x006e:
            com.google.android.gms.measurement.internal.zzio r3 = r9.zzu     // Catch:{ all -> 0x0057 }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ all -> 0x0057 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ all -> 0x0057 }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzhe.zzn(r10)     // Catch:{ all -> 0x0057 }
            r3.zzc(r4, r10, r2)     // Catch:{ all -> 0x0057 }
        L_0x0081:
            if (r1 == 0) goto L_0x0086
            r1.close()
        L_0x0086:
            return r0
        L_0x0087:
            if (r0 == 0) goto L_0x008c
            r0.close()
        L_0x008c:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzn(java.lang.String):com.google.android.gms.measurement.internal.zzar");
    }

    public final zzas zzo(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        return zzp(j, str, 1, false, false, z3, false, z5, z6, z7);
    }

    public final zzas zzp(long j, String str, long j2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        Preconditions.checkNotEmpty(str);
        zzg();
        zzav();
        String[] strArr = {str};
        zzas zzas = new zzas();
        Cursor cursor = null;
        try {
            SQLiteDatabase zzj2 = zzj();
            cursor = zzj2.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count", "daily_realtime_dcu_count", "daily_registered_triggers_count"}, "app_id=?", new String[]{str}, (String) null, (String) null, (String) null);
            if (!cursor.moveToFirst()) {
                this.zzu.zzaW().zzk().zzb("Not updating daily counts, app is not known. appId", zzhe.zzn(str));
            } else {
                if (cursor.getLong(0) == j) {
                    zzas.zzb = cursor.getLong(1);
                    zzas.zza = cursor.getLong(2);
                    zzas.zzc = cursor.getLong(3);
                    zzas.zzd = cursor.getLong(4);
                    zzas.zze = cursor.getLong(5);
                    zzas.zzf = cursor.getLong(6);
                    zzas.zzg = cursor.getLong(7);
                }
                if (z) {
                    zzas.zzb += j2;
                }
                if (z2) {
                    zzas.zza += j2;
                }
                if (z3) {
                    zzas.zzc += j2;
                }
                if (z4) {
                    zzas.zzd += j2;
                }
                if (z5) {
                    zzas.zze += j2;
                }
                if (z6) {
                    zzas.zzf += j2;
                }
                if (z7) {
                    zzas.zzg += j2;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("day", Long.valueOf(j));
                contentValues.put("daily_public_events_count", Long.valueOf(zzas.zza));
                contentValues.put("daily_events_count", Long.valueOf(zzas.zzb));
                contentValues.put("daily_conversions_count", Long.valueOf(zzas.zzc));
                contentValues.put("daily_error_events_count", Long.valueOf(zzas.zzd));
                contentValues.put("daily_realtime_events_count", Long.valueOf(zzas.zze));
                contentValues.put("daily_realtime_dcu_count", Long.valueOf(zzas.zzf));
                contentValues.put("daily_registered_triggers_count", Long.valueOf(zzas.zzg));
                zzj2.update("apps", contentValues, "app_id=?", strArr);
            }
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzc("Error updating daily counts. appId", zzhe.zzn(str), e);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (cursor != null) {
            cursor.close();
        }
        return zzas;
    }

    public final zzba zzq(String str) {
        Preconditions.checkNotNull(str);
        zzg();
        zzav();
        return zzba.zze(zzaD("select dma_consent_settings from consent_settings where app_id=? limit 1;", new String[]{str}, ""));
    }

    /* access modifiers changed from: package-private */
    public final zzbd zzr(String str, zzhm zzhm, String str2) {
        zzbd zzaA = zzaA("events", str, zzhm.zzh());
        if (zzaA == null) {
            zzio zzio = this.zzu;
            zzio.zzaW().zzk().zzc("Event aggregate wasn't created during raw event logging. appId, event", zzhe.zzn(str), zzio.zzj().zzd(str2));
            return new zzbd(str, zzhm.zzh(), 1, 1, 1, zzhm.zzd(), 0, (Long) null, (Long) null, (Long) null, (Boolean) null);
        }
        long j = zzaA.zze + 1;
        long j2 = zzaA.zzd + 1;
        return new zzbd(zzaA.zza, zzaA.zzb, zzaA.zzc + 1, j2, j, zzaA.zzf, zzaA.zzg, zzaA.zzh, zzaA.zzi, zzaA.zzj, zzaA.zzk);
    }

    public final zzbd zzs(String str, String str2) {
        return zzaA("events", str, str2);
    }

    public final zzjx zzt(String str) {
        Preconditions.checkNotNull(str);
        zzg();
        zzav();
        return zzjx.zzk(zzaD("select storage_consent_at_bundling from consent_settings where app_id=? limit 1;", new String[]{str}, ""), 100);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.google.android.gms.measurement.internal.zzjx} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005b, code lost:
        if (r5 != null) goto L_0x002d;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzjx zzu(java.lang.String r5) {
        /*
            r4 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            r4.zzg()
            r4.zzav()
            java.lang.String[] r5 = new java.lang.String[]{r5}
            java.lang.String r0 = "select consent_state, consent_source from consent_settings where app_id=? limit 1;"
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.zzj()     // Catch:{ SQLiteException -> 0x0049, all -> 0x0046 }
            android.database.Cursor r5 = r2.rawQuery(r0, r5)     // Catch:{ SQLiteException -> 0x0049, all -> 0x0046 }
            boolean r0 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0033 }
            if (r0 != 0) goto L_0x0035
            com.google.android.gms.measurement.internal.zzio r0 = r4.zzu     // Catch:{ SQLiteException -> 0x0033 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ SQLiteException -> 0x0033 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()     // Catch:{ SQLiteException -> 0x0033 }
            java.lang.String r2 = "No data found"
            r0.zza(r2)     // Catch:{ SQLiteException -> 0x0033 }
        L_0x002d:
            r5.close()
            goto L_0x005e
        L_0x0031:
            r0 = move-exception
            goto L_0x0044
        L_0x0033:
            r0 = move-exception
            goto L_0x004c
        L_0x0035:
            r0 = 0
            java.lang.String r0 = r5.getString(r0)     // Catch:{ SQLiteException -> 0x0033 }
            r2 = 1
            int r2 = r5.getInt(r2)     // Catch:{ SQLiteException -> 0x0033 }
            com.google.android.gms.measurement.internal.zzjx r1 = com.google.android.gms.measurement.internal.zzjx.zzk(r0, r2)     // Catch:{ SQLiteException -> 0x0033 }
            goto L_0x002d
        L_0x0044:
            r1 = r5
            goto L_0x0064
        L_0x0046:
            r5 = move-exception
            r0 = r5
            goto L_0x0064
        L_0x0049:
            r5 = move-exception
            r0 = r5
            r5 = r1
        L_0x004c:
            com.google.android.gms.measurement.internal.zzio r2 = r4.zzu     // Catch:{ all -> 0x0031 }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x0031 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x0031 }
            java.lang.String r3 = "Error querying database."
            r2.zzb(r3, r0)     // Catch:{ all -> 0x0031 }
            if (r5 == 0) goto L_0x005e
            goto L_0x002d
        L_0x005e:
            if (r1 != 0) goto L_0x0063
            com.google.android.gms.measurement.internal.zzjx r5 = com.google.android.gms.measurement.internal.zzjx.zza
            return r5
        L_0x0063:
            return r1
        L_0x0064:
            if (r1 == 0) goto L_0x0069
            r1.close()
        L_0x0069:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzu(java.lang.String):com.google.android.gms.measurement.internal.zzjx");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0118  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzpz zzw(java.lang.String r27) {
        /*
            r26 = this;
            r14 = r26
            r15 = r27
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r27)
            r26.zzg()
            r26.zzav()
            com.google.android.gms.measurement.internal.zzio r0 = r14.zzu
            com.google.android.gms.measurement.internal.zzam r1 = r0.zzf()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaM
            r12 = 0
            boolean r1 = r1.zzx(r12, r2)
            if (r1 == 0) goto L_0x011c
            com.google.android.gms.measurement.internal.zzam r0 = r0.zzf()
            com.google.android.gms.measurement.internal.zzgg r1 = com.google.android.gms.measurement.internal.zzgi.zzaP
            boolean r0 = r0.zzx(r12, r1)
            r1 = 0
            if (r0 == 0) goto L_0x0046
            com.google.android.gms.measurement.internal.zzmf r0 = com.google.android.gms.measurement.internal.zzmf.GOOGLE_SIGNAL
            com.google.android.gms.measurement.internal.zzmf[] r0 = new com.google.android.gms.measurement.internal.zzmf[]{r0}
            com.google.android.gms.measurement.internal.zzpc r0 = com.google.android.gms.measurement.internal.zzpc.zza(r0)
            r2 = 1
            java.util.List r0 = r14.zzD(r15, r0, r2)
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L_0x003f
            return r12
        L_0x003f:
            java.lang.Object r0 = r0.get(r1)
            com.google.android.gms.measurement.internal.zzpz r0 = (com.google.android.gms.measurement.internal.zzpz) r0
            return r0
        L_0x0046:
            android.database.sqlite.SQLiteDatabase r2 = r26.zzj()     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.String r3 = "upload_queue"
            java.lang.String r16 = "rowId"
            java.lang.String r17 = "app_id"
            java.lang.String r18 = "measurement_batch"
            java.lang.String r19 = "upload_uri"
            java.lang.String r20 = "upload_headers"
            java.lang.String r21 = "upload_type"
            java.lang.String r22 = "retry_count"
            java.lang.String r23 = "creation_timestamp"
            java.lang.String r24 = "associated_row_id"
            java.lang.String r25 = "last_upload_timestamp"
            java.lang.String[] r4 = new java.lang.String[]{r16, r17, r18, r19, r20, r21, r22, r23, r24, r25}     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.String r0 = r26.zzaC()     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            r5.<init>()     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.String r6 = "app_id=? AND NOT "
            r5.append(r6)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            r5.append(r0)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.String r5 = r5.toString()     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.String[] r6 = new java.lang.String[]{r27}     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            java.lang.String r9 = "creation_timestamp ASC"
            java.lang.String r10 = "1"
            r7 = 0
            r8 = 0
            android.database.Cursor r13 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x00f0, all -> 0x00ec }
            boolean r0 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x00dc, all -> 0x00d8 }
            if (r0 != 0) goto L_0x0092
            r20 = r12
            r1 = r14
            goto L_0x010f
        L_0x0092:
            long r3 = r13.getLong(r1)     // Catch:{ SQLiteException -> 0x00dc, all -> 0x00d8 }
            r0 = 2
            byte[] r5 = r13.getBlob(r0)     // Catch:{ SQLiteException -> 0x00dc, all -> 0x00d8 }
            r0 = 3
            java.lang.String r6 = r13.getString(r0)     // Catch:{ SQLiteException -> 0x00dc, all -> 0x00d8 }
            r0 = 4
            java.lang.String r7 = r13.getString(r0)     // Catch:{ SQLiteException -> 0x00dc, all -> 0x00d8 }
            r0 = 5
            int r8 = r13.getInt(r0)     // Catch:{ SQLiteException -> 0x00dc, all -> 0x00d8 }
            r0 = 6
            int r9 = r13.getInt(r0)     // Catch:{ SQLiteException -> 0x00dc, all -> 0x00d8 }
            r0 = 7
            long r10 = r13.getLong(r0)     // Catch:{ SQLiteException -> 0x00dc, all -> 0x00d8 }
            r0 = 8
            long r16 = r13.getLong(r0)     // Catch:{ SQLiteException -> 0x00dc, all -> 0x00d8 }
            r0 = 9
            long r18 = r13.getLong(r0)     // Catch:{ SQLiteException -> 0x00dc, all -> 0x00d8 }
            r1 = r26
            r2 = r27
            r20 = r12
            r21 = r13
            r12 = r16
            r14 = r18
            com.google.android.gms.measurement.internal.zzpz r0 = r1.zzaB(r2, r3, r5, r6, r7, r8, r9, r10, r12, r14)     // Catch:{ SQLiteException -> 0x00d6, all -> 0x00d4 }
            r21.close()
            return r0
        L_0x00d4:
            r0 = move-exception
            goto L_0x00e2
        L_0x00d6:
            r0 = move-exception
            goto L_0x00e7
        L_0x00d8:
            r0 = move-exception
            r21 = r13
            goto L_0x00e2
        L_0x00dc:
            r0 = move-exception
            r20 = r12
            r21 = r13
            goto L_0x00e7
        L_0x00e2:
            r1 = r26
            r12 = r21
            goto L_0x0116
        L_0x00e7:
            r1 = r26
            r12 = r21
            goto L_0x00fd
        L_0x00ec:
            r0 = move-exception
            r20 = r12
            goto L_0x00f4
        L_0x00f0:
            r0 = move-exception
            r20 = r12
            goto L_0x00f9
        L_0x00f4:
            r1 = r26
            r12 = r20
            goto L_0x0116
        L_0x00f9:
            r1 = r26
            r12 = r20
        L_0x00fd:
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu     // Catch:{ all -> 0x0115 }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x0115 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x0115 }
            java.lang.String r3 = "Error to querying MeasurementBatch from upload_queue. appId"
            r4 = r27
            r2.zzc(r3, r4, r0)     // Catch:{ all -> 0x0115 }
            r13 = r12
        L_0x010f:
            if (r13 == 0) goto L_0x0114
            r13.close()
        L_0x0114:
            return r20
        L_0x0115:
            r0 = move-exception
        L_0x0116:
            if (r12 == 0) goto L_0x011b
            r12.close()
        L_0x011b:
            throw r0
        L_0x011c:
            r20 = r12
            r1 = r14
            return r20
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzw(java.lang.String):com.google.android.gms.measurement.internal.zzpz");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzpz zzx(long r22) {
        /*
            r21 = this;
            r14 = r21
            com.google.android.gms.measurement.internal.zzio r0 = r14.zzu
            com.google.android.gms.measurement.internal.zzam r0 = r0.zzf()
            com.google.android.gms.measurement.internal.zzgg r1 = com.google.android.gms.measurement.internal.zzgi.zzaP
            r15 = 0
            boolean r0 = r0.zzx(r15, r1)
            if (r0 != 0) goto L_0x0012
            return r15
        L_0x0012:
            r21.zzg()
            r21.zzav()
            android.database.sqlite.SQLiteDatabase r1 = r21.zzj()     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b6 }
            java.lang.String r2 = "upload_queue"
            java.lang.String r3 = "rowId"
            java.lang.String r4 = "app_id"
            java.lang.String r5 = "measurement_batch"
            java.lang.String r6 = "upload_uri"
            java.lang.String r7 = "upload_headers"
            java.lang.String r8 = "upload_type"
            java.lang.String r9 = "retry_count"
            java.lang.String r10 = "creation_timestamp"
            java.lang.String r11 = "associated_row_id"
            java.lang.String r12 = "last_upload_timestamp"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r5, r6, r7, r8, r9, r10, r11, r12}     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b6 }
            java.lang.String r4 = "rowId=?"
            java.lang.String r0 = java.lang.String.valueOf(r22)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b6 }
            java.lang.String[] r5 = new java.lang.String[]{r0}     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b6 }
            java.lang.String r9 = "1"
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r12 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b6 }
            boolean r0 = r12.moveToFirst()     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a2 }
            if (r0 != 0) goto L_0x0054
            r1 = r14
            r16 = r15
            goto L_0x00db
        L_0x0054:
            r0 = 1
            java.lang.String r0 = r12.getString(r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a2 }
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a2 }
            r2 = r0
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a2 }
            r0 = 2
            byte[] r5 = r12.getBlob(r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a2 }
            r0 = 3
            java.lang.String r6 = r12.getString(r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a2 }
            r0 = 4
            java.lang.String r7 = r12.getString(r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a2 }
            r0 = 5
            int r8 = r12.getInt(r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a2 }
            r0 = 6
            int r9 = r12.getInt(r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a2 }
            r0 = 7
            long r10 = r12.getLong(r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a2 }
            r0 = 8
            long r16 = r12.getLong(r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a2 }
            r0 = 9
            long r18 = r12.getLong(r0)     // Catch:{ SQLiteException -> 0x00a6, all -> 0x00a2 }
            r1 = r21
            r3 = r22
            r20 = r12
            r12 = r16
            r16 = r15
            r14 = r18
            com.google.android.gms.measurement.internal.zzpz r0 = r1.zzaB(r2, r3, r5, r6, r7, r8, r9, r10, r12, r14)     // Catch:{ SQLiteException -> 0x00a0, all -> 0x009e }
            r20.close()
            return r0
        L_0x009e:
            r0 = move-exception
            goto L_0x00ac
        L_0x00a0:
            r0 = move-exception
            goto L_0x00b1
        L_0x00a2:
            r0 = move-exception
            r20 = r12
            goto L_0x00ac
        L_0x00a6:
            r0 = move-exception
            r20 = r12
            r16 = r15
            goto L_0x00b1
        L_0x00ac:
            r1 = r21
            r15 = r20
            goto L_0x00e2
        L_0x00b1:
            r1 = r21
            r15 = r20
            goto L_0x00c7
        L_0x00b6:
            r0 = move-exception
            r16 = r15
            goto L_0x00be
        L_0x00ba:
            r0 = move-exception
            r16 = r15
            goto L_0x00c3
        L_0x00be:
            r1 = r21
            r15 = r16
            goto L_0x00e2
        L_0x00c3:
            r1 = r21
            r15 = r16
        L_0x00c7:
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu     // Catch:{ all -> 0x00e1 }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x00e1 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x00e1 }
            java.lang.String r3 = "Error to querying MeasurementBatch from upload_queue. rowId"
            java.lang.Long r4 = java.lang.Long.valueOf(r22)     // Catch:{ all -> 0x00e1 }
            r2.zzc(r3, r4, r0)     // Catch:{ all -> 0x00e1 }
            r12 = r15
        L_0x00db:
            if (r12 == 0) goto L_0x00e0
            r12.close()
        L_0x00e0:
            return r16
        L_0x00e1:
            r0 = move-exception
        L_0x00e2:
            if (r15 == 0) goto L_0x00e7
            r15.close()
        L_0x00e7:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzx(long):com.google.android.gms.measurement.internal.zzpz");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0097  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzqd zzy(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            r10.zzg()
            r10.zzav()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r10.zzj()     // Catch:{ SQLiteException -> 0x0071, all -> 0x006f }
            java.lang.String r2 = "user_attributes"
            java.lang.String r3 = "set_timestamp"
            java.lang.String r4 = "value"
            java.lang.String r5 = "origin"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4, r5}     // Catch:{ SQLiteException -> 0x0071, all -> 0x006f }
            java.lang.String r4 = "app_id=? and name=?"
            java.lang.String[] r5 = new java.lang.String[]{r11, r12}     // Catch:{ SQLiteException -> 0x0071, all -> 0x006f }
            r7 = 0
            r8 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0071, all -> 0x006f }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0067 }
            if (r2 != 0) goto L_0x0031
            goto L_0x008f
        L_0x0031:
            r2 = 0
            long r7 = r1.getLong(r2)     // Catch:{ SQLiteException -> 0x0067 }
            r2 = 1
            java.lang.Object r9 = r10.zzz(r1, r2)     // Catch:{ SQLiteException -> 0x0067 }
            if (r9 != 0) goto L_0x003e
            goto L_0x008f
        L_0x003e:
            r2 = 2
            java.lang.String r5 = r1.getString(r2)     // Catch:{ SQLiteException -> 0x0067 }
            com.google.android.gms.measurement.internal.zzqd r2 = new com.google.android.gms.measurement.internal.zzqd     // Catch:{ SQLiteException -> 0x0067 }
            r3 = r2
            r4 = r11
            r6 = r12
            r3.<init>(r4, r5, r6, r7, r9)     // Catch:{ SQLiteException -> 0x0067 }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0067 }
            if (r3 == 0) goto L_0x0069
            com.google.android.gms.measurement.internal.zzio r3 = r10.zzu     // Catch:{ SQLiteException -> 0x0067 }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ SQLiteException -> 0x0067 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ SQLiteException -> 0x0067 }
            java.lang.String r4 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzhe.zzn(r11)     // Catch:{ SQLiteException -> 0x0067 }
            r3.zzb(r4, r5)     // Catch:{ SQLiteException -> 0x0067 }
            goto L_0x0069
        L_0x0065:
            r11 = move-exception
            goto L_0x006d
        L_0x0067:
            r2 = move-exception
            goto L_0x0074
        L_0x0069:
            r1.close()
            return r2
        L_0x006d:
            r0 = r1
            goto L_0x0095
        L_0x006f:
            r11 = move-exception
            goto L_0x0095
        L_0x0071:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L_0x0074:
            com.google.android.gms.measurement.internal.zzio r3 = r10.zzu     // Catch:{ all -> 0x0065 }
            com.google.android.gms.measurement.internal.zzhe r4 = r3.zzaW()     // Catch:{ all -> 0x0065 }
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zze()     // Catch:{ all -> 0x0065 }
            java.lang.String r5 = "Error querying user property. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzhe.zzn(r11)     // Catch:{ all -> 0x0065 }
            com.google.android.gms.measurement.internal.zzgx r3 = r3.zzj()     // Catch:{ all -> 0x0065 }
            java.lang.String r12 = r3.zzf(r12)     // Catch:{ all -> 0x0065 }
            r4.zzd(r5, r11, r12, r2)     // Catch:{ all -> 0x0065 }
        L_0x008f:
            if (r1 == 0) goto L_0x0094
            r1.close()
        L_0x0094:
            return r0
        L_0x0095:
            if (r0 == 0) goto L_0x009a
            r0.close()
        L_0x009a:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaw.zzy(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzqd");
    }

    /* access modifiers changed from: package-private */
    public final Object zzz(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            this.zzu.zzaW().zze().zza("Loaded invalid null value from database");
            return null;
        } else if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        } else {
            if (type == 2) {
                return Double.valueOf(cursor.getDouble(i));
            }
            if (type == 3) {
                return cursor.getString(i);
            }
            if (type != 4) {
                this.zzu.zzaW().zze().zzb("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
            }
            this.zzu.zzaW().zze().zza("Loaded invalid blob type value, ignoring it");
            return null;
        }
    }
}
