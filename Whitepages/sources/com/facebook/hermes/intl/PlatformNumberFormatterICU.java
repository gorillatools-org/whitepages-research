package com.facebook.hermes.intl;

import android.icu.text.CompactDecimalFormat;
import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.MeasureFormat;
import android.icu.text.NumberFormat;
import android.icu.text.NumberingSystem;
import android.icu.util.Currency;
import android.icu.util.MeasureUnit;
import android.icu.util.ULocale;
import android.os.Build;
import com.facebook.hermes.intl.IPlatformNumberFormatter;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.text.AttributedCharacterIterator;
import java.text.Format;
import java.util.ArrayList;

public class PlatformNumberFormatterICU implements IPlatformNumberFormatter {
    private Format mFinalFormat;
    private LocaleObjectICU mLocaleObject;
    private MeasureUnit mMeasureUnit;
    private NumberFormat mNumberFormat;
    private IPlatformNumberFormatter.Style mStyle;

    PlatformNumberFormatterICU() {
    }

    private void initialize(NumberFormat numberFormat, ILocaleObject<?> iLocaleObject, IPlatformNumberFormatter.Style style) {
        this.mNumberFormat = numberFormat;
        this.mFinalFormat = numberFormat;
        this.mLocaleObject = (LocaleObjectICU) iLocaleObject;
        this.mStyle = style;
        numberFormat.setRoundingMode(4);
    }

    public PlatformNumberFormatterICU setCurrency(String str, IPlatformNumberFormatter.CurrencyDisplay currencyDisplay) throws JSRangeErrorException {
        if (this.mStyle == IPlatformNumberFormatter.Style.CURRENCY) {
            Currency instance = Currency.getInstance(str);
            this.mNumberFormat.setCurrency(instance);
            if (currencyDisplay != IPlatformNumberFormatter.CurrencyDisplay.CODE) {
                str = instance.getName(this.mLocaleObject.getLocale(), currencyDisplay.getNameStyle(), (boolean[]) null);
            }
            NumberFormat numberFormat = this.mNumberFormat;
            if (numberFormat instanceof DecimalFormat) {
                DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
                DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();
                decimalFormatSymbols.setCurrencySymbol(str);
                decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
            }
        }
        return this;
    }

    public PlatformNumberFormatterICU setGrouping(boolean z) {
        this.mNumberFormat.setGroupingUsed(z);
        return this;
    }

    public PlatformNumberFormatterICU setMinIntergerDigits(int i) {
        if (i != -1) {
            this.mNumberFormat.setMinimumIntegerDigits(i);
        }
        return this;
    }

    public PlatformNumberFormatterICU setSignificantDigits(IPlatformNumberFormatter.RoundingType roundingType, int i, int i2) throws JSRangeErrorException {
        NumberFormat numberFormat = this.mNumberFormat;
        if ((numberFormat instanceof DecimalFormat) && roundingType == IPlatformNumberFormatter.RoundingType.SIGNIFICANT_DIGITS) {
            DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
            if (i >= 0) {
                decimalFormat.setMinimumSignificantDigits(i);
            }
            if (i2 >= 0) {
                if (i2 >= decimalFormat.getMinimumSignificantDigits()) {
                    decimalFormat.setMaximumSignificantDigits(i2);
                } else {
                    throw new JSRangeErrorException("maximumSignificantDigits should be at least equal to minimumSignificantDigits");
                }
            }
            decimalFormat.setSignificantDigitsUsed(true);
        }
        return this;
    }

    public PlatformNumberFormatterICU setFractionDigits(IPlatformNumberFormatter.RoundingType roundingType, int i, int i2) {
        if (roundingType == IPlatformNumberFormatter.RoundingType.FRACTION_DIGITS) {
            if (i >= 0) {
                this.mNumberFormat.setMinimumFractionDigits(i);
            }
            if (i2 >= 0) {
                this.mNumberFormat.setMaximumFractionDigits(i2);
            }
            NumberFormat numberFormat = this.mNumberFormat;
            if (numberFormat instanceof DecimalFormat) {
                ((DecimalFormat) numberFormat).setSignificantDigitsUsed(false);
            }
        }
        return this;
    }

