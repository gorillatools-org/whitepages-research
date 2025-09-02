package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Locale;

public final class zzli extends IOException {
    zzli() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    zzli(long j, long j2, int i, Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.format(Locale.US, "Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i)})), th);
    }

    zzli(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
