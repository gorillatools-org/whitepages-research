package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import java.lang.reflect.InvocationTargetException;

public class PoolFactory {
    private MemoryChunkPool mAshmemMemoryChunkPool;
    private BitmapPool mBitmapPool;
    private MemoryChunkPool mBufferMemoryChunkPool;
    private final PoolConfig mConfig;
    private MemoryChunkPool mNativeMemoryChunkPool;
    private PooledByteBufferFactory mPooledByteBufferFactory;
    private PooledByteStreams mPooledByteStreams;
    private ByteArrayPool mSmallByteArrayPool;

    public PoolFactory(PoolConfig poolConfig) {
        this.mConfig = (PoolConfig) Preconditions.checkNotNull(poolConfig);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.imagepipeline.memory.BitmapPool getBitmapPool() {
        /*
            r5 = this;
            com.facebook.imagepipeline.memory.BitmapPool r0 = r5.mBitmapPool
            if (r0 != 0) goto L_0x00c5
            com.facebook.imagepipeline.memory.PoolConfig r0 = r5.mConfig
            java.lang.String r0 = r0.getBitmapPoolType()
            int r1 = r0.hashCode()
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r1) {
                case -1868884870: goto L_0x003d;
                case -1106578487: goto L_0x0033;
                case -404562712: goto L_0x0029;
                case -402149703: goto L_0x001f;
                case 95945896: goto L_0x0015;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x0047
        L_0x0015:
            java.lang.String r1 = "dummy"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0047
            r0 = 0
            goto L_0x0048
        L_0x001f:
            java.lang.String r1 = "dummy_with_tracking"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0047
            r0 = r4
            goto L_0x0048
        L_0x0029:
            java.lang.String r1 = "experimental"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0047
            r0 = r3
            goto L_0x0048
        L_0x0033:
            java.lang.String r1 = "legacy"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0047
            r0 = 4
            goto L_0x0048
        L_0x003d:
            java.lang.String r1 = "legacy_default_params"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0047
            r0 = r2
            goto L_0x0048
        L_0x0047:
            r0 = -1
        L_0x0048:
            if (r0 == 0) goto L_0x00be
            if (r0 == r4) goto L_0x00b6
            if (r0 == r3) goto L_0x008e
            if (r0 == r2) goto L_0x0070
            com.facebook.imagepipeline.memory.BucketsBitmapPool r0 = new com.facebook.imagepipeline.memory.BucketsBitmapPool
            com.facebook.imagepipeline.memory.PoolConfig r1 = r5.mConfig
            com.facebook.common.memory.MemoryTrimmableRegistry r1 = r1.getMemoryTrimmableRegistry()
            com.facebook.imagepipeline.memory.PoolConfig r2 = r5.mConfig
            com.facebook.imagepipeline.memory.PoolParams r2 = r2.getBitmapPoolParams()
            com.facebook.imagepipeline.memory.PoolConfig r3 = r5.mConfig
            com.facebook.imagepipeline.memory.PoolStatsTracker r3 = r3.getBitmapPoolStatsTracker()
            com.facebook.imagepipeline.memory.PoolConfig r4 = r5.mConfig
            boolean r4 = r4.isIgnoreBitmapPoolHardCap()
            r0.<init>(r1, r2, r3, r4)
            r5.mBitmapPool = r0
            goto L_0x00c5
        L_0x0070:
            com.facebook.imagepipeline.memory.BucketsBitmapPool r0 = new com.facebook.imagepipeline.memory.BucketsBitmapPool
            com.facebook.imagepipeline.memory.PoolConfig r1 = r5.mConfig
            com.facebook.common.memory.MemoryTrimmableRegistry r1 = r1.getMemoryTrimmableRegistry()
            com.facebook.imagepipeline.memory.PoolParams r2 = com.facebook.imagepipeline.memory.DefaultBitmapPoolParams.get()
            com.facebook.imagepipeline.memory.PoolConfig r3 = r5.mConfig
            com.facebook.imagepipeline.memory.PoolStatsTracker r3 = r3.getBitmapPoolStatsTracker()
            com.facebook.imagepipeline.memory.PoolConfig r4 = r5.mConfig
            boolean r4 = r4.isIgnoreBitmapPoolHardCap()
            r0.<init>(r1, r2, r3, r4)
            r5.mBitmapPool = r0
            goto L_0x00c5
        L_0x008e:
            com.facebook.imagepipeline.memory.LruBitmapPool r0 = new com.facebook.imagepipeline.memory.LruBitmapPool
            com.facebook.imagepipeline.memory.PoolConfig r1 = r5.mConfig
            int r1 = r1.getBitmapPoolMaxPoolSize()
            com.facebook.imagepipeline.memory.PoolConfig r2 = r5.mConfig
            int r2 = r2.getBitmapPoolMaxBitmapSize()
            com.facebook.imagepipeline.memory.NoOpPoolStatsTracker r3 = com.facebook.imagepipeline.memory.NoOpPoolStatsTracker.getInstance()
            com.facebook.imagepipeline.memory.PoolConfig r4 = r5.mConfig
            boolean r4 = r4.isRegisterLruBitmapPoolAsMemoryTrimmable()
            if (r4 == 0) goto L_0x00af
            com.facebook.imagepipeline.memory.PoolConfig r4 = r5.mConfig
            com.facebook.common.memory.MemoryTrimmableRegistry r4 = r4.getMemoryTrimmableRegistry()
            goto L_0x00b0
        L_0x00af:
            r4 = 0
        L_0x00b0:
            r0.<init>(r1, r2, r3, r4)
            r5.mBitmapPool = r0
            goto L_0x00c5
        L_0x00b6:
            com.facebook.imagepipeline.memory.DummyTrackingInUseBitmapPool r0 = new com.facebook.imagepipeline.memory.DummyTrackingInUseBitmapPool
            r0.<init>()
            r5.mBitmapPool = r0
            goto L_0x00c5
        L_0x00be:
            com.facebook.imagepipeline.memory.DummyBitmapPool r0 = new com.facebook.imagepipeline.memory.DummyBitmapPool
            r0.<init>()
            r5.mBitmapPool = r0
        L_0x00c5:
            com.facebook.imagepipeline.memory.BitmapPool r0 = r5.mBitmapPool
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.PoolFactory.getBitmapPool():com.facebook.imagepipeline.memory.BitmapPool");
    }

    public MemoryChunkPool getBufferMemoryChunkPool() {
        if (this.mBufferMemoryChunkPool == null) {
            try {
                this.mBufferMemoryChunkPool = BufferMemoryChunkPool.class.getConstructor(new Class[]{MemoryTrimmableRegistry.class, PoolParams.class, PoolStatsTracker.class}).newInstance(new Object[]{this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getMemoryChunkPoolParams(), this.mConfig.getMemoryChunkPoolStatsTracker()});
            } catch (ClassNotFoundException unused) {
                this.mBufferMemoryChunkPool = null;
            } catch (IllegalAccessException unused2) {
                this.mBufferMemoryChunkPool = null;
            } catch (InstantiationException unused3) {
                this.mBufferMemoryChunkPool = null;
            } catch (NoSuchMethodException unused4) {
                this.mBufferMemoryChunkPool = null;
            } catch (InvocationTargetException unused5) {
                this.mBufferMemoryChunkPool = null;
            }
        }
        return this.mBufferMemoryChunkPool;
    }

    public int getFlexByteArrayPoolMaxNumThreads() {
        return this.mConfig.getFlexByteArrayPoolParams().maxNumThreads;
    }

    public MemoryChunkPool getNativeMemoryChunkPool() {
        if (this.mNativeMemoryChunkPool == null) {
            try {
                this.mNativeMemoryChunkPool = NativeMemoryChunkPool.class.getConstructor(new Class[]{MemoryTrimmableRegistry.class, PoolParams.class, PoolStatsTracker.class}).newInstance(new Object[]{this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getMemoryChunkPoolParams(), this.mConfig.getMemoryChunkPoolStatsTracker()});
            } catch (ClassNotFoundException e) {
                FLog.e("PoolFactory", "", (Throwable) e);
                this.mNativeMemoryChunkPool = null;
            } catch (IllegalAccessException e2) {
                FLog.e("PoolFactory", "", (Throwable) e2);
                this.mNativeMemoryChunkPool = null;
            } catch (InstantiationException e3) {
                FLog.e("PoolFactory", "", (Throwable) e3);
                this.mNativeMemoryChunkPool = null;
            } catch (NoSuchMethodException e4) {
                FLog.e("PoolFactory", "", (Throwable) e4);
                this.mNativeMemoryChunkPool = null;
            } catch (InvocationTargetException e5) {
                FLog.e("PoolFactory", "", (Throwable) e5);
                this.mNativeMemoryChunkPool = null;
            }
        }
        return this.mNativeMemoryChunkPool;
    }

    private MemoryChunkPool getAshmemMemoryChunkPool() {
        if (this.mAshmemMemoryChunkPool == null) {
            try {
                this.mAshmemMemoryChunkPool = AshmemMemoryChunkPool.class.getConstructor(new Class[]{MemoryTrimmableRegistry.class, PoolParams.class, PoolStatsTracker.class}).newInstance(new Object[]{this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getMemoryChunkPoolParams(), this.mConfig.getMemoryChunkPoolStatsTracker()});
            } catch (ClassNotFoundException unused) {
                this.mAshmemMemoryChunkPool = null;
            } catch (IllegalAccessException unused2) {
                this.mAshmemMemoryChunkPool = null;
            } catch (InstantiationException unused3) {
                this.mAshmemMemoryChunkPool = null;
            } catch (NoSuchMethodException unused4) {
                this.mAshmemMemoryChunkPool = null;
            } catch (InvocationTargetException unused5) {
                this.mAshmemMemoryChunkPool = null;
            }
        }
        return this.mAshmemMemoryChunkPool;
    }

    public PooledByteBufferFactory getPooledByteBufferFactory(int i) {
        if (this.mPooledByteBufferFactory == null) {
            MemoryChunkPool memoryChunkPool = getMemoryChunkPool(i);
            Preconditions.checkNotNull(memoryChunkPool, "failed to get pool for chunk type: " + i);
            this.mPooledByteBufferFactory = new MemoryPooledByteBufferFactory(memoryChunkPool, getPooledByteStreams());
        }
        return this.mPooledByteBufferFactory;
    }

    public PooledByteStreams getPooledByteStreams() {
        if (this.mPooledByteStreams == null) {
            this.mPooledByteStreams = new PooledByteStreams(getSmallByteArrayPool());
        }
        return this.mPooledByteStreams;
    }

    public ByteArrayPool getSmallByteArrayPool() {
        if (this.mSmallByteArrayPool == null) {
            this.mSmallByteArrayPool = new GenericByteArrayPool(this.mConfig.getMemoryTrimmableRegistry(), this.mConfig.getSmallByteArrayPoolParams(), this.mConfig.getSmallByteArrayPoolStatsTracker());
        }
        return this.mSmallByteArrayPool;
    }

    private MemoryChunkPool getMemoryChunkPool(int i) {
        if (i == 0) {
            return getNativeMemoryChunkPool();
        }
        if (i == 1) {
            return getBufferMemoryChunkPool();
        }
        if (i == 2) {
            return getAshmemMemoryChunkPool();
        }
        throw new IllegalArgumentException("Invalid MemoryChunkType");
    }
}
