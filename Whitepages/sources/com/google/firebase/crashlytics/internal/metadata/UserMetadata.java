package com.google.firebase.crashlytics.internal.metadata;

import com.facebook.internal.FetchedAppSettingsManager$$ExternalSyntheticBackportWithForwarding0;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

public class UserMetadata {
    public static final String INTERNAL_KEYDATA_FILENAME = "internal-keys";
    public static final String KEYDATA_FILENAME = "keys";
    public static final int MAX_ATTRIBUTES = 64;
    public static final int MAX_ATTRIBUTE_SIZE = 1024;
    public static final int MAX_INTERNAL_KEY_SIZE = 8192;
    public static final int MAX_ROLLOUT_ASSIGNMENTS = 128;
    public static final String ROLLOUTS_STATE_FILENAME = "rollouts-state";
    public static final String USERDATA_FILENAME = "user-data";
    /* access modifiers changed from: private */
    public final CrashlyticsWorkers crashlyticsWorkers;
    private final SerializeableKeysMap customKeys = new SerializeableKeysMap(false);
    private final SerializeableKeysMap internalKeys = new SerializeableKeysMap(true);
    /* access modifiers changed from: private */
    public final MetaDataStore metaDataStore;
    private final RolloutAssignmentList rolloutsState = new RolloutAssignmentList(128);
    /* access modifiers changed from: private */
    public String sessionIdentifier;
    private final AtomicMarkableReference<String> userId = new AtomicMarkableReference<>((Object) null, false);

    public static String readUserId(String str, FileStore fileStore) {
        return new MetaDataStore(fileStore).readUserId(str);
    }

    public static UserMetadata loadFromExistingSession(String str, FileStore fileStore, CrashlyticsWorkers crashlyticsWorkers2) {
        MetaDataStore metaDataStore2 = new MetaDataStore(fileStore);
        UserMetadata userMetadata = new UserMetadata(str, fileStore, crashlyticsWorkers2);
        userMetadata.customKeys.map.getReference().setKeys(metaDataStore2.readKeyData(str, false));
        userMetadata.internalKeys.map.getReference().setKeys(metaDataStore2.readKeyData(str, true));
        userMetadata.userId.set(metaDataStore2.readUserId(str), false);
        userMetadata.rolloutsState.updateRolloutAssignmentList(metaDataStore2.readRolloutsState(str));
        return userMetadata;
    }

    public UserMetadata(String str, FileStore fileStore, CrashlyticsWorkers crashlyticsWorkers2) {
        this.sessionIdentifier = str;
        this.metaDataStore = new MetaDataStore(fileStore);
        this.crashlyticsWorkers = crashlyticsWorkers2;
    }

