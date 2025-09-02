package com.google.firebase.abt.component;

import android.content.Context;
import androidx.annotation.Keep;
import com.google.firebase.abt.BuildConfig;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

@Keep
public class AbtRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-abt";

    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(AbtComponent.class).name(LIBRARY_NAME).add(Dependency.required((Class<?>) Context.class)).add(Dependency.optionalProvider((Class<?>) AnalyticsConnector.class)).factory(new AbtRegistrar$$ExternalSyntheticLambda0()).build(), LibraryVersionComponent.create(LIBRARY_NAME, BuildConfig.VERSION_NAME)});
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ AbtComponent lambda$getComponents$0(ComponentContainer componentContainer) {
        return new AbtComponent((Context) componentContainer.get(Context.class), componentContainer.getProvider(AnalyticsConnector.class));
    }
}
