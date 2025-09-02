package io.invertase.notifee;

import android.os.Bundle;
import androidx.annotation.Keep;
import app.notifee.core.event.BlockStateEvent;
import app.notifee.core.event.ForegroundServiceEvent;
import app.notifee.core.event.LogEvent;
import app.notifee.core.event.NotificationEvent;
import app.notifee.core.interfaces.EventListener;
import app.notifee.core.model.NotificationModel;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import io.invertase.notifee.NotifeeReactUtils;

@Keep
public class NotifeeEventSubscriber implements EventListener {
    static final String FOREGROUND_NOTIFICATION_TASK_KEY = "app.notifee.foreground-service-headless-task";
    private static final String KEY_BLOCKED = "blocked";
    private static final String KEY_DETAIL = "detail";
    private static final String KEY_DETAIL_INPUT = "input";
    private static final String KEY_DETAIL_PRESS_ACTION = "pressAction";
    private static final String KEY_HEADLESS = "headless";
    private static final String KEY_NOTIFICATION = "notification";
    private static final String KEY_TYPE = "type";
    static final String NOTIFICATION_EVENT_KEY = "app.notifee.notification-event";

    public void onLogEvent(LogEvent logEvent) {
    }

    public void onNotificationEvent(NotificationEvent notificationEvent) {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap.putInt(KEY_TYPE, notificationEvent.getType());
        createMap2.putMap(KEY_NOTIFICATION, Arguments.fromBundle(notificationEvent.getNotification().toBundle()));
        Bundle extras = notificationEvent.getExtras();
        if (extras != null) {
            Bundle bundle = extras.getBundle(KEY_DETAIL_PRESS_ACTION);
            if (bundle != null) {
                createMap2.putMap(KEY_DETAIL_PRESS_ACTION, Arguments.fromBundle(bundle));
            }
            String string = extras.getString(KEY_DETAIL_INPUT);
            if (string != null) {
                createMap2.putString(KEY_DETAIL_INPUT, string);
            }
        }
        createMap.putMap(KEY_DETAIL, createMap2);
        if (NotifeeReactUtils.isAppInForeground()) {
            createMap.putBoolean(KEY_HEADLESS, false);
            NotifeeReactUtils.sendEvent(NOTIFICATION_EVENT_KEY, createMap);
            return;
        }
        createMap.putBoolean(KEY_HEADLESS, true);
        NotifeeReactUtils.startHeadlessTask(NOTIFICATION_EVENT_KEY, createMap, 60000, (NotifeeReactUtils.GenericCallback) null);
    }

    public void onBlockStateEvent(BlockStateEvent blockStateEvent) {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap.putInt(KEY_TYPE, blockStateEvent.getType());
        int type = blockStateEvent.getType();
        if (type == 5 || type == 6) {
            String str = type == 5 ? "channel" : "channelGroup";
            if (blockStateEvent.getChannelOrGroupBundle() != null) {
                createMap2.putMap(str, Arguments.fromBundle(blockStateEvent.getChannelOrGroupBundle()));
            }
        }
        if (type == 4) {
            createMap2.putBoolean(KEY_BLOCKED, blockStateEvent.isBlocked());
        }
        createMap.putMap(KEY_DETAIL, createMap2);
        if (NotifeeReactUtils.isAppInForeground()) {
            createMap.putBoolean(KEY_HEADLESS, false);
            NotifeeReactUtils.sendEvent(NOTIFICATION_EVENT_KEY, createMap);
            return;
        }
        createMap.putBoolean(KEY_HEADLESS, true);
        NotifeeReactUtils.startHeadlessTask(NOTIFICATION_EVENT_KEY, createMap, 0, new NotifeeEventSubscriber$$ExternalSyntheticLambda0(blockStateEvent));
    }

    public void onForegroundServiceEvent(ForegroundServiceEvent foregroundServiceEvent) {
        NotificationModel notification = foregroundServiceEvent.getNotification();
        WritableMap createMap = Arguments.createMap();
        createMap.putMap(KEY_NOTIFICATION, Arguments.fromBundle(notification.toBundle()));
        NotifeeReactUtils.startHeadlessTask(FOREGROUND_NOTIFICATION_TASK_KEY, createMap, 0, new NotifeeEventSubscriber$$ExternalSyntheticLambda1(foregroundServiceEvent));
    }
}
