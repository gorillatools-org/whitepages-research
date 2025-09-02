package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.salesforce.marketingcloud.messages.iam.j;

final class zzq implements Handler.Callback {
    final /* synthetic */ zzs zza;

    /* synthetic */ zzq(zzs zzs, zzr zzr) {
        this.zza = zzs;
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            synchronized (this.zza.zzb) {
                try {
                    zzo zzo = (zzo) message.obj;
                    zzp zzp = (zzp) this.zza.zzb.get(zzo);
                    if (zzp != null && zzp.zzi()) {
                        if (zzp.zzj()) {
                            zzp.zzg("GmsClientSupervisor");
                        }
                        this.zza.zzb.remove(zzo);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return true;
        } else if (i != 1) {
            return false;
        } else {
            synchronized (this.zza.zzb) {
                try {
                    zzo zzo2 = (zzo) message.obj;
                    zzp zzp2 = (zzp) this.zza.zzb.get(zzo2);
                    if (zzp2 != null && zzp2.zza() == 3) {
                        String valueOf = String.valueOf(zzo2);
                        Log.e("GmsClientSupervisor", "Timeout waiting for ServiceConnection callback " + valueOf, new Exception());
                        ComponentName zzb = zzp2.zzb();
                        if (zzb == null) {
                            zzb = zzo2.zza();
                        }
                        if (zzb == null) {
                            String zzc = zzo2.zzc();
                            Preconditions.checkNotNull(zzc);
                            zzb = new ComponentName(zzc, j.h);
                        }
                        zzp2.onServiceDisconnected(zzb);
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            return true;
        }
    }
}
