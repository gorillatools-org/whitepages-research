package com.google.firebase.sessions;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseKt;

public interface SessionFirelogPublisher {
    public static final Companion Companion = Companion.$$INSTANCE;

    void logSession(SessionDetails sessionDetails);

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final SessionFirelogPublisher getInstance() {
            return ((FirebaseSessionsComponent) FirebaseKt.getApp(Firebase.INSTANCE).get(FirebaseSessionsComponent.class)).getSessionFirelogPublisher();
        }
    }
}
