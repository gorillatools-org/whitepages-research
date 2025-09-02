package app.notifee.core.interfaces;

import app.notifee.core.KeepForSdk;
import app.notifee.core.event.BlockStateEvent;
import app.notifee.core.event.ForegroundServiceEvent;
import app.notifee.core.event.LogEvent;
import app.notifee.core.event.NotificationEvent;

@KeepForSdk
public interface EventListener {
    @KeepForSdk
    void onBlockStateEvent(BlockStateEvent blockStateEvent);

    @KeepForSdk
    void onForegroundServiceEvent(ForegroundServiceEvent foregroundServiceEvent);

    @KeepForSdk
    void onLogEvent(LogEvent logEvent);

    @KeepForSdk
    void onNotificationEvent(NotificationEvent notificationEvent);
}
