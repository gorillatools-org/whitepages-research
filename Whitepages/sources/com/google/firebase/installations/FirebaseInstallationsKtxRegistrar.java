package com.google.firebase.installations;

import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import java.util.List;
import kotlin.collections.CollectionsKt;

public final class FirebaseInstallationsKtxRegistrar implements ComponentRegistrar {
    public List<Component<?>> getComponents() {
        return CollectionsKt.emptyList();
    }
}