    public void setNewSession(String str) {
        synchronized (this.sessionIdentifier) {
            this.sessionIdentifier = str;
            this.crashlyticsWorkers.diskWrite.submit((Runnable) new UserMetadata$$ExternalSyntheticLambda1(this, str, this.customKeys.getKeys(), this.rolloutsState.getRolloutAssignmentList()));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setNewSession$0(String str, Map map, List list) {
        if (getUserId() != null) {
            this.metaDataStore.writeUserData(str, getUserId());
        }
        if (!map.isEmpty()) {
            this.metaDataStore.writeKeyData(str, map);
        }
        if (!list.isEmpty()) {
            this.metaDataStore.writeRolloutState(str, list);
        }
    }

    public String getUserId() {
        return this.userId.getReference();
    }

    public void setUserId(String str) {
        String sanitizeString = KeysMap.sanitizeString(str, 1024);
        synchronized (this.userId) {
            try {
                if (!CommonUtils.nullSafeEquals(sanitizeString, this.userId.getReference())) {
                    this.userId.set(sanitizeString, true);
                    this.crashlyticsWorkers.diskWrite.submit((Runnable) new UserMetadata$$ExternalSyntheticLambda2(this));
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public Map<String, String> getCustomKeys(Map<String, String> map) {
        if (map.isEmpty()) {
            return this.customKeys.getKeys();
        }
        HashMap hashMap = new HashMap(this.customKeys.getKeys());
        int i = 0;
        for (Map.Entry next : map.entrySet()) {
            String sanitizeString = KeysMap.sanitizeString((String) next.getKey(), 1024);
            if (hashMap.size() < 64 || hashMap.containsKey(sanitizeString)) {
                hashMap.put(sanitizeString, KeysMap.sanitizeString((String) next.getValue(), 1024));
            } else {
                i++;
            }
        }
        if (i > 0) {
            Logger logger = Logger.getLogger();
            logger.w("Ignored " + i + " keys when adding event specific keys. Maximum allowable: " + 1024);
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public Map<String, String> getCustomKeys() {
        return this.customKeys.getKeys();
    }

    public boolean setCustomKey(String str, String str2) {
        return this.customKeys.setKey(str, str2);
    }

    public void setCustomKeys(Map<String, String> map) {
        this.customKeys.setKeys(map);
    }

    public Map<String, String> getInternalKeys() {
        return this.internalKeys.getKeys();
    }

    public boolean setInternalKey(String str, String str2) {
        return this.internalKeys.setKey(str, str2);
    }

    public List<CrashlyticsReport.Session.Event.RolloutAssignment> getRolloutsState() {
        return this.rolloutsState.getReportRolloutsState();
    }

    public boolean updateRolloutsState(List<RolloutAssignment> list) {
        synchronized (this.rolloutsState) {
            try {
                if (!this.rolloutsState.updateRolloutAssignmentList(list)) {
                    return false;
                }
                this.crashlyticsWorkers.diskWrite.submit((Runnable) new UserMetadata$$ExternalSyntheticLambda0(this, this.rolloutsState.getRolloutAssignmentList()));
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateRolloutsState$1(List list) {
        this.metaDataStore.writeRolloutState(this.sessionIdentifier, list);
    }

    /* access modifiers changed from: private */
    public void serializeUserDataIfNeeded() {
        boolean z;
        String str;
        synchronized (this.userId) {
            try {
                z = false;
                if (this.userId.isMarked()) {
                    str = getUserId();
                    this.userId.set(str, false);
                    z = true;
                } else {
                    str = null;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (z) {
            this.metaDataStore.writeUserData(this.sessionIdentifier, str);
        }
    }

    private class SerializeableKeysMap {
        private final boolean isInternal;
        final AtomicMarkableReference<KeysMap> map;
        private final AtomicReference<Runnable> queuedSerializer = new AtomicReference<>((Object) null);

        public SerializeableKeysMap(boolean z) {
            this.isInternal = z;
            this.map = new AtomicMarkableReference<>(new KeysMap(64, z ? UserMetadata.MAX_INTERNAL_KEY_SIZE : 1024), false);
        }

        public Map<String, String> getKeys() {
            return this.map.getReference().getKeys();
        }

        public boolean setKey(String str, String str2) {
            synchronized (this) {
                try {
                    if (!this.map.getReference().setKey(str, str2)) {
                        return false;
                    }
                    AtomicMarkableReference<KeysMap> atomicMarkableReference = this.map;
                    atomicMarkableReference.set(atomicMarkableReference.getReference(), true);
                    scheduleSerializationTaskIfNeeded();
                    return true;
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }

        public void setKeys(Map<String, String> map2) {
            synchronized (this) {
                this.map.getReference().setKeys(map2);
                AtomicMarkableReference<KeysMap> atomicMarkableReference = this.map;
                atomicMarkableReference.set(atomicMarkableReference.getReference(), true);
            }
            scheduleSerializationTaskIfNeeded();
        }

        private void scheduleSerializationTaskIfNeeded() {
            UserMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0 userMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0 = new UserMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0(this);
            if (FetchedAppSettingsManager$$ExternalSyntheticBackportWithForwarding0.m(this.queuedSerializer, (Object) null, userMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0)) {
                UserMetadata.this.crashlyticsWorkers.diskWrite.submit((Runnable) userMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$scheduleSerializationTaskIfNeeded$0() {
            this.queuedSerializer.set((Object) null);
            serializeIfMarked();
        }

        private void serializeIfMarked() {
            Map<String, String> map2;
            synchronized (this) {
                try {
                    if (this.map.isMarked()) {
                        map2 = this.map.getReference().getKeys();
                        AtomicMarkableReference<KeysMap> atomicMarkableReference = this.map;
                        atomicMarkableReference.set(atomicMarkableReference.getReference(), false);
                    } else {
                        map2 = null;
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            if (map2 != null) {
                UserMetadata.this.metaDataStore.writeKeyData(UserMetadata.this.sessionIdentifier, map2, this.isInternal);
            }
        }
    }
}
