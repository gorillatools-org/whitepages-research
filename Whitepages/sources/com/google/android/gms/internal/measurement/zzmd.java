package com.google.android.gms.internal.measurement;

import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.gms.internal.measurement.zzlz;
import com.google.android.gms.internal.measurement.zzmd;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzmd<MessageType extends zzmd<MessageType, BuilderType>, BuilderType extends zzlz<MessageType, BuilderType>> extends zzko<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzof zzc = zzof.zzc();
    private int zzd = -1;

    private final int zzc(zzns zzns) {
        return zznp.zza().zzb(getClass()).zza(this);
    }

    static zzmd zzci(Class cls) {
        Map map = zzb;
        zzmd zzmd = (zzmd) map.get(cls);
        if (zzmd == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzmd = (zzmd) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzmd == null) {
            zzmd = (zzmd) ((zzmd) zzol.zze(cls)).zzl(6, (Object) null, (Object) null);
            if (zzmd != null) {
                map.put(cls, zzmd);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzmd;
    }

    protected static zzmh zzck() {
        return zzme.zzf();
    }

    protected static zzmi zzcl() {
        return zzmw.zzf();
    }

    protected static zzmi zzcm(zzmi zzmi) {
        int size = zzmi.size();
        return zzmi.zze(size + size);
    }

    protected static zzmj zzcn() {
        return zznq.zze();
    }

    protected static zzmj zzco(zzmj zzmj) {
        int size = zzmj.size();
        return zzmj.zzd(size + size);
    }

    static Object zzcp(Method method, Object obj, Object... objArr) {
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

    protected static Object zzcq(zznh zznh, String str, Object[] objArr) {
        return new zznr(zznh, str, objArr);
    }

    protected static void zzct(Class cls, zzmd zzmd) {
        zzmd.zzcs();
        zzb.put(cls, zzmd);
    }

    /* access modifiers changed from: private */
    public static final boolean zzd(zzmd zzmd, boolean z) {
        byte byteValue = ((Byte) zzmd.zzl(1, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzk = zznp.zza().zzb(zzmd.getClass()).zzk(zzmd);
        if (z) {
            zzmd.zzl(2, true != zzk ? null : zzmd, (Object) null);
        }
        return zzk;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zznp.zza().zzb(getClass()).zzj(this, (zzmd) obj);
    }

    public final int hashCode() {
        if (zzcw()) {
            return zzce();
        }
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzce = zzce();
        this.zza = zzce;
        return zzce;
    }

    public final String toString() {
        return zznj.zza(this, super.toString());
    }

    public final /* synthetic */ zzng zzcA() {
        return (zzlz) zzl(5, (Object) null, (Object) null);
    }

    public final void zzcB(zzlk zzlk) throws IOException {
        zznp.zza().zzb(getClass()).zzi(this, zzll.zza(zzlk));
    }

    public final /* synthetic */ zznh zzcC() {
        return (zzmd) zzl(6, (Object) null, (Object) null);
    }

    public final boolean zzcD() {
        return zzd(this, true);
    }

    /* access modifiers changed from: package-private */
    public final int zzca(zzns zzns) {
        if (zzcw()) {
            int zza = zzns.zza(this);
            if (zza >= 0) {
                return zza;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zza);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int zza2 = zzns.zza(this);
        if (zza2 >= 0) {
            this.zzd = (this.zzd & ExploreByTouchHelper.INVALID_ID) | zza2;
            return zza2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + zza2);
    }

    /* access modifiers changed from: package-private */
    public final int zzce() {
        return zznp.zza().zzb(getClass()).zzb(this);
    }

    /* access modifiers changed from: protected */
    public final zzlz zzcg() {
        return (zzlz) zzl(5, (Object) null, (Object) null);
    }

    public final zzlz zzch() {
        zzlz zzlz = (zzlz) zzl(5, (Object) null, (Object) null);
        zzlz.zzaY(this);
        return zzlz;
    }

    /* access modifiers changed from: package-private */
    public final zzmd zzcj() {
        return (zzmd) zzl(4, (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public final void zzcr() {
        zznp.zza().zzb(getClass()).zzf(this);
        zzcs();
    }

    /* access modifiers changed from: package-private */
    public final void zzcs() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public final void zzcu(int i) {
        this.zzd = (this.zzd & ExploreByTouchHelper.INVALID_ID) | Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzcw() {
        return (this.zzd & ExploreByTouchHelper.INVALID_ID) != 0;
    }

    /* access modifiers changed from: protected */
    public abstract Object zzl(int i, Object obj, Object obj2);

    public final int zzcf() {
        int i;
        if (zzcw()) {
            i = zzc((zzns) null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zzc((zzns) null);
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
