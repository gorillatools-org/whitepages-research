package com.facebook.hermes.intl;

import android.icu.lang.UCharacter;
import android.icu.util.ULocale;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DoNotStrip
public class Intl {
    private static List<String> canonicalizeLocaleList(List<String> list) throws JSRangeErrorException {
        if (list.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (String next : list) {
            if (next == null) {
                throw new JSRangeErrorException("Incorrect locale information provided");
            } else if (!next.isEmpty()) {
                String canonicalizeLocaleId = LocaleIdentifier.canonicalizeLocaleId(next);
                if (!canonicalizeLocaleId.isEmpty() && !arrayList.contains(canonicalizeLocaleId)) {
                    arrayList.add(canonicalizeLocaleId);
                }
            } else {
                throw new JSRangeErrorException("Incorrect locale information provided");
            }
        }
        return arrayList;
    }

    @DoNotStrip
    public static List<String> getCanonicalLocales(List<String> list) throws JSRangeErrorException {
        return canonicalizeLocaleList(list);
    }

    @DoNotStrip
    public static String toLocaleLowerCase(List<String> list, String str) throws JSRangeErrorException {
        return UCharacter.toLowerCase((ULocale) LocaleMatcher.bestFitMatch((String[]) list.toArray(new String[list.size()])).matchedLocale.getLocale(), str);
    }

    @DoNotStrip
    public static String toLocaleUpperCase(List<String> list, String str) throws JSRangeErrorException {
        return UCharacter.toUpperCase((ULocale) LocaleMatcher.bestFitMatch((String[]) list.toArray(new String[list.size()])).matchedLocale.getLocale(), str);
    }
}
