package okhttp3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;

public final class FormBody extends RequestBody {
    private static final MediaType CONTENT_TYPE = MediaType.Companion.get("application/x-www-form-urlencoded");
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<String> encodedNames;
    private final List<String> encodedValues;

    public FormBody(List<String> list, List<String> list2) {
        Intrinsics.checkNotNullParameter(list, "encodedNames");
        Intrinsics.checkNotNullParameter(list2, "encodedValues");
        this.encodedNames = Util.toImmutableList(list);
        this.encodedValues = Util.toImmutableList(list2);
    }

    public final int size() {
        return this.encodedNames.size();
    }

    /* renamed from: -deprecated_size  reason: not valid java name */
    public final int m972deprecated_size() {
        return size();
    }

    public final String encodedName(int i) {
        return this.encodedNames.get(i);
    }

    public final String name(int i) {
        return HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, encodedName(i), 0, 0, true, 3, (Object) null);
    }

    public final String encodedValue(int i) {
        return this.encodedValues.get(i);
    }

    public final String value(int i) {
        return HttpUrl.Companion.percentDecode$okhttp$default(HttpUrl.Companion, encodedValue(i), 0, 0, true, 3, (Object) null);
    }

    public MediaType contentType() {
        return CONTENT_TYPE;
    }

    public long contentLength() {
        return writeOrCountBytes((BufferedSink) null, true);
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
        writeOrCountBytes(bufferedSink, false);
    }

    private final long writeOrCountBytes(BufferedSink bufferedSink, boolean z) {
        Buffer buffer;
        if (z) {
            buffer = new Buffer();
        } else {
            Intrinsics.checkNotNull(bufferedSink);
            buffer = bufferedSink.getBuffer();
        }
        int size = this.encodedNames.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                buffer.writeByte(38);
            }
            buffer.writeUtf8(this.encodedNames.get(i));
            buffer.writeByte(61);
            buffer.writeUtf8(this.encodedValues.get(i));
        }
        if (!z) {
            return 0;
        }
        long size2 = buffer.size();
        buffer.clear();
        return size2;
    }

    public static final class Builder {
        private final Charset charset;
        private final List<String> names;
        private final List<String> values;

        public Builder() {
            this((Charset) null, 1, (DefaultConstructorMarker) null);
        }

        public Builder(Charset charset2) {
            this.charset = charset2;
            this.names = new ArrayList();
            this.values = new ArrayList();
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(Charset charset2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : charset2);
        }

        public final Builder add(String str, String str2) {
            String str3 = str;
            Intrinsics.checkNotNullParameter(str3, "name");
            Intrinsics.checkNotNullParameter(str2, "value");
            HttpUrl.Companion companion = HttpUrl.Companion;
            this.names.add(HttpUrl.Companion.canonicalize$okhttp$default(companion, str3, 0, 0, HttpUrl.FORM_ENCODE_SET, false, false, true, false, this.charset, 91, (Object) null));
            this.values.add(HttpUrl.Companion.canonicalize$okhttp$default(companion, str2, 0, 0, HttpUrl.FORM_ENCODE_SET, false, false, true, false, this.charset, 91, (Object) null));
            return this;
        }

        public final Builder addEncoded(String str, String str2) {
            String str3 = str;
            Intrinsics.checkNotNullParameter(str3, "name");
            Intrinsics.checkNotNullParameter(str2, "value");
            HttpUrl.Companion companion = HttpUrl.Companion;
            this.names.add(HttpUrl.Companion.canonicalize$okhttp$default(companion, str3, 0, 0, HttpUrl.FORM_ENCODE_SET, true, false, true, false, this.charset, 83, (Object) null));
            this.values.add(HttpUrl.Companion.canonicalize$okhttp$default(companion, str2, 0, 0, HttpUrl.FORM_ENCODE_SET, true, false, true, false, this.charset, 83, (Object) null));
            return this;
        }

        public final FormBody build() {
            return new FormBody(this.names, this.values);
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
