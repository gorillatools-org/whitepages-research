package androidx.datastore.core.okio;

import androidx.datastore.core.InterProcessCoordinator;
import androidx.datastore.core.Storage;
import androidx.datastore.core.StorageConnection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.FileSystem;
import okio.Path;

public final class OkioStorage implements Storage {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Set activeFiles = new LinkedHashSet();
    /* access modifiers changed from: private */
    public static final Synchronizer activeFilesLock = new Synchronizer();
    private final Lazy canonicalPath$delegate;
    private final Function2 coordinatorProducer;
    private final FileSystem fileSystem;
    /* access modifiers changed from: private */
    public final Function0 producePath;
    private final OkioSerializer serializer;

    public OkioStorage(FileSystem fileSystem2, OkioSerializer okioSerializer, Function2 function2, Function0 function0) {
        Intrinsics.checkNotNullParameter(fileSystem2, "fileSystem");
        Intrinsics.checkNotNullParameter(okioSerializer, "serializer");
        Intrinsics.checkNotNullParameter(function2, "coordinatorProducer");
        Intrinsics.checkNotNullParameter(function0, "producePath");
        this.fileSystem = fileSystem2;
        this.serializer = okioSerializer;
        this.coordinatorProducer = function2;
        this.producePath = function0;
        this.canonicalPath$delegate = LazyKt.lazy(new OkioStorage$canonicalPath$2(this));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OkioStorage(FileSystem fileSystem2, OkioSerializer okioSerializer, Function2 function2, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fileSystem2, okioSerializer, (i & 4) != 0 ? AnonymousClass1.INSTANCE : function2, function0);
    }

    /* access modifiers changed from: private */
    public final Path getCanonicalPath() {
        return (Path) this.canonicalPath$delegate.getValue();
    }

    public StorageConnection createConnection() {
        String path = getCanonicalPath().toString();
        synchronized (activeFilesLock) {
            Set set = activeFiles;
            if (!set.contains(path)) {
                set.add(path);
            } else {
                throw new IllegalStateException(("There are multiple DataStores active for the same file: " + path + ". You should either maintain your DataStore as a singleton or confirm that there is no two DataStore's active on the same file (by confirming that the scope is cancelled).").toString());
            }
        }
        return new OkioStorageConnection(this.fileSystem, getCanonicalPath(), this.serializer, (InterProcessCoordinator) this.coordinatorProducer.invoke(getCanonicalPath(), this.fileSystem), new OkioStorage$createConnection$2(this));
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set getActiveFiles$datastore_core_okio() {
            return OkioStorage.activeFiles;
        }

        public final Synchronizer getActiveFilesLock() {
            return OkioStorage.activeFilesLock;
        }
    }
}
