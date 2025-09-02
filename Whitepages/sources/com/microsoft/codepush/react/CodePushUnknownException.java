package com.microsoft.codepush.react;

class CodePushUnknownException extends RuntimeException {
    public CodePushUnknownException(String str, Throwable th) {
        super(str, th);
    }

    public CodePushUnknownException(String str) {
        super(str);
    }
}
