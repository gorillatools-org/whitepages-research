package com.google.firebase.heartbeatinfo;

import android.content.Context;
import android.content.SharedPreferences;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class HeartBeatInfoStorage {
    private static final String GLOBAL = "fire-global";
    private static final String HEARTBEAT_PREFERENCES_NAME = "FirebaseHeartBeat";
    private static final int HEART_BEAT_COUNT_LIMIT = 30;
    private static final String HEART_BEAT_COUNT_TAG = "fire-count";
    private static final String LAST_STORED_DATE = "last-used-date";
    private static final String PREFERENCES_NAME = "FirebaseAppHeartBeat";
    private static HeartBeatInfoStorage instance;
    private final SharedPreferences firebaseSharedPreferences;

    public HeartBeatInfoStorage(Context context, String str) {
        this.firebaseSharedPreferences = context.getSharedPreferences(HEARTBEAT_PREFERENCES_NAME + str, 0);
    }

    HeartBeatInfoStorage(SharedPreferences sharedPreferences) {
        this.firebaseSharedPreferences = sharedPreferences;
    }

    /* access modifiers changed from: package-private */
    public int getHeartBeatCount() {
        return (int) this.firebaseSharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0);
    }

    /* access modifiers changed from: package-private */
    public synchronized void deleteAllHeartBeats() {
        try {
            SharedPreferences.Editor edit = this.firebaseSharedPreferences.edit();
            int i = 0;
            for (Map.Entry next : this.firebaseSharedPreferences.getAll().entrySet()) {
                if (next.getValue() instanceof Set) {
                    String formattedDate = getFormattedDate(System.currentTimeMillis());
                    String str = (String) next.getKey();
                    if (((Set) next.getValue()).contains(formattedDate)) {
                        HashSet hashSet = new HashSet();
                        hashSet.add(formattedDate);
                        i++;
                        edit.putStringSet(str, hashSet);
                    } else {
                        edit.remove(str);
                    }
                }
            }
            if (i == 0) {
                edit.remove(HEART_BEAT_COUNT_TAG);
            } else {
                edit.putLong(HEART_BEAT_COUNT_TAG, (long) i);
            }
            edit.commit();
        } finally {
            while (true) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized List<HeartBeatResult> getAllHeartBeats() {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (Map.Entry next : this.firebaseSharedPreferences.getAll().entrySet()) {
                if (next.getValue() instanceof Set) {
                    HashSet hashSet = new HashSet((Set) next.getValue());
                    hashSet.remove(getFormattedDate(System.currentTimeMillis()));
                    if (!hashSet.isEmpty()) {
                        arrayList.add(HeartBeatResult.create((String) next.getKey(), new ArrayList(hashSet)));
                    }
                }
            }
            updateGlobalHeartBeat(System.currentTimeMillis());
        } finally {
            while (true) {
            }
        }
        return arrayList;
    }

    private synchronized String getStoredUserAgentString(String str) {
        for (Map.Entry next : this.firebaseSharedPreferences.getAll().entrySet()) {
            if (next.getValue() instanceof Set) {
                for (String equals : (Set) next.getValue()) {
                    if (str.equals(equals)) {
                        return (String) next.getKey();
                    }
                }
                continue;
            }
        }
        return null;
    }

    private synchronized void updateStoredUserAgent(String str, String str2) {
        removeStoredDate(str2);
        HashSet hashSet = new HashSet(this.firebaseSharedPreferences.getStringSet(str, new HashSet()));
        hashSet.add(str2);
        this.firebaseSharedPreferences.edit().putStringSet(str, hashSet).commit();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0040, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void removeStoredDate(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = r4.getStoredUserAgentString(r5)     // Catch:{ all -> 0x0030 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r4)
            return
        L_0x0009:
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ all -> 0x0030 }
            android.content.SharedPreferences r2 = r4.firebaseSharedPreferences     // Catch:{ all -> 0x0030 }
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ all -> 0x0030 }
            r3.<init>()     // Catch:{ all -> 0x0030 }
            java.util.Set r2 = r2.getStringSet(r0, r3)     // Catch:{ all -> 0x0030 }
            r1.<init>(r2)     // Catch:{ all -> 0x0030 }
            r1.remove(r5)     // Catch:{ all -> 0x0030 }
            boolean r5 = r1.isEmpty()     // Catch:{ all -> 0x0030 }
            if (r5 == 0) goto L_0x0032
            android.content.SharedPreferences r5 = r4.firebaseSharedPreferences     // Catch:{ all -> 0x0030 }
            android.content.SharedPreferences$Editor r5 = r5.edit()     // Catch:{ all -> 0x0030 }
            android.content.SharedPreferences$Editor r5 = r5.remove(r0)     // Catch:{ all -> 0x0030 }
            r5.commit()     // Catch:{ all -> 0x0030 }
            goto L_0x003f
        L_0x0030:
            r5 = move-exception
            goto L_0x0041
        L_0x0032:
            android.content.SharedPreferences r5 = r4.firebaseSharedPreferences     // Catch:{ all -> 0x0030 }
            android.content.SharedPreferences$Editor r5 = r5.edit()     // Catch:{ all -> 0x0030 }
            android.content.SharedPreferences$Editor r5 = r5.putStringSet(r0, r1)     // Catch:{ all -> 0x0030 }
            r5.commit()     // Catch:{ all -> 0x0030 }
        L_0x003f:
            monitor-exit(r4)
            return
        L_0x0041:
            monitor-exit(r4)     // Catch:{ all -> 0x0030 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.heartbeatinfo.HeartBeatInfoStorage.removeStoredDate(java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public synchronized void postHeartBeatCleanUp() {
        String formattedDate = getFormattedDate(System.currentTimeMillis());
        this.firebaseSharedPreferences.edit().putString(LAST_STORED_DATE, formattedDate).commit();
        removeStoredDate(formattedDate);
    }

    private synchronized String getFormattedDate(long j) {
        return new Date(j).toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /* access modifiers changed from: package-private */
    public synchronized void storeHeartBeat(long j, String str) {
        String formattedDate = getFormattedDate(j);
        if (this.firebaseSharedPreferences.getString(LAST_STORED_DATE, "").equals(formattedDate)) {
            String storedUserAgentString = getStoredUserAgentString(formattedDate);
            if (storedUserAgentString != null) {
                if (!storedUserAgentString.equals(str)) {
                    updateStoredUserAgent(str, formattedDate);
                    return;
                }
                return;
            }
            return;
        }
        long j2 = this.firebaseSharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0);
        if (j2 + 1 == 30) {
            cleanUpStoredHeartBeats();
            j2 = this.firebaseSharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0);
        }
        HashSet hashSet = new HashSet(this.firebaseSharedPreferences.getStringSet(str, new HashSet()));
        hashSet.add(formattedDate);
        this.firebaseSharedPreferences.edit().putStringSet(str, hashSet).putLong(HEART_BEAT_COUNT_TAG, j2 + 1).putString(LAST_STORED_DATE, formattedDate).commit();
    }

    private synchronized void cleanUpStoredHeartBeats() {
        try {
            long j = this.firebaseSharedPreferences.getLong(HEART_BEAT_COUNT_TAG, 0);
            String str = "";
            String str2 = null;
            for (Map.Entry next : this.firebaseSharedPreferences.getAll().entrySet()) {
                if (next.getValue() instanceof Set) {
                    for (String str3 : (Set) next.getValue()) {
                        if (str2 != null) {
                            if (str2.compareTo(str3) > 0) {
                            }
                        }
                        str = (String) next.getKey();
                        str2 = str3;
                    }
                }
            }
            HashSet hashSet = new HashSet(this.firebaseSharedPreferences.getStringSet(str, new HashSet()));
            hashSet.remove(str2);
            this.firebaseSharedPreferences.edit().putStringSet(str, hashSet).putLong(HEART_BEAT_COUNT_TAG, j - 1).commit();
        } finally {
            while (true) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized long getLastGlobalHeartBeat() {
        return this.firebaseSharedPreferences.getLong(GLOBAL, -1);
    }

    /* access modifiers changed from: package-private */
    public synchronized void updateGlobalHeartBeat(long j) {
        this.firebaseSharedPreferences.edit().putLong(GLOBAL, j).commit();
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean isSameDateUtc(long j, long j2) {
        return getFormattedDate(j).equals(getFormattedDate(j2));
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean shouldSendSdkHeartBeat(String str, long j) {
        if (!this.firebaseSharedPreferences.contains(str)) {
            this.firebaseSharedPreferences.edit().putLong(str, j).commit();
            return true;
        } else if (isSameDateUtc(this.firebaseSharedPreferences.getLong(str, -1), j)) {
            return false;
        } else {
            this.firebaseSharedPreferences.edit().putLong(str, j).commit();
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean shouldSendGlobalHeartBeat(long j) {
        return shouldSendSdkHeartBeat(GLOBAL, j);
    }
}
