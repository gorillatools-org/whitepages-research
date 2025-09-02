package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzbn;

public abstract class zzgk extends zzbn implements zzgl {
    public zzgk() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    /* JADX WARNING: type inference failed for: r12v1 */
    /* JADX WARNING: type inference failed for: r12v24, types: [com.google.android.gms.measurement.internal.zzgr] */
    /* JADX WARNING: type inference failed for: r12v32, types: [com.google.android.gms.measurement.internal.zzgo] */
    /* JADX WARNING: type inference failed for: r12v37 */
    /* JADX WARNING: type inference failed for: r12v38 */
    /* JADX WARNING: type inference failed for: r12v39 */
    /* JADX WARNING: type inference failed for: r12v40 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) throws android.os.RemoteException {
        /*
            r8 = this;
            r12 = 0
            r0 = 0
            r1 = 1
            switch(r9) {
                case 1: goto L_0x02ae;
                case 2: goto L_0x0294;
                case 3: goto L_0x0006;
                case 4: goto L_0x0282;
                case 5: goto L_0x0268;
                case 6: goto L_0x0256;
                case 7: goto L_0x023c;
                case 8: goto L_0x0006;
                case 9: goto L_0x0221;
                case 10: goto L_0x0205;
                case 11: goto L_0x01ee;
                case 12: goto L_0x01d3;
                case 13: goto L_0x01c0;
                case 14: goto L_0x019d;
                case 15: goto L_0x017e;
                case 16: goto L_0x015f;
                case 17: goto L_0x0144;
                case 18: goto L_0x0131;
                case 19: goto L_0x0116;
                case 20: goto L_0x0103;
                case 21: goto L_0x00e2;
                case 22: goto L_0x0006;
                case 23: goto L_0x0006;
                case 24: goto L_0x00c3;
                case 25: goto L_0x00b0;
                case 26: goto L_0x009d;
                case 27: goto L_0x008a;
                case 28: goto L_0x0006;
                case 29: goto L_0x0056;
                case 30: goto L_0x003b;
                case 31: goto L_0x0007;
                default: goto L_0x0006;
            }
        L_0x0006:
            return r0
        L_0x0007:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r0)
            android.os.Bundle r0 = (android.os.Bundle) r0
            android.os.IBinder r2 = r10.readStrongBinder()
            if (r2 != 0) goto L_0x001e
            goto L_0x0030
        L_0x001e:
            java.lang.String r12 = "com.google.android.gms.measurement.internal.ITriggerUrisCallback"
            android.os.IInterface r12 = r2.queryLocalInterface(r12)
            boolean r3 = r12 instanceof com.google.android.gms.measurement.internal.zzgo
            if (r3 == 0) goto L_0x002b
            com.google.android.gms.measurement.internal.zzgo r12 = (com.google.android.gms.measurement.internal.zzgo) r12
            goto L_0x0030
        L_0x002b:
            com.google.android.gms.measurement.internal.zzgm r12 = new com.google.android.gms.measurement.internal.zzgm
            r12.<init>(r2)
        L_0x0030:
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzr(r9, r0, r12)
            r11.writeNoException()
            goto L_0x02c7
        L_0x003b:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzag> r12 = com.google.android.gms.measurement.internal.zzag.CREATOR
            android.os.Parcelable r12 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r12)
            com.google.android.gms.measurement.internal.zzag r12 = (com.google.android.gms.measurement.internal.zzag) r12
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzC(r9, r12)
            r11.writeNoException()
            goto L_0x02c7
        L_0x0056:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzpc> r0 = com.google.android.gms.measurement.internal.zzpc.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r0)
            com.google.android.gms.measurement.internal.zzpc r0 = (com.google.android.gms.measurement.internal.zzpc) r0
            android.os.IBinder r2 = r10.readStrongBinder()
            if (r2 != 0) goto L_0x006d
            goto L_0x007f
        L_0x006d:
            java.lang.String r12 = "com.google.android.gms.measurement.internal.IUploadBatchesCallback"
            android.os.IInterface r12 = r2.queryLocalInterface(r12)
            boolean r3 = r12 instanceof com.google.android.gms.measurement.internal.zzgr
            if (r3 == 0) goto L_0x007a
            com.google.android.gms.measurement.internal.zzgr r12 = (com.google.android.gms.measurement.internal.zzgr) r12
            goto L_0x007f
        L_0x007a:
            com.google.android.gms.measurement.internal.zzgp r12 = new com.google.android.gms.measurement.internal.zzgp
            r12.<init>(r2)
        L_0x007f:
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzo(r9, r0, r12)
            r11.writeNoException()
            goto L_0x02c7
        L_0x008a:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzm(r9)
            r11.writeNoException()
            goto L_0x02c7
        L_0x009d:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzy(r9)
            r11.writeNoException()
            goto L_0x02c7
        L_0x00b0:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzA(r9)
            r11.writeNoException()
            goto L_0x02c7
        L_0x00c3:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
            android.os.Parcelable r12 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r12)
            android.os.Bundle r12 = (android.os.Bundle) r12
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            java.util.List r9 = r8.zzg(r9, r12)
            r11.writeNoException()
            r11.writeTypedList(r9)
            goto L_0x02c7
        L_0x00e2:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            com.google.android.gms.measurement.internal.zzap r9 = r8.zze(r9)
            r11.writeNoException()
            if (r9 != 0) goto L_0x00fb
            r11.writeInt(r0)
            goto L_0x02c7
        L_0x00fb:
            r11.writeInt(r1)
            r9.writeToParcel(r11, r1)
            goto L_0x02c7
        L_0x0103:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzv(r9)
            r11.writeNoException()
            goto L_0x02c7
        L_0x0116:
            android.os.Parcelable$Creator r9 = android.os.Bundle.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            android.os.Bundle r9 = (android.os.Bundle) r9
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r12 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r12 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r12)
            com.google.android.gms.measurement.internal.zzr r12 = (com.google.android.gms.measurement.internal.zzr) r12
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzx(r9, r12)
            r11.writeNoException()
            goto L_0x02c7
        L_0x0131:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzs(r9)
            r11.writeNoException()
            goto L_0x02c7
        L_0x0144:
            java.lang.String r9 = r10.readString()
            java.lang.String r12 = r10.readString()
            java.lang.String r0 = r10.readString()
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            java.util.List r9 = r8.zzj(r9, r12, r0)
            r11.writeNoException()
            r11.writeTypedList(r9)
            goto L_0x02c7
        L_0x015f:
            java.lang.String r9 = r10.readString()
            java.lang.String r12 = r10.readString()
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r0 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r0)
            com.google.android.gms.measurement.internal.zzr r0 = (com.google.android.gms.measurement.internal.zzr) r0
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            java.util.List r9 = r8.zzi(r9, r12, r0)
            r11.writeNoException()
            r11.writeTypedList(r9)
            goto L_0x02c7
        L_0x017e:
            java.lang.String r9 = r10.readString()
            java.lang.String r12 = r10.readString()
            java.lang.String r0 = r10.readString()
            boolean r2 = com.google.android.gms.internal.measurement.zzbo.zzf(r10)
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            java.util.List r9 = r8.zzl(r9, r12, r0, r2)
            r11.writeNoException()
            r11.writeTypedList(r9)
            goto L_0x02c7
        L_0x019d:
            java.lang.String r9 = r10.readString()
            java.lang.String r12 = r10.readString()
            boolean r0 = com.google.android.gms.internal.measurement.zzbo.zzf(r10)
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r2 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r2)
            com.google.android.gms.measurement.internal.zzr r2 = (com.google.android.gms.measurement.internal.zzr) r2
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            java.util.List r9 = r8.zzk(r9, r12, r0, r2)
            r11.writeNoException()
            r11.writeTypedList(r9)
            goto L_0x02c7
        L_0x01c0:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzai> r9 = com.google.android.gms.measurement.internal.zzai.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzai r9 = (com.google.android.gms.measurement.internal.zzai) r9
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzu(r9)
            r11.writeNoException()
            goto L_0x02c7
        L_0x01d3:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzai> r9 = com.google.android.gms.measurement.internal.zzai.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzai r9 = (com.google.android.gms.measurement.internal.zzai) r9
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r12 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r12 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r12)
            com.google.android.gms.measurement.internal.zzr r12 = (com.google.android.gms.measurement.internal.zzr) r12
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzt(r9, r12)
            r11.writeNoException()
            goto L_0x02c7
        L_0x01ee:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            java.lang.String r9 = r8.zzf(r9)
            r11.writeNoException()
            r11.writeString(r9)
            goto L_0x02c7
        L_0x0205:
            long r3 = r10.readLong()
            java.lang.String r5 = r10.readString()
            java.lang.String r6 = r10.readString()
            java.lang.String r7 = r10.readString()
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r2 = r8
            r2.zzw(r3, r5, r6, r7)
            r11.writeNoException()
            goto L_0x02c7
        L_0x0221:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbh> r9 = com.google.android.gms.measurement.internal.zzbh.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzbh r9 = (com.google.android.gms.measurement.internal.zzbh) r9
            java.lang.String r12 = r10.readString()
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            byte[] r9 = r8.zzD(r9, r12)
            r11.writeNoException()
            r11.writeByteArray(r9)
            goto L_0x02c7
        L_0x023c:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            boolean r12 = com.google.android.gms.internal.measurement.zzbo.zzf(r10)
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            java.util.List r9 = r8.zzh(r9, r12)
            r11.writeNoException()
            r11.writeTypedList(r9)
            goto L_0x02c7
        L_0x0256:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzz(r9)
            r11.writeNoException()
            goto L_0x02c7
        L_0x0268:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbh> r9 = com.google.android.gms.measurement.internal.zzbh.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzbh r9 = (com.google.android.gms.measurement.internal.zzbh) r9
            java.lang.String r12 = r10.readString()
            java.lang.String r0 = r10.readString()
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzq(r9, r12, r0)
            r11.writeNoException()
            goto L_0x02c7
        L_0x0282:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r9 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzr r9 = (com.google.android.gms.measurement.internal.zzr) r9
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzn(r9)
            r11.writeNoException()
            goto L_0x02c7
        L_0x0294:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzqb> r9 = com.google.android.gms.measurement.internal.zzqb.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzqb r9 = (com.google.android.gms.measurement.internal.zzqb) r9
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r12 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r12 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r12)
            com.google.android.gms.measurement.internal.zzr r12 = (com.google.android.gms.measurement.internal.zzr) r12
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzB(r9, r12)
            r11.writeNoException()
            goto L_0x02c7
        L_0x02ae:
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbh> r9 = com.google.android.gms.measurement.internal.zzbh.CREATOR
            android.os.Parcelable r9 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r9)
            com.google.android.gms.measurement.internal.zzbh r9 = (com.google.android.gms.measurement.internal.zzbh) r9
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzr> r12 = com.google.android.gms.measurement.internal.zzr.CREATOR
            android.os.Parcelable r12 = com.google.android.gms.internal.measurement.zzbo.zza(r10, r12)
            com.google.android.gms.measurement.internal.zzr r12 = (com.google.android.gms.measurement.internal.zzr) r12
            com.google.android.gms.internal.measurement.zzbo.zzc(r10)
            r8.zzp(r9, r12)
            r11.writeNoException()
        L_0x02c7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgk.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
