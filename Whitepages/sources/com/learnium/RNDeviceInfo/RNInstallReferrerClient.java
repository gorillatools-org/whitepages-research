package com.learnium.RNDeviceInfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RNInstallReferrerClient {
    /* access modifiers changed from: private */
    public static Class InstallReferrerClientClazz = InstallReferrerClient.class;
    private static Class InstallReferrerStateListenerClazz = InstallReferrerStateListener.class;
    /* access modifiers changed from: private */
    public static Class ReferrerDetailsClazz = ReferrerDetails.class;
    private Object installReferrerStateListener;
    /* access modifiers changed from: private */
    public Object mReferrerClient;
    /* access modifiers changed from: private */
    public final SharedPreferences sharedPreferences;

    static {
        try {
        } catch (Exception unused) {
            System.err.println("RNInstallReferrerClient exception. 'installreferrer' APIs are unavailable.");
        }
    }

    RNInstallReferrerClient(Context context) {
        this.sharedPreferences = context.getSharedPreferences("react-native-device-info", 0);
        Class cls = InstallReferrerClientClazz;
        if (cls != null && InstallReferrerStateListenerClazz != null && ReferrerDetailsClazz != null) {
            try {
                Object invoke = cls.getMethod("newBuilder", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
                this.mReferrerClient = invoke.getClass().getMethod("build", (Class[]) null).invoke(invoke, (Object[]) null);
                this.installReferrerStateListener = Proxy.newProxyInstance(InstallReferrerStateListenerClazz.getClassLoader(), new Class[]{InstallReferrerStateListenerClazz}, new InstallReferrerStateListenerProxy());
                InstallReferrerClientClazz.getMethod("startConnection", new Class[]{InstallReferrerStateListenerClazz}).invoke(this.mReferrerClient, new Object[]{this.installReferrerStateListener});
            } catch (Exception e) {
                PrintStream printStream = System.err;
                printStream.println("RNInstallReferrerClient exception. getInstallReferrer will be unavailable: " + e.getMessage());
                e.printStackTrace(printStream);
            }
        }
    }

    private class InstallReferrerStateListenerProxy implements InvocationHandler {
        private InstallReferrerStateListenerProxy() {
        }

        public Object invoke(Object obj, Method method, Object[] objArr) {
            String name = method.getName();
            try {
                if (name.equals("onInstallReferrerSetupFinished") && objArr != null) {
                    Integer num = objArr[0];
                    if (num instanceof Integer) {
                        onInstallReferrerSetupFinished(num.intValue());
                        return null;
                    }
                }
                if (!name.equals("onInstallReferrerServiceDisconnected")) {
                    return null;
                }
                onInstallReferrerServiceDisconnected();
                return null;
            } catch (Exception e) {
                throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
            }
        }

        public void onInstallReferrerSetupFinished(int i) {
            if (i == 0) {
                try {
                    Log.d("InstallReferrerState", "OK");
                    Object invoke = RNInstallReferrerClient.InstallReferrerClientClazz.getMethod("getInstallReferrer", (Class[]) null).invoke(RNInstallReferrerClient.this.mReferrerClient, (Object[]) null);
                    SharedPreferences.Editor edit = RNInstallReferrerClient.this.sharedPreferences.edit();
                    edit.putString("installReferrer", (String) RNInstallReferrerClient.ReferrerDetailsClazz.getMethod("getInstallReferrer", (Class[]) null).invoke(invoke, (Object[]) null));
                    edit.apply();
                    RNInstallReferrerClient.InstallReferrerClientClazz.getMethod("endConnection", (Class[]) null).invoke(RNInstallReferrerClient.this.mReferrerClient, (Object[]) null);
                } catch (Exception e) {
                    PrintStream printStream = System.err;
                    printStream.println("RNInstallReferrerClient exception. getInstallReferrer will be unavailable: " + e.getMessage());
                    e.printStackTrace(printStream);
                }
            } else if (i == 1) {
                Log.d("InstallReferrerState", "SERVICE_UNAVAILABLE");
            } else if (i == 2) {
                Log.d("InstallReferrerState", "FEATURE_NOT_SUPPORTED");
            }
        }

        public void onInstallReferrerServiceDisconnected() {
            Log.d("RNInstallReferrerClient", "InstallReferrerService disconnected");
        }
    }
}
