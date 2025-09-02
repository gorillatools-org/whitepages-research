package com.google.firebase.messaging;

import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import java.util.List;
import kotlin.collections.CollectionsKt;

public final class FirebaseMessagingKtxRegistrar implements ComponentRegistrar {
    public List<Component<?>> getComponents() {
        return CollectionsKt.emptyList();
    }
}
