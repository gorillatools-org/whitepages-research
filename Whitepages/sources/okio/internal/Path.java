package okio.internal;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;

/* renamed from: okio.internal.-Path  reason: invalid class name */
public abstract class Path {
    private static final ByteString ANY_SLASH;
    /* access modifiers changed from: private */
    public static final ByteString BACKSLASH;
    /* access modifiers changed from: private */
    public static final ByteString DOT;
    /* access modifiers changed from: private */
    public static final ByteString DOT_DOT;
    /* access modifiers changed from: private */
    public static final ByteString SLASH;

    static {
        ByteString.Companion companion = ByteString.Companion;
        SLASH = companion.encodeUtf8(RemoteSettings.FORWARD_SLASH_STRING);
        BACKSLASH = companion.encodeUtf8("\\");
        ANY_SLASH = companion.encodeUtf8("/\\");
        DOT = companion.encodeUtf8(".");
        DOT_DOT = companion.encodeUtf8("..");
    }

    /* access modifiers changed from: private */
    public static final int rootLength(okio.Path path) {
        if (path.getBytes$okio().size() == 0) {
            return -1;
        }
        if (path.getBytes$okio().getByte(0) == 47) {
            return 1;
        }
        if (path.getBytes$okio().getByte(0) != 92) {
            if (path.getBytes$okio().size() > 2 && path.getBytes$okio().getByte(1) == 58 && path.getBytes$okio().getByte(2) == 92) {
                char c = (char) path.getBytes$okio().getByte(0);
                if ('a' <= c && c < '{') {
                    return 3;
                }
                if ('A' > c || c >= '[') {
                    return -1;
                }
                return 3;
            }
            return -1;
        } else if (path.getBytes$okio().size() <= 2 || path.getBytes$okio().getByte(1) != 92) {
            return 1;
        } else {
            int indexOf = path.getBytes$okio().indexOf(BACKSLASH, 2);
            return indexOf == -1 ? path.getBytes$okio().size() : indexOf;
        }
    }

