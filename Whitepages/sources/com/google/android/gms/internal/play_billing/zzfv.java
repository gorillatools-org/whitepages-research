package com.google.android.gms.internal.play_billing;

public enum zzfv {
    DOUBLE(zzfw.DOUBLE, 1),
    FLOAT(zzfw.FLOAT, 5),
    INT64(r12, 0),
    UINT64(r12, 0),
    INT32(r9, 0),
    FIXED64(r12, 1),
    FIXED32(r9, 5),
    BOOL(zzfw.BOOLEAN, 0),
    STRING(zzfw.STRING, 2),
    GROUP(r13, 3),
    MESSAGE(r13, 2),
    BYTES(zzfw.BYTE_STRING, 2),
    UINT32(r15, 0),
    ENUM(zzfw.ENUM, 0),
    SFIXED32(r15, 5),
    SFIXED64(r1, 1),
    SINT32(r3, 0),
    SINT64(r1, 0);
    
    private final zzfw zzt;

    private zzfv(zzfw zzfw, int i) {
        this.zzt = zzfw;
    }

    public final zzfw zza() {
        return this.zzt;
    }
}
