package com.lugg.RNCConfig;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class RNCConfigModule extends ReactContextBaseJavaModule {
    public RNCConfigModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "RNCConfigModule";
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        r2 = getReactApplicationContext().getApplicationContext().getPackageName();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0058 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0020 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, java.lang.Object> getConstants() {
        /*
            r8 = this;
            java.lang.String r0 = "ReactNative"
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            com.facebook.react.bridge.ReactApplicationContext r2 = r8.getReactApplicationContext()     // Catch:{ ClassNotFoundException -> 0x0073 }
            android.content.res.Resources r3 = r2.getResources()     // Catch:{ ClassNotFoundException -> 0x0073 }
            java.lang.String r4 = "build_config_package"
            java.lang.String r5 = "string"
            java.lang.String r6 = r2.getPackageName()     // Catch:{ ClassNotFoundException -> 0x0073 }
            int r3 = r3.getIdentifier(r4, r5, r6)     // Catch:{ ClassNotFoundException -> 0x0073 }
            java.lang.String r2 = r2.getString(r3)     // Catch:{ NotFoundException -> 0x0020 }
            goto L_0x002c
        L_0x0020:
            com.facebook.react.bridge.ReactApplicationContext r2 = r8.getReactApplicationContext()     // Catch:{ ClassNotFoundException -> 0x0073 }
            android.content.Context r2 = r2.getApplicationContext()     // Catch:{ ClassNotFoundException -> 0x0073 }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ ClassNotFoundException -> 0x0073 }
        L_0x002c:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0073 }
            r3.<init>()     // Catch:{ ClassNotFoundException -> 0x0073 }
            r3.append(r2)     // Catch:{ ClassNotFoundException -> 0x0073 }
            java.lang.String r2 = ".BuildConfig"
            r3.append(r2)     // Catch:{ ClassNotFoundException -> 0x0073 }
            java.lang.String r2 = r3.toString()     // Catch:{ ClassNotFoundException -> 0x0073 }
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ ClassNotFoundException -> 0x0073 }
            java.lang.reflect.Field[] r2 = r2.getDeclaredFields()     // Catch:{ ClassNotFoundException -> 0x0073 }
            int r3 = r2.length     // Catch:{ ClassNotFoundException -> 0x0073 }
            r4 = 0
        L_0x0047:
            if (r4 >= r3) goto L_0x0078
            r5 = r2[r4]     // Catch:{ ClassNotFoundException -> 0x0073 }
            java.lang.String r6 = r5.getName()     // Catch:{ IllegalAccessException -> 0x0058 }
            r7 = 0
            java.lang.Object r7 = r5.get(r7)     // Catch:{ IllegalAccessException -> 0x0058 }
            r1.put(r6, r7)     // Catch:{ IllegalAccessException -> 0x0058 }
            goto L_0x0070
        L_0x0058:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0073 }
            r6.<init>()     // Catch:{ ClassNotFoundException -> 0x0073 }
            java.lang.String r7 = "ReactConfig: Could not access BuildConfig field "
            r6.append(r7)     // Catch:{ ClassNotFoundException -> 0x0073 }
            java.lang.String r5 = r5.getName()     // Catch:{ ClassNotFoundException -> 0x0073 }
            r6.append(r5)     // Catch:{ ClassNotFoundException -> 0x0073 }
            java.lang.String r5 = r6.toString()     // Catch:{ ClassNotFoundException -> 0x0073 }
            android.util.Log.d(r0, r5)     // Catch:{ ClassNotFoundException -> 0x0073 }
        L_0x0070:
            int r4 = r4 + 1
            goto L_0x0047
        L_0x0073:
            java.lang.String r2 = "ReactConfig: Could not find BuildConfig class"
            android.util.Log.d(r0, r2)
        L_0x0078:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lugg.RNCConfig.RNCConfigModule.getConstants():java.util.Map");
    }
}
