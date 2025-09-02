package com.facebook.soloader.recovery;

import android.content.Context;
import com.facebook.soloader.BackupSoSource;
import com.facebook.soloader.DirectorySoSource;
import com.facebook.soloader.LogUtil;
import com.facebook.soloader.SoLoaderULError;
import com.facebook.soloader.SoSource;
import com.facebook.soloader.UnpackingSoSource;
import java.io.File;
import java.util.ArrayList;

public class CheckOnDiskStateDataApp implements RecoveryStrategy {
    private final Context mContext;

    public CheckOnDiskStateDataApp(Context context) {
        this.mContext = context;
    }

    public boolean recover(UnsatisfiedLinkError unsatisfiedLinkError, SoSource[] soSourceArr) {
        if (!(unsatisfiedLinkError instanceof SoLoaderULError)) {
            return false;
        }
        LogUtil.e("SoLoader", "Checking /data/app missing libraries.");
        File file = new File(this.mContext.getApplicationInfo().nativeLibraryDir);
        if (!file.exists()) {
            LogUtil.e("SoLoader", "Native library directory " + file + " does not exist, exiting /data/app recovery.");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        int length = soSourceArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            BackupSoSource backupSoSource = soSourceArr[i];
            if (!(backupSoSource instanceof BackupSoSource)) {
                i++;
            } else {
                BackupSoSource backupSoSource2 = backupSoSource;
                try {
                    for (UnpackingSoSource.Dso dso : backupSoSource2.getDsosBaseApk()) {
                        if (!new File(file, dso.name).exists()) {
                            arrayList.add(dso.name);
                        }
                    }
                    if (arrayList.isEmpty()) {
                        LogUtil.e("SoLoader", "No libraries missing from " + file);
                        return false;
                    }
                    LogUtil.e("SoLoader", "Missing libraries from " + file + ": " + arrayList.toString() + ", will run prepare on tbe backup so source");
                    backupSoSource2.prepare(0);
                } catch (Exception e) {
                    LogUtil.e("SoLoader", "Encountered an exception while recovering from /data/app failure ", e);
                    return false;
                }
            }
        }
        for (DirectorySoSource directorySoSource : soSourceArr) {
            if ((directorySoSource instanceof DirectorySoSource) && !(directorySoSource instanceof BackupSoSource)) {
                directorySoSource.setExplicitDependencyResolution();
            }
        }
        LogUtil.e("SoLoader", "Successfully recovered from /data/app disk failure.");
        return true;
    }
}
