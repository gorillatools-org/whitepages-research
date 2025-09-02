package okio.internal;

import com.salesforce.marketingcloud.config.a;
import java.io.IOException;
import java.util.Iterator;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;

/* renamed from: okio.internal.-FileSystem  reason: invalid class name */
public abstract class FileSystem {
    public static final boolean commonExists(okio.FileSystem fileSystem, Path path) {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, a.j);
        return fileSystem.metadataOrNull(path) != null;
    }

    public static final void commonCreateDirectories(okio.FileSystem fileSystem, Path path, boolean z) {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "dir");
        ArrayDeque arrayDeque = new ArrayDeque();
        Path path2 = path;
        while (path2 != null && !fileSystem.exists(path2)) {
            arrayDeque.addFirst(path2);
            path2 = path2.parent();
        }
        if (!z || !arrayDeque.isEmpty()) {
            Iterator it = arrayDeque.iterator();
            while (it.hasNext()) {
                fileSystem.createDirectory((Path) it.next());
            }
            return;
        }
        throw new IOException(path + " already exist.");
    }
}
