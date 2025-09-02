package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzbm;
import com.google.android.gms.internal.measurement.zzbo;
import java.util.ArrayList;
import java.util.List;

public final class zzgj extends zzbm implements zzgl {
    zzgj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public final void zzA(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        zzc(25, zza);
    }

    public final void zzB(zzqb zzqb, zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzqb);
        zzbo.zzd(zza, zzr);
        zzc(2, zza);
    }

    public final void zzC(zzr zzr, zzag zzag) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        zzbo.zzd(zza, zzag);
        zzc(30, zza);
    }

    public final byte[] zzD(zzbh zzbh, String str) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzbh);
        zza.writeString(str);
        Parcel zzb = zzb(9, zza);
        byte[] createByteArray = zzb.createByteArray();
        zzb.recycle();
        return createByteArray;
    }

    public final zzap zze(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        Parcel zzb = zzb(21, zza);
        zzap zzap = (zzap) zzbo.zza(zzb, zzap.CREATOR);
        zzb.recycle();
        return zzap;
    }

    public final String zzf(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        Parcel zzb = zzb(11, zza);
        String readString = zzb.readString();
        zzb.recycle();
        return readString;
    }

    public final List zzg(zzr zzr, Bundle bundle) throws RemoteException {
        throw null;
    }

    public final List zzh(zzr zzr, boolean z) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        zza.writeInt(z ? 1 : 0);
        Parcel zzb = zzb(7, zza);
        ArrayList<zzqb> createTypedArrayList = zzb.createTypedArrayList(zzqb.CREATOR);
        zzb.recycle();
        return createTypedArrayList;
    }

    public final List zzi(String str, String str2, zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzbo.zzd(zza, zzr);
        Parcel zzb = zzb(16, zza);
        ArrayList<zzai> createTypedArrayList = zzb.createTypedArrayList(zzai.CREATOR);
        zzb.recycle();
        return createTypedArrayList;
    }

    public final List zzj(String str, String str2, String str3) throws RemoteException {
        Parcel zza = zza();
        zza.writeString((String) null);
        zza.writeString(str2);
        zza.writeString(str3);
        Parcel zzb = zzb(17, zza);
        ArrayList<zzai> createTypedArrayList = zzb.createTypedArrayList(zzai.CREATOR);
        zzb.recycle();
        return createTypedArrayList;
    }

    public final List zzk(String str, String str2, boolean z, zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        int i = zzbo.zza;
        zza.writeInt(z ? 1 : 0);
        zzbo.zzd(zza, zzr);
        Parcel zzb = zzb(14, zza);
        ArrayList<zzqb> createTypedArrayList = zzb.createTypedArrayList(zzqb.CREATOR);
        zzb.recycle();
        return createTypedArrayList;
    }

    public final List zzl(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel zza = zza();
        zza.writeString((String) null);
        zza.writeString(str2);
        zza.writeString(str3);
        int i = zzbo.zza;
        zza.writeInt(z ? 1 : 0);
        Parcel zzb = zzb(15, zza);
        ArrayList<zzqb> createTypedArrayList = zzb.createTypedArrayList(zzqb.CREATOR);
        zzb.recycle();
        return createTypedArrayList;
    }

    public final void zzm(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        zzc(27, zza);
    }

    public final void zzn(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        zzc(4, zza);
    }

    public final void zzo(zzr zzr, zzpc zzpc, zzgr zzgr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        zzbo.zzd(zza, zzpc);
        zzbo.zze(zza, zzgr);
        zzc(29, zza);
    }

    public final void zzp(zzbh zzbh, zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzbh);
        zzbo.zzd(zza, zzr);
        zzc(1, zza);
    }

    public final void zzq(zzbh zzbh, String str, String str2) throws RemoteException {
        throw null;
    }

    public final void zzr(zzr zzr, Bundle bundle, zzgo zzgo) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        zzbo.zzd(zza, bundle);
        zzbo.zze(zza, zzgo);
        zzc(31, zza);
    }

    public final void zzs(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        zzc(18, zza);
    }

    public final void zzt(zzai zzai, zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzai);
        zzbo.zzd(zza, zzr);
        zzc(12, zza);
    }

    public final void zzu(zzai zzai) throws RemoteException {
        throw null;
    }

    public final void zzv(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        zzc(20, zza);
    }

    public final void zzw(long j, String str, String str2, String str3) throws RemoteException {
        Parcel zza = zza();
        zza.writeLong(j);
        zza.writeString(str);
        zza.writeString(str2);
        zza.writeString(str3);
        zzc(10, zza);
    }

    public final void zzx(Bundle bundle, zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, bundle);
        zzbo.zzd(zza, zzr);
        zzc(19, zza);
    }

    public final void zzy(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        zzc(26, zza);
    }

    public final void zzz(zzr zzr) throws RemoteException {
        Parcel zza = zza();
        zzbo.zzd(zza, zzr);
        zzc(6, zza);
    }
}
