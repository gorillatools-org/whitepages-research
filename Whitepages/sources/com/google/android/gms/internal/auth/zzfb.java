package com.google.android.gms.internal.auth;

import java.io.IOException;

public final class zzfb extends IOException {
    private zzfx zza = null;

    public zzfb(IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    static zzfb zza() {
        return new zzfb("Protocol message contained an invalid tag (zero).");
    }

    static zzfb zzb() {
        return new zzfb("Protocol message had invalid UTF-8.");
    }

    static zzfb zzc() {
        return new zzfb("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzfb zzd() {
        return new zzfb("Failed to parse the message.");
    }

    static zzfb zzf() {
        return new zzfb("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzfb zze(zzfx zzfx) {
        this.zza = zzfx;
        return this;
    }

    public zzfb(String str) {
        super(str);
    }
}
