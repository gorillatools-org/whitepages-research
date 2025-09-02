package app.notifee.core;

import android.content.Context;
import app.notifee.core.event.BlockStateEvent;
import app.notifee.core.event.ForegroundServiceEvent;
import app.notifee.core.event.LogEvent;
import app.notifee.core.event.NotificationEvent;
import app.notifee.core.interfaces.EventListener;
import java.util.HashSet;
import java.util.Set;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.f;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@KeepForSdk
public class EventSubscriber {
    public static final EventSubscriber b = new EventSubscriber();
    public final Set a = new HashSet();

    private EventSubscriber() {
        f.c(this);
    }

    @KeepForSdk
    public static Context getContext() {
        return e.a;
    }

    @KeepForSdk
    public static void register(EventListener eventListener) {
        b.a.add(eventListener);
    }

    @KeepForSdk
    public static void unregister(EventListener eventListener) {
        b.a.remove(eventListener);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBlockStateEvent(BlockStateEvent blockStateEvent) {
        for (EventListener onBlockStateEvent : this.a) {
            onBlockStateEvent.onBlockStateEvent(blockStateEvent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onForegroundServiceEvent(ForegroundServiceEvent foregroundServiceEvent) {
        for (EventListener onForegroundServiceEvent : this.a) {
            onForegroundServiceEvent.onForegroundServiceEvent(foregroundServiceEvent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogEvent(LogEvent logEvent) {
        for (EventListener onLogEvent : this.a) {
            onLogEvent.onLogEvent(logEvent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNotificationEvent(NotificationEvent notificationEvent) {
        for (EventListener onNotificationEvent : this.a) {
            onNotificationEvent.onNotificationEvent(notificationEvent);
        }
    }
}
