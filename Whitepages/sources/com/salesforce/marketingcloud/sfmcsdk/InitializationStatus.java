package com.salesforce.marketingcloud.sfmcsdk;

public interface InitializationStatus {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int FAILURE = -1;
    public static final int SUCCESS = 1;

    int getStatus();

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int FAILURE = -1;
        public static final int SUCCESS = 1;

        private Companion() {
        }
    }
}
