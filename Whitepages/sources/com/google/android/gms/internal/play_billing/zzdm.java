package com.google.android.gms.internal.play_billing;

import java.util.Collections;
import java.util.List;

final class zzdm extends zzdq {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzdm() {
        throw null;
    }

    /* synthetic */ zzdm(zzdl zzdl) {
        super((zzdp) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        Object obj2;
        List list = (List) zzfp.zzf(obj, j);
        if (list instanceof zzdk) {
            obj2 = ((zzdk) list).zze();
        } else if (!zza.isAssignableFrom(list.getClass())) {
            if (!(list instanceof zzek) || !(list instanceof zzcz)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                zzcz zzcz = (zzcz) list;
                if (zzcz.zzc()) {
                    zzcz.zzb();
                    return;
                }
                return;
            }
        } else {
            return;
        }
        zzfp.zzs(obj, j, obj2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: com.google.android.gms.internal.play_billing.zzdj} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: com.google.android.gms.internal.play_billing.zzdj} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: com.google.android.gms.internal.play_billing.zzdj} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.Object r5, java.lang.Object r6, long r7) {
        /*
            r4 = this;
            java.lang.Object r6 = com.google.android.gms.internal.play_billing.zzfp.zzf(r6, r7)
            java.util.List r6 = (java.util.List) r6
            int r0 = r6.size()
            java.lang.Object r1 = com.google.android.gms.internal.play_billing.zzfp.zzf(r5, r7)
            java.util.List r1 = (java.util.List) r1
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x0039
            boolean r2 = r1 instanceof com.google.android.gms.internal.play_billing.zzdk
            if (r2 == 0) goto L_0x0020
            com.google.android.gms.internal.play_billing.zzdj r1 = new com.google.android.gms.internal.play_billing.zzdj
            r1.<init>((int) r0)
            goto L_0x0035
        L_0x0020:
            boolean r2 = r1 instanceof com.google.android.gms.internal.play_billing.zzek
            if (r2 == 0) goto L_0x0030
            boolean r2 = r1 instanceof com.google.android.gms.internal.play_billing.zzcz
            if (r2 == 0) goto L_0x0030
            com.google.android.gms.internal.play_billing.zzcz r1 = (com.google.android.gms.internal.play_billing.zzcz) r1
            com.google.android.gms.internal.play_billing.zzcz r0 = r1.zzd(r0)
            r1 = r0
            goto L_0x0035
        L_0x0030:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r0)
        L_0x0035:
            com.google.android.gms.internal.play_billing.zzfp.zzs(r5, r7, r1)
            goto L_0x008f
        L_0x0039:
            java.lang.Class r2 = zza
            java.lang.Class r3 = r1.getClass()
            boolean r2 = r2.isAssignableFrom(r3)
            if (r2 == 0) goto L_0x0057
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = r1.size()
            int r3 = r3 + r0
            r2.<init>(r3)
            r2.addAll(r1)
            com.google.android.gms.internal.play_billing.zzfp.zzs(r5, r7, r2)
        L_0x0055:
            r1 = r2
            goto L_0x008f
        L_0x0057:
            boolean r2 = r1 instanceof com.google.android.gms.internal.play_billing.zzfk
            if (r2 == 0) goto L_0x0072
            com.google.android.gms.internal.play_billing.zzdj r2 = new com.google.android.gms.internal.play_billing.zzdj
            int r3 = r1.size()
            int r3 = r3 + r0
            r2.<init>((int) r3)
            com.google.android.gms.internal.play_billing.zzfk r1 = (com.google.android.gms.internal.play_billing.zzfk) r1
            int r0 = r2.size()
            r2.addAll(r0, r1)
            com.google.android.gms.internal.play_billing.zzfp.zzs(r5, r7, r2)
            goto L_0x0055
        L_0x0072:
            boolean r2 = r1 instanceof com.google.android.gms.internal.play_billing.zzek
            if (r2 == 0) goto L_0x008f
            boolean r2 = r1 instanceof com.google.android.gms.internal.play_billing.zzcz
            if (r2 == 0) goto L_0x008f
            r2 = r1
            com.google.android.gms.internal.play_billing.zzcz r2 = (com.google.android.gms.internal.play_billing.zzcz) r2
            boolean r3 = r2.zzc()
            if (r3 != 0) goto L_0x008f
            int r1 = r1.size()
            int r1 = r1 + r0
            com.google.android.gms.internal.play_billing.zzcz r1 = r2.zzd(r1)
            com.google.android.gms.internal.play_billing.zzfp.zzs(r5, r7, r1)
        L_0x008f:
            int r0 = r1.size()
            int r2 = r6.size()
            if (r0 <= 0) goto L_0x009e
            if (r2 <= 0) goto L_0x009e
            r1.addAll(r6)
        L_0x009e:
            if (r0 > 0) goto L_0x00a1
            goto L_0x00a2
        L_0x00a1:
            r6 = r1
        L_0x00a2:
            com.google.android.gms.internal.play_billing.zzfp.zzs(r5, r7, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzdm.zzb(java.lang.Object, java.lang.Object, long):void");
    }
}
