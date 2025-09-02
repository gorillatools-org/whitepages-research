package com.facebook.hermes.intl;

import com.facebook.hermes.intl.LocaleIdTokenizer;
import com.facebook.hermes.intl.ParsedLocaleIdentifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

public class LocaleIdentifier {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    private static void addVariantSubtag(String str, ParsedLocaleIdentifier.ParsedLanguageIdentifier parsedLanguageIdentifier) throws JSRangeErrorException {
        ArrayList<String> arrayList = parsedLanguageIdentifier.variantSubtagList;
        if (arrayList != null) {
            int binarySearch = Collections.binarySearch(arrayList, str);
            if (binarySearch < 0) {
                parsedLanguageIdentifier.variantSubtagList.add((binarySearch * -1) - 1, str);
                return;
            }
            throw new JSRangeErrorException("Duplicate variant");
        }
        ArrayList<String> arrayList2 = new ArrayList<>();
        parsedLanguageIdentifier.variantSubtagList = arrayList2;
        arrayList2.add(str);
    }

    public static void replaceLanguageSubtagIfNeeded(StringBuffer stringBuffer, StringBuffer stringBuffer2, StringBuffer stringBuffer3) {
        String[] strArr;
        String[] strArr2;
        String[] strArr3;
        String[] strArr4;
        String[] strArr5;
        String[] strArr6;
        if (LanguageTagsGenerated.languageAliasKeys2 != null) {
            if (stringBuffer.length() == 2) {
                strArr6 = LanguageTagsGenerated.languageAliasKeys2;
                strArr5 = LanguageTagsGenerated.languageAliasReplacements2;
                strArr4 = LanguageTagsGenerated.complexLanguageAliasKeys2;
                strArr3 = LanguageTagsGenerated.complexLanguageAliasReplacementsLanguage2;
                strArr2 = LanguageTagsGenerated.complexLanguageAliasReplacementsScript2;
                strArr = LanguageTagsGenerated.complexLanguageAliasReplacementsRegion2;
            } else {
                strArr6 = LanguageTagsGenerated.languageAliasKeys3;
                strArr5 = LanguageTagsGenerated.languageAliasReplacements3;
                strArr4 = LanguageTagsGenerated.complexLanguageAliasKeys3;
                strArr3 = LanguageTagsGenerated.complexLanguageAliasReplacementsLanguage3;
                strArr2 = LanguageTagsGenerated.complexLanguageAliasReplacementsScript3;
                strArr = LanguageTagsGenerated.complexLanguageAliasReplacementsRegion3;
            }
            int binarySearch = Arrays.binarySearch(strArr6, stringBuffer.toString());
            if (binarySearch >= 0) {
                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(strArr5[binarySearch]);
                return;
            }
            int binarySearch2 = Arrays.binarySearch(strArr4, stringBuffer.toString());
            if (binarySearch2 >= 0) {
                String str = strArr3[binarySearch2];
                String str2 = strArr2[binarySearch2];
                String str3 = strArr[binarySearch2];
                stringBuffer.delete(0, stringBuffer.length());
                stringBuffer.append(str);
                if (stringBuffer2.length() == 0 && str2 != null) {
                    stringBuffer2.append(str2);
                }
                if (stringBuffer3.length() == 0 && str3 != null) {
                    stringBuffer3.append(str3);
                }
            }
        }
    }

    public static String replaceRegionSubtagIfNeeded(StringBuffer stringBuffer) {
        if (LanguageTagsGenerated.regionAliasKeys2 == null) {
            return stringBuffer.toString();
        }
        if (stringBuffer.length() == 2) {
            int binarySearch = Arrays.binarySearch(LanguageTagsGenerated.regionAliasKeys2, stringBuffer.toString());
            if (binarySearch >= 0) {
                return LanguageTagsGenerated.regionAliasReplacements2[binarySearch];
            }
            return stringBuffer.toString();
        }
        int binarySearch2 = Arrays.binarySearch(LanguageTagsGenerated.regionAliasKeys3, stringBuffer.toString());
        if (binarySearch2 >= 0) {
            return LanguageTagsGenerated.regionAliasReplacements3[binarySearch2];
        }
        return stringBuffer.toString();
    }

