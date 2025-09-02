package androidx.lifecycle.viewmodel;

import androidx.lifecycle.viewmodel.CreationExtras;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class MutableCreationExtras extends CreationExtras {
    public MutableCreationExtras() {
        this((CreationExtras) null, 1, (DefaultConstructorMarker) null);
    }

    public MutableCreationExtras(CreationExtras creationExtras) {
        Intrinsics.checkNotNullParameter(creationExtras, "initialExtras");
        getMap$lifecycle_viewmodel_release().putAll(creationExtras.getMap$lifecycle_viewmodel_release());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MutableCreationExtras(CreationExtras creationExtras, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CreationExtras.Empty.INSTANCE : creationExtras);
    }

    public final void set(CreationExtras.Key key, Object obj) {
        Intrinsics.checkNotNullParameter(key, "key");
        getMap$lifecycle_viewmodel_release().put(key, obj);
    }

    public Object get(CreationExtras.Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return getMap$lifecycle_viewmodel_release().get(key);
    }
}
