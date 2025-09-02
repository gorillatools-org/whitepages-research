package com.nimbusds.jose.util;

import java.util.Date;

public abstract class DateUtils {
    public static long toSecondsSinceEpoch(Date date) {
        return date.getTime() / 1000;
    }
}
