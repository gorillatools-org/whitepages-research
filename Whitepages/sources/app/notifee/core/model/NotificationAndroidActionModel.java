package app.notifee.core.model;

import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import java.util.ArrayList;
import java.util.Objects;

@Keep
public class NotificationAndroidActionModel {
    private Bundle mNotificationAndroidActionBundle;

    private NotificationAndroidActionModel(Bundle bundle) {
        this.mNotificationAndroidActionBundle = bundle;
    }

    public static NotificationAndroidActionModel fromBundle(Bundle bundle) {
        return new NotificationAndroidActionModel(bundle);
    }

    public String getIcon() {
        return this.mNotificationAndroidActionBundle.getString("icon");
    }

    public NotificationAndroidPressActionModel getPressAction() {
        return NotificationAndroidPressActionModel.fromBundle(this.mNotificationAndroidActionBundle.getBundle("pressAction"));
    }

    public RemoteInput getRemoteInput(NotificationCompat.Action.Builder builder) {
        if (!this.mNotificationAndroidActionBundle.containsKey("input")) {
            return null;
        }
        Bundle bundle = this.mNotificationAndroidActionBundle.getBundle("input");
        Objects.requireNonNull(bundle);
        RemoteInput.Builder builder2 = new RemoteInput.Builder("app.notifee.core.ReceiverService.REMOTE_INPUT_RECEIVER_KEY");
        if (bundle.containsKey("allowFreeFormInput")) {
            builder2.setAllowFreeFormInput(bundle.getBoolean("allowFreeFormInput"));
        }
        if (bundle.containsKey("allowGeneratedReplies")) {
            builder.setAllowGeneratedReplies(bundle.getBoolean("allowGeneratedReplies"));
        }
        if (bundle.containsKey(ReactTextInputShadowNode.PROP_PLACEHOLDER)) {
            builder2.setLabel(bundle.getCharSequence(ReactTextInputShadowNode.PROP_PLACEHOLDER));
        }
        if (bundle.containsKey("choices")) {
            ArrayList<String> stringArrayList = bundle.getStringArrayList("choices");
            Objects.requireNonNull(stringArrayList);
            builder2.setChoices((CharSequence[]) stringArrayList.toArray(new CharSequence[stringArrayList.size()]));
        }
        if (!bundle.containsKey("editableChoices")) {
            builder2.setEditChoicesBeforeSending(0);
        } else if (bundle.getBoolean("editableChoices")) {
            builder2.setEditChoicesBeforeSending(2);
        } else {
            builder2.setEditChoicesBeforeSending(1);
        }
        return builder2.build();
    }

    public String getTitle() {
        String string = this.mNotificationAndroidActionBundle.getString("title");
        Objects.requireNonNull(string);
        return string;
    }

    public Bundle toBundle() {
        return (Bundle) this.mNotificationAndroidActionBundle.clone();
    }
}