    static String canonicalizeLocaleId(String str) throws JSRangeErrorException {
        return LocaleObject.createFromLocaleId(str).toCanonicalTag();
    }

    static void parseUnicodeExtensions(CharSequence charSequence, LocaleIdTokenizer localeIdTokenizer, ParsedLocaleIdentifier parsedLocaleIdentifier) throws JSRangeErrorException, LocaleIdTokenizer.LocaleIdSubtagIterationFailed {
        LocaleIdTokenizer.LocaleIdSubtag localeIdSubtag;
        if (localeIdTokenizer.hasMoreSubtags()) {
            LocaleIdTokenizer.LocaleIdSubtag nextSubtag = localeIdTokenizer.nextSubtag();
            if (parsedLocaleIdentifier.unicodeExtensionAttributes == null && parsedLocaleIdentifier.unicodeExtensionKeywords == null) {
                while (localeIdSubtag.isUnicodeExtensionAttribute()) {
                    if (parsedLocaleIdentifier.unicodeExtensionAttributes == null) {
                        parsedLocaleIdentifier.unicodeExtensionAttributes = new ArrayList<>();
                    }
                    parsedLocaleIdentifier.unicodeExtensionAttributes.add(localeIdSubtag.toString());
                    if (localeIdTokenizer.hasMoreSubtags()) {
                        nextSubtag = localeIdTokenizer.nextSubtag();
                    } else {
                        return;
                    }
                }
                if (localeIdSubtag.isUnicodeExtensionKey()) {
                    if (parsedLocaleIdentifier.unicodeExtensionKeywords == null) {
                        parsedLocaleIdentifier.unicodeExtensionKeywords = new TreeMap<>();
                    }
                    do {
                        String localeIdSubtag2 = localeIdSubtag.toString();
                        ArrayList arrayList = new ArrayList();
                        parsedLocaleIdentifier.unicodeExtensionKeywords.put(localeIdSubtag2, arrayList);
                        if (localeIdTokenizer.hasMoreSubtags()) {
                            localeIdSubtag = localeIdTokenizer.nextSubtag();
                            while (localeIdSubtag.isUnicodeExtensionKeyTypeItem()) {
                                arrayList.add(localeIdSubtag.toString());
                                if (localeIdTokenizer.hasMoreSubtags()) {
                                    localeIdSubtag = localeIdTokenizer.nextSubtag();
                                } else {
                                    return;
                                }
                            }
                        } else {
                            return;
                        }
                    } while (localeIdSubtag.isUnicodeExtensionKey());
                }
                if (localeIdSubtag.isExtensionSingleton()) {
                    parseExtensions(charSequence, localeIdSubtag, localeIdTokenizer, parsedLocaleIdentifier);
                    return;
                }
                throw new JSRangeErrorException("Malformed sequence expected.");
            }
            throw new JSRangeErrorException(String.format("Duplicate unicode extension sequence in [%s]", new Object[]{charSequence}));
        }
        throw new JSRangeErrorException("Extension sequence expected.");
    }

