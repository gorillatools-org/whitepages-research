package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;

final class zzma implements Runnable {
    final /* synthetic */ zzmb zza;
    private final URL zzb;
    private final byte[] zzc;
    private final zzly zzd;
    private final String zze;
    private final Map zzf;

    public zzma(zzmb zzmb, String str, URL url, byte[] bArr, Map map, zzly zzly) {
        this.zza = zzmb;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzly);
        this.zzb = url;
        this.zzc = bArr;
        this.zzd = zzly;
        this.zze = str;
        this.zzf = map;
    }

    private final void zzb(int i, Exception exc, byte[] bArr, Map map) {
        this.zza.zzu.zzaX().zzq(new zzlz(this, i, exc, bArr, map));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v32, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v33, resolved type: java.io.OutputStream} */
    /* JADX WARNING: type inference failed for: r5v0, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r5v2, types: [java.io.OutputStream] */
    /* JADX WARNING: type inference failed for: r5v28 */
    /* JADX WARNING: type inference failed for: r5v29 */
    /* JADX WARNING: type inference failed for: r5v30 */
    /* JADX WARNING: type inference failed for: r5v31 */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005d, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x011e, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0139, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x013a, code lost:
        r11.zza.zzu.zzaW().zze().zzc("Error closing HTTP compressed POST connection output stream. appId", com.google.android.gms.measurement.internal.zzhe.zzn(r11.zze), r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0151, code lost:
        r4.disconnect();
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x005d A[Catch:{ IOException -> 0x00bc, all -> 0x005d, IOException -> 0x0060, all -> 0x005d }, ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0106 A[SYNTHETIC, Splitter:B:55:0x0106] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0135 A[SYNTHETIC, Splitter:B:77:0x0135] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x015d A[SYNTHETIC, Splitter:B:87:0x015d] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0179  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r11 = this;
            java.lang.String r0 = "Error closing HTTP compressed POST connection output stream. appId"
            com.google.android.gms.measurement.internal.zzmb r1 = r11.zza
            r1.zzaY()
            r2 = 0
            r3 = 0
            java.net.URL r4 = r11.zzb     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            int r5 = com.google.android.gms.internal.measurement.zzcm.zzb     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            boolean r5 = r4 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            if (r5 == 0) goto L_0x0128
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            r4.setDefaultUseCaches(r2)     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            com.google.android.gms.measurement.internal.zzio r1 = r1.zzu     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            r1.zzf()     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            r5 = 60000(0xea60, float:8.4078E-41)
            r4.setConnectTimeout(r5)     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            r1.zzf()     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            r5 = 61000(0xee48, float:8.5479E-41)
            r4.setReadTimeout(r5)     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            r4.setInstanceFollowRedirects(r2)     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            r5 = 1
            r4.setDoInput(r5)     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            java.util.Map r6 = r11.zzf     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            if (r6 == 0) goto L_0x0063
            java.util.Set r6 = r6.entrySet()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
        L_0x0041:
            boolean r7 = r6.hasNext()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            if (r7 == 0) goto L_0x0063
            java.lang.Object r7 = r6.next()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.Object r8 = r7.getKey()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.Object r7 = r7.getValue()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r4.addRequestProperty(r8, r7)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            goto L_0x0041
        L_0x005d:
            r1 = move-exception
            goto L_0x011e
        L_0x0060:
            r1 = move-exception
            goto L_0x0121
        L_0x0063:
            byte[] r6 = r11.zzc     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            if (r6 == 0) goto L_0x00cf
            r1.zzaV()     // Catch:{ IOException -> 0x00bc, all -> 0x005d }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00bc, all -> 0x005d }
            r1.<init>()     // Catch:{ IOException -> 0x00bc, all -> 0x005d }
            java.util.zip.GZIPOutputStream r7 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x00bc, all -> 0x005d }
            r7.<init>(r1)     // Catch:{ IOException -> 0x00bc, all -> 0x005d }
            r7.write(r6)     // Catch:{ IOException -> 0x00bc, all -> 0x005d }
            r7.close()     // Catch:{ IOException -> 0x00bc, all -> 0x005d }
            r1.close()     // Catch:{ IOException -> 0x00bc, all -> 0x005d }
            byte[] r1 = r1.toByteArray()     // Catch:{ IOException -> 0x00bc, all -> 0x005d }
            com.google.android.gms.measurement.internal.zzmb r6 = r11.zza     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            com.google.android.gms.measurement.internal.zzio r6 = r6.zzu     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            com.google.android.gms.measurement.internal.zzhe r6 = r6.zzaW()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zzj()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.String r7 = "Uploading data. size"
            int r8 = r1.length     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r8)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r6.zzb(r7, r9)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r4.setDoOutput(r5)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.String r5 = "Content-Encoding"
            java.lang.String r6 = "gzip"
            r4.addRequestProperty(r5, r6)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r4.setFixedLengthStreamingMode(r8)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r4.connect()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.io.OutputStream r5 = r4.getOutputStream()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            r5.write(r1)     // Catch:{ IOException -> 0x00b4, all -> 0x00b2 }
            r5.close()     // Catch:{ IOException -> 0x00b4, all -> 0x00b2 }
            goto L_0x00cf
        L_0x00b2:
            r1 = move-exception
            goto L_0x00b6
        L_0x00b4:
            r1 = move-exception
            goto L_0x00b9
        L_0x00b6:
            r6 = r3
            goto L_0x0133
        L_0x00b9:
            r6 = r3
            goto L_0x015b
        L_0x00bc:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzmb r5 = r11.zza     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            com.google.android.gms.measurement.internal.zzio r5 = r5.zzu     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.lang.String r6 = "Failed to gzip post request content"
            r5.zzb(r6, r1)     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            throw r1     // Catch:{ IOException -> 0x0060, all -> 0x005d }
        L_0x00cf:
            int r1 = r4.getResponseCode()     // Catch:{ IOException -> 0x0060, all -> 0x005d }
            java.util.Map r5 = r4.getHeaderFields()     // Catch:{ IOException -> 0x011a, all -> 0x0116 }
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0102 }
            r6.<init>()     // Catch:{ all -> 0x0102 }
            java.io.InputStream r7 = r4.getInputStream()     // Catch:{ all -> 0x0102 }
            r8 = 1024(0x400, float:1.435E-42)
            byte[] r8 = new byte[r8]     // Catch:{ all -> 0x00ee }
        L_0x00e4:
            int r9 = r7.read(r8)     // Catch:{ all -> 0x00ee }
            if (r9 <= 0) goto L_0x00f0
            r6.write(r8, r2, r9)     // Catch:{ all -> 0x00ee }
            goto L_0x00e4
        L_0x00ee:
            r2 = move-exception
            goto L_0x0104
        L_0x00f0:
            byte[] r2 = r6.toByteArray()     // Catch:{ all -> 0x00ee }
            r7.close()     // Catch:{ IOException -> 0x0100, all -> 0x00fe }
            r4.disconnect()
            r11.zzb(r1, r3, r2, r5)
            return
        L_0x00fe:
            r2 = move-exception
            goto L_0x010a
        L_0x0100:
            r2 = move-exception
            goto L_0x0110
        L_0x0102:
            r2 = move-exception
            r7 = r3
        L_0x0104:
            if (r7 == 0) goto L_0x0109
            r7.close()     // Catch:{ IOException -> 0x0100, all -> 0x00fe }
        L_0x0109:
            throw r2     // Catch:{ IOException -> 0x0100, all -> 0x00fe }
        L_0x010a:
            r6 = r5
            r5 = r3
        L_0x010c:
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x0133
        L_0x0110:
            r6 = r5
            r5 = r3
        L_0x0112:
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x015b
        L_0x0116:
            r2 = move-exception
            r5 = r3
            r6 = r5
            goto L_0x010c
        L_0x011a:
            r2 = move-exception
            r5 = r3
            r6 = r5
            goto L_0x0112
        L_0x011e:
            r5 = r3
        L_0x011f:
            r6 = r5
            goto L_0x0133
        L_0x0121:
            r5 = r3
        L_0x0122:
            r6 = r5
            goto L_0x015b
        L_0x0124:
            r1 = move-exception
            goto L_0x0130
        L_0x0126:
            r1 = move-exception
            goto L_0x0158
        L_0x0128:
            java.io.IOException r1 = new java.io.IOException     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            java.lang.String r4 = "Failed to obtain HTTP connection"
            r1.<init>(r4)     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
            throw r1     // Catch:{ IOException -> 0x0126, all -> 0x0124 }
        L_0x0130:
            r4 = r3
            r5 = r4
            goto L_0x011f
        L_0x0133:
            if (r5 == 0) goto L_0x014f
            r5.close()     // Catch:{ IOException -> 0x0139 }
            goto L_0x014f
        L_0x0139:
            r5 = move-exception
            com.google.android.gms.measurement.internal.zzmb r7 = r11.zza
            com.google.android.gms.measurement.internal.zzio r7 = r7.zzu
            com.google.android.gms.measurement.internal.zzhe r7 = r7.zzaW()
            com.google.android.gms.measurement.internal.zzhc r7 = r7.zze()
            java.lang.String r8 = r11.zze
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzhe.zzn(r8)
            r7.zzc(r0, r8, r5)
        L_0x014f:
            if (r4 == 0) goto L_0x0154
            r4.disconnect()
        L_0x0154:
            r11.zzb(r2, r3, r3, r6)
            throw r1
        L_0x0158:
            r4 = r3
            r5 = r4
            goto L_0x0122
        L_0x015b:
            if (r5 == 0) goto L_0x0177
            r5.close()     // Catch:{ IOException -> 0x0161 }
            goto L_0x0177
        L_0x0161:
            r5 = move-exception
            com.google.android.gms.measurement.internal.zzmb r7 = r11.zza
            com.google.android.gms.measurement.internal.zzio r7 = r7.zzu
            com.google.android.gms.measurement.internal.zzhe r7 = r7.zzaW()
            com.google.android.gms.measurement.internal.zzhc r7 = r7.zze()
            java.lang.String r8 = r11.zze
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzhe.zzn(r8)
            r7.zzc(r0, r8, r5)
        L_0x0177:
            if (r4 == 0) goto L_0x017c
            r4.disconnect()
        L_0x017c:
            r11.zzb(r2, r1, r3, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzma.run():void");
    }
}
