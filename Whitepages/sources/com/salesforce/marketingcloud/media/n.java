package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.media.s;
import com.salesforce.marketingcloud.media.u;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

@SuppressLint({"UnknownNullness"})
public class n implements Runnable {
    private static final String m = "ImageHandler-";

    /* renamed from: n  reason: collision with root package name */
    private static final String f31n = "ImageHandler-Idle";
    private static final ThreadLocal<StringBuilder> o = new a();
    private static final u p = new b();
    final o a;
    final h b;
    final String c;
    final u d;
    final c e;
    s f;
    a g;
    List<a> h;
    u.b i;
    Future<?> j;
    Exception k;
    o.c l;

    class a extends ThreadLocal<StringBuilder> {
        a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public StringBuilder initialValue() {
            return new StringBuilder(n.m);
        }
    }

    class b extends u {
        b() {
        }

        public boolean a(s sVar) {
            return true;
        }

        public void a(o oVar, s sVar, u.a aVar) throws IOException {
            aVar.a((Throwable) new k(sVar));
        }
    }

    class c implements u.a {
        final /* synthetic */ AtomicReference a;
        final /* synthetic */ CountDownLatch b;
        final /* synthetic */ AtomicReference c;

        c(AtomicReference atomicReference, CountDownLatch countDownLatch, AtomicReference atomicReference2) {
            this.a = atomicReference;
            this.b = countDownLatch;
            this.c = atomicReference2;
        }

        public void a(Throwable th) {
            this.c.set(th);
            this.b.countDown();
        }

        public void a(u.b bVar) {
            this.a.set(bVar);
            this.b.countDown();
        }
    }

    n(o oVar, h hVar, c cVar, a aVar, u uVar) {
        this.a = oVar;
        this.b = hVar;
        this.e = cVar;
        this.g = aVar;
        this.c = aVar.c();
        this.f = aVar.e();
        this.d = uVar;
        this.l = aVar.d();
    }

    public void a(a aVar) {
        if (this.g == null) {
            this.g = aVar;
            return;
        }
        if (this.h == null) {
            this.h = new ArrayList();
        }
        this.h.add(aVar);
        o.c d2 = aVar.d();
        if (d2.ordinal() > this.l.ordinal()) {
            this.l = d2;
        }
    }

    public void b(a aVar) {
        if (this.g == aVar) {
            this.g = null;
            return;
        }
        List<a> list = this.h;
        if (list != null) {
            list.remove(aVar);
        }
    }

    public a c() {
        return this.g;
    }

    public List<a> d() {
        return this.h;
    }

    public s e() {
        return this.f;
    }

    public Exception f() {
        return this.k;
    }

    public o g() {
        return this.a;
    }

    public String h() {
        return this.c;
    }

    public u.b i() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public boolean j() {
        Future<?> future = this.j;
        return future != null && future.isCancelled();
    }

