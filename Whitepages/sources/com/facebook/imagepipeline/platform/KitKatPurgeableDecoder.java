package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;

@TargetApi(19)
@DoNotStrip
public class KitKatPurgeableDecoder extends DalvikPurgeableDecoder {
    private final FlexByteArrayPool mFlexByteArrayPool;

    @DoNotStrip
    public KitKatPurgeableDecoder(FlexByteArrayPool flexByteArrayPool) {
    }

    /* access modifiers changed from: protected */
    public Bitmap decodeByteArrayAsPurgeable(CloseableReference closeableReference, BitmapFactory.Options options) {
        PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) closeableReference.get();
        int size = pooledByteBuffer.size();
        CloseableReference closeableReference2 = this.mFlexByteArrayPool.get(size);
        try {
            byte[] bArr = (byte[]) closeableReference2.get();
            pooledByteBuffer.read(0, bArr, 0, size);
            return (Bitmap) Preconditions.checkNotNull(BitmapFactory.decodeByteArray(bArr, 0, size, options), "BitmapFactory returned null");
        } finally {
            CloseableReference.closeSafely(closeableReference2);
        }
    }

    /* access modifiers changed from: protected */
    public Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference closeableReference, int i, BitmapFactory.Options options) {
        byte[] bArr = DalvikPurgeableDecoder.endsWithEOI(closeableReference, i) ? null : DalvikPurgeableDecoder.EOI;
        PooledByteBuffer pooledByteBuffer = (PooledByteBuffer) closeableReference.get();
        Preconditions.checkArgument(Boolean.valueOf(i <= pooledByteBuffer.size()));
        int i2 = i + 2;
        CloseableReference closeableReference2 = this.mFlexByteArrayPool.get(i2);
        try {
            byte[] bArr2 = (byte[]) closeableReference2.get();
            pooledByteBuffer.read(0, bArr2, 0, i);
            if (bArr != null) {
                putEOI(bArr2, i);
                i = i2;
            }
            Bitmap bitmap = (Bitmap) Preconditions.checkNotNull(BitmapFactory.decodeByteArray(bArr2, 0, i, options), "BitmapFactory returned null");
            CloseableReference.closeSafely(closeableReference2);
            return bitmap;
        } catch (Throwable th) {
            CloseableReference.closeSafely(closeableReference2);
            throw th;
        }
    }

    private static void putEOI(byte[] bArr, int i) {
        bArr[i] = -1;
        bArr[i + 1] = -39;
    }
}
