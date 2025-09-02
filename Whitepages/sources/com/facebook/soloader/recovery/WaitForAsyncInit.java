package com.facebook.soloader.recovery;

import com.facebook.soloader.AsyncInitSoSource;
import com.facebook.soloader.LogUtil;
import com.facebook.soloader.SoSource;

public class WaitForAsyncInit implements RecoveryStrategy {
    public boolean recover(UnsatisfiedLinkError unsatisfiedLinkError, SoSource[] soSourceArr) {
        for (SoSource soSource : soSourceArr) {
            if (soSource instanceof AsyncInitSoSource) {
                LogUtil.e("SoLoader", "Waiting on SoSource " + soSource.getName());
                ((AsyncInitSoSource) soSource).waitUntilInitCompleted();
            }
        }
        return true;
    }
}
