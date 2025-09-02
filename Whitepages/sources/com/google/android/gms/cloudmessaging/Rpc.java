package com.google.android.gms.cloudmessaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.internal.cloudmessaging.zza;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.Constants;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rpc {
    private static int zza;
    private static PendingIntent zzb;
    private static final Executor zzc = zzy.zza;
    private static final Pattern zzd = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)");
    private final SimpleArrayMap zze = new SimpleArrayMap();
    private final Context zzf;
    private final zzw zzg;
    private final ScheduledExecutorService zzh;
    private final Messenger zzi;
    private Messenger zzj;
    private zzd zzk;

    public Rpc(Context context) {
        this.zzf = context;
        this.zzg = new zzw(context);
        this.zzi = new Messenger(new zzae(this, Looper.getMainLooper()));
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.setKeepAliveTime(60, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.allowCoreThreadTimeOut(true);
        this.zzh = scheduledThreadPoolExecutor;
    }

    static /* synthetic */ Task zza(Bundle bundle) throws Exception {
        if (zzi(bundle)) {
            return Tasks.forResult(null);
        }
        return Tasks.forResult(bundle);
    }

    static /* bridge */ /* synthetic */ void zzc(Rpc rpc, Message message) {
        if (message != null) {
            Object obj = message.obj;
            if (obj instanceof Intent) {
                Intent intent = (Intent) obj;
                intent.setExtrasClassLoader(new zzc());
                if (intent.hasExtra("google.messenger")) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                    if (parcelableExtra instanceof zzd) {
                        rpc.zzk = (zzd) parcelableExtra;
                    }
                    if (parcelableExtra instanceof Messenger) {
                        rpc.zzj = (Messenger) parcelableExtra;
                    }
                }
                Intent intent2 = (Intent) message.obj;
                String action = intent2.getAction();
                if (Objects.equals(action, "com.google.android.c2dm.intent.REGISTRATION")) {
                    String stringExtra = intent2.getStringExtra("registration_id");
                    if (stringExtra == null) {
                        stringExtra = intent2.getStringExtra("unregistered");
                    }
                    if (stringExtra == null) {
                        String stringExtra2 = intent2.getStringExtra("error");
                        if (stringExtra2 == null) {
                            Log.w("Rpc", "Unexpected response, no error or registration id ".concat(String.valueOf(intent2.getExtras())));
                            return;
                        }
                        if (Log.isLoggable("Rpc", 3)) {
                            Log.d("Rpc", "Received InstanceID error ".concat(stringExtra2));
                        }
                        if (stringExtra2.startsWith("|")) {
                            String[] split = stringExtra2.split("\\|");
                            if (split.length <= 2 || !Objects.equals(split[1], "ID")) {
                                Log.w("Rpc", "Unexpected structured response ".concat(stringExtra2));
                                return;
                            }
                            String str = split[2];
                            String str2 = split[3];
                            if (str2.startsWith(":")) {
                                str2 = str2.substring(1);
                            }
                            rpc.zzh(str, intent2.putExtra("error", str2).getExtras());
                            return;
                        }
                        synchronized (rpc.zze) {
                            int i = 0;
                            while (i < rpc.zze.size()) {
                                try {
                                    rpc.zzh((String) rpc.zze.keyAt(i), intent2.getExtras());
                                    i++;
                                } finally {
                                }
                            }
                        }
                        return;
                    }
                    Matcher matcher = zzd.matcher(stringExtra);
                    if (matcher.matches()) {
                        String group = matcher.group(1);
                        String group2 = matcher.group(2);
                        if (group != null) {
                            Bundle extras = intent2.getExtras();
                            extras.putString("registration_id", group2);
                            rpc.zzh(group, extras);
                            return;
                        }
                        return;
                    } else if (Log.isLoggable("Rpc", 3)) {
                        Log.d("Rpc", "Unexpected response string: ".concat(stringExtra));
                        return;
                    } else {
                        return;
                    }
                } else if (Log.isLoggable("Rpc", 3)) {
                    Log.d("Rpc", "Unexpected response action: ".concat(String.valueOf(action)));
                    return;
                } else {
                    return;
                }
            }
        }
        Log.w("Rpc", "Dropping invalid message");
    }

    private final Task zze(Bundle bundle) {
        String zzf2 = zzf();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.zze) {
            this.zze.put(zzf2, taskCompletionSource);
        }
        Intent intent = new Intent();
        intent.setPackage("com.google.android.gms");
        if (this.zzg.zzb() == 2) {
            intent.setAction("com.google.iid.TOKEN_REQUEST");
        } else {
            intent.setAction("com.google.android.c2dm.intent.REGISTER");
        }
        intent.putExtras(bundle);
        zzg(this.zzf, intent);
        intent.putExtra("kid", "|ID|" + zzf2 + "|");
        if (Log.isLoggable("Rpc", 3)) {
            Log.d("Rpc", "Sending ".concat(String.valueOf(intent.getExtras())));
        }
        intent.putExtra("google.messenger", this.zzi);
        if (!(this.zzj == null && this.zzk == null)) {
            Message obtain = Message.obtain();
            obtain.obj = intent;
            try {
                Messenger messenger = this.zzj;
                if (messenger != null) {
                    messenger.send(obtain);
                } else {
                    this.zzk.zzb(obtain);
                }
            } catch (RemoteException unused) {
                if (Log.isLoggable("Rpc", 3)) {
                    Log.d("Rpc", "Messenger failed, fallback to startService");
                }
            }
            taskCompletionSource.getTask().addOnCompleteListener(zzc, new zzad(this, zzf2, this.zzh.schedule(new zzac(taskCompletionSource), 30, TimeUnit.SECONDS)));
            return taskCompletionSource.getTask();
        }
        if (this.zzg.zzb() == 2) {
            this.zzf.sendBroadcast(intent);
        } else {
            this.zzf.startService(intent);
        }
        taskCompletionSource.getTask().addOnCompleteListener(zzc, new zzad(this, zzf2, this.zzh.schedule(new zzac(taskCompletionSource), 30, TimeUnit.SECONDS)));
        return taskCompletionSource.getTask();
    }

    private static synchronized String zzf() {
        String num;
        synchronized (Rpc.class) {
            int i = zza;
            zza = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    private static synchronized void zzg(Context context, Intent intent) {
        synchronized (Rpc.class) {
            try {
                if (zzb == null) {
                    Intent intent2 = new Intent();
                    intent2.setPackage("com.google.example.invalidpackage");
                    zzb = PendingIntent.getBroadcast(context, 0, intent2, zza.zza);
                }
                intent.putExtra("app", zzb);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    private final void zzh(String str, Bundle bundle) {
        synchronized (this.zze) {
            try {
                TaskCompletionSource taskCompletionSource = (TaskCompletionSource) this.zze.remove(str);
                if (taskCompletionSource == null) {
                    Log.w("Rpc", "Missing callback for " + str);
                    return;
                }
                taskCompletionSource.setResult(bundle);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static boolean zzi(Bundle bundle) {
        return bundle != null && bundle.containsKey("google.messenger");
    }

    public Task<CloudMessage> getProxiedNotificationData() {
        if (this.zzg.zza() >= 241100000) {
            return zzv.zzb(this.zzf).zzd(5, Bundle.EMPTY).continueWith(zzc, zzab.zza);
        }
        return Tasks.forException(new IOException(InstanceID.ERROR_SERVICE_NOT_AVAILABLE));
    }

    public Task<Void> messageHandled(CloudMessage cloudMessage) {
        if (this.zzg.zza() < 233700000) {
            return Tasks.forException(new IOException(InstanceID.ERROR_SERVICE_NOT_AVAILABLE));
        }
        Bundle bundle = new Bundle();
        bundle.putString(Constants.MessagePayloadKeys.MSGID, cloudMessage.getMessageId());
        Integer zza2 = cloudMessage.zza();
        if (zza2 != null) {
            bundle.putInt(Constants.MessagePayloadKeys.PRODUCT_ID, zza2.intValue());
        }
        return zzv.zzb(this.zzf).zzc(3, bundle);
    }

    public Task<Bundle> send(Bundle bundle) {
        if (this.zzg.zza() >= 12000000) {
            return zzv.zzb(this.zzf).zzd(1, bundle).continueWith(zzc, zzaa.zza);
        }
        if (this.zzg.zzb() != 0) {
            return zze(bundle).continueWithTask(zzc, new zzz(this, bundle));
        }
        return Tasks.forException(new IOException(InstanceID.ERROR_MISSING_INSTANCEID_SERVICE));
    }

    public Task<Void> setRetainProxiedNotifications(boolean z) {
        if (this.zzg.zza() < 241100000) {
            return Tasks.forException(new IOException(InstanceID.ERROR_SERVICE_NOT_AVAILABLE));
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("proxy_retention", z);
        return zzv.zzb(this.zzf).zzc(4, bundle);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task zzb(Bundle bundle, Task task) throws Exception {
        return (task.isSuccessful() && zzi((Bundle) task.getResult())) ? zze(bundle).onSuccessTask(zzc, zzx.zza) : task;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(String str, ScheduledFuture scheduledFuture, Task task) {
        synchronized (this.zze) {
            this.zze.remove(str);
        }
        scheduledFuture.cancel(false);
    }
}
