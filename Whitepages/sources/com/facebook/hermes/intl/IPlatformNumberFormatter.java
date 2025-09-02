package com.facebook.hermes.intl;

import android.icu.text.MeasureFormat;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.text.AttributedCharacterIterator;

public interface IPlatformNumberFormatter {

    public enum RoundingType {
        SIGNIFICANT_DIGITS,
        FRACTION_DIGITS,
        COMPACT_ROUNDING
    }

    IPlatformNumberFormatter configure(ILocaleObject<?> iLocaleObject, String str, Style style, CurrencySign currencySign, Notation notation, CompactDisplay compactDisplay) throws JSRangeErrorException;

    String fieldToString(AttributedCharacterIterator.Attribute attribute, double d);

    String format(double d) throws JSRangeErrorException;

    AttributedCharacterIterator formatToParts(double d) throws JSRangeErrorException;

    String[] getAvailableLocales();

    String getDefaultNumberingSystem(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException;

    IPlatformNumberFormatter setCurrency(String str, CurrencyDisplay currencyDisplay) throws JSRangeErrorException;

    IPlatformNumberFormatter setFractionDigits(RoundingType roundingType, int i, int i2);

    IPlatformNumberFormatter setGrouping(boolean z);

    IPlatformNumberFormatter setMinIntergerDigits(int i);

    IPlatformNumberFormatter setSignDisplay(SignDisplay signDisplay);

    IPlatformNumberFormatter setSignificantDigits(RoundingType roundingType, int i, int i2) throws JSRangeErrorException;

    IPlatformNumberFormatter setUnits(String str, UnitDisplay unitDisplay) throws JSRangeErrorException;

    public enum Style {
        DECIMAL,
        PERCENT,
        CURRENCY,
        UNIT;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Style[ordinal()];
            if (i == 1) {
                return "decimal";
            }
            if (i == 2) {
                return "percent";
            }
            if (i == 3) {
                return FirebaseAnalytics.Param.CURRENCY;
            }
            if (i == 4) {
                return "unit";
            }
            throw new IllegalArgumentException();
        }

        public int getInitialNumberFormatStyle(Notation notation, CurrencySign currencySign) throws JSRangeErrorException {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Style[ordinal()];
            if (i == 2) {
                return 2;
            }
            if (i != 3) {
                if (notation == Notation.SCIENTIFIC || notation == Notation.ENGINEERING) {
                    return 3;
                }
                return 0;
            } else if (currencySign == CurrencySign.ACCOUNTING) {
                return 7;
            } else {
                if (currencySign == CurrencySign.STANDARD) {
                    return 1;
                }
                throw new JSRangeErrorException("Unrecognized formatting style requested.");
            }
        }
    }

    public enum Notation {
        STANDARD,
        SCIENTIFIC,
        ENGINEERING,
        COMPACT;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Notation[ordinal()];
            if (i == 1) {
                return Constants.COLLATION_STANDARD;
            }
            if (i == 2) {
                return "scientific";
            }
            if (i == 3) {
                return "engineering";
            }
            if (i == 4) {
                return "compact";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum CompactDisplay {
        SHORT,
        LONG;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CompactDisplay[ordinal()];
            if (i == 1) {
                return "short";
            }
            if (i == 2) {
                return "long";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum SignDisplay {
        AUTO,
        ALWAYS,
        NEVER,
        EXCEPTZERO;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay[ordinal()];
            if (i == 1) {
                return "auto";
            }
            if (i == 2) {
                return ReactScrollViewHelper.OVER_SCROLL_ALWAYS;
            }
            if (i == 3) {
                return ReactScrollViewHelper.OVER_SCROLL_NEVER;
            }
            if (i == 4) {
                return "exceptZero";
            }
            throw new IllegalArgumentException();
        }
    }

    public enum UnitDisplay {
        SHORT,
        NARROW,
        LONG;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$UnitDisplay[ordinal()];
            if (i == 1) {
                return "short";
            }
            if (i == 2) {
                return "narrow";
            }
            if (i == 3) {
                return "long";
            }
            throw new IllegalArgumentException();
        }

        public MeasureFormat.FormatWidth getFormatWidth() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$UnitDisplay[ordinal()];
            if (i == 2) {
                return MeasureFormat.FormatWidth.NARROW;
            }
            if (i != 3) {
                return MeasureFormat.FormatWidth.SHORT;
            }
            return MeasureFormat.FormatWidth.WIDE;
        }
    }

    public enum CurrencyDisplay {
        SYMBOL,
        NARROWSYMBOL,
        CODE,
        NAME;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay[ordinal()];
            if (i == 1) {
                return "symbol";
            }
            if (i == 2) {
                return "narrowSymbol";
            }
            if (i == 3) {
                return "code";
            }
            if (i == 4) {
                return "name";
            }
            throw new IllegalArgumentException();
        }

