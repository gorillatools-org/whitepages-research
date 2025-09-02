package com.nimbusds.jose;

public final class JWEAlgorithm extends Algorithm {
    public static final JWEAlgorithm A128GCMKW;
    public static final JWEAlgorithm A128KW;
    public static final JWEAlgorithm A192GCMKW;
    public static final JWEAlgorithm A192KW;
    public static final JWEAlgorithm A256GCMKW;
    public static final JWEAlgorithm A256KW;
    public static final JWEAlgorithm DIR;
    public static final JWEAlgorithm ECDH_ES;
    public static final JWEAlgorithm ECDH_ES_A128KW;
    public static final JWEAlgorithm ECDH_ES_A192KW;
    public static final JWEAlgorithm ECDH_ES_A256KW;
    public static final JWEAlgorithm PBES2_HS256_A128KW;
    public static final JWEAlgorithm PBES2_HS384_A192KW;
    public static final JWEAlgorithm PBES2_HS512_A256KW;
    public static final JWEAlgorithm RSA1_5 = new JWEAlgorithm("RSA1_5", Requirement.REQUIRED);
    public static final JWEAlgorithm RSA_OAEP;
    public static final JWEAlgorithm RSA_OAEP_256;
    private static final long serialVersionUID = 1;

    static {
        Requirement requirement = Requirement.OPTIONAL;
        RSA_OAEP = new JWEAlgorithm("RSA-OAEP", requirement);
        RSA_OAEP_256 = new JWEAlgorithm("RSA-OAEP-256", requirement);
        Requirement requirement2 = Requirement.RECOMMENDED;
        A128KW = new JWEAlgorithm("A128KW", requirement2);
        A192KW = new JWEAlgorithm("A192KW", requirement);
        A256KW = new JWEAlgorithm("A256KW", requirement2);
        DIR = new JWEAlgorithm("dir", requirement2);
        ECDH_ES = new JWEAlgorithm("ECDH-ES", requirement2);
        ECDH_ES_A128KW = new JWEAlgorithm("ECDH-ES+A128KW", requirement2);
        ECDH_ES_A192KW = new JWEAlgorithm("ECDH-ES+A192KW", requirement);
        ECDH_ES_A256KW = new JWEAlgorithm("ECDH-ES+A256KW", requirement2);
        A128GCMKW = new JWEAlgorithm("A128GCMKW", requirement);
        A192GCMKW = new JWEAlgorithm("A192GCMKW", requirement);
        A256GCMKW = new JWEAlgorithm("A256GCMKW", requirement);
        PBES2_HS256_A128KW = new JWEAlgorithm("PBES2-HS256+A128KW", requirement);
        PBES2_HS384_A192KW = new JWEAlgorithm("PBES2-HS384+A192KW", requirement);
        PBES2_HS512_A256KW = new JWEAlgorithm("PBES2-HS512+A256KW", requirement);
    }

    public JWEAlgorithm(String str, Requirement requirement) {
        super(str, requirement);
    }

    public JWEAlgorithm(String str) {
        super(str, (Requirement) null);
    }

    public static JWEAlgorithm parse(String str) {
        JWEAlgorithm jWEAlgorithm = RSA1_5;
        if (str.equals(jWEAlgorithm.getName())) {
            return jWEAlgorithm;
        }
        JWEAlgorithm jWEAlgorithm2 = RSA_OAEP;
        if (str.equals(jWEAlgorithm2.getName())) {
            return jWEAlgorithm2;
        }
        JWEAlgorithm jWEAlgorithm3 = RSA_OAEP_256;
        if (str.equals(jWEAlgorithm3.getName())) {
            return jWEAlgorithm3;
        }
        JWEAlgorithm jWEAlgorithm4 = A128KW;
        if (str.equals(jWEAlgorithm4.getName())) {
            return jWEAlgorithm4;
        }
        JWEAlgorithm jWEAlgorithm5 = A192KW;
        if (str.equals(jWEAlgorithm5.getName())) {
            return jWEAlgorithm5;
        }
        JWEAlgorithm jWEAlgorithm6 = A256KW;
        if (str.equals(jWEAlgorithm6.getName())) {
            return jWEAlgorithm6;
        }
        JWEAlgorithm jWEAlgorithm7 = DIR;
        if (str.equals(jWEAlgorithm7.getName())) {
            return jWEAlgorithm7;
        }
        JWEAlgorithm jWEAlgorithm8 = ECDH_ES;
        if (str.equals(jWEAlgorithm8.getName())) {
            return jWEAlgorithm8;
        }
        JWEAlgorithm jWEAlgorithm9 = ECDH_ES_A128KW;
        if (str.equals(jWEAlgorithm9.getName())) {
            return jWEAlgorithm9;
        }
        JWEAlgorithm jWEAlgorithm10 = ECDH_ES_A192KW;
        if (str.equals(jWEAlgorithm10.getName())) {
            return jWEAlgorithm10;
        }
        JWEAlgorithm jWEAlgorithm11 = ECDH_ES_A256KW;
        if (str.equals(jWEAlgorithm11.getName())) {
            return jWEAlgorithm11;
        }
        JWEAlgorithm jWEAlgorithm12 = A128GCMKW;
        if (str.equals(jWEAlgorithm12.getName())) {
            return jWEAlgorithm12;
        }
        JWEAlgorithm jWEAlgorithm13 = A192GCMKW;
        if (str.equals(jWEAlgorithm13.getName())) {
            return jWEAlgorithm13;
        }
        JWEAlgorithm jWEAlgorithm14 = A256GCMKW;
        if (str.equals(jWEAlgorithm14.getName())) {
            return jWEAlgorithm14;
        }
        JWEAlgorithm jWEAlgorithm15 = PBES2_HS256_A128KW;
        if (str.equals(jWEAlgorithm15.getName())) {
            return jWEAlgorithm15;
        }
        JWEAlgorithm jWEAlgorithm16 = PBES2_HS384_A192KW;
        if (str.equals(jWEAlgorithm16.getName())) {
            return jWEAlgorithm16;
        }
        JWEAlgorithm jWEAlgorithm17 = PBES2_HS512_A256KW;
        if (str.equals(jWEAlgorithm17.getName())) {
            return jWEAlgorithm17;
        }
        return new JWEAlgorithm(str);
    }
}
