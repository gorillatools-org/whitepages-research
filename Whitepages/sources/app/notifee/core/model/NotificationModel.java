package app.notifee.core.model;

import android.os.Bundle;
import app.notifee.core.KeepForSdk;
import java.util.Objects;

@KeepForSdk
public class NotificationModel {
    public Bundle a;

    public NotificationModel(Bundle bundle) {
        this.a = bundle;
    }

    public NotificationAndroidModel a() {
        return NotificationAndroidModel.fromBundle(this.a.getBundle("android"));
    }

    public Integer b() {
        return Integer.valueOf(c().hashCode());
    }

    public String c() {
        String string = this.a.getString("id");
        Objects.requireNonNull(string);
        return string;
    }

    @KeepForSdk
    public Bundle toBundle() {
        return (Bundle) this.a.clone();
    }
}
