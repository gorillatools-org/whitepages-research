package com.facebook.appevents.integrity;

import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.salesforce.marketingcloud.config.a;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

public final class BlocklistEventsManager {
    public static final BlocklistEventsManager INSTANCE = new BlocklistEventsManager();
    private static Set blocklist = new HashSet();
    private static boolean enabled;

    private BlocklistEventsManager() {
    }

    public static final void enable() {
        Class<BlocklistEventsManager> cls = BlocklistEventsManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                INSTANCE.loadBlocklistEvents();
                Collection collection = blocklist;
                if (collection == null) {
                    return;
                }
                if (!collection.isEmpty()) {
                    enabled = true;
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void loadBlocklistEvents() {
        HashSet convertJSONArrayToHashSet;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
                if (queryAppSettings != null && (convertJSONArrayToHashSet = Utility.convertJSONArrayToHashSet(queryAppSettings.getBlocklistEvents())) != null) {
                    blocklist = convertJSONArrayToHashSet;
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final boolean isInBlocklist(String str) {
        Class<BlocklistEventsManager> cls = BlocklistEventsManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            Intrinsics.checkNotNullParameter(str, a.h);
            if (!enabled) {
                return false;
            }
            return blocklist.contains(str);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }
}
