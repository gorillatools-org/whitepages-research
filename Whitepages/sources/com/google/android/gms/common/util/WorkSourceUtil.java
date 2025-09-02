package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@KeepForSdk
public class WorkSourceUtil {
    private static final int zza = Process.myUid();
    private static final Method zzb;
    private static final Method zzc;
    private static final Method zzd;
    private static final Method zze;
    private static final Method zzf;
    private static final Method zzg;
    private static final Method zzh;
    private static final Method zzi;
    private static Boolean zzj = null;

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0093  */
    static {
        /*
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            java.lang.String r1 = "add"
            java.lang.Class<android.os.WorkSource> r2 = android.os.WorkSource.class
            int r3 = android.os.Process.myUid()
            zza = r3
            r3 = 0
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0018 }
            java.lang.Class[] r4 = new java.lang.Class[]{r4}     // Catch:{ Exception -> 0x0018 }
            java.lang.reflect.Method r4 = r2.getMethod(r1, r4)     // Catch:{ Exception -> 0x0018 }
            goto L_0x0019
        L_0x0018:
            r4 = r3
        L_0x0019:
            zzb = r4
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0026 }
            java.lang.Class[] r4 = new java.lang.Class[]{r4, r0}     // Catch:{ Exception -> 0x0026 }
            java.lang.reflect.Method r1 = r2.getMethod(r1, r4)     // Catch:{ Exception -> 0x0026 }
            goto L_0x0027
        L_0x0026:
            r1 = r3
        L_0x0027:
            zzc = r1
            java.lang.String r1 = "size"
            java.lang.reflect.Method r1 = r2.getMethod(r1, r3)     // Catch:{ Exception -> 0x0030 }
            goto L_0x0031
        L_0x0030:
            r1 = r3
        L_0x0031:
            zzd = r1
            java.lang.String r1 = "get"
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0040 }
            java.lang.Class[] r4 = new java.lang.Class[]{r4}     // Catch:{ Exception -> 0x0040 }
            java.lang.reflect.Method r1 = r2.getMethod(r1, r4)     // Catch:{ Exception -> 0x0040 }
            goto L_0x0041
        L_0x0040:
            r1 = r3
        L_0x0041:
            zze = r1
            java.lang.String r1 = "getName"
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0050 }
            java.lang.Class[] r4 = new java.lang.Class[]{r4}     // Catch:{ Exception -> 0x0050 }
            java.lang.reflect.Method r1 = r2.getMethod(r1, r4)     // Catch:{ Exception -> 0x0050 }
            goto L_0x0051
        L_0x0050:
            r1 = r3
        L_0x0051:
            zzf = r1
            boolean r1 = com.google.android.gms.common.util.PlatformVersion.isAtLeastP()
            java.lang.String r4 = "WorkSourceUtil"
            if (r1 == 0) goto L_0x0068
            java.lang.String r1 = "createWorkChain"
            java.lang.reflect.Method r1 = r2.getMethod(r1, r3)     // Catch:{ Exception -> 0x0062 }
            goto L_0x0069
        L_0x0062:
            r1 = move-exception
            java.lang.String r5 = "Missing WorkChain API createWorkChain"
            android.util.Log.w(r4, r5, r1)
        L_0x0068:
            r1 = r3
        L_0x0069:
            zzg = r1
            boolean r1 = com.google.android.gms.common.util.PlatformVersion.isAtLeastP()
            if (r1 == 0) goto L_0x008a
            java.lang.String r1 = "android.os.WorkSource$WorkChain"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r5 = "addNode"
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0084 }
            java.lang.Class[] r0 = new java.lang.Class[]{r6, r0}     // Catch:{ Exception -> 0x0084 }
            java.lang.reflect.Method r0 = r1.getMethod(r5, r0)     // Catch:{ Exception -> 0x0084 }
            goto L_0x008b
        L_0x0084:
            r0 = move-exception
            java.lang.String r1 = "Missing WorkChain class"
            android.util.Log.w(r4, r1, r0)
        L_0x008a:
            r0 = r3
        L_0x008b:
            zzh = r0
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastP()
            if (r0 == 0) goto L_0x009e
            java.lang.String r0 = "isEmpty"
            java.lang.reflect.Method r0 = r2.getMethod(r0, r3)     // Catch:{ Exception -> 0x009e }
            r1 = 1
            r0.setAccessible(r1)     // Catch:{ Exception -> 0x009f }
            goto L_0x009f
        L_0x009e:
            r0 = r3
        L_0x009f:
            zzi = r0
            zzj = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.WorkSourceUtil.<clinit>():void");
    }

    private WorkSourceUtil() {
    }

    @KeepForSdk
    public static void add(WorkSource workSource, int i, String str) {
        Method method = zzc;
        if (method != null) {
            if (str == null) {
                str = "";
            }
            try {
                method.invoke(workSource, new Object[]{Integer.valueOf(i), str});
            } catch (Exception e) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            }
        } else {
            Method method2 = zzb;
            if (method2 != null) {
                try {
                    method2.invoke(workSource, new Object[]{Integer.valueOf(i)});
                } catch (Exception e2) {
                    Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
                }
            }
        }
    }

    @KeepForSdk
    public static WorkSource fromPackage(Context context, String str) {
        if (!(context == null || context.getPackageManager() == null || str == null)) {
            try {
                ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
                if (applicationInfo == null) {
                    Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(str));
                    return null;
                }
                int i = applicationInfo.uid;
                WorkSource workSource = new WorkSource();
                add(workSource, i, str);
                return workSource;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("WorkSourceUtil", "Could not find package: ".concat(str));
            }
        }
        return null;
    }

    @KeepForSdk
    public static WorkSource fromPackageAndModuleExperimentalPi(Context context, String str, String str2) {
        Method method;
        if (context == null || context.getPackageManager() == null || str2 == null || str == null) {
            Log.w("WorkSourceUtil", "Unexpected null arguments");
            return null;
        }
        int i = -1;
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
            if (applicationInfo == null) {
                Log.e("WorkSourceUtil", "Could not get applicationInfo from package: ".concat(str));
            } else {
                i = applicationInfo.uid;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("WorkSourceUtil", "Could not find package: ".concat(str));
        }
        if (i < 0) {
            return null;
        }
        WorkSource workSource = new WorkSource();
        Method method2 = zzg;
        if (method2 == null || (method = zzh) == null) {
            add(workSource, i, str);
        } else {
            try {
                Object invoke = method2.invoke(workSource, (Object[]) null);
                int i2 = zza;
                if (i != i2) {
                    method.invoke(invoke, new Object[]{Integer.valueOf(i), str});
                }
                method.invoke(invoke, new Object[]{Integer.valueOf(i2), str2});
            } catch (Exception e) {
                Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", e);
            }
        }
        return workSource;
    }

    @KeepForSdk
    public static int get(WorkSource workSource, int i) {
        Method method = zze;
        if (method == null) {
            return 0;
        }
        try {
            Object invoke = method.invoke(workSource, new Object[]{Integer.valueOf(i)});
            Preconditions.checkNotNull(invoke);
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            return 0;
        }
    }

    @KeepForSdk
    public static String getName(WorkSource workSource, int i) {
        Method method = zzf;
        if (method == null) {
            return null;
        }
        try {
            return (String) method.invoke(workSource, new Object[]{Integer.valueOf(i)});
        } catch (Exception e) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            return null;
        }
    }

    @KeepForSdk
    public static List<String> getNames(WorkSource workSource) {
        int i;
        ArrayList arrayList = new ArrayList();
        if (workSource == null) {
            i = 0;
        } else {
            i = size(workSource);
        }
        if (i != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                String name = getName(workSource, i2);
                if (!Strings.isEmptyOrWhitespace(name)) {
                    Preconditions.checkNotNull(name);
                    arrayList.add(name);
                }
            }
        }
        return arrayList;
    }

    @KeepForSdk
    public static synchronized boolean hasWorkSourcePermission(Context context) {
        synchronized (WorkSourceUtil.class) {
            Boolean bool = zzj;
            if (bool != null) {
                boolean booleanValue = bool.booleanValue();
                return booleanValue;
            }
            boolean z = false;
            if (context == null) {
                return false;
            }
            if (ContextCompat.checkSelfPermission(context, "android.permission.UPDATE_DEVICE_STATS") == 0) {
                z = true;
            }
            zzj = Boolean.valueOf(z);
            return z;
        }
    }

    @KeepForSdk
    public static boolean isEmpty(WorkSource workSource) {
        Method method = zzi;
        if (method != null) {
            try {
                Object invoke = method.invoke(workSource, (Object[]) null);
                Preconditions.checkNotNull(invoke);
                return ((Boolean) invoke).booleanValue();
            } catch (Exception e) {
                Log.e("WorkSourceUtil", "Unable to check WorkSource emptiness", e);
            }
        }
        return size(workSource) == 0;
    }

    @KeepForSdk
    public static int size(WorkSource workSource) {
        Method method = zzd;
        if (method == null) {
            return 0;
        }
        try {
            Object invoke = method.invoke(workSource, (Object[]) null);
            Preconditions.checkNotNull(invoke);
            return ((Integer) invoke).intValue();
        } catch (Exception e) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e);
            return 0;
        }
    }
}