    public PlatformNumberFormatterICU setSignDisplay(IPlatformNumberFormatter.SignDisplay signDisplay) {
        NumberFormat numberFormat = this.mNumberFormat;
        if (numberFormat instanceof DecimalFormat) {
            DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
            DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();
            if (Build.VERSION.SDK_INT >= 31) {
                int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay[signDisplay.ordinal()];
                if (i == 1) {
                    decimalFormat.setSignAlwaysShown(false);
                } else if (i == 2 || i == 3) {
                    decimalFormat.setSignAlwaysShown(true);
                }
            } else {
                int i2 = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay[signDisplay.ordinal()];
                if (i2 == 1) {
                    decimalFormat.setPositivePrefix("");
                    decimalFormat.setPositiveSuffix("");
                    decimalFormat.setNegativePrefix("");
                    decimalFormat.setNegativeSuffix("");
                } else if (i2 == 2 || i2 == 3) {
                    if (!decimalFormat.getNegativePrefix().isEmpty()) {
                        decimalFormat.setPositivePrefix(new String(new char[]{decimalFormatSymbols.getPlusSign()}));
                    }
                    if (!decimalFormat.getNegativeSuffix().isEmpty()) {
                        decimalFormat.setPositiveSuffix(new String(new char[]{decimalFormatSymbols.getPlusSign()}));
                    }
                }
            }
        }
        return this;
    }

