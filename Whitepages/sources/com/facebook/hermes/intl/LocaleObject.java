package com.facebook.hermes.intl;

public class LocaleObject {
    public static ILocaleObject createDefault() {
        return LocaleObjectICU.createDefault();
    }

    public static ILocaleObject createFromLocaleId(String str) throws JSRangeErrorException {
        return LocaleObjectICU.createFromLocaleId(str);
    }
}
