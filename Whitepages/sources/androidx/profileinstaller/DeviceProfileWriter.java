package androidx.profileinstaller;

import android.content.res.AssetManager;
import android.os.Build;
import androidx.profileinstaller.ProfileInstaller;
import com.facebook.react.uimanager.drawable.InsetBoxShadowDrawableKt;
import com.facebook.react.uimanager.drawable.OutsetBoxShadowDrawableKt;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

public class DeviceProfileWriter {
    private final String mApkName;
    private final AssetManager mAssetManager;
    private final File mCurProfile;
    private final byte[] mDesiredVersion;
    private boolean mDeviceSupportsAotProfile = false;
    private final ProfileInstaller.DiagnosticsCallback mDiagnostics;
    private final Executor mExecutor;
    private DexProfileData[] mProfile;
    private final String mProfileMetaSourceLocation;
    private final String mProfileSourceLocation;
    private byte[] mTranscodedProfile;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$result$0(int i, Object obj) {
        this.mDiagnostics.onResultReceived(i, obj);
    }

    private void result(int i, Object obj) {
        this.mExecutor.execute(new DeviceProfileWriter$$ExternalSyntheticLambda0(this, i, obj));
    }

    public DeviceProfileWriter(AssetManager assetManager, Executor executor, ProfileInstaller.DiagnosticsCallback diagnosticsCallback, String str, String str2, String str3, File file) {
        this.mAssetManager = assetManager;
        this.mExecutor = executor;
        this.mDiagnostics = diagnosticsCallback;
        this.mApkName = str;
        this.mProfileSourceLocation = str2;
        this.mProfileMetaSourceLocation = str3;
        this.mCurProfile = file;
        this.mDesiredVersion = desiredVersion();
    }

    public boolean deviceAllowsProfileInstallerAotWrites() {
        if (this.mDesiredVersion == null) {
            result(3, Integer.valueOf(Build.VERSION.SDK_INT));
            return false;
        }
        if (!this.mCurProfile.exists()) {
            try {
                this.mCurProfile.createNewFile();
            } catch (IOException unused) {
                result(4, (Object) null);
                return false;
            }
        } else if (!this.mCurProfile.canWrite()) {
            result(4, (Object) null);
            return false;
        }
        this.mDeviceSupportsAotProfile = true;
        return true;
    }

