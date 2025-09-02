package okio.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;

public final class ZipEntry {
    private final Path canonicalPath;
    private final List children;
    private final String comment;
    private final long compressedSize;
    private final int compressionMethod;
    private final long crc;
    private final boolean isDirectory;
    private final Long lastModifiedAtMillis;
    private final long offset;
    private final long size;

    public ZipEntry(Path path, boolean z, String str, long j, long j2, long j3, int i, Long l, long j4) {
        Intrinsics.checkNotNullParameter(path, "canonicalPath");
        Intrinsics.checkNotNullParameter(str, "comment");
        this.canonicalPath = path;
        this.isDirectory = z;
        this.comment = str;
        this.crc = j;
        this.compressedSize = j2;
        this.size = j3;
        this.compressionMethod = i;
        this.lastModifiedAtMillis = l;
        this.offset = j4;
        this.children = new ArrayList();
    }

    public final Path getCanonicalPath() {
        return this.canonicalPath;
    }

    public final boolean isDirectory() {
        return this.isDirectory;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ZipEntry(okio.Path r14, boolean r15, java.lang.String r16, long r17, long r19, long r21, int r23, java.lang.Long r24, long r25, int r27, kotlin.jvm.internal.DefaultConstructorMarker r28) {
        /*
            r13 = this;
            r0 = r27
            r1 = r0 & 2
            if (r1 == 0) goto L_0x0008
            r1 = 0
            goto L_0x0009
        L_0x0008:
            r1 = r15
        L_0x0009:
            r2 = r0 & 4
            if (r2 == 0) goto L_0x0010
            java.lang.String r2 = ""
            goto L_0x0012
        L_0x0010:
            r2 = r16
        L_0x0012:
            r3 = r0 & 8
            r4 = -1
            if (r3 == 0) goto L_0x001a
            r6 = r4
            goto L_0x001c
        L_0x001a:
            r6 = r17
        L_0x001c:
            r3 = r0 & 16
            if (r3 == 0) goto L_0x0022
            r8 = r4
            goto L_0x0024
        L_0x0022:
            r8 = r19
        L_0x0024:
            r3 = r0 & 32
            if (r3 == 0) goto L_0x002a
            r10 = r4
            goto L_0x002c
        L_0x002a:
            r10 = r21
        L_0x002c:
            r3 = r0 & 64
            if (r3 == 0) goto L_0x0032
            r3 = -1
            goto L_0x0034
        L_0x0032:
            r3 = r23
        L_0x0034:
            r12 = r0 & 128(0x80, float:1.794E-43)
            if (r12 == 0) goto L_0x003a
            r12 = 0
            goto L_0x003c
        L_0x003a:
            r12 = r24
        L_0x003c:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x0041
            goto L_0x0043
        L_0x0041:
            r4 = r25
        L_0x0043:
            r15 = r13
            r16 = r14
            r17 = r1
            r18 = r2
            r19 = r6
            r21 = r8
            r23 = r10
            r25 = r3
            r26 = r12
            r27 = r4
            r15.<init>(r16, r17, r18, r19, r21, r23, r25, r26, r27)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ZipEntry.<init>(okio.Path, boolean, java.lang.String, long, long, long, int, java.lang.Long, long, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final long getCompressedSize() {
        return this.compressedSize;
    }

    public final long getSize() {
        return this.size;
    }

    public final int getCompressionMethod() {
        return this.compressionMethod;
    }

    public final Long getLastModifiedAtMillis() {
        return this.lastModifiedAtMillis;
    }

    public final long getOffset() {
        return this.offset;
    }

    public final List getChildren() {
        return this.children;
    }
}
