package com.google.android.gms.internal.play_billing;

import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.gms.internal.play_billing.zzcn;
import com.google.android.gms.internal.play_billing.zzcs;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzcs<MessageType extends zzcs<MessageType, BuilderType>, BuilderType extends zzcn<MessageType, BuilderType>> extends zzay<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzfg zzc = zzfg.zzc();
    private int zzd = -1;

    static zzcs zzj(Class cls) {
        Map map = zzb;
        zzcs zzcs = (zzcs) map.get(cls);
        if (zzcs == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzcs = (zzcs) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzcs == null) {
            zzcs = (zzcs) ((zzcs) zzfp.zze(cls)).zzx(6, (Object) null, (Object) null);
            if (zzcs != null) {
                map.put(cls, zzcs);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzcs;
    }

    protected static zzcs zzm(zzcs zzcs, byte[] bArr, zzcd zzcd) throws zzdc {
        zzcs zzz = zzz(zzcs, bArr, 0, bArr.length, zzcd);
        if (zzz == null || zzz.zzk()) {
            return zzz;
        }
        zzdc zza = new zzfe(zzz).zza();
        zza.zzf(zzz);
        throw zza;
    }

    protected static zzcx zzn() {
        return zzct.zzf();
    }

    protected static zzcz zzo() {
        return zzem.zze();
    }

    static Object zzp(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static Object zzq(zzec zzec, String str, Object[] objArr) {
        return new zzen(zzec, str, objArr);
    }

    protected static void zzt(Class cls, zzcs zzcs) {
        zzcs.zzs();
        zzb.put(cls, zzcs);
    }

    protected static final boolean zzv(zzcs zzcs, boolean z) {
        byte byteValue = ((Byte) zzcs.zzx(1, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zzel.zza().zzb(zzcs.getClass()).zzk(zzcs);
        if (z) {
            zzcs.zzx(2, true != zzk ? null : zzcs, (Object) null);
        }
        return zzk;
    }

    private final int zzy(zzeo zzeo) {
        return zzel.zza().zzb(getClass()).zza(this);
    }

    private static zzcs zzz(zzcs zzcs, byte[] bArr, int i, int i2, zzcd zzcd) throws zzdc {
        if (i2 == 0) {
            return zzcs;
        }
        zzcs zzl = zzcs.zzl();
        try {
            zzeo zzb2 = zzel.zza().zzb(zzl.getClass());
            zzb2.zzh(zzl, bArr, 0, i2, new zzbc(zzcd));
            zzb2.zzf(zzl);
            return zzl;
        } catch (zzdc e) {
            e.zzf(zzl);
            throw e;
        } catch (zzfe e2) {
            zzdc zza = e2.zza();
            zza.zzf(zzl);
            throw zza;
        } catch (IOException e3) {
            if (e3.getCause() instanceof zzdc) {
                throw ((zzdc) e3.getCause());
            }
            zzdc zzdc = new zzdc(e3);
            zzdc.zzf(zzl);
            throw zzdc;
        } catch (IndexOutOfBoundsException unused) {
            zzdc zzg = zzdc.zzg();
            zzg.zzf(zzl);
            throw zzg;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzel.zza().zzb(getClass()).zzj(this, (zzcs) obj);
    }

    public final int hashCode() {
        if (zzw()) {
            return zzd();
        }
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzd2 = zzd();
        this.zza = zzd2;
        return zzd2;
    }

    public final String toString() {
        return zzee.zza(this, super.toString());
    }

    public final /* synthetic */ zzeb zzE() {
        return (zzcn) zzx(5, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzeb zzF() {
        zzcn zzcn = (zzcn) zzx(5, (Object) null, (Object) null);
        zzcn.zzc(this);
        return zzcn;
    }

    /* access modifiers changed from: package-private */
    public final int zza(zzeo zzeo) {
        if (zzw()) {
            int zza = zzeo.zza(this);
            if (zza >= 0) {
                return zza;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zza);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int zza2 = zzeo.zza(this);
        if (zza2 >= 0) {
            this.zzd = (this.zzd & ExploreByTouchHelper.INVALID_ID) | zza2;
            return zza2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + zza2);
    }

    /* access modifiers changed from: package-private */
    public final int zzd() {
        return zzel.zza().zzb(getClass()).zzb(this);
    }

    public final void zze(zzby zzby) throws IOException {
        zzel.zza().zzb(getClass()).zzi(this, zzbz.zza(zzby));
    }

    /* access modifiers changed from: protected */
    public final zzcn zzg() {
        return (zzcn) zzx(5, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzec zzh() {
        return (zzcs) zzx(6, (Object) null, (Object) null);
    }

    public final zzcn zzi() {
        zzcn zzcn = (zzcn) zzx(5, (Object) null, (Object) null);
        zzcn.zzc(this);
        return zzcn;
    }

    public final boolean zzk() {
        return zzv(this, true);
    }

    /* access modifiers changed from: package-private */
    public final zzcs zzl() {
        return (zzcs) zzx(4, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final void zzr() {
        zzel.zza().zzb(getClass()).zzf(this);
        zzs();
    }

    /* access modifiers changed from: package-private */
    public final void zzs() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public final void zzu(int i) {
        this.zzd = (this.zzd & ExploreByTouchHelper.INVALID_ID) | Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzw() {
        return (this.zzd & ExploreByTouchHelper.INVALID_ID) != 0;
    }

    /* access modifiers changed from: protected */
    public abstract Object zzx(int i, Object obj, Object obj2);

    public final int zzf() {
        int i;
        if (zzw()) {
            i = zzy((zzeo) null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zzy((zzeo) null);
                if (i >= 0) {
                    this.zzd = (this.zzd & ExploreByTouchHelper.INVALID_ID) | i;
                } else {
                    throw new IllegalStateException("serialized size must be non-negative, was " + i);
                }
            }
        }
        return i;
    }
}
