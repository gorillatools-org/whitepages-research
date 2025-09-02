package app.notifee.core.model;

import android.os.Bundle;
import com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager;
import java.util.concurrent.TimeUnit;
import n.o.t.i.f.e.e.l;

public class a {
    public Bundle a;
    public int b = -1;
    public TimeUnit c = null;
    public Boolean d = Boolean.FALSE;
    public C0000a e;
    public String f;
    public Long g;

    /* renamed from: app.notifee.core.model.a$a  reason: collision with other inner class name */
    public enum C0000a {
        SET,
        SET_AND_ALLOW_WHILE_IDLE,
        SET_EXACT,
        SET_EXACT_AND_ALLOW_WHILE_IDLE,
        SET_ALARM_CLOCK
    }

    public a(Bundle bundle) {
        C0000a aVar = C0000a.SET_EXACT;
        this.e = aVar;
        this.f = null;
        this.g = null;
        this.a = bundle;
        int i = 2;
        if (bundle.containsKey("repeatFrequency")) {
            int a2 = l.a(this.a.get("repeatFrequency"));
            this.g = Long.valueOf(l.b(this.a.get("timestamp")));
            if (a2 == 0) {
                this.b = 1;
                this.c = TimeUnit.HOURS;
                this.f = "HOURLY";
            } else if (a2 == 1) {
                this.b = 1;
                this.c = TimeUnit.DAYS;
                this.f = "DAILY";
            } else if (a2 == 2) {
                this.b = 7;
                this.c = TimeUnit.DAYS;
                this.f = "WEEKLY";
            }
        }
        if (this.a.containsKey("alarmManager")) {
            this.d = Boolean.TRUE;
            Bundle bundle2 = this.a.getBundle("alarmManager");
            Object obj = bundle2.get("type");
            i = obj != null ? l.a(obj) : i;
            if (bundle2.containsKey("allowWhileIdle") && bundle2.getBoolean("allowWhileIdle")) {
                i = 3;
            }
            if (i == 0) {
                this.e = C0000a.SET;
            } else if (i == 1) {
                this.e = C0000a.SET_AND_ALLOW_WHILE_IDLE;
            } else if (i == 3) {
                this.e = C0000a.SET_EXACT_AND_ALLOW_WHILE_IDLE;
            } else if (i != 4) {
                this.e = aVar;
            } else {
                this.e = C0000a.SET_ALARM_CLOCK;
            }
        } else if (this.a.containsKey("allowWhileIdle")) {
            this.d = Boolean.TRUE;
            this.e = C0000a.SET_EXACT_AND_ALLOW_WHILE_IDLE;
        }
    }

    public void a() {
        long j;
        if (this.f != null) {
            long longValue = this.g.longValue();
            String str = this.f;
            str.getClass();
            char c2 = 65535;
            switch (str.hashCode()) {
                case -1738378111:
                    if (str.equals("WEEKLY")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 64808441:
                    if (str.equals("DAILY")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 2136870513:
                    if (str.equals("HOURLY")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    j = 604800000;
                    break;
                case 1:
                    j = NetworkManager.MAX_SERVER_RETRY;
                    break;
                case 2:
                    j = 3600000;
                    break;
                default:
                    j = 0;
                    break;
            }
            while (longValue < System.currentTimeMillis()) {
                longValue += j;
            }
            this.g = Long.valueOf(longValue);
        }
    }
}
