package com.salesforce.marketingcloud.sfmcsdk.util;

import android.content.Context;
import com.salesforce.marketingcloud.sfmcsdk.components.http.RequestKt;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class FileUtilsKt {
    public static final String SDK_PACKAGE_PREFIX = "com.salesforce.marketingcloud";

    public static final String getFilenamePrefixForSFMCSdk(String str) {
        Intrinsics.checkNotNullParameter(str, "filename");
        return "com.salesforce.marketingcloud_sfmcsdk_" + str;
    }

    public static final String getFilenamePrefixForModule(String str) {
        Intrinsics.checkNotNullParameter(str, "moduleApplicationId");
        return "com.salesforce.marketingcloud_" + str;
    }

    public static final String getFilenamePrefixForModuleInstallation(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "moduleApplicationId");
        Intrinsics.checkNotNullParameter(str2, "registrationId");
        return getFilenamePrefixForModule(str) + '_' + str2;
    }

    public static final String getFilenameForModuleInstallation(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "filename");
        Intrinsics.checkNotNullParameter(str2, "moduleApplicationId");
        Intrinsics.checkNotNullParameter(str3, "registrationId");
        return getFilenamePrefixForModuleInstallation(str2, str3) + '_' + str;
    }

    public static final void storeModuleKey(Context context, String str, String str2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "moduleName");
        Intrinsics.checkNotNullParameter(str2, "keyValue");
        try {
            FilesKt.writeText$default(new File(context.getNoBackupFilesDir(), str), str2, (Charset) null, 2, (Object) null);
        } catch (Exception unused) {
            SFMCSdkLogger.INSTANCE.e("~$SFMCSdkStorage", FileUtilsKt$storeModuleKey$1.INSTANCE);
        }
    }

    public static final String retrieveModuleKey(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "moduleApplicationId");
        try {
            File file = new File(context.getNoBackupFilesDir(), str);
            if (file.exists()) {
                String readText$default = FilesKt.readText$default(file, (Charset) null, 1, (Object) null);
                if (!StringsKt.isBlank(readText$default)) {
                    return readText$default;
                }
            }
        } catch (Exception unused) {
            SFMCSdkLogger.INSTANCE.e("~$SFMCSdkStorage", FileUtilsKt$retrieveModuleKey$1.INSTANCE);
        }
        return null;
    }

    public static final void wipeModuleFiles(Context context, String str) {
        File[] listFiles;
        File[] listFiles2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "moduleApplicationId");
        try {
            File parentFile = context.getDatabasePath(str).getParentFile();
            if (!(parentFile == null || !parentFile.isDirectory() || (listFiles2 = parentFile.listFiles(new FileUtilsKt$$ExternalSyntheticLambda0(str))) == null)) {
                for (File file : listFiles2) {
                    SFMCSdkLogger.INSTANCE.w("~$SFMCSdkStorage", new FileUtilsKt$wipeModuleFiles$2$1(file));
                    file.delete();
                }
            }
        } catch (Exception unused) {
            SFMCSdkLogger.INSTANCE.w("~$SFMCSdkStorage", FileUtilsKt$wipeModuleFiles$3.INSTANCE);
        }
        try {
            File file2 = new File(context.getFilesDir().getParent() + "/shared_prefs");
            if (file2.isDirectory() && (listFiles = file2.listFiles(new FileUtilsKt$$ExternalSyntheticLambda1(str))) != null) {
                for (File file3 : listFiles) {
                    SFMCSdkLogger.INSTANCE.w("~$SFMCSdkStorage", new FileUtilsKt$wipeModuleFiles$5$1(file3));
                    file3.delete();
                }
            }
        } catch (Exception unused2) {
            SFMCSdkLogger.INSTANCE.w("~$SFMCSdkStorage", FileUtilsKt$wipeModuleFiles$6.INSTANCE);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: wipeModuleFiles$lambda-0  reason: not valid java name */
    public static final boolean m815wipeModuleFiles$lambda0(String str, File file, String str2) {
        Intrinsics.checkNotNullParameter(str, "$moduleApplicationId");
        Intrinsics.checkNotNullExpressionValue(str2, "name");
        return StringsKt.startsWith$default(str2, getFilenamePrefixForModule(str), false, 2, (Object) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: wipeModuleFiles$lambda-2  reason: not valid java name */
    public static final boolean m816wipeModuleFiles$lambda2(String str, File file, String str2) {
        Intrinsics.checkNotNullParameter(str, "$moduleApplicationId");
        Intrinsics.checkNotNullExpressionValue(str2, "name");
        return StringsKt.startsWith$default(str2, getFilenamePrefixForModule(str), false, 2, (Object) null);
    }

    public static final String readAll(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, RequestKt.getUTF_8()));
        try {
            StringBuilder sb = new StringBuilder();
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                sb.append(readLine);
                sb.append(10);
            }
            String sb2 = sb.toString();
            CloseableKt.closeFinally(bufferedReader, (Throwable) null);
            return sb2;
        } catch (Throwable th) {
            CloseableKt.closeFinally(bufferedReader, th);
            throw th;
        }
    }
}
