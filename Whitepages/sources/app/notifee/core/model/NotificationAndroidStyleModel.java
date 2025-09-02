package app.notifee.core.model;

import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.text.HtmlCompat;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.salesforce.marketingcloud.storage.db.i;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import n.o.t.i.f.e.e.l;

@Keep
public class NotificationAndroidStyleModel {
    private static final String TAG = "NotificationAndroidStyle";
    private Bundle mNotificationAndroidStyleBundle;

    private NotificationAndroidStyleModel(Bundle bundle) {
        this.mNotificationAndroidStyleBundle = bundle;
    }

    public static NotificationAndroidStyleModel fromBundle(Bundle bundle) {
        return new NotificationAndroidStyleModel(bundle);
    }

    private Task<NotificationCompat.Style> getBigPictureStyleTask(Executor executor) {
        return Tasks.call(executor, new NotificationAndroidStyleModel$$ExternalSyntheticLambda0(this));
    }

    private NotificationCompat.BigTextStyle getBigTextStyle() {
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        if (this.mNotificationAndroidStyleBundle.containsKey("text")) {
            bigTextStyle = bigTextStyle.bigText(HtmlCompat.fromHtml(this.mNotificationAndroidStyleBundle.getString("text"), 0));
        }
        if (this.mNotificationAndroidStyleBundle.containsKey("title")) {
            bigTextStyle = bigTextStyle.setBigContentTitle(HtmlCompat.fromHtml(this.mNotificationAndroidStyleBundle.getString("title"), 0));
        }
        return this.mNotificationAndroidStyleBundle.containsKey("summary") ? bigTextStyle.setSummaryText(HtmlCompat.fromHtml(this.mNotificationAndroidStyleBundle.getString("summary"), 0)) : bigTextStyle;
    }

    private NotificationCompat.InboxStyle getInboxStyle() {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        if (this.mNotificationAndroidStyleBundle.containsKey("title")) {
            inboxStyle = inboxStyle.setBigContentTitle(HtmlCompat.fromHtml(this.mNotificationAndroidStyleBundle.getString("title"), 0));
        }
        if (this.mNotificationAndroidStyleBundle.containsKey("summary")) {
            inboxStyle = inboxStyle.setSummaryText(HtmlCompat.fromHtml(this.mNotificationAndroidStyleBundle.getString("summary"), 0));
        }
        ArrayList<String> stringArrayList = this.mNotificationAndroidStyleBundle.getStringArrayList("lines");
        int i = 0;
        while (true) {
            Objects.requireNonNull(stringArrayList);
            if (i >= stringArrayList.size()) {
                return inboxStyle;
            }
            inboxStyle = inboxStyle.addLine(HtmlCompat.fromHtml(stringArrayList.get(i), 0));
            i++;
        }
    }

    private Task<NotificationCompat.Style> getMessagingStyleTask(Executor executor) {
        return Tasks.call(executor, new NotificationAndroidStyleModel$$ExternalSyntheticLambda2(this, executor));
    }

