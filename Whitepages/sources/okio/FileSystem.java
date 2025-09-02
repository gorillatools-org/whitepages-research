package okio;

import com.salesforce.marketingcloud.config.a;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;
import okio.internal.ResourceFileSystem;

public abstract class FileSystem {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final FileSystem RESOURCES;
    public static final FileSystem SYSTEM;
    public static final Path SYSTEM_TEMPORARY_DIRECTORY;

    public abstract void atomicMove(Path path, Path path2);

    public abstract void createDirectory(Path path, boolean z);

    public abstract void delete(Path path, boolean z);

    public abstract FileMetadata metadataOrNull(Path path);

    public abstract FileHandle openReadOnly(Path path);

    public abstract FileHandle openReadWrite(Path path, boolean z, boolean z2);

    public abstract Source source(Path path);

    public final boolean exists(Path path) {
        Intrinsics.checkNotNullParameter(path, a.j);
        return okio.internal.FileSystem.commonExists(this, path);
    }

    public final FileHandle openReadWrite(Path path) {
        Intrinsics.checkNotNullParameter(path, "file");
        return openReadWrite(path, false, false);
    }

    public final void createDirectory(Path path) {
        Intrinsics.checkNotNullParameter(path, "dir");
        createDirectory(path, false);
    }

    public final void createDirectories(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "dir");
        okio.internal.FileSystem.commonCreateDirectories(this, path, z);
    }

    public final void delete(Path path) {
        Intrinsics.checkNotNullParameter(path, a.j);
        delete(path, false);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        FileSystem fileSystem;
        try {
            Class.forName("java.nio.file.Files");
            fileSystem = new NioSystemFileSystem();
        } catch (ClassNotFoundException unused) {
            fileSystem = new JvmSystemFileSystem();
        }
        SYSTEM = fileSystem;
        Path.Companion companion = Path.Companion;
        String property = System.getProperty("java.io.tmpdir");
        Intrinsics.checkNotNullExpressionValue(property, "getProperty(\"java.io.tmpdir\")");
        SYSTEM_TEMPORARY_DIRECTORY = Path.Companion.get$default(companion, property, false, 1, (Object) null);
        ClassLoader classLoader = ResourceFileSystem.class.getClassLoader();
        Intrinsics.checkNotNullExpressionValue(classLoader, "ResourceFileSystem::class.java.classLoader");
        RESOURCES = new ResourceFileSystem(classLoader, false);
    }
}
