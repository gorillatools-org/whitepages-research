package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.Behavior;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

public final class BehaviorTypeKt {
    public static final Event toEvent(Behavior behavior) {
        String str;
        Intrinsics.checkNotNullParameter(behavior, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String appVersion = behavior.getAppVersion();
        if (appVersion != null) {
            linkedHashMap.put(BehaviorManagerImpl.BUNDLE_KEY_CURRENT_VERSION, appVersion);
        }
        String appName = behavior.getAppName();
        if (appName != null) {
            linkedHashMap.put(BehaviorManagerImpl.BUNDLE_KEY_APP_NAME, appName);
        }
        if (behavior instanceof Behavior.AppVersionChanged) {
            String previousVersion = behavior.getPreviousVersion();
            if (previousVersion != null) {
                linkedHashMap.put(BehaviorManagerImpl.BUNDLE_KEY_PREVIOUS_VERSION, previousVersion);
            }
            str = String.valueOf(Reflection.getOrCreateKotlinClass(behavior.getClass()).getSimpleName());
        } else if (behavior instanceof Behavior.ScreenEntry) {
            linkedHashMap.put("screen_name", ((Behavior.ScreenEntry) behavior).getName());
            str = String.valueOf(Reflection.getOrCreateKotlinClass(behavior.getClass()).getSimpleName());
        } else {
            str = String.valueOf(Reflection.getOrCreateKotlinClass(behavior.getClass()).getSimpleName());
        }
        return EventManager.Companion.customEvent(str, linkedHashMap, Event.Producer.SFMC_SDK, Event.Category.SYSTEM);
    }
}
