package okhttp3;

import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;
import okio.ByteString;

public final class RequestBody$Companion$toRequestBody$1 extends RequestBody {
    final /* synthetic */ MediaType $contentType;
    final /* synthetic */ ByteString $this_toRequestBody;

    RequestBody$Companion$toRequestBody$1(ByteString byteString, MediaType mediaType) {
        this.$this_toRequestBody = byteString;
        this.$contentType = mediaType;
    }

    public MediaType contentType() {
        return this.$contentType;
    }

    public long contentLength() {
        return (long) this.$this_toRequestBody.size();
    }

    public void writeTo(BufferedSink bufferedSink) {
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
        bufferedSink.write(this.$this_toRequestBody);
    }
}
