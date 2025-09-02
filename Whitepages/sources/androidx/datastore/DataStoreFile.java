package androidx.datastore;

import android.content.Context;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;

public abstract class DataStoreFile {
    public static final File dataStoreFile(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "fileName");
        File filesDir = context.getApplicationContext().getFilesDir();
        return new File(filesDir, "datastore/" + str);
    }
}
