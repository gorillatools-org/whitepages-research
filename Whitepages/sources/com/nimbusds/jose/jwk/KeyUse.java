package com.nimbusds.jose.jwk;

import java.text.ParseException;

public enum KeyUse {
    SIGNATURE("sig"),
    ENCRYPTION("enc");
    
    private final String identifier;

    private KeyUse(String str) {
        if (str != null) {
            this.identifier = str;
            return;
        }
        throw new IllegalArgumentException("The key use identifier must not be null");
    }

    public String identifier() {
        return this.identifier;
    }

    public String toString() {
        return identifier();
    }

    public static KeyUse parse(String str) {
        if (str == null) {
            return null;
        }
        for (KeyUse keyUse : values()) {
            if (str.equals(keyUse.identifier)) {
                return keyUse;
            }
        }
        throw new ParseException("Invalid JWK use: " + str, 0);
    }
}
