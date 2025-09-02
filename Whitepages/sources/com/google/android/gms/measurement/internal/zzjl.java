package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzhl;
import com.google.android.gms.internal.measurement.zzhm;
import com.google.android.gms.internal.measurement.zzhn;
import com.google.android.gms.internal.measurement.zzho;
import com.google.android.gms.internal.measurement.zzhp;
import com.google.android.gms.internal.measurement.zzhq;
import com.google.android.gms.internal.measurement.zzht;
import com.google.android.gms.internal.measurement.zzhv;
import com.google.android.gms.internal.measurement.zzhw;
import com.google.android.gms.internal.measurement.zzhx;
import com.google.android.gms.internal.measurement.zzhy;
import com.google.android.gms.internal.measurement.zzia;
import com.google.android.gms.internal.measurement.zzin;
import com.google.android.gms.internal.measurement.zzio;
import com.google.android.gms.internal.measurement.zzrd;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

final class zzjl implements Callable {
    final /* synthetic */ zzbh zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzjp zzc;

    zzjl(zzjp zzjp, zzbh zzbh, String str) {
        this.zza = zzbh;
        this.zzb = str;
        this.zzc = zzjp;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        zzmc zzmc;
        byte[] bArr;
        zzpv zzpv;
        zzpv zzpv2;
        zzqd zzqd;
        zzpv zzpv3;
        zzio zzio;
        Bundle bundle;
        zzmc zzmc2;
        String str;
        zzh zzh;
        String str2;
        zzht zzht;
        zzpv zzpv4;
        zzio zzio2;
        String str3;
        Bundle bundle2;
        Object obj;
        long j;
        zzhw zzhw;
        zzbd zzbd;
        zzmc zzmc3;
        byte[] bArr2;
        zzaw zzj;
        zzjp zzjp = this.zzc;
        zzjp.zza.zzL();
        zzmc zzv = zzjp.zza.zzv();
        zzv.zzg();
        zzio zzio3 = zzv.zzu;
        zzio.zzP();
        zzbh zzbh = this.zza;
        Preconditions.checkNotNull(zzbh);
        String str4 = this.zzb;
        Preconditions.checkNotEmpty(str4);
        String str5 = zzbh.zza;
        if ("_iap".equals(str5) || "_iapx".equals(str5)) {
            zzpv zzpv5 = zzv.zzg;
            zzht zzb2 = zzhv.zzb();
            zzpv5.zzj().zzH();
            zzh zzl = zzpv5.zzj().zzl(str4);
            if (zzl == null) {
                zzv.zzu.zzaW().zzd().zzb("Log and bundle not available. package_name", str4);
                bArr2 = new byte[0];
            } else if (!zzl.zzaJ()) {
                zzv.zzu.zzaW().zzd().zzb("Log and bundle disabled. package_name", str4);
                bArr2 = new byte[0];
            } else {
                zzhw zzz = zzhx.zzz();
                zzz.zzar(1);
                zzz.zzan("android");
                if (!TextUtils.isEmpty(zzl.zzC())) {
                    zzz.zzI(zzl.zzC());
                }
                if (!TextUtils.isEmpty(zzl.zzE())) {
                    zzz.zzK((String) Preconditions.checkNotNull(zzl.zzE()));
                }
                if (!TextUtils.isEmpty(zzl.zzF())) {
                    zzz.zzL((String) Preconditions.checkNotNull(zzl.zzF()));
                }
                if (zzl.zze() != -2147483648L) {
                    zzz.zzM((int) zzl.zze());
                }
                zzz.zzai(zzl.zzq());
                zzz.zzZ(zzl.zzo());
                String zzH = zzl.zzH();
                String zzA = zzl.zzA();
                if (!TextUtils.isEmpty(zzH)) {
                    zzz.zzah(zzH);
                } else if (!TextUtils.isEmpty(zzA)) {
                    zzz.zzH(zzA);
                }
                zzz.zzay(zzl.zzw());
                zzjx zzu = zzv.zzg.zzu(str4);
                zzz.zzW(zzl.zzn());
                if (zzio3.zzJ() && zzv.zzu.zzf().zzy(zzz.zzaF()) && zzu.zzr(zzjw.AD_STORAGE) && !TextUtils.isEmpty((CharSequence) null)) {
                    zzz.zzY((String) null);
                }
                zzz.zzT(zzu.zzp());
                if (zzu.zzr(zzjw.AD_STORAGE) && zzl.zzaI()) {
                    Pair zzd = zzpv5.zzw().zzd(zzl.zzC(), zzu);
                    if (zzl.zzaI() && !TextUtils.isEmpty((CharSequence) zzd.first)) {
                        try {
                            zzz.zzas(zzmc.zza((String) zzd.first, Long.toString(zzbh.zzd)));
                            Object obj2 = zzd.second;
                            if (obj2 != null) {
                                zzz.zzal(((Boolean) obj2).booleanValue());
                            }
                        } catch (SecurityException e) {
                            zzv.zzu.zzaW().zzd().zzb("Resettable device id encryption failed", e.getMessage());
                            bArr = new byte[0];
                            zzpv = zzv.zzg;
                            zzj = zzpv.zzj();
                            zzj.zzL();
                            return bArr2;
                        } catch (Throwable th) {
                            th = th;
                            zzmc = zzv;
                            zzmc.zzg.zzj().zzL();
                            throw th;
                        }
                    }
                }
                zzio zzio4 = zzv.zzu;
                zzio4.zzg().zzv();
                zzz.zzX(Build.MODEL);
                zzio4.zzg().zzv();
                zzz.zzam(Build.VERSION.RELEASE);
                zzz.zzaz((int) zzio4.zzg().zza());
                zzz.zzaD(zzio4.zzg().zzb());
                try {
                    if (zzu.zzr(zzjw.ANALYTICS_STORAGE) && zzl.zzD() != null) {
                        zzz.zzJ(zzmc.zza((String) Preconditions.checkNotNull(zzl.zzD()), Long.toString(zzbh.zzd)));
                    }
                    if (!TextUtils.isEmpty(zzl.zzG())) {
                        zzz.zzag((String) Preconditions.checkNotNull(zzl.zzG()));
                    }
                    String zzC = zzl.zzC();
                    zzpv2 = zzv.zzg;
                    List zzE = zzpv2.zzj().zzE(zzC);
                    Iterator it = zzE.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            zzqd = null;
                            break;
                        }
                        zzqd = (zzqd) it.next();
                        if ("_lte".equals(zzqd.zzc)) {
                            break;
                        }
                    }
                    if (zzqd == null || zzqd.zze == null) {
                        zzqd zzqd2 = new zzqd(zzC, "auto", "_lte", zzv.zzu.zzaU().currentTimeMillis(), 0L);
                        zzE.add(zzqd2);
                        zzpv2.zzj().zzai(zzqd2);
                    }
                    zzio[] zzioArr = new zzio[zzE.size()];
                    for (int i = 0; i < zzE.size(); i++) {
                        zzin zze = zzio.zze();
                        zze.zzf(((zzqd) zzE.get(i)).zzc);
                        zze.zzg(((zzqd) zzE.get(i)).zzd);
                        zzpv2.zzA().zzx(zze, ((zzqd) zzE.get(i)).zze);
                        zzioArr[i] = (zzio) zze.zzba();
                    }
                    zzz.zzm(Arrays.asList(zzioArr));
                    zzpv3 = zzv.zzg;
                    zzpv3.zzQ(zzl, zzz);
                    zzpv3.zzaa(zzl, zzz);
                    zzhf zzb3 = zzhf.zzb(zzbh);
                    zzio = zzv.zzu;
                    zzqf zzw = zzio.zzw();
                    bundle = zzb3.zzd;
                    zzw.zzO(bundle, zzpv2.zzj().zzk(str4));
                    zzio.zzw().zzQ(zzb3, zzio.zzf().zzf(str4));
                    zzmc2 = zzv;
                } catch (SecurityException e2) {
                    zzmc = zzv;
                    zzmc.zzu.zzaW().zzd().zzb("app instance id encryption failed", e2.getMessage());
                    bArr = new byte[0];
                    zzpv = zzmc.zzg;
                } catch (Throwable th2) {
                    th = th2;
                    zzmc.zzg.zzj().zzL();
                    throw th;
                }
                try {
                    bundle.putLong("_c", 1);
                    zzio.zzaW().zzd().zza("Marking in-app purchase as real-time");
                    bundle.putLong(NotificationMessage.NOTIF_KEY_REQUEST_ID, 1);
                    String str6 = zzbh.zzc;
                    bundle.putString("_o", str6);
                    if (zzio.zzw().zzak(zzz.zzaF(), zzl.zzM())) {
                        zzio.zzw().zzS(bundle, "_dbg", 1L);
                        zzio.zzw().zzS(bundle, NotificationMessage.NOTIF_KEY_REQUEST_ID, 1L);
                    }
                    zzaw zzj2 = zzpv2.zzj();
                    str = zzbh.zza;
                    zzbd zzs = zzj2.zzs(str4, str);
                    if (zzs == null) {
                        bundle2 = bundle;
                        str3 = str6;
                        zzio2 = zzio;
                        zzhw = zzz;
                        zzpv4 = zzpv2;
                        zzht = zzb2;
                        str2 = str4;
                        zzh = zzl;
                        obj = null;
                        zzbd = new zzbd(str4, str, 0, 0, 0, zzbh.zzd, 0, (Long) null, (Long) null, (Long) null, (Boolean) null);
                        j = 0;
                    } else {
                        zzpv4 = zzpv2;
                        zzh = zzl;
                        zzht = zzb2;
                        bundle2 = bundle;
                        str3 = str6;
                        zzio2 = zzio;
                        zzhw = zzz;
                        str2 = str4;
                        obj = null;
                        long j2 = zzs.zzf;
                        zzbd = zzs.zzc(zzbh.zzd);
                        j = j2;
                    }
                    zzpv4.zzj().zzV(zzbd);
                    zzmc3 = zzmc2;
                } catch (Throwable th3) {
                    th = th3;
                    zzmc = zzmc2;
                    zzmc.zzg.zzj().zzL();
                    throw th;
                }
                try {
                    zzio zzio5 = zzmc3.zzu;
                    long j3 = zzbh.zzd;
                    zzmc = zzmc3;
                    zzbc zzbc = new zzbc(zzio5, str3, str2, str, j3, j, bundle2);
                    zzhl zze2 = zzhm.zze();
                    zze2.zzm(zzbc.zzd);
                    zze2.zzi(zzbc.zzb);
                    zze2.zzl(zzbc.zze);
                    zzbf zzbf = zzbc.zzf;
                    zzbe zzbe = new zzbe(zzbf);
                    while (zzbe.hasNext()) {
                        String zza2 = zzbe.next();
                        zzhp zze3 = zzhq.zze();
                        zze3.zzj(zza2);
                        Object zzf = zzbf.zzf(zza2);
                        if (zzf != null) {
                            zzpv4.zzA().zzw(zze3, zzf);
                            zze2.zze(zze3);
                        }
                    }
                    zzhw.zzn(zze2);
                    zzhy zza3 = zzia.zza();
                    zzhn zza4 = zzho.zza();
                    zza4.zza(zzbd.zzc);
                    zza4.zzb(str);
                    zza3.zza(zza4);
                    zzhw.zzao(zza3);
                    zzhw.zzi(zzpv4.zzh().zza(zzh.zzC(), Collections.emptyList(), zzhw.zzaN(), Long.valueOf(zze2.zzc()), Long.valueOf(zze2.zzc()), false));
                    if (zze2.zzq()) {
                        zzhw.zzax(zze2.zzc());
                        zzhw.zzab(zze2.zzc());
                    }
                    long zzs2 = zzh.zzs();
                    int i2 = (zzs2 > 0 ? 1 : (zzs2 == 0 ? 0 : -1));
                    if (i2 != 0) {
                        zzhw.zzap(zzs2);
                    }
                    long zzu2 = zzh.zzu();
                    if (zzu2 != 0) {
                        zzhw.zzaq(zzu2);
                    } else if (i2 != 0) {
                        zzhw.zzaq(zzs2);
                    }
                    String zzL = zzh.zzL();
                    zzrd.zzb();
                    String str7 = str2;
                    if (zzio2.zzf().zzx(str7, zzgi.zzaL) && zzL != null) {
                        zzhw.zzav(zzL);
                    }
                    zzh.zzP();
                    zzhw.zzP((int) zzh.zzt());
                    zzio2.zzf().zzj();
                    zzhw.zzaB(119002);
                    zzhw.zzaA(zzio2.zzaU().currentTimeMillis());
                    zzhw.zzau(true);
                    zzpv3.zzN(zzhw.zzaF(), zzhw);
                    zzht zzht2 = zzht;
                    zzht2.zzc(zzhw);
                    zzh zzh2 = zzh;
                    zzh2.zzau(zzhw.zzf());
                    zzh2.zzas(zzhw.zze());
                    zzpv4.zzj().zzT(zzh2, false, false);
                    zzpv4.zzj().zzS();
                    zzpv4.zzj().zzL();
                    try {
                        return zzpv4.zzA().zzB(((zzhv) zzht2.zzba()).zzcd());
                    } catch (IOException e3) {
                        zzmc.zzu.zzaW().zze().zzc("Data loss. Failed to bundle and serialize. appId", zzhe.zzn(str7), e3);
                        return obj;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    zzmc = zzmc3;
                    zzmc.zzg.zzj().zzL();
                    throw th;
                }
            }
            zzj = zzpv5.zzj();
            zzj.zzL();
            return bArr2;
        }
        zzv.zzu.zzaW().zzd().zzc("Generating a payload for this event is not available. package_name, event_name", str4, str5);
        return null;
    }
}
