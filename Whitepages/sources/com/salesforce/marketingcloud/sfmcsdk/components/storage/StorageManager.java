package com.salesforce.marketingcloud.sfmcsdk.components.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.salesforce.marketingcloud.sfmcsdk.components.encryption.EncryptedSharedPreferences;
import com.salesforce.marketingcloud.sfmcsdk.components.encryption.EncryptionManager;
import com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt;
import kotlin.jvm.internal.Intrinsics;

public final class StorageManager {
    private final Context context;
    private final EncryptionManager encryptionManager;
    private final String moduleAppId;
    private final String registrationId;

    public StorageManager(Context context2, EncryptionManager encryptionManager2, String str, String str2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(encryptionManager2, "encryptionManager");
        Intrinsics.checkNotNullParameter(str, "moduleAppId");
        Intrinsics.checkNotNullParameter(str2, "registrationId");
        this.context = context2;
        this.encryptionManager = encryptionManager2;
        this.moduleAppId = str;
        this.registrationId = str2;
    }

    public final SharedPreferences getSecurePrefs(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        SharedPreferences create = EncryptedSharedPreferences.create(this.context, FileUtilsKt.getFilenameForModuleInstallation(str, this.moduleAppId, this.registrationId), this.encryptionManager.getEncryptionKey$sfmcsdk_release());
        Intrinsics.checkNotNullExpressionValue(create, "create(\n      context,\n …nager.encryptionKey\n    )");
        return create;
    }
}
