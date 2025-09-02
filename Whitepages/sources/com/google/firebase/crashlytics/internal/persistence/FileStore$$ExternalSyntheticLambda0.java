package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;
import java.io.FilenameFilter;

public final /* synthetic */ class FileStore$$ExternalSyntheticLambda0 implements FilenameFilter {
    public final /* synthetic */ String f$0;

    public /* synthetic */ FileStore$$ExternalSyntheticLambda0(String str) {
        this.f$0 = str;
    }

    public final boolean accept(File file, String str) {
        return str.startsWith(this.f$0);
    }
}
