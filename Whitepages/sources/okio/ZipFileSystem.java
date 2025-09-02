package okio;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.salesforce.marketingcloud.config.a;
import java.io.IOException;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;

public final class ZipFileSystem extends FileSystem {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Path ROOT = Path.Companion.get$default(Path.Companion, RemoteSettings.FORWARD_SLASH_STRING, false, 1, (Object) null);
    private final String comment;
    private final Map entries;
    private final FileSystem fileSystem;
    private final Path zipPath;

    public ZipFileSystem(Path path, FileSystem fileSystem2, Map map, String str) {
        Intrinsics.checkNotNullParameter(path, "zipPath");
        Intrinsics.checkNotNullParameter(fileSystem2, "fileSystem");
        Intrinsics.checkNotNullParameter(map, RemoteConfigConstants.ResponseFieldKey.ENTRIES);
        this.zipPath = path;
        this.fileSystem = fileSystem2;
        this.entries = map;
        this.comment = str;
    }

    private final Path canonicalizeInternal(Path path) {
        return ROOT.resolve(path, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0080  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okio.FileMetadata metadataOrNull(okio.Path r15) {
        /*
            r14 = this;
            java.lang.String r0 = "path"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            okio.Path r15 = r14.canonicalizeInternal(r15)
            java.util.Map r0 = r14.entries
            java.lang.Object r15 = r0.get(r15)
            okio.internal.ZipEntry r15 = (okio.internal.ZipEntry) r15
            r0 = 0
            if (r15 != 0) goto L_0x0015
            return r0
        L_0x0015:
            okio.FileMetadata r12 = new okio.FileMetadata
            boolean r1 = r15.isDirectory()
            r2 = r1 ^ 1
            boolean r3 = r15.isDirectory()
            boolean r1 = r15.isDirectory()
            if (r1 == 0) goto L_0x0029
            r5 = r0
            goto L_0x0032
        L_0x0029:
            long r4 = r15.getSize()
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            r5 = r1
        L_0x0032:
            java.lang.Long r7 = r15.getLastModifiedAtMillis()
            r10 = 128(0x80, float:1.794E-43)
            r11 = 0
            r4 = 0
            r6 = 0
            r8 = 0
            r9 = 0
            r1 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            long r1 = r15.getOffset()
            r3 = -1
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x004c
            return r12
        L_0x004c:
            okio.FileSystem r1 = r14.fileSystem
            okio.Path r2 = r14.zipPath
            okio.FileHandle r1 = r1.openReadOnly(r2)
            long r2 = r15.getOffset()     // Catch:{ all -> 0x0068 }
            okio.Source r15 = r1.source(r2)     // Catch:{ all -> 0x0068 }
            okio.BufferedSource r15 = okio.Okio.buffer((okio.Source) r15)     // Catch:{ all -> 0x0068 }
            if (r1 == 0) goto L_0x0076
            r1.close()     // Catch:{ all -> 0x0066 }
            goto L_0x0076
        L_0x0066:
            r0 = move-exception
            goto L_0x0076
        L_0x0068:
            r15 = move-exception
            if (r1 == 0) goto L_0x0073
            r1.close()     // Catch:{ all -> 0x006f }
            goto L_0x0073
        L_0x006f:
            r1 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r15, r1)
        L_0x0073:
            r13 = r0
            r0 = r15
            r15 = r13
        L_0x0076:
            if (r0 != 0) goto L_0x0080
            kotlin.jvm.internal.Intrinsics.checkNotNull(r15)
            okio.FileMetadata r15 = okio.internal.ZipFilesKt.readLocalHeader(r15, r12)
            return r15
        L_0x0080:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ZipFileSystem.metadataOrNull(okio.Path):okio.FileMetadata");
    }

    public FileHandle openReadOnly(Path path) {
        Intrinsics.checkNotNullParameter(path, "file");
        throw new UnsupportedOperationException("not implemented yet!");
    }

    public FileHandle openReadWrite(Path path, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(path, "file");
        throw new IOException("zip entries are not writable");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0076  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okio.Source source(okio.Path r8) {
        /*
            r7 = this;
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            okio.Path r0 = r7.canonicalizeInternal(r8)
            java.util.Map r1 = r7.entries
            java.lang.Object r0 = r1.get(r0)
            okio.internal.ZipEntry r0 = (okio.internal.ZipEntry) r0
            if (r0 == 0) goto L_0x0077
            okio.FileSystem r8 = r7.fileSystem
            okio.Path r1 = r7.zipPath
            okio.FileHandle r8 = r8.openReadOnly(r1)
            r1 = 0
            long r2 = r0.getOffset()     // Catch:{ all -> 0x0030 }
            okio.Source r2 = r8.source(r2)     // Catch:{ all -> 0x0030 }
            okio.BufferedSource r2 = okio.Okio.buffer((okio.Source) r2)     // Catch:{ all -> 0x0030 }
            if (r8 == 0) goto L_0x003e
            r8.close()     // Catch:{ all -> 0x002e }
            goto L_0x003e
        L_0x002e:
            r1 = move-exception
            goto L_0x003e
        L_0x0030:
            r2 = move-exception
            if (r8 == 0) goto L_0x003b
            r8.close()     // Catch:{ all -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r8 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r2, r8)
        L_0x003b:
            r6 = r2
            r2 = r1
            r1 = r6
        L_0x003e:
            if (r1 != 0) goto L_0x0076
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            okio.internal.ZipFilesKt.skipLocalHeader(r2)
            int r8 = r0.getCompressionMethod()
            r1 = 1
            if (r8 != 0) goto L_0x0057
            okio.internal.FixedLengthSource r8 = new okio.internal.FixedLengthSource
            long r3 = r0.getSize()
            r8.<init>(r2, r3, r1)
            goto L_0x0075
        L_0x0057:
            okio.InflaterSource r8 = new okio.InflaterSource
            okio.internal.FixedLengthSource r3 = new okio.internal.FixedLengthSource
            long r4 = r0.getCompressedSize()
            r3.<init>(r2, r4, r1)
            java.util.zip.Inflater r2 = new java.util.zip.Inflater
            r2.<init>(r1)
            r8.<init>((okio.Source) r3, (java.util.zip.Inflater) r2)
            okio.internal.FixedLengthSource r1 = new okio.internal.FixedLengthSource
            long r2 = r0.getSize()
            r0 = 0
            r1.<init>(r8, r2, r0)
            r8 = r1
        L_0x0075:
            return r8
        L_0x0076:
            throw r1
        L_0x0077:
            java.io.FileNotFoundException r0 = new java.io.FileNotFoundException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "no such file: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.<init>(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ZipFileSystem.source(okio.Path):okio.Source");
    }

    public void createDirectory(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "dir");
        throw new IOException("zip file systems are read-only");
    }

    public void atomicMove(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "source");
        Intrinsics.checkNotNullParameter(path2, "target");
        throw new IOException("zip file systems are read-only");
    }

    public void delete(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, a.j);
        throw new IOException("zip file systems are read-only");
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
