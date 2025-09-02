package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zzol {
    static final long zza = ((long) zzz(byte[].class));
    static final boolean zzb;
    private static final Unsafe zzc;
    private static final Class zzd = Memory.class;
    private static final boolean zze;
    private static final zzok zzf;
    private static final boolean zzg;
    private static final boolean zzh;

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x011a  */
    static {
        /*
            java.lang.Class<java.lang.Class> r0 = java.lang.Class.class
            sun.misc.Unsafe r1 = zzg()
            zzc = r1
            int r2 = com.google.android.gms.internal.measurement.zzkr.zza
            java.lang.Class<libcore.io.Memory> r2 = libcore.io.Memory.class
            zzd = r2
            java.lang.Class r2 = java.lang.Long.TYPE
            boolean r3 = zzv(r2)
            zze = r3
            java.lang.Class r4 = java.lang.Integer.TYPE
            boolean r4 = zzv(r4)
            r5 = 0
            if (r1 != 0) goto L_0x0020
            goto L_0x002f
        L_0x0020:
            if (r3 == 0) goto L_0x0028
            com.google.android.gms.internal.measurement.zzoj r5 = new com.google.android.gms.internal.measurement.zzoj
            r5.<init>(r1)
            goto L_0x002f
        L_0x0028:
            if (r4 == 0) goto L_0x002f
            com.google.android.gms.internal.measurement.zzoi r5 = new com.google.android.gms.internal.measurement.zzoi
            r5.<init>(r1)
        L_0x002f:
            zzf = r5
            java.lang.String r1 = "getLong"
            java.lang.Class<java.lang.reflect.Field> r3 = java.lang.reflect.Field.class
            java.lang.String r4 = "objectFieldOffset"
            r6 = 1
            r7 = 0
            java.lang.Class<java.lang.Object> r8 = java.lang.Object.class
            if (r5 != 0) goto L_0x003f
        L_0x003d:
            r2 = r7
            goto L_0x0061
        L_0x003f:
            sun.misc.Unsafe r5 = r5.zza
            java.lang.Class r5 = r5.getClass()     // Catch:{ all -> 0x005c }
            java.lang.Class[] r9 = new java.lang.Class[]{r3}     // Catch:{ all -> 0x005c }
            r5.getMethod(r4, r9)     // Catch:{ all -> 0x005c }
            java.lang.Class[] r2 = new java.lang.Class[]{r8, r2}     // Catch:{ all -> 0x005c }
            r5.getMethod(r1, r2)     // Catch:{ all -> 0x005c }
            java.lang.reflect.Field r2 = zzB()     // Catch:{ all -> 0x005c }
            if (r2 != 0) goto L_0x005a
            goto L_0x003d
        L_0x005a:
            r2 = r6
            goto L_0x0061
        L_0x005c:
            r2 = move-exception
            java.util.logging.Logger.getLogger(com.google.android.gms.internal.measurement.zzol.class.getName()).logp(java.util.logging.Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r2.toString()))
            goto L_0x003d
        L_0x0061:
            zzg = r2
            com.google.android.gms.internal.measurement.zzok r2 = zzf
            if (r2 != 0) goto L_0x0069
        L_0x0067:
            r0 = r7
            goto L_0x00c7
        L_0x0069:
            sun.misc.Unsafe r2 = r2.zza
            java.lang.Class r2 = r2.getClass()     // Catch:{ all -> 0x00c2 }
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r4, r3)     // Catch:{ all -> 0x00c2 }
            java.lang.String r3 = "arrayBaseOffset"
            java.lang.Class[] r4 = new java.lang.Class[]{r0}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r3, r4)     // Catch:{ all -> 0x00c2 }
            java.lang.String r3 = "arrayIndexScale"
            java.lang.Class[] r0 = new java.lang.Class[]{r0}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r3, r0)     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = "getInt"
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ all -> 0x00c2 }
            java.lang.Class[] r4 = new java.lang.Class[]{r8, r3}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r0, r4)     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = "putInt"
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x00c2 }
            java.lang.Class[] r4 = new java.lang.Class[]{r8, r3, r4}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r0, r4)     // Catch:{ all -> 0x00c2 }
            java.lang.Class[] r0 = new java.lang.Class[]{r8, r3}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r1, r0)     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = "putLong"
            java.lang.Class[] r1 = new java.lang.Class[]{r8, r3, r3}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r0, r1)     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = "getObject"
            java.lang.Class[] r1 = new java.lang.Class[]{r8, r3}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r0, r1)     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = "putObject"
            java.lang.Class[] r1 = new java.lang.Class[]{r8, r3, r8}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r0, r1)     // Catch:{ all -> 0x00c2 }
            r0 = r6
            goto L_0x00c7
        L_0x00c2:
            r0 = move-exception
            java.util.logging.Logger.getLogger(com.google.android.gms.internal.measurement.zzol.class.getName()).logp(java.util.logging.Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r0.toString()))
            goto L_0x0067
        L_0x00c7:
            zzh = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzz(r0)
            long r0 = (long) r0
            zza = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<int[]> r0 = int[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<long[]> r0 = long[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<float[]> r0 = float[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<double[]> r0 = double[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzz(r0)
            zzA(r0)
            java.lang.reflect.Field r0 = zzB()
            if (r0 == 0) goto L_0x0111
            com.google.android.gms.internal.measurement.zzok r1 = zzf
            if (r1 == 0) goto L_0x0111
            sun.misc.Unsafe r1 = r1.zza
            r1.objectFieldOffset(r0)
        L_0x0111:
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x011a
            goto L_0x011b
        L_0x011a:
            r6 = r7
        L_0x011b:
            zzb = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzol.<clinit>():void");
    }

    private zzol() {
    }

    private static int zzA(Class cls) {
        if (zzh) {
            return zzf.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    private static Field zzB() {
        int i = zzkr.zza;
        Class<Buffer> cls = Buffer.class;
        Field zzC = zzC(cls, "effectiveDirectAddress");
        if (zzC != null) {
            return zzC;
        }
        Field zzC2 = zzC(cls, "address");
        if (zzC2 == null || zzC2.getType() != Long.TYPE) {
            return null;
        }
        return zzC2;
    }

    private static Field zzC(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zzD(Object obj, long j, byte b) {
        Unsafe unsafe = zzf.zza;
        long j2 = -4 & j;
        int i = unsafe.getInt(obj, j2);
        int i2 = ((~((int) j)) & 3) << 3;
        unsafe.putInt(obj, j2, ((255 & b) << i2) | (i & (~(255 << i2))));
    }

    /* access modifiers changed from: private */
    public static void zzE(Object obj, long j, byte b) {
        Unsafe unsafe = zzf.zza;
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        unsafe.putInt(obj, j2, ((255 & b) << i) | (unsafe.getInt(obj, j2) & (~(255 << i))));
    }

    static double zza(Object obj, long j) {
        return zzf.zza(obj, j);
    }

    static float zzb(Object obj, long j) {
        return zzf.zzb(obj, j);
    }

    static int zzc(Object obj, long j) {
        return zzf.zza.getInt(obj, j);
    }

    static long zzd(Object obj, long j) {
        return zzf.zza.getLong(obj, j);
    }

    static Object zze(Class cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static Object zzf(Object obj, long j) {
        return zzf.zza.getObject(obj, j);
    }

    static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzoh());
        } catch (Throwable unused) {
            return null;
        }
    }

    static void zzm(Object obj, long j, boolean z) {
        zzf.zzc(obj, j, z);
    }

    static void zzn(byte[] bArr, long j, byte b) {
        zzf.zzd(bArr, zza + j, b);
    }

    static void zzo(Object obj, long j, double d) {
        zzf.zze(obj, j, d);
    }

    static void zzp(Object obj, long j, float f) {
        zzf.zzf(obj, j, f);
    }

    static void zzq(Object obj, long j, int i) {
        zzf.zza.putInt(obj, j, i);
    }

    static void zzr(Object obj, long j, long j2) {
        zzf.zza.putLong(obj, j, j2);
    }

    static void zzs(Object obj, long j, Object obj2) {
        zzf.zza.putObject(obj, j, obj2);
    }

    static /* bridge */ /* synthetic */ boolean zzt(Object obj, long j) {
        return ((byte) ((zzf.zza.getInt(obj, -4 & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    static /* bridge */ /* synthetic */ boolean zzu(Object obj, long j) {
        return ((byte) ((zzf.zza.getInt(obj, -4 & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    static boolean zzv(Class cls) {
        Class<byte[]> cls2 = byte[].class;
        int i = zzkr.zza;
        try {
            Class cls3 = zzd;
            Class cls4 = Boolean.TYPE;
            cls3.getMethod("peekLong", new Class[]{cls, cls4});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, cls4});
            Class cls5 = Integer.TYPE;
            cls3.getMethod("pokeInt", new Class[]{cls, cls5, cls4});
            cls3.getMethod("peekInt", new Class[]{cls, cls4});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, cls5, cls5});
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, cls5, cls5});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    static boolean zzw(Object obj, long j) {
        return zzf.zzg(obj, j);
    }

    static boolean zzx() {
        return zzh;
    }

    static boolean zzy() {
        return zzg;
    }

    private static int zzz(Class cls) {
        if (zzh) {
            return zzf.zza.arrayBaseOffset(cls);
        }
        return -1;
    }
}
