package com.google.android.gms.auth.api;

import com.google.android.gms.common.internal.ShowFirstParty;

@Deprecated
public final class zbc {
    protected Boolean zba = Boolean.FALSE;
    protected String zbb;

    public zbc() {
    }

    @ShowFirstParty
    public final zbc zba(String str) {
        this.zbb = str;
        return this;
    }

    @ShowFirstParty
    public zbc(zbd zbd) {
        String unused = zbd.zbb;
        this.zba = Boolean.valueOf(zbd.zbc);
        this.zbb = zbd.zbd;
    }
}
