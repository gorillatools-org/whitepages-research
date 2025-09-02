package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzcu extends zzbn implements zzcv {
    public zzcu() {
        super("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public static zzcv asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
        return queryLocalInterface instanceof zzcv ? (zzcv) queryLocalInterface : new zzct(iBinder);
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v9, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v14, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v22, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v28, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v33, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v38, types: [com.google.android.gms.internal.measurement.zzdg] */
    /* JADX WARNING: type inference failed for: r3v43, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v48, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v53, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v58, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v64, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v69, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v76, types: [com.google.android.gms.internal.measurement.zzde] */
    /* JADX WARNING: type inference failed for: r3v81, types: [com.google.android.gms.internal.measurement.zzde] */
    /* JADX WARNING: type inference failed for: r3v86, types: [com.google.android.gms.internal.measurement.zzde] */
    /* JADX WARNING: type inference failed for: r3v91, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v96, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v101, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v108, types: [com.google.android.gms.internal.measurement.zzcy] */
    /* JADX WARNING: type inference failed for: r3v113, types: [com.google.android.gms.internal.measurement.zzdb] */
    /* JADX WARNING: type inference failed for: r3v118 */
    /* JADX WARNING: type inference failed for: r3v119 */
    /* JADX WARNING: type inference failed for: r3v120 */
    /* JADX WARNING: type inference failed for: r3v121 */
    /* JADX WARNING: type inference failed for: r3v122 */
    /* JADX WARNING: type inference failed for: r3v123 */
    /* JADX WARNING: type inference failed for: r3v124 */
    /* JADX WARNING: type inference failed for: r3v125 */
    /* JADX WARNING: type inference failed for: r3v126 */
    /* JADX WARNING: type inference failed for: r3v127 */
    /* JADX WARNING: type inference failed for: r3v128 */
    /* JADX WARNING: type inference failed for: r3v129 */
    /* JADX WARNING: type inference failed for: r3v130 */
    /* JADX WARNING: type inference failed for: r3v131 */
    /* JADX WARNING: type inference failed for: r3v132 */
    /* JADX WARNING: type inference failed for: r3v133 */
    /* JADX WARNING: type inference failed for: r3v134 */
    /* JADX WARNING: type inference failed for: r3v135 */
    /* JADX WARNING: type inference failed for: r3v136 */
    /* JADX WARNING: type inference failed for: r3v137 */
    /* JADX WARNING: type inference failed for: r3v138 */
    /* JADX WARNING: type inference failed for: r3v139 */
    /* JADX WARNING: type inference failed for: r3v140 */
    /* JADX WARNING: type inference failed for: r3v141 */
    /* JADX WARNING: type inference failed for: r3v142 */
    /* JADX WARNING: type inference failed for: r3v143 */
    /* JADX WARNING: type inference failed for: r3v144 */
    /* JADX WARNING: type inference failed for: r3v145 */
    /* JADX WARNING: type inference failed for: r3v146 */
    /* JADX WARNING: type inference failed for: r3v147 */
    /* JADX WARNING: type inference failed for: r3v148 */
    /* JADX WARNING: type inference failed for: r3v149 */
    /* JADX WARNING: type inference failed for: r3v150 */
    /* JADX WARNING: type inference failed for: r3v151 */
    /* JADX WARNING: type inference failed for: r3v152 */
    /* JADX WARNING: type inference failed for: r3v153 */
    /* JADX WARNING: type inference failed for: r3v154 */
    /* JADX WARNING: type inference failed for: r3v155 */
    /* JADX WARNING: type inference failed for: r3v156 */
    /* JADX WARNING: type inference failed for: r3v157 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r11, android.os.Parcel r12, android.os.Parcel r13, int r14) throws android.os.RemoteException {
        /*
            r10 = this;
            java.lang.String r1 = "com.google.android.gms.measurement.api.internal.IEventHandlerProxy"
            java.lang.String r2 = "com.google.android.gms.measurement.api.internal.IBundleReceiver"
            r3 = 0
            switch(r11) {
                case 1: goto L_0x05ac;
                case 2: goto L_0x0588;
                case 3: goto L_0x054e;
                case 4: goto L_0x052d;
                case 5: goto L_0x0501;
                case 6: goto L_0x04dd;
                case 7: goto L_0x04cd;
                case 8: goto L_0x04b9;
                case 9: goto L_0x04a1;
                case 10: goto L_0x0479;
                case 11: goto L_0x0469;
                case 12: goto L_0x045d;
                case 13: goto L_0x0451;
                case 14: goto L_0x0445;
                case 15: goto L_0x0428;
                case 16: goto L_0x0408;
                case 17: goto L_0x03e8;
                case 18: goto L_0x03c6;
                case 19: goto L_0x03a6;
                case 20: goto L_0x0386;
                case 21: goto L_0x0366;
                case 22: goto L_0x0346;
                case 23: goto L_0x0336;
                case 24: goto L_0x0326;
                case 25: goto L_0x0312;
                case 26: goto L_0x02fe;
                case 27: goto L_0x02e2;
                case 28: goto L_0x02ce;
                case 29: goto L_0x02ba;
                case 30: goto L_0x02a6;
                case 31: goto L_0x027a;
                case 32: goto L_0x024e;
                case 33: goto L_0x0225;
                case 34: goto L_0x0205;
                case 35: goto L_0x01e5;
                case 36: goto L_0x01c5;
                case 37: goto L_0x01b9;
                case 38: goto L_0x0195;
                case 39: goto L_0x0189;
                case 40: goto L_0x0169;
                case 41: goto L_0x0008;
                case 42: goto L_0x0159;
                case 43: goto L_0x014d;
                case 44: goto L_0x0139;
                case 45: goto L_0x0125;
                case 46: goto L_0x0105;
                case 47: goto L_0x0008;
                case 48: goto L_0x00f5;
                case 49: goto L_0x0008;
                case 50: goto L_0x00d8;
                case 51: goto L_0x00c4;
                case 52: goto L_0x00b0;
                case 53: goto L_0x0094;
                case 54: goto L_0x0080;
                case 55: goto L_0x006c;
                case 56: goto L_0x0058;
                case 57: goto L_0x002c;
                case 58: goto L_0x000a;
                default: goto L_0x0008;
            }
        L_0x0008:
            r0 = 0
            return r0
        L_0x000a:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x0011
            goto L_0x0024
        L_0x0011:
            java.lang.String r2 = "com.google.android.gms.measurement.api.internal.IDynamiteUploadBatchesCallback"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdb
            if (r3 == 0) goto L_0x001f
            r3 = r2
            com.google.android.gms.internal.measurement.zzdb r3 = (com.google.android.gms.internal.measurement.zzdb) r3
            goto L_0x0024
        L_0x001f:
            com.google.android.gms.internal.measurement.zzcz r3 = new com.google.android.gms.internal.measurement.zzcz
            r3.<init>(r1)
        L_0x0024:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.retrieveAndUploadBatches(r3)
            goto L_0x05c6
        L_0x002c:
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzdj> r1 = com.google.android.gms.internal.measurement.zzdj.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            com.google.android.gms.internal.measurement.zzdj r1 = (com.google.android.gms.internal.measurement.zzdj) r1
            android.os.IBinder r4 = r12.readStrongBinder()
            if (r4 != 0) goto L_0x003b
            goto L_0x004c
        L_0x003b:
            android.os.IInterface r2 = r4.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x0047
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x004c
        L_0x0047:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r4)
        L_0x004c:
            long r4 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivitySaveInstanceStateByScionActivityInfo(r1, r3, r4)
            goto L_0x05c6
        L_0x0058:
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzdj> r1 = com.google.android.gms.internal.measurement.zzdj.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            com.google.android.gms.internal.measurement.zzdj r1 = (com.google.android.gms.internal.measurement.zzdj) r1
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivityResumedByScionActivityInfo(r1, r2)
            goto L_0x05c6
        L_0x006c:
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzdj> r1 = com.google.android.gms.internal.measurement.zzdj.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            com.google.android.gms.internal.measurement.zzdj r1 = (com.google.android.gms.internal.measurement.zzdj) r1
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivityPausedByScionActivityInfo(r1, r2)
            goto L_0x05c6
        L_0x0080:
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzdj> r1 = com.google.android.gms.internal.measurement.zzdj.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            com.google.android.gms.internal.measurement.zzdj r1 = (com.google.android.gms.internal.measurement.zzdj) r1
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivityDestroyedByScionActivityInfo(r1, r2)
            goto L_0x05c6
        L_0x0094:
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzdj> r1 = com.google.android.gms.internal.measurement.zzdj.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            com.google.android.gms.internal.measurement.zzdj r1 = (com.google.android.gms.internal.measurement.zzdj) r1
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            long r3 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivityCreatedByScionActivityInfo(r1, r2, r3)
            goto L_0x05c6
        L_0x00b0:
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzdj> r1 = com.google.android.gms.internal.measurement.zzdj.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            com.google.android.gms.internal.measurement.zzdj r1 = (com.google.android.gms.internal.measurement.zzdj) r1
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivityStoppedByScionActivityInfo(r1, r2)
            goto L_0x05c6
        L_0x00c4:
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzdj> r1 = com.google.android.gms.internal.measurement.zzdj.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            com.google.android.gms.internal.measurement.zzdj r1 = (com.google.android.gms.internal.measurement.zzdj) r1
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivityStartedByScionActivityInfo(r1, r2)
            goto L_0x05c6
        L_0x00d8:
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzdj> r1 = com.google.android.gms.internal.measurement.zzdj.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            com.google.android.gms.internal.measurement.zzdj r1 = (com.google.android.gms.internal.measurement.zzdj) r1
            java.lang.String r2 = r12.readString()
            java.lang.String r3 = r12.readString()
            long r4 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r0 = r10
            r0.setCurrentScreenByScionActivityInfo(r1, r2, r3, r4)
            goto L_0x05c6
        L_0x00f5:
            android.os.Parcelable$Creator r1 = android.content.Intent.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            android.content.Intent r1 = (android.content.Intent) r1
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.setSgtmDebugInfo(r1)
            goto L_0x05c6
        L_0x0105:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x010c
            goto L_0x011d
        L_0x010c:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x0118
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x011d
        L_0x0118:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r1)
        L_0x011d:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.getSessionId(r3)
            goto L_0x05c6
        L_0x0125:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.setConsentThirdParty(r1, r2)
            goto L_0x05c6
        L_0x0139:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.setConsent(r1, r2)
            goto L_0x05c6
        L_0x014d:
            long r1 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.clearMeasurementEnabled(r1)
            goto L_0x05c6
        L_0x0159:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.setDefaultEventParameters(r1)
            goto L_0x05c6
        L_0x0169:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x0170
            goto L_0x0181
        L_0x0170:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x017c
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x0181
        L_0x017c:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r1)
        L_0x0181:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.isDataCollectionEnabled(r3)
            goto L_0x05c6
        L_0x0189:
            boolean r1 = com.google.android.gms.internal.measurement.zzbo.zzf(r12)
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.setDataCollectionEnabled(r1)
            goto L_0x05c6
        L_0x0195:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x019c
            goto L_0x01ad
        L_0x019c:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x01a8
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x01ad
        L_0x01a8:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r1)
        L_0x01ad:
            int r1 = r12.readInt()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.getTestFlag(r3, r1)
            goto L_0x05c6
        L_0x01b9:
            java.util.HashMap r1 = com.google.android.gms.internal.measurement.zzbo.zzb(r12)
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.initForTests(r1)
            goto L_0x05c6
        L_0x01c5:
            android.os.IBinder r2 = r12.readStrongBinder()
            if (r2 != 0) goto L_0x01cc
            goto L_0x01dd
        L_0x01cc:
            android.os.IInterface r1 = r2.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.android.gms.internal.measurement.zzde
            if (r3 == 0) goto L_0x01d8
            r3 = r1
            com.google.android.gms.internal.measurement.zzde r3 = (com.google.android.gms.internal.measurement.zzde) r3
            goto L_0x01dd
        L_0x01d8:
            com.google.android.gms.internal.measurement.zzdc r3 = new com.google.android.gms.internal.measurement.zzdc
            r3.<init>(r2)
        L_0x01dd:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.unregisterOnMeasurementEventListener(r3)
            goto L_0x05c6
        L_0x01e5:
            android.os.IBinder r2 = r12.readStrongBinder()
            if (r2 != 0) goto L_0x01ec
            goto L_0x01fd
        L_0x01ec:
            android.os.IInterface r1 = r2.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.android.gms.internal.measurement.zzde
            if (r3 == 0) goto L_0x01f8
            r3 = r1
            com.google.android.gms.internal.measurement.zzde r3 = (com.google.android.gms.internal.measurement.zzde) r3
            goto L_0x01fd
        L_0x01f8:
            com.google.android.gms.internal.measurement.zzdc r3 = new com.google.android.gms.internal.measurement.zzdc
            r3.<init>(r2)
        L_0x01fd:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.registerOnMeasurementEventListener(r3)
            goto L_0x05c6
        L_0x0205:
            android.os.IBinder r2 = r12.readStrongBinder()
            if (r2 != 0) goto L_0x020c
            goto L_0x021d
        L_0x020c:
            android.os.IInterface r1 = r2.queryLocalInterface(r1)
            boolean r3 = r1 instanceof com.google.android.gms.internal.measurement.zzde
            if (r3 == 0) goto L_0x0218
            r3 = r1
            com.google.android.gms.internal.measurement.zzde r3 = (com.google.android.gms.internal.measurement.zzde) r3
            goto L_0x021d
        L_0x0218:
            com.google.android.gms.internal.measurement.zzdc r3 = new com.google.android.gms.internal.measurement.zzdc
            r3.<init>(r2)
        L_0x021d:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.setEventInterceptor(r3)
            goto L_0x05c6
        L_0x0225:
            int r1 = r12.readInt()
            java.lang.String r2 = r12.readString()
            android.os.IBinder r3 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            android.os.IBinder r4 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r4)
            android.os.IBinder r5 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r5)
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r0 = r10
            r0.logHealthData(r1, r2, r3, r4, r5)
            goto L_0x05c6
        L_0x024e:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            android.os.IBinder r4 = r12.readStrongBinder()
            if (r4 != 0) goto L_0x025d
            goto L_0x026e
        L_0x025d:
            android.os.IInterface r2 = r4.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x0269
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x026e
        L_0x0269:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r4)
        L_0x026e:
            long r4 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.performAction(r1, r3, r4)
            goto L_0x05c6
        L_0x027a:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.IBinder r4 = r12.readStrongBinder()
            if (r4 != 0) goto L_0x0289
            goto L_0x029a
        L_0x0289:
            android.os.IInterface r2 = r4.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x0295
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x029a
        L_0x0295:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r4)
        L_0x029a:
            long r4 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivitySaveInstanceState(r1, r3, r4)
            goto L_0x05c6
        L_0x02a6:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivityResumed(r1, r2)
            goto L_0x05c6
        L_0x02ba:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivityPaused(r1, r2)
            goto L_0x05c6
        L_0x02ce:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivityDestroyed(r1, r2)
            goto L_0x05c6
        L_0x02e2:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r2)
            android.os.Bundle r2 = (android.os.Bundle) r2
            long r3 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivityCreated(r1, r2, r3)
            goto L_0x05c6
        L_0x02fe:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivityStopped(r1, r2)
            goto L_0x05c6
        L_0x0312:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.onActivityStarted(r1, r2)
            goto L_0x05c6
        L_0x0326:
            java.lang.String r1 = r12.readString()
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.endAdUnitExposure(r1, r2)
            goto L_0x05c6
        L_0x0336:
            java.lang.String r1 = r12.readString()
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.beginAdUnitExposure(r1, r2)
            goto L_0x05c6
        L_0x0346:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x034d
            goto L_0x035e
        L_0x034d:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x0359
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x035e
        L_0x0359:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r1)
        L_0x035e:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.generateEventId(r3)
            goto L_0x05c6
        L_0x0366:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x036d
            goto L_0x037e
        L_0x036d:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x0379
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x037e
        L_0x0379:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r1)
        L_0x037e:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.getGmpAppId(r3)
            goto L_0x05c6
        L_0x0386:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x038d
            goto L_0x039e
        L_0x038d:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x0399
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x039e
        L_0x0399:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r1)
        L_0x039e:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.getAppInstanceId(r3)
            goto L_0x05c6
        L_0x03a6:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x03ad
            goto L_0x03be
        L_0x03ad:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x03b9
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x03be
        L_0x03b9:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r1)
        L_0x03be:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.getCachedAppInstanceId(r3)
            goto L_0x05c6
        L_0x03c6:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x03cd
            goto L_0x03e0
        L_0x03cd:
            java.lang.String r2 = "com.google.android.gms.measurement.api.internal.IStringProvider"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzdg
            if (r3 == 0) goto L_0x03db
            r3 = r2
            com.google.android.gms.internal.measurement.zzdg r3 = (com.google.android.gms.internal.measurement.zzdg) r3
            goto L_0x03e0
        L_0x03db:
            com.google.android.gms.internal.measurement.zzdf r3 = new com.google.android.gms.internal.measurement.zzdf
            r3.<init>(r1)
        L_0x03e0:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.setInstanceIdProvider(r3)
            goto L_0x05c6
        L_0x03e8:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x03ef
            goto L_0x0400
        L_0x03ef:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x03fb
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x0400
        L_0x03fb:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r1)
        L_0x0400:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.getCurrentScreenClass(r3)
            goto L_0x05c6
        L_0x0408:
            android.os.IBinder r1 = r12.readStrongBinder()
            if (r1 != 0) goto L_0x040f
            goto L_0x0420
        L_0x040f:
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x041b
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x0420
        L_0x041b:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r1)
        L_0x0420:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.getCurrentScreenName(r3)
            goto L_0x05c6
        L_0x0428:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            java.lang.String r2 = r12.readString()
            java.lang.String r3 = r12.readString()
            long r4 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r0 = r10
            r0.setCurrentScreen(r1, r2, r3, r4)
            goto L_0x05c6
        L_0x0445:
            long r1 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.setSessionTimeoutDuration(r1)
            goto L_0x05c6
        L_0x0451:
            long r1 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.setMinimumSessionDuration(r1)
            goto L_0x05c6
        L_0x045d:
            long r1 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.resetAnalyticsData(r1)
            goto L_0x05c6
        L_0x0469:
            boolean r1 = com.google.android.gms.internal.measurement.zzbo.zzf(r12)
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.setMeasurementEnabled(r1, r2)
            goto L_0x05c6
        L_0x0479:
            java.lang.String r1 = r12.readString()
            java.lang.String r4 = r12.readString()
            android.os.IBinder r5 = r12.readStrongBinder()
            if (r5 != 0) goto L_0x0488
            goto L_0x0499
        L_0x0488:
            android.os.IInterface r2 = r5.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x0494
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x0499
        L_0x0494:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r5)
        L_0x0499:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.getConditionalUserProperties(r1, r4, r3)
            goto L_0x05c6
        L_0x04a1:
            java.lang.String r1 = r12.readString()
            java.lang.String r2 = r12.readString()
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r3)
            android.os.Bundle r3 = (android.os.Bundle) r3
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.clearConditionalUserProperty(r1, r2, r3)
            goto L_0x05c6
        L_0x04b9:
            android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r1)
            android.os.Bundle r1 = (android.os.Bundle) r1
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.setConditionalUserProperty(r1, r2)
            goto L_0x05c6
        L_0x04cd:
            java.lang.String r1 = r12.readString()
            long r2 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.setUserId(r1, r2)
            goto L_0x05c6
        L_0x04dd:
            java.lang.String r1 = r12.readString()
            android.os.IBinder r4 = r12.readStrongBinder()
            if (r4 != 0) goto L_0x04e8
            goto L_0x04f9
        L_0x04e8:
            android.os.IInterface r2 = r4.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x04f4
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x04f9
        L_0x04f4:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r4)
        L_0x04f9:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.getMaxUserProperties(r1, r3)
            goto L_0x05c6
        L_0x0501:
            java.lang.String r1 = r12.readString()
            java.lang.String r4 = r12.readString()
            boolean r5 = com.google.android.gms.internal.measurement.zzbo.zzf(r12)
            android.os.IBinder r6 = r12.readStrongBinder()
            if (r6 != 0) goto L_0x0514
            goto L_0x0525
        L_0x0514:
            android.os.IInterface r2 = r6.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x0520
            r3 = r2
            com.google.android.gms.internal.measurement.zzcy r3 = (com.google.android.gms.internal.measurement.zzcy) r3
            goto L_0x0525
        L_0x0520:
            com.google.android.gms.internal.measurement.zzcw r3 = new com.google.android.gms.internal.measurement.zzcw
            r3.<init>(r6)
        L_0x0525:
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.getUserProperties(r1, r4, r5, r3)
            goto L_0x05c6
        L_0x052d:
            java.lang.String r1 = r12.readString()
            java.lang.String r2 = r12.readString()
            android.os.IBinder r3 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            boolean r4 = com.google.android.gms.internal.measurement.zzbo.zzf(r12)
            long r5 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r0 = r10
            r0.setUserProperty(r1, r2, r3, r4, r5)
            goto L_0x05c6
        L_0x054e:
            java.lang.String r1 = r12.readString()
            java.lang.String r4 = r12.readString()
            android.os.Parcelable$Creator r5 = android.os.Bundle.CREATOR
            android.os.Parcelable r5 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r5)
            android.os.Bundle r5 = (android.os.Bundle) r5
            android.os.IBinder r6 = r12.readStrongBinder()
            if (r6 != 0) goto L_0x0566
            r6 = r3
            goto L_0x0578
        L_0x0566:
            android.os.IInterface r2 = r6.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzcy
            if (r3 == 0) goto L_0x0572
            com.google.android.gms.internal.measurement.zzcy r2 = (com.google.android.gms.internal.measurement.zzcy) r2
        L_0x0570:
            r6 = r2
            goto L_0x0578
        L_0x0572:
            com.google.android.gms.internal.measurement.zzcw r2 = new com.google.android.gms.internal.measurement.zzcw
            r2.<init>(r6)
            goto L_0x0570
        L_0x0578:
            long r8 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r0 = r10
            r2 = r4
            r3 = r5
            r4 = r6
            r5 = r8
            r0.logEventAndBundle(r1, r2, r3, r4, r5)
            goto L_0x05c6
        L_0x0588:
            java.lang.String r1 = r12.readString()
            java.lang.String r2 = r12.readString()
            android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r3)
            android.os.Bundle r3 = (android.os.Bundle) r3
            boolean r4 = com.google.android.gms.internal.measurement.zzbo.zzf(r12)
            boolean r5 = com.google.android.gms.internal.measurement.zzbo.zzf(r12)
            long r6 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r0 = r10
            r0.logEvent(r1, r2, r3, r4, r5, r6)
            goto L_0x05c6
        L_0x05ac:
            android.os.IBinder r1 = r12.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r1)
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzdh> r2 = com.google.android.gms.internal.measurement.zzdh.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.measurement.zzbo.zza(r12, r2)
            com.google.android.gms.internal.measurement.zzdh r2 = (com.google.android.gms.internal.measurement.zzdh) r2
            long r3 = r12.readLong()
            com.google.android.gms.internal.measurement.zzbo.zzc(r12)
            r10.initialize(r1, r2, r3)
        L_0x05c6:
            r13.writeNoException()
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzcu.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