    static void parseTransformedExtensionFields(CharSequence charSequence, LocaleIdTokenizer localeIdTokenizer, LocaleIdTokenizer.LocaleIdSubtag localeIdSubtag, ParsedLocaleIdentifier parsedLocaleIdentifier) throws JSRangeErrorException, LocaleIdTokenizer.LocaleIdSubtagIterationFailed {
        if (localeIdSubtag.isTranformedExtensionTKey()) {
            TreeMap<String, ArrayList<String>> treeMap = parsedLocaleIdentifier.transformedExtensionFields;
            if (treeMap == null) {
                if (treeMap == null) {
                    parsedLocaleIdentifier.transformedExtensionFields = new TreeMap<>();
                }
                do {
                    String localeIdSubtag2 = localeIdSubtag.toString();
                    ArrayList arrayList = new ArrayList();
                    parsedLocaleIdentifier.transformedExtensionFields.put(localeIdSubtag2, arrayList);
                    if (localeIdTokenizer.hasMoreSubtags()) {
                        localeIdSubtag = localeIdTokenizer.nextSubtag();
                        while (localeIdSubtag.isTranformedExtensionTValueItem()) {
                            arrayList.add(localeIdSubtag.toString());
                            if (localeIdTokenizer.hasMoreSubtags()) {
                                localeIdSubtag = localeIdTokenizer.nextSubtag();
                            } else {
                                return;
                            }
                        }
                    } else {
                        throw new JSRangeErrorException(String.format("Malformated transformed key in : %s", new Object[]{charSequence}));
                    }
                } while (localeIdSubtag.isTranformedExtensionTKey());
            } else {
                throw new JSRangeErrorException(String.format("Duplicate transformed extension sequence in [%s]", new Object[]{charSequence}));
            }
        }
        if (localeIdSubtag.isExtensionSingleton()) {
            parseExtensions(charSequence, localeIdSubtag, localeIdTokenizer, parsedLocaleIdentifier);
            return;
        }
        throw new JSRangeErrorException("Malformed extension sequence.");
    }

    static void parseTransformedExtensions(CharSequence charSequence, LocaleIdTokenizer localeIdTokenizer, ParsedLocaleIdentifier parsedLocaleIdentifier) throws JSRangeErrorException, LocaleIdTokenizer.LocaleIdSubtagIterationFailed {
        if (localeIdTokenizer.hasMoreSubtags()) {
            LocaleIdTokenizer.LocaleIdSubtag nextSubtag = localeIdTokenizer.nextSubtag();
            if (nextSubtag.isUnicodeLanguageSubtag()) {
                parseLanguageId(charSequence, localeIdTokenizer, nextSubtag, true, parsedLocaleIdentifier);
            } else if (nextSubtag.isTranformedExtensionTKey()) {
                parseTransformedExtensionFields(charSequence, localeIdTokenizer, nextSubtag, parsedLocaleIdentifier);
            } else {
                throw new JSRangeErrorException(String.format("Unexpected token [%s] in transformed extension sequence [%s]", new Object[]{nextSubtag.toString(), charSequence}));
            }
        } else {
            throw new JSRangeErrorException("Extension sequence expected.");
        }
    }

    static void parsePrivateUseExtensions(CharSequence charSequence, LocaleIdTokenizer localeIdTokenizer, ParsedLocaleIdentifier parsedLocaleIdentifier) throws JSRangeErrorException, LocaleIdTokenizer.LocaleIdSubtagIterationFailed {
        if (localeIdTokenizer.hasMoreSubtags()) {
            LocaleIdTokenizer.LocaleIdSubtag nextSubtag = localeIdTokenizer.nextSubtag();
            if (parsedLocaleIdentifier.puExtensions == null) {
                parsedLocaleIdentifier.puExtensions = new ArrayList<>();
            }
            while (nextSubtag.isPrivateUseExtension()) {
                parsedLocaleIdentifier.puExtensions.add(nextSubtag.toString());
                if (localeIdTokenizer.hasMoreSubtags()) {
                    nextSubtag = localeIdTokenizer.nextSubtag();
                } else {
                    return;
                }
            }
            throw new JSRangeErrorException("Tokens are not expected after pu extension.");
        }
        throw new JSRangeErrorException("Extension sequence expected.");
    }

