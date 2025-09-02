package okhttp3;

import java.io.File;

public final class RequestBody$Companion$asRequestBody$1 extends RequestBody {
    final /* synthetic */ MediaType $contentType;
    final /* synthetic */ File $this_asRequestBody;

    RequestBody$Companion$asRequestBody$1(File file, MediaType mediaType) {
        this.$this_asRequestBody = file;
        this.$contentType = mediaType;
    }

    public MediaType contentType() {
        return this.$contentType;
    }

    public long contentLength() {
        return this.$this_asRequestBody.length();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeTo(okio.BufferedSink r3) {
        /*
            r2 = this;
            java.lang.String r0 = "sink"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.io.File r0 = r2.$this_asRequestBody
            okio.Source r0 = okio.Okio.source((java.io.File) r0)
            r3.writeAll(r0)     // Catch:{ all -> 0x0013 }
            r3 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r3)
            return
        L_0x0013:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0015 }
        L_0x0015:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.RequestBody$Companion$asRequestBody$1.writeTo(okio.BufferedSink):void");
    }
}
