package com.salesforce.marketingcloud.sfmcsdk.util;

import java.io.File;
import java.io.FilenameFilter;

public final /* synthetic */ class FileUtilsKt$$ExternalSyntheticLambda1 implements FilenameFilter {
    public final /* synthetic */ String f$0;

    public /* synthetic */ FileUtilsKt$$ExternalSyntheticLambda1(String str) {
        this.f$0 = str;
    }

    public final boolean accept(File file, String str) {
        return FileUtilsKt.m816wipeModuleFiles$lambda2(this.f$0, file, str);
    }
}
