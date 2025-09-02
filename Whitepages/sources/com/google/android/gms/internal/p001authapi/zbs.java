package com.google.android.gms.internal.p001authapi;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenResult;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.auth-api.zbs  reason: invalid package */
public interface zbs extends IInterface {
    void zbb(Status status, SaveAccountLinkingTokenResult saveAccountLinkingTokenResult) throws RemoteException;
}
