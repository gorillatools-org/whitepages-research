package com.google.android.gms.measurement.internal;

import androidx.collection.ArrayMap;
import com.google.android.gms.internal.measurement.zzhh;
import com.google.android.gms.internal.measurement.zzhi;
import com.google.android.gms.internal.measurement.zzhj;
import com.google.android.gms.internal.measurement.zzhk;
import com.google.android.gms.internal.measurement.zzib;
import com.google.android.gms.internal.measurement.zzic;
import com.google.android.gms.internal.measurement.zzid;
import com.google.android.gms.internal.measurement.zzie;
import com.google.android.gms.internal.measurement.zzpq;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class zzy {
    final /* synthetic */ zzae zza;
    private String zzb;
    private boolean zzc;
    private zzic zzd;
    /* access modifiers changed from: private */
    public BitSet zze;
    private BitSet zzf;
    private Map zzg;
    private Map zzh;

    /* synthetic */ zzy(zzae zzae, String str, zzad zzad) {
        this.zza = zzae;
        this.zzb = str;
        this.zzc = true;
        this.zze = new BitSet();
        this.zzf = new BitSet();
        this.zzg = new ArrayMap();
        this.zzh = new ArrayMap();
    }

    /* access modifiers changed from: package-private */
    public final zzhi zza(int i) {
        ArrayList arrayList;
        List list;
        zzhh zzb2 = zzhi.zzb();
        zzb2.zza(i);
        zzb2.zzc(this.zzc);
        zzic zzic = this.zzd;
        if (zzic != null) {
            zzb2.zzd(zzic);
        }
        zzib zze2 = zzic.zze();
        zze2.zzb(zzqa.zzu(this.zze));
        zze2.zzd(zzqa.zzu(this.zzf));
        Map map = this.zzg;
        if (map == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(map.size());
            for (Integer num : this.zzg.keySet()) {
                int intValue = num.intValue();
                Long l = (Long) this.zzg.get(num);
                if (l != null) {
                    zzhj zzc2 = zzhk.zzc();
                    zzc2.zzb(intValue);
                    zzc2.zza(l.longValue());
                    arrayList2.add((zzhk) zzc2.zzba());
                }
            }
            arrayList = arrayList2;
        }
        if (arrayList != null) {
            zze2.zza(arrayList);
        }
        Map map2 = this.zzh;
        if (map2 == null) {
            list = Collections.emptyList();
        } else {
            ArrayList arrayList3 = new ArrayList(map2.size());
            for (Integer num2 : this.zzh.keySet()) {
                zzid zzd2 = zzie.zzd();
                zzd2.zzb(num2.intValue());
                List list2 = (List) this.zzh.get(num2);
                if (list2 != null) {
                    Collections.sort(list2);
                    zzd2.zza(list2);
                }
                arrayList3.add((zzie) zzd2.zzba());
            }
            list = arrayList3;
        }
        zze2.zzc(list);
        zzb2.zzb(zze2);
        return (zzhi) zzb2.zzba();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzab zzab) {
        int zza2 = zzab.zza();
        if (zzab.zzd != null) {
            this.zzf.set(zza2, true);
        }
        Boolean bool = zzab.zze;
        if (bool != null) {
            this.zze.set(zza2, bool.booleanValue());
        }
        if (zzab.zzf != null) {
            Map map = this.zzg;
            Integer valueOf = Integer.valueOf(zza2);
            Long l = (Long) map.get(valueOf);
            long longValue = zzab.zzf.longValue() / 1000;
            if (l == null || longValue > l.longValue()) {
                this.zzg.put(valueOf, Long.valueOf(longValue));
            }
        }
        if (zzab.zzg != null) {
            Map map2 = this.zzh;
            Integer valueOf2 = Integer.valueOf(zza2);
            List list = (List) map2.get(valueOf2);
            if (list == null) {
                list = new ArrayList();
                this.zzh.put(valueOf2, list);
            }
            if (zzab.zzc()) {
                list.clear();
            }
            zzpq.zzb();
            zzio zzio = this.zza.zzu;
            zzam zzf2 = zzio.zzf();
            String str = this.zzb;
            zzgg zzgg = zzgi.zzaE;
            if (zzf2.zzx(str, zzgg) && zzab.zzb()) {
                list.clear();
            }
            zzpq.zzb();
            if (zzio.zzf().zzx(this.zzb, zzgg)) {
                Long valueOf3 = Long.valueOf(zzab.zzg.longValue() / 1000);
                if (!list.contains(valueOf3)) {
                    list.add(valueOf3);
                    return;
                }
                return;
            }
            list.add(Long.valueOf(zzab.zzg.longValue() / 1000));
        }
    }

    /* synthetic */ zzy(zzae zzae, String str, zzic zzic, BitSet bitSet, BitSet bitSet2, Map map, Map map2, zzad zzad) {
        this.zza = zzae;
        this.zzb = str;
        this.zze = bitSet;
        this.zzf = bitSet2;
        this.zzg = map;
        this.zzh = new ArrayMap();
        for (Integer num : map2.keySet()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((Long) map2.get(num));
            this.zzh.put(num, arrayList);
        }
        this.zzc = false;
        this.zzd = zzic;
    }
}
