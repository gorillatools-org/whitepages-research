package n.o.t.i.f.e.e;

import org.greenrobot.eventbus.EventBus;

public class f {
    public static final f b = new f();
    public EventBus a = EventBus.builder().build();

    public static void a(Object obj) {
        b.a.post(obj);
    }

    public static void b(Object obj) {
        b.a.postSticky(obj);
    }

    public static void c(Object obj) {
        b.a.register(obj);
    }
}
