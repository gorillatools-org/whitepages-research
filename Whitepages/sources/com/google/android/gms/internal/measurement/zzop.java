package com.google.android.gms.internal.measurement;

public enum zzop {
    DOUBLE(zzoq.DOUBLE, 1),
    FLOAT(zzoq.FLOAT, 5),
    INT64(r12, 0),
    UINT64(r12, 0),
    INT32(r9, 0),
    FIXED64(r12, 1),
    FIXED32(r9, 5),
    BOOL(zzoq.BOOLEAN, 0),
    STRING(zzoq.STRING, 2),
    GROUP(r13, 3),
    MESSAGE(r13, 2),
    BYTES(zzoq.BYTE_STRING, 2),
    UINT32(r15, 0),
    ENUM(zzoq.ENUM, 0),
    SFIXED32(r15, 5),
    SFIXED64(r1, 1),
    SINT32(r3, 0),
    SINT64(r1, 0);
    
    private final zzoq zzt;

    private zzop(zzoq zzoq, int i) {
        this.zzt = zzoq;
    }

    public final zzoq zza() {
        return this.zzt;
    }
}
