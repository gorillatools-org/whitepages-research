package com.google.zxing.oned;

import com.facebook.react.views.image.ReactImageView;
import okhttp3.internal.http.StatusLine;

public abstract class Code93Reader extends OneDReader {
    private static final char[] ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
    private static final int ASTERISK_ENCODING;
    static final int[] CHARACTER_ENCODINGS;

    static {
        int[] iArr = {276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, 420, 418, 404, 402, 394, 360, 356, 354, StatusLine.HTTP_PERM_REDIRECT, 282, 344, 332, 326, ReactImageView.REMOTE_IMAGE_FADE_DURATION_MS, 278, 436, 434, 428, 422, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        CHARACTER_ENCODINGS = iArr;
        ASTERISK_ENCODING = iArr[47];
    }
}
