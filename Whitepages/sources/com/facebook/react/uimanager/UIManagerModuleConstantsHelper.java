package com.facebook.react.uimanager;

import com.facebook.common.logging.FLog;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class UIManagerModuleConstantsHelper {
    private static final String BUBBLING_EVENTS_KEY = "bubblingEventTypes";
    private static final String DIRECT_EVENTS_KEY = "directEventTypes";
    private static final String TAG = "UIManagerModuleConstantsHelper";

    static Map<String, Object> createConstants(ViewManagerResolver viewManagerResolver) {
        Map<String, Object> constants = UIManagerModuleConstants.getConstants();
        constants.put("ViewManagerNames", new ArrayList(viewManagerResolver.getViewManagerNames()));
        constants.put("LazyViewManagersEnabled", Boolean.TRUE);
        return constants;
    }

    public static Map<String, Object> getDefaultExportableEventTypes() {
        return MapBuilder.of(BUBBLING_EVENTS_KEY, UIManagerModuleConstants.getBubblingEventTypeConstants(), DIRECT_EVENTS_KEY, UIManagerModuleConstants.getDirectEventTypeConstants());
    }

    private static void validateDirectEventNames(String str, Map<String, Object> map) {
        String str2;
        if (ReactBuildConfig.DEBUG && map != null) {
            for (String next : map.keySet()) {
                Object obj = map.get(next);
                if (obj != null && (obj instanceof Map) && (str2 = (String) ((Map) obj).get("registrationName")) != null && next.startsWith(ViewProps.TOP) && str2.startsWith(ViewProps.ON) && !next.substring(3).equals(str2.substring(2))) {
                    FLog.e(TAG, String.format("Direct event name for '%s' doesn't correspond to the naming convention, expected 'topEventName'->'onEventName', got '%s'->'%s'", new Object[]{str, next, str2}));
                }
            }
        }
    }

    static Map<String, Object> createConstants(List<ViewManager> list, Map<String, Object> map, Map<String, Object> map2) {
        Map<String, Object> constants = UIManagerModuleConstants.getConstants();
        Map bubblingEventTypeConstants = UIManagerModuleConstants.getBubblingEventTypeConstants();
        Map directEventTypeConstants = UIManagerModuleConstants.getDirectEventTypeConstants();
        if (map != null) {
            map.putAll(bubblingEventTypeConstants);
        }
        if (map2 != null) {
            map2.putAll(directEventTypeConstants);
        }
        for (ViewManager next : list) {
            String name = next.getName();
            Map<String, Object> createConstantsForViewManager = createConstantsForViewManager(next, (Map) null, (Map) null, map, map2);
            if (!createConstantsForViewManager.isEmpty()) {
                constants.put(name, createConstantsForViewManager);
            }
        }
        constants.put("genericBubblingEventTypes", bubblingEventTypeConstants);
        constants.put("genericDirectEventTypes", directEventTypeConstants);
        return constants;
    }

    static Map<String, Object> createConstantsForViewManager(ViewManager viewManager, Map map, Map map2, Map map3, Map map4) {
        HashMap newHashMap = MapBuilder.newHashMap();
        Map<String, Object> exportedCustomBubblingEventTypeConstants = viewManager.getExportedCustomBubblingEventTypeConstants();
        if (exportedCustomBubblingEventTypeConstants != null) {
            if (ReactNativeFeatureFlags.enableFabricRenderer() && ReactNativeFeatureFlags.useFabricInterop()) {
                exportedCustomBubblingEventTypeConstants = normalizeEventTypes(exportedCustomBubblingEventTypeConstants);
            }
            recursiveMerge(map3, exportedCustomBubblingEventTypeConstants);
            recursiveMerge(exportedCustomBubblingEventTypeConstants, map);
            newHashMap.put(BUBBLING_EVENTS_KEY, exportedCustomBubblingEventTypeConstants);
        } else if (map != null) {
            newHashMap.put(BUBBLING_EVENTS_KEY, map);
        }
        Map<String, Object> exportedCustomDirectEventTypeConstants = viewManager.getExportedCustomDirectEventTypeConstants();
        validateDirectEventNames(viewManager.getName(), exportedCustomDirectEventTypeConstants);
        if (exportedCustomDirectEventTypeConstants != null) {
            if (ReactNativeFeatureFlags.enableFabricRenderer() && ReactNativeFeatureFlags.useFabricInterop()) {
                exportedCustomDirectEventTypeConstants = normalizeEventTypes(exportedCustomDirectEventTypeConstants);
            }
            recursiveMerge(map4, exportedCustomDirectEventTypeConstants);
            recursiveMerge(exportedCustomDirectEventTypeConstants, map2);
            newHashMap.put(DIRECT_EVENTS_KEY, exportedCustomDirectEventTypeConstants);
        } else if (map2 != null) {
            newHashMap.put(DIRECT_EVENTS_KEY, map2);
        }
        Map<String, Object> exportedViewConstants = viewManager.getExportedViewConstants();
        if (exportedViewConstants != null) {
            newHashMap.put("Constants", exportedViewConstants);
        }
        Map<String, Integer> commandsMap = viewManager.getCommandsMap();
        if (commandsMap != null) {
            newHashMap.put("Commands", commandsMap);
        }
        Map<String, String> nativeProps = viewManager.getNativeProps();
        if (!nativeProps.isEmpty()) {
            newHashMap.put("NativeProps", nativeProps);
        }
        return newHashMap;
    }

    static Map normalizeEventTypes(Map map) {
        String str;
        if (map == null) {
            return null;
        }
        HashSet<String> hashSet = new HashSet<>();
        for (Object next : map.keySet()) {
            if (next instanceof String) {
                String str2 = (String) next;
                if (!str2.startsWith(ViewProps.TOP)) {
                    hashSet.add(str2);
                }
            }
        }
        if (!(map instanceof HashMap)) {
            map = new HashMap(map);
        }
        for (String str3 : hashSet) {
            Object obj = map.get(str3);
            if (str3.startsWith(ViewProps.ON)) {
                str = str3.substring(2);
            } else {
                str = str3.substring(0, 1).toUpperCase() + str3.substring(1);
            }
            map.put(ViewProps.TOP + str, obj);
        }
        return map;
    }

    private static void recursiveMerge(Map map, Map map2) {
        if (map != null && map2 != null && !map2.isEmpty()) {
            for (Object next : map2.keySet()) {
                Object obj = map2.get(next);
                Object obj2 = map.get(next);
                if (obj2 == null || !(obj instanceof Map) || !(obj2 instanceof Map)) {
                    map.put(next, obj);
                } else {
                    if (!(obj2 instanceof HashMap)) {
                        HashMap hashMap = new HashMap((Map) obj2);
                        map.replace(next, hashMap);
                        obj2 = hashMap;
                    }
                    recursiveMerge((Map) obj2, (Map) obj);
                }
            }
        }
    }
}
