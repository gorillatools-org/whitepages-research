package com.google.firebase.perf.util;

public class Constants {
    public static final String ACTIVITY_ATTRIBUTE_KEY = "Hosting_activity";
    public static final int BURST_CAPACITY = 500;
    public static final String ENABLE_DISABLE = "isEnabled";
    public static final int FROZEN_FRAME_TIME = 700;
    public static final int MAX_ATTRIBUTE_KEY_LENGTH = 40;
    public static final int MAX_ATTRIBUTE_VALUE_LENGTH = 100;
    public static final int MAX_CONTENT_TYPE_LENGTH = 128;
    public static final int MAX_COUNTER_ID_LENGTH = 100;
    public static final int MAX_HOST_LENGTH = 255;
    public static final double MAX_SAMPLING_RATE = 1.0d;
    public static final int MAX_SUBTRACE_DEEP = 1;
    public static final int MAX_TRACE_CUSTOM_ATTRIBUTES = 5;
    public static final int MAX_TRACE_ID_LENGTH = 100;
    public static final int MAX_URL_LENGTH = 2000;
    public static final double MIN_SAMPLING_RATE = 0.0d;
    public static final String PARENT_FRAGMENT_ATTRIBUTE_KEY = "Parent_fragment";
    public static final String PARENT_FRAGMENT_ATTRIBUTE_VALUE_NONE = "No parent";
    public static final String PREFS_NAME = "FirebasePerfSharedPrefs";
    public static final int RATE_PER_MINUTE = 100;
    public static final String SCREEN_TRACE_PREFIX = "_st_";
    public static final int SLOW_FRAME_TIME = 16;

    public enum TraceNames {
        APP_START_TRACE_NAME("_as"),
        ON_CREATE_TRACE_NAME("_astui"),
        ON_START_TRACE_NAME("_astfd"),
        ON_RESUME_TRACE_NAME("_asti"),
        FOREGROUND_TRACE_NAME("_fs"),
        BACKGROUND_TRACE_NAME("_bs");
        
        private String mName;

        private TraceNames(String str) {
            this.mName = str;
        }

        public String toString() {
            return this.mName;
        }
    }

    public enum CounterNames {
        TRACE_EVENT_RATE_LIMITED("_fstec"),
        NETWORK_TRACE_EVENT_RATE_LIMITED("_fsntc"),
        TRACE_STARTED_NOT_STOPPED("_tsns"),
        FRAMES_TOTAL("_fr_tot"),
        FRAMES_SLOW("_fr_slo"),
        FRAMES_FROZEN("_fr_fzn");
        
        private String mName;

        private CounterNames(String str) {
            this.mName = str;
        }

        public String toString() {
            return this.mName;
        }
    }
}
