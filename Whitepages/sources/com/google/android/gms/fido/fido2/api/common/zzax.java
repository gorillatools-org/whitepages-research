package com.google.android.gms.fido.fido2.api.common;

public final class zzax extends Exception {
    public zzax(String str) {
        super(String.format("User verification requirement %s not supported", new Object[]{str}));
    }
}