    /* renamed from: com.facebook.hermes.intl.PlatformNumberFormatterICU$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.facebook.hermes.intl.IPlatformNumberFormatter$SignDisplay[] r0 = com.facebook.hermes.intl.IPlatformNumberFormatter.SignDisplay.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay = r0
                com.facebook.hermes.intl.IPlatformNumberFormatter$SignDisplay r1 = com.facebook.hermes.intl.IPlatformNumberFormatter.SignDisplay.NEVER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.hermes.intl.IPlatformNumberFormatter$SignDisplay r1 = com.facebook.hermes.intl.IPlatformNumberFormatter.SignDisplay.ALWAYS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$SignDisplay     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$SignDisplay r1 = com.facebook.hermes.intl.IPlatformNumberFormatter.SignDisplay.EXCEPTZERO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.hermes.intl.PlatformNumberFormatterICU.AnonymousClass1.<clinit>():void");
        }
    }

    private static MeasureUnit parseUnit(String str) throws JSRangeErrorException {
        for (MeasureUnit next : MeasureUnit.getAvailable()) {
            if (!next.getSubtype().equals(str)) {
                String subtype = next.getSubtype();
                if (subtype.equals(next.getType() + "-" + str)) {
                }
            }
            return next;
        }
        throw new JSRangeErrorException("Unknown unit: " + str);
    }

    public PlatformNumberFormatterICU setUnits(String str, IPlatformNumberFormatter.UnitDisplay unitDisplay) throws JSRangeErrorException {
        if (this.mStyle == IPlatformNumberFormatter.Style.UNIT) {
            this.mMeasureUnit = parseUnit(str);
            this.mFinalFormat = MeasureFormat.getInstance(this.mLocaleObject.getLocale(), unitDisplay.getFormatWidth(), this.mNumberFormat);
        }
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003e, code lost:
        return android.icu.text.NumberFormat.getInstance(android.icu.util.ULocale.forLanguageTag("en")).format(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        return android.icu.text.NumberFormat.getInstance(android.icu.util.ULocale.getDefault()).format(r5);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0023 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String format(double r5) {
        /*
            r4 = this;
            java.text.Format r0 = r4.mFinalFormat     // Catch:{ NumberFormatException -> 0x0023 }
            boolean r1 = r0 instanceof android.icu.text.MeasureFormat     // Catch:{ NumberFormatException -> 0x0023 }
            if (r1 == 0) goto L_0x001a
            android.icu.util.MeasureUnit r1 = r4.mMeasureUnit     // Catch:{ NumberFormatException -> 0x0023 }
            if (r1 == 0) goto L_0x001a
            android.icu.util.Measure r1 = new android.icu.util.Measure     // Catch:{ NumberFormatException -> 0x0023 }
            java.lang.Double r2 = java.lang.Double.valueOf(r5)     // Catch:{ NumberFormatException -> 0x0023 }
            android.icu.util.MeasureUnit r3 = r4.mMeasureUnit     // Catch:{ NumberFormatException -> 0x0023 }
            r1.<init>(r2, r3)     // Catch:{ NumberFormatException -> 0x0023 }
            java.lang.String r5 = r0.format(r1)     // Catch:{ NumberFormatException -> 0x0023 }
            goto L_0x0022
        L_0x001a:
            java.lang.Double r1 = java.lang.Double.valueOf(r5)     // Catch:{ NumberFormatException -> 0x0023 }
            java.lang.String r5 = r0.format(r1)     // Catch:{ NumberFormatException -> 0x0023 }
        L_0x0022:
            return r5
        L_0x0023:
            android.icu.util.ULocale r0 = android.icu.util.ULocale.getDefault()     // Catch:{ RuntimeException -> 0x0030 }
            android.icu.text.NumberFormat r0 = android.icu.text.NumberFormat.getInstance(r0)     // Catch:{ RuntimeException -> 0x0030 }
            java.lang.String r5 = r0.format(r5)     // Catch:{ RuntimeException -> 0x0030 }
            return r5
        L_0x0030:
            java.lang.String r0 = "en"
            android.icu.util.ULocale r0 = android.icu.util.ULocale.forLanguageTag(r0)
            android.icu.text.NumberFormat r0 = android.icu.text.NumberFormat.getInstance(r0)
            java.lang.String r5 = r0.format(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.hermes.intl.PlatformNumberFormatterICU.format(double):java.lang.String");
    }

    public String fieldToString(AttributedCharacterIterator.Attribute attribute, double d) {
        if (attribute == NumberFormat.Field.SIGN) {
            if (Double.compare(d, 0.0d) >= 0) {
                return "plusSign";
            }
            return "minusSign";
        } else if (attribute == NumberFormat.Field.INTEGER) {
            if (Double.isNaN(d)) {
                return "nan";
            }
            if (Double.isInfinite(d)) {
                return "infinity";
            }
            return "integer";
        } else if (attribute == NumberFormat.Field.FRACTION) {
            return "fraction";
        } else {
            if (attribute == NumberFormat.Field.EXPONENT) {
                return "exponentInteger";
            }
            if (attribute == NumberFormat.Field.EXPONENT_SIGN) {
                return "exponentMinusSign";
            }
            if (attribute == NumberFormat.Field.EXPONENT_SYMBOL) {
                return "exponentSeparator";
            }
            if (attribute == NumberFormat.Field.DECIMAL_SEPARATOR) {
                return "decimal";
            }
            if (attribute == NumberFormat.Field.GROUPING_SEPARATOR) {
                return "group";
            }
            if (attribute == NumberFormat.Field.PERCENT) {
                return "percentSign";
            }
            if (attribute == NumberFormat.Field.PERMILLE) {
                return "permilleSign";
            }
            if (attribute == NumberFormat.Field.CURRENCY) {
                return FirebaseAnalytics.Param.CURRENCY;
            }
            if (attribute.toString().equals("android.icu.text.NumberFormat$Field(compact)")) {
                return "compact";
            }
            return "literal";
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.text.AttributedCharacterIterator formatToParts(double r6) {
        /*
            r5 = this;
            java.lang.String r0 = "en"
            java.text.Format r1 = r5.mFinalFormat     // Catch:{ NumberFormatException -> 0x0036, Exception -> 0x0025 }
            boolean r2 = r1 instanceof android.icu.text.MeasureFormat     // Catch:{ NumberFormatException -> 0x0036, Exception -> 0x0025 }
            if (r2 == 0) goto L_0x001c
            android.icu.util.MeasureUnit r2 = r5.mMeasureUnit     // Catch:{ NumberFormatException -> 0x0036, Exception -> 0x0025 }
            if (r2 == 0) goto L_0x001c
            android.icu.util.Measure r2 = new android.icu.util.Measure     // Catch:{ NumberFormatException -> 0x0036, Exception -> 0x0025 }
            java.lang.Double r3 = java.lang.Double.valueOf(r6)     // Catch:{ NumberFormatException -> 0x0036, Exception -> 0x0025 }
            android.icu.util.MeasureUnit r4 = r5.mMeasureUnit     // Catch:{ NumberFormatException -> 0x0036, Exception -> 0x0025 }
            r2.<init>(r3, r4)     // Catch:{ NumberFormatException -> 0x0036, Exception -> 0x0025 }
            java.text.AttributedCharacterIterator r6 = r1.formatToCharacterIterator(r2)     // Catch:{ NumberFormatException -> 0x0036, Exception -> 0x0025 }
            goto L_0x0024
        L_0x001c:
            java.lang.Double r2 = java.lang.Double.valueOf(r6)     // Catch:{ NumberFormatException -> 0x0036, Exception -> 0x0025 }
            java.text.AttributedCharacterIterator r6 = r1.formatToCharacterIterator(r2)     // Catch:{ NumberFormatException -> 0x0036, Exception -> 0x0025 }
        L_0x0024:
            return r6
        L_0x0025:
            android.icu.util.ULocale r0 = android.icu.util.ULocale.forLanguageTag(r0)
            android.icu.text.NumberFormat r0 = android.icu.text.NumberFormat.getInstance(r0)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            java.text.AttributedCharacterIterator r6 = r0.formatToCharacterIterator(r6)
            return r6
        L_0x0036:
            android.icu.util.ULocale r1 = android.icu.util.ULocale.getDefault()     // Catch:{ RuntimeException -> 0x0047 }
            android.icu.text.NumberFormat r1 = android.icu.text.NumberFormat.getInstance(r1)     // Catch:{ RuntimeException -> 0x0047 }
            java.lang.Double r2 = java.lang.Double.valueOf(r6)     // Catch:{ RuntimeException -> 0x0047 }
            java.text.AttributedCharacterIterator r6 = r1.formatToCharacterIterator(r2)     // Catch:{ RuntimeException -> 0x0047 }
            return r6
        L_0x0047:
            android.icu.util.ULocale r0 = android.icu.util.ULocale.forLanguageTag(r0)
            android.icu.text.NumberFormat r0 = android.icu.text.NumberFormat.getInstance(r0)
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            java.text.AttributedCharacterIterator r6 = r0.formatToCharacterIterator(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.hermes.intl.PlatformNumberFormatterICU.formatToParts(double):java.text.AttributedCharacterIterator");
    }

    public static int getCurrencyDigits(String str) throws JSRangeErrorException {
        try {
            return Currency.getInstance(str).getDefaultFractionDigits();
        } catch (IllegalArgumentException unused) {
            throw new JSRangeErrorException("Invalid currency code !");
        }
    }

    public PlatformNumberFormatterICU configure(ILocaleObject<?> iLocaleObject, String str, IPlatformNumberFormatter.Style style, IPlatformNumberFormatter.CurrencySign currencySign, IPlatformNumberFormatter.Notation notation, IPlatformNumberFormatter.CompactDisplay compactDisplay) throws JSRangeErrorException {
        CompactDecimalFormat.CompactStyle compactStyle;
        if (!str.isEmpty()) {
            try {
                if (NumberingSystem.getInstanceByName(JSObjects.getJavaString(str)) != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(JSObjects.getJavaString(str));
                    iLocaleObject.setUnicodeExtensions("nu", arrayList);
                } else {
                    throw new JSRangeErrorException("Invalid numbering system: " + str);
                }
            } catch (RuntimeException unused) {
                throw new JSRangeErrorException("Invalid numbering system: " + str);
            }
        }
        if (notation == IPlatformNumberFormatter.Notation.COMPACT && (style == IPlatformNumberFormatter.Style.DECIMAL || style == IPlatformNumberFormatter.Style.UNIT)) {
            if (compactDisplay == IPlatformNumberFormatter.CompactDisplay.SHORT) {
                compactStyle = CompactDecimalFormat.CompactStyle.SHORT;
            } else {
                compactStyle = CompactDecimalFormat.CompactStyle.LONG;
            }
            initialize(CompactDecimalFormat.getInstance((ULocale) iLocaleObject.getLocale(), compactStyle), iLocaleObject, style);
        } else {
            NumberFormat instance = NumberFormat.getInstance((ULocale) iLocaleObject.getLocale(), style.getInitialNumberFormatStyle(notation, currencySign));
            if (notation == IPlatformNumberFormatter.Notation.ENGINEERING) {
                instance.setMaximumIntegerDigits(3);
            }
            initialize(instance, iLocaleObject, style);
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

    public String getDefaultNumberingSystem(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        return NumberingSystem.getInstance((ULocale) iLocaleObject.getLocale()).getName();
    }
}
