package com.google.firebase.sessions;

import android.content.Context;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.dagger.Binds;
import com.google.firebase.sessions.dagger.BindsInstance;
import com.google.firebase.sessions.dagger.Component;
import com.google.firebase.sessions.dagger.Module;
import com.google.firebase.sessions.dagger.Provides;
import com.google.firebase.sessions.settings.SessionsSettings;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Component(modules = {MainModule.class})
public interface FirebaseSessionsComponent {

    @Component.Builder
    public interface Builder {
        @BindsInstance
        Builder appContext(Context context);

        @BindsInstance
        Builder backgroundDispatcher(@Background CoroutineContext coroutineContext);

        @BindsInstance
        Builder blockingDispatcher(@Blocking CoroutineContext coroutineContext);

        FirebaseSessionsComponent build();

        @BindsInstance
        Builder firebaseApp(FirebaseApp firebaseApp);

        @BindsInstance
        Builder firebaseInstallationsApi(FirebaseInstallationsApi firebaseInstallationsApi);

        @BindsInstance
        Builder transportFactoryProvider(Provider<TransportFactory> provider);
    }

    FirebaseSessions getFirebaseSessions();

    SessionDatastore getSessionDatastore();

    SessionFirelogPublisher getSessionFirelogPublisher();

    SessionGenerator getSessionGenerator();

    SessionsSettings getSessionsSettings();

    @Module
    public interface MainModule {
        public static final Companion Companion = Companion.$$INSTANCE;

        @Binds
        EventGDTLoggerInterface eventGDTLoggerInterface(EventGDTLogger eventGDTLogger);

        @Binds
        SessionDatastore sessionDatastore(SessionDatastoreImpl sessionDatastoreImpl);

        @Binds
        SessionFirelogPublisher sessionFirelogPublisher(SessionFirelogPublisherImpl sessionFirelogPublisherImpl);

        @Binds
        SessionLifecycleServiceBinder sessionLifecycleServiceBinder(SessionLifecycleServiceBinderImpl sessionLifecycleServiceBinderImpl);

        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            private Companion() {
            }

            @Provides
            public final SessionGenerator sessionGenerator() {
                return new SessionGenerator(WallClock.INSTANCE, (Function0) null, 2, (DefaultConstructorMarker) null);
            }
        }
    }
}