    static void parseOtherExtensions(CharSequence charSequence, LocaleIdTokenizer localeIdTokenizer, ParsedLocaleIdentifier parsedLocaleIdentifier, char c) throws JSRangeErrorException, LocaleIdTokenizer.LocaleIdSubtagIterationFailed {
        if (localeIdTokenizer.hasMoreSubtags()) {
            LocaleIdTokenizer.LocaleIdSubtag nextSubtag = localeIdTokenizer.nextSubtag();
            if (parsedLocaleIdentifier.otherExtensionsMap == null) {
                parsedLocaleIdentifier.otherExtensionsMap = new TreeMap<>();
            }
            ArrayList arrayList = new ArrayList();
            parsedLocaleIdentifier.otherExtensionsMap.put(Character.valueOf(c), arrayList);
            while (nextSubtag.isOtherExtension()) {
                arrayList.add(nextSubtag.toString());
                if (localeIdTokenizer.hasMoreSubtags()) {
                    nextSubtag = localeIdTokenizer.nextSubtag();
                } else {
                    return;
                }
            }
            if (nextSubtag.isExtensionSingleton()) {
                parseExtensions(charSequence, nextSubtag, localeIdTokenizer, parsedLocaleIdentifier);
                return;
            }
            throw new JSRangeErrorException("Malformed sequence expected.");
        }
        throw new JSRangeErrorException("Extension sequence expected.");
    }

    static void parseExtensions(CharSequence charSequence, LocaleIdTokenizer.LocaleIdSubtag localeIdSubtag, LocaleIdTokenizer localeIdTokenizer, ParsedLocaleIdentifier parsedLocaleIdentifier) throws JSRangeErrorException, LocaleIdTokenizer.LocaleIdSubtagIterationFailed {
        if (localeIdTokenizer.hasMoreSubtags()) {
            char charAt = localeIdSubtag.toString().charAt(0);
            if (charAt == 'u') {
                parseUnicodeExtensions(charSequence, localeIdTokenizer, parsedLocaleIdentifier);
            } else if (charAt == 't') {
                parseTransformedExtensions(charSequence, localeIdTokenizer, parsedLocaleIdentifier);
            } else if (charAt == 'x') {
                parsePrivateUseExtensions(charSequence, localeIdTokenizer, parsedLocaleIdentifier);
            } else {
                parseOtherExtensions(charSequence, localeIdTokenizer, parsedLocaleIdentifier, charAt);
            }
        } else {
            throw new JSRangeErrorException("Extension sequence expected.");
        }
    }

    static boolean handleExtensions(CharSequence charSequence, LocaleIdTokenizer localeIdTokenizer, LocaleIdTokenizer.LocaleIdSubtag localeIdSubtag, boolean z, ParsedLocaleIdentifier parsedLocaleIdentifier) throws JSRangeErrorException, LocaleIdTokenizer.LocaleIdSubtagIterationFailed {
        if (z && localeIdSubtag.isTranformedExtensionTKey()) {
            parseTransformedExtensionFields(charSequence, localeIdTokenizer, localeIdSubtag, parsedLocaleIdentifier);
            return true;
        } else if (!localeIdSubtag.isExtensionSingleton()) {
            return false;
        } else {
            if (!z) {
                parseExtensions(charSequence, localeIdSubtag, localeIdTokenizer, parsedLocaleIdentifier);
                return true;
            }
            throw new JSRangeErrorException(String.format("Extension singletons in transformed extension language tag: %s", new Object[]{charSequence}));
        }
    }

