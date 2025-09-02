package com.google.firebase.perf;

import androidx.annotation.Keep;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.StartupTime;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.perf.injection.components.DaggerFirebasePerformanceComponent;
import com.google.firebase.perf.injection.modules.FirebasePerformanceModule;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

@Keep
public class FirebasePerfRegistrar implements ComponentRegistrar {
    private static final String EARLY_LIBRARY_NAME = "fire-perf-early";
    private static final String LIBRARY_NAME = "fire-perf";

    @Keep
    public List<Component<?>> getComponents() {
        Qualified<Executor> qualified = Qualified.qualified(UiThread.class, Executor.class);
        Class<FirebaseApp> cls = FirebaseApp.class;
        Class<FirebasePerfEarly> cls2 = FirebasePerfEarly.class;
        return Arrays.asList(new Component[]{Component.builder(FirebasePerformance.class).name(LIBRARY_NAME).add(Dependency.required((Class<?>) cls)).add(Dependency.requiredProvider((Class<?>) RemoteConfigComponent.class)).add(Dependency.required((Class<?>) FirebaseInstallationsApi.class)).add(Dependency.requiredProvider((Class<?>) TransportFactory.class)).add(Dependency.required((Class<?>) cls2)).factory(new FirebasePerfRegistrar$$ExternalSyntheticLambda0()).build(), Component.builder(cls2).name(EARLY_LIBRARY_NAME).add(Dependency.required((Class<?>) cls)).add(Dependency.optionalProvider((Class<?>) StartupTime.class)).add(Dependency.required((Qualified<?>) qualified)).eagerInDefaultApp().factory(new FirebasePerfRegistrar$$ExternalSyntheticLambda1(qualified)).build(), LibraryVersionComponent.create(LIBRARY_NAME, BuildConfig.VERSION_NAME)});
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ FirebasePerfEarly lambda$getComponents$0(Qualified qualified, ComponentContainer componentContainer) {
        return new FirebasePerfEarly((FirebaseApp) componentContainer.get(FirebaseApp.class), componentContainer.getProvider(StartupTime.class).get(), (Executor) componentContainer.get(qualified));
    }

    /* access modifiers changed from: private */
    public static FirebasePerformance providesFirebasePerformance(ComponentContainer componentContainer) {
        componentContainer.get(FirebasePerfEarly.class);
        return DaggerFirebasePerformanceComponent.builder().firebasePerformanceModule(new FirebasePerformanceModule((FirebaseApp) componentContainer.get(FirebaseApp.class), (FirebaseInstallationsApi) componentContainer.get(FirebaseInstallationsApi.class), componentContainer.getProvider(RemoteConfigComponent.class), componentContainer.getProvider(TransportFactory.class))).build().getFirebasePerformance();
    }
}
