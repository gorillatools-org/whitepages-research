package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import com.salesforce.marketingcloud.g;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

@SuppressLint({"UnknownNullness"})
public class q extends u {
    private static final String b = g.a("NetworkRequestHandler");
    private final r a;

    public q(r rVar) {
        this.a = rVar;
    }

    public boolean a(s sVar) {
        try {
            String lowerCase = sVar.a.getScheme().toLowerCase(Locale.ENGLISH);
            return "http".equalsIgnoreCase(lowerCase) || "https".equalsIgnoreCase(lowerCase);
        } catch (Exception e) {
            g.b(b, e, "Unable to get scheme from request.", new Object[0]);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.salesforce.marketingcloud.media.o r6, com.salesforce.marketingcloud.media.s r7, com.salesforce.marketingcloud.media.u.a r8) throws java.io.IOException {
        /*
            r5 = this;
            android.net.Uri r6 = r7.a
            java.lang.String r6 = r6.toString()
            android.graphics.Bitmap r0 = r5.a(r6, r7)
            if (r0 == 0) goto L_0x0017
            com.salesforce.marketingcloud.media.u$b r6 = new com.salesforce.marketingcloud.media.u$b
            com.salesforce.marketingcloud.media.o$b r7 = com.salesforce.marketingcloud.media.o.b.DISK
            r6.<init>((android.graphics.Bitmap) r0, (com.salesforce.marketingcloud.media.o.b) r7)
            r8.a((com.salesforce.marketingcloud.media.u.b) r6)
            return
        L_0x0017:
            r0 = 0
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r2 = "Starting network request for image"
            java.lang.String r3 = "IMAGE"
            com.salesforce.marketingcloud.g.a((java.lang.String) r3, (java.lang.String) r2, (java.lang.Object[]) r1)
            r1 = 1
            java.net.HttpURLConnection.setFollowRedirects(r1)
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x007e }
            r2.<init>(r6)     // Catch:{ Exception -> 0x007e }
            java.net.URLConnection r2 = r2.openConnection()     // Catch:{ Exception -> 0x007e }
            javax.net.ssl.HttpsURLConnection r2 = (javax.net.ssl.HttpsURLConnection) r2     // Catch:{ Exception -> 0x007e }
            r2.setUseCaches(r0)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            r0 = 30000(0x7530, float:4.2039E-41)
            r2.setConnectTimeout(r0)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            java.lang.String r0 = "GET"
            r2.setRequestMethod(r0)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            java.io.InputStream r0 = r2.getInputStream()     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            int r1 = r7.d     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            boolean r1 = com.salesforce.marketingcloud.media.s.b.c(r1)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            if (r1 == 0) goto L_0x0067
            byte[] r1 = com.salesforce.marketingcloud.util.g.a((java.io.InputStream) r0)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            com.salesforce.marketingcloud.util.g.a((java.io.Closeable) r0)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            com.salesforce.marketingcloud.media.r r0 = r5.a     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            r0.a(r6, r4)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            goto L_0x0067
        L_0x0061:
            r6 = move-exception
            r1 = r2
            goto L_0x0091
        L_0x0064:
            r7 = move-exception
            r1 = r2
            goto L_0x007f
        L_0x0067:
            android.graphics.Bitmap r7 = com.salesforce.marketingcloud.media.u.a(r0, r7)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            com.salesforce.marketingcloud.util.g.a((java.io.Closeable) r0)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            com.salesforce.marketingcloud.media.u$b r0 = new com.salesforce.marketingcloud.media.u$b     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            com.salesforce.marketingcloud.media.o$b r1 = com.salesforce.marketingcloud.media.o.b.NETWORK     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            r0.<init>((android.graphics.Bitmap) r7, (com.salesforce.marketingcloud.media.o.b) r1)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            r8.a((com.salesforce.marketingcloud.media.u.b) r0)     // Catch:{ Exception -> 0x0064, all -> 0x0061 }
            r2.disconnect()
            goto L_0x0090
        L_0x007c:
            r6 = move-exception
            goto L_0x0091
        L_0x007e:
            r7 = move-exception
        L_0x007f:
            java.lang.String r0 = "Image network error for URL: %s"
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch:{ all -> 0x007c }
            com.salesforce.marketingcloud.g.b(r3, r7, r0, r6)     // Catch:{ all -> 0x007c }
            r8.a((java.lang.Throwable) r7)     // Catch:{ all -> 0x007c }
            if (r1 == 0) goto L_0x0090
            r1.disconnect()
        L_0x0090:
            return
        L_0x0091:
            if (r1 == 0) goto L_0x0096
            r1.disconnect()
        L_0x0096:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.media.q.a(com.salesforce.marketingcloud.media.o, com.salesforce.marketingcloud.media.s, com.salesforce.marketingcloud.media.u$a):void");
    }

    private Bitmap a(String str, s sVar) throws IOException {
        InputStream a2 = this.a.a(str);
        Bitmap bitmap = null;
        if (a2 == null) {
            return null;
        }
        try {
            bitmap = u.a(a2, sVar);
            com.salesforce.marketingcloud.util.g.a((Closeable) a2);
            return bitmap;
        } catch (Exception e) {
            g.a(b, (Throwable) e, "Failed to decode cache into Bitmap.", new Object[0]);
            return bitmap;
        }
    }
}
