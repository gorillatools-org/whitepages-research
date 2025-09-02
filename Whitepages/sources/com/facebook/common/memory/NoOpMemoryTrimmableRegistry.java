package com.facebook.common.memory;

public class NoOpMemoryTrimmableRegistry implements MemoryTrimmableRegistry {
    private static NoOpMemoryTrimmableRegistry sInstance;

    public void registerMemoryTrimmable(MemoryTrimmable memoryTrimmable) {
    }

    public static synchronized NoOpMemoryTrimmableRegistry getInstance() {
        NoOpMemoryTrimmableRegistry noOpMemoryTrimmableRegistry;
        synchronized (NoOpMemoryTrimmableRegistry.class) {
            try {
                if (sInstance == null) {
                    sInstance = new NoOpMemoryTrimmableRegistry();
                }
                noOpMemoryTrimmableRegistry = sInstance;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return noOpMemoryTrimmableRegistry;
    }
}