    private void assertDeviceAllowsProfileInstallerAotWritesCalled() {
        if (!this.mDeviceSupportsAotProfile) {
            throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        r0 = addMetadata(r0, r2.mDesiredVersion);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.profileinstaller.DeviceProfileWriter read() {
        /*
            r2 = this;
            r2.assertDeviceAllowsProfileInstallerAotWritesCalled()
            byte[] r0 = r2.mDesiredVersion
            if (r0 != 0) goto L_0x0008
            return r2
        L_0x0008:
            android.content.res.AssetManager r0 = r2.mAssetManager
            java.io.InputStream r0 = r2.getProfileInputStream(r0)
            if (r0 == 0) goto L_0x0016
            androidx.profileinstaller.DexProfileData[] r0 = r2.readProfileInternal(r0)
            r2.mProfile = r0
        L_0x0016:
            androidx.profileinstaller.DexProfileData[] r0 = r2.mProfile
            if (r0 == 0) goto L_0x0029
            boolean r1 = requiresMetadata()
            if (r1 == 0) goto L_0x0029
            byte[] r1 = r2.mDesiredVersion
            androidx.profileinstaller.DeviceProfileWriter r0 = r2.addMetadata(r0, r1)
            if (r0 == 0) goto L_0x0029
            return r0
        L_0x0029:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.profileinstaller.DeviceProfileWriter.read():androidx.profileinstaller.DeviceProfileWriter");
    }

    private InputStream openStreamFromAssets(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            if (message != null && message.contains("compressed")) {
                this.mDiagnostics.onDiagnosticReceived(5, (Object) null);
            }
            return null;
        }
    }

    private InputStream getProfileInputStream(AssetManager assetManager) {
        try {
            return openStreamFromAssets(assetManager, this.mProfileSourceLocation);
        } catch (FileNotFoundException e) {
            this.mDiagnostics.onResultReceived(6, e);
            return null;
        } catch (IOException e2) {
            this.mDiagnostics.onResultReceived(7, e2);
            return null;
        }
    }

    private DexProfileData[] readProfileInternal(InputStream inputStream) {
        try {
            DexProfileData[] readProfile = ProfileTranscoder.readProfile(inputStream, ProfileTranscoder.readHeader(inputStream, ProfileTranscoder.MAGIC_PROF), this.mApkName);
            try {
                inputStream.close();
                return readProfile;
            } catch (IOException e) {
                this.mDiagnostics.onResultReceived(7, e);
                return readProfile;
            }
        } catch (IOException e2) {
            this.mDiagnostics.onResultReceived(7, e2);
            inputStream.close();
            return null;
        } catch (IllegalStateException e3) {
            this.mDiagnostics.onResultReceived(8, e3);
            try {
                inputStream.close();
            } catch (IOException e4) {
                this.mDiagnostics.onResultReceived(7, e4);
            }
            return null;
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e5) {
                this.mDiagnostics.onResultReceived(7, e5);
            }
            throw th;
        }
    }

    private DeviceProfileWriter addMetadata(DexProfileData[] dexProfileDataArr, byte[] bArr) {
        InputStream openStreamFromAssets;
        try {
            openStreamFromAssets = openStreamFromAssets(this.mAssetManager, this.mProfileMetaSourceLocation);
            if (openStreamFromAssets != null) {
                this.mProfile = ProfileTranscoder.readMeta(openStreamFromAssets, ProfileTranscoder.readHeader(openStreamFromAssets, ProfileTranscoder.MAGIC_PROFM), bArr, dexProfileDataArr);
                openStreamFromAssets.close();
                return this;
            }
            if (openStreamFromAssets != null) {
                openStreamFromAssets.close();
            }
            return null;
        } catch (FileNotFoundException e) {
            this.mDiagnostics.onResultReceived(9, e);
        } catch (IOException e2) {
            this.mDiagnostics.onResultReceived(7, e2);
        } catch (IllegalStateException e3) {
            this.mProfile = null;
            this.mDiagnostics.onResultReceived(8, e3);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public DeviceProfileWriter transcodeIfNeeded() {
        ByteArrayOutputStream byteArrayOutputStream;
        DexProfileData[] dexProfileDataArr = this.mProfile;
        byte[] bArr = this.mDesiredVersion;
        if (!(dexProfileDataArr == null || bArr == null)) {
            assertDeviceAllowsProfileInstallerAotWritesCalled();
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                ProfileTranscoder.writeHeader(byteArrayOutputStream, bArr);
                if (!ProfileTranscoder.transcodeAndWriteBody(byteArrayOutputStream, bArr, dexProfileDataArr)) {
                    this.mDiagnostics.onResultReceived(5, (Object) null);
                    this.mProfile = null;
                    byteArrayOutputStream.close();
                    return this;
                }
                this.mTranscodedProfile = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                this.mProfile = null;
            } catch (IOException e) {
                this.mDiagnostics.onResultReceived(7, e);
            } catch (IllegalStateException e2) {
                this.mDiagnostics.onResultReceived(8, e2);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return this;
        throw th;
    }

    public boolean write() {
        ByteArrayInputStream byteArrayInputStream;
        FileOutputStream fileOutputStream;
        byte[] bArr = this.mTranscodedProfile;
        if (bArr == null) {
            return false;
        }
        assertDeviceAllowsProfileInstallerAotWritesCalled();
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            fileOutputStream = new FileOutputStream(this.mCurProfile);
            Encoding.writeAll(byteArrayInputStream, fileOutputStream);
            result(1, (Object) null);
            fileOutputStream.close();
            byteArrayInputStream.close();
            this.mTranscodedProfile = null;
            this.mProfile = null;
            return true;
        } catch (FileNotFoundException e) {
            result(6, e);
            this.mTranscodedProfile = null;
            this.mProfile = null;
            return false;
        } catch (IOException e2) {
            result(7, e2);
            this.mTranscodedProfile = null;
            this.mProfile = null;
            return false;
        } catch (Throwable th) {
            this.mTranscodedProfile = null;
            this.mProfile = null;
            throw th;
        }
        throw th;
        throw th;
    }

    private static byte[] desiredVersion() {
        int i = Build.VERSION.SDK_INT;
        if (i > 34) {
            return null;
        }
        switch (i) {
            case 26:
                return ProfileVersion.V005_O;
            case 27:
                return ProfileVersion.V009_O_MR1;
            case OutsetBoxShadowDrawableKt.MIN_OUTSET_BOX_SHADOW_SDK_VERSION /*28*/:
            case InsetBoxShadowDrawableKt.MIN_INSET_BOX_SHADOW_SDK_VERSION /*29*/:
            case 30:
                return ProfileVersion.V010_P;
            case 31:
            case 32:
            case 33:
            case 34:
                return ProfileVersion.V015_S;
            default:
                return null;
        }
    }

    private static boolean requiresMetadata() {
        int i = Build.VERSION.SDK_INT;
        if (i > 34) {
            return false;
        }
        switch (i) {
            case 31:
            case 32:
            case 33:
            case 34:
                return true;
            default:
                return false;
        }
    }
}
