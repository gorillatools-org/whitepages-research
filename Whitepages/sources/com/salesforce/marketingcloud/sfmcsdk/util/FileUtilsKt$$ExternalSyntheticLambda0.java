package com.salesforce.marketingcloud.sfmcsdk.util;

import java.io.File;
import java.io.FilenameFilter;

public final /* synthetic */ class FileUtilsKt$$ExternalSyntheticLambda0 implements FilenameFilter {
    public final /* synthetic */ String f$0;

    public /* synthetic */ FileUtilsKt$$ExternalSyntheticLambda0(String str) {
        this.f$0 = str;
    }

    public final boolean accept(File file, String str) {
        return FileUtilsKt.m815wipeModuleFiles$lambda0(this.f$0, file, str);
    }
}
