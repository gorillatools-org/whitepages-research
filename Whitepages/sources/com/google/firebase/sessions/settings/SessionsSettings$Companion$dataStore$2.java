package com.google.firebase.sessions.settings;

import android.util.Log;
import androidx.datastore.core.CorruptionException;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesFactory;
import com.google.firebase.sessions.ProcessDetailsProvider;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class SessionsSettings$Companion$dataStore$2 extends Lambda implements Function1 {
    public static final SessionsSettings$Companion$dataStore$2 INSTANCE = new SessionsSettings$Companion$dataStore$2();

    SessionsSettings$Companion$dataStore$2() {
        super(1);
    }

    public final Preferences invoke(CorruptionException corruptionException) {
        Intrinsics.checkNotNullParameter(corruptionException, "ex");
        Log.w("SessionsSettings", "CorruptionException in settings DataStore in " + ProcessDetailsProvider.INSTANCE.getProcessName$com_google_firebase_firebase_sessions() + '.', corruptionException);
        return PreferencesFactory.createEmpty();
    }
}
