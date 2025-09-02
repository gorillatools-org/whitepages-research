package okio.internal;

import com.google.firebase.sessions.settings.RemoteSettings;
import com.salesforce.marketingcloud.config.a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Path;
import okio.Source;

public final class ResourceFileSystem extends FileSystem {
    /* access modifiers changed from: private */
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Path ROOT = Path.Companion.get$default(Path.Companion, RemoteSettings.FORWARD_SLASH_STRING, false, 1, (Object) null);
    private final Lazy roots$delegate;

    public ResourceFileSystem(ClassLoader classLoader, boolean z) {
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        this.roots$delegate = LazyKt.lazy(new ResourceFileSystem$roots$2(classLoader));
        if (z) {
            getRoots().size();
        }
    }

    private final List getRoots() {
        return (List) this.roots$delegate.getValue();
    }

    private final Path canonicalizeInternal(Path path) {
        return ROOT.resolve(path, true);
    }

    public FileHandle openReadOnly(Path path) {
        Intrinsics.checkNotNullParameter(path, "file");
        if (Companion.keepPath(path)) {
            String relativePath = toRelativePath(path);
            for (Pair pair : getRoots()) {
                try {
                    return ((FileSystem) pair.component1()).openReadOnly(((Path) pair.component2()).resolve(relativePath));
                } catch (FileNotFoundException unused) {
                }
            }
            throw new FileNotFoundException("file not found: " + path);
        }
        throw new FileNotFoundException("file not found: " + path);
    }

    public FileHandle openReadWrite(Path path, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(path, "file");
        throw new IOException("resources are not writable");
    }

    public FileMetadata metadataOrNull(Path path) {
        Intrinsics.checkNotNullParameter(path, a.j);
        if (!Companion.keepPath(path)) {
            return null;
        }
        String relativePath = toRelativePath(path);
        for (Pair pair : getRoots()) {
            FileMetadata metadataOrNull = ((FileSystem) pair.component1()).metadataOrNull(((Path) pair.component2()).resolve(relativePath));
            if (metadataOrNull != null) {
                return metadataOrNull;
            }
        }
        return null;
    }

    public Source source(Path path) {
        Intrinsics.checkNotNullParameter(path, "file");
        if (Companion.keepPath(path)) {
            String relativePath = toRelativePath(path);
            for (Pair pair : getRoots()) {
                try {
                    return ((FileSystem) pair.component1()).source(((Path) pair.component2()).resolve(relativePath));
                } catch (FileNotFoundException unused) {
                }
            }
            throw new FileNotFoundException("file not found: " + path);
        }
        throw new FileNotFoundException("file not found: " + path);
    }

    public void createDirectory(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "dir");
        throw new IOException(this + " is read-only");
    }

    public void atomicMove(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "source");
        Intrinsics.checkNotNullParameter(path2, "target");
        throw new IOException(this + " is read-only");
    }

    public void delete(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, a.j);
        throw new IOException(this + " is read-only");
    }

    private final String toRelativePath(Path path) {
        return canonicalizeInternal(path).relativeTo(ROOT).toString();
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Path getROOT() {
            return ResourceFileSystem.ROOT;
        }

        public final List toClasspathRoots(ClassLoader classLoader) {
            Intrinsics.checkNotNullParameter(classLoader, "<this>");
            Enumeration<URL> resources = classLoader.getResources("");
            Intrinsics.checkNotNullExpressionValue(resources, "getResources(\"\")");
            ArrayList<T> list = Collections.list(resources);
            Intrinsics.checkNotNullExpressionValue(list, "list(this)");
            ArrayList arrayList = new ArrayList();
            for (T t : list) {
                Companion access$getCompanion$p = ResourceFileSystem.Companion;
                Intrinsics.checkNotNullExpressionValue(t, "it");
                Pair fileRoot = access$getCompanion$p.toFileRoot(t);
                if (fileRoot != null) {
                    arrayList.add(fileRoot);
                }
            }
            Enumeration<URL> resources2 = classLoader.getResources("META-INF/MANIFEST.MF");
            Intrinsics.checkNotNullExpressionValue(resources2, "getResources(\"META-INF/MANIFEST.MF\")");
            ArrayList<T> list2 = Collections.list(resources2);
            Intrinsics.checkNotNullExpressionValue(list2, "list(this)");
            ArrayList arrayList2 = new ArrayList();
            for (T t2 : list2) {
                Companion access$getCompanion$p2 = ResourceFileSystem.Companion;
                Intrinsics.checkNotNullExpressionValue(t2, "it");
                Pair jarRoot = access$getCompanion$p2.toJarRoot(t2);
                if (jarRoot != null) {
                    arrayList2.add(jarRoot);
                }
            }
            return CollectionsKt.plus((Collection) arrayList, (Iterable) arrayList2);
        }

        public final Pair toFileRoot(URL url) {
            Intrinsics.checkNotNullParameter(url, "<this>");
            if (!Intrinsics.areEqual((Object) url.getProtocol(), (Object) "file")) {
                return null;
            }
            return TuplesKt.to(FileSystem.SYSTEM, Path.Companion.get$default(Path.Companion, new File(url.toURI()), false, 1, (Object) null));
        }

        public final Pair toJarRoot(URL url) {
            int lastIndexOf$default;
            Intrinsics.checkNotNullParameter(url, "<this>");
            String url2 = url.toString();
            Intrinsics.checkNotNullExpressionValue(url2, "toString()");
            if (!StringsKt.startsWith$default(url2, "jar:file:", false, 2, (Object) null) || (lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) url2, "!", 0, false, 6, (Object) null)) == -1) {
                return null;
            }
            Path.Companion companion = Path.Companion;
            String substring = url2.substring(4, lastIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return TuplesKt.to(ZipFilesKt.openZip(Path.Companion.get$default(companion, new File(URI.create(substring)), false, 1, (Object) null), FileSystem.SYSTEM, ResourceFileSystem$Companion$toJarRoot$zip$1.INSTANCE), getROOT());
        }

        /* access modifiers changed from: private */
        public final boolean keepPath(Path path) {
            return !StringsKt.endsWith(path.name(), ".class", true);
        }
    }
}
