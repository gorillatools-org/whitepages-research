package io.invertase.notifee;

import android.os.Build;
import app.notifee.core.Logger;
import app.notifee.core.Notifee;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotifeeApiModule extends ReactContextBaseJavaModule implements PermissionListener {
    private static final int NOTIFICATION_TYPE_ALL = 0;
    private static final int NOTIFICATION_TYPE_DISPLAYED = 1;
    private static final int NOTIFICATION_TYPE_TRIGGER = 2;

    @ReactMethod
    public void addListener(String str) {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public NotifeeApiModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public static String getMainComponent(String str) {
        return Notifee.getInstance().getMainComponent(str);
    }

    public void onCatalystInstanceDestroy() {
        NotifeeReactUtils.clearRunningHeadlessTasks();
    }

    @ReactMethod
    public void cancelAllNotifications(Promise promise) {
        Notifee.getInstance().cancelAllNotifications(0, new NotifeeApiModule$$ExternalSyntheticLambda21(promise));
    }

    @ReactMethod
    public void cancelDisplayedNotifications(Promise promise) {
        Notifee.getInstance().cancelAllNotifications(1, new NotifeeApiModule$$ExternalSyntheticLambda9(promise));
    }

    @ReactMethod
    public void cancelTriggerNotifications(Promise promise) {
        Notifee.getInstance().cancelAllNotifications(2, new NotifeeApiModule$$ExternalSyntheticLambda28(promise));
    }

    @ReactMethod
    public void cancelAllNotificationsWithIds(ReadableArray readableArray, int i, String str, Promise promise) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            arrayList.add(readableArray.getString(i2));
        }
        Notifee.getInstance().cancelAllNotificationsWithIds(i, arrayList, str, new NotifeeApiModule$$ExternalSyntheticLambda8(promise));
    }

    @ReactMethod
    public void getDisplayedNotifications(Promise promise) {
        Notifee.getInstance().getDisplayedNotifications(new NotifeeApiModule$$ExternalSyntheticLambda6(promise));
    }

    @ReactMethod
    public void getTriggerNotifications(Promise promise) {
        Notifee.getInstance().getTriggerNotifications(new NotifeeApiModule$$ExternalSyntheticLambda30(promise));
    }

    @ReactMethod
    public void getTriggerNotificationIds(Promise promise) {
        Notifee.getInstance().getTriggerNotificationIds(new NotifeeApiModule$$ExternalSyntheticLambda18(promise));
    }

    @ReactMethod
    public void createChannel(ReadableMap readableMap, Promise promise) {
        Notifee.getInstance().createChannel(Arguments.toBundle(readableMap), new NotifeeApiModule$$ExternalSyntheticLambda14(promise));
    }

    @ReactMethod
    public void createChannels(ReadableArray readableArray, Promise promise) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(Arguments.toBundle(readableArray.getMap(i)));
        }
        Notifee.getInstance().createChannels(arrayList, new NotifeeApiModule$$ExternalSyntheticLambda11(promise));
    }

    @ReactMethod
    public void createChannelGroup(ReadableMap readableMap, Promise promise) {
        Notifee.getInstance().createChannelGroup(Arguments.toBundle(readableMap), new NotifeeApiModule$$ExternalSyntheticLambda10(promise));
    }

    @ReactMethod
    public void createChannelGroups(ReadableArray readableArray, Promise promise) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(Arguments.toBundle(readableArray.getMap(i)));
        }
        Notifee.getInstance().createChannelGroups(arrayList, new NotifeeApiModule$$ExternalSyntheticLambda0(promise));
    }

    @ReactMethod
    public void deleteChannel(String str, Promise promise) {
        Notifee.getInstance().deleteChannel(str, new NotifeeApiModule$$ExternalSyntheticLambda31(promise));
    }

    @ReactMethod
    public void deleteChannelGroup(String str, Promise promise) {
        Notifee.getInstance().deleteChannelGroup(str, new NotifeeApiModule$$ExternalSyntheticLambda15(promise));
    }

    @ReactMethod
    public void displayNotification(ReadableMap readableMap, Promise promise) {
        Notifee.getInstance().displayNotification(Arguments.toBundle(readableMap), new NotifeeApiModule$$ExternalSyntheticLambda5(promise));
    }

    @ReactMethod
    public void openAlarmPermissionSettings(Promise promise) {
        Notifee.getInstance().openAlarmPermissionSettings(getCurrentActivity(), new NotifeeApiModule$$ExternalSyntheticLambda17(promise));
    }

    @ReactMethod
    public void createTriggerNotification(ReadableMap readableMap, ReadableMap readableMap2, Promise promise) {
        Notifee.getInstance().createTriggerNotification(Arguments.toBundle(readableMap), Arguments.toBundle(readableMap2), new NotifeeApiModule$$ExternalSyntheticLambda26(promise));
    }

    @ReactMethod
    public void getChannels(Promise promise) {
        Notifee.getInstance().getChannels(new NotifeeApiModule$$ExternalSyntheticLambda19(promise));
    }

    @ReactMethod
    public void getChannel(String str, Promise promise) {
        Notifee.getInstance().getChannel(str, new NotifeeApiModule$$ExternalSyntheticLambda3(promise));
    }

    @ReactMethod
    public void getChannelGroups(Promise promise) {
        Notifee.getInstance().getChannelGroups(new NotifeeApiModule$$ExternalSyntheticLambda13(promise));
    }

    @ReactMethod
    public void getChannelGroup(String str, Promise promise) {
        Notifee.getInstance().getChannel(str, new NotifeeApiModule$$ExternalSyntheticLambda32(promise));
    }

    @ReactMethod
    public void isChannelCreated(String str, Promise promise) {
        Notifee.getInstance().isChannelCreated(str, new NotifeeApiModule$$ExternalSyntheticLambda22(promise));
    }

    @ReactMethod
    public void isChannelBlocked(String str, Promise promise) {
        Notifee.getInstance().isChannelBlocked(str, new NotifeeApiModule$$ExternalSyntheticLambda2(promise));
    }

    @ReactMethod
    public void getInitialNotification(Promise promise) {
        Notifee.getInstance().getInitialNotification(getCurrentActivity(), new NotifeeApiModule$$ExternalSyntheticLambda12(promise));
    }

    @ReactMethod
    public void getNotificationSettings(Promise promise) {
        Notifee.getInstance().getNotificationSettings(new NotifeeApiModule$$ExternalSyntheticLambda7(promise));
    }

    @ReactMethod
    public void requestPermission(Promise promise) {
        int i = Build.VERSION.SDK_INT;
        if (i < 33) {
            Notifee.getInstance().getNotificationSettings(new NotifeeApiModule$$ExternalSyntheticLambda23(promise));
            return;
        }
        PermissionAwareActivity permissionAwareActivity = (PermissionAwareActivity) getCurrentActivity();
        if (permissionAwareActivity == null) {
            Logger.d("requestPermission", "Unable to get permissionAwareActivity for " + i);
            Notifee.getInstance().getNotificationSettings(new NotifeeApiModule$$ExternalSyntheticLambda24(promise));
            return;
        }
        Notifee.getInstance().setRequestPermissionCallback(new NotifeeApiModule$$ExternalSyntheticLambda25(promise));
        permissionAwareActivity.requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, Notifee.REQUEST_CODE_NOTIFICATION_PERMISSION, this);
    }

    @ReactMethod
    public void openNotificationSettings(String str, Promise promise) {
        Notifee.getInstance().openNotificationSettings(str, getCurrentActivity(), new NotifeeApiModule$$ExternalSyntheticLambda29(promise));
    }

    @ReactMethod
    public void openBatteryOptimizationSettings(Promise promise) {
        Notifee.getInstance().openBatteryOptimizationSettings(getCurrentActivity(), new NotifeeApiModule$$ExternalSyntheticLambda16(promise));
    }

    @ReactMethod
    public void isBatteryOptimizationEnabled(Promise promise) {
        Notifee.getInstance().isBatteryOptimizationEnabled(new NotifeeApiModule$$ExternalSyntheticLambda20(promise));
    }

    @ReactMethod
    public void getPowerManagerInfo(Promise promise) {
        Notifee.getInstance().getPowerManagerInfo(new NotifeeApiModule$$ExternalSyntheticLambda4(promise));
    }

    @ReactMethod
    public void openPowerManagerSettings(Promise promise) {
        Notifee.getInstance().openPowerManagerSettings(getCurrentActivity(), new NotifeeApiModule$$ExternalSyntheticLambda27(promise));
    }

    @ReactMethod
    public void stopForegroundService(Promise promise) {
        Notifee.getInstance().stopForegroundService(new NotifeeApiModule$$ExternalSyntheticLambda1(promise));
    }

    @ReactMethod
    public void hideNotificationDrawer() {
        NotifeeReactUtils.hideNotificationDrawer();
    }

    public String getName() {
        return "NotifeeApiModule";
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("ANDROID_API_LEVEL", Integer.valueOf(Build.VERSION.SDK_INT));
        return hashMap;
    }

    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        return Notifee.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }
}
