package com.google.firebase.sessions;

import android.content.Context;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.FirebaseSessionsComponent;
import com.google.firebase.sessions.dagger.internal.DaggerGenerated;
import com.google.firebase.sessions.dagger.internal.DoubleCheck;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.InstanceFactory;
import com.google.firebase.sessions.dagger.internal.Preconditions;
import com.google.firebase.sessions.settings.SessionsSettings;
import com.google.firebase.sessions.settings.SessionsSettings_Factory;
import kotlin.coroutines.CoroutineContext;

@DaggerGenerated
public final class DaggerFirebaseSessionsComponent {
    private DaggerFirebaseSessionsComponent() {
    }

    public static FirebaseSessionsComponent.Builder builder() {
        return new Builder();
    }

    private static final class Builder implements FirebaseSessionsComponent.Builder {
        private Context appContext;
        private CoroutineContext backgroundDispatcher;
        private CoroutineContext blockingDispatcher;
        private FirebaseApp firebaseApp;
        private FirebaseInstallationsApi firebaseInstallationsApi;
        private Provider<TransportFactory> transportFactoryProvider;

        private Builder() {
        }

        public Builder appContext(Context context) {
            this.appContext = (Context) Preconditions.checkNotNull(context);
            return this;
        }

        public Builder backgroundDispatcher(CoroutineContext coroutineContext) {
            this.backgroundDispatcher = (CoroutineContext) Preconditions.checkNotNull(coroutineContext);
            return this;
        }

        public Builder blockingDispatcher(CoroutineContext coroutineContext) {
            this.blockingDispatcher = (CoroutineContext) Preconditions.checkNotNull(coroutineContext);
            return this;
        }

        public Builder firebaseApp(FirebaseApp firebaseApp2) {
            this.firebaseApp = (FirebaseApp) Preconditions.checkNotNull(firebaseApp2);
            return this;
        }

