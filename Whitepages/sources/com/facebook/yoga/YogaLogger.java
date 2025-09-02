package com.facebook.yoga;

import com.facebook.yoga.annotations.DoNotStrip;

@DoNotStrip
public interface YogaLogger {
    @DoNotStrip
    void log(YogaLogLevel yogaLogLevel, String str);
}
