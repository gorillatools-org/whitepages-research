package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import android.os.StrictMode;
import com.facebook.soloader.ExtractFromZipSoSource;
import com.facebook.soloader.UnpackingSoSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class BackupSoSource extends UnpackingSoSource implements RecoverableSoSource {
    protected boolean mInitialized;
    /* access modifiers changed from: private */
    public final ArrayList mZipSources;

    public BackupSoSource(Context context, String str, boolean z) {
        super(context, str, z);
        ArrayList arrayList = new ArrayList();
        this.mZipSources = arrayList;
        this.mInitialized = false;
        arrayList.add(new ExtractFromZipSoSource(context, str, new File(context.getApplicationInfo().sourceDir), "^lib/([^/]+)/([^/]+\\.so)$"));
        addBackupsFromSplitApks(context, str);
    }

    public BackupSoSource(Context context, String str) {
        this(context, str, true);
    }

    private void addBackupsFromSplitApks(Context context, String str) {
        if (context.getApplicationInfo().splitSourceDirs != null) {
            try {
                for (String file : context.getApplicationInfo().splitSourceDirs) {
                    ExtractFromZipSoSource extractFromZipSoSource = new ExtractFromZipSoSource(context, str, new File(file), "^lib/([^/]+)/([^/]+\\.so)$");
                    if (extractFromZipSoSource.hasZippedLibs()) {
                        LogUtil.w("BackupSoSource", "adding backup source from split: " + extractFromZipSoSource.toString());
                        this.mZipSources.add(extractFromZipSoSource);
                    }
                }
            } catch (IOException e) {
                LogUtil.w("BackupSoSource", "failed to read split apks", e);
            }
        }
    }

    public String getName() {
        return "BackupSoSource";
    }

    /* access modifiers changed from: protected */
    public UnpackingSoSource.Unpacker makeUnpacker() {
        return new ApkUnpacker();
    }

    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        if (!this.mInitialized) {
            return 0;
        }
        return super.loadLibrary(str, i, threadPolicy);
    }

    public void prepare(int i) {
        if ((i & 8) == 0) {
            super.prepare(i);
            this.mInitialized = true;
        }
    }

    public boolean peekAndPrepareSoSource(String str, int i) {
        boolean z;
        UnpackingSoSource.Unpacker makeUnpacker = makeUnpacker();
        try {
            UnpackingSoSource.Dso[] dsos = makeUnpacker.getDsos();
            int length = dsos.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z = false;
                    break;
                } else if (dsos[i2].name.equals(str)) {
                    LogUtil.e("SoLoader", "Found " + str + " in " + getName());
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            makeUnpacker.close();
            if (!z) {
                return false;
            }
            LogUtil.e("SoLoader", "Preparing " + getName());
            prepare(i);
            return true;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public UnpackingSoSource.Dso[] getDsosBaseApk() {
        UnpackingSoSource.Unpacker makeUnpacker = ((ExtractFromZipSoSource) this.mZipSources.get(0)).makeUnpacker();
        try {
            UnpackingSoSource.Dso[] dsos = makeUnpacker.getDsos();
            makeUnpacker.close();
            return dsos;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    protected class ApkUnpacker extends UnpackingSoSource.Unpacker {
        protected ApkUnpacker() {
        }

        public UnpackingSoSource.Dso[] getDsos() {
            ArrayList arrayList = new ArrayList();
            Iterator it = BackupSoSource.this.mZipSources.iterator();
            while (it.hasNext()) {
                UnpackingSoSource.Unpacker makeUnpacker = ((ExtractFromZipSoSource) it.next()).makeUnpacker();
                try {
                    arrayList.addAll(Arrays.asList(makeUnpacker.getDsos()));
                    makeUnpacker.close();
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            return (UnpackingSoSource.Dso[]) arrayList.toArray(new UnpackingSoSource.Dso[arrayList.size()]);
            throw th;
        }

        public void unpack(File file) {
            Iterator it = BackupSoSource.this.mZipSources.iterator();
            while (it.hasNext()) {
                ExtractFromZipSoSource.ZipUnpacker zipUnpacker = (ExtractFromZipSoSource.ZipUnpacker) ((ExtractFromZipSoSource) it.next()).makeUnpacker();
                try {
                    zipUnpacker.unpack(file);
                    zipUnpacker.close();
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            return;
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public byte[] getDepsBlock() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeByte((byte) 3);
            obtain.writeInt(SysUtil.getAppVersionCode(this.mContext));
            obtain.writeInt(this.mZipSources.size());
            Iterator it = this.mZipSources.iterator();
            while (it.hasNext()) {
                obtain.writeByteArray(((ExtractFromZipSoSource) it.next()).getDepsBlock());
            }
            String str = this.mContext.getApplicationInfo().sourceDir;
            if (str == null) {
                obtain.writeByte((byte) 1);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                return marshall;
            }
            File canonicalFile = new File(str).getCanonicalFile();
            if (!canonicalFile.exists()) {
                obtain.writeByte((byte) 1);
                byte[] marshall2 = obtain.marshall();
                obtain.recycle();
                return marshall2;
            }
            obtain.writeByte((byte) 2);
            obtain.writeString(canonicalFile.getPath());
            obtain.writeLong(canonicalFile.lastModified());
            byte[] marshall3 = obtain.marshall();
            obtain.recycle();
            return marshall3;
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }

    public SoSource recover(Context context) {
        BackupSoSource backupSoSource = new BackupSoSource(context, this.soDirectory.getName());
        try {
            backupSoSource.prepare(0);
            return backupSoSource;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        String str;
        try {
            str = String.valueOf(this.soDirectory.getCanonicalPath());
        } catch (IOException unused) {
            str = this.soDirectory.getName();
        }
        return getName() + "[root = " + str + " flags = " + this.flags + " apks = " + this.mZipSources.toString() + "]";
    }
}
