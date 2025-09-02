package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzgo;
import com.google.android.gms.internal.measurement.zzih;

public final class zzpi extends zzoz {
    zzpi(zzpv zzpv) {
        super(zzpv);
    }

    private final String zzf(String str) {
        String zzm = this.zzg.zzr().zzm(str);
        if (TextUtils.isEmpty(zzm)) {
            return (String) zzgi.zzq.zza((Object) null);
        }
        Uri parse = Uri.parse((String) zzgi.zzq.zza((Object) null));
        Uri.Builder buildUpon = parse.buildUpon();
        String authority = parse.getAuthority();
        buildUpon.authority(zzm + "." + authority);
        return buildUpon.build().toString();
    }

    private final boolean zzh(String str, String str2) {
        zzh zzl;
        zzpv zzpv = this.zzg;
        zzgo zzj = zzpv.zzr().zzj(str);
        if (zzj == null || (zzl = zzpv.zzj().zzl(str)) == null) {
            return false;
        }
        if ((zzj.zzv() && zzj.zzi().zza() == 100) || this.zzu.zzw().zzak(str, zzl.zzM())) {
            return true;
        }
        if (TextUtils.isEmpty(str2) || Math.abs(str2.hashCode() % 100) >= zzj.zzi().zza()) {
            return false;
        }
        return true;
    }

