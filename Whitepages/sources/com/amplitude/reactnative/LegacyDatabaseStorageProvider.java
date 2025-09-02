package com.amplitude.reactnative;

import android.content.Context;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public final class LegacyDatabaseStorageProvider {
    public static final LegacyDatabaseStorageProvider INSTANCE = new LegacyDatabaseStorageProvider();
    private static final Map instances = new LinkedHashMap();

    private LegacyDatabaseStorageProvider() {
    }

    public final LegacyDatabaseStorage getStorage(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        String databaseName = getDatabaseName(str);
        Map map = instances;
        LegacyDatabaseStorage legacyDatabaseStorage = (LegacyDatabaseStorage) map.get(databaseName);
        if (legacyDatabaseStorage != null) {
            return legacyDatabaseStorage;
        }
        LegacyDatabaseStorage legacyDatabaseStorage2 = new LegacyDatabaseStorage(context, databaseName);
        map.put(databaseName, legacyDatabaseStorage2);
        return legacyDatabaseStorage2;
    }

    private final String getDatabaseName(String str) {
        String str2;
        if (str != null) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            str2 = str.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(str2, "toLowerCase(...)");
        } else {
            str2 = null;
        }
        if (str2 == null || str2.length() == 0 || Intrinsics.areEqual((Object) str2, (Object) "$default_instance")) {
            return "com.amplitude.api";
        }
        return "com.amplitude.api_" + str2;
    }
}
