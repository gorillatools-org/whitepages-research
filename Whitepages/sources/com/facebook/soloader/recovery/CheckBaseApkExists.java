package com.facebook.soloader.recovery;

import android.content.Context;
import com.facebook.soloader.LogUtil;
import com.facebook.soloader.NoBaseApkException;
import com.facebook.soloader.SoSource;
import java.io.File;

public class CheckBaseApkExists implements RecoveryStrategy {
    private final BaseApkPathHistory mBaseApkPathHistory;
    private final Context mContext;

    public CheckBaseApkExists(Context context, BaseApkPathHistory baseApkPathHistory) {
        this.mContext = context;
        this.mBaseApkPathHistory = baseApkPathHistory;
    }

    public boolean recover(UnsatisfiedLinkError unsatisfiedLinkError, SoSource[] soSourceArr) {
        String str = this.mContext.getApplicationInfo().sourceDir;
        if (new File(str).exists()) {
            LogUtil.w("soloader.recovery.CheckBaseApkExists", "Base apk exists: " + str);
            return false;
        }
        StringBuilder sb = new StringBuilder("Base apk does not exist: ");
        sb.append(str);
        sb.append(". ");
        this.mBaseApkPathHistory.report(sb);
        throw new NoBaseApkException(sb.toString(), unsatisfiedLinkError);
    }
}
