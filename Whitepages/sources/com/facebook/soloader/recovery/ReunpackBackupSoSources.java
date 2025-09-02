package com.facebook.soloader.recovery;

import com.facebook.soloader.BackupSoSource;
import com.facebook.soloader.DirectorySoSource;
import com.facebook.soloader.LogUtil;
import com.facebook.soloader.SoLoaderDSONotFoundError;
import com.facebook.soloader.SoLoaderULError;
import com.facebook.soloader.SoSource;
import java.io.IOException;

public class ReunpackBackupSoSources implements RecoveryStrategy {
    private int mRecoveryFlags;

    public ReunpackBackupSoSources(int i) {
        this.mRecoveryFlags = i;
    }

    public boolean recover(UnsatisfiedLinkError unsatisfiedLinkError, SoSource[] soSourceArr) {
        if (!(unsatisfiedLinkError instanceof SoLoaderULError)) {
            return false;
        }
        SoLoaderULError soLoaderULError = (SoLoaderULError) unsatisfiedLinkError;
        String soName = soLoaderULError.getSoName();
        String message = soLoaderULError.getMessage();
        if (soName == null) {
            LogUtil.e("SoLoader", "No so name provided in ULE, cannot recover");
            return false;
        } else if (soLoaderULError instanceof SoLoaderDSONotFoundError) {
            if ((this.mRecoveryFlags & 1) == 0) {
                return false;
            }
            logRecovery(soLoaderULError, soName);
            return recoverDSONotFoundError(soSourceArr, soName, 0);
        } else if (message == null || (!message.contains("/app/") && !message.contains("/mnt/"))) {
            return false;
        } else {
            logRecovery(soLoaderULError, soName);
            return lazyPrepareBackupSoSource(soSourceArr, soName);
        }
    }

    private boolean recoverDSONotFoundError(SoSource[] soSourceArr, String str, int i) {
        try {
            for (BackupSoSource backupSoSource : soSourceArr) {
                if (backupSoSource instanceof BackupSoSource) {
                    if (backupSoSource.peekAndPrepareSoSource(str, i)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            LogUtil.e("SoLoader", "Failed to run recovery for backup so source due to: " + e);
            return false;
        }
    }

    private boolean lazyPrepareBackupSoSource(SoSource[] soSourceArr, String str) {
        int length = soSourceArr.length;
        int i = 0;
        while (i < length) {
            BackupSoSource backupSoSource = soSourceArr[i];
            if (!(backupSoSource instanceof BackupSoSource)) {
                i++;
            } else {
                BackupSoSource backupSoSource2 = backupSoSource;
                try {
                    LogUtil.e("SoLoader", "Preparing BackupSoSource for the first time " + backupSoSource2.getName());
                    backupSoSource2.prepare(0);
                    for (DirectorySoSource directorySoSource : soSourceArr) {
                        if ((directorySoSource instanceof DirectorySoSource) && !(directorySoSource instanceof BackupSoSource)) {
                            directorySoSource.setExplicitDependencyResolution();
                        }
                    }
                    return true;
                } catch (Exception e) {
                    LogUtil.e("SoLoader", "Encountered an exception while reunpacking BackupSoSource " + backupSoSource2.getName() + " for library " + str + ": ", e);
                }
            }
        }
        return false;
    }

    private void logRecovery(Error error, String str) {
        LogUtil.e("SoLoader", "Reunpacking BackupSoSources due to " + error + ", retrying for specific library " + str);
    }
}
