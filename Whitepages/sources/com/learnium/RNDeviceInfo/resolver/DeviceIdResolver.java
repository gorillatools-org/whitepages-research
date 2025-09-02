package com.learnium.RNDeviceInfo.resolver;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.iid.InstanceID;
import com.learnium.RNDeviceInfo.RNDeviceModule;
import com.salesforce.marketingcloud.messages.iam.j;
import java.util.UUID;

public class DeviceIdResolver {
    private final Context context;

    public DeviceIdResolver(Context context2) {
        this.context = context2;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0018 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getInstanceIdSync() {
        /*
            r2 = this;
            java.lang.String r0 = r2.getInstanceIdFromPrefs()
            java.lang.String r1 = "unknown"
            if (r0 == r1) goto L_0x0009
            return r0
        L_0x0009:
            java.lang.String r0 = r2.getFirebaseInstanceId()     // Catch:{ ClassNotFoundException -> 0x0018, IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException -> 0x0011 }
            r2.setInstanceIdInPrefs(r0)     // Catch:{ ClassNotFoundException -> 0x0018, IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException -> 0x0011 }
            return r0
        L_0x0011:
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "N/A: Unsupported version of com.google.firebase:firebase-iid in your project."
            r0.println(r1)
        L_0x0018:
            java.lang.String r0 = r2.getGmsInstanceId()     // Catch:{ ClassNotFoundException -> 0x0027, IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException -> 0x0020 }
            r2.setInstanceIdInPrefs(r0)     // Catch:{ ClassNotFoundException -> 0x0027, IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException -> 0x0020 }
            return r0
        L_0x0020:
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "N/A: Unsupported version of com.google.android.gms.iid in your project."
            r0.println(r1)
        L_0x0027:
            java.lang.String r0 = r2.getUUIDInstanceId()
            r2.setInstanceIdInPrefs(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.learnium.RNDeviceInfo.resolver.DeviceIdResolver.getInstanceIdSync():java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public String getUUIDInstanceId() {
        return UUID.randomUUID().toString();
    }

    /* access modifiers changed from: package-private */
    public String getInstanceIdFromPrefs() {
        return RNDeviceModule.getRNDISharedPreferences(this.context).getString("instanceId", j.h);
    }

    /* access modifiers changed from: package-private */
    public void setInstanceIdInPrefs(String str) {
        SharedPreferences.Editor edit = RNDeviceModule.getRNDISharedPreferences(this.context).edit();
        edit.putString("instanceId", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public String getGmsInstanceId() {
        String str = InstanceID.ERROR_SERVICE_NOT_AVAILABLE;
        Object invoke = InstanceID.class.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{this.context.getApplicationContext()});
        return (String) invoke.getClass().getMethod("getId", (Class[]) null).invoke(invoke, (Object[]) null);
    }

    /* access modifiers changed from: package-private */
    public String getFirebaseInstanceId() {
        Object invoke = Class.forName("com.google.firebase.iid.FirebaseInstanceId").getDeclaredMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null);
        return (String) invoke.getClass().getMethod("getId", (Class[]) null).invoke(invoke, (Object[]) null);
    }
}
