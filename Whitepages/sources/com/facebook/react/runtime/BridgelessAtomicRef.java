package com.facebook.react.runtime;

import com.facebook.infer.annotation.Assertions;

class BridgelessAtomicRef<T> {
    private volatile String failureMessage;
    T mInitialValue;
    volatile T mValue;
    private volatile State state;

    interface Provider<T> {
        T get();
    }

    enum State {
        Init,
        Creating,
        Success,
        Failure
    }

    public BridgelessAtomicRef(T t) {
        this.mValue = t;
        this.mInitialValue = t;
        this.state = State.Init;
        this.failureMessage = "";
    }

    public BridgelessAtomicRef() {
        this((Object) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0024, code lost:
        if (r0 == false) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r5.mValue = r6.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002c, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r5.state = r1;
        notifyAll();
        r6 = get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0036, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0037, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003b, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x003c, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r5.state = com.facebook.react.runtime.BridgelessAtomicRef.State.Failure;
        r5.failureMessage = java.util.Objects.toString(r6.getMessage(), "null");
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0058, code lost:
        throw new java.lang.RuntimeException("BridgelessAtomicRef: Failed to create object.", r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x005c, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0061, code lost:
        if (r5.state != com.facebook.react.runtime.BridgelessAtomicRef.State.Creating) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        wait();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0069, code lost:
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x006b, code lost:
        if (r4 == false) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0078, code lost:
        if (r5.state == com.facebook.react.runtime.BridgelessAtomicRef.State.Failure) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x007a, code lost:
        r6 = get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x007e, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x007f, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0098, code lost:
        throw new java.lang.RuntimeException("BridgelessAtomicRef: Failed to create object. Reason: " + r5.failureMessage);
     */
    @android.annotation.SuppressLint({"CatchGeneralException"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public T getOrCreate(com.facebook.react.runtime.BridgelessAtomicRef.Provider<T> r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            com.facebook.react.runtime.BridgelessAtomicRef$State r0 = r5.state     // Catch:{ all -> 0x000d }
            com.facebook.react.runtime.BridgelessAtomicRef$State r1 = com.facebook.react.runtime.BridgelessAtomicRef.State.Success     // Catch:{ all -> 0x000d }
            if (r0 != r1) goto L_0x0010
            java.lang.Object r6 = r5.get()     // Catch:{ all -> 0x000d }
            monitor-exit(r5)     // Catch:{ all -> 0x000d }
            return r6
        L_0x000d:
            r6 = move-exception
            goto L_0x00b4
        L_0x0010:
            com.facebook.react.runtime.BridgelessAtomicRef$State r0 = r5.state     // Catch:{ all -> 0x000d }
            com.facebook.react.runtime.BridgelessAtomicRef$State r2 = com.facebook.react.runtime.BridgelessAtomicRef.State.Failure     // Catch:{ all -> 0x000d }
            if (r0 == r2) goto L_0x009b
            com.facebook.react.runtime.BridgelessAtomicRef$State r0 = r5.state     // Catch:{ all -> 0x000d }
            com.facebook.react.runtime.BridgelessAtomicRef$State r2 = com.facebook.react.runtime.BridgelessAtomicRef.State.Creating     // Catch:{ all -> 0x000d }
            r3 = 1
            r4 = 0
            if (r0 == r2) goto L_0x0022
            r5.state = r2     // Catch:{ all -> 0x000d }
            r0 = r3
            goto L_0x0023
        L_0x0022:
            r0 = r4
        L_0x0023:
            monitor-exit(r5)     // Catch:{ all -> 0x000d }
            if (r0 == 0) goto L_0x005c
            java.lang.Object r6 = r6.get()     // Catch:{ RuntimeException -> 0x003b }
            r5.mValue = r6     // Catch:{ RuntimeException -> 0x003b }
            monitor-enter(r5)     // Catch:{ RuntimeException -> 0x003b }
            r5.state = r1     // Catch:{ all -> 0x0038 }
            r5.notifyAll()     // Catch:{ all -> 0x0038 }
            java.lang.Object r6 = r5.get()     // Catch:{ all -> 0x0038 }
            monitor-exit(r5)     // Catch:{ all -> 0x0038 }
            return r6
        L_0x0038:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0038 }
            throw r6     // Catch:{ RuntimeException -> 0x003b }
        L_0x003b:
            r6 = move-exception
            monitor-enter(r5)
            com.facebook.react.runtime.BridgelessAtomicRef$State r0 = com.facebook.react.runtime.BridgelessAtomicRef.State.Failure     // Catch:{ all -> 0x0059 }
            r5.state = r0     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = r6.getMessage()     // Catch:{ all -> 0x0059 }
            java.lang.String r1 = "null"
            java.lang.String r0 = java.util.Objects.toString(r0, r1)     // Catch:{ all -> 0x0059 }
            r5.failureMessage = r0     // Catch:{ all -> 0x0059 }
            r5.notifyAll()     // Catch:{ all -> 0x0059 }
            monitor-exit(r5)     // Catch:{ all -> 0x0059 }
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "BridgelessAtomicRef: Failed to create object."
            r0.<init>(r1, r6)
            throw r0
        L_0x0059:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0059 }
            throw r6
        L_0x005c:
            monitor-enter(r5)
        L_0x005d:
            com.facebook.react.runtime.BridgelessAtomicRef$State r6 = r5.state     // Catch:{ all -> 0x0067 }
            com.facebook.react.runtime.BridgelessAtomicRef$State r0 = com.facebook.react.runtime.BridgelessAtomicRef.State.Creating     // Catch:{ all -> 0x0067 }
            if (r6 != r0) goto L_0x006b
            r5.wait()     // Catch:{ InterruptedException -> 0x0069 }
            goto L_0x005d
        L_0x0067:
            r6 = move-exception
            goto L_0x0099
        L_0x0069:
            r4 = r3
            goto L_0x005d
        L_0x006b:
            if (r4 == 0) goto L_0x0074
            java.lang.Thread r6 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0067 }
            r6.interrupt()     // Catch:{ all -> 0x0067 }
        L_0x0074:
            com.facebook.react.runtime.BridgelessAtomicRef$State r6 = r5.state     // Catch:{ all -> 0x0067 }
            com.facebook.react.runtime.BridgelessAtomicRef$State r0 = com.facebook.react.runtime.BridgelessAtomicRef.State.Failure     // Catch:{ all -> 0x0067 }
            if (r6 == r0) goto L_0x0080
            java.lang.Object r6 = r5.get()     // Catch:{ all -> 0x0067 }
            monitor-exit(r5)     // Catch:{ all -> 0x0067 }
            return r6
        L_0x0080:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch:{ all -> 0x0067 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0067 }
            r0.<init>()     // Catch:{ all -> 0x0067 }
            java.lang.String r1 = "BridgelessAtomicRef: Failed to create object. Reason: "
            r0.append(r1)     // Catch:{ all -> 0x0067 }
            java.lang.String r1 = r5.failureMessage     // Catch:{ all -> 0x0067 }
            r0.append(r1)     // Catch:{ all -> 0x0067 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0067 }
            r6.<init>(r0)     // Catch:{ all -> 0x0067 }
            throw r6     // Catch:{ all -> 0x0067 }
        L_0x0099:
            monitor-exit(r5)     // Catch:{ all -> 0x0067 }
            throw r6
        L_0x009b:
            java.lang.RuntimeException r6 = new java.lang.RuntimeException     // Catch:{ all -> 0x000d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x000d }
            r0.<init>()     // Catch:{ all -> 0x000d }
            java.lang.String r1 = "BridgelessAtomicRef: Failed to create object. Reason: "
            r0.append(r1)     // Catch:{ all -> 0x000d }
            java.lang.String r1 = r5.failureMessage     // Catch:{ all -> 0x000d }
            r0.append(r1)     // Catch:{ all -> 0x000d }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x000d }
            r6.<init>(r0)     // Catch:{ all -> 0x000d }
            throw r6     // Catch:{ all -> 0x000d }
        L_0x00b4:
            monitor-exit(r5)     // Catch:{ all -> 0x000d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.runtime.BridgelessAtomicRef.getOrCreate(com.facebook.react.runtime.BridgelessAtomicRef$Provider):java.lang.Object");
    }

    public synchronized T getAndReset() {
        T t;
        t = get();
        reset();
        return t;
    }

    public synchronized void reset() {
        this.mValue = this.mInitialValue;
        this.state = State.Init;
        this.failureMessage = "";
    }

    public synchronized T get() {
        return Assertions.assertNotNull(this.mValue);
    }

    public synchronized T getNullable() {
        return this.mValue;
    }
}