    static void parseLanguageId(CharSequence charSequence, LocaleIdTokenizer localeIdTokenizer, LocaleIdTokenizer.LocaleIdSubtag localeIdSubtag, boolean z, ParsedLocaleIdentifier parsedLocaleIdentifier) throws JSRangeErrorException, LocaleIdTokenizer.LocaleIdSubtagIterationFailed {
        ParsedLocaleIdentifier.ParsedLanguageIdentifier parsedLanguageIdentifier = new ParsedLocaleIdentifier.ParsedLanguageIdentifier();
        if (z) {
            parsedLocaleIdentifier.transformedLanguageIdentifier = parsedLanguageIdentifier;
        } else {
            parsedLocaleIdentifier.languageIdentifier = parsedLanguageIdentifier;
        }
        try {
            if (localeIdSubtag.isUnicodeLanguageSubtag()) {
                parsedLanguageIdentifier.languageSubtag = localeIdSubtag.toLowerString();
                if (localeIdTokenizer.hasMoreSubtags()) {
                    LocaleIdTokenizer.LocaleIdSubtag nextSubtag = localeIdTokenizer.nextSubtag();
                    if (!handleExtensions(charSequence, localeIdTokenizer, nextSubtag, z, parsedLocaleIdentifier)) {
                        if (nextSubtag.isUnicodeScriptSubtag()) {
                            parsedLanguageIdentifier.scriptSubtag = nextSubtag.toTitleString();
                            if (localeIdTokenizer.hasMoreSubtags()) {
                                nextSubtag = localeIdTokenizer.nextSubtag();
                            } else {
                                return;
                            }
                        }
                        if (nextSubtag.isUnicodeRegionSubtag()) {
                            parsedLanguageIdentifier.regionSubtag = nextSubtag.toUpperString();
                            if (localeIdTokenizer.hasMoreSubtags()) {
                                nextSubtag = localeIdTokenizer.nextSubtag();
                            } else {
                                return;
                            }
                        }
                        while (!handleExtensions(charSequence, localeIdTokenizer, nextSubtag, z, parsedLocaleIdentifier)) {
                            if (nextSubtag.isUnicodeVariantSubtag()) {
                                addVariantSubtag(nextSubtag.toString(), parsedLanguageIdentifier);
                                if (localeIdTokenizer.hasMoreSubtags()) {
                                    nextSubtag = localeIdTokenizer.nextSubtag();
                                } else {
                                    return;
                                }
                            } else {
                                throw new JSRangeErrorException(String.format("Unknown token [%s] found in locale id: %s", new Object[]{nextSubtag.toString(), charSequence}));
                            }
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new JSRangeErrorException(String.format("Language subtag expected at %s: %s", new Object[]{localeIdSubtag.toString(), charSequence}));
        } catch (LocaleIdTokenizer.LocaleIdSubtagIterationFailed unused) {
            throw new JSRangeErrorException(String.format("Locale Identifier subtag iteration failed: %s", new Object[]{charSequence}));
        }
    }

    static ParsedLocaleIdentifier parseLocaleId(String str, LocaleIdTokenizer localeIdTokenizer) throws JSRangeErrorException {
        ParsedLocaleIdentifier parsedLocaleIdentifier = new ParsedLocaleIdentifier();
        try {
            if (localeIdTokenizer.hasMoreSubtags()) {
                parseLanguageId(str, localeIdTokenizer, localeIdTokenizer.nextSubtag(), false, parsedLocaleIdentifier);
                return parsedLocaleIdentifier;
            }
            throw new JSRangeErrorException(String.format("Language subtag not found: %s", new Object[]{str}));
        } catch (LocaleIdTokenizer.LocaleIdSubtagIterationFailed unused) {
            throw new JSRangeErrorException(String.format("Locale Identifier subtag iteration failed: %s", new Object[]{str}));
        }
    }

    static ParsedLocaleIdentifier parseLocaleId(String str) throws JSRangeErrorException {
        int binarySearch;
        String[] strArr = LanguageTagsGenerated.regularGrandfatheredKeys;
        if (strArr != null && (binarySearch = Arrays.binarySearch(strArr, str.toString())) >= 0) {
            str = LanguageTagsGenerated.regularGrandfatheredReplacements[binarySearch];
        }
        String lowerCase = str.toLowerCase();
        return parseLocaleId(lowerCase, new LocaleIdTokenizer(lowerCase));
    }
}
