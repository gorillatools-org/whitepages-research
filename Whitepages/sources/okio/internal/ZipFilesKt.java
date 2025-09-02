package okio.internal;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$LongRef;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.BufferedSource;
import okio.FileMetadata;
import okio.Path;

public abstract class ZipFilesKt {
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r10.close();
        r4 = r4 - ((long) 20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0058, code lost:
        if (r4 <= 0) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005a, code lost:
        r4 = okio.Okio.buffer(r3.source(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0069, code lost:
        if (r4.readIntLe() != 117853008) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006b, code lost:
        r5 = r4.readIntLe();
        r12 = r4.readLongLe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0078, code lost:
        if (r4.readIntLe() != 1) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007a, code lost:
        if (r5 != 0) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x007c, code lost:
        r5 = okio.Okio.buffer(r3.source(r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r10 = r5.readIntLe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008b, code lost:
        if (r10 != 101075792) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008d, code lost:
        r8 = readZip64EocdRecord(r5, r8);
        r10 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0097, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0098, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c3, code lost:
        throw new java.io.IOException("bad zip: expected " + getHex(101075792) + " but was " + getHex(r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c6, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ca, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d2, code lost:
        throw new java.io.IOException("unsupported zip: spanned");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d3, code lost:
        r5 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00de, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e3, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e4, code lost:
        r4 = new java.util.ArrayList();
        r5 = okio.Okio.buffer(r3.source(r8.getCentralDirectoryOffset()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r12 = r8.getEntryCount();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00fb, code lost:
        if (r6 >= r12) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00fd, code lost:
        r10 = readEntry(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x010b, code lost:
        if (r10.getOffset() >= r8.getCentralDirectoryOffset()) goto L_0x0124;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0117, code lost:
        if (((java.lang.Boolean) r2.invoke(r10)).booleanValue() == false) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0119, code lost:
        r4.add(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x011d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x011e, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0120, code lost:
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x012b, code lost:
        throw new java.io.IOException("bad zip: local file header offset >= central directory offset");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x012c, code lost:
        r2 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, (java.lang.Throwable) null);
        r4 = new okio.ZipFileSystem(r0, r1, buildIndex(r4), r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x013a, code lost:
        kotlin.io.CloseableKt.closeFinally(r3, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x013d, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x013f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0140, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0144, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0041, code lost:
        r8 = readEocdRecord(r10);
        r9 = r10.readUtf8((long) r8.getCommentByteCount());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final okio.ZipFileSystem openZip(okio.Path r18, okio.FileSystem r19, kotlin.jvm.functions.Function1 r20) {
        /*
            r0 = r18
            r1 = r19
            r2 = r20
            java.lang.String r3 = "zipPath"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r3)
            java.lang.String r3 = "fileSystem"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.String r3 = "predicate"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            okio.FileHandle r3 = r1.openReadOnly(r0)
            long r4 = r3.size()     // Catch:{ all -> 0x00d9 }
            r6 = 22
            long r6 = (long) r6     // Catch:{ all -> 0x00d9 }
            long r4 = r4 - r6
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 < 0) goto L_0x015f
            r8 = 65536(0x10000, double:3.2379E-319)
            long r8 = r4 - r8
            long r8 = java.lang.Math.max(r8, r6)     // Catch:{ all -> 0x00d9 }
        L_0x0030:
            okio.Source r10 = r3.source(r4)     // Catch:{ all -> 0x00d9 }
            okio.BufferedSource r10 = okio.Okio.buffer((okio.Source) r10)     // Catch:{ all -> 0x00d9 }
            int r11 = r10.readIntLe()     // Catch:{ all -> 0x0145 }
            r12 = 101010256(0x6054b50, float:2.506985E-35)
            if (r11 != r12) goto L_0x0147
            okio.internal.EocdRecord r8 = readEocdRecord(r10)     // Catch:{ all -> 0x0145 }
            int r9 = r8.getCommentByteCount()     // Catch:{ all -> 0x0145 }
            long r11 = (long) r9     // Catch:{ all -> 0x0145 }
            java.lang.String r9 = r10.readUtf8(r11)     // Catch:{ all -> 0x0145 }
            r10.close()     // Catch:{ all -> 0x00d9 }
            r10 = 20
            long r10 = (long) r10     // Catch:{ all -> 0x00d9 }
            long r4 = r4 - r10
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            r11 = 0
            if (r10 <= 0) goto L_0x00e4
            okio.Source r4 = r3.source(r4)     // Catch:{ all -> 0x00d9 }
            okio.BufferedSource r4 = okio.Okio.buffer((okio.Source) r4)     // Catch:{ all -> 0x00d9 }
            int r5 = r4.readIntLe()     // Catch:{ all -> 0x0097 }
            r10 = 117853008(0x7064b50, float:1.0103172E-34)
            if (r5 != r10) goto L_0x00d3
            int r5 = r4.readIntLe()     // Catch:{ all -> 0x0097 }
            long r12 = r4.readLongLe()     // Catch:{ all -> 0x0097 }
            int r10 = r4.readIntLe()     // Catch:{ all -> 0x0097 }
            r14 = 1
            if (r10 != r14) goto L_0x00cb
            if (r5 != 0) goto L_0x00cb
            okio.Source r5 = r3.source(r12)     // Catch:{ all -> 0x0097 }
            okio.BufferedSource r5 = okio.Okio.buffer((okio.Source) r5)     // Catch:{ all -> 0x0097 }
            int r10 = r5.readIntLe()     // Catch:{ all -> 0x009a }
            r12 = 101075792(0x6064b50, float:2.525793E-35)
            if (r10 != r12) goto L_0x009d
            okio.internal.EocdRecord r8 = readZip64EocdRecord(r5, r8)     // Catch:{ all -> 0x009a }
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x009a }
            kotlin.io.CloseableKt.closeFinally(r5, r11)     // Catch:{ all -> 0x0097 }
            goto L_0x00d3
        L_0x0097:
            r0 = move-exception
            r1 = r0
            goto L_0x00dd
        L_0x009a:
            r0 = move-exception
            r1 = r0
            goto L_0x00c4
        L_0x009d:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x009a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x009a }
            r1.<init>()     // Catch:{ all -> 0x009a }
            java.lang.String r2 = "bad zip: expected "
            r1.append(r2)     // Catch:{ all -> 0x009a }
            java.lang.String r2 = getHex(r12)     // Catch:{ all -> 0x009a }
            r1.append(r2)     // Catch:{ all -> 0x009a }
            java.lang.String r2 = " but was "
            r1.append(r2)     // Catch:{ all -> 0x009a }
            java.lang.String r2 = getHex(r10)     // Catch:{ all -> 0x009a }
            r1.append(r2)     // Catch:{ all -> 0x009a }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x009a }
            r0.<init>(r1)     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x00c4:
            throw r1     // Catch:{ all -> 0x00c5 }
        L_0x00c5:
            r0 = move-exception
            r2 = r0
            kotlin.io.CloseableKt.closeFinally(r5, r1)     // Catch:{ all -> 0x0097 }
            throw r2     // Catch:{ all -> 0x0097 }
        L_0x00cb:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0097 }
            java.lang.String r1 = "unsupported zip: spanned"
            r0.<init>(r1)     // Catch:{ all -> 0x0097 }
            throw r0     // Catch:{ all -> 0x0097 }
        L_0x00d3:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0097 }
            kotlin.io.CloseableKt.closeFinally(r4, r11)     // Catch:{ all -> 0x00d9 }
            goto L_0x00e4
        L_0x00d9:
            r0 = move-exception
            r1 = r0
            goto L_0x017a
        L_0x00dd:
            throw r1     // Catch:{ all -> 0x00de }
        L_0x00de:
            r0 = move-exception
            r2 = r0
            kotlin.io.CloseableKt.closeFinally(r4, r1)     // Catch:{ all -> 0x00d9 }
            throw r2     // Catch:{ all -> 0x00d9 }
        L_0x00e4:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x00d9 }
            r4.<init>()     // Catch:{ all -> 0x00d9 }
            long r12 = r8.getCentralDirectoryOffset()     // Catch:{ all -> 0x00d9 }
            okio.Source r5 = r3.source(r12)     // Catch:{ all -> 0x00d9 }
            okio.BufferedSource r5 = okio.Okio.buffer((okio.Source) r5)     // Catch:{ all -> 0x00d9 }
            long r12 = r8.getEntryCount()     // Catch:{ all -> 0x011d }
        L_0x00f9:
            int r10 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r10 >= 0) goto L_0x012c
            okio.internal.ZipEntry r10 = readEntry(r5)     // Catch:{ all -> 0x011d }
            long r14 = r10.getOffset()     // Catch:{ all -> 0x011d }
            long r16 = r8.getCentralDirectoryOffset()     // Catch:{ all -> 0x011d }
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L_0x0124
            java.lang.Object r14 = r2.invoke(r10)     // Catch:{ all -> 0x011d }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ all -> 0x011d }
            boolean r14 = r14.booleanValue()     // Catch:{ all -> 0x011d }
            if (r14 == 0) goto L_0x0120
            r4.add(r10)     // Catch:{ all -> 0x011d }
            goto L_0x0120
        L_0x011d:
            r0 = move-exception
            r1 = r0
            goto L_0x013e
        L_0x0120:
            r14 = 1
            long r6 = r6 + r14
            goto L_0x00f9
        L_0x0124:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x011d }
            java.lang.String r1 = "bad zip: local file header offset >= central directory offset"
            r0.<init>(r1)     // Catch:{ all -> 0x011d }
            throw r0     // Catch:{ all -> 0x011d }
        L_0x012c:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x011d }
            kotlin.io.CloseableKt.closeFinally(r5, r11)     // Catch:{ all -> 0x00d9 }
            java.util.Map r2 = buildIndex(r4)     // Catch:{ all -> 0x00d9 }
            okio.ZipFileSystem r4 = new okio.ZipFileSystem     // Catch:{ all -> 0x00d9 }
            r4.<init>(r0, r1, r2, r9)     // Catch:{ all -> 0x00d9 }
            kotlin.io.CloseableKt.closeFinally(r3, r11)
            return r4
        L_0x013e:
            throw r1     // Catch:{ all -> 0x013f }
        L_0x013f:
            r0 = move-exception
            r2 = r0
            kotlin.io.CloseableKt.closeFinally(r5, r1)     // Catch:{ all -> 0x00d9 }
            throw r2     // Catch:{ all -> 0x00d9 }
        L_0x0145:
            r0 = move-exception
            goto L_0x015b
        L_0x0147:
            r10.close()     // Catch:{ all -> 0x00d9 }
            r10 = -1
            long r4 = r4 + r10
            int r10 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r10 < 0) goto L_0x0153
            goto L_0x0030
        L_0x0153:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x00d9 }
            java.lang.String r1 = "not a zip: end of central directory signature not found"
            r0.<init>(r1)     // Catch:{ all -> 0x00d9 }
            throw r0     // Catch:{ all -> 0x00d9 }
        L_0x015b:
            r10.close()     // Catch:{ all -> 0x00d9 }
            throw r0     // Catch:{ all -> 0x00d9 }
        L_0x015f:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x00d9 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d9 }
            r1.<init>()     // Catch:{ all -> 0x00d9 }
            java.lang.String r2 = "not a zip: size="
            r1.append(r2)     // Catch:{ all -> 0x00d9 }
            long r4 = r3.size()     // Catch:{ all -> 0x00d9 }
            r1.append(r4)     // Catch:{ all -> 0x00d9 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00d9 }
            r0.<init>(r1)     // Catch:{ all -> 0x00d9 }
            throw r0     // Catch:{ all -> 0x00d9 }
        L_0x017a:
            throw r1     // Catch:{ all -> 0x017b }
        L_0x017b:
            r0 = move-exception
            r2 = r0
            kotlin.io.CloseableKt.closeFinally(r3, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ZipFilesKt.openZip(okio.Path, okio.FileSystem, kotlin.jvm.functions.Function1):okio.ZipFileSystem");
    }

    private static final Map buildIndex(List list) {
        Path path = Path.Companion.get$default(Path.Companion, RemoteSettings.FORWARD_SLASH_STRING, false, 1, (Object) null);
        Map mutableMapOf = MapsKt.mutableMapOf(TuplesKt.to(path, new ZipEntry(path, true, (String) null, 0, 0, 0, 0, (Long) null, 0, 508, (DefaultConstructorMarker) null)));
        for (ZipEntry zipEntry : CollectionsKt.sortedWith(list, new ZipFilesKt$buildIndex$$inlined$sortedBy$1())) {
            if (((ZipEntry) mutableMapOf.put(zipEntry.getCanonicalPath(), zipEntry)) == null) {
                while (true) {
                    Path parent = zipEntry.getCanonicalPath().parent();
                    if (parent == null) {
                        break;
                    }
                    ZipEntry zipEntry2 = (ZipEntry) mutableMapOf.get(parent);
                    if (zipEntry2 != null) {
                        zipEntry2.getChildren().add(zipEntry.getCanonicalPath());
                        break;
                    }
                    ZipEntry zipEntry3 = r4;
                    ZipEntry zipEntry4 = new ZipEntry(parent, true, (String) null, 0, 0, 0, 0, (Long) null, 0, 508, (DefaultConstructorMarker) null);
                    ZipEntry zipEntry5 = zipEntry3;
                    mutableMapOf.put(parent, zipEntry5);
                    zipEntry5.getChildren().add(zipEntry.getCanonicalPath());
                    zipEntry = zipEntry5;
                }
            }
        }
        return mutableMapOf;
    }

    public static final ZipEntry readEntry(BufferedSource bufferedSource) {
        BufferedSource bufferedSource2 = bufferedSource;
        Intrinsics.checkNotNullParameter(bufferedSource2, "<this>");
        int readIntLe = bufferedSource.readIntLe();
        if (readIntLe == 33639248) {
            bufferedSource2.skip(4);
            short readShortLe = bufferedSource.readShortLe();
            short s = readShortLe & 65535;
            if ((readShortLe & 1) == 0) {
                short readShortLe2 = bufferedSource.readShortLe() & 65535;
                Long dosDateTimeToEpochMillis = dosDateTimeToEpochMillis(bufferedSource.readShortLe() & 65535, bufferedSource.readShortLe() & 65535);
                long readIntLe2 = ((long) bufferedSource.readIntLe()) & 4294967295L;
                Ref$LongRef ref$LongRef = new Ref$LongRef();
                ref$LongRef.element = ((long) bufferedSource.readIntLe()) & 4294967295L;
                Ref$LongRef ref$LongRef2 = new Ref$LongRef();
                ref$LongRef2.element = ((long) bufferedSource.readIntLe()) & 4294967295L;
                short readShortLe3 = bufferedSource.readShortLe() & 65535;
                short readShortLe4 = bufferedSource.readShortLe() & 65535;
                bufferedSource2.skip(8);
                Ref$LongRef ref$LongRef3 = new Ref$LongRef();
                ref$LongRef3.element = ((long) bufferedSource.readIntLe()) & 4294967295L;
                String readUtf8 = bufferedSource2.readUtf8((long) (bufferedSource.readShortLe() & 65535));
                if (!StringsKt.contains$default((CharSequence) readUtf8, 0, false, 2, (Object) null)) {
                    long j = ref$LongRef2.element == 4294967295L ? (long) 8 : 0;
                    long j2 = ref$LongRef.element == 4294967295L ? j + ((long) 8) : j;
                    String str = readUtf8;
                    if (ref$LongRef3.element == 4294967295L) {
                        j2 += (long) 8;
                    }
                    long j3 = j2;
                    Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
                    long j4 = readIntLe2;
                    ZipFilesKt$readEntry$1 zipFilesKt$readEntry$1 = r0;
                    Ref$BooleanRef ref$BooleanRef2 = ref$BooleanRef;
                    String str2 = str;
                    Ref$LongRef ref$LongRef4 = ref$LongRef3;
                    ZipFilesKt$readEntry$1 zipFilesKt$readEntry$12 = new ZipFilesKt$readEntry$1(ref$BooleanRef, j3, ref$LongRef2, bufferedSource, ref$LongRef, ref$LongRef3);
                    readExtra(bufferedSource2, readShortLe3, zipFilesKt$readEntry$1);
                    if (j3 <= 0 || ref$BooleanRef2.element) {
                        String str3 = str2;
                        return new ZipEntry(Path.Companion.get$default(Path.Companion, RemoteSettings.FORWARD_SLASH_STRING, false, 1, (Object) null).resolve(str3), StringsKt.endsWith$default(str3, RemoteSettings.FORWARD_SLASH_STRING, false, 2, (Object) null), bufferedSource2.readUtf8((long) readShortLe4), j4, ref$LongRef.element, ref$LongRef2.element, readShortLe2, dosDateTimeToEpochMillis, ref$LongRef4.element);
                    }
                    throw new IOException("bad zip: zip64 extra required but absent");
                }
                throw new IOException("bad zip: filename contains 0x00");
            }
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(s));
        }
        throw new IOException("bad zip: expected " + getHex(33639248) + " but was " + getHex(readIntLe));
    }

    private static final EocdRecord readEocdRecord(BufferedSource bufferedSource) {
        short readShortLe = bufferedSource.readShortLe() & 65535;
        short readShortLe2 = bufferedSource.readShortLe() & 65535;
        long readShortLe3 = (long) (bufferedSource.readShortLe() & 65535);
        if (readShortLe3 == ((long) (bufferedSource.readShortLe() & 65535)) && readShortLe == 0 && readShortLe2 == 0) {
            bufferedSource.skip(4);
            return new EocdRecord(readShortLe3, 4294967295L & ((long) bufferedSource.readIntLe()), bufferedSource.readShortLe() & 65535);
        }
        throw new IOException("unsupported zip: spanned");
    }

    private static final EocdRecord readZip64EocdRecord(BufferedSource bufferedSource, EocdRecord eocdRecord) {
        bufferedSource.skip(12);
        int readIntLe = bufferedSource.readIntLe();
        int readIntLe2 = bufferedSource.readIntLe();
        long readLongLe = bufferedSource.readLongLe();
        if (readLongLe == bufferedSource.readLongLe() && readIntLe == 0 && readIntLe2 == 0) {
            bufferedSource.skip(8);
            return new EocdRecord(readLongLe, bufferedSource.readLongLe(), eocdRecord.getCommentByteCount());
        }
        throw new IOException("unsupported zip: spanned");
    }

    private static final void readExtra(BufferedSource bufferedSource, int i, Function2 function2) {
        long j = (long) i;
        while (j != 0) {
            if (j >= 4) {
                short readShortLe = bufferedSource.readShortLe() & 65535;
                long readShortLe2 = ((long) bufferedSource.readShortLe()) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
                long j2 = j - ((long) 4);
                if (j2 >= readShortLe2) {
                    bufferedSource.require(readShortLe2);
                    long size = bufferedSource.getBuffer().size();
                    function2.invoke(Integer.valueOf(readShortLe), Long.valueOf(readShortLe2));
                    long size2 = (bufferedSource.getBuffer().size() + readShortLe2) - size;
                    int i2 = (size2 > 0 ? 1 : (size2 == 0 ? 0 : -1));
                    if (i2 >= 0) {
                        if (i2 > 0) {
                            bufferedSource.getBuffer().skip(size2);
                        }
                        j = j2 - readShortLe2;
                    } else {
                        throw new IOException("unsupported zip: too many bytes processed for " + readShortLe);
                    }
                } else {
                    throw new IOException("bad zip: truncated value in extra field");
                }
            } else {
                throw new IOException("bad zip: truncated header in extra field");
            }
        }
    }

    public static final void skipLocalHeader(BufferedSource bufferedSource) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        readOrSkipLocalHeader(bufferedSource, (FileMetadata) null);
    }

    public static final FileMetadata readLocalHeader(BufferedSource bufferedSource, FileMetadata fileMetadata) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        Intrinsics.checkNotNullParameter(fileMetadata, "basicMetadata");
        FileMetadata readOrSkipLocalHeader = readOrSkipLocalHeader(bufferedSource, fileMetadata);
        Intrinsics.checkNotNull(readOrSkipLocalHeader);
        return readOrSkipLocalHeader;
    }

    private static final FileMetadata readOrSkipLocalHeader(BufferedSource bufferedSource, FileMetadata fileMetadata) {
        BufferedSource bufferedSource2 = bufferedSource;
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = fileMetadata != null ? fileMetadata.getLastModifiedAtMillis() : null;
        Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
        Ref$ObjectRef ref$ObjectRef3 = new Ref$ObjectRef();
        int readIntLe = bufferedSource.readIntLe();
        if (readIntLe == 67324752) {
            bufferedSource2.skip(2);
            short readShortLe = bufferedSource.readShortLe();
            short s = readShortLe & 65535;
            if ((readShortLe & 1) == 0) {
                bufferedSource2.skip(18);
                long readShortLe2 = ((long) bufferedSource.readShortLe()) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
                short readShortLe3 = bufferedSource.readShortLe() & 65535;
                bufferedSource2.skip(readShortLe2);
                if (fileMetadata == null) {
                    bufferedSource2.skip((long) readShortLe3);
                    return null;
                }
                readExtra(bufferedSource2, readShortLe3, new ZipFilesKt$readOrSkipLocalHeader$1(bufferedSource2, ref$ObjectRef, ref$ObjectRef2, ref$ObjectRef3));
                return new FileMetadata(fileMetadata.isRegularFile(), fileMetadata.isDirectory(), (Path) null, fileMetadata.getSize(), (Long) ref$ObjectRef3.element, (Long) ref$ObjectRef.element, (Long) ref$ObjectRef2.element, (Map) null, 128, (DefaultConstructorMarker) null);
            }
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(s));
        }
        throw new IOException("bad zip: expected " + getHex(67324752) + " but was " + getHex(readIntLe));
    }

    private static final Long dosDateTimeToEpochMillis(int i, int i2) {
        if (i2 == -1) {
            return null;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(14, 0);
        GregorianCalendar gregorianCalendar2 = gregorianCalendar;
        gregorianCalendar2.set(((i >> 9) & 127) + 1980, ((i >> 5) & 15) - 1, i & 31, (i2 >> 11) & 31, (i2 >> 5) & 63, (i2 & 31) << 1);
        return Long.valueOf(gregorianCalendar.getTime().getTime());
    }

    private static final String getHex(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("0x");
        String num = Integer.toString(i, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
        sb.append(num);
        return sb.toString();
    }
}
