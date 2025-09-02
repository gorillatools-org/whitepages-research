package okio.internal;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;

public final class ZipFilesKt$buildIndex$$inlined$sortedBy$1 implements Comparator {
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt.compareValues(((ZipEntry) obj).getCanonicalPath(), ((ZipEntry) obj2).getCanonicalPath());
    }
}