    private static final boolean zzi(String str) {
        String str2 = (String) zzgi.zzs.zza((Object) null);
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        for (String trim : str2.split(",")) {
            if (str.equalsIgnoreCase(trim.trim())) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x0243 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0244  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzph zza(java.lang.String r14) {
        /*
            r13 = this;
            com.google.android.gms.measurement.internal.zzio r0 = r13.zzu
            com.google.android.gms.measurement.internal.zzam r1 = r0.zzf()
            com.google.android.gms.measurement.internal.zzgg r2 = com.google.android.gms.measurement.internal.zzgi.zzaP
            r3 = 0
            boolean r1 = r1.zzx(r3, r2)
            java.lang.String r4 = "x-gtm-server-preview"
            if (r1 == 0) goto L_0x0188
            com.google.android.gms.measurement.internal.zzpv r1 = r13.zzg
            com.google.android.gms.measurement.internal.zzaw r5 = r1.zzj()
            com.google.android.gms.measurement.internal.zzh r5 = r5.zzl(r14)
            if (r5 == 0) goto L_0x0178
            boolean r6 = r5.zzaL()
            if (r6 != 0) goto L_0x0025
            goto L_0x0178
        L_0x0025:
            com.google.android.gms.internal.measurement.zzif r6 = com.google.android.gms.internal.measurement.zzim.zza()
            r7 = 2
            r6.zzc(r7)
            int r8 = r5.zzb()
            com.google.android.gms.internal.measurement.zzih r8 = com.google.android.gms.internal.measurement.zzih.zzb(r8)
            java.lang.Object r8 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)
            com.google.android.gms.internal.measurement.zzih r8 = (com.google.android.gms.internal.measurement.zzih) r8
            r6.zza(r8)
            java.lang.String r8 = r5.zzD()
            boolean r8 = r13.zzh(r14, r8)
            r9 = 3
            if (r8 != 0) goto L_0x0063
            r6.zzb(r9)
            com.google.android.gms.measurement.internal.zzph r0 = new com.google.android.gms.measurement.internal.zzph
            java.lang.String r14 = r13.zzf(r14)
            java.util.Map r1 = java.util.Collections.emptyMap()
            com.google.android.gms.measurement.internal.zzmf r2 = com.google.android.gms.measurement.internal.zzmf.GOOGLE_ANALYTICS
            com.google.android.gms.internal.measurement.zzmd r3 = r6.zzba()
            com.google.android.gms.internal.measurement.zzim r3 = (com.google.android.gms.internal.measurement.zzim) r3
            r0.<init>(r14, r1, r2, r3)
            goto L_0x0187
        L_0x0063:
            java.lang.String r8 = r5.zzC()
            r6.zzc(r7)
            com.google.android.gms.measurement.internal.zzif r1 = r1.zzr()
            java.lang.String r10 = r5.zzC()
            com.google.android.gms.internal.measurement.zzgo r1 = r1.zzj(r10)
            if (r1 == 0) goto L_0x014d
            boolean r10 = r1.zzv()
            if (r10 != 0) goto L_0x0080
            goto L_0x014d
        L_0x0080:
            java.util.HashMap r10 = new java.util.HashMap
            r10.<init>()
            java.lang.String r11 = r5.zzM()
            boolean r11 = android.text.TextUtils.isEmpty(r11)
            if (r11 != 0) goto L_0x0096
            java.lang.String r11 = r5.zzM()
            r10.put(r4, r11)
        L_0x0096:
            com.google.android.gms.internal.measurement.zzgy r4 = r1.zzi()
            java.lang.String r4 = r4.zze()
            int r11 = r5.zzb()
            com.google.android.gms.internal.measurement.zzih r11 = com.google.android.gms.internal.measurement.zzih.zzb(r11)
            if (r11 == 0) goto L_0x00b0
            com.google.android.gms.internal.measurement.zzih r12 = com.google.android.gms.internal.measurement.zzih.CLIENT_UPLOAD_ELIGIBLE
            if (r11 == r12) goto L_0x00b0
            r6.zza(r11)
            goto L_0x00db
        L_0x00b0:
            com.google.android.gms.measurement.internal.zzam r11 = r0.zzf()
            boolean r2 = r11.zzx(r3, r2)
            if (r2 != 0) goto L_0x00c0
            com.google.android.gms.internal.measurement.zzih r2 = com.google.android.gms.internal.measurement.zzih.SERVICE_FLAG_OFF
            r6.zza(r2)
            goto L_0x00db
        L_0x00c0:
            java.lang.String r2 = r5.zzC()
            boolean r2 = zzi(r2)
            if (r2 == 0) goto L_0x00d0
            com.google.android.gms.internal.measurement.zzih r2 = com.google.android.gms.internal.measurement.zzih.PINNED_TO_SERVICE_UPLOAD
            r6.zza(r2)
            goto L_0x00db
        L_0x00d0:
            boolean r2 = android.text.TextUtils.isEmpty(r4)
            if (r2 == 0) goto L_0x012a
            com.google.android.gms.internal.measurement.zzih r2 = com.google.android.gms.internal.measurement.zzih.MISSING_SGTM_SERVER_URL
            r6.zza(r2)
        L_0x00db:
            com.google.android.gms.internal.measurement.zzgy r2 = r1.zzi()
            r2.zzf()
            com.google.android.gms.internal.measurement.zzgy r1 = r1.zzi()
            r1.zzd()
            r0.zzaV()
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 != 0) goto L_0x0114
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            java.lang.String r1 = "[sgtm] Eligible for local service direct upload. appId"
            r0.zzb(r1, r8)
            r0 = 5
            r6.zzc(r0)
            r6.zzb(r7)
            com.google.android.gms.measurement.internal.zzph r3 = new com.google.android.gms.measurement.internal.zzph
            com.google.android.gms.measurement.internal.zzmf r0 = com.google.android.gms.measurement.internal.zzmf.SGTM
            com.google.android.gms.internal.measurement.zzmd r1 = r6.zzba()
            com.google.android.gms.internal.measurement.zzim r1 = (com.google.android.gms.internal.measurement.zzim) r1
            r3.<init>(r4, r10, r0, r1)
            goto L_0x015e
        L_0x0114:
            r1 = 6
            r6.zzb(r1)
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            java.lang.String r1 = r5.zzC()
            java.lang.String r2 = "[sgtm] Local service, missing sgtm_server_url"
            r0.zzb(r2, r1)
            goto L_0x015e
        L_0x012a:
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            java.lang.String r1 = "[sgtm] Eligible for client side upload. appId"
            r0.zzb(r1, r8)
            r6.zzc(r9)
            com.google.android.gms.internal.measurement.zzih r0 = com.google.android.gms.internal.measurement.zzih.CLIENT_UPLOAD_ELIGIBLE
            r6.zza(r0)
            com.google.android.gms.measurement.internal.zzph r3 = new com.google.android.gms.measurement.internal.zzph
            com.google.android.gms.measurement.internal.zzmf r0 = com.google.android.gms.measurement.internal.zzmf.SGTM_CLIENT
            com.google.android.gms.internal.measurement.zzmd r1 = r6.zzba()
            com.google.android.gms.internal.measurement.zzim r1 = (com.google.android.gms.internal.measurement.zzim) r1
            r3.<init>(r4, r10, r0, r1)
            goto L_0x015e
        L_0x014d:
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            java.lang.String r1 = "[sgtm] Missing sgtm_setting in remote config. appId"
            r0.zzb(r1, r8)
            r0 = 4
            r6.zzb(r0)
        L_0x015e:
            if (r3 == 0) goto L_0x0162
            r0 = r3
            goto L_0x0187
        L_0x0162:
            com.google.android.gms.measurement.internal.zzph r0 = new com.google.android.gms.measurement.internal.zzph
            java.lang.String r14 = r13.zzf(r14)
            java.util.Map r1 = java.util.Collections.emptyMap()
            com.google.android.gms.measurement.internal.zzmf r2 = com.google.android.gms.measurement.internal.zzmf.GOOGLE_ANALYTICS
            com.google.android.gms.internal.measurement.zzmd r3 = r6.zzba()
            com.google.android.gms.internal.measurement.zzim r3 = (com.google.android.gms.internal.measurement.zzim) r3
            r0.<init>(r14, r1, r2, r3)
            return r0
        L_0x0178:
            com.google.android.gms.measurement.internal.zzph r0 = new com.google.android.gms.measurement.internal.zzph
            java.lang.String r14 = r13.zzf(r14)
            com.google.android.gms.measurement.internal.zzmf r1 = com.google.android.gms.measurement.internal.zzmf.GOOGLE_ANALYTICS
            java.util.Map r2 = java.util.Collections.emptyMap()
            r0.<init>(r14, r2, r1, r3)
        L_0x0187:
            return r0
        L_0x0188:
            com.google.android.gms.measurement.internal.zzpv r1 = r13.zzg
            com.google.android.gms.measurement.internal.zzaw r2 = r1.zzj()
            com.google.android.gms.measurement.internal.zzh r2 = r2.zzl(r14)
            if (r2 != 0) goto L_0x01a4
            com.google.android.gms.measurement.internal.zzph r0 = new com.google.android.gms.measurement.internal.zzph
            java.lang.String r14 = r13.zzf(r14)
            com.google.android.gms.measurement.internal.zzmf r1 = com.google.android.gms.measurement.internal.zzmf.GOOGLE_ANALYTICS
            java.util.Map r2 = java.util.Collections.emptyMap()
            r0.<init>(r14, r2, r1, r3)
            return r0
        L_0x01a4:
            java.lang.String r5 = r2.zzD()
            boolean r5 = r13.zzh(r14, r5)
            if (r5 == 0) goto L_0x0254
            boolean r5 = r2.zzaL()
            if (r5 != 0) goto L_0x01b7
        L_0x01b4:
            r0 = r3
            goto L_0x0241
        L_0x01b7:
            com.google.android.gms.measurement.internal.zzhe r5 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zzj()
            java.lang.String r6 = "sgtm upload enabled in manifest."
            r5.zza(r6)
            com.google.android.gms.measurement.internal.zzif r1 = r1.zzr()
            java.lang.String r5 = r2.zzC()
            com.google.android.gms.internal.measurement.zzgo r1 = r1.zzj(r5)
            if (r1 == 0) goto L_0x01b4
            boolean r5 = r1.zzv()
            if (r5 != 0) goto L_0x01d9
            goto L_0x01b4
        L_0x01d9:
            com.google.android.gms.internal.measurement.zzgy r5 = r1.zzi()
            java.lang.String r5 = r5.zzf()
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 == 0) goto L_0x01e8
            goto L_0x01b4
        L_0x01e8:
            com.google.android.gms.internal.measurement.zzgy r1 = r1.zzi()
            java.lang.String r1 = r1.zzd()
            com.google.android.gms.measurement.internal.zzhe r6 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zzj()
            r7 = 1
            boolean r8 = android.text.TextUtils.isEmpty(r1)
            if (r7 == r8) goto L_0x0202
            java.lang.String r7 = "N"
            goto L_0x0204
        L_0x0202:
            java.lang.String r7 = "Y"
        L_0x0204:
            java.lang.String r8 = "sgtm configured with upload_url, server_info"
            r6.zzc(r8, r5, r7)
            boolean r6 = android.text.TextUtils.isEmpty(r1)
            if (r6 == 0) goto L_0x021e
            r0.zzaV()
            com.google.android.gms.measurement.internal.zzph r0 = new com.google.android.gms.measurement.internal.zzph
            com.google.android.gms.measurement.internal.zzmf r1 = com.google.android.gms.measurement.internal.zzmf.SGTM
            java.util.Map r2 = java.util.Collections.emptyMap()
            r0.<init>(r5, r2, r1, r3)
            goto L_0x0241
        L_0x021e:
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r6 = "x-sgtm-server-info"
            r0.put(r6, r1)
            java.lang.String r1 = r2.zzM()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0239
            java.lang.String r1 = r2.zzM()
            r0.put(r4, r1)
        L_0x0239:
            com.google.android.gms.measurement.internal.zzph r1 = new com.google.android.gms.measurement.internal.zzph
            com.google.android.gms.measurement.internal.zzmf r2 = com.google.android.gms.measurement.internal.zzmf.SGTM
            r1.<init>(r5, r0, r2, r3)
            r0 = r1
        L_0x0241:
            if (r0 == 0) goto L_0x0244
            return r0
        L_0x0244:
            com.google.android.gms.measurement.internal.zzph r0 = new com.google.android.gms.measurement.internal.zzph
            java.lang.String r14 = r13.zzf(r14)
            com.google.android.gms.measurement.internal.zzmf r1 = com.google.android.gms.measurement.internal.zzmf.GOOGLE_ANALYTICS
            java.util.Map r2 = java.util.Collections.emptyMap()
            r0.<init>(r14, r2, r1, r3)
            return r0
        L_0x0254:
            com.google.android.gms.measurement.internal.zzph r0 = new com.google.android.gms.measurement.internal.zzph
            java.lang.String r14 = r13.zzf(r14)
            com.google.android.gms.measurement.internal.zzmf r1 = com.google.android.gms.measurement.internal.zzmf.GOOGLE_ANALYTICS
            java.util.Map r2 = java.util.Collections.emptyMap()
            r0.<init>(r14, r2, r1, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzpi.zza(java.lang.String):com.google.android.gms.measurement.internal.zzph");
    }

    /* access modifiers changed from: package-private */
    public final boolean zzd(String str, zzih zzih) {
        zzgo zzj;
        zzg();
        if (!this.zzu.zzf().zzx((String) null, zzgi.zzaP) || zzih != zzih.CLIENT_UPLOAD_ELIGIBLE || zzi(str) || (zzj = this.zzg.zzr().zzj(str)) == null || !zzj.zzv() || zzj.zzi().zze().isEmpty()) {
            return false;
        }
        return true;
    }
}