        public Builder firebaseInstallationsApi(FirebaseInstallationsApi firebaseInstallationsApi2) {
            this.firebaseInstallationsApi = (FirebaseInstallationsApi) Preconditions.checkNotNull(firebaseInstallationsApi2);
            return this;
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.google.firebase.inject.Provider<com.google.android.datatransport.TransportFactory>, java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.firebase.sessions.DaggerFirebaseSessionsComponent.Builder transportFactoryProvider(com.google.firebase.inject.Provider<com.google.android.datatransport.TransportFactory> r1) {
            /*
                r0 = this;
                java.lang.Object r1 = com.google.firebase.sessions.dagger.internal.Preconditions.checkNotNull(r1)
                com.google.firebase.inject.Provider r1 = (com.google.firebase.inject.Provider) r1
                r0.transportFactoryProvider = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.DaggerFirebaseSessionsComponent.Builder.transportFactoryProvider(com.google.firebase.inject.Provider):com.google.firebase.sessions.DaggerFirebaseSessionsComponent$Builder");
        }

        public FirebaseSessionsComponent build() {
            Preconditions.checkBuilderRequirement(this.appContext, Context.class);
            Class<CoroutineContext> cls = CoroutineContext.class;
            Preconditions.checkBuilderRequirement(this.backgroundDispatcher, cls);
            Preconditions.checkBuilderRequirement(this.blockingDispatcher, cls);
            Preconditions.checkBuilderRequirement(this.firebaseApp, FirebaseApp.class);
            Preconditions.checkBuilderRequirement(this.firebaseInstallationsApi, FirebaseInstallationsApi.class);
            Preconditions.checkBuilderRequirement(this.transportFactoryProvider, Provider.class);
            return new FirebaseSessionsComponentImpl(this.appContext, this.backgroundDispatcher, this.blockingDispatcher, this.firebaseApp, this.firebaseInstallationsApi, this.transportFactoryProvider);
        }
    }

    private static final class FirebaseSessionsComponentImpl implements FirebaseSessionsComponent {
        private javax.inject.Provider appContextProvider;
        private javax.inject.Provider backgroundDispatcherProvider;
        private javax.inject.Provider blockingDispatcherProvider;
        private javax.inject.Provider eventGDTLoggerProvider;
        private javax.inject.Provider firebaseAppProvider;
        private javax.inject.Provider firebaseInstallationsApiProvider;
        private final FirebaseSessionsComponentImpl firebaseSessionsComponentImpl;
        private javax.inject.Provider firebaseSessionsProvider;
        private javax.inject.Provider sessionDatastoreImplProvider;
        private javax.inject.Provider sessionFirelogPublisherImplProvider;
        private javax.inject.Provider sessionGeneratorProvider;
        private javax.inject.Provider sessionLifecycleServiceBinderImplProvider;
        private javax.inject.Provider sessionsSettingsProvider;
        private javax.inject.Provider transportFactoryProvider;

        private FirebaseSessionsComponentImpl(Context context, CoroutineContext coroutineContext, CoroutineContext coroutineContext2, FirebaseApp firebaseApp, FirebaseInstallationsApi firebaseInstallationsApi, Provider<TransportFactory> provider) {
            this.firebaseSessionsComponentImpl = this;
            initialize(context, coroutineContext, coroutineContext2, firebaseApp, firebaseInstallationsApi, provider);
        }

        private void initialize(Context context, CoroutineContext coroutineContext, CoroutineContext coroutineContext2, FirebaseApp firebaseApp, FirebaseInstallationsApi firebaseInstallationsApi, Provider<TransportFactory> provider) {
            this.firebaseAppProvider = InstanceFactory.create(firebaseApp);
            this.blockingDispatcherProvider = InstanceFactory.create(coroutineContext2);
            this.backgroundDispatcherProvider = InstanceFactory.create(coroutineContext);
            Factory create = InstanceFactory.create(firebaseInstallationsApi);
            this.firebaseInstallationsApiProvider = create;
            this.sessionsSettingsProvider = DoubleCheck.provider(SessionsSettings_Factory.create(this.firebaseAppProvider, this.blockingDispatcherProvider, this.backgroundDispatcherProvider, create));
            Factory create2 = InstanceFactory.create(context);
            this.appContextProvider = create2;
            javax.inject.Provider provider2 = DoubleCheck.provider(SessionLifecycleServiceBinderImpl_Factory.create(create2));
            this.sessionLifecycleServiceBinderImplProvider = provider2;
            this.firebaseSessionsProvider = DoubleCheck.provider(FirebaseSessions_Factory.create(this.firebaseAppProvider, this.sessionsSettingsProvider, this.backgroundDispatcherProvider, provider2));
            this.sessionDatastoreImplProvider = DoubleCheck.provider(SessionDatastoreImpl_Factory.create(this.appContextProvider, this.backgroundDispatcherProvider));
            Factory<TransportFactory> create3 = InstanceFactory.create(provider);
            this.transportFactoryProvider = create3;
            javax.inject.Provider provider3 = DoubleCheck.provider(EventGDTLogger_Factory.create(create3));
            this.eventGDTLoggerProvider = provider3;
            this.sessionFirelogPublisherImplProvider = DoubleCheck.provider(SessionFirelogPublisherImpl_Factory.create(this.firebaseAppProvider, this.firebaseInstallationsApiProvider, this.sessionsSettingsProvider, provider3, this.backgroundDispatcherProvider));
            this.sessionGeneratorProvider = DoubleCheck.provider(FirebaseSessionsComponent_MainModule_Companion_SessionGeneratorFactory.create());
        }

        public FirebaseSessions getFirebaseSessions() {
            return (FirebaseSessions) this.firebaseSessionsProvider.get();
        }

        public SessionDatastore getSessionDatastore() {
            return (SessionDatastore) this.sessionDatastoreImplProvider.get();
        }

        public SessionFirelogPublisher getSessionFirelogPublisher() {
            return (SessionFirelogPublisher) this.sessionFirelogPublisherImplProvider.get();
        }

        public SessionGenerator getSessionGenerator() {
            return (SessionGenerator) this.sessionGeneratorProvider.get();
        }

        public SessionsSettings getSessionsSettings() {
            return (SessionsSettings) this.sessionsSettingsProvider.get();
        }
    }
}
