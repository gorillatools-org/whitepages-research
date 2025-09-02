package com.facebook.hermes.intl;

import android.icu.util.ULocale;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class LocaleObjectICU implements ILocaleObject<ULocale> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean mIsDirty;
    private ULocale m_icuLocale;
    private ULocale.Builder m_icuLocaleBuilder;

    private LocaleObjectICU(ULocale uLocale) {
        this.m_icuLocaleBuilder = null;
        this.mIsDirty = false;
        this.m_icuLocale = uLocale;
    }

    private LocaleObjectICU(String str) throws JSRangeErrorException {
        this.m_icuLocale = null;
        this.m_icuLocaleBuilder = null;
        this.mIsDirty = false;
        ULocale.Builder builder = new ULocale.Builder();
        this.m_icuLocaleBuilder = builder;
        try {
            builder.setLanguageTag(str);
            this.mIsDirty = true;
        } catch (RuntimeException e) {
            throw new JSRangeErrorException(e.getMessage());
        }
    }

    private void ensureNotDirty() throws JSRangeErrorException {
        if (this.mIsDirty) {
            try {
                this.m_icuLocale = this.m_icuLocaleBuilder.build();
                this.mIsDirty = false;
            } catch (RuntimeException e) {
                throw new JSRangeErrorException(e.getMessage());
            }
        }
    }

    public ArrayList<String> getUnicodeExtensions(String str) throws JSRangeErrorException {
        ensureNotDirty();
        String CanonicalKeyToICUKey = UnicodeExtensionKeys.CanonicalKeyToICUKey(str);
        ArrayList<String> arrayList = new ArrayList<>();
        String keywordValue = this.m_icuLocale.getKeywordValue(CanonicalKeyToICUKey);
        if (keywordValue != null && !keywordValue.isEmpty()) {
            Collections.addAll(arrayList, keywordValue.split("-|_"));
        }
        return arrayList;
    }

    public HashMap<String, String> getUnicodeExtensions() throws JSRangeErrorException {
        ensureNotDirty();
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<String> keywords = this.m_icuLocale.getKeywords();
        if (keywords != null) {
            while (keywords.hasNext()) {
                String next = keywords.next();
                hashMap.put(UnicodeExtensionKeys.ICUKeyToCanonicalKey(next), this.m_icuLocale.getKeywordValue(next));
            }
        }
        return hashMap;
    }

    public void setUnicodeExtensions(String str, ArrayList<String> arrayList) throws JSRangeErrorException {
        ensureNotDirty();
        if (this.m_icuLocaleBuilder == null) {
            this.m_icuLocaleBuilder = new ULocale.Builder().setLocale(this.m_icuLocale);
        }
        try {
            this.m_icuLocaleBuilder.setUnicodeLocaleKeyword(str, TextUtils.join("-", arrayList));
            this.mIsDirty = true;
        } catch (RuntimeException e) {
            throw new JSRangeErrorException(e.getMessage());
        }
    }

    public ULocale getLocale() throws JSRangeErrorException {
        ensureNotDirty();
        return this.m_icuLocale;
    }

    public ULocale getLocaleWithoutExtensions() throws JSRangeErrorException {
        ensureNotDirty();
        ULocale.Builder builder = new ULocale.Builder();
        builder.setLocale(this.m_icuLocale);
        builder.clearExtensions();
        return builder.build();
    }

    public String toCanonicalTag() throws JSRangeErrorException {
        return getLocale().toLanguageTag();
    }

    public String toCanonicalTagWithoutExtensions() throws JSRangeErrorException {
        return getLocaleWithoutExtensions().toLanguageTag();
    }

    public ILocaleObject<ULocale> cloneObject() throws JSRangeErrorException {
        ensureNotDirty();
        return new LocaleObjectICU(this.m_icuLocale);
    }

    public static ILocaleObject<ULocale> createFromLocaleId(String str) throws JSRangeErrorException {
        return new LocaleObjectICU(str);
    }

    public static ILocaleObject<ULocale> createFromULocale(ULocale uLocale) {
        return new LocaleObjectICU(uLocale);
    }

    public static ILocaleObject<ULocale> createDefault() {
        return new LocaleObjectICU(ULocale.getDefault(ULocale.Category.FORMAT));
    }
}
