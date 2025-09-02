package com.facebook.hermes.intl;

import com.facebook.hermes.intl.IPlatformCollator;
import com.facebook.hermes.intl.OptionHelpers;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@DoNotStrip
public class Collator {
    private IPlatformCollator mPlatformCollatorObject = new PlatformCollatorICU();
    private IPlatformCollator.CaseFirst mResolvedCaseFirst;
    private String mResolvedCollation = Constants.COLLATION_DEFAULT;
    private boolean mResolvedIgnorePunctuation;
    private ILocaleObject<?> mResolvedLocaleObject;
    private ILocaleObject<?> mResolvedLocaleObjectForResolvedOptions;
    private boolean mResolvedNumeric;
    private IPlatformCollator.Sensitivity mResolvedSensitivity;
    private IPlatformCollator.Usage mResolvedUsage;

    private void initializeCollator(List<String> list, Map<String, Object> map) throws JSRangeErrorException {
        OptionHelpers.OptionType optionType = OptionHelpers.OptionType.STRING;
        this.mResolvedUsage = (IPlatformCollator.Usage) OptionHelpers.searchEnum(IPlatformCollator.Usage.class, JSObjects.getJavaString(OptionHelpers.GetOption(map, Constants.COLLATION_OPTION_USAGE, optionType, Constants.COLLATOR_USAGE_POSSIBLE_VALUES, Constants.SORT)));
        Object newObject = JSObjects.newObject();
        JSObjects.Put(newObject, Constants.LOCALEMATCHER, OptionHelpers.GetOption(map, Constants.LOCALEMATCHER, optionType, Constants.LOCALEMATCHER_POSSIBLE_VALUES, Constants.LOCALEMATCHER_BESTFIT));
        Object GetOption = OptionHelpers.GetOption(map, Constants.COLLATION_OPTION_NUMERIC, OptionHelpers.OptionType.BOOLEAN, JSObjects.Undefined(), JSObjects.Undefined());
        if (!JSObjects.isUndefined(GetOption)) {
            GetOption = JSObjects.newString(String.valueOf(JSObjects.getJavaBoolean(GetOption)));
        }
        JSObjects.Put(newObject, Constants.COLLATION_EXTENSION_PARAM_NUMERIC_SHORT, GetOption);
        JSObjects.Put(newObject, Constants.COLLATION_EXTENSION_PARAM_CASEFIRST_SHORT, OptionHelpers.GetOption(map, Constants.COLLATION_OPTION_CASEFIRST, optionType, Constants.CASEFIRST_POSSIBLE_VALUES, JSObjects.Undefined()));
        HashMap<String, Object> resolveLocale = LocaleResolver.resolveLocale(list, newObject, Arrays.asList(new String[]{Constants.COLLATION_EXTENSION_KEY_SHORT, Constants.COLLATION_EXTENSION_PARAM_CASEFIRST_SHORT, Constants.COLLATION_EXTENSION_PARAM_NUMERIC_SHORT}));
        ILocaleObject<?> iLocaleObject = (ILocaleObject) JSObjects.getJavaMap(resolveLocale).get("locale");
        this.mResolvedLocaleObject = iLocaleObject;
        this.mResolvedLocaleObjectForResolvedOptions = iLocaleObject.cloneObject();
        Object Get = JSObjects.Get(resolveLocale, Constants.COLLATION_EXTENSION_KEY_SHORT);
        if (JSObjects.isNull(Get)) {
            Get = JSObjects.newString(Constants.COLLATION_DEFAULT);
        }
        this.mResolvedCollation = JSObjects.getJavaString(Get);
        Object Get2 = JSObjects.Get(resolveLocale, Constants.COLLATION_EXTENSION_PARAM_NUMERIC_SHORT);
        if (JSObjects.isNull(Get2)) {
            this.mResolvedNumeric = false;
        } else {
            this.mResolvedNumeric = Boolean.parseBoolean(JSObjects.getJavaString(Get2));
        }
        Object Get3 = JSObjects.Get(resolveLocale, Constants.COLLATION_EXTENSION_PARAM_CASEFIRST_SHORT);
        if (JSObjects.isNull(Get3)) {
            Get3 = JSObjects.newString(Constants.CASEFIRST_FALSE);
        }
        this.mResolvedCaseFirst = (IPlatformCollator.CaseFirst) OptionHelpers.searchEnum(IPlatformCollator.CaseFirst.class, JSObjects.getJavaString(Get3));
        if (this.mResolvedUsage == IPlatformCollator.Usage.SEARCH) {
            ArrayList<String> unicodeExtensions = this.mResolvedLocaleObject.getUnicodeExtensions("collation");
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = unicodeExtensions.iterator();
            while (it.hasNext()) {
                arrayList.add(UnicodeExtensionKeys.resolveCollationAlias(it.next()));
            }
            arrayList.add(UnicodeExtensionKeys.resolveCollationAlias("search"));
            this.mResolvedLocaleObject.setUnicodeExtensions(Constants.COLLATION_EXTENSION_KEY_SHORT, arrayList);
        }
        Object GetOption2 = OptionHelpers.GetOption(map, Constants.COLLATION_OPTION_SENSITIVITY, OptionHelpers.OptionType.STRING, Constants.SENSITIVITY_POSSIBLE_VALUES, JSObjects.Undefined());
        if (!JSObjects.isUndefined(GetOption2)) {
            this.mResolvedSensitivity = (IPlatformCollator.Sensitivity) OptionHelpers.searchEnum(IPlatformCollator.Sensitivity.class, JSObjects.getJavaString(GetOption2));
        } else if (this.mResolvedUsage == IPlatformCollator.Usage.SORT) {
            this.mResolvedSensitivity = IPlatformCollator.Sensitivity.VARIANT;
        } else {
            this.mResolvedSensitivity = IPlatformCollator.Sensitivity.LOCALE;
        }
        this.mResolvedIgnorePunctuation = JSObjects.getJavaBoolean(OptionHelpers.GetOption(map, Constants.COLLATION_OPTION_IGNOREPUNCTUATION, OptionHelpers.OptionType.BOOLEAN, JSObjects.Undefined(), Boolean.FALSE));
    }

