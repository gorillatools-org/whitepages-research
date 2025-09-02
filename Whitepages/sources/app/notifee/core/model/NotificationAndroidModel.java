package app.notifee.core.model;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Keep;
import app.notifee.core.Logger;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import n.o.t.i.f.e.e.l;
import n.o.t.i.f.e.e.n;

@Keep
public class NotificationAndroidModel {
    private static final String TAG = "NotificationAndroidModel";
    private Bundle mNotificationAndroidBundle;

    public static class a {
        public int a;
        public int b;
        public boolean c;

        public a(int i, int i2, boolean z) {
            this.a = i;
            this.b = i2;
            this.c = z;
        }
    }

    private NotificationAndroidModel(Bundle bundle) {
        this.mNotificationAndroidBundle = bundle;
    }

    public static NotificationAndroidModel fromBundle(Bundle bundle) {
        return new NotificationAndroidModel(bundle);
    }

    public ArrayList<NotificationAndroidActionModel> getActions() {
        if (!this.mNotificationAndroidBundle.containsKey("actions")) {
            return null;
        }
        ArrayList parcelableArrayList = this.mNotificationAndroidBundle.getParcelableArrayList("actions");
        Objects.requireNonNull(parcelableArrayList);
        ArrayList<NotificationAndroidActionModel> arrayList = new ArrayList<>(parcelableArrayList.size());
        Iterator it = parcelableArrayList.iterator();
        while (it.hasNext()) {
            arrayList.add(NotificationAndroidActionModel.fromBundle((Bundle) it.next()));
        }
        return arrayList;
    }

