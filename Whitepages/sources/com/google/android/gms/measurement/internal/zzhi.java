package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;

final class zzhi implements Runnable {
    final /* synthetic */ zzhk zza;
    private final URL zzb;
    private final byte[] zzc;
    private final zzhg zzd;
    private final String zze;
    private final Map zzf;

    public zzhi(zzhk zzhk, String str, URL url, byte[] bArr, Map map, zzhg zzhg) {
        this.zza = zzhk;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzhg);
        this.zzb = url;
        this.zzc = bArr;
        this.zzd = zzhg;
        this.zze = str;
        this.zzf = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f8 A[SYNTHETIC, Splitter:B:50:0x00f8] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0123 A[SYNTHETIC, Splitter:B:70:0x0123] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0161 A[SYNTHETIC, Splitter:B:80:0x0161] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x017d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r13 = this;
            java.lang.String r0 = "Error closing HTTP compressed POST connection output stream. appId"
            com.google.android.gms.measurement.internal.zzhk r1 = r13.zza
            r1.zzaY()
            r2 = 0
            r3 = 0
            java.net.URL r4 = r13.zzb     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            int r5 = com.google.android.gms.internal.measurement.zzcm.zzb     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            boolean r5 = r4 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            if (r5 == 0) goto L_0x0116
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            r4.setDefaultUseCaches(r2)     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            com.google.android.gms.measurement.internal.zzio r5 = r1.zzu     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            r5.zzf()     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            r6 = 60000(0xea60, float:8.4078E-41)
            r4.setConnectTimeout(r6)     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            r5.zzf()     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            r6 = 61000(0xee48, float:8.5479E-41)
            r4.setReadTimeout(r6)     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            r4.setInstanceFollowRedirects(r2)     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            r6 = 1
            r4.setDoInput(r6)     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            java.util.Map r7 = r13.zzf     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            if (r7 == 0) goto L_0x0063
            java.util.Set r7 = r7.entrySet()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
        L_0x0041:
            boolean r8 = r7.hasNext()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            if (r8 == 0) goto L_0x0063
            java.lang.Object r8 = r7.next()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.Object r9 = r8.getKey()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.Object r8 = r8.getValue()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r4.addRequestProperty(r9, r8)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            goto L_0x0041
        L_0x005d:
            r1 = move-exception
            goto L_0x010c
        L_0x0060:
            r1 = move-exception
            goto L_0x010e
        L_0x0063:
            byte[] r7 = r13.zzc     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            if (r7 == 0) goto L_0x00ad
            com.google.android.gms.measurement.internal.zzpv r1 = r1.zzg     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            com.google.android.gms.measurement.internal.zzqa r1 = r1.zzA()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            byte[] r1 = r1.zzB(r7)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zzj()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.String r7 = "Uploading data. size"
            int r8 = r1.length     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r8)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r5.zzb(r7, r9)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r4.setDoOutput(r6)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.String r5 = "Content-Encoding"
            java.lang.String r6 = "gzip"
            r4.addRequestProperty(r5, r6)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r4.setFixedLengthStreamingMode(r8)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r4.connect()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.io.OutputStream r5 = r4.getOutputStream()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r5.write(r1)     // Catch:{ IOException -> 0x00a0, all -> 0x009e }
            r5.close()     // Catch:{ IOException -> 0x00a0, all -> 0x009e }
            goto L_0x00ad
        L_0x009e:
            r1 = move-exception
            goto L_0x00a2
        L_0x00a0:
            r1 = move-exception
            goto L_0x00a7
        L_0x00a2:
            r8 = r3
            r3 = r5
            r5 = r2
            goto L_0x0121
        L_0x00a7:
            r8 = r1
            r7 = r2
            r10 = r3
            r3 = r5
            goto L_0x015f
        L_0x00ad:
            int r8 = r4.getResponseCode()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.util.Map r11 = r4.getHeaderFields()     // Catch:{ IOException -> 0x0108, all -> 0x0104 }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x00f4 }
            r1.<init>()     // Catch:{ all -> 0x00f4 }
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ all -> 0x00f4 }
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch:{ all -> 0x00cc }
        L_0x00c2:
            int r7 = r5.read(r6)     // Catch:{ all -> 0x00cc }
            if (r7 <= 0) goto L_0x00ce
            r1.write(r6, r2, r7)     // Catch:{ all -> 0x00cc }
            goto L_0x00c2
        L_0x00cc:
            r1 = move-exception
            goto L_0x00f6
        L_0x00ce:
            byte[] r10 = r1.toByteArray()     // Catch:{ all -> 0x00cc }
            r5.close()     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
            r4.disconnect()
            com.google.android.gms.measurement.internal.zzhk r0 = r13.zza
            java.lang.String r6 = r13.zze
            com.google.android.gms.measurement.internal.zzhg r7 = r13.zzd
            com.google.android.gms.measurement.internal.zzio r0 = r0.zzu
            com.google.android.gms.measurement.internal.zzil r0 = r0.zzaX()
            com.google.android.gms.measurement.internal.zzhh r1 = new com.google.android.gms.measurement.internal.zzhh
            r9 = 0
            r12 = 0
            r5 = r1
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)
        L_0x00ec:
            r0.zzq(r1)
            return
        L_0x00f0:
            r1 = move-exception
            goto L_0x00fc
        L_0x00f2:
            r1 = move-exception
            goto L_0x00ff
        L_0x00f4:
            r1 = move-exception
            r5 = r3
        L_0x00f6:
            if (r5 == 0) goto L_0x00fb
            r5.close()     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
        L_0x00fb:
            throw r1     // Catch:{ IOException -> 0x00f2, all -> 0x00f0 }
        L_0x00fc:
            r5 = r8
            r8 = r11
            goto L_0x0121
        L_0x00ff:
            r7 = r8
            r10 = r11
        L_0x0101:
            r8 = r1
            goto L_0x015f
        L_0x0104:
            r1 = move-exception
            r5 = r8
        L_0x0106:
            r8 = r3
            goto L_0x0121
        L_0x0108:
            r1 = move-exception
            r10 = r3
            r7 = r8
            goto L_0x0101
        L_0x010c:
            r5 = r2
            goto L_0x0106
        L_0x010e:
            r8 = r1
            r7 = r2
            r10 = r3
            goto L_0x015f
        L_0x0112:
            r1 = move-exception
            goto L_0x011e
        L_0x0114:
            r1 = move-exception
            goto L_0x015b
        L_0x0116:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            java.lang.String r4 = "Failed to obtain HTTP connection"
            r1.<init>(r4)     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
            throw r1     // Catch:{ IOException -> 0x0114, all -> 0x0112 }
        L_0x011e:
            r5 = r2
            r4 = r3
            r8 = r4
        L_0x0121:
            if (r3 == 0) goto L_0x013d
            r3.close()     // Catch:{ IOException -> 0x0127 }
            goto L_0x013d
        L_0x0127:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzhk r3 = r13.zza
            com.google.android.gms.measurement.internal.zzio r3 = r3.zzu
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()
            java.lang.String r6 = r13.zze
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r6)
            r3.zzc(r0, r6, r2)
        L_0x013d:
            if (r4 == 0) goto L_0x0142
            r4.disconnect()
        L_0x0142:
            com.google.android.gms.measurement.internal.zzhk r0 = r13.zza
            java.lang.String r3 = r13.zze
            com.google.android.gms.measurement.internal.zzhg r4 = r13.zzd
            com.google.android.gms.measurement.internal.zzio r0 = r0.zzu
            com.google.android.gms.measurement.internal.zzil r0 = r0.zzaX()
            com.google.android.gms.measurement.internal.zzhh r10 = new com.google.android.gms.measurement.internal.zzhh
            r7 = 0
            r9 = 0
            r6 = 0
            r2 = r10
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            r0.zzq(r10)
            throw r1
        L_0x015b:
            r8 = r1
            r7 = r2
            r4 = r3
            r10 = r4
        L_0x015f:
            if (r3 == 0) goto L_0x017b
            r3.close()     // Catch:{ IOException -> 0x0165 }
            goto L_0x017b
        L_0x0165:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzhk r2 = r13.zza
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()
            java.lang.String r3 = r13.zze
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)
            r2.zzc(r0, r3, r1)
        L_0x017b:
            if (r4 == 0) goto L_0x0180
            r4.disconnect()
        L_0x0180:
            com.google.android.gms.measurement.internal.zzhk r0 = r13.zza
            java.lang.String r5 = r13.zze
            com.google.android.gms.measurement.internal.zzhg r6 = r13.zzd
            com.google.android.gms.measurement.internal.zzio r0 = r0.zzu
            com.google.android.gms.measurement.internal.zzil r0 = r0.zzaX()
            com.google.android.gms.measurement.internal.zzhh r1 = new com.google.android.gms.measurement.internal.zzhh
            r9 = 0
            r11 = 0
            r4 = r1
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            goto L_0x00ec
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhi.run():void");
    }
}
