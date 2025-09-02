package com.google.android.material.datepicker;

import android.icu.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

abstract class UtcDates {
    static AtomicReference timeSourceRef = new AtomicReference();

    static TimeSource getTimeSource() {
        TimeSource timeSource = (TimeSource) timeSourceRef.get();
        return timeSource == null ? TimeSource.system() : timeSource;
    }

    private static TimeZone getTimeZone() {
        return TimeZone.getTimeZone("UTC");
    }

    private static android.icu.util.TimeZone getUtcAndroidTimeZone() {
        return android.icu.util.TimeZone.getTimeZone("UTC");
    }

    static Calendar getTodayCalendar() {
        Calendar now = getTimeSource().now();
        now.set(11, 0);
        now.set(12, 0);
        now.set(13, 0);
        now.set(14, 0);
        now.setTimeZone(getTimeZone());
        return now;
    }

    static Calendar getUtcCalendar() {
        return getUtcCalendarOf((Calendar) null);
    }

    static Calendar getUtcCalendarOf(Calendar calendar) {
        Calendar instance = Calendar.getInstance(getTimeZone());
        if (calendar == null) {
            instance.clear();
        } else {
            instance.setTimeInMillis(calendar.getTimeInMillis());
        }
        return instance;
    }

    static Calendar getDayCopy(Calendar calendar) {
        Calendar utcCalendarOf = getUtcCalendarOf(calendar);
        Calendar utcCalendar = getUtcCalendar();
        utcCalendar.set(utcCalendarOf.get(1), utcCalendarOf.get(2), utcCalendarOf.get(5));
        return utcCalendar;
    }

    static long canonicalYearMonthDay(long j) {
        Calendar utcCalendar = getUtcCalendar();
        utcCalendar.setTimeInMillis(j);
        return getDayCopy(utcCalendar).getTimeInMillis();
    }

    private static DateFormat getAndroidFormat(String str, Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, locale);
        instanceForSkeleton.setTimeZone(getUtcAndroidTimeZone());
        return instanceForSkeleton;
    }

    static DateFormat getAbbrMonthWeekdayDayFormat(Locale locale) {
        return getAndroidFormat("MMMEd", locale);
    }

    static DateFormat getYearAbbrMonthWeekdayDayFormat(Locale locale) {
        return getAndroidFormat("yMMMEd", locale);
    }
}
