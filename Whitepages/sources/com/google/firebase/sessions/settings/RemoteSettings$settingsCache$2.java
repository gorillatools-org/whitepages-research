package com.google.firebase.sessions.settings;

import androidx.datastore.core.DataStore;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class RemoteSettings$settingsCache$2 extends Lambda implements Function0 {
    final /* synthetic */ DataStore $dataStore;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteSettings$settingsCache$2(DataStore dataStore) {
        super(0);
        this.$dataStore = dataStore;
    }

    public final SettingsCache invoke() {
        return new SettingsCache(this.$dataStore);
    }
}
