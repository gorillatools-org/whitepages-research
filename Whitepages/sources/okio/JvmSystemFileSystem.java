package okio;

import com.salesforce.marketingcloud.config.a;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class JvmSystemFileSystem extends FileSystem {
    public FileMetadata metadataOrNull(Path path) {
        Intrinsics.checkNotNullParameter(path, a.j);
        File file = path.toFile();
        boolean isFile = file.isFile();
        boolean isDirectory = file.isDirectory();
        long lastModified = file.lastModified();
        long length = file.length();
        if (!isFile && !isDirectory && lastModified == 0 && length == 0 && !file.exists()) {
            return null;
        }
        return new FileMetadata(isFile, isDirectory, (Path) null, Long.valueOf(length), (Long) null, Long.valueOf(lastModified), (Long) null, (Map) null, 128, (DefaultConstructorMarker) null);
    }

    public FileHandle openReadOnly(Path path) {
        Intrinsics.checkNotNullParameter(path, "file");
        return new JvmFileHandle(false, new RandomAccessFile(path.toFile(), "r"));
    }

    public FileHandle openReadWrite(Path path, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(path, "file");
        if (!z || !z2) {
            if (z) {
                requireCreate(path);
            }
            if (z2) {
                requireExist(path);
            }
            return new JvmFileHandle(true, new RandomAccessFile(path.toFile(), "rw"));
        }
        throw new IllegalArgumentException("Cannot require mustCreate and mustExist at the same time.");
    }

    public Source source(Path path) {
        Intrinsics.checkNotNullParameter(path, "file");
        return Okio.source(path.toFile());
    }

    public void createDirectory(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "dir");
        if (!path.toFile().mkdir()) {
            FileMetadata metadataOrNull = metadataOrNull(path);
            if (metadataOrNull == null || !metadataOrNull.isDirectory()) {
                throw new IOException("failed to create directory: " + path);
            } else if (z) {
                throw new IOException(path + " already exist.");
            }
        }
    }

    public void atomicMove(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "source");
        Intrinsics.checkNotNullParameter(path2, "target");
        if (!path.toFile().renameTo(path2.toFile())) {
            throw new IOException("failed to move " + path + " to " + path2);
        }
    }

    public void delete(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, a.j);
        if (!Thread.interrupted()) {
            File file = path.toFile();
            if (file.delete()) {
                return;
            }
            if (file.exists()) {
                throw new IOException("failed to delete " + path);
            } else if (z) {
                throw new FileNotFoundException("no such file: " + path);
            }
        } else {
            throw new InterruptedIOException("interrupted");
        }
    }

    public String toString() {
        return "JvmSystemFileSystem";
    }

    private final void requireExist(Path path) {
        if (!exists(path)) {
            throw new IOException(path + " doesn't exist.");
        }
    }

    private final void requireCreate(Path path) {
        if (exists(path)) {
            throw new IOException(path + " already exists.");
        }
    }
}
