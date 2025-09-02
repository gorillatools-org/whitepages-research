package com.facebook.hermes.intl;

import android.icu.text.Collator;
import android.icu.text.RuleBasedCollator;
import android.icu.util.ULocale;
import com.facebook.hermes.intl.IPlatformCollator;
import java.util.ArrayList;

public class PlatformCollatorICU implements IPlatformCollator {
    private RuleBasedCollator mCollator = null;

    PlatformCollatorICU() {
    }

    public IPlatformCollator configure(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        RuleBasedCollator ruleBasedCollator = (RuleBasedCollator) Collator.getInstance(((LocaleObjectICU) iLocaleObject).getLocale());
        this.mCollator = ruleBasedCollator;
        ruleBasedCollator.setDecomposition(17);
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
            if (this.mCollator.isCaseLevel()) {
                return IPlatformCollator.Sensitivity.CASE;
            }
            return IPlatformCollator.Sensitivity.BASE;
        } else if (strength == 1) {
            return IPlatformCollator.Sensitivity.ACCENT;
        } else {
            return IPlatformCollator.Sensitivity.VARIANT;
        }
    }

    public IPlatformCollator setSensitivity(IPlatformCollator.Sensitivity sensitivity) {
        int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity[sensitivity.ordinal()];
        if (i == 1) {
            this.mCollator.setStrength(0);
        } else if (i == 2) {
            this.mCollator.setStrength(1);
        } else if (i == 3) {
            this.mCollator.setStrength(0);
            this.mCollator.setCaseLevel(true);
        } else if (i == 4) {
            this.mCollator.setStrength(2);
        }
        return this;
    }

    public IPlatformCollator setIgnorePunctuation(boolean z) {
        if (z) {
            this.mCollator.setAlternateHandlingShifted(true);
        }
        return this;
    }

    public IPlatformCollator setNumericAttribute(boolean z) {
        if (z) {
            this.mCollator.setNumericCollation(JSObjects.getJavaBoolean(Boolean.TRUE));
        }
        return this;
    }

    /* renamed from: com.facebook.hermes.intl.PlatformCollatorICU$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$CaseFirst;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        static {
            /*
                com.facebook.hermes.intl.IPlatformCollator$CaseFirst[] r0 = com.facebook.hermes.intl.IPlatformCollator.CaseFirst.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$CaseFirst = r0
                r1 = 1
                com.facebook.hermes.intl.IPlatformCollator$CaseFirst r2 = com.facebook.hermes.intl.IPlatformCollator.CaseFirst.UPPER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$CaseFirst     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.hermes.intl.IPlatformCollator$CaseFirst r3 = com.facebook.hermes.intl.IPlatformCollator.CaseFirst.LOWER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$CaseFirst     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.hermes.intl.IPlatformCollator$CaseFirst r4 = com.facebook.hermes.intl.IPlatformCollator.CaseFirst.FALSE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity[] r3 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity = r3
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r4 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.BASE     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r3 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.ACCENT     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity     // Catch:{ NoSuchFieldError -> 0x004d }
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r1 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.CASE     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r1 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.VARIANT     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.hermes.intl.PlatformCollatorICU.AnonymousClass1.<clinit>():void");
        }
    }

    public IPlatformCollator setCaseFirstAttribute(IPlatformCollator.CaseFirst caseFirst) {
        int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformCollator$CaseFirst[caseFirst.ordinal()];
        if (i == 1) {
            this.mCollator.setUpperCaseFirst(true);
        } else if (i != 2) {
            this.mCollator.setCaseFirstDefault();
        } else {
            this.mCollator.setLowerCaseFirst(true);
        }
        return this;
    }

    public String[] getAvailableLocales() {
        ArrayList arrayList = new ArrayList();
        for (ULocale languageTag : ULocale.getAvailableLocales()) {
            arrayList.add(languageTag.toLanguageTag());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
