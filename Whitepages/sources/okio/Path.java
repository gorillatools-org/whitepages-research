package okio;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Path implements Comparable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DIRECTORY_SEPARATOR;
    private final ByteString bytes;

    public Path(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        this.bytes = byteString;
    }

    public final ByteString getBytes$okio() {
        return this.bytes;
    }

    public final Path getRoot() {
        int access$rootLength = okio.internal.Path.rootLength(this);
        if (access$rootLength == -1) {
            return null;
        }
        return new Path(getBytes$okio().substring(0, access$rootLength));
    }

    public final List getSegmentsBytes() {
        ArrayList arrayList = new ArrayList();
        int access$rootLength = okio.internal.Path.rootLength(this);
        if (access$rootLength == -1) {
            access$rootLength = 0;
        } else if (access$rootLength < getBytes$okio().size() && getBytes$okio().getByte(access$rootLength) == 92) {
            access$rootLength++;
        }
        int size = getBytes$okio().size();
        int i = access$rootLength;
        while (access$rootLength < size) {
            if (getBytes$okio().getByte(access$rootLength) == 47 || getBytes$okio().getByte(access$rootLength) == 92) {
                arrayList.add(getBytes$okio().substring(i, access$rootLength));
                i = access$rootLength + 1;
            }
            access$rootLength++;
        }
        if (i < getBytes$okio().size()) {
            arrayList.add(getBytes$okio().substring(i, getBytes$okio().size()));
        }
        return arrayList;
    }

    public final Path resolve(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "child");
        return okio.internal.Path.commonResolve(this, path, z);
    }

    public final File toFile() {
        return new File(toString());
    }

    public final java.nio.file.Path toNioPath() {
        java.nio.file.Path path = Paths.get(toString(), new String[0]);
        Intrinsics.checkNotNullExpressionValue(path, "get(toString())");
        return path;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ Path get$default(Companion companion, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(str, z);
        }

        public final Path get(String str, boolean z) {
            Intrinsics.checkNotNullParameter(str, "<this>");
            return okio.internal.Path.commonToPath(str, z);
        }

        public static /* synthetic */ Path get$default(Companion companion, File file, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(file, z);
        }

        public final Path get(File file, boolean z) {
            Intrinsics.checkNotNullParameter(file, "<this>");
            String file2 = file.toString();
            Intrinsics.checkNotNullExpressionValue(file2, "toString()");
            return get(file2, z);
        }

        public static /* synthetic */ Path get$default(Companion companion, java.nio.file.Path path, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.get(path, z);
        }

        public final Path get(java.nio.file.Path path, boolean z) {
            Intrinsics.checkNotNullParameter(path, "<this>");
            return get(path.toString(), z);
        }
    }

    public final boolean isAbsolute() {
        return okio.internal.Path.rootLength(this) != -1;
    }

    static {
        String str = File.separator;
        Intrinsics.checkNotNullExpressionValue(str, "separator");
        DIRECTORY_SEPARATOR = str;
    }

    public final Character volumeLetter() {
        if (ByteString.indexOf$default(getBytes$okio(), okio.internal.Path.SLASH, 0, 2, (Object) null) != -1 || getBytes$okio().size() < 2 || getBytes$okio().getByte(1) != 58) {
            return null;
        }
        char c = (char) getBytes$okio().getByte(0);
        if (('a' > c || c >= '{') && ('A' > c || c >= '[')) {
            return null;
        }
        return Character.valueOf(c);
    }

    public final ByteString nameBytes() {
        int access$getIndexOfLastSlash = okio.internal.Path.getIndexOfLastSlash(this);
        if (access$getIndexOfLastSlash != -1) {
            return ByteString.substring$default(getBytes$okio(), access$getIndexOfLastSlash + 1, 0, 2, (Object) null);
        }
        if (volumeLetter() == null || getBytes$okio().size() != 2) {
            return getBytes$okio();
        }
        return ByteString.EMPTY;
    }

    public final String name() {
        return nameBytes().utf8();
    }

    public final Path parent() {
        Path path;
        if (Intrinsics.areEqual((Object) getBytes$okio(), (Object) okio.internal.Path.DOT) || Intrinsics.areEqual((Object) getBytes$okio(), (Object) okio.internal.Path.SLASH) || Intrinsics.areEqual((Object) getBytes$okio(), (Object) okio.internal.Path.BACKSLASH) || okio.internal.Path.lastSegmentIsDotDot(this)) {
            return null;
        }
        int access$getIndexOfLastSlash = okio.internal.Path.getIndexOfLastSlash(this);
        if (access$getIndexOfLastSlash != 2 || volumeLetter() == null) {
            if (access$getIndexOfLastSlash == 1 && getBytes$okio().startsWith(okio.internal.Path.BACKSLASH)) {
                return null;
            }
            if (access$getIndexOfLastSlash != -1 || volumeLetter() == null) {
                if (access$getIndexOfLastSlash == -1) {
                    return new Path(okio.internal.Path.DOT);
                }
                if (access$getIndexOfLastSlash != 0) {
                    return new Path(ByteString.substring$default(getBytes$okio(), 0, access$getIndexOfLastSlash, 1, (Object) null));
                }
                path = new Path(ByteString.substring$default(getBytes$okio(), 0, 1, 1, (Object) null));
            } else if (getBytes$okio().size() == 2) {
                return null;
            } else {
                path = new Path(ByteString.substring$default(getBytes$okio(), 0, 2, 1, (Object) null));
            }
        } else if (getBytes$okio().size() == 3) {
            return null;
        } else {
            path = new Path(ByteString.substring$default(getBytes$okio(), 0, 3, 1, (Object) null));
        }
        return path;
    }

    public final Path resolve(String str) {
        Intrinsics.checkNotNullParameter(str, "child");
        return okio.internal.Path.commonResolve(this, okio.internal.Path.toPath(new Buffer().writeUtf8(str), false), false);
    }

    public final Path relativeTo(Path path) {
        Intrinsics.checkNotNullParameter(path, "other");
        if (Intrinsics.areEqual((Object) getRoot(), (Object) path.getRoot())) {
            List segmentsBytes = getSegmentsBytes();
            List segmentsBytes2 = path.getSegmentsBytes();
            int min = Math.min(segmentsBytes.size(), segmentsBytes2.size());
            int i = 0;
            while (i < min && Intrinsics.areEqual(segmentsBytes.get(i), segmentsBytes2.get(i))) {
                i++;
            }
            if (i == min && getBytes$okio().size() == path.getBytes$okio().size()) {
                return Companion.get$default(Companion, ".", false, 1, (Object) null);
            }
            if (segmentsBytes2.subList(i, segmentsBytes2.size()).indexOf(okio.internal.Path.DOT_DOT) == -1) {
                Buffer buffer = new Buffer();
                ByteString access$getSlash = okio.internal.Path.getSlash(path);
                if (access$getSlash == null && (access$getSlash = okio.internal.Path.getSlash(this)) == null) {
                    access$getSlash = okio.internal.Path.toSlash(DIRECTORY_SEPARATOR);
                }
                int size = segmentsBytes2.size();
                for (int i2 = i; i2 < size; i2++) {
                    buffer.write(okio.internal.Path.DOT_DOT);
                    buffer.write(access$getSlash);
                }
                int size2 = segmentsBytes.size();
                while (i < size2) {
                    buffer.write((ByteString) segmentsBytes.get(i));
                    buffer.write(access$getSlash);
                    i++;
                }
                return okio.internal.Path.toPath(buffer, false);
            }
            throw new IllegalArgumentException(("Impossible relative path to resolve: " + this + " and " + path).toString());
        }
        throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + this + " and " + path).toString());
    }

    public final Path normalized() {
        return Companion.get(toString(), true);
    }

    public int compareTo(Path path) {
        Intrinsics.checkNotNullParameter(path, "other");
        return getBytes$okio().compareTo(path.getBytes$okio());
    }

    public boolean equals(Object obj) {
        return (obj instanceof Path) && Intrinsics.areEqual((Object) ((Path) obj).getBytes$okio(), (Object) getBytes$okio());
    }

    public int hashCode() {
        return getBytes$okio().hashCode();
    }

    public String toString() {
        return getBytes$okio().utf8();
    }
}
