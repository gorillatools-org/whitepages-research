package com.facebook.login;

public enum CodeChallengeMethod {
    S256("S256"),
    PLAIN("plain");

    private CodeChallengeMethod(String str) {
    }
}
