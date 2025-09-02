package com.facebook.react.bridge;

public class DefaultJSExceptionHandler implements JSExceptionHandler {
    public void handleException(Exception exc) {
        if (exc instanceof RuntimeException) {
            throw ((RuntimeException) exc);
        }
        throw new RuntimeException(exc);
    }
}
