package com.facebook.react.uimanager.events;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface EventCategoryDef {
    public static final int CONTINUOUS = 4;
    public static final int CONTINUOUS_END = 1;
    public static final int CONTINUOUS_START = 0;
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int DISCRETE = 3;
    public static final int UNSPECIFIED = 2;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int CONTINUOUS = 4;
        public static final int CONTINUOUS_END = 1;
        public static final int CONTINUOUS_START = 0;
        public static final int DISCRETE = 3;
        public static final int UNSPECIFIED = 2;

        private Companion() {
        }
    }
}
