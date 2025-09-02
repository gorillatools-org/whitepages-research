package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import androidx.collection.LruCache;
import androidx.core.graphics.BitmapCompat;

@SuppressLint({"UnknownNullness"})
public class c {
    private final LruCache a;

    class a extends LruCache {
        a(int i) {
            super(i);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public int sizeOf(String str, b bVar) {
            return bVar.b;
        }
    }

    static final class b {
        final Bitmap a;
        final int b;

        b(Bitmap bitmap, int i) {
            this.a = bitmap;
            this.b = i;
        }
    }

    c(Context context) {
        this.a = new a(a(context));
    }

    private static int a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        return (int) ((((long) ((context.getApplicationInfo().flags & 1048576) != 0 ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass())) * 1048576) / 10);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.a.evictAll();
    }

    /* access modifiers changed from: package-private */
    public Bitmap a(String str) {
        b bVar = (b) this.a.get(str);
        if (bVar != null) {
            return bVar.a;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Bitmap bitmap) {
        if (str != null && bitmap != null) {
            int allocationByteCount = BitmapCompat.getAllocationByteCount(bitmap);
            if (allocationByteCount > this.a.maxSize()) {
                this.a.remove(str);
            } else {
                this.a.put(str, new b(bitmap, allocationByteCount));
            }
        }
    }
}