    private static Task<Person> getPerson(Executor executor, Bundle bundle) {
        return Tasks.call(executor, new NotificationAndroidStyleModel$$ExternalSyntheticLambda1(bundle));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0076 A[SYNTHETIC, Splitter:B:19:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.core.app.NotificationCompat.Style lambda$getBigPictureStyleTask$1() throws java.lang.Exception {
        /*
            r9 = this;
            androidx.core.app.NotificationCompat$BigPictureStyle r0 = new androidx.core.app.NotificationCompat$BigPictureStyle
            r0.<init>()
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r2 = "picture"
            boolean r1 = r1.containsKey(r2)
            r3 = 10
            java.lang.String r5 = "NotificationAndroidStyle"
            r6 = 0
            if (r1 == 0) goto L_0x005d
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r1 = r1.getString(r2)
            java.util.Objects.requireNonNull(r1)
            com.google.android.gms.tasks.Task r2 = n.o.t.i.f.e.e.n.a(r1)     // Catch:{ TimeoutException -> 0x002c, Exception -> 0x002a }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ TimeoutException -> 0x002c, Exception -> 0x002a }
            java.lang.Object r2 = com.google.android.gms.tasks.Tasks.await(r2, r3, r7)     // Catch:{ TimeoutException -> 0x002c, Exception -> 0x002a }
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2     // Catch:{ TimeoutException -> 0x002c, Exception -> 0x002a }
            goto L_0x0058
        L_0x002a:
            r2 = move-exception
            goto L_0x002e
        L_0x002c:
            r2 = move-exception
            goto L_0x0043
        L_0x002e:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "An error occurred whilst trying to retrieve a big picture style image: "
            r7.append(r8)
            r7.append(r1)
            java.lang.String r1 = r7.toString()
            app.notifee.core.Logger.e((java.lang.String) r5, (java.lang.String) r1, (java.lang.Exception) r2)
            goto L_0x0057
        L_0x0043:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Timeout occurred whilst trying to retrieve a big picture style image: "
            r7.append(r8)
            r7.append(r1)
            java.lang.String r1 = r7.toString()
            app.notifee.core.Logger.e((java.lang.String) r5, (java.lang.String) r1, (java.lang.Exception) r2)
        L_0x0057:
            r2 = r6
        L_0x0058:
            if (r2 == 0) goto L_0x005d
            r0.bigPicture(r2)
        L_0x005d:
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r2 = "largeIcon"
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L_0x0073
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r1 = r1.getString(r2)
            if (r1 != 0) goto L_0x0074
            r0.bigLargeIcon(r6)
            goto L_0x0074
        L_0x0073:
            r1 = r6
        L_0x0074:
            if (r1 == 0) goto L_0x00b6
            com.google.android.gms.tasks.Task r2 = n.o.t.i.f.e.e.n.a(r1)     // Catch:{ TimeoutException -> 0x0086, Exception -> 0x0084 }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ TimeoutException -> 0x0086, Exception -> 0x0084 }
            java.lang.Object r2 = com.google.android.gms.tasks.Tasks.await(r2, r3, r7)     // Catch:{ TimeoutException -> 0x0086, Exception -> 0x0084 }
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2     // Catch:{ TimeoutException -> 0x0086, Exception -> 0x0084 }
            r6 = r2
            goto L_0x00b1
        L_0x0084:
            r2 = move-exception
            goto L_0x0088
        L_0x0086:
            r2 = move-exception
            goto L_0x009d
        L_0x0088:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "An error occurred whilst trying to retrieve a big picture style large icon: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            app.notifee.core.Logger.e((java.lang.String) r5, (java.lang.String) r1, (java.lang.Exception) r2)
            goto L_0x00b1
        L_0x009d:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Timeout occurred whilst trying to retrieve a big picture style large icon: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            app.notifee.core.Logger.e((java.lang.String) r5, (java.lang.String) r1, (java.lang.Exception) r2)
        L_0x00b1:
            if (r6 == 0) goto L_0x00b6
            r0.bigLargeIcon(r6)
        L_0x00b6:
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r2 = "title"
            boolean r1 = r1.containsKey(r2)
            r3 = 0
            if (r1 == 0) goto L_0x00cf
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r1 = r1.getString(r2)
            android.text.Spanned r1 = androidx.core.text.HtmlCompat.fromHtml(r1, r3)
            androidx.core.app.NotificationCompat$BigPictureStyle r0 = r0.setBigContentTitle(r1)
        L_0x00cf:
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r2 = "summary"
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L_0x00e7
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r1 = r1.getString(r2)
            android.text.Spanned r1 = androidx.core.text.HtmlCompat.fromHtml(r1, r3)
            androidx.core.app.NotificationCompat$BigPictureStyle r0 = r0.setSummaryText(r1)
        L_0x00e7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: app.notifee.core.model.NotificationAndroidStyleModel.lambda$getBigPictureStyleTask$1():androidx.core.app.NotificationCompat$Style");
    }

    /* access modifiers changed from: private */
    public NotificationCompat.Style lambda$getMessagingStyleTask$2(Executor executor) throws Exception {
        Person person;
        Bundle bundle = this.mNotificationAndroidStyleBundle.getBundle("person");
        Objects.requireNonNull(bundle);
        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle((Person) Tasks.await(getPerson(executor, bundle), 20, TimeUnit.SECONDS));
        if (this.mNotificationAndroidStyleBundle.containsKey("title")) {
            messagingStyle = messagingStyle.setConversationTitle(HtmlCompat.fromHtml(this.mNotificationAndroidStyleBundle.getString("title"), 0));
        }
        if (this.mNotificationAndroidStyleBundle.containsKey("group")) {
            messagingStyle = messagingStyle.setGroupConversation(this.mNotificationAndroidStyleBundle.getBoolean("group"));
        }
        ArrayList parcelableArrayList = this.mNotificationAndroidStyleBundle.getParcelableArrayList(i.e);
        int i = 0;
        while (true) {
            Objects.requireNonNull(parcelableArrayList);
            if (i >= parcelableArrayList.size()) {
                return messagingStyle;
            }
            Bundle bundle2 = (Bundle) parcelableArrayList.get(i);
            long b = l.b(bundle2.get("timestamp"));
            if (bundle2.containsKey("person")) {
                Bundle bundle3 = bundle2.getBundle("person");
                Objects.requireNonNull(bundle3);
                person = (Person) Tasks.await(getPerson(executor, bundle3), 20, TimeUnit.SECONDS);
            } else {
                person = null;
            }
            messagingStyle = messagingStyle.addMessage(HtmlCompat.fromHtml(bundle2.getString("text"), 0), b, person);
            i++;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ androidx.core.app.Person lambda$getPerson$0(android.os.Bundle r7) throws java.lang.Exception {
        /*
            java.lang.String r0 = "NotificationAndroidStyle"
            androidx.core.app.Person$Builder r1 = new androidx.core.app.Person$Builder
            r1.<init>()
            java.lang.String r2 = "name"
            java.lang.String r2 = r7.getString(r2)
            r1.setName(r2)
            java.lang.String r2 = "id"
            boolean r3 = r7.containsKey(r2)
            if (r3 == 0) goto L_0x001f
            java.lang.String r2 = r7.getString(r2)
            r1.setKey(r2)
        L_0x001f:
            java.lang.String r2 = "bot"
            boolean r3 = r7.containsKey(r2)
            if (r3 == 0) goto L_0x002e
            boolean r2 = r7.getBoolean(r2)
            r1.setBot(r2)
        L_0x002e:
            java.lang.String r2 = "important"
            boolean r3 = r7.containsKey(r2)
            if (r3 == 0) goto L_0x003d
            boolean r2 = r7.getBoolean(r2)
            r1.setImportant(r2)
        L_0x003d:
            java.lang.String r2 = "icon"
            boolean r3 = r7.containsKey(r2)
            if (r3 == 0) goto L_0x0092
            java.lang.String r2 = r7.getString(r2)
            java.util.Objects.requireNonNull(r2)
            com.google.android.gms.tasks.Task r3 = n.o.t.i.f.e.e.n.a(r2)     // Catch:{ TimeoutException -> 0x005d, Exception -> 0x005b }
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ TimeoutException -> 0x005d, Exception -> 0x005b }
            r5 = 10
            java.lang.Object r3 = com.google.android.gms.tasks.Tasks.await(r3, r5, r4)     // Catch:{ TimeoutException -> 0x005d, Exception -> 0x005b }
            android.graphics.Bitmap r3 = (android.graphics.Bitmap) r3     // Catch:{ TimeoutException -> 0x005d, Exception -> 0x005b }
            goto L_0x0089
        L_0x005b:
            r3 = move-exception
            goto L_0x005f
        L_0x005d:
            r3 = move-exception
            goto L_0x0074
        L_0x005f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "An error occurred whilst trying to retrieve a person icon: "
            r4.append(r5)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            app.notifee.core.Logger.e((java.lang.String) r0, (java.lang.String) r2, (java.lang.Exception) r3)
            goto L_0x0088
        L_0x0074:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Timeout occurred whilst trying to retrieve a person icon: "
            r4.append(r5)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            app.notifee.core.Logger.e((java.lang.String) r0, (java.lang.String) r2, (java.lang.Exception) r3)
        L_0x0088:
            r3 = 0
        L_0x0089:
            if (r3 == 0) goto L_0x0092
            androidx.core.graphics.drawable.IconCompat r0 = androidx.core.graphics.drawable.IconCompat.createWithAdaptiveBitmap(r3)
            r1.setIcon(r0)
        L_0x0092:
            java.lang.String r0 = "uri"
            boolean r2 = r7.containsKey(r0)
            if (r2 == 0) goto L_0x00a1
            java.lang.String r7 = r7.getString(r0)
            r1.setUri(r7)
        L_0x00a1:
            androidx.core.app.Person r7 = r1.build()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: app.notifee.core.model.NotificationAndroidStyleModel.lambda$getPerson$0(android.os.Bundle):androidx.core.app.Person");
    }

    public Task<NotificationCompat.Style> getStyleTask(Executor executor) {
        int a = l.a(this.mNotificationAndroidStyleBundle.get("type"));
        if (a == 0) {
            return getBigPictureStyleTask(executor);
        }
        if (a == 1) {
            return Tasks.forResult(getBigTextStyle());
        }
        if (a == 2) {
            return Tasks.forResult(getInboxStyle());
        }
        if (a != 3) {
            return null;
        }
        return getMessagingStyleTask(executor);
    }

    public Bundle toBundle() {
        return (Bundle) this.mNotificationAndroidStyleBundle.clone();
    }
}
