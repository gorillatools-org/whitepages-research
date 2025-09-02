package n.o.t.i.f.e.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import app.notifee.core.Logger;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.hermes.intl.Constants;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.HashMap;
import java.util.Map;

public abstract class n {
    public static volatile Map a;

    public class a extends BaseBitmapDataSubscriber {
        public final /* synthetic */ TaskCompletionSource a;
        public final /* synthetic */ String b;

        public a(TaskCompletionSource taskCompletionSource, String str) {
            this.a = taskCompletionSource;
            this.b = str;
        }

        public void onFailureImpl(DataSource dataSource) {
            Logger.e("ResourceUtils", "Failed to load an image: " + this.b, dataSource.getFailureCause());
            this.a.setResult(null);
        }

        public void onNewResultImpl(Bitmap bitmap) {
            this.a.setResult(bitmap);
        }
    }

    public static Map a() {
        if (a == null) {
            synchronized (n.class) {
                try {
                    if (a == null) {
                        a = new HashMap();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return a;
    }

    public static Uri b(String str) {
        int a2 = a(str, "drawable");
        if (a2 > 0) {
            return new Uri.Builder().scheme("res").path(String.valueOf(a2)).build();
        }
        return Uri.EMPTY;
    }

    public static Uri c(String str) {
        Context context = e.a;
        if (str == null) {
            return null;
        }
        if (str.contains("://")) {
            return Uri.parse(str);
        }
        if (str.equalsIgnoreCase(Constants.COLLATION_DEFAULT)) {
            return RingtoneManager.getDefaultUri(2);
        }
        int a2 = a(str, "raw");
        if (a2 == 0 && str.contains(".")) {
            a2 = a(str.substring(0, str.lastIndexOf(46)), "raw");
        }
        if (a2 == 0) {
            return null;
        }
        return Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + str);
    }

    public static Task a(String str) {
        Uri uri;
        String str2;
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        Task task = taskCompletionSource.getTask();
        if (!str.contains(RemoteSettings.FORWARD_SLASH_STRING)) {
            int a2 = a(str, "mipmap");
            if (a2 == 0) {
                a2 = a(str, "drawable");
            }
            if (a2 == 0) {
                str2 = null;
            } else if (a2 > 0) {
                str2 = new Uri.Builder().scheme("res").path(String.valueOf(a2)).build().toString();
            } else {
                str2 = Uri.EMPTY.toString();
            }
            if (str2 == null) {
                taskCompletionSource.setResult(null);
                return task;
            }
            try {
                uri = Uri.parse(str2);
                if (uri.getScheme() == null) {
                    uri = b(str2);
                }
            } catch (Exception unused) {
                uri = b(str2);
            }
        } else {
            try {
                Uri parse = Uri.parse(str);
                if (parse.getScheme() == null) {
                    parse = b(str);
                }
                uri = parse;
            } catch (Exception unused2) {
                uri = b(str);
            }
        }
        ImageRequest build = ImageRequestBuilder.newBuilderWithSource(uri).build();
        if (!Fresco.hasBeenInitialized()) {
            Logger.w("ResourceUtils", "Fresco initializing natively by Notifee");
            Fresco.initialize(e.a);
        }
        Fresco.getImagePipeline().fetchDecodedImage(build, e.a).subscribe(new a(taskCompletionSource, str), CallerThreadExecutor.getInstance());
        return task;
    }

    public static int a(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        String replace = str.toLowerCase().replace("-", "_");
        String str3 = replace + "_" + str2;
        synchronized (n.class) {
            try {
                if (a().containsKey(str3)) {
                    int intValue = ((Integer) a().get(str3)).intValue();
                    return intValue;
                }
                Context context = e.a;
                int identifier = context.getResources().getIdentifier(replace, str2, context.getPackageName());
                a().put(str3, Integer.valueOf(identifier));
                return identifier;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
