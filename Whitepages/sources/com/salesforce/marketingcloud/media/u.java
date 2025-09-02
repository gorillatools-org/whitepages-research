package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.util.g;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@SuppressLint({"UnknownNullness"})
public abstract class u {

    public interface a {
        void a(b bVar);

        void a(Throwable th);
    }

    public static final class b {
        private final o.b a;
        private final Bitmap b;
        private final Drawable c;

        private b(Bitmap bitmap, Drawable drawable, o.b bVar) {
            this.b = bitmap;
            this.c = drawable;
            this.a = bVar;
        }

        public Bitmap a() {
            return this.b;
        }

        public Drawable b() {
            return this.c;
        }

        public o.b c() {
            return this.a;
        }

        public boolean d() {
            return this.b != null;
        }

        public boolean e() {
            return this.c != null;
        }

        public b(Bitmap bitmap, o.b bVar) {
            this(bitmap, (Drawable) null, bVar);
        }

        public b(Drawable drawable, o.b bVar) {
            this((Bitmap) null, drawable, bVar);
        }
    }

    static void a(int i, int i2, int i3, int i4, BitmapFactory.Options options) {
        int i5;
        double d;
        if (i4 > i2 || i3 > i) {
            if (i2 == 0) {
                d = (double) (((float) i3) / ((float) i));
            } else if (i == 0) {
                d = (double) (((float) i4) / ((float) i2));
            } else {
                i5 = Math.min((int) Math.floor((double) (((float) i4) / ((float) i2))), (int) Math.floor((double) (((float) i3) / ((float) i))));
            }
            i5 = (int) Math.floor(d);
        } else {
            i5 = 1;
        }
        options.inSampleSize = i5;
        options.inJustDecodeBounds = false;
    }

    static BitmapFactory.Options b(s sVar) {
        if (!sVar.d()) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        return options;
    }

    public abstract void a(o oVar, s sVar, a aVar) throws IOException;

    public abstract boolean a(s sVar);

    private static void a(int i, int i2, BitmapFactory.Options options) {
        a(i, i2, options.outWidth, options.outHeight, options);
    }

    static Bitmap a(InputStream inputStream, s sVar) throws IOException {
        BitmapFactory.Options b2 = b(sVar);
        boolean a2 = a(b2);
        byte[] a3 = g.a(inputStream);
        if (a2) {
            BitmapFactory.decodeStream(new ByteArrayInputStream(a3), (Rect) null, b2);
            a(sVar.e, sVar.f, b2);
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(a3), (Rect) null, b2);
        if (decodeStream != null) {
            return decodeStream;
        }
        throw new IOException("Failed to decode bitmap");
    }

    static boolean a(BitmapFactory.Options options) {
        return options != null && options.inJustDecodeBounds;
    }
}
