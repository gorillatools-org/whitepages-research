package com.google.android.gms.internal.play_billing;

import java.io.IOException;

public final class zzbw extends IOException {
    zzbw() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    zzbw(String str, Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.valueOf(str)), th);
    }

    zzbw(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
