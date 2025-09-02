package com.facebook.hermes.intl;

import com.facebook.hermes.intl.IPlatformNumberFormatter;
import java.math.RoundingMode;
import java.text.AttributedCharacterIterator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class PlatformNumberFormatterAndroid implements IPlatformNumberFormatter {
    private DecimalFormat mDecimalFormat;
    private Format mFinalFormat;
    private LocaleObjectAndroid mLocaleObject;
    private IPlatformNumberFormatter.Style mStyle;

    public PlatformNumberFormatterAndroid setSignificantDigits(IPlatformNumberFormatter.RoundingType roundingType, int i, int i2) {
        return this;
    }

    public PlatformNumberFormatterAndroid setUnits(String str, IPlatformNumberFormatter.UnitDisplay unitDisplay) {
        return this;
    }

    PlatformNumberFormatterAndroid() {
    }

    private void initialize(DecimalFormat decimalFormat, ILocaleObject<?> iLocaleObject, IPlatformNumberFormatter.Style style) {
        this.mDecimalFormat = decimalFormat;
        this.mFinalFormat = decimalFormat;
        this.mLocaleObject = (LocaleObjectAndroid) iLocaleObject;
        this.mStyle = style;
    }

    public PlatformNumberFormatterAndroid setCurrency(String str, IPlatformNumberFormatter.CurrencyDisplay currencyDisplay) throws JSRangeErrorException {
        if (this.mStyle == IPlatformNumberFormatter.Style.CURRENCY) {
            Currency instance = Currency.getInstance(str);
            this.mDecimalFormat.setCurrency(instance);
            int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay[currencyDisplay.ordinal()];
            if (i == 1) {
                str = instance.getDisplayName(this.mLocaleObject.getLocale());
            } else if (i != 2) {
                str = instance.getSymbol(this.mLocaleObject.getLocale());
            }
            DecimalFormatSymbols decimalFormatSymbols = this.mDecimalFormat.getDecimalFormatSymbols();
            decimalFormatSymbols.setCurrencySymbol(str);
            this.mDecimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
            this.mDecimalFormat.getDecimalFormatSymbols().setCurrencySymbol(str);
        }
        return this;
    }

    /* renamed from: com.facebook.hermes.intl.PlatformNumberFormatterAndroid$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencyDisplay[] r0 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencyDisplay.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay = r0
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencyDisplay r1 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencyDisplay.NAME     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencyDisplay r1 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencyDisplay.CODE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencyDisplay r1 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencyDisplay.SYMBOL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformNumberFormatter$CurrencyDisplay     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.hermes.intl.IPlatformNumberFormatter$CurrencyDisplay r1 = com.facebook.hermes.intl.IPlatformNumberFormatter.CurrencyDisplay.NARROWSYMBOL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.hermes.intl.PlatformNumberFormatterAndroid.AnonymousClass1.<clinit>():void");
        }
    }

    public PlatformNumberFormatterAndroid setGrouping(boolean z) {
        this.mDecimalFormat.setGroupingUsed(z);
        return this;
    }

    public PlatformNumberFormatterAndroid setMinIntergerDigits(int i) {
        if (i != -1) {
            this.mDecimalFormat.setMinimumIntegerDigits(i);
        }
        return this;
    }

    public PlatformNumberFormatterAndroid setFractionDigits(IPlatformNumberFormatter.RoundingType roundingType, int i, int i2) {
        if (roundingType == IPlatformNumberFormatter.RoundingType.FRACTION_DIGITS) {
            if (i >= 0) {
                this.mDecimalFormat.setMinimumFractionDigits(i);
            }
            if (i2 >= 0) {
                this.mDecimalFormat.setMaximumFractionDigits(i2);
            }
        }
        return this;
    }

    public PlatformNumberFormatterAndroid setSignDisplay(IPlatformNumberFormatter.SignDisplay signDisplay) {
        if (signDisplay == IPlatformNumberFormatter.SignDisplay.NEVER) {
            this.mDecimalFormat.setPositivePrefix("");
            this.mDecimalFormat.setPositiveSuffix("");
            this.mDecimalFormat.setNegativePrefix("");
            this.mDecimalFormat.setNegativeSuffix("");
        }
        return this;
    }

    public static int getCurrencyDigits(String str) throws JSRangeErrorException {
        try {
            return Currency.getInstance(str).getDefaultFractionDigits();
        } catch (IllegalArgumentException unused) {
            throw new JSRangeErrorException("Invalid currency code !");
        }
    }

    public String format(double d) {
        return this.mFinalFormat.format(Double.valueOf(d));
    }

    public String fieldToString(AttributedCharacterIterator.Attribute attribute, double d) {
        return "literal";
    }

    public AttributedCharacterIterator formatToParts(double d) {
        return this.mFinalFormat.formatToCharacterIterator(Double.valueOf(d));
    }

    public PlatformNumberFormatterAndroid configure(ILocaleObject<?> iLocaleObject, String str, IPlatformNumberFormatter.Style style, IPlatformNumberFormatter.CurrencySign currencySign, IPlatformNumberFormatter.Notation notation, IPlatformNumberFormatter.CompactDisplay compactDisplay) throws JSRangeErrorException {
        NumberFormat instance = NumberFormat.getInstance((Locale) iLocaleObject.getLocale());
        instance.setRoundingMode(RoundingMode.HALF_UP);
        initialize((DecimalFormat) instance, iLocaleObject, style);
        return this;
    }

    public String[] getAvailableLocales() {
        ArrayList arrayList = new ArrayList();
        for (Locale languageTag : NumberFormat.getAvailableLocales()) {
            arrayList.add(languageTag.toLanguageTag());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public String getDefaultNumberingSystem(ILocaleObject<?> iLocaleObject) {
        return "latn";
    }
}
