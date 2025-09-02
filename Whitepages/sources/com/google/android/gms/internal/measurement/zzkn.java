package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzkn;
import com.google.android.gms.internal.measurement.zzko;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

public abstract class zzkn<MessageType extends zzko<MessageType, BuilderType>, BuilderType extends zzkn<MessageType, BuilderType>> implements zzng {
    private static void zza(List list, int i) {
        String str = "Element at index " + (list.size() - i) + " is null.";
        int size = list.size();
        while (true) {
            size--;
            if (size >= i) {
                list.remove(size);
            } else {
                throw new NullPointerException(str);
            }
        }
    }

    protected static void zzaW(Iterable iterable, List list) {
        byte[] bArr = zzmk.zzb;
        iterable.getClass();
        if (iterable instanceof zzmt) {
            List zza = ((zzmt) iterable).zza();
            zzmt zzmt = (zzmt) list;
            int size = list.size();
            for (Object next : zza) {
                if (next == null) {
                    String str = "Element at index " + (zzmt.size() - size) + " is null.";
                    int size2 = zzmt.size();
                    while (true) {
                        size2--;
                        if (size2 >= size) {
                            zzmt.remove(size2);
                        } else {
                            throw new NullPointerException(str);
                        }
                    }
                } else if (next instanceof zzld) {
                    zzld zzld = (zzld) next;
                    zzmt.zzb();
                } else if (next instanceof byte[]) {
                    byte[] bArr2 = (byte[]) next;
                    zzld.zzj(bArr2, 0, bArr2.length);
                    zzmt.zzb();
                } else {
                    zzmt.add((String) next);
                }
            }
        } else if (!(iterable instanceof zzno)) {
            if (iterable instanceof Collection) {
                int size3 = ((Collection) iterable).size();
                if (list instanceof ArrayList) {
                    ((ArrayList) list).ensureCapacity(list.size() + size3);
                } else if (list instanceof zznq) {
                    ((zznq) list).zzf(list.size() + size3);
                }
            }
            int size4 = list.size();
            if (!(iterable instanceof List) || !(iterable instanceof RandomAccess)) {
                for (Object next2 : iterable) {
                    if (next2 == null) {
                        zza(list, size4);
                    }
                    list.add(next2);
                }
                return;
            }
            List list2 = (List) iterable;
            int size5 = list2.size();
            for (int i = 0; i < size5; i++) {
                Object obj = list2.get(i);
                if (obj == null) {
                    zza(list, size4);
                }
                list.add(obj);
            }
        } else {
            list.addAll((Collection) iterable);
        }
    }

    /* renamed from: zzaR */
    public abstract zzkn clone();

    public zzkn zzaS(byte[] bArr, int i, int i2) throws zzmm {
        throw null;
    }

    public zzkn zzaT(byte[] bArr, int i, int i2, zzlp zzlp) throws zzmm {
        throw null;
    }

    public final /* synthetic */ zzng zzaU(byte[] bArr) throws zzmm {
        return zzaS(bArr, 0, bArr.length);
    }

    public final /* synthetic */ zzng zzaV(byte[] bArr, zzlp zzlp) throws zzmm {
        return zzaT(bArr, 0, bArr.length, zzlp);
    }
}
