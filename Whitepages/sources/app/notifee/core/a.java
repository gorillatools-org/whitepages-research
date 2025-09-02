package app.notifee.core;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import androidx.core.app.NotificationManagerCompat;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import n.o.t.i.f.e.e.b;
import n.o.t.i.f.e.e.c;
import n.o.t.i.f.e.e.d;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.l;
import n.o.t.i.f.e.e.n;

public abstract class a {
    public static ExecutorService a = Executors.newCachedThreadPool();

    public static Task a(c cVar) {
        return Tasks.call(a, new a$$ExternalSyntheticLambda7(cVar));
    }

    public static List c() {
        List<NotificationChannelGroup> notificationChannelGroups = NotificationManagerCompat.from(e.a).getNotificationChannelGroups();
        if (notificationChannelGroups.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(notificationChannelGroups.size());
        for (NotificationChannelGroup a2 : notificationChannelGroups) {
            arrayList.add(a(a2));
        }
        return arrayList;
    }

    public static List d() {
        List<NotificationChannel> notificationChannels = NotificationManagerCompat.from(e.a).getNotificationChannels();
        if (notificationChannels.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(notificationChannels.size());
        for (NotificationChannel a2 : notificationChannels) {
            arrayList.add(a(a2));
        }
        return arrayList;
    }

    public static Bundle e(String str) {
        return a(NotificationManagerCompat.from(e.a).getNotificationChannel(str));
    }

    public static Bundle f(String str) {
        return a(NotificationManagerCompat.from(e.a).getNotificationChannelGroup(str));
    }

    public static Task a(b bVar) {
        return Tasks.call(a, new a$$ExternalSyntheticLambda3(bVar));
    }

    public static Boolean g(String str) {
        NotificationChannel notificationChannel = NotificationManagerCompat.from(e.a).getNotificationChannel(str);
        if (notificationChannel == null) {
            return Boolean.FALSE;
        }
        return Boolean.valueOf(notificationChannel.getImportance() == 0);
    }

    public static Boolean h(String str) {
        return Boolean.valueOf(NotificationManagerCompat.from(e.a).getNotificationChannel(str) != null);
    }

    public static Task a(List list) {
        return Tasks.call(a, new a$$ExternalSyntheticLambda10(list));
    }

    public static Task a(String str) {
        return Tasks.call(a, new a$$ExternalSyntheticLambda4(str));
    }

    public static Task a() {
        return Tasks.call(a, new a$$ExternalSyntheticLambda9());
    }

    public static Void b(c cVar) {
        Integer num;
        long[] jArr;
        String str;
        String str2;
        String string = cVar.a.getString("id");
        Objects.requireNonNull(string);
        String string2 = cVar.a.getString("name");
        Objects.requireNonNull(string2);
        if (cVar.a.containsKey("importance")) {
            num = Integer.valueOf(l.a(cVar.a.get("importance")));
        } else {
            num = 3;
        }
        NotificationChannel notificationChannel = new NotificationChannel(string, string2, num.intValue());
        notificationChannel.setShowBadge(cVar.a.getBoolean("badge", true));
        notificationChannel.setBypassDnd(cVar.a.getBoolean("bypassDnd", false));
        notificationChannel.setDescription(cVar.a.getString("description"));
        notificationChannel.setGroup(cVar.a.getString("groupId"));
        notificationChannel.enableLights(cVar.a.getBoolean("lights", true));
        if (cVar.a() != null) {
            notificationChannel.setLightColor(cVar.a().intValue());
        }
        notificationChannel.setLockscreenVisibility(cVar.a.containsKey("visibility") ? l.a(cVar.a.get("visibility")) : 0);
        notificationChannel.enableVibration(cVar.a.getBoolean("vibration", true));
        if (!cVar.a.containsKey("vibrationPattern")) {
            jArr = new long[0];
        } else {
            ArrayList parcelableArrayList = cVar.a.getParcelableArrayList("vibrationPattern");
            Objects.requireNonNull(parcelableArrayList);
            long[] jArr2 = new long[parcelableArrayList.size()];
            for (int i = 0; i < parcelableArrayList.size(); i++) {
                jArr2[i] = ((Integer) parcelableArrayList.get(i)).longValue();
            }
            jArr = jArr2;
        }
        if (jArr.length > 0) {
            notificationChannel.setVibrationPattern(jArr);
        }
        if (!cVar.a.containsKey("sound")) {
            str = null;
        } else {
            str = cVar.a.getString("sound");
        }
        if (str != null) {
            if (!cVar.a.containsKey("sound")) {
                str2 = null;
            } else {
                str2 = cVar.a.getString("sound");
            }
            Uri c = n.c(str2);
            if (c != null) {
                notificationChannel.setSound(c, new AudioAttributes.Builder().setUsage(5).setContentType(4).build());
            } else {
                Logger.w("ChannelManager", "Unable to retrieve sound for channel, sound was specified as: " + notificationChannel.getSound());
            }
        } else {
            notificationChannel.setSound((Uri) null, (AudioAttributes) null);
        }
        NotificationManagerCompat.from(e.a).createNotificationChannel(notificationChannel);
        return null;
    }

    public static Bundle a(NotificationChannel notificationChannel) {
        String str = null;
        if (notificationChannel == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", notificationChannel.getId());
        bundle.putString("name", notificationChannel.getName().toString());
        bundle.putBoolean("badge", notificationChannel.canShowBadge());
        bundle.putBoolean("bypassDnd", notificationChannel.canBypassDnd());
        if (notificationChannel.getDescription() != null) {
            bundle.putString("description", notificationChannel.getDescription());
        }
        if (notificationChannel.getGroup() != null) {
            bundle.putString("groupId", notificationChannel.getGroup());
        }
        bundle.putInt("importance", notificationChannel.getImportance());
        bundle.putBoolean("lights", notificationChannel.shouldShowLights());
        bundle.putBoolean("vibration", notificationChannel.shouldVibrate());
        bundle.putBoolean("blocked", notificationChannel.getImportance() == 0);
        if (notificationChannel.getSound() != null) {
            bundle.putString("soundURI", notificationChannel.getSound().toString());
            Uri sound = notificationChannel.getSound();
            if (sound != null && sound.toString().contains("android.resource")) {
                String lastPathSegment = sound.getLastPathSegment();
                try {
                    int intValue = Integer.valueOf(lastPathSegment).intValue();
                    Logger.e("ResourceUtils", "Loaded sound by resource id. New app builds will fail to play sound. Create a new channel to resolve. Issue #341");
                    if (intValue != 0) {
                        TypedValue typedValue = new TypedValue();
                        e.a.getResources().getValue(intValue, typedValue, true);
                        CharSequence charSequence = typedValue.string;
                        if (charSequence != null || charSequence.length() > 0) {
                            str = charSequence.toString().replace("res/raw/", "");
                        }
                    }
                } catch (NumberFormatException unused) {
                    str = lastPathSegment;
                }
            }
            if (str != null) {
                bundle.putString("sound", str);
            }
        }
        if (notificationChannel.getLightColor() != 0) {
            int lightColor = notificationChannel.getLightColor();
            String str2 = (String) d.a.get(Integer.valueOf(lightColor));
            if (str2 == null) {
                str2 = "#" + Integer.toHexString(lightColor).substring(2);
            }
            bundle.putString("lightColor", str2);
        }
        long[] vibrationPattern = notificationChannel.getVibrationPattern();
        if (vibrationPattern != null && vibrationPattern.length > 0) {
            try {
                int[] iArr = new int[vibrationPattern.length];
                for (int i = 0; i < vibrationPattern.length; i++) {
                    iArr[i] = (int) vibrationPattern[i];
                }
                bundle.putIntArray("vibrationPattern", iArr);
            } catch (Exception e) {
                Logger.e("ChannelManager", "Unable to convert Vibration Pattern to Channel Bundle", e);
            }
        }
        int lockscreenVisibility = notificationChannel.getLockscreenVisibility();
        if (lockscreenVisibility != -1000) {
            bundle.putInt("visibility", lockscreenVisibility);
        }
        return bundle;
    }

    public static /* synthetic */ Void d(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Tasks.await(a((c) it.next()));
        }
        return null;
    }

    public static Task d(String str) {
        return Tasks.call(a, new a$$ExternalSyntheticLambda8(str));
    }

    public static /* synthetic */ Void c(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Tasks.await(a((b) it.next()));
        }
        return null;
    }

    public static Task c(String str) {
        return Tasks.call(a, new a$$ExternalSyntheticLambda11(str));
    }

    public static Task b(List list) {
        return Tasks.call(a, new a$$ExternalSyntheticLambda6(list));
    }

    public static Void b(b bVar) {
        int i = Build.VERSION.SDK_INT;
        String string = bVar.a.getString("id");
        Objects.requireNonNull(string);
        String string2 = bVar.a.getString("name");
        Objects.requireNonNull(string2);
        NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(string, string2);
        if (i >= 28 && bVar.a.getString("description") != null) {
            notificationChannelGroup.setDescription(bVar.a.getString("description"));
        }
        NotificationManagerCompat.from(e.a).createNotificationChannelGroup(notificationChannelGroup);
        return null;
    }

    public static Bundle a(NotificationChannelGroup notificationChannelGroup) {
        if (notificationChannelGroup == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", notificationChannelGroup.getId());
        bundle.putString("name", notificationChannelGroup.getName().toString());
        List<NotificationChannel> channels = notificationChannelGroup.getChannels();
        ArrayList arrayList = new ArrayList(channels.size());
        for (NotificationChannel a2 : channels) {
            arrayList.add(a(a2));
        }
        bundle.putParcelableArrayList("channels", arrayList);
        if (Build.VERSION.SDK_INT >= 28) {
            bundle.putBoolean("blocked", notificationChannelGroup.isBlocked());
            bundle.putString("description", notificationChannelGroup.getDescription());
        } else {
            bundle.putBoolean("blocked", false);
        }
        return bundle;
    }

    public static Task b() {
        return Tasks.call(a, new a$$ExternalSyntheticLambda5());
    }

    public static Task b(String str) {
        return Tasks.call(a, new a$$ExternalSyntheticLambda12(str));
    }
}
