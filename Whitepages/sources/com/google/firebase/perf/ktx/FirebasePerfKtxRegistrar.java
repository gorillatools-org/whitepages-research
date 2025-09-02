package com.google.firebase.perf.ktx;

import androidx.annotation.Keep;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import java.util.List;
import kotlin.collections.CollectionsKt;

@Keep
public final class FirebasePerfKtxRegistrar implements ComponentRegistrar {
    public List<Component<?>> getComponents() {
        return CollectionsKt.emptyList();
    }
}
