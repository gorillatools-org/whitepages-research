package okhttp3;

import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;

public final class RequestBody$Companion$toRequestBody$2 extends RequestBody {
    final /* synthetic */ int $byteCount;
    final /* synthetic */ MediaType $contentType;
    final /* synthetic */ int $offset;
    final /* synthetic */ byte[] $this_toRequestBody;

    RequestBody$Companion$toRequestBody$2(byte[] bArr, MediaType mediaType, int i, int i2) {
        this.$this_toRequestBody = bArr;
        this.$contentType = mediaType;
        this.$byteCount = i;
        this.$offset = i2;
    }

    public MediaType contentType() {
        return this.$contentType;
    }

    public long contentLength() {
        return (long) this.$byteCount;
    }

    public void writeTo(BufferedSink bufferedSink) {
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
        bufferedSink.write(this.$this_toRequestBody, this.$offset, this.$byteCount);
    }
}
