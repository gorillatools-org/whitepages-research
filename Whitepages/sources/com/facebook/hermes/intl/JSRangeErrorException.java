package com.facebook.hermes.intl;

public class JSRangeErrorException extends Exception {
    public JSRangeErrorException() {
    }

    public JSRangeErrorException(String str) {
        super(str);
    }

    public JSRangeErrorException(String str, Throwable th) {
        super(str, th);
    }

    public JSRangeErrorException(Throwable th) {
        super(th);
    }
}
