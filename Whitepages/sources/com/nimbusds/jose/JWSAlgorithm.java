package com.nimbusds.jose;

public final class JWSAlgorithm extends Algorithm {
    public static final JWSAlgorithm ES256;
    public static final JWSAlgorithm ES384;
    public static final JWSAlgorithm ES512;
    public static final JWSAlgorithm EdDSA;
    public static final JWSAlgorithm HS256 = new JWSAlgorithm("HS256", Requirement.REQUIRED);
    public static final JWSAlgorithm HS384;
    public static final JWSAlgorithm HS512;
    public static final JWSAlgorithm PS256;
    public static final JWSAlgorithm PS384;
    public static final JWSAlgorithm PS512;
    public static final JWSAlgorithm RS256;
    public static final JWSAlgorithm RS384;
    public static final JWSAlgorithm RS512;
    private static final long serialVersionUID = 1;

    static {
        Requirement requirement = Requirement.OPTIONAL;
        HS384 = new JWSAlgorithm("HS384", requirement);
        HS512 = new JWSAlgorithm("HS512", requirement);
        Requirement requirement2 = Requirement.RECOMMENDED;
        RS256 = new JWSAlgorithm("RS256", requirement2);
        RS384 = new JWSAlgorithm("RS384", requirement);
        RS512 = new JWSAlgorithm("RS512", requirement);
        ES256 = new JWSAlgorithm("ES256", requirement2);
        ES384 = new JWSAlgorithm("ES384", requirement);
        ES512 = new JWSAlgorithm("ES512", requirement);
        PS256 = new JWSAlgorithm("PS256", requirement);
        PS384 = new JWSAlgorithm("PS384", requirement);
        PS512 = new JWSAlgorithm("PS512", requirement);
        EdDSA = new JWSAlgorithm("EdDSA", requirement);
    }

    public JWSAlgorithm(String str, Requirement requirement) {
        super(str, requirement);
    }

    public JWSAlgorithm(String str) {
        super(str, (Requirement) null);
    }

    public static JWSAlgorithm parse(String str) {
        JWSAlgorithm jWSAlgorithm = HS256;
        if (str.equals(jWSAlgorithm.getName())) {
            return jWSAlgorithm;
        }
        JWSAlgorithm jWSAlgorithm2 = HS384;
        if (str.equals(jWSAlgorithm2.getName())) {
            return jWSAlgorithm2;
        }
        JWSAlgorithm jWSAlgorithm3 = HS512;
        if (str.equals(jWSAlgorithm3.getName())) {
            return jWSAlgorithm3;
        }
        JWSAlgorithm jWSAlgorithm4 = RS256;
        if (str.equals(jWSAlgorithm4.getName())) {
            return jWSAlgorithm4;
        }
        JWSAlgorithm jWSAlgorithm5 = RS384;
        if (str.equals(jWSAlgorithm5.getName())) {
            return jWSAlgorithm5;
        }
        JWSAlgorithm jWSAlgorithm6 = RS512;
        if (str.equals(jWSAlgorithm6.getName())) {
            return jWSAlgorithm6;
        }
        JWSAlgorithm jWSAlgorithm7 = ES256;
        if (str.equals(jWSAlgorithm7.getName())) {
            return jWSAlgorithm7;
        }
        JWSAlgorithm jWSAlgorithm8 = ES384;
        if (str.equals(jWSAlgorithm8.getName())) {
            return jWSAlgorithm8;
        }
        JWSAlgorithm jWSAlgorithm9 = ES512;
        if (str.equals(jWSAlgorithm9.getName())) {
            return jWSAlgorithm9;
        }
        JWSAlgorithm jWSAlgorithm10 = PS256;
        if (str.equals(jWSAlgorithm10.getName())) {
            return jWSAlgorithm10;
        }
        JWSAlgorithm jWSAlgorithm11 = PS384;
        if (str.equals(jWSAlgorithm11.getName())) {
            return jWSAlgorithm11;
        }
        JWSAlgorithm jWSAlgorithm12 = PS512;
        if (str.equals(jWSAlgorithm12.getName())) {
            return jWSAlgorithm12;
        }
        JWSAlgorithm jWSAlgorithm13 = EdDSA;
        if (str.equals(jWSAlgorithm13.getName())) {
            return jWSAlgorithm13;
        }
        return new JWSAlgorithm(str);
    }
}
