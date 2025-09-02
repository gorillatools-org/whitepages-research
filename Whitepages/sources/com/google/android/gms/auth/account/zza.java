package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzb;
import com.google.android.gms.internal.auth.zzc;

public abstract class zza extends zzb implements zzb {
    public zza() {
        super("com.google.android.gms.auth.account.IWorkAccountCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            boolean z = false;
            if (i != 2) {
                return false;
            }
            int i3 = zzc.zza;
            int readInt = parcel.readInt();
            zzc.zzb(parcel);
            if (readInt != 0) {
                z = true;
            }
            zzc(z);
        } else {
            zzc.zzb(parcel);
            zzb((Account) zzc.zza(parcel, Account.CREATOR));
        }
        return true;
    }
}
