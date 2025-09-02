package n.o.t.i.f.e.e;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public abstract class m {
    public static volatile Intent a;

    public static Intent a(Context context) {
        String lowerCase = Build.BRAND.toLowerCase(Locale.US);
        List arrayList = new ArrayList();
        lowerCase.getClass();
        char c = 65535;
        switch (lowerCase.hashCode()) {
            case -1320380160:
                if (lowerCase.equals("oneplus")) {
                    c = 0;
                    break;
                }
                break;
            case -1206476313:
                if (lowerCase.equals("huawei")) {
                    c = 1;
                    break;
                }
                break;
            case -759499589:
                if (lowerCase.equals("xiaomi")) {
                    c = 2;
                    break;
                }
                break;
            case 103639:
                if (lowerCase.equals("htc")) {
                    c = 3;
                    break;
                }
                break;
            case 3003984:
                if (lowerCase.equals("asus")) {
                    c = 4;
                    break;
                }
                break;
            case 3318203:
                if (lowerCase.equals("letv")) {
                    c = 5;
                    break;
                }
                break;
            case 3418016:
                if (lowerCase.equals("oppo")) {
                    c = 6;
                    break;
                }
                break;
            case 3620012:
                if (lowerCase.equals("vivo")) {
                    c = 7;
                    break;
                }
                break;
            case 99462250:
                if (lowerCase.equals("honor")) {
                    c = 8;
                    break;
                }
                break;
            case 103777484:
                if (lowerCase.equals("meizu")) {
                    c = 9;
                    break;
                }
                break;
            case 105000290:
                if (lowerCase.equals("nokia")) {
                    c = 10;
                    break;
                }
                break;
            case 108389869:
                if (lowerCase.equals("redmi")) {
                    c = 11;
                    break;
                }
                break;
            case 949547143:
                if (lowerCase.equals("coloros")) {
                    c = 12;
                    break;
                }
                break;
            case 1864941562:
                if (lowerCase.equals("samsung")) {
                    c = 13;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                arrayList = Arrays.asList(new Intent[]{a("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity")});
                break;
            case 1:
                arrayList = Arrays.asList(new Intent[]{a("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"), a("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity"), a("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity")});
                break;
            case 2:
            case 11:
                arrayList = Arrays.asList(new Intent[]{a("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")});
                break;
            case 3:
                arrayList = Arrays.asList(new Intent[]{a("com.htc.pitroad", "com.htc.pitroad.landingpage.activity.LandingPageActivity")});
                break;
            case 4:
                arrayList = Arrays.asList(new Intent[]{a("com.asus.mobilemanager", "com.asus.mobilemanager.powersaver.PowerSaverSettings"), a("com.asus.mobilemanager", "com.asus.mobilemanager.autostart.AutoStartActivity"), a("com.asus.mobilemanager", "com.asus.mobilemanager.entry.FunctionActivity").setData(Uri.parse("mobilemanager://function/entry/AutoStart"))});
                break;
            case 5:
                arrayList = Arrays.asList(new Intent[]{a("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity").setData(Uri.parse("mobilemanager://function/entry/AutoStart"))});
                break;
            case 6:
            case 12:
                arrayList = Arrays.asList(new Intent[]{a("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"), a("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity"), a("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity").setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS"), a("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerUsageModelActivity"), a("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerSaverModeActivity"), a("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerConsumptionActivity")});
                break;
            case 7:
                arrayList = Arrays.asList(new Intent[]{a("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"), a("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"), a("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")});
                break;
            case 8:
                arrayList = Arrays.asList(new Intent[]{a("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")});
                break;
            case 9:
                arrayList = Arrays.asList(new Intent[]{a("com.meizu.safe", "com.meizu.safe.security.SHOW_APPSEC").addCategory("android.intent.category.DEFAULT")});
                break;
            case 10:
                arrayList = Arrays.asList(new Intent[]{a("com.evenwell.powersaving.g3", "com.evenwell.powersaving.g3.exception.PowerSaverExceptionActivity")});
                break;
            case 13:
                arrayList = Arrays.asList(new Intent[]{a("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity"), a("com.samsung.android.sm", "com.samsung.android.sm.ui.battery.BatteryActivity"), a("com.samsung.android.lool", "com.samsung.android.sm.battery.ui.BatteryActivity")});
                break;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            Intent intent = (Intent) arrayList.get(i);
            if (h.a(context, intent)) {
                synchronized (m.class) {
                    a = intent;
                }
                return intent;
            }
        }
        return null;
    }

    public static Intent a(String str, String str2) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, str2));
        return intent;
    }
}
