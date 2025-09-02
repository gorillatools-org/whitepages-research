package com.google.firebase.crashlytics.ndk;

import java.io.File;
import java.io.FilenameFilter;

public final /* synthetic */ class JniNativeApi$$ExternalSyntheticLambda0 implements FilenameFilter {
    public final boolean accept(File file, String str) {
        return str.toLowerCase().endsWith(".apk");
    }
}
