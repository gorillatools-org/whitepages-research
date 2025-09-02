package androidx.datastore.core.okio;

import androidx.datastore.core.okio.OkioStorage;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class OkioStorage$createConnection$2 extends Lambda implements Function0 {
    final /* synthetic */ OkioStorage this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OkioStorage$createConnection$2(OkioStorage okioStorage) {
        super(0);
        this.this$0 = okioStorage;
    }

    public final void invoke() {
        OkioStorage.Companion companion = OkioStorage.Companion;
        Synchronizer activeFilesLock = companion.getActiveFilesLock();
        OkioStorage okioStorage = this.this$0;
        synchronized (activeFilesLock) {
            companion.getActiveFiles$datastore_core_okio().remove(okioStorage.getCanonicalPath().toString());
            Unit unit = Unit.INSTANCE;
        }
    }
}