    public void run() {
        try {
            a(this.f);
            u.b b2 = b();
            this.i = b2;
            if (!b2.d()) {
                this.b.c(this);
            } else {
                g.a("IMAGE", "onSuccess - Loaded from: %s", this.i.c());
                this.b.b(this);
            }
        } catch (Exception e2) {
            this.k = e2;
            this.b.c(this);
        } catch (Throwable th) {
            Thread.currentThread().setName(f31n);
            throw th;
        }
        Thread.currentThread().setName(f31n);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        r0 = r2.j;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a() {
        /*
            r2 = this;
            com.salesforce.marketingcloud.media.a r0 = r2.g
            r1 = 0
            if (r0 != 0) goto L_0x001a
            java.util.List<com.salesforce.marketingcloud.media.a> r0 = r2.h
            if (r0 == 0) goto L_0x000f
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x001a
        L_0x000f:
            java.util.concurrent.Future<?> r0 = r2.j
            if (r0 == 0) goto L_0x001a
            boolean r0 = r0.cancel(r1)
            if (r0 == 0) goto L_0x001a
            r1 = 1
        L_0x001a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.media.n.a():boolean");
    }

    /* access modifiers changed from: package-private */
    public u.b b() throws IOException {
        Bitmap a2;
        if (s.b.a(this.f.d) && (a2 = this.e.a(this.c)) != null) {
            return new u.b(a2, o.b.MEMORY);
        }
        AtomicReference atomicReference = new AtomicReference();
        AtomicReference atomicReference2 = new AtomicReference();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            this.d.a(this.a, this.f, (u.a) new c(atomicReference, countDownLatch, atomicReference2));
            countDownLatch.await();
            Throwable th = (Throwable) atomicReference2.get();
            if (th == null) {
                u.b bVar = (u.b) atomicReference.get();
                if (!bVar.d()) {
                    return bVar;
                }
                Bitmap a3 = bVar.a();
                if (!this.f.e()) {
                    return bVar;
                }
                if (this.f.d()) {
                    a3 = b(this.f, a3);
                }
                if (this.f.c()) {
                    a3 = a(this.f, a3);
                }
                return new u.b(a3, bVar.c());
            }
            throw new RuntimeException(th);
        } catch (InterruptedException e2) {
            throw new InterruptedIOException(e2.getMessage());
        }
    }

    static Bitmap a(s sVar, Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f2 = sVar.i;
        float f3 = sVar.j;
        float f4 = (float) width;
        float f5 = (float) height;
        RectF rectF = new RectF(0.0f, 0.0f, f4, f5);
        RectF rectF2 = new RectF(0.0f, 0.0f, f4, f5);
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        paint.setAntiAlias(true);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        int i2 = (f3 > 0.0f ? 1 : (f3 == 0.0f ? 0 : -1));
        if (i2 > 0) {
            paint2.setStrokeWidth(f3);
            paint2.setColor(sVar.k);
            float f6 = f3 / 2.0f;
            rectF2.inset(f6, f6);
            float floor = (float) Math.floor((double) f6);
            rectF.inset(floor, floor);
        }
        if (f2 > 0.0f) {
            canvas.drawRoundRect(rectF, f2, f2, paint);
            if (i2 > 0) {
                canvas.drawRoundRect(rectF2, f2, f2, paint2);
            }
        } else {
            canvas.drawRect(rectF, paint);
            if (i2 > 0) {
                canvas.drawRect(rectF2, paint2);
            }
        }
        if (bitmap == createBitmap) {
            return bitmap;
        }
        bitmap.recycle();
        return createBitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.graphics.Bitmap b(com.salesforce.marketingcloud.media.s r11, android.graphics.Bitmap r12) {
        /*
            int r0 = r12.getWidth()
            int r1 = r12.getHeight()
            android.graphics.Matrix r7 = new android.graphics.Matrix
            r7.<init>()
            boolean r2 = r11.d()
            r3 = 0
            if (r2 == 0) goto L_0x00a5
            int r2 = r11.e
            int r4 = r11.f
            boolean r5 = r11.g
            if (r5 == 0) goto L_0x0068
            if (r2 == 0) goto L_0x0022
            float r11 = (float) r2
            float r5 = (float) r0
        L_0x0020:
            float r11 = r11 / r5
            goto L_0x0025
        L_0x0022:
            float r11 = (float) r4
            float r5 = (float) r1
            goto L_0x0020
        L_0x0025:
            if (r4 == 0) goto L_0x002b
            float r5 = (float) r4
            float r6 = (float) r1
        L_0x0029:
            float r5 = r5 / r6
            goto L_0x002e
        L_0x002b:
            float r5 = (float) r2
            float r6 = (float) r0
            goto L_0x0029
        L_0x002e:
            int r6 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r6 <= 0) goto L_0x0046
            float r2 = (float) r1
            float r5 = r5 / r11
            float r2 = r2 * r5
            double r5 = (double) r2
            double r5 = java.lang.Math.ceil(r5)
            int r2 = (int) r5
            int r1 = r1 - r2
            int r1 = r1 / 2
            float r4 = (float) r4
            float r5 = (float) r2
            float r5 = r4 / r5
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x0061
        L_0x0046:
            int r4 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x005f
            float r4 = (float) r0
            float r11 = r11 / r5
            float r4 = r4 * r11
            double r8 = (double) r4
            double r8 = java.lang.Math.ceil(r8)
            int r11 = (int) r8
            int r0 = r0 - r11
            int r0 = r0 / 2
            float r2 = (float) r2
            float r4 = (float) r11
            float r2 = r2 / r4
            r10 = r0
            r0 = r11
            r11 = r2
            r2 = r3
            r3 = r10
            goto L_0x0061
        L_0x005f:
            r2 = r3
            r11 = r5
        L_0x0061:
            r7.preScale(r11, r5)
            r5 = r0
            r6 = r1
            r4 = r2
            goto L_0x00a8
        L_0x0068:
            boolean r11 = r11.h
            if (r11 == 0) goto L_0x0088
            if (r2 == 0) goto L_0x0072
            float r11 = (float) r2
            float r5 = (float) r0
        L_0x0070:
            float r11 = r11 / r5
            goto L_0x0075
        L_0x0072:
            float r11 = (float) r4
            float r5 = (float) r1
            goto L_0x0070
        L_0x0075:
            if (r4 == 0) goto L_0x007b
            float r2 = (float) r4
            float r4 = (float) r1
        L_0x0079:
            float r2 = r2 / r4
            goto L_0x007e
        L_0x007b:
            float r2 = (float) r2
            float r4 = (float) r0
            goto L_0x0079
        L_0x007e:
            int r4 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0083
            goto L_0x0084
        L_0x0083:
            r11 = r2
        L_0x0084:
            r7.preScale(r11, r11)
            goto L_0x00a5
        L_0x0088:
            if (r2 != 0) goto L_0x008c
            if (r4 == 0) goto L_0x00a5
        L_0x008c:
            if (r2 != r0) goto L_0x0090
            if (r4 == r1) goto L_0x00a5
        L_0x0090:
            if (r2 == 0) goto L_0x0096
            float r11 = (float) r2
            float r5 = (float) r0
        L_0x0094:
            float r11 = r11 / r5
            goto L_0x0099
        L_0x0096:
            float r11 = (float) r4
            float r5 = (float) r1
            goto L_0x0094
        L_0x0099:
            if (r4 == 0) goto L_0x009f
            float r2 = (float) r4
            float r4 = (float) r1
        L_0x009d:
            float r2 = r2 / r4
            goto L_0x00a2
        L_0x009f:
            float r2 = (float) r2
            float r4 = (float) r0
            goto L_0x009d
        L_0x00a2:
            r7.preScale(r11, r2)
        L_0x00a5:
            r5 = r0
            r6 = r1
            r4 = r3
        L_0x00a8:
            r8 = 1
            r2 = r12
            android.graphics.Bitmap r11 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7, r8)
            if (r11 == r12) goto L_0x00b4
            r12.recycle()
            r12 = r11
        L_0x00b4:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.media.n.b(com.salesforce.marketingcloud.media.s, android.graphics.Bitmap):android.graphics.Bitmap");
    }

    static n a(o oVar, h hVar, c cVar, a aVar) {
        s e2 = aVar.e();
        List<u> a2 = oVar.a();
        int size = a2.size();
        for (int i2 = 0; i2 < size; i2++) {
            u uVar = a2.get(i2);
            if (uVar.a(e2)) {
                return new n(oVar, hVar, cVar, aVar, uVar);
            }
        }
        return new n(oVar, hVar, cVar, aVar, p);
    }

    static void a(s sVar) {
        String b2 = sVar.b();
        StringBuilder sb = o.get();
        sb.ensureCapacity(b2.length() + 13);
        sb.replace(13, sb.length(), b2);
        Thread.currentThread().setName(sb.toString());
    }
}
