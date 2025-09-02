package okhttp3.internal.ws;

import okio.ByteString;

public final class MessageDeflaterKt {
    /* access modifiers changed from: private */
    public static final ByteString EMPTY_DEFLATE_BLOCK = ByteString.Companion.decodeHex("000000ffff");
    private static final int LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION = 4;
}
