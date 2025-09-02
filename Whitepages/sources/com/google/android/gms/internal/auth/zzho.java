package com.google.android.gms.internal.auth;

public enum zzho {
    DOUBLE(zzhp.DOUBLE, 1),
    FLOAT(zzhp.FLOAT, 5),
    INT64(r12, 0),
    UINT64(r12, 0),
    INT32(r9, 0),
    FIXED64(r12, 1),
    FIXED32(r9, 5),
    BOOL(zzhp.BOOLEAN, 0),
    STRING(zzhp.STRING, 2),
    GROUP(r13, 3),
    MESSAGE(r13, 2),
    BYTES(zzhp.BYTE_STRING, 2),
    UINT32(r15, 0),
    ENUM(zzhp.ENUM, 0),
    SFIXED32(r15, 5),
    SFIXED64(r1, 1),
    SINT32(r3, 0),
    SINT64(r1, 0);
    
    private final zzhp zzt;

    private zzho(zzhp zzhp, int i) {
        this.zzt = zzhp;
    }

    public final zzhp zza() {
        return this.zzt;
    }
}
