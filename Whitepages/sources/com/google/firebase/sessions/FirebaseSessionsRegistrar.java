package com.google.firebase.sessions;

import android.content.Context;
import android.util.Log;
import androidx.annotation.Keep;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.sessions.FirebaseSessionsComponent;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;

@Keep
public final class FirebaseSessionsRegistrar implements ComponentRegistrar {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @Deprecated
    public static final String LIBRARY_NAME = "fire-sessions";
    @Deprecated
    public static final String TAG = "FirebaseSessions";
    /* access modifiers changed from: private */
    public static final Qualified<Context> appContext;
    /* access modifiers changed from: private */
    public static final Qualified<CoroutineDispatcher> backgroundDispatcher;
    /* access modifiers changed from: private */
    public static final Qualified<CoroutineDispatcher> blockingDispatcher;
    /* access modifiers changed from: private */
    public static final Qualified<FirebaseApp> firebaseApp;
    /* access modifiers changed from: private */
    public static final Qualified<FirebaseInstallationsApi> firebaseInstallationsApi;
    /* access modifiers changed from: private */
    public static final Qualified<FirebaseSessionsComponent> firebaseSessionsComponent;
    /* access modifiers changed from: private */
    public static final Qualified<TransportFactory> transportFactory;

    public List<Component<? extends Object>> getComponents() {
        return CollectionsKt.listOf(Component.builder(FirebaseSessions.class).name(LIBRARY_NAME).add(Dependency.required((Qualified<?>) firebaseSessionsComponent)).factory(new FirebaseSessionsRegistrar$$ExternalSyntheticLambda0()).eagerInDefaultApp().build(), Component.builder(FirebaseSessionsComponent.class).name("fire-sessions-component").add(Dependency.required((Qualified<?>) appContext)).add(Dependency.required((Qualified<?>) backgroundDispatcher)).add(Dependency.required((Qualified<?>) blockingDispatcher)).add(Dependency.required((Qualified<?>) firebaseApp)).add(Dependency.required((Qualified<?>) firebaseInstallationsApi)).add(Dependency.requiredProvider((Qualified<?>) transportFactory)).factory(new FirebaseSessionsRegistrar$$ExternalSyntheticLambda1()).build(), LibraryVersionComponent.create(LIBRARY_NAME, BuildConfig.VERSION_NAME));
    }

    /* access modifiers changed from: private */
    public static final FirebaseSessions getComponents$lambda$0(ComponentContainer componentContainer) {
        return ((FirebaseSessionsComponent) componentContainer.get(firebaseSessionsComponent)).getFirebaseSessions();
    }

