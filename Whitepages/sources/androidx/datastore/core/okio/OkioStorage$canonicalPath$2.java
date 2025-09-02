package androidx.datastore.core.okio;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import okio.Path;

final class OkioStorage$canonicalPath$2 extends Lambda implements Function0 {
    final /* synthetic */ OkioStorage this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OkioStorage$canonicalPath$2(OkioStorage okioStorage) {
        super(0);
        this.this$0 = okioStorage;
    }

    public final Path invoke() {
        Path path = (Path) this.this$0.producePath.invoke();
        boolean isAbsolute = path.isAbsolute();
        OkioStorage okioStorage = this.this$0;
        if (isAbsolute) {
            return path.normalized();
        }
        throw new IllegalStateException(("OkioStorage requires absolute paths, but did not get an absolute path from producePath = " + okioStorage.producePath + ", instead got " + path).toString());
    }
}
