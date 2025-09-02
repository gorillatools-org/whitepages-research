package com.google.firebase.sessions;

import android.util.Log;
import androidx.datastore.core.CorruptionException;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesFactory;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class SessionDatastoreImpl$Companion$dataStore$2 extends Lambda implements Function1 {
    public static final SessionDatastoreImpl$Companion$dataStore$2 INSTANCE = new SessionDatastoreImpl$Companion$dataStore$2();

    SessionDatastoreImpl$Companion$dataStore$2() {
        super(1);
    }

    public final Preferences invoke(CorruptionException corruptionException) {
        Intrinsics.checkNotNullParameter(corruptionException, "ex");
        Log.w("FirebaseSessionsRepo", "CorruptionException in sessions DataStore in " + ProcessDetailsProvider.INSTANCE.getProcessName$com_google_firebase_firebase_sessions() + '.', corruptionException);
        return PreferencesFactory.createEmpty();
    }
}
