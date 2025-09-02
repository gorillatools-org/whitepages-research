package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SyncFailedException;
import java.util.Arrays;

public abstract class UnpackingSoSource extends DirectorySoSource implements AsyncInitSoSource {
    private String[] mAbis;
    protected final Context mContext;

    private static boolean forceRefresh(int i) {
        return (i & 2) != 0;
    }

    private static boolean rewriteStateAsync(int i) {
        return (i & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public abstract Unpacker makeUnpacker();

    protected UnpackingSoSource(Context context, String str, boolean z) {
        super(getSoStorePath(context, str), z ? 1 : 0);
        this.mContext = context;
    }

    protected UnpackingSoSource(Context context, String str) {
        this(context, str, true);
    }

    public static File getSoStorePath(Context context, String str) {
        return new File(context.getApplicationInfo().dataDir + RemoteSettings.FORWARD_SLASH_STRING + str);
    }

    public Dso[] getDsosBaseApk() {
        Unpacker makeUnpacker = makeUnpacker();
        try {
            Dso[] dsos = makeUnpacker.getDsos();
            makeUnpacker.close();
            return dsos;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void setSoSourceAbis(String[] strArr) {
        this.mAbis = strArr;
    }

    public static class Dso {
        public final String hash;
        public final String name;

        public Dso(String str, String str2) {
            this.name = str;
            this.hash = str2;
        }
    }

    protected static final class InputDso implements Closeable {
        /* access modifiers changed from: private */
        public final InputStream content;
        private final Dso dso;

        public InputDso(Dso dso2, InputStream inputStream) {
            this.dso = dso2;
            this.content = inputStream;
        }

        public Dso getDso() {
            return this.dso;
        }

        public int available() {
            return this.content.available();
        }

        public void close() {
            this.content.close();
        }
    }

    /* access modifiers changed from: private */
    public static void writeState(File file, byte b, boolean z) {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0);
            randomAccessFile.write(b);
            randomAccessFile.setLength(randomAccessFile.getFilePointer());
            if (z) {
                randomAccessFile.getFD().sync();
            }
            randomAccessFile.close();
            return;
        } catch (SyncFailedException e) {
            LogUtil.w("fb-UnpackingSoSource", "state file sync failed", e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void deleteSoFiles() {
        File[] listFiles = this.soDirectory.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return !str.equals("dso_state") && !str.equals("dso_lock") && !str.equals("dso_deps");
            }
        });
        if (listFiles != null) {
            for (File file : listFiles) {
                LogUtil.v("fb-UnpackingSoSource", "Deleting " + file);
                SysUtil.dumbDelete(file);
            }
            return;
        }
        throw new IOException("unable to list directory " + this.soDirectory);
    }

    protected static abstract class Unpacker implements Closeable {
        public void close() {
        }

        public abstract Dso[] getDsos();

        public abstract void unpack(File file);

        protected Unpacker() {
        }

        public void extractDso(InputDso inputDso, byte[] bArr, File file) {
            RandomAccessFile randomAccessFile;
            Throwable th;
            File file2 = file;
            LogUtil.i("fb-UnpackingSoSource", "extracting DSO " + inputDso.getDso().name);
            File file3 = new File(file2, inputDso.getDso().name);
            try {
                randomAccessFile = new RandomAccessFile(file3, "rw");
                int available = inputDso.available();
                if (available > 1) {
                    SysUtil.fallocateIfSupported(randomAccessFile.getFD(), (long) available);
                }
                SysUtil.copyBytes(randomAccessFile, inputDso.content, Integer.MAX_VALUE, bArr);
                randomAccessFile.setLength(randomAccessFile.getFilePointer());
                if (file3.setExecutable(true, false)) {
                    randomAccessFile.close();
                    if (file3.exists() && !file3.setWritable(false)) {
                        LogUtil.e("SoLoader", "Error removing " + file3 + " write permission from directory " + file2 + " (writable: " + file.canWrite() + ")");
                        return;
                    }
                    return;
                }
                throw new IOException("cannot make file executable: " + file3);
            } catch (IOException e) {
                try {
                    LogUtil.e("fb-UnpackingSoSource", "error extracting dso  " + file3 + " due to: " + e);
                    SysUtil.dumbDelete(file3);
                    throw e;
                } catch (Throwable th2) {
                    if (file3.exists() && !file3.setWritable(false)) {
                        LogUtil.e("SoLoader", "Error removing " + file3 + " write permission from directory " + file2 + " (writable: " + file.canWrite() + ")");
                    }
                    throw th2;
                }
            } catch (Throwable th3) {
                th.addSuppressed(th3);
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public boolean depsChanged(byte[] bArr, byte[] bArr2) {
        return !Arrays.equals(bArr, bArr2);
    }

    /* access modifiers changed from: protected */
    public boolean depsChanged(byte[] bArr) {
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(new File(this.soDirectory, "dso_deps"), "rw");
            if (randomAccessFile.length() == 0) {
                randomAccessFile.close();
                return true;
            }
            int length = (int) randomAccessFile.length();
            byte[] bArr2 = new byte[length];
            if (randomAccessFile.read(bArr2) != length) {
                LogUtil.v("fb-UnpackingSoSource", "short read of so store deps file: marking unclean");
                randomAccessFile.close();
                return true;
            }
            boolean depsChanged = depsChanged(bArr2, bArr);
            randomAccessFile.close();
            return depsChanged;
        } catch (IOException e) {
            LogUtil.w("fb-UnpackingSoSource", "failed to compare whether deps changed", e);
            return true;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private boolean refreshLocked(final FileLocker fileLocker, int i) {
        byte b;
        final File file = new File(this.soDirectory, "dso_state");
        byte[] depsBlock = getDepsBlock();
        if (forceRefresh(i) || depsChanged(depsBlock)) {
            b = 0;
        } else {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                if (randomAccessFile.length() == 1) {
                    try {
                        b = randomAccessFile.readByte();
                        if (b == 1) {
                            LogUtil.v("fb-UnpackingSoSource", "dso store " + this.soDirectory + " regeneration not needed: state file clean");
                            randomAccessFile.close();
                        }
                    } catch (IOException e) {
                        LogUtil.v("fb-UnpackingSoSource", "dso store " + this.soDirectory + " regeneration interrupted: " + e.getMessage());
                    }
                }
                b = 0;
                randomAccessFile.close();
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (b == 1) {
            return false;
        }
        final boolean z = (i & 4) == 0;
        LogUtil.v("fb-UnpackingSoSource", "so store dirty: regenerating");
        writeState(file, (byte) 0, z);
        deleteSoFiles();
        Unpacker makeUnpacker = makeUnpacker();
        try {
            makeUnpacker.unpack(this.soDirectory);
            makeUnpacker.close();
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(new File(this.soDirectory, "dso_deps"), "rw");
            try {
                randomAccessFile2.write(depsBlock);
                randomAccessFile2.setLength(randomAccessFile2.getFilePointer());
                randomAccessFile2.close();
                AnonymousClass2 r2 = new Runnable() {
                    public void run() {
                        LogUtil.v("fb-UnpackingSoSource", "starting syncer worker");
                        try {
                            if (z) {
                                SysUtil.fsyncAll(UnpackingSoSource.this.soDirectory);
                            }
                            UnpackingSoSource.writeState(file, (byte) 1, z);
                            LogUtil.v("fb-UnpackingSoSource", "releasing dso store lock for " + UnpackingSoSource.this.soDirectory + " (from syncer thread)");
                            fileLocker.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (Throwable th) {
                            LogUtil.v("fb-UnpackingSoSource", "releasing dso store lock for " + UnpackingSoSource.this.soDirectory + " (from syncer thread)");
                            fileLocker.close();
                            throw th;
                        }
                    }
                };
                if (rewriteStateAsync(i)) {
                    new Thread(r2, "SoSync:" + this.soDirectory.getName()).start();
                } else {
                    r2.run();
                }
                return true;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } catch (Throwable th3) {
            th.addSuppressed(th3);
        }
        throw th;
        throw th;
        throw th;
    }

    public void waitUntilInitCompleted() {
        try {
            FileLocker orCreateLockOnDir = SysUtil.getOrCreateLockOnDir(this.soDirectory, new File(this.soDirectory, "dso_lock"));
            if (orCreateLockOnDir != null) {
                orCreateLockOnDir.close();
            }
        } catch (Exception e) {
            LogUtil.e("fb-UnpackingSoSource", "Encountered exception during wait for unpacking trying to acquire file lock for " + getClass().getName() + " (" + this.soDirectory + "): ", e);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] getDepsBlock() {
        Parcel obtain = Parcel.obtain();
        Unpacker makeUnpacker = makeUnpacker();
        try {
            Dso[] dsos = makeUnpacker.getDsos();
            obtain.writeInt(dsos.length);
            for (Dso dso : dsos) {
                obtain.writeString(dso.name);
                obtain.writeString(dso.hash);
            }
            makeUnpacker.close();
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            return marshall;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0122 A[SYNTHETIC, Splitter:B:38:0x0122] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x013a A[Catch:{ all -> 0x00d5 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void prepare(int r14) {
        /*
            r13 = this;
            java.lang.String r0 = "error removing "
            java.lang.String r1 = " (syncer thread started)"
            java.lang.String r2 = "not releasing dso store lock for "
            java.lang.String r3 = "releasing dso store lock for "
            java.lang.String r4 = "fb-UnpackingSoSource"
            java.io.File r5 = r13.soDirectory
            com.facebook.soloader.SysUtil.mkdirOrThrow(r5)
            java.io.File r5 = r13.soDirectory
            boolean r5 = r5.canWrite()
            java.lang.String r6 = "error adding "
            r7 = 1
            java.lang.String r8 = " write permission"
            if (r5 != 0) goto L_0x0043
            java.io.File r5 = r13.soDirectory
            boolean r5 = r5.setWritable(r7)
            if (r5 == 0) goto L_0x0025
            goto L_0x0043
        L_0x0025:
            java.io.IOException r14 = new java.io.IOException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r6)
            java.io.File r1 = r13.soDirectory
            java.lang.String r1 = r1.getCanonicalPath()
            r0.append(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            r14.<init>(r0)
            throw r14
        L_0x0043:
            r5 = 0
            r9 = 0
            java.io.File r10 = new java.io.File     // Catch:{ all -> 0x011f }
            java.io.File r11 = r13.soDirectory     // Catch:{ all -> 0x011f }
            java.lang.String r12 = "dso_lock"
            r10.<init>(r11, r12)     // Catch:{ all -> 0x011f }
            java.io.File r11 = r13.soDirectory     // Catch:{ all -> 0x011f }
            com.facebook.soloader.FileLocker r10 = com.facebook.soloader.SysUtil.getOrCreateLockOnDir(r11, r10)     // Catch:{ all -> 0x011f }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r11.<init>()     // Catch:{ all -> 0x0099 }
            java.lang.String r12 = "locked dso store "
            r11.append(r12)     // Catch:{ all -> 0x0099 }
            java.io.File r12 = r13.soDirectory     // Catch:{ all -> 0x0099 }
            r11.append(r12)     // Catch:{ all -> 0x0099 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0099 }
            com.facebook.soloader.LogUtil.v(r4, r11)     // Catch:{ all -> 0x0099 }
            java.io.File r11 = r13.soDirectory     // Catch:{ all -> 0x0099 }
            boolean r11 = r11.canWrite()     // Catch:{ all -> 0x0099 }
            if (r11 != 0) goto L_0x009d
            java.io.File r11 = r13.soDirectory     // Catch:{ all -> 0x0099 }
            boolean r7 = r11.setWritable(r7)     // Catch:{ all -> 0x0099 }
            if (r7 == 0) goto L_0x007b
            goto L_0x009d
        L_0x007b:
            java.io.IOException r14 = new java.io.IOException     // Catch:{ all -> 0x0099 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r7.<init>()     // Catch:{ all -> 0x0099 }
            r7.append(r6)     // Catch:{ all -> 0x0099 }
            java.io.File r6 = r13.soDirectory     // Catch:{ all -> 0x0099 }
            java.lang.String r6 = r6.getCanonicalPath()     // Catch:{ all -> 0x0099 }
            r7.append(r6)     // Catch:{ all -> 0x0099 }
            r7.append(r8)     // Catch:{ all -> 0x0099 }
            java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x0099 }
            r14.<init>(r6)     // Catch:{ all -> 0x0099 }
            throw r14     // Catch:{ all -> 0x0099 }
        L_0x0099:
            r14 = move-exception
            r9 = r10
            goto L_0x0120
        L_0x009d:
            boolean r14 = r13.refreshLocked(r10, r14)     // Catch:{ all -> 0x0099 }
            if (r14 == 0) goto L_0x00a4
            goto L_0x00bb
        L_0x00a4:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0099 }
            r14.<init>()     // Catch:{ all -> 0x0099 }
            java.lang.String r6 = "dso store is up-to-date: "
            r14.append(r6)     // Catch:{ all -> 0x0099 }
            java.io.File r6 = r13.soDirectory     // Catch:{ all -> 0x0099 }
            r14.append(r6)     // Catch:{ all -> 0x0099 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0099 }
            com.facebook.soloader.LogUtil.i(r4, r14)     // Catch:{ all -> 0x0099 }
            r9 = r10
        L_0x00bb:
            if (r9 == 0) goto L_0x00d8
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d5 }
            r14.<init>()     // Catch:{ all -> 0x00d5 }
            r14.append(r3)     // Catch:{ all -> 0x00d5 }
            java.io.File r1 = r13.soDirectory     // Catch:{ all -> 0x00d5 }
            r14.append(r1)     // Catch:{ all -> 0x00d5 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x00d5 }
            com.facebook.soloader.LogUtil.v(r4, r14)     // Catch:{ all -> 0x00d5 }
            r9.close()     // Catch:{ all -> 0x00d5 }
            goto L_0x00ef
        L_0x00d5:
            r14 = move-exception
            goto L_0x0152
        L_0x00d8:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d5 }
            r14.<init>()     // Catch:{ all -> 0x00d5 }
            r14.append(r2)     // Catch:{ all -> 0x00d5 }
            java.io.File r2 = r13.soDirectory     // Catch:{ all -> 0x00d5 }
            r14.append(r2)     // Catch:{ all -> 0x00d5 }
            r14.append(r1)     // Catch:{ all -> 0x00d5 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x00d5 }
            com.facebook.soloader.LogUtil.v(r4, r14)     // Catch:{ all -> 0x00d5 }
        L_0x00ef:
            java.io.File r14 = r13.soDirectory
            boolean r14 = r14.canWrite()
            if (r14 == 0) goto L_0x011e
            java.io.File r14 = r13.soDirectory
            boolean r14 = r14.setWritable(r5)
            if (r14 == 0) goto L_0x0100
            goto L_0x011e
        L_0x0100:
            java.io.IOException r14 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.io.File r0 = r13.soDirectory
            java.lang.String r0 = r0.getCanonicalPath()
            r1.append(r0)
            r1.append(r8)
            java.lang.String r0 = r1.toString()
            r14.<init>(r0)
            throw r14
        L_0x011e:
            return
        L_0x011f:
            r14 = move-exception
        L_0x0120:
            if (r9 == 0) goto L_0x013a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d5 }
            r1.<init>()     // Catch:{ all -> 0x00d5 }
            r1.append(r3)     // Catch:{ all -> 0x00d5 }
            java.io.File r2 = r13.soDirectory     // Catch:{ all -> 0x00d5 }
            r1.append(r2)     // Catch:{ all -> 0x00d5 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00d5 }
            com.facebook.soloader.LogUtil.v(r4, r1)     // Catch:{ all -> 0x00d5 }
            r9.close()     // Catch:{ all -> 0x00d5 }
            goto L_0x0151
        L_0x013a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d5 }
            r3.<init>()     // Catch:{ all -> 0x00d5 }
            r3.append(r2)     // Catch:{ all -> 0x00d5 }
            java.io.File r2 = r13.soDirectory     // Catch:{ all -> 0x00d5 }
            r3.append(r2)     // Catch:{ all -> 0x00d5 }
            r3.append(r1)     // Catch:{ all -> 0x00d5 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x00d5 }
            com.facebook.soloader.LogUtil.v(r4, r1)     // Catch:{ all -> 0x00d5 }
        L_0x0151:
            throw r14     // Catch:{ all -> 0x00d5 }
        L_0x0152:
            java.io.File r1 = r13.soDirectory
            boolean r1 = r1.canWrite()
            if (r1 == 0) goto L_0x0180
            java.io.File r1 = r13.soDirectory
            boolean r1 = r1.setWritable(r5)
            if (r1 != 0) goto L_0x0180
            java.io.IOException r14 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.io.File r0 = r13.soDirectory
            java.lang.String r0 = r0.getCanonicalPath()
            r1.append(r0)
            r1.append(r8)
            java.lang.String r0 = r1.toString()
            r14.<init>(r0)
            throw r14
        L_0x0180:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.prepare(int):void");
    }
}
