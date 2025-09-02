package com.facebook.hermes.intl;

import com.facebook.hermes.intl.LocaleMatcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LocaleResolver {
    public static HashMap<String, Object> resolveLocale(List<String> list, Object obj, List<String> list2) throws JSRangeErrorException {
        LocaleMatcher.LocaleMatchResult localeMatchResult;
        HashMap<String, Object> hashMap = new HashMap<>();
        if (JSObjects.getJavaString(JSObjects.Get(obj, Constants.LOCALEMATCHER)).equals(Constants.LOCALEMATCHER_LOOKUP)) {
            localeMatchResult = LocaleMatcher.lookupMatch((String[]) list.toArray(new String[list.size()]));
        } else {
            localeMatchResult = LocaleMatcher.bestFitMatch((String[]) list.toArray(new String[list.size()]));
        }
        HashSet<String> hashSet = new HashSet<>();
        for (String next : list2) {
            Object Null = JSObjects.Null();
            if (!localeMatchResult.extensions.isEmpty() && localeMatchResult.extensions.containsKey(next)) {
                String str = localeMatchResult.extensions.get(next);
                boolean isEmpty = str.isEmpty();
                String str2 = str;
                if (isEmpty) {
                    str2 = JSObjects.newString("true");
                }
                hashSet.add(next);
                Null = str2;
            }
            if (JSObjects.getJavaMap(obj).containsKey(next)) {
                Object Get = JSObjects.Get(obj, next);
                if (JSObjects.isString(Get) && JSObjects.getJavaString(Get).isEmpty()) {
                    Get = JSObjects.newBoolean(true);
                }
                if (!JSObjects.isUndefined(Get) && !Get.equals(Null)) {
                    hashSet.remove(next);
                    Null = Get;
                }
            }
            if (!JSObjects.isNull(Null)) {
                Null = UnicodeExtensionKeys.resolveKnownAliases(next, Null);
            }
            if (!JSObjects.isString(Null) || UnicodeExtensionKeys.isValidKeyword(next, JSObjects.getJavaString(Null), localeMatchResult.matchedLocale)) {
                hashMap.put(next, Null);
            } else {
                hashMap.put(next, JSObjects.Null());
            }
        }
        for (String str3 : hashSet) {
            ArrayList arrayList = new ArrayList();
            String javaString = JSObjects.getJavaString(UnicodeExtensionKeys.resolveKnownAliases(str3, JSObjects.newString(localeMatchResult.extensions.get(str3))));
            if (!JSObjects.isString(javaString) || UnicodeExtensionKeys.isValidKeyword(str3, JSObjects.getJavaString(javaString), localeMatchResult.matchedLocale)) {
                arrayList.add(javaString);
                localeMatchResult.matchedLocale.setUnicodeExtensions(str3, arrayList);
            }
        }
        hashMap.put("locale", localeMatchResult.matchedLocale);
        return hashMap;
    }
}