    @DoNotStrip
    public Collator(List<String> list, Map<String, Object> map) throws JSRangeErrorException {
        initializeCollator(list, map);
        this.mPlatformCollatorObject.configure(this.mResolvedLocaleObject).setNumericAttribute(this.mResolvedNumeric).setCaseFirstAttribute(this.mResolvedCaseFirst).setSensitivity(this.mResolvedSensitivity).setIgnorePunctuation(this.mResolvedIgnorePunctuation);
    }

    @DoNotStrip
    public static List<String> supportedLocalesOf(List<String> list, Map<String, Object> map) throws JSRangeErrorException {
        if (JSObjects.getJavaString(OptionHelpers.GetOption(map, Constants.LOCALEMATCHER, OptionHelpers.OptionType.STRING, Constants.LOCALEMATCHER_POSSIBLE_VALUES, Constants.LOCALEMATCHER_BESTFIT)).equals(Constants.LOCALEMATCHER_BESTFIT)) {
            return Arrays.asList(LocaleMatcher.bestFitSupportedLocales((String[]) list.toArray(new String[list.size()])));
        }
        return Arrays.asList(LocaleMatcher.lookupSupportedLocales((String[]) list.toArray(new String[list.size()])));
    }

    @DoNotStrip
    public Map<String, Object> resolvedOptions() throws JSRangeErrorException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("locale", this.mResolvedLocaleObjectForResolvedOptions.toCanonicalTag().replace("-kn-true", "-kn"));
        linkedHashMap.put(Constants.COLLATION_OPTION_USAGE, this.mResolvedUsage.toString());
        IPlatformCollator.Sensitivity sensitivity = this.mResolvedSensitivity;
        if (sensitivity == IPlatformCollator.Sensitivity.LOCALE) {
            linkedHashMap.put(Constants.COLLATION_OPTION_SENSITIVITY, this.mPlatformCollatorObject.getSensitivity().toString());
        } else {
            linkedHashMap.put(Constants.COLLATION_OPTION_SENSITIVITY, sensitivity.toString());
        }
        linkedHashMap.put(Constants.COLLATION_OPTION_IGNOREPUNCTUATION, Boolean.valueOf(this.mResolvedIgnorePunctuation));
        linkedHashMap.put("collation", this.mResolvedCollation);
        linkedHashMap.put(Constants.COLLATION_OPTION_NUMERIC, Boolean.valueOf(this.mResolvedNumeric));
        linkedHashMap.put(Constants.COLLATION_OPTION_CASEFIRST, this.mResolvedCaseFirst.toString());
        return linkedHashMap;
    }

    @DoNotStrip
    public double compare(String str, String str2) {
        return (double) this.mPlatformCollatorObject.compare(str, str2);
    }
}
