package com.google.firebase.sessions;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseKt;

public interface SessionDatastore {
    public static final Companion Companion = Companion.$$INSTANCE;

    String getCurrentSessionId();

    void updateSessionId(String str);

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final SessionDatastore getInstance() {
            return ((FirebaseSessionsComponent) FirebaseKt.getApp(Firebase.INSTANCE).get(FirebaseSessionsComponent.class)).getSessionDatastore();
        }
    }
}