        public int getNameStyle() {
            return AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay[ordinal()] != 4 ? 0 : 1;
        }
    }

    /* renamed from: com.facebook.hermes.intl.IPlatformNumberFormatter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CompactDisplay;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencySign;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Notation;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Style;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$UnitDisplay;

        /* JADX WARNING: Can't wrap try/catch for region: R(48:0|(2:1|2)|3|(2:5|6)|7|9|10|(2:11|12)|13|(2:15|16)|17|(2:19|20)|21|23|24|25|26|27|28|29|31|32|33|34|35|36|(2:37|38)|39|41|42|(2:43|44)|45|47|48|49|50|51|52|53|54|55|57|58|59|60|61|62|(3:63|64|66)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(49:0|(2:1|2)|3|(2:5|6)|7|9|10|(2:11|12)|13|15|16|17|(2:19|20)|21|23|24|25|26|27|28|29|31|32|33|34|35|36|(2:37|38)|39|41|42|(2:43|44)|45|47|48|49|50|51|52|53|54|55|57|58|59|60|61|62|(3:63|64|66)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(54:0|(2:1|2)|3|5|6|7|9|10|(2:11|12)|13|15|16|17|(2:19|20)|21|23|24|25|26|27|28|29|31|32|33|34|35|36|37|38|39|41|42|43|44|45|47|48|49|50|51|52|53|54|55|57|58|59|60|61|62|63|64|66) */
        /* JADX WARNING: Can't wrap try/catch for region: R(56:0|1|2|3|5|6|7|9|10|11|12|13|15|16|17|(2:19|20)|21|23|24|25|26|27|28|29|31|32|33|34|35|36|37|38|39|41|42|43|44|45|47|48|49|50|51|52|53|54|55|57|58|59|60|61|62|63|64|66) */
        /* JADX WARNING: Can't wrap try/catch for region: R(57:0|1|2|3|5|6|7|9|10|11|12|13|15|16|17|19|20|21|23|24|25|26|27|28|29|31|32|33|34|35|36|37|38|39|41|42|43|44|45|47|48|49|50|51|52|53|54|55|57|58|59|60|61|62|63|64|66) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x005f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0069 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x008e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0098 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00b3 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:49:0x00ce */
        /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x00d8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00e2 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x00fd */
        /* JADX WARNING: Missing exception handler attribute for start block: B:61:0x0107 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:63:0x0111 */
        static {
            /*
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencySign[] r0 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencySign.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencySign = r0
                r1 = 1
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencySign r2 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencySign.ACCOUNTING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencySign     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencySign r3 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencySign.STANDARD     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencyDisplay[] r2 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencyDisplay.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay = r2
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencyDisplay r3 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencyDisplay.SYMBOL     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r2 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencyDisplay r3 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencyDisplay.NARROWSYMBOL     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                r2 = 3
                int[] r3 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencyDisplay r4 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencyDisplay.CODE     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                r3 = 4
                int[] r4 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay     // Catch:{ NoSuchFieldError -> 0x004e }
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencyDisplay r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencyDisplay.NAME     // Catch:{ NoSuchFieldError -> 0x004e }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                com.facebook.hermes.intl.IPlatformNumberFormatter$UnitDisplay[] r4 = com.facebook.hermes.intl.IPlatformNumberFormatter.UnitDisplay.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$UnitDisplay = r4
                com.facebook.hermes.intl.IPlatformNumberFormatter$UnitDisplay r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.UnitDisplay.SHORT     // Catch:{ NoSuchFieldError -> 0x005f }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x005f }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x005f }
            L_0x005f:
                int[] r4 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$UnitDisplay     // Catch:{ NoSuchFieldError -> 0x0069 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$UnitDisplay r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.UnitDisplay.NARROW     // Catch:{ NoSuchFieldError -> 0x0069 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0069 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0069 }
            L_0x0069:
                int[] r4 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$UnitDisplay     // Catch:{ NoSuchFieldError -> 0x0073 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$UnitDisplay r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.UnitDisplay.LONG     // Catch:{ NoSuchFieldError -> 0x0073 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0073 }
                r4[r5] = r2     // Catch:{ NoSuchFieldError -> 0x0073 }
            L_0x0073:
                com.facebook.hermes.intl.IPlatformNumberFormatter$SignDisplay[] r4 = com.facebook.hermes.intl.IPlatformNumberFormatter.SignDisplay.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay = r4
                com.facebook.hermes.intl.IPlatformNumberFormatter$SignDisplay r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.SignDisplay.AUTO     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r4 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay     // Catch:{ NoSuchFieldError -> 0x008e }
                com.facebook.hermes.intl.IPlatformNumberFormatter$SignDisplay r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.SignDisplay.ALWAYS     // Catch:{ NoSuchFieldError -> 0x008e }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x008e }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x008e }
            L_0x008e:
                int[] r4 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay     // Catch:{ NoSuchFieldError -> 0x0098 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$SignDisplay r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.SignDisplay.NEVER     // Catch:{ NoSuchFieldError -> 0x0098 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0098 }
                r4[r5] = r2     // Catch:{ NoSuchFieldError -> 0x0098 }
            L_0x0098:
                int[] r4 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay     // Catch:{ NoSuchFieldError -> 0x00a2 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$SignDisplay r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.SignDisplay.EXCEPTZERO     // Catch:{ NoSuchFieldError -> 0x00a2 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a2 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x00a2 }
            L_0x00a2:
                com.facebook.hermes.intl.IPlatformNumberFormatter$CompactDisplay[] r4 = com.facebook.hermes.intl.IPlatformNumberFormatter.CompactDisplay.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CompactDisplay = r4
                com.facebook.hermes.intl.IPlatformNumberFormatter$CompactDisplay r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.CompactDisplay.SHORT     // Catch:{ NoSuchFieldError -> 0x00b3 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b3 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x00b3 }
            L_0x00b3:
                int[] r4 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CompactDisplay     // Catch:{ NoSuchFieldError -> 0x00bd }
                com.facebook.hermes.intl.IPlatformNumberFormatter$CompactDisplay r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.CompactDisplay.LONG     // Catch:{ NoSuchFieldError -> 0x00bd }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x00bd }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x00bd }
            L_0x00bd:
                com.facebook.hermes.intl.IPlatformNumberFormatter$Notation[] r4 = com.facebook.hermes.intl.IPlatformNumberFormatter.Notation.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Notation = r4
                com.facebook.hermes.intl.IPlatformNumberFormatter$Notation r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.Notation.STANDARD     // Catch:{ NoSuchFieldError -> 0x00ce }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ce }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x00ce }
            L_0x00ce:
                int[] r4 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Notation     // Catch:{ NoSuchFieldError -> 0x00d8 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$Notation r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.Notation.SCIENTIFIC     // Catch:{ NoSuchFieldError -> 0x00d8 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
                r4[r5] = r0     // Catch:{ NoSuchFieldError -> 0x00d8 }
            L_0x00d8:
                int[] r4 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Notation     // Catch:{ NoSuchFieldError -> 0x00e2 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$Notation r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.Notation.ENGINEERING     // Catch:{ NoSuchFieldError -> 0x00e2 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e2 }
                r4[r5] = r2     // Catch:{ NoSuchFieldError -> 0x00e2 }
            L_0x00e2:
                int[] r4 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Notation     // Catch:{ NoSuchFieldError -> 0x00ec }
                com.facebook.hermes.intl.IPlatformNumberFormatter$Notation r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.Notation.COMPACT     // Catch:{ NoSuchFieldError -> 0x00ec }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ec }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x00ec }
            L_0x00ec:
                com.facebook.hermes.intl.IPlatformNumberFormatter$Style[] r4 = com.facebook.hermes.intl.IPlatformNumberFormatter.Style.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Style = r4
                com.facebook.hermes.intl.IPlatformNumberFormatter$Style r5 = com.facebook.hermes.intl.IPlatformNumberFormatter.Style.DECIMAL     // Catch:{ NoSuchFieldError -> 0x00fd }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x00fd }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x00fd }
            L_0x00fd:
                int[] r1 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Style     // Catch:{ NoSuchFieldError -> 0x0107 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$Style r4 = com.facebook.hermes.intl.IPlatformNumberFormatter.Style.PERCENT     // Catch:{ NoSuchFieldError -> 0x0107 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0107 }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0107 }
            L_0x0107:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Style     // Catch:{ NoSuchFieldError -> 0x0111 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$Style r1 = com.facebook.hermes.intl.IPlatformNumberFormatter.Style.CURRENCY     // Catch:{ NoSuchFieldError -> 0x0111 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0111 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0111 }
            L_0x0111:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$Style     // Catch:{ NoSuchFieldError -> 0x011b }
                com.facebook.hermes.intl.IPlatformNumberFormatter$Style r1 = com.facebook.hermes.intl.IPlatformNumberFormatter.Style.UNIT     // Catch:{ NoSuchFieldError -> 0x011b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x011b }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x011b }
            L_0x011b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.hermes.intl.IPlatformNumberFormatter.AnonymousClass1.<clinit>():void");
        }
    }

    public enum CurrencySign {
        STANDARD,
        ACCOUNTING;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencySign[ordinal()];
            if (i == 1) {
                return "accounting";
            }
            if (i == 2) {
                return Constants.COLLATION_STANDARD;
            }
            throw new IllegalArgumentException();
        }
    }
}
