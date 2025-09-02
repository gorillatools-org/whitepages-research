package com.google.android.gms.measurement.internal;

import android.os.Process;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

final class zzik extends Thread {
    final /* synthetic */ zzil zza;
    private final Object zzb;
    private final BlockingQueue zzc;
    private boolean zzd = false;

    public zzik(zzil zzil, String str, BlockingQueue blockingQueue) {
        this.zza = zzil;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zzb = new Object();
        this.zzc = blockingQueue;
        setName(str);
    }

    private final void zzb() {
        zzil zzil = this.zza;
        synchronized (zzil.zzh) {
            try {
                if (!this.zzd) {
                    zzil.zzi.release();
                    zzil.zzh.notifyAll();
                    if (this == zzil.zzb) {
                        zzil.zzb = null;
                    } else if (this == zzil.zzc) {
                        zzil.zzc = null;
                    } else {
                        zzil.zzu.zzaW().zze().zza("Current scheduler thread is neither worker nor network");
                    }
                    this.zzd = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void zzc(InterruptedException interruptedException) {
        this.zza.zzu.zzaW().zzk().zzb(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }

    public final void run() {
        boolean z = false;
        while (!z) {
            try {
                this.zza.zzi.acquire();
                z = true;
            } catch (InterruptedException e) {
                zzc(e);
            }
        }
        try {
            int threadPriority = Process.getThreadPriority(Process.myTid());
            while (true) {
                BlockingQueue blockingQueue = this.zzc;
                zzij zzij = (zzij) blockingQueue.poll();
                if (zzij != null) {
                    Process.setThreadPriority(true != zzij.zza ? 10 : threadPriority);
                    zzij.run();
                } else {
                    Object obj = this.zzb;
                    synchronized (obj) {
                        if (blockingQueue.peek() == null) {
                            boolean unused = this.zza.zzj;
                            try {
                                obj.wait(30000);
                            } catch (InterruptedException e2) {
                                zzc(e2);
                            }
                        }
                    }
                    synchronized (this.zza.zzh) {
                        if (this.zzc.peek() == null) {
                            zzb();
                            zzb();
                            return;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            zzb();
            throw th;
        }
    }

    public final void zza() {
        Object obj = this.zzb;
        synchronized (obj) {
            obj.notifyAll();
        }
    }
}
