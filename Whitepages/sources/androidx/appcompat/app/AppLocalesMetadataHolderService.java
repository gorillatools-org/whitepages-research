package androidx.appcompat.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ServiceInfo;

public abstract class AppLocalesMetadataHolderService extends Service {

    private static class Api24Impl {
        static int getDisabledComponentFlag() {
            return 512;
        }
    }

    public static ServiceInfo getServiceInfo(Context context) {
        return context.getPackageManager().getServiceInfo(new ComponentName(context, AppLocalesMetadataHolderService.class), Api24Impl.getDisabledComponentFlag() | 128);
    }
}
