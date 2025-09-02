package okio.internal;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class ResourceFileSystem$Companion$toJarRoot$zip$1 extends Lambda implements Function1 {
    public static final ResourceFileSystem$Companion$toJarRoot$zip$1 INSTANCE = new ResourceFileSystem$Companion$toJarRoot$zip$1();

    ResourceFileSystem$Companion$toJarRoot$zip$1() {
        super(1);
    }

    public final Boolean invoke(ZipEntry zipEntry) {
        Intrinsics.checkNotNullParameter(zipEntry, "entry");
        return Boolean.valueOf(ResourceFileSystem.Companion.keepPath(zipEntry.getCanonicalPath()));
    }
}
