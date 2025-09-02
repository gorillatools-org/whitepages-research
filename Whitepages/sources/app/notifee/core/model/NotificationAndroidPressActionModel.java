package app.notifee.core.model;

import android.os.Bundle;
import androidx.annotation.Keep;
import java.util.Objects;

@Keep
public class NotificationAndroidPressActionModel {
    private Bundle mNotificationAndroidPressActionBundle;

    private NotificationAndroidPressActionModel(Bundle bundle) {
        this.mNotificationAndroidPressActionBundle = bundle;
    }

    public static NotificationAndroidPressActionModel fromBundle(Bundle bundle) {
        return new NotificationAndroidPressActionModel(bundle);
    }

    public String getId() {
        String string = this.mNotificationAndroidPressActionBundle.getString("id");
        Objects.requireNonNull(string);
        return string;
    }

    public String getLaunchActivity() {
        return this.mNotificationAndroidPressActionBundle.getString("launchActivity");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getLaunchActivityFlags() {
        /*
            r5 = this;
            android.os.Bundle r0 = r5.mNotificationAndroidPressActionBundle
            java.lang.String r1 = "launchActivityFlags"
            boolean r0 = r0.containsKey(r1)
            if (r0 != 0) goto L_0x000c
            r0 = -1
            return r0
        L_0x000c:
            android.os.Bundle r0 = r5.mNotificationAndroidPressActionBundle
            java.util.ArrayList r0 = r0.getIntegerArrayList(r1)
            java.util.Objects.requireNonNull(r0)
            r1 = 0
            r2 = r1
        L_0x0017:
            int r3 = r0.size()
            if (r1 >= r3) goto L_0x0071
            java.lang.Object r3 = r0.get(r1)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            switch(r3) {
                case 0: goto L_0x006b;
                case 1: goto L_0x0068;
                case 2: goto L_0x0065;
                case 3: goto L_0x0062;
                case 4: goto L_0x005f;
                case 5: goto L_0x005c;
                case 6: goto L_0x0059;
                case 7: goto L_0x0056;
                case 8: goto L_0x0053;
                case 9: goto L_0x0050;
                case 10: goto L_0x004d;
                case 11: goto L_0x004a;
                case 12: goto L_0x004a;
                case 13: goto L_0x0047;
                case 14: goto L_0x0044;
                case 15: goto L_0x0041;
                case 16: goto L_0x003d;
                case 17: goto L_0x003a;
                case 18: goto L_0x0037;
                case 19: goto L_0x0034;
                case 20: goto L_0x002b;
                default: goto L_0x002a;
            }
        L_0x002a:
            goto L_0x006e
        L_0x002b:
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 28
            if (r3 < r4) goto L_0x006e
            r3 = 2048(0x800, float:2.87E-42)
            goto L_0x006d
        L_0x0034:
            r3 = 4096(0x1000, float:5.74E-42)
            goto L_0x006d
        L_0x0037:
            r3 = 8192(0x2000, float:1.14794E-41)
            goto L_0x006d
        L_0x003a:
            r3 = 16384(0x4000, float:2.2959E-41)
            goto L_0x006d
        L_0x003d:
            r3 = 32768(0x8000, float:4.5918E-41)
            goto L_0x006d
        L_0x0041:
            r3 = 65536(0x10000, float:9.18355E-41)
            goto L_0x006d
        L_0x0044:
            r3 = 131072(0x20000, float:1.83671E-40)
            goto L_0x006d
        L_0x0047:
            r3 = 262144(0x40000, float:3.67342E-40)
            goto L_0x006d
        L_0x004a:
            r3 = 524288(0x80000, float:7.34684E-40)
            goto L_0x006d
        L_0x004d:
            r3 = 1048576(0x100000, float:1.469368E-39)
            goto L_0x006d
        L_0x0050:
            r3 = 2097152(0x200000, float:2.938736E-39)
            goto L_0x006d
        L_0x0053:
            r3 = 4194304(0x400000, float:5.877472E-39)
            goto L_0x006d
        L_0x0056:
            r3 = 8388608(0x800000, float:1.17549435E-38)
            goto L_0x006d
        L_0x0059:
            r3 = 16777216(0x1000000, float:2.3509887E-38)
            goto L_0x006d
        L_0x005c:
            r3 = 33554432(0x2000000, float:9.403955E-38)
            goto L_0x006d
        L_0x005f:
            r3 = 67108864(0x4000000, float:1.5046328E-36)
            goto L_0x006d
        L_0x0062:
            r3 = 134217728(0x8000000, float:3.85186E-34)
            goto L_0x006d
        L_0x0065:
            r3 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x006d
        L_0x0068:
            r3 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x006d
        L_0x006b:
            r3 = 1073741824(0x40000000, float:2.0)
        L_0x006d:
            r2 = r2 | r3
        L_0x006e:
            int r1 = r1 + 1
            goto L_0x0017
        L_0x0071:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: app.notifee.core.model.NotificationAndroidPressActionModel.getLaunchActivityFlags():int");
    }

    public String getMainComponent() {
        return this.mNotificationAndroidPressActionBundle.getString("mainComponent");
    }

    public Bundle toBundle() {
        return (Bundle) this.mNotificationAndroidPressActionBundle.clone();
    }
}
