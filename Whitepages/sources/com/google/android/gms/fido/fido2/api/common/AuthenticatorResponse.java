package com.google.android.gms.fido.fido2.api.common;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class AuthenticatorResponse extends AbstractSafeParcelable {
    public abstract byte[] getClientDataJSON();

    public abstract byte[] serializeToBytes();
}
