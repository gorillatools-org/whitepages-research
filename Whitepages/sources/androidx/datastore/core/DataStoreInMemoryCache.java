package androidx.datastore.core;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

public final class DataStoreInMemoryCache {
    private final MutableStateFlow cachedValue;

    public DataStoreInMemoryCache() {
        UnInitialized unInitialized = UnInitialized.INSTANCE;
        Intrinsics.checkNotNull(unInitialized, "null cannot be cast to non-null type androidx.datastore.core.State<T of androidx.datastore.core.DataStoreInMemoryCache>");
        this.cachedValue = StateFlowKt.MutableStateFlow(unInitialized);
    }

    public final State getCurrentState() {
        return (State) this.cachedValue.getValue();
    }

    public final Flow getFlow() {
        return this.cachedValue;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: androidx.datastore.core.State} */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        if (r6.getVersion() > r2.getVersion()) goto L_0x002b;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final androidx.datastore.core.State tryUpdate(androidx.datastore.core.State r6) {
        /*
            r5 = this;
            java.lang.String r0 = "newState"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            kotlinx.coroutines.flow.MutableStateFlow r0 = r5.cachedValue
        L_0x0007:
            java.lang.Object r1 = r0.getValue()
            r2 = r1
            androidx.datastore.core.State r2 = (androidx.datastore.core.State) r2
            boolean r3 = r2 instanceof androidx.datastore.core.ReadException
            if (r3 == 0) goto L_0x0014
            r3 = 1
            goto L_0x001a
        L_0x0014:
            androidx.datastore.core.UnInitialized r3 = androidx.datastore.core.UnInitialized.INSTANCE
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
        L_0x001a:
            if (r3 == 0) goto L_0x001d
            goto L_0x002b
        L_0x001d:
            boolean r3 = r2 instanceof androidx.datastore.core.Data
            if (r3 == 0) goto L_0x002d
            int r3 = r6.getVersion()
            int r4 = r2.getVersion()
            if (r3 <= r4) goto L_0x0031
        L_0x002b:
            r2 = r6
            goto L_0x0031
        L_0x002d:
            boolean r3 = r2 instanceof androidx.datastore.core.Final
            if (r3 == 0) goto L_0x0038
        L_0x0031:
            boolean r1 = r0.compareAndSet(r1, r2)
            if (r1 == 0) goto L_0x0007
            return r2
        L_0x0038:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.DataStoreInMemoryCache.tryUpdate(androidx.datastore.core.State):androidx.datastore.core.State");
    }
}
