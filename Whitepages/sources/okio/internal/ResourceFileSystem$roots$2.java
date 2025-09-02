package okio.internal;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class ResourceFileSystem$roots$2 extends Lambda implements Function0 {
    final /* synthetic */ ClassLoader $classLoader;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ResourceFileSystem$roots$2(ClassLoader classLoader) {
        super(0);
        this.$classLoader = classLoader;
    }

    public final List invoke() {
        return ResourceFileSystem.Companion.toClasspathRoots(this.$classLoader);
    }
}
