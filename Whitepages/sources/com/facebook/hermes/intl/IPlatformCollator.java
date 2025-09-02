package com.facebook.hermes.intl;

public interface IPlatformCollator {
    int compare(String str, String str2);

    IPlatformCollator configure(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException;

    String[] getAvailableLocales();

    Sensitivity getSensitivity();

    IPlatformCollator setCaseFirstAttribute(CaseFirst caseFirst);

    IPlatformCollator setIgnorePunctuation(boolean z);

    IPlatformCollator setNumericAttribute(boolean z);

    IPlatformCollator setSensitivity(Sensitivity sensitivity);

    public enum Sensitivity {
        BASE,
        ACCENT,
        CASE,
        VARIANT,
        LOCALE;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity[ordinal()];
            if (i == 1) {
                return Constants.SENSITIVITY_BASE;
            }
            if (i == 2) {
                return Constants.SENSITIVITY_ACCENT;
            }
            if (i == 3) {
                return Constants.SENSITIVITY_CASE;
            }
            if (i == 4) {
                return Constants.SENSITIVITY_VARIANT;
            }
            if (i == 5) {
                return "";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum Usage {
        SORT,
        SEARCH;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Usage[ordinal()];
            if (i == 1) {
                return Constants.SORT;
            }
            if (i == 2) {
                return "search";
            }
            throw new IllegalArgumentException();
        }
    }

    /* renamed from: com.facebook.hermes.intl.IPlatformCollator$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$CaseFirst;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Usage;

        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|(2:15|16)|17|19|20|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|19|20|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|19|20|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0068 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0073 */
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
                com.facebook.hermes.intl.IPlatformCollator$Usage[] r3 = com.facebook.hermes.intl.IPlatformCollator.Usage.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Usage = r3
                com.facebook.hermes.intl.IPlatformCollator$Usage r4 = com.facebook.hermes.intl.IPlatformCollator.Usage.SORT     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r3 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Usage     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.facebook.hermes.intl.IPlatformCollator$Usage r4 = com.facebook.hermes.intl.IPlatformCollator.Usage.SEARCH     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity[] r3 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity = r3
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r4 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.BASE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r1 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity     // Catch:{ NoSuchFieldError -> 0x005e }
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r3 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.ACCENT     // Catch:{ NoSuchFieldError -> 0x005e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity     // Catch:{ NoSuchFieldError -> 0x0068 }
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r1 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.CASE     // Catch:{ NoSuchFieldError -> 0x0068 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0068 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0068 }
            L_0x0068:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity     // Catch:{ NoSuchFieldError -> 0x0073 }
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r1 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.VARIANT     // Catch:{ NoSuchFieldError -> 0x0073 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0073 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0073 }
            L_0x0073:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformCollator$Sensitivity     // Catch:{ NoSuchFieldError -> 0x007e }
                com.facebook.hermes.intl.IPlatformCollator$Sensitivity r1 = com.facebook.hermes.intl.IPlatformCollator.Sensitivity.LOCALE     // Catch:{ NoSuchFieldError -> 0x007e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007e }
            L_0x007e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.hermes.intl.IPlatformCollator.AnonymousClass1.<clinit>():void");
        }
    }

    public enum CaseFirst {
        UPPER,
        LOWER,
        FALSE;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformCollator$CaseFirst[ordinal()];
            if (i == 1) {
                return Constants.CASEFIRST_UPPER;
            }
            if (i == 2) {
                return Constants.CASEFIRST_LOWER;
            }
            if (i == 3) {
                return Constants.CASEFIRST_FALSE;
            }
            throw new IllegalArgumentException();
        }
    }
}