    /* access modifiers changed from: private */
    public static final FirebaseSessionsComponent getComponents$lambda$1(ComponentContainer componentContainer) {
        FirebaseSessionsComponent.Builder builder = DaggerFirebaseSessionsComponent.builder();
        Object obj = componentContainer.get(appContext);
        Intrinsics.checkNotNullExpressionValue(obj, "container[appContext]");
        FirebaseSessionsComponent.Builder appContext2 = builder.appContext((Context) obj);
        Object obj2 = componentContainer.get(backgroundDispatcher);
        Intrinsics.checkNotNullExpressionValue(obj2, "container[backgroundDispatcher]");
        FirebaseSessionsComponent.Builder backgroundDispatcher2 = appContext2.backgroundDispatcher((CoroutineContext) obj2);
        Object obj3 = componentContainer.get(blockingDispatcher);
        Intrinsics.checkNotNullExpressionValue(obj3, "container[blockingDispatcher]");
        FirebaseSessionsComponent.Builder blockingDispatcher2 = backgroundDispatcher2.blockingDispatcher((CoroutineContext) obj3);
        Object obj4 = componentContainer.get(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(obj4, "container[firebaseApp]");
        FirebaseSessionsComponent.Builder firebaseApp2 = blockingDispatcher2.firebaseApp((FirebaseApp) obj4);
        Object obj5 = componentContainer.get(firebaseInstallationsApi);
        Intrinsics.checkNotNullExpressionValue(obj5, "container[firebaseInstallationsApi]");
        FirebaseSessionsComponent.Builder firebaseInstallationsApi2 = firebaseApp2.firebaseInstallationsApi((FirebaseInstallationsApi) obj5);
        Provider<TransportFactory> provider = componentContainer.getProvider(transportFactory);
        Intrinsics.checkNotNullExpressionValue(provider, "container.getProvider(transportFactory)");
        return firebaseInstallationsApi2.transportFactoryProvider(provider).build();
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Qualified<Context> getAppContext() {
            return FirebaseSessionsRegistrar.appContext;
        }

        public final Qualified<FirebaseApp> getFirebaseApp() {
            return FirebaseSessionsRegistrar.firebaseApp;
        }

        public final Qualified<FirebaseInstallationsApi> getFirebaseInstallationsApi() {
            return FirebaseSessionsRegistrar.firebaseInstallationsApi;
        }

        public final Qualified<CoroutineDispatcher> getBackgroundDispatcher() {
            return FirebaseSessionsRegistrar.backgroundDispatcher;
        }

        public final Qualified<CoroutineDispatcher> getBlockingDispatcher() {
            return FirebaseSessionsRegistrar.blockingDispatcher;
        }

        public final Qualified<TransportFactory> getTransportFactory() {
            return FirebaseSessionsRegistrar.transportFactory;
        }

        public final Qualified<FirebaseSessionsComponent> getFirebaseSessionsComponent() {
            return FirebaseSessionsRegistrar.firebaseSessionsComponent;
        }
    }

    static {
        Qualified<Context> unqualified = Qualified.unqualified(Context.class);
        Intrinsics.checkNotNullExpressionValue(unqualified, "unqualified(Context::class.java)");
        appContext = unqualified;
        Qualified<FirebaseApp> unqualified2 = Qualified.unqualified(FirebaseApp.class);
        Intrinsics.checkNotNullExpressionValue(unqualified2, "unqualified(FirebaseApp::class.java)");
        firebaseApp = unqualified2;
        Qualified<FirebaseInstallationsApi> unqualified3 = Qualified.unqualified(FirebaseInstallationsApi.class);
        Intrinsics.checkNotNullExpressionValue(unqualified3, "unqualified(FirebaseInstallationsApi::class.java)");
        firebaseInstallationsApi = unqualified3;
        Class<CoroutineDispatcher> cls = CoroutineDispatcher.class;
        Qualified<CoroutineDispatcher> qualified = Qualified.qualified(Background.class, cls);
        Intrinsics.checkNotNullExpressionValue(qualified, "qualified(Background::cl…neDispatcher::class.java)");
        backgroundDispatcher = qualified;
        Qualified<CoroutineDispatcher> qualified2 = Qualified.qualified(Blocking.class, cls);
        Intrinsics.checkNotNullExpressionValue(qualified2, "qualified(Blocking::clas…neDispatcher::class.java)");
        blockingDispatcher = qualified2;
        Qualified<TransportFactory> unqualified4 = Qualified.unqualified(TransportFactory.class);
        Intrinsics.checkNotNullExpressionValue(unqualified4, "unqualified(TransportFactory::class.java)");
        transportFactory = unqualified4;
        Qualified<FirebaseSessionsComponent> unqualified5 = Qualified.unqualified(FirebaseSessionsComponent.class);
        Intrinsics.checkNotNullExpressionValue(unqualified5, "unqualified(FirebaseSessionsComponent::class.java)");
        firebaseSessionsComponent = unqualified5;
        try {
            Companion.AnonymousClass1.INSTANCE.getClass();
        } catch (NoClassDefFoundError unused) {
            Log.w(TAG, "Your app is experiencing a known issue in the Android Gradle plugin, see https://issuetracker.google.com/328687152\n\nIt affects Java-only apps using AGP version 8.3.2 and under. To avoid the issue, either:\n\n1. Upgrade Android Gradle plugin to 8.4.0+\n   Follow the guide at https://developer.android.com/build/agp-upgrade-assistant\n\n2. Or, add the Kotlin plugin to your app\n   Follow the guide at https://developer.android.com/kotlin/add-kotlin\n\n3. Or, do the technical workaround described in https://issuetracker.google.com/issues/328687152#comment3");
        }
    }
}
