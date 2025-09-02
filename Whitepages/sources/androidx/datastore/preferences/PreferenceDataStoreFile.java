package androidx.datastore.preferences;

import android.content.Context;
import androidx.datastore.DataStoreFile;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;

public abstract class PreferenceDataStoreFile {
    public static final File preferencesDataStoreFile(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        return DataStoreFile.dataStoreFile(context, str + ".preferences_pb");
    }
}
