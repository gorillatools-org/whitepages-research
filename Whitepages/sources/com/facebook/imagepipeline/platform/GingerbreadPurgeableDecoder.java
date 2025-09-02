package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.MemoryFile;
import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

@DoNotStrip
public class GingerbreadPurgeableDecoder extends DalvikPurgeableDecoder {
    private static Method sGetFileDescriptorMethod;

    @DoNotStrip
    public GingerbreadPurgeableDecoder() {
        WebpSupportStatus.loadWebpBitmapFactoryIfExists();
    }

    /* access modifiers changed from: protected */
    public Bitmap decodeByteArrayAsPurgeable(CloseableReference closeableReference, BitmapFactory.Options options) {
        return decodeFileDescriptorAsPurgeable(closeableReference, ((PooledByteBuffer) closeableReference.get()).size(), (byte[]) null, options);
    }

    /* access modifiers changed from: protected */
    public Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference closeableReference, int i, BitmapFactory.Options options) {
        return decodeFileDescriptorAsPurgeable(closeableReference, i, DalvikPurgeableDecoder.endsWithEOI(closeableReference, i) ? null : DalvikPurgeableDecoder.EOI, options);
    }

    private static MemoryFile copyToMemoryFile(CloseableReference closeableReference, int i, byte[] bArr) {
        Closeable closeable;
        LimitedInputStream limitedInputStream;
        PooledByteBufferInputStream pooledByteBufferInputStream = null;
        MemoryFile memoryFile = new MemoryFile((String) null, (bArr == null ? 0 : bArr.length) + i);
        memoryFile.allowPurging(false);
        try {
            PooledByteBufferInputStream pooledByteBufferInputStream2 = new PooledByteBufferInputStream((PooledByteBuffer) closeableReference.get());
            try {
                limitedInputStream = new LimitedInputStream(pooledByteBufferInputStream2, i);
                try {
                    OutputStream outputStream = memoryFile.getOutputStream();
                    ByteStreams.copy(limitedInputStream, outputStream);
                    if (bArr != null) {
                        memoryFile.writeBytes(bArr, 0, i, bArr.length);
                    }
                    CloseableReference.closeSafely(closeableReference);
                    Closeables.closeQuietly(pooledByteBufferInputStream2);
                    Closeables.closeQuietly(limitedInputStream);
                    Closeables.close(outputStream, true);
                    return memoryFile;
                } catch (Throwable th) {
                    th = th;
                    closeable = null;
                    pooledByteBufferInputStream = pooledByteBufferInputStream2;
                    CloseableReference.closeSafely(closeableReference);
                    Closeables.closeQuietly(pooledByteBufferInputStream);
                    Closeables.closeQuietly(limitedInputStream);
                    Closeables.close(closeable, true);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                closeable = null;
                limitedInputStream = null;
                pooledByteBufferInputStream = pooledByteBufferInputStream2;
                CloseableReference.closeSafely(closeableReference);
                Closeables.closeQuietly(pooledByteBufferInputStream);
                Closeables.closeQuietly(limitedInputStream);
                Closeables.close(closeable, true);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            closeable = null;
            limitedInputStream = null;
            CloseableReference.closeSafely(closeableReference);
            Closeables.closeQuietly(pooledByteBufferInputStream);
            Closeables.closeQuietly(limitedInputStream);
            Closeables.close(closeable, true);
            throw th;
        }
    }

    private synchronized Method getFileDescriptorMethod() {
        if (sGetFileDescriptorMethod == null) {
            try {
                sGetFileDescriptorMethod = MemoryFile.class.getDeclaredMethod("getFileDescriptor", (Class[]) null);
            } catch (Exception e) {
                throw Throwables.propagate(e);
            }
        }
        return sGetFileDescriptorMethod;
    }

    private FileDescriptor getMemoryFileDescriptor(MemoryFile memoryFile) {
        try {
            return (FileDescriptor) Preconditions.checkNotNull(getFileDescriptorMethod().invoke(memoryFile, (Object[]) null));
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    private Bitmap decodeFileDescriptorAsPurgeable(CloseableReference closeableReference, int i, byte[] bArr, BitmapFactory.Options options) {
        MemoryFile memoryFile = null;
        try {
            getMemoryFileDescriptor(copyToMemoryFile(closeableReference, i, bArr));
            throw new IllegalStateException("WebpBitmapFactory is null");
        } catch (IOException e) {
            throw Throwables.propagate(e);
        } catch (Throwable th) {
            if (memoryFile != null) {
                memoryFile.close();
            }
            throw th;
        }
    }
}
