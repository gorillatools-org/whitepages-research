package com.facebook.common.disk;

public class NoOpDiskTrimmableRegistry implements DiskTrimmableRegistry {
    private static NoOpDiskTrimmableRegistry sInstance;

    public void registerDiskTrimmable(DiskTrimmable diskTrimmable) {
    }

    private NoOpDiskTrimmableRegistry() {
    }

    public static synchronized NoOpDiskTrimmableRegistry getInstance() {
        NoOpDiskTrimmableRegistry noOpDiskTrimmableRegistry;
        synchronized (NoOpDiskTrimmableRegistry.class) {
            try {
                if (sInstance == null) {
                    sInstance = new NoOpDiskTrimmableRegistry();
                }
                noOpDiskTrimmableRegistry = sInstance;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return noOpDiskTrimmableRegistry;
    }
}
