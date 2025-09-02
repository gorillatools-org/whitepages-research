package com.google.firebase.sessions;

import com.google.firebase.sessions.FirebaseSessionsComponent;
import com.google.firebase.sessions.dagger.internal.DaggerGenerated;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.Preconditions;
import com.google.firebase.sessions.dagger.internal.QualifierMetadata;
import com.google.firebase.sessions.dagger.internal.ScopeMetadata;

@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata
public final class FirebaseSessionsComponent_MainModule_Companion_SessionGeneratorFactory implements Factory<SessionGenerator> {
    public SessionGenerator get() {
        return sessionGenerator();
    }

    public static FirebaseSessionsComponent_MainModule_Companion_SessionGeneratorFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static SessionGenerator sessionGenerator() {
        return (SessionGenerator) Preconditions.checkNotNullFromProvides(FirebaseSessionsComponent.MainModule.Companion.sessionGenerator());
    }

    private static final class InstanceHolder {
        /* access modifiers changed from: private */
        public static final FirebaseSessionsComponent_MainModule_Companion_SessionGeneratorFactory INSTANCE = new FirebaseSessionsComponent_MainModule_Companion_SessionGeneratorFactory();

        private InstanceHolder() {
        }
    }
}
