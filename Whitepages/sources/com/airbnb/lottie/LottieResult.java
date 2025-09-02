package com.airbnb.lottie;

import java.util.Arrays;

public final class LottieResult {
    private final Throwable exception;
    private final Object value;

    public LottieResult(Object obj) {
        this.value = obj;
        this.exception = null;
    }

    public LottieResult(Throwable th) {
        this.exception = th;
        this.value = null;
    }

    public Object getValue() {
        return this.value;
    }

    public Throwable getException() {
        return this.exception;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LottieResult)) {
            return false;
        }
        LottieResult lottieResult = (LottieResult) obj;
        if (getValue() != null && getValue().equals(lottieResult.getValue())) {
            return true;
        }
        if (getException() == null || lottieResult.getException() == null) {
            return false;
        }
        return getException().toString().equals(getException().toString());
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{getValue(), getException()});
    }
}
