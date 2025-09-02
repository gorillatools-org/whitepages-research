package okhttp3;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.MediaType;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;

public abstract class RequestBody {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public static final RequestBody create(File file, MediaType mediaType) {
        return Companion.create(file, mediaType);
    }

    public static final RequestBody create(String str, MediaType mediaType) {
        return Companion.create(str, mediaType);
    }

    public static final RequestBody create(MediaType mediaType, File file) {
        return Companion.create(mediaType, file);
    }

    public static final RequestBody create(MediaType mediaType, String str) {
        return Companion.create(mediaType, str);
    }

    public static final RequestBody create(MediaType mediaType, ByteString byteString) {
        return Companion.create(mediaType, byteString);
    }

    public static final RequestBody create(MediaType mediaType, byte[] bArr) {
        return Companion.create$default(Companion, mediaType, bArr, 0, 0, 12, (Object) null);
    }

    public static final RequestBody create(MediaType mediaType, byte[] bArr, int i) {
        return Companion.create$default(Companion, mediaType, bArr, i, 0, 8, (Object) null);
    }

    public static final RequestBody create(MediaType mediaType, byte[] bArr, int i, int i2) {
        return Companion.create(mediaType, bArr, i, i2);
    }

    public static final RequestBody create(ByteString byteString, MediaType mediaType) {
        return Companion.create(byteString, mediaType);
    }

    public static final RequestBody create(byte[] bArr) {
        return Companion.create$default(Companion, bArr, (MediaType) null, 0, 0, 7, (Object) null);
    }

    public static final RequestBody create(byte[] bArr, MediaType mediaType) {
        return Companion.create$default(Companion, bArr, mediaType, 0, 0, 6, (Object) null);
    }

    public static final RequestBody create(byte[] bArr, MediaType mediaType, int i) {
        return Companion.create$default(Companion, bArr, mediaType, i, 0, 4, (Object) null);
    }

    public static final RequestBody create(byte[] bArr, MediaType mediaType, int i, int i2) {
        return Companion.create(bArr, mediaType, i, i2);
    }

    public long contentLength() throws IOException {
        return -1;
    }

    public abstract MediaType contentType();

    public boolean isDuplex() {
        return false;
    }

    public boolean isOneShot() {
        return false;
    }

    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;

    public static final class Companion {
        public final RequestBody create(MediaType mediaType, byte[] bArr) {
            return create$default(this, mediaType, bArr, 0, 0, 12, (Object) null);
        }

        public final RequestBody create(MediaType mediaType, byte[] bArr, int i) {
            return create$default(this, mediaType, bArr, i, 0, 8, (Object) null);
        }

        public final RequestBody create(byte[] bArr) {
            return create$default(this, bArr, (MediaType) null, 0, 0, 7, (Object) null);
        }

        public final RequestBody create(byte[] bArr, MediaType mediaType) {
            return create$default(this, bArr, mediaType, 0, 0, 6, (Object) null);
        }

        public final RequestBody create(byte[] bArr, MediaType mediaType, int i) {
            return create$default(this, bArr, mediaType, i, 0, 4, (Object) null);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, String str, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = null;
            }
            return companion.create(str, mediaType);
        }

        public final RequestBody create(String str, MediaType mediaType) {
            Intrinsics.checkNotNullParameter(str, "$this$toRequestBody");
            Charset charset = Charsets.UTF_8;
            if (mediaType != null) {
                Charset charset$default = MediaType.charset$default(mediaType, (Charset) null, 1, (Object) null);
                if (charset$default == null) {
                    MediaType.Companion companion = MediaType.Companion;
                    mediaType = companion.parse(mediaType + "; charset=utf-8");
                } else {
                    charset = charset$default;
                }
            }
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            return create(bytes, mediaType, 0, bytes.length);
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, ByteString byteString, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = null;
            }
            return companion.create(byteString, mediaType);
        }

        public final RequestBody create(ByteString byteString, MediaType mediaType) {
            Intrinsics.checkNotNullParameter(byteString, "$this$toRequestBody");
            return new RequestBody$Companion$toRequestBody$1(byteString, mediaType);
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, byte[] bArr, MediaType mediaType, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                mediaType = null;
            }
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = bArr.length;
            }
            return companion.create(bArr, mediaType, i, i2);
        }

        public final RequestBody create(byte[] bArr, MediaType mediaType, int i, int i2) {
            Intrinsics.checkNotNullParameter(bArr, "$this$toRequestBody");
            Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
            return new RequestBody$Companion$toRequestBody$2(bArr, mediaType, i2, i);
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, File file, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = null;
            }
            return companion.create(file, mediaType);
        }

        public final RequestBody create(File file, MediaType mediaType) {
            Intrinsics.checkNotNullParameter(file, "$this$asRequestBody");
            return new RequestBody$Companion$asRequestBody$1(file, mediaType);
        }

        public final RequestBody create(MediaType mediaType, String str) {
            Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.CONTENT);
            return create(str, mediaType);
        }

        public final RequestBody create(MediaType mediaType, ByteString byteString) {
            Intrinsics.checkNotNullParameter(byteString, FirebaseAnalytics.Param.CONTENT);
            return create(byteString, mediaType);
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, MediaType mediaType, byte[] bArr, int i, int i2, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                i = 0;
            }
            if ((i3 & 8) != 0) {
                i2 = bArr.length;
            }
            return companion.create(mediaType, bArr, i, i2);
        }

        public final RequestBody create(MediaType mediaType, byte[] bArr, int i, int i2) {
            Intrinsics.checkNotNullParameter(bArr, FirebaseAnalytics.Param.CONTENT);
            return create(bArr, mediaType, i, i2);
        }

        public final RequestBody create(MediaType mediaType, File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            return create(file, mediaType);
        }
    }
}
