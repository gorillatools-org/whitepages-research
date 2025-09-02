package com.facebook.react.jstasks;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class NoRetryPolicy implements HeadlessJsTaskRetryPolicy {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final NoRetryPolicy INSTANCE = new NoRetryPolicy();

    public boolean canRetry() {
        return false;
    }

    public HeadlessJsTaskRetryPolicy copy() {
        return this;
    }

    private NoRetryPolicy() {
    }

    public int getDelay() {
        boolean canRetry = canRetry();
        throw new IllegalStateException("Should not retrieve delay as canRetry is: " + canRetry);
    }

    public HeadlessJsTaskRetryPolicy update() {
        boolean canRetry = canRetry();
        throw new IllegalStateException("Should not update as canRetry is: " + canRetry);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
