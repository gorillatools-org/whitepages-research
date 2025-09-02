package n.o.t.i.f.e.e;

import android.content.Context;
import app.notifee.core.Logger;

public abstract class e {
    public static Context a;

    public static void a(Context context) {
        Logger.d("context", "received application context");
        a = context;
    }
}
