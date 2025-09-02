package com.google.android.gms.iid;

public abstract class zzai {
    private static zzai zzdd;

    public abstract zzaj<Boolean> zzd(String str, boolean z);

    public static synchronized zzai zzy() {
        zzai zzai;
        synchronized (zzai.class) {
            try {
                if (zzdd == null) {
                    zzdd = new zzac();
                }
                zzai = zzdd;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return zzai;
    }
}
