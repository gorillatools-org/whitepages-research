package com.google.firebase.messaging;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

final class SharedPreferencesQueue {
    private boolean bulkOperation = false;
    final ArrayDeque<String> internalQueue = new ArrayDeque<>();
    private final String itemSeparator;
    private final String queueName;
    private final SharedPreferences sharedPreferences;
    private final Executor syncExecutor;

    private SharedPreferencesQueue(SharedPreferences sharedPreferences2, String str, String str2, Executor executor) {
        this.sharedPreferences = sharedPreferences2;
        this.queueName = str;
        this.itemSeparator = str2;
        this.syncExecutor = executor;
    }

    static SharedPreferencesQueue createInstance(SharedPreferences sharedPreferences2, String str, String str2, Executor executor) {
        SharedPreferencesQueue sharedPreferencesQueue = new SharedPreferencesQueue(sharedPreferences2, str, str2, executor);
        sharedPreferencesQueue.initQueue();
        return sharedPreferencesQueue;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initQueue() {
        /*
            r6 = this;
            java.util.ArrayDeque<java.lang.String> r0 = r6.internalQueue
            monitor-enter(r0)
            java.util.ArrayDeque<java.lang.String> r1 = r6.internalQueue     // Catch:{ all -> 0x0033 }
            r1.clear()     // Catch:{ all -> 0x0033 }
            android.content.SharedPreferences r1 = r6.sharedPreferences     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = r6.queueName     // Catch:{ all -> 0x0033 }
            java.lang.String r3 = ""
            java.lang.String r1 = r1.getString(r2, r3)     // Catch:{ all -> 0x0033 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0033 }
            if (r2 != 0) goto L_0x004b
            java.lang.String r2 = r6.itemSeparator     // Catch:{ all -> 0x0033 }
            boolean r2 = r1.contains(r2)     // Catch:{ all -> 0x0033 }
            if (r2 != 0) goto L_0x0021
            goto L_0x004b
        L_0x0021:
            java.lang.String r2 = r6.itemSeparator     // Catch:{ all -> 0x0033 }
            r3 = -1
            java.lang.String[] r1 = r1.split(r2, r3)     // Catch:{ all -> 0x0033 }
            int r2 = r1.length     // Catch:{ all -> 0x0033 }
            if (r2 != 0) goto L_0x0035
            java.lang.String r2 = "FirebaseMessaging"
            java.lang.String r3 = "Corrupted queue. Please check the queue contents and item separator provided"
            android.util.Log.e(r2, r3)     // Catch:{ all -> 0x0033 }
            goto L_0x0035
        L_0x0033:
            r1 = move-exception
            goto L_0x004d
        L_0x0035:
            int r2 = r1.length     // Catch:{ all -> 0x0033 }
            r3 = 0
        L_0x0037:
            if (r3 >= r2) goto L_0x0049
            r4 = r1[r3]     // Catch:{ all -> 0x0033 }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0033 }
            if (r5 != 0) goto L_0x0046
            java.util.ArrayDeque<java.lang.String> r5 = r6.internalQueue     // Catch:{ all -> 0x0033 }
            r5.add(r4)     // Catch:{ all -> 0x0033 }
        L_0x0046:
            int r3 = r3 + 1
            goto L_0x0037
        L_0x0049:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return
        L_0x004b:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return
        L_0x004d:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.SharedPreferencesQueue.initQueue():void");
    }

    public List<String> toList() {
        ArrayList arrayList;
        synchronized (this.internalQueue) {
            arrayList = new ArrayList(this.internalQueue);
        }
        return arrayList;
    }

    public boolean add(String str) {
        boolean checkAndSyncState;
        if (TextUtils.isEmpty(str) || str.contains(this.itemSeparator)) {
            return false;
        }
        synchronized (this.internalQueue) {
            checkAndSyncState = checkAndSyncState(this.internalQueue.add(str));
        }
        return checkAndSyncState;
    }

    private String checkAndSyncState(String str) {
        checkAndSyncState(str != null);
        return str;
    }

    private boolean checkAndSyncState(boolean z) {
        if (z && !this.bulkOperation) {
            syncStateAsync();
        }
        return z;
    }

    private void syncStateAsync() {
        this.syncExecutor.execute(new SharedPreferencesQueue$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public void syncState() {
        synchronized (this.internalQueue) {
            this.sharedPreferences.edit().putString(this.queueName, serialize()).commit();
        }
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.internalQueue.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(this.itemSeparator);
        }
        return sb.toString();
    }

    public void beginTransaction() {
        this.bulkOperation = true;
    }

    /* access modifiers changed from: package-private */
    public void beginTransactionSync() {
        synchronized (this.internalQueue) {
            beginTransaction();
        }
    }

    public void commitTransaction() {
        this.bulkOperation = false;
        syncStateAsync();
    }

    /* access modifiers changed from: package-private */
    public void commitTransactionSync() {
        synchronized (this.internalQueue) {
            commitTransaction();
        }
    }

    public String serializeSync() {
        String serialize;
        synchronized (this.internalQueue) {
            serialize = serialize();
        }
        return serialize;
    }

    public boolean remove(Object obj) {
        boolean checkAndSyncState;
        synchronized (this.internalQueue) {
            checkAndSyncState = checkAndSyncState(this.internalQueue.remove(obj));
        }
        return checkAndSyncState;
    }

    public String remove() {
        String checkAndSyncState;
        synchronized (this.internalQueue) {
            checkAndSyncState = checkAndSyncState(this.internalQueue.remove());
        }
        return checkAndSyncState;
    }

    public void clear() {
        synchronized (this.internalQueue) {
            this.internalQueue.clear();
            checkAndSyncState(true);
        }
    }

    public String peek() {
        String peek;
        synchronized (this.internalQueue) {
            peek = this.internalQueue.peek();
        }
        return peek;
    }

    public int size() {
        int size;
        synchronized (this.internalQueue) {
            size = this.internalQueue.size();
        }
        return size;
    }
}
