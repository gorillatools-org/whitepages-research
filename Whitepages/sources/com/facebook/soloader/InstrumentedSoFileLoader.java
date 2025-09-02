package com.facebook.soloader;

public class InstrumentedSoFileLoader implements SoFileLoader {
    private final SoFileLoader mDelegate;

    public InstrumentedSoFileLoader(SoFileLoader soFileLoader) {
        this.mDelegate = soFileLoader;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        com.facebook.soloader.observer.ObserverHolder.onSoFileLoaderLoadEnd(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void load(java.lang.String r3, int r4) {
        /*
            r2 = this;
            com.facebook.soloader.SoFileLoader r0 = r2.mDelegate
            java.lang.String r1 = "load"
            com.facebook.soloader.observer.ObserverHolder.onSoFileLoaderLoadStart(r0, r1, r4)
            com.facebook.soloader.SoFileLoader r0 = r2.mDelegate     // Catch:{ all -> 0x0011 }
            r0.load(r3, r4)     // Catch:{ all -> 0x0011 }
            r3 = 0
            com.facebook.soloader.observer.ObserverHolder.onSoFileLoaderLoadEnd(r3)
            return
        L_0x0011:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0013 }
        L_0x0013:
            r4 = move-exception
            com.facebook.soloader.observer.ObserverHolder.onSoFileLoaderLoadEnd(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.InstrumentedSoFileLoader.load(java.lang.String, int):void");
    }
}
