package com.facebook.soloader.recovery;

import com.facebook.soloader.BackupSoSource;
import com.facebook.soloader.LogUtil;
import com.facebook.soloader.SoLoaderDSONotFoundError;
import com.facebook.soloader.SoLoaderULError;
import com.facebook.soloader.SoSource;
import com.facebook.soloader.UnpackingSoSource;

public class ReunpackNonBackupSoSources implements RecoveryStrategy {
    public boolean recover(UnsatisfiedLinkError unsatisfiedLinkError, SoSource[] soSourceArr) {
        String str;
        if (!(unsatisfiedLinkError instanceof SoLoaderULError) || (unsatisfiedLinkError instanceof SoLoaderDSONotFoundError)) {
            return false;
        }
        String soName = ((SoLoaderULError) unsatisfiedLinkError).getSoName();
        StringBuilder sb = new StringBuilder();
        sb.append("Reunpacking NonApk UnpackingSoSources due to ");
        sb.append(unsatisfiedLinkError);
        if (soName == null) {
            str = "";
        } else {
            str = ", retrying for specific library " + soName;
        }
        sb.append(str);
        LogUtil.e("SoLoader", sb.toString());
        for (UnpackingSoSource unpackingSoSource : soSourceArr) {
            if (unpackingSoSource instanceof UnpackingSoSource) {
                UnpackingSoSource unpackingSoSource2 = unpackingSoSource;
                if (!(unpackingSoSource2 instanceof BackupSoSource)) {
                    try {
                        LogUtil.e("SoLoader", "Runpacking " + unpackingSoSource2.getName());
                        unpackingSoSource2.prepare(2);
                    } catch (Exception e) {
                        LogUtil.e("SoLoader", "Encountered an exception while reunpacking " + unpackingSoSource2.getName() + " for library " + soName + ": ", e);
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
