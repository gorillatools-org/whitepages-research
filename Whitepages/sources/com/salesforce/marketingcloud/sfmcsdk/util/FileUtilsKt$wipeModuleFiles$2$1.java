package com.salesforce.marketingcloud.sfmcsdk.util;

import java.io.File;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class FileUtilsKt$wipeModuleFiles$2$1 extends Lambda implements Function0 {
    final /* synthetic */ File $it;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileUtilsKt$wipeModuleFiles$2$1(File file) {
        super(0);
        this.$it = file;
    }

    public final String invoke() {
        return "Deleting Database File: " + this.$it.getName();
    }
}
