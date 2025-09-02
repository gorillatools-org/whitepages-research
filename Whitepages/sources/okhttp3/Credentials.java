package okhttp3;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;

public final class Credentials {
    public static final Credentials INSTANCE = new Credentials();

    public static final String basic(String str, String str2) {
        return basic$default(str, str2, (Charset) null, 4, (Object) null);
    }

    private Credentials() {
    }

    public static /* synthetic */ String basic$default(String str, String str2, Charset charset, int i, Object obj) {
        if ((i & 4) != 0) {
            charset = StandardCharsets.ISO_8859_1;
            Intrinsics.checkNotNullExpressionValue(charset, "ISO_8859_1");
        }
        return basic(str, str2, charset);
    }

    public static final String basic(String str, String str2, Charset charset) {
        Intrinsics.checkNotNullParameter(str, "username");
        Intrinsics.checkNotNullParameter(str2, "password");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String base64 = ByteString.Companion.encodeString(str + ':' + str2, charset).base64();
        return "Basic " + base64;
    }
}
