package com.facebook.soloader;

import android.os.StrictMode;

public class SystemLoadWrapperSoSource extends SoSource {
    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        try {
            System.loadLibrary(str.substring(3, str.length() - 3));
            return 1;
        } catch (Exception e) {
            LogUtil.e("SoLoader", "Error loading library: " + str, e);
            return 0;
        }
    }

    public String getName() {
        return "SystemLoadWrapperSoSource";
    }

    public String toString() {
        return getName() + "[" + SysUtil.getClassLoaderLdLoadLibrary() + "]";
    }
}
