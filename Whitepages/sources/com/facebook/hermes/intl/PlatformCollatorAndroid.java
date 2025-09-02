package com.facebook.hermes.intl;

import com.facebook.hermes.intl.IPlatformCollator;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Locale;

public class PlatformCollatorAndroid implements IPlatformCollator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private RuleBasedCollator mCollator;
    private LocaleObjectAndroid mLocale;

    public IPlatformCollator setCaseFirstAttribute(IPlatformCollator.CaseFirst caseFirst) {
        return this;
    }

    public IPlatformCollator setIgnorePunctuation(boolean z) {
        return this;
    }

    public IPlatformCollator setNumericAttribute(boolean z) {
        return this;
    }

    PlatformCollatorAndroid() {
    }

    public IPlatformCollator configure(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        LocaleObjectAndroid localeObjectAndroid = (LocaleObjectAndroid) iLocaleObject;
        this.mLocale = localeObjectAndroid;
        this.mCollator = (RuleBasedCollator) Collator.getInstance(localeObjectAndroid.getLocale());
        return this;
    }

    public int compare(String str, String str2) {
        return this.mCollator.compare(str, str2);
    }

    public IPlatformCollator.Sensitivity getSensitivity() {
        RuleBasedCollator ruleBasedCollator = this.mCollator;
        if (ruleBasedCollator == null) {
            return IPlatformCollator.Sensitivity.LOCALE;
        }
        int strength = ruleBasedCollator.getStrength();
        if (strength == 0) {
            return IPlatformCollator.Sensitivity.BASE;
        }
        if (strength == 1) {
            return IPlatformCollator.Sensitivity.ACCENT;
        }
        return IPlatformCollator.Sensitivity.VARIANT;
    }

    /* renamed from: com.facebook.hermes.intl.PlatformCollatorAndroid$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity[] r0 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity = r0
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r1 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.BASE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r1 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.ACCENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r1 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.VARIANT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r1 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.CASE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.hermes.intl.PlatformCollatorAndroid.AnonymousClass1.<clinit>():void");
        }
    }

    public IPlatformCollator setSensitivity(IPlatformCollator.Sensitivity sensitivity) {
        int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity[sensitivity.ordinal()];
        if (i == 1) {
            this.mCollator.setStrength(0);
        } else if (i == 2) {
            this.mCollator.setStrength(1);
        } else if (i == 3) {
            this.mCollator.setStrength(2);
        } else if (i == 4) {
            this.mCollator.setStrength(0);
        }
        return this;
    }

    public String[] getAvailableLocales() {
        ArrayList arrayList = new ArrayList();
        for (Locale languageTag : Collator.getAvailableLocales()) {
            arrayList.add(languageTag.toLanguageTag());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
