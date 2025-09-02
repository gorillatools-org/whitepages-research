package com.nimbusds.jose.jwk;

import java.io.Serializable;
import java.security.spec.ECParameterSpec;

public final class Curve implements Serializable {
    public static final Curve Ed25519 = new Curve("Ed25519", "Ed25519", (String) null);
    public static final Curve Ed448 = new Curve("Ed448", "Ed448", (String) null);
    public static final Curve P_256 = new Curve("P-256", "secp256r1", "1.2.840.10045.3.1.7");
    public static final Curve P_384 = new Curve("P-384", "secp384r1", "1.3.132.0.34");
    public static final Curve P_521 = new Curve("P-521", "secp521r1", "1.3.132.0.35");
    public static final Curve X25519 = new Curve("X25519", "X25519", (String) null);
    public static final Curve X448 = new Curve("X448", "X448", (String) null);
    private static final long serialVersionUID = 1;
    private final String name;
    private final String oid;
    private final String stdName;

    public Curve(String str) {
        this(str, (String) null, (String) null);
    }

    public Curve(String str, String str2, String str3) {
        if (str != null) {
            this.name = str;
            this.stdName = str2;
            this.oid = str3;
            return;
        }
        throw new IllegalArgumentException("The JOSE cryptographic curve name must not be null");
    }

    public String getName() {
        return this.name;
    }

    public ECParameterSpec toECParameterSpec() {
        return ECParameterTable.get(this);
    }

    public String toString() {
        return getName();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Curve) && toString().equals(obj.toString());
    }

    public static Curve parse(String str) {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException("The cryptographic curve string must not be null or empty");
        }
        Curve curve = P_256;
        if (str.equals(curve.getName())) {
            return curve;
        }
        Curve curve2 = P_384;
        if (str.equals(curve2.getName())) {
            return curve2;
        }
        Curve curve3 = P_521;
        if (str.equals(curve3.getName())) {
            return curve3;
        }
        Curve curve4 = Ed25519;
        if (str.equals(curve4.getName())) {
            return curve4;
        }
        Curve curve5 = Ed448;
        if (str.equals(curve5.getName())) {
            return curve5;
        }
        Curve curve6 = X25519;
        if (str.equals(curve6.getName())) {
            return curve6;
        }
        Curve curve7 = X448;
        if (str.equals(curve7.getName())) {
            return curve7;
        }
        return new Curve(str);
    }
}
