package com.google.android.gms.fido.fido2.api.common;

public enum RSAAlgorithm implements Algorithm {
    RS256(-257),
    RS384(-258),
    RS512(-259),
    LEGACY_RS1(-262),
    PS256(-37),
    PS384(-38),
    PS512(-39),
    RS1(-65535);
    
    private final int zzb;

    private RSAAlgorithm(int i) {
        this.zzb = i;
    }

    public int getAlgoValue() {
        return this.zzb;
    }
}
