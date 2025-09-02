package androidx.core.content;

import android.content.Context;
import android.os.Process;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.util.ObjectsCompat;

public abstract class PermissionChecker {
    public static int checkPermission(Context context, String str, int i, int i2, String str2) {
        int i3;
        if (context.checkPermission(str, i, i2) == -1) {
            return -1;
        }
        String permissionToOp = AppOpsManagerCompat.permissionToOp(str);
        if (permissionToOp == null) {
            return 0;
        }
        if (str2 == null) {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(i2);
            if (packagesForUid == null || packagesForUid.length <= 0) {
                return -1;
            }
            str2 = packagesForUid[0];
        }
        int myUid = Process.myUid();
        String packageName = context.getPackageName();
        if (myUid != i2 || !ObjectsCompat.equals(packageName, str2)) {
            i3 = AppOpsManagerCompat.noteProxyOpNoThrow(context, permissionToOp, str2);
        } else {
            i3 = AppOpsManagerCompat.checkOrNoteProxyOp(context, i2, permissionToOp, str2);
        }
        if (i3 == 0) {
            return 0;
        }
        return -2;
    }

    public static int checkSelfPermission(Context context, String str) {
        return checkPermission(context, str, Process.myPid(), Process.myUid(), context.getPackageName());
    }
}
