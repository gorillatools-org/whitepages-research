package com.google.firebase.ktx;

import androidx.annotation.Keep;
import com.google.firebase.BuildConfig;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.List;
import kotlin.collections.CollectionsKt;

@Keep
public final class FirebaseCommonLegacyRegistrar implements ComponentRegistrar {
    public List<Component<?>> getComponents() {
        return CollectionsKt.listOf(LibraryVersionComponent.create(LoggingKt.LIBRARY_NAME, BuildConfig.VERSION_NAME));
    }
}
