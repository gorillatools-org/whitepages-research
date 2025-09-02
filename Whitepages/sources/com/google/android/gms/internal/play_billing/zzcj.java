package com.google.android.gms.internal.play_billing;

public enum zzcj {
    DOUBLE(0, 1, r5),
    FLOAT(1, 1, r48),
    INT64(2, 1, r60),
    UINT64(3, 1, r60),
    INT32(4, 1, r59),
    FIXED64(5, 1, r60),
    FIXED32(6, 1, r59),
    BOOL(7, 1, r55),
    STRING(8, 1, r38),
    MESSAGE(9, 1, r66),
    BYTES(10, 1, r39),
    UINT32(11, 1, r59),
    ENUM(12, 1, r72),
    SFIXED32(13, 1, r59),
    SFIXED64(14, 1, r60),
    SINT32(15, 1, r59),
    SINT64(16, 1, r60),
    GROUP(17, 1, r66),
    DOUBLE_LIST(18, 2, r5),
    FLOAT_LIST(19, 2, r48),
    INT64_LIST(20, 2, r60),
    UINT64_LIST(21, 2, r60),
    INT32_LIST(22, 2, r59),
    FIXED64_LIST(23, 2, r60),
    FIXED32_LIST(24, 2, r59),
    BOOL_LIST(25, 2, r55),
    STRING_LIST(26, 2, r38),
    MESSAGE_LIST(27, 2, r66),
    BYTES_LIST(28, 2, r39),
    UINT32_LIST(29, 2, r59),
    ENUM_LIST(30, 2, r72),
    SFIXED32_LIST(31, 2, r59),
    SFIXED64_LIST(32, 2, r60),
    SINT32_LIST(33, 2, r59),
    SINT64_LIST(34, 2, r54),
    DOUBLE_LIST_PACKED(35, 3, r5),
    FLOAT_LIST_PACKED(36, 3, r48),
    INT64_LIST_PACKED(37, 3, r54),
    UINT64_LIST_PACKED(38, 3, r54),
    INT32_LIST_PACKED(39, 3, r59),
    FIXED64_LIST_PACKED(40, 3, r60),
    FIXED32_LIST_PACKED(41, 3, r59),
    BOOL_LIST_PACKED(42, 3, r55),
    UINT32_LIST_PACKED(43, 3, r59),
    ENUM_LIST_PACKED(44, 3, r72),
    SFIXED32_LIST_PACKED(45, 3, r59),
    SFIXED64_LIST_PACKED(46, 3, r60),
    SINT32_LIST_PACKED(47, 3, r59),
    SINT64_LIST_PACKED(48, 3, r60),
    GROUP_LIST(49, 2, r66),
    MAP(50, 4, zzdd.VOID);
    
    private static final zzcj[] zzZ = null;
    private final zzdd zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzZ = new zzcj[r1];
        for (zzcj zzcj : values()) {
            zzZ[zzcj.zzac] = zzcj;
        }
    }

    private zzcj(int i, int i2, zzdd zzdd) {
        this.zzac = i;
        this.zzab = zzdd;
        int i3 = i2 - 1;
        if (i3 == 1) {
            this.zzad = zzdd.zza();
        } else if (i3 != 3) {
            this.zzad = null;
        } else {
            this.zzad = zzdd.zza();
        }
        if (i2 == 1) {
            zzdd zzdd2 = zzdd.VOID;
            zzdd.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
