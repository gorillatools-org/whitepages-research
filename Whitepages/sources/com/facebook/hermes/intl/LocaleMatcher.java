package com.facebook.hermes.intl;

import android.icu.util.ULocale;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class LocaleMatcher {

    public static class LocaleMatchResult {
        public HashMap<String, String> extensions = new HashMap<>();
        public ILocaleObject<?> matchedLocale;
    }

    public static String BestAvailableLocale(String[] strArr, String str) {
        while (Arrays.asList(strArr).indexOf(str) <= -1) {
            int lastIndexOf = str.lastIndexOf("-");
            if (lastIndexOf < 0) {
                return "";
            }
            if (lastIndexOf >= 2 && str.charAt(lastIndexOf - 2) == '-') {
                lastIndexOf -= 2;
            }
            str = str.substring(0, lastIndexOf);
        }
        return str;
    }

    public static LocaleMatchResult lookupMatch(String[] strArr, String[] strArr2) throws JSRangeErrorException {
        LocaleMatchResult localeMatchResult = new LocaleMatchResult();
        for (String createFromLocaleId : strArr) {
            ILocaleObject createFromLocaleId2 = LocaleObject.createFromLocaleId(createFromLocaleId);
            String BestAvailableLocale = BestAvailableLocale(strArr2, createFromLocaleId2.toCanonicalTagWithoutExtensions());
            if (!BestAvailableLocale.isEmpty()) {
                localeMatchResult.matchedLocale = LocaleObject.createFromLocaleId(BestAvailableLocale);
                localeMatchResult.extensions = createFromLocaleId2.getUnicodeExtensions();
                return localeMatchResult;
            }
        }
        localeMatchResult.matchedLocale = LocaleObject.createDefault();
        return localeMatchResult;
    }

    public static String[] getAvailableLocales() {
        ArrayList arrayList = new ArrayList();
        for (Locale languageTag : Locale.getAvailableLocales()) {
            arrayList.add(languageTag.toLanguageTag());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static LocaleMatchResult lookupMatch(String[] strArr) throws JSRangeErrorException {
        return lookupMatch(strArr, getAvailableLocales());
    }

    public static String[] lookupSupportedLocales(String[] strArr) throws JSRangeErrorException {
        ArrayList arrayList = new ArrayList();
        String[] availableLocales = getAvailableLocales();
        for (String str : strArr) {
            String BestAvailableLocale = BestAvailableLocale(availableLocales, LocaleObject.createFromLocaleId(str).toCanonicalTagWithoutExtensions());
            if (BestAvailableLocale != null && !BestAvailableLocale.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static ULocale bestFitBestAvailableLocale(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        boolean[] zArr = new boolean[1];
        ULocale acceptLanguage = ULocale.acceptLanguage(new ULocale[]{(ULocale) iLocaleObject.getLocaleWithoutExtensions()}, ULocale.getAvailableLocales(), zArr);
        if (zArr[0] || acceptLanguage == null) {
            return null;
        }
        return acceptLanguage;
    }

    public static LocaleMatchResult bestFitMatch(String[] strArr) throws JSRangeErrorException {
        LocaleMatchResult localeMatchResult = new LocaleMatchResult();
        for (String createFromLocaleId : strArr) {
            ILocaleObject createFromLocaleId2 = LocaleObject.createFromLocaleId(createFromLocaleId);
            ULocale bestFitBestAvailableLocale = bestFitBestAvailableLocale(createFromLocaleId2);
            if (bestFitBestAvailableLocale != null) {
                localeMatchResult.matchedLocale = LocaleObjectICU.createFromULocale(bestFitBestAvailableLocale);
                localeMatchResult.extensions = createFromLocaleId2.getUnicodeExtensions();
                return localeMatchResult;
            }
        }
        localeMatchResult.matchedLocale = LocaleObjectICU.createDefault();
        return localeMatchResult;
    }

    public static String[] bestFitSupportedLocales(String[] strArr) throws JSRangeErrorException {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (bestFitBestAvailableLocale(LocaleObject.createFromLocaleId(str)) != null) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
