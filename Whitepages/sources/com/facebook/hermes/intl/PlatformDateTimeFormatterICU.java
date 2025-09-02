package com.facebook.hermes.intl;

import android.icu.text.DateFormat;
import android.icu.text.NumberingSystem;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.icu.util.ULocale;
import com.facebook.hermes.intl.IPlatformDateTimeFormatter;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PlatformDateTimeFormatterICU implements IPlatformDateTimeFormatter {
    private DateFormat mDateFormat = null;

    public String format(double d) {
        return this.mDateFormat.format(new Date((long) d));
    }

    public String fieldToString(AttributedCharacterIterator.Attribute attribute, String str) {
        if (attribute == DateFormat.Field.DAY_OF_WEEK) {
            return "weekday";
        }
        if (attribute == DateFormat.Field.ERA) {
            return "era";
        }
        if (attribute == DateFormat.Field.YEAR) {
            try {
                Double.parseDouble(str);
                return "year";
            } catch (NumberFormatException unused) {
                return "yearName";
            }
        } else if (attribute == DateFormat.Field.MONTH) {
            return "month";
        } else {
            if (attribute == DateFormat.Field.DAY_OF_MONTH) {
                return "day";
            }
            if (attribute == DateFormat.Field.HOUR0 || attribute == DateFormat.Field.HOUR1 || attribute == DateFormat.Field.HOUR_OF_DAY0 || attribute == DateFormat.Field.HOUR_OF_DAY1) {
                return "hour";
            }
            if (attribute == DateFormat.Field.MINUTE) {
                return "minute";
            }
            if (attribute == DateFormat.Field.SECOND) {
                return "second";
            }
            if (attribute == DateFormat.Field.TIME_ZONE) {
                return "timeZoneName";
            }
            if (attribute == DateFormat.Field.AM_PM) {
                return "dayPeriod";
            }
            if (attribute.toString().equals("android.icu.text.DateFormat$Field(related year)")) {
                return "relatedYear";
            }
            return "literal";
        }
    }

    public AttributedCharacterIterator formatToParts(double d) {
        return this.mDateFormat.formatToCharacterIterator(Double.valueOf(d));
    }

    public String getDefaultCalendarName(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        return UnicodeExtensionKeys.resolveCalendarAlias(DateFormat.getDateInstance(3, (ULocale) iLocaleObject.getLocale()).getCalendar().getType());
    }

    private static class PatternUtils {
        private PatternUtils() {
        }

        public static String getPatternWithoutLiterals(String str) {
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt == '\'') {
                    z = !z;
                } else if (!z && ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z'))) {
                    sb.append(str.charAt(i));
                }
            }
            return sb.toString();
        }
    }

    public IPlatformDateTimeFormatter.HourCycle getDefaultHourCycle(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        try {
            String patternWithoutLiterals = PatternUtils.getPatternWithoutLiterals(((SimpleDateFormat) DateFormat.getTimeInstance(0, (ULocale) iLocaleObject.getLocale())).toPattern());
            if (patternWithoutLiterals.contains(String.valueOf('h'))) {
                return IPlatformDateTimeFormatter.HourCycle.H12;
            }
            if (patternWithoutLiterals.contains(String.valueOf('K'))) {
                return IPlatformDateTimeFormatter.HourCycle.H11;
            }
            if (patternWithoutLiterals.contains(String.valueOf('H'))) {
                return IPlatformDateTimeFormatter.HourCycle.H23;
            }
            return IPlatformDateTimeFormatter.HourCycle.H24;
        } catch (ClassCastException unused) {
            return IPlatformDateTimeFormatter.HourCycle.H24;
        }
    }

    public String getDefaultTimeZone(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        return Calendar.getInstance((ULocale) iLocaleObject.getLocale()).getTimeZone().getID();
    }

    public String getDefaultNumberingSystem(ILocaleObject<?> iLocaleObject) throws JSRangeErrorException {
        return NumberingSystem.getInstance((ULocale) iLocaleObject.getLocale()).getName();
    }

    static int toICUDateStyle(IPlatformDateTimeFormatter.DateStyle dateStyle) throws JSRangeErrorException {
        int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle[dateStyle.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        if (i == 4) {
            return 3;
        }
        throw new JSRangeErrorException("Invalid DateStyle: " + dateStyle.toString());
    }

    /* renamed from: com.facebook.hermes.intl.PlatformDateTimeFormatterICU$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle;
        static final /* synthetic */ int[] $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|(2:17|18)|19|21|22|23|24|25|26|27|28|(3:29|30|32)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|(2:17|18)|19|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x004f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0059 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0063 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x006d */
        static {
            /*
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeStyle[] r0 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeStyle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle = r0
                r1 = 1
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeStyle r2 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeStyle.FULL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeStyle r3 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeStyle.LONG     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeStyle r4 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeStyle.MEDIUM     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeStyle r5 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeStyle.SHORT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle     // Catch:{ NoSuchFieldError -> 0x003e }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$TimeStyle r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.TimeStyle.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$DateStyle[] r5 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.DateStyle.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle = r5
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$DateStyle r6 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.DateStyle.FULL     // Catch:{ NoSuchFieldError -> 0x004f }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x004f }
                r5[r6] = r1     // Catch:{ NoSuchFieldError -> 0x004f }
            L_0x004f:
                int[] r1 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle     // Catch:{ NoSuchFieldError -> 0x0059 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$DateStyle r5 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.DateStyle.LONG     // Catch:{ NoSuchFieldError -> 0x0059 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0059 }
                r1[r5] = r0     // Catch:{ NoSuchFieldError -> 0x0059 }
            L_0x0059:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle     // Catch:{ NoSuchFieldError -> 0x0063 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$DateStyle r1 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.DateStyle.MEDIUM     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle     // Catch:{ NoSuchFieldError -> 0x006d }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$DateStyle r1 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.DateStyle.SHORT     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r0 = $SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$DateStyle     // Catch:{ NoSuchFieldError -> 0x0077 }
                com.facebook.hermes.intl.IPlatformDateTimeFormatter$DateStyle r1 = com.facebook.hermes.intl.IPlatformDateTimeFormatter.DateStyle.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x0077 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0077 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0077 }
            L_0x0077:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.hermes.intl.PlatformDateTimeFormatterICU.AnonymousClass1.<clinit>():void");
        }
    }

    static int toICUTimeStyle(IPlatformDateTimeFormatter.TimeStyle timeStyle) throws JSRangeErrorException {
        int i = AnonymousClass1.$SwitchMap$com$facebook$hermes$intl$IPlatformDateTimeFormatter$TimeStyle[timeStyle.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        if (i == 4) {
            return 3;
        }
        throw new JSRangeErrorException("Invalid DateStyle: " + timeStyle.toString());
    }

    private static void replaceChars(StringBuilder sb, String str, String str2) {
        int indexOf = sb.indexOf(str);
        if (indexOf != -1) {
            sb.replace(indexOf, str.length() + indexOf, str2);
        }
    }

    private static String getPatternForStyle(ILocaleObject<?> iLocaleObject, IPlatformDateTimeFormatter.DateStyle dateStyle, IPlatformDateTimeFormatter.TimeStyle timeStyle) throws JSRangeErrorException {
        if (dateStyle == IPlatformDateTimeFormatter.DateStyle.UNDEFINED) {
            return ((SimpleDateFormat) DateFormat.getTimeInstance(toICUTimeStyle(timeStyle), (ULocale) iLocaleObject.getLocale())).toLocalizedPattern();
        }
        if (timeStyle == IPlatformDateTimeFormatter.TimeStyle.UNDEFINED) {
            return ((SimpleDateFormat) DateFormat.getDateInstance(toICUDateStyle(dateStyle), (ULocale) iLocaleObject.getLocale())).toLocalizedPattern();
        }
        return ((SimpleDateFormat) DateFormat.getDateTimeInstance(toICUDateStyle(dateStyle), toICUTimeStyle(timeStyle), (ULocale) iLocaleObject.getLocale())).toLocalizedPattern();
    }

    private static void replacePatternChars(StringBuilder sb, char[] cArr, char c) {
        for (int i = 0; i < sb.length(); i++) {
            int length = cArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                if (sb.charAt(i) == cArr[i2]) {
                    sb.setCharAt(i, c);
                    break;
                }
                i2++;
            }
        }
    }

    private static String getSkeleton(ILocaleObject<?> iLocaleObject, IPlatformDateTimeFormatter.WeekDay weekDay, IPlatformDateTimeFormatter.Era era, IPlatformDateTimeFormatter.Year year, IPlatformDateTimeFormatter.Month month, IPlatformDateTimeFormatter.Day day, IPlatformDateTimeFormatter.Hour hour, IPlatformDateTimeFormatter.Minute minute, IPlatformDateTimeFormatter.Second second, IPlatformDateTimeFormatter.TimeZoneName timeZoneName, IPlatformDateTimeFormatter.HourCycle hourCycle, IPlatformDateTimeFormatter.DateStyle dateStyle, IPlatformDateTimeFormatter.TimeStyle timeStyle, Object obj) throws JSRangeErrorException {
        IPlatformDateTimeFormatter.HourCycle hourCycle2 = hourCycle;
        IPlatformDateTimeFormatter.DateStyle dateStyle2 = dateStyle;
        IPlatformDateTimeFormatter.TimeStyle timeStyle2 = timeStyle;
        StringBuilder sb = new StringBuilder();
        if (dateStyle2 == IPlatformDateTimeFormatter.DateStyle.UNDEFINED && timeStyle2 == IPlatformDateTimeFormatter.TimeStyle.UNDEFINED) {
            sb.append(weekDay.getSkeleonSymbol());
            sb.append(era.getSkeleonSymbol());
            sb.append(year.getSkeleonSymbol());
            sb.append(month.getSkeleonSymbol());
            sb.append(day.getSkeleonSymbol());
            if (hourCycle2 == IPlatformDateTimeFormatter.HourCycle.H11 || hourCycle2 == IPlatformDateTimeFormatter.HourCycle.H12) {
                sb.append(hour.getSkeleonSymbol12());
            } else {
                sb.append(hour.getSkeleonSymbol24());
            }
            sb.append(minute.getSkeleonSymbol());
            sb.append(second.getSkeleonSymbol());
            sb.append(timeZoneName.getSkeleonSymbol());
        } else {
            ILocaleObject<?> iLocaleObject2 = iLocaleObject;
            sb.append(getPatternForStyle(iLocaleObject, dateStyle2, timeStyle2));
            HashMap<String, String> unicodeExtensions = iLocaleObject.getUnicodeExtensions();
            if (unicodeExtensions.containsKey("hc")) {
                String str = unicodeExtensions.get("hc");
                if (str == "h11" || str == "h12") {
                    replacePatternChars(sb, new char[]{'H', 'K', 'k'}, 'h');
                } else if (str == "h23" || str == "h24") {
                    replacePatternChars(sb, new char[]{'h', 'H', 'K'}, 'k');
                }
            }
            if (hourCycle2 == IPlatformDateTimeFormatter.HourCycle.H11 || hourCycle2 == IPlatformDateTimeFormatter.HourCycle.H12) {
                replacePatternChars(sb, new char[]{'H', 'K', 'k'}, 'h');
            } else if (hourCycle2 == IPlatformDateTimeFormatter.HourCycle.H23 || hourCycle2 == IPlatformDateTimeFormatter.HourCycle.H24) {
                replacePatternChars(sb, new char[]{'h', 'H', 'K'}, 'k');
            }
            if (!JSObjects.isUndefined(obj) && !JSObjects.isNull(obj)) {
                if (JSObjects.getJavaBoolean(obj)) {
                    replacePatternChars(sb, new char[]{'H', 'K', 'k'}, 'h');
                } else {
                    replacePatternChars(sb, new char[]{'h', 'H', 'K'}, 'k');
                }
            }
        }
        return sb.toString();
    }

    public void configure(ILocaleObject<?> iLocaleObject, String str, String str2, IPlatformDateTimeFormatter.FormatMatcher formatMatcher, IPlatformDateTimeFormatter.WeekDay weekDay, IPlatformDateTimeFormatter.Era era, IPlatformDateTimeFormatter.Year year, IPlatformDateTimeFormatter.Month month, IPlatformDateTimeFormatter.Day day, IPlatformDateTimeFormatter.Hour hour, IPlatformDateTimeFormatter.Minute minute, IPlatformDateTimeFormatter.Second second, IPlatformDateTimeFormatter.TimeZoneName timeZoneName, IPlatformDateTimeFormatter.HourCycle hourCycle, Object obj, IPlatformDateTimeFormatter.DateStyle dateStyle, IPlatformDateTimeFormatter.TimeStyle timeStyle, Object obj2) throws JSRangeErrorException {
        Calendar calendar;
        String str3 = str2;
        String skeleton = getSkeleton(iLocaleObject, weekDay, era, year, month, day, hour, minute, second, timeZoneName, hourCycle, dateStyle, timeStyle, obj2);
        if (!str.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(JSObjects.getJavaString(str));
            ILocaleObject<?> cloneObject = iLocaleObject.cloneObject();
            cloneObject.setUnicodeExtensions("ca", arrayList);
            calendar = Calendar.getInstance((ULocale) cloneObject.getLocale());
        } else {
            calendar = null;
        }
        if (!str2.isEmpty()) {
            try {
                if (NumberingSystem.getInstanceByName(JSObjects.getJavaString(str2)) != null) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(JSObjects.getJavaString(str2));
                    iLocaleObject.setUnicodeExtensions("nu", arrayList2);
                } else {
                    throw new JSRangeErrorException("Invalid numbering system: " + str3);
                }
            } catch (RuntimeException unused) {
                throw new JSRangeErrorException("Invalid numbering system: " + str3);
            }
        } else {
            ILocaleObject<?> iLocaleObject2 = iLocaleObject;
        }
        if (calendar != null) {
            this.mDateFormat = DateFormat.getPatternInstance(calendar, skeleton, (ULocale) iLocaleObject.getLocale());
        } else {
            this.mDateFormat = DateFormat.getPatternInstance(skeleton, (ULocale) iLocaleObject.getLocale());
        }
        if (!JSObjects.isUndefined(obj) && !JSObjects.isNull(obj)) {
            this.mDateFormat.setTimeZone(TimeZone.getTimeZone(JSObjects.getJavaString(obj)));
        }
    }

    public String[] getAvailableLocales() {
        ArrayList arrayList = new ArrayList();
        for (ULocale languageTag : ULocale.getAvailableLocales()) {
            arrayList.add(languageTag.toLanguageTag());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    PlatformDateTimeFormatterICU() {
    }
}
