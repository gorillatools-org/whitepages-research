package n.o.t.i.f.e.e;

import android.graphics.Color;
import android.os.Bundle;

public class c {
    public final Bundle a;

    public c(Bundle bundle) {
        this.a = bundle;
    }

    public Integer a() {
        if (this.a.containsKey("lightColor")) {
            return Integer.valueOf(Color.parseColor(this.a.getString("lightColor")));
        }
        return null;
    }
}
