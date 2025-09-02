package com.facebook.soloader.recovery;

import com.facebook.soloader.BackupSoSource;
import com.facebook.soloader.LogUtil;
import com.facebook.soloader.SoLoaderULError;
import com.facebook.soloader.SoSource;
import com.facebook.soloader.UnpackingSoSource;

public class CheckOnDiskStateDataData implements RecoveryStrategy {
    public boolean recover(UnsatisfiedLinkError unsatisfiedLinkError, SoSource[] soSourceArr) {
        if (!(unsatisfiedLinkError instanceof SoLoaderULError)) {
            return false;
        }
        LogUtil.e("SoLoader", "Checking /data/data missing libraries.");
        boolean z = false;
        for (UnpackingSoSource unpackingSoSource : soSourceArr) {
            if ((unpackingSoSource instanceof UnpackingSoSource) && !(unpackingSoSource instanceof BackupSoSource)) {
                UnpackingSoSource unpackingSoSource2 = unpackingSoSource;
                try {
                    UnpackingSoSource.Dso[] dsosBaseApk = unpackingSoSource2.getDsosBaseApk();
                    int length = dsosBaseApk.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        UnpackingSoSource.Dso dso = dsosBaseApk[i];
                        if (unpackingSoSource2.getSoFileByName(dso.name) == null) {
                            LogUtil.e("SoLoader", "Missing " + dso.name + " from " + unpackingSoSource2.getName() + ", will force prepare.");
                            unpackingSoSource2.prepare(2);
                            z = true;
                            break;
                        }
                        i++;
                    }
                } catch (Exception e) {
                    LogUtil.e("SoLoader", "Encountered an exception while recovering from /data/data failure ", e);
                    return false;
                }
            }
        }
        if (z) {
            LogUtil.e("SoLoader", "Successfully recovered from /data/data disk failure.");
            return true;
        }
        LogUtil.e("SoLoader", "No libraries missing from unpacking so paths while recovering /data/data failure");
        return false;
    }
}
