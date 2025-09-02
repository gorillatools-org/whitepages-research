package okio;

import com.salesforce.marketingcloud.config.a;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;

public class NioSystemFileSystem extends JvmSystemFileSystem {
    public FileMetadata metadataOrNull(Path path) {
        Intrinsics.checkNotNullParameter(path, a.j);
        return metadataOrNull(path.toNioPath());
    }

    /* access modifiers changed from: protected */
    public final FileMetadata metadataOrNull(Path path) {
        Intrinsics.checkNotNullParameter(path, "nioPath");
        Long l = null;
        try {
            BasicFileAttributes readAttributes = Files.readAttributes(path, BasicFileAttributes.class, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
            Path readSymbolicLink = readAttributes.isSymbolicLink() ? Files.readSymbolicLink(path) : null;
            boolean isRegularFile = readAttributes.isRegularFile();
            boolean isDirectory = readAttributes.isDirectory();
            Path path2 = readSymbolicLink != null ? Path.Companion.get$default(Path.Companion, readSymbolicLink, false, 1, (Object) null) : null;
            Long valueOf = Long.valueOf(readAttributes.size());
            FileTime creationTime = readAttributes.creationTime();
            Long zeroToNull = creationTime != null ? zeroToNull(creationTime) : null;
            FileTime lastModifiedTime = readAttributes.lastModifiedTime();
            Long zeroToNull2 = lastModifiedTime != null ? zeroToNull(lastModifiedTime) : null;
            FileTime lastAccessTime = readAttributes.lastAccessTime();
            if (lastAccessTime != null) {
                l = zeroToNull(lastAccessTime);
            }
            return new FileMetadata(isRegularFile, isDirectory, path2, valueOf, zeroToNull, zeroToNull2, l, (Map) null, 128, (DefaultConstructorMarker) null);
        } catch (FileSystemException | NoSuchFileException unused) {
            return null;
        }
    }

    private final Long zeroToNull(FileTime fileTime) {
        Long valueOf = Long.valueOf(fileTime.toMillis());
        if (valueOf.longValue() != 0) {
            return valueOf;
        }
        return null;
    }

    public void atomicMove(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "source");
        Intrinsics.checkNotNullParameter(path2, "target");
        try {
            Files.move(path.toNioPath(), path2.toNioPath(), new CopyOption[]{StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING});
        } catch (NoSuchFileException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (UnsupportedOperationException unused) {
            throw new IOException("atomic move not supported");
        }
    }

    public String toString() {
        return "NioSystemFileSystem";
    }
}
