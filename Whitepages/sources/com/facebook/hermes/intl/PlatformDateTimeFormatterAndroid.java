package com.facebook.hermes.intl;

import com.facebook.hermes.intl.IPlatformDateTimeFormatter;
import java.text.AttributedCharacterIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PlatformDateTimeFormatterAndroid implements IPlatformDateTimeFormatter {
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
        return DateFormat.getDateInstance(3, (Locale) iLocaleObject.getLocale()).getCalendar().toString();
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
            String patternWithoutLiterals = PatternUtils.getPatternWithoutLiterals(((SimpleDateFormat) DateFormat.getTimeInstance(0, (Locale) iLocaleObject.getLocale())).toPattern());
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
        return Calendar.getInstance((Locale) iLocaleObject.getLocale()).getTimeZone().getID();
    }

    public String getDefaultNumberingSystem(ILocaleObject<?> iLocaleObject) {
        return "latn";
    }

    public void configure(ILocaleObject<?> iLocaleObject, String str, String str2, IPlatformDateTimeFormatter.FormatMatcher formatMatcher, IPlatformDateTimeFormatter.WeekDay weekDay, IPlatformDateTimeFormatter.Era era, IPlatformDateTimeFormatter.Year year, IPlatformDateTimeFormatter.Month month, IPlatformDateTimeFormatter.Day day, IPlatformDateTimeFormatter.Hour hour, IPlatformDateTimeFormatter.Minute minute, IPlatformDateTimeFormatter.Second second, IPlatformDateTimeFormatter.TimeZoneName timeZoneName, IPlatformDateTimeFormatter.HourCycle hourCycle, Object obj, IPlatformDateTimeFormatter.DateStyle dateStyle, IPlatformDateTimeFormatter.TimeStyle timeStyle, Object obj2) throws JSRangeErrorException {
        ILocaleObject<?> iLocaleObject2 = iLocaleObject;
        if (!str.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(JSObjects.getJavaString(str));
            iLocaleObject.setUnicodeExtensions("ca", arrayList);
        }
        if (!str2.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(JSObjects.getJavaString(str2));
            iLocaleObject.setUnicodeExtensions("nu", arrayList2);
        }
        boolean z = true;
        boolean z2 = (year == null && month == null && day == null) ? false : true;
        if (hour == null && minute == null && second == null) {
            z = false;
        }
        if (z2 && z) {
            this.mDateFormat = DateFormat.getDateTimeInstance(0, 0, (Locale) iLocaleObject.getLocale());
        } else if (z2) {
            this.mDateFormat = DateFormat.getDateInstance(0, (Locale) iLocaleObject.getLocale());
        } else if (z) {
            this.mDateFormat = DateFormat.getTimeInstance(0, (Locale) iLocaleObject.getLocale());
        }
        if (!JSObjects.isUndefined(obj) && !JSObjects.isNull(obj)) {
            this.mDateFormat.setTimeZone(TimeZone.getTimeZone(JSObjects.getJavaString(obj)));
        }
    }

    public String[] getAvailableLocales() {
        ArrayList arrayList = new ArrayList();
        for (Locale languageTag : DateFormat.getAvailableLocales()) {
            arrayList.add(languageTag.toLanguageTag());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    PlatformDateTimeFormatterAndroid() {
    }
}
