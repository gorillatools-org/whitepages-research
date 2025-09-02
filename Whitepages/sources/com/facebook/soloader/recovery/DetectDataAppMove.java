package com.facebook.soloader.recovery;

import android.content.Context;
import com.facebook.soloader.LogUtil;
import com.facebook.soloader.RecoverableSoSource;
import com.facebook.soloader.SoSource;
import java.io.File;

public class DetectDataAppMove implements RecoveryStrategy {
    private final BaseApkPathHistory mBaseApkPathHistory;
    private final Context mContext;
    private final int mInitialHistorySize;

    public DetectDataAppMove(Context context, BaseApkPathHistory baseApkPathHistory) {
        this.mContext = context;
        this.mBaseApkPathHistory = baseApkPathHistory;
        this.mInitialHistorySize = baseApkPathHistory.size();
    }

    public boolean recover(UnsatisfiedLinkError unsatisfiedLinkError, SoSource[] soSourceArr) {
        if (detectMove()) {
            recoverSoSources(soSourceArr);
            return true;
        } else if (this.mInitialHistorySize == this.mBaseApkPathHistory.size()) {
            return false;
        } else {
            LogUtil.w("soloader.recovery.DetectDataAppMove", "Context was updated (perhaps by another thread)");
            return true;
        }
    }

    private boolean detectMove() {
        String baseApkPath = getBaseApkPath();
        return new File(baseApkPath).exists() && this.mBaseApkPathHistory.recordPathIfNew(baseApkPath);
    }

    private void recoverSoSources(SoSource[] soSourceArr) {
        for (int i = 0; i < soSourceArr.length; i++) {
            SoSource soSource = soSourceArr[i];
            if (soSource instanceof RecoverableSoSource) {
                soSourceArr[i] = ((RecoverableSoSource) soSource).recover(this.mContext);
            }
        }
    }

    private String getBaseApkPath() {
        return this.mContext.getApplicationInfo().sourceDir;
    }
}