    public Boolean getAsForegroundService() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("asForegroundService", false));
    }

    public Boolean getAutoCancel() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("autoCancel", false));
    }

    public Integer getBadgeIconType() {
        if (this.mNotificationAndroidBundle.containsKey("badgeIconType")) {
            return Integer.valueOf(l.a(this.mNotificationAndroidBundle.get("badgeIconType")));
        }
        return 2;
    }

    public String getCategory() {
        return this.mNotificationAndroidBundle.getString("category");
    }

    public String getChannelId() {
        if (!this.mNotificationAndroidBundle.containsKey("channelId")) {
            return "";
        }
        String string = this.mNotificationAndroidBundle.getString("channelId");
        Objects.requireNonNull(string);
        return string;
    }

    public Boolean getChronometerCountDown() {
        if (!this.mNotificationAndroidBundle.containsKey("chronometerDirection")) {
            return Boolean.FALSE;
        }
        String string = this.mNotificationAndroidBundle.getString("chronometerDirection");
        return Boolean.valueOf(string != null && string.equals("down"));
    }

    public Boolean getCircularLargeIcon() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("circularLargeIcon", false));
    }

    public Integer getColor() {
        if (this.mNotificationAndroidBundle.containsKey(ViewProps.COLOR)) {
            return Integer.valueOf(Color.parseColor(this.mNotificationAndroidBundle.getString(ViewProps.COLOR)));
        }
        return null;
    }

    public Boolean getColorized() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("colorized", false));
    }

    public Integer getDefaults(Boolean bool) {
        Integer num;
        if (this.mNotificationAndroidBundle.containsKey(RemoteConfigComponent.DEFAULTS_FILE_NAME)) {
            ArrayList<Integer> integerArrayList = this.mNotificationAndroidBundle.getIntegerArrayList(RemoteConfigComponent.DEFAULTS_FILE_NAME);
            Objects.requireNonNull(integerArrayList);
            Iterator<Integer> it = integerArrayList.iterator();
            num = null;
            while (it.hasNext()) {
                Integer next = it.next();
                if (num == null) {
                    num = next;
                } else {
                    num = Integer.valueOf(num.intValue() | next.intValue());
                }
            }
        } else {
            num = -1;
        }
        if (bool.booleanValue()) {
            num = Integer.valueOf(num.intValue() & -2);
        }
        if (!this.mNotificationAndroidBundle.containsKey("vibrationPattern")) {
            num = Integer.valueOf(num.intValue() & -3);
        }
        return this.mNotificationAndroidBundle.containsKey("lights") ? Integer.valueOf(num.intValue() & -5) : num;
    }

    public int[] getFlags() {
        if (!this.mNotificationAndroidBundle.containsKey("flags")) {
            return null;
        }
        ArrayList parcelableArrayList = this.mNotificationAndroidBundle.getParcelableArrayList("flags");
        Objects.requireNonNull(parcelableArrayList);
        int[] iArr = new int[parcelableArrayList.size()];
        for (int i = 0; i < parcelableArrayList.size(); i++) {
            iArr[i] = l.a(parcelableArrayList.get(i));
        }
        return iArr;
    }

    public NotificationAndroidPressActionModel getFullScreenAction() {
        if (!hasFullScreenAction().booleanValue()) {
            return null;
        }
        return NotificationAndroidPressActionModel.fromBundle(this.mNotificationAndroidBundle.getBundle("fullScreenAction"));
    }

    public String getGroup() {
        return this.mNotificationAndroidBundle.getString("groupId");
    }

    public int getGroupAlertBehaviour() {
        if (this.mNotificationAndroidBundle.containsKey("groupAlertBehavior")) {
            return l.a(this.mNotificationAndroidBundle.get("groupAlertBehavior"));
        }
        return 0;
    }

    public Boolean getGroupSummary() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("groupSummary", false));
    }

    public CharSequence[] getInputHistory() {
        if (!this.mNotificationAndroidBundle.containsKey("inputHistory")) {
            return null;
        }
        ArrayList<String> stringArrayList = this.mNotificationAndroidBundle.getStringArrayList("inputHistory");
        Objects.requireNonNull(stringArrayList);
        return (CharSequence[]) stringArrayList.toArray(new CharSequence[stringArrayList.size()]);
    }

    public String getLargeIcon() {
        if (!hasLargeIcon().booleanValue()) {
            return null;
        }
        String string = this.mNotificationAndroidBundle.getString("largeIcon");
        Objects.requireNonNull(string);
        return string;
    }

    public Boolean getLightUpScreen() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("lightUpScreen", false));
    }

    public ArrayList<Integer> getLights() {
        if (this.mNotificationAndroidBundle.containsKey("lights")) {
            try {
                ArrayList parcelableArrayList = this.mNotificationAndroidBundle.getParcelableArrayList("lights");
                Objects.requireNonNull(parcelableArrayList);
                ArrayList<Integer> arrayList = new ArrayList<>(3);
                arrayList.add(Integer.valueOf(Color.parseColor((String) parcelableArrayList.get(0))));
                arrayList.add((Integer) parcelableArrayList.get(1));
                arrayList.add((Integer) parcelableArrayList.get(2));
                return arrayList;
            } catch (Exception unused) {
                Logger.e(TAG, "getLights -> Failed to parse lights");
            }
        }
        return null;
    }

    public Boolean getLocalOnly() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("localOnly", false));
    }

    public Boolean getLoopSound() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("loopSound", false));
    }

    public Integer getNumber() {
        if (this.mNotificationAndroidBundle.containsKey("badgeCount")) {
            return Integer.valueOf(l.a(this.mNotificationAndroidBundle.get("badgeCount")));
        }
        return null;
    }

    public Boolean getOngoing() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("ongoing", false));
    }

    public Boolean getOnlyAlertOnce() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("onlyAlertOnce", false));
    }

    public Bundle getPressAction() {
        return this.mNotificationAndroidBundle.getBundle("pressAction");
    }

    public int getPriority() {
        if (!this.mNotificationAndroidBundle.containsKey("importance")) {
            return 0;
        }
        int a2 = l.a(this.mNotificationAndroidBundle.get("importance"));
        if (a2 == 0) {
            return -2;
        }
        if (a2 == 1) {
            return -1;
        }
        if (a2 != 4) {
            return 0;
        }
        return 1;
    }

    public a getProgress() {
        if (!this.mNotificationAndroidBundle.containsKey(ReactProgressBarViewManager.PROP_PROGRESS)) {
            return null;
        }
        Bundle bundle = this.mNotificationAndroidBundle.getBundle(ReactProgressBarViewManager.PROP_PROGRESS);
        Objects.requireNonNull(bundle);
        return new a(l.a(bundle.get("max")), l.a(bundle.get("current")), bundle.getBoolean(ReactProgressBarViewManager.PROP_INDETERMINATE, false));
    }

    public String getShortcutId() {
        return this.mNotificationAndroidBundle.getString("shortcutId");
    }

    public Boolean getShowChronometer() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("showChronometer", false));
    }

    public Boolean getShowTimestamp() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.getBoolean("showTimestamp", false));
    }

    public Integer getSmallIcon() {
        if (!this.mNotificationAndroidBundle.containsKey("smallIcon")) {
            return null;
        }
        String string = this.mNotificationAndroidBundle.getString("smallIcon");
        int a2 = n.a(string, "mipmap");
        if (a2 == 0) {
            a2 = n.a(string, "drawable");
        }
        if (a2 != 0) {
            return Integer.valueOf(a2);
        }
        Logger.d(TAG, String.format("Notification small icon '%s' could not be found", new Object[]{string}));
        return null;
    }

    public Integer getSmallIconLevel() {
        if (!this.mNotificationAndroidBundle.containsKey("smallIconLevel")) {
            return null;
        }
        return Integer.valueOf(this.mNotificationAndroidBundle.getInt("smallIconLevel"));
    }

    public String getSortKey() {
        return this.mNotificationAndroidBundle.getString("sortKey");
    }

    public String getSound() {
        if (!this.mNotificationAndroidBundle.containsKey("sound")) {
            return null;
        }
        return this.mNotificationAndroidBundle.getString("sound");
    }

    public NotificationAndroidStyleModel getStyle() {
        if (!hasStyle().booleanValue()) {
            return null;
        }
        return NotificationAndroidStyleModel.fromBundle(this.mNotificationAndroidBundle.getBundle("style"));
    }

    public String getTag() {
        return this.mNotificationAndroidBundle.getString("tag");
    }

    public String getTicker() {
        return this.mNotificationAndroidBundle.getString("ticker");
    }

    public Long getTimeoutAfter() {
        if (this.mNotificationAndroidBundle.containsKey("timeoutAfter")) {
            return Long.valueOf(l.b(this.mNotificationAndroidBundle.get("timeoutAfter")));
        }
        return null;
    }

    public long getTimestamp() {
        if (this.mNotificationAndroidBundle.containsKey("timestamp")) {
            return l.b(this.mNotificationAndroidBundle.get("timestamp"));
        }
        return -1;
    }

    public long[] getVibrationPattern() {
        if (!this.mNotificationAndroidBundle.containsKey("vibrationPattern")) {
            return new long[0];
        }
        ArrayList parcelableArrayList = this.mNotificationAndroidBundle.getParcelableArrayList("vibrationPattern");
        Objects.requireNonNull(parcelableArrayList);
        long[] jArr = new long[parcelableArrayList.size()];
        for (int i = 0; i < parcelableArrayList.size(); i++) {
            jArr[i] = ((Integer) parcelableArrayList.get(i)).longValue();
        }
        return jArr;
    }

    public int getVisibility() {
        if (this.mNotificationAndroidBundle.containsKey("visibility")) {
            return l.a(this.mNotificationAndroidBundle.get("visibility"));
        }
        return 0;
    }

    public Boolean hasFullScreenAction() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.containsKey("fullScreenAction"));
    }

    public Boolean hasLargeIcon() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.containsKey("largeIcon"));
    }

    public Boolean hasStyle() {
        return Boolean.valueOf(this.mNotificationAndroidBundle.containsKey("style"));
    }
}