    /* access modifiers changed from: private */
    public static final int getIndexOfLastSlash(okio.Path path) {
        int lastIndexOf$default = ByteString.lastIndexOf$default(path.getBytes$okio(), SLASH, 0, 2, (Object) null);
        if (lastIndexOf$default != -1) {
            return lastIndexOf$default;
        }
        return ByteString.lastIndexOf$default(path.getBytes$okio(), BACKSLASH, 0, 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final boolean lastSegmentIsDotDot(okio.Path path) {
        if (!path.getBytes$okio().endsWith(DOT_DOT) || (path.getBytes$okio().size() != 2 && !path.getBytes$okio().rangeEquals(path.getBytes$okio().size() - 3, SLASH, 0, 1) && !path.getBytes$okio().rangeEquals(path.getBytes$okio().size() - 3, BACKSLASH, 0, 1))) {
            return false;
        }
        return true;
    }

    public static final okio.Path commonResolve(okio.Path path, okio.Path path2, boolean z) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(path2, "child");
        if (path2.isAbsolute() || path2.volumeLetter() != null) {
            return path2;
        }
        ByteString slash = getSlash(path);
        if (slash == null && (slash = getSlash(path2)) == null) {
            slash = toSlash(okio.Path.DIRECTORY_SEPARATOR);
        }
        Buffer buffer = new Buffer();
        buffer.write(path.getBytes$okio());
        if (buffer.size() > 0) {
            buffer.write(slash);
        }
        buffer.write(path2.getBytes$okio());
        return toPath(buffer, z);
    }

    /* access modifiers changed from: private */
    public static final ByteString getSlash(okio.Path path) {
        ByteString bytes$okio = path.getBytes$okio();
        ByteString byteString = SLASH;
        if (ByteString.indexOf$default(bytes$okio, byteString, 0, 2, (Object) null) != -1) {
            return byteString;
        }
        ByteString bytes$okio2 = path.getBytes$okio();
        ByteString byteString2 = BACKSLASH;
        if (ByteString.indexOf$default(bytes$okio2, byteString2, 0, 2, (Object) null) != -1) {
            return byteString2;
        }
        return null;
    }

    public static final okio.Path commonToPath(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return toPath(new Buffer().writeUtf8(str), z);
    }

    public static final okio.Path toPath(Buffer buffer, boolean z) {
        ByteString byteString;
        ByteString byteString2;
        Buffer buffer2 = buffer;
        Intrinsics.checkNotNullParameter(buffer2, "<this>");
        Buffer buffer3 = new Buffer();
        ByteString byteString3 = null;
        int i = 0;
        while (true) {
            if (!buffer2.rangeEquals(0, SLASH)) {
                byteString = BACKSLASH;
                if (!buffer2.rangeEquals(0, byteString)) {
                    break;
                }
            }
            byte readByte = buffer.readByte();
            if (byteString3 == null) {
                byteString3 = toSlash(readByte);
            }
            i++;
        }
        boolean z2 = i >= 2 && Intrinsics.areEqual((Object) byteString3, (Object) byteString);
        if (z2) {
            Intrinsics.checkNotNull(byteString3);
            buffer3.write(byteString3);
            buffer3.write(byteString3);
        } else if (i > 0) {
            Intrinsics.checkNotNull(byteString3);
            buffer3.write(byteString3);
        } else {
            long indexOfElement = buffer2.indexOfElement(ANY_SLASH);
            if (byteString3 == null) {
                if (indexOfElement == -1) {
                    byteString3 = toSlash(okio.Path.DIRECTORY_SEPARATOR);
                } else {
                    byteString3 = toSlash(buffer2.getByte(indexOfElement));
                }
            }
            if (startsWithVolumeLetterAndColon(buffer2, byteString3)) {
                if (indexOfElement == 2) {
                    buffer3.write(buffer2, 3);
                } else {
                    buffer3.write(buffer2, 2);
                }
            }
        }
        boolean z3 = buffer3.size() > 0;
        ArrayList arrayList = new ArrayList();
        while (!buffer.exhausted()) {
            long indexOfElement2 = buffer2.indexOfElement(ANY_SLASH);
            if (indexOfElement2 == -1) {
                byteString2 = buffer.readByteString();
            } else {
                byteString2 = buffer2.readByteString(indexOfElement2);
                buffer.readByte();
            }
            ByteString byteString4 = DOT_DOT;
            if (Intrinsics.areEqual((Object) byteString2, (Object) byteString4)) {
                if (!z3 || !arrayList.isEmpty()) {
                    if (!z || (!z3 && (arrayList.isEmpty() || Intrinsics.areEqual(CollectionsKt.last((List) arrayList), (Object) byteString4)))) {
                        arrayList.add(byteString2);
                    } else if (!z2 || arrayList.size() != 1) {
                        CollectionsKt.removeLastOrNull(arrayList);
                    }
                }
            } else if (!Intrinsics.areEqual((Object) byteString2, (Object) DOT) && !Intrinsics.areEqual((Object) byteString2, (Object) ByteString.EMPTY)) {
                arrayList.add(byteString2);
            }
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 > 0) {
                buffer3.write(byteString3);
            }
            buffer3.write((ByteString) arrayList.get(i2));
        }
        if (buffer3.size() == 0) {
            buffer3.write(DOT);
        }
        return new okio.Path(buffer3.readByteString());
    }

    /* access modifiers changed from: private */
    public static final ByteString toSlash(String str) {
        if (Intrinsics.areEqual((Object) str, (Object) RemoteSettings.FORWARD_SLASH_STRING)) {
            return SLASH;
        }
        if (Intrinsics.areEqual((Object) str, (Object) "\\")) {
            return BACKSLASH;
        }
        throw new IllegalArgumentException("not a directory separator: " + str);
    }

    private static final ByteString toSlash(byte b) {
        if (b == 47) {
            return SLASH;
        }
        if (b == 92) {
            return BACKSLASH;
        }
        throw new IllegalArgumentException("not a directory separator: " + b);
    }

    private static final boolean startsWithVolumeLetterAndColon(Buffer buffer, ByteString byteString) {
        if (!Intrinsics.areEqual((Object) byteString, (Object) BACKSLASH) || buffer.size() < 2 || buffer.getByte(1) != 58) {
            return false;
        }
        char c = (char) buffer.getByte(0);
        if (('a' > c || c >= '{') && ('A' > c || c >= '[')) {
            return false;
        }
        return true;
    }
}
