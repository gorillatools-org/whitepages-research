package com.facebook.soloader;

import android.content.res.AssetManager;
import android.os.StrictMode;
import com.facebook.hermes.intl.Constants;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class DirectSplitSoSource extends SoSource {
    protected Set mLibs = null;
    protected Manifest mManifest = null;
    protected final String mSplitName;

    public DirectSplitSoSource(String str) {
        this.mSplitName = str;
    }

    /* access modifiers changed from: protected */
    public void prepare(int i) {
        AssetManager assets = SoLoader.sApplicationContext.getAssets();
        InputStream open = assets.open(this.mSplitName + ".soloader-manifest");
        try {
            this.mManifest = Manifest.read(open);
            if (open != null) {
                open.close();
            }
            this.mLibs = new HashSet(this.mManifest.libs);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        Set set = this.mLibs;
        if (set == null) {
            throw new IllegalStateException("prepare not called");
        } else if (set.contains(str)) {
            return loadLibraryImpl(str, i);
        } else {
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    public int loadLibraryImpl(String str, int i) {
        String libraryPath = getLibraryPath(str);
        libraryPath.getClass();
        System.load(libraryPath);
        return 1;
    }

    public String getLibraryPath(String str) {
        Set set = this.mLibs;
        if (set == null || this.mManifest == null) {
            throw new IllegalStateException("prepare not called");
        } else if (!set.contains(str)) {
            return null;
        } else {
            return getSplitPath(this.mSplitName) + "!/lib/" + this.mManifest.arch + RemoteSettings.FORWARD_SLASH_STRING + str;
        }
    }

    static String getSplitPath(String str) {
        if (Constants.SENSITIVITY_BASE.equals(str)) {
            return SoLoader.sApplicationContext.getApplicationInfo().sourceDir;
        }
        String[] strArr = SoLoader.sApplicationContext.getApplicationInfo().splitSourceDirs;
        if (strArr != null) {
            String str2 = "split_" + str + ".apk";
            for (String str3 : strArr) {
                if (str3.endsWith(str2)) {
                    return str3;
                }
            }
            throw new IllegalStateException("Could not find " + str + " split");
        }
        throw new IllegalStateException("No splits avaiable");
    }

    public String getName() {
        return "DirectSplitSoSource";
    }
}
