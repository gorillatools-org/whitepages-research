package androidx.datastore.core.okio;

import androidx.datastore.core.InterProcessCoordinator;
import androidx.datastore.core.InterProcessCoordinatorKt;
import com.salesforce.marketingcloud.config.a;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;

public abstract class OkioStorageKt {
    public static final InterProcessCoordinator createSingleProcessCoordinator(Path path) {
        Intrinsics.checkNotNullParameter(path, a.j);
        return InterProcessCoordinatorKt.createSingleProcessCoordinator(path.normalized().toString());
    }
}
