package com.google.firebase.analytics.connector.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.events.Subscriber;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
@Keep
public class AnalyticsConnectorRegistrar implements ComponentRegistrar {
    @SuppressLint({"MissingPermission"})
    @Keep
    @KeepForSdk
    public List<Component<?>> getComponents() {
        return Arrays.asList(new Component[]{Component.builder(AnalyticsConnector.class).add(Dependency.required((Class<?>) FirebaseApp.class)).add(Dependency.required((Class<?>) Context.class)).add(Dependency.required((Class<?>) Subscriber.class)).factory(new zzb()).eagerInDefaultApp().build(), LibraryVersionComponent.create("fire-analytics", "22.4.0")});
    }
}
