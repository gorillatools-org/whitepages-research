package com.salesforce.marketingcloud.sfmcsdk.components.storage;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt;
import kotlin.jvm.internal.Intrinsics;

public abstract class SFMCSdkSQLiteOpenHelper extends SQLiteOpenHelper {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SFMCSdkSQLiteOpenHelper(String str, int i, SFMCSdkComponents sFMCSdkComponents) {
        super(sFMCSdkComponents.getContext$sfmcsdk_release(), FileUtilsKt.getFilenameForModuleInstallation(str, sFMCSdkComponents.getModuleApplicationId(), sFMCSdkComponents.getRegistrationId()), (SQLiteDatabase.CursorFactory) null, i);
        Intrinsics.checkNotNullParameter(str, "databaseName");
        Intrinsics.checkNotNullParameter(sFMCSdkComponents, "components");
    }
}
