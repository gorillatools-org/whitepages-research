package com.google.android.gms.internal.measurement;

public enum zzmn {
    VOID(Void.class, Void.class, (Class) null),
    INT(r0, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.FALSE),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzld.class, zzld.class, zzld.zzb),
    ENUM(r0, Integer.class, (Class) null),
    MESSAGE(Object.class, Object.class, (Class) null);
    
    private final Class zzl;

    private zzmn(Class cls, Class cls2, Object obj) {
        this.zzl = cls2;
    }

    public final Class zza() {
        return this.zzl;
    }
}
